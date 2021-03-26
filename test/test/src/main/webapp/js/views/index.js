$(function(){
	$("#menuTree").tree({
		url:'/queryForMenu',
		onClick:function(node){
			if (node.attributes) {
				node.attributes = $.parseJSON(node.attributes);
			}
			console.log(node);
			//在选项中添加新面板
			var myTab = $("#myTabs");
			//在选项卡中是否已经有该节点的面板.
			if(myTab.tabs("exists",node.text)){
				//选中面板
				myTab.tabs("select",node.text);
			}else{
				myTab.tabs("add",{
					title:node.text,
					closable:true,
					content:"<iframe src='"+node.attributes.url+"' style='width:100%;height:100%' frameborder=0></iframe>"
				});
			}
			
		}
	});
});


