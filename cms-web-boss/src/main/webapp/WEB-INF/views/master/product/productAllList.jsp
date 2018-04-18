<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/common/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript"
	src="${ctx}/resources/js/master/product/productAllList.js"></script>
<script type="text/javascript"
	src="${ctx}/resources/js/common.js"></script>
<div id="productPageAll" title="产品表查询"
	style="height: 92px; padding-top: 8px;">
	<table id="new_appPersonDatagrid" toolbar="#toolbar"></table>
</div>



