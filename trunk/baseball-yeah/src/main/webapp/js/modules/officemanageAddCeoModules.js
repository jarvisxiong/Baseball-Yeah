define(['base','packetOrderCountModules','acctFlowModules','packetOrderCommentModules'], function (base,packetOrderCount,acctFlow,packetOrderComment) {
    var packetOrderInit=function (){
        packetOrderCount.init();
    }
    var acctFlowInit=function (){
        acctFlow.init();
    }
    var packetOrderCommentInit=function (){
        packetOrderComment.init();
    }
	 return {
         init: function (panel) {
             var self = this;
             base.datagrid({
                 url: 'office/assist/query',
                 method: 'post', singleSelect: true,
                 showExport: false,// 显示导出按钮
                 queryParams: function (params) {
                     return $.extend(params,
                         {
                    	 flag: $("#flag", panel).val(), 
                    	 phone: $("#phone", panel).val().trim(),
                    	 realName: $("#realName", panel).val().trim(),
                    	 gender: $("#gender", panel).val(),
                         collegeId: $("#collegeId", panel).val() == null ? "" : $("#collegeId", panel).val().trim(),
//                         state: $("#state", panel).val() == null ? "" : $("#state", panel).val().trim(),
                         startDate: $("#startDate", panel).val(),
                         endDate: $("#endDate", panel).val()
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
                     field: 'phone',
                     title: '登录账号',
                     sortable: true,
                     formatter: function (value, row, index) {
                         return "<a href='#' class='showCrowdUserDetail' id=\'" + row.userId + "\'>" + value + "</a>";
                         // return "<a href='#' onclick='showAuditDetail(\"" + row.smsModelId + "\");'>" + value + "</a>";
                     },
                     events: {
                         'click .showCrowdUserDetail': function (e, value, row, index) {
                             self.showPieDetail(row.userId, panel, row);
                         }
                     }
                 }, {
                     field: 'pulicTime',
                     title: '注册时间',
                     sortable: true
                 }, {
                     field: 'realName',
                     title: '姓名',
                     sortable: true  
                 }, {
                     field: 'gender',
                     title: '性别',
                     sortable: true,  
                     formatter: function (value,row, index) {
			            var result = value;
			            if (value == "p_gender_male") {
			                result = "男";
			            } else if (value == "p_gender_female") {
			                result = "女";
			            } else if (value == "p_gender_secret") {
			                result = "保密";
			            } else{
			            	result = "-";
			            }
			            return result;
			        }
                 }, {
                     field: 'cityName',
                     title: '城市',
                     sortable: true 
                 }, {
                     field: 'collegeName',
                     title: '校区',
                     sortable: true,
                     formatter: function (value, row, index) {
                         if (value != '' && value != null) {
                             return '<a href="javaScript:void(0)" class="collegeDetail" name="'+row.collegeId+'">'+value+'</a>';
                         }
                     },
                     events: {
                         'click .collegeDetail': function (e, value, row, index) {
                             self.showCollegeDetail(row.collegeId, panel, row);
                         }
                     }
                 }, {
                     field: 'dormitoryAddress',
                     title: '宿舍',
                     sortable: true  
                 }, {
                     field: 'packetNum',
                     title: '订单量',
                     sortable: true,
                     formatter: function (value, row, index) {
                         if (value != '' && value != null) {
                             return '<a href="javaScript:void(0)" class="orderDetailList" name="'+row.userId+'" title="小派订单列表">'+value+'</a>';
                         }
                     },
                     events: {
                         'click .orderDetailList': function (e, value, row, index) {
                             var userId = row.userId;
                             var title = "订单总数";
                             var href = '/statistic/gotoOrderCount?packetUserId='+userId;
                             base.openTab(title,href,packetOrderInit);
                         }
                     }
                 }, {
                     field: 'favorableRate',
                     title: '好评率',
                     sortable: true ,
                     formatter: function (value,row, index) {
                         var result = value;
                         if (value <= 0) { 
                             result = "0";
                         }else {
                        	 result = '<a href="javaScript:void(0)" class="pieCommentDetail" title="">'+value + '%' +'</a>';
                         }
                         return result;
                     },
                     events: {
                         'click .pieCommentDetail': function (e, value, row, index) {
                             var userId = row.userId;
                             var title = "小派好评率";
                             var href = '/statistic/gotoComment?packetUserId='+userId;
                             base.openTab(title,href,packetOrderCommentInit);
                         }
                     }
                 }, {
                     field: 'balance',
                     title: '钱包余额',
                     sortable: true,
                     formatter: function (value, row, index) {
                         if(value!=null && value > 0){
                             return '<a href="javaScript:void(0)" class="pieAcctBalanceDetail" title="">'+value+'</a>';
                         }
                     },
                     events: {
                         'click .pieAcctBalanceDetail': function (e, value, row, index) {
                             var userId = row.userId;
                             var title = "小派钱包余额";
                             var href = '/statistic/gotoAcctFlow?packetUserId=' + userId;
                             base.openTab(title, href, acctFlowInit);
                         }
                     }
                 }, 
                 {
                     field: 'state',
                     title: '账户状态',
                     sortable: true,
                     formatter: function (value,row, index) {
                         var result = value;
                         if (value == "0") {//1激活,0未激活
                             result = "冻结";
                         }else if(value == "1"){
                        	 result = "正常";
                         }
                         return result;
                     }
                 }]
             }, '#acctTable', panel); 
             $("#btn_query", panel).click(function () {
                 $("#acctTable", panel).bootstrapTable('selectPage', 1);
             });
             $("#clearSearch", panel).click(function () {
                 base.reset(".main-box-header");
                 $('#startDate', panel).val("");
                 $('#endDate', panel).val("");
                 $("#collegeId",panel).val(" ").trigger("change");
             });
             $("#btn_back", panel).click(function () {
            	 var flag = $("#flag", panel).val();
            	 if(flag == 1) {
            		 base.colseTab("新增CEO");
            	 } else{
            		 base.colseTab("新增COO");	
            	 }                 
             });
             //开始时间
             $('#starttimePicker1', panel).datetimepicker({
                 format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 pickTime: false,
                 minView: 1,
                 todayHighlight: true
             });
             //结束时间
             $('#endtimePicker1', panel).datetimepicker({
                 format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 pickTime: false,
                 minView: 1,
                 todayHighlight: true
             });
             $.ajax({
                 type: "POST",
                 url: "/manage/college/getCollageForSel",
                 dataType: "json",
                 success: function (data) {
                     $("#collegeId", panel).select2({
                         data: data.data
                     });
                 }
             });
             $("#btn_add", panel).click(function () {
                 self.add(panel);
             });
         },
         add: function (panel) {
        	  var arrselections = $("#acctTable", panel)
              .bootstrapTable('getSelections');
	          if (arrselections.length > 1) {
	              base.error("只能选择一行进行编辑!");
	              return;
	          }
	          if (arrselections.length <= 0) {
	              base.error("请选择有效数据!");
	              return;
	          }
	          if (arrselections[0].state == 0) {
	              base.error("该账户已被冻结!");
	              return;
	          }
	          base.cancel({text: "确认新增该帐号？"}, function () {
	              $.post("/office/assist/add", {userId: arrselections[0].userId, flag: $("#flag", panel).val()}, function (data) {
	                  if (data.success == 0) {
	                      base.success("新增成功");
	                      $("#acctTable", panel)
	                          .bootstrapTable('refresh');
	                  }
	                  else {
	                      base.error(data.message);
	                  }
	              });
	          });
         },
         showPieDetail: function (userId, panel) {
             var self = this;
             $('#detailPhone', panel).html("");
             $('#detailUserState', panel).html("");
             $('#detailSource', panel).html("");
             $('#detailUserName', panel).html("");
             $('#detailSex', panel).html("");
             $('#detailCardNo', panel).html("");
             $('#detailWalletBalance', panel).html("");
             $('#detailOrderNum', panel).html("");
             $('#detailFavourable', panel).html("");
             $('#detailCityName', panel).html("");
             $('#detailCollegeName', panel).html("");
             $('#detailDorm', panel).html("");
             $('#detailAuditTime', panel).html("");
             $('#detailAuditState', panel).html("");
             $('#detailAuditRemark', panel).html("");
             $('#detailPath', panel).html("");
             $('#detailAccountImg',panel).html("");
             $('#divlabel1',panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg1'>点击查看 </strong>");
             $('#divlabel2',panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg2'>点击查看 </strong>");

             $.post("/crowdUser/queryCrowdUserDetail", {
                 "userId": userId
             }, function (data, status) {
                 if (status == "success") {
                     $('#detailPhone', panel).html(data.phone);
                     if(data.userState == '1'){
                         $('#detailUserState', panel).html("启用");
                     }else if(data.userState == '0'){
                         $('#detailUserState', panel).html("禁用");
                     }
                     $('#detailSource', panel).html(data.userState);
                     $('#detailUserName', panel).html(data.userName);
                     $('#detailSex', panel).html(data.sex);
                     $('#detailCardNo', panel).html(data.cardNo);
                     $('#detailWalletBalance', panel).html(data.walletBalance);
                     $('#detailOrderNum', panel).html(data.orderNum);
                     if(data.favourable!=null){
                         $('#detailFavourable', panel).html(data.favourable+"%");
                     }
                     $('#detailCityName', panel).html(data.cityName);
                     $('#detailCollegeName', panel).html(data.collegeName);
                     $('#detailDorm', panel).html(data.dorm);
                     var auditDate = data.auditTime;
                     if( auditDate != null && auditDate.length > 19){
                         $('#detailAuditTime', panel).html(auditDate.substr(0,auditDate.length-2));
                     }
                     var auditState = data.auditState;
                     if(auditState!=null){
                         if(auditState == '0'){
                             $('#detailAuditState', panel).html("未提交");
                         }else if(auditState == '1'){
                             $('#detailAuditState', panel).html("已提交");
                         }else if(auditState == '2'){
                             $('#detailAuditState', panel).html("审核通过");
                         }else if(auditState == '3'){
                             $('#detailAuditState', panel).html("审核失败");
                         }
                     }

                     $('#detailAuditRemark', panel).html(data.auditRemark);
                     $('#detailPath', panel).html(data.detailPath);

                     if (data.photoList != null) {
                         for (var i = 0; i < data.photoList.length; i++) {
                             if (data.photoList[i].fileUrl.indexOf("handheld") > 0) {
                                 (function () {
                                     var url = data.photoList[i].fileUrl;
                                     $("#labelimg1",panel).unbind("click");
                                     $("#labelimg1",panel).click(function () {
                                         self.dialogImg("手持身份证正面照片", url)
                                     });
                                 })();
                             }else if (data.photoList[i].fileUrl.indexOf("idcard") > 0) {
                                 (function () {
                                     var url = data.photoList[i].fileUrl;
                                     $("#labelimg2",panel).unbind("click");
                                     $("#labelimg2",panel).click(function () {
                                         self.dialogImg("身份证正面照片", url);
                                     });
                                 })();
                             }else{
                                 $('#detailAccountImg',panel).html("<img width='140px' height='120px' src='" + data.photoList[i].fileUrl + "'/>");
                                 // $('#detailAccountImg',panel).html("<img src='" + data.photoList[i].nailFileUrl + "'/>");
                                 // $('#detailAccountImg',panel).attr("src",data.photoList[i].nailFileUrl);
                             }
                         }
                     }

                 } else {
                     base.error("初始化失败!");
                 }
             });
             $('#smallPieDetailModal', panel).modal();
         },
         dialogImg: function (title, url) {
             BootstrapDialog.show({
                 cssClass: 'img-dialog',
                 title: title,
                 message: '<div style="text-align: center;"><img src="' + url + '" style="width: 550px;height: 500px;"></div>'
             });
         },
         showCollegeDetail: function (collegeId, panel) {
             var self = this;
             $('#schCollegeCode', panel).html("");
             $('#schFullName', panel).html("");
             $('#schNature', panel).html("");
             $('#schCollegeType', panel).html("");
             $('#schProvinceName', panel).html("");
             $('#schCityName', panel).html("");
             $('#schCountyName', panel).html("");
             $('#schCeoName', panel).html("");
             $('#schCollegeRemark', panel).html("");

             $.post("/crowdUser/queryCollegeDetail", {
                 "collegeId": collegeId
             }, function (data, status) {
                 if (status == "success") {
                     $('#schCollegeCode', panel).html(data.collegeId);
                     $('#schFullName', panel).html(data.fullName);
                     if(data.nature!=null && data.nature == 'p_nature_private'){
                         $('#schNature', panel).html("民办");
                     }else if(data.nature!=null && data.nature == 'p_nature_public'){
                         $('#schNature', panel).html("公办");
                     }
                     if(data.collegeType!=null && data.collegeType == 'p_college_type_speci'){
                         $('#schCollegeType', panel).html("专科院校");
                     }else if(data.collegeType!=null && data.collegeType == 'p_college_type_under'){
                         $('#schCollegeType', panel).html("本科院校");
                     }
                     $('#schProvinceName', panel).html(data.provinceName);
                     $('#schCityName', panel).html(data.cityName);
                     $('#schCountyName', panel).html(data.countyName);
                     $('#schCeoName', panel).html(data.ceoName);
                     $('#schCollegeRemark', panel).html(data.collegeRemark);

                 } else {
                     base.error("初始化失败!");
                 }
             });
             $('#collegePieDetailModal', panel).modal();
         }

     };
});