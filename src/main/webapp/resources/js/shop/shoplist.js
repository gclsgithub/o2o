$(function () {

    getShopList();

    /**
    $('#log-out').on('click',function () {

        var url = "http://localhost:8081/jumpto/logout";

        window.location.href=url;
    })*/

    function getShopList() {
        var getShopListInitUril="/shopadmin/showShopList";

        getShopInitList();

        function getShopInitList(){
            $.getJSON(getShopListInitUril,function(data){
                var tempHtmlCompenx="";
                if (data.success){
                    data.shopList.map(function(item,index){
                        tempHtmlCompenx += '<div class="row row-shop"><div class="col-40" >'+item.shopName+'</div>'
                         +'<div class="col-40" >'+item.enableStatus+'</div>'
                         +'<div class="col-20" >'+goShop(item.enableStatus,item.shopId)+'</div></div>';
                    })
                }
                $('#user-name').html(data.userName);
                if (tempHtmlCompenx != '') {
                    $('.shop-wrap').html(tempHtmlCompenx);
                }
            })
        }
    }

    function goShop(status,id ) {
        if (status != 0 && status != -1){
            return '<a href = "/shop/mappershopfunction?shopId='+id+'">进入</a>';
        }else{
            return '';
        }
    }
})