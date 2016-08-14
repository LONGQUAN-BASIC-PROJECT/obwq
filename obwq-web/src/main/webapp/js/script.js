KISSY.ready(function(c) {
	KISSY.use('gallery/autoResponsive/1.3/index',function(S,T){
	    var E = S.Event,D = S.DOM,
	        closeAnimate = false;

	    var $ = S.Node.all ;
	    
	    $(".block").each(function(o,t,a){
	    	var i = Math.ceil(Math.random()*9) ;
	    	o.addClass("color_"+i);
	    })

	    var width = $(window).width();
		
		if(width > 500){
			var lw = width - 200 ;
			var count = Math.floor(lw / 140) ;
			$("#main_div").css("width",(count * 140 + (count - 1) * 10  + 5) + "px");
		}
		

	    $(".ks-autoResponsive-container").each(function(o,t,a){
	    	
	    	var prioritySort = new T.Sort();
	    	var priority = new T({
	    		container:o,
	    		selector:'div',
	    		unitMargin:{
	    			x :10,
	    			y:10
	    		},
	    		gridWidth:10,
	    		plugins:[prioritySort],
	    		autoInit:false,
	    		closeAnim:false
	    	});
	    	
	    	priority.init();
	    })
	});
})
