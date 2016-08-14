<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.cl:after {
    clear: both;
    content: ".";
    display: block;
    height: 0;
    visibility: hidden;
}
#toptb {
    background: url("../images/top.png") repeat-x scroll 0 0 transparent !important;
    height: 40px;
    left: 0;
    line-height: 40px;
    padding-bottom: 0;
    position: fixed;
    right: 0;
    top: 0;
    z-index: 102;
}
.wp {
	margin: 0 auto;
	width: 85%;
}
</style>
<div id="toptb" class="cl">
	<div class="wp">
		<div style="float: left;">
		<input type="radio"  name="t1" />全部的
		<input type="radio"  name="t1"  />最新上线的
		<input type="radio"  name="t1" />大家都喜欢的
	</div>
	
	<div style="float: left" title="类型,单选">
		<input type="radio"    name="t2"  />爬山
		<input type="radio"    name="t2" />骑车
	</div>
		
	<div>
		<input type="button" value="QQ登陆" />
		<input type="button" value="发布内容" />
	</div>
	</div>
</div>