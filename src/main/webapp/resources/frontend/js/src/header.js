$(document).ready(function() {
	$.ajax({
		type : 'get',
		url : 'countDown',
		cache : true,
		dataType : 'json',
		success : function(data) {
			$("#countDown").html(data.object)
		}
	});
});