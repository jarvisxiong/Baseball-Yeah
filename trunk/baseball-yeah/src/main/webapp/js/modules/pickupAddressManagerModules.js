define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */

    // 获取当前页第一个选择行
    function getfirstSelectInCurrentPage() {
        var currentPageItems = $('#pickupAddressTable').bootstrapTable('getData', true);
        var selection = null;
        $.each(currentPageItems, function () {
            if (this[0] == 1) {
                selection = this;
                return false;
            }
        });
        return selection;
    }

    return {
        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            base.datagrid({
                url: '/manage/pickupaddress/list',
                method: 'get',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            "storeName": $("#sel_storename").val().trim(),
                            "storePhone": $("#sel_phone").val().trim()
                        });
                },
                singleSelect: false,
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'storeName',
                        title: '商户名称',
                        sortable: true
                    },
                    {
                        field: 'storePhone',
                        title: '商户手机',
                        sortable: true
                    },
                    {
                        field: 'address',
                        title: '详细地址',
                        sortable: true
                    },
                    {
                        field: 'collegeNames',
                        title: '学校',
                        sortable: true
                    },
                    {
                        field: 'createTime',
                        title: '添加时间',
                        sortable: true
                    }]
            }, '#pickupAddressTable');

            $.ajax({
                type: "POST",
                url: "/manage/college/getCollageForSel",
                dataType: "json",
                success: function (data) {
                    $("#sel_college").select2({
                        data: data.data
                    });
                    $("#add_college").select2({
                        data: data.data
                    });
                    $("#edit_college").select2({
                        data: data.data
                    });
                }
            });

            //region 省市区先去掉
            /*$.ajax({
             type: "GET",
             url: "/manage/province/getprovince",
             dataType: "json",
             success: function (data) {
             $("#sel_province").select2({
             data: data
             });
             $("#add_province").select2({
             data: data
             });
             $("#edit_province").select2({
             data: data
             });
             }
             });

             $.ajax({
             type: "GET",
             url: "/manage/city/getcity",
             dataType: "json",
             success: function (data) {
             $("#sel_city").select2({
             data: data
             });
             $("#add_city").select2({
             data: data
             });
             $("#edit_city").select2({
             data: data
             });
             }
             });

             $.ajax({
             type: "GET",
             url: "/manage/county/getcounty",
             dataType: "json",
             success: function (data) {
             $("#sel_county").select2({
             data: data
             });
             $("#add_county").select2({
             data: data
             });
             $("#edit_county").select2({
             data: data
             });
             }
             });*/
            //endregion

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
                $("#pickupAddressTable").bootstrapTable('refresh');
            });
            $('#addModal').on('shown.bs.modal', function () {
                $("#add_college").val("").trigger("change");
                $('#addForm').data('bootstrapValidator').resetForm(true);
                $("#add_address").val("");
            });

            //region 省市区处理先去掉
            /*$("#add_province").on("change",
             function (e) {
             var provinceId = $("#add_province").val();
             if (provinceId == "" || provinceId == null) {
             $("#add_city").val('');
             $("#add_city").attr("disabled", true);
             return;
             }
             $("#add_city").attr("disabled", false);
             $.ajax({
             type: "GET",
             url: "/manage/city/getcity",
             data: {
             "provinceId": provinceId
             },
             dataType: "json",
             success: function (data) {
             $("#add_city").select2({
             data: data
             });
             }
             });
             });

             $("#add_city").on("change",
             function (e) {
             var cityId = $("#add_city").val();
             if (cityId == "" || cityId == null) {
             $("#add_county").val('');
             $("#add_county").attr("disabled", true);
             return;
             }
             $("#add_county").attr("disabled", false);
             $.ajax({
             type: "GET",
             url: "/manage/county/getcounty",
             data: {
             "cityId": cityId
             },
             dataType: "json",
             success: function (data) {
             $("#add_county").select2({
             data: data
             });
             }
             });
             });

             $("#edit_province").on("change",
             function (e) {
             var provinceId = $("#edit_province").val();
             if (provinceId == "" || provinceId == null) {
             $("#edit_city").val('');
             $("#edit_city").attr("disabled", true);
             return;
             }
             $("#edit_city").attr("disabled", false);
             $.ajax({
             type: "GET",
             url: "/manage/city/getcity",
             data: {
             "provinceId": provinceId
             },
             dataType: "json",
             success: function (data) {
             var control = $('#edit_city');
             var opVal = control.val();
             if (opVal == null) {
             opVal = getfirstSelectInCurrentPage().cityId;
             }
             $("#edit_city").select2({
             data: data
             });
             $("#edit_city").val(opVal).trigger("change");
             }
             });
             });

             $("#edit_city").on("change",
             function (e) {
             var cityId = $("#edit_city").val();
             if (cityId == "" || cityId == null) {
             $("#edit_county").val('');
             $("#edit_county").attr("disabled", true);
             return;
             }
             $("#edit_county").attr("disabled", false);
             $.ajax({
             type: "GET",
             url: "/manage/county/getcounty",
             data: {
             "cityId": cityId
             },
             dataType: "json",
             success: function (data) {
             var control = $('#edit_county');
             var opVal = control.val();
             if (opVal == null) {
             opVal = getfirstSelectInCurrentPage().countyId;
             }
             $("#edit_county").select2({
             data: data
             });
             $("#edit_county").val(opVal).trigger("change");
             }
             });
             });*/
            //endregion

        },
        add: function () {
            var self = this;
            $('#addModal').modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                    add_store: {
                        validators: {
                            notEmpty: {
                                message: '商户名称不能为空'
                            }
                        },
                        stringLength: {
                            min: 0,
                            max: 50,
                            message: '商户名长度不能超过50'
                        }
                    },
                    add_phone: {
                        validators: {
                            notEmpty: {
                                message: '商户手机不能为空'
                            },
                            regexp: {
                                regexp: /^[1]+[3,5,7,8]+\d{9}$/,
                                message: '商户手机号格式不正确'
                            }
                        }
                    },
                    add_college: {
                        validators: {
                            notEmpty: {
                                message: '学校不能为空'
                            }
                        }
                    },
                }
            }, "#addForm", self.create);
        },
        create: function () {
            $.post("/manage/pickupaddress/add",
                {
                    "storeName": $("#add_store").val(),
                    "storePhone": $("#add_phone").val(),
                    "colleges": $("#add_college").val().join(),
                    "address": $("#add_address").val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("添加成功！");
                            $("#pickupAddressTable").bootstrapTable('refresh');
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
            var arrselections = $("#pickupAddressTable").bootstrapTable('getSelections');
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
                backdrop: 'static'
            });
            $("#edit_pickupaddressid").val(arrselections[0].pickupAddressId)
            $("#edit_store").val(arrselections[0].storeName)
            $("#edit_phone").val(arrselections[0].storePhone)
            if (arrselections[0].collegeIds) {
                var collegeIds = arrselections[0].collegeIds;
                $("#edit_college").val(collegeIds.split(',')).trigger("change");
            } else {
                $("#edit_college").val("").trigger("change");
            }
            $("#edit_address").val(arrselections[0].address);

            $('#editForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    edit_store: {
                        validators: {
                            notEmpty: {
                                message: '商户名称不能为空'
                            },
                            stringLength: {
                                min: 0,
                                max: 50,
                                message: '商户名长度不能超过50'
                            }
                        }
                    },
                    edit_phone: {
                        validators: {
                            notEmpty: {
                                message: '商户电话不能为空'
                            },
                            regexp: {
                                regexp: /^[1]+[3,5,7,8]+\d{9}$/,
                                message: '商户手机号格式不正确'
                            }
                        }
                    },
                    edit_college: {
                        validators: {
                            notEmpty: {
                                message: '学校不能为空'
                            }
                        }
                    },
                }
            }).on('success.form.bv', function (e) {
                e.preventDefault();
                self.update();
            });
            $('#editModal').modal();
        },
        update: function () {
            $.post("/manage/pickupaddress/edit",
                {
                    "pickupAddressId": $("#edit_pickupaddressid").val(),
                    "storeName": $("#edit_store").val().trim(),
                    "storePhone": $("#edit_phone").val().trim(),
                    "address": $("#edit_address").val().trim(),
                    "colleges": $("#edit_college").val().join(),
                },
                function (data, status) {
                    if (status == "success") {

                        if (data.success == 0) {
                            base.success("更新成功！");
                            $("#pickupAddressTable").bootstrapTable('refresh');
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
            var arrselections = $("#pickupAddressTable").bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var pickupAddressInfos = [];
            for (var i = 0; i < arrselections.length; i++) {
                pickupAddressInfos.push(arrselections[i].pickupAddressId);
            }

            base.cancel({title: "删除", text: "您确定要删除吗？"}, function () {
                $.post("/manage/pickupaddress/del", {
                    "pickupAddressIds": pickupAddressInfos.join()
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("删除成功！");
                            $("#pickupAddressTable").bootstrapTable('refresh');
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