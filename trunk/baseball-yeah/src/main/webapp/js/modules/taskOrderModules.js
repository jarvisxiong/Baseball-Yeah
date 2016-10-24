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
        function show() {
            $("#taskSave").removeAttr("disabled");
            $("#taskPub").removeAttr("disabled");
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
                                    startDate: $('#submit_startdate').val(),
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
                                formatter: function (value) {
                                    switch (value) {
                                        case "0":
                                            return "未发布";
                                        case "1" :
                                            return "已发布";
                                        case "2":
                                            return "已生成";
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
                                title: '完成数量'
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
                    }, '#areaTable');

                $.ajax({
                    type: "POST",
                    url: "/manage/dept/selectalldept",
                    dataType: "json",
                    success: function (data) {
                        $("#dutyId").select2({
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
                    }
                });

                //开始时间
                $('#starttimePicker').datetimepicker({
                    format: 'yyyy-mm-dd hh:ii',
                    autoclose: true,
                    pickTime: false,
                    startView: 2,
                    minView: 0,
                    todayHighlight: true
                })

                //结束时间
                $('#endtimePicker').datetimepicker(
                    {
                        format: 'yyyy-mm-dd hh:ii',
                        autoclose: true,
                        pickTime: false,
                        startView: 2,
                        minView: 0,
                        todayHighlight: true
                    });

                $("#btn_add").click(function () {
                    self.add();
                });
                $("#btn_edit").click(function () {
                    self.edit();
                });
                $("#btn_delete").click(function () {
                    self.remove();
                });
                $("#btn_query").click(function () {
                    $("#areaTable").bootstrapTable('refresh', {"query": {"offset": 0}});
                });

                $("#clearSearch").click(function () {
                    base.reset(".main-box-body");
                    $("#provinceIds").val("").trigger("change");
                });

            },
            add: function () {
                var self = this;
                window.files = [];

                window.cover = "";
                $("#btn_add").click(function () {
                    self.addSub();
                });
                $("#btn_delete").click(function () {
                    self.subRemove();
                });
                $("#taskSave").click(function () {
                    self.create(0);
                });
                $("#taskPub").click(function () {
                    self.create(1);
                });
                $("#taskLevel").change(function () {
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

                $("#taskUnitPrice").change(function () {
                    $("#moneyTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal").val() || 0)))
                })
                $("#unitTotal").change(function () {
                    $("#moneyTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitPrice").val() || 0)))
                    $("#numberTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitNum").val() || 0)))
                })

                $("#taskUnitNum").change(function () {
                    $("#numberTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal").val() || 0)))
                })
                $('#addModal').on('shown.bs.modal', function () {
                    $('#addForm').data('bootstrapValidator').resetForm(true);
                    $("#moneyTotal").text("");
                    $("#numberTotal").text("");
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
                        }
                    }
                })

                var data = [];

                $('#orderSubTable').bootstrapTable({
                    data: data,
                    toolbar: "#toolbar",
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
                $('#starttimePicker').datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                })

                //结束时间
                $('#endtimePicker').datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });
                //结束时间
                $('#deadlinePicker').datetimepicker(
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
                        $("#add_College").select2({
                            data: data.data
                        });
                    }
                })
            },
            addSub: function () {
                var self = this;
                $('#addModal').modal({
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
                    }, "#addForm", self.subCreate)
            },
            subCreate: function () {

                if ($("#deadlineTime").val() == "") {
                    base.error("任务完成时间必填!");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ($("#effectiveStartDate").val() == "") {
                    base.error("活动开始时间必填!");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ($("#effectiveEndDate").val() == "") {
                    base.error("活动结束时间必填!");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveStartDate").val())) > (new Date($("#effectiveEndDate").val()))) {
                    base.error("活动开始时间要小于结束时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate").val())) > (new Date($("#deadlineTime").val()))) {
                    base.error("活动结束时间要小于任务完成时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#deadlineTime").val())) < (new Date())) {
                    base.error("任务完成时间要大于当前时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate").val())) < (new Date())) {
                    base.error("活动结束时间要大于当前时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                for (var i = 0; i < $("#add_College").select2("data").length; i++) {
                    $('#orderSubTable').bootstrapTable('append', {
                        "ID": $('#orderSubTable').bootstrapTable('getData').length,
                        "taskUnitPrice": $("#taskUnitPrice").val(),
                        "unitTotal": $("#unitTotal").val(),
                        "upperLimit": $("#upperLimit").val(),
                        "taskUnitNum": $("#taskUnitNum").val(),
                        "collegeName": $("#add_College").select2("data")[i].text,
                        "collegeId": $("#add_College").select2("data")[i].id,
                        "effectiveStartDate": $("#effectiveStartDate").val() + " 00:00:00",
                        "effectiveStartDate": $("#effectiveStartDate").val() + " 00:00:00",
                        "effectiveEndDate": $("#effectiveEndDate").val() + " 23:59:59",
                        "deadline": $("#deadlineTime").val() + " 23:59:59"
                    });
                }
                $('#addModal').modal('hide');
                $("#addForm").data("bootstrapValidator").resetForm(true);

                $("#deadlineTime").val("");
                $("#effectiveStartDate").val("");
                $("#effectiveEndDate").val("");
                $('#add_College').select2("val", null);

            },
            subRemove: function () {
                var arrselections = $("#orderSubTable").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                var id = arrselections[0].ID;
                $('#orderSubTable').bootstrapTable('removeByUniqueId', id)
            },
            create: function (isPublished) {


                $("#taskSave").attr("disabled", "disabled");//设置button不可用
                $("#taskPub").attr("disabled", "disabled");
                var t = setTimeout(show(), 3000);

                if ($("#orderSubTable").bootstrapTable('getData').length == 0 && isPublished == 1) {
                    base.error("发布必须要有子任务");
                    return;
                }
                var postDate = {
                    "theme": $("#theme").val(),
                    "description": $("#description").val(),
                    "rule": $("#rule").val(),
                    "claim": $("#claim").val(),
                    "taskLevel": $("#taskLevel").val(),
                    "isImg": $("#addYEnabled").prop('checked') ? "1" : "0",
                    "isRemark": $("#addYIsRemark").prop('checked') ? "1" : "0",
                    "tbTaskSubs": $("#orderSubTable").bootstrapTable('getData'),
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
                            window.location.href = "/order/gotoTaskOrderManager";
                        } else {
                            base.error(data.message);
                        }
                    }
                });
            },
            editInit: function (taskId) {
                var self = this;
                window.files = [];
                window.removefiles = [];
                window.cover = "";
                window.myDropzone = new Dropzone("#myId", {
                    init: function () {
                        //添加监听 下面success是重写
                        this.on("removedfile", function (file) {
                            window.removefiles.push(file.fileid);
                        });
                    },
                    url: "/order/taskorder/uploadFile",
                    dictDefaultMessage: "将文件拖至此处或点击上传",
                    addRemoveLinks: true,
                    maxFiles: 10,
                    acceptedFiles: ".png,.jpg,.bmp",
                    dictRemoveFile: "删除",
                    success: function (data, resultData) {
                        if (resultData.success > -1) {
                            data.fileid = resultData.data.id;//回调的附件id给到file
                            window.files.push(resultData.data.id);
                        }
                    }
                });
                window.myCover = new Dropzone("#cover", {
                    init: function () {
                        this.on("removedfile", function (file) {
                            window.files.remove(file.fileid);
                            window.removefiles.push(file.fileid);
                            myCover.options.maxFiles = 1;
                        });
                    },
                    url: "/order/taskorder/uploadFile",
                    dictDefaultMessage: "将文件拖至此处或点击上传",
                    acceptedFiles: ".png,.jpg,.bmp",
                    addRemoveLinks: true,
                    dictRemoveFile: "删除",
                    maxFiles: 1,
                    success: function (data, resultData) {
                        if (resultData.success > -1) {
                            data.fileid = resultData.data.id;
                            window.files.push(resultData.data.id);
                            window.cover = resultData.data.id;
                        }
                    }
                })
                $("#taskLevel").change(function () {
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
                $("#taskUnitPrice").change(function () {
                    $("#moneyTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal").val() || 0)))
                })
                $("#unitTotal").change(function () {
                    $("#moneyTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitPrice").val() || 0)))
                    $("#numberTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#taskUnitNum").val() || 0)))
                })

                $("#taskUnitNum").change(function () {
                    $("#numberTotal").text(new Number(($(this).val() || 0)) * new Number(( $("#unitTotal").val() || 0)))
                })


                $("#btn_add").click(function () {
                    self.editSub();
                });
                $("#btn_delete").click(function () {
                    self.subEditRemove();
                });
                $("#btn_pub").click(function () {
                    self.subEditPub(1);
                    // $("#btn_add").hide();
                });
                $("#taskSave").click(function () {
                    self.update(0);
                });
                $("#taskPub").click(function () {
                    self.update(1);
                });
                $('#addModal').on('shown.bs.modal', function () {
                    $('#addForm').data('bootstrapValidator').resetForm(true);
                    $("#moneyTotal").text("");
                    $("#numberTotal").text("");
                })

                //开始时间
                $('#starttimePicker').datetimepicker({
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                })

                //结束时间
                $('#endtimePicker').datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });
                //结束时间
                $('#deadlinePicker').datetimepicker(
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
                        $("#add_College").select2({
                            data: data.data
                        });
                    }
                })
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "/order/taskorder/getdto?taskId=" + taskId,
                    success: function (data) {
                        $("#theme").val(data.theme);
                        $("#claim").val(data.claim);

                        $("#rule").val(data.rule);
                        $("#description").val(data.description);
                        $("#taskLevel").val(data.taskLevel);
                        $("#taskLevel").change();
                        $("#addYEnabled").prop("checked", data.isImg == 1 ? true : false);
                        $("#addNEnabled").prop("checked", data.isImg == 1 ? false : true);
                        $("#editYIsRemark").prop("checked", data.isRemark == 1 ? true : false);
                        $("#editNIsRemark").prop("checked", data.isRemark == 1 ? false : true);
                        window.cover = data.taskCover;

                        for (var i = 0; i < data.attachmentInfos.length; i++) {
                            // Create the mock file:
                            var mockFile = {
                                name: data.attachmentInfos[i].originFileName,
                                size: data.attachmentInfos[i].size,
                                fileid: data.attachmentInfos[i].attachmentId
                            };
                            if (data.attachmentInfos[i].attachmentId == data.taskCover) {
                                // Call the default addedfile event handler
                                myCover.emit("addedfile", mockFile);
                                // And optionally show the thumbnail of the file:
                                myCover.emit("thumbnail", mockFile, data.attachmentInfos[i].nailFileUrl);
                                // Make sure that there is no progress bar, etc...
                                myCover.emit("complete", mockFile);
                                myCover.options.maxFiles = myCover.options.maxFiles - 1;
                            } else {
                                // Call the default addedfile event handler
                                myDropzone.emit("addedfile", mockFile);
                                // And optionally show the thumbnail of the file:
                                myDropzone.emit("thumbnail", mockFile, data.attachmentInfos[i].nailFileUrl);
                                // Make sure that there is no progress bar, etc...
                                myDropzone.emit("complete", mockFile);
                            }


                            // window.myCover
                        }

                        // If you use the maxFiles option, make sure you adjust it to the
                        var existingFileCount = data.attachmentInfos.length; // The number of files already uploaded
                        myDropzone.options.maxFiles = myDropzone.options.maxFiles - existingFileCount;


                        if (data.isPublished == 1) {
                            $("#taskSave").hide();
                            $("#taskPub").hide();
                            $("#btn_add").hide();
                        }

                        $('#orderSubTable').bootstrapTable({
                            url: '/order/taskorder/postSubList',
                            singleSelect: true,
                            queryParams: function (params) {
                                return $.extend(params,
                                    {
                                        taskId: taskId
                                    });
                            },
                            toolbar: "#toolbar",
                            height: 300,
                            uniqueId: "ID",
                            columns: [
                                {
                                    checkbox: true
                                },
                                {
                                    field: 'taskSubId',
                                    title: 'taskSubId',
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
                    }
                });
            },
            edit: function () {
                var self = this;
                var arrselections = $("#areaTable").bootstrapTable('getSelections');
                if (arrselections.length > 1) {
                    sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                    return;
                }
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                window.location.href = "/order/gotoEditTask?taskId=" + arrselections[0].taskId;
            },
            editSub: function () {
                var self = this;
                $('#addModal').modal({
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
                    }, "#addForm", self.subEditCreate)
            },
            subEditCreate: function () {
                if ($("#deadlineTime").val() == "") {
                    base.error("任务完成时间必填!");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ($("#effectiveStartDate").val() == "") {
                    base.error("活动开始时间必填!");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ($("#effectiveEndDate").val() == "") {
                    base.error("活动结束时间必填!");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveStartDate").val())) > (new Date($("#effectiveEndDate").val()))) {
                    base.error("活动开始时间要小于结束时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate").val())) > (new Date($("#deadlineTime").val()))) {
                    base.error("活动结束时间要小于任务完成时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#deadlineTime").val())) < (new Date())) {
                    base.error("任务完成时间要大于当前时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                if ((new Date($("#effectiveEndDate").val())) < (new Date())) {
                    base.error("活动结束时间要大于当前时间");
                    $(".btn-primary").removeAttr("disabled");
                    return;
                }
                var postDate = {
                    "taskId": $("#taskId").val(),
                    "taskUnitPrice": $("#taskUnitPrice").val(),
                    "unitTotal": $("#unitTotal").val(),
                    "upperLimit": $("#upperLimit").val(),
                    "taskUnitNum": $("#taskUnitNum").val(),
                    "collegeName": $("#add_College").select2("data")[0].text,
                    "collegeId": $("#add_College").select2("data")[0].id,
                    "effectiveStartDate": $("#effectiveStartDate").val() + " 00:00:00",
                    "effectiveEndDate": $("#effectiveEndDate").val() + " 23:59:59",
                    "deadline": $("#deadlineTime").val() + " 23:59:59"
                };
                postDate = JSON.stringify(postDate, null, 2);
                $.ajax({
                    type: "POST",
                    url: "/order/taskorder/addSub",
                    data: postDate,
                    contentType: "application/json",
                    success: function (data) {
                        if (data.success == 0) {
                            $("#orderSubTable").bootstrapTable('refresh');
                            $('#addModal').modal('hide');
                            $("#addForm").data("bootstrapValidator").resetForm(true);
                            $("#deadlineTime").val("");
                            $("#effectiveStartDate").val("");
                            $("#effectiveEndDate").val("");
                            base.success("添加成功！")

                        } else {
                            base.error(data.message);
                            $(".btn-primary").removeAttr("disabled");
                        }
                    }
                });

            },
            subEditRemove: function () {
                var arrselections = $("#orderSubTable").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                base.cancel({title: "删除子任务", text: "您确定要删除任务？"}, function () {
                    $.post("/order/taskorder/delSub", {"taskSubId": arrselections[0].taskSubId}
                        , function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    $("#orderSubTable").bootstrapTable('refresh');
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
            subEditPub: function () {
                var arrselections = $("#orderSubTable").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                base.cancel({title: "发布子任务", text: "您确定要发布任务？"}, function () {
                    $.post("/order/taskorder/pubSub", {"taskSubId": arrselections[0].taskSubId}
                        , function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                    $("#orderSubTable").bootstrapTable('refresh');
                                    base.success("发布成功！")
                                    $("#btn_add").hide();
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("发布失败");
                            }
                        });
                });
            },
            update: function (isPublished) {
                var postDate = {
                    "taskId": $("#taskId").val(),
                    "theme": $("#theme").val(),
                    "description": $("#description").val(),
                    "rule": $("#rule").val(),
                    "claim": $("#claim").val(),
                    "taskLevel": $("#taskLevel").val(),
                    "isImg": $("#addYEnabled").prop('checked') ? "1" : "0",
                    "isRemark": $("#editYIsRemark").prop('checked') ? "1" : "0",
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
                            window.location.href = "/order/gotoTaskOrderManager";
                        } else {
                            base.error(data.message);
                        }
                    }
                });
            },
            remove: function () {
                var arrselections = $("#areaTable").bootstrapTable(
                    'getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                var taskId = arrselections[0].taskId;

                base.cancel({
                        title: "删除任务",
                        text: "您确定要删除此任务吗？"
                    },
                    function () {
                        $.post("/order/taskorder/del",
                            {
                                "taskId": taskId
                            },
                            function (data, status) {
                                if (status == "success") {
                                    if (data.success == 0) {
                                        base.success("删除成功");
                                        $("#areaTable").bootstrapTable('refresh');
                                    } else {
                                        base.error(data.message);
                                    }
                                } else {
                                    base.error("删除失败");
                                }
                            });
                    });
            }
        };
    });