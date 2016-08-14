<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = JspUtil.basePath(request);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
	<c:forEach items="${webAddressList }" var="item">
	<li  class="webAddressSequ"  rtype="webItem">	
		<input type="radio" value="${item.entryId }" name="webAddresstType" />  ${item.entryName }   
				&nbsp;&nbsp;<input type ="button" class="webAddresstDelete" value="删除" rvalue="${item.entryId }" /><br /> 
		<%-- 		&nbsp;&nbsp;<input type ="button" class="webAddresstEdit" value="修改" rvlaue="${item.entryId }" /> --%>
	 </li>
	</c:forEach>
</ul>