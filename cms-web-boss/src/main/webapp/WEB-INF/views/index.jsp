<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link rel="stylesheet" href="${ctx}/resources/easyui/themes/default/easyui.css"> --%>
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/resources/easyui/themes/blue/easyui.css">
<link rel="stylesheet" href="${ctx}/resources/easyui/themes/icon.css">
<link rel="stylesheet" href="${ctx}/resources/easyui/themes/color.css">
<link rel="stylesheet" href="${ctx}/resources/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${ctx}/resources/css/bms.css">
<link rel="stylesheet" href="${ctx}/resources/css/style.css">
<script type="text/javascript" src="${ctx}/resources/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/extendsEasyui.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/moment.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<title>借款管理</title>
</head>
<body class="easyui-layout">
     <div id="loading"
     style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
     <img src="${ctx}/resources/images/ajax-loader.gif"
         style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
     </div>

	<div data-options="region:'north',split:false,collapsible:false,border:0">
		<jsp:include page="common/header.jsp"></jsp:include>
	</div>
	
	<div data-options="region:'west',split:false,collapsible:false,border:0,href:'${ctx}/index/leftMenu'" style="width: 200px;"></div>
	<div data-options="region:'center'">
		<div id="layout-container" class="easyui-tabs" data-options="fit:true,border:0"></div>
	</div> 
	  
</body>

<script type="text/javascript">
	/**添加tab**/
	function addTabs(title, url) {
		if ($("#layout-container").tabs("exists", title)) {//获取当前tab
			$("#layout-container").tabs("select", title);
		} else {// 新建
			$("#layout-container").tabs("add", {
				title : title,
				closable : true,
				href : "${ctx}" + url
			});
		}
	}
</script>
</html>