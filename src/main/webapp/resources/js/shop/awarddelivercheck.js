$(function() {
	var shopId = getQueryString('shopId');
	var useName = '';
	var backUrl = '/shop/mappershopfunction?shopId='+shopId;

	function getList() {
		var listUrl = '/shop/listuserawardmapsbyshop?pageIndex=1&pageSize=9&shopId='+ shopId + '&useName=' + useName;
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var userAwardMapList = data.userAwardMapList;
				var tempHtml = '';
				userAwardMapList.map(function(item, index) {
					tempHtml += '' + '<div class="row row-awarddeliver">'
							+ '<div class="col-33">' + item.point
							+ '</div>'
							+ '<div class="col-33 awarddeliver-time">'
							+ new Date(item.createTime).Format("yyyy-MM-dd HH:mm:ss")
							+ '</div>' + '<div class="col-33">' + item.userName
							+ '</div>' + '</div>';
				});
				$('.awarddeliver-wrap').html(tempHtml);
			}
		});
	}

	$('#search').on('input', function(e) {
        useName = e.target.value;
		$('.awarddeliver-wrap').empty();
		getList();
	});

    $('#back').on('click', function(e) {
		window.location.href =backUrl;
    });

	getList();
});