<%@page import="com.desksoft.util.JspUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style>
    .ks-waterfall {
        position: absolute;
        width: 192px;
        overflow: hidden;
        padding: 15px;
        border: 2px solid #ddd;
        margin-bottom: 20px;
        text-align: center;
        left:-9999px;
        top:-9999px;
    }
    html {
    overflow-y: scroll;
}

body {
    background-color: #E8E7E3;
}

#BackToTop {
    position: fixed;
    right: 9px;
    bottom: 9px;
    width: 50px;
    padding: 20px 10px;
    text-align: center;
    text-transform: uppercase;
    color: #41545F;
    text-decoration: none;
    background-color: white;
    border: 1px solid #ECECEC;
    opacity: 0.9;
    filter: alpha(opacity = 90);
    cursor: pointer;
    border-radius: 8px;
}

#ColumnContainer {
    position: relative;
    min-width: 500px;
    min-height: 500px;
    margin: 0 auto;
}

#article {
    width: auto;
    min-width: 500px;
    padding: 8px 0 0 0;
}

#wrapper {
    position: relative;
    width: auto;
    margin: 0 auto;
    border: none;
    margin-top: 30px;
}

#loadingPins {
    display: none;
    height: 20px;
    padding: 15px 0;
    text-align: center;
    font-weight: bold;
    border-top: 1px solid #CCC;
}

#loadingPins img {
    display: inline-block;
    margin-right: 6px;
}

#loadingPins span {
    font-size: 1.3em;
}

.pin {
    position: absolute;
    width: 193px;
    padding: 14px;
    background-color: white;
    overflow: hidden;
    margin-bottom: 20px;
}

.pin .image {
    position: relative;
    display: block;
    width: 190px;
    overflow: hidden;
    background-color: white;
    border: 1px solid white;
}

.pin .description {
    padding-top: 4px;
}

.pin p {
    display: block;
    margin: 0 2px;
    font-size: 1.1em;
    line-height: 1.45em;
}

.pin .image img {
    display: block;
}
</style>
<title>旅游网</title>
</head>
<body>
<jsp:include page="/p/top.jsp"></jsp:include>
<div id="wrapper">
    <div id="button_container"></div>

    <div id="article">
        <div id="ColumnContainer"></div>
        <a id="BackToTop" href="#">Scroll to Top</a>
        <div id="loadingPins"><img src="http://img03.taobaocdn.com/tps/i3/T1Ar9xXg0JXXXXXXXX-16-16.gif" alt="Pin Loader Image"/><span>正在努力加载数据&hellip;</span></div>
    </div>
</div>
<script type="tpl" id="tpl">
    <div class="pin ks-waterfall" data-id="{id}">
        <a href="#" class="image">
            <img height="{height}" alt="{title}" src="http://farm{farm}.static.flickr.com/{server}/{id}_{secret}_m.jpg"/>
        </a>
        <p class="description">{title}</p>
        <p>
            <button class="del">删除</button>
            <button class="grow">增高</button>
        </p>
    </div>
</script>
<script type="text/javascript">
KISSY.use("waterfall,ajax,node,button", function(S, Waterfall, io,  Node, Button) {
    var $ = Node.all;

    var tpl = $('#tpl').html(),
        nextpage = 1,
        waterfall = new Waterfall.Loader({
        container:"#ColumnContainer",
        load:function(success, end) {
            $('#loadingPins').show();
            S.IO({
                data:{
                    'method': 'flickr.photos.search',
                    'api_key': '5d93c2e473e39e9307e86d4a01381266',
                    'tags': 'rose',
                    'page': nextpage,
                    'per_page': 20,
                    'format': 'json'
                },
                url: 'http://api.flickr.com/services/rest/',
                dataType: "jsonp",
                jsonp: "jsoncallback",
                success: function(d) {
                    // 如果数据错误, 则立即结束
                    if (d.stat !== 'ok') {
                        alert('load data error!');
                        end();
                        return;
                    }
                    // 拼装每页数据
                    var items = [];
                    S.each(d.photos.photo, function(item) {
                        item.height = Math.round(Math.random()*(300 - 180) + 180); // fake height
                        items.push(new S.Node(S.substitute(tpl,item)));
                    });
                    success(items);
                    // 如果到最后一页了, 也结束加载
                    nextpage = d.photos.page + 1;
                    if (nextpage > d.photos.pages) {
                        end();
                    }
                },
                complete: function() {
                    $('#loadingPins').hide();
                }
            });
        },
        minColCount:2,
        colWidth:228
    });

    // scrollTo
    $('#BackToTop').on('click', function(e) {
        e.halt();
        e.preventDefault();
        $(window).stop();
        $(window).animate({
            scrollTop:0
        },1,"easeOut");
    });

});
</script>

</body>
</html>