$(function() {
	//新增页面默认关闭
	initDatagrid1();
	$("#updateBMSUrgentConfigInfo").window({inline:true});
	initcombobox();
	
		//控制日期控件格式为年月
        var db = $('#limitDate');
        db.datebox({
            onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                span.trigger('click'); //触发click事件弹出月份层
                //fix 1.3.x不选择日期点击其他地方隐藏在弹出日期框显示日期面板
                if (p.find('div.calendar-menu').is(':hidden')) p.find('div.calendar-menu').show();
                p.find('.datebox-button').find('.datebox-current').hide();  //隐藏今天按钮
                if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                    tds = p.find('div.calendar-menu-month-inner td');
                    tds.click(function (e) {
                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                        var year = /\d{4}/.exec(span.html())[0];//得到年份
                         month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                         var month_b='0'+month;
                         month = month.toString().length==1?month_b:month;
                        db.datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month); //设置日期的值
                    });
                }, 0);
                yearIpt.unbind();//解绑年份输入框中任何事件
            },
            parser: function (s) {
                if (!s) return new Date();
                var arr = s.split('-');
                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
            },
            formatter: function (d) { 
            	return d.getFullYear() + '-' + ((d.getMonth() + 1)<10?'0'+(d.getMonth()+ 1):(d.getMonth()+ 1));/*getMonth返回的是0开始的，忘记了。。已修正*/ 
            	}
        });
        var p = db.datebox('panel'), //日期选择对象
            tds = false, //日期选择对象中月份
            aToday = p.find('a.datebox-current'),
            yearIpt = p.find('input.calendar-menu-year'),//年份输入框
            //显示月份层的触发控件
            span = aToday.length ? p.find('div.calendar-title span') ://1.3.x版本
            p.find('span.calendar-text'); //1.4.x版本
        if (aToday.length) {//1.3.x版本，取消Today按钮的click事件，重新绑定新事件设置日期框为今天，防止弹出日期选择面板
           
            aToday.unbind('click').click(function () {
                var now=new Date();
                db.datebox('hidePanel').datebox('setValue', now.getFullYear() + '-' + (now.getMonth() + 1));
            });
        }


});

$(window).keydown(function(event) {
    if(event.keyCode == 13) {
    	queryBMSOrg();
    }
});

function initDatagrid1() {
		$("#new_datagrid_Org").datagrid({
			onLoadSuccess:function(data){ 
				  if(data.total==0)
				  {
				    $.messager.show({
		                title:'结果',
		                msg:'没查到符合条件的数据！',
		                showType:'slide',
		            });
				  };
		     },
			url : 'urgentLimit/listPage',
			striped : true,
			singleSelect : true,
			rownumbers : true,
			pagination : true,
			fitColumns : false,
			//pageList : [ 10, 20, 50 ],
			scrollbarSize : 0,
			columns : [ [{
				field : 'id',
				title : 'id',
				checkbox : true,
				width : 30,
				hidden:true
			}, 
		  {
				field : 'orgId',
				title : '营业部ID',
				width : 50,
				hidden:true
			}, {
				field : 'orgName',
				title : '网点名称',
				width : 200,
			},
			{
				field : 'limitTime',
				title : '时间',
				width : 100
			},
			{
				field : 'urgentCount',
				title : '当月可加急件',
				width : 100
			},
			{
				field : 'overCount',
				title : '当月剩余加急件',
				width : 100
			}
			,{
				field : 'operation',
				title : '操作',
				formatter : formatOperations1,
				width : 250
			}
			] ]
		});
}

