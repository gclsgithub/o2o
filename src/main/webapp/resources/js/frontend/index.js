$(function () {

    var initUrl = "http://localhost:8081/frontend/showIndex";

    $.ajax({
        url: initUrl,
        type: 'POST',
        success: function (data) {
            var headLineList = data.headLineList;
            var shopCategoerieList = data.shopCategoerieList;

            var headLineHtml = '';
            //头条信息
            headLineList.map(function (item, index) {

                headLineHtml += '<div class="swiper-slide img-wrap">' +
                    '<img class="banner-img"  onclick="jumpToProduct(' + item.line_link + ')" src="' + item.lineImg + '" alt="' + item.lineName + '">' +
                    '</div>';
            })

            $('.swiper-wrapper').html(headLineHtml);

            //开启轮播图
            $(".swiper-container").swiper({
                autoplay: 1000,
                autoplayDisableOnInteraction: false,
            });

            var productCategoeryHtml = '';
            shopCategoerieList.map(function (item, index) {
                productCategoeryHtml +=
                    "<div class='col-50 shop-classify' onclick='jumpShopListWithcategoeryId(" + item.shopCategoeryId + ")'> "
                    + "<div class='word'>  "
                    + "<p class='shop-title'>" + item.shopCategoeryName + "</p>  "
                    + "<p class='shop-desc'>" + item.shopCategoeryDesc + "</p> "
                    + "</div> "
                    + "<div class='shop-classify-img-warp'> "
                    + "<img class='shop-img' src='" + item.shopCateGoeryImg + "'> "
                    + "</div>"
                    + "</div>"
            })
            $('.row').html(productCategoeryHtml);
        }
    })
})

function jumpShopListWithcategoeryId(id) {
    window.location.href = "http://localhost:8081/frontend/shoplist?shopCategoeryId=" + id;
}

function jumpToProduct(line) {
    window.location.href = line;
}

function openOverLay() {
    $.openPanel("#panel-left-demo");
}

function closeOverLay() {
    $.closePanel("#panel-left-demo");
}
