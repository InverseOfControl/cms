//文件下载
function exportFile(url) {
	window.location.href = url;
}

// 外贸信托导出
function exportFileTxt(url) {
	window.location.href = url;
}

// 债权人附件下载
function showLoanInfo(batchNum) {

}

// 批次号链接
function operateLoan(val, row, index) {
	return "<a href='javascript:openTab(" + '"' + val + '",' + '"'
			+ row.createTime + '"' + ");'>" + val + "</a>";

}

function openTab(batchNum, createTime) {
	$("#layout-container").tabs("close", "债权批次信息");
	addTabs('债权批次信息', '/batchMang/batchLoanView?batchNum=' + batchNum
			+ '&createTime=' + createTime);
}

// 操作
function operateFmt(value, row, index) {

	var channelCode = row.channelCode;
	// 国民，海门，龙信的导出
	if (window.ghlChannelList.indexOf(channelCode) > -1) {
		var loanApprove_exp = "<a href='javascript:exportFile(\"batchMang/exportRightsAudit?batchNum="
				+ row.batchNum
				+ "&channelName="
				+ row.channelName
				+ "\");'>债权审核导出</a>";
		var loan_exp = "<a href='javascript:exportFile(\"batchMang/exportRights?batchNum="
				+ row.batchNum
				+ "&channelName="
				+ row.channelName
				+ "\");'>债权导出</a>";
		return loanApprove_exp + "&nbsp;&nbsp;&nbsp;" + loan_exp;
	} else if (window.wmxtChannelCode == channelCode) {
		var loanApprove_exp = "<a href='javascript:exportFileTxt(\"batchMang/wmxtExpLoan?batchNum="
				+ row.batchNum + "\");'>债权导出</a>";
		var loan_exp = "<a href='javascript:exportFileTxt(\"batchMang/wmxtExpLoanCheck?batchNum="
				+ row.batchNum + "\");'>还款计划导出</a>";
		return loanApprove_exp + "&nbsp;&nbsp;&nbsp;" + loan_exp;
	} else {// 渤海2的导出
		var transferAppExpPdf = "<a href='javascript:exportOperate(\"appForm/transferAppExpPdf\",\""
				+ row.batchNum + "\")'>划拔申请书导出(PDF)</a>&nbsp;&nbsp;";
		var transferAppExpXls = "<a href='javascript:exportOperate(\"appForm/transferAppExpXls\",\""
				+ row.batchNum + "\")'>划拔申请书导出(Xls)</a>&nbsp;&nbsp;";
		var loanAppExpXls = "<a href='javascript:exportOperate(\"appForm/loanAppExpXls\",\""
				+ row.batchNum + "\");'>放款申请明细导出(Xls)</a>&nbsp;&nbsp;";
		var loanAppExpTxt = "<a href='javascript:exportOperate(\"appForm/loanAppExpTxt\",\""
				+ row.batchNum + "\");'>放款申请明细导出(Txt)</a>&nbsp;&nbsp;";
		var repaymentExpXls = "<a href='javascript:exportOperate(\"appForm/repaymentExpXls\",\""
				+ row.batchNum + "\");'>还款计划导出(Xls)</a>&nbsp;&nbsp;";
		var transferAppImpXls = "<a href='javascript:importOperate(\"appForm/transferAppImpXls\",\""
				+ row.batchNum + "\");'>划拔申请书导入(Xls)</a>";
		return transferAppExpPdf + transferAppExpXls + loanAppExpXls
				+ loanAppExpTxt + repaymentExpXls + transferAppImpXls;
	}

}

// 导出操作
function exportOperate(url, batchNum) {
	window.location.href = url + "?batchNum=" + batchNum;
}

// 划拨申请书导入
function importOperate(url, data) {
	$("#importExcelWin").window('open');
	$("#baseFileForm").form('clear');
	document.getElementById("formbatchNum").value = data;
}

