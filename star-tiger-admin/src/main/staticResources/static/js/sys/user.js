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
        btn: ["保存"],
        offset: "100px"
    }

    let dataTableOptions = {
        id: "dataTable",
        elem: "#data-table",
        toolbar: "#top-tool-bar",
        url: contextPath + "/sys/user/data",
        loading: true,
        page: {
            limit: 10,
            layout: ['prev', 'page', 'next', 'limit', 'count', 'refresh']
        },
        cols: [[
            {field: "username", title: "用户名"},
            {field: "shortName", title: "昵称"},
            {field: "createDateTime", title: "创建时间"},
            {field: "locked", title: "锁定", templet: "#locked-switch"},
            // {field: "expire_date_time", title: "过期时间"},
            {field: "expired", title: "过期", templet: "#expired-span"},
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

    let roleTransferOptions = {
        id: "role-transfer",
        elem: "#role-transfer",
        title: ['所有角色', '已选角色'],
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

    let dataTable = undefined;

    let roleTransfer = undefined;

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

    table.on("toolbar(data-table)", function (obj) {
        let layEvent = obj.event;
        let $body = $("body");
        let width = $body.innerWidth();
        width = (width / 10) * 7;

        if (layEvent === "add") {
            form.val("info-save-form", {
                userFlow: "",
                username: "",
                shortName: ""
            });
            let winOptions = {};
            $.extend(winOptions, infoWinOptions);
            winOptions.title = "新增";
            winOptions.area = width + "px";
            winOptions.success = function (layero, index) {
                RoleRestApi.getAllRoleTransferData(function (data, textStatus, xhr) {
                    let code = data.code;
                    let msg = data.msg;
                    if (code !== "10000") {
                        layer.msg(msg);
                        return;
                    }
                    let options = {};
                    $.extend(options, roleTransferOptions);
                    options.data = data.data;
                    options.value = [];
                    if (roleTransfer) {
                        transfer.reload(roleTransferOptions.id, options);
                    } else {
                        roleTransfer = transfer.render(options);
                    }
                });
            }
            winOptions.yes = function (index, layero) {
                let user = form.val("info-save-form");
                let selectRoleData = transfer.getData(roleTransferOptions.id);
                let roleFlows = [];
                $.each(selectRoleData, function (i, n) {
                    roleFlows.push(n.value);
                });
                console.log(user);
                console.log(roleFlows);
                UserRestApi.add(user, roleFlows, function (data, textStatus, xhr) {
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
        width = (width / 10) * 7;

        let userFlow = data.userFlow;

        if (layEvent === "edit") {
            UserRestApi.read(userFlow, function (data, textStatus, xhr) {
                let code = data.code;
                let msg = data.msg;
                let user = data.data.user;
                let roleFlows = data.data.roleFlows;
                if (code !== "10000") {
                    layer.msg(msg);
                } else {
                    form.val("info-save-form", user);
                    let winOptions = {};
                    $.extend(winOptions, infoWinOptions);
                    winOptions.title = "编辑";
                    winOptions.area = width + "px";
                    winOptions.success = function (layero, index) {
                        RoleRestApi.getAllRoleTransferData(function (data, textStatus, xhr) {
                            let code = data.code;
                            let msg = data.msg;
                            if (code !== "10000") {
                                layer.msg(msg);
                                return;
                            }
                            let options = {};
                            $.extend(options, roleTransferOptions);
                            options.data = data.data;
                            options.value = roleFlows;
                            if (roleTransfer) {
                                transfer.reload(roleTransferOptions.id, options);
                            } else {
                                roleTransfer = transfer.render(options);
                            }
                        });
                    }
                    winOptions.yes = function (index, layero) {
                        let user = form.val("info-save-form");
                        let selectRoleData = transfer.getData(roleTransferOptions.id);
                        let roleFlows = [];
                        $.each(selectRoleData, function (i, n) {
                            roleFlows.push(n.value);
                        });
                        console.log(user);
                        console.log(roleFlows);
                        UserRestApi.mod(user, roleFlows, function (data, textStatus, xhr) {
                            loadDataTable();
                            layer.close(index);
                        });
                    };
                    layer.open(winOptions);
                }
            });
        }

        if (layEvent === "reset") {
            layer.confirm("是否确认重置密码？", {},
                function (index) {
                    UserRestApi.resetPassword(userFlow,
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
    });

    form.on('switch(locked-switch)', function (obj) {
        let checked = obj.elem.checked;
        let value = obj.elem.value;
        let newFlag = (checked ? "N" : "Y");
        let stopChange = function () {
            obj.elem.checked = (!checked);
            form.render('checkbox');
        };
        layer.confirm("是否确认修改？", {},
            function (index) {
                UserRestApi.changeLockStatus(value, newFlag,
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

});