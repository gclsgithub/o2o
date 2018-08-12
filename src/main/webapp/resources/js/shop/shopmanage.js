$(function () {

    var shopId ;
    getShopId();
    function getShopId() {
        shopId = "?shopId="+getQueryString('shopId');
        $('a').each(function(){
            var href = $(this).attr('href');
            $(this).attr('href',href+shopId);
        })
    }
})