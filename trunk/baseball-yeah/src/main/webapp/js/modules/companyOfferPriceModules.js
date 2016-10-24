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
                url: '/manage/companyofferprice/getall',
                method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            offerName: $("#offerName").val(),
                            sendAreaId: $("#selSendArea").val(),
                            dispAreaId: $("#selDespArea").val()
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'offerName',
                        title: '报价名称'
                    },
                    {
                        field: 'initialWeight',
                        title: '首重费用',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value + "¥"
                        }
                    },
                    {
                        field: 'additionalWeight',
                        title: '续重费用',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value + "¥"
                        }
                    },
                    {
                        field: 'isEnabled',
                        title: '是否启用',
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value == 0) {
                                return "不启用";
                            } else {
                                return "启用";
                            }
                        }
                    },
                    {
                        field: 'sortNo',
                        title: '排序',
                        sortable: true
                    },
                    {
                        field: 'sendArearName',
                        title: '寄件人区域'
                    },
                    {
                        field: 'dispAreaName',
                        title: '收件人区域'
                    },
                    {
                        field: 'sendProvinceName',
                        title: '寄件省份'
                    },
                    {
                        field: 'dispProvinceName',
                        title: '派件省份'
                    },
                    {
                        field: 'companyName',
                        title: '快递公司'
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        sortable: true
                    },
                    {
                        field: 'companyIds',
                        title: 'companyIds',
                        visible: false
                    },
                    {
                        field: 'offerId',
                        title: 'offerId',
                        visible: false
                    }]
            }, '#offerTable');
            $.ajax({
                type: "POST",
                url: "/manage/college/getcollageforsel",
                dataType: "json",
                success: function (data) {
                    $("#add_Collage").select2({
                        data: data.data,
                        placeholder: '选择学校',
                        allowClear: true
                    });
                    $("#edit_Collage").select2({
                        data: data.data,
                        placeholder: '选择学校',
                        allowClear: true
                    });
                    $("#copy_Collage").select2({
                        data: data.data,
                        placeholder: '选择学校',
                        allowClear: true
                    });
                    $('#add_Collage').select2("val", null);
                    $('#edit_Collage').select2("val", null);
                    $('#copy_Collage').select2("val", null);
                }
            });
            $.ajax({
                type: "POST",
                url: "/manage/offerPrice/allforsel",
                dataType: "json",
                success: function (data) {

                    $("#selSendArea").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#selDespArea").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#add_sendAreaId").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#add_dispAreaId").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#edit_sendAreaId").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#edit_dispAreaId").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#copy_sendAreaId").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#copy_dispAreaId").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#copy_sendAreaId').select2("val", null);
                    $('#copy_dispAreaId').select2("val", null);
                    $('#selSendArea').select2("val", null);
                    $('#selDespArea').select2("val", null);
                    $('#add_sendAreaId').select2("val", null);
                    $('#add_dispAreaId').select2("val", null);
                    $('#edit_sendAreaId').select2("val", null);
                    $('#edit_dispAreaId').select2("val", null);
                }
            });
            $.ajax({
                url: "/manage/express/queryenabledforsel",
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $("#add_EcList").select2({
                        data: data,
                        placeholder: '选择合作商',
                        allowClear: true
                    });
                    $("#edit_EcList").select2({
                        data: data,
                        placeholder: '选择合作商',
                        allowClear: true
                    });
                    $("#copy_EcList").select2({
                        data: data,
                        placeholder: '选择合作商',
                        allowClear: true
                    });
                    $('#copy_EcList').select2("val", null);
                    $('#add_EcList').select2("val", null);
                    $('#edit_EcList').select2("val", null);
                }
            });
            $("#btn_add").click(function () {
                self.add();
            });
            $("#btn_edit").click(function () {
                self.edit();
            });
            $("#btn_copy").click(function () {
                self.copy();
            });
            $("#btn_delete").click(function () {
                self.remove();
            });
            $("#btn_query").click(function () {
                $("#offerTable").bootstrapTable('refresh');
            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
            });
            $("#btnClose").click(function () {
                $("#editForm").data("bootstrapValidator").resetForm(true);
            });
            $('#addModal').on('shown.bs.modal', function () {
                $("#add_Collage").val(" ").trigger("change");
                $("#add_sendAreaId").val(" ").trigger("change");
                $("#add_dispAreaId").val(" ").trigger("change");
                $("#add_EcList").val(" ").trigger("change");
                $('#addForm').data('bootstrapValidator').resetForm(true);
            });

        },
        add: function () {
            var self = this;
            $('#addModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_offerName: {
                        validators: {
                            notEmpty: {
                                message: '报价名称不能为空'
                            }
                        }
                    },
                    add_EcList: {
                        validators: {
                            notEmpty: {
                                message: '快递品牌不能为空'
                            }
                        }
                    },
                    add_initialWeight: {
                        validators: {
                            notEmpty: {
                                message: '首重费用不能为空'
                            },
                            regexp: {
                                regexp: /^[0-9]*$/,
                                message: '必须为数字'
                            }
                        }
                    },
                    add_additionalWeight: {
                        validators: {
                            notEmpty: {
                                message: '续重费用不能为空'
                            },
                            regexp: {
                                regexp: /^[0-9]*$/,
                                message: '必须为数字'
                            }
                        }
                    },
                    add_sendAreaId: {
                        validators: {
                            notEmpty: {
                                message: '寄件人区域不能为空'
                            }
                        }
                    },
                    add_dispAreaId: {
                        validators: {
                            notEmpty: {
                                message: '收件人区域不能为空'
                            }
                        }
                    },
                    add_sortNo: {
                        validators: {
                            notEmpty: {
                                message: '排序不能为空'
                            },
                            regexp: {
                                regexp: /^[0-9]*$/,
                                message: '必须为数字'
                            }
                        }
                    }

                }
            }, "#addForm", self.create);
        },
        create: function () {
            $.post("/manage/companyofferprice/add",
                {
                    "offerName": $("#add_offerName").val(),
                    "initialWeight": $("#add_initialWeight").val(),
                    "additionalWeight": $("#add_additionalWeight").val(),
                    "sendAreaId": $("#add_sendAreaId").val(),
                    "dispAreaId": $("#add_dispAreaId").val(),
                    "sortNo": $("#add_sortNo").val(),
                    "isEnabled": $("#addYEnabled").prop('checked') ? "1" : "0",
                    "ecList": $("#add_EcList").val().join(','),
                    "collageList": $("#add_Collage").val() && $("#add_Collage").val().join(',')
                },
                function (data, status) {
                    if (status == "success") {

                        if (data.success == 0) {
                            base.success("添加成功！")
                            $('#offerTable').bootstrapTable('refresh');
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
            var arrselections = $("#offerTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $('#editModal').modal({
                keyboard: false,
                backdrop: 'static'
            });


            $('#editModal').modal();
            // $("#editForm").data("bootstrapValidator").resetForm(true);
            $("#edit_offerName").val(arrselections[0].offerName);
            $("#edit_initialWeight").val(arrselections[0].initialWeight);
            $("#edit_additionalWeight").val(arrselections[0].additionalWeight);
            $("#edit_sortNo").val(arrselections[0].sortNo);
            $("#editYEnabled").prop("checked", arrselections[0].isEnabled == 1 ? true : false);
            $("#editNEnabled").prop("checked", arrselections[0].isEnabled == 1 ? false : true);

            $("#edit_offerId").val(arrselections[0].offerId);
            $("#edit_EcList").val(arrselections[0].ecList).trigger("change");
            $("#edit_Collage").val(arrselections[0].collageList).trigger("change");

            $("#edit_sendAreaId").val(arrselections[0].sendAreaId).trigger("change");
            $("#edit_dispAreaId").val(arrselections[0].dispAreaId).trigger("change");
            base.validator({
                fields: {
                    edit_store: {
                        validators: {
                            notEmpty: {
                                message: '快递站点不能为空'
                            }
                        }
                    },
                    edit_EcList: {
                        validators: {
                            notEmpty: {
                                message: '快递品牌不能为空'
                            }
                        }
                    },
                    edit_userName: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    edit_sendAreaId: {
                        validators: {
                            notEmpty: {
                                message: '寄件人区域不能为空'
                            }
                        }
                    },
                    edit_dispAreaId: {
                        validators: {
                            notEmpty: {
                                message: '收件人区域不能为空'
                            }
                        }
                    },
                    edit_phone: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^[1]+[3,4,5,7,8]+\d{9}$/,
                                message: '手机号格式不正确'
                            }
                        }
                    }
                }
            }, '#editForm', self.update)
        },
        copy: function () {
            var self = this;
            var arrselections = $("#offerTable").bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行复制!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $('#copyModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            $("#copy_offerName").val(arrselections[0].offerName);
            $("#copy_initialWeight").val(arrselections[0].initialWeight);
            $("#copy_additionalWeight").val(arrselections[0].additionalWeight);
            $("#copy_sortNo").val(arrselections[0].sortNo);
            $("#copyYEnabled").prop("checked", arrselections[0].isEnabled == 1 ? true : false);
            $("#ecopyNEnabled").prop("checked", arrselections[0].isEnabled == 1 ? false : true);

            $("#copy_offerId").val(arrselections[0].offerId);
            $('#copyModal').modal();
            $("#copy_EcList").val(arrselections[0].ecList).trigger("change");
            $("#copy_Collage").val(arrselections[0].collageList).trigger("change");

            $("#copy_sendAreaId").val(arrselections[0].sendAreaId).trigger("change");
            $("#copy_dispAreaId").val(arrselections[0].dispAreaId).trigger("change");
            base.validator({
                fields: {
                    copy_store: {
                        validators: {
                            notEmpty: {
                                message: '快递站点不能为空'
                            }
                        }
                    },
                    copy_EcList: {
                        validators: {
                            notEmpty: {
                                message: '快递品牌不能为空'
                            }
                        }
                    },
                    copy_userName: {
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            }
                        }
                    },
                    copy_phone: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^[1]+[3,4,5,7,8]+\d{9}$/,
                                message: '手机号格式不正确'
                            }
                        }
                    }
                }
            }, '#copyForm', self.copycreate)
        },
        copycreate: function () {
            $.post("/manage/companyofferprice/add",
                {
                    "offerName": $("#copy_offerName").val(),
                    "initialWeight": $("#copy_initialWeight").val(),
                    "additionalWeight": $("#copy_additionalWeight").val(),
                    "sendAreaId": $("#copy_sendAreaId").val(),
                    "dispAreaId": $("#copy_dispAreaId").val(),
                    "sortNo": $("#copy_sortNo").val(),
                    "isEnabled": $("#ecopyYEnabled").prop('checked') ? "1" : "0",
                    "ecList": $("#copy_EcList").val().join(','),
                    "collageList": $("#copy_Collage").val() && $("#copy_Collage").val().join(',')
                },
                function (data, status) {
                    if (status == "success") {

                        if (data.success == 0) {
                            base.success("添加成功！")
                            $('#offerTable').bootstrapTable('refresh');
                            $("#copyModal").modal('hide');
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
        },
        update: function () {
            $.post("/manage/companyofferprice/update",
                {
                    "offerName": $("#edit_offerName").val(),
                    "initialWeight": $("#edit_initialWeight").val(),
                    "additionalWeight": $("#edit_additionalWeight").val(),
                    "sendAreaId": $("#edit_sendAreaId").val(),
                    "dispAreaId": $("#edit_dispAreaId").val(),
                    "sortNo": $("#edit_sortNo").val(),
                    "isEnabled": $("#editYEnabled").prop('checked') ? "1" : "0",
                    "offerId": $("#edit_offerId").val(),
                    "ecList": $("#edit_EcList").val().join(','),
                    "collageList": $("#edit_Collage").val() && $("#edit_Collage").val().join(',')
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("更新成功！")
                            $('#offerTable').bootstrapTable('refresh');
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
            var arrselections = $("#offerTable").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }

            base.cancel({title: "删除", text: "您确定要删除吗？"}, function () {
                $.post("/manage/companyofferprice/del", {
                    "offerId": arrselections[0].offerId
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $('#offerTable').bootstrapTable('refresh');
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