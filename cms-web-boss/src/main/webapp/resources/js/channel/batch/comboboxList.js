$(function() {
	//重写combobox
	$.extend($.fn.combobox.methods, {
		selectedIndex : function(jq, index) {
			if (!index) {
				index = 0;
			}
			$(jq).combobox(
					{
						onLoadSuccess : function() {
							var opt = $(jq).combobox('options');
							var data = $(jq).combobox('getData');

							for (var i = 0; i < data.length; i++) {
								if (i == index) {
									$(jq).combobox(
											'setValue',
											eval('data[index].'
													+ opt.valueField));
									break;
								}
							}
						}
					});
		}
	});
	//下拉框:渠道
	$('#appFrom_channelId').combobox({
		url : 'loanConfirm/findChannel',
		value : '00001',
		valueField : 'code',
		textField : 'name',
		filter : function(q, row) {
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;//将从头位置匹配改为任意匹配 
		},
		onHidePanel : function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue"); //当前combobox的值
			var allData = $(this).combobox("getData"); //获取combobox所有数据
			var result = true; //为true说明输入的值在下拉框数据中不存在
			for (var i = 0; i < allData.length; i++) {
				if (val == allData[i][valueField]) {
					result = false;
				}
			}
			if (result) {
				$(this).combobox("clear");
			}

		}
	})

});