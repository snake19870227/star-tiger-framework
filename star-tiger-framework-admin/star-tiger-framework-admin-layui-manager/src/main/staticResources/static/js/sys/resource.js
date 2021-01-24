layui.use(["table", "form", "layer", "laypage", "util"], function () {

    let $dataTableCurrent = $("#data-table-current");

    let table = layui.table,
        form = layui.form,
        layer = layui.layer,
        laypage = layui.laypage,
        util = layui.util
    ;

    let infoWinOptions = {
        type: 1,
        id: "infoWin",
        title: "新增资源",
        content: $("#info-win"),
        // btn: ["保存"],
        offset: "100px"
    }

    let dataTableOptions = {
        id: "dataTable",
        elem: "#data-table",
        toolbar: "#top-tool-bar",
        url: contextPath + "/sys/resource/data",
        loading: true,
        page: {
            limit: 10,
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh']
        },
        cols: [[
            {field: "resName", title: "资源名"},
            {field: "resPath", title: "资源地址"},
            {field: "resMethod", title: "访问方式"},
            {field: "enableFlag", title: "启用标记", templet: "#enable-flag-switch"},
            {title: "操作", toolbar: "#record-tool-bar"}
        ]],
        parseData: function (res) {
            let parseData = {};
            if ("10000" === res.code) {
                parseData.code = "0";
            }
            parseData.msg = res.msg;
            parseData.count = res.data.total;
            parseData.data = res.data.records;
            return parseData;
        }
    }

    $(function () {
        loadDataTable();
        util.event("lay-event", {
            search: function(){
                loadDataTable();
            }
        });
    });

    let infoWinIndex = undefined;

    let dataTable = undefined;

    let loadDataTable = function (keepPage) {
        let mainInnerHeight = $(".layuimini-main").innerHeight();
        let searchOuterHeight = $(".table-search-fieldset").outerHeight();
        let options = {};
        $.extend(options, dataTableOptions);
        options.height = "full-" + (searchOuterHeight + 60);
        options.where = form.val("search-form");
        if (dataTable) {
            if (keepPage && $dataTableCurrent && $dataTableCurrent.val()) {
                options.page.curr = $dataTableCurrent.val();
            }
            dataTable.reload(options);
        } else {
            dataTable = table.render(options);
        }
    };

    table.on("toolbar(data-table)", function (obj) {
        let layEvent = obj.event;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 5) * 3;
        if (layEvent === "add") {
            form.val("info-save-form", {
                resFlow: "",
                resName: "",
                resPath: "",
                resMethod: ""
            });
            let winOptions = {};
            $.extend(winOptions, infoWinOptions);
            winOptions.title = "新增";
            winOptions.area = width + "px";
            infoWinIndex = layer.open(winOptions);
        }
    });

    form.on('switch(enable-flag)', function (obj) {
        let checked = obj.elem.checked;
        let value = obj.elem.value;
        let newFlag = (checked ? "Y" : "N");
        let stopChange = function () {
            obj.elem.checked = (!checked);
            form.render('checkbox');
        };
        layer.confirm("是否确认修改？", {},
            function (index) {
                ResourceRestApi.changeEnableStatus(value, newFlag,
                    function (data, textStatus, xhr) {
                        let code = data.code;
                        let msg = data.msg;
                        if (code !== "10000") {
                            layer.msg(msg);
                            obj.elem.checked = (!checked);
                            form.render('checkbox');
                        }
                    },
                    function (xhr, textStatus, errorThrown) {
                        layer.msg("操作失败");
                        stopChange();
                    },
                    function (xhr, textStatus) {
                        layer.close(index);
                    }
                );
            },
            function () {
                stopChange();
            }
        );
    });

    table.on("tool(data-table)", function (obj) {
        let data = obj.data;
        let layEvent = obj.event;
        let tr = obj.tr;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 5) * 3;
        // let height = $body.innerHeight();
        // height = (height / 3);
        if (layEvent === "edit") {
            ResourceRestApi.read(data.resFlow,
                function (data, textStatus, xhr) {
                    let code = data.code;
                    let msg = data.msg;
                    let resInfo = data.data;
                    if (code !== "10000") {
                        layer.msg(msg);
                    } else {
                        form.val("info-save-form", resInfo);
                        let winOptions = {};
                        $.extend(winOptions, infoWinOptions);
                        winOptions.title = "编辑";
                        winOptions.area = width + "px";
                        infoWinIndex = layer.open(winOptions);
                    }
                }
            );
        }
    });

    form.on('submit(info-save-form-submit-btn)', function(data) {
        let resource = data.field;
        if (resource.resFlow && resource.resFlow !== "") {
            ResourceRestApi.mod(resource, function (data, textStatus, xhr) {
                loadDataTable(true);
                layer.close(infoWinIndex);
            });
        } else {
            ResourceRestApi.add(resource, function (data, textStatus, xhr) {
                loadDataTable();
                layer.close(infoWinIndex);
            });
        }
        return false;
    });
});
