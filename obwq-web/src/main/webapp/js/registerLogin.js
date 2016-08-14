function getRandomKey(eId){
	var contextPath = $("#page_context_path").val();
	$("#"+eId).attr("src",contextPath+"/html/code_two.jsp?t="+new Date());
}

KISSY.use("extends/ajax/ajax,node,dom",function(S,Ajax,node,dom){
	var $ = S.all;
	var contextPath = $("#page_context_path").val();

	$("#email").on("blur",function(){
		var elem = $(this) ;
		var str = S.trim(elem.val());
		if(str.length == 0 ){
			$("#emailinfo").show().html("邮箱不能为空");
			elem.attr("error",1);
		}else{
			$("#emailinfo").hide();
			elem.attr("error",0);
			new Ajax(contextPath + "/page/user/validation.jspx",{email:str},function(data){
				if(data.status == 0){
					$("#emailinfo").show().html("此邮箱已被注册。！");
					elem.attr("error",1);
				}
			})
		}
	})
	
	$("#username").on("blur",function(){
		var elem = $(this) ;
		var str = S.trim(elem.val());
		if(str.length == 0 ){
			$("#usernameinfo").show().html("用户名不能为空");
			elem.attr("error",1);
		}else{
			$("#usernameinfo").hide();
			$("#right_now_register").show();
			elem.attr("error",0);
			new Ajax(contextPath + "/page/user/validation.jspx",{userName:str},function(data){
				if(data.status == 0){
					$("#usernameinfo").show().html("此用户名已被注册。！");
					elem.attr("error",1);
				}
			})
		}
	})
	
	
	$("#enterpassword").on("blur",function(){
		var elem = $(this);
		var password = S.trim($("#password").val());
		var enterpassword = S.trim($("#enterpassword").val());
		if(!(enterpassword.length != 0 &&  password == enterpassword)){
			$("#passwordinfo").show().html("两次密码不匹配!");
			elem.attr("error",1);
		}else{
			$("#passwordinfo").show().html('');
			elem.attr("error",0);
		}
		    
	})
	
	
	/**
	 * 注册
	 */
	$("#right_now_register").on("click",function(){
		var elem = $(this);
		elem.css("display","none");
		var email = $("#email").val();
		var userName = $("#username").val();
		var password = $("#password").val();
		var enterpassword = $("#enterpassword").val();
		var inputRegisterRandomKey = $("#inputRegisterRandomKey").val();
		var subbox = $("#subbox");
		
		var param = {
				email : email ,
				userName : userName,
				passWord : password,
				inputRegisterRandomKey : inputRegisterRandomKey
		}
		
		new Ajax(contextPath + "/page/user/register.jspx",param,function(data){
			if(data.status == 1){
				alert("注册成功,请登陆。！");
				//window.location.reload();
			}else{
				alert("注册失败");
				elem.css("display","block");
			}
		})
	})
	
	/**
	 * 登陆
	 */
	$("#right_now_login").on("click",function(){
		var loginname = $("#loginname").val();
		var loginpwd = $("#loginpwd").val();
		var inputLoginRandomKey  = $("#inputLoginRandomKey").val();
		var param = {
				email : loginname ,
				userName : loginname,
				passWord : loginpwd,
				inputLoginRandomKey : inputLoginRandomKey
		}
		
		new Ajax(contextPath + "/page/user/login.jspx",param,function(data){
			if(data.status == 1){
				alert("登陆成功成功,回到首页");
				window.location.href=contextPath + "/page/home/index.jspx";
			}else{
				alert("登陆失败,请重试。！");
			}
		})
	})
})