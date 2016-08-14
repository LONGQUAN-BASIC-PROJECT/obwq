<%@page import="com.desksoft.util.JspUtil"%>
<%
	response.sendRedirect(JspUtil.basePath(request) + "/index.htm");
%>