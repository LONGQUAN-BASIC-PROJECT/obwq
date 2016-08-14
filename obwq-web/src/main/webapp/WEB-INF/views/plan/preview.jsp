<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.desksoft.util.JspUtil"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
<%-- 	<script type="text/javascript" src="<%=JspUtil.basePath(request) %>/js/jquery-1.8.3.min.js"></script> --%>
<%-- 	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=33LXlsL6GbBLpxRF1sFGD6vL"></script> --%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=33LXlsL6GbBLpxRF1sFGD6vL&v=1.0"></script>
	<title>足迹-${plan.name}</title>
</head>
<body onload="loadData()">
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">

	var map = new BMap.Map("allmap");    // 创建Map实例
	var before = "http://obwq.oss-cn-hangzhou.aliyuncs.com/" ;
	

	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.clientHeight;
	
	var sContent =
		"<div style='width:{divWidth};height:{divHeight}'><div><h4 style='margin:0 0 5px 0;padding:0.2em 0'>{planName}</h4></div>" + 
		"<div><img   id='infoWindow' src='{planImage}' width='{width}px' height='{height}px' title='${plan.name}'/></div>" + 
// 		"<div><p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>{imageNote}</p></div>" +
		"<div><input type='button'  value='上一张' id='show_pre_img'><input type='text' size='8' value='共{countIndex}张' id='show_imge_count'><input type='button'  value='下一张' id='show_next_img'></div>" + 
		"</div>";
	
	
	var showIndex = 0 ;
	var markerArr = [] ;
	
	function loadData(){
		// 百度地图API功能
		map.centerAndZoom(new BMap.Point(${planDetail.yy}, ${planDetail.xx}), 21);  // 初始化地图,设置中心点坐标和地图级别
		map.addControl(new BMap.ZoomControl()); //添加地图缩放控件
	
	
		//百度地图API功能
// 		var sContent =
// 		"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>{planName}</h4>" + 
// 		"<img style='float:right;margin:4px' id='infoWindow' src='{planImage}' width='{width}px' height='{height}px' title='天安门'/>" + 
// 		"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>{imageNote}</p>" + 
// 		"</div>";
	

		last_point = null ;
		var g = mapData.datas ;
		if(!g){
			return 
		}
		
		
		
		for(var j = 0 ; j < g.length  ; j++){
			var data = g[j];
			var myIcon = new BMap.Icon(before + data.imageUrl.replace(".png","_mini.png"), new BMap.Size(30,30));
			var p = new BMap.Point(data.x,data.y) ;
			var m = new BMap.Marker(p,{icon:myIcon}); 
			m.d = data
			m.showIndex = j;
			m.addEventListener("click",clickOldFunction );
			markerArr[j] = m ;
			map.addOverlay(m)
		}
	
		initControl();
		
		oldFunction(markerArr[showIndex]) ;
	}
	
	function clickMarker(){
		alert("you click");
	}
	
	function clickOldFunction(){
		oldFunction(this)	
	}
	
	function oldFunction(marker){     
		 var  content = sContent.replace("{planName}",mapData.planName) ;
		 content = content.replace("{planImage}", before + marker.d.imageUrl) ;
		 content = content.replace("{imageNote}", marker.d.imageNote) ;
		  
		 showIndex = marker.showIndex ;
		 var image = new Image();
		 image.src = before + marker.d.imageUrl ;
		 image.xx = marker ;
		 image.onload = function(){
			  var imgeW = this.width ;
			  var imgeH = this.height ;
			  
			  
			  var fw = windowWidth * 0.5 ;
			  
			  var fh = fw * imgeH  / imgeW ;
		  
			  
//			  alert(fw + ":" + fh)
			  content = content.replace("{height}",fh);
			  content = content.replace("{width}",fw);
			  
			  content = content.replace("{divWidth}",fw + 50);
			  content = content.replace("{divHeight}",fh + 50);
			  content = content.replace("{countIndex}",(showIndex + 1)  + "/" + markerArr.length);
			  
			  var infoWindow = new BMap.InfoWindow(content);  // 创建信息窗口对象
			  this.xx.openInfoWindow(infoWindow);
			  this.xx.show()
			   //图片加载完毕重绘infowindow
			 
			   setTimeout(function(){
				   document.getElementById('infoWindow').onload = function (){
				       infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
				   }
				   
				   document.getElementById('show_next_img').onclick = function (){
				       showNextImage()   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
				   } 
				   document.getElementById('show_pre_img').onclick = function (){
				       showPreImage()   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
				   }
				   
				   //调整位置 
				   if(imgeW < imgeH){
					   var center = map.getCenter();
					   var bound = map.getBounds() ;
					   var lat = ( bound.getNorthEast().lat - center.lat) / 1.4 + center.lat ;
					   var lng = center.lng ;
					   var p = new BMap.Point(lng,lat) ;
					   map.setCenter(p);
				   }
				   
			   },1000)
			   
		}
		 
	}
	
	function showNextImage(){
		if(showIndex >= markerArr.length - 1){
			oldFunction(markerArr[0]) ;
		}else{
			oldFunction(markerArr[showIndex+1]) ;
		}
	}
	
	function showPreImage(){
		if(showIndex  <= 0){
			oldFunction(markerArr[markerArr.length - 1]) ;
		}else{
			oldFunction(markerArr[showIndex - 1]) ;
		}
	}
	
	/*********下截按钮 start ********/
	// 定义一个控件类,即function  
    function ZoomControl(){  
         // 默认停靠位置和偏移量  
        this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;  
        this.defaultOffset = new BMap.Size(8, 180);  
    }  

    // 通过JavaScript的prototype属性继承于BMap.Control  
    ZoomControl.prototype = new BMap.Control();  
	
 // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回  
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中  
    ZoomControl.prototype.initialize = function(map){  
        // 创建一个DOM元素  
        var div = document.createElement("div");  
        // 添加文字说明  
        //div.appendChild(document.createTextNode("放大2级"));  
         div.innerHTML="<img src=\"<%=JspUtil.basePath(request) %>/images/download.png\" />"  
        // 设置样式  
        div.style.cursor = "pointer";  
        div.style.border = "1px solid gray";  
        div.style.backgroundColor = "white"; 
        div.style.width = "36px";
        div.style.heigth = "36px";
        // 绑定事件  
        div.onclick = function(e){  
            //点击一次放大两级  
            map.setZoom(map.getZoom() + 2);  
            window.open("http://a.app.qq.com/o/simple.jsp?pkgname=com.tmall.PhotoMap") 
        }  
        // 添加DOM元素到地图中  
        map.getContainer().appendChild(div);  
        // 将DOM元素返回  
        return div;  
    } 
    /*********下截按钮 end ********/
    
    
    /*********alert按钮 start ********/
	// 定义一个控件类,即function  
    function AlertControl(){  
         // 默认停靠位置和偏移量  
        this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;  
        this.defaultOffset = new BMap.Size(8, 10);  
    }  

    // 通过JavaScript的prototype属性继承于BMap.Control  
    AlertControl.prototype = new BMap.Control();  
	
 	// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回  
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中  
    AlertControl.prototype.initialize = function(map){  
        // 创建一个DOM元素  
        var div = document.createElement("div");  
        // 添加文字说明  
        //div.appendChild(document.createTextNode("放大2级"));  
         div.innerHTML="<img src=\"<%=JspUtil.basePath(request) %>/images/alert.png\" />"  
        // 设置样式  
        div.style.cursor = "pointer";  
        div.style.border = "1px solid gray";  
        div.style.backgroundColor = "white"; 
        div.style.width = "200px";
        div.style.heigth = "36px";
        // 绑定事件  
        div.onclick = function(e){  
            //点击一次放大两级  
            map.setZoom(map.getZoom() + 2);  
            window.open("http://a.app.qq.com/o/simple.jsp?pkgname=com.tmall.PhotoMap") 
        }  
        // 添加DOM元素到地图中  
        map.getContainer().appendChild(div);  
        // 将DOM元素返回  
        return div;  
    } 
    /*********alert end ********/
    
    
    
 	function initControl(){
		// 创建控件  
	    var myZoomCtrl = new ZoomControl();  
	    // 添加到地图当中  
	    map.addControl(myZoomCtrl);  
	 // 创建控件  
	    var alertControl = new AlertControl();  
	    // 添加到地图当中  
	    map.addControl(alertControl);  
 	}
</script>

<script type="text/javascript">
var mapData = {
		datas:[
		<c:forEach var="detail" items="${planDetailList}">
			 {y:"${detail.xx}",x:"${detail.yy}",imageUrl:"${detail.pictureUrl}",imageNote:"${detail.content}"},
	    </c:forEach>

		 ],
		 planName:"${plan.name}"
}
</script>
