KISSY.use("ds/mallutil/ajax,node,dom",function(S,Ajax,node,dom){
	var $ = S.all;
	
	var webAddress = webAddress || 	{
		firstClickObject : null ,
		addAddress : function(){
			$("#webAddressName").val('');
			$("#webAddressUri").val('');
			$("#addWebAddress").on("click",function(){
				var webAddressName = $("#webAddressName").val();
				var webAddressUri = $("#webAddressUri").val();
				if(webAddressName.length < 1 || webAddressUri.length < 1){
					alert("请添加数据");
					return ;
				}
				var param = {
						entryName : webAddressName ,
						address :  webAddressUri
				}
				new Ajax($("#page_context_path").val()+"/opterate/webAddress/ajax/add.jspx",param,function(data){
	        		if(data.status == 1){
	        			alert("新增成功");
	        			window.location.reload();
	        		}else{
	        			alert("新增失败");
	        		}
	        	})
			})
		},
		loadData : function(){
			new Ajax($("#page_context_path").val()+"/opterate/webAddress/ajax/findCommonWebAddress.jspx",{},function(data){
				$("#list_web_address").html(data);
				webAddress.initWebAddressDeleted();
				webAddress.initWebAddressEdit();
				webAddress.initCliclWebAddressSequ();
			})
		},
		initWebAddressDeleted : function(){
			$(".webAddressDeleted").on("click",function(){
				new Ajax($("#page_context_path").val()+"/opterate/object/ajax/delete.jspx",{
					serviceImpl : "webAddressService",
					id : $(this).attr("rvalue")
				},function(data){
					if(data.status == 1){
	        			alert("删除成功");
	        			window.location.reload();
	        		}else{
	        			alert("删除失败");
	        		}
				});
				return false ;
			})
		},
		initWebAddressEdit : function(){
			$(".webAddressEdit").on("click",function(){
				
			})
		},
		initCliclWebAddressSequ : function(){
			$(".cliclWebAddressSequ").on("click",function(){
				if(!webAddress.firstClickObject){
					webAddress.firstClickObject = this ;
					$(this).addClass("selectBackgroup");
					return ;
				}
				if(webAddress.firstClickObject == this){
					return ;
				}
				$(webAddress.firstClickObject).insertBefore($(this));
				//$(webAddress.firstClickObject).remove();
				webAddress.firstClickObject = null ;
				$("#list_web_address li").removeClass("selectBackgroup");
			})
		},
		saveWebAddressSequ : function(){
			$("#saveWebAddressSequ").on("click",function(){
				var arr = new Array();
				$("#list_web_address li").each(function(){
					arr.push($(this).attr("rvalue"));
				});

				new Ajax($("#page_context_path").val()+"/opterate/object/ajax/updateSequ.jspx",{
					serviceImpl : "webAddressService",
					idlist : arr.join(",")
				},function(data){
					if(data.status == 1){
	        			alert("顺序保存成功");
	        			window.location.reload();
	        		}else{
	        			alert("顺序保存失败");
	        		}
				});
			
			});
		}
	}
	
	S.ready(function(){
		webAddress.addAddress();
		webAddress.loadData();
		webAddress.saveWebAddressSequ();
	})
})