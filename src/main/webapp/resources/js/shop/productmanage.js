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
        //返回是因为这个方法使用的是Dom对象的上一个画面

        var backUrl = "http://localhost:8081/shop/mappershopfunction"+shopId;
        $("#back").attr("href",backUrl);
    })
    $("#addButton").on("click",function(){
       window.location.href="http://localhost:8081/productmapper/productaddmapper"+(shopId == undefined?"?shopId=":shopId);
    })
	function initproduction() {
    	var initUrl="/shopadmin/productmanageinit";

    	if (shopId !=null && shopId !=null){
            initUrl =initUrl+shopId
        }
    	var initHtml="";
    	$.getJSON(initUrl,function (data) {
			if(data.success){
                data.productList.map(function(item ,index){

                    if (item.enableStatus == '1') {
                        initHtml+='<div class="row row-product">'+
                            '<div class="col-40">'+item.productName+'</div>'+
                            '<div class="col-10">'+item.priority+'</div>'+
                            '<div class="col-50">'+
                            '<a href='+"/productmapper/productaddmapper?productId="+item.productId+'>编辑</a>'+
                            '<a href='+"javascript:void(0)"+' onclick="changeStatus(this)">上架</a>'+
                            '<a href='+"/productmapper/showproduct?productId="+item.productId+'>预览</a>'+
                            '<input type="hidden" name="enableStatus" value='+item.enableStatus+' />'+
                            '<input type="hidden" name="productId" value='+item.productId +' />'+
                            '</div>'+
                            '</div>';
                    }else if(item.enableStatus == '0'){
                        initHtml+='<div class="row row-product">'+
                            '<div class="col-40">'+item.productName+'</div>'+
                            '<div class="col-10">'+item.priority+'</div>'+
                            '<div class="col-50">'+
                            '<a href='+"/productmapper/productaddmapper?productId="+item.productId+'>编辑</a>'+
                            '<a href='+"javascript:void(0)"+' onclick="changeStatus(this)">下架</a>'+
                            '<a href='+"/productmapper/showproduct?productId="+' onclick="showPicDetial(this)">预览</a>'+
                            '<input type="hidden" name="enableStatus" value='+item.enableStatus+' />'+
                            '<input type="hidden" name="productId" value= '+item.productId +' />'+
                            '</div>'+
                            '</div>';
                    }


				})
                $('.product-wrap').html(initHtml);
			}
        })

    }
});

function changeStatus(ahref) {
    $.confirm("确定执行操作？" ,function () {
        var enableStatus ;
        var productId ;
        for (var i = 0;i<ahref.parentElement.children.length;i++){
            if (ahref.parentElement.children[i].name == 'enableStatus'){
                enableStatus = ahref.parentElement.children[i].value;
            }else if (ahref.parentElement.children[i].name == 'productId') {
                productId = ahref.parentElement.children[i].value;
            }
        }
        var href = "http://localhost:8081/productmapper/delproduct?productId="+productId+"&enableStatus="+enableStatus;
        window.location.href=href;
        $.toast('操作成功');
    })
}

function showPicDetial(ahref) {

}