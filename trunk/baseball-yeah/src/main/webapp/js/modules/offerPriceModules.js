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
                                    offerAreaName: $.trim($("#offerAreaName").val()),
                                    provinceId: $.trim($("#provinceIds").val())
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
                    }, '#areaTable');

                $.ajax({
                    type: "GET",
                    url: "/manage/province/getProvinceNoDefault",
                    dataType: "json",
                    success: function (DATA) {
                        $("#addProvinceIds").select2({
                            data: DATA
                        });
                        $("#editProvinceIds").select2({
                            data: DATA
                        });
                    }
                });

                $.ajax({
                    type: "GET",
                    url: "/manage/province/getprovince",
                    dataType: "json",
                    success: function (DATA) {
                        $("#provinceIds").select2({
                            data: DATA
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
                    $("#areaTable").bootstrapTable('refresh');
                });

                $("#clearSearch").click(function () {
                    base.reset(".main-box-body");
                    $("#provinceIds").val("").trigger("change");
                });

                $('#addProvinceIds').select2({
                    placeholder: '请选择角色'
                });

                $('#addModal').on('shown.bs.modal', function () {
                    $('#addForm').data('bootstrapValidator').resetForm(true);
                })
            },
            add: function () {
                var self = this;
                $('#addOfferAreaName').val('');
                $('#addProvinceIds').val('').trigger("change");
                $('#addModal').modal({
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
                    }, "#addForm", self.create)
            },
            create: function () {
                $.post("/manage/offerPrice/add",
                    {
                        "offerAreaName": $("#addOfferAreaName").val(),
                        "provinceIds": $("#addProvinceIds").val() == null ? "" : $("#addProvinceIds").val().join(",")
                    }, function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                base.success("增加成功");
                                $("#areaTable").bootstrapTable('refresh');
                                $('#addModal').modal('hide');
                                
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
                var arrselections = $("#areaTable").bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                    return;
                }
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                $('#editOfferAreaId').val(arrselections[0].offerAreaId);
                $("#editOfferAreaName").val(arrselections[0].offerAreaName);
                $("#editProvinceIds").val(arrselections[0].provinceIds).trigger("change");

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
                    "#editForm", self.update
                )
            },
            update: function () {
                $.post("/manage/offerPrice/update",
                    {
                        "offerAreaId": $("#editOfferAreaId").val(),
                        "offerAreaName": $("#editOfferAreaName").val(),
                        "provinceIds": $("#editProvinceIds").val() == null ? "" : $("#editProvinceIds").val().join(",")
                    }, function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                base.success("编辑成功");
                                $("#areaTable").bootstrapTable('refresh');
                                $('#editModal').modal('hide');
                                $('#editForm').data('bootstrapValidator').resetForm(true);
                                $('#addForm').data('bootstrapValidator').resetForm(true);
                            } else {
                                base.error(data.message);
                                $('#editForm').find(".btn-primary").removeAttr("disabled");
                            }
                        } else {
                            base.error("更新失败!");
                        }
                    });
            },
            remove: function () {
                var arrselections = $("#areaTable").bootstrapTable(
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
                                        $("#areaTable").bootstrapTable('refresh');
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