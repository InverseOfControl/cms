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
	initMultCombobox("cha_pro_channelId","channeProduct/findChannel","id","name",true);

    $('#cha_pro_channelId').combobox({
	    onLoadSuccess : function(){
    		query_cha_pro_Info();
	    }
    });
    //下拉框:产品
	initMultCombobox("cha_pro_productId","channeProduct/findProduct","productId","name",true);
    
    //下拉框:产品期限
	initMultCombobox("cha_pro_auditLimit","productAuditLimit/listAuditLimit","auditLimit","auditLimitName",false);
   
	
});

function onChange(newValues,oldValues){
	var params="";
	for(var i=0;i<newValues.length;i++){
		if(i!=newValues.length-1){
			params=newValues[i]+""+","+params;
		}else{
			params=params+newValues[i];
		}
		
	}
	$.ajax({
		url : 'channeProduct/findProduct',
		type:'post',
		data:{"channels":params},
		success:function(data){
			$('#cha_pro_productId').combobox('clear');
			$('#cha_pro_productId').combobox('loadData',data);
		}
	});
}

function onChangePro(newValues,oldValues){
	var params="";
	for(var i=0;i<newValues.length;i++){
		if(i!=newValues.length-1){
			params=newValues[i]+""+","+params;
		}else{
			params=params+newValues[i];
		}
		
	}
	$.ajax({
		url : 'productAuditLimit/listAuditLimit',
		type:'post',
		data:{"productIds":params},
		success:function(data){
			$('#cha_pro_auditLimit').combobox('clear');
			$('#cha_pro_auditLimit').combobox('loadData',data);
		}
	});
}