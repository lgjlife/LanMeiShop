var currentPage;
/**
 * 向服务端获取当前秒杀活动列表
 * @param page 分页显示的页面 每页5条数据  page 为字符串类型
 * @returns
 */
function getSeckillingList(page){
	var jsonData = {"page":""};
	jsonData.page = page;
	console.log("传入的page = " + page);
	$.ajax({
        type : "post",
        url : "admin/get/adminlist",
        contentType : "application/json;charset=utf-8",
        //数据格式是json串,传进一个person
		/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
        data : JSON.stringify(jsonData),
        dataType: "json",
        success:function(data){
        	
        	
        	$(".page-item-add").remove();
        	var length = 0;
        	for(var e  in data){
        		length++;
        	}
        	console.log(" length = " +  length );
        	var loopCounts;
        	// 5 -- page = 1 -- 5/5 = 1  loopCounts = 2 
        	//6 -- loopCounts = 3 == 6/5=1 + 2 = 3
        	var allListCount = data[length-1].allListCount;
        	if(allListCount == 0){
        		return;
        	}
        	else if((allListCount%5 == 0)){
        		loopCounts = (allListCount / 5) + 1;
        	}
        	else{
        		loopCounts = Math.round(allListCount / 5) + 2;
        	}
        	//获取最大页码
        	maxPage = (loopCounts - 1).toString();
        	console.log("loopCounts = " + loopCounts );
        	for(var i = 1;i < loopCounts ;i++)
        	{
        		if(  i == currentPage){
        			var txt = " <li class='page-item page-item-add active'><a class='page-link'>"+ i + "</a></li>";
	        		$("#toNextPage").before(txt);
        		}
        		else{
        			var txt = " <li class='page-item page-item-add'><a class='page-link'>"+ i + "</a></li>";
	        		$("#toNextPage").before(txt);
        		}
        	}
        	/*console.log("this currentPage = " + currentPage);
        	if($(".page-item-add").find('a').text() == currentPage){
        		$(this).addClass("active");
        		console.log("this = " + $(this));
        	}*/
        	$(".trList").remove();
        	for(var i = 0;i < (length - 1) ;i++){
        		
        		
        		/*时间*/
        		var generateTime  = data[i].generateTime;
        		var time =  "20" + (generateTime.year - 100) + "/"
        					+  generateTime.month + "/" 
        					+ generateTime.date + "-" 
        					+ generateTime.hours + ":" + generateTime.minutes;
        		/*显示列表*/
        		var txt = "<tr class='trList'>"
        				+ "<td>" + data[i].actualName + "</td>"
        				+ "<td>" + data[i].loginJobnum + "</td>"
        				+ "<td>" + data[i].gender + "</td>"
        				+ "<td>" + data[i].department + "</td>"
        				+ "<td>" + data[i].state + "</td>"
        				+ "<td>" + time + "</td>"
        				+ "</tr>";
        		$("#tbodyList").append(txt);		
	  	 			
        	}
        	
        }
	 });
}
/**
 * 向服务端获取过期秒杀活动列表
 * @param page 分页显示的页面 每页5条数据  page 为字符串类型
 * @returns
 */
function getSeckilledList(page){
	var jsonData = {"page":""};
	jsonData.page = page;
	console.log("传入的page = " + page);
	$.ajax({
        type : "post",
        url : "admin/get/adminlist",
        contentType : "application/json;charset=utf-8",
        //数据格式是json串,传进一个person
		/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
        data : JSON.stringify(jsonData),
        dataType: "json",
        success:function(data){
        	
        	
        	$(".page-item-add").remove();
        	var length = 0;
        	for(var e  in data){
        		length++;
        	}
        	console.log(" length = " +  length );
        	var loopCounts;
        	// 5 -- page = 1 -- 5/5 = 1  loopCounts = 2 
        	//6 -- loopCounts = 3 == 6/5=1 + 2 = 3
        	var allListCount = data[length-1].allListCount;
        	if(allListCount == 0){
        		return;
        	}
        	else if((allListCount%5 == 0)){
        		loopCounts = (allListCount / 5) + 1;
        	}
        	else{
        		loopCounts = Math.round(allListCount / 5) + 2;
        	}
        	//获取最大页码
        	maxPage = (loopCounts - 1).toString();
        	console.log("loopCounts = " + loopCounts );
        	for(var i = 1;i < loopCounts ;i++)
        	{
        		if(  i == currentPage){
        			var txt = " <li class='page-item page-item-add active'><a class='page-link'>"+ i + "</a></li>";
	        		$("#toNextPage").before(txt);
        		}
        		else{
        			var txt = " <li class='page-item page-item-add'><a class='page-link'>"+ i + "</a></li>";
	        		$("#toNextPage").before(txt);
        		}
        	}
        	/*console.log("this currentPage = " + currentPage);
        	if($(".page-item-add").find('a').text() == currentPage){
        		$(this).addClass("active");
        		console.log("this = " + $(this));
        	}*/
        	$(".trList").remove();
        	for(var i = 0;i < (length - 1) ;i++){
        		
        		
        		/*时间*/
        		var generateTime  = data[i].generateTime;
        		var time =  "20" + (generateTime.year - 100) + "/"
        					+  generateTime.month + "/" 
        					+ generateTime.date + "-" 
        					+ generateTime.hours + ":" + generateTime.minutes;
        		/*显示列表*/
        		var txt = "<tr class='trList'>"
        				+ "<td>" + data[i].actualName + "</td>"
        				+ "<td>" + data[i].loginJobnum + "</td>"
        				+ "<td>" + data[i].gender + "</td>"
        				+ "<td>" + data[i].department + "</td>"
        				+ "<td>" + data[i].state + "</td>"
        				+ "<td>" + time + "</td>"
        				+ "</tr>";
        		$("#tbodyList").append(txt);		
	  	 			
        	}
        	
        }
	 });
}
/*
 * 当前秒杀活动
 * */
