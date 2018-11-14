$(function () {
    var url = "http://localhost:8081/jumpto/toregist";

    var backShopUrl = "http://localhost:8081/jumpto/toLogin";

    var backFrontUrl = "http://localhost:8081/frontend/toLogin";

    var type = getQueryString("type");

    /**
     * 返回
     */
    $('#back').on('click', function () {

        //跳转回shop
        if ("1" == type) {

            window.location.href = backShopUrl;

        } else if ("2" == type) {

            //跳转回front
            window.location.href = backFrontUrl;
        }
    })

    /**
     * 注册
     */
    $('#submit').on('click', function () {

        //获取验证码
        var verifyCodeActual = $('#j_captcha').val();

        if (verifyCodeActual == null || verifyCodeActual == '') {
            $.toast('请输入验证码');
            return;
        }

        var formdata = new FormData();


        //头像
        var thumbnail = $('#small-img')[0].files[0]

        var localAuth = {};

        var personInfo = {};

        localAuth.userName = $('#userName').val();

        localAuth.passWord = $('#password').val();

        var confirmPassWord = $('#confirmPassWord').val();

        personInfo.email = $('#email').val();

        personInfo.name = $('#name').val();

        personInfo.phone = $('#phone').val();

        personInfo.userType = '1';

        localAuth.personInfo = personInfo;

        if (confirmPassWord == '' || confirmPassWord != localAuth.passWord  ) {
            $.toast('密码不能为空且必须一致');
            return;
        }
        if (localAuth.userName == '' || localAuth.userName == null) {
            $.toast('用户名不能为空');
            return;
        }
        if (localAuth.personInfo.email == '' || localAuth.personInfo.email == null) {
            $.toast('邮箱不能为空');
            return;
        }
        if (localAuth.personInfo.phone == '' || localAuth.personInfo.phone == null) {
            $.toast('电话不能为空');
            return;
        }
        formdata.append("auth", JSON.stringify(localAuth));
        formdata.append("verifyCodeActual", verifyCodeActual);
        formdata.append("thumbnail", thumbnail);
        formdata.append("type", type);

        $.ajax({
            url:"http://localhost:8081/user/regix",
            type:'POST',
            data:formdata,
            cache:false,
            contentType:false,
            processData:false,
            success:function (data) {
                if (data.success){
                    //休眠2秒
                    sleep(2000);
                    $('#back').onclick();
                }
            }
        })
    })

})