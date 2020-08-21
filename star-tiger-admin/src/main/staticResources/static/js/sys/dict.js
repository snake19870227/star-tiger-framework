layui.use(["table", "form", "layer", "util"], function () {

    let table = layui.table,
        form = layui.form,
        layer = layui.layer,
        util = layui.util
    ;

    let infoWinOptions = {
        type: 1,
        id: "infoWin",
        content: $("#info-win"),
        btn: ["保存"],
        offset: "100px"
    }

    let itemWinOptions = {
        type: 1,
        id: "itemWin",
        content: $("#item-win"),
        shadeClose: true
    }

    let itemInfoWinOptions = {
        type: 1,
        id: "itemInfoWin",
        content: $("#item-info-win"),
        btn: ["保存"],
        offset: "100px"
    }

    let dataTableOptions = {
        id: "dataTable",
        elem: "#data-table",
        toolbar: "#top-tool-bar",
        url: contextPath + "/sys/dict/data",
        loading: true,
        page: {
            limit: 10,
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh']
        },
        cols: [[
            {field: "dictCode", title: "字典项目编码"},
            {field: "dictName", title: "字典项目名称"},
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

    let itemTableOptions = {
        id: "itemTable",
        elem: "#item-table",
        toolbar: "#item-top-tool-bar",
        // url: contextPath + "/sys/dict/items",
        loading: true,
        // page: {
        //     limit: 10,
        //     layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh']
        // },
        cols: [[
            {field: "dictItemCode", title: "字典选项编码"},
            {field: "dictItemName", title: "字典选项名称"},
            {title: "操作", toolbar: "#item-record-tool-bar"}
        ]],
        parseData: function (res) {
            let parseData = {};
            if ("10000" === res.code) {
                parseData.code = "0";
            }
            parseData.msg = res.msg;
            parseData.data = res.data;
            return parseData;
        }
    }

    $(function () {
        loadDataTable();
        util.event("lay-event", {
            search: function () {
                loadDataTable();
            }
        });
    });

    let dataTable = undefined;

    let itemTable = undefined;

    let loadDataTable = function () {
        let mainInnerHeight = $(".layuimini-main").innerHeight();
        let searchOuterHeight = $(".table-search-fieldset").outerHeight();
        let options = {};
        $.extend(options, dataTableOptions);
        options.height = "full-" + (searchOuterHeight + 60);
        // options.height = "full-50";
        options.where = form.val("search-form");
        if (dataTable) {
            dataTable.reload(options);
        } else {
            dataTable = table.render(options);
        }
    };

    let loadItemTable = function (dictFlow) {
        let options = {};
        $.extend(options, itemTableOptions);
        options.height = "full-110";
        options.url = contextPath + "/sys/dict/items/" + dictFlow;
        if (itemTable) {
            itemTable.reload(options);
        } else {
            itemTable = table.render(options);
        }
    }

    table.on("toolbar(data-table)", function (obj) {
        let layEvent = obj.event;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 10) * 7;

        if (layEvent === "add") {
            form.val("info-save-form", {
                dictFlow: "",
                dictCode: "",
                dictName: ""
            });
            let winOptions = {};
            $.extend(winOptions, infoWinOptions);
            winOptions.title = "新增";
            winOptions.area = width + "px";
            winOptions.yes = function (index, layero) {
                let dict = form.val("info-save-form");
                DictRestApi.add(dict, function (data, textStatus, xhr) {
                    loadDataTable();
                    layer.close(index);
                });
            };
            layer.open(winOptions);
        }
    });

    table.on("tool(data-table)", function (obj) {
        let data = obj.data;
        let layEvent = obj.event;
        let tr = obj.tr;
        let $body = $("body");
        let width = $body.innerWidth();
        let height = $body.innerHeight();
        width = (width / 10) * 7;

        let dictFlow = data.dictFlow;

        if (layEvent === "edit") {
            DictRestApi.read(dictFlow, function (data, textStatus, xhr) {
                let code = data.code;
                let msg = data.msg;
                let dict = data.data;
                if (code !== "10000") {
                    layer.msg(msg);
                } else {
                    form.val("info-save-form", dict);
                    let winOptions = {};
                    $.extend(winOptions, infoWinOptions);
                    winOptions.title = "编辑";
                    winOptions.area = width + "px";
                    winOptions.yes = function (index, layero) {
                        let dict = form.val("info-save-form");
                        DictRestApi.mod(dict, function (data, textStatus, xhr) {
                            loadDataTable();
                            layer.close(index);
                        });
                    };
                    layer.open(winOptions);
                }
            });
        }

        if (layEvent === "editItem") {
            let winOptions = {};
            $.extend(winOptions, itemWinOptions);
            winOptions.title = "字典选项";
            winOptions.area = [$body.innerWidth() + "px", $body.innerHeight() + "px"];
            winOptions.success = function (layero, index) {
                loadItemTable(dictFlow);
                $("#item-win-dict-flow").val(dictFlow);
            }
            layer.open(winOptions);
        }
    });

    table.on("toolbar(item-table)", function (obj) {
        let layEvent = obj.event;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 10) * 7;

        let dictFlow = $("#item-win-dict-flow").val();

        if (layEvent === "add") {
            form.val("item-info-save-form", {
                dictFlow: dictFlow,
                dictItemFlow: "",
                dictItemCode: "",
                dictItemName: ""
            });
            let winOptions = {};
            $.extend(winOptions, itemInfoWinOptions);
            winOptions.title = "新增";
            winOptions.area = width + "px";
            winOptions.yes = function (index, layero) {
                let dictItem = form.val("item-info-save-form");
                DictRestApi.addItem(dictItem, function (data, textStatus, xhr) {
                    loadItemTable(dictFlow);
                    layer.close(index);
                });
            };
            layer.open(winOptions);
        }
    });

    table.on("tool(item-table)", function (obj) {
        let data = obj.data;
        let layEvent = obj.event;
        let tr = obj.tr;
        let $body = $("body");
        let width = $body.innerWidth();
        let height = $body.innerHeight();
        width = (width / 10) * 7;

        let dictFlow = data.dictFlow;
        let dictItemFlow = data.dictItemFlow;

        if (layEvent === "edit") {
            DictRestApi.readItem(dictItemFlow, function (data, textStatus, xhr) {
                let code = data.code;
                let msg = data.msg;
                let dictItem = data.data;
                if (code !== "10000") {
                    layer.msg(msg);
                } else {
                    form.val("item-info-save-form", dictItem);
                    let winOptions = {};
                    $.extend(winOptions, itemInfoWinOptions);
                    winOptions.title = "编辑";
                    winOptions.area = width + "px";
                    winOptions.yes = function (index, layero) {
                        let dictItem = form.val("item-info-save-form");
                        DictRestApi.modItem(dictItem, function (data, textStatus, xhr) {
                            loadItemTable(dictFlow);
                            layer.close(index);
                        });
                    };
                    layer.open(winOptions);
                }
            });
        }
    });

});