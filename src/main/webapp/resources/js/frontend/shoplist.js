$(function () {
    var loading = false;

    var maxItem = 999;

    var pageSize = 10;

    //获取店铺列表
    var searchDivUrl = "http://localhost:8081/shoplist/listshoppageinfo";

    var pageNum = 1;

    var shopCategoeryId = getQueryString("shopCategoeryId");

    var areaId = '';

    var shopName = '';

    getSearchDivData();

    addItems(pageSize, pageNum);

    function getSearchDivData() {

        if (shopCategoeryId == ''){
            shopCategoeryId =-1;
        }

        var url = searchDivUrl + "?shopCategoeryId=" + shopCategoeryId;
        $.getJSON(url, function (data) {
            if (data.success) {
                //商铺类别List
                var shopCategoeryList = data.shopCategoeryList;
                var shopCategoeryHtml = '';
                shopCategoeryList.map(function (item, index) {
                    shopCategoeryHtml += "<a href='javascript:void(0)' class='button' data-id='" + item.shopCategoeryId + "'>" + item.shopCategoeryName + "</a>";
                })
                $('#shoplist-search-div').html(shopCategoeryHtml);
                var selectOptions = '<option value="+ +">全部地区</option>'

                var areaList = data.areaList;
                areaList.map(function (item, index) {
                    selectOptions += '<option value="+item.areaId+">'+item.areaName+'</option>';
                })

                $('#area-search').html(selectOptions);


            }
        })
    }

    function addItems() {
        var url = 'http://localhost:8081/shoplist/init?shopCategoeryId=' + shopCategoeryId + '&index=' + pageNum +
            '&pageSize=' + pageSize + '&areaId=' + areaId + '&shopName=' + shopName;

        loading = true;

        var cardHtml = '';
        $.getJSON(url, function (data) {

            var shopList = data.shopList;
            shopList.map(function (item, index) {
                cardHtml +=
                '<div class="card" data-shop-id="' + item.shopId + '">' +
                '<div class="card-header">' + item.shopName + '</div>' +
                '<div class="card-content">' +
                '<div class="list-block media-list">'+
                '<ul>' +
                '<li class="item-content">' +
                '<div class="item-media">' +
                '<img src="' + item.shopImg + '" width="44">' +
                '</div>' +
                '<div class="item-inner">'+
                '<div class="item-subtitle"></div>' +
                '</div>' +
                '</li>' +
                '</ul>' +
                '</div>' +
                '</div>' +
                '<div class="card-footer">' +
                    '<p class="color-gray">'+new Date(item.lastEditTime).Format("yyyy-MM-dd")+'更新</p>'+
                '<span>点击查看</span>' +
                ' </div>' +
                '</div>'
            })

            $('.list-div').append(cardHtml);

            var total = $('.list-div .card').length;

            if (total > maxItem){

                // 加载完毕，则注销无限加载事件，以防不必要的加载

                // 隐藏加载提示符
                $('.infinite-scroll-preloader').hide();
                return;
            }else{
                $('.infinite-scroll-preloader').show();
            }

            pageNum++;
            loading =lase;
            $.refreshScroller();

        })
    }

    /**
     * 当向下活动屏幕，自动进行分页搜索
     */
    $(document).on('infinite','infinite-scroll-bottom',function () {
        if (loading){
            return;
        }
        addItems(pageSize,pageNum);
    })

    /**
     * 点击卡片进入详情
     */
    $('.shop-list').on('click','.card',function (cuurent) {
        var shopId= cuurent.currentTarget.dataset.shopId;
        window.location.href = "http://localhost:8081/shoplist/shopDetial?shopId="+shopId;
    })

    /**
     * 搜索
     */
    $('#shoplist-search-div')
})