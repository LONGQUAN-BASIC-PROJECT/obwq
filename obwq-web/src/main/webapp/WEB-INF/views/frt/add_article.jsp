<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {height: 95%;overflow: hidden;margin-left: 50px;margin-right: 50px;}
</style>
<%-- <script type="text/javascript" src="<%=JspUtil.basePath(request) %>/js/jquery-1.5.min.js"></script> --%>
<%-- <script type="text/javascript" src="<%=JspUtil.basePath(request) s>/js/init.js"></script> --%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=A9b452236c17b1062a2872dc4888a7a6"></script>
<script type="text/javascript" src="<%=JspUtil.basePath(request) %>/js/addArticle.js"></script> 
<title>旅游网</title>
</head>
<body>
<table>
	<tr>
		<td>给这次活动起一个名称吧</td>
		<td><input /></td>
	</tr>
	<tr>
		<td>活动开发时间</td>
		<td><input /></td>
	</tr>
	<tr>
		<td>活动的内容</td>
		<td><textarea > </textarea></td>
	</tr>
</table>
<div id="allmap"></div>
</body>
</html>