define(['base'],
    function (base) {

        Array.prototype.indexOf = function (val) {
            for (var i = 0; i < this.length; i++) {
                if (this[i] == val) return i;
            }
            return -1;
        };
        Array.prototype.remove = function (val) {
            var index = this.indexOf(val);
            if (index > -1) {
                this.splice(index, 1);
            }
        };
        /**
         * 私有成员定义区域
         */
        function show(args) {
            $("#taskSave", args).removeAttr("disabled");
            $("#taskPub", args).removeAttr("disabled");
        }

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
                var self = this;

                base.datagrid(
                    {
                        url: '/order/taskorder/oders',
                        queryParams: function (params) {
                            return $.extend(params,
                                {
                                    theme: $("#theme").val(),
                                    dutyId: $("#dutyId").val(),
                                    taskLevel: $("#taskLevel").val(),
                                    startDate: $('#submit_startdate').val() == "" ? "" : $('#submit_startdate').val() + " 00:00:00",
                                    endDate: $('#submit_enddate').val() == "" ? "" : $('#submit_enddate').val() + " 23:59:59",
                                    pubstartDate: $('#pub_startdate').val(),
                                    collegeId: $("#selcollage").val(),
                                    state: $("#taskState").val(),
                                    taskNo: $("#taskNo").val()
                                });
                        },
                        columns: [
                            {
                                checkbox: true
                            },
                            {
                                field: 'theme',
                                title: '主题',
                                sortable: true,
                                width: 200
                            },
                            {
                                field: 'taskNo',
                                title: '任务单号',
                                sortable: true,
                                formatter: function (value, row, index) {
                                    return '<a href="#" class="seeCheck"><span class="label label-success">' + value + '</span></a>';
                                },
                                events: {
                                    'click .seeCheck': function (e, value, row, index) {
                                        self.editInit(row.taskSubId, args, row);
                                    }
                                },
                                width: 200
                            },
                            {
                                field: 'taskLevel',
                                title: '级别',
                                sortable: true,
                                formatter: function (value) {
                                    switch (value) {
                                        case 1:
                                            return "绿色";
                                        case 2:
                                            return "蓝色";
                                        case 3:
                                            return "紫色";
                                        case 4:
                                            return "金色";
                                    }
                                }
                            },
                            {
                                field: 'state',
                                title: '状态',
                                formatter: function (value, row) {

                                    if (new Date() > new Date(row.effectiveEndDate)) {
                                        return "已结束";
                                    }
                                    switch (value) {
                                        case "0":
                                            return "新建";
                                        case "1" :
                                            return "已发布";
                                        case "2":
                                            return "已生成";
                                        case "3":
                                            return "已终止";

                                    }
                                }
                            },
                            {
                                field: 'taskUnitPrice',
                                title: '任务赏金',
                                formatter: function (value) {
                                    return value / 100;
                                }
                            },
                            {
                                field: 'unitTotal',
                                title: '任务数量'
                            },
                            {
                                field: 'carry',
                                title: '进行中数量'
                            },
                            {
                                field: 'done',
                                title: '完成数量'
                            },
                            {
                                field: 'audit',
                                title: '待审核数量'
                            },

                            {
                                field: 'cancel',
                                title: '取消数量'
                            },
                            {
                                field: 'fullName',
                                title: '校区'
                            },
                            {
                                field: 'userName',
                                title: '创建人',
                                sortable: true
                            },
                            {
                                field: 'dutyName',
                                title: '部门',
                                sortable: true
                            },

                            {
                                field: 'createTime',
                                title: '创建时间',
                                sortable: true
                            },
                            {
                                field: 'taskId',
                                title: 'taskId',
                                visible: false
                            }
                        ]
                    }, '#areaTable', args);

                $.ajax({
                    type: "POST",
                    url: "/manage/dept/selectalldept",
                    dataType: "json",
                    success: function (data) {
                        $("#dutyId", args).select2({
                            data: data,
                            placeholder: '请选择',
                            allowClear: true
                        });
                    }
                });


                $.ajax({
                    type: "POST",
                    url: "/manage/college/getcollageforsel",
                    dataType: "json",
                    success: function (data) {
                        $("#selcollage").select2({
                            data: data.data,
                            placeholder: '请选择',
                            allowClear: true
                        });
                        $("#add_College", args).select2({
                            data: data.data
                        });
                    }
                });

                //开始时间
                $('#starttimePicker', args).datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    startView: 2,
                    minView: 2,
                    todayHighlight: true
                })

                //结束时间
                $('#endtimePicker', args).datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        startView: 2,
                        minView: 2,
                        todayHighlight: true
                    });

                $("#btn_add", args).click(function () {
                    base.openTab("新增任务", "/order/gotoAddTask", self.add, self);
                });
                $("#btn_edit", args).click(function () {
                    self.edit(args);
                });
                $("#btn_delete", args).click(function () {
                    self.remove(args);
                });
                $("#btn_query", args).click(function () {
                    $("#areaTable", args).bootstrapTable('refresh', {"query": {"offset": 0}});
                });
                $("#btn_pub", args).click(function () {
                    self.subEditPub(1, args);
                });
                $("#btn_stop", args).click(function () {
                    self.stop();
                });
                $("#clearSearch", args).click(function () {
                    base.reset(".main-box-body");
                    $("#provinceIds", args).val("").trigger("change");
                });

            },
            add: function (args) {
                var self = this;
                window.files = [];
                args = args || $('#indextab').tabs('getSelected');
                window.cover = "";
                $("#btn_add", args).click(function () {
                    self.addSub(args);
                });
                $("#btn_delete", args).click(function () {
                    self.subRemove(args);
                });
                $("#taskSave", args).click(function () {
                    self.create(0, args);
                });
                $("#taskPub", args).click(function () {
                    self.create(1, args);
                });
                $("#taskLevel", args).change(function () {
                    switch ($(this).val()) {
                        case "1":
                            $(this).css("background-color", "#008000")
                            break;
                        case "2":
                            $(this).css("background-color", "#428bca")
                            break;
                        case "3":
                            $(this).css("background-color", "#ee82ee")
                            break;
                        case "4":
                            $(this).css("background-color", "#ffff00")
                            break;
                    }
                })

                $("#taskUnitPrice", args).change(function () {
                    $("#moneyTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal", args).val() || 0)))
                })
                $("#unitTotal", args).change(function () {
                    $("#moneyTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitPrice", args).val() || 0)))
                    $("#numberTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitNum", args).val() || 0)))
                })

                $("#taskUnitNum", args).change(function () {
                    $("#numberTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal", args).val() || 0)))
                })
                $('#addModal', args).on('shown.bs.modal', function () {
                    $('#addForm', args).data('bootstrapValidator').resetForm(true);
                    $("#moneyTotal", args).text("");
                    $("#numberTotal", args).text("");
                })
                window.myDropzone = new Dropzone("#myId", {
                    init: function () {
                        //添加监听 下面success是重写
                        this.on("removedfile", function (file) {
                            window.files.remove(file.fileid);
                        });
                    },
                    url: "/order/taskorder/uploadFile",
                    dictDefaultMessage: "将文件拖至此处或点击上传",
                    addRemoveLinks: true,
                    acceptedFiles: ".png,.jpg,.bmp",
                    maxFiles: 10,
                    dictRemoveFile: "删除",
                    success: function (data, resultData) {
                        if (resultData.success > -1) {
                            data.fileid = resultData.data.id;//回调的附件id给到file
                            window.files.push(resultData.data.id);
                        }
                    }
                })

                window.myCover = new Dropzone("#cover", {
                    init: function () {
                        this.on("removedfile", function (file) {
                            window.files.remove(file.fileid);
                        });
                    },
                    url: "/order/taskorder/uploadFile",
                    dictDefaultMessage: "将文件拖至此处或点击上传",
                    acceptedFiles: ".png,.jpg,.bmp",
                    maxFiles: 1,
                    addRemoveLinks: true,
                    dictRemoveFile: "删除",
                    success: function (data, resultData) {
                        if (resultData.success > -1) {
                            data.fileid = resultData.data.id;
                            window.files.push(resultData.data.id);
                            window.cover = resultData.data.id;
                        } else {
                            base.error("封面图片上传失败！请删除后再试！");
                        }
                    }
                })

                var data = [];

                $('#orderSubTable', args).bootstrapTable({
                    data: data,
                    toolbar: $("#toolbar", args),
                    singleSelect: true,
                    height: 300,
                    uniqueId: "ID",
                    columns: [
                        {
                            checkbox: true
                        },
                        {
                            field: 'ID',
                            title: 'ID',
                            visible: false
                        },

                        {
                            field: 'taskUnitPrice',
                            title: '任务每单赏金'
                        },
                        {
                            field: 'unitTotal',
                            title: '任务单子数量'
                        }, {
                            field: 'upperLimit',
                            title: '每人接单上限'
                        },
                        {
                            field: 'taskUnitNum',
                            title: '每单多少件'
                        },
                        {
                            field: 'collegeName',
                            title: '学校'
                        }, {
                            field: 'collegeId',
                            title: 'ID',
                            visible: false
                        },

                        {
                            field: 'state',
                            title: '状态',
                            formatter: function (value) {
                                switch (value) {
                                    case 0:
                                        return "未发布";
                                    case 1:
                                        return "已发布";
                                    case 2:
                                        return "已生成";
                                }
                            }
                        },
                        {
                            field: 'effectiveStartDate',
                            title: '开始有效日期'
                        },
                        {
                            field: 'effectiveEndDate',
                            title: '结束有效日期'
                        },
                        {
                            field: 'deadline',
                            title: '任务截止日期'
                        }
                    ]
                });


                //开始时间
                $('#starttimePicker', args).datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                })

                //结束时间
                $('#endtimePicker', args).datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });
                //结束时间
                $('#deadlinePicker', args).datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });
                $.ajax({
                    type: "POST",
                    url: "/manage/college/getcollageforsel",
                    dataType: "json",

                    success: function (data) {
                        $("#add_College", args).select2({
                            data: data.data
                        });
                    }
                })
            },
            addSub: function (args) {
                var self = this;
                $('#addModal', args).modal({
                    keyboard: false,
                    backdrop: 'static'
                });
                base.validator(
                    {
                        message: 'This value is not valid',
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            taskUnitPrice: {
                                validators: {
                                    notEmpty: {
                                        message: '任务每单赏金不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            },
                            unitTotal: {
                                validators: {
                                    notEmpty: {
                                        message: '任务单子数量不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            },
                            upperLimit: {
                                validators: {
                                    notEmpty: {
                                        message: '每人接单上限不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            }, taskUnitNum: {
                                validators: {
                                    notEmpty: {
                                        message: '每单多少件上限不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            },
                            add_Collage: {
                                validators: {
                                    notEmpty: {
                                        message: '学校不能为空'
                                    }
                                }
                            }
                        }
                    }, "#addForm", self.subCreate, args)
            },
            subCreate: function (args) {

                if ($("#deadlineTime", args).val() == "") {
                    base.error("任务完成时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ($("#effectiveStartDate", args).val() == "") {
                    base.error("活动开始时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ($("#effectiveEndDate", args).val() == "") {
                    base.error("活动结束时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveStartDate", args).val())) > (new Date($("#effectiveEndDate", args).val()))) {
                    base.error("活动开始时间要小于结束时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate", args).val())) > (new Date($("#deadlineTime", args).val()))) {
                    base.error("活动结束时间要小于任务完成时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#deadlineTime", args).val())) < (new Date())) {
                    base.error("任务完成时间要大于当前时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate", args).val())) < (new Date())) {
                    base.error("活动结束时间要大于当前时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }

                var selData = $("#add_College", args).select2("data");
                for (var i = 0; i < selData.length; i++) {
                    $('#orderSubTable', args).bootstrapTable('append', {
                        "ID": $('#orderSubTable', args).bootstrapTable('getData').length,
                        "taskUnitPrice": $("#taskUnitPrice", args).val(),
                        "unitTotal": $("#unitTotal", args).val(),
                        "upperLimit": $("#upperLimit", args).val(),
                        "taskUnitNum": $("#taskUnitNum", args).val(),
                        "collegeName": selData[i].text,
                        "collegeId": selData[i].id,
                        "effectiveStartDate": $("#effectiveStartDate", args).val() + " 00:00:00",
                        "effectiveStartDate": $("#effectiveStartDate", args).val() + " 00:00:00",
                        "effectiveEndDate": $("#effectiveEndDate", args).val() + " 23:59:59",
                        "deadline": $("#deadlineTime", args).val() + " 23:59:59"
                    });
                }

                $('#addModal', args).modal('hide');
                $("#addForm", args).data("bootstrapValidator").resetForm(true);
                $("#deadlineTime", args).val("");
                $("#effectiveStartDate", args).val("");
                $("#effectiveEndDate", args).val("");
                // $('#add_College').select2("val", null);
                $("#add_College", args).val("").trigger("change");
            },
            subRemove: function (args) {
                var arrselections = $("#orderSubTable", args).bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                var id = arrselections[0].ID;
                $('#orderSubTable', args).bootstrapTable('removeByUniqueId', id)
            },
            create: function (isPublished, args) {


                $("#taskSave", args).attr("disabled", "disabled");//设置button不可用
                $("#taskPub", args).attr("disabled", "disabled");
                var t = setTimeout(show(args), 3000);

                if ($("#orderSubTable", args).bootstrapTable('getData').length == 0 && isPublished == 1) {
                    base.error("发布必须要有子任务");
                    return;
                }
                var postDate = {
                    "theme": $("#theme", args).val(),
                    "description": $("#description", args).val(),
                    "rule": $("#rule", args).val(),
                    "claim": $("#claim", args).val(),
                    "taskLevel": $("#taskLevel", args).val(),
                    "isImg": $("#addYEnabled", args).prop('checked') ? "1" : "0",
                    "isRemark": $("#addYIsRemark", args).prop('checked') ? "1" : "0",
                    "tbTaskSubs": $("#orderSubTable", args).bootstrapTable('getData'),
                    "isPublished": isPublished,
                    "files": window.files,
                    "taskCover": window.cover
                };
                postDate = JSON.stringify(postDate, null, 2);
                $.ajax({
                    type: "POST",
                    url: "/order/taskorder/add",
                    data: postDate,
                    contentType: "application/json",
                    success: function (data) {
                        if (data.success == 0) {
                            base.success("增加成功！")
                            base.colseTab("新增任务", "任务管理", refParentPage);

                            //window.location.href = "/order/gotoTaskOrderManager";
                        } else {
                            base.error(data.message);
                        }
                    }
                });
            },
            editInit: function (taskId, args, row) {
                var self = this;
                args = args || $('#indextab').tabs('getSelected');
                taskId = taskId || $("#taskId", args).val();

                $('#editModal', args).modal({
                    keyboard: false,
                    backdrop: 'static'
                });


                //如果状态已改变不能修改
                if (row.state != 0) {
                    $(".btn-primary", args).hide();
                } else {
                    $(".btn-primary", args).show();
                }
                $(".btn-primary", args).click(function () {
                    $(".btn-primary", args).unbind("click");
                    self.subEditUpdate(args, row);
                });
                $("#effectiveStartDate", args).val(row.effectiveStartDate.substring(0, 10));
                $("#effectiveEndDate", args).val(row.effectiveEndDate.substring(0, 10));
                $("#deadlineTime", args).val(row.deadline.substring(0, 10));
                $("#taskUnitPrice", args).val(row.taskUnitPrice / 100);
                $("#unitTotal", args).val(row.unitTotal);
                $("#upperLimit", args).val(row.upperLimit);
                $("#taskUnitNum", args).val(row.taskUnitNum);
                $("#add_College", args).val(row.collegeId).trigger("change");

                $("#taskUnitPrice", args).change(function () {
                    $("#moneyTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal", args).val() || 0)))
                })
                $("#unitTotal", args).change(function () {
                    $("#moneyTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitPrice", args).val() || 0)))
                    $("#numberTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitNum", args).val() || 0)))
                })
                $("#taskUnitNum", args).change(function () {
                    $("#numberTotal", args).text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal", args).val() || 0)))
                })


                //开始时间
                $('#editstarttimePicker', args).datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                })

                //结束时间
                $('#editendtimePicker', args).datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });
                //任务完成时间
                $('#deadlinePicker', args).datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });

                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/order/taskorder/getdto?taskId=" + row.taskId,
                    success: function (data) {
                        $("#detailtheme", args).val(data.theme);
                        $("#claim", args).val(data.claim);
                        $("#rule", args).val(data.rule);
                        $("#description", args).val(data.description);
                        $("#edittaskLevel", args).val(data.taskLevel);
                        $("#edittaskLevel", args).change();
                        $("#editIsImg", args).val(data.isImg == 1 ? "是" : "否");
                        $("#editIsRemark", args).val(data.isRemark == 1 ? "是" : "否");
                        //触发下绑定的事件
                        $("#unitTotal", args).change();
                        $("#img_big").empty();
                        $("#img_small").empty();
                        if (data.attachmentInfos != null && data.attachmentInfos.length > 0) {


                            for (var i = 0; i < data.attachmentInfos.length; i++) {
                                if (data.attachmentInfos[i].attachmentId == data.taskCover) {

                                    (function () {
                                        var url = data.attachmentInfos[i].nailFileUrl;
                                        $("#labelimg1", args).unbind("click");
                                        $("#labelimg1", args).click(function () {
                                            self.dialogImg("封面", url);
                                        });
                                    })();
                                } else {
                                    $("#img_big").append('<li><a href="javascript:;"><img src="' + data.attachmentInfos[i].nailFileUrl + '" width="500" height="500" alt=""/></a></li>');
                                    $("#img_small").append('<li><a href="javascript:;"><img src="' + data.attachmentInfos[i].nailFileUrl + '" width="80" height="80" alt=""/></a></li>');
                                }
                            }
                        }
                        //初始化图片轮播插件
                        $('#demo1').banqh({
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
                });
                $('#pictureModal', args).on('hidden.bs.modal', function () {
                    $("body").attr("class", "theme-blue-gradient modal-open");
                });

                $("#labelimg2", args).click(function () {
                    $("#pictureModal", args).modal("show");
                });
            },
            edit: function (args) {
                var self = this;
                var arrselections = $("#areaTable", args).bootstrapTable('getSelections');
                if (arrselections.length > 1) {
                    sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                    return;
                }
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                self.editInit(arrselections[0].taskSubId, args, arrselections[0]);
            },
            editSub: function (args) {
                var self = this;
                $('#addModal', args).modal({
                    keyboard: false,
                    backdrop: 'static'
                });
                base.validator(
                    {
                        message: 'This value is not valid',
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            taskUnitPrice: {
                                validators: {
                                    notEmpty: {
                                        message: '任务每单赏金不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            },
                            unitTotal: {
                                validators: {
                                    notEmpty: {
                                        message: '任务单子数量不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            },
                            upperLimit: {
                                validators: {
                                    notEmpty: {
                                        message: '每人接单上限不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            }, taskUnitNum: {
                                validators: {
                                    notEmpty: {
                                        message: '每单多少件上限不能为空'
                                    },
                                    regexp: {
                                        regexp: /^[0-9]*$/,
                                        message: '必须为数字'
                                    }
                                }
                            },
                            add_Collage: {
                                validators: {
                                    notEmpty: {
                                        message: '学校不能为空'
                                    }
                                }
                            }
                        }
                    }, "#addForm", self.subEditCreate, args)
            },
            subEditCreate: function (args) {
                if ($("#deadlineTime", args).val() == "") {
                    base.error("任务完成时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ($("#effectiveStartDate", args).val() == "") {
                    base.error("活动开始时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ($("#effectiveEndDate", args).val() == "") {
                    base.error("活动结束时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveStartDate", args).val())) > (new Date($("#effectiveEndDate", args).val()))) {
                    base.error("活动开始时间要小于结束时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate", args).val())) > (new Date($("#deadlineTime", args).val()))) {
                    base.error("活动结束时间要小于任务完成时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#deadlineTime", args).val())) < (new Date())) {
                    base.error("任务完成时间要大于当前时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate", args).val())) < (new Date())) {
                    base.error("活动结束时间要大于当前时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                var postDate = {
                    "taskId": $("#taskId", args).val(),
                    "taskUnitPrice": $("#taskUnitPrice", args).val(),
                    "unitTotal": $("#unitTotal", args).val(),
                    "upperLimit": $("#upperLimit", args).val(),
                    "taskUnitNum": $("#taskUnitNum", args).val(),
                    "collegeName": $("#add_College", args).select2("data")[0].text,
                    "collegeId": $("#add_College", args).select2("data")[0].id,
                    "effectiveStartDate": $("#effectiveStartDate", args).val() + " 00:00:00",
                    "effectiveEndDate": $("#effectiveEndDate", args).val() + " 23:59:59",
                    "deadline": $("#deadlineTime", args).val() + " 23:59:59"
                };
                postDate = JSON.stringify(postDate, null, 2);
                $.ajax({
                    type: "POST",
                    url: "/order/taskorder/addSub",
                    data: postDate,
                    contentType: "application/json",
                    success: function (data) {
                        if (data.success == 0) {
                            $("#orderSubTable", args).bootstrapTable('refresh');
                            $('#addModal', args).modal('hide');
                            $("#addForm", args).data("bootstrapValidator").resetForm(true);
                            $("#deadlineTime", args).val("");
                            $("#effectiveStartDate", args).val("");
                            $("#effectiveEndDate", args).val("");
                            base.success("添加成功！")

                        } else {
                            base.error(data.message);
                            $(".btn-primary", args).removeAttr("disabled");
                        }
                    }
                });

            },
            subEditUpdate: function (args, row) {
                if ($("#deadlineTime", args).val() == "") {
                    base.error("任务完成时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ($("#effectiveStartDate", args).val() == "") {
                    base.error("活动开始时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ($("#effectiveEndDate", args).val() == "") {
                    base.error("活动结束时间必填!");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveStartDate", args).val())) > (new Date($("#effectiveEndDate", args).val()))) {
                    base.error("活动开始时间要小于结束时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate", args).val())) > (new Date($("#deadlineTime", args).val()))) {
                    base.error("活动结束时间要小于任务完成时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#deadlineTime", args).val())) < (new Date())) {
                    base.error("任务完成时间要大于当前时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate", args).val())) < (new Date())) {
                    base.error("活动结束时间要大于当前时间");
                    $(".btn-primary", args).removeAttr("disabled");
                    return;
                }
                var postDate = {
                    "taskSubId": row.taskSubId,
                    "taskUnitPrice": $("#taskUnitPrice", args).val(),
                    "unitTotal": $("#unitTotal", args).val(),
                    "upperLimit": $("#upperLimit", args).val(),
                    "taskUnitNum": $("#taskUnitNum", args).val(),
                    "collegeName": $("#add_College", args).select2("data")[0].text,
                    "collegeId": $("#add_College", args).select2("data")[0].id,
                    "effectiveStartDate": $("#effectiveStartDate", args).val() + " 00:00:00",
                    "effectiveEndDate": $("#effectiveEndDate", args).val() + " 23:59:59",
                    "deadline": $("#deadlineTime", args).val() + " 23:59:59"
                };
                postDate = JSON.stringify(postDate, null, 2);
                $.ajax({
                    type: "POST",
                    url: "/order/taskorder/updateSub",
                    data: postDate,
                    contentType: "application/json",
                    success: function (data) {
                        if (data.success == 0) {
                            $("#areaTable", args).bootstrapTable('refresh');
                            base.success("修改成功！")
                            $('#editModal', args).modal('hide');

                        } else {
                            base.error(data.message);
                            $(".btn-primary", args).removeAttr("disabled");
                        }
                    }
                });

            },
            subEditRemove: function (args) {
                var arrselections = $("#orderSubTable", args).bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                base.cancel({title: "删除子任务", text: "您确定要删除任务？"}, function () {
                    $.post("/order/taskorder/delSub", {"taskSubId": arrselections[0].taskSubId}
                        , function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    $("#orderSubTable", args).bootstrapTable('refresh');
                                    base.success("删除成功！")
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("删除失败");
                            }
                        });
                });
            },
            subEditPub: function (index, args) {
                var arrselections = $("#areaTable", args).bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                base.cancel({title: "发布子任务", text: "您确定要发布任务？"}, function () {
                    $.post("/order/taskorder/pubSub", {"taskSubId": arrselections[0].taskSubId}
                        , function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    $("#areaTable", args).bootstrapTable('refresh');
                                    base.success("发布成功！");
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("发布失败");
                            }
                        });
                });
            },
            update: function (isPublished, args) {
                args = args || $('#indextab').tabs('getSelected');
                var postDate = {
                    "taskId": $("#taskId", args).val(),
                    "theme": $("#theme", args).val(),
                    "description": $("#description", args).val(),
                    "rule": $("#rule", args).val(),
                    "claim": $("#claim", args).val(),
                    "taskLevel": $("#taskLevel", args).val(),
                    "isImg": $("#addYEnabled", args).prop('checked') ? "1" : "0",
                    "isRemark": $("#editYIsRemark", args).prop('checked') ? "1" : "0",
                    "isPublished": isPublished,
                    "files": window.files,
                    "taskCover": window.cover,
                    "removeFiles": window.removefiles
                };

                postDate = JSON.stringify(postDate, null, 2);
                $.ajax({
                    type: "POST",
                    url: "/order/taskorder/update",
                    data: postDate,
                    contentType: "application/json",
                    success: function (data) {
                        if (data.success == 0) {
                            $("#doOrderTaskTable", args).bootstrapTable('refresh');
                            if (isPublished == 0) {
                                base.success("修改成功！")
                            } else {
                                base.success("发布成功！")
                            }
                            base.colseTab("编辑任务", "任务管理", refParentPage);//参数：需关闭的标签，需打开的标签，新打开标签后执行的操作
                            //window.location.href = "/order/gotoTaskOrderManager";
                        } else {
                            base.error(data.message);
                        }
                    }
                });
            },
            remove: function (args) {
                var arrselections = $("#areaTable", args).bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }

                base.cancel({title: "删除任务", text: "您确定要删除任务？"}, function () {
                    $.post("/order/taskorder/delSub", {"taskSubId": arrselections[0].taskSubId}
                        , function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    $("#areaTable", args).bootstrapTable('refresh');
                                    base.success("删除成功！")
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("删除失败");
                            }
                        });
                });
            },
            stop: function (args) {
                var arrselections = $("#areaTable", args).bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }

                base.cancel({title: "停止任务", text: "您确定要停止任务？"}, function () {
                    $.post("/order/taskorder/stop", {"taskSubId": arrselections[0].taskSubId}
                        , function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    $("#areaTable", args).bootstrapTable('refresh');
                                    base.success("停止成功！")
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("删除失败");
                            }
                        });
                });
            },
            dialogImg: function (title, url) {
                BootstrapDialog.show({
                    cssClass: 'img-dialog',
                    title: title,
                    onhidden: function (dialogRef) {
                        $("body").attr("class", "theme-blue-gradient modal-open");
                    },
                    message: '<div style="text-align: center;"><img src="' + url + '" style="width: 670px;height: 500px;"></div>'
                });
            }
        };
    });