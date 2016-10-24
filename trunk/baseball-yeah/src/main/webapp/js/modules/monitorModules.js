define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var patterns = new Object();
    //匹配邮件地址
    patterns.email = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    /*手机，电话*/
    patterns.phone = /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8})|(0\d{2}-\d{8})|(0\d{3}-\d{7})$)/;

    /*verify – 校验一个字符串是否符合某种模式
     *str – 要进行校验的字符串
     *pat – 与patterns中的某个正则表达式模式对应的属性名称
     */
    function verify(str, pat) {
        thePat = patterns[pat];
        if (thePat.test(str)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 增加联系人
     * @param control
     */
    function addRow(data) {
        var rowDiv = '<div class="row" style="margin-top: 5px;"><div class="col-lg-1"></div><label class="col-lg-1 control-label">联系人：</label> <div class="col-lg-2">' +
            '<input type="text" name="contactNames[]" class="form-control add-user"></div>' +
            '<label class="col-lg-1 control-label">联系电话：</label>' +
            '<div class="col-lg-2">' +
            '<input type="text" name="contactPhones[]" class="form-control add-phone"></div>' +
            '<label class="col-lg-1 control-label">邮箱：</label>' +
            '<div class="col-lg-2">' +
            '<input type="text" name="contactEmails[]" class="form-control add-email"></div>' +
            '<a href="javascript:void(0)" name="remove-row" class="table-link remove-row">' +
            '<span class="fa-stack">' +
            '<i class="fa fa-square fa-stack-2x"></i>' +
            '<i class="fa fa-times fa-stack-1x fa-inverse"></i>' +
            '</span></a> </div>';
        data.prepend(rowDiv);
    }

    return {
        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            //开始时间
            $('#addstarttimePicker').datetimepicker({
                format: 'hh:ii:ss',
                autoclose: true,
                pickTime: true,
                minView: 0,
                maxView: 1,
                startView: 1
            });

            //结束时间
            $('#addendtimePicker').datetimepicker(
                {
                    format: 'hh:ii:ss',
                    autoclose: true,
                    pickTime: true,
                    minView: 0,
                    maxView: 1,
                    startView: 1
                });

            base.datagrid({
                url: '/manage/monitor/list',
                method: 'get',
                singleSelect: true,//单选
                showFooter: false,
                search: true,
                pagination: true, // 是否显示分页（*）
                sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                queryParams: function (params) {
                    return $.extend(params, {
                        monitorName: $('#monitorName').val()
                    });
                },
                onPageChange: function (number, size) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#monitorTable').find("thead").height() + $('#monitorTable').find("tbody").height()
                        + 3 + $('#monitorTable').parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#monitorTable').bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#monitorTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#monitorTable').bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1150);
                    }
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'monitorName',
                        title: '监控项名称',
                        sortable: true,
                        width: 100
                    },
                    {
                        field: 'monitorSql',
                        title: '监控SQL',
                        sortable: true,
                        width: 700
                    },
                    {
                        field: 'threshold',
                        title: '阀值',
                        sortable: true,
                        width: 100
                    },
                    {
                        field: 'startTime',
                        title: '开始监控时间',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value.slice(value.indexOf(' ') + 1);
                        },
                        width: 150
                    },
                    {
                        field: 'endTime',
                        title: '结束监控时间',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value.slice(value.indexOf(' ') + 1);
                        },
                        width: 150
                    },
                    {
                        field: 'runInterval',
                        title: '执行间隔',
                        sortable: true,
                        width: 100
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        sortable: true
                    }
                ]
            }, '#monitorTable');

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
                $("#monitorTable").bootstrapTable('refresh');
            });

            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
            });

            $("#btn_contact_add").click(function () {
                addRow($('#contactDiv'));
                $('#addForm').bootstrapValidator('addField', $("#contactDiv").find(".add-user").eq(0));
                $('#addForm').bootstrapValidator('addField', $("#contactDiv").find(".add-phone").eq(0));
                $('#addForm').bootstrapValidator('addField', $("#contactDiv").find(".add-email").eq(0));
            });

            $("#btn_contact_edit").click(function () {
                addRow($('#contactEditDiv'));
            });

            $('#addModal').on('shown.bs.modal', function () {
                $('#addForm').data('bootstrapValidator').resetForm(true);
                $("#addmonitorName").val("");
                $("#addmonitorSql").val("");
                $("#addthreshold").val("");
                $("#addrunInterval").val("");
                $("#addstartTime").val("00:00:00");
                $("#addendTime").val("23:59:59");
                $("#contactDiv").empty();
            });

            $('#editModal').on('hidden.bs.modal', function () {
                $('#editForm').data('bootstrapValidator').resetForm(true);
                $("#editmonitorName").val("");
                $("#editmonitorSql").val("");
                $("#editthreshold").val("");
                $("#editstartTime").val("");
                $("#editendTime").val("");
                $("#editrunInterval").val("");
                $("#contactEditDiv").empty();
            });

            $(document).on('click', '#editForm .row .remove-row', function () {
                var parent = $(this).parent();
                $('#editForm').bootstrapValidator('removeField', parent.find(".add-user").eq(0));
                $('#editForm').bootstrapValidator('removeField', parent.find(".add-phone").eq(0));
                $('#editForm').bootstrapValidator('removeField', parent.find(".add-email").eq(0));
                parent.remove();
            });

            $(document).on('click', '#addForm .row .remove-row', function () {
                var parent = $(this).parent();
                $('#addForm').bootstrapValidator('removeField', parent.find(".add-user").eq(0));
                $('#addForm').bootstrapValidator('removeField', parent.find(".add-phone").eq(0));
                $('#addForm').bootstrapValidator('removeField', parent.find(".add-email").eq(0));
                parent.remove();
            });
        },
        add: function () {
            var self = this;
            $('#addModal').modal();
            var validate = {
                fields: {
                    addmonitorName: {
                        validators: {
                            notEmpty: {
                                message: '监控名称不能为空'
                            }
                        }
                    },
                    addmonitorSql: {
                        validators: {
                            notEmpty: {
                                message: '监控SQL不能为空'
                            }
                        }
                    },
                    addthreshold: {
                        validators: {
                            notEmpty: {
                                message: '阀值不能为空'
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '阀值格式不正确'
                            }
                        }
                    },
                    addrunInterval: {
                        validators: {
                            notEmpty: {
                                message: '执行间隔不能为空'
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '执行间隔格式不正确'
                            }
                        }
                    },
                    addstartTime: {
                        validators: {
                            regexp: {
                                regexp: /^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/,
                                message: '开始时间格式不正确'
                            }
                        }
                    },
                    addendTime: {
                        validators: {
                            regexp: {
                                regexp: /^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/,
                                message: '结束时间格式不正确'
                            }
                        }
                    },
                    'contactNames[]': {
                        validators: {
                            // notEmpty: {
                            //     message: '联系人姓名不能为空'
                            // },
                            stringLength: {
                                min: 0,
                                max: 50,
                                message: '联系人姓名长度不能超过50位'
                            }
                        }
                    },
                    'contactPhones[]': {
                        validators: {
                            // notEmpty: {
                            //     message: '联系电话不能为空'
                            // },
                            regexp: {
                                regexp: /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8})|(0\d{2}-\d{8})|(0\d{3}-\d{7})$)/,
                                message: '联系电话格式不正确'
                            }
                        }
                    },
                    'contactEmails[]': {
                        validators: {
                            // notEmpty: {
                            //     message: '邮箱不能为空'
                            // },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                                message: '邮箱格式不正确'
                            }
                        }
                    },
                }
            };
            base.validator(validate, "#addForm", self.create);
        },
        create: function () {
            var monitorContactsInfoList = [];
            var contactRows = $("#contactDiv").children();
            for (var i = 0; i < contactRows.length; i++) { //遍历子元素
                var monitorContactsInfo = {};
                monitorContactsInfo.contactName = $(contactRows[i]).find(".add-user").val();
                monitorContactsInfo.phone = $(contactRows[i]).find(".add-phone").val();
                if (!verify(monitorContactsInfo.phone, "phone")) {
                    sweetAlert("Oops...", "联系电话格式不正确!", "error");
                    return;
                }
                monitorContactsInfo.email = $(contactRows[i]).find(".add-email").val();
                if (!verify(monitorContactsInfo.email, "email")) {
                    sweetAlert("Oops...", "邮箱格式不正确!", "error");
                    return;
                }
                monitorContactsInfoList.push(monitorContactsInfo);
            }
            var startTime = $("#addstartTime").val() == '' ? '1970-01-01 00:00:00' : '1970-01-01 ' + $("#addstartTime").val();
            var endTime = $("#addendTime").val() == '' ? '1970-01-01 23:59:59' : '1970-01-01 ' + $("#addendTime").val();
            $.ajax({
                url: "/manage/monitor/add",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify({
                    "monitorName": $("#addmonitorName").val(),
                    "monitorSql": $("#addmonitorSql").val(),
                    "threshold": $("#addthreshold").val(),
                    "startTime": startTime,
                    "endTime": endTime,
                    "runInterval": $("#addrunInterval").val(),
                    "monitorContactsInfoList": monitorContactsInfoList
                }),
                success: function (data) {
                    var obj = data;
                    if (obj.success == 0) {
                        $("#monitorTable").bootstrapTable('refresh');
                        $('#addModal').modal('hide');
                        $('#addForm').data('bootstrapValidator').resetForm(true);
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
        edit: function () {
            var self = this;
            var validate = {
                fields: {
                    editmonitorName: {
                        validators: {
                            notEmpty: {
                                message: '监控名称不能为空'
                            }
                        }
                    },
                    editmonitorSql: {
                        validators: {
                            notEmpty: {
                                message: '监控SQL不能为空'
                            }
                        }
                    },
                    editthreshold: {
                        validators: {
                            notEmpty: {
                                message: '阀值不能为空'
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '阀值格式不正确'
                            }
                        }
                    },
                    editrunInterval: {
                        validators: {
                            notEmpty: {
                                message: '执行间隔不能为空'
                            },
                            regexp: {
                                regexp: /^[1-9]\d*$/,
                                message: '执行间隔格式不正确'
                            }
                        }
                    },
                    editstartTime: {
                        validators: {
                            regexp: {
                                regexp: /^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/,
                                message: '开始时间格式不正确'
                            }
                        }
                    },
                    editendTime: {
                        validators: {
                            regexp: {
                                regexp: /^([0-1]\d|2[0-3]):[0-5]\d:[0-5]\d$/,
                                message: '结束时间格式不正确'
                            }
                        }
                    },
                    'contactNames[]': {
                        validators: {
                            // notEmpty: {
                            //     message: '联系人姓名不能为空'
                            // },
                            stringLength: {
                                min: 0,
                                max: 50,
                                message: '联系人姓名长度不能超过50位'
                            }
                        }
                    },
                    'contactPhones[]': {
                        validators: {
                            // notEmpty: {
                            //     message: '联系电话不能为空'
                            // },
                            regexp: {
                                regexp: /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8})|(0\d{2}-\d{8})|(0\d{3}-\d{7})$)/,
                                message: '联系电话格式不正确'
                            }
                        }
                    },
                    'contactEmails[]': {
                        validators: {
                            // notEmpty: {
                            //     message: '邮箱不能为空'
                            // },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                                message: '邮箱格式不正确'
                            }
                        }
                    },
                }
            };
            var arrselections = $("#monitorTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            $('#editModal').modal();

            $("#monitorId").val(arrselections[0].monitorId);
            $("#editmonitorName").val(arrselections[0].monitorName);
            $("#editmonitorSql").val(arrselections[0].monitorSql);
            $("#editthreshold").val(arrselections[0].threshold);
            $("#editrunInterval").val(arrselections[0].runInterval);
            $("#editstartTime").val(arrselections[0].startTime.slice(arrselections[0].startTime.indexOf(' ') + 1));
            $("#editendTime").val(arrselections[0].endTime.slice(arrselections[0].endTime.indexOf(' ') + 1));
            base.validator(validate, "#editForm", self.update);
            var contactsList = arrselections[0].monitorContactsInfoList;
            for (var i = 0; i < contactsList.length; i++) {
                addRow($('#contactEditDiv'));
                $('#editForm').bootstrapValidator('addField', $("#contactEditDiv").find(".add-user").eq(0));
                $('#editForm').bootstrapValidator('addField', $("#contactEditDiv").find(".add-phone").eq(0));
                $('#editForm').bootstrapValidator('addField', $("#contactEditDiv").find(".add-email").eq(0));
            }
            for (var i = 0; i < contactsList.length; i++) {
                $("#contactEditDiv").find(".add-user").eq(i).val(contactsList[i].contactName);
                $("#contactEditDiv").find(".add-phone").eq(i).val(contactsList[i].phone);
                $("#contactEditDiv").find(".add-email").eq(i).val(contactsList[i].email);
            }
        },
        update: function () {
            var monitorContactsInfoList = [];
            var contactRows = $("#contactEditDiv").children();
            for (var i = 0; i < contactRows.length; i++) { //遍历子元素
                var monitorContactsInfo = {};
                monitorContactsInfo.contactName = $(contactRows[i]).find(".add-user").val();
                monitorContactsInfo.phone = $(contactRows[i]).find(".add-phone").val();
                monitorContactsInfo.email = $(contactRows[i]).find(".add-email").val();
                monitorContactsInfoList.push(monitorContactsInfo);
            }
            var startTime = $("#editstartTime").val() == '' ? '1970-01-01 00:00:00' : '1970-01-01 ' + $("#editstartTime").val();
            var endTime = $("#editendTime").val() == '' ? '1970-01-01 23:59:59' : '1970-01-01 ' + $("#editendTime").val();
            $.ajax({
                url: "/manage/monitor/update",
                contentType: "application/json",
                type: "POST",
                data: JSON.stringify({
                    "monitorId": $("#monitorId").val(),
                    "monitorName": $("#editmonitorName").val(),
                    "monitorSql": $("#editmonitorSql").val(),
                    "threshold": $("#editthreshold").val(),
                    "startTime": startTime,
                    "endTime": endTime,
                    "runInterval": $("#editrunInterval").val(),
                    "monitorContactsInfoList": monitorContactsInfoList
                }),
                success: function (data) {
                    var obj = data;
                    if (obj.success == 0) {
                        $("#monitorTable").bootstrapTable('refresh');
                        $('#editModal').modal('hide');
                        $('#editForm').data('bootstrapValidator').resetForm(true);
                        base.success("更新成功！");
                    } else {
                        base.error(obj.message);
                    }
                },
                error: function () {
                    base.error("数据加载失败!");
                }
            });
        },
        remove: function () {
            var arrselections = $("#monitorTable").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var monitorId = arrselections[0].monitorId;
            base.cancel({title: "删除监控项", text: "您确定要删除此监控项吗？"}, function () {
                $.post("/manage/monitor/del", {"monitorId": monitorId}, function (data) {
                    if (data.success == 0) {
                        base.success("删除成功");
                        $("#monitorTable").bootstrapTable('refresh');
                    } else {
                        base.error(data.message);
                    }
                });
            });
        }
    };
});