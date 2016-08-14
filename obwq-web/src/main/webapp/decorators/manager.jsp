<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
String basePath = JspUtil.basePath(request);
%>
<script type="text/javascript" src="<%=basePath %>/s/kissy/1.2.0/seed-min.js" ></script>
<script type="text/javascript" src="<%=basePath %>/js/init.js"></script>
<script src="<%=basePath %>/js/jquery-1.5.min.js" type="text/javascript"></script>
<title><sitemesh:write property='title'/></title>
<sitemesh:write property='head'/>
</head>
<body>
<input type="hidden" value="<%=basePath%>" id="page_context_path">
<sitemesh:write property='body'/>
</body>
</html>