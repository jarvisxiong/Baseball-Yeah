define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    return {
        init: function () {
            var self = this;
            //初始化下拉框
            base.loadRemoteSelect("#activityId", "/wallet/activity/activityselect");
            base.loadRemoteSelect("#policyId", "/wallet/activity/policyselect");
            //event
            $('#activityId').on('change', function () {
                base.loadRemoteSelect("#policyId", "/wallet/activity/policyselect", {activityId: $("#activityId").val()});
            });


            $("#btn_query").click(function () {
                if (!$("#activityId").val()) {
                    base.error("请先选择活动!");
                    return;
                }
                if (datatable) datatable.bootstrapTable('refresh');
                datatable = base.datagrid({
                    url: '/wallet/account/policyVoucherList',
                    queryParams: function (params) {
                        return $.extend(params,
                            {
                                data: '{"useStartTime":"' + $("#startdate2").val() + '","useEndTime":"' + $("#enddate2").val() + '","receiveStartTime":"' + $("#startdate1").val() + '","receiveEndTime":"' + $("#enddate1").val() + '","activityId":"' + $("#activityId").val() + '","policyId":"' + $("#policyId").val() + '","state":"' + $("#state").val() + '"}'
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
                        datatable.bootstrapTable('refresh')
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
                }, '#voucherTable');

            });
            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
            });

        }
    };
});
