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
                url: '/manage/msgtemplate/selectall',
            	method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                            {
                    	messageTemplateName: $(
                                        "#messageTemplateName")
                                        .val()
                            });
                },
                columns: [
                                                                           {
                                                                               checkbox: true
                                                                           },
                                                                          
                                                                           {
                                                                        	   field: 'messageTemplateId',
                                                                               title: '模板ID',
                                                                               visible: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                               field: 'messageTemplateName',
                                                                               title: '名称',
                                                                               sortable: true,
                                                                               width:600
                                                                           },
                                                                           {
                                                                               field: 'messageTemplate',
                                                                               title: '短信模版',
                                                                               sortable: true,
                                                                               width:1600
                                                                           },
                                                                           {
                                                                               field: 'priority',
                                                                               title: '优先级',
                                                                               sortable: true,
                                                                               width:200
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

            $("#btn_add").click(function () {
                self.add();
            });
            $("#btn_edit").click(function () {
                self.edit();
            });
            $("#btn_delete").click(function () {
                self.remove();
            });
            $("#btn_query").click(function () {
                $("#userTable").bootstrapTable('refresh');
            });
            $("#btn_add").click(function () {
            $("#add_publishTime").datetimepicker({
                format: 'yyyy-mm-dd hh:ii:mm'
            });
            });

            $('#addModal').on('shown.bs.modal', function () {
                $('#addForm').data('bootstrapValidator').resetForm(true);
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
                        add_messageTemplateId: {
                            validators: {
                                notEmpty: {
                                    message: '模板ID不能为空'
                                }
                            }
                        },
                    	add_messageTemplateName: {
                            validators: {
                                notEmpty: {
                                    message: '名称不能为空'
                                }
                            }
                        },
                        add_messageTemplate: {
                            validators: {
                                notEmpty: {
                                    message: '短信模版不能为空'
                                }
                            }
                        },
                        add_priority : {
                            validators: {
                                notEmpty: {
                                    message: '优先级不能为空'
                                }
                            }
                        }
                    
                    }
                }, "#addForm", self.create);
        },
        create: function () {
            $.post("/manage/msgtemplate/addtemplate",
                           {
                               "messageTemplateName": $(
                                       "#add_messageTemplateName")
                                   .val(),
                               "messageTemplate": $(
                                       "#add_messageTemplate")
                                   .val(),
                               "priority": $(
                                       "#add_priority")
                                   .val(),
                                   "messageTemplateId": $(
                                       "#add_messageTemplateId")
                                   .val()

                           },
                           function (data, status) {
                               if (status == "success") {
                   
                                   if (data.success == 0) {
                                	   base.success("添加成功！")
                                       $('#userTable').bootstrapTable('refresh');
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
  
            var arrselections = $("#userTable")
                                  .bootstrapTable('getSelections');
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
            $("#edit_messageTemplateName").val(
                arrselections[0].messageTemplateName);
            $("#edit_messageTemplate").val(
                arrselections[0].messageTemplate);
            $("#edit_priority").val(
                arrselections[0].priority);
           
            $("#edit_messageTemplateId").val(
                arrselections[0].messageTemplateId);
            $('#editModal').modal();

            $('#editForm')
                .bootstrapValidator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        edit_messageTemplateId: {
                            validators: {
                                notEmpty: {
                                    message: '模板ID不能为空'
                                }
                            }
                        },
                    	edit_messageTemplateName: {
                            validators: {
                                notEmpty: {
                                    message: '名称不能为空'
                                }
                            }
                        },
                        edit_messageTemplate: {
                            validators: {
                                notEmpty: {
                                    message: '短信模版不能为空'
                                }
                            }
                        },
                        edit_priority : {
                            validators: {
                                notEmpty: {
                                    message: '优先级不能为空'
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
        },
        update: function () {
            $.post(
                      "/manage/msgtemplate/updatetemplate",
                      {    
                    	  "messageTemplateId": $("#edit_messageTemplateId").val(),
                          "messageTemplateName": $("#edit_messageTemplateName").val(),
                          "messageTemplate": $("#edit_messageTemplate").val(),
                          "priority": $("#edit_priority").val()
                      },
                      function (data, status) {
                          if (status == "success") {
                              if (data.success == 0) {
                            	  base.success("更新成功！")
                                $('#userTable').bootstrapTable('refresh');
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
                userInfos.push(arrselections[i].messageTemplateId);
            }

            base.cancel({ title: "删除", text: "您确定要删除吗？" }, function () {
                $.post("/manage/msgtemplate/deletetemplate", {
                    "messageTemplateIds": userInfos.join(',')
                }, function (data, status) {
                    if (status == "success") {
                      
                        if (data.success == 0) {
                        	$('#userTable').bootstrapTable('refresh');
                            base.success("删除成功！");
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("注销失败");
                    }
                });
            });
        }
    };
});