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
<script src="<%=basePath %>/js/jquery-1.5.min.js" type="text/javascript"></script>
<title>左边</title>
<script type="text/javascript">
	$().ready(function(){
		$(".leftItem")[0].click();
	})
</script>
</head>
<body bgcolor="#aacbb;">
 <a class="leftItem" href="<%=basePath%>/opterate/webAddress/add_init.jspx" target="mainFrame">公共网站</a><br />
 <a class="leftItem" href="<%=basePath%>/opterate/category/add_init.jspx" target="mainFrame">添加数据</a> <br />
 <a class="leftItem" href="<%=basePath%>/opterate/webAddress/add_init.jspx" target="mainFrame">工具网站</a><br />
</body>
</html>