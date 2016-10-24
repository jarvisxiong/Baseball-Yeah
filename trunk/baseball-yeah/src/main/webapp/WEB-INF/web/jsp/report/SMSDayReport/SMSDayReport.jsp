<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <script type="text/javascript"
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
            var date = new Date();
            //开始时间
            $('#starttimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                todayHighlight: true,
                endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker').datetimepicker('setStartDate', startTime);
            });

            //结束时间
            $('#endtimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                useCurrent: false,
                todayHighlight: true,
                endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker').datetimepicker('setEndDate', endTime);
            });

            require(['siteDayReportModules'], function (siteDayReport) {
                siteDayReport.init();
            });
        });
    </script>

</head>
<body>
<div id="page-wrapper" class="container">
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">

                    <header class="main-box-header clearfix">
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                    <li class="active"><span>报表管理</span></li>
                                </ol>
                                <h1>货源用户使用情况报表</h1>
                            </div>
                        </div>
                        <div class="panel-group" style="margin-bottom: 0px;"
                             id="accordion">
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
                                        <form id="formSearch" class="form-horizontal" role="form">

                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="storeName" class="control-label col-xs-2">站点名称:</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" name="storeName"
                                                               style="width: 240px" id="storeName"/>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="storeid" class="control-label col-xs-2">站点编号:</label>
                                                    <div class="col-xs-10">
                                                        <select id='storeid' style="width: 240px"
                                                                class="js-example-data-array form-control">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="supervisor"
                                                           class="control-label col-xs-2">站点负责人:</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" name="supervisor"
                                                               style="width: 240px" id="supervisor"/>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="expressId"
                                                           class="control-label col-xs-2">快递公司(默认):</label>
                                                    <div class="col-xs-10">
                                                        <select id='expressId' style="width: 240px"
                                                                class="js-example-data-array form-control">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2"
                                                               for="startDate">日期:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker">
                                                                <input type="text" class="form-control" id="startDate"/> <span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker">
                                                                <input type="text" class="form-control"
                                                                       id="endDate"/><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="status" class="control-label col-xs-2">站点状态:</label>
                                                    <div class="col-xs-10">
                                                        <select id='status' style="width: 240px"
                                                                class="form-control">
                                                            <option value=" ">全部</option>
                                                            <option value="1">启用</option>
                                                            <option value="0">禁用</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>

                    <div class="main-box-body clearfix">

                        <div id="toolbar" class="btn-group">
                            <shiro:hasPermission name="26_QUERY">
                                <button type="button" id="btn_query" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                </button>
                            </shiro:hasPermission>
                        </div>

                        <div class="table-responsive">
                            <table id="siteDayReportTable" class="table">
                            </table>
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
        var date = new Date();
        $('#starttimePicker').datetimepicker('setDate', new Date(date.getFullYear(), date.getMonth(), date.getDate(), 0, 0, 0));
        $('#starttimePicker').datetimepicker('update');
        $('#startDate').val(date.Format("yyyy-MM-dd") + " 00:00:00");
        $('#endtimePicker').datetimepicker('setDate', new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59));
        $('#endtimePicker').datetimepicker('update');
        $('#endDate').val(date.Format("yyyy-MM-dd") + " 23:59:59");
    });
</script>
</body>
</html>
