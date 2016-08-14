<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../ex/jquery-1.5.min.js" ></script>
<title>ajax</title>
</head>
<body>
<input type="button" value="click" onclick="aaaa();">
	<script type="text/javascript">
		function aaaa(){
			 $.post("../testServlet",{},function(result){
				    $("#span_a").html(result);
			 });
		}
	</script>
	<div id="span_a">
	
	</div>
</body>
</html>