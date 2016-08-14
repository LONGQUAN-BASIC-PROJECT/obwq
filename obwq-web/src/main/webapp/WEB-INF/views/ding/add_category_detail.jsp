<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.desksoft.util.JspUtil"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://file.obwq.cn/static/jquery.bsgrid-1.37/plugins/jquery-1.4.4.min.js"></script>
	<title>钉咚-添加子类别 </title>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$("#submit").click(function(){
			var name = $("#name").val();
			var doTypeKey = $("#doTypeKey").val();
			var doTypeValue = $("#doTypeValue").val();
			var xseqt = $("#xseqt").val();
			var buttonType = $("#buttonType").val();

			
			if(name.length < 1 || doTypeKey.length < 1  || doTypeValue.length < 1  || xseqt.length < 1 || buttonType.length < 1){
				alert("请填写数据,所有字段都不能为空");
				return ;
			}
			
			
			 $.post("saveCategoryDatail.htm",
					  {
				 		 pid : $("#pid").val(),
						 name : name ,
						 doTypeKey : doTypeKey ,
						 doTypeValue : doTypeValue ,
						 xseqt : xseqt ,
						 buttonType : buttonType
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
	
	<input id="pid" value="${id}" type="hidden" />
	
	<h1 style="margin-bottom: 20px;margin-left: 20px"> 添加子类别 </h1>
	
	<div style="margin-left: 20px">
	
		名称:<input type="text" size="20" id="name"> <br />  <br />
		
		doTypeKey:
		<select id="doTypeKey">
			<option  value="msgReply">发短信</option>
			<option  value="openUrl">打开网页</option>
			<option  value="openActive">打开本地页面</option>
		</select>
		<br /><br />
		
		doTypeValue:<input type="text" size="80" id="doTypeValue"> <br /><br />
		
		顺序:  
		<select id="xseqt">
			<option  value="1">--1--</option>
			<option  value="2">--2--</option>
			<option  value="3">--3--</option>
			<option  value="4">--4--</option>
			<option  value="5">--5--</option>
			<option  value="6">--6--</option>
		</select>
		
		
		<br /><br />
		
		位置: 
		<select id="buttonType">
			<option value="btn1"> btn1</option>
			<option  value="btn2"> btn2</option>
			<option  value="btn3"> btn3</option>
			<option  value="btn4"> btn4</option>
		</select>
		
		<br /><br />
		
		
		<input type="button" value="提交" id="submit"> 
		
		
	</div>
	
	
</body>
</html>