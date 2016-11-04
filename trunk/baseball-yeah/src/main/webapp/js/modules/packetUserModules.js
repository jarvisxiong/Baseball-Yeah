define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    var selectedPacket;
    return {
        init: function (panel) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;
            //var $tablePacketer = $("#selectedPacketer").bootstrapTable();
            panel=panel|| $('#indextab').tabs('getSelected');
            $('#registeStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });
            $('#registeEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2,
                endDate: new Date()
            });
            datatable = base.datagrid({
                onCheck:function(row){
                    var $table = $('#selectedPacketer');
                    var ids = $.map($table.bootstrapTable('getData'), function (row) {
                        return row.userId;
                    });
                    var insertFlag = false;
                    if(ids != null && ids.length > 0){
                        for(var j = 0;j<ids.length;j++){
                            if(ids[j] == row.userId){
                                insertFlag = true;
                            }
                        }
                        if(insertFlag == false){
                            $("#selectedPacketer", panel).bootstrapTable('append', [{"userName":row.userName,"realName":row.realName,"userId":row.userId}]);
                        }
                    }else{
                        $("#selectedPacketer", panel).bootstrapTable('append', [{"userName":row.userName,"realName":row.realName,"userId":row.userId}]);
                    }
                },
                onUncheck:function (row) {
                    $("#selectedPacketer", panel).bootstrapTable('remove', {"field":'userId',"values":[row.userId]});
                },
                onCheckAll:function (rows) {
                    var $table = $('#selectedPacketer');
                    var ids = $.map($table.bootstrapTable('getData'), function (row) {
                        return row.userId;
                    });
                    //var allTableData = $table.bootstrapTable('getData');//获取表格的所有内容行
                    //console.log("allTableData---》"+allTableData);
                    for(var i = 0;i<rows.length;i++){
                        var insertFlag = false;
                        if(ids != null && ids.length > 0){
                            for(var j = 0;j<ids.length;j++){
                                if(ids[j] == rows[i].userId){
                                   insertFlag = true;
                                }
                            }
                            if(insertFlag == false){
                                $("#selectedPacketer", panel).bootstrapTable('append', [{"userName":rows[i].userName,"realName":rows[i].realName,"userId":rows[i].userId}]);
                            }
                        }else{
                            $("#selectedPacketer", panel).bootstrapTable('append', [{"userName":rows[i].userName,"realName":rows[i].realName,"userId":rows[i].userId}]);
                        }
                    }
                },
                onUncheckAll:function (rows) {
                    for(var i = 0;i<rows.length;i++){
                        $("#selectedPacketer", panel).bootstrapTable('remove',{"field":'userId',"values":[rows[i].userId]});
                    }
                },
                onPageChange:function (number,size) {
                },
                url: '/orderMgr/ajaxpusers',
                singleSelect:false,
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            collegeId:$("#collegeId", panel).val(),
                            userName: $("#userName", panel).val(),//用户名
                            realName: $("#realName", panel).val(),//姓名
                            gender: $("#sex", panel).val(),//性别
                            officeRoleType: $("#zhiwu", panel).val(),//职务
                            signupStartTime: $('#registeStartDate', panel).val()=="" ? "": $("#registeStartDate", panel).val() + " 00:00:00",//注册开始时间
                            signupEndTime: $('#registeEndDate', panel).val()=="" ? "": $("#registeEndDate", panel).val() + " 23:59:59",//注册结束时间
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
                        formatter: function (value, row, index) {
                            return '<a href="#" class="seeCheck"><span class="label label-success">' + value + '</span></a>';
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                self.redirctPacketUser(value, panel, row);
                            }
                        },
                        width: 100
                    },
                    {
                        field: 'signupTime',
                        title: '注册时间',
                        width: 150
                    },
                    {
                        field: 'realName',
                        title: '姓名',
                        width: 80
                    },
                    {
                        field: 'gender',
                        title: '性别',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 'p_gender_female':
                                    return "女";
                                case 'p_gender_male':
                                    return "男";
                                case 'p_gender_secret':
                                    return "保密";
                            }
                        },
                        width: 50
                    },
                    {
                        field: 'officeRoleType',
                        title: '职务',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 1:
                                    return "";
                                case 2:
                                    return '<a href="#" class="seeCheck"><span class="label label-success">校园COO</span></a>';
                                case 3:
                                    return '<a href="#" class="seeCheck"><span class="label label-success">校园CEO</span></a>';
                                default:
                                    return "";
                            }
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                self.showDetail(row.userId,panel, self);
                            }
                        },
                        width: 50
                    },
                    {
                        field: 'cityName',
                        title: '城市',
                        width: 150
                    },
                    {
                        field: 'fullName',
                        title: '校区',
                        formatter: function (value, row, index) {
                            return '<a href="#" class="seeCheck"><span class="label label-success">' + value + '</span></a>';
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                self.showCollegeInfo(value, panel, row);
                            }
                        },
                        width: 150
                    },
                    {
                        field: 'dormitoryAddress',
                        title: '宿舍',
                        width: 200
                    },
                    {
                        field:'userId',
                        title: 'userId',
                        visible: false
                    }
                    ]
            }, '#userTable', panel);
            selectedPacket = base.datagrid({
                showExport: false,// 显示导出按钮
                pagination: false,
                singleSelect:false,
                pageNumber:1,   
                //每页的记录行数（*）   
                pageSize: 1000,
                formatNoMatches: function () {
                    return ;
                },
                toolbar: $("#toolbar1", panel),
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        title: '登录账号',
                        field:'userName',
                        width: 100
                    },
                    {
                        title: '用户名',
                        field:'realName',
                        width: 80
                    },
                    {
                        field:'userId',
                        title: 'userId',
                        visible: false
                    }
                ]
            },'#selectedPacketer',panel);
            //查询
            $("#btn_query", panel).click(function () {
                if(($('#registeStartDate', panel).val()!="" && $('#registeEndDate', panel).val()=="")||($('#registeStartDate', panel).val()=="" && $('#registeEndDate', panel).val()!="")){
                    sweetAlert("", "注册开始时间和结束时间都不能为空!", "info");
                    return;
                }
                if ((new Date(Date.parse($('#registeEndDate', panel).val().replace(/-/g, "/"))).getTime() - new Date(Date.parse($('#registeStartDate', panel).val().replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "注册结束时间不能小于开始时间!", "info");
                    return;
                }
                $("#userTable", panel).bootstrapTable('selectPage', 1);
            });
            //删除已选择小派事件
            $("#btn_delPuser",panel).click(function(){
                var $table = $('#selectedPacketer');
                /* var ids = $.map($table.bootstrapTable('getSelections'), function (row) {
                    return row.userId;
                });
               console.log("delete-getSelections-ids---->"+ids);
                $table.bootstrapTable('remove', {
                    field: 'userId',
                    values: ids
                });*/
                var userIdArray = [];
                var tableData = $("#selectedPacketer input[name='btSelectItem']:checked");
                if(tableData && tableData.length > 0){
                    for(var i=0;i<tableData.length;i++){
                        var tableUserName = $(tableData[i]).parent().parent().children("td").eq(1).html();
                        userIdArray.push(tableUserName);
                    }
                    $table.bootstrapTable('remove', {
                        field: 'userName',
                        values: userIdArray
                    });
                }
                $table.bootstrapTable('refresh');
                var allSelections = $("#userTable", panel).bootstrapTable("getAllSelections");
                if (allSelections != null && allSelections.length > 0) {
                   /* var removerCheck = [];
                    for (var i = 0; i < allSelections.length; i++) {
                        removerCheck.push(allSelections[i].userName);
                    }*/
                    $("#userTable", panel).bootstrapTable("uncheckBy", {field: "userName", values: userIdArray});
                }
            });
            var assignFlag = 0;
            //派单点击事件
            $("#btn_assignOrder",panel).click(function () {
                /*var userIds = $.map($table.bootstrapTable('getData'), function (row) {
                    return row.userId;
                });*/               
                var $table = $("#selectedPacketer");
                var tableData = $("#selectedPacketer input[name='btSelectItem']:checked");
                var arrselections = $("#selectedPacketer", panel)
                    .bootstrapTable('getData');
                if (tableData==null || tableData.length <= 0 ) {
                    base.error("请选择有效数据!!");
                    return;
                }
                if(assignFlag ==1){
                    sweetAlert("", "派单处理中...", "info");
                    return;
                }
                if(assignFlag ==2){
                    sweetAlert("", "单子已经派成功了！", "info");
                    return;
                }
                if(assignFlag ==3){
                    sweetAlert("", "派单失败！", "info");
                    return;
                }
                $("#btn_assignOrder").removeClass("btn-default");
                $("#btn_assignOrder").addClass("disabled");
                assignFlag = 1;
                var userIds = new Array();
                for(var i=0;i<arrselections.length;i++){
                    for(var j=0;j<tableData.length;j++){
                        var tableUserName = $(tableData[j]).parent().parent().children("td").eq(1).html();
                        if(arrselections[i].userName==tableUserName){
                            userIds.push(arrselections[i].userId);
                        }
                    }
                }
                var orderIds = $("#orderIds").val();
                $.post("/orderMgr/assignOrder",
                    {
                        "orderIds": orderIds,
                        "userIds": JSON.stringify(userIds)
                    }, function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                base.success("派单成功");
                                assignFlag = 2;
                               // var title = "订单管理-派单";
                               // base.colseTab(title);
                               // $("#userTable",panel).bootstrapTable('selectPage', 1);
                                $("#selectedPacketer",panel).bootstrapTable('selectPage', 1);
                                $('#packeterForm',panel).data('bootstrapValidator').resetForm(true);
                            } else {
                                base.error(data.message);
                                $('#packeterForm',panel).find(".btn-primary").removeAttr("disabled");
                            }
                        } else {
                            assignFlag = 3;
                            base.error("派单失败!");
                        }
                    });
            });
            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header", panel));
                $("#gender", panel).val(null);
                $("#selcollage", panel).select2("val", null);
                $("#selcity", panel).select2("val", null);
                $("#selState", panel).select2("val", null);
                $("#audit_startdate", panel).val("");
                $("#audit_enddate", panel).val("");
            });
        },
        showCollegeInfo:function(collegeName,panel,row){
            var self = this;
            var collegeId = $("#collegeId", panel).val();
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
        showDetail : function(orderId, panel, self){
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
                    if(data.state == 0) {
                        $('#detailState', panel).val("冻结");
                    }
                    if(data.state == 1) {
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
                    } else{
                        $('#detailAbc', panel).val("-");
                    }
                } else {
                    base.error("初始化失败!");
                }
            });

            $('#detailTable',panel).bootstrapTable({
                // 请求后台的URL（*）
                url : '/office/assist/smallPieList',
                striped: true, // 是否显示行间隔色
                singleSelect: true,
                queryParams : function(pa) {
                    return {"userId" : orderId};
                },
                height: 320,
                columns : [ {
                    field : 'phone',
                    title : '登陆账号',
                    width : 100,
                    sortable: true,
                    formatter: function (value, row, index) {
                        return "<a href='#' class='showCrowdUserDetail1' id=\'" + row.userId + "\'>" + value + "</a>";
                        // return "<a href='#' onclick='showAuditDetail(\"" + row.smsModelId + "\");'>" + value + "</a>";
                    },
                    events: {
                        'click .showCrowdUserDetail1': function (e, value, row, index) {
                            self.showPieDetail(row.userId, panel, row);
                        }
                    }
                }, {
                    field : 'pulicTime',
                    title : '注册时间',
                    width : 100
                }, {
                    field : 'realName',
                    title : '姓名',
                    width : 100
                }, {
                    field: 'gender',
                    title: '性别',
                    formatter: function (value,row, index) {
                        var result = value;
                        if (value == "p_gender_male") {
                            result = "男";
                        } else if (value == "p_gender_female") {
                            result = "女";
                        } else if (value == "p_gender_secret") {
                            result = "保密";
                        } else{
                            result = "-";
                        }
                        return result;
                    }
                } , {
                    field: 'officeRoleType',
                    title: '职务',
                    formatter: function (value,row, index) {
                        var result = value;
                        if (value == "1") {
                            result = "普通小派";
                        } else if (value == "2") {
                            result = "校园COO";
                        } else if (value == "3") {
                            result = "校园CEO";
                        } else{
                            result = "-";
                        }
                        return result;
                    }
                }, {
                    field : 'cityName',
                    title : '城市',
                    width : 100
                }, {
                    field : 'collegeName',
                    title : '校园',
                    width : 100
                }, {
                    field : 'dormitoryAddress',
                    title : '宿舍',
                    width : 100
                }, {
                    field: 'state',
                    title: '账户状态',
                    formatter: function (value,row, index) {
                        var result = value;
                        if (value == "0") {//1激活,0未激活
                            result = "冻结";
                        }else if(value == "1"){
                            result = "正常";
                        }
                        return result;
                    }
                }
                ]
            }).bootstrapTable('refresh',{query: {"userId" : orderId}});

            $('#detailModal',panel).modal();


            $('#storeTable',panel).bootstrapTable({
                // 请求后台的URL（*）
                url : '/office/assist/storeList',
                striped: true, // 是否显示行间隔色
                singleSelect: true,
                queryParams : function(pa) {
                    return {"userId" : orderId};
                },
                height: 320,
                columns : [
                    {
                        field : 'storeName',
                        title : '商户名称',
                        width : 100
                    }, {
                        field : 'phone',
                        title : '商户手机',
                        width : 100
                    }, {
                        field : 'dormitoryAddress',
                        title : '详细地址',
                        width : 100
                    },
//					{
//						field : 'collegeName',
//						title : '学校',
//						width : 100
//					}, 
                    {
                        field : 'pulicTime',
                        title : '注册时间',
                        width : 100
                    }
                ]
            }).bootstrapTable('refresh',{query: {"userId" : orderId}});
//
//				$('#storeTable',panel).modal();
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
        redirctPacketUser:function (mobile, panel, row) {
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
                                        self.dialogPacketImg("手持身份证正面照片", url)
                                    });
                                })();
                            }else if (data.photoList[i].fileUrl.indexOf("idcard") > 0) {
                                (function () {
                                    var url = data.photoList[i].fileUrl;
                                    $("#labelimg2",panel).unbind("click");
                                    $("#labelimg2",panel).click(function () {
                                        self.dialogPacketImg("身份证正面照片", url);
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
        dialogPacketImg: function (title, url) {
            BootstrapDialog.show({
                cssClass: 'img-dialog',
                title: title,
                message: '<div style="text-align: center;"><img src="' + url + '" style="width: 550px;height: 500px;"></div>'
            });
        }
    };
});