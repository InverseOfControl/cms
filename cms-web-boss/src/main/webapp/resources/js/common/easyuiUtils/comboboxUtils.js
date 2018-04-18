/**
 * 扩展EasyUI Combobox组件
 */

/**
 * 多选，含勾选框。
 */
function initMultCombobox(eleId,url,valueField,textField,editable) {
	// 下拉框:渠道名称
	$('#'+eleId).combobox({
		url:url,
		valueField : valueField,
		textField : textField,
		editable : editable,
		multiple:true,
		filter : function(q, row) {
			if(q==""){
				return false;
			}
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		formatter : function(row) {
			var valueField = $(this).combobox("options").valueField;
			var textField = $(this).combobox("options").textField;
			var rowShow;

			if (row.selected == true) {
				rowShow = "<input type='checkbox' style='height:14px; vertical-align:bottom;' checked='checked' selectName='"+eleId+"' id='"+eleId
						+ row[valueField] + "' value='" + row[valueField] + "'>" + row[textField]
						+ "</input>";
			} else {
				rowShow = "<input type='checkbox' style='height:14px; vertical-align:bottom;' selectName='"+eleId+"' id='"+eleId + row[valueField]
						+ "' value='" + row[valueField] + "'>" + row[textField]
						+ "</input>";
			}
			return rowShow;
		},
		onSelect : function(row) {
			if(row){
				var valueField = $(this).combobox("options").valueField;
				oCheckbox = document.getElementById(eleId+row[valueField]);
				oCheckbox.checked = true;
				
				//单选一个，支持模糊匹配
				var selectEles=$("input[selectName='"+eleId+"']:checked");
				if(selectEles && selectEles.length == 1){
					var selectEle = selectEles[0];
					$(this).combobox("clear");
					$(this).combobox("setValue",selectEle.value);
				}
			}
		},
		onUnselect : function(row) {
			if(row){
				var valueField = $(this).combobox("options").valueField;
				oCheckbox = document.getElementById(eleId+row[valueField]);
				oCheckbox.checked = false;
				
				//单选一个，支持模糊匹配
				var selectEles=$("input[selectName='"+eleId+"']:checked");
				if(selectEles && selectEles.length == 1){
					var selectEle = selectEles[0];
					$(this).combobox("clear");
					$(this).combobox("setValue",selectEle.value);
				}
			}
		},
		onHidePanel : function() {
			//输入框值是否与下拉框值匹配检验
			debugger;
			
			var aa = $(this).combobox("getText");

			var valueField = $(this).combobox("options").valueField;
			var vals = $(this).combobox("getValues"); // 当前combobox的值
			var allData = $(this).combobox("getData"); // 获取combobox所有数据
			var result = true; // 为true说明输入的值在下拉框数据中不存在
			for (var j = 0; j < vals.length; j++) {
				var val = vals[j];
				var isbreak = false;
				for (var i = 0; i < allData.length; i++) {
					if (val == allData[i][valueField]) {
						result = false;
						break;
					}
					if (i == allData.length-1) {
						result = true;
						isbreak = true;  
						break;
					}
				}
				if (isbreak) {
					break;
				}
			}
			if (result) {
				//清空值和勾选框
				$(this).combobox("clear");
				$("input[selectName='"+eleId+"']").attr("checked", false);
			} else {
				//清空无用值
				$(this).combobox("clear");
				$(this).combobox("setValues",vals); // 当前combobox的值
				
				//清空未选勾选框
				$("input[selectName='"+eleId+"']").attr("checked", false);
				for (var j = 0; j < vals.length; j++) {
					var val = vals[j];
					oCheckbox = document.getElementById(eleId+val);
					oCheckbox.checked = true;
				}
			}
			
		}
	})
};