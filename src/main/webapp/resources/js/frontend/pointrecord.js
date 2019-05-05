$(function () {


    var formData = new FormData();

    var userName = $('#username').val();
    var passWord = $('#passWord').val();

    formData.append("userName",userName);
    formData.append("passWord",passWord);
    formData.append("userType","1.");

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

            } else {
                $.toast(data.message);
            }
        }
    })
})