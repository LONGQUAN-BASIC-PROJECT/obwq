/**
 * 文章单页的JS
 */
var editor = null ;
KISSY.ready(function (S) {
    S.use('editor,extends/ajax/ajax', function (S,editor,Ajax) {
		var $ = S.all ;
        var KE = KISSY.Editor;
        editor = KE("#editor", S.clone(edit_cfg)).use("elementpaths," +
            "sourcearea,preview," +
            "checkbox-sourcearea," +
            "templates,separator," +
            "undo,separator," +
            "removeformat,font,format,color,separator," +
            "list,indent," +
            "justify,separator,link," +
            "image,flash," +
            "video," +
            "music," +
            "xiami-music," +
            "smiley,separator,table,resize," +
            "draft," +
            "multi-upload," +
            "pagebreak,separator,maximize,dragupload");
        
        
        $("#click").on("click",function(){
        	var content = editor.getData();
        	var articleId = $("#articl_id").val();
        	var param = {articleId:articleId,
        				content:content,
        		};
        	new Ajax($("#page_context_path").val()+"/page/reply/insert.jspx",param,function(data){
        		if(data.status == 1){
        			alert("回复成功");
        			window.location.reload();
        		}else{
        			alert("回复失败");
        		}
        	})
        });
    });
});