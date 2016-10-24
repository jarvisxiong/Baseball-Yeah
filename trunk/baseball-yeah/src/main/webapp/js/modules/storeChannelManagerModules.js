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
            var date = new Date();
            // 开始时间
            $('#createStartDatePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                todayHighlight: true,
                endDate: new Date(date.getFullYear(), date.getMonth(),
                    date.getDate(), 23, 59, 59)
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#createEndDatePicker').datetimepicker('setStartDate', startTime);
            });

            $('#createEndDatePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                todayHighlight: true,
                endDate: new Date(date.getFullYear(), date.getMonth(),
                    date.getDate(), 23, 59, 59)
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#createStartDatePicker').datetimepicker('setEndDate', startTime);
            });

            base.datagrid({
                url: '/business/chl/queryAll',
                sScrollY: true,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            channelId: $.trim($("#channelId").val()),
                            state: $("#state").val() == "请选择" ? "" : $("#state").val(),
                            createStartDate: $('#createStartDate').val(),
                            createEndDate: $('#createEndDate').val(),
                            nickName: $('#nickName').val(),
                            connectPhone: $('#connectPhone').val(),
                            contacts: $('#contacts').val(),
                            state: $('#state').val(),
                            channelName: $('#channelName').val()
                        });
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'channelId',
                    title: '渠道Id',
                    sortable: true,
                    visible: true,
                    width: 800
                }, {
                    field: 'channelName',
                    title: '渠道名称',
                    sortable: true,
                    width: 800
                }, {
                    field: 'state',
                    title: '状态',
                    formatter: function (value, row, index) {
                        return value == "0" ? "<a class='label label-success' onclick='enableChl(" + row.channelId + ")'>启用</a>"
                            : "<a class='label label-warning' onclick='cancleChl(" + row.channelId + ")'>禁用</a>";
                    },
                    sortable: true,
                    width: 800
                }, {
                    field: 'nickName',
                    title: '维护人',
                    formatter: function (value, row, index) {
                        return value == null || value == "" ? "未设置昵称"
                            : value;
                    },
                    sortable: true,
                    width: 800
                }, {
                    field: 'createDate',
                    title: '维护时间',
                    sortable: true,
                    visible: true,
                    width: 800
                }, {
                    field: 'contacts',
                    title: '联系人',
                    sortable: true,
                    visible: true,
                    width: 800
                }, {
                    field: 'connectPhone',
                    title: '联系电话',
                    sortable: true,
                    visible: true,
                    width: 800
                }, {
                    field: 'spreadUrl',
                    title: '推广URL',
                    sortable: true,
                    visible: true,
                    width: 800
                }, {
                    field: 'targetUrl',
                    title: '目标URL',
                    sortable: true,
                    visible: true,
                    width: 800
                }, {
                    field: 'remark',
                    title: '备注',
                    sortable: true,
                    visible: true,
                    width: 800
                },]
            }, '#userTable');


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

            $("#clearSearch").click(function () {
                base.reset(".main-box-body");
                $('#channelId').val('');
                $('#channelName').val("");
                $('#nickName').val("");
                $('#connectPhone').val("");
                $('#contacts').val("");
                $('#state').val("");
                $('#channelName').val("");
                $('#createStartDate').val("");
                $('#createEndDate').val("");
            });

            $('#addModal').on('shown.bs.modal', function () {
                $('#addForm').data('bootstrapValidator').resetForm(true);
                $('#addTargetUrl').val("http://");
                $('#addSpreadUrl').val("http://");
                $.post(
                    "/business/chl/getRandomChlCode", "", function (data, status) {
                        if (status == "success") {
                            if (data != null && data != "") {
                                $("#addChannelCode").val(data.substring(1,7));
                            } else {
                                base.error("获取渠道编码失败");
                            }
                        } else {
                            base.error("获取渠道编码出错!");
                        }
                    });
            })
        },
        add: function () {
            var self = this;

            var strRegex = "^(https|http|ftp|rtsp|mms)?://\\w+";
            var strRegex1 = "^(https|http|ftp|rtsp|mms)?://";
                /*+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
                 + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                 + "|" // 允许IP和DOMAIN（域名）
                 + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
                 + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
                 + "[a-z]{2,6})" // first level domain- .com or .museum
                 + "(:[0-9]{1,4})?" // 端口- :80
                 + "((/?)|" // a slash isn't required if there is no file name
                 + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$"*/
            $('#addRemark').val("");

            $('#addModal').modal({
                keyboard: false,
                backdrop: 'static'
            });

            base.validator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    addChannelName: {
                        validators: {
                            notEmpty: {
                                message: '渠道名称不能为空'
                            },
                            stringLength: {
                                min: 0,
                                max: 25,
                                message: '渠道名称长度不能超过25'
                            }
                        }
                    },
                    addChannelCode: {
                        validators: {
                            notEmpty: {
                                message: '渠道编号不能为空'
                            },
                            callback:{
                                message : '渠道编码已存在',
                                callback : function (value, validator) {
                                    var res = true;
                                    if (value != null && value != ""){
                                        $.ajax({
                                            url: '/business/chl/ifExistChannelCode.htm',
                                            type: 'post',
                                            dataType: 'json',
                                            data:{"channelCode": value},
                                            async: false,
                                            success: function (data,state) {
                                                if (state == "success") {
                                                    if (data.success != 0) {
                                                        res = false;
                                                    }
                                                }
                                            },
                                    });
                                        return res;
                                    }
                                }
                            }
                        }
                    },
                    addContacts: {
                        validators: {
                            notEmpty: {
                                message: '联系人不能为空'
                            }
                        }
                    },
                    addSpreadUrl: {
                        validators: {
                            notEmpty: {
                                message: '推广URL不能为空'
                            },
                            regexp: {
                                regexp: strRegex1,
                                message: '推广URL不正确'
                            }
                        }
                    },
                    addTargetUrl: {
                        validators: {
                            notEmpty: {
                                message: '目标URL不能为空'
                            },
                            regexp: {
                                regexp: strRegex,
                                message: '目标URL不正确'
                            },
                            stringLength: {
                                min: 0,
                                max: 100,
                                message: '备注长度不能超过100'
                            }
                        }
                    },
                    addContactPhone: {
                        validators: {
                            notEmpty: {
                                message: '联系电话不能为空'
                            },
                            regexp: {
                                regexp: /^[1]+[3,5,7,8]+\d{9}$/,
                                message: '手机号格式不正确'
                            }
                        }
                    },
                    addRemark: {
                        validators: {
                            stringLength: {
                                min: 0,
                                max: 100,
                                message: '备注长度不能超过100'
                            }
                        }
                    }
                }
            }, '#addForm', self.create);
        },
        create: function () {
            $.post(
                "/business/chl/addBussinessChl",
                {
                    "channelName": $("#addChannelName").val(),
                    "channelCode": $("#addChannelCode").val(),
                    "spreadUrl": $("#addSpreadUrl").val(),
                    "targetUrl": $("#addTargetUrl").val(),
                    "remark": $("#addRemark").val(),
                    "connectPhone": $("#addContactPhone").val(),
                    "contacts": $("#addContacts").val()
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            swal("增加成功");
                            $('#addModal').modal('hide');
                            $("#userTable").bootstrapTable('refresh');
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
            var arrselections = $("#userTable").bootstrapTable(
                'getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            $('#editRoleID').val(arrselections[0].roleId);
            $('#editRoleName').val(arrselections[0].roleName);
            $('#editRoleType').val(arrselections[0].roleType).trigger("change");
            $('#editBeSysPrivilege').val(arrselections[0].beSysPrivilege).trigger("change");

            $.get("/manage/menu/getRoleMenu", {
                "roleId": arrselections[0].roleId
            }, function (data, status) {
            });

            $('#editModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    editRoleName: {
                        validators: {
                            notEmpty: {
                                message: '角色名不能为空'
                            }
                        }
                    },
                    editRoleType: {
                        validators: {
                            notEmpty: {
                                message: '角色类型不能为空'
                            }
                        }
                    },
                    editBeSysPrivilege: {
                        validators: {
                            notEmpty: {
                                message: '是否系统权限不能为空'
                            }
                        }
                    }
                }
            }, '#editForm', self.update)
        },
        update: function () {
            var zTree = $.fn.zTree.getZTreeObj("editTreeDemo");
            var checkNodes = new Array();
            var nodes = zTree.getChangeCheckedNodes();
            for (var i = 0; i < nodes.length; i++) {
                checkNodes[i] = nodes[i].idStr;
            }
            $.post(
                "/manage/role/updateRoleAndPermission",
                {
                    "roleId": $('#editRoleID').val(),
                    "roleName": $("#editRoleName").val(),
                    "roleType": $("#editRoleType").val(),
                    "beSysPrivilege": $("#editBeSysPrivilege").val() == "请选择" ? "" : $("#editBeSysPrivilege").val(),
                    "checkNodes": checkNodes.toString()
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $('#editModal').modal('hide');
                            $("#userTable").bootstrapTable('refresh');
                            $('#editForm').data('bootstrapValidator').resetForm(true);
                            base.success("编辑成功");
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        },
        remove: function () {
            var arrselections = $("#userTable").bootstrapTable(
                'getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var channelId = arrselections[0].channelId;

            base.cancel({
                title: "删除商户渠道",
                text: "您确定要删除此商户渠道吗？"
            }, function () {
                $.post("/business/chl/delBussinessChl", {
                    "channelId": channelId
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("删除成功");
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
    };
});

function enableChl(channelId) {
    $.post(
        "/business/chl/cancleBussinessChl",
        {
            "channelId": channelId
        }, function (data, status) {
            if (status == "success") {
                if (data.success == 0) {
                    $('#editModal').modal('hide');
                    $("#userTable").bootstrapTable('refresh');
                    $('#editForm').data('bootstrapValidator').resetForm(true);
                    base.success("启用成功");
                } else {
                    base.error(data.message);
                }
            } else {
                base.error("启用失败!");
            }
        });
}

function cancleChl(channelId) {
    $.post(
        "/business/chl/enabledBussinessChl",
        {
            "channelId": channelId
        }, function (data, status) {
            if (status == "success") {
                if (data.success == 0) {
                    $('#editModal').modal('hide');
                    $("#userTable").bootstrapTable('refresh');
                    $('#editForm').data('bootstrapValidator').resetForm(true);
                    base.success("禁用成功");
                } else {
                    base.error(data.message);
                }
            } else {
                base.error("禁用失败!");
            }
        });
}

function changeSpreadUrl() {
    var channelCode = $('#addChannelCode').val();
    var targetUrl = $('#addTargetUrl').val();
    var holeTargetUrl="";
    if (channelCode !== null && targetUrl != null && channelCode != "" && targetUrl != "" && targetUrl !="http://") {
        if (targetUrl.indexOf("?")>=0){
            holeTargetUrl=targetUrl + "&channel=" + channelCode;
        }else {
            holeTargetUrl=targetUrl + "?channel=" + channelCode;
        }
        $.post("/manage/url/getshorturl.htm", {"longurl": encodeURIComponent(holeTargetUrl)}, function (data, state) {
            if (state == "success") {
                $('#addSpreadUrl').val(data);
            }
        });
    }
}

function refreshChannelCode(){
    $.post(
        "/business/chl/getRandomChlCode", "", function (data, status) {
            if (status == "success") {
                if (data != null && data != "") {
                    $("#addChannelCode").val(data.substring(1,7));
                    changeSpreadUrl();
                } else {
                    base.error("获取渠道编码失败");
                }
            } else {
                base.error("获取渠道编码出错!");
            }
        });
}