<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派</title>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/axp/base.js"></script>

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/moment.min.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
            rel="stylesheet">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>

    <script type="text/javascript">
        $(function () {
            //开始时间
            $('#starttimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                todayHighlight: true,
                endDate: new Date()
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker').datetimepicker('setStartDate', startTime);
            });

            //结束时间
            $('#endtimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 1,
                todayHighlight: true,
                endDate: new Date()
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker').datetimepicker('setEndDate', endTime);
            });

            require(['siteSmsReportModules'], function (siteReport) {
                siteReport.init();
            });
        });
    </script>

</head>
<body>
<div id="page-wrapper" class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="main-box clearfix">
                <div class="main-box-body clearfix">
                    <div class="table-responsive">
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                    <li class="active"><span>报表管理</span></li>
                                </ol>
                                <h1>站点短信统计</h1>
                            </div>
                        </div>

                        <div class="row">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a-clear-search" id="clearSearch">
                                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                        </a>
                                        <a class="accordion-toggle a-search" data-toggle="collapse"
                                           data-parent="#accordion" href="#collapseOne"> 查询条件
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <form id="formSearch" class="form-inline" role="form">
                                            <div class="form-group">
                                                <div class="input-daterange">
                                                    <label class="sr-only">时间范围</label>
                                                    <div class="input-group date" id="starttimePicker">
                                                        <input type="text" class="form-control" name="start"
                                                               id="startdate" placeholder="开始日期"/> <span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                    至
                                                    <div class="input-group date" id="endtimePicker">
                                                        <input type="text" class="form-control" name="end"
                                                               id="enddate" placeholder="结束日期"/><span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="storeid" class="sr-only">站点</label>
                                                <div class="input-group">
                                                    <div class="input-group">
                                                        <select id='storeid' style="width: 200px"
                                                                class="form-control">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="button" id="btn_query" class="btn btn-success">
                                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row" style="padding-bottom: 0px;">
                            <ul class="nav nav-tabs" id="myTab">
                                <li class="active"><a href="#tab-home" data-toggle="tab">汇总</a></li>
                                <li><a href="#tab-accounts" data-toggle="tab">明细</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="tab-home">
                                    <table id="taotalContent" class="table">

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="tab-accounts">
                                    <table id="detailContent" class="table">

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
<script type="text/javascript">
    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()
            //毫秒 
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
                    .substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1,
                        (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
                                .substr(("" + o[k]).length)));
        return fmt;
    }
    $(function () {
        $("#startdate").val((new Date()).Format("yyyy-MM-dd") + " 00:00:00");
        $("#enddate").val((new Date()).Format("yyyy-MM-dd") + " 23:59:59");
    });
</script>
</body>
</html>
