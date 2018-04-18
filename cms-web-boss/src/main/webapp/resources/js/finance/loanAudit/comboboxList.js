$(function() {
	//重写combobox
	$.extend($.fn.combobox.methods, { 
        selectedIndex: function (jq, index) { 
            if (!index) { 
                index = 0; 
            } 
            $(jq).combobox({ 
                onLoadSuccess: function () { 
                    var opt = $(jq).combobox('options'); 
                    var data = $(jq).combobox('getData');

                    for (var i = 0; i < data.length; i++) { 
                        if (i == index) { 
                            $(jq).combobox('setValue', eval('data[index].' + opt.valueField)); 
                            break; 
                        } 
                    } 
                } 
            }); 
        } 
    });
    //下拉框:渠道
    $('#loanAudit_channelId').combobox({
    	url:'loanAudit/findChannel',    
  	    valueField:'code',    
  	    textField:'name',
     filter: function(q, row){
      var opts = $(this).combobox('options');
      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
      },
     onHidePanel: function() {
                var valueField = $(this).combobox("options").valueField;
                var val = $(this).combobox("getValue");  //当前combobox的值
                var allData = $(this).combobox("getData");   //获取combobox所有数据
                var result = true;      //为true说明输入的值在下拉框数据中不存在
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
    //下拉框:产品
    $('#loanAudit_productId').combobox({
    	url:'loanAudit/findProduct',    
  	    valueField:'productId',    
  	    textField:'name',
     filter: function(q, row){
      var opts = $(this).combobox('options');
      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
      },
     onHidePanel: function() {
                var valueField = $(this).combobox("options").valueField;
                var val = $(this).combobox("getValue");  //当前combobox的值
                var allData = $(this).combobox("getData");   //获取combobox所有数据
                var result = true;      //为true说明输入的值在下拉框数据中不存在
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
   
   //下拉框:网点名称
    $('#loanAudit_applyInputFlag').combobox({
    	url:'loanAudit/findApplyInputFlag',    
  	    valueField:'code',    
  	    textField:'value',
     filter: function(q, row){
      var opts = $(this).combobox('options');
      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
      },
     onHidePanel: function() {
                var valueField = $(this).combobox("options").valueField;
                var val = $(this).combobox("getValue");  //当前combobox的值
                var allData = $(this).combobox("getData");  //获取combobox所有数据
                var result = true;      //为true说明输入的值在下拉框数据中不存在
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
    
    //一级原因
 var  oneReason= $('#backAudit_oneReason').combobox({
    	url:'loanAudit/findOneReason',    
  	    valueField:'code',    
  	    textField:'reason',
	     filter: function(q, row){
	      var opts = $(this).combobox('options');
	      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
	      },
	      onHidePanel: function() {
	          var valueField = $(this).combobox("options").valueField;
	          var val = $(this).combobox("getValue");  //当前combobox的值
	          var allData = $(this).combobox("getData");  //获取combobox所有数据
	          var result = true;      //为true说明输入的值在下拉框数据中不存在
	          for (var i = 0; i < allData.length; i++) {
	              if (val == allData[i][valueField]) {
	                  result = false;
	              }
	          }
	          if (result) {
	              $(this).combobox("clear");
	          }
	      },
          onSelect: function (oneR) {  
              $.post('loanAudit/findTwoReason',{ "parentId": oneR.id }, function (data) {  
            	  if(data != null){
            		  twoReason.combobox("clear").combobox('loadData', data);
            	  }
              });  
          }   
    })
    
    
     //二级原因
    var twoReason= $('#backAudit_twoReason').combobox({
    	url:'loanAudit/findTwoReason' , 
  	    valueField:'code',    
  	    textField:'reason',
	     filter: function(q, row){
	      var opts = $(this).combobox('options');
	      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
	      },
	    onHidePanel: function() {
	        var valueField = $(this).combobox("options").valueField;
	        var val = $(this).combobox("getValue");  //当前combobox的值
	        var allData = $(this).combobox("getData");  //获取combobox所有数据
	        var result = true;      //为true说明输入的值在下拉框数据中不存在
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
    
   $('#loanAudit_orgAuditState').combobox({
    	url:'loanAudit/findOrgAuditState' , 
  	    valueField:'code',    
  	    textField:'value',
	     filter: function(q, row){
	      var opts = $(this).combobox('options');
	      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
	      },
	    onHidePanel: function() {
	        var valueField = $(this).combobox("options").valueField;
	        var val = $(this).combobox("getValue");  //当前combobox的值
	        var allData = $(this).combobox("getData");  //获取combobox所有数据
	        var result = true;      //为true说明输入的值在下拉框数据中不存在
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