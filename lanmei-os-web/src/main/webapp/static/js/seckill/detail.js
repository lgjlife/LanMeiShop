var currentTime = 0;
var startTime = 0;
var endTime = 0;
var seckillId;
var md5;

var seckill = {
		
	/*向服务器获取时间*/
	detail:{
		init:function(params){
			 seckillId = params['seckillId'];
             startTime = params['startTime'];
             endTime = params['endTime'];
             console.log("seckillId = " + seckillId
            			+ " startTime " + startTime
            			+ "  endTime" + endTime);
            $.ajax({
    	        type : "get",
    	        url : "/lanmei-os/seckill/get/time",
    	        data:"",
    	        contentType : "application/json;charset=utf-8",		      
    	        dataType: "json",
    	        success:function(data){
    	        	currentTime = data.currentTime;
    	        	seckill.countdownTime();//倒计时
    	        	console.log("current time =  " + data.currentTime);
    	        }
    		 })	/*end of $.ajax */					  						
    		
		}
	},/*end of detail  */
     /*倒计时*/
	 countdownTime:function(){
		 
		 if(currentTime >= endTime){
			 //秒杀结束
			 $("#seckillWarn").show();
			 $("#seckillBtn").hide();
			 $("#seckillWarn").text("秒杀已经结束")
			 console.log("秒杀已经结束");
		 }
		 else if((currentTime >= startTime)&&(currentTime < endTime)){
			 //正在进行秒杀
			 /*$("#seckillWarn").hide();
			 $("#seckillBtn").show();*/
			 
			 console.log("秒杀正在进行");
			 seckill.getMd5();	
			 
		 }
		 else{
			 //秒杀还未开始，进行倒计时
			 $("#seckillWarn").show();
			 $("#seckillBtn").hide();
			 //根据时间差获取倒计时 天 时 分 秒 
	         var timeDiff =  Number(startTime - currentTime);//ms
			 var days = Math.round(timeDiff/(24*60*60*1000));
			 var hours = Math.round(((days*24*60*60*1000) - timeDiff )) / (60*60*1000);
			 var minutes = Math.round((timeDiff % (60*60*1000))) / (60*1000);
			 var secons = Math.round((timeDiff % (60*1000))) / (1000);
			 
			 
			 $("#seckillWarn").text("秒杀倒计时：  " + Math.round(days) + "天   " 
					 				+ Math.round(hours) + "时   " 
					 				+ Math.round(minutes) + "分   " 
					 				+ Math.round(secons) + "秒   ");
			 
			
			 currentTime = Number(currentTime) + 1000;

			 setTimeout("seckill.countdownTime()",1000);
		 }
		 
	},
	/**
	 * 获取MD5
	 */
	getMd5:function(){
		 $.ajax({
 	        type : "get",
 	        url : "/lanmei-os/seckill/" + seckillId +  "/exposer",
 	        data:"",
 	        contentType : "application/json;charset=utf-8",		      
 	        dataType: "json",
 	        success:function(data){
 	        	md5  = data.exposer.md5;
 	        	seckillId = data.exposer.seckillId;
 	        	startTime = data.exposer.start;
 	        	endTime = data.exposer.end;
 	        	currentTime = data.exposer.now;
 	        	var exposed = data.exposer.exposed;
 	        	console.log("seckillId = " + seckillId +
 	        			"  md5 =  " + md5 + 
 	        			"  exposed = " + exposed
 	        			+ "  currentTime  = " + currentTime
 	        			+ "  startTime  = " + startTime
 	        			+ "  endTime = " + endTime);
 	        	if(exposed == true){
 	        		/*显示按钮*/
 	        		$("#seckillWarn").hide();
 	        		$("#seckillBtn").show();
 	        		
 	        	}
 	        	if(exposed == false){
 	        		seckill.countdownTime();
 	        	}
 	        }
 	 })	/*end of $.ajax */
	},
	/**
	 * 执行秒杀 
	 */
	execution:function(){
		 $.ajax({
	 	        type : "post",
	 	        url : "/lanmei-os/seckill/" + seckillId + "/" + md5 + "/execute",
	 	        data:"",
	 	        contentType : "application/json;charset=utf-8",		      
	 	        dataType: "json",
	 	        success:function(data){
	 	        	 seckill.executionHandle(data);  
	 	        }
	 	 })	/*end of $.ajax */
		},
	
	executionHandle:function(data){
		console.log("执行秒杀成功 data.data = " + data.data );
     	console.log("执行秒杀结果 = " 
     			+ "  success = " + data.success
     			+ "  error = " + data.error
     			+ "  seeckilklId = " + data.data.seckillId
     			+ "  state = " + data.data.state);
     	if(data.success == false){
     		//用户未登录，跳转带登录界面
     		 location.href=("/lanmei-os/user-login");
     	}
     	if(data.data.state == "SECKILL_SUCCESS"){
     		//执行秒杀成功
     		 $("#seckillWarn").show();
			 $("#seckillBtn").hide();
			 $("#seckillWarn").text("恭喜您，秒杀成功！")
     	}
     	else if(data.data.state == "SECKILL_CLOSE"){
     		//秒杀关闭
     		 $("#seckillWarn").show();
			 $("#seckillBtn").hide();
			 $("#seckillWarn").text("抱歉，秒杀已经结束！")
     	}
     	else if(data.data.state == "SECKILL_REPEAT"){
     		//重复秒杀
     		$("#seckillWarn").show();
			$("#seckillBtn").hide();
			$("#seckillWarn").text("抱歉，您已经秒杀该商品！")
     	}
     	else if(data.data.state == "SECKILL_REQUEST_ERROOR"){
     		//秒杀异常，地址错误
     		$("#seckillWarn").show();
			$("#seckillBtn").hide();
			$("#seckillWarn").text("抱歉，秒杀异常，请重新执行！")
     	}
     	else if(data.data.state == "SECKILL_INNER_ERROOR"){
    		//秒杀执行异常，程序碰带未知错误
     		$("#seckillWarn").show();
			$("#seckillBtn").hide();
			$("#seckillWarn").text("抱歉，秒杀遇到未知错误，请重新执行！")
     	}
	},
	
}

$(function(){
	/**
	 * 用于测试
	 */
	$("#getMd5Btn").click(function(){
		seckill.getMd5();
	})
	/**
	 * 执行秒杀按钮按下
	 */
	$("#seckillBtn").click(function(){
		console.log("开始执行秒杀");
		seckill.execution();
	})
	
	
})