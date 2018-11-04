$(function () {

    var productId = getQueryString("productId");

    init();

    function init(){

        var url  = 'http://localhost:8081/productfront/searchproduct';

        var formData = new FormData();

        formData.append("productId",productId);

        $.ajax({
            url:url,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success:function (data) {
                if (data.success){

                    $('#product-time').html(new Date(data.product.lastEditTime).Format("yyyy-MM-dd"));
                    $('#product-img').prop("src",data.product.imgAddr);
                    $('#product-name').html(data.product.productName);
                    $('#product-desc').html(data.product.productDesc);

                } else {
                    $.toast(data.message);
                }
            }
        })
    }

    $('#buy').onclick(function () {
        $.toast("功能尚未开放，请到店购买");
    })

    $('#love').onclick(function () {
        $.toast("已经点赞");
        $('#love').prop("disable",true);
    })
})