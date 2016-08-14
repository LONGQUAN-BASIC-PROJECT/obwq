KISSY.use('dom,node,sizzle,event',function(S,dom,node,sizzle,event){
	 var $ = S.all ;
	 var indexApp = indexApp || {
		 bindMore : function(){
			 $(".moreWebAddress").on("click",function(){
				 var flag = $(this).attr("flag");
				 if("s" == flag){
					 //动态计算高度
					 var length = $(this).siblings("ul").children().length ;
					 var size = length / 7 ;
					 if(length % 7 != 0){
						 size++;
					 }
					 $(this).parent().css("height",(size*24)+"px")
					 $(this).parent().parent().css("height",(size*24)+"px")
					 $(this).text("收起»");
					 $(this).attr("flag","z");
				 }else{
					 $(this).parent().css("height","24px")
					 $(this).parent().parent().css("height","24px")
					 $(this).attr("flag","s");
					 $(this).text("展开»");
				 }
			 })
		 }
	 };
	 
	 S.ready(function(){
		 indexApp.bindMore();
	 })
 });
