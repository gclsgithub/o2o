$(function() {
    var productId ='';
    getProductId();
    function getProductId() {
        productId = "?productId="+getQueryString('productId');
        initproduction();
    }


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
                         temHtmlShopCategoeryCompenx += '<option data-id ="'+item.productionCategoryId+'selected'+'">'+item.productionCategoryName+'</option>';

                    })
                    $('#product-name').attr("value",product.productName);
                    $('#category').html(temHtmlShopCategoeryCompenx);
                    $('#priority').attr("value",product.priority);
                    $('#normal-price').attr("value",product.normalPrice);
                    $('#promotion-price').attr("value",product.promotionPrice);
                    $('#small-img').attr("value",product.promotionPrice);
                    $('#product-desc').html(product.productDesc);
                }
            }
        })
    }
});