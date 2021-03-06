$(function() {
	var awardId = getQueryString('awardId');
	var shopId = getQueryString('shopId');
	var infoUrl = '/shop/getawardbyid?awardId=' + awardId;
	var awardPostUrl = '/shop/modifyaward';
	var isEdit = false;
	if (awardId) {
		getInfo(awardId);
		isEdit = true;
	} else {
		awardPostUrl = '/shop/modifyaward';
	}

	$("#pass-date").calendar({
	    value: ['2017-12-31']
	});

	function getInfo(id) {
		$.getJSON(infoUrl, function(data) {
			if (data.success) {
				var award = data.award;
				$('#award-name').val(award.awardName);
				$('#priority').val(award.priority);
				$('#award-desc').val(award.awardDesc);
				$('#point').val(award.point);
			}
		});
	}

    $('#back').click(function() {
        window.location.href = '/shop/awardmanage?shopId='+shopId;
    });
	$('#submit').click(function() {
		var award = {};
		award.awardName = $('#award-name').val();
		award.priority = $('#priority').val();
		award.awardDesc = $('#award-desc').val();
		award.point = $('#point').val();
		award.awardId = awardId ? awardId : '';
		award.expireTime = $('#pass-date').val();
		award.shopId = shopId;
		console.log(award.expireTime);
		var thumbnail = $('#small-img')[0].files[0];
		var formData = new FormData();
		formData.append('thumbnail', thumbnail);
		formData.append('awardStr', JSON.stringify(award));
		var verifyCodeActual = $('#j_captcha').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		formData.append("verifyCodeActual", verifyCodeActual);
		$.ajax({
			url : awardPostUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					$('#captcha_img').click();
				} else {
					if (data.errorMsg != null || data.errorMsg != ''|| data.errorMsg != undefined){
                        $.toast(data.errorMsg);
					}else {
                        $.toast('提交失败！');
                    }
                    $('#captcha_img').click();
                }
			}
		});
	});
});