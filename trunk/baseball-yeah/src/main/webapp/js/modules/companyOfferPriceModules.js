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
                            offerName: $("#offerName", args).val(),
                            sendAreaId: $("#selSendArea", args).val(),
                            dispAreaId: $("#selDespArea", args).val()
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
                            return "¥" + value;
                        }
                    },
                    {
                        field: 'additionalWeight',
                        title: '续重费用',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return "¥" + value;
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
            }, '#offerTable', args);
            $.ajax({
                type: "POST",
                url: "/manage/college/getcollageforsel",
                dataType: "json",
                success: function (data) {
                    $("#add_Collage", args).select2({
                        data: data.data,
                        placeholder: '选择学校',
                        allowClear: true
                    });
                    $("#edit_Collage", args).select2({
                        data: data.data,
                        placeholder: '选择学校',
                        allowClear: true
                    });
                    $("#copy_Collage", args).select2({
                        data: data.data,
                        placeholder: '选择学校',
                        allowClear: true
                    });
                    $('#add_Collage', args).select2("val", null);
                    $('#edit_Collage', args).select2("val", null);
                    $('#copy_Collage', args).select2("val", null);
                }
            });
            $.ajax({
                type: "POST",
                url: "/manage/offerPrice/allforsel",
                dataType: "json",
                success: function (data) {

                    $("#selSendArea", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#selDespArea", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#add_sendAreaId", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#add_dispAreaId", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#edit_sendAreaId", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#edit_dispAreaId", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#copy_sendAreaId", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $("#copy_dispAreaId", args).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#copy_sendAreaId', args).select2("val", null);
                    $('#copy_dispAreaId', args).select2("val", null);
                    $('#selSendArea', args).select2("val", null);
                    $('#selDespArea', args).select2("val", null);
                    $('#add_sendAreaId', args).select2("val", null);
                    $('#add_dispAreaId', args).select2("val", null);
                    $('#edit_sendAreaId', args).select2("val", null);
                    $('#edit_dispAreaId', args).select2("val", null);
                }
            });
            $.ajax({
                url: "/manage/express/queryenabledforsel",
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    $("#add_EcList", args).select2({
                        data: data,
                        placeholder: '选择合作商',
                        allowClear: true
                    });
                    $("#edit_EcList", args).select2({
                        data: data,
                        placeholder: '选择合作商',
                        allowClear: true
                    });
                    $("#copy_EcList", args).select2({
                        data: data,
                        placeholder: '选择合作商',
                        allowClear: true
                    });
                    $('#copy_EcList', args).select2("val", null);
                    $('#add_EcList', args).select2("val", null);
                    $('#edit_EcList', args).select2("val", null);
                }
            });
            $("#btn_add", args).click(function () {
                self.add(args);
            });
            $("#btn_edit", args).click(function () {
                self.edit(args);
            });
            $("#btn_copy", args).click(function () {
                self.copy(args);
            });
            $("#btn_delete", args).click(function () {
                self.remove(args);
            });
            $("#btn_query", args).click(function () {
                // $("#offerTable",args).bootstrapTable('refresh');
                $("#offerTable", args).bootstrapTable('selectPage', 1);
            });
            $("#clearSearch", args).click(function () {
                base.reset(".main-box-header");
            });
            $("#btnClose", args).click(function () {
                $("#editForm", args).data("bootstrapValidator").resetForm(true);
            });
            $('#addModal', args).on('shown.bs.modal', function () {
                $("#add_Collage", args).val(" ").trigger("change");
                $("#add_sendAreaId", args).val(" ").trigger("change");
                $("#add_dispAreaId", args).val(" ").trigger("change");
                $("#add_EcList", args).val(" ").trigger("change");
                $('#addForm', args).data('bootstrapValidator').resetForm(true);
            });

        },
        add: function (args) {
            var self = this;
            $('#addModal', args).modal({
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
            }, "#addForm", self.create, args);
        },
        create: function (args) {
            $.post("/manage/companyofferprice/add",
                {
                    "offerName": $("#add_offerName", args).val(),
                    "initialWeight": $("#add_initialWeight", args).val(),
                    "additionalWeight": $("#add_additionalWeight", args).val(),
                    "sendAreaId": $("#add_sendAreaId", args).val(),
                    "dispAreaId": $("#add_dispAreaId", args).val(),
                    "sortNo": $("#add_sortNo", args).val(),
                    "isEnabled": $("#addYEnabled", args).prop('checked') ? "1" : "0",
                    "ecList": $("#add_EcList", args).val().join(','),
                    "collageList": $("#add_Collage", args).val() && $("#add_Collage", args).val().join(',')
                },
                function (data, status) {
                    if (status == "success") {

                        if (data.success == 0) {
                            base.success("添加成功！")
                            // $('#offerTable',args).bootstrapTable('refresh');
                            $("#offerTable", args).bootstrapTable('selectPage', 1);
                            $("#addModal", args).modal('hide');
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
        },
        edit: function (args) {
            var self = this;
            var arrselections = $("#offerTable", args).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $('#editModal', args).modal({
                keyboard: false,
                backdrop: 'static'
            });


            $('#editModal', args).modal();
            // $("#editForm").data("bootstrapValidator").resetForm(true);
            $("#edit_offerName", args).val(arrselections[0].offerName);
            $("#edit_initialWeight", args).val(arrselections[0].initialWeight);
            $("#edit_additionalWeight", args).val(arrselections[0].additionalWeight);
            $("#edit_sortNo", args).val(arrselections[0].sortNo);
            $("#editYEnabled", args).prop("checked", arrselections[0].isEnabled == 1 ? true : false);
            $("#editNEnabled", args).prop("checked", arrselections[0].isEnabled == 1 ? false : true);

            $("#edit_offerId", args).val(arrselections[0].offerId);
            $("#edit_EcList", args).val(arrselections[0].ecList).trigger("change");
            $("#edit_Collage", args).val(arrselections[0].collageList).trigger("change");

            $("#edit_sendAreaId", args).val(arrselections[0].sendAreaId).trigger("change");
            $("#edit_dispAreaId", args).val(arrselections[0].dispAreaId).trigger("change");
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
                    edit_initialWeight: {
                        validators: {
                            notEmpty: {
                                message: '首重费用不能为空'
                            },
                            regexp: {
                                regexp: /^[1-9]*[1-9][0-9]*$/,
                                message: '首重费用必须为正整数'
                            }
                        }
                    },
                    edit_additionalWeight: {
                        validators: {
                            notEmpty: {
                                message: '续重费用不能为空'
                            },
                            regexp: {
                                regexp: /^[1-9]*[1-9][0-9]*$/,
                                message: '续重费用必须为正整数'
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
            }, '#editForm', self.update, args)
        },
        copy: function (args) {
            var self = this;
            var arrselections = $("#offerTable", args).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行复制!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            $('#copyModal', args).modal({
                keyboard: false,
                backdrop: 'static'
            });
            $("#copy_offerName", args).val(arrselections[0].offerName);
            $("#copy_initialWeight", args).val(arrselections[0].initialWeight);
            $("#copy_additionalWeight", args).val(arrselections[0].additionalWeight);
            $("#copy_sortNo", args).val(arrselections[0].sortNo);
            $("#copyYEnabled", args).prop("checked", arrselections[0].isEnabled == 1 ? true : false);
            $("#copyNEnabled", args).prop("checked", arrselections[0].isEnabled == 1 ? false : true);

            $("#copy_offerId", args).val(arrselections[0].offerId);
            $('#copyModal', args).modal();
            $("#copy_EcList", args).val(arrselections[0].ecList).trigger("change");
            $("#copy_Collage", args).val(arrselections[0].collageList).trigger("change");

            $("#copy_sendAreaId", args).val(arrselections[0].sendAreaId).trigger("change");
            $("#copy_dispAreaId", args).val(arrselections[0].dispAreaId).trigger("change");
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
            }, '#copyForm', self.copycreate, args)
        },
        copycreate: function (args) {
            $.post("/manage/companyofferprice/add",
                {
                    "offerName": $("#copy_offerName", args).val(),
                    "initialWeight": $("#copy_initialWeight", args).val(),
                    "additionalWeight": $("#copy_additionalWeight", args).val(),
                    "sendAreaId": $("#copy_sendAreaId", args).val(),
                    "dispAreaId": $("#copy_dispAreaId", args).val(),
                    "sortNo": $("#copy_sortNo", args).val(),
                    "isEnabled": $("#copyYEnabled", args).prop('checked') ? "1" : "0",
                    "ecList": $("#copy_EcList", args).val().join(','),
                    "collageList": $("#copy_Collage", args).val() && $("#copy_Collage", args).val().join(',')
                },
                function (data, status) {
                    if (status == "success") {

                        if (data.success == 0) {
                            base.success("添加成功！")
                            // $('#offerTable',args).bootstrapTable('refresh');
                            $("#offerTable", args).bootstrapTable('selectPage', 1);
                            $("#copyModal", args).modal('hide');
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
        },
        update: function (args) {
            $.post("/manage/companyofferprice/update",
                {
                    "offerName": $("#edit_offerName", args).val(),
                    "initialWeight": $("#edit_initialWeight", args).val(),
                    "additionalWeight": $("#edit_additionalWeight", args).val(),
                    "sendAreaId": $("#edit_sendAreaId", args).val(),
                    "dispAreaId": $("#edit_dispAreaId", args).val(),
                    "sortNo": $("#edit_sortNo", args).val(),
                    "isEnabled": $("#editYEnabled", args).prop('checked') ? "1" : "0",
                    "offerId": $("#edit_offerId", args).val(),
                    "ecList": $("#edit_EcList", args).val().join(','),
                    "collageList": $("#edit_Collage", args).val() && $("#edit_Collage", args).val().join(',')
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("更新成功！")
                            // $('#offerTable',args).bootstrapTable('refresh');
                            $("#offerTable", args).bootstrapTable('selectPage', 1);
                            $("#editModal", args).modal('hide');
                            $("#editForm", args).data("bootstrapValidator").resetForm(true);

                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        },
        remove: function (args) {
            var arrselections = $("#offerTable", args).bootstrapTable('getSelections');
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
                            // $('#offerTable',args).bootstrapTable('refresh');
                            $("#offerTable", args).bootstrapTable('selectPage', 1);
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