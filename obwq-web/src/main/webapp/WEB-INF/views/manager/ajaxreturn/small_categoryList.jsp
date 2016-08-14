<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = JspUtil.basePath(request);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
	<c:forEach items="${categorydetailList }" var="item">
		<li  class="smallCategorySequ" rtype="smallCategory">
		<input type="radio" value="${item.categorydetailid }" name="smallCategoryType" />  ${item.categorydetailname }   
			&nbsp;&nbsp;<input type ="button" class="smallCategoryDelete" value="删除" rvalue="${item.categorydetailid }" /><br /> 
	<%-- 		&nbsp;&nbsp;<input type ="button" class="smallCategoryEdit" value="修改" rvlaue="${item.categorydetailid }" /> --%>
	</li>
	</c:forEach>
</ul>