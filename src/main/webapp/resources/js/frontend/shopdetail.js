$(function () {

    var shopId = getQueryString("shopId");

    var index = 1;

    var pageSize = 3;

    var maxItem = 999;

    var loading = false;

    init();

    function init() {
        var url = "http://localhost:8081/shoplist/getShopInfo?index=" + index + "&pageSize=" + pageSize + "&shopId=" + shopId;
        loading = true;
        $.getJSON(url, function (data) {
            if (data.success) {
                var shop = data.shop;
                $('#shop-cover-pic').prop('src', shop.shopImg);
                $('#shop-name').html(shop.shopName);
                $('#shop-desc').html(shop.shopDesc);
                $('#shop-addr').html(shop.shopAddr);
                $('#shop-phone').html(shop.phone);
                $('#shop-update-time').html(new Date(shop.lastEditTime).Format("yyyy-MM-dd"));

                //类别
                var productCategoryList = data.productCategoryList;

                var productCategoeryHtml = '';
                productCategoryList.map(function (item, index) {
                    productCategoeryHtml += '<a href="javascript:void(0)" class="button" data-id="' + item.productionCategoryId + '">' + item.productionCategoryName + '</a>'
                })

                $('#shopdetail-button-div').html(productCategoeryHtml);

                var productList = data.productList;
                var productHtml = '';
                productList.map(function (item, index) {
                    productHtml +=
                        '<div class="card">' +
                        '<div class="card-header">' + item.productName + '</div>' +
                        '<div class="card-content">' +
                        '<div class="list-block media-list">' +
                        '<ul>' +
                        '<li class="item-content">' +
                        '<div class="item-media">' +
                        '<img src="' + item.imgAddr + '" width="44">' +
                        '</div>' +
                        '<div class="item-inner">' +
                        '<div class="item-subtitle"></div>' +
                        '</div>' +
                        '</li>' +
                        '</ul>' +
                        '</div>' +
                        '</div>' +
                        '<div class="card-footer">' +
                        '<span>' + new Date(item.lastEditTime).Format("yyyy-MM-dd") + '</span>' +
                        '<span>点击查看</span>' +
                        '</div>' +
                        '</div>'
                })
                $('.list-div').append(productHtml);
            } else {
                $.toast(data.message);
            }
        })
        index++;
        var total = $('.list-div .card').length;
        if (total > maxItem) {

            // 加载完毕，则注销无限加载事件，以防不必要的加载

            // 隐藏加载提示符
            $('.infinite-scroll-preloader').hide();
            return;
        } else {
            $('.infinite-scroll-preloader').show();
        }

        if (total == null || total == 0) {
            $('.infinite-scroll-preloader').hide();
        }
        loading = false;
        //刷新界面显示数据
        $.refreshScroller();
    }

    /**
     * 搜索
     */
    $('#search').on("change", function () {
        searchProductInfos();
    })

    $('.shopdetail-button-div').on(
        'click',
        '.button',
        function (bf) {

            $('.list-div').empty();
            areaId = $('#area-search').val();
            shopCategoeryId = $(bf.target).dataset().id;
            shopName = $('#search').val();
            pageNum = 1;
            $('a[data-Id]').removeClass('button-fill');
            $(bf.target).addClass('button-fill');
            addItems(pageSize, pageNum);
        })

    /**
     * 搜索加载
     */
    function searchProductInfos() {

        var url = 'http://localhost:8081/productfront/searchproductInfos';
        var formData = new FormData();

        index = 1;
        $('a[data-Id]').find('.button-fill');
        formData.append("productName", $('#search').val());
        formData.append("productCategoeryId", $('#search').val());
        formData.append("shopId", getQueryString("shopId"));
        formData.append("index", index);
        formData.append("pageSize", pageSize);

        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.success) {
                    var productList = data.productList;
                    var productHtml = '';
                    productList.map(function (item, index) {
                        productHtml +=
                            '<div class="card">' +
                            '<div class="card-header">' + item.productName + '</div>' +
                            '<div class="card-content">' +
                            '<div class="list-block media-list">' +
                            '<ul>' +
                            '<li class="item-content">' +
                            '<div class="item-media">' +
                            '<img src="' + item.imgAddr + '" width="44">' +
                            '</div>' +
                            '<div class="item-inner">' +
                            '<div class="item-subtitle"></div>' +
                            '</div>' +
                            '</li>' +
                            '</ul>' +
                            '</div>' +
                            '</div>' +
                            '<div class="card-footer">' +
                            '<span>' + new Date(item.lastEditTime).Format("yyyy-MM-dd") + '</span>' +
                            '<span>点击查看</span>' +
                            '</div>' +
                            '</div>'
                    })
                    $('.list-div').html(productHtml);
                } else {
                    $.toast(data.message);
                }

                var total = $('.list-div .card').length;

                if (total > maxItem) {

                    // 加载完毕，则注销无限加载事件，以防不必要的加载

                    // 隐藏加载提示符
                    $('.infinite-scroll-preloader').hide();
                    return;
                } else {
                    $('.infinite-scroll-preloader').show();
                }

                if (productList.size == null) {
                    $('.infinite-scroll-preloader').hide();
                }
            }
        })
    }

    /**
     * 分页加载
     */
    function addItems() {
        var url = 'http://localhost:8081/productfront/searchproductInfos';
        var formData = new FormData();

        formData.append("productName", $('#search').val());
        formData.append("productCategoeryId", $('#search').val());
        formData.append("shopId", getQueryString("shopId"));
        formData.append("index", index);
        formData.append("pageSize", pageSize);

        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if (data.success) {
                    var productList = data.productList;
                    var productHtml = '';
                    productList.map(function (item, index) {
                        productHtml +=
                            '<div class="card">' +
                            '<div class="card-header">' + item.productName + '</div>' +
                            '<div class="card-content">' +
                            '<div class="list-block media-list">' +
                            '<ul>' +
                            '<li class="item-content">' +
                            '<div class="item-media">' +
                            '<img src="' + item.imgAddr + '" width="44">' +
                            '</div>' +
                            '<div class="item-inner">' +
                            '<div class="item-subtitle"></div>' +
                            '</div>' +
                            '</li>' +
                            '</ul>' +
                            '</div>' +
                            '</div>' +
                            '<div class="card-footer">' +
                            '<span>' + new Date(item.lastEditTime).Format("yyyy-MM-dd") + '</span>' +
                            '<span>点击查看</span>' +
                            '</div>' +
                            '</div>'
                    })
                    $('.list-div').append(productHtml);
                } else {
                    $.toast(data.message);
                }

                var total = $('.list-div .card').length;

                if (total > maxItem) {

                    // 加载完毕，则注销无限加载事件，以防不必要的加载

                    // 隐藏加载提示符
                    $('.infinite-scroll-preloader').hide();
                    return;
                } else {
                    $('.infinite-scroll-preloader').show();
                }

                if (productList.size == null) {
                    $('.infinite-scroll-preloader').hide();
                }
            }
        })
    }

    /**
     * 当向下活动屏幕，自动进行分页搜索
     */
    $('.content').scroll(function () {
        if (loading) {
            return;
        }
        var height = $('.content').height();
        var scroll = $('.content').scrollTop();

        if (height < height - scroll + 50) {
            addItems(index, pageSize);
        }
    })
})