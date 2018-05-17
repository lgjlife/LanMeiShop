/**
 * 商品编辑页面
 * １．分类，名称编辑
 * 2.　ＳＫＵ销售属性编辑
 * 3.　商品属性编辑
 * 4.　商行描述编辑
 * @returns
 */

$(function(){
	var categoryItem;
	var hideCount = 0; //隐藏计数器
	
	/**
	 * 主类目鼠标放置事件
	 * 
	 */
	$("#mainCategoryItem").mouseenter(function(){
		console.log(" mainCategoryItem  mouse enter");
		hideCount = 0;
		categoryItem = "mainCategoryItem"; 
		$("#dynamicProduceTable").show();
		$(".dynamicItemTd").remove();
		getNodes(0);
	})
	/**
	 * 一级类目鼠标放置事件
	 * 
	 */
	$("#firstCategoryItem").mouseenter(function(){
		console.log(" firstCategoryItem  mouse enter");
		var id = $("#mainCategoryItem").attr('value');
		console.log("id = " + id)
		hideCount = 0;
		categoryItem = "firstCategoryItem"; 
		$("#dynamicProduceTable").show();
		$(".dynamicItemTd").remove();
		getNodes(id);
		
	})
	/**
	 * 二级类目鼠标放置事件
	 * 
	 */
	$("#secondCategoryItem").mouseenter(function(){
		console.log(" secondCategoryItem  mouse enter");
		var id = $("#firstCategoryItem").attr('value');
		console.log("id = " + id)
		hideCount = 0;
		categoryItem = "secondCategoryItem"; 
		$("#dynamicProduceTable").show();
		$(".dynamicItemTd").remove();
		getNodes(id);
	})
	/**
	 * 三级主类目鼠标放置事件
	 * 
	 */
	$("#threeCategoryItem").mouseenter(function(){
		console.log("threeCategoryItem  mouse enter");
		
		var id = $("#secondCategoryItem").attr('value');
		console.log("id = " + id)
		
		hideCount = 0;
		categoryItem = "threeCategoryItem"; 
		$("#dynamicProduceTable").show();
		$(".dynamicItemTd").remove();
		getNodes(id);
		
	})
	/**
	 * 很据ｉd获取子节点
	 */
	function getNodes(id){
		var jsonData={"id":""};
		jsonData.id = id;	
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/get/node",
	        contentType : "application/x-www-form-urlencoded",
	        data : jsonData,//JSON.stringify(jsonData),
	        dataType: "text",
	        success:function(data){
	        	var obj = JSON.parse(data);
	        	$(".optionclass0").remove();	        	
	        	for(var i = 0;i < Math.ceil(obj.length/3);i++) 
	    		{
	    	    	var tabletext ;
	    	    	var len = obj.length % 3;
	    	    	if(len == 0){
	    	    		len = 3;
	    	    	}
	    	    	if(i != Math.ceil(obj.length/3)  -1){
	    	    		len = 3;
	    	    	}
	    	    	console.log("len = " + len);
	    	    	for(var j = i*3;j < i*3+len ;j++){
	    	    		tabletext += '<td class="dynamicItemTd" value="'+obj[j].id + '">'+obj[j].name+'</td>';
	    	    		console.log(obj[j].name);
	    	    	}
	    	    	//产生动态元素
	    	    	$("#tableTbody").append('<tr>' + tabletext + '</tr>');
	    	    	tabletext = "";
	    	     }
	        }
		 });
	}
	/*
	 * 事件绑定动态生成的元素
	 * 
	 * */
	$(document).on('click','.dynamicItemTd',function(){
		var name = $(this).text();
		var value = $(this).attr("value");
		console.log("value = " + value);
		if(categoryItem == "mainCategoryItem"){
			$("#mainCategoryItem").text(name);		
			$("#mainCategoryItem").attr('value',value);
			$("#firstCategoryItem").text("所有");
			$("#secondCategoryItem").text("所有");
			$("#threeCategoryItem").text("所有");
		}
		else if(categoryItem == "firstCategoryItem"){
			$("#firstCategoryItem").text(name);		
			$("#firstCategoryItem").attr('value',value);
			$("#secondCategoryItem").text("所有");
			$("#threeCategoryItem").text("所有");
		}
		else if(categoryItem == "secondCategoryItem"){
			$("#secondCategoryItem").text(name);		
			$("#secondCategoryItem").attr('value',value);
			$("#threeCategoryItem").text("所有");
		}
		else if(categoryItem == "threeCategoryItem"){
			$("#threeCategoryItem").text(name);		
			$("#threeCategoryItem").attr('value',value);
		}
		else{
			
		}
		
	})	
	
	/**
	 * 通过商品获取商品　列表
	 * 服务端接受ｉd 后,
	 */
	function  getCommodityList(){
		var jsonData = {"id":""};
		jsonData.id = categoryId;
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/new/commodity",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	    
	        }
		 });
	}
	 /**
	  * 计数点击次数，大于１关闭弹窗
	  */
	 $(document).on("click", function(){
	        
	       hideCount++;
	       if(hideCount >= 1){
	        	$("#dynamicProduceTable").hide();
	        }
	 });
	
})
/**
 * 切换显示和编辑页面处理
 * @returns
 */
