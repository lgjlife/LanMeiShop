/**
 * 商品编辑页面
 * １．分类，名称编辑
 * 2.　ＳＫＵ销售属性编辑
 * 3.　商品属性编辑
 * 4.　商行描述编辑
 * @returns
 */
//公共变量
var currentEditCommodityData = {"mainCategory":"","firstCategory":"","secondCategory":"",
		    "commodityId":"","brand":"","name":"","title":"","createBy":"",
			"createTime":"","putOnSaleTime":"","putOffSaleTime":"","isPutOff":""};
/**
 * 商品查询列表展示
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
		$("#categoryItemDiv").show();
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
		$("#categoryItemDiv").show();
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
		$("#categoryItemDiv").show();
		$(".dynamicItemTd").remove();
		getNodes(id);
	})
	/**
	 * 三级类目鼠标放置事件
	 * 
	 */
	$("#threeCategoryItem").mouseenter(function(){
		console.log("threeCategoryItem  mouse enter");
		
		var id = $("#secondCategoryItem").attr('value');
		console.log("id = " + id)
		
		hideCount = 0;
		categoryItem = "threeCategoryItem"; 
		$("#categoryItemDiv").show();
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
	    	    	$("#categoryItemTableTbody").append('<tr>' + tabletext + '</tr>');
	    	    	tabletext = "";
	    	     }
	        }
		 });
	}
	/**
	 * 很据ｉd获取所有符合类的商品
	 */
	function getComodityList(id){
		var jsonData={"id":""};
		jsonData.id = id;	
		$.ajax({
	        type : "get",
	        url : "/lanmei-cms/commodity/get/commodity/list",
	        contentType : "application/x-www-form-urlencoded",
	        data : jsonData,//JSON.stringify(jsonData),
	        dataType: "text",
	        success:function(data){
	        	var jsonData = JSON.parse(data);
	        	var commodityData = jsonData.data;
	        
	        	$("#commodityLisTableTbody").empty();
	        	if(commodityData != null){
	        		commodityDataDisplay(commodityData);
	        	}
	        	
	        }
		 });
	}
	/*
	 * 事件绑定动态生成的元素
	 * 选择类别
	 * */
	$(document).on('click','.dynamicItemTd',function(){
		var name = $(this).text();
		var value = $(this).attr("value");
		getComodityList(value);
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
	 * 商品数据　
	 * 商品id , 商品品牌　,　商品名称，显示标题，创建人，创建时间，上架时间，下架时间，是否下架
	 */
	var  commodityData = [
		{"commodityId":"1","brand":"华为","name":"P20","title":"全网通手机","createBy":"李白",
		"createTime":"2018-05-19　14：00:00","putOnSaleTime":"2018-05-20　14：00:00","putOffSaleTime":"2018-06-19　14：00:00","isPutOff":"false"},
		{"commodityId":"2","brand":"华为","name":"P30","title":"全网通手机","createBy":"张三",
		"createTime":"2018-05-20　14：00:00","putOnSaleTime":"2018-05-21　14：00:00","putOffSaleTime":"2018-05-22　14：00:00","isPutOff":"true"}
	];
	/**
	 * 展开查询列表
	 */
	$("#queryDisplayCtrlBtn").click(function(){	
		if($("#queryDisplayCtrlBtn").text() == "展开查询列表"){
			//收起状态－－＞展开状态
			$("#queryDisplayCtrlBtn").text("收起查询列表");
			$("#commodityLisTableTbody").empty();
			//commodityDataDisplay(commodityData);
			$("#commodityListModel").show();
			 //getCommodityList(0, 1, 10);
			getComodityList(0);
		}
		else{
			//展开状态－－＞收起状态
			$("#queryDisplayCtrlBtn").text("展开查询列表");
			$("#commodityListModel").hide();
		}
		
	})
	
	/**
	 * 表格显示商品数据　
	 */
	function commodityDataDisplay(commodityData){
		var commodityDataDisPlayText;
		console.log("commodityData = " + JSON.stringify(commodityData));
		
		for(var i = 0; i <  commodityData.length;i++ ){
			//转换时间
			var  date = new Date();
			var  createTime = "";
			var  putOffSaleTime = "";
			var  putOnSaleTime = "";
			
			if(commodityData[i].createTime != null){
				date.setTime(commodityData[i].createTime);
				createTime =  date.Format("yyyy-MM-dd hh:mm:ss");	
			}
			if(commodityData[i].putOffSaleTime != null){
				
				date.setTime(commodityData[i].putOffSaleTime);
				putOffSaleTime =  date.Format("yyyy-MM-dd hh:mm:ss"); 			
			}
			if(commodityData[i].putOnSaleTime != null){
				date.setTime(commodityData[i].putOnSaleTime);
				putOnSaleTime =  date.Format("yyyy-MM-dd hh:mm:ss"); 
			}		
			
			//获取销售状态
			var saleState = commodityData[i].saleState;
			console.log(" saleState = " + saleState);
			if(saleState == -1){
				saleState = "未上架";
			}
			else if(saleState == 0){
				saleState = "已上架";
			}
			else if(saleState == 1){
				saleState = "已下架";
			}
			console.log(" saleState = " + saleState);
			commodityDataDisPlayText = '<tr>'
				+ '<td id="commodity-list-id">' + commodityData[i].commodityId + '</td>'
				+ '<td id="commodity-list-brand">' + commodityData[i].brand + '</td>'
				+ '<td id="commodity-list-name">' + commodityData[i].name + '</td>'
				+ '<td id="commodity-list-title">' + commodityData[i].title + '</td>'
				+ '<td id="commodity-list-createBy">' + commodityData[i].createBy + '</td>'
				+ '<td id="commodity-list-createTime" value="' + commodityData[i].createTime + '">' + createTime + '</td>'
				+ '<td id="commodity-list-putOffSaleTime" value="' + commodityData[i].putOffSaleTime + '">' + putOffSaleTime + '</td>'
				+ '<td id="commodity-list-putOnSaleTime" value="' + commodityData[i].putOnSaleTime + '">' + putOnSaleTime + '</td>'
				+ '<td id="commodity-list-isPutOff">' + saleState + '</td>'
				+ '<td>' + '<button class="commodityListEditBtn">编辑</button>' 
				+ '<button class="commodityListDeleteBtn">删除</button>'+ '</td>'
				+ '</tr>';
			
			$("#commodityLisTableTbody").append(commodityDataDisPlayText);
		}
		
	}
	/**
     * 绑定动态元素 编辑按钮
     */
     $(document).on('click','.commodityListEditBtn',function(){
    	 //获取父及元素
    	var $parent = $(this).parent().parent();
    	//
    	
    	 //获取值
    	//currentEditCommodityData.mainCategory = $parent.find('#commodity-list-id').text();
    	//currentEditCommodityData.firstCategory = $parent.find('#commodity-list-brand').text();
    	//currentEditCommodityData.secondCategory = $parent.find('#commodity-list-name').text();
    	currentEditCommodityData.commodityId = $parent.find('#commodity-list-id').text();
    	currentEditCommodityData.brand = $parent.find('#commodity-list-brand').text();
    	currentEditCommodityData.name = $parent.find('#commodity-list-name').text();
    	currentEditCommodityData.title = $parent.find('#commodity-list-title').text();
    	currentEditCommodityData.createBy = $parent.find('#commodity-list-createBy').text();
    	currentEditCommodityData.createTime = $parent.find('#commodity-list-createTime').text();
    	currentEditCommodityData.putOffSaleTime = $parent.find('#commodity-list-putOffSaleTime').text();
    	currentEditCommodityData.putOnSaleTime = $parent.find('#commodity-list-putOnSaleTime').text();
    	currentEditCommodityData.isPutOff = $parent.find('#commodity-list-isPutOff').text();
   	    console.log("attrData = " + JSON.stringify(currentEditCommodityData));
   	 
   	    $("#commodityEditModel").show();
		
   	    var titleText = "当前编辑的商品为:" + "编号：" + currentEditCommodityData.commodityId
			+ "  名称：" + currentEditCommodityData.name;
   	    $("#commodityEditModelTitle").empty();
		$("#commodityEditModelTitle").append(titleText);
		
     })
     /**
     * 绑定动态元素 删除按钮
     */
     $(document).on('click','.commodityListDeleteBtn',function(){
    	 //获取父及元素
    	var $parent = $(this).parent().parent();

    	 //获取值
    	var id = $parent.find('#commodity-list-id').text();
    	
    	deleteCommodity(id);
    	
     })
     /**
 	 * 很据商品id删除商品
 	 */
 	function deleteCommodity(id){
 		var jsonData={"id":""};
 		jsonData.id = id;	
 		$.ajax({
 	        type : "get",
 	        url : "/lanmei-cms/commodity/delete/commodity",
 	        contentType : "application/x-www-form-urlencoded",
 	        data : jsonData,//JSON.stringify(jsonData),
 	        dataType: "text",
 	        success:function(data){
 	        	
 	        	
 	        }
 		 });
 	}
	/**
	 * 从服务器获取商品列表
	 * 
	 */
	function  getCommodityList(categoryPid, page, everyPageCount){
		var jsonData = {"categoryPid":"","page":"","everyPageCount":"",};
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/get/list",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	
	        }
		 });
	}
	
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
	       console.log("hideCount = " + hideCount );
	       if(hideCount >= 1){
	        	$("#categoryItemDiv").hide();
	        }
	 });
	
})
/**
 * 商品类别　名称　显示标题　更改
 * @returns
 */
