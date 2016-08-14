<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=33LXlsL6GbBLpxRF1sFGD6vL"></script>
	<script type="text/javascript" src="js/data.js"></script>
	<script type="text/javascript" src="js/plugs/layer/layer.min.js"></script>
	<title>地图展示</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	
	//设置本地城市
	function myFun(result){
	    var cityName = result.name;
	    map.setCenter(cityName);
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
	

	$(document).ready(function(){

		var last_point = null ;
		//百度地图API功能
		var sContent =
		"<h4 style='margin:0 0 5px 0;padding:0.2em 0'>天安门</h4>" + 
		"<img style='float:right;margin:4px' id='imgDemo' src='http://app.baidu.com/map/images/tiananmen.jpg' width='139' height='104' title='天安门'/>" + 
		"<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>天安门坐落在中国北京市中心,故宫的南侧,与天安门广场隔长安街相望,是清朝皇城的大门...</p>" + 
		"</div>";



		for(var i = 0 ; i < 20 ; i++){
			last_point = null ;
			var g = mapData["group_" + i] ;
			if(!g){
				break ;
			}
			
			for(var j = 0 ; j < g.length  ; j++){
				var data = g[j];
				var myIcon = new BMap.Icon("http://obwq.oss-cn-hangzhou.aliyuncs.com/00A21F42F22CFE1A7AFD6B1DE904376E/00A21F42F22CFE1A7AFD6B1DE904376E/2015032723223200A21_mini.png", new BMap.Size(30,30));
				var p = new BMap.Point(data.x,data.y) ;
				var m = new BMap.Marker(p,{icon:myIcon}); 
				m.d = data
				m.addEventListener("click", function(){          
//		 			   this.openInfoWindow(infoWindow);
//		 			   //图片加载完毕重绘infowindow
//		 			   document.getElementById('imgDemo').onload = function (){
//		 			       infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
//		 			   }
// 					alert("x:" + this.d.x + ",y:" + this.d.y)
					
					$.layer({
					    type : 2,
					    title : '查看图片列表',
					    iframe : {src : 'wan/photo_list.htm'},
//		 			    iframe : {src : 'http://www.baidu.com'},
					    area : ['1100px' , '600px'],
					    offset : ['10px',''],
					    border : [0]
					});
				});
				map.addOverlay(m)
				
				if(last_point != null) {
					var pl = new BMap.Polyline([last_point,p ],{strokeColor:"red", strokeWeight:6, strokeOpacity:1});
					map.addOverlay(pl);
				}
				last_point = p ;
			}
			
		}

			
			
			
			
			var p4 = new BMap.Point(120.133351,30.296917);
			var m4 = new BMap.Marker(p4);
			
			var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
			map.addOverlay(m4);
			m4.addEventListener("click", function(){          
			   this.openInfoWindow(infoWindow);
			   //图片加载完毕重绘infowindow
			   document.getElementById('imgDemo').onload = function (){
			       infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
			   }
			});
		
	});
		
	
	
</script>