$(function(){
	/**
	 * 类别，名称编辑显示切换
	 */
	$("#baseInfoBtn").click(function(){
		if($("#baseInfoBtn").text() == "编辑"){
			$("#baseInfoDisplayMode").hide();
			$("#baseInfoEditMode").show();
			$("#baseInfoBtn").text("返回");
		}
		else if($("#baseInfoBtn").text() == "返回"){
			$("#baseInfoDisplayMode").show();
			$("#baseInfoEditMode").hide();
			$("#baseInfoBtn").text("编辑");
		}		
	})
	/**
	 * ＳＫＵ属性编辑显示切换
	 */
	$("#skuInfoBtn").click(function(){
		if($("#skuInfoBtn").text() == "编辑"){
			$("#skuInfoDisplayMode").hide();
			$("#skuInfoEditMode").show();
			$("#skuInfoBtn").text("返回");
		}
		else if($("#skuInfoBtn").text() == "返回"){
			$("#skuInfoDisplayMode").show();
			$("#skuInfoEditMode").hide();
			$("#skuInfoBtn").text("编辑");
		}		
	})
	/**
	 * 商品属性编辑显示切换
	 */
	$("#attrInfoBtn").click(function(){
		if($("#attrInfoBtn").text() == "编辑"){
			$("#attrInfoDisplayMode").hide();
			$("#attrInfoEditMode").show();
			$("#attrInfoBtn").text("返回");
		}
		else if($("#attrInfoBtn").text() == "返回"){
			$("#attrInfoDisplayMode").show();
			$("#attrInfoEditMode").hide();
			$("#attrInfoBtn").text("编辑");
		}		
	})
})

 /*
  * 商品详情查看和编辑
  *　富文本编辑器操作相关
  * */   
$(function(){
	
	var E = window.wangEditor;
    var editor = new E('#editor');
    // 配置服务器端地址，Ｃontroller 接收地址
    editor.customConfig.uploadImgServer = 'commodity/upload/editor/img';
    //配置属性名称　客户端　＠RequestBody("img") 绑定
    editor.customConfig.uploadFileName = 'img';
    //创建编辑器
    editor.create();
    $("#descriptionSubmitBtn").click(function(){
    	var  descriptionInfo = editor.txt.html();
    	uploadEditorText(descriptionInfo,commodityId);
    })
    function uploadEditorText(descriptionInfo,commodityId){
    	var jsonData = {"descriptionInfo":"","commodityId":""};
		jsonData.descriptionInfo = descriptionInfo;
		jsonData.commodityId = commodityId;
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/new/commodity",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	    
	        }
		 });
    }
    /**
     * 切换显示和编辑状态处理
     */
    $("#descriptionInfoBtn").click(function(){
		if($("#descriptionInfoBtn").text() == "编辑"){
			$("#descriptionDisplayMode").hide();
			$("#descriptionEdiMode").show();
			$("#descriptionInfoBtn").text("返回");
		}
		else if($("#descriptionInfoBtn").text() == "返回"){
			$("#descriptionDisplayMode").show();
			$("#descriptionEdiMode").hide();
			$("#descriptionInfoBtn").text("编辑");
			
			var descriptionInfo = editor.txt.html();
			//清空显示
			$("#descriptionDisplayMode").empty();
			//显示编辑器的内容
			$("#descriptionDisplayMode").prepend(descriptionInfo);
		}		
	})
	//测试按钮
    $("#editorSetBtn").click(function(){
    	editor.txt.html("dsafdfadsfdafdsfds");
    })
     $("#editorGetBtn1").click(function(){
    	 alert(editor.txt.html());
    })
     $("#editorGetBtn2").click(function(){
    	 alert(editor.txt.text());
    })
})
