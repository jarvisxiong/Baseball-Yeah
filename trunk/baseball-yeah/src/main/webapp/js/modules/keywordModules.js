define(['base'], function (base) { 
	 return {
         init: function (panel) {
             var self = this;
             base.datagrid({
                 url: '/manage/keyword/query',
                 method: 'post', singleSelect: true,
                 showExport: false,// 显示导出按钮
                 queryParams: function (params) {
                     return $.extend(params,
                         {
                    	 wordContent: $("#wordContent", panel).val().trim(),
                    	 realName: $("#realName", panel).val().trim(),
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
                     field: 'wordContent',
                     title: '关键字',
                     sortable: true 
                 }, {
                     field: 'realName',
                     title: '创建人',
                     sortable: true 
                 }, {
                     field: 'createTime',
                     title: '创建时间',
                     sortable: true
                 }    
                 ]
             }, '#acctTable', panel); 
             $("#btn_query", panel).click(function () {
                 $("#acctTable", panel).bootstrapTable('selectPage', 1);
             });
             $("#clearSearch", panel).click(function () {
                 base.reset(".main-box-header");
                 $('#startDate', panel).val("");
                 $('#endDate', panel).val("");
                 $("#wordContent",panel).val(" ");
             }); 
             //开始时间
//             $('#starttimePicker', panel).datetimepicker({
//                 format: 'yyyy-mm-dd',
////                 format: 'yyyy-mm-dd hh:ii:ss',
//                 autoclose: true,
//                 pickTime: false,
//                 minView: 1,
//                 todayHighlight: true
//             });
             $('#starttimePicker', panel).datetimepicker({
               format: 'yyyy-mm-dd',
               autoclose: true,
               pickTime: false,
               minView: 2,
               todayHighlight: true,
               endDate: new Date()
             }).on('changeDate', function (e) {
               var startTime = e.date;
               $('#endtimePicker', panel).datetimepicker('setStartDate', startTime);
             }); 
             $('#endtimePicker', panel).datetimepicker({
                 format: 'yyyy-mm-dd',
                 autoclose: true,
                 pickTime: false,
                 minView: 2,
                 todayHighlight: true,
                 endDate: new Date()
               }).on('changeDate', function (e) {
                 var endTime = e.date;
                 $('#starttimePicker', panel).datetimepicker('setEndDate', endTime);
               });
//             //结束时间
//             $('#endtimePicker1', panel).datetimepicker({
//                 format: 'yyyy-mm-dd',
////                 format: 'yyyy-mm-dd hh:ii:ss',
//                 autoclose: true,
//                 pickTime: false,
//                 minView: 1,
//                 todayHighlight: true
//             });
//             $('#starttimePicker', panel).datetimepicker({
//                 format: 'yyyy-mm-dd',
//                 autoclose: true,
//                 pickTime: false,
//                 minView: 2
//             })
//             $('#starttimePicker', panel).datetimepicker({
//                 format: 'yyyy-mm-dd',
//                 autoclose: true,
//                 startView: 2,
//                 pickTime: false,
//                 minView: 1,
//                 todayHighlight: true,
//                 endDate: new Date()
//             }).on('changeDate', function (e) {
//                 var startTime = e.date;
//                 $('#endtimePicker', panel).datetimepicker('setStartDate', startTime);
//             });

             //结束时间
//             $('#endtimePicker', panel).datetimepicker({
//                 format: 'yyyy-mm-dd',
//                 autoclose: true,
//                 startView: 2,
//                 minView: 1,
//                 todayHighlight: true,
//                 endDate: new Date()
//             }).on('changeDate', function (e) {
//                 var endTime = e.date;
//                 $('#starttimePicker', panel).datetimepicker('setEndDate', endTime);
//             });
             
             $("#btn_add", panel).click(function () {
                 self.add(panel);
             });
             $("#btn_delete", panel).click(function () {
                 self.remove(panel);
             });
             $("#btn_dy", panel).click(function () {
                 self.dy(panel);
             });
             window.myCover = new Dropzone("#cover", {
                 init: function () {
                     this.on("removedfile", function (file) {
                         window.files.remove(file.fileid);
                     });
                 },
                 url: "/manage/keyword/uploadFile",
                 dictDefaultMessage: "将文件拖至此处或点击上传",
                 acceptedFiles: ".txt",
                 maxFiles: 1,
                 addRemoveLinks: true,
                 dictRemoveFile: "删除",
                 success: function (data, resultData) {
                     if (resultData.success > -1) {
//                         data.fileid = resultData.data.id;
//                         window.files.push(resultData.data.id);
//                         window.cover = resultData.data.id;
                    	 base.success(resultData.message);
                     } else {
                        base.error(resultData.message);
                     }
                 },
                 error: function () {
                     base.error("数据加载失败!");
                 }
             });             
             
         },
         add: function (panel) {
             var self = this;
             $('#addModal', panel).modal();
             var validate = {
                 fields: {
                	 addWordContent: {
                         validators: {
                             notEmpty: {
                                 message: '关键字不能为空'
                             },
                             stringLength: {
                                 min: 1,
                                 max: 30,
                                 message: '关键字过长'
                             }
                         }
                     } 
                 }
             };
             base.validator(validate, "#addForm", self.create, panel);
         },
         create: function (panel) { 
             $.ajax({
                 url: "/manage/keyword/add",
                 contentType: "application/json",
                 type: "POST",
                 data: JSON.stringify({
                     "addWordContent": $("#addWordContent", panel).val() 
                 }),
                 success: function (data) {
                     var obj = data;
                     if (obj.success == 0) {
                         $("#acctTable", panel).bootstrapTable('refresh');
                         $('#addModal', panel).modal('hide');
                         $('#addForm', panel).data('bootstrapValidator').resetForm(true);
//                         $("#acctTable", panel).bootstrapTable('selectPage', 1);
                         base.success("添加成功！");
                     } else {
                         base.error(obj.message);
                     }
                 },
                 error: function () {
                     base.error("数据加载失败!");
                 }
             });
         },
         remove: function (panel) {
             var arrselections = $("#acctTable", panel).bootstrapTable('getSelections');
             if (arrselections.length <= 0) {
                 base.error("请选择有效数据!");
                 return;
             }
             if (arrselections.length > 1) {
                 base.error("只能选择一行进行编辑!");
                 return;
             }
             var wordId = arrselections[0].wordId;
             base.cancel({title: "删除关键字", text: "您确定要删除此关键字吗？"}, function () {
                 $.post("/manage/keyword/del", {"wordId": wordId}, function (data) {
                     if (data.success == 0) {
                         base.success("删除成功");
                         $("#acctTable", panel).bootstrapTable('selectPage', 1);
                     } else {
                         base.error(data.message);
                     }
                 });
             });
         } 
     };
});