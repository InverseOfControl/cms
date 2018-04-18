//操作
function operateFmt(value, row, index) {
	var transferAppExpPdf = "<a href='javascript:exportOperate(\"appForm/transferAppExpPdf\",\""
			+ row.batchNum + "\")'>划拔申请书导出(PDF)</a>&nbsp;&nbsp;";
	var transferAppExpXls = "<a href='javascript:exportOperate(\"appForm/transferAppExpXls\",\""
			+ row.batchNum + "\")'>划拔申请书导出(Xls)</a>&nbsp;&nbsp;";
	var loanAppExpXls = "<a href='javascript:exportOperate(\"appForm/loanAppExpXls\",\""
			+ row.batchNum + "\");'>放款申请明细导出(Xls)</a>&nbsp;&nbsp;";
	var loanAppExpTxt = "<a href='javascript:exportOperate(\"appForm/loanAppExpTxt\",\""
			+ row.batchNum + "\");'>划拔申请明细导出(Txt)</a>&nbsp;&nbsp;";
	var repaymentExpXls = "<a href='javascript:exportOperate(\"appForm/repaymentExpXls\",\""
			+ row.batchNum + "\");'>还款计划导出(Xls)</a>&nbsp;&nbsp;";
	var transferAppImpXls = "<a href='javascript:importOperate(\"appForm/transferAppImpXls\",\""
			+ row.batchNum + "\");'>划拔申请书导入(Xls)</a>";
	return transferAppExpPdf + transferAppExpXls + loanAppExpXls
			+ loanAppExpTxt + repaymentExpXls + transferAppImpXls;

}

//导出操作
function exportOperate(url, data) {
	window.location.href = url + "?bacthNum=" + data;
}

//划拨申请书导入
function importOperate(url) {
	var batchNum = $("#batchNum").val();
	$("#importExcelWin").window('open');
	$("#baseFileForm").form('clear');
	$("#hksqBatchNum").attr("value", batchNum);
}

//签章
function submitEsignatureBtn() {
	debugger;
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
			$.requestManagement.baseFileForm.ajaxSubmit({
				type : "post",
				dataType : 'json',
				url : '',
				hasDownloadFile : true,
				success : function(data) {
					if (data == null) {
						$.messager.alert('信息', "盖章成功！", 'info');
					}

				},
				error : function(data) {
					$.messager.alert('警告', data.resMsg, 'warning');
				}
			});
		}
	})
}

//提交
function submitBtn() {
	debugger;
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
			$.requestManagement.baseFileForm.ajaxSubmit({
				type : "post",
				dataType : 'json',
				url : $.requestManagement.importUrl,
				hasDownloadFile : false,
				success : function(data) {
					var resCode = data.resCode;
					var resMsg = data.resMsg;
					if (resCode != "000000") {
						$.messager.alert('警告', resMsg, 'warning');
						return;
					}
					$.messager.alert('提示', resMsg, 'info');
					setTimeout(function() {
						$.requestManagement.importExcelWin.window('close');
						$.requestManagement.reloadDataGrid();
					}, 1000);
				},
				error : function(data) {
					$.messager.alert('警告', '操作失败！', 'warning');
				}
			});
		}
	});
};

//关闭
function closeBtn(){
	$("#importExcelWin").window('close');
}
