/**
 *从后台获取详细信息
 */
$(function(){
    var initUrl = '/shop/shopinit';
    var editShopUrl = '/shopadmin/registershop';

    var shopId = getQueryString('shopId');
    //查询某一条数据的Url
    var shopInfoUrl = '/shopadmin/queryShop?shopId='+shopId;
    var idEdit = (shopId!=null)?true:false;
    var httpMethod = 'POST';
    if (idEdit){
        httpMethod = 'PUT';
        getSpecialShop(shopId);
        editShopUrl = '/shopadmin/updateShop';
    }else{
        getShopInitInfo();
    }
    function getSpecialShop(shopId) {
        $.getJSON(shopInfoUrl,function (data) {
            if (data.success) {

                var shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);

                var temHtmlShopCategoeryCompenx = '<option data-id ="'+shop.shopCategoeryId+'selected'+'">'+shop.shopCategoery.shopCategoeryName+'</option>';
                var tempHtmlAreaIdCompenx = '<option data-id ="'+shop.areaId+'selected'+'">'+shop.area.areaName+'</option>';
                $('#shop-category').attr('disabled','disabled');
                $('#shopCategoery-shopCategoeryId').html(temHtmlShopCategoeryCompenx);
                $('#area').attr('data-id',shop.areaId);
                $('#area-areaId').html(tempHtmlAreaIdCompenx);
                $('#shop-file').src = shop.shopImg;
            }
        })
    }
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtmlCompenx = '';
                var tempAreaHtmlCompenx = '';
                data.shopCategoeryList.map(function (item,index) {
                    tempHtmlCompenx += '<option data-id="'+item.shopCategoeryId+'">'+item.shopCategoeryName+'</option>';
                })
                data.areaList.map(function (item,index) {
                    tempAreaHtmlCompenx += '<option data-id="'+item.areaId+'">'+item.areaName+'</option>';
                })
                $('#shopCategoery-shopCategoeryId').html(tempHtmlCompenx);
                $('#area-areaId').html(tempAreaHtmlCompenx);
            }
        })
    }
    $('#submit').click(function(){
        var shop = {};
        shop.shopName = $("#shop-name").val();
        shop.shopCategoery = {
            shopCategoeryId : $('#shopCategoery-shopCategoeryId').find('option').not(function () {
                return !this.selected;
            }).dataset().id
        }

        shop.area = {
            areaId:$('#area-areaId').find('option').not(function () {
                return !this.selected;
            }).dataset().id
        }
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#phone').val();
        var shopImg = $('#shop-file')[0].files[0];
        shop.shopDesc = $('#shop-desc').val();
        var formData = new FormData();
        formData.append('shopImg',shopImg);
        formData.append('shopStr',JSON.stringify(shop))

        // 获取验证码的内容
        var verifyCodeActual = $('#j_captchar').val();
        if (verifyCodeActual == null){
            $.toast("请输入验证码");
            return;
        }else {
            formData.append("verifyCodeActual",verifyCodeActual);
        }

        $.ajax({
            //保证Json对象不被转码
            traditional:true,
            url:editShopUrl,
            type:httpMethod,
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if (data.success){
                    $.toast('提交成功！')
                    if (idEdit) {
                        $('#captchar_img').click();
                    }else{
                        window.location.href = '/shop/shoplist';
                    }
                }else{
                    $.toast('提交失败'+data.error)
                }
                //不管是成功还是失败都需要更换验证码图片
                $('#captchar_img').click();
            }
        })
    })
})