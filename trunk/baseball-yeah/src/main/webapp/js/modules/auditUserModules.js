define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;

    return {
        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            datatable = base.datagrid({
                url: '/user/getauditusers',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            realName: $(
                                "#realName")
                                .val(),
                            phone: $("#phone")
                                .val(),
                            collegeId: $("#selcollage").val() == "请选择" ? "" : $("#selcollage").val()
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'phone',
                        title: '手机号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'realName',
                        title: '姓名',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'beSupervisor',
                        title: '角色',
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value == 0) {
                                return "员工";
                            } else {
                                return "负责人"
                            }
                        },
                        width: 150
                    },
                    {
                        field: 'storeCode',
                        title: '站点编号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'storeName',
                        title: '站点名称',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'signupTime',
                        title: '提交时间',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'verifyTime',
                        title: '审核时间',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'verifyStatus',
                        title: '状态',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "未完善";
                                case 1:
                                    return "审核中";
                                case 2:
                                    return "审核通过";
                                case 3:
                                    return "审核失败";
                            }
                        },
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'collegeFullName',
                        title: '校区',
                        width: 300
                    },
                    {
                        field: 'userId',
                        title: 'userId',
                        visible: false
                    }]
            }, '#userTable');

            $('#auditcollage').bootstrapTable({
                data:[],
                height: 200
            });

            $.ajax({
                type: "POST",
                url: "/manage/college/getcollageforsel",
                dataType: "json",
                success: function (data) {
                  
                    $("#selcollage").select2({
                        data: data.data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcollage').select2("val", null);
                }
            });

            $("#auditPost").click(function () {
                self.auditPost();
            })
            $("#btn_query").click(function () {
                $("#userTable").bootstrapTable('refresh');
            });
            $("#btn_audit").click(function () {
                self.audit();
            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
                $('#selcollage').select2("val", null);
                // $('#selcity').select2("val", null);
                // $("#selstore").val(" ").trigger("change");
            });

        },
        audit: function () {
            var self = this;
            var arrselections = $("#userTable")
                .bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            if (arrselections[0].beSupervisor == 0) {
                base.error("请选择负责人!");
                return;
            }
            if (arrselections[0].verifyStatus == 0) {
                base.error("审核未提交!");
                return;
            }

            $.post("/user/exp/getauditinfo", {
                "userId": arrselections[0].userId
            }, function (data) {
                if (data.success == 0) {
                    self.getUserInfo(data.data)
                }
                else {
                    base.error(data.message);
                }
            });
        },
        getUserInfo: function (model) {
            var self = this;
            $("#userId").val(model.userId);
            $("#lableUserName").html(model.userName);
            switch (model.verifyStatus) {
                case    0:
                    $("#lableVerifyStatus").html("未完善");
                    break;
                case   1:
                    $("#lableVerifyStatus").html("审核中");
                    break;
                case    2:
                    $("#lableVerifyStatus").html("审核通过");
                    break;
                case    3:
                    $("#lableVerifyStatus").html("审核失败");
                    break;
            }
            if (model.auditResultInfo) {
                $("#f1").prop("checked", model.auditResultInfo.f1 == "0" ? true : false);
                $("#fe1").prop("checked", model.auditResultInfo.f1 == "0" ? false : true);
                $("#f2").prop("checked", model.auditResultInfo.f2 == "0" ? true : false);
                $("#fe2").prop("checked", model.auditResultInfo.f2 == "0" ? false : true);
                $("#f3").prop("checked", model.auditResultInfo.f3 == "0" ? true : false);
                $("#fe3").prop("checked", model.auditResultInfo.f3 == "0" ? false : true);
                $("#f4").prop("checked", model.auditResultInfo.f4 == "0" ? true : false);
                $("#fe4").prop("checked", model.auditResultInfo.f4 == "0" ? false : true);
                $("#f5").prop("checked", model.auditResultInfo.f5 == "0" ? true : false);
                $("#fe5").prop("checked", model.auditResultInfo.f5 == "0" ? false : true);
                $("#f6").prop("checked", model.auditResultInfo.f6 == "0" ? true : false);
                $("#fe6").prop("checked", model.auditResultInfo.f6 == "0" ? false : true);
                $("#f7").prop("checked", model.auditResultInfo.f7 == "0" ? true : false);
                $("#fe7").prop("checked", model.auditResultInfo.f7 == "0" ? false : true);
                $("#f8").prop("checked", model.auditResultInfo.f8 == "0" ? true : false);
                $("#fe8").prop("checked", model.auditResultInfo.f8 == "0" ? false : true);
                $("#f9").prop("checked", model.auditResultInfo.f9 == "0" ? true : false);
                $("#fe9").prop("checked", model.auditResultInfo.f9 == "0" ? false : true);
                $("#f10").prop("checked", model.auditResultInfo.f10 == "0" ? true : false);
                $("#fe10").prop("checked", model.auditResultInfo.f10 == "0" ? false : true);
            }else {
                $("#f1").prop("checked", true );
                $("#fe1").prop("checked", false);
                $("#f2").prop("checked", true);
                $("#fe2").prop("checked", false);
                $("#f3").prop("checked", true);
                $("#fe3").prop("checked", false);
                $("#f4").prop("checked", true);
                $("#fe4").prop("checked",false);
                $("#f5").prop("checked",true);
                $("#fe5").prop("checked", false);
                $("#f6").prop("checked", true);
                $("#fe6").prop("checked", false);
                $("#f7").prop("checked", true);
                $("#fe7").prop("checked",false);
                $("#f8").prop("checked",true);
                $("#fe8").prop("checked", false);
                $("#f9").prop("checked",true);
                $("#fe9").prop("checked", false);
                $("#f10").prop("checked", true);
                $("#fe10").prop("checked", false);
            }

            $("#aduitrealName").val(model.realName);
            $("#aduitidentityNumber").val(model.identityNumber);
            $("#aduitCompanyName").val(model.expressCompanyName);
            $("#aduitStoreCode").val(model.storeCode);
            $("#aduitStoreName").val(model.storeName);
            $("#aduitLocation").val(model.location);
            $("#verifyRemark").val(model.verifyRemark);
            $("#labelimg1").unbind("click");
            $("#labelimg2").unbind("click");
            $("#labelimg3").unbind("click");
            if (model.photoList) {
                for (var i = 0; i < model.photoList.length; i++) {

                    if (model.photoList[i].fileUrl.indexOf("handheld") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg1").unbind("click");
                            $("#labelimg1").click(function () {
                                self.dialogImg("手持身份证正面照片", url)
                            });
                        })();
                    }
                    if (model.photoList[i].fileUrl.indexOf("idcard") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg2").unbind("click");
                            $("#labelimg2").click(function () {
                                self.dialogImg("身份证正面照片", url);
                            });
                        })();
                    }
                    if (model.photoList[i].fileUrl.indexOf("other") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg3").unbind("click");
                            $("#labelimg3").click(function () {
                                self.dialogImg("许可证正面照片", url);
                            });
                        })();
                    }
                }
            }

            $('#auditcollage').bootstrapTable('load', model.collegeList);


            $('#auditModal').modal();
        },
        auditPost: function () {
            $.post("/user/exp/audit", {
                "userId": $("#userId").val(),
                "verifyRemark": $("#verifyRemark").val(),
                "verifyStatus": ($("#fe1").prop('checked') && "3") || ($("#fe2").prop('checked') && "3") || ($("#fe3").prop('checked') && "3")
                || ($("#fe4").prop('checked') && "3") || ($("#fe5").prop('checked') && "3") || ($("#fe6").prop('checked') && "3")
                || ($("#fe7").prop('checked') && "3") || ($("#fe8").prop('checked') && "3") || ($("#fe9").prop('checked') && "3") || ($("#fe10").prop('checked') && "3") || "2",
                "verifyInfoString": '{"f1":' + ($("#f1").prop('checked') ? '"0"' : '"-1"' ) + ',"f2":' + ($("#f2").prop('checked') ? '"0"' : '"-1"') + ',"f3":' + ($("#f3").prop('checked') ? '"0"' : '"-1"') + ',"f4":' + ( $("#f4").prop('checked') ? '"0"' : '"-1"' ) + ',"f5":' + ( $("#f5").prop('checked') ? '"0"' : '"-1"') + ',"f6":' + ($("#f6").prop('checked') ? '"0"' : '"-1"') + ',"f7":' + ($("#f7").prop('checked') ? '"0"' : '"-1"') + ',"f8":' + ($("#f8").prop('checked') ? '"0"' : '"-1"') + ',"f9":' + ( $("#f9").prop('checked') ? '"0"' : '"-1"') + ',"f10":' + ($("#f10").prop('checked') ? '"0"' : '"-1"') + '}'
            }, function (data) {
                if (data.success == 0) {
                    $("#userTable").bootstrapTable('refresh');
                    $('#auditModal').modal('hide');
                    base.success("操作成功！")
                } else {
                    base.error(data.message);
                }
            });
        },
        dialogImg: function (title, url) {
            BootstrapDialog.show({
                cssClass: 'img-dialog',
                title: title,
                message: '<div style="text-align: center;"><img src="' + url + '" style="width: 670;height: 500;"></div>'
            });
        }
    };
});