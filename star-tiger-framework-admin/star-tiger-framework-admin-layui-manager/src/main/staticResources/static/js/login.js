layui.use(["form", "layer"], function () {
    let layer = layui.layer;

    if (top.location !== self.location) {
        top.location = self.location;
    }

    let msg = $("#message").val();
    if (msg && msg !== "") {
        layer.msg(msg);
    }

    $(".layui-container").particleground({
        dotColor: "#7ec7fd",
        lineColor: "#7ec7fd"
    });

    let $username = $("#username");
    let $password = $("#password");
    let $captcha = $("#captcha");
    let username = $username.val();
    let password = $password.val();

    let hasUsername = (username && username.length > 0);
    let hasPassword = (password && password.length > 0);

    if (hasUsername && !hasPassword) {
        $password.focus();
    } else if (hasUsername && hasPassword) {
        $captcha.focus();
    } else {
        $username.focus();
    }

    let refreshCaptcha = function () {
        let $this = $(this);
        let $parent = $this.parent();
        $this.remove();
        let $newDom = $("<img id='captchaPic' src='" + $this.attr("src") + "' alt='点击刷新'>");
        $parent.append($newDom);
        $newDom.on("click", refreshCaptcha);
    }

    $("#captchaPic").on("click", refreshCaptcha);
});