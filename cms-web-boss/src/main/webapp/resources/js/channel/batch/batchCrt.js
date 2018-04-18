// 生成批次号
function gener_batchNum() {
	var rows = $("#tblBatchGenert").datagrid("getSelections");
	var moneys = countMoney(rows);
	if (rows.length <= 0) {
		$.messager.alert('提示', '请选择借款条目!');
		return false;
	} else {
		$.messager.confirm('提示', '您已选择债权数为：' + rows.length + '；放款金额为：'
				+ moneys.grantMoneyTotal + '；合同金额为：' + moneys.pactMoneyTotal
				+ '，确认生成批次号？', function(isSure) {
			if (isSure) {
				generBatch(rows);
			}
		});
	}
}

// 批次生成
function generBatch(rows) {
	var reqData = {};
	var loans = new Array();
	var ids = new Array();
	for (var i = 0; i < rows.length; i++) {
		loans.push(rows[i].loanNo);
		ids.push(rows[i].loanBaseId);
	}
	reqData.loanNos = loans;
	reqData.loanBaseIds=ids;
	channel.ajax('batchMang/generBatch', JSON.stringify(reqData), "post", "json", succFn, null,"application/json;charset=utf-8");
}

function succFn(data) {
	if (data && data.repCode == '000000') {
		channel.queryTbl('conditionForm','tblBatchGenert','validateDate');
		addTabs('批次管理', '/batchMang/batchMangView');
	}else{
		$.messager.show({
			title : '提示',
			msg : '操作失败!'+data.repMsg
		});
	}
}