$(function(){
	
	/**
     * 点击展开按钮处理
     * 
     */
    $("#baseInfoEditDispalyCtrlBtn").click(function(){
   	
    	if($("#baseInfoEditDispalyCtrlBtn").text() == "展开"){
    		$("#baseInfoEditDispalyCtrlBtn").text("收起");
    		$("#baseInfoEditDispalyCtrl").show();
    		console.log("baseInfoEditDispalyCtrlBtn 展开");
    		//向服务端请求数据
    		//getSkuInfoFromServer(commodityId);
    		$("#baseInfoDisBrand").text("品　　牌：" + currentEditCommodityData.brand );
    		$("#baseInfoDisName").text("产品名称：" + currentEditCommodityData.name );
    		$("#baseInfoDisTitle").text("产品标题：" + currentEditCommodityData.title );
    	}
    	//　展开状态－－－＞收起状态
    	else if($("#baseInfoEditDispalyCtrlBtn").text() == "收起"){
    		$("#baseInfoEditDispalyCtrlBtn").text("展开");
    		$("#baseInfoEditDispalyCtrl").hide();
    		console.log("baseInfoEditDispalyCtrlBtn 关闭");
    	}
    })
    //function 
	
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
	
	
})
/**
 * SKU属性处理
 * @returns
 */
