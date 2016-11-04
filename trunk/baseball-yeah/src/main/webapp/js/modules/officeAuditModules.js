define(['base', 'officemanageChooseModules'], function (base, officemanage) {
    var orderinit = function () {
        officemanage.init();
    };
    return {
        init: function (panel) {
            var self = this;
            base.datagrid({
                url: '/office/getOfficeAudit',
                method: 'post',
                singleSelect: true,
                showExport: false,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            username: $("#username", panel).val(),
                            realName: $("#realName", panel).val(),
                            sex: $("#sex", panel).val(),
                            collegeArea: $("#collegeArea", panel).val(),
                            auditState: $("#auditState", panel).val(),
                            office: $("#office", panel).val(),
                            auditor: $("#auditor", panel).val(),
                            startDate: $("#startDate", panel).val(),
                            endDate: $("#endDate", panel).val()
                        });
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = 40 * data.total + 150;
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 120;
                    }
                    else if (data.total >= 10) {
                        tableHeight = 550;
                    }
                    $('#officeAuditTable', panel).bootstrapTable('resetView', {"height": tableHeight});

                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'auditId',
                    title: 'ID',
                    visible: false
                }, {
                    field: 'username',
                    title: '登录账号',
                    sortable: true,
                    formatter: function (value, row, index) {
                        return "<a href='#' class='showCrowdUserDetail' id=\'" + row.userId + "\'>" + value + "</a>";
                    },
                    events: {
                        'click .showCrowdUserDetail': function (e, value, row, index) {
                            self.showPieDetail(row.userId, panel, row);
                        }
                    }
                }, {
                    field: 'signupTime',
                    title: '注册时间',
                    sortable: true
                }, {
                    field: 'realName',
                    title: '姓名'
                }, {
                    field: 'gender',
                    title: '性别',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "p_gender_male") {
                            result = "男";
                        } else if (value == "p_gender_female") {
                            result = "女";
                        } else {
                            result = "保密";
                        }
                        return result;
                    }
                }, {
                    field: 'optType',
                    title: '申请职务',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "1") {
                            result = "校园CEO";
                        } else if (value == "2") {
                            result = "校园COO";
                        }
                        return result;
                    }
                }, {
                    field: 'auditState',
                    title: '审核状态',
                    sortable: false,
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "0") {//0-待审核 1-审核通过 2-审核拒绝
                            result = "待审核";
                        } else if (value == "1") {
                            result = "审核通过";
                        } else if (value == "2") {
                            result = "审核拒绝";
                        }
                        return result;
                    }
                }, {
                    field: 'auditUserName',
                    title: '审核人'
                }, {
                    field: 'cityName',
                    title: '城市',
                }, {
                    field: 'collegeName',
                    title: '校区',
                    formatter: function (value, row, index) {
                        if (value != '' && value != null) {
                            return '<a href="javaScript:void(0)" class="collegeDetail" name="' + row.collegeId + '">' + value + '</a>';
                        }
                    },
                    events: {
                        'click .collegeDetail': function (e, value, row, index) {
                            self.showCollegeDetail(row.collegeId, panel, row);
                        }
                    }
                }, {
                    field: 'dormitoryAddress',
                    title: '宿舍'
                }, {
                    field: 'beEnabled',
                    title: '账户状态',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "0") {//1激活,0未激活
                            result = "冻结";
                        } else if (value == "1") {
                            result = "正常";
                        }
                        return result;
                    }
                }]
            }, '#officeAuditTable', panel);
            //请求校区信息
            $.ajax({
                type: "GET",
                url: "/manage/college/getCollageForSel",
                dataType: "json",
                success: function (data) {
                    $("#collegeArea", panel).select2({
                        data: data.data
                    });
                }
            });
            $("#btn_query", panel).click(function () {
                $("#officeAuditTable", panel).bootstrapTable('selectPage', 1);
            });
            function clear() {
                base.reset(".main-box-header");
                $('#startDate', panel).val("");
                $('#endDate', panel).val("");
                $("#collegeArea", panel).val(" ").trigger("change");
            }

            $("#clearSearch", panel).click(clear);
            $("#btn_reset", panel).click(clear);
            //开始时间
            $('#starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: false,
                minView: 1,
                todayHighlight: true
            });
            //结束时间
            $('#endtimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: false,
                minView: 1,
                todayHighlight: true
            });
            $("#btn_approve", panel).click(function () {
                self.reviewStateUpdate(panel, 1);
            });
            $("#btn_refuse", panel).click(function () {
                self.reviewStateUpdate(panel, 2);
            });
        },
        showDetail: function (orderId, panel) {
            $('#detailMoblile', panel).val("");
            $('#detailOrderId', panel).val("");
            $('#detailRealName', panel).val("");
            $('#detailWaybillNo', panel).val("");
            $('#detailSex', panel).val("");
            $('#detailState', panel).val("");
            $('#detailCityName', panel).val("");
            $('#detailAddress', panel).val("");
            $('#detailCollegeName', panel).val("");
            $('#detailStoreName', panel).val("");
            $('#detailLocation', panel).val("");
            $('#detailTotalMoney', panel).val("");
            $('#detailRebateMoney', panel).val("");
            $('#detailFinalMoney', panel).val("");
            $('#detailPayMoney', panel).val("");

            $.post("/office/assist/partInfo?userId=" + orderId, {
                "userId": orderId
            }, function (data, status) {
                if (status == "success") {
                    $('#detailMoblile', panel).val(data.phone);
                    if (data.officeRoleType == "1") {
                        $('#detailOrderId', panel).val("普通小派");
                    } else if (data.officeRoleType == "2") {
                        $('#detailOrderId', panel).val("校园COO");
                    } else if (data.officeRoleType == "3") {
                        $('#detailOrderId', panel).val("校园CEO");
                    }
                    if (data.state == 0) {
                        $('#detailState', panel).val("冻结");
                    }
                    if (data.state == 1) {
                        $('#detailState', panel).val("正常");
                    }
                    $('#detailRealName', panel).val(data.realName);
                    $('#detailWaybillNo', panel).val(data.waybillNo);
//                     $('#detailAbc', panel).val(data.gender);
                    if (data.gender == "p_gender_male") {
                        $('#detailAbc', panel).val("男");
                    } else if (data.gender == "p_gender_female") {
                        $('#detailAbc', panel).val("女");
                    } else if (data.gender == "p_gender_secret") {
                        $('#detailAbc', panel).val("保密");
                    } else {
                        $('#detailAbc', panel).val("-");
                    }
                } else {
                    base.error("初始化失败!");
                }
            });

            $('#detailTable', panel).bootstrapTable({
                // 请求后台的URL（*）
                url: '/office/assist/smallPieList',
                striped: true, // 是否显示行间隔色
                singleSelect: true,
                queryParams: function (pa) {
                    return {"userId": orderId};
                },
                height: 320,
                columns: [{
                    field: 'phone',
                    title: '登陆账号',
                    width: 100
                }, {
                    field: 'pulicTime',
                    title: '注册时间',
                    width: 100
                }, {
                    field: 'realName',
                    title: '姓名',
                    width: 100
                }, {
                    field: 'gender',
                    title: '性别',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "p_gender_male") {
                            result = "男";
                        } else if (value == "p_gender_female") {
                            result = "女";
                        } else if (value == "p_gender_secret") {
                            result = "保密";
                        } else {
                            result = "-";
                        }
                        return result;
                    }
                }, {
                    field: 'officeRoleType',
                    title: '职务',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "1") {
                            result = "普通小派";
                        } else if (value == "2") {
                            result = "校园COO";
                        } else if (value == "3") {
                            result = "校园CEO";
                        } else {
                            result = "-";
                        }
                        return result;
                    }
                }, {
                    field: 'cityName',
                    title: '城市',
                    width: 100
                }, {
                    field: 'collegeName',
                    title: '校园',
                    width: 100
                }, {
                    field: 'dormitoryAddress',
                    title: '宿舍',
                    width: 100
                }, {
                    field: 'beEnabled',
                    title: '小派状态',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "0") {//1激活,0未激活
                            result = "冻结";
                        } else if (value == "1") {
                            result = "正常";
                        }
                        return result;
                    }
                }
                ]
            }).bootstrapTable('refresh', {query: {"userId": orderId}});

            $('#detailModal', panel).modal();


            $('#storeTable', panel).bootstrapTable({
                // 请求后台的URL（*）
                url: '/office/assist/storeList',
                striped: true, // 是否显示行间隔色
                singleSelect: true,
                queryParams: function (pa) {
                    return {"userId": orderId};
                },
                height: 320,
                columns: [
                    {
                        field: 'storeName',
                        title: '商户名称',
                        width: 100
                    }, {
                        field: 'phone',
                        title: '商户手机',
                        width: 100
                    }, {
                        field: 'dormitoryAddress',
                        title: '详细地址',
                        width: 100
                    }, {
                        field: 'collegeName',
                        title: '学校',
                        width: 100
                    }, {
                        field: 'pulicTime',
                        title: '注册时间',
                        width: 100
                    }
                ]
            }).bootstrapTable('refresh', {query: {"userId": orderId}});
        },
        stateUpdate: function (panel, value) {
            var arrselections = $("#officeAuditTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            if (arrselections[0].beEnabled == value) {
                base.success("操作成功！");
                return;
            }
            $.post("/office/stateupdate",
                {
                    "userId": arrselections[0].userId,
                    "beEnabled": value,
                }, function (data, status) {
                    if (status == "success") {
                        var obj = data;
                        if (obj.success == 0) {
                            base.success("操作成功！");
                            $("#officeAuditTable", panel).bootstrapTable('refresh');
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        },
        dimiss: function (panel) {
            var arrselections = $("#officeAuditTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            $.post("/office/dismiss", {
                "userId": arrselections[0].userId,
                "office": arrselections[0].office
            }, function (data, status) {
                if (status == "success") {
                    var obj = data;
                    if (obj.success == 0) {
                        base.success("操作成功！");
                        $("#officeAuditTable", panel).bootstrapTable('refresh');
                    } else {
                        base.error(obj.message);
                    }
                } else {
                    base.error("更新失败!");
                }
            });
        },
        reviewStateUpdate: function (panel, value) {
            var arrselections = $("#officeAuditTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            if (arrselections[0].auditState == 1) {//0-待审核 1-审核通过 2-审核拒绝
                sweetAlert("Oops...", "此条记录已审核!", "error");
                return;
            }
            $.post("/office/audit", {
                "auditId": arrselections[0].auditId,
                "isPass": value == 1 ? true : false
            }, function (data, status) {
                if (status == "success") {
                    var obj = data;
                    if (obj.success == 0) {
                        base.success("操作成功！");
                        $("#officeAuditTable", panel).bootstrapTable('refresh');
                    } else {
                        base.error(obj.message);
                    }
                } else {
                    base.error("更新失败!");
                }
            });
        },
        showPieDetail: function (userId, panel) {
            var self = this;
            $('#detailPhone', panel).html("");
            $('#detailUserState', panel).html("");
            $('#detailSource', panel).html("");
            $('#detailUserName', panel).html("");
            $('#detailSex', panel).html("");
            $('#detailCardNo', panel).html("");
            $('#detailWalletBalance', panel).html("");
            $('#detailOrderNum', panel).html("");
            $('#detailFavourable', panel).html("");
            $('#detailCityName', panel).html("");
            $('#detailCollegeName', panel).html("");
            $('#detailDorm', panel).html("");
            $('#detailAuditTime', panel).html("");
            $('#detailAuditState', panel).html("");
            $('#detailAuditRemark', panel).html("");
            $('#detailPath', panel).html("");
            $('#detailAccountImg', panel).html("");
            $('#divlabel1',panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg1'>点击查看 </strong>");
            $('#divlabel2',panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg2'>点击查看 </strong>");

            $.post("/crowdUser/queryCrowdUserDetail", {
                "userId": userId
            }, function (data, status) {
                if (status == "success") {
                    $('#detailPhone', panel).html(data.phone);
                    if (data.userState == '1') {
                        $('#detailUserState', panel).html("启用");
                    } else if (data.userState == '0') {
                        $('#detailUserState', panel).html("禁用");
                    }
                    $('#detailSource', panel).html(data.userState);
                    $('#detailUserName', panel).html(data.userName);
                    $('#detailSex', panel).html(data.sex);
                    $('#detailCardNo', panel).html(data.cardNo);
                    $('#detailWalletBalance', panel).html(data.walletBalance);
                    $('#detailOrderNum', panel).html(data.orderNum);
                    if (data.favourable != null) {
                        $('#detailFavourable', panel).html(data.favourable + "%");
                    }
                    $('#detailCityName', panel).html(data.cityName);
                    $('#detailCollegeName', panel).html(data.collegeName);
                    $('#detailDorm', panel).html(data.dorm);
                    var auditDate = data.auditTime;
                    if (auditDate != null && auditDate.length > 19) {
                        $('#detailAuditTime', panel).html(auditDate.substr(0, auditDate.length - 2));
                    }
                    var auditState = data.auditState;
                    if (auditState != null) {
                        if (auditState == '0') {
                            $('#detailAuditState', panel).html("未提交");
                        } else if (auditState == '1') {
                            $('#detailAuditState', panel).html("已提交");
                        } else if (auditState == '2') {
                            $('#detailAuditState', panel).html("审核通过");
                        } else if (auditState == '3') {
                            $('#detailAuditState', panel).html("审核失败");
                        }
                    }

                    $('#detailAuditRemark', panel).html(data.auditRemark);
                    $('#detailPath', panel).html(data.detailPath);

                    if (data.photoList != null) {
                        for (var i = 0; i < data.photoList.length; i++) {
                            if (data.photoList[i].fileUrl.indexOf("handheld") > 0) {
                                (function () {
                                    var url = data.photoList[i].fileUrl;
                                    $("#labelimg1", panel).unbind("click");
                                    $("#labelimg1", panel).click(function () {
                                        self.dialogImg("手持身份证正面照片", url)
                                    });
                                })();
                            } else if (data.photoList[i].fileUrl.indexOf("idcard") > 0) {
                                (function () {
                                    var url = data.photoList[i].fileUrl;
                                    $("#labelimg2", panel).unbind("click");
                                    $("#labelimg2", panel).click(function () {
                                        self.dialogImg("身份证正面照片", url);
                                    });
                                })();
                            } else {
                                $('#detailAccountImg', panel).html("<img width='140px' height='120px' src='" + data.photoList[i].fileUrl + "'/>");
                                // $('#detailAccountImg',panel).html("<img src='" + data.photoList[i].nailFileUrl + "'/>");
                                // $('#detailAccountImg',panel).attr("src",data.photoList[i].nailFileUrl);
                            }
                        }
                    }

                } else {
                    base.error("初始化失败!");
                }
            });
            $('#smallPieDetailModal', panel).modal();
        },
        dialogImg: function (title, url) {
            BootstrapDialog.show({
                cssClass: 'img-dialog',
                title: title,
                message: '<div style="text-align: center;"><img src="' + url + '" style="width: 550px;height: 500px;"></div>'
            });
        },
        showCollegeDetail: function (collegeId, panel) {
            var self = this;
            $('#schCollegeCode', panel).html("");
            $('#schFullName', panel).html("");
            $('#schNature', panel).html("");
            $('#schCollegeType', panel).html("");
            $('#schProvinceName', panel).html("");
            $('#schCityName', panel).html("");
            $('#schCountyName', panel).html("");
            $('#schCeoName', panel).html("");
            $('#schCollegeRemark', panel).html("");

            $.post("/crowdUser/queryCollegeDetail", {
                "collegeId": collegeId
            }, function (data, status) {
                if (status == "success") {
                    $('#schCollegeCode', panel).html(data.collegeId);
                    $('#schFullName', panel).html(data.fullName);
                    if (data.nature != null && data.nature == 'p_nature_private') {
                        $('#schNature', panel).html("民办");
                    } else if (data.nature != null && data.nature == 'p_nature_public') {
                        $('#schNature', panel).html("公办");
                    }
                    if (data.collegeType != null && data.collegeType == 'p_college_type_speci') {
                        $('#schCollegeType', panel).html("专科院校");
                    } else if (data.collegeType != null && data.collegeType == 'p_college_type_under') {
                        $('#schCollegeType', panel).html("本科院校");
                    }
                    $('#schProvinceName', panel).html(data.provinceName);
                    $('#schCityName', panel).html(data.cityName);
                    $('#schCountyName', panel).html(data.countyName);
                    $('#schCeoName', panel).html(data.ceoName);
                    $('#schCollegeRemark', panel).html(data.collegeRemark);

                } else {
                    base.error("初始化失败!");
                }
            });
            $('#collegePieDetailModal', panel).modal();
        },
        puserAdd: function (panel) {
            panel = panel || $('#indextab').tabs('getSelected');
            base.datagrid({
                url: '/office/listPuser',
                method: 'post', singleSelect: false,
                showExport: false,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            username: $("#pu_username", panel).val(),
                            realName: $("#pu_realName", panel).val(),
                            sex: $("#pu_sex", panel).val(),
                            beEnabled: $("#pu_beEnabled", panel).val(),
                            cityId: $("#pu_city", panel).val(),
                            startDate: $("#pu_startDate", panel).val(),
                            endDate: $("#pu_endDate", panel).val()
                        });
                },
                toolbar: "#pu_toolbar",
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#puserAddTable', panel).find("thead").height() + $('#puserAddTable', panel).find("tbody").height()
                        + 65 + $('#puserAddTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 155;
                    }
                    $('#puserAddTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'userId',
                    title: 'ID',
                    visible: false
                }, {
                    field: 'username',
                    title: '登录账号',
                    sortable: true
                }, {
                    field: 'createDate',
                    title: '注册时间',
                    sortable: true
                }, {
                    field: 'realName',
                    title: '姓名'
                }, {
                    field: 'sex',
                    title: '性别',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "p_gender_male") {
                            result = "男";
                        } else if (value == "p_gender_female") {
                            result = "女";
                        } else {
                            result = "保密";
                        }
                        return result;
                    }
                }, {
                    field: 'city',
                    title: '城市',
                }, {
                    field: 'collegeArea',
                    title: '校区',
                }, {
                    field: 'dormitoryAddress',
                    title: '宿舍'
                }, {
                    field: 'beEnabled',
                    title: '账户状态',
                    formatter: function (value, row, index) {
                        var result = value;
                        if (value == "0") {//1激活,0未激活
                            result = "冻结";
                        } else if (value == "1") {
                            result = "正常";
                        }
                        return result;
                    }
                }]
            }, '#puserAddTable', panel);
            base.datagrid({
                url: '/office/attached',
                method: 'post', singleSelect: false,
                showExport: false,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            userId: $("#pu_userId", panel).val(),
                            office: $("#pu_office", panel).val()
                        });
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#puserSelectedTable', panel).find("thead").height() + $('#puserSelectedTable', panel).find("tbody").height()
                        + 35 + $('#puserSelectedTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 120;
                    }
                    $('#puserSelectedTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'userId',
                    title: 'ID',
                    visible: false
                }, {
                    field: 'username',
                    title: '登录账号',
                    sortable: true
                }, {
                    field: 'realName',
                    title: '姓名'
                }]
            }, '#puserSelectedTable', panel);
            $("#btn_pu_query", panel).click(function () {
                $("#puserAddTable", panel).bootstrapTable('selectPage', 1);
            });
            $("#btn_pu_del", panel).click(function () {
                var arrselections = $("#puserSelectedTable", panel).bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                var ids = "[";
                for (var i = 0; i < arrselections.length; i++) {
                    if (i != 0) {
                        ids += "," + arrselections[i].userId;
                    } else {
                        ids += arrselections[i].userId;
                    }
                }
                ids += "]";
                var jsonObj = "{" + "\"office\"" + ":\"" + $("#pu_office", panel).val() + "\"," + "\"list\"" + ":" + ids + "}";
                $.ajax({
                    type: "POST",
                    url: "/office/pDel",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    data: jsonObj,
                    success: function (data) {
                        base.success("删除成功！");
                        $("#puserSelectedTable", panel).bootstrapTable('selectPage', 1);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        base.error("删除失败!");
                    }
                });
            });
            $("#btn_pu_back", panel).click(function () {
                base.colseTab("选择管辖小派", "职务关系维护", function () {
                    panel = $('#indextab').tabs('select', "职务关系维护");
                    $("#pTable", panel).bootstrapTable('selectPage', 1);
                });
            });
            //请求城市信息
            $.ajax({
                type: "GET",
                url: "/manage/city/getAllCities",
                dataType: "json",
                success: function (data) {
                    $("#pu_city", panel).select2({
                        data: data
                    });
                }
            });
            function clearSearch() {
                base.reset(".main-box-header");
                $('#pu_startDate', panel).val("");
                $('#pu_endDate', panel).val("");
                $("#pu_city", panel).val(" ").trigger("change");
            }

            $("#pu_clearSearch", panel).click(clearSearch);
            $("#btn_pu_reset", panel).click(clearSearch);
            //开始时间
            $('#pu_starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: false,
                minView: 1,
                todayHighlight: true
            });
            //结束时间
            $('#pu_endtimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: false,
                minView: 1,
                todayHighlight: true
            });
            $("#btn_pu_done", panel).click(function () {
                var arrselections = $("#puserAddTable", panel).bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                var ids = "[";
                for (var i = 0; i < arrselections.length; i++) {
                    if (i != 0) {
                        ids += "," + arrselections[i].userId;
                    } else {
                        ids += arrselections[i].userId;
                    }
                }
                ids += "]";
                var jsonObj = "{" + "\"userId\"" + ":\"" + $("#pu_userId", panel).val() + "\"" + ",\"office\"" + ":\"" + $("#pu_office", panel).val() + "\"," + "\"list\"" + ":" + ids + "}";
                $.ajax({
                    type: "POST",
                    url: "/office/pAdd",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    data: jsonObj,
                    success: function (data) {
                        base.success("添加成功！");
                        base.colseTab("选择管辖小派", "职务关系维护", function () {
                            panel = $('#indextab').tabs('select', "职务关系维护");
                            $("#pTable", panel).bootstrapTable('refresh');
                            $("#pTable", panel).bootstrapTable('selectPage', 1);
                        });
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        base.error("添加失败!");
                    }
                });
            });

        }
    };
});