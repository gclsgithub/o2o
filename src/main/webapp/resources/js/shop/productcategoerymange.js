$(function() {

    var shopId ;
    getShopId();
    function getShopId() {
        shopId = "?shopId="+getQueryString('shopId');
        initproduction();
    }
    var addUrl = "/productmanage/insertlist"+shopId;
    var deleteUrl = "/productmanage/delproductcategoery";


	function initproduction() {
    	var initUrl="/productmanage/productinit"+shopId;
    	var initHtml="";
    	//初期化情况
        $('.category-wrap').html(initHtml);
    	$.getJSON(initUrl,function (data) {
			if(data.success){
                data.productCategoeryList.map(function(item ,index){
                    initHtml+='<div class="row row-product now">'+
                        '<div class="col-20">'+item.productionCategoryName+'</div>'+
                        '<div class="col-30">'+item.priority+'</div>'+
                        '<div class="col-50">'+
                        '<a href='+"/productmapper/editproductcategoery?productionCategoryId="+item.productionCategoryId+'>编辑</a>'+'&nbsp&nbsp&nbsp&nbsp'+
                        '<a href="javascript:void(0);" class="delete" onclick="js_delNow(this)" data-id="'+item.productionCategoryId+'" >删除</a>'+'&nbsp&nbsp&nbsp&nbsp'+
                        '<a href='+"/productmanage/showproductcategoery?productId="+item.productionCategoryId+'>预览</a>'+'&nbsp&nbsp&nbsp&nbsp'+
                        '<input type="hidden" name="productId" value="item.productionCategoryId" />'+
                        '</div>'+
                        '</div>';
				})
                $('.category-wrap').html(initHtml);
			}
        })
    }

    js_delTemp = function(line){
        console.log(line.parentElement.parentElement);
        line.parentElement.parentElement.remove();
    }

    $('#new').on("click",function(){
        var initHtml="";
        initHtml+='<div class="row row-product temp">' +
            '<input class="col-20 productCategoery" type="text" placeholder="分类名" />' +
            '<input class="col-30 priority" type="number" placeholder="优先级" />'+
            '<div class="col-50">'+
            '<a href="#" disabled="disabled" >编辑</a>'+'&nbsp&nbsp&nbsp&nbsp'+
            '<a href="javascript:void(0);"class="delete" onclick="js_delTemp(this)">删除</a>'+'&nbsp&nbsp&nbsp&nbsp'+
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
            tmpObj.productionCategoryName=$(item).find('.productCategoery').val();
            tmpObj.priority=$(item).find('.priority').val();
            if (tmpObj.productionCategoryName && tmpObj.priority){
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
                    $.toast('提交成功');
                    initproduction();
                } else{
                    $.toast('提交失败');
                }
            }
        })
    })
    js_delNow = function(line){
        var productionCategoryId = line.dataset.id;
        var  parseDate = {
                productionCategoryId : productionCategoryId,
            };
        $.confirm('确定么?', function() {
            $.ajax({
                url : deleteUrl,
                type : 'POST',
                data : JSON.stringify(parseDate),
                contentType:'application/json',
                success : function(data) {
                    if (data.success) {
                        $.toast('删除成功');
                        initproduction();
                    } else {
                        $.toast('删除失败');
                    }
                }
            });
        });
    }
});