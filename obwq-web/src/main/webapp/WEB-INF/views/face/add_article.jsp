<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String basePath = JspUtil.basePath(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<title>添加文章</title>
<script type="application/javascript">
    $(document).ready(function(){

        $("#basic-url").keyup(function(){
            var url = $(this).val();
            if(!url.startsWith("http") && !url.startsWith("https")){
                return ;
            }

            //$("#iframe_url").attr("src",url);
        });

        $("#conf_submit").click(function(){
            var rurl = "<%=basePath%>/ae/article/add/url.htm";
            $.post(rurl,
                {url:$("#basic-url").val(),
                groupId:77},
                function(result){
                if(result.success){
                    alert('添加成功');
                }else{
                    alert("添加失败(" + result.resultDesc +  "),请联系龙拳,谢谢");
                }
            });
        });


        $("#conf_refersh").click(function(){
            var url = $("#basic-url").val();
            $("#iframe_url").attr("src",url);
        });

    });
</script>
</head>
<input type="hidden" id="" value="JspUtil.basePath(request)" />

<label for="basic-url" style="margin-top: 10px;">输入URL，自动预览，请不能自动，回车确认预览</label>
<div class="input-group">
    <span class="input-group-addon" id="basic-addon3">请输入网址</span>
    <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3" value="http://3g.163.com/touch/article.html?docid=CDI568UO00018AOQ" />
</div>


<div class="btn-group" role="group" aria-label="..." style="margin-top: 5px;width: 100%">
    <button type="button" class="btn btn-default"  id="conf_submit"  style="float: right;font-size: 10px;margin-right: 10px">确认提交</button>
    <button type="button" class="btn btn-default"  id="conf_refersh" style="float: right;font-size: 10px;margin-right: 10px">刷新预览</button>
</div>

<h2 style="margin-top: 40px;">预览效果</h2>

<div class="embed-responsive embed-responsive-4by3" style="margin-top: 5px;">
    <iframe class="embed-responsive-item" id="iframe_url" src="http://3g.163.com/touch/article.html?docid=CDI568UO00018AOQ"></iframe>
</div>

</body>
</html>