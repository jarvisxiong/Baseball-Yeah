define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;

    function refParentPage(args) {
        args = args || $('#indextab').tabs('getSelected');
        datatable.bootstrapTable('refresh', {"query": {"offset": 0}});
        // $("#areaTable", args).bootstrapTable('refresh', {"query": {"offset": 0}});
    }

    return {
        init: function (panel) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            //开始时间1
            $('#starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker', panel).datetimepicker('setStartDate', startTime);
            });
            //开始时间2
            $('#starttimePicker2', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker2', panel).datetimepicker('setStartDate', startTime);
            });
            //开始时间3
            $('#starttimePicker3', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker3', panel).datetimepicker('setStartDate', startTime);
            });
            //结束时间1
            $('#endtimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker', panel).datetimepicker('setEndDate', endTime);
            });
            //结束时间2
            $('#endtimePicker2', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker2', panel).datetimepicker('setEndDate', endTime);
            });

            //结束时间3
            $('#endtimePicker3', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker3', panel).datetimepicker('setEndDate', endTime);
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
                    $("#add_College", panel).select2({
                        data: data.data
                    });
                }
            });
            datatable = base.datagrid({
                url: '/order/doordertask/query',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            mainTaskNo: $("#mainTaskNo", panel).val(),
                            taskNo: $("#taskNo", panel).val(),
                            orderId: $("#orderId", panel).val(),
                            state: $("#stateSelect", panel).val(),
                            winnerName: $("#winner", panel).val(),
                            phone: $("#phone", panel).val(),
                            startReceiveTime: $("#startdate", panel).val() && ($("#startdate", panel).val() + " 00:00:00"),
                            endReceiveTime: $("#enddate", panel).val() && ( $("#enddate", panel).val() + " 23:59:59"),
                            startOverTime: $("#startdate3", panel).val() && ( $("#startdate3", panel).val() + " 00:00:00"),
                            endOverTime: $("#enddate3", panel).val() && ($("#enddate3", panel).val() + " 23:59:59"),
                            startAuditTime: $("#startdate2", panel).val() && ($("#startdate2", panel).val() + " 00:00:00"),
                            endAuditTime: $("#enddate2", panel).val() && ($("#enddate2", panel).val() + " 23:59:59")
                        });
                },
                onPageChange: function (number, size) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#doOrderTaskTable', panel).find("thead").height() + $('#doOrderTaskTable', panel).find("tbody").height()
                        + 3 + $('#doOrderTaskTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#doOrderTaskTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#doOrderTaskTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#doOrderTaskTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame", panel).height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame", panel).height(1150);
                    }
                },
                onSort: function () {
                    datatable.bootstrapTable('refresh')
                },
                columns: [
                    {checkbox: true},
                    {
                        field: 'taskNo',
                        title: '任务单号',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return '<a href="#" class="seeCheck"><span class="label label-success">' + value + '</span></a>';
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                self.showDetail(panel, row);
                            }
                        },
                        width: 150

                    },
                    {
                        field: 'orderId',
                        title: '订单编号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'theme',
                        title: '任务名称',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'taskUnitPrice',
                        title: '任务赏金/元',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            var result = value;
                            if (/^[0-9]*$/.test(value)) {
                                result = value / 100;
                            }
                            return result;
                        }
                    },
                    {
                        field: 'finalMoney',
                        title: '实际赏金/元',
                        sortable: true,
                        formatter: function (value,
                                             row, index) {
                            var result = value;
                            if (/^[0-9]*$/.test(value)) {
                                result = value / 100;
                            }
                            return result;
                        }
                    },
                    {
                        field: 'collegeName',
                        title: '学校名称',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'winner',
                        title: '小派姓名',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'phone',
                        title: '手机号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'receiveTime',
                        title: '接单时间',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'state',
                        title: '状态',
                        sortable: true,
                        width: 150,
                        formatter: function (value, row, index) {
                            var stateName;
                            switch (row.state) {
                                case 3:
                                    stateName = "已接单";
                                    break;
                                case 10:
                                    stateName = "待审核";
                                    break;
                                case 6:
                                    stateName = "已取消";
                                    break;
                                case 5:
                                    stateName = "已完成";
                                    break;
                                default:
                                    stateName = "进行中";
                                    break;
                            }
                            return stateName;
                        }
                    },
                    {
                        field: 'auditTime',
                        title: '提交审核时间',
                        sortable: true,
                        width: 200
                    },
                    {
                        field: 'endTime',
                        title: "结束时间",
                        sortable: true,
                        width: 200
                    },
                    {
                        title: '操作',
                        width: 200,
                        formatter: function (value, row, index) {
                            if (row.state == 10) {
                                return '<a href="#" class="seeCheck"><span class="label label-success">审核</span></a> ';
                            }
                        },
                        events: {
                            'click .seeCheck': function (e, value, row, index) {
                                base.openTab("做单任务审核", "/order/gotoDoOrderTaskAudit/" + row.orderId, self.auditInit, self);
                            }
                        },
                    }
                ]
            }, '#doOrderTaskTable', panel);
            $("#btn_query", panel).click(function () {
                datatable.bootstrapTable('refresh', {"query": {"offset": 0}});
            });
            $("#clearSearch", panel).click(function () {
                base.reset(".main-box-header");
            });

        },
        showDetail: function (panel, row) {

            $('#editModal', panel).modal({
                keyboard: false,
                backdrop: 'static'
            });
            $('#star', panel).raty({
                starHalf: '/js/raty/img/star-half.png',
                starOff: '/js/raty/img/star-off.png',
                starOn: '/js/raty/img/star-on.png',
                number: 10,
                size: 20,
                width: 180
            });
            $.post("/order/doordertask/detail/" + row.orderId, function (data) {
                if (data) {
                    jsonData = data;
                    $("#deatiltaskNo", panel).text(data.taskNo == null ? "" : data.taskNo);
                    $("#publishTime", panel).text(data.publishTime == null ? "" : data.publishTime);
                    switch (data.taskLevel) {
                        case 1:
                            $("#level", panel).html('<div style="background-color: green; width: 30px;text-align: center; color: black;">绿色</div>');
                            break;
                        case 2:
                            $("#level", panel).html('<div style="background-color: blue; width: 30px;text-align: center;color: black;">蓝色</div>');
                            break;
                        case 3:
                            $("#level", panel).html('<div style="background-color: purple; width: 30px;text-align: center;color: black;">紫色</div>');
                            break;
                        case 4:
                            $("#level", panel).html('<div style="background-color: yellow; width: 30px;text-align: center;color: black;">金色</div>');
                            break;
                        default:
                            break;
                    }

                    $("#deadline", panel).text(data.deadLine == null ? "" : data.deadLine);
                    $("#clame", panel).text(data.claim == null ? "" : data.claim);
                    $("#college", panel).text(data.college == null ? "" : data.college);
                    $("#deatilwinner", panel).text(data.winner == null ? "" : data.winner);
                    $("#deatilphone", panel).text(data.phone == null ? "" : data.phone);
                    $("#acceptTime", panel).text(data.receiveTime == null ? "" : data.receiveTime);
                    $("#audiTime", panel).text(data.auditTime == null ? "" : data.auditTime);
                    $("#remark", panel).text(data.winnerRemark == null ? "" : data.winnerRemark);
                    $("#txt_appraise", panel).val(data.auditRemark == null ? "" : data.auditRemark);


                    $('#rule', panel).popover({
                        content: "<ul>" +
                        "<li>" + data.rule + "</li>" +
                        "</ul>",
                        title: "评分规则",
                        html: true,
                        placement: 'top'
                    });
                    $("#img_big", panel).empty();
                    $("#img_small", panel).empty();
                    if (data.attachmentInfo != null && data.attachmentInfo.length > 0) {
                        for (var i = 0; i < data.attachmentInfo.length; i++) {
                            $("#img_big", panel).append('<li><a href="javascript:;"><img src="' + data.attachmentInfo[i].fileUrl + '" width="500" height="500" alt=""/></a></li>');
                            $("#img_small", panel).append('<li><a href="javascript:;"><img src="' + data.attachmentInfo[i].fileUrl + '" width="80" height="80" alt=""/></a></li>');
                            if (i == (data.attachmentInfo.length - 1)) {
                                //初始化图片轮播插件
                                $('#demo1', panel).banqh({
                                    box: "#demo1",//总框架
                                    pic: "#ban_pic1",//大图框架
                                    pnum: "#ban_num1",//小图框架
                                    prev_btn: "#prev_btn1",//小图左箭头
                                    next_btn: "#next_btn1",//小图右箭头
                                    pop_prev: "#prev2",//弹出框左箭头
                                    pop_next: "#next2",//弹出框右箭头
                                    prev: "#prev1",//大图左箭头
                                    next: "#next1",//大图右箭头
                                    pop_div: "#demo2",//弹出框框架
                                    pop_pic: "#ban_pic2",//弹出框图片框架
                                    pop_xx: ".pop_up_xx",//关闭弹出框按钮
                                    mhc: ".mhc",//朦灰层
                                    autoplay: true,//是否自动播放
                                    interTime: 5000,//图片自动切换间隔
                                    delayTime: 400,//切换一张图片时间
                                    pop_delayTime: 400,//弹出框切换一张图片时间
                                    order: 0,//当前显示的图片（从0开始）
                                    picdire: true,//大图滚动方向（true为水平方向滚动）
                                    mindire: true,//小图滚动方向（true为水平方向滚动）
                                    min_picnum: 5,//小图显示数量
                                    pop_up: true//大图是否有弹出框
                                })
                            }
                        }
                    }
                    else {
                        $("#hide_pic", panel).css("display", "none");
                    }

                    $("#btn_save", panel).click(function () {
                        self.save(jsonData, panel);
                    });
                    $("#btn_audit", panel).click(function () {
                        self.audit(jsonData, panel);
                    });
                    $('#star', panel).raty('score', data.overallScore);
                }
            }, "json");
            $("#imageShow", panel).click(function () {
                $("#pictureModal", panel).modal("show");
            });

        },
        auditInit: function (panel) {
            panel = panel || $('#indextab').tabs('getSelected');
            var jsonData = {};
            var self = this;
            $('#star', panel).raty({
                starHalf: '/js/raty/img/star-half.png',
                starOff: '/js/raty/img/star-off.png',
                starOn: '/js/raty/img/star-on.png',
                number: 10,
                size: 20,
                width: 180
            });
            $.post("/order/doordertask/detail/" + $("#inputOerder", panel).val(), function (data) {
                if (data) {
                    jsonData = data;
                    $("#taskNo", panel).text(data.taskNo == null ? "" : data.taskNo);
                    $("#publishTime", panel).text(data.publishTime == null ? "" : data.publishTime);
                    switch (data.taskLevel) {
                        case 1:
                            $("#level", panel).html('<div style="background-color: green; width: 30px;text-align: center; color: black;">绿色</div>');
                            break;
                        case 2:
                            $("#level", panel).html('<div style="background-color: blue; width: 30px;text-align: center;color: black;">蓝色</div>');
                            break;
                        case 3:
                            $("#level", panel).html('<div style="background-color: purple; width: 30px;text-align: center;color: black;">紫色</div>');
                            break;
                        case 4:
                            $("#level", panel).html('<div style="background-color: yellow; width: 30px;text-align: center;color: black;">金色</div>');
                            break;
                        default:
                            break;
                    }

                    $("#deadline", panel).text(data.deadLine == null ? "" : data.deadLine);
                    $("#clame", panel).text(data.claim == null ? "" : data.claim);
                    $("#college", panel).text(data.college == null ? "" : data.college);
                    $("#winner", panel).text(data.winner == null ? "" : data.winner);
                    $("#phone", panel).text(data.phone == null ? "" : data.phone);
                    $("#acceptTime", panel).text(data.receiveTime == null ? "" : data.receiveTime);
                    $("#audiTime", panel).text(data.auditTime == null ? "" : data.auditTime);
                    $("#remark", panel).text(data.winnerRemark == null ? "" : data.winnerRemark);
                    $("#txt_appraise", panel).val(data.auditRemark == null ? "" : data.auditRemark);


                    $('#rule', panel).popover({
                        content: "<ul>" +
                        "<li>" + data.rule + "</li>" +
                        "</ul>",
                        title: "评分规则",
                        html: true,
                        placement: 'top'
                    });

                    $("#img_big", panel).empty();
                    $("#img_small", panel).empty();
                    if (data.attachmentInfo != null && data.attachmentInfo.length > 0) {
                        for (var i = 0; i < data.attachmentInfo.length; i++) {
                            $("#img_big", panel).append('<li><a href="javascript:;"><img src="' + data.attachmentInfo[i].fileUrl + '" width="500" height="500" alt=""/></a></li>');
                            $("#img_small", panel).append('<li><a href="javascript:;"><img src="' + data.attachmentInfo[i].fileUrl + '" width="80" height="80" alt=""/></a></li>');
                            if (i == (data.attachmentInfo.length - 1)) {
                                //初始化图片轮播插件
                                $('#demo2', panel).banqh({
                                    box: $("#demo1", panel),//总框架
                                    pic: $("#ban_pic1", panel),//大图框架
                                    pnum: $("#ban_num1", panel),//小图框架
                                    prev_btn: $("#prev_btn1", panel),//小图左箭头
                                    next_btn: $("#next_btn1", panel),//小图右箭头
                                    pop_prev: $("#prev2", panel),//弹出框左箭头
                                    pop_next: $("#next2", panel),//弹出框右箭头
                                    prev: $("#prev1", panel),//大图左箭头
                                    next: $("#next1", panel),//大图右箭头
                                    pop_div: $("#demo2", panel),//弹出框框架
                                    pop_pic: $("#ban_pic2", panel),//弹出框图片框架
                                    pop_xx: $(".pop_up_xx", panel),//关闭弹出框按钮
                                    mhc: ".mhc",//朦灰层
                                    autoplay: true,//是否自动播放
                                    interTime: 5000,//图片自动切换间隔
                                    delayTime: 400,//切换一张图片时间
                                    pop_delayTime: 400,//弹出框切换一张图片时间
                                    order: 0,//当前显示的图片（从0开始）
                                    picdire: true,//大图滚动方向（true为水平方向滚动）
                                    mindire: true,//小图滚动方向（true为水平方向滚动）
                                    min_picnum: 5,//小图显示数量
                                    pop_up: true//大图是否有弹出框
                                })
                            }
                        }
                    }
                    else {
                        $("#hide_pic", panel).css("display", "none");
                    }

                    $("#btn_save", panel).click(function () {
                        self.save(jsonData, panel);
                    });
                    $("#btn_audit", panel).click(function () {
                        self.audit(jsonData, panel);
                    });
                    $('#star', panel).raty('score', data.overallScore);
                }
            }, "json");
            $("#imageShow", panel).click(function () {
                $("#pictureModal", panel).modal("show");
            });

        },
        save: function (formData, panel) {
            formData.auditRemark = $("#txt_appraise", panel).val();
            if ($('#star', panel).raty('score')) {
                formData.overallScore = $('#star', panel).raty('score');
            }
            else {
                formData.overallScore = 0;
            }
            $.post("/order/doordertask/save", {data: JSON.stringify(formData)}, function (data) {
                if (data.success == 0) {
                    base.success(data.message);
                    base.colseTab("做单任务审核", "做单任务管理", refParentPage);
                }
                else {
                    base.error(data.message);
                }
            }, "json");
        },
        audit: function (formData, panel) {
            formData.auditRemark = $("#txt_appraise", panel).val();
            if ($('#star', panel).raty('score')) {
                formData.overallScore = $('#star', panel).raty('score');
            }
            else {
                formData.overallScore = 0;
            }
            $.post("/order/doordertask/submit", {data: JSON.stringify(formData)}, function (data) {
                if (data.success == 0) {
                    base.success(data.message);
                    base.colseTab("做单任务审核", "做单任务管理", refParentPage);
                }
                else {
                    base.error(data.message);
                }
            }, "json");
        }
    };
});
