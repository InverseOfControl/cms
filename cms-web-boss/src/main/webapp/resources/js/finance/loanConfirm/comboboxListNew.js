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
    $('#loanConfirm_channelId').combobox({
    	url:'loanConfirm/findChannel', 
    	value:'00001',
  	    valueField:'code',    
  	    textField:'name',
     filter: function(q, row){
      var opts = $(this).combobox('options');
      return row[opts.textField].indexOf(q)>-1;//将从头位置匹配改为任意匹配 
      },
     onSelect: function(data){    
	    hideOrShowByChannel();
	 },
	 onLoadSuccess: function(data){
		$("#uploadLoanFile").hide();//外贸信托导入回盘文件
		$("#uploadFileOne").hide();//龙信小贷导入放款文件
		$(".noClassPaymentStatus").hide();//报盘状态
		$("#resetBtn").hide();//重置按钮
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
    $('#loanConfirm_productId').combobox({
    	url:'loanConfirm/findProduct',    
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
      /*          if (result) {
                    $(this).combobox("clear");
                }*/

            }
    })
   
    //下拉框:网点名称
/*    $('#loanConfirm_org').combobox({
    	url:'loanConfirm/findBusinessProduct',    
  	    valueField:'id',    
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
    })*/
       //一级原因
 var  oneReason= $('#backLoan_oneReason').combobox({
    	url:'loanAudit/findOneReason',    
  	    valueField:'id',    
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
              $.get('loanAudit/findTwoReason',{ "parentId": oneR.id }, function (data) {  
            	  twoReason.combobox("clear").combobox('loadData', data);  
              }, 'json');  
          }   
    })
    
    
     //二级原因
    var twoReason= $('#backLoan_twoReason').combobox({
    	url:'loanAudit/findTwoReason' , 
  	    valueField:'id',    
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
  //按照渠道控制
    function hideOrShowByChannel(){
    	var channelCode = $('#loanConfirm_channelId').combobox('getValue'); 
    	 if(window.gmxtChannelCode == channelCode){//国民信托
    		 //下载按钮
    		 $("#bacthPassConfirmBtn").hide();
    		 $("#sercBtnOne").show();
    		 $("#sercBtnTwo").show();
    		 $("#sercBtnThree").show();
    		 $("#uploadLoanFile").hide();
    		 $("#uploadFileOne").hide();
    		 $("#resetBtn").show();//重置按钮
    		 //搜索按钮
    		 $("#bacthPassConfirmBtn").hide();
    		 $(".noClassLoanNo").hide();
    		 $(".noClassName").show();
    		 $(".noClassIdNo").show();
    		 $(".noClassBatchNum").show();
    		 $(".noClassProductId").hide();
    		 $(".noClassOrgId").hide();
    		 $(".noClassSignDate").hide();
    		 $(".noClassPaymentStatus").show();
    	 }else if(window.hmxdChannelCode == channelCode){//海门小贷
    		 //下载按钮
    		 $("#sercBtnOne").hide();
    		 $("#sercBtnTwo").hide();
    		 $("#sercBtnThree").hide();
    		 $("#uploadLoanFile").hide();
    		 $("#uploadFileOne").hide();
    		 $("#resetBtn").hide();//重置按钮
    		 //搜索按钮
    		 $("#bacthPassConfirmBtn").show();
    		 $(".noClassLoanNo").show();
    		 $(".noClassName").show();
    		 $(".noClassIdNo").show();
    		 $(".noClassBatchNum").show();
    		 $(".noClassProductId").show();
    		 $(".noClassOrgId").show();
    		 $(".noClassSignDate").show();
    		 $(".noClassPaymentStatus").hide();
    	 }else if(window.lxxdChannelCode == channelCode){//龙信小贷
    		 //下载按钮
    		 $("#sercBtnOne").hide();
    		 $("#sercBtnTwo").hide();
    		 $("#sercBtnThree").hide();
    		 $("#uploadLoanFile").hide();
    		 $("#uploadFileOne").show();
    		 $("#resetBtn").hide();//重置按钮
    		 //搜索按钮
    		 $("#bacthPassConfirmBtn").hide();
    		 $(".noClassLoanNo").hide();
    		 $(".noClassName").show();
    		 $(".noClassIdNo").show();
    		 $(".noClassBatchNum").hide();
    		 $(".noClassProductId").hide();
    		 $(".noClassOrgId").hide();
    		 $(".noClassSignDate").hide();
    		 $(".noClassPaymentStatus").hide();
    		 
    	 }else if(window.wmxtChannelCode == channelCode){
    		 
    		 $("#sercBtnOne").hide();
    		 $("#sercBtnTwo").hide();
    		 $("#sercBtnThree").hide();
    		 $("#uploadFileOne").hide();
    		 $("#uploadLoanFile").show();
    		 $("#resetBtn").hide();//重置按钮
    		 //搜索按钮
    		 $("#bacthPassConfirmBtn").hide();
    		 $(".noClassLoanNo").hide();
    		 $(".noClassName").show();
    		 $(".noClassIdNo").show();
    		 $(".noClassBatchNum").hide();
    		 $(".noClassProductId").hide();
    		 $(".noClassOrgId").hide();
    		 $(".noClassSignDate").hide();
    		 $(".noClassPaymentStatus").hide();
    	 }
    	 else{
    		 //下载按钮
    		 $("#sercBtnOne").show();
    		 $("#sercBtnTwo").show();
    		 $("#sercBtnThree").show();
    		 $("#uploadLoanFile").hide();
    		 $("#uploadFileOne").hide();
    		 $("#resetBtn").hide();//重置按钮
    		 //搜索按钮
    		 $("#bacthPassConfirmBtn").show();
    		 $(".noClassLoanNo").show();
    		 $(".noClassName").show();
    		 $(".noClassIdNo").show();
    		 $(".noClassBatchNum").show();
    		 $(".noClassProductId").show();
    		 $(".noClassOrgId").show();
    		 $(".noClassSignDate").show();
    		 $(".noClassPaymentStatus").hide();
    	 }
    }
    

	// 下拉框:报盘状态
	$('#paymentStatus').combobox({
		url : 'loanConfirm/queryPaymentStatus',
		valueField : 'code',
		textField : 'desc',
		filter : function(q, row) {
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;// 将从头位置匹配改为任意匹配
		},
		onHidePanel : function() {
			var valueField = $(this).combobox("options").valueField;
			var val = $(this).combobox("getValue"); // 当前combobox的值
			var allData = $(this).combobox("getData"); // 获取combobox所有数据
			var result = true; // 为true说明输入的值在下拉框数据中不存在
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
