/**
 * Created by wny on 2016-06-17.
 */
/**
 * Created by wny on 2016-06-17.
 */
define(['base'], function (base) {
    //var datatable;

    var _sumMethod;
    var _startSubmitTime;
    var _endSubmitTime;

    return {
        init: function (panel) {
            var self = this;

            //开始时间
            $('#starttimePicker',panel).datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: true,
                minView: 2
            })

            //结束时间
            $('#endtimePicker',panel).datetimepicker({
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
                    $("#selsmsSumMethod",panel).select2({
                        data: data,
                        allowClear: false
                    });
                    $('#selsmsSumMethod',panel).select2("val", "month");
                }
            });
            if (new Date().getMonth().toString() == "1"||new Date().getMonth().toString()=="01") {
                $("#startdate",panel).val(new Date(new Date().getFullYear() - 1, 12, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

            }
            else {
                $("#startdate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth() - 1, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

            }
            $("#enddate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59).Format("yyyy-MM-dd HH:mm:ss"));
            _sumMethod = $("#selsmsSumMethod",panel).val();
            _startSubmitTime = $("#startdate",panel).val();
            _endSubmitTime = $("#enddate",panel).val();
            function queryList(panel) {
                base.datagrid({
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
                    onLoadSuccess : function(data) {
    					// 表格控件不支持高度自适应
    					var tableHeight = 105
    							+ $("#userTable", panel).find("thead")
    									.height()
    							+ $("#userTable", panel).find("tbody")
    									.height()
    							+ $("#userTable", panel).parent().parent()
    									.parent().parent().find(".clearfix")
    									.height();
    					if (!data || data.length == 0) {// 如果没有数据 给固定文字的高度
    						tableHeight = 105;
    					}
    					$("#userTable", panel).bootstrapTable('resetView', {
    						"height" : tableHeight
    					});
                    },
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
                }, '#userTable',panel);
                $('#userTable',panel).bootstrapTable('refresh');
                //$('#userTable',panel).bootstrapTable('selectPage', 1);
                $(window).resize(function () {
                    $('#userTable',panel).bootstrapTable('resetView');
                });
            }


            $("#btn_query",panel).click(function () {

                _sumMethod = $("#selsmsSumMethod",panel).val();
                _startSubmitTime = $("#startdate",panel).val();
                _endSubmitTime = $("#enddate",panel).val();
                if ((new Date(Date.parse(_endSubmitTime.replace(/-/g, "/"))).getTime() - new Date(Date.parse(_startSubmitTime.replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "结束时间不能小于开始时间!", "info");
                    return;
                }
                queryList(panel);
            });
            $("#clearSearch",panel).click(function () {
                base.reset($(".main-box-header",panel));
                if (new Date().getMonth().toString() == "1"||new Date().getMonth().toString()=="01") {
                    $("#startdate",panel).val(new Date(new Date().getFullYear() - 1, 12, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

                }
                else {
                    $("#startdate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth() - 1, 01, 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));

                }
                $("#enddate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59).Format("yyyy-MM-dd HH:mm:ss"));
                $('#selsmsSumMethod',panel).select2("val", "month");
            });
        }

    }
});