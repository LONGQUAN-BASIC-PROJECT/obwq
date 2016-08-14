<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.desksoft.util.JspUtil"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<link rel="stylesheet"         href="http://file.obwq.cn/static/jquery.bsgrid-1.37/builds/merged/bsgrid.all.min.css"/>
    <script type="text/javascript" src="http://file.obwq.cn/static/jquery.bsgrid-1.37/plugins/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="http://file.obwq.cn/static/jquery.bsgrid-1.37/builds/js/lang/grid.zh-CN.min.js"></script>
    <script type="text/javascript" src="http://file.obwq.cn/static/jquery.bsgrid-1.37/builds/merged/bsgrid.all.min.js"></script>
	<title>钉咚</title>
</head>
<body>
	
	<h1 style="margin-bottom: 20px"> 钉咚后台操作 </h1>
	
	<div style="margin-bottom: 40px;">
		<input size="40" id="searchKey"/> 
		<input type="button"  value="搜索" style="margin-left: 30px"/>
		<a  href="addCategory.htm" target="_blank"  style="margin-left: 30px">添加</a>
	</div>
	
	<table id="searchTable">
        <tr>
            <th w_index="id" width="20%;">ID</th>
            <th w_index="phone" w_align="left" width="20%;">号码</th>
            <th w_index="descr" w_align="left" width="20%;">描述</th>
            <th w_index="gmtCreateStr" width="15%;">创建时间</th>
            <th w_index="gmtModifyStr" width="15%;">修改时间</th>
            <th w_render="deleteRecord" width="10%;">操作</th>
            <th w_render="appendChild" width="10%;">操作</th>
            
        </tr>
    </table>
    <script type="text/javascript">
        var gridObj;
        $(function () {
            gridObj = $.fn.bsgrid.init('searchTable', {
                url: 'queryCategory.htm',
                // autoLoad: false,
                pageSizeSelect: true,
                pageSize: 18
            });
        });

        function deleteRecord(record, rowIndex, colIndex, options) {
            return '<a href="#" onclick="alert(\'ID=' + gridObj.getRecordIndexValue(record, 'id') + '\');">删除</a>';
        }
        
        function appendChild(record, rowIndex, colIndex, options) {
            return '<a href="addCategoryDetail.htm?id=' + gridObj.getRecordIndexValue(record, 'id')  + '" target="_blank">添加子类</a>';
        }
        
        
    </script>
	
</body>
</html>