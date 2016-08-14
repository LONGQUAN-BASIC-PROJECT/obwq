var mk = {} ;
var ma = {} ;
function addEventBind(m,o) {
	var $=KISSY.Node.all;
	mk = o ;
	ma = m ;
	$(".up_load_pic").detach();
	$(".remove_overy").detach();
	
	$(".up_load_pic").on("click",function(){
		alert("上传图片");
	})
	$(".remove_overy").on("click",function(){
		ma.removeOverlay(mk);
		mk = null ;
	})
}



function addPointDB(){
	
}