$(function(){
	
	/**
	 * 新增秒杀按钮
	 */
	$("#newSeckillBtn").click(function(){
		//秒杀名称，库存数量，秒杀时间，结束时间
		//组装发送的数据


		var jsonData = {"seckillName":"","stockCount":"","startTime":"","finishTime":""};
		
		var year =  $("#seckillStartYearInput").val();
		var month =  $("#seckillStartMonthInput").val();
		var date =  $("#seckillStartDateInput").val();
		var hours =  $("#seckillStartHourInput").val();
		var minutes =  $("#seckillStartMinuteInput").val();
		var startTime = new Date(year,month,date,hours,minutes,0,0);
		
		var year =  $("#seckillFinishYearInput").val();
		var month =  $("#seckillFinishMonthInput").val();
		var date =  $("#seckillFinishDateInput").val();
		var hours =  $("#seckillFinishHourInput").val();
		var minutes =  $("#seckillFinishMinuteInput").val();
		var finishTime = new Date(year,month,date,hours,minutes,0,0);
		
		jsonData.seckillName = $("#seckillNameInput").val();
		jsonData.stockCount = $("#stockCountInput").val();
		jsonData.startTime = startTime.getTime();
		jsonData.finishTime = finishTime.getTime();

		console.log("jsonData.startTime = " + jsonData.startTime);
		console.log("jsonData.finishTime = " + jsonData.finishTime);
		$.ajax({
	        type : "post",
	        url : "seckill/new/seckill",
	        contentType : "application/json;charset=utf-8",
	        //数据格式是json串,传进一个person
			/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
	        data : JSON.stringify(jsonData),
	        dataType: "json",
	        success:function(data){
	        	
	        	
	        	$(".page-item-add").remove();
	        	var length = 0;
	        	for(var e  in data){
	        		length++;
	        	}
	        	console.log(" length = " +  length );
	        	var loopCounts;
	        	// 5 -- page = 1 -- 5/5 = 1  loopCounts = 2 
	        	//6 -- loopCounts = 3 == 6/5=1 + 2 = 3
	        	var allListCount = data[length-1].allListCount;
	        	if(allListCount == 0){
	        		return;
	        	}
	        	else if((allListCount%5 == 0)){
	        		loopCounts = (allListCount / 5) + 1;
	        	}
	        	else{
	        		loopCounts = Math.round(allListCount / 5) + 2;
	        	}
	        	//获取最大页码
	        	maxPage = (loopCounts - 1).toString();
	        	console.log("loopCounts = " + loopCounts );
	        	for(var i = 1;i < loopCounts ;i++)
	        	{
	        		if(  i == currentPage){
	        			var txt = " <li class='page-item page-item-add active'><a class='page-link'>"+ i + "</a></li>";
		        		$("#toNextPage").before(txt);
	        		}
	        		else{
	        			var txt = " <li class='page-item page-item-add'><a class='page-link'>"+ i + "</a></li>";
		        		$("#toNextPage").before(txt);
	        		}
	        	}
	        	/*console.log("this currentPage = " + currentPage);
	        	if($(".page-item-add").find('a').text() == currentPage){
	        		$(this).addClass("active");
	        		console.log("this = " + $(this));
	        	}*/
	        	$(".trList").remove();
	        	for(var i = 0;i < (length - 1) ;i++){
	        		
	        		
	        		/*时间*/
	        		var generateTime  = data[i].generateTime;
	        		var time =  "20" + (generateTime.year - 100) + "/"
	        					+  generateTime.month + "/" 
	        					+ generateTime.date + "-" 
	        					+ generateTime.hours + ":" + generateTime.minutes;
	        		/*显示列表*/
	        		var txt = "<tr class='trList'>"
	        				+ "<td>" + data[i].actualName + "</td>"
	        				+ "<td>" + data[i].loginJobnum + "</td>"
	        				+ "<td>" + data[i].gender + "</td>"
	        				+ "<td>" + data[i].department + "</td>"
	        				+ "<td>" + data[i].state + "</td>"
	        				+ "<td>" + time + "</td>"
	        				+ "</tr>";
	        		$("#tbodyList").append(txt);		
		  	 			
	        	}
	        	
	        }
		 });/*$.ajax({*/	
	});/*$("#").click(function(){*/

});/*$(function(){*/

/*$(function () {  
    $('#date1').datetimepicker({  
        format: 'YYYY-MM-DD',  
        locale: moment.locale('zh-cn')  
    });  
});  */
/*项目列表点击时进行显示页面*/
$(function(){
	/*页面刷新时全部隐藏*/
	$(".seckillPageDiplay").hide();
	/*当前秒杀活动按钮*/
	$("#seckillingtem").click(function(){
		$(".seckillPageDiplay").hide();
		$("#seckillingItemPage").show();
		//获取秒杀活动列表
		//获取第一页
		//getSeckillingList("1");
	});
	/*过时秒杀活动按钮*/
	$("#seckilledItem").click(function(){
		$(".seckillPageDiplay").hide();
		$("#seckilledItemPage").show();
		//向服务端获取过期秒杀活动列表
		//获取第一页
		//getSeckilledList("1");
	});
});