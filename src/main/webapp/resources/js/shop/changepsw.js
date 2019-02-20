$(function() {
	var url = '/shop/changelocalpwd';
	$('#submit').click(function() {
		var userName = $('#userName').val();
		var oldPassWord = $('#oldPassWord').val();
        var newPassWord = $('#newPassWord').val();
        var confirmPassWord = $('#confirmPassWord').val();
		var formData = new FormData();
		formData.append('userName', userName);
		formData.append('oldPassWord', oldPassWord);
		formData.append('newPassword', newPassWord);
		var verifyCodeActual = $('#j_captcha').val();
		if (!verifyCodeActual) {
			$.toast('请输入验证码！');
			return;
		}
		if (newPassWord != confirmPassWord) {
            $.toast('两次输入的密码必须一致');
            return;
		}
		formData.append("verifyCodeActual", verifyCodeActual);
		$.ajax({
			url : url,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					window.location.href = '/shop/shoplist';
				} else {
					$.toast('提交失败！');
					$('#captcha_img').click();
				}
			}
		});
	});

	$('#back').click(function() {
		window.location.href = '/shop/shoplist';
	});
});
