/**
 * Created by lijun on 2016-10-14.
 */
define(['base', 'packetOrderCountModules', 'acctFlowModules', 'packetOrderCommentModules'], function (base, packetOrderCount, acctFlow, packetOrderComment) {

    var packetOrderInit = function () {
        packetOrderCount.init();
    }
    var acctFlowInit = function () {
        acctFlow.init();
    }
    var packetOrderCommentInit = function () {
        packetOrderComment.init();
    }
    var editcollege;

    return {
        init: function (panel) {
            var self = this;
            panel = panel || $('#indextab').tabs('getSelected');
            //开始时间
            $('#registerStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            })

            //结束时间
            $('#registerEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            });

            /*** 加载校区名称列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/college/getCollageForSel",
                dataType: "json",
                success: function (data) {
                    $("#collegeId", panel).select2({
                        data: data.data
                    });
                    editcollege = $("#edit_college", panel).select2({
                        data: data.data
                    });
                    $("#collegeId", panel).val($('#crowdCollegeId', panel).val()).trigger("change");
                }
            });

            var par = {
                url: '/crowdUser/queryCrowdUserList' + self.searchVal(panel),
                // method: 'post',
                // showExport: true,// 显示导出按钮
                singleSelect: false,//允许多选
                // exportDataType: "all",// 导出类型
                pagination: true, // 是否显示分页（*）
                // sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            loginAccount: $("#loginAccount", panel).val().trim(),
                            userName: $("#userName", panel).val().trim(),
                            sex: $("#sex", panel).val() == null ? "" : $("#sex", panel).val().trim(),
                            collegeId: $("#collegeId", panel).val() == null ? "" : $("#collegeId", panel).val().trim(),
                            userState: $("#userState", panel).val() == null ? "" : $("#userState", panel).val().trim(),
                            registerStartTime: $("#registerStartTime", panel).val().trim(),
                            registerEndTime: $("#registerEndTime", panel).val().trim()
                        });
                },
                onLoadSuccess: function (data) {
                    // $('.showCrowdUserDetail', panel).click(function () {
                    //     var userId = $(this).attr("id");
                    //     self.showPieDetail(userId, panel);
                    // });
                    // $('.collegeDetail', panel).click(function () {
                    //     var collegeId = $(this).attr("name");
                    //     self.showCollegeDetail(collegeId, panel);
                    // });
                    // $('.orderDetailList', panel).click(function () {
                    //     var userId = $(this).attr("name");
                    //     var title = $(this).attr("title");
                    //     var href = '/statistic/gotoOrderCount?packetUserId=' + userId;
                    //     base.openTab(title, href, packetOrderInit);
                    // });
                    var tableHeight = 40 * data.total + 150;
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 120;
                    }
                    else if (data.total >= 10) {
                        tableHeight = 550;
                    }
                    $('#crowdUserTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                },

                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'loginAccount',
                        title: '登录账号',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return "<a href='#' class='showCrowdUserDetail' id=\'" + row.userId + "\'>" + value + "</a>";
                            // return "<a href='#' onclick='showAuditDetail(\"" + row.smsModelId + "\");'>" + value + "</a>";
                        },
                        events: {
                            'click .showCrowdUserDetail': function (e, value, row, index) {
                                self.showPieDetail(row.userId, panel, row);
                            }
                        }
                    },
                    {
                        field: 'registerTime',
                        title: '注册时间',
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value.length > 19) {
                                return value.substr(0, value.length - 2);
                            }
                        }
                    },
                    {
                        field: 'userName',
                        title: '姓名',
                        sortable: true
                    },
                    {
                        field: 'sex',
                        title: '性别',
                        sortable: true
                    },
                    {
                        field: 'cityName',
                        title: '城市',
                        sortable: true
                    },
                    {
                        field: 'collegeName',
                        title: '校区',
                        sortable: true,
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
                    },
                    {
                        field: 'dorm',
                        title: '宿舍',
                        sortable: true

                    },
                    {
                        field: 'orderNum',
                        title: '订单量',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != '' && value != null) {
                                return '<a href="javaScript:void(0)" class="orderDetailList" name="' + row.userId + '" title="小派订单列表">' + value + '</a>';
                            }
                        },
                        events: {
                            'click .orderDetailList': function (e, value, row, index) {
                                var userId = row.userId;
                                var title = "订单总数";
                                var href = '/statistic/gotoOrderCount?packetUserId=' + userId;
                                base.openTab(title, href, packetOrderInit);
                            }
                        }
                    },
                    {
                        field: 'favourable',
                        title: '好评率',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value > 0) {
                                return '<a href="javaScript:void(0)" class="pieCommentDetail" title="">' + value + '%' + '</a>';
                            }
                        },
                        events: {
                            'click .pieCommentDetail': function (e, value, row, index) {
                                var userId = row.userId;
                                var title = "小派好评率";
                                var href = '/statistic/gotoComment?packetUserId=' + userId;
                                base.openTab(title, href, packetOrderCommentInit);
                            }
                        }
                    },
                    {
                        field: 'walletBalance',
                        title: '钱包余额(元)',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value > 0) {
                                return '<a href="javaScript:void(0)" class="pieAcctBalanceDetail" title="">' + value + '</a>';
                            }
                        },
                        events: {
                            'click .pieAcctBalanceDetail': function (e, value, row, index) {
                                var userId = row.userId;
                                var title = "小派钱包余额";
                                var href = '/statistic/gotoAcctFlow?packetUserId=' + userId;
                                base.openTab(title, href, acctFlowInit);
                            }
                        }
                    },
                    {
                        field: 'userState',
                        title: '用户状态',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return "启用"
                            } else if (value == 0) {
                                return "禁用";
                            }
                        }
                    }

                ]
            };
            var userId = $('#crowdUserId', panel).val();
            var searchType = $('#crowdSearchType', panel).val();
            var collegeId = $('#crowdCollegeId', panel).val();

            if (searchType != null && userId != '' && collegeId != '') {
                par.showExport = false;
            }
            base.datagrid(par, '#crowdUserTable', panel);

            $("#btn_query", panel).click(function () {
                var myDate1 = new Date();
                var myDate2 = new Date();
                var receive1 = $("#registerStartTime", panel).val();
                var receive2 = $("#registerEndTime", panel).val();
                if (receive1.trim() != "" && receive2.trim() != "") {
                    myDate1.setFullYear(receive1.substring(0, 4), receive1.substring(5, 7), receive1.substring(8));
                    myDate2.setFullYear(receive2.substring(0, 4), receive2.substring(5, 7), receive2.substring(8));
                    if (myDate2 < myDate1) {
                        base.error("注册起始日期不能小于截止日期!");
                        return;
                    }
                }
                $("#crowdUserTable", panel).bootstrapTable('selectPage', 1);
            });

            $("#clearSearch", panel).click(function () {
                $("#loginAccount", panel).val('');
                $("#userName", panel).val('');
                $("#sex", panel).val('').trigger("change");
                $("#collegeId", panel).val('').trigger("change");
                $("#userState", panel).val('').trigger("change");
                $("#registerStartTime", panel).val('');
                $("#registerEndTime", panel).val('');
                $("#crowdUserTable", panel).bootstrapTable('selectPage', 1);
            });
            $("#btn_clear", panel).click(function () {
                $("#loginAccount", panel).val('');
                $("#userName", panel).val('');
                $("#sex", panel).val('').trigger("change");
                $("#collegeId", panel).val('').trigger("change");
                $("#userState", panel).val('').trigger("change");
                $("#registerStartTime", panel).val('');
                $("#registerEndTime", panel).val('');
                $("#crowdUserTable", panel).bootstrapTable('selectPage', 1);
            })

            $("#btn_edit", panel).click(function () {
                self.edit(panel);
            });
            $("#editUpdate", panel).click(function () {
                self.update(panel);
            });

            $("#btn_enable", panel).click(function () {
                self.updateState(1, panel);
            });
            $("#btn_disable", panel).click(function () {
                self.updateState(0, panel);
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
            $('#divlabel1', panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg1'>点击查看 </strong>");
            $('#divlabel2', panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg2'>点击查看 </strong>");

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
        updateState: function (state, panel) {
            var self = this;
            var arrselections = $("#crowdUserTable", panel).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var arr = {};
            for (var i = 0; i < arrselections.length; i++) {
                if (arrselections[i].userState == state) {
                    if (state == 1) {
                        base.error("已选择记录中存在已激活账户，请重新选择激活记录！");
                    } else {
                        base.error("已选择记录中存在已禁用账户，请重新选择禁用记录！");
                    }
                    return;
                }
                arr[i] = arrselections[i].userId;
            }
            $.post("/crowdUser/batchUpdateState", {
                "userIdArr": arr,
                "userState": state
            }, function (data, status) {
                if (status == "success") {
                    if (data.success == 0) {
                        base.success("操作成功");
                        $("#crowdUserTable", panel).bootstrapTable('selectPage', 1);
                    } else {
                        base.error(data.message);
                    }
                } else {
                    base.error("数据加载失败!");
                }
            })
        },
        searchVal: function (panel) {
            var userId = $('#crowdUserId', panel).val();
            var searchType = $('#crowdSearchType', panel).val();
            var collegeId = $('#crowdCollegeId', panel).val();

            if (searchType != null && userId != '' && collegeId != '') {
                // $('#collegeId').attr("readonly",true);
                $('#collegeTitle', panel).html("<input type='text' value='" + $('#crowdCollegeName', panel).val() + "' class='form-control' readonly>");
                $('#btn_enable', panel).hide();
                $('#btn_disable', panel).hide();
                $('.export', panel).hide();
                return '?' + 'userId=' + userId + '&searchType=' + searchType + '&collegeId=' + collegeId;
            } else {
                return '';
            }
        },
        edit: function (panel) {
            var self = this;
            var arrselections = $("#crowdUserTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }

            $("#edit_loginAccount", panel).val(arrselections[0].loginAccount);
            $("#edit_userName", panel).val(arrselections[0].userName);
            $("#edit_sex", panel).val(arrselections[0].sex);
            $("#edit_cityName", panel).val(arrselections[0].cityName);
            $("#edit_userId", panel).val(arrselections[0].userId);
            
            $('#editModal', panel).modal({
                keyboard: false,
                backdrop: 'static'
            });

            editcollege.val(arrselections[0].collegeId).trigger("change");

        },
        update: function (panel) {
            $.post("/crowdUser/updateuser",
                {
                    "collegeId": $("#edit_college", panel).val(),
                    "userId": $("#edit_userId", panel).val()
                },
                function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            $("#crowdUserTable", panel).bootstrapTable('refresh');
                            $('#editModal', panel).modal('hide');
                            editcollege.val(" ").trigger("change")
                            base.success("更新成功！")
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("更新失败!");
                    }
                });
        }
    }
});
