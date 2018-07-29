/**
 *从后台获取详细信息
 */
$(function(){
    var initUrl = '/shop/shopinit';
    var registerShopUrl = '/shopadmin/registershop';

    getShopInitInfo()

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
            url:registerShopUrl,
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if (data.success){
                    $.toast('提交成功！')
                }else{
                    $.toast('提交失败'+data.error)
                }
                //不管是成功还是失败都需要更换验证码图片
                $('#captchar_img').click();
            }
        })
    })
})