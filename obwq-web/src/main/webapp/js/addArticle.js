KISSY.use("ds/mallutil/ajax,node,dom,ds/overlay/1.0/index,sizzle",function(S,Ajax,node,dom,overlay,sizzle){
	var $ = S.all;
	
	S.ready(function(){
		var sContent =  "<input type='button' style='cursor:wait;'  class='up_load_pic' value='上传图片'/>&nbsp;&nbsp;<input type='button' style='cursor:wait;' class='remove_overy' value='移除锚点'/>";
		var map = new BMap.Map("allmap");            // 创建Map实例
		
		map.centerAndZoom(new BMap.Point(120.170506,30.275153),13);  //中心地点，需要根据用户IP可指定
		map.enableScrollWheelZoom(true); //可以缩放
		
		
		//------------------------------------
		//添加右击事件。
		var menu = new BMap.ContextMenu();
		var txtMenuItem = [{
		   text:'添加锚点',
		   callback:function(e){
			    //添加右击事件
				var point = new BMap.Point(e.lng, e.lat);
				var marker = new BMap.Marker(point);
				
				marker.addEventListener("click", function(){          
				   this.openInfoWindow(new BMap.InfoWindow(sContent));
				   //addEventBind(map,marker);
				   setTimeout(function(){
					    $(".up_load_pic").detach();
						$(".remove_overy").detach();
						
						$(".up_load_pic").on("click",function(){
							alert("上传图片");
						})
						$(".remove_overy").on("click",function(){
							map.removeOverlay(marker);
						})
						$(".up_load_pic").css("cursor","pointer");
						$(".remove_overy").css("cursor","pointer");
				   },1000)
				});
				map.addOverlay(marker);
		   }
		 }
		];
		
		for(var i=0; i < txtMenuItem.length; i++){
		    menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
		}
		map.addContextMenu(menu);
	})
})
