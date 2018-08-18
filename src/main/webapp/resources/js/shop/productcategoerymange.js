$(function() {

    var shopId ;
    getShopId();
    function getShopId() {
        shopId = "?shopId="+getQueryString('shopId');
        initproduction();
    }
    var addUrl = "/productmanage/insertlist"+shopId;

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
                        '<a href='+"/productmapper/editproductcategoery?productId="+item.productionCategoryId+'>编辑</a>'+'&nbsp&nbsp&nbsp&nbsp'+
                        '<a href='+"/productmanage/delproductcategoery?productId="+item.productionCategoryId+'>删除</a>'+'&nbsp&nbsp&nbsp&nbsp'+
                        '<a href='+"/productmanage/showproductcategoery?productId="+item.productionCategoryId+'>预览</a>'+'&nbsp&nbsp&nbsp&nbsp'+
                        '<input type="hidden" name="productId" value=item.productionCategoryId />'+
                        '</div>'+
                        '</div>';
				})
                $('.category-wrap').html(initHtml);
			}
        })
    }
    $('#new').on("click",function(){
        var initHtml="";
        initHtml+='<div class="row row-product new">' +
            '<input class="col-20 productCategoery tmp" type="text" placeholder="分类名" />' +
            '<input class="col-30 priority temp" type="number" placeholder="优先级" />'+
            '<div class="col-50">'+
            '<a href="#" disabled="disabled" >编辑</a>'+'&nbsp&nbsp&nbsp&nbsp'+
            '<a href="#" disabled="disabled" >删除</a>'+'&nbsp&nbsp&nbsp&nbsp'+
            '<a href="#" disabled="disabled" >预览</a>'+'&nbsp&nbsp&nbsp&nbsp'+
            '</div>'+
            '</div>';
        $('.category-wrap').append(initHtml);
    })

    $('#submit').on("click",function(){
        var tempArr = $('.temp');
        //申请一个数组存放新增的对象
        var productCategoryList=[];
        tempArr.map(function(index,item) {

            //申请一个JSON对象存放对象属性
            var tmpObj={};
            tmpObj.productionCategoryName=$(item).val();
            tmpObj.priority=$(item).val();
            if (tmpObj.productionCategoryName!=null && tmpObj.priority!=null){
                productCategoryList.push(tmpObj);
            }
        });
        $.ajax({
            url:addUrl,
            type:'POST',
            data:JSON.stringify(productCategoryList),
            contentType:'application/json',
            success:function (data) {
                if(data.success){
                    $.total('提交成功');
                    initproduction();
                } else{
                    $.total('提交失败')
                }
            }
        })

    })
});