define(['base'], function (base) {
    /**
       * 私有成员定义区域
       */

    return {
        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            base.datagrid({
                url: '/manage/sysnotice/selectall',
            	method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                            {
                    	caption: $(
                                        "#caption")
                                        .val()
                            });
                },
                singleSelect: false,
                columns: [
                          
                                                                           {
                                                                               checkbox: true
                                                                           },
                                                                           {
                                                                               field: 'caption',
                                                                               title: '标题',
                                                                               sortable: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                               field: 'url',
                                                                               title: '网址',
                                                                               sortable: true,
                                                                               width:600
                                                                               
                                                                           },
                                                                           {
                                                                               field: 'bePush',
                                                                               title: '是否推送',
                                                                               formatter: function (value,
                                                                                       row, index) {
                                                   			                   return value == "1" ? "是" : "否";
                                                   							},
                                                                               sortable: true,
                                                                               width:200
                                                                           },
                                                                           {
                                                                               field: 'publishTime',
                                                                               title: '发布日期',
                                                                               sortable: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                               field: 'content',
                                                                               title: '内容',
                                                                               sortable: true,
                                                                               width:800
                                                                           },
                                                                           {
                                                                               field: 'pushType',
                                                                               title: '用户类型',
                                                                               formatter: function (value,row, index) {
                                                                            	   return value == "axp" ? "货源" : "众包";
                                                   								},
                                                                               sortable: true,
                                                                               width:200
                                                                           },
                                                                           {
                                                                        	   field: 'sysNoticeId',
                                                                               title: '编号',
                                                                               visible: false
                                                                           }]
            }, '#userTable');

            $.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {

                    $("#selstore").select2({
                        data: data
                    });
                    $("#add_store").select2({
                        data: data
                    });
                    $("#edit_store").select2({
                        data: data
                    });
                }
            });
            
            $("#add_pushTypes,#edit_userTypes").select2({
    			placeholder : '选择用户类型',
    			allowClear : true
    		});

            $("#btn_add").click(function () {
                self.add();
            });
            $("#btn_edit").click(function () {
                $("#edit_publishTime").datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:mm'
                });
            });
            $("#btn_edit").click(function () {
   
                self.edit();
            });
            $("#btn_audit").click(function () {
                self.audit();
            });
            $("#btn_delete").click(function () {
                self.remove();
            });
            $("#btn_query").click(function () {
                $("#userTable").bootstrapTable('refresh');
            });
            $('#addModal').on('shown.bs.modal', function () {
            	$("#add_pushTypes").val("").trigger("change");
                $('#addForm').data('bootstrapValidator').resetForm(true);
            });
 
            $("#btn_add").click(function () {
                $("#add_publishTime").datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:mm'
                });
                });
        },
        add: function () {
            var self = this;
            $('#addModal').modal({
			    keyboard: false,
			    backdrop:'static'
			});
            base.validator({
                    fields: {
                    	add_caption: {
                            validators: {
                                notEmpty: {
                                    message: '标题不能为空'
                                }
                            }
                        },
                        add_content: {
                            validators: {
                                notEmpty: {
                                    message: '内容不能为空'
                                }
                            }
                        },
                        add_url: {
                            validators: {
                                notEmpty: {
                                    message: 'URL不能为空'
                                }
                            }
                        },
                        add_publishTime: {
                            validators: {
                                notEmpty: {
                                    message: '提交时间不能为空'
                                }
                            }
                        },
                        add_pushTypes: {
                            validators: {
                                notEmpty: {
                                    message: '推送类型不能为空'
                                }
                            }
                        }
                    }
                }, "#addForm", self.create);
        },
        create: function () {
            $.post("/manage/sysnotice/addsysnotice",
                           {
                               "caption": $("#add_caption").val(),
                               "url": $("#add_url").val(),
                               "publishTime": $("#add_publishTime").val(),
                               "userId": $("#add_userId").val(),
                               "beDeleted": $("#addDeleted").prop('checked') ? "1" : "0",
                                "beDeleted": $("#addNoDeleted").prop('checked') ? "0" : "1",
                               "content": $("#add_content").val(),
                               "pushTypeList":$("#add_pushTypes").val().join()
                           },
                           function (data, status) {
                               if (status == "success") {
                                   if (data.success == 0) {
                                	   base.success("添加成功！");
                                     	$("#userTable").bootstrapTable('refresh');
                                     	  $("#addModal").modal('hide');
                                   } else {
                                       base.error(data.message);
                                   }
                               } else {
                                   base.error("数据加载失败!");
                               }
                           });
        },
        edit: function () {
            var self = this;

            var arrselections = $("#userTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...",
                    "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!",
                    "error");
                return;
            }
            $('#editModal').modal({
			    keyboard: false,
			    backdrop:'static'
			});
            $("#edit_caption").val(
                arrselections[0].caption);
            $("#sysNoticeId").val(
                    arrselections[0].sysNoticeId);
            $("#edit_url").val(
                arrselections[0].url);
            $("#edit_publishTime").val(
                    arrselections[0].publishTime);
                $("#edit_userId").val(
                    arrselections[0].userId);
                $("#edit_content").val(
                        arrselections[0].content);
            $("#editPush").prop("checked",arrselections[0].bePush == "1" ? true: false);
            $("#editNoPush")
                .prop("checked", arrselections[0].bePush == "0" ? true: false);
            $("#editDeleted").prop("checked",arrselections[0].bePush == "1" ? true: false);
            $("#editNoDeleted")
                .prop("checked", arrselections[0].bePush == "0" ? true: false);
            //绑定下拉框
			if(arrselections[0].pushTypeList){
				var pushTypeArray = arrselections[0].pushTypeList.split(",");
				$("#edit_pushTypes").val(pushTypeArray).trigger("change");				
			} else {
				$("#edit_pushTypes").val("").trigger("change");
			}
            
            $('#editForm')
                .bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                    	edit_content: {
                            validators: {
                                notEmpty: {
                                    message: '内容不能为空'
                                }
                            }
                        },
                        edit_caption: {
                            validators: {
                                notEmpty: {
                                    message: '用户名不能为空'
                                }
                            }
                        },
                    	
                        edit_url: {
                            validators: {
                                notEmpty: {
                                    message: '标题不能为空'
                                }
                            }
                        },
                        edit_publishTime: {
                            validators: {
                                notEmpty: {
                                    message: '内容不能为空'
                                }
                            }
                        },
                        edit_userId: {
                            validators: {
                                notEmpty: {
                                    message: '标题不能为空'
                                }
                            }
                        },
                        edit_userTypes: {
                            validators: {
                                notEmpty: {
                                    message: '用户类型不能为空'
                                }
                            }
                        }               
                    }
                })
                .on('success.form.bv', function (e) {
                    // Prevent form submission
                    e.preventDefault();
                    self.update();

                });
            $('#editModal').modal();
        },
        update: function () {
            $.post(
                      "/manage/sysnotice/updatesysnotice",
                      {        
                    	  "sysNoticeId": $("#sysNoticeId").val(),
                          "caption": $("#edit_caption").val(),
                          "url": $("#edit_url").val(),
                          "bePush": $("#editPush").prop('checked') ? "1" : "0",
                          "bePush": $("#editNoPush").prop('checked') ? "0" : "1",
                          "publishTime": $("#edit_publishTime").val(),
                          "userId": $("#edit_userId").val(),
                          "beDeleted": $("#editDeleted").prop('checked') ? "1" : "0",
                          "beDeleted": $("#editNoDeleted").prop('checked') ? "0" : "1",
                          "content": $("#edit_content").val(),
                          "pushTypeList":$("#edit_pushTypes").val()
                      },
                      function (data, status) {
                          if (status == "success") {
                    
                              if (data.success == 0) {
                            	  base.success("更新成功！");
                              	$("#userTable").bootstrapTable('refresh');
                                $("#editModal").modal('hide');
                                $("#editForm").data("bootstrapValidator").resetForm(true);
                              } else {
                                  base.error(data.message);
                              }
                          } else {
                              base.error("更新失败!");
                          }
                      });
        },
        remove: function () {
            var arrselections = $("#userTable").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var userInfos=[];
            for (var i = 0; i < arrselections.length; i++) {
                userInfos.push(arrselections[i].sysNoticeId);
            }

            base.cancel({ title: "删除", text: "您确定要删除吗？" }, function () {
                $.post("/manage/sysnotice/deletesysnotice", {
                    "sysNoticeIds": userInfos.join(',')
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                        	 base.success("删除成功！");
                        	$("#userTable").bootstrapTable('refresh');
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("删除失败");
                    }
                });
            });
        },
        audit : function () {
        	var arrselections = $("#userTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...",
                    "只能选择一行审核!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!",
                    "error");
                return;
            }
            
            base.cancel({title: "系统消息审核", text: "您确定要审核此系统消息吗？"}, function () {
            	$.post("/manage/sysnotice/audit", {
                    "sysNoticeId": arrselections[0].sysNoticeId
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                        	 base.success("审核成功！");
                        	$("#userTable").bootstrapTable('refresh');
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("审核失败");
                    }
                });
            });
        },
    };
});