/**
 * 新增管理员页面
 * @returns
 */
$(function(){
	
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