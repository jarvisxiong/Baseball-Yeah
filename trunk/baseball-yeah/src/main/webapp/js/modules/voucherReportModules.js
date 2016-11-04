define(['base'], function (base) {
    var datatable;

    return {
        init: function (panel) {
            var self = this;
            /*领用时间*/
            //开始时间
            $('#receiveStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2
                /*format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 startView: 2,
                 minView: 0,
                 todayHighlight: true*/
            })

            //结束时间
            $('#receiveEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2
                /*format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 startView: 2,
                 minView: 0,
                 todayHighlight: true*/
            });

            /*使用时间*/
            //开始时间
            $('#useStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2
                /*format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 startView: 2,
                 minView: 0,
                 todayHighlight: true*/
            })

            //结束时间
            $('#useEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2
                /*format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 startView: 2,
                 minView: 0,
                 todayHighlight: true*/
            });

            /*失效时间*/
            //开始时间
            $('#expireStartTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2
                /*format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 startView: 2,
                 minView: 0,
                 todayHighlight: true*/
            })

            //结束时间
            $('#expireEndTimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: true,
                minView: 2
                /*format: 'yyyy-mm-dd hh:ii:ss',
                 autoclose: true,
                 startView: 2,
                 minView: 0,
                 todayHighlight: true*/
            });

            $("#useStartTime", panel).val((new Date((new Date()).getTime() - 30 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
            $("#useEndTime", panel).val((new Date()).Format("yyyy-MM-dd"));

            /*** 加载城市列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#cityId", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#cityId', panel).select2("val", null);
                }
            });
            /*** 加载校区名称列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/college/getCollageForSel",
                dataType: "json",
                success: function (data) {
                    $("#collegeId", panel).select2({
                        data: data.data
                    });
                }
            });
            base.datagrid({
                url: '/report/voucher/queryReportVoucherList',
                method: 'GET',
                exportAllExcel: function () {
                    return "/report/voucher/exportExcel" + self.searchVal(panel);
                },

                showExport: true,// 显示导出按钮
                exportDataType: "all",// 导出类型
                pagination: true, // 是否显示分页（*）
                pageList: [10, 20, 50, 100, 500, 1000], // 可供选择的每页的行数（*）
                queryParams: function (params) {
                    return $.extend(params, {
                        collegeId: $("#collegeId", panel).val() == null ? "" : $("#collegeId", panel).val().trim(),
                        state: $("#state", panel).val() == null ? "" : $("#state", panel).val().trim(),
                        receiveStartTime: $("#receiveStartTime", panel).val().trim(),
                        receiveEndTime: $("#receiveEndTime", panel).val().trim(),
                        cityId: $("#cityId", panel).val() == null ? "" : $("#cityId", panel).val().trim(),
                        useStartTime: $("#useStartTime", panel).val().trim(),
                        useEndTime: $("#useEndTime", panel).val().trim(),
                        expireStartTime: $("#expireStartTime", panel).val().trim(),
                        expireEndTime: $("#expireEndTime", panel).val().trim()
                    });
                },
                columns: [
                    /*{
                     checkbox: true
                     },*/
                    {
                        field: 'rowNo',
                        title: '序号',
                        sortable: true
                    },
                    {
                        field: 'voucherId',
                        title: '代金券ID',
                        sortable: true
                    },
                    {
                        field: 'collegeName',
                        title: '校区名称',
                        sortable: true
                    },
                    {
                        field: 'cityName',
                        title: '城市',
                        sortable: true
                    },
                    {
                        field: 'orderId',
                        title: '订单号',
                        sortable: true
                    },
                    {
                        field: 'money',
                        title: '代金券金额',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return "￥" + value
                        }
                    },
                    {
                        field: 'state',
                        title: '订单状态',
                        sortable: true
                    },
                    {
                        field: 'channelName',
                        title: '订单入口',
                        sortable: true,
                        visible: true
                    },
                    {
                        field: 'itemName',
                        title: '渠道来源',
                        sortable: true,
                        visible: true
                    },
                    {
                        field: 'receiveTime',
                        title: '领用时间',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value.length > 2) {
                                return value.substr(0, value.length - 2)
                            }
                        }
                    },
                    {
                        field: 'useTime',
                        title: '使用时间',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value.length > 2) {
                                return value.substr(0, value.length - 2)
                            }
                        }
                    },
                    {
                        field: 'expireTime',
                        title: '失效时间',
                        visible: true,
                        sortable: true,
                        formatter: function (value, row, index) {
                            if (value != null && value.length > 2) {
                                return value.substr(0, value.length - 2)
                            }
                        }
                    }],
            }, '#voucherTable', panel);

            $("#btn_query", panel).click(function () {
                self.searchDateVail(panel);
                // $("#voucherTable", panel).bootstrapTable('selectPage', 1);

            });
            // $("#btn_Excel", panel).click(function () {
            //     location.href="/report/offline/exportExcel";
            // });
            $("#clearSearch", panel).click(function () {
                base.reset(".main-box-header");
                $("#collegeId", panel).val("").trigger("change");
                $("#state", panel).val("").trigger("change");
                $("#receiveStartTime", panel).val("");
                $("#receiveEndTime", panel).val("");
                $("#cityId", panel).val("").trigger("change");
                $("#useStartTime", panel).val("");
                $("#useEndTime", panel).val("");
                $("#expireStartTime", panel).val("");
                $("#expireEndTime", panel).val("");
                $("#useStartTime", panel).val((new Date((new Date()).getTime() - 30 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
                $("#useEndTime", panel).val((new Date()).Format("yyyy-MM-dd"));
                self.searchDateVail(panel);
                // $("#voucherTable", panel).bootstrapTable('selectPage', 1);
            });
        },
        searchDateVail: function (panel) {
            var myDate1 = new Date();
            var myDate2 = new Date();
            // $("#voucherTable", panel).bootstrapTable('refresh');
            var receive1 = $("#receiveStartTime", panel).val();
            var receive2 = $("#receiveEndTime", panel).val();
            if(receive1.trim()!="" && receive2.trim()!=""){
                myDate1.setFullYear(receive1.substring(0, 4), receive1.substring(5, 7), receive1.substring(8));
                myDate2.setFullYear(receive2.substring(0, 4), receive2.substring(5, 7), receive2.substring(8));
                if (myDate2 < myDate1) {
                    base.error("领用时间：查询结束时间不能小于开始时间!");
                    return;
                }
            }
            /*            if((myDate2 - myDate1)/86400000 > 31){//如今天20161008，一个月前为20160908，查询范围为2016-09-08 00:00:00 至 2016-10-08 00:00:00为30天，但计算的结果为31
             base.error("领用时间查询范围应在一个月内!");return;
             }*/


            var used1 = $("#useStartTime", panel).val();
            var used2 = $("#useEndTime", panel).val();

            myDate1.setFullYear(used1.substring(0, 4), used1.substring(5, 7), used1.substring(8));
            myDate2.setFullYear(used2.substring(0, 4), used2.substring(5, 7), used2.substring(8));
            if (myDate2 < myDate1) {
                base.error("使用时间：查询结束时间不能小于开始时间!");
                return;
            }
            // alert((myDate2 - myDate1)/86400000);
            if ((myDate2 - myDate1) / 86400000 > 31) {
                base.error("使用时间查询范围应在一个月内!");
                return;
            }

            var expire1 = $("#expireStartTime", panel).val();
            var expire2 = $("#expireEndTime", panel).val();
            if(expire1.trim()!="" && expire2.trim()!=""){
                myDate1.setFullYear(expire1.substring(0, 4), expire1.substring(5, 7), expire1.substring(8));
                myDate2.setFullYear(expire2.substring(0, 4), expire2.substring(5, 7), expire2.substring(8));
                if (myDate2 < myDate1) {
                    base.error("失效时间：查询结束时间不能小于开始时间!");
                    return;
                }
            }
            /*            if((myDate2 - myDate1)/86400000 > 31){
             base.error("失效时间查询范围应在一个月内!");return;
             }*/
            $("#voucherTable", panel).bootstrapTable('selectPage', 1);
        },
        searchVal: function (panel) {
            var str = "?collegeId=" + ($("#collegeId", panel).val() == null ? "" : $("#collegeId", panel).val().trim())
                + "&state=" + ($("#state", panel).val() == null ? "" : $("#state", panel).val().trim())
                + "&receiveStartTime=" + ($("#receiveStartTime", panel).val().trim())
                + "&receiveEndTime=" + ($("#receiveEndTime", panel).val().trim())
                + "&cityId=" + ($("#cityId", panel).val() == null ? "" : $("#cityId", panel).val().trim())
                + "&useStartTime=" + ($("#useStartTime", panel).val().trim())
                + "&useEndTime=" + ($("#useEndTime", panel).val().trim())
                + "&expireStartTime=" + ($("#expireStartTime", panel).val().trim())
                + "&expireEndTime=" + ($("#expireEndTime", panel).val().trim());
            return str;
        }
    }
});
