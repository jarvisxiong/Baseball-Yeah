/**
 * Created by wny on 2016-06-17.
 */
/**
 * Created by wny on 2016-06-17.
 */
define(['base'], function (base) {
    var datatable;

    var _sumMethod;
    var _startSubmitTime;
    var _endSubmitTime;

    return {
        init: function (args) {
            var self = this;

            //开始时间
            $('#starttimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: true,
                minView: 2
            })

            //结束时间
            $('#endtimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: true,
                minView: 2
            });


            /*** 加载汇总方式列表 ***/
            $.ajax({
                type: 'GET',
                url: '/message/sms/getSumMethod',
                dataType: 'json',
                success: function (data) {
                    $("#selsmsSumMethod").select2({
                        data: data,
                        allowClear: true
                    });
                    $('#selsmsSumMethod').select2("val", "month");
                }
            });
            if (new Date().getMonth().toString() == "1"||new Date().getMonth().toString()=="01") {
                $("#startdate").val(new Date(new Date().getFullYear() - 1, 12, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

            }
            else {
                $("#startdate").val(new Date(new Date().getFullYear(), new Date().getMonth() - 1, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

            }
            $("#enddate").val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59).Format("yyyy-MM-dd HH:mm:ss"));
            _sumMethod = $("#selsmsSumMethod").val();
            _startSubmitTime = $("#startdate").val();
            _endSubmitTime = $("#enddate").val();
            function queryList() {
                datatable = base.datagrid({
                    url: '/message/sms/querySmsVendorSumGrid',
                    method: 'post',
                    queryParams: function (params) {
                        return $.extend(params, {
                            sumMethod: _sumMethod,
                            startSubmitTime: _startSubmitTime,
                            endSubmitTime: _endSubmitTime
                        });
                    },
                    showExport: true,// 显示导出按钮
                    exportDataType: "all",// 导出类型
                    pagination: true, // 是否显示分页（*）
                    sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
                    pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                    columns: [
                        {
                            field: 'groupDate',
                            title: '日期',
                            sortable: true
                        },
                        {
                            field: 'groupName',
                            title: '服务商名称',
                            sortable: true
                        },
                        {
                            field: 'submitCount',
                            title: '提交成功总数',
                            sortable: true
                        },
                        {
                            field: 'sucessCount',
                            title: '成功条数',
                            sortable: true
                        },
                        {
                            field: 'failCount',
                            title: '失败条数',
                            sortable: true

                        },
                        {
                            field: 'sentCount',
                            title: '未回执条数',
                            visible: true,
                            sortable: true

                        },

                        {
                            field: 'sucessRate',
                            title: '成功率',
                            visible: true,
                            sortable: true
                        }

                    ],
                    onClickCell: function (field, value, row) {
                        switch (field) {
                            case "arrivTotal":
                                self.detail(row);
                                break;
                        }
                    }
                }, '#userTable');
                datatable.bootstrapTable('refresh');
                $(window).resize(function () {
                    $('#wayBillLogTable').bootstrapTable('resetView');
                });
            }


            $("#btn_query").click(function () {

                _sumMethod = $("#selsmsSumMethod").val();
                _startSubmitTime = $("#startdate").val();
                _endSubmitTime = $("#enddate").val();
                queryList();
            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
                if (new Date().getMonth().toString() == "1"||new Date().getMonth().toString()=="01") {
                    $("#startdate").val(new Date(new Date().getFullYear() - 1, 12, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

                }
                else {
                    $("#startdate").val(new Date(new Date().getFullYear(), new Date().getMonth() - 1, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

                }
                $("#enddate").val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59).Format("yyyy-MM-dd HH:mm:ss"));
                $('#selsmsSumMethod').select2("val", "month");
            });
        }

    }
});