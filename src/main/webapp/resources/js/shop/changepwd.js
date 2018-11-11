$(function () {

    var backurl = "http://localhost:8081/shop/shoplist";

    var modifyurl = "http://localhost:8081/user/modifypwd";


    $('#submit').on('click',function () {

        var formdata = new FormData();
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();
        //获取验证码
        var verifyCodeActual = $('#j_captcha').val();

        if (verifyCodeActual == null || verifyCodeActual == '') {
            $.toast('请输入验证码');
            return;
        }
        formdata.append('password',)


        $.ajax({
            url:
        })
    })
    
    
    $('#back').on('click',function () {
        window.location.href =backurl;
    })
})