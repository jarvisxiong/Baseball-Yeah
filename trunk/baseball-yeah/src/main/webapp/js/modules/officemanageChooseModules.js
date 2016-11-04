define(['base'], function (base) {
	 
	 return {
         init: function (panel) {
             var self = this;
             panel=panel|| $('#indextab').tabs('getSelected');
             base.datagrid(
            		 {
                 url: 'office/assist/getStoreList',
                 method: 'post', singleSelect: false,
                 showExport: false,// 显示导出按钮
                 queryParams: function (params) {
                     return $.extend(params,
                         {
                    	 userId: $("#userId", panel).val(), 
                    	 phone: $("#phone", panel).val(),
                    	 storeName: $("#storeName", panel).val() 
                         });
                 },
                 onLoadSuccess: function (data) {
                     //表格控件不支持高度自适应
                     var tableHeight = 40 * data.total + 150;
                     if (data.total == 0) {//如果没有数据 给固定文字的高度
                         tableHeight = 120;
                     }
                     else if(data.total >= 10){
                         tableHeight = 550;
                     }
                     $("#acctTable", panel).bootstrapTable('resetView', {"height": tableHeight});
                 },
                 columns: [{ 
                     checkbox: true
                 }, {
                     field: 'storeName',     
                     title: '商户名称',
                     sortable: true
                 },  {
                     field: 'phone',
                     title: '商户手机',
                     sortable: true 
                 }, {
                     field: 'location',     
                     title: '详细地址',
                     sortable: true 
                 }, 
//                 {
//                     field: 'collegeName',
//                     title: '学校'   
//                 },  
                 {
                     field: 'pulicTime',     
                     title: '添加时间',
                     sortable: true 
                 }  ]
             }, '#acctTable', panel);
             base.datagrid(
            		 {
                 url: 'office/assist/getCooStoreList',
                 method: 'post', singleSelect: false,
                 showExport: false,
                 queryParams: function (params) {
                     return $.extend(params,
                         {
                    	 userId: $("#userId", panel).val()  
                         });
                 }, 
                 onLoadSuccess: function (data) {
                     //表格控件不支持高度自适应
                     var tableHeight = $('#storeTable', panel).find("thead").height() + $('#storeTable', panel).find("tbody").height()
                         + 30 + $('#storeTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                     if (data.total == 0) {//如果没有数据 给固定文字的高度
                         tableHeight = 120;
                     }
                     $('#storeTable', panel).bootstrapTable('resetView', {"height": tableHeight});

                 },
                 columns: [{ 
                     checkbox: true
                 }, {
                     field: 'storeName',     
                     title: '商户名称',
                     sortable: true 
                 },  {
                     field: 'phone',
                     title: '商户手机',
                     sortable: true 
                 }
//                 , {
//                     field: 'collegeName',
//                     title: '学校'   
//                 }  
                 ]
             }, '#storeTable', panel);
            
           
//             dataObject2.bootstrapTable('refresh', {query: {data: '{"acctId":"' + $("#acctId1").val() + '","startTime":"' + $("#startdate2", panel).val() + '","endTime":"' + $("#enddate2", panel).val() + '","payType":"' + $("#payType", panel).val() + '"}'}});
//             $("#btn_query_capital", panel).click(function () {
//                 dataObject2.bootstrapTable('refresh');
//             });  
             
             $("#btn_query", panel).click(function () {
                 $("#acctTable", panel).bootstrapTable('selectPage', 1);
             });
             $("#clearSearch", panel).click(function () {
                 base.reset(".main-box-header"); 
             }); 
             
             $("#btn_p_return", panel).click(function () {
            	 base.colseTab("选择商户");
             }); 
//             $.ajax({
//                 type: "POST",
//                 url: "/manage/college/getCollageForSel",
//                 dataType: "json",
//                 success: function (data) {
//                     $("#collegeId", panel).select2({
//                         data: data.data
//                     });
//                 }
//             });
             $("#btn_p_sure", panel).click(function () {
                 self.chooseStore(panel);
             }); 
             $("#btn_coo_del", panel).click(function () {
                 self.delStore(panel);
             });
         },
         chooseStore: function (panel) {
        	  var arrselections = $("#acctTable", panel)
              .bootstrapTable('getSelections');
        	  var ids = "";
	          if (arrselections.length >= 1) {
	        	  for(var i = 0;i<arrselections.length;i++){
	        			 if(i != 0){
	        				 ids += ","+arrselections[i].stoExpId;
	        			 }else{
	        				 ids += arrselections[i].stoExpId;
	        			 }
	        		 }
//	        	  alert(ids);
//	              base.error("只能选择一行进行编辑!");
//	              return;
	          }
	          if (arrselections.length <= 0) {
	              base.error("请选择有效数据!");
	              return;
	          }
//	          if (arrselections[0].state == 0) {
//	              base.error("该账户已被冻结!");
//	              return;
//	          }
	          base.cancel({text: "确认选择该商户？"}, function () {
	              $.post("/office/assist/addChooseStore", {stoExpId: ids, userId: $("#userId", panel).val()}, function (data) {
	                  if (data.success == 0) {
	                      base.success("新增成功");
	                      $("#acctTable", panel).bootstrapTable('selectPage', 1); 

	                      $("#storeTable", panel).bootstrapTable('refresh'); 
	                  }
	                  else {
	                      base.error(data.message);
	                  }
	              });
	          });
         },  
         delStore: function (panel) {
        	  var arrselections = $("#storeTable", panel)
              .bootstrapTable('getSelections');
        	  var ids = "";
	          if (arrselections.length > 0) {
//	              base.error("只能选择一行进行编辑!");
//	              return;
	        	  for(var i = 0;i<arrselections.length;i++){
	        			 if(i != 0){
	        				 ids += ","+arrselections[i].stoExpId;
	        			 }else{
	        				 ids += arrselections[i].stoExpId;
	        			 }
	        		 }
	          }
	          if (arrselections.length <= 0) {
	              base.error("请选择有效数据!");
	              return;
	          } 
	          
	          base.cancel({text: "确认删除该商户？"}, function () {
	              $.post("/office/assist/delChooseStore", {stoExpId: ids}, function (data) {
	                  if (data.success == 0) {
	                      base.success("删除成功");
	                      $("#acctTable", panel).bootstrapTable('selectPage', 1); 

	                      $("#storeTable", panel).bootstrapTable('selectPage', 1); 
	                  }
	                  else {
	                      base.error(data.message);
	                  }
	              });
	          });
         }             
         
     };
});