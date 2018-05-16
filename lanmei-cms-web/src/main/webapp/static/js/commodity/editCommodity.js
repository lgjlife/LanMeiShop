//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
/*var ue = UE.getEditor('editor');

function  sendEditorContent(content){
	console.log("content = " + content);
	var jsonData = {"content":""};
	jsonData.content = content;
	$.ajax({
        type : "post",
        url : "/lanmei-cms/commodity/set/content",
        contentType : "application/json;charset=utf-8",
        data : JSON.stringify(jsonData),
        dataType: "json",
        success:function(data){
    
        }
	 });
}

function createEditor() {
    UE.getEditor('editor');
    }
  
    function getContent() {
    	console.log("getContent = " + UE.getEditor('editor').getContent());
    var arr = [];
    arr.push("使用editor.getContent()方法可以获得编辑器的内容hhhh");
    arr.push("内容为：");
    arr.push(UE.getEditor('editor').getContent());
    sendEditorContent(UE.getEditor('editor').getContent());
    alert(arr.join("\n"));
   
}*/
    
$(function(){
	
	var E = window.wangEditor;
    var editor = new E('#editor');
    // 配置服务器端地址
    editor.customConfig.uploadImgServer = 'commodity/upload/editor/img';
    editor.customConfig.uploadFileName = 'img';
    editor.create();
    
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