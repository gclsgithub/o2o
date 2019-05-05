$(function () {

    var url = "http://localhost:8081/nav/myrecord";
    var formData = new FormData();



    $.ajax({
        url:url,
        type:'POST',
        data:formData,
        cache:false,
        contentType:false,
        processData:false,
        success:function (data) {
            if (data.success){

            } else {
                $.toast(data.message);
            }
        }
    })
})