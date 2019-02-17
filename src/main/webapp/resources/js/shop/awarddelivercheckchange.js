$(function() {
	var shopId = getQueryString('shopId');
	var awardName = '';
	var backUrl = '/shop/mappershopfunction?shopId='+shopId;

	function getList() {
		var listUrl = '/shop/listaward?pageIndex=1&pageSize=9999&shopId='
				+ shopId + '&awardName=' + awardName;
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var userAwardMapList = data.userAwardMapList;
				var tempHtml = '';
				userAwardMapList.map(function(item, index) {
					tempHtml += '' + '<div class="row row-awarddeliver">'
							+ '<div class="col-33">' + item.needPoint
							+ '</div>'
							+ '<div class="col-33 awarddeliver-time">'
							+ new Date(item.createTime).Format("yyyy-MM-dd HH:mm:ss")
							+ '</div>' + '<div class="col-33">' + item.awardName
							+ '</div>' + '</div>';
				});
				$('.awarddeliver-wrap').html(tempHtml);
			}
		});
	}

	$('#search').on('input', function(e) {
		awardName = e.target.value;
		$('.awarddeliver-wrap').empty();
		getList();
	});
    $('#back').on('click', function(e) {
    	window.location.href = backUrl;
	})


	getList();
});