<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = JspUtil.basePath(request);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
	<c:forEach items="${commonWebAddress }" var="item">
		<li class="cliclWebAddressSequ"   rvalue="${item.entryId }">
			<span style="width: 100px;">${item.entryName }</span>----<input type="button" value="删" rvalue="${item.entryId }" class="webAddressDeleted" />
		</li>
	</c:forEach>
</ul>