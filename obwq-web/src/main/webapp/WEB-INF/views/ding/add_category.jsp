<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.desksoft.util.JspUtil"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://file.obwq.cn/static/jquery.bsgrid-1.37/plugins/jquery-1.4.4.min.js"></script>
	<title>钉咚-添加类别 </title>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#submit").click(function(){
			var telphone = $("#telphone").val();
			var desc = $("#desc").val();
			
			if(telphone.length < 1 || desc.length < 1 ){
				alert("请填写数据");
				return ;
			}
			
			
			 $.post("saveCategory.htm",
					  {
				  		telphone:telphone,
				  		desc : desc
					  },
					  function(result){
// 			    		 alert(result.success)
			    		 if(result.success){
			    			 alert("添加成功");
			    		 }else if(result.desc){
			    			 alert("添加失败,原因:" + result.desc);
			    		 }else {
			    			 alert("添加失败");
			    		 }

			  		  }
			  );
			
		})
		
	})
	
	</script>
</head>
<body>
	
	<h1 style="margin-bottom: 20px;margin-left: 20px"> 添加类别 </h1>
	
	<div style="margin-left: 20px">
	
		号码:<input type="text" size="20" id="telphone"> <br />  <br />
		
		描述:<input type="text" size="80" id="desc"> <br /><br />
		
		<input type="button" value="提交" id="submit"> 
		
		
	</div>
	
	
</body>
</html>