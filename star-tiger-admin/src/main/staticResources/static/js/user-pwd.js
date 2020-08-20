layui.use(['form', 'miniTab'], function () {
    let form = layui.form,
        layer = layui.layer,
        miniTab = layui.miniTab;

    form.on('submit(saveBtn)', function (data) {
        let fields = data.field;
        let oldPwd = fields.oldPwd;
        let newPwd = fields.newPwd;
        let againPwd = fields.againPwd;

        if (newPwd !== againPwd) {
            layer.msg("输入的新密码不一致");
        } else {
            MainRestApi.changeUserPwd(oldPwd, newPwd,
                function (data, textStatus, xhr) {
                    let code = data.code;
                    let msg = data.msg;
                    if (code !== "10000") {
                        layer.msg(msg);
                        return;
                    }
                    window.location.href = $("#context-path").val() + "/logout";
                }
            );
        }

        return false;
    });

});