$(function() {
    var productName = '';

    var prodctNames = [];
    var showdata ;
    var shopId = getQueryString('shopId');
    function getList() {
        var listUrl = '/shop/listuserproductmapsbyshop?pageIndex=1&pageSize=9&shopId=' + shopId + '&productName=' + productName;
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                var userProductMapList = data.userProductMapList;
                showdata = data.showData
                var tempHtml = '';
                userProductMapList.map(function (item, index) {
                    tempHtml += ''
                         +      '<div class="row row-productbuycheck">'
                         +          '<div class="col-33">'+ item.productName +'</div>'
                         +          '<div class="col-33 productbuycheck-time">'+ item.createTime +'</div>'
                         +          '<div class="col-33">'+ item.shopName+'</div>'
                         +      '</div>';
                    //设置总的类别
                    prodctNames.push(item.productName);
                });
                $('.productbuycheck-wrap').html(tempHtml);

                var myChart = echarts.init(document.getElementById('chart'));

                var option = {
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data:prodctNames
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ['周一','周二','周三','周四','周五','周六','周日']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : showdata
                };

                myChart.setOption(option);
            }
        });
    }


    $('#back').click(function() {
        window.location.href = "/shop/mappershopfunction?shopId="+shopId;
    });

    $('#search').on('input', function (e) {
        productName = e.target.value;
        $('.productbuycheck-wrap').empty();
        getList();
    });

    getList();

});