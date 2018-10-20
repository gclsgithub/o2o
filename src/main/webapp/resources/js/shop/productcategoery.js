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
                    var priority = data.product.priority;
                    var productionCategoryName = data.product.productionCategoryName;
                    var productionCategoryId = data.product.productionCategoryId;
                    $('#priority').val(priority);
                    $('#productionCategoryName').val(productionCategoryName);
                    $('#productionCategoryId').val(productionCategoryId);
                }
            }
        })
        
        $('#back').on('click',function () {
            window.location.href="http://localhost:8081/productmapper/jumpproduct/categoery";
        })

        $('#submit').on('click',function () {

            var priority = $('#priority').val();
            var productionCategoryName = $('#productionCategoryName').val();
            var productionCategoryId = $('#productionCategoryId').val();

            var productCategory = {};
            productCategory.productionCategoryId = productionCategoryId;
            productCategory.productionCategoryName = productionCategoryName;
            productCategory.priority = priority;



            //更新商品信息
            $.ajax({
                url:"http://localhost:8081/productmanage/updateProductCategoery",
                type:'POST',
                data:JSON.stringify(productCategory),
                contentType:'application/json',
                success:function (data) {
                    if(data.success){
                        var priority = data.productCategory.priority;
                        var productionCategoryName = data.productCategory.productionCategoryName;
                        var productionCategoryId = data.productCategory.productionCategoryId;
                        $('#priority').val(priority);
                        $('#productionCategoryName').val(productionCategoryName);
                        $('#productionCategoryId').val(productionCategoryId);

                        if (data.message != null && data.message != ''){
                            $.toast(data.message);
                        }else{
                            $.toast('修改成功');
                        }
                    }
                }
            })


        })
    }
});