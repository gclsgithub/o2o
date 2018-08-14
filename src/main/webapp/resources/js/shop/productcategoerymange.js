$(function() {
    var shopId ;
    getShopId();
    function getShopId() {
        shopId = "?shopId="+getQueryString('shopId');
        initproduction();
    }

	function initproduction() {
    	var initUrl="/productmanage/productinit"+shopId;
    	var initHtml="";
    	$.getJSON(initUrl,function (data) {
			if(data.success){
                data.productCategoeryList.map(function(item ,index){
                    initHtml+='<div class="row row-product">'+
                        '<div class="col-20">'+item.productionCategoryName+'</div>'+
                        '<div class="col-30">'+item.priority+'</div>'+
                        '<div class="col-50">'+
                        '<a href='+"/productmapper/editproductcategoery?productId="+item.productionCategoryId+'>编辑</a>'+
                        '<a href='+"/productmanage/delproductcategoery?productId="+item.productionCategoryId+'>删除</a>'+
                        '<a href='+"/productmanage/showproductcategoery?productId="+item.productionCategoryId+'>预览</a>'+
                        '<input type="hidden" name="productId" value=item.productionCategoryId />'+
                        '</div>'+
                        '</div>';
				})
                $('.product-wrap').html(initHtml);
			}
        })

    }
});