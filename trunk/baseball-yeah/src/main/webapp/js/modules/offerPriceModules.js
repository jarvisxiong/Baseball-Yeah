define(
    ['base'],
    function (base) {
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

                base.datagrid(
                    {
                        url: '/manage/offerPrice/all',
                        queryParams: function (params) {
                            return $.extend(params,
                                {
                                    offerAreaName: $.trim($("#offerAreaName",args).val()),
                                    provinceId: $.trim($("#provinceIds",args).val())
                                });
                        },
                        columns: [
                            {
                                checkbox: true
                            },
                            {
                                field: 'offerAreaId',
                                title: '区域编号',
                                sortable: true,
                                visible: false
                            },
                            {
                                field: 'offerAreaName',
                                title: '区域名称',
                                sortable: true,
                                width: 400
                            },
                            {
                                field: 'provinceIds',
                                title: '拥有身份Id',
                                sortable: true,
                                visible: false
                            },
                            {
                                field: 'provinceNames',
                                title: '包括省份',
                                sortable: true,
                                width: 400
                            }
                        ]
                    }, '#areaTable',args);

                $.ajax({
                    type: "GET",
                    url: "/manage/province/getProvinceNoDefault",
                    dataType: "json",
                    success: function (DATA) {
                        $("#addProvinceIds",args).select2({
                            data: DATA
                        });
                        $("#editProvinceIds",args).select2({
                            data: DATA
                        });
                    }
                });

                $.ajax({
                    type: "GET",
                    url: "/manage/province/getprovince",
                    dataType: "json",
                    success: function (DATA) {
                        $("#provinceIds",args).select2({
                            data: DATA
                        });
                    }
                });

                $("#btn_add",args).click(function () {
                    self.add(args);
                });
                $("#btn_edit",args).click(function () {
                    self.edit(args);
                });
                $("#btn_delete",args).click(function () {
                    self.remove(args);
                });
                $("#btn_query",args).click(function () {
                    // $("#areaTable",args).bootstrapTable('refresh');
                    $("#areaTable",args).bootstrapTable('selectPage', 1);
                });

                $("#clearSearch",args).click(function () {
                    base.reset(".main-box-body");
                    $("#provinceIds",args).val("").trigger("change");
                });

                $('#addProvinceIds',args).select2({
                    placeholder: '请选择角色'
                });

                $('#addModal',args).on('shown.bs.modal', function () {
                    $('#addForm',args).data('bootstrapValidator').resetForm(true);
                });
                
                $("#editModal", args).on('hidden.bs.modal', function() {
                    $("#editForm", args).data('bootstrapValidator').destroy();
                    $("#editForm", args).data('bootstrapValidator', null);
                });
            },
            add: function (args) {
                var self = this;
                $('#addOfferAreaName',args).val('');
                $('#addProvinceIds',args).val('').trigger("change");
                $('#addModal',args).modal({
                    keyboard: false,
                    backdrop: 'static'
                });
                base.validator(
                    {
                        message: 'This value is not valid',
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            addOfferAreaName: {
                                validators: {
                                    notEmpty: {
                                        message: '区域名称不能为空'
                                    }
                                }
                            },
                            addProvinceIds: {
                                validators: {
                                    notEmpty: {
                                        message: '省份不能为空'
                                    }
                                }
                            }
                        }
                    }, "#addForm", self.create,args)
            },
            create: function (args) {
                $.post("/manage/offerPrice/add",
                    {
                        "offerAreaName": $("#addOfferAreaName",args).val(),
                        "provinceIds": $("#addProvinceIds",args).val() == null ? "" : $("#addProvinceIds",args).val().join(",")
                    }, function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                base.success("增加成功");
                                // $("#areaTable",args).bootstrapTable('refresh');
                                $("#areaTable",args).bootstrapTable('selectPage', 1);
                                $('#addModal',args).modal('hide');
                                
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
                var arrselections = $("#areaTable",args).bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                    return;
                }
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                $('#editOfferAreaId',args).val(arrselections[0].offerAreaId);
                $("#editOfferAreaName",args).val(arrselections[0].offerAreaName);
                $("#editProvinceIds",args).val(arrselections[0].provinceIds).trigger("change");

                $('#editModal',args).modal({
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
                            editOfferAreaName: {
                                validators: {
                                    notEmpty: {
                                        message: '区域名称不能为空'
                                    }
                                }
                            }
                            ,
                            editProvinceIds: {
                                validators: {
                                    notEmpty: {
                                        message: '省份不能为空'
                                    }
                                }
                            }
                        }
                    },
                    "#editForm", self.update,args
                )
            },
            update: function (args) {
                $.post("/manage/offerPrice/update",
                    {
                        "offerAreaId": $("#editOfferAreaId",args).val(),
                        "offerAreaName": $("#editOfferAreaName",args).val(),
                        "provinceIds": $("#editProvinceIds",args).val() == null ? "" : $("#editProvinceIds",args).val().join(",")
                    }, function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                base.success("编辑成功");
                                // $("#areaTable",args).bootstrapTable('refresh');
                                $("#areaTable",args).bootstrapTable('selectPage', 1);
                                $('#editModal',args).modal('hide');
                                $('#editForm',args).data('bootstrapValidator').resetForm(true);
                                $('#addForm',args).data('bootstrapValidator').resetForm(true);
                            } else {
                                base.error(data.message);
                                $('#editForm',args).find(".btn-primary").removeAttr("disabled");
                            }
                        } else {
                            base.error("更新失败!");
                        }
                    });
            },
            remove: function (args) {
                var arrselections = $("#areaTable",args).bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                var offerAreaId = arrselections[0].offerAreaId;

                base.cancel({
                        title: "删除区域",
                        text: "您确定要删除此区域吗？"
                    },
                    function () {
                        $.post("/manage/offerPrice/del",
                            {
                                "offerAreaId": offerAreaId
                            },
                            function (data, status) {
                                if (status == "success") {
                                    if (data.success == 0) {
                                        base.success("删除成功");
                                        // $("#areaTable",args).bootstrapTable('refresh');
                                        $("#areaTable",args).bootstrapTable('selectPage', 1);
                                    } else {
                                        base.error(obj.message);
                                    }
                                } else {
                                    base.error("删除失败");
                                }
                            });
                    });
            }
        };
    });