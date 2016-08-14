<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = JspUtil.basePath(request);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加网址</title>
<script type="text/javascript" src="<%=basePath%>/js/Category.js"></script>
<style type="text/css">
	li{
		list-style: none;
	}
	.selectBackgroup{background-color:red;}
</style>
</head>
<body>
	<table width="1000px" height="400px;" border="2" style="margin-left: 20px;margin-top: 20px;">
	<tr height="10%">
    	<td colspan="3">
    		<div style="width: 80%;height: 100%;float: left;" id="language">
    			<input type="radio" name="languageType" value="0" rtype='share'>共享类
    			<c:forEach items="${lanagerList}" var="item">
    				<input type="radio" name="languageType" value="${item.languageid }" rtype='common'>${item.languagename }
    			</c:forEach>
    		</div>
    		<input type="button" id="addLanguage" value="添加语言方向" style="float: left;">
    	</td>
    </tr>
    <tr>
    	<td width="25%">
	    	<div style="width: 100%;height: 90%;overflow-y:scroll;" id="bigCategory">
	    	
	    	</div>
	    	<input type="button" id="addBigCategory" value="添加大类" style="float: left;">
	    	<input type="button" class="saveSequ" rid="bigCategory" value="保存大类顺序" style="float: left;">
    	</td>
        <td width="30%">
	        <div style="width: 100%;height: 90%;overflow-y:scroll;" id="smallCategory">
	        </div>
	        <input type="button" id="addSmallCategory" value="添加小类" style="float: left;">
	        <input type="button" class="saveSequ" rid="smallCategory" value="保存小类顺序" style="float: left;">
        </td>
        <td>
	        <div style="width: 100%;height: 90%;overflow-y:scroll;" id="webItem">
	        </div>
	        <input type="button" id="addWebAddress" value="添加网址" style="float: left;">
	        <input type="button" class="saveSequ"  rid="webItem" value="保存网址顺序" style="float: left;">
        </td>
    </tr>
</table>
</body>
</html>