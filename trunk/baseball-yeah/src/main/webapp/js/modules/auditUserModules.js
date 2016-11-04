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

            datatable = base.datagrid({
                url: '/user/getauditusers',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            realName: $(
                                "#realName",panel)
                                .val(),
                            phone: $("#phone",panel)
                                .val(),
                            collegeId: $("#selcollage",panel).val() == "请选择" ? "" : $("#selcollage",panel).val()
                        });
                },
                onLoadSuccess : function(data) {
					// 表格控件不支持高度自适应
					var tableHeight = 105
							+ $("#userTable", panel).find("thead")
									.height()
							+ $("#userTable", panel).find("tbody")
									.height()
							+ $("#userTable", panel).parent().parent()
									.parent().parent().find(".clearfix")
									.height();
					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
						tableHeight = 105;
					}
					$("#userTable", panel).bootstrapTable('resetView', {
						"height" : tableHeight
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
            }, '#userTable',panel);

            $('#auditcollage',panel).bootstrapTable({
                data:[],
                height: 200
            });

            $.ajax({
                type: "POST",
                url: "/manage/college/getcollageforsel",
                dataType: "json",
                success: function (data) {
                  
                    $("#selcollage",panel).select2({
                        data: data.data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcollage',panel).select2("val", null);
                }
            });

            $("#auditPost",panel).click(function () {
                self.auditPost(panel);
            })
            $("#btn_query",panel).click(function () {
                //$("#userTable",panel).bootstrapTable('refresh',{"query": {"offset": 0}});
                $("#userTable", panel).bootstrapTable('selectPage', 1);
            });
            $("#btn_audit",panel).click(function () {
                self.audit(panel);
            });
            $("#clearSearch",panel).click(function () {
                base.reset($(".main-box-header",panel));
                $('#selcollage',panel).select2("val", null);
            });
        },
        audit: function (panel) {
            var self = this;
            var arrselections = $("#userTable",panel)
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
                    self.getUserInfo(data.data,panel)
                }
                else {
                    base.error(data.message);
                }
            });
        },
        getUserInfo: function (model,panel) {
            var self = this;
            $("#userId",panel).val(model.userId);
            $("#lableUserName",panel).html(model.userName);
            switch (model.verifyStatus) {
                case    0:
                    $("#lableVerifyStatus",panel).html("未完善");
                    break;
                case   1:
                    $("#lableVerifyStatus",panel).html("审核中");
                    break;
                case    2:
                    $("#lableVerifyStatus",panel).html("审核通过");
                    break;
                case    3:
                    $("#lableVerifyStatus",panel).html("审核失败");
                    break;
            }
            if (model.auditResultInfo) {
                $("#auditStoref1",panel).prop("checked", model.auditResultInfo.f1 == "0" ? true : false);
                $("#auditStorefe1",panel).prop("checked", model.auditResultInfo.f1 == "0" ? false : true);
                $("#auditStoref2",panel).prop("checked", model.auditResultInfo.f2 == "0" ? true : false);
                $("#auditStorefe2",panel).prop("checked", model.auditResultInfo.f2 == "0" ? false : true);
                $("#auditStoref3",panel).prop("checked", model.auditResultInfo.f3 == "0" ? true : false);
                $("#auditStorefe3",panel).prop("checked", model.auditResultInfo.f3 == "0" ? false : true);
                $("#auditStoref4",panel).prop("checked", model.auditResultInfo.f4 == "0" ? true : false);
                $("#auditStorefe4",panel).prop("checked", model.auditResultInfo.f4 == "0" ? false : true);
                $("#auditStoref5",panel).prop("checked", model.auditResultInfo.f5 == "0" ? true : false);
                $("#auditStorefe5",panel).prop("checked", model.auditResultInfo.f5 == "0" ? false : true);
                $("#auditStoref6",panel).prop("checked", model.auditResultInfo.f6 == "0" ? true : false);
                $("#auditStorefe6",panel).prop("checked", model.auditResultInfo.f6 == "0" ? false : true);
                $("#auditStoref7",panel).prop("checked", model.auditResultInfo.f7 == "0" ? true : false);
                $("#auditStorefe7",panel).prop("checked", model.auditResultInfo.f7 == "0" ? false : true);
                $("#auditStoref8",panel).prop("checked", model.auditResultInfo.f8 == "0" ? true : false);
                $("#auditStorefe8",panel).prop("checked", model.auditResultInfo.f8 == "0" ? false : true);
                //$("#auditStoref9",panel).prop("checked", model.auditResultInfo.f9 == "0" ? true : false);
                //$("#auditStorefe9",panel).prop("checked", model.auditResultInfo.f9 == "0" ? false : true);
                $("#auditStoref10",panel).prop("checked", model.auditResultInfo.f10 == "0" ? true : false);
                $("#auditStorefe10",panel).prop("checked", model.auditResultInfo.f10 == "0" ? false : true);
            }else {
                $("#auditStoref1",panel).prop("checked", true );
                $("#auditStorefe1",panel).prop("checked", false);
                $("#auditStoref2",panel).prop("checked", true);
                $("#auditStorefe2",panel).prop("checked", false);
                $("#auditStoref3",panel).prop("checked", true);
                $("#auditStorefe3",panel).prop("checked", false);
                $("#auditStoref4",panel).prop("checked", true);
                $("#auditStorefe4",panel).prop("checked",false);
                $("#auditStoref5",panel).prop("checked",true);
                $("#auditStorefe5",panel).prop("checked", false);
                $("#auditStoref6",panel).prop("checked", true);
                $("#auditStorefe6",panel).prop("checked", false);
                $("#auditStoref7",panel).prop("checked", true);
                $("#auditStorefe7",panel).prop("checked",false);
                $("#auditStoref8",panel).prop("checked",true);
                $("#auditStorefe8",panel).prop("checked", false);
                //$("#auditStoref9",panel).prop("checked",true);
                //$("#auditStorefe9",panel).prop("checked", false);
                $("#auditStoref10",panel).prop("checked", true);
                $("#auditStorefe10",panel).prop("checked", false);
            }

            $("#aduitrealName",panel).val(model.realName);
            $("#aduitidentityNumber",panel).val(model.identityNumber);
            $("#aduitCompanyName",panel).val(model.expressCompanyName);
            $("#aduitStoreCode",panel).val(model.storeCode);
            $("#aduitStoreName",panel).val(model.storeName);
            $("#aduitLocation",panel).val(model.location);
            $("#verifyRemark",panel).val(model.verifyRemark);
            $("#labelimg1",panel).unbind("click");
            $("#labelimg2",panel).unbind("click");
            $("#labelimg3",panel).unbind("click");
            if (model.photoList) {
                for (var i = 0; i < model.photoList.length; i++) {

                    if (model.photoList[i].fileUrl.indexOf("handheld") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg1",panel).unbind("click");
                            $("#labelimg1",panel).click(function () {
                                self.dialogImg("手持身份证正面照片", url)
                            });
                        })();
                    }
                    if (model.photoList[i].fileUrl.indexOf("idcard") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg2",panel).unbind("click");
                            $("#labelimg2",panel).click(function () {
                                self.dialogImg("身份证正面照片", url);
                            });
                        })();
                    }
                    if (model.photoList[i].fileUrl.indexOf("other") > 0) {
                        (function () {
                            var url = model.photoList[i].fileUrl;
                            $("#labelimg3",panel).unbind("click");
                            $("#labelimg3",panel).click(function () {
                                self.dialogImg("许可证正面照片", url);
                            });
                        })();
                    }
                }
            }

            $('#auditcollage',panel).bootstrapTable('load', model.collegeList);


            $('#auditModal',panel).modal();
        },
        auditPost: function (panel) {
            $.post("/user/exp/audit", {
                "userId": $("#userId",panel).val(),
                "verifyRemark": $("#verifyRemark",panel).val(),
                "verifyStatus": ($("#auditStorefe1",panel).prop('checked') && "3") || ($("#auditStorefe2",panel).prop('checked') && "3") || ($("#auditStorefe3",panel).prop('checked') && "3")
                || ($("#auditStorefe4",panel).prop('checked') && "3") || ($("#auditStorefe5",panel).prop('checked') && "3") || ($("#auditStorefe6",panel).prop('checked') && "3")
                || ($("#auditStorefe7",panel).prop('checked') && "3") || ($("#auditStorefe8",panel).prop('checked') && "3") || ($("#auditStorefe9",panel).prop('checked') && "3") || ($("#auditStorefe10",panel).prop('checked') && "3") || "2",
                "verifyInfoString": '{"f1":' + ($("#auditStoref1",panel).prop('checked') ? '"0"' : '"-1"' ) + ',"f2":' + ($("#auditStoref2",panel).prop('checked') ? '"0"' : '"-1"') + ',"f3":' + ($("#auditStoref3",panel).prop('checked') ? '"0"' : '"-1"') + ',"f4":' + ( $("#auditStoref4",panel).prop('checked') ? '"0"' : '"-1"' ) + ',"f5":' + ( $("#auditStoref5",panel).prop('checked') ? '"0"' : '"-1"') + ',"f6":' + ($("#auditStoref6",panel).prop('checked') ? '"0"' : '"-1"') + ',"f7":' + ($("#auditStoref7",panel).prop('checked') ? '"0"' : '"-1"') + ',"f8":' + ($("#auditStoref8",panel).prop('checked') ? '"0"' : '"-1"') + ',"f10":' + ($("#auditStoref10",panel).prop('checked') ? '"0"' : '"-1"') + '}'
            }, function (data) {
                if (data.success == 0) {
                    $("#userTable",panel).bootstrapTable('refresh');
                    $('#auditModal',panel).modal('hide');
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