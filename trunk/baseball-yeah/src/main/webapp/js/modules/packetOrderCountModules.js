define(['base'], function (base) {
    var dataTable;

    return {
        init: function (panel) {
            var self = this;
            panel= panel || $('#indextab').tabs('getSelected');
            $('#startDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            $('#endDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            $('#updStartDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            $('#updEndDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            dataTable = base.datagrid({
                url: '/statistic/getOrderStatis',
                showExport: false,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            orderId: $("#orderId", panel).val(),//订单编号
                            payMode: $("#payMode", panel).val(),//支付类型
                            startDate: $('#startDate', panel).val(),
                            endDate: $('#endDate', panel).val(),
                            packetUserId: $("#packetUserId", panel).val(),//小派手机号
                            updStartDate: $("#updStartDate", panel).val(),//更新开始时间
                            updEndDate: $("#updEndDate", panel).val()//更新结束时间
                        });
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = 40 * data.total + 150;
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 120;
                    }
                    else if(data.total >= 10){
                        tableHeight = 550;
                    }
                    $("#orderTable", panel).bootstrapTable('resetView', {"height": tableHeight});

                    $('.orderDetail', panel).click(function () {
                        var orderId = $(this).attr("name");
                        var orderType = $(this).attr("id");
                        self.showOrderDetail(orderId,orderType, panel);
                    });
                    $('.collegeDetail', panel).click(function () {
                        var collegeId = $(this).attr("name");
                        self.showCollegeDetail(collegeId, panel);
                    });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'orderId',
                        title: '订单编号',
                        sortable: true,
                        width: 100,
                        formatter: function (value, row, index) {
                            if (value != '' && value != null) {
                                return '<a href="javaScript:void(0)" class="orderDetail" name="'+value+'" id="'+row.orderType+'">'+value+'</a>';
                            }
                        }
                    },
                    {
                        field: 'createDate',
                        title: '创建时间',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'updateDate',
                        title: '完成时间',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'createUserMobile',
                        title: '下单人手机号',
                        sortable: true,
                        width: 50
                    },
                    {
                        field: 'collegeName',
                        title: '校区',
                        sortable: false,
                        width: 300,
                        formatter: function (value, row, index) {
                            if(value!=null && value!=''){
                                return '<a href="javaScript:void(0)" class="collegeDetail" name="'+row.collegeId+'">'+value+'</a>';
                            }
                        }
                    },
                    {
                        field: 'serviceType',
                        title: '服务类型',
                        formatter: function (value, row, index) {
                            return "快递";
                        },
                        sortable: false,
                        width: 80
                    },
                    {
                        field: 'orderType',
                        title: '订单类型',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 'packet':
                                    return "代取";
                                case 'agent_packet':
                                    return "代取";
                                case 'agent_system':
                                    return "代办";
                                case 'send':
                                    return "代寄";
                            }
                        },
                        sortable: false,
                        width: 80
                    },
                    {
                        field: 'packetPhone',
                        title: '小派手机号',
                        sortable: false,
                        width: 100,
                        formatter: function (value, row, index) {
                            if(value!=null && value!=''){
                                return '<a href="javaScript:void(0)" class="showCrowdUserDetail" name="'+$("#packetUserId",panel).val()+'">'+value+'</a>';
                            }
                        },
                        events: {
                            'click .showCrowdUserDetail': function (e, value, row, index) {
                                self.showPieDetail($("#packetUserId",panel).val(), panel, row);
                            }
                        }
                    },
                    {
                        field: 'payMode',
                        title: '支付类型',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 1:
                                    return "预付";
                                case 2:
                                    return "当面付";
                            }
                        },
                        sortable: false,
                        width: 80
                    },
                    {
                        field: 'rewardMoney',
                        title: '打赏金额（元）',
                        sortable: false,
                        width: 100,
                        formatter: function (value, row, index) {
                            return value/100;
                        }
                    },
                    {
                        field: 'discountMoney',
                        title: '优惠金额（元）',
                        sortable: false,
                        width: 100,
                        formatter: function (value, row, index) {
                            return value/100;
                        }
                    },
                    {
                        field: 'payMoney',
                        title: '实付金额（元）',
                        sortable: false,
                        width: 100,
                        formatter: function (value, row, index) {
                            return value/100;
                        }
                    },
                    {
                        field: 'userId',
                        title: 'userId',
                        visible: false
                    }]
            }, '#orderTable', panel);

            $("#btn_query", panel).click(function () {
                if ((new Date(Date.parse($('#endDate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#startDate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "创建时间-结束时间不能小于开始时间!", "info");
                    return;
                }
                if ((new Date(Date.parse($('#updEndDate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#updStartDate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "状态更新时间-结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#orderTable", panel).bootstrapTable('selectPage', 1);

            });

            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header", panel));
                $("#orderId", panel).val(null);
                $("#payMode", panel).val("");
                $("#startDate", panel).val("");
                $("#endDate", panel).val("");
                $("#updStartDate", panel).val("");
                $("#updEndDate", panel).val("");
            });
        },
        showOrderDetail: function (orderId, orderType,panel) {
            var self = this;
            $('#orUserPhone', panel).val("");
            $('#orCollegeName', panel).val("");
            $('#orUserName', panel).val("");
            $('#orDeliAddress', panel).val("");
            $('#orSex', panel).val("");
            $('#orFetchAddress', panel).val("");
            $('#orCityName', panel).val("");
            $('#orExpressAddress', panel).val("");

            $('#orOrderId', panel).val("");
            $('#orPayMoney',panel).val("");
            $('#orWaybillNo',panel).val("");
            $('#orRebateType',panel).val("");
            $('#orState',panel).val("");
            $('#orPayMethod',panel).val("");
            $('#orFeeMoney',panel).val("");
            $('#orPayType',panel).val("");
            $('#orRebateMoney',panel).val("");
            $('#orDistribionRemark',panel).val("");
            if(orderType =="agent_packet"){//代取件
                $("#orUserPhoneTitle", panel).html("收件人手机号");
                $("#orUserNameTitle", panel).html("收件人姓名");
                $("#orDeliAddressTitle", panel).html("送货地址");
                $("#orPacketPickup", panel).hide();
                $("#orPickupCodeSpan", panel).show();

            }else if(orderType=="send"){//待寄件
                $("#orUserPhoneTitle", panel).html("寄件人手机号");
                $("#orUserNameTitle", panel).html("寄件人姓名");
                $("#orDeliAddressTitle", panel).html("收货地址");
                $("#orPacketPickup", panel).show();
                $("#orFetchAddressTitle", panel).show();
                $("#orPickupCodeSpan", panel).hide();
            }else if(orderType=="agent_system"){//待办
                $("#orUserPhoneTitle", panel).html("收件人手机号");
                $("#orUserNameTitle", panel).html("收件人姓名");
                $("#orDeliAddressTitle", panel).html("送货地址");
                $("#orPacketPickup", panel).show();
                $("#orFetchAddressTitle", panel).hide();$("#orPickupCodeSpan", panel).hide();
            }else if(orderType=="packet"){
                $("#orUserPhoneTitle", panel).html("收件人手机号");
                $("#orUserNameTitle", panel).html("收件人姓名");
                $("#orDeliAddressTitle", panel).html("送货地址");
                // $("#orPacketPickup", panel).hide();
                $("#orFetchAddressTitle", panel).hide();$("#orPickupCodeSpan", panel).hide();
            }else if(orderType=="errands"){
                $("#orPacketPickup", panel).hide();
                $("#orUserPhoneTitle", panel).html("手机号");
                $("#orUserNameTitle", panel).html("姓名");
                $("#orDeliAddressTitle", panel).html("地址");
                $("#orderDetailSpanId",panel).html(
                    '<div class="form-group" style=" margin-bottom: 5px;">'
                        +'<label for="orOrderId" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">订单号</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="orOrderId" readonly></div>'
                        +'<label for="orState" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">当前状态</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="orState" readonly></div>'
                    +'</div>'
                    +'<div class="form-group" style=" margin-bottom: 5px;">'
                        +'<label for="goodsAmount" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">商品金额</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="goodsAmount" readonly></div>'
                        +'<label for="orFeeMoney" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">打赏金额</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="orFeeMoney" readonly></div>'
                    +'</div>'
                    +'<div class="form-group" style=" margin-bottom: 5px;">'
                        +'<label for="orRebateType" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">优惠方式</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="orRebateType" readonly></div>'
                        +'<label for="orPayMethod" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">付款方式</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="orPayMethod" readonly></div>'
                    +'</div>'
                    +'<div class="form-group" style=" margin-bottom: 5px;">'
                        +'<label for="orRebateMoney" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">优惠金额</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="orRebateMoney" readonly></div>'
                        +'<label for="orPayMoney" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">实际支付金额</label>'
                        +'<div class="col-lg-4"><input type="text" class="form-control" id="orPayMoney" readonly></div>'
                    +'</div>'
                    +'<div class="form-group" style=" margin-bottom: 5px;">'
                        +'<label for="orDistribionRemark" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">服务时间</label>'
                        +'<div class="col-lg-6"><input type="text" class="form-control" id="orBookTime" readonly></div>'
                    +'</div>'
                    +'<div class="form-group" style=" margin-bottom: 5px;">'
                        +'<label for="orDistribionRemark" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">需求描述</label>'
                        +'<div class="col-lg-6"><textarea type="text" class="form-control" rows="1" id="orDistribionRemark" readonly></textarea></div>'
                    +'</div>'
                    +'<table id="packetDetailTableList" class="table"></table>'
                );
            }
            $.post("/crowdUser/queryOrderDetail", {
                "orderId": orderId
            }, function (data, status) {
                if (status == "success") {
                    $('#orUserPhone', panel).val(data.userPhone);
                    $('#orCollegeName', panel).val(data.collegeName);
                    $('#orUserName', panel).val(data.userName);
                    $('#orDeliAddress', panel).val(data.deliAddress);
                    $('#orSex', panel).val(data.sex);
                    $('#orFetchAddress', panel).val(data.fetchAddress);
                    $('#orCityName', panel).val(data.cityName);
                    $('#orExpressAddress', panel).val(data.expressAddress);

                    $('#orOrderId', panel).val(data.orderId);
                    $('#orPayMoney',panel).val(data.payMoney);
                    $('#orWaybillNo',panel).val(data.waybillNo);
                    if(data.rebateType!=null && data.rebateType != ""){
                        $('#orRebateType',panel).val(data.rebateType);
                    }
                    $('#orState',panel).val(data.state);
                    if (data.payMethod == "1") {
                        $('#orPayMethod',panel).val("微信");
                    } else if (data.payMethod == "2") {
                        $('#orPayMethod',panel).val("支付宝");
                    } else if (data.payMethod == "3") {
                        $('#orPayMethod',panel).val("指端支付");
                    } else if (data.payMethod == "4") {
                        $('#orPayMethod',panel).val("现金");
                    } else {
                        $('#orPayMethod',panel).val("未知方式");
                    }
                    $('#orFeeMoney',panel).val(data.feeMoney);
                    if(data.payType=='1'){
                        $('#orPayType',panel).val("预付");
                    }else if(data.payType=='2'){
                        $('#orPayType',panel).val("当面付");
                    }
                    $('#orRebateMoney',panel).val(data.rebateMoney);
                    $('#orDistribionRemark',panel).val(data.distribionRemark);
                    $('#orPickupCode',panel).val(data.pickupCode);
                    $('#orBookTime',panel).val(data.bookTime);
                    $('#goodsAmount',panel).val(data.goodsAmount);

                    $('#packetDetailTableList',panel).bootstrapTable({
                        // 请求后台的URL（*）
                        url: '/order/user/orderDetail',
                        striped: true, // 是否显示行间隔色
                        singleSelect: true,
                        queryParams: function (pa) {
                            return {"orderId": orderId};
                        },
                        height: 260,
                        columns: [{
                            field: 'createDateStr',
                            title: '操作时间',
                            width: 60
                        }, {
                            field: 'content',
                            title: '内容',
                            width: 60
                        }
                        ]
                    }).bootstrapTable('refresh', {query: {"orderId": orderId}});

                } else {
                    base.error("初始化失败!");
                }
            });

            $('#packetDetailModal', panel).modal();
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
            $('#detailAccountImg',panel).html("");
            $('#divlabel1',panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg1'>点击查看 </strong>");
            $('#divlabel2',panel).html("<i class='fa fa-file-image-o fa-fw fa-lg'></i><strong style='cursor: pointer;' id='labelimg2'>点击查看 </strong>");

            $.post("/crowdUser/queryCrowdUserDetail", {
                "userId": userId
            }, function (data, status) {
                if (status == "success") {
                    $('#detailPhone', panel).html(data.phone);
                    if(data.userState == '1'){
                        $('#detailUserState', panel).html("启用");
                    }else if(data.userState == '0'){
                        $('#detailUserState', panel).html("禁用");
                    }
                    $('#detailSource', panel).html(data.userState);
                    $('#detailUserName', panel).html(data.userName);
                    $('#detailSex', panel).html(data.sex);
                    $('#detailCardNo', panel).html(data.cardNo);
                    $('#detailWalletBalance', panel).html(data.walletBalance);
                    $('#detailOrderNum', panel).html(data.orderNum);
                    if(data.favourable!=null){
                        $('#detailFavourable', panel).html(data.favourable+"%");
                    }
                    $('#detailCityName', panel).html(data.cityName);
                    $('#detailCollegeName', panel).html(data.collegeName);
                    $('#detailDorm', panel).html(data.dorm);
                    var auditDate = data.auditTime;
                    if( auditDate != null && auditDate.length > 19){
                        $('#detailAuditTime', panel).html(auditDate.substr(0,auditDate.length-2));
                    }
                    var auditState = data.auditState;
                    if(auditState!=null){
                        if(auditState == '0'){
                            $('#detailAuditState', panel).html("未提交");
                        }else if(auditState == '1'){
                            $('#detailAuditState', panel).html("已提交");
                        }else if(auditState == '2'){
                            $('#detailAuditState', panel).html("审核通过");
                        }else if(auditState == '3'){
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
                                    $("#labelimg1",panel).unbind("click");
                                    $("#labelimg1",panel).click(function () {
                                        self.dialogImg("手持身份证正面照片", url)
                                    });
                                })();
                            }else if (data.photoList[i].fileUrl.indexOf("idcard") > 0) {
                                (function () {
                                    var url = data.photoList[i].fileUrl;
                                    $("#labelimg2",panel).unbind("click");
                                    $("#labelimg2",panel).click(function () {
                                        self.dialogImg("身份证正面照片", url);
                                    });
                                })();
                            }else{
                                $('#detailAccountImg',panel).html("<img width='140px' height='120px' src='" + data.photoList[i].fileUrl + "'/>");
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
                    if(data.nature!=null && data.nature == 'p_nature_private'){
                        $('#schNature', panel).html("民办");
                    }else if(data.nature!=null && data.nature == 'p_nature_public'){
                        $('#schNature', panel).html("公办");
                    }
                    if(data.collegeType!=null && data.collegeType == 'p_college_type_speci'){
                        $('#schCollegeType', panel).html("专科院校");
                    }else if(data.collegeType!=null && data.collegeType == 'p_college_type_under'){
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
        }
    };
});