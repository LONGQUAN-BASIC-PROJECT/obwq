//在cookie中存放用户的user_email
$(document).ready(function(){
	$.getScript("js/plugs/jquery.cookie.js",function(data, textStatus, jqxhr){
		var email = $.cookie("user_email");
		if(email){  //登陆过
			$("#_user_login_btn").css("cursor","").html("LOGINED");
		}else {
			//加载js
			$.getScript("js/plugs/jquery.simplemodal.js",function(data, textStatus, jqxhr){
				$.getScript("js/plugs/osx.js",function(data, textStatus, jqxhr){});			
			});
			//加载css
			 var fileref=document.createElement("link"); 
			  	 fileref.setAttribute("rel", "stylesheet"); 
			     fileref.setAttribute("type", "text/css");  
			     fileref.setAttribute("href", "css/osx.css"); 
			 document.getElementsByTagName("head")[0].appendChild(fileref) ;
			 
			 $.post('../other/load_login.htm', function(data) {
				 $("#display_login_vm").html(data);
			 });
			 
		}
	})
})