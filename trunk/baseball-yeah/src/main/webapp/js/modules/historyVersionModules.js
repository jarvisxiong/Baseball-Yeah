define(['base'], function (base, audit) {
    /**
     * 私有成员定义区域
     */


    return {
        init: function (args) {
            var self = this;

            base.datagrid({
                url: '/version/list',
                method: 'get',
                sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            version: $("#version").val(),
                            startTime: $('#startdate').val(),
                            endTime: $('#enddate').val()
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'version',
                        title: '版本号',
                        sortable: true,
                        width: 150
                    }
                    ,
                    {
                        field: 'title',
                        title: '标题',
                        sortable: true,
                        width: 150
                    }
                    , /*{
                        field: 'content',
                        title: '内容',
                        sortable: true,
                        width: 500,
                        formatter:function(value,row,index){
                        	var str=row.content
                        	if(str.length>100){
                        		str=str.substring(0,100)+"</br>"+"<p>.....</p>";
                        	}
                        	return str;
                        }
                    }
                    ,*/
                    {
                        field: 'createTime',
                        title: '创建时间',
                        sortable: true,
                        width: 150
                    }
                    ,
                    {
                        field: 'sortNo',
                        title: '序号',
                        sortable: true,
                        width: 150
                    }
                ]
            }, '#historyVersionTable');
            //开始时间
            $('#starttimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:mm:ss',
                autoclose: true,
                pickTime: false,
                minView: 2
            })

            //结束时间
            $('#endtimePicker').datetimepicker(
                {
                    format: 'yyyy-mm-dd hh:mm:ss',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
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
                $("#historyVersionTable").bootstrapTable('refresh');
            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
            });

            $('#addModal').on('shown.bs.modal', function () {
                $('#addForm').data('bootstrapValidator').resetForm(true);
            })
         
       /*  $('#edit_content').ckeditor();//修改的cke控件
          $('#add_content').ckeditor();//修改的cke控件
*/        },
        add: function () {
        	CKEDITOR.instances["add_content"].setData("");
            var self = this;
            $('#addModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_version: {
                        validators: {
                            notEmpty: {
                                message: '版本号不能为空'
                            }
                        }
                    },
                    add_title: {
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
                    add_sortNo: {
                        validators: {
                            notEmpty: {
                                message: '序号不能为空'
                            },
							regexp : {
								regexp : /^[0-9]*$/,
								message : '序号必须为数字格式'
							},
							stringLength : {
								min : 1,
								max : 4,
								message : '排序号过长'
							}
                        }
                    }
                }
            }, "#addForm", self.create)
        },
        create: function () {
            $.post("/version/add",
                {
				"version" : $("#add_version").val(),
				"title" : $("#add_title").val(),
				"content" : $("#add_content").val(),
				"sortNo" : $("#add_sortNo").val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#historyVersionTable").bootstrapTable('refresh');
                            $('#addModal').modal('hide');
                            base.success("添加成功！")
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
            var arrselections = $("#historyVersionTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $("#edit_version").val(arrselections[0].version);
            $("#edit_sortNo").val(arrselections[0].sortNo);
            $("#edit_title").val(arrselections[0].title);
            CKEDITOR.instances["edit_content"].setData(arrselections[0].content);
            /*$("#edit_content").val();*/
            $("#versionId").val(arrselections[0].versionId);
            $('#editModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_version: {
                        validators: {
                            notEmpty: {
                                message: '版本号不能为空'
                            }
                        }
                    },
                    add_title: {
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
                    add_sortNo: {
                        validators: {
                            notEmpty: {
                                message: '序号不能为空'
                            },
							regexp : {
								regexp : /^[0-9]*$/,
								message : '序号必须为数字格式'
							},
							stringLength : {
								min : 1,
								max : 4,
								message : '排序号过长'
							}
                        }
                    }
                }
            }, "#editForm", self.update)

        },
        update: function () {
            $.post(
                "/version/update",
                {
				"version" : $("#edit_version").val(),
				"title" : $("#edit_title").val(),
				"content" : $("#edit_content").val(),
				"sortNo" : $("#edit_sortNo").val(),
				"versionId":$("#versionId").val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#historyVersionTable").bootstrapTable('refresh');
                            $('#editModal').modal('hide');
                            $('#editForm').data('bootstrapValidator').resetForm(true);
                            base.success("更新成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        },
        remove: function () {
            var arrselections = $("#historyVersionTable").bootstrapTable('getSelections');

            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }

            base.cancel({title: "删除操作", text: "您确定要删除该项吗？"}, function () {
                $.post("/version/remove", {"versionId":arrselections[0].versionId}
                    , function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                $("#historyVersionTable").bootstrapTable('refresh');
                                base.success("删除成功！")
                            } else {
                                base.error(data.message);
                            }
                        } else {
                            base.error("删除失败");
                        }
                    });
            });
        }
    };
});