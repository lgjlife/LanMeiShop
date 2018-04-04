var indexImgAutoDispay = 0;
var count = 0;
var ImgPath=["static/img/recommend/recommend-1.jpg",
			 "static/img/recommend/recommend-2.jpg",
			 "static/img/recommend/recommend-3.jpg",
			 "static/img/recommend/recommend-4.jpg"];

$(function(){
	$("#list-group-img").children(":first").show();
	setInterval(function(){showPic()},6000);
});

function showPic(){
	
	$("#img-huodong").attr("href",ImgPath[indexImgAutoDispay]);
	indexImgAutoDispay++;
	
	if(indexImgAutoDispay >= 4) indexImgAutoDispay =0;
/*	console.log("indexImgAutoDispay = " + indexImgAutoDispay);
	console.log($("#img-huodong").attr("href"));*/

}