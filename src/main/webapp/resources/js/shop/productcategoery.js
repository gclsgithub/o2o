$(function() {
    init();
    function init() {
        var productCategoryId =getQueryString('productionCategoryId');
        var initUrl = "/productmanage/showPeoductCategoey";

        var formData = new FormData();
        formData.append("productCategoryId",productCategoryId);
        //初期化查询出某个商品分类信息
        $.ajax({
            url:initUrl,
            type:'POST',
            data:formData,
            cache:false,
            contentType:false,
            processData:false,
            success:function (data) {
                if(data.success){

                }
            }
        })

    }

});