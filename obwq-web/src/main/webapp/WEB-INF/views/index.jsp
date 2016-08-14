<%@page import="com.desksoft.util.JspUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://a.tbcdn.cn/s/kissy/1.3.0/seed-min.js"></script>
<link rel="stylesheet" href="css/demo.css"/>
<script type="text/javascript" src="js/script.js"></script>

<script type="text/javascript" src="js/jquery-1.5.min.js"></script>
<title>导航</title>
</head>
<body>
	<div  style="margin:auto;" id="main_div">
			  
               <c:forEach var="category" items="${baseData.xmlCategory}">
					<div>
						<div style="color:#FFF0F0;height: 50px;line-height: 50px;font-weight: bold;" >
							${category.categoryname }
						</div>
						<div class="ks-autoResponsive-container" id="${category.categoryid}">
	                        <c:forEach var="webAddress" items="${category.webAddress}">
		                        <div class="block"><a href="${webAddress.address }" target="_blank" style="">${webAddress.entryName}</a></div>
	                        </c:forEach>
						</div>
	               </div>
			   </c:forEach>
    </div>
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1253663835'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/stat.php%3Fid%3D1253663835%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script>
</body>
</html>