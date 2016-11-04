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
                url: '/report/packetoperation/queryCommentRateList',
                singleSelect: false,
                pageSize: 25,
                pageList: [25, 50, 100, 200],
                queryParams: function (params) {
                    return $.extend(params, {
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
                    title: '日期',
                    width: 80,
                    sortable: true
                }, {
                    field: 'campus',
                    title: '校区'
                }, {
                    field: 'commentOrderNum',
                    title: '有评论订单数',
                    width: 120,
                    sortable: true
                }, {
                    field: 'commentOrderTotalscore',
                    title: '有评论订单分值',
                    width: 120,
                    sortable: true
                }, {
                    field: 'commentOrderGetscore',
                    title: '用户评论分数',
                    width: 120,
                    sortable: true
                }, {
                    field: 'favorableRate',
                    title: '好评率',
                    width: 100,
                    sortable: true
                }, {
                    field: 'increaseRate',
                    title: '环比增长',
                    width: 100,
                    sortable: true
                }]
            }, '#commentTable', panel);

            $("#query", panel).click(function () {
                var tableList = $("#commentTable", panel).bootstrapTable('getData');
                if (tableList && tableList.length > 0) {
                    $("#commentTable", panel).bootstrapTable('selectPage', 1);
                } else {
                    $("#commentTable", panel).bootstrapTable('refresh');
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

            var now = new Date();
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
            $("#startDate", panel).val((new Date((new Date()).getTime() - 1 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));

            $('#endDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#startDatePicker', panel).datetimepicker('setEndDate', endTime);
            });
            $("#endDate", panel).val((new Date((new Date()).getTime() - 1 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));

        }
    };
});