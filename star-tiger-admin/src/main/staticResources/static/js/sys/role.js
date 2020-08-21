layui.use(["table", "form", "layer", "laypage", "util", "transfer"], function () {
    let table = layui.table,
        form = layui.form,
        layer = layui.layer,
        laypage = layui.laypage,
        util = layui.util,
        transfer = layui.transfer
    ;

    let infoWinOptions = {
        type: 1,
        id: "infoWin",
        content: $("#info-win"),
        // btn: ["保存"],
        offset: "100px"
    }

    let dataTableOptions = {
        id: "dataTable",
        elem: "#data-table",
        toolbar: "#top-tool-bar",
        url: contextPath + "/sys/role/data",
        loading: true,
        page: {
            limit: 10,
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh']
        },
        cols: [[
            {field: "roleCode", title: "角色代码"},
            {field: "roleName", title: "角色名"},
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

    let resourceTransferOptions = {
        id: "resource-transfer",
        elem: "#resource-transfer",
        title: ['所有资源', '已选资源'],
        showSearch: true
    }

    $(function () {
        loadDataTable();
        util.event("lay-event", {
            search: function () {
                loadDataTable();
            }
        });
    });

    let infoWinIndex = undefined;

    let dataTable = undefined;

    let resourceTransfer = undefined;

    let loadDataTable = function () {
        let mainInnerHeight = $(".layuimini-main").innerHeight();
        let searchOuterHeight = $(".table-search-fieldset").outerHeight();
        let options = {};
        $.extend(options, dataTableOptions);
        options.height = "full-" + (searchOuterHeight + 60);
        options.where = form.val("search-form");
        if (dataTable) {
            dataTable.reload(options);
        } else {
            dataTable = table.render(options);
        }
    };

    table.on("toolbar(data-table)", function (obj) {
        let layEvent = obj.event;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 10) * 7;

        if (layEvent === "add") {
            form.val("info-save-form", {
                roleCode: "",
                roleName: ""
            });
            let winOptions = {};
            $.extend(winOptions, infoWinOptions);
            winOptions.title = "新增角色";
            winOptions.area = width + "px";
            winOptions.success = function (layero, index) {
                ResourceRestApi.getAllTransferData(
                    function (data, textStatus, xhr) {
                        let code = data.code;
                        let msg = data.msg;
                        if (code !== "10000") {
                            layer.msg(msg);
                            return;
                        }
                        let options = {};
                        $.extend(options, resourceTransferOptions);
                        options.data = data.data;
                        options.value = [];
                        if (resourceTransfer) {
                            transfer.reload(resourceTransferOptions.id, options);
                        } else {
                            resourceTransfer = transfer.render(options);
                        }
                    }
                );
            };
            infoWinIndex = layer.open(winOptions);
        }
    });

    table.on("tool(data-table)", function (obj) {
        let data = obj.data;
        let layEvent = obj.event;
        let tr = obj.tr;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 10) * 7;

        let roleFlow = data.roleFlow;

        if (layEvent === "edit") {
            RoleRestApi.read(roleFlow, function (data, textStatus, xhr) {
                    let code = data.code;
                    let msg = data.msg;
                    let role = data.data.role;
                    let resourceFlows = data.data.resourceFlows;
                    if (code !== "10000") {
                        layer.msg(msg);
                    } else {
                        form.val("info-save-form", role);
                        let winOptions = {};
                        $.extend(winOptions, infoWinOptions);
                        winOptions.title = "编辑角色";
                        winOptions.area = width + "px";
                        winOptions.success = function (layero, index) {
                            ResourceRestApi.getAllTransferData(
                                function (data, textStatus, xhr) {
                                    let code = data.code;
                                    let msg = data.msg;
                                    if (code !== "10000") {
                                        layer.msg(msg);
                                        return;
                                    }
                                    let options = {};
                                    $.extend(options, resourceTransferOptions);
                                    options.data = data.data;
                                    options.value = resourceFlows;
                                    if (resourceTransfer) {
                                        transfer.reload(resourceTransferOptions.id, options);
                                    } else {
                                        resourceTransfer = transfer.render(options);
                                    }
                                }
                            );
                        };
                        infoWinIndex = layer.open(winOptions);
                    }
                }
            );
        }
    });

    form.on('submit(info-save-form-submit-btn)', function(data) {
        let role = data.field;
        let selectResourceData = transfer.getData(resourceTransferOptions.id);
        let resourceFlows = [];
        $.each(selectResourceData, function (i, n) {
            resourceFlows.push(n.value);
        });
        if (role.roleFlow && role.roleFlow !== "") {
            RoleRestApi.mod(role, resourceFlows, function (data, textStatus, xhr) {
                loadDataTable();
                layer.close(infoWinIndex);
            });
        } else {
            RoleRestApi.add(role, resourceFlows, function (data, textStatus, xhr) {
                loadDataTable();
                layer.close(infoWinIndex);
            });
        }
        return false;
    });

});