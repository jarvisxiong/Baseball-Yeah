define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    function refParentPage(args) {
        args = args || $('#indextab').tabs('getSelected');
        
        $("#areaTable", args).bootstrapTable('refresh', {"query": {"offset": 0}});
    }
    return {
        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var jsonData = {};
            var self = this;
            $('#star').raty({
                number: 10,
                size: 20,
                width: 180
            });
            $.post("/order/doordertask/detail/" + args, function (data) {
                if (data) {
                    jsonData = data;
                    $("#taskNo").text(data.taskNo == null ? "" : data.taskNo);
                    $("#publishTime").text(data.publishTime == null ? "" : data.publishTime);
                    switch (data.taskLevel) {
                        case 1:
                            $("#level").html('<div style="background-color: green; width: 30px;text-align: center; color: black;">绿色</div>');
                            break;
                        case 2:
                            $("#level").html('<div style="background-color: blue; width: 30px;text-align: center;color: black;">蓝色</div>');
                            break;
                        case 3:
                            $("#level").html('<div style="background-color: purple; width: 30px;text-align: center;color: black;">紫色</div>');
                            break;
                        case 4:
                            $("#level").html('<div style="background-color: yellow; width: 30px;text-align: center;color: black;">金色</div>');
                            break;
                        default:
                            break;
                    }

                    $("#deadline").text(data.deadLine == null ? "" : data.deadLine);
                    $("#clame").text(data.claim == null ? "" : data.claim);
                    $("#college").text(data.college == null ? "" : data.college);
                    $("#winner").text(data.winner == null ? "" : data.winner);
                    $("#phone").text(data.phone == null ? "" : data.phone);
                    $("#acceptTime").text(data.receiveTime == null ? "" : data.receiveTime);
                    $("#audiTime").text(data.auditTime == null ? "" : data.auditTime);
                    $("#remark").text(data.winnerRemark == null ? "" : data.winnerRemark);
                    $("#txt_appraise").val(data.auditRemark == null ? "" : data.auditRemark);


                    $('#rule').popover({
                        content: "<ul>" +
                        "<li>" + data.rule + "</li>" +
                        "</ul>",
                        title: "评分规则",
                        html: true,
                        placement: 'top'
                    });
                    if (data.attachmentInfo != null && data.attachmentInfo.length > 0) {
                        for (var i = 0; i < data.attachmentInfo.length; i++) {
                            $("#img_big").append('<li><a href="javascript:;"><img src="' + data.attachmentInfo[i].fileUrl + '" width="500" height="500" alt=""/></a></li>');
                            $("#img_small").append('<li><a href="javascript:;"><img src="' + data.attachmentInfo[i].fileUrl + '" width="80" height="80" alt=""/></a></li>');
                        }
                    }
                    else {
                        $("#hide_pic").css("display", "none");
                    }
                    //初始化图片轮播插件
                    jq('#demo1').banqh({
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
                    $("#btn_save").click(function () {
                        self.save(jsonData);
                    });
                    $("#btn_audit").click(function () {
                        self.audit(jsonData);
                    });
                    $('#star').raty('score', data.overallScore);
                }
            }, "json");
            $("#imageShow").click(function () {
                $("#pictureModal").modal("show");
            });

        },
        save: function (formData) {
            formData.auditRemark = $("#txt_appraise").val();
            if ($('#star').raty('score')) {
                formData.overallScore = $('#star').raty('score');
            }
            else {
                formData.overallScore = 0;
            }
            $.post("/order/doordertask/save", {data: JSON.stringify(formData)}, function (data) {
                if (data.success == 0) {
                    base.success(data.message);
                }
                else {
                    base.error(data.message);
                }
            }, "json");
        },
        audit: function (formData) {
            formData.auditRemark = $("#txt_appraise").val();
            if ($('#star').raty('score')) {
                formData.overallScore = $('#star').raty('score');
            }
            else {
                formData.overallScore = 0;
            }
            $.post("/order/doordertask/submit", {data: JSON.stringify(formData)}, function (data) {
                if (data.success == 0) {
                    base.success(data.message);
                }
                else {
                    base.error(data.message);
                }
            }, "json");
        }
    };
});

