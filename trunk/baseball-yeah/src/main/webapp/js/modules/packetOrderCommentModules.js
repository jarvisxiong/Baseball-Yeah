define(['base'], function (base) {
    var dataTable;

    return {
        init: function (panel) {
            var self = this;
            panel = panel || $('#indextab').tabs('getSelected');
            $('#commentStartDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            $('#commentEndDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                minView : 2
            });

            dataTable = base.datagrid({
                url: '/statistic/getOrderComment',
                showExport: false,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            orderId: $("#orderId", panel).val(),//订单编号
                            customerPhone: $("#customerPhone", panel).val(),//下单人手机号
                            commentStartDate: $('#commentStartDate', panel).val(),
                            commentEndDate: $('#commentEndDate', panel).val(),
                            packetUserId: $("#packetUserId", panel).val(),//小派编号
                            score: $("#score", panel).val()//评分
                        });
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = 40 * data.total + 150;
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 120;
                    } else if (data.total >= 10) {
                        tableHeight = 550;
                    }
                    $("#commentTable", panel).bootstrapTable('resetView', {"height": tableHeight});

                    $('.orderDetail', panel).click(function () {
                        var orderId = $(this).attr("name");
                        self.showOrderDetail(orderId, $(this).attr("evalue"),panel);
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
                                return '<a href="javaScript:void(0)" class="orderDetail" name="' + value + '" evalue="' + row.type +'">' + value + '</a>';
                            }
                        }
                    },
                    {
                        field: 'appraiserMobile',
                        title: '下单人手机号',
                        sortable: true,
                        width: 80
                    },
                    {
                        field: 'packetPhone',
                        title: '小派手机号',
                        sortable: false,
                        width: 80,
                        formatter: function (value, row, index) {
                            if (value != null && value != '') {
                                return '<a href="javaScript:void(0)" class="showCrowdUserDetail" name="' + $("#packetUserId", panel).val() + '">' + value + '</a>';
                            }
                        },
                        events: {
                            'click .showCrowdUserDetail': function (e, value, row, index) {
                                self.showPieDetail($("#packetUserId", panel).val(), panel, row);
                            }
                        }
                    },
                    {
                        field: 'createDate',
                        title: '评论时间',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'overallScore',
                        title: '评分星级',
                        sortable: false,
                        width: 50
                    },
                    {
                        field: 'overallDesc',
                        title: '评论详情',
                        sortable: false,
                        width: 50,
                        height: 35,
                        formatter: function (value, row, index) {
                            return '<a id="btnComment" href="#" class="table-link" value="' + row["appraiseId"] + '"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span></a>';
                        }
                    },
                    {
                        field: '',
                        title: 'appraiseId',
                        visible: false
                    },
                    {
                        field: '',
                        title: 'type',
                        visible: false
                    }]
            }, '#commentTable', panel);

            //查看评论信息
            $('#commentTable').on('click', 'a#btnComment', function () {
                var appraiseId = this.getAttribute("value");
                self.getCommentInfo(appraiseId, panel);
            });

            $("#btnCommentQuery", panel).click(function () {
                if ((new Date(Date.parse($('#commentEndDate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#commentStartDate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "评论时间-结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#commentTable", panel).bootstrapTable('selectPage', 1);
            });

            $("#clearCommentSearch", panel).click(function () {
                base.reset($(".main-box-header", panel));
                $("#orderId", panel).val(null);
                $("#customerPhone", panel).val("");
                $("#commentStartDate", panel).val("");
                $("#commentEndDate", panel).val("");
                $("#score", panel).val("");
            });
        },
        getCommentInfo: function (appraiseId, panel) {
            var self = this;
            $.post("/statistic/getCommentDetail", {
                appraiseId: appraiseId
            }, function (res, status) {
                if (status == "success") {
                    if (res.success == 0) {
                        $("#commentModal").modal("show");
                        $("#mOrderId").text(res.data["orderId"]);
                        var score = parseInt(res.data["overallScore"]);
                        for (var i = 0; i < score; i++) {
                            $($("#divScore i")[i]).removeClass("fa-star-o").addClass("fa-star");
                        }
                        for (var i = 4; i >= score; i--) {
                            $($("#divScore i")[i]).removeClass("fa-star").addClass("fa-star-o");
                        }
                        $("#mComment").text(res.data["overallDesc"]);

                        var list = res.data["imgList"];
                        if (list != null && list.length > 0) {
                            var html = "";
                            var css = "padding-left:2px";
                            $.each(list, function (i, val) {
                                html += '<img src="' + val + '" style="' + css + '"/>';
                            });
                            $("#imgList").html(html);
                        }
                        else {
                            $("#imgList").html("未上传图片");
                        }
                    } else {
                        base.error(res.message);
                    }
                } else {
                    base.error("数据加载失败!");
                }
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
        }
    };
});