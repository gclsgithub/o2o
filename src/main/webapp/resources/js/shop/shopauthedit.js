$(function () {

    var shopAuthId = getQueryString('shopAuthId');
    var init = "http://localhost:8081/shopauth/shopauthedit?shopAuthId=" + shopAuthId;

    var modify = "http://localhost:8081/shopauth/updateShopAuth";

    if (shopAuthId == '' || shopAuthId == null || shopAuthId == undefined) {
        $.toast("用户不存在");
    } else {
        initFun();
    }


    function initFun() {
        $.getJSON(init, function (data) {
            if (data.success) {
                var shopAuth = data.shopAuth;
                $('#shopauth-name').val(shopAuth.emmployee.name);
                $('#title').val(shopAuth.jobName);
            }
        })
    }

    $('#submit').on('click', function () {
        var shopAuth = {};
        var emmployee = {};

        shopAuth.emmployee = emmployee;

        shopAuth.emmployee.name = $('#shopauth-name').val();

        shopAuth.jobName = $('#title').val();

        shopAuth.shopAuthId = shopAuthId;

        var verifyCodeExpected = $('#j_captcha').val();

        if (verifyCodeExpected == null || verifyCodeExpected == '' || verifyCodeExpected == undefined) {
            $.toast("请输入验证码");
            return;
        }
        $.ajax({
            url: modify,
            type: 'POST',
            data: {
                shopAuthStr: JSON.stringify(shopAuth),
                verifyCodeActual: verifyCodeExpected
            },
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    $.toast("更改成功");
                    $('#captcha_img').click();
                } else {
                    $.toast("更改失败");
                }
            }
        })
    })

})