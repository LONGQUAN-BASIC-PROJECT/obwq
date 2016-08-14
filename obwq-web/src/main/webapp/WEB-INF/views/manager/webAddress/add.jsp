<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = JspUtil.basePath(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加网址</title>
<script type="text/javascript" src="<%=basePath%>/js/webAddress.js"></script>
<style type="text/css">
	#list_web_address li {
		width: 160px ;float: left;
		list-style: none;
	}
	.selectBackgroup{background-color:red;}
</style>
</head>
<body>
	名称:<input type="text" id="webAddressName" size="30"><br />
	网址:<input type="text" id="webAddressUri" size="50"> <br /><br />
	
	 <input type="button" id="addWebAddress" value="增加内容">
	 <hr />
	 <div  style="width: 1200px;height: 300px;" id="list_web_address">
	 	
	 </div>
	  <input type="button" id="saveWebAddressSequ" value="保存顺序">
</body>
</html>