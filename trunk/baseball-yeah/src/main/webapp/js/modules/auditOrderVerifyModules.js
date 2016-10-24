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


            $("#selState").select2({
                data: [{id: 0, text: '未提交'}, {id: 1, text: '已提交'}, {id: 2, text: '审核通过'}, {id: 3, text: '审核失败'}],
                placeholder: '请选择',
                allowClear: true
            })
            $('#selState').select2("val", null);

            $('#starttimePicker').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });

            $('#endtimePicker').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });

            $('#starttimePicker1').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });

            $('#endtimePicker1').datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
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

            /*** 加载城市列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#selcity").select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcity').select2("val", null);
                }
            });

            datatable = base.datagrid({
                url: '/order/verify/getauditusers',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            userName: $("#userName").val(),//登录账号
                            realName: $("#realName").val(),//姓名
                            gender: $("#gender").val(),//性别
                            collegeId: $("#selcollage").val() == "请选择" ? "" : $("#selcollage").val(),//校区
                            cityName: $("#selcity").val() == "请选择" ? "" : $("#selcity").val(),//城市
                            startDate: $('#submit_startdate').val(),
                            endDate: $('#submit_enddate').val(),
                            idNo: $("#idNo").val(),//证件号码
                            verifyStatus: $("#selState").val(),//审核状态
                            auditStartDate: $("#audit_startdate").val(),//审核开始时间
                            auditEndDate: $("#audit_enddate").val()//审核结束时间
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'userName',
                        title: '登录账号',
                        sortable: true,
                        width: 100

                    },
                    {
                        field: 'realName',
                        title: '姓名',
                        sortable: true,
                        width: 80
                    },
                    {
                        field: 'gender',
                        title: '性别',
                        sortable: true,
                        width: 50
                    },
                    {
                        field: 'cityName',
                        title: '城市',
                        sortable: true,
                        width: 50
                    },
                    {
                        field: 'collegeFullName',
                        title: '校区',
                        width: 300
                    },
                    {
                        field: 'collegeAddress',
                        title: '学校地址',
                        sortable: true,
                        width: 300
                    },
                    {
                        field: 'dormitoryAddress',
                        title: '宿舍地址',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'idNo',
                        title: '证件号码',
                        width: 150
                    },
                    {
                        field: 'signupTime',
                        title: '提交时间',
                        sortable: true,
                        width: 180
                    },
                    {
                        field: 'verifyTime',
                        title: '审核时间',
                        sortable: true,
                        width: 180
                    },
                    {
                        field: 'verifyStatus',
                        title: '状态',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "未提交";
                                case 1:
                                    return "已提交";
                                case 2:
                                    return "审核通过";
                                case 3:
                                    return "审核失败";
                            }
                        },
                        sortable: true,
                        width: 80
                    },
                    {
                        field: 'userId',
                        title: 'userId',
                        visible: false
                    }]
            }, '#userTable');

            $("#auditPost").click(function () {
                self.auditPost();
            });

            $("#btn_query").click(function () {

                $("#userTable").bootstrapTable('refresh');

            });
            $("#btn_audit").click(function () {
                self.audit();
            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
                $("#gender").val(null);
                $("#selcollage").select2("val", null);
                $("#selcity").select2("val", null);
                $("#selState").select2("val", null);
                $("#audit_startdate").val("");
                $("#audit_enddate").val("");

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
            if (arrselections[0].verifyStatus == 0) {
                base.error("审核未提交!");
                return;
            }

            $.post("/order/verify/getauditinfo", {
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
                    $("#lableVerifyStatus").html("未提交");
                    break;
                case   1:
                    $("#lableVerifyStatus").html("已提交");
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

            } else {
                $("#f1").prop("checked", true);
                $("#fe1").prop("checked", false);
                $("#f2").prop("checked", true);
                $("#fe2").prop("checked", false);
                $("#f3").prop("checked", true);
                $("#fe3").prop("checked", false);
                $("#f4").prop("checked", true);
                $("#fe4").prop("checked", false);
                $("#f5").prop("checked", true);
                $("#fe5").prop("checked", false);
                $("#f6").prop("checked", true);
                $("#fe6").prop("checked", false);
                $("#f7").prop("checked", true);
                $("#fe7").prop("checked", false);
            }
            $("#aduitidentityNumber").val(model.identityNumber);
            $("#aduitrealName").val(model.realName);
            $("#aduitidentityNumber").val(model.identityNumber);
            $("#aduitCompanyName").val(model.expressCompanyName);
            $("#verifyRemark").val(model.verifyRemark);

            $("#auditCollegeFullName").val(model.collegeFullName);
            $("#auditCollegeAddress").val(model.collegeAddress);
            $("#auditDormitoryAddress").val(model.dormitoryAddress);

            $("#labelimg1").unbind("click");
            $("#labelimg2").unbind("click");
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
                }
            }


            $('#auditModal').modal();
        },
        auditPost: function () {
            $.post("/order/verify/audit", {
                "userId": $("#userId").val(),
                "verifyRemark": $("#verifyRemark").val(),
                "verifyStatus": ($("#fe1").prop('checked') && "3") || ($("#fe2").prop('checked') && "3") || ($("#fe3").prop('checked') && "3")
                || ($("#fe4").prop('checked') && "3") || ($("#fe5").prop('checked') && "3") || ($("#fe6").prop('checked') && "3") || ($("#fe7").prop('checked') && "3") || "2",
                "verifyInfoString": '{"f1":' + ($("#f1").prop('checked') ? '"0"' : '"-1"' ) + ',"f2":' + ($("#f2").prop('checked') ? '"0"' : '"-1"') + ',"f3":' +
                ($("#f3").prop('checked') ? '"0"' : '"-1"') + ',"f4":' + ( $("#f4").prop('checked') ? '"0"' : '"-1"' )
                + ',"f5":' + ( $("#f5").prop('checked') ? '"0"' : '"-1"' ) + ',"f6":' + ( $("#f6").prop('checked') ? '"0"' : '"-1"' )
                + ',"f7":' + ( $("#f7").prop('checked') ? '"0"' : '"-1"' ) + '}'
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