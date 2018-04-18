<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/contractTemplate/contractTemplateList.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<div id="processManager" title="工作流管理"
	style="height: 100%; padding-top: 8px;">
	<iframe frameborder=0  marginheight=0 marginwidth=0 scrolling=no width="100%" height="100%"
	src="${ctx}/uflo/central"></iframe>
</div>