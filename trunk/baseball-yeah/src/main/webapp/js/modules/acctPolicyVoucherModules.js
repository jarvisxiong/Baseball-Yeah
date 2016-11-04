define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    return {
        init: function (panel) {
            var self = this;
            //初始化下拉框
            //base.loadRemoteSelect("#activityId", "/wallet/activity/activityselect");
            $.ajax({
                type: "POST",
                url: "/wallet/activity/activityselect",
                dataType: "json",
                success: function (data) {
                    $("#activityId", panel).select2({
                        data: data,
                        allowClear: false,
                        placeholder: {
                            id: '',
                            text: '请选择'
                        }
                    });
                    $("#activityId", panel).val("").trigger("change");
                }
            });
            base.loadRemoteSelect("#policyId", "/wallet/activity/policyselect");
            //event
            $('#activityId', panel).on('change', function () {
                base.loadRemoteSelect("#policyId", "/wallet/activity/policyselect", {activityId: $("#activityId", panel).val()});
            });
            //开始时间
            $('#starttimePicker,#starttimePicker2', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true,
                endDate: new Date()
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker,#endtimePicker2', panel).datetimepicker('setStartDate', startTime);
            });

            //结束时间
            $('#endtimePicker,#endtimePicker2', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true,
                endDate: new Date()
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker,#starttimePicker2', panel).datetimepicker('setEndDate', endTime);
            });


            $("#btn_query", panel).click(function () {
                if (!$("#activityId", panel).val()) {
                    base.error("请先选择活动!");
                    return;
                }
                if (datatable)   datatable.bootstrapTable('selectPage', 1);
                datatable = base.datagrid({
                    url: '/wallet/account/policyVoucherList',
                    queryParams: function (params) {
                        return $.extend(params,
                            {
                                data: '{"useStartTime":"' + ($("#startdate2").val() && ($("#startdate2").val() + " 00:00:00")) + '","useEndTime":"' + ($("#enddate2").val() && ($("#enddate2").val() + " 23:59:59")) + '","receiveStartTime":"' + ($("#startdate1").val()&&($("#startdate1").val()+" 00:00:00")) + '","receiveEndTime":"' + ($("#enddate1").val()&&($("#enddate1").val()+" 23:59:59")) + '","activityId":"' + $("#activityId").val() + '","policyId":"' + $("#policyId").val() + '","state":"' + $("#state").val() + '"}'
                            });
                    },
                    method: "post",
                    contentType: "application/x-www-form-urlencoded",
                    dataType: "json",
                    cache: false,
                    sidePagination: "server",
                    onPageChange: function () {
                        datatable.bootstrapTable('refresh');
                    },
                    onSort: function () {
                        datatable.bootstrapTable('refresh');
                    },
                    columns: [
                        {
                            field: 'voucherId',
                            title: '代金券id',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'activityName',
                            title: '活动名称',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'policyName',
                            title: '策略名称',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'stateName',
                            title: '状态',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'effectTime',
                            title: '生效时间',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'expireTime',
                            title: '失效时间',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'useTime',
                            title: '使用时间',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'receiveTime',
                            title: '领取时间',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'userId',
                            title: '所属用户',
                            sortable: true,
                            width: 150
                        },
                        {
                            field: 'faceValue',
                            title: '面值(元)',
                            sortable: true,
                            formatter: function (value, row, index) {
                                return "￥" + (row.faceValue) / 100
                            }
                        }
                    ]
                }, '#voucherTable', panel);

            });

            $("#clearSearch", panel).click(function () {
                base.reset(".main-box-header");
                $('#userName').val('');
                $('#activityId',panel).select2("val", null);
                $('#policyId',panel).select2("val", null);
                $('#startdate1').val('');
                $('#enddate1').val('');
                $('#startdate2').val('');
                $('#enddate2').val('');
                $('#state').val('');
            });

        }
    };
});
