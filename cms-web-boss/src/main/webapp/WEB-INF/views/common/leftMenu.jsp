<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!--  left menu -->
<div class="easyui-accordion" data-options="border:0,multiple:true">
</div>
<script type="text/javascript">
	var html = '';
	$(function() {
		var url = 'index/findMenuTree';
		$.ajax({
			type : "POST",
			url : url,
			data : {
			},
			dataType : 'json',
			async : false, // 设置同步方式
			cache : false,
			success : function(result) {
				//菜单元素建立
				if(result.hasChildren){
					html = '';
					$("div.easyui-accordion").empty();
					//回调遍历
					writeMenusElement(result.children);
					$("div.easyui-accordion").append(html);
					
				}
			},
			error : function(data) {
				$.messager.show({
					title : 'warning',
					msg : data.responseText
				});
			}
		});
		
		$(".easyui-accordion li").bind("click", function() {
			$(".selected_menu_li").removeClass("selected_menu_li");
			$(this).addClass("selected_menu_li");
		});
	});
	//回调遍历
	function writeMenusElement(menuTreeList){
		for (var i = 0; i < menuTreeList.length; i++) {
			var menuNode = menuTreeList[i];
			if(menuNode.hasChildren){//上级菜单
				html += '<div title="'+menuNode.name+'">';
				var menulist = menuNode.children;
				writeMenusElement(menulist);
				html += '</div>';
			} else {//最低级菜单
				if(i == 0){
					html += '<ul class="menu_ui">';
				}
				html += '<li><a href="javaScript:void(0);" onclick="addTabs(\''+menuNode.name+'\',\'${ctx}'+menuNode.url+'\')"><i class="fa fa-address-book-o" aria-hidden="true"></i> '+menuNode.name+'</a></li>';
				if(i == menuTreeList.length-1){
					html += '</ul>';
				}
			}
		}
	}
	
</script>