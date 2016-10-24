define(['base'], function (base) {
    var datatable;
    var status;
    var beVirtual;
    var storeId;


    function sumFormatter(data) {
        field = this.field;
        var total_sum = data.reduce(function (sum, row) {
            return (sum) + (row[field] || 0);
        }, 0);
        return total_sum;
    }

    function calcTableHight(control, search) {
        //表格控件不支持高度自适应
        var tableHeight = control.find("thead").height() + control.find("tbody").height() + 3 + control.parent().parent().parent().parent().find(".clearfix").height();
        var dataCount = control.bootstrapTable('getData').length;
        if (dataCount == 0) {//如果没有数据 给固定文字的高度
            tableHeight = 105;
        }
        if (search) {//如果有自带的功能 把自带功能的元素高度加上
            tableHeight += (control.parent().parent().parent().parent().find(".pull-right").height() + 20);
        }
        return tableHeight;
    }

    return {
        init: function (args) {

            var self = this;

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
            $("#startdate").val((new Date((new Date()).getTime() - 7 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
            $("#enddate").val((new Date()).Format("yyyy-MM-dd"));


            self.initData();


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
                    $('#selcollage').select2("val", null);
                }
            });

            $("#btn_query").click(function () {
                self.loadData();
                $('#myTabHome').tab('show');
            });
        },
        initData: function () {
            var self = this;
            $.post("/report/thirdparty/getReport", {
                collegeIds: $('#storeid').val(),
                startDate: $('#startdate').val(),
                endDate: $('#enddate').val()
            }, function (data) {
                var obj = JSON.parse(data);
                $("#totalNum").html(obj.totalNum);
                $("#totalUnregNum").html(obj.totalUnregNum);
                $("#rate").html(obj.rate);
                $("#totalRegNum").html(obj.totalRegNum);

                datatable = base.datagrid({
                    sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                    data: obj.reportList,
                    onDblClickCell: function (field, value, row, $element) {
                        switch (field) {
                            case "totalPhoneNum":
                                self.loadAllPhone(row.collegeId, row.reportDate);
                                break;
                            case "totalUnregNum":
                                self.loadUnRe(row.collegeId, row.reportDate);
                                break;
                            case "totalRegNum":
                                self.loadAllRe(row.collegeId, row.reportDate);
                                break;
                            case "newNum":
                                self.loadNewPhone(row.collegeId, row.reportDate);
                                break;
                        }

                    },
                    columns: [{
                        field: 'reportDate',
                        title: '日期',
                        sortable: true,
                        footerFormatter: function () {
                            return '合计';
                        }
                    }, {
                            field: 'collegeName',
                            title: '学校',
                            sortable: true
                        }, {
                            field: 'areaName',
                            title: '所属区域',
                            sortable: true
                        }, {
                            field: 'businessPrincipal',
                            title: '商户负责人',
                            sortable: true
                        }, {
                            field: 'totalPhoneNum',
                            title: '手机号总数',
                            sortable: true
                        },
                        {
                            field: 'totalUnregNum',
                            title: '未注册号码',
                            sortable: true
                        },
                        {
                            field: 'totalRegNum',
                            title: '注册总数',
                            sortable: true
                        },
                        {
                            field: 'regRate',
                            title: '注册率%',
                            sortable: true
                        },
                        {
                            field: 'newNum',
                            title: '新增号码数',
                            sortable: true
                        },
                        {
                            field: 'collegeId',
                            title: 'collegeId',
                            sortable: true,
                            visible: false
                        }]
                }, '#taotalContent');
                base.datagrid({
                    sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                    data: [],
                    toolbar: "",
                    exportOptions: {
                        fileName: (new Date())
                            .Format("yyyy-MM-dd HH:mm:ss")
                        + $('h1').text() + "号码总数"
                    },
                    columns: [{
                        field: 'collegeName',
                        title: '学校/校区',
                        sortable: true
                    }, {
                        field: 'phone',
                        title: '手机号码',
                        sortable: true
                    }]
                }, "#detailAllPhone");
                base.datagrid({
                    sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                    data: [],
                    toolbar: "",
                    exportOptions: {
                        fileName: (new Date())
                            .Format("yyyy-MM-dd HH:mm:ss")
                        + $('h1').text() + "号码总数"
                    },
                    columns: [{
                        field: 'collegeName',
                        title: '学校/校区',
                        sortable: true
                    }, {
                        field: 'phone',
                        title: '手机号码',
                        sortable: true
                    }]
                }, "#detailAllRe");
                base.datagrid({
                    sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                    toolbar: "",
                    data: [],
                    exportOptions: {
                        fileName: (new Date())
                            .Format("yyyy-MM-dd HH:mm:ss")
                        + $('h1').text() + "号码总数"
                    },
                    columns: [{
                        field: 'collegeName',
                        title: '学校/校区',
                        sortable: true
                    }, {
                        field: 'phone',
                        title: '手机号码',
                        sortable: true
                    }]
                }, "#detailUnRe");
                base.datagrid({
                    sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                    toolbar: "",
                    data: [],
                    exportOptions: {
                        fileName: (new Date())
                            .Format("yyyy-MM-dd HH:mm:ss")
                        + $('h1').text() + "号码总数"
                    },
                    columns: [{
                        field: 'collegeName',
                        title: '学校/校区',
                        sortable: true
                    }, {
                        field: 'phone',
                        title: '手机号码',
                        sortable: true
                    }]
                }, "#detailNewPhone");
            })
        },
        loadData: function () {
            $.post("/report/thirdparty/getReport", {
                collegeIds: $('#storeid').val(),
                startDate: $('#startdate').val(),
                endDate: $('#enddate').val(),
            }, function (data) {
                var obj = JSON.parse(data);
                $("#totalNum").html(obj.totalNum);
                $("#totalUnregNum").html(obj.totalUnregNum);
                $("#rate").html(obj.rate);
                $("#totalRegNum").html(obj.totalRegNum);
                // datatable.load(obj.reportList);
                $('#taotalContent').bootstrapTable('load', obj.reportList);
            })
        },
        loadAllPhone: function (collegeId, reportDate) {
            $.post("/report/thirdparty/getAllNum", {
                collegeId: collegeId,
                reportDate: reportDate
            }, function (data) {
                var obj = JSON.parse(data);
                $('#detailAllPhone').bootstrapTable('load', obj);
                $('#myTabAllPhone').tab('show');
            });
        },
        loadAllRe: function (collegeId, reportDate) {
            $.post("/report/thirdparty/getRegNum", {
                collegeId: collegeId,
                reportDate: reportDate
            }, function (data) {
                var obj = JSON.parse(data);
                $('#detailAllRe').bootstrapTable('load', obj);
                $('#myTabAllRe').tab('show');
            });
        },
        loadUnRe: function (collegeId, reportDate) {
            $.post("/report/thirdparty/getUnRegNum", {
                collegeId: collegeId,
                reportDate: reportDate
            }, function (data) {
                var obj = JSON.parse(data);
                $('#detailUnRe').bootstrapTable('load', obj);
                $('#myTabUnRe').tab('show');
            });
        },
        loadNewPhone: function (collegeId, reportDate) {
            $.post("/report/thirdparty/getNewNum", {
                collegeId: collegeId,
                reportDate: reportDate
            }, function (data) {
                var obj = JSON.parse(data);
                $('#detailNewPhone').bootstrapTable('load', obj);
                $('#myTabNewPhone').tab('show');
            });
        }
    };
});