$(function(){
	/**
	 * 大类
	 */
	$("#bigCategoryInput").mouseenter(function(){
		console.log("bigCategoryInput  mouseenter")
		var jsonData={"id":""};
		jsonData.id = 0;	
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/get/node",
	        contentType : "application/x-www-form-urlencoded",
	        data : jsonData,//JSON.stringify(jsonData),
	        dataType: "text",
	        success:function(data){
	        	var obj = JSON.parse(data);
	        	$(".optionclass0").remove();
	        	for(var i = 0 ; i < obj.length ;i++){
	        		console.log("obj.name = " + obj[i].name );
	        		txt  = "<option value='" + obj[i].id + "--" + obj[i].name + "' class='optionclass0'></option> ";
	        		$("#datalist0").append(txt);
	        		
	        	}
	        }
		 });
	});
	/**
	 * 一级细分
	 */
	$("#firstCategoryInput").mouseenter(function(){
		console.log("firstCategoryInput  mouseenter")
		var str = $("#bigCategoryInput").val();
		id = getNumByStr(str);
		console.log("id = " + id);
		var jsonData={"id":""};
		jsonData.id = id.toString();// Number.valueOf(id);	
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/get/node",
	        contentType : "application/x-www-form-urlencoded",
	        data : jsonData,//JSON.stringify(jsonData),
	        dataType: "text",
	        success:function(data){
	        	var obj = JSON.parse(data);
	        	$(".optionclass1").remove();
	        	for(var i = 0 ; i < obj.length ;i++){
	        		console.log("obj.name = " +obj[i].id + "--" +  obj[i].name );
	        		txt  = "<option value='" + obj[i].id + "--" + obj[i].name
	        				+ "' class='optionclass1'></option> ";
	        		$("#datalist1").append(txt);
	        		
	        	}
	        }
		 });
	});
	/**
	 *　二级细分
	 */
	$("#secondCategoryInput").mouseenter(function(){
		console.log("secondCategoryInput  mouseenter")
		var str = $("#firstCategoryInput").val();
		id = getNumByStr(str);
		console.log("id = " + id);
		var jsonData={"id":""};
		jsonData.id = id.toString();// Number.valueOf(id);	
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/get/node",
	        contentType : "application/x-www-form-urlencoded",
	        data : jsonData,//JSON.stringify(jsonData),
	        dataType: "text",
	        success:function(data){
	        	var obj = JSON.parse(data);
	        	$(".optionclass2").remove();
	        	console.log("obj = " + obj);
	        	for(var i = 0 ; i < obj.length ;i++){
	        		console.log("obj.name = " +obj[i].id + "--" +  obj[i].name );
	        		txt  = "<option value='" + obj[i].id + "--" + obj[i].name
	        				+ "' class='optionclass2'></option> ";
	        		$("#datalist2").append(txt);
	        		
	        	}
	        }
		 });
	});
	/**
	 * 从字符串中提取数字
	 * 使用正则表达式
	 */
	function getNumByStr(str){
		
		var patt1 = /[0-9]+/;
		return (str.match(patt1));
	}
	
	/**
	 * 提交按钮
	 */
	$("#addCommoditySubmitBtn").click(function(){
		console.log("addCommoditySubmitBtn  click");
	});
	/**
	 * 添加商品　Ajax 请求
	 */
	function addCommoditySubmit(){
		var jsonData={"name":"","brand":"","title":"","reference_price":"",
				"activity_price":"","description":"","id":""};
		jsonData.brand = $("#brandInput").val();
		jsonData.name = $("#productNameInput").val();
		jsonData.title = $("#titleInput").val();
		jsonData.reference_price = $("#referencePriceInput").val();
		jsonData.activity_price = $("#activityPriceInput").val();
		jsonData.description = $("#descriptionInput").val();
		//二级分类ＩＤ
		var str = $("#secondCategoryInput").val();
		id = getNumByStr(str);
		jsonData.id = id;
		
		jsonData.id = id.toString();// Number.valueOf(id);	
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/get/node",
	        contentType : "application/json,charset=UTF-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	    
	        }
		 });
	}
	
	/**
	 * 上传文件按钮
	 */
	$("#imgUnloadBtn").click(function(){
		console.log("imgUnloadBtn  click");
		unloadFile();
	});
	/**
	 *上传文件Ａjax处理
	 */
	function unloadFile(){
		
		var formData = new FormData($("#imgUploadForm")[0]);
		$.ajax({
            type: "POST",
            url: "/lanmei-cms/commodity/upload/img",
            data: formData,
            async: false,  
            cache: false,  
            contentType: false,  
            processData: false,
            success: function (data) {
            	alert(data);
            },
            error: function(data) {
                alert("error:"+data.responseText);

             }
        });
	}
	
	
});