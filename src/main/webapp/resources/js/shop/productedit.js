$(function() {
    var productId ='';
    getProductId();
    function getProductId() {
        productId = "?productId="+getQueryString('productId');
        initproduction();
    }

    //返回按键
    $('#back').on("click",function () {
        window.location.href = "/shop/productmanage?shopId="+$('#shopId').val();

    })

    //增加详情图作事件绑定
    $('.detail-img-div').on('change','.detail-img:last-child',function () {
        if($('.detail-img').length < $('#img_detial_max_length').val()){
                $('#detail-img').append('<input type="file" class="detail-img">')
        }
    })

    //提交商品详情图
    $('#submit').on("click",function () {

        //获取验证码
        var verifyCodeActual = $('#j_captcha').val();

        if(verifyCodeActual == null || verifyCodeActual == ''){
            $.toast('请输入验证码');
            return;
        }

        var formData = new FormData();

        var product = {};

        product.productName = $('#product-name').val();
        product.priority = $('#priority').val();
        product.normalPrice = $('#normal-price').val();
        product.promotionPrice = $('#promotion-price').val();
        product.productDesc = $('#product-desc').val();
        product.productId = getQueryString('productId');

        //类别
        product.productCategory = {
            productionCategoryId:$('#category').find('option').not(function(){
                return !this.selected;
            }).data('id')
        };

        //缩略图文件流
        var thumbnail = $('#small-img')[0].files[0];

        formData.append('thumbnail',thumbnail);

        //详情图文件流
        $('.detail-img').map(
            function (index,item) {
                if ($('.detail-img')[index].files.length>0){
                    formData.append('productImg'+index,$('.detail-img')[index].files[0])
                }
            }
        )

        formData.append('productStr',JSON.stringify(product));

        formData.append("verifyCodeActual",verifyCodeActual);

        productId = $('#productId').val();
        formData.append("productId",productId);

        $.ajax({
            url:"http://localhost:8081/productmanage/addproduct",
            type:'POST',
            data:formData,
            cache:false,
            contentType:false,
            processData:false,
            success:function (output) {
                $('#productId').val(output.productId);
                if(output.success){
                    $.toast('添加成功');
                    $('#captcha_img').click();
                }else {
                    $.toast('添加失败');
                    if (output.errMsg != null && output.errMsg != '') {
                        $.toast(output.errMsg);
                    }
                    $('#captcha_img').click();
                }
            }
        })

    })

    function initproduction() {
        var intitProductUrl = "http://localhost:8081/productmanage/initproductedit"+productId;
        $.ajax({
            url:intitProductUrl,
            type:'GET',
            success:function (output) {

                $('#img_detial_max_length').val(output.imageMaxLength);
                $('#productId').val(output.productId);
                if(output.success){
                    var product = output.Product;
                    var productList = output.ProductList;
                    var temHtmlShopCategoeryCompenx = '';
                    productList.map(function(item ,index){
                        if (product == null ){
                            temHtmlShopCategoeryCompenx += '<option data-id ="' + item.productionCategoryId + '">' + item.productionCategoryName + '</option>';
                        }
                    })
                    $('#category').html(temHtmlShopCategoeryCompenx);
                    if (product != null){
                        $('#product-name').attr("value",product.productName);
                        $('#category').html(temHtmlShopCategoeryCompenx);
                        $('#priority').attr("value",product.priority);
                        $('#normal-price').attr("value",product.normalPrice);
                        $('#promotion-price').attr("value",product.promotionPrice);
                        $('#small-img').attr("value",product.promotionPrice);
                        $('#shopId').attr("value",output.shopId);
                        $('#product-desc').html(product.productDesc);
                        productList.map(function(item ,index){
                            $('#category').html('');
                            if (product.productCategory.productionCategoryId != null  || product.productCategory.productionCategoryId !='') {
                                if (product.productCategory.productionCategoryId == item.productionCategoryId) {
                                    temHtmlShopCategoeryCompenx += '<option data-id ="' + item.productionCategoryId + '" selected>' + item.productionCategoryName + '</option>';
                                } else {
                                    temHtmlShopCategoeryCompenx += '<option data-id ="' + item.productionCategoryId + '">' + item.productionCategoryName + '</option>';
                                }
                            }
                        })
                        $('#category').html(temHtmlShopCategoeryCompenx);
                    }
                }
            }
        })
    }
});