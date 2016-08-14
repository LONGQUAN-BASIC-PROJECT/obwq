<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = JspUtil.basePath(request);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
	<c:forEach items="${categoryList }" var="item">
		<li class="bigCategorySequ"  rtype="bigCategory">
			<input type="radio" value="${item.categoryid }" name="bigCategoryType" />  ${item.categoryname }   
				&nbsp;&nbsp;<input type ="button" class="bigCategoryDelete" value="删除" rvalue="${item.categoryid }" /><br />
		<%-- 		&nbsp;&nbsp;<input type ="button" class="bigCategoryEdit" value="修改" rvlaue="${item.categoryid }" />  --%>
	</li>
	</c:forEach>
</ul>
