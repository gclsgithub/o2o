$(function() {
    var productId ;
    getProductId();
    var initUrl = "productmanage/initproductedit";
    function getProductId() {
        productId = "?productId="+getQueryString('productId');
        initproduction();
    }
    function initproduction() {
        $.ajax({
            url:initUrl+productId,
            type:'GET',
            success:function (data) {
                if(data.success){

                }
            }
        })
    }
});