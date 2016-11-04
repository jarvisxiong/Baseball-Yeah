define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var self;
    return {
        init: function (panel) {
            self = this;
            panel = panel || $('#indextab').tabs('getSelected');

            self.loadControl(panel);
            base.datagrid({
                url: '/report/packetoperation/queryActivityRateList',
                pageSize: 25,
                pageList: [25, 50, 100, 200],
                singleSelect: false,
                queryParams: function (params) {
                    return $.extend(params, {
                        searchMonth: $("#monthSelect", panel).val(),
                        searchColleges: base.getTreeValues(campusSelect).join(","),
                        searchStartDate: $('#startDate', panel).val(),
                        searchEndDate: $('#endDate', panel).val()
                    });
                },
                columns: [{
                    checkbox: true,
                    width: 40
                }, {
                    field: 'day',
                    title: '时间',
                    width: 80,
                    sortable: true
                }, {
                    field: 'collegeName',
                    title: '校区'
                }, {
                    field: 'puserNum',
                    title: '小派总数',
                    width: 100,
                    sortable: true
                }, {
                    field: 'activePuserNum',
                    title: '活跃小派数',
                    width: 100,
                    sortable: true
                }, {
                    field: 'activeRate',
                    title: '活跃度',
                    width: 120,
                    sortable: true
                }, {
                    field: 'averageActiveRate',
                    title: '平均活跃度',
                    width: 120,
                    sortable: true
                }, {
                    field: 'increaseActiveRate',
                    title: '活跃度新增',
                    width: 120,
                    sortable: true
                }, {
                    field: 'packetIncreaseRate',
                    title: '环比增长',
                    width: 120,
                    sortable: true
                }]
            }, '#activityTable', panel);

            $("#query", panel).click(function () {
                if ($("#monthSelect").val() && ($("#startDate").val() || $("#endDate").val())) {
                    sweetAlert("", "月份和日期不能同时选择", "info");
                    return;
                }
                var tableList = $("#activityTable", panel).bootstrapTable('getData');
                if (tableList && tableList.length > 0) {
                    $("#activityTable", panel).bootstrapTable('selectPage', 1);
                } else {
                    $("#activityTable", panel).bootstrapTable('refresh');
                }
            });

            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header", panel));

                campusSelect.combotree("clear");
                $('#startDate', panel).val('');
                $('#endDate', panel).val('');
            });
        },
        loadControl: function (panel) {
            campusSelect = $('#campus', panel);
            base.collegeTree(campusSelect);

            // 开始时间
            $('#startDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endDatePicker', panel).datetimepicker('setStartDate', startTime);
            });

            $('#endDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#startDatePicker', panel).datetimepicker('setEndDate', endTime);
            });

            $('#monthSelectPicker', panel).datetimepicker({
                format: 'yyyy-mm',
                autoclose: true,
                startView: 3,
                minView: 3
            });

        }
    };
});