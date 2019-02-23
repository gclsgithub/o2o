$(function () {

    var productId = getQueryString("productId");

    var shopId = getQueryString("shopId");

    init();

    function init(){

        var url  = 'http://localhost:8081/productfront/searchproduct';

        var formData = new FormData();

        formData.append("productId",productId);
        formData.append("shopId",shopId);

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
                    if (data.product.point != undefined) {
                        $('#product-point').html("购买可以得到：" + data.product.point + "个积分");
                    }
                } else {
                    $.toast(data.message);
                }
            }
        })
    }

    $('#buy').click(function () {

        var formData = new FormData();

        formData.append("productId",productId);
        formData.append("shopId",shopId);

        var createUrl = 'http://localhost:8081/productfront/creatProduct';

        $.ajax({
            url:createUrl,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success:function (data) {

                var jumpUrl = 'http://localhost:8081/frontend/jumpToQrcode?sellId=';
                if (data.success){
                    $.toast("购买成功");
                    var sellId = data.sellId;
                    window.location.href = jumpUrl+sellId;
                } else {
                    $.toast("购买失败");
                }
        }
    })})

    $('#love').click(function () {
        $.toast("已经点赞");
        $('#love').prop("disable",true);
    })
})