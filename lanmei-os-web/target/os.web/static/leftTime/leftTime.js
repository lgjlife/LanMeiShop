(function($,app){
	Date.prototype.format = function(format) {
		var o = {
			"M+": this.getMonth() + 1, //month
			"d+": this.getDate(), //day
			"h+": this.getHours(), //hour
			"m+": this.getMinutes(), //minute
			"s+": this.getSeconds(), //second
			"q+": Math.floor((this.getMonth() + 3) / 3), //quarter
			"S": this.getMilliseconds() //millisecond
		}
		if(/(y+)/.test(format)) format = format.replace(RegExp.$1,
			(this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for(var k in o)
			if(new RegExp("(" + k + ")").test(format))
				format = format.replace(RegExp.$1,
					RegExp.$1.length == 1 ? o[k] :
					("00" + o[k]).substr(("" + o[k]).length));
		return format;
	};
	app.leftTime=function(setdate,callback,isCheckTime) {
		var timer=this.timer;
		if(!isCheckTime && isCheckTime != false) {
			isCheckTime = true;
		};
		var opts={};
		    opts.count=0;
		//定义变量 d,h,m,s保存倒计时的时间  
		var d = 0,
			h = 0,
			m = 0,
			s = 0,
			setCount=0,
			setCount2=0,
			stepCheck=0,
			status = false;
		if(typeof(setdate) === "object"){
			if(!setdate.init && setdate.init != true) {
				setdate.init = false;
			};
			var countTime=0,
			newDateTime,
			setDayNo=0;
			if(setdate.setday!=0 || setdate.setday!="0"){
				setDayNo=parseInt(setdate.setday)*86400000;
			}
			if(!setdate.nowdate || setdate.nowdate==null || setdate.nowdate==undefined || setdate.nowdate=="undefined"){
				setdate.nowdate=new Date().getTime();
				newDateTime=new Date();
			}else{
				countTime=(new Date().getTime())-parseInt(setdate.nowdate);
				newDateTime=new Date(setdate.nowdate);
			}
			var newSeverDateTime=new Date(parseInt(setdate.nowdate)+setDayNo);
			var severStart=0,severEnd=0;
			if((setdate.startdate!=0 && setdate.startdate!="0") && !setdate.init){
				if(typeof(setdate.startdate)==="string"){
					if(checkDateTime(setdate.startdate)){
						severStart=new Date((setdate.startdate).replace(/-/g,"/")).getTime();
					}else{
						if(checkIsTime(setdate.startdate)){
						severStart=new Date(newSeverDateTime.format("yyyy/MM/dd")+" "+setdate.startdate).getTime();
						}
					};
				}else if(typeof(setdate.startdate)==="number"){
					severStart=setdate.startdate;
				}
			}
			if(setdate.enddate!=0 || setdate.enddate!="0"){
				if(typeof(setdate.enddate)==="string"){
					if(checkDateTime(setdate.enddate)){
						severEnd=new Date((setdate.enddate).replace(/-/g,"/")).getTime();
					}else{
						if(checkIsTime(setdate.enddate)){
						 severEnd=new Date(newSeverDateTime.format("yyyy/MM/dd")+" "+setdate.enddate).getTime();
						}
					};
				}else if(typeof(setdate.enddate)==="number"){
					severStart=setdate.enddate;
				}
			};
			var currDateTime=newDateTime.getTime();
		};
		function checkDateTime(str){
			if(str.indexOf("-")!=-1 || str.indexOf("/")!=-1){return true;}else{return false;}
		}
		function checkIsTime(str){
			var reg = /^(20|21|22|23|[0-1]\d):[0-5]\d$/;
			if(reg.test($.trim(str))){return true;}else{return false;}
		}
		function checkTime(i) {
			if(i < 10) {
				if(isCheckTime) {
					i = "0" + i;
				}
			}
			return i;
		}
		function leftTimeExeFn(){
			if(typeof(setdate) === "string" || typeof(setdate) === "number") {
				//获取当前时间  
				var startDate = new Date();
				var nowTime = startDate.getTime();
				//设置截止时间  
				var endTime=0;
				var leftTimeCount =0;
				if(typeof(setdate) === "string" || setdate.toString().length>=12){
					setdate=typeof(setdate) === "string"&&checkDateTime(setdate)==true?setdate.replace(/-/g,"/"):setdate;
					var endDate = new Date(setdate);
					endTime = endDate.getTime();
					leftTimeCount = endTime - nowTime;
				}else{
					endTime=setdate-opts.count;
					leftTimeCount=endTime*1000;
					opts.count++;
				}
				if(leftTimeCount>0) {
					d = Math.floor(leftTimeCount / 1000 / 60 / 60 / 24);
					h = Math.floor(leftTimeCount / 1000 / 60 / 60 % 24);
					m = Math.floor(leftTimeCount / 1000 / 60 % 60);
					s = Math.floor(leftTimeCount / 1000 % 60);
					status = true;
				} else {
					window.clearInterval(timer);
					d = 0;
					h = 0;
					m = 0;
					s = 0;
					status = false;
				}
			}else if(typeof(setdate) === "object"){
				var nowTime=new Date().getTime()+(countTime>0?countTime*-1:Math.abs(countTime));
				var endTime=0;
				if(currDateTime<severStart){
					endTime=severStart;
					stepCheck=1;
				}else if(currDateTime>=severStart && currDateTime<severEnd){
					endTime=severEnd;
					stepCheck=2;
				}else if(currDateTime>=severEnd){
					stepCheck=3;
				}
				//时间差  
				var countEnd=endTime-nowTime;
				if(countEnd>0) {
					d = Math.floor(countEnd / 1000 / 60 / 60 / 24);
					h = Math.floor(countEnd / 1000 / 60 / 60 % 24);
					m = Math.floor(countEnd / 1000 / 60 % 60);
					s = Math.floor(countEnd / 1000 % 60);
					status = true;
				} else {
					window.clearInterval(timer);
					d = 0;
					h = 0;
					m = 0;
					s = 0;
					status = false;
				}
			}
			var dataTime = {
				"d": checkTime(d),
				"h": checkTime(h),
				"m": checkTime(m),
				"s": checkTime(s)
			};
			dataTime.status=status;
			dataTime.step=stepCheck;
			if(callback && typeof(callback) === "function") {
				return callback(dataTime);
			}
		}
		leftTimeExeFn();
		timer=setInterval(leftTimeExeFn,1000);
		if(timer!="undefined" || timer!=null || timer!=undefined){
			return timer;
		}
	};
	$.extend(app);
})(jQuery||zepto,{});