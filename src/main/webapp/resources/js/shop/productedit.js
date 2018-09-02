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
                    $('#product-name').attr("value",product.productName);
                    $('#category').attr("value",product.productName);

                }
            }
        })
    }
});