define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;

    return {
        init: function (panel) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;


            $("#selState", panel).select2({
                data: [{id: " ", text: '全部'},{id: 0, text: '未提交'}, {id: 1, text: '已提交'}, {id: 3, text: '审核失败'}],
                placeholder: '请选择',
                allowClear: true
            });
            $("#selState", panel).select2().val(1).trigger("change");

            $('#starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView: 2
            });

            $('#endtimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView: 2
            });

            $('#starttimePicker1', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView: 2
            });

            $('#endtimePicker1', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView: 2
            });


            $.ajax({
                type: "POST",
                url: "/manage/college/getcollageforsel",
                dataType: "json",
                success: function (data) {
                    $("#selcollage", panel).select2({
                        data: data.data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcollage', panel).select2("val", null);
                }
            });

            /*** 加载城市列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#selcity", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcity', panel).select2("val", null);
                }
            });

            datatable = base.datagrid({
                url: '/order/verify/getauditusers',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            userName: $("#userName", panel).val(),//登录账号
                            realName: $("#realName", panel).val(),//姓名
                            gender: $("#gender", panel).val(),//性别
                            collegeId: $("#selcollage", panel).val() == "请选择" ? "" : $("#selcollage", panel).val(),//校区
                            cityId: $("#selcity", panel).val() == "请选择" ? "" : $("#selcity", panel).val(),//城市
                            startDate: $('#submit_startdate', panel).val(),
                            endDate: $('#submit_enddate', panel).val(),
                            idNo: $("#idNo", panel).val(),//证件号码
                            verifyStatus: $("#selState", panel).val(),//审核状态
                            auditStartDate: $("#audit_startdate", panel).val(),//审核开始时间
                            auditEndDate: $("#audit_enddate", panel).val(),//审核结束时间
                            remark: $("#remark", panel).val(),//标签
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
                        field: 'remark',
                        title: '标签',
                        sortable: false,
                        width: 80,
                        formatter: function (value, row, index) {
                            return '<a id="btnLabel" href="#" class="table-link" pid="' + row["userId"] + '" value="' + value + '"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span></a>';
                        }
                    },
                    {
                        field: 'userId',
                        title: 'userId',
                        visible: false
                    }]
            }, '#userTable', panel);

            //给小派打标记
            $('#userTable').on('click', 'a#btnLabel', function () {
                //清除缓存
                $('#labelForm [name="pUserId"]', panel).val(pUserId);
                $('#labelForm [name="remark"]', panel).val(remark);

                var remark = this.getAttribute("value");
                var pUserId = this.getAttribute("pid");
                $("#labelModal").modal("show");
                $('#labelModal', panel).on('shown.bs.modal', function () {
                    $('#labelForm [name="pUserId"]', panel).val(pUserId);
                    $('#labelForm [name="remark"]', panel).val(remark);
                });

            });

            //保存标记
            $("#btnLabelSave", panel).click(function () {
                $.post("/order/verify/setlabel", {
                    pUserId: $('#labelForm [name="pUserId"]', panel).val(),
                    remark: $('#labelForm [name="remark"]', panel).val()
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $('#labelForm [name="pUserId"]', panel).val("");
                            $('#labelForm [name="remark"]', panel).val("");
                            $('#labelModal', panel).modal('hide');
                            base.success("标记成功！");
                            $("#btn_query").click();
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });

            $("#auditPost", panel).click(function () {
                self.auditPost(panel);
            });

            $("#btn_query", panel).click(function () {
                //$("#userTable",panel).bootstrapTable('refresh',{"query": {"offset": 0}});
                if ((new Date(Date.parse($('#audit_enddate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#audit_startdate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "结束时间不能小于开始时间!", "info");
                    return;
                }
                if ((new Date(Date.parse($('#submit_enddate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#submit_startdate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#userTable", panel).bootstrapTable('selectPage', 1);

            });
            $("#btn_audit", panel).click(function () {
                self.audit(panel);
            });
            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header", panel));
                $("#gender", panel).val(null);
                $("#selcity", panel).select2("val", null);
                $("#selState", panel).select2("value", 1);
                $("#audit_startdate", panel).val("");
                $("#audit_enddate", panel).val("");
            });

        },
        audit: function (panel) {
            var self = this;
            var arrselections = $("#userTable", panel)
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
                    self.getUserInfo(data.data, panel)
                }
                else {
                    base.error(data.message);
                }
            });
        },
        getUserInfo: function (model, panel) {
            var self = this;
            $("#userId", panel).val(model.userId);
            $("#lableUserName", panel).html(model.userName);
            switch (model.verifyStatus) {
                case    0:
                    $("#lableVerifyStatus", panel).html("未提交");
                    break;
                case   1:
                    $("#lableVerifyStatus", panel).html("已提交");
                    break;
                case    2:
                    $("#lableVerifyStatus", panel).html("审核通过");
                    break;
                case    3:
                    $("#lableVerifyStatus").html("审核失败");
                    break;
            }
            if (model.auditResultInfo) {
                $("#f1", panel).prop("checked", model.auditResultInfo.f1 == "0" ? true : false);
                $("#fe1", panel).prop("checked", model.auditResultInfo.f1 == "0" ? false : true);
                $("#f2", panel).prop("checked", model.auditResultInfo.f2 == "0" ? true : false);
                $("#fe2", panel).prop("checked", model.auditResultInfo.f2 == "0" ? false : true);
                $("#f3", panel).prop("checked", model.auditResultInfo.f3 == "0" ? true : false);
                $("#fe3", panel).prop("checked", model.auditResultInfo.f3 == "0" ? false : true);
                $("#f4", panel).prop("checked", model.auditResultInfo.f4 == "0" ? true : false);
                $("#fe4", panel).prop("checked", model.auditResultInfo.f4 == "0" ? false : true);
                $("#f5", panel).prop("checked", model.auditResultInfo.f5 == "0" ? true : false);
                $("#fe5", panel).prop("checked", model.auditResultInfo.f5 == "0" ? false : true);
                $("#f6", panel).prop("checked", model.auditResultInfo.f6 == "0" ? true : false);
                $("#fe6", panel).prop("checked", model.auditResultInfo.f6 == "0" ? false : true);
                $("#f7", panel).prop("checked", model.auditResultInfo.f7 == "0" ? true : false);
                $("#fe7", panel).prop("checked", model.auditResultInfo.f7 == "0" ? false : true);

            } else {
                $("#f1", panel).prop("checked", true);
                $("#fe1", panel).prop("checked", false);
                $("#f2", panel).prop("checked", true);
                $("#fe2", panel).prop("checked", false);
                $("#f3", panel).prop("checked", true);
                $("#fe3", panel).prop("checked", false);
                $("#f4", panel).prop("checked", true);
                $("#fe4", panel).prop("checked", false);
                $("#f5", panel).prop("checked", true);
                $("#fe5", panel).prop("checked", false);
                $("#f6", panel).prop("checked", true);
                $("#fe6", panel).prop("checked", false);
                $("#f7", panel).prop("checked", true);
                $("#fe7", panel).prop("checked", false);
            }
            $("#aduitidentityNumber", panel).val(model.identityNumber);
            $("#aduitrealName", panel).val(model.realName);
            $("#aduitidentityNumber", panel).val(model.identityNumber);
            $("#aduitCompanyName", panel).val(model.expressCompanyName);
            $("#verifyRemark", panel).val(model.verifyRemark);

            $("#auditCollegeFullName", panel).val(model.collegeFullName);
            $("#auditCollegeAddress", panel).val(model.collegeAddress);
            $("#auditDormitoryAddress", panel).val(model.dormitoryAddress);

            $("#labelimg1", panel).unbind("click");
            $("#labelimg2", panel).unbind("click");
            if (model.photoList) {
                for (var i = 0; i < model.photoList.length; i++) {
                    if (model.photoList[i].fileUrl.indexOf("handheld") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg1", panel).unbind("click");
                            $("#labelimg1", panel).click(function () {
                                self.dialogImg("手持身份证正面照片", url)
                            });
                        })();
                    }
                    if (model.photoList[i].fileUrl.indexOf("idcard") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg2", panel).unbind("click");
                            $("#labelimg2", panel).click(function () {
                                self.dialogImg("身份证正面照片", url);
                            });
                        })();
                    }
                }
            }


            $('#auditModal', panel).modal();
        },
        auditPost: function (panel) {
            $.post("/order/verify/audit", {
                "userId": $("#userId", panel).val(),
                "verifyRemark": $("#verifyRemark", panel).val(),
                "verifyStatus": ($("#fe1", panel).prop('checked') && "3") || ($("#fe2", panel).prop('checked') && "3") || ($("#fe3", panel).prop('checked') && "3")
                || ($("#fe4", panel).prop('checked') && "3") || ($("#fe5", panel).prop('checked') && "3") || ($("#fe6", panel).prop('checked') && "3") || ($("#fe7", panel).prop('checked') && "3") || "2",
                "verifyInfoString": '{"f1":' + ($("#f1", panel).prop('checked') ? '"0"' : '"-1"' ) + ',"f2":' + ($("#f2", panel).prop('checked') ? '"0"' : '"-1"') + ',"f3":' +
                ($("#f3", panel).prop('checked') ? '"0"' : '"-1"') + ',"f4":' + ( $("#f4", panel).prop('checked') ? '"0"' : '"-1"' )
                + ',"f5":' + ( $("#f5", panel).prop('checked') ? '"0"' : '"-1"' ) + ',"f6":' + ( $("#f6", panel).prop('checked') ? '"0"' : '"-1"' )
                + ',"f7":' + ( $("#f7", panel).prop('checked') ? '"0"' : '"-1"' ) + '}'
            }, function (data) {
                if (data.success == 0) {
                    $("#userTable", panel).bootstrapTable('refresh');
                    $('#auditModal', panel).modal('hide');
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
                message: '<div style="text-align: center;"><img src="' + url + '" style="width: 670px;height: 500px;"></div>'
            });
        }
    };
});