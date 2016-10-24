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
                url: '/user/feedback/selectall',
            	method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                            {
                    	phone: $(
                                        "#phone")
                                        .val()
                            });
                },
                columns: [
                                                                         {
                                                                               checkbox: true
                                                                           },
                                                                           {
                                                                               field: 'userId',
                                                                               title: '用户id',
                                                                               sortable: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                               field: 'name',
                                                                               title: '用户姓名',
                                                                               sortable: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                               field: 'phone',
                                                                               title: '用户联系方式',
                                                                               sortable: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                               field: 'content',
                                                                               title: '反馈信息内容',
                                                                               sortable: true,
                                                                               width:1600
                                                                            
                                                                               
                                                                           },
                                                                           {
                                                                               field: 'ip',
                                                                               title: '本地IP地址',
                                                                               sortable: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                               field: 'submittedTime',
                                                                               title: '提交时间',
                                                                               sortable: true,
                                                                               width:400
                                                                           },
                                                                           {
                                                                        	   field: 'feedbackId',
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

            $("#btn_add").click(function () {
                self.add();
            });
            $("#btn_delete").click(function () {
                self.remove();
            });

            $("#btn_query").click(function () {
                $("#userTable").bootstrapTable('refresh');
            });
      
            $("#btn_add").click(function () {
                $("#add_submittedTime").datetimepicker({
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
                    	add_content: {
                            validators: {
                                notEmpty: {
                                    message: '内容不能为空'
                                }
                            }
                        },
                        add_ip: {
                            validators: {
                                notEmpty: {
                                    message: 'IP地址不能为空'
                                }
                            }
                        },
                        add_submittedTime: {
                            validators: {
                                notEmpty: {
                                    message: '提交时间不能为空'
                                }
                            }
                        },
                        add_phone: {
                            validators: {
                                notEmpty: {
                                    message: '手机号不能为空'
                                }, regexp: {
	                                regexp: /^[1]+[3,5,7,8]+\d{9}/,
	                                message: '手机号格式不正确'
	                         }
                            }
                        },
                        add_name: {
                            validators: {
                                notEmpty: {
                                    message: '用户名称不能为空'
                                }
                            }
                        },
                        add_userId: {
                            validators: {
                                notEmpty: {
                                    message: '用户ID不能为空'
                                }
                            }
                        }
                    }
                }, "#addForm", self.create);
        },
        create: function () {
            $.post("/user/feedback/addfeedback",
                           {
                               "caption": $(
                                       "#add_caption")
                                   .val(),
                               "userId": $(
                                       "#add_userId")
                                   .val(),
                               "name": $(
                                       "#add_name")
                                   .val(),
                               "publishTime": $(
                                       "#add_publishTime")
                                   .val(),
                               "phone": $(
                                       "#add_phone").val(),
                               "content": $(
                                       "#add_content")
                                   .val(),
                               "ip": $(
                                       "#add_ip").val(),
                               "submittedTime": $(
                                       "#submittedTime_ip").val()
                                       

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
        remove: function () {
            var arrselections = $("#userTable").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var userInfos = [];

            for (var i = 0; i < arrselections.length; i++) {
                userInfos.push(arrselections[i].feedbackId);
            }
            base.cancel({ title: "删除", text: "您确定要删除吗？" }, function () {
                $.post("/user/feedback/deletefeedback", {
                    "feedbackIds": userInfos.join(',')
                }, function (data, status) {
                    if (status == "success") {
                  
                        if (data.success == 0) {
                        	 base.success("删除成功！");
                        	$("#userTable").bootstrapTable('refresh');
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