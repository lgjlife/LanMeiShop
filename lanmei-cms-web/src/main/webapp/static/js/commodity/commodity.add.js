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
	        		//清除其他分类
	        		
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
	 *　三级细分　品牌　
	 */
	$("#threeCategoryInput").mouseenter(function(){
		console.log("threeCategoryInput  mouseenter")
		var str = $("#secondCategoryInput").val();
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
	        	$(".optionclass3").remove();
	        	console.log("obj = " + obj);
	        	for(var i = 0 ; i < obj.length ;i++){
	        		console.log("obj.name = " +obj[i].id + "--" +  obj[i].name );
	        		txt  = "<option value='" + obj[i].id + "--" + obj[i].name
	        				+ "' class='optionclass3'></option> ";
	        		$("#datalist3").append(txt);
	        		
	        	}
	        }
		 });
	});
	$("#bigCategoryInput").change(function(){
		console.log( "bigCategoryInput change ");
		$(".optionclass1").remove();
		$(".optionclass2").remove();
		$(".optionclass3").remove();
		$("#firstCategoryInput").val("");
		$("#secondCategoryInput").val("");
		$("#threeCategoryInput").val("");
		
	})
	$("#firstCategoryInput").change(function(){
		console.log( "firstCategoryInput change ");
		$(".optionclass2").remove();
		$(".optionclass3").remove();
		$("#secondCategoryInput").val("");
		$("#threeCategoryInput").val("");
	})
	$("#secondCategoryInput").change(function(){
		console.log( "secondCategoryInput change ");
		$(".optionclass3").remove();
		$("#threeCategoryInput").val("");
	})
    
	/**
	 * 从字符串中提取数字
	 * 使用正则表达式
	 */
	function getNumByStr(str){
		
		var regexp = /[0-9]+/;
		return (str.match(regexp));
	}
	$("#getBrandBtn").click(function(){
		var res = getBrandByRegexp("49-坚果１");
		console.log(res);
	})
	/**
	 * 通过正则表达式去除数字和-
	 * 输入 123--蓝莓321
	 * 输出　蓝莓321
	 */
	function getBrandByRegexp(str){
		var regexp =/^[0-9]+-+/g;
	    return str.replace(regexp,"");
	}
	/**
	 * 提交按钮
	 */
	$("#addCommoditySubmitBtn").click(function(){
		console.log("addCommoditySubmitBtn  click");
		addCommoditySubmit();
	});
	/**
	 * 添加商品　Ajax 请求
	 */
	function addCommoditySubmit(){
		var jsonData={"name":"","categoryId":"","brand":"","title":""};
		jsonData.name = $("#productNameInput").val();
		console.log("name = " + jsonData.name);
		var idAndBrand = $("#threeCategoryInput").val();
		//三级分类ＩＤ 品牌　ｉd
		
		var  num = getNumByStr(idAndBrand);
		jsonData.categoryId =  Number(num);
		//品牌
		jsonData.brand = getBrandByRegexp(idAndBrand);
		//显示标题
		jsonData.title = $("#titleInput").val();
		console.log("title = " + JSON.stringify(jsonData));
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/new/commodity",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	console.log("state = " + data.data);
	        	if(data.data == "ADD_COMMODITY_FAIL"){
	        		$("#addCommoditySubmitWarn").text("创建商品失败！");
	        		
	        	}
	        	else{
	        		$("#addCommoditySubmitWarn").text("创建商品成功！");
	        	}
	        }
		 });
	}
	/**
	 * 商品名称改变事件
	 * 向数据库查询是否已经存在该商品
	 */
	/*$("#productNameInput").change(function(){
		console.log("productNameInput   change " );
		var jsonData = {"name":""};
		jsonData.name = $("#productNameInput").val();
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/check/name",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	console.log("state = " + data.data);
	        	if(data.data == "COMMODITY_NAME_REPEAT"){
	        		$("#productNameInputWarn").text("该商品已经存在，请进行更改");
	        	}
	        	else{
	        		$("#productNameInputWarn").text("");
	        	}
	        }
		 });
	})*/
	/**
	 * 上传文件按钮
	 */
	$("#imgUnloadBtn").click(function(){
		console.log("imgUnloadBtn  click");
		unloadFile();//上传文件处理
	});
	/**
	 *上传文件Ａjax处理
	 */
	function unloadFile(){
		
		var formData = new FormData($("#imgUploadForm")[0]);
		var commodityId = 1;
		var addUrl = "?commodityId=" + commodityId;
		$.ajax({
            type: "POST",
            url: ("/lanmei-cms/commodity/upload/img" + addUrl),
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