define(['base'], function (base) {
    var datatable;

    function sumFormatter(data) {
        field = this.field;
        var total_sum = data.reduce(function (sum, row) {
            return (sum) + (row[field] || 0);
        }, 0);
        return total_sum;
    }

    function totalTextFormatter(data) {
        return '合计';
    }

    return {
        init: function (panel) {
            var self = this;
            datatable = base.datagrid({
                url: '/report/storesms/dayReport',
                method: 'get',
                sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
                pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                search: false,
                showRefresh: false,
                showFooter: true,
                showExport: true,//显示导出按钮
                exportDataType: "all",//导出类型
                height: 800,
                queryParams: function (params) {
                    return $.extend(params, {
                        storename: $('#storeName', panel).val().trim(),
                        storeid: $('#storeid', panel).val(),
                        supervisor: $('#supervisor', panel).val().trim(),
                        expressid: $('#expressId', panel).val(),
                        startdate: $('#startDate', panel).val(),
                        enddate: $('#endDate', panel).val(),
                        status: $('#status', panel).val()
                    });
                },
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#siteDayReportTable', panel).find("thead").height() + $('#siteDayReportTable', panel).find("tbody").height()
                        + 3; //+ $('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height();
                    var dataCount = $('#siteDayReportTable', panel).bootstrapTable('getData').length;
                    if ($('#siteDayReportTable', panel).parent().parent().parent().parent().find(".clearfix").height() <
                        $('#siteDayReportTable', panel).parent().parent().find(".fixed-table-footer").height()) {
                        if (dataCount < 10) {
                            tableHeight += 53;
                        } else {
                            tableHeight += 55;
                        }
                    } else {
                        tableHeight += $('#siteDayReportTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    }
                    var showFooter = true;
                    if (dataCount == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                        showFooter = false;
                    }
                    //if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                    tableHeight += ($('#siteDayReportTable', panel).parent().parent().parent().parent().find(".pull-right").height() + 20);
                    //}
                    tableHeight += 43;
                    if (showFooter) {
                        $('#siteDayReportTable', panel).parent().parent().find('.fixed-table-footer').show();
                    } else {
                        $('#siteDayReportTable', panel).parent().parent().find('.fixed-table-footer').hide();
                    }
                    $('#siteDayReportTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
                },
                /*onSearch: function (text) {
                 //表格控件不支持高度自适应
                 var tableHeight = $('#siteDayReportTable').find("thead").height() + $('#siteDayReportTable').find("tbody").height()
                 + 3 + $('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height();
                 if ($('#siteDayReportTable').parent().parent().parent().parent().find(".clearfix").height() == 0) {
                 tableHeight += 56;
                 }
                 if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                 tableHeight += ($('#siteDayReportTable').parent().parent().parent().parent().find(".pull-right").height() + 20);
                 }
                 if($('#siteDayReportTable').bootstrapTable('getData').length==0){//如果没有数据 给固定文字的高度
                 tableHeight=105;
                 }
                 tableHeight+=43;
                 $('#siteDayReportTable').bootstrapTable('resetView', {"height": tableHeight});
                 if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                 $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                 }
                 },*/
                columns: [{
                    field: 'storeCode',
                    title: '站点编号',
                    sortable: true,
                    footerFormatter: totalTextFormatter
                }, {
                    field: 'storeName',
                    title: '站点名称',
                    sortable: true,
                    width: 300
                }, {
                    field: 'location',
                    title: '站点地址',
                    sortable: true,
                    width: 200
                }, {
                    field: 'supervisor',
                    title: '站点负责人',
                    sortable: true
                }, {
                    field: 'expressCompanyName',
                    title: '快递公司(默认)',
                    sortable: true
                }, {
                    field: 'collegeName',
                    title: '负责校区',
                    sortable: true,
                    width: 300,
                }, {
                    field: 'status',
                    title: '站点状态',
                    sortable: true,
                }, {
                    field: 'submitTime',
                    title: '日期',
                    sortable: true,
                    width: 100
                }, {
                    field: 'totalCount',
                    title: '短信总数',
                    sortable: true,
                    footerFormatter: sumFormatter
                }, {
                    field: 'billCount',
                    title: '带单号短信数',
                    sortable: true,
                    footerFormatter: sumFormatter
                }, {
                    field: 'sucessCount',
                    title: '成功数',
                    sortable: true,
                    footerFormatter: sumFormatter
                }, {
                    field: 'failCount',
                    title: '失败数',
                    sortable: true,
                    footerFormatter: sumFormatter
                }]
            }, '#siteDayReportTable', panel);

            $.ajax({
                type: "POST",
                url: "/store/exp/storecodeinfo",
                dataType: "json",
                success: function (data) {
                    $("#storeid", panel).select2({
                        data: data
                    });
                }
            });

            $.ajax({
                type: "POST",
                url: "/manage/express/getenable",
                dataType: "json",
                success: function (data) {
                    $("#expressId", panel).select2({
                        data: data
                    });
                }
            });

            $(window).resize(function () {
                $('#siteDayReportTable', panel).bootstrapTable('resetView');
            });

            $("#btn_query", panel).click(function () {
                if ($('#startDate', panel).val() === '' || $('#endDate', panel).val() === '') {
                    base.error("请选择日期");
                    return;
                }
                datatable.bootstrapTable('refresh');
            });

            /*$(".text-change").on("input propertychange", function () {
                $(this).val($(this).val().trim());
            });*/

            $("#clearSearch", panel).click(function () {
                base.reset(".main-box-header");
                $("#storeid", panel).val(" ").trigger("change");
                $("#expressId", panel).val(" ").trigger("change");
                $("#status", panel).val(" ").trigger("change");
            });
        }
    };
});