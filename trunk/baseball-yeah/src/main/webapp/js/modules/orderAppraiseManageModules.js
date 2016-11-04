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
                var date = new Date();
                
                args = args || $('#indextab').tabs('getSelected');
                // 开始时间
                $('#createStartDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createEndDatePicker',args).datetimepicker('setStartDate', startTime);
                });

                $('#createEndDatePicker',args).datetimepicker({
                    format: 'yyyy-mm-dd hh:ii:ss',
                    autoclose: true,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true,
                    endDate: new Date(date.getFullYear(), date.getMonth(),
                        date.getDate(), 23, 59, 59)
                }).on('changeDate', function (e) {
                    var startTime = e.date;
                    $('#createStartDatePicker',args).datetimepicker('setEndDate', startTime);
                });

                base.datagrid(
                    {
                        url: '/order/statistics/getOrderAppraise',
                        singleSelect:false,
                        queryParams: function (params) {
                            return $.extend(params, {
                                createStartDate: $('#createStartDate',args)
                                    .val(),
                                createEndDate: $('#createEndDate',args)
                                    .val(),
                                orderId: $.trim($("#orderId",args)
                                    .val()),
                                appraiserMobile: $.trim($("#appraiserMobile",args)
                                    .val()),
                                overallScore: $.trim($("#overallScore",args)
                                    .val()),
                                phone: $.trim($("#phone",args)
                                    .val()),
                            });
                        },
                        columns: [
                            {
                                checkbox: true
                            },
                            {
                                field: 'orderId',
                                title: '订单编号',
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row, index) {
                                    return '<a href="#" class="orderDetail"><span>' + value + '</span></a>';
                                },
                                events: {
                                    'click .orderDetail': function (e, value, row, index) {
                                        //self.showOrderDetail(value, args, row);
                                        self.showOrderDetailNew(value,row.orderType,args);
                                    }
                                },
                                width: 250
                            },
                            {
                                field: 'appraiserMobile',
                                title: '下单人手机号',
                                align: 'center',
                                sortable: true,
                                width: 250
                            },
                            {
                                field: 'phone',
                                title: '小派手机号',
                                align: 'center',
                                sortable: true,
                                formatter: function (value, row, index) {
                                    return "<a href='#' class='showCrowdUserDetail' id=\'" + row["appraiserOther"] + "\'>" + value + "</a>";
                                    // return "<a href='#' onclick='showAuditDetail(\"" + row.smsModelId + "\");'>" + value + "</a>";
                                },
                                events: {
                                    'click .showCrowdUserDetail': function (e, value, row, index) {
                                        self.showPieDetail(row.appraiserOther, args, row);
                                    }
                                },
                                width: 250
                            }, {
                                field: 'createDate',
                                title: '评论时间',
                                align: 'center',
                                visible:true,
                                sortable: true,
                                width: 250
                            }, {
                                field: 'overallScore',
                                title: '评价等级',
                                align: 'center',
                                sortable: true,
                                width: 250
                            }, {
                                field: 'overallDesc',
                                title: '评论内容',
                                align: 'center',
                                sortable: false,
                                width: 250,
                                formatter: function (value, row, index) {
                                    return "<a href='#' class='showCommentInfo' id=\'" + row.appraiseId + "\'>查看</a>";
                                    // return "<a href='#' onclick='showAuditDetail(\"" + row.smsModelId + "\");'>" + value + "</a>";
                                },
                                events: {
                                    'click .showCommentInfo': function (e, value, row, index) {
                                        self.getCommentInfo(row.appraiseId, args, row);
                                    }
                                },
                            },
                            {
                                field: 'appraiseId',
                                title: 'appraiseId',
                                visible: false
                            },
                            {
                                field: 'appraiserOther',
                                title: 'appraiserOther',
                                visible: false
                            }
                            ,
                            {
                                field: 'orderType',
                                title: 'orderType',
                                visible: false
                            }
                        ]
                    }, '#userTable',args);
                
                $("#btn_query",args).click(function () {
                    $("#userTable", args).bootstrapTable('selectPage', 1);
                });

                $("#clearSearch",args).click(function () {
                    base.reset(".main-box-body");
                    $('#orderId',args).val("");
                    $('#appraiserMobile',args).val("");
                    $('#overallScore',args).select2("val", "");
                    $('#phone',args).val("");
                    $('#createStartDate',args).val("");
                    $('#createEndDate',args).val("");
                });
                
                //查看评论信息
               /* $('#userTable').on('click', 'a#btnComment', function () {
                    var appraiseId = this.getAttribute("value");
                    self.getCommentInfo(appraiseId, args);
                });*/
            },
            showOrderDetail: function (orderId, args, row) {
                var self = this;
                $('#orderManagerDetail',args).modal({
                    keyboard: false,
                    backdrop: 'static'
                });
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/orderMgr/detail?orderId=" + orderId,
                    success: function (data) {
                        if(data && data.data){
                            data = data.data;
                            $("#mobile", args).html(data.mobile);
                            $("#collegeName", args).html(data.collegeName);
                            $("#consignee", args).html(data.consignee);
                            $("#cityName", args).html(data.cityName);
                            $("#traderAddress", args).html(data.traderAddress);
                            $("#mgrOrderId", args).html(orderId);
                            $("#waybillNo", args).html(data.waybillNo);
                            if(data.type=="packet" || data.type=="agent_packet"){
                                $("#address1", args).html(data.address);
                                $("#address2", args).html(data.traderAddress);
                                $("#mobileText",args).html("收件人手机号：");
                                $("#nameText",args).html("收件人姓名：");
                                $("#city_addrText",args).html("城市：");
                                $("#address2", args).html(data.cityName);
                                $("#cityNameText").html("");
                                $("#cityName").html("");
                                $("#pickUpTR").hide();
                            }else if(data.type=="send"){
                                $("#mobileText",args).html("寄件人手机号：");
                                $("#nameText",args).html("寄件人姓名：");
                                $("#pickUpTR").hide();
                                $("#cityNameText").html("城市：");
                                $("#sendAddrText",args).html("取件地址：");
                                $("#city_addrText",args).html(" 收货地址：");
                                $("#address2", args).html(data.traderAddress);
                                $("#address1", args).html(data.address);
                            }
                            if(data.type=="agent_packet"){
                                $("#pickUpTR").show();
                                $("#pickUpCode",args).html(data.agentPickupCode);
                            }
                            if(data.sex=="p_gender_male"){
                                $("#sex", args).html("男");
                            }else if(data.sex=="p_gender_female"){
                                $("#sex", args).html("女");
                            }else if(data.sex=="p_gender_secret"){
                                $("#sex", args).html("保密");
                            }else{
                                $("#sex", args).html("女");
                            }
                            if(data.totalMoney-data.finalMoney != 0){
                                $("#perferWay", args).html("代金券");
                            }
                            if(data.state==1){
                                $("#state", args).html("待接单");
                            }else if(data.state==2){
                                $("#state", args).html("已接单");
                            }else if(data.state==3){
                                $("#state", args).html("配送中");
                            }else if(data.state==5){
                                $("#state", args).html("已完成");
                            }else if(data.state==6){
                                $("#state", args).html("已取消");
                            }else if(data.state==7){
                                $("#state", args).html("异常");
                            }
                            if(data.payType==1){
                                $("#payType", args).html("微信支付");
                            }else if(data.payType==2){
                                $("#payType", args).html("支付宝支付");
                            }else if(data.payType==3){
                                $("#payType", args).html("指端支付");
                            }else if(data.payType==4){
                                $("#payType", args).html("现金支付");
                            }

                            $("#rewardMoney", args).html((data["rewardMoney"]/100)+".00");
                            if(data.payMode==1){
                                $("#payMode", args).html("预付");
                            }else if(data.payMode==2){
                                $("#payMode", args).html("当面付");
                            }
                            $("#perferMoney", args).html(((data["totalMoney"]-data["finalMoney"])/100)+".00");
                            $("#finalMoney",args).html((data["finalMoney"]/100)+".00");
                            $("#remark", args).html(data.remark);
                            $("#orderTime",args).html(data.deliveryStartDate+"-"+data.deliveryEndDate);
                            if (data.orderFlowList != null && data.orderFlowList.length > 0) {
                                var flow ="<tr><td><b>操作时间</b></td><td></td><td><b>内容</b></td><td ></td></tr>";
                                for (var i = 0; i < data.orderFlowList.length; i++) {
                                    if(data.orderFlowList[i].state==4){
                                        continue;
                                    }
                                    flow+="<tr>";
                                    flow+="<td colspan='2'>";
                                    flow+=data.orderFlowList[i].createDate;
                                    flow+=" </td>";
                                    flow+=" <td colspan='2'>";
                                    flow+=data.orderFlowList[i].remark;
                                    flow+=" </td>";
                                    flow+="</tr>";
                                }
                                $("#orderMgrFlowList", args).html(flow);
                            }
                        }
                    }
                });
            },
            showOrderDetailNew: function (orderId, orderType,panel) {
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
            getCommentInfo:function(appraiseId, args) {
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
                            if (list != null) {
                                var html = "";
                                $.each(list, function (i, val) {
                                    html += '<img src="' + val + '"/>';
                                });
                                $("#imgList").html(html);
                            }
                        } else {
                            base.error(res.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
                $('#detailModal',args).modal();
            }
        
        };
        
    });
