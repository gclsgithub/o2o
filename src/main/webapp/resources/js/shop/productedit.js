$(function() {
    var productId ='';
    getProductId();
    function getProductId() {
        productId = "?productId="+getQueryString('productId');
        initproduction();
    }

    $('#back').on("click",function () {
        window.location.href = "/shop/productmanage?shopId="+$('#shopId').val();

    })

    function initproduction() {
        var intitProductUrl = "http://localhost:8081/productmanage/initproductedit"+productId;
        $.ajax({
            url:intitProductUrl,
            type:'GET',
            success:function (output) {
                if(output.success){
                    var product = output.Product;
                    var productList = output.ProductList;
                    var temHtmlShopCategoeryCompenx = '';
                    if (product)
                    productList.map(function(item ,index){
                        if (product.productCategory.productionCategoryId == item.productionCategoryId) {
                            temHtmlShopCategoeryCompenx += '<option data-id ="' + item.productionCategoryId +'" selected>' + item.productionCategoryName + '</option>';
                        }else {
                            temHtmlShopCategoeryCompenx += '<option data-id ="' + item.productionCategoryId + '">' + item.productionCategoryName + '</option>';
                        }

                    })
                    $('#product-name').attr("value",product.productName);
                    $('#category').html(temHtmlShopCategoeryCompenx);
                    $('#priority').attr("value",product.priority);
                    $('#normal-price').attr("value",product.normalPrice);
                    $('#promotion-price').attr("value",product.promotionPrice);
                    $('#small-img').attr("value",product.promotionPrice);
                    $('#shopId').attr("value",output.shopId);
                    $('#product-desc').html(product.productDesc);
                }
            }
        })
    }
});