$(function(){
	var skuAttrData =[
		{"skuId":"1","skuCollectId":"1","name":"颜色","attr":"亮黑色","price":"","stock":""},
		{"skuId":"2","skuCollectId":"2","name":"颜色","attr":"宝石蓝","price":"","stock":""},
		{"skuId":"3","skuCollectId":"3","name":"颜色","attr":"极光色","price":"","stock":""},
		{"skuId":"4","skuCollectId":"4","name":"内存","attr":"32Ｇ","price":"","stock":""},
		{"skuId":"5","skuCollectId":"5","name":"内存","attr":"64Ｇ","price":"","stock":""}
		];
	/**
     * 点击展开按钮处理
     * 展开请求ＳＫＵ数据
     */
    $("#skuInfoEditDispalyCtrlBtn").click(function(){
   	
    	if($("#attrInfoEditDispalyCtrlBtn").text() == "展开"){
    		$("#attrInfoEditDispalyCtrlBtn").text("收起");
    		$("#skuInfoEditDispalyCtrl").show();
    		console.log("attrInfoEditDispalyCtrlBtn 展开");
    		//向服务端请求数据
    		//getSkuInfoFromServer(commodityId);
    		//向服务端请求销售数据
    		getSkuAttr(currentEditCommodityData.commodityId);
    		
    	}
    	//　展开状态－－－＞收起状态
    	else if($("#attrInfoEditDispalyCtrlBtn").text() == "收起"){
    		$("#attrInfoEditDispalyCtrlBtn").text("展开");
    		$("#skuInfoEditDispalyCtrl").hide();
    		console.log("attrInfoEditDispalyCtrlBtn 关闭");
    	}
    })
    /**
     * 从服务器获取sku属性
     * ＠Ｐaram : commodityId 商品ID
     */
    function getSkuInfoFromServer(commodityId){
    	
    	var jsonData = {"commodityId":""};
		jsonData.commodityId = commodityId;
		$.ajax({
	        type : "get",
	        url : "/lanmei-cms/commodity/get/sku/info",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	
	        }
		 });
    }
    /**
     * 绑定动态元素 删除按钮
     */
     $(document).on('click','.sku-attr-delete-btn-class',function(){
    	 //获取父及元素
    	var $parent = $(this).parent();

    	 //获取值
    	var skuId = $parent.attr("skuId");
    	
    	console.log("删除的　skuId = " + skuId);
    	deleteSkuAttr(skuId);
     })
    /**
     *　动态生成元素
     *　显示ＳＫＵ属性
     */
    function displaySkuInfo(SkuInfoData){
    	
    //	var skuInfoText = 
    }
    
    /**
     * 添加属性按钮按下
     */
    $("#addSkuAttrInputBtn").click(function(){
    	console.log("addSkuAttrInputBtn 按下");
    	
    	var name = $("#addSkuAttrInput-name").val();
    	var attr = $("#addSkuAttrInput-attr").val();
    	//发送设置请求给服务端
    	setSkuAttr(currentEditCommodityData.commodityId,name,attr);
    	
    })
    /**
     * 向服务端发送设置的销售属性
     */
    function setSkuAttr(commodityId,name,attr){
    	
    	var jsonData = {"commodityId":"","name":"","attr":""};
		jsonData.commodityId = commodityId;
		jsonData.name = name;
		jsonData.attr = attr;
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/set/sku/attr",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	console.log(data);
	        	console.log(data.success);
	        	var returnData = data;//JSON.parse(data);
	        	if(returnData.success == true){
	        		if(returnData.data == "SET_SKU_ATTR_SUCCESS"){
	        			console.log("sku属性添加成功");
	        			$("#addSkuAttrInputWarn").text("{名称:"+jsonData.name + " 属性：" +  jsonData.attr + "} 属性添加成功");
	        		}
	        		else if(returnData.data == "SET_SKU_ATTR_FAIL"){
	        			console.log("sku属性添加失败");
	        			$("#addSkuAttrInputWarn").text("{名称:"+jsonData.name + " 属性：" +  jsonData.attr + "} 属性添加失败");
	        		}
	        	}	        	
	        }
		 });
    }
    /**
     * 向服务端获取sku属性
     */
    function getSkuAttr(commodityId){

    	console.log("commodityId = " + commodityId);
    	console.log("String.valueOf(commodityId) = " + String.valueOf(2))
    	var jsonData = {"commodityId":""};
		jsonData.commodityId = commodityId;
		var sendData= "commodityId="  + commodityId;
		$.ajax({
	        type : "get",
	        url : "/lanmei-cms/commodity/get/sku/attr",
	        contentType : "application/json;charset=utf-8",
	        data :sendData,// JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	console.log(data);
	        	console.log(data.success);
	        	var returnData = data;//JSON.parse(data);
	        	if(returnData.success == true){
	        		skuAttrDataDisplay(returnData.data);
	        	}	        	
	        }
		 });
    }
    /**
     * 向服务端请求删除销售属性
     */
    function deleteSkuAttr(skuId){
    	console.log("向服务端请求删除销售属性 skuId = " + skuId);
    	var jsonData = {"skuId":"","name":"","attr":""};
		jsonData.skuId = Number(skuId);
		jsonData.name = "sdfs";
		jsonData.attr = "dsf";
		console.log("向服务端请求删除销售属性");
		console.log("skuId =  " + jsonData.skuId);
		$.ajax({
	        type : "delete",
	        url : "/lanmei-cms/commodity/delete/sku/attr",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	console.log(data);        	
	        }
		 });
    }
    //测试
    $("#skuAttrDataDisplayBtn").click(function(){
    	skuAttrDataDisplay(SkuAttrData);
    })
    /**
     * 动态显示销售属性
     */
    function skuAttrDataDisplay(SkuAttrData){
    	
    	
    	var len = SkuAttrData.length;
    	console.log("SkuAttrData len = " + len);
    	console.log("SkuAttrData name  = " + SkuAttrData[0].name);
    	console.log("SkuAttrData name  = " + SkuAttrData[4].name);
    	var arrName;
    	
    	arrName = getName(SkuAttrData);
    	console.log("arrName len  = " + arrName.length);
    	for(var i = 0;i < arrName.length ;i++ ){
    		//console.log("arrName name = " + arrName[i]);
    	}
    	console.log("开始显示");
    	$("#sku-attr-display-div").empty();
    	for(var i = 0;i < arrName.length ;i++ ){    		
    		console.log("arrName name = " + arrName[i]);
    		var  text1 = "";
    	    text1 = '<ul class="list-group sku-attr-list">'
    			+ '<li class="list-group-item list-group-item-info">' + arrName[i] + '</li>';
    		var  text2="";
    		for(var j = 0;j < SkuAttrData.length ;j++ ){
    			if(arrName[i] == SkuAttrData[j].name){
    				console.log("j = " + j + "  name = " + SkuAttrData[j].name +  "  arrName = " + arrName[i]);
    				text2 =text2 + '<li class="list-group-item" skuId="' + SkuAttrData[j].skuId + '">'
    				+ SkuAttrData[j].attr 
    				+ '<button class="sku-attr-delete-btn-class">删除</button></li>';
    			}    			 
    		}
    		var text = text1 + text2 + '</ul>';
    		
    		console.log(text);
    		
    		$("#sku-attr-display-div").append(text);
    		
    	}
    	
    	
    }
    /**
     * 获取销售属性中name数组，已经去除重复值
     */
    function getName(attrdata){
    	
    	/*console.log("attrdata len  = " + attrdata.length);
    	console.log("attrdata name  = " + attrdata[0].name);
    	console.log("attrdata name  = " + attrdata[4].name);*/
    	
    	var json={};
    	for(var i = 0;i < attrdata.length ;i++ ){
    		json[attrdata[i].name] = attrdata[i].name;
    		//console.log("item.name = " + attrdata[i].name);
    	}
    	
    	var arr = new Array();
    	for(var item in json){
    		//console.log("item = " + item);
    		arr.push(item);
    	}
    	return arr;
    }
    
    
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
})
/**
 * 商品属性处理操作
 * @returns
 */
