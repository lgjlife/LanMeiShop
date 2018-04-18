$(function(){
	$("#infoLink").click(function(){
		
		var context = $("contextPathData").attr("contextPathValue");
		console.log("infoLink into " + context);
		$(location).attr("href",context + "/user-info");
	});
	
});