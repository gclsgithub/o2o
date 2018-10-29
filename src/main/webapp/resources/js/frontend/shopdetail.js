$(function () {

    var shopId =getQueryString("shopId");

    var index = 1;

    var pageSize = 3;

    init();
    function init() {
        var url = "http://localhost:8081/shoplist/getShopInfo?index="+index+"&pageSize="+pageSize+"&shopId="+shopId;
        $.getJSON(url, function (data) {
            
            if (data.success){
                var shop = data.shop;
                $('#shop-cover-pic').prop('src',shop.shopImg);
                $('#shop-name').html(shop.shopName);
                $('#shop-desc').html(shop.shopDesc);
                $('#shop-addr').html(shop.shopAddr);
                $('#shop-phone').html(shop.phone);
                $('#shop-update-time').html(new Date(shop.lastEditTime).Format("yyyy-MM-dd"));

                //类别
                var productCategoryList = data.productCategoryList;

                var productCategoeryHtml = '' ;
                productCategoryList.map(function (item,index) {
                    productCategoeryHtml+= '<a href="javascript:void(0)" class="button" data-id="'+item.productionCategoryId+'">'+item.productionCategoryName+'</a>'
                })

                $('#shopdetail-button-div').html(productCategoeryHtml);

                var productList = data.productList;
                var productHtml = '';
                productList.map(function (item,index) {
                    productHtml+='div class="card">'+
                        '<div class="card-header">'+item.productName+'</div>'+
                        '<div class="card-content">'+
                        '<div class="list-block media-list">'+
                        '<ul>'+
                        '<li class="item-content">'+
                        '<div class="item-media">'+
                        '<img src="'+item.imgAddr+'" width="44">'+
                        '</div>'+
                        '<div class="item-inner">'+
                        '<div class="item-subtitle"></div>'+
                        '</div>'+
                        '</li>'+
                        '</ul>'+
                        '</div>'+
                        '</div>'+
                        '<div class="card-footer">'+
                        '<span>'+new Date(item.lastEditTime).Format("yyyy-MM-dd")+'</span>'+
                        '<span>点击查看</span>'+
                    '</div>'+
                    '</div>'
                })



            }else {
                $.toast(data.message);
            }
        })


    }
})