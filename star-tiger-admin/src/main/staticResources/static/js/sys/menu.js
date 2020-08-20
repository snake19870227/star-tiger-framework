layui.use(["form", "layer", "util", "treeTable"], function () {
    let form = layui.form,
        layer = layui.layer,
        util = layui.util,
        transfer = layui.transfer,
        treeTable = layui.treeTable
    ;

    let infoWinOptions = {
        type: 1,
        id: "infoWin",
        content: $("#info-win"),
        btn: ["保存"],
        offset: "100px"
    }

    let dataTableOptions = {
        id: "dataTable",
        elem: '#data-table',
        toolbar: "#top-tool-bar",
        url: contextPath + '/sys/menu/data',
        tree: {
            iconIndex: 0,
            isPidData: true,
            idName: 'menuFlow',
            pidName: 'parentMenuFlow'
        },
        cols: [[
            // {type: "checkbox"},
            {field: "menuName", title: "菜单名称", width: '15%'},
            {field: "menuCode", title: "菜单编码", width: '15%'},
            {field: "menuPath", title: "菜单路径", width: '20%'},
            {field: "menuOrder", title: "菜单排序码", width: '10%'},
            {field: "enableFlag", title: "启用标记", templet: "#enable-flag-span", width: '10%'},
            {title: "操作", toolbar: '#record-tool-bar', width: '30%'}
        ]],
        parseData: function (res) {
            let parseData = {};
            if ("10000" === res.code) {
                parseData.code = "0";
            }
            parseData.msg = res.msg;
            parseData.data = res.data;
            $.each(parseData.data, function (i, n) {
                n.open = true;
            });
            return parseData;
        }
    }

    $(function () {
        loadDataTable();
    });

    let dataTable = undefined;

    let loadDataTable = function () {
        // let mainInnerHeight = $(".layuimini-main").innerHeight();
        // let searchOuterHeight = $(".table-search-fieldset").outerHeight();
        let options = {};
        $.extend(options, dataTableOptions);
        // options.height = "full-" + (searchOuterHeight + 60);
        options.height = "full-50";
        // options.where = form.val("role-data-table");
        if (dataTable) {
            dataTable.reload(options);
        } else {
            dataTable = treeTable.render(options);
        }
    };

    treeTable.on("toolbar(data-table)", function (obj) {
        let layEvent = obj.event;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 10) * 7;

        if (layEvent === "add") {
            form.val("info-save-form", {
                menuFlow: "",
                parentMenuFlow: "",
                menuLevel: 1,
                menuCode: "",
                menuName: "",
                menuPath: "",
                menuOrder: ""
            });
            let winOptions = {};
            $.extend(winOptions, infoWinOptions);
            winOptions.title = "新增一级菜单";
            winOptions.area = width + "px";
            winOptions.yes = function (index, layero) {
                let info = form.val("info-save-form");
                MenuRestApi.addMenu(
                    info,
                    function (data, textStatus, xhr) {
                        loadDataTable();
                        layer.close(index);
                    }
                );
            };
            layer.open(winOptions);
        }
    });

    treeTable.on("tool(data-table)", function (obj) {
        let data = obj.data;
        let layEvent = obj.event;
        let tr = obj.tr;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 10) * 7;

        let menuFlow = data.menuFlow;

        if (layEvent === "edit") {
            MenuRestApi.read(menuFlow, function (data, textStatus, xhr) {
                let code = data.code;
                let msg = data.msg;
                if (code !== "10000") {
                    layer.msg(msg);
                } else {
                    let menu = data.data;
                    form.val("info-save-form", menu);
                    let winOptions = {};
                    $.extend(winOptions, infoWinOptions);
                    winOptions.title = "编辑菜单";
                    winOptions.area = width + "px";
                    winOptions.yes = function (index, layero) {
                        let info = form.val("info-save-form");
                        MenuRestApi.modMenu(info, function (data, textStatus, xhr) {
                            loadDataTable();
                            layer.close(index);
                        })
                    };
                    layer.open(winOptions);
                }
            });
        }

        if (layEvent === "disable") {
            layer.confirm("是否停用菜单？", {},
                function (index) {
                    MenuRestApi.changeEnableFlag(menuFlow, "N",
                        function (data, textStatus, xhr) {
                            let code = data.code;
                            let msg = data.msg;
                            if (code !== "10000") {
                                layer.msg(msg);
                            } else {
                                loadDataTable();
                            }
                        },
                        function (xhr, textStatus, errorThrown) {
                            layer.msg("操作失败");
                        },
                        function (xhr, textStatus) {
                            layer.close(index);
                        }
                    );
                }
            );
        }

        if (layEvent === "enable") {
            layer.confirm("是否启用菜单？", {},
                function (index) {
                    MenuRestApi.changeEnableFlag(menuFlow, "Y",
                        function (data, textStatus, xhr) {
                            let code = data.code;
                            let msg = data.msg;
                            if (code !== "10000") {
                                layer.msg(msg);
                            } else {
                                loadDataTable();
                            }
                        },
                        function (xhr, textStatus, errorThrown) {
                            layer.msg("操作失败");
                        },
                        function (xhr, textStatus) {
                            layer.close(index);
                        }
                    );
                }
            );
        }

        if (layEvent === "add-children") {
            form.val("info-save-form", {
                menuFlow: "",
                parentMenuFlow: menuFlow,
                menuLevel: 2,
                menuCode: "",
                menuName: "",
                menuPath: "",
                menuOrder: ""
            });
            let winOptions = {};
            $.extend(winOptions, infoWinOptions);
            winOptions.title = "新增二级菜单";
            winOptions.area = width + "px";
            winOptions.yes = function (index, layero) {
                let info = form.val("info-save-form");
                MenuRestApi.addMenu(
                    info,
                    function (data, textStatus, xhr) {
                        loadDataTable();
                        layer.close(index);
                    }
                );
            };
            layer.open(winOptions);
        }
    });
});