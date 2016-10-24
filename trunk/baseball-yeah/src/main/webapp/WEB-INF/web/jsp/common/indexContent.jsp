<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/js/axp/index.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/plugs/echart/echarts.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/plugs/echart/theme/macarons.js"></script>
<script type="text/javascript">
    $(function () {
        //setInterval("getTimerInfo()", 60000);
        $.get('/user/tenDayUsers', function (DATA, status) {
            if (status == 'success') {
                var j = JSON.parse(DATA);
                /* charts开始 */
                $('.conversation-inner').slimScroll({
                    height: '352px',
                    alwaysVisible: false,
                    railVisible: true,
                    wheelStep: 5,
                    allowPageScroll: false
                });

                //CHARTS
                graphLine = Morris.Line({
                    element: 'graph-line',
                    data: j,
                    lineColors: ['#ffffff'],
                    xkey: 'dayTime',
                    ykeys: ['number'],
                    labels: ['注册用户数量'],
                    pointSize: 5,
                    hideHover: 'auto',
                    gridTextColor: '#ffffff',
                    gridLineColor: 'rgba(255, 255, 255, 0.3)',
                    resize: true
                });
            }
            /* charts结束 */
        });

        var chart = echarts.init(document.getElementById('main'), 'macarons');
        var option = {
            title: {
                text: '短信监控'
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '2px',
                right: '20px',
                bottom: '8%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value', minInterval: 1
                }
            ],
            series: [
                {
                    name: '短信数量',
                    type: 'line',
                    stack: '总量',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    },
                    areaStyle: {normal: {}},
                    data: []
                }
            ]
        };
        chart.setOption(option);

        var orderChart = echarts.init(document.getElementById('order'), 'macarons');
        orderOption = {
            title: {
                text: '订单监控'
            },
            tooltip: {
                trigger: 'axis'
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: ['订单数', '总金额']
            },
            xAxis: [
                {
                    type: 'category',
                    data: []
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '数量',
                    axisLabel: {
                        formatter: '{value}'
                    }
                },
                {
                    type: 'value',
                    name: '金额',
                    axisLabel: {
                        formatter: '{value} ￥'
                    }
                }
            ],
            series: []
        };

        orderChart.setOption(orderOption);

        orderChart.showLoading();
        $.get('/user/getOderStatiscs', function (data) {
            orderChart.hideLoading();
            var datacategories = [], series1 = [], series2 = [];
            var ii=5;
            for(var i=0;i<6;i++){
                datacategories.push(data.data[ii].dateTimeString.substring(11) + ":00");
                series1.push(data.data[ii].orderCout || 0);
                series2.push((data.data[ii].finalMoney || 0)/100);//有null值给默认值0
                ii--;
            }
            orderChart.setOption({
                xAxis: {
                    data: datacategories
                },
                series: [{
                    name: '订单数',
                    type: 'bar',
                    data: series1
                },
                    {
                        name: '总金额',
                        type: 'line',
                        yAxisIndex: 1,
                        data: series2
                    }]
            });
        })
        var xdata = [];
        var sdata = [];
        setInterval(function () {
            var now = new Date();
            var dateEnd = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('-') + " " + [now.getHours(), now.getMinutes(), now.getSeconds()].join(':');
            now.setSeconds(now.getSeconds() - 10);
            var dateBegin = [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('-') + " " + [now.getHours(), now.getMinutes(), now.getSeconds()].join(':');
            $.get('/user/getSMSTotalByDateTime', {
                "dateBegin": dateBegin,
                "dateEnd": dateEnd
            }, function (data) {
                xdata.push(dateEnd);
                if (xdata.length > 6) {
                    xdata.shift();
                }
                sdata.push(data.data);
                if (sdata.length > 6) {
                    sdata.shift();
                }
                chart.setOption({
                    xAxis: {
                        data: xdata
                    },
                    series: [{
                        name: '成交',
                        data: sdata
                    }]
                });
            });
        }, 10000);

    });

    // graph real time
    if ($('#graph-flot-realtime').length) {

        var data = [], totalPoints = 300;

        function getRandomData() {

            if (data.length > 0)
                data = data.slice(1);

            // Do a random walk

            while (data.length < totalPoints) {

                var prev = data.length > 0 ? data[data.length - 1] : 50, y = prev
                        + Math.random() * 10 - 5;

                if (y < 0) {
                    y = 0;
                } else if (y > 100) {
                    y = 100;
                }

                data.push(y);
            }

            // Zip the generated y values with the x values

            var res = [];
            for (var i = 0; i < data.length; ++i) {
                res.push([i, data[i]])
            }

            return res;
        }

        // Set up the control widget

        var updateInterval = 30;
        $("#updateInterval").val(updateInterval).change(function () {
            var v = $(this).val();
            if (v && !isNaN(+v)) {
                updateInterval = +v;
                if (updateInterval < 1) {
                    updateInterval = 1;
                } else if (updateInterval > 2000) {
                    updateInterval = 2000;
                }
                $(this).val("" + updateInterval);
            }
        });

        var plot = $.plot("#graph-flot-realtime", [getRandomData()], {
            series: {
                lines: {
                    show: true,
                    lineWidth: 2,
                    fill: true,
                    fillColor: {
                        colors: [{
                            opacity: 0.3
                        }, {
                            opacity: 0.3
                        }]
                    }
                },
                shadowSize: 0
                // Drawing is faster without shadows
            },
            colors: ["#e74c3c"],
            yaxis: {
                min: 0,
                max: 100
            },
            xaxis: {
                show: false
            }
        });

        function update() {

            plot.setData([getRandomData()]);

            // Since the axes don't change, we don't need to call plot.setupGrid()

            plot.draw();
            setTimeout(update, updateInterval);
        }

        update();
    }
</script>

<div class="main-box clearfix">
    <div class="main-box-body clearfix">
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="main-box infographic-box">
                    <i class="fa fa-user purple-bg"></i> <span class="headline">用户</span>
                    <span id="userNumbers" class="value">${userTotal}</span>
                </div>
            </div>
            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="main-box infographic-box">
                    <%--                 <a href="<%=request.getContextPath()%>/user/storeUserList?startDate=<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" style="text-decoration: none;">--%>
                    <i class="fa fa-shopping-cart emerald-bg"></i> <span
                        class="headline">今日订单数量</span> <span id="todayUsers" class="value">${todayNewOrderTotal}</span>
                    <!-- </a> -->
                </div>
            </div>
            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="main-box infographic-box">
                    <i class="fa fa-money green-bg"></i> <span class="headline">今日发送短信数量</span>
                    <span id="todaySMS" class="value">${todaySMS}</span>
                </div>
            </div>
            <div class="col-lg-3 col-sm-6 col-xs-12">
                <div class="main-box infographic-box">
                    <i class="fa fa-eye yellow-bg"></i> <span class="headline">今日新增手机数量</span>
                    <span id="todayNewPhoneTotal" class="value">${todayNewPhoneTotal}</span>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="graph-box emerald-bg">
                    <h2>注册用户数量</h2>
                    <div class="graph" id="graph-line" style="max-height: 335px;"></div>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 20px;">
            <div class="col-lg-6">
                <div id="order" style="height:500px;"></div>
            </div>
            <div class="col-lg-6">
                <div id="main" style="height:500px;"></div>
            </div>
        </div>


    </div>
</div>

