//grid loadsuccess
LoadSuccess = function(data) {
	$(":checkbox").unbind();
	$(":checkbox[name^='dwn']").bind("click", function(e) {
		var id = this.id;
		if ($("#" + id).prop("checked")) {
			$(":checkbox[name^=" + "'" + id + "-']").prop("checked", true);
		} else {
			$(":checkbox[name^=" + "'" + id + "-']").prop("checked", false);
		}

		if (id == "head") {
			channel.chMoneyTip();
		}
	})
	if (data.total == 0) {
		$.messager.show({
			title : '结果',
			msg : '没查到符合条件的数据！',
			showType : 'slide',
		});
	}
}

// 信审操作
function operateAduit(val, row, index) {
	return "<input type='checkbox' id='aduit-" + row.loanNo
			+ "' name='aduit-'/>&nbsp;<a href='javascript:fileDown(" + '"'
			+ row.loanNo + '","xs",false' + ")'>信审</a>";
}
// 合同操作
function operateContract(val, row, index) {
	return "<input type='checkbox' id='contract-" + row.loanNo
			+ "' name='contract-" + row.loanNo
			+ "'/>&nbsp;<a href='javascript:fileDown(" + '"' + row.loanNo + '","ht",false'
			+ ")'>合同</a>";
}

// 征信操作
function operateDwnCredit(val, row, index) {
	return "<input type='checkbox' id='credit-" + row.loanNo
			+ "' name='credit-" + row.loanNo
			+ "'/>&nbsp;<a href='javascript:fileDown(" + '"' + row.loanNo + '","zx",false'
			+ ")'>征信</a>";
}

// 附件下载
function fileDown(loanNo,fileType,isBatch) {
	alert("功能开发中..");
}

// 选中事件
function operateCheck(value, row, index) {
	return '<input onclick="channel.chMoneyTip()" type="checkbox" id="head-'
			+ row.loanNo + '" name="head-' + row.loanNo + '" value="'
			+ row.loanNo + '">'
}

// 更新批次
function updBatch() {
	var rows = $("#tblBatchLoan").datagrid("getSelections");
	var createTime = channel.getPageObj("createTime").value;
	var crtDate = channel.dataFmt(Number.parseInt(createTime))
	var curDate = channel.dataFmt(new Date());
	if (rows && rows.length <= 0) {
		$.messager.alert('提示', '请选择要更新的债权!');
		return;
	}
	if (crtDate != curDate) {
		$.messager.alert('提示', '非当日生成批次不可更新!');
		return;
	}
	var curBatchNum = channel.getPageObj("batchNum").value;
	var reqData = {};
	var loans = new Array();
	for (var i = 0; i < rows.length; i++) {
		loans.push(rows[i].loanNo);
	}
	reqData.newLoanNos = loans;
	reqData.bacthNum = curBatchNum;
	channel.ajax('batchMang/updBatch', JSON.stringify(reqData), "post", "json",
			succFn, null, "application/json;charset=utf-8");
}

//成功处理函数
function succFn(data) {
	if (data && data.repCode == '000000') {
		channel.queryTbl('conditionForm', 'tblBatchLoan', '');
	} else {
		$.messager.show({
			title : '提示',
			msg : '操作失败!' + data.repMsg
		});
	}
}