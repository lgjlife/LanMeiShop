
$(document).ready(function(){
  $("#json_btns").click(function(){
	  console.log("json_btn执行开始");
	  $.ajax({
	        type : "post",
	        url : "${projectPath}/json/json",
	        contentType : "application/json;charset=utf-8",
	        //数据格式是json串,传进一个person
	        data : '{"age" : "17","userName" : "liangguojian","passWord":"1234565"}',
	      
	        success:function(data){
	            console.log("服务器处理后的age是:" + data.age);
	            console.log("服务器处理后的userName是:" + data.userName);
	            console.log("服务器处理后的passWord是:" + data.passWord);
	        }

	    });
	  console.log("json_btn执行结束");
  });
});

function sendJson(path){
	
	var age = $("#inputval").val();
	console.log("json_btn执行开始.... age = " + age);
	  $.ajax({
	        type : "post",
	        url : "json/json",
	        contentType : "application/json;charset=utf-8",
	        //数据格式是json串,传进一个person
	        data : '{"age" : ${age},"userName" : "liangguojian","passWord":"1234565"}',
	      
	        success:function(data){
	            console.log("服务器处理后的age是:" + data.age);
	            console.log("服务器处理后的userName是:" + data.userName);
	            console.log("服务器处理后的passWord是:" + data.passWord);
	        }

	    });
	  console.log("json_btn执行结束....");
}
function sendmapJson(){
	
	var age = $("#inputval").val();
	console.log("sendmapJson  执行开始.... age = " + age);
	  $.ajax({
	        type : "post",
	        url : "json/map",
	        contentType : "application/json;charset=utf-8",
	        //数据格式是json串,传进一个person
	        data : '{"age" : "123","userName" : "liangguojian","passWord":"1234565"}',
	      
	        success:function(data){
	            console.log("服务器处理后的value1是:" + data.value1);
	            console.log("服务器处理后的value2是:" + data.value2);
	            console.log("服务器处理后的value3是:" + data.value3);
	        }

	    });
	  console.log("json_btn执行结束....");
}
//JSON.stringify
function formToJson(){

	$('#formDemo').append("userName = " + $("#formDemo").attr("userName")
						+ "  passWord = " + $('#formDemo').attr('passWord')
						+ "  phoneNum = " + $('#formDemo').attr('phoneNum'));
	//return $('#formDemo').serializeJSON();
	
	//$('#formDemo').serializeJSON()返回的是一个Object体
	//JSON.stringify($('#formDemo').serializeJSON())返回的是JSON字符串
	return JSON.stringify($('#formDemo').serializeJSON());
}

function sendformJson(){
	var json={"name":"liang"};
	var jsondata = formToJson();
	
	console.log("sendformJson  执行开始....  ");
	console.log("jsondata = " + jsondata.userName + "  json  name =" + json.name);
	  $.ajax({
	        type : "post",
	        url : "json/form",
	        contentType : "application/json;charset=utf-8",
	        //数据格式是json串,传进一个person
	        data : jsondata,
	        dataType: "json",
	        success:function(data){
	            console.log("服务器返回的userName是:" + data.userName);
	            console.log("服务器返回后的passWord是:" + data.passWord);
	            console.log("服务器返回后的phoneNum是:" + data.phoneNum);
	        }

	    });
	  console.log("sendformJson 执行结束....");
}