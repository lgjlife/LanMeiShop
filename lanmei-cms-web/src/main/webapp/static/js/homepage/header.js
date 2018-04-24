/**
 * 跳转到个人中心
 * @returns
 */
$(function(){
	$("#infoLink").click(function(){
		
		var context = $("contextPathData").attr("contextPathValue");
		console.log("infoLink into " + context);
		$(location).attr("href",context + "/user-info");
	});
	
});
/**
 * 头部 登录显示
 * @returns
 */
$(function(){

	console.log("进入主界面");
	
	var user = $("#ServiceInfo").attr("user");	
	if(user == ""){
		$("#LoginedTag").hide();
		$("#loginTag").show();
		console.log("user = null" );
	}
	else{
		$("#LoginedTag").show();
		$("#loginTag").hide();
		console.log("user != null" );	
	}
});