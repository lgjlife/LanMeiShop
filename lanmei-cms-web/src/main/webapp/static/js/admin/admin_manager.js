
/**
 * 新增管理员页面
 * @returns
 */
$(function(){
	
	var currentPage = "0";   //当前的页码数，页码定义为string,后端才可以解析
	var maxPage; //最大页数
	/**
	 * 获取随机数按钮按下
	 * @returns
	 */
	$("#invitationCodeBtn").click(function(){
		var random = getRandom(100000,999999).toString();
		$("#invitationCode").val(random);
		console.log("生成邀请码");
	});
	
	/**
	 * 获取随机数
	 * @param start
	 * @param end
	 * @returns
	 */
	function getRandom(start,end){
		var random = Math.random()*end + start;
		
		return Math.round(random);
	}
	
	$("#addAdminBtn").click(function(){
		
		var actualName = $("#actualName").val();
		var jobNum = $("#jobNum").val();		
		var invitationCode = $("#invitationCode").val();
		var adminPassword = $("#adminPassword").val();
		var email = $("#email").val();
		
		console.log("actualName = " + actualName + "jobNum = " + jobNum + 
				  "  invitationCode = " + invitationCode 
					+ "  adminPassword = " + adminPassword  + "  email = " + email );
		
		if(checkAllInput() == true){
			/*输入无误*/
			var jsonData={"actualName":"","loginJobnum":"","invitationCode":"","adminPassword":"","email":""};
			jsonData.actualName = actualName;
			jsonData.loginJobnum = jobNum;
			jsonData.invitationCode = invitationCode;
			jsonData.adminPassword = adminPassword;
			jsonData.email = email;
			
			$.ajax({
		        type : "post",
		        url : "admin/newadmin",
		        contentType : "application/json;charset=utf-8",
		        //数据格式是json串,传进一个person
				/*data :'{"phoneNum" : ${phoneNum},"loginPassword": "password","phoneNumValidate":"phoneNumValidate"}',*/
		        data : JSON.stringify(jsonData),
		        dataType: "json",
		        success:function(data){

		            if(data.adminCreateStatus == "ADMIN_CREATE_SUCCESS"){
		            	console.log("新增管理员成功");
		            	$("#addAdminWarn").text("管理员创建成功");
		            }
		            else if(data.adminCreateStatus == "ADMIN_CREATE_FAIL"){
		            	console.log("新增管理员失败");
		            	$("#addAdminWarn").text("管理员创建失败");
		            }
		            
		        }
			 });
			
			
		}
		
		
		
	});
	
	function checkAllInput(){
		if( ($("#actualName").val().length == 0)
			||($("#invitationCode").val().length == 0)
			||($("#adminPassword").val().length == 0)
			||(isEmail($("#email").val()) == false))
			{
				console.log("输入不正确");
				$("#addAdminWarn").text("请检查输入是否正确！");
				return false;
			}
		else{
			$("#addAdminWarn").text("");
			console.log("输入正确");
			return true;
		}
	}
	/**
	 * 获取管理员列表按钮
	 */
	$("#getAdminListBtn").click(function(){
		console.log("getAdminListBtn  按下");
		currentPage = "1";
		getAdminList(currentPage);
	});
	
    /*事件绑定动态生成的元素*/
	$(document).on('click','.page-item-add',function(){
		console.log("按下" + $(this).find('a').text());
		currentPage = $(this).find('a').text();
		getAdminList(currentPage);
		
		/*$(".page-item-add").removeClass("active");*/
		$(this).addClass("active");
		console.log("this  = " + $(this));
		
	})
	$("#toPreviousPage").click(function(){
		if(currentPage > "1"){
			currentPage--;
			getAdminList(currentPage.toString());
		}
	});
	$("#toNextPage").click(function(){
		if(currentPage < maxPage){
			currentPage++;
			getAdminList(currentPage.toString());
		}
	});
	/**
	 * 获取总页码数和管理员列表
	 * page : 第几页
	 */
	function getAdminList(page){
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
	
});



/*项目列表点击时进行显示页面*/
$(function(){
	/*页面刷新时全部隐藏*/
	$(".adminManagerPageDiplay").hide();
	/*管理员列表*/
	$("#adminListItem").click(function(){
		$(".adminManagerPageDiplay").hide();
		$("#adminListItemPage").show();
	});
	/*新增管理员*/
	$("#addAdminItem").click(function(){
		$(".adminManagerPageDiplay").hide();
		$("#addAdminItemPage").show();
	});
	/*角色管理*/
	$("#roleItem").click(function(){
		$(".adminManagerPageDiplay").hide();
		$("#roleItemPage").show();
	});
	/*权限管理*/
	$("#authorityItem").click(function(){
		$(".adminManagerPageDiplay").hide();
		$("#authorityItemPage").show();
	});	
});