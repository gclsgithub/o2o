$(function () {
    
    $('#register').on('click',function () {
        window.location.href = "http://localhost:8081/jumpto/toregist?type=2";
    })

    //登陆方法
    $('#submit').on('click',function () {

        var url = "http://localhost:8081/user/login";

        var indexUrl =  "http://localhost:8081/frontend/index";

        var formData = new FormData();

        var userName = $('#username').val();
        var passWord = $('#passWord').val();

        formData.append("userName",userName);
        formData.append("passWord",passWord);
        formData.append("userType","1");

        $.ajax({
            url:url,
            type:'POST',
            data:formData,
            cache:false,
            contentType:false,
            processData:false,
            success:function (data) {
                if (data.success){
                    $.toast("success");
                    window.location.href=indexUrl;
                } else {
                    $.toast(data.message);
                }
            }
        })
    })
})