function initcombobox(){
	$('#orgId').combobox({
		url:'urgentLimit/findOrgs',
		valueField : 'id',
		textField : 'name',
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
}



function yearFormatter(date){  
	var y = date.getFullYear();  
    var m = date.getMonth()+1;  
   // var d = date.getDate();  
    return y+'-'+(m<10?('0'+m):m);
};  
  
function yearParser(s){ 
    if (!s) return new Date();  
    var y = s;  
    var date;  
    if (!isNaN(y)){  
        return new Date(y,0,1);  
    } else { 
    	//alert(new Date(y));
        return new Date(y);  
    }  
}; 





function queryBMSOrg() {
	debugger;
	var orgId = $('#orgId').combobox('getValue');
	var limitDate=$('#limitDate').datebox('getValue');	
	//查询
	var queryParams = $('#new_datagrid_Org').datagrid('options').queryParams;
	queryParams.orgId = orgId;
	queryParams.limitDate = limitDate;
	setFirstPage("#new_datagrid_Org");
	$('#new_datagrid_Org').datagrid('options').queryParams = queryParams;
	$("#new_datagrid_Org").datagrid('reload');
}
function setFirstPage(ids){
    var opts = $(ids).datagrid('options');
    var pager = $(ids).datagrid('getPager');
    opts.pageNumber = 1;
    opts.pageSize = opts.pageSize;
    pager.pagination('refresh',{
	pageNumber:1,
	pageSize:opts.pageSize
    });
}



//关闭表单
function closeForm() {
	$('#updateBMSUrgentConfigInfo').window('close');
	initDatagrid1();
}



function formatOperations1(value,row,index) {
	var operations = '';
	var orgId=row.orgId;
	var orgName=row.orgName;
	var limitTime=row.limitTime;
	var urgentCount=row.urgentCount;
	operations += '<a href="javascript:void(0)" onclick="loadUpdateOrgToWindow('+urgentCount+',\''+limitTime+'\','+orgId+',\''+orgName+'\')">修改 &nbsp;&nbsp;</a>';
    return operations;
 
 };


//编辑初始化
function loadUpdateOrgToWindow(urgentCount,limitTime,orgId,orgName) {
	var date=new Date();
	var y = date.getFullYear();  
    var m = date.getMonth()+1; 
    var arr=limitTime.split("-");
    var arr1=arr[0];
    var arr2=arr[1];
    arr1=parseInt(arr1);
    arr2=parseInt(arr2);
    if(arr1<y){
    	$.messager.show({
			title : '提示',
			msg : y+'-'+m+'以前的数据不可编辑'
		});
	    return;
    }else{
    	if(arr2<m){
    		$.messager.show({
    			title : '提示',
    			msg : y+'-'+m+'以前的数据不可编辑'
    		});
    	    return;
    	}
    }
    
	 $('#updateBMSUrgentConfigInfo').window({
			modal:true,
			closed:false,
		});
		$('#updateBMSUrgentConfigInfoForm').form('clear');
		//stuffUpdateBankPage(result.info);
		
		$('#countSize').textbox('setValue',urgentCount);
		$('#updateorgId').textbox('setValue',orgId);
		$('#updateorgName').textbox('setValue',orgName);
		$('#updateorglimitTime').textbox('setValue',limitTime);
//	 $.ajax({
//         url : 'bank/updateBankInit',
//         data : {id:id},
//         type:"POST",
//         success : function(result){
//        	 $('#updateBMSUrgentConfigInfo').window({
//        			modal:true,
//        			closed:false,
//        		});
//        		$('#updateBMSBankInfo').form('clear');
//         		stuffUpdateBankPage(result.info);
//         },
//         error:function(data){
//        	 $.messager.show({
//					title: 'warning',
//					msg: data.responseText
//				});
//         }
//	 });
}
//编辑填充
//function stuffUpdateBankPage(result) {
//	$('#updateBMSBankInfo').find('input[name="id"]').val(result.id);
//	$('#updateBMSBankInfo #name').textbox('setValue',result.name);
//	$('#updateBMSBankInfo #code').textbox('setValue',result.code).textbox({ disabled: true });
//	if (result.isDisabled == 0) {
//		$('#updateBMSUrgentConfigInfo input[name="editredio"]').get(0).checked = true;
//	} else {
//		$('#updateBMSUrgentConfigInfo input[name="editredio"]').get(1).checked = true;
//	}
//	$('#oldBankCode').val(result.code);
// }

//修改渠道信息
function updateBMSUrgentConfigInfo() {
	//必填校验
	var countSize=$('#countSize').textbox('getValue');
	if(countSize==null||countSize==''){
		$.messager.show({
			title : '提示',
			msg : '请输入数量'
		});
	    return;
	}
	if(countSize.length>5){
		$.messager.show({
			title : '提示',
			msg : '配置数量输入过长'
		});
	    return;
	}
	var regu =/^[0-9]*$/;
	if (!regu.test(countSize)) {
			$.messager.show({
				title : '提示',
				msg : '配置数量请填写数字'
			});
		    return;
	  }
	
	var updateorgId=$('#updateorgId').textbox('getValue');
	var updateorgName=$('#updateorgName').textbox('getValue');
	var updateorglimitTime=$('#updateorglimitTime').textbox('getValue');
	
	  $.ajax({
		   url : 'urgentLimit/updateOrg',
		   data : {
			   		'urgentCount' : countSize,
			   		'orgId' : updateorgId,
			   		'orgName' : updateorgName,
			   		'limitDate' :updateorglimitTime
			    },
		   type:"POST",
		   async:false,  // 设置同步方式
	       cache:false,
		   success : function(result){
		   		if(result.isSuccess){
		   			initDatagrid1();
		   			$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
		   			
		   		 $('#updateBMSUrgentConfigInfo').window({
	        			modal:true,
	        			closed:true,
	        		});
		   			$('#updateBMSUrgentConfigInfoForm').form('clear');
		   			
		   		}else{
		   			parent.$.messager.show({
						title: 'Error',
						msg: result
					});
		   		}
		   },
		   error:function(data){
		 		 $.messager.show({
						title: 'warning',
						msg: data.responseText
					});
		   }
	});
}
