$(function(){
	
	$("#invitationCodeBtn").click(function(){
		var random = getRandom(100000,999999).toString();
		$("#invitationCode").val(random);
	});
	
	
});

function getRandom(start,end){
	var random = Math.random()*end + start;
	
	return Math.round(random);
}