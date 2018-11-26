$(function () {

    var initUrl = "http://localhost:8081/shopauth/initShopAuth";
    var updateUrl = "http://localhost:8081/shopauth/updateShopAuth";
    var back = "http://localhost:8081/shop/mappershopfunction";

    $('#back').on('click',function () {
        window.location.href = back+"?shopId="+(getQueryString('shopId') != null?getQueryString('shopId'):$('#shopId').val());
    })

    getList();
    
    function getList() {
        var index = $('#index').val();
        var pageSize = $('#pageSize').val();
        var listUrl = initUrl + "?index="+index+"&pageSize="+pageSize+"&shopId="+getQueryString('shopId') ;
        $.getJSON(listUrl, function (data) {
            if (data.success) {
                $('#shopId').val(data.shopId);
                var shopAuthList = data.lists;
                var temp = '';
                shopAuthList.map(function (item, index) {
                    var tesxOp = "恢复";
                    var contraryStatus = 1;
                    //该条认证的状态是未被删除的状态
                    if (item.emableStatus == 2) {
                        tesxOp = "删除";
                    }else {
                        contraryStatus = 2;
                    }

                    temp += '<div class="row row-shopauth">'+
                        '<div class="col-40">'+item.shop.shopName+'</div>';
                    if (item.titleFlag) {
                        temp += '<div class = "col-20">'+item.jobName+"</div>"+
                            '<div class = "col-40">' +
                            '<a href="javascript:void(0)" class="edit" data-employee-id="'+item.emmployee.userId+'" data-auth-id="'+item.shopAuthId+'">编辑</a>'+
                            '<a href="javascript:void(0)" data-auth-id="'+item.shopAuthId+'" data-status="'+contraryStatus+'" >'+tesxOp+'</a>'+
                            '</div>';
                    }else {
                        temp += '<div class = "col-20">'+item.title+"</div>"+
                            '<div class = "col-40"><span>不可操作</span></div>';
                    }
                    temp += '</div>';
                })
                $('.shopauth-wrap').html(temp);
            }
        })
    }

    $('.shopauth-wrap').on('click','a',function (e) {
        var target = $(e.currentTarget);
        if (target.hasClass('edit')) {
            window.location.href="http://localhost:8081/shop/shopauthedit?shopAuthId="+e.currentTarget.dataset.authId;
        }else {
            changeStatuss(e.currentTarget.dataset.authId,e.currentTarget.dataset.status);
        }
    })

    function changeStatuss(id,status) {
        var shopAuth = {};
        shopAuth.shopAuthId = id;
        shopAuth.emableStatus = status;
        $.confirm("确定么？",function () {
            $.ajax({
                url:updateUrl,
                type:'POST',
                data:{
                    shopAuthStr:JSON.stringify(shopAuth),
                    statusChange:true
                },
                dataType:'json',
                success:function f(data) {
                    if (data.success) {
                        $.toast('操作成功')
                        getList();
                    }else{
                        $.toast(data.errorMsg);
                    }
                }
            })
        })
    }
})