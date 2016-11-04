define(['base',"packetUserModules"], function (base,packetUserManager) {
    /**
     * 私有成员定义区域
     */
    var datatable;

    var packetUserInit=function (){
        packetUserManager.init();
    }
    return {
        init: function (panel) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;
            $('#createStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });
            $('#createEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });

            $('#updateStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });

            $('#updateEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });

            base.collegeTree($('#selcollage', panel));

            datatable = base.datagrid({
                url: '/orderMgr/ajaxlist',
                singleSelect:false,
                onPageChange:function () {
                    $("#showAmount").hide();
                    $("#btn_statistic").removeClass("disabled");
                    $("#btn_statistic").addClass("btn-default");
                },
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            orderId: $("#orderId", panel).val(),//订单编号
                            orderState: $("#orderState", panel).val(),//订单状态
                            payModel: $("#payModel", panel).val(),//支付类型
                            payType:$("#payType",panel).val(),
                            collegeId: $("#selcollage", panel).val() == "请选择" ? "" : $("#selcollage", panel).val(),//校区
                            updateStartDate: $("#updateStartDate", panel).val()=="" ? "": $("#updateStartDate", panel).val() + " 00:00:00",//更新开始时间
                            updateEndDate: $('#updateEndDate', panel).val()=="" ? "": $("#updateEndDate", panel).val() + " 23:59:59",//更新结束时间
                            winnerMobile: $('#winnerMobile', panel).val(),//小派手机号
                            createUserMobile: $("#createUserMobile", panel).val(),//下单人手机号
                            orderType: $("#orderType", panel).val(),//订单类型
                            createStartDate: $("#createStartDate", panel).val()=="" ? "": $("#createStartDate", panel).val() + " 00:00:00",//创建开始时间
                            createEndDate: $("#createEndDate", panel).val()==""? "":$("#createEndDate", panel).val()+" 23:59:59",//创建结束时间
                            colleges:JSON.stringify(base.getTreeValues($('#selcollage', panel)))
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'orderId',
                        title: '订单编号',
                        formatter: function (value, row, index) {
                            //return '<a href="#" class="seeCheck"><span class="label label-success">' + value + '</span></a>';
                            if (value != '' && value != null) {
                                return '<a href="#" class="orderDetail"><span class="label label-success">' + value + '</span></a>';
                            }
                        },
                        events: {
                            'click .orderDetail': function (e, value, row, index) {
                                //self.detailInit(value, panel, row);
                                self.showOrderDetail(value,row.type,panel);
                            }
                        },
                        width: 100
                    },
                    {
                        field: 'createDate',
                        title: '创建时间',
                        width: 150
                    },
                    {
                        field: 'state',
                        title: '订单状态',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 1:
                                    return "待接单";
                                case 2:
                                    return "已接单";
                                case 3:
                                    return "配送中";
                                case 5:
                                    return "已完成";
                                case 6:
                                    return "已取消";
                                case 7:
                                    return "异常";
                                case 100:
                                    return "创建";
                            }
                        },
                        width: 50
                    },
                    {
                        field: 'lockState',
                        title: '锁定状态',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "否";
                                case 1:
                                    return "收件人分单锁定";
                                case 2:
                                    return "货源分单锁定";
                                case 3:
                                    return "众包转单锁定";
                                case 4:
                                    return "定向派单锁定";
                                case 5:
                                    return "预支付未付款锁定";
                            }
                        },
                        width: 30
                    },
                    {
                        field: 'type',
                        title: '订单类型',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 'packet':
                                    return "代取";
                                case 'agent_packet':
                                    return "代取";
                                case 'send':
                                    return "代寄";
                                case 'errands':
                                    return "代跑腿";
                            }
                        },
                        width: 50
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
                        width: 50
                    },
                    {
                        field: 'payType',
                        title: '支付方式',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 1:
                                    return "微信支付";
                                case 2:
                                    return "支付宝支付";
                                case 3:
                                    return "指端支付";
                                case 4:
                                    return "现金支付";
                                default:
                                    return "未支付";
                            }
                        },
                        width: 80
                    },
                    {
                        field: 'createUserMobile',
                        title: '下单人手机号',
                        width: 150
                    },
                    {
                        field: 'collegeName',
                        title: '校区',
                        formatter: function (value, row, index) {
                            return '<a href="#" class="seeCheck"><span class="label label-success">' + value + '</span></a>';
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                self.showCollegeInfo(value, panel, row);
                            }
                        },
                        width: 200
                    },
                    /*{
                        field: 'serviceType',
                        title: '服务类型',
                        width: 50
                    },*/
                    {
                        field: 'winnerMobile',
                        title: '小派手机号',
                        formatter: function (value, row, index) {
                            return '<a href="#" class="seeCheck"><span class="label label-success">' + value + '</span></a>';
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                self.redirctToPacketUser(value, panel, row);
                            }
                        },
                        width: 150
                    },
                    {
                        field: 'updateDate',
                        title: '状态更新时间',
                        width: 150
                    },
                    {
                        field: 'rewardMoney',
                        title: '打赏金额（元）',
                        formatter: function (value) {
                            return self.formartAmount(value / 100);
                        },
                        width: 10
                    },
                    {
                        field: 'rebateMoney',
                        title: '优惠金额（元）',
                        formatter: function (value) {
                            return self.formartAmount(value / 100);
                        },
                        width: 10
                    },
                    {
                        field: 'finalMoney',
                        title: '实付金额（元）',
                        formatter: function (value) {
                            return self.formartAmount(value / 100);
                        },
                        width: 10
                    },
                    {
                        field:'collegeId',
                        title: 'collegeId',
                        visible: false
                    },
                    {
                        field:'userId',
                        title: 'userId',
                        visible: false
                    }
                ]
            }, '#userTable', panel);
            //查询
            $("#btn_query", panel).click(function () {
                //$("#userTable",panel).bootstrapTable('refresh',{"query": {"offset": 0}});
                if(($('#createEndDate', panel).val()!="" && $('#createStartDate', panel).val()=="")||($('#createEndDate', panel).val()=="" && $('#createStartDate', panel).val()!="")){
                    sweetAlert("", "创建开始时间和结束时间都不能为空!", "info");
                    return;
                }
                if(($('#updateEndDate', panel).val()!="" && $('#updateStartDate', panel).val()=="")||($('#updateEndDate', panel).val()=="" && $('#updateStartDate', panel).val()!="")){
                    sweetAlert("", "更新开始时间和结束时间多不能为空!", "info");
                    return;
                }
                if ((new Date(Date.parse($('#createEndDate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#createStartDate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "创建结束时间不能小于开始时间!", "info");
                    return;
                }
                if ((new Date(Date.parse($('#updateEndDate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#updateStartDate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "更新结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#btn_statistic").removeClass("disabled");
                $("#btn_statistic").addClass("btn-default");
                $("#userTable", panel).bootstrapTable('selectPage', 1);
                $("#showAmount",panel).hide();
            });
            //转单
            $("#btn_assign",panel).click(function () {
                self.assignOrder(panel);
            });
            //取消
            $("#btn_cancel",panel).click(function () {
                self.cancelOrder(panel);
            });
            //统计
            $("#btn_statistic",panel).click(function () {
                self.statisticOrder(panel);
            });
            $("#btn_reset",panel).click(function () {
                base.reset($(".main-box-header", panel));
                base.collegeTree($('#selcollage', panel));
            });
            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header", panel));
                $("#gender", panel).val(null);
                $("#selcollage", panel).select2("val", null);
                $("#selcity", panel).select2("val", null);
                $("#selState", panel).select2("val", null);
                $("#audit_startdate", panel).val("");
                $("#audit_enddate", panel).val("");
                base.collegeTree($('#selcollage', panel));
            });

        },
        formartAmount:function (x) {
            var f = parseFloat(x);
            if (isNaN(f)) {
                return false;
            }
            var f = Math.round(x*100)/100;
            var s = f.toString();
            var rs = s.indexOf('.');
            if (rs < 0) {
                rs = s.length;
                s += '.';
            }
            while (s.length <= rs + 2) {
                s += '0';
            }
            return s;
        },
        showCollegeInfo:function(collegeName,panel,row){
            var self = this;
            var collegeId = row.collegeId;
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
        },
        redirctToPacketUser:function(mobile, panel, row){
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
                "userId": row.userId
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
                                        self.dialogPacketHeadImg("手持身份证正面照片", url)
                                    });
                                })();
                            }else if (data.photoList[i].fileUrl.indexOf("idcard") > 0) {
                                (function () {
                                    var url = data.photoList[i].fileUrl;
                                    $("#labelimg2",panel).unbind("click");
                                    $("#labelimg2",panel).click(function () {
                                        self.dialogPacketHeadImg("身份证正面照片", url);
                                    });
                                })();
                            }else{
                                $('#detailAccountImg',panel).html("<img width='140px' height='120px' src='" + data.photoList[i].fileUrl + "'/>");
                            }
                        }
                    }
                } else {
                    base.error("初始化失败!");
                }
            });
            $('#smallPieDetailModal', panel).modal();
        },
        cancelOrder:function(panel){
            var self = this;
            var arrselections = $("#userTable", panel)
                .bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!!");
                return;
            }
            if (arrselections.length != 1) {
                base.error("只能选择一行进行取消!");
                return;
            }
            var state = arrselections[0].state;
            if(state != 1 && state != 2 && state != 3 && state != 100 && state != 7 ){
                base.error("请选择创建、待接单、已接单、配送中、异常订单进行取消!");
                return;
            }
            $('#cancelOrderId',panel).val(arrselections[0].orderId);
            $('#cancelOrderType',panel).val(arrselections[0].type);
            $('#cancelOrderModal',panel).modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                    message: 'This value is not valid',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        cancelReason: {
                            validators: {
                                notEmpty: {
                                    message: '取消原因不能为空'
                                }
                            }
                        }
                    }
                },
                "#cancelOrderForm", self.updateOrderCanceled,panel
            )
        },
        updateOrderCanceled:function (args) {
            $.post("/orderMgr/cancelOrder",
                {
                    "orderId": $("#cancelOrderId",args).val(),
                    "type":$("#cancelOrderType",args).val(),
                    "cancelReason": $("#cancelReason",args).val()
                }, function (data, status) {
                    if (status == "success") {
                        if (data.success == 0) {
                            base.success("取消成功");
                            // $("#areaTable",args).bootstrapTable('refresh');
                            $("#userTable",args).bootstrapTable('selectPage', 1);
                            $('#cancelOrderModal',args).modal('hide');
                            $('#cancelOrderForm',args).data('bootstrapValidator').resetForm(true);
                        } else {
                            base.error(data.message);
                            $('#cancelOrderForm',args).find(".btn-primary").removeAttr("disabled");
                        }
                    } else {
                        base.error("取消失败!");
                    }
                });
        },
        assignOrder:function (panel) {
            var self = this;
            var arrselections = $("#userTable", panel)
                .bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var orderIds = new Array();
            var collegeIds = new Array();
            var states = new Array();
            var lockState = new Array();
            for(var i=0;i<arrselections.length;i++){
                orderIds[i] = arrselections[i].orderId
                collegeIds[i] = arrselections[i].collegeId;
                states[i] = arrselections[i].state;
                lockState[i] = arrselections[i].lockState;
            }
            for(var i=0;i<arrselections.length;i++){
                if (collegeIds[0]!=collegeIds[i]){
                    base.error("请选择同一个校区的订单!");
                    return;
                }
                if(states[i] != 1){
                    base.error("请选择待接单的订单!");
                    return;
                }
                if(lockState[i] != 0){
                    base.error("请选择未锁定的订单!");
                    return;
                }
            }
            var title = "总部派单";
            var href = '/orderMgr/topusers?orderIds='+JSON.stringify(orderIds)+"&collegeId="+collegeIds[0];
            base.openTab(title,href,packetUserInit);
        },
        detailInit: function (orderId, args, row) {
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
        dialogPacketHeadImg: function (title, url) {
            BootstrapDialog.show({
                cssClass: 'img-dialog',
                title: title,
                message: '<div style="text-align: center;"><img src="' + url + '" style="width: 550px;height: 500px;"></div>'
            });
        },
        statisticOrder:function (panel) {
            var self = this;
            $("#btn_statistic").removeClass("btn-default");
            $("#btn_statistic").addClass("disabled");
            var queryData = {
                "orderId": $("#orderId", panel).val(),//订单编号
                "orderState": $("#orderState", panel).val(),//订单状态
                "payModel": $("#payModel", panel).val(),//支付类型
                "payType":$("#payType",panel).val(),//支付方式
                "collegeId": $("#selcollage", panel).val() == "请选择" ? "" : $("#selcollage", panel).val(),//校区
                "updateStartDate": $("#updateStartDate", panel).val() == "" ? "": $("#updateStartDate", panel).val() + " 00:00:00",//更新开始时间
                "updateEndDate": $('#updateEndDate', panel).val()  == "" ? "": $("#updateEndDate", panel).val() + " 23:59:59",//更新结束时间
                "winnerMobile": $('#winnerMobile', panel).val(),//小派手机号
                "createUserMobile": $("#createUserMobile", panel).val(),//下单人手机号
                "orderType": $("#orderType", panel).val(),//订单类型
                "createStartDate": $("#createStartDate", panel).val()  == "" ? "": $("#createStartDate", panel).val() + " 00:00:00",//创建开始时间
                "createEndDate": $("#createEndDate", panel).val()  == "" ? "":$("#createEndDate", panel).val()+" 23:59:59",//创建结束时间
                "offset":($(".page-number").filter(".active").find("a").html()-1)*$(".page-size").html(),
                "limit":$(".page-size").html(),
                "colleges":JSON.stringify(base.getTreeValues($('#selcollage', panel)))
            };
            $.ajax({
                type: "get",
                contentType: "application/json",
                dataType: "json",
                url: "/orderMgr/statistic",
                data:queryData,
                success: function (data) {
                    if (data && data.data) {
                        data = data.data;
                        $("#rMoney",panel).html(self.formartAmount(data.rewardAmount));
                        $("#dMoney",panel).html(self.formartAmount(data.discountAmount));
                        $("#fMoney",panel).html(self.formartAmount(data.finalAmount));
                        $("#showAmount",panel).show();
                    }
                }
            });
        }
    };
});