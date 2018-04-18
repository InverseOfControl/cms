var channel = new Object();
// 查询
channel.queryTbl = function(formName, tblName, isValid) {
	var tblObj = $("#" + tblName + "");
	var formObj = $("#" + formName + "");
	var data = formObj.serializeArray();
	var jsonData = channel.formToJson(data);
	if (isValid) {
		if (channel[isValid]()) {
			tblObj.datagrid("reload", jsonData);
		}
	} else {
		tblObj.datagrid("reload", jsonData);
	}
}

// 日期校验
channel.validateDate = function() {
	var begDate = $('#begDate').datebox('getValue');
	var endDate = $('#endDate').datebox('getValue');
	var curentseconds = new Date().getTime();
	var begmillseconds = new Date(begDate).getTime();
	var endmillseconds = new Date(endDate).getTime();
	if ((begmillseconds && begmillseconds > curentseconds)
			|| (endmillseconds && endmillseconds > curentseconds)) {
		$.messager.alert('提示', '放款审核通过时间须小于当前时间!');
		return false;
	}

	if (begmillseconds > endmillseconds) {
		$.messager.alert('提示', '放款审核通结束时间不能小于开始时间!');
		return false;
	}

	return true;
}

// 表单数据转json对象
channel.formToJson = function(dataObject) {
	var jsonObject = '{';
	var k = 0;
	$.each(dataObject, function() {
		k++;
		var objName = this.name;
		var objValue = this.value;
		jsonObject += '"' + objName + '":';
		jsonObject += '"' + objValue + '"';
		if (k < dataObject.length) {
			jsonObject += ",";
		}
	});
	jsonObject += '}';
	return eval("(" + jsonObject + ")");
}

// 表格数据加载完成提示
channel.gridLoadSuccess = function(data) {
	if (data.total == 0) {
		$.messager.show({
			title : '结果',
			msg : '没查到符合条件的数据！',
			showType : 'slide',
		});
	}
}

channel.chMoneyTip = function() {
	var arrayRow = new Array();
	var rows = $("#tblBatchLoan").datagrid("getRows");
	var checks = $("input[name^='head-']:checked");
	for (var k = 0; k < checks.length; k++) {
		var loanNo = checks[k].value;
		for (var i = 0; i < rows.length; i++) {
			if (loanNo == rows[i].loanNo) {
				arrayRow.push(rows[i]);
			}
		}
	}
	var moneys = countMoney(arrayRow);
	channel.showTip(moneys);

};

// 金额计算
channel.moneyTip = function() {
	var gridObj = $(this);
	var rows = gridObj.datagrid("getSelections");
	var moneys = countMoney(rows);
	channel.showTip(moneys);
}

//金额提示
channel.showTip = function(moneys) {
	var index = $("#layout-container").tabs("getTabIndex",
			$("#layout-container").tabs("getSelected"));
	$("#moneyTip", $("#layout-container").tabs("getTab", index))[0].innerHTML = "已选放款总金额："
			+ moneys.grantMoneyTotal + "&nbsp;已选合同总金额：" + moneys.pactMoneyTotal;
}

// 日期格式化yyyy-MM-dd HH:mi:ss
channel.dataTimeFmt = function(date) {
	if (!date) {
		return '';
	}
	if (typeof date != 'object') {
		date = new Date(date);
	}
	var y = date.getFullYear();
	var M = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d)
			+ ' ' + h + ':' + (m < 10 ? ('0' + m) : m) + ':' + (s < 10 ? ('0' + s) : s);
}

// 日期格式化yyyy-MM-dd
channel.dataFmt = function(date) {
	if (!date) {
		return '';
	}
	if (typeof date != 'object') {
		date = new Date(date);
	}
	var y = date.getFullYear();
	var M = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d)
}

// 获取页面元素对象
channel.getPageObj = function(id) {
	var index = $("#layout-container").tabs("getTabIndex",
			$("#layout-container").tabs("getSelected"));
	return $("#" + id, $("#layout-container").tabs("getTab", index))[0];
}

channel.defultSucFn = function(data) {
	if (data && data.repCode != '000000') {
		console.log(data.repMsg);
		$.messager.show({
			title : '提示',
			msg : data.repMsg
		});
	} else {
		$.messager.show({
			title : '提示',
			msg : "操作成功!"
		});
	}
}

channel.defultEroFn = function(data) {
	var msg = "操作失败!"
	if (data && data.repMsg) {
		msg = data.repMsg;
	}
	$.messager.show({
		title : '提示',
		msg : msg
	});
}
// 金额计算
function countMoney(loans) {
	var pactMoneyTotal = 0;
	var grantMoneyTotal = 0;
	var moneys = {
		"grantMoneyTotal" : 0,
		"pactMoneyTotal" : 0
	};
	for (var i = 0; i < loans.length; i++) {
		pactMoneyTotal = addNum(pactMoneyTotal, Number(loans[i].pactMoney));
		grantMoneyTotal = addNum(grantMoneyTotal, Number(loans[i].grantMoney));
	}
	moneys.grantMoneyTotal = grantMoneyTotal;
	moneys.pactMoneyTotal = pactMoneyTotal;
	return moneys;
}

// 加法运算
function addNum(num1, num2) {
	var len1, len2, m;
	try {
		len1 = num1.toString().split(".")[1].length;
	} catch (e) {
		len1 = 0;
	}
	try {
		len2 = num2.toString().split(".")[1].length;
	} catch (e) {
		len2 = 0;
	}
	m = Math.pow(10, Math.max(len1, len1));
	return (num1 * m + num2 * m) / m;
}

channel.ajax = function(url, param, method, dataType, successFn, errorFn,
		contextType) {
	if (!successFn) {
		successFn = channel.defultSucFn;
	}

	if (!errorFn) {
		errorFn = channel.defultEroFn;
	}

	if (!dataType) {
		dataType = "json";
	}

	if (!method) {
		method = "post";
	}

	$.ajax({
		url : url,
		data : param,
		type : method,
		dataType : dataType,
		contentType : contextType,
		success : successFn,
		error : errorFn
	});
}

channel.datagrid = $.extend({}, $.fn.datagrid.defaults, {
	loader : function(_7c5, _7c6, _7c7) {
		var opts = $(this).datagrid("options");
		if (!opts.url) {
			return false;
		}

		var successFn = function(data) {
			if (data.repCode != '000000') {
				console.log(data.repMsg);
				$.messager.show({
					title : '提示',
					msg : '数据加载异常!'
				});
			}

			if (data.data) {
				var data = data.data;
				_7c6(data);
			} else {
				sucFn({
					"total" : 0,
					"rows" : []
				});
			}

		}
		var errorFn = function() {
			_7c7.apply(this, arguments);
		};
		channel.ajax(opts.url, _7c5, opts.method, null, successFn, errorFn);
	}

});