// 签章
function submitEsignatureBtn() {
	var applyEsignaturePdf = $("#applyPdfEsignatureFile").filebox("getValue");
	if (!applyEsignaturePdf) {
		$.messager.alert('警告', '请按规则导入需要签章的原文件！', 'warning');
		return;
	}
	if (applyEsignaturePdf.lastIndexOf(".pdf") == -1) {
		$.messager.alert('警告', '请按规则导入正确的文件格式(pdf)！', 'warning');
		return;
	}
	$.messager.confirm("提示", "确认上传划拨申请书原文件吗？", function(r) {
		if (r) {
			$("#baseFileForm").form('submit', {
				type : "post",
				dataType : 'json',
				url : 'appForm/applyPdfEsignature',
				success : function(data) {
					fileUpload(data);

				},
				error : function(data) {
					$.messager.alert('警告', data.resMsg, 'warning');
				}
			});
		}
	})
}

// 提交
function submitBtn() {
	// 划拨申请书签章文件(pdf)文件
	var pdfFile = $("#applyPdfFile").filebox("getValue");
	if (!pdfFile) {
		$.messager.alert('警告', '请选择导入已签章划拨申请书(pdf)文件！', 'warning');
		return;
	}
	// 文件名转换为小写
	pdfFile = pdfFile.toLowerCase();
	// 必须是pdf文件
	if (pdfFile.lastIndexOf(".pdf") == -1) {
		$.messager.alert('警告', '文件类型错误！必须是pdf格式。', 'warning');
		return;
	}
	// 划拨申请书(xls)文件
	var xlsFile = $("#applyXlsFile").filebox("getValue");
	if (!xlsFile) {
		$.messager.alert('警告', '请选择导入划拨申请书(xls)文件！', 'warning');
		return;
	}
	// 文件名转换为小写
	xlsFile = xlsFile.toLowerCase();
	// 必须是xls文件
	if (xlsFile.lastIndexOf(".xls") == -1) {
		$.messager.alert('警告', '文件类型错误！必须是xls格式。', 'warning');
		return;
	}
	// 划拨申请书签章(pdf)文件批次号
	var pdfSeqNo = pdfFile.substring(pdfFile.lastIndexOf("_") + 1, pdfFile
			.lastIndexOf(".pdf"));
	// 划拨申请书(xls)文件批次号
	var xlsSeqNo = xlsFile.substring(xlsFile.lastIndexOf("_") + 1, xlsFile
			.lastIndexOf(".xls"));
	// 两个文件的批次号必须相同
	if (pdfSeqNo != xlsSeqNo) {
		$.messager.alert('警告', '请导入批次号相同的文件！', 'warning');
		return;
	}
	$.messager.confirm("提示", "确认上传划拨申请书吗？", function(r) {
		if (r) {
			$("#baseFileForm").form('submit', {
				type : "post",
				dataType : 'json',
				url : 'appForm/transferAppImpXls',
				success : function(data) {
					fileUpload(data);
					setTimeout(function() {
						$("#importExcelWin").window('close');
						$("#tblBatchMang").datagrid("reload");
					}, 1000);
				},
				error : function(data) {
					$.messager.alert('警告', '操作失败！', 'warning');
				}
			});
		}
	});
};

function fileUpload(dataStr){
	var data=$.parseJSON(dataStr);
	var repCode = data.repCode;
	if (repCode == "000000") {
		$.messager.show({
			title : '提示',
			msg : '操作成功 !'
		});
	} else {
		$.messager.show({
			title : '提示',
			msg : '操作失败 !'
		});
	}
}

// 关闭
function closeBtn() {
	$("#importExcelWin").window('close');
	
}

//根据渠道控制是否显示状态查询按钮
function channelSelect() {
	var channelCode = $(this).combobox("getValue");
	if (window.ghlwChannelList.indexOf(channelCode) > -1) {// 国民，海门，龙信，信托
		$(".statusSearchClass").hide();//根据class控制显示与隐藏，因为同时有两个地方需要控制
	} else {
		$(".statusSearchClass").show();
	}
}
//表格数据加载完成提示
gridLoadSuccess = function(data) {
	if (data.total == 0) {
		$.messager.show({
			title : '结果',
			msg : '没查到符合条件的数据！',
			showType : 'slide',
		});
	}
	// 控制表格中状态的显示与隐藏
	var channelCode = $("#channelId").combobox("getValue");
	if (window.ghlChannelList.indexOf(channelCode) > -1) {// 国民，海门，龙信，外贸信托渠道
		$("#tblBatchMang").datagrid('hideColumn', 'status');
	} else {
		$("#tblBatchMang").datagrid('showColumn', 'status');
	}
	
}
