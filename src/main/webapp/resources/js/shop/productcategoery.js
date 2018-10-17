$(function() {

    function init() {
        var productCategoryId =getQueryString('productId');
        var initUrl = "/productmanage/showPeoductCategoey";
        var  parseDate = {
            productCategoryId : productCategoryId,
        };
        //初期化查询出某个商品分类信息
        $.ajax({
            url:initUrl,
            type:'POST',
            data:JSON.stringify(parseDate),
            contentType:'application/json',
            success:function (data) {
                if(data.success){

                }
            }
        })

    }

});