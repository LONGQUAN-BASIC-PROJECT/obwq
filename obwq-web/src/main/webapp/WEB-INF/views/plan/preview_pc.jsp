<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.desksoft.util.JspUtil"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<title>足迹-${plan.name}</title>
<meta name="viewport"
	content="width=device-width, initial-scale=0.65, maximum-scale=1, minimum-scale=0.65, user-scalable=1">
<link rel="shortcut icon" type="image/x-icon"	href="<%=JspUtil.basePath(request) %>/images/favicon.ico">
<link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'	rel='stylesheet'>
<link rel="stylesheet" href="<%=JspUtil.basePath(request) %>/js/g/themes/1/aino.css">
<link rel="stylesheet"	href="<%=JspUtil.basePath(request) %>/js/g/themes/1/G-preview.css">

<script>document.documentElement.className = "js";</script>
 
<script type="text/javascript">
var before = "http://obwq.oss-cn-hangzhou.aliyuncs.com/" ;
G_DEMO_IMAGES = [
	 <c:forEach var="detail" items="${planDetailList}">
	 	{
	 		title:"${plan.name}",
	 		thumb:before + "${detail.pictureUrl}".replace(".png","_mini.png").replace(".jpg","_mini.jpg"),
	 		image:before + "${detail.pictureUrl}",
	 		big:before +  "${detail.pictureUrl}",
	 		description:"${detail.content}",
	 		y:"${detail.xx}",
	 		x:"${detail.yy}",
	 		imageUrl:before + "${detail.pictureUrl}",
	 		imageNote:before + "${detail.content}"
	 	},
	 </c:forEach>
];


</script>
<script src="<%=JspUtil.basePath(request) %>/js/g/f.js"></script>


</head>
<body class="tp twelve">
	<div id="top">
		<!-- <div id="crumb">
			<div class="switch">
				<span class="title">Theme preview:</span>
				<div id="theme-switcher">
					<h1 id="title">Twelve</h1>
					<div id="themes">

						<a href="/themes/azur/">Azur <span> $29</span></a> <a
							href="/themes/twelve/">Twelve <span> $29</span></a> <a
							href="/themes/fullscreen/">Fullscreen <span> $9</span></a> <a
							href="/themes/classic/">Classic <span> Included</span></a> <a
							href="/themes/folio/">Folio <span> $29</span></a> <a
							href="/themes/miniml/">Miniml <span> $29</span></a>

					</div>
				</div>
			</div>
			<div class="nav">
				<a class="back" href="/themes/">Browse all themes</a> <a
					class="home" href="/">Home</a>
			</div>
		</div> -->
		<div id="btn" class="btn">
			<a class="buy green" target="_blank" href="http://android.myapp.com/myapp/detail.htm?apkName=com.tmall.PhotoMap">下载android客户端</a>
		</div>
	</div>
	<div id="preview-body">
		<div id="gallery">
			<div id="demo"></div>
		</div>
	</div>
	<script>G.init(1)</script>

</body>
</html>