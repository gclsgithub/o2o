$(function() {
    var shopId ;
    getShopId();
    function getShopId() {
        if (getQueryString('shopId') != null && getQueryString('shopId') != '') {
            shopId = "?shopId=" + getQueryString('shopId');
        }
        initproduction();
    }
    $("#back").on("click",function(){
        var backUrl = window.parent.document.referrer;
        $("#back").attr("href",backUrl);
    })
	function initproduction() {
    	var initUrl="/shopadmin/productmanageinit"+shopId;
    	var initHtml="";
    	$.getJSON(initUrl,function (data) {
			if(data.success){
                data.productList.map(function(item ,index){
                    initHtml+='<div class="row row-product">'+
                        '<div class="col-40">'+item.productName+'</div>'+
                        '<div class="col-10">'+item.priority+'</div>'+
                        '<div class="col-50">'+
                        '<a href='+"/productmapper/productaddmapper?productId="+item.productId+'>编辑</a>'+
                        '<a href='+"/productmapper/delproduct?productId="+item.productId+'>下架</a>'+
                        '<a href='+"/productmapper/showproduct?productId="+item.productId+'>预览</a>'+
                        '<input type="hidden" name="productId" value="item.productId" />'+
                        '</div>'+
                        '</div>';
				})
                $('.product-wrap').html(initHtml);
			}
        })

    }
});