$(function(){
	
	var attrData = [{"attrId":"1","category":"包装清单","attName":"包装清单","attVal":"手机x1、快速指南x1"},
			{"attrId":"2","category":"主体","attName":"品牌","attVal":"华为(HUAWEI)"},
			{"attrId":"3","category":"系统","attName":"手机操作系统","attVal":"Android"},
			{"attrId":"4","category":"系统","attName":"系统版本","attVal":"华为EMUI 8.1"},
			{"attrId":"5","category":"网络","attName":"4G网络制式","attVal":"移动4G,联通4G,电信4G"},];
	
	 /**
     * 点击展开按钮处理
     */
    $("#attrInfoEditDispalyCtrlBtn").click(function(){
   	
    	if($("#attrInfoEditDispalyCtrlBtn").text() == "展开"){
    		$("#attrInfoEditDispalyCtrlBtn").text("收起");
    		$("#attrInfoEditDispalyCtrl").show();
    		console.log("attrInfoEditDispalyCtrlBtn 展开");
    		//向服务端请求数据
        	//var commodityId = 
        	//getＡttrInfo(commodityId);
    		//$("#attrInfoEditDispalyCtrl").show();
    		
    		displayAttr(attrData);
    	}
    	//　展开状态－－－＞收起状态
    	else if($("#attrInfoEditDispalyCtrlBtn").text() == "收起"){
    		$("#attrInfoEditDispalyCtrlBtn").text("展开");
    		$("#attrInfoEditDispalyCtrl").hide();
    		console.log("attrInfoEditDispalyCtrlBtn 关闭");
    	}
    })
   /*
    * 排序规则
    */
    function sortCategory(a,b){  
         return a.category.localeCompare(b.category,'zh');
    }
    /**
     * 显示属性列表．先排序，，再显示
     */
    function displayAttr(data){
    	//console.log("data.length = " + data.length);
    	$("#attrInfoTableTbody").empty();
    	
    //	console.log("排序前=" + JSON.stringify(data));
    	data.sort(sortCategory);
    	//console.log("排序后=" + JSON.stringify(data));
    	
    	for(var i =  0;i < data.length ;i++ ){
    		
    		var text = '<tr class="attrDisplayList"><td id="attrId" style="display:none">'+data[i].attrId +'</td>'
    				+ '<td id="category">' + data[i].category + '</td>'
    				+ '<td id="attName">' + data[i].attName + '</td>'
    				+ '<td id="attVal">' + data[i].attVal + '</td>'
    				+ '<td>' + '<button type="button" class="attrInfoEditBtn">编辑</button>'
    				+ '<button type="button" class="attrInfoDeleteBtn">删除</button>' + '</td>'
    				+ '</tr>';
    	//	console.log("text = " + text);
    		
    		$("#attrInfoTableTbody").append(text);
    		
    	}
    }
   
    /**
     * 绑定动态元素 编辑按钮
     */
     $(document).on('click','.attrInfoEditBtn',function(){
    	 //获取父及元素
    	var $attrDisplayList = $(this).parent().parent();
    	console.log("$attrDisplayList = " + $attrDisplayList);
    	 //获取值
    	var attrData = {"attrId":"","category":"","attName":"","attVal":""};
    	attrData.attrId = $attrDisplayList.find('#attrId').text();
    	attrData.category = $attrDisplayList.find('#category').text();
    	attrData.attName = $attrDisplayList.find('#attName').text();
   	 	attrData.attVal = $attrDisplayList.find('#attVal').text();
   	    console.log("attrData = " + JSON.stringify(attrData));
   	    
   	   //显示
   	    $("#attrInfoDisplayMode").hide();
		$("#attrInfoEditMode").show();
		$("#attrInfoBtn").text("返回");
		//填充
		$("#attrInfo-attrId").val(attrData.attrId);
		$("#attrInfo-category").val(attrData.category);
		$("#attrInfo-attName").val(attrData.attName);
		$("#attrInfo-attVal").val(attrData.attVal);
		
    	
     })
     /**
      * 提交按钮,提交更新请求
      */
     $("#attrInfoSubmitBtn").click(function(){
    	 
    	 var attrData = {"attrId":"","category":"","attName":"","attVal":""};
         attrData.attrId = $("#attrInfo-attrId").val();
         attrData.category = $("#attrInfo-category").val();
         attrData.attName = $("#attrInfo-attName").val();
         attrData.attVal = $("#attrInfo-attVal").val();
    	 	
    	 editAttribution(attrData);
    })
     /**
     * 绑定动态元素 删除按钮
     */
     $(document).on('click','.attrInfoDeleteBtn',function(){
    	 //获取父及元素
    	var $attrDisplayList = $(this).parent().parent();
    	console.log("$attrDisplayList = " + $attrDisplayList);
    	 //获取值
    	var attrData = {"attrId":"","category":"","attName":"","attVal":""};
    	attrData.attrId = $attrDisplayList.find('#attrId').text();
    	attrData.category = $attrDisplayList.find('#category').text();
    	attrData.attName = $attrDisplayList.find('#attName').text();
   	 	attrData.attVal = $attrDisplayList.find('#attVal').text();
   	    var result = confirm("是否确认删除" + "属性名称为[" + attrData.attName + "]的条目?" );
   	    console.log("result = " + result);
   	    //发送删除请求
   	    deleteAttribution(attrData.attrId);
     })
    
    /*－－－－－－－－－－－－－－－－－Ajax　请求－－－－－－－－－－－－－－－－－－*/
     //根据商品id获取商品属性
     //根据属性id修改属性
     //根据属性id删除属性
    /**
     * 通过商品id 向服务端请求商品属性
     */
    function getAttribution(commodityId){
    	var jsonData = {"commodityId":""};
		jsonData.commodityId = commodityId;
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/get/attribution",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	
	        }
		 });
    }
    /**
     * 通过属性id向服务端发送商品属性(编辑现有的属性)
     */
    function editAttribution(attrData){
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/edit/attribution",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(attrData),
	        dataType: "json",
	        success:function(data){
	        	
	        }
		 });
    }
    /**
     * 通过属性id删除商品属性(编辑现有的属性)
     */
    function deleteAttribution(attrId){
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/delete/attribution",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	
	        }
		 });
    }
    
    
	/**
	 * 商品属性编辑显示切换
	 */
	$("#attrInfoBtn").click(function(){
		if($("#attrInfoBtn").text() == "添加属性"){
			$("#attrInfoDisplayMode").hide();
			$("#attrInfoEditMode").show();
			$("#attrInfoBtn").text("返回");
		}
		else if($("#attrInfoBtn").text() == "返回"){
			$("#attrInfoDisplayMode").show();
			$("#attrInfoEditMode").hide();
			$("#attrInfoBtn").text("添加属性");
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
    /**
     * 向服务端获取商品详情内容
     */
    $("#descriptionSubmitBtn").click(function(){
    	var  descriptionInfo = editor.txt.html();
    	setDescription(descriptionInfo,currentEditCommodityData.commodityId);
    })
    /**
     * 向服务端读取商品详情内容
     */
    $("#descriptionInfoEditDispalyCtrlBtn").click(function(){
   	
    	if($("#descriptionInfoEditDispalyCtrlBtn").text() == "展开"){
    		$("#descriptionInfoEditDispalyCtrlBtn").text("收起");
    		//向服务端请求数据
    		var  descriptionInfo = editor.txt.html();
        	//var commodityId = 
        	getDescription(currentEditCommodityData.commodityId);
    		$("#descriptionInfoEditDispalyCtrl").show();
    	}
    	//　展开状态－－－＞收起状态
    	else if($("#descriptionInfoEditDispalyCtrlBtn").text() == "收起"){
    		$("#descriptionInfoEditDispalyCtrlBtn").text("展开");
    		$("#descriptionInfoEditDispalyCtrl").hide();
    	}
    })
    /**
     * 向服务端发送商品详情
     */
    function setDescription(descriptionInfo,commodityId){
    	var jsonData = {"descriptionInfo":"","commodityId":""};
		jsonData.descriptionInfo = descriptionInfo;
		jsonData.commodityId = Number(commodityId);
		$.ajax({
	        type : "post",
	        url : "/lanmei-cms/commodity/set/description",
	        contentType : "application/json;charset=utf-8",
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	
	        }
		 });
    }
    /**
     * 向服务端获取商品详情
     */
    function getDescription(commodityId){
    	var jsonData = {"commodityId":""};
		jsonData.commodityId = commodityId;
		var sendData = "commodityId="+commodityId;
		$.ajax({
	        type : "get",
	        url : "/lanmei-cms/commodity/get/description",
	        contentType : "application/json;charset=utf-8",
	        data : sendData,//JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	console.log("data.success = " + data.success);
	        	
	        	if(data.success == true){
	        		console.log("data.data.success = " + data.data.success);
	        		if(data.data.success == true){
	        			console.log("data.data.data = " + data.data.data);
	        			//清空显示
						$("#descriptionDisplayMode").empty();
						//显示编辑器的内容
						$("#descriptionDisplayMode").prepend(data.data.data);
	        		}	        		
	        	}	        	
	        }
		 });
    }
    /**
     * 切换显示和编辑状态处理
     */
    $("#descriptionInfoBtn").click(function(){
    	//显示状态－－－＞编辑状态
		if($("#descriptionInfoBtn").text() == "编辑"){
			$("#descriptionDisplayMode").hide();
			$("#descriptionEdiMode").show();
			$("#descriptionInfoBtn").text("返回");
			//获取显示页面的Ｈtml
			var html = $("#descriptionDisplayMode").html();
			//设置编辑框内容
			editor.txt.html(html);
		}
		//　编辑状态－－－＞显示状态
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
/**
 * 格式化Date
 */
Date.prototype.Format = function (fmt) { //author: meizz
var o = {
	"M+": this.getMonth() + 1, //月份
	"d+": this.getDate(), //日
	"h+": this.getHours(), //小时
	"m+": this.getMinutes(), //分
	"s+": this.getSeconds(), //秒
	"q+": Math.floor((this.getMonth() + 3) / 3), //季度
	"S": this.getMilliseconds() //毫秒
};
console.log("o.getHours = " + this.getHours() );
if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}