<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>做单任务管理</title>
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
            src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
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
            //开始时间1
            $('#starttimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker').datetimepicker('setStartDate', startTime);
            });
            //开始时间2
            $('#starttimePicker2').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker2').datetimepicker('setStartDate', startTime);
            });
            //开始时间3
            $('#starttimePicker3').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 0,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#endtimePicker3').datetimepicker('setStartDate', startTime);
            });
            //结束时间1
            $('#endtimePicker').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 1,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker').datetimepicker('setEndDate', endTime);
            });
            //结束时间2
            $('#endtimePicker2').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 1,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker2').datetimepicker('setEndDate', endTime);
            });

            //结束时间3
            $('#endtimePicker3').datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                startView: 2,
                minView: 1,
                todayHighlight: true
            }).on('changeDate', function (e) {
                var endTime = e.date;
                $('#starttimePicker3').datetimepicker('setEndDate', endTime);
            });


            require(['doOrderTaskModules'], function (task) {
                task.init();
            });
        });
    </script>
    <style type="text/css">
        #formSearch label {
            margin-top: 2px;
        }
    </style>
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
                                    <li><a
                                            href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                    <li class="active"><span>任务订单管理</span></li>
                                </ol>
                                <h1>做单任务管理</h1>
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
                                                    <label for="mainTaskNo"
                                                           class="control-label col-xs-2">主任务单号：</label>
                                                    <div class="col-xs-4">
                                                        <input type="text" id="mainTaskNo" style="width: 240px"
                                                               class="form-control" placeholder="主任务单号"/>
                                                    </div>
                                                    <label for="taskNo" class="control-label col-xs-2">任务单号</label>
                                                    <div class="col-xs-4">
                                                        <input type="text" id="taskNo" style="width: 240px"
                                                               class="form-control" placeholder="任务单号"/>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="orderId" class="control-label col-xs-2">订单号：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="orderId" style="width: 240px"
                                                               class="form-control" placeholder="订单号"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-xs-6">

                                                    <label for="stateSelect"
                                                           class="control-label col-xs-2">状态:</label>
                                                    <div class="col-xs-10">
                                                        <select id="stateSelect" style="width: 240px"
                                                                class="form-control" placeholder="帐号状态">
                                                            <option value="">全部</option>
                                                            <option value="10">待审核</option>
                                                            <option value="5">已完成</option>
                                                            <option value="6">已取消</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <label for="winner" class="control-label col-xs-2">小派姓名：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="winner" style="width: 240px"
                                                               class="form-control" placeholder="小派姓名"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="phone" class="control-label col-xs-2">手机号：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="phone" style="width: 240px"
                                                               class="form-control" placeholder="手机号"/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2"
                                                               for="startDate">接单时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker">
                                                                <input type="text" class="form-control" id="startdate"
                                                                       placeholder="开始日期" name="start" /><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker">
                                                                <input type="text" class="form-control" name="end"
                                                                       id="enddate" placeholder="结束日期"/><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2"
                                                               for="startDate2">提交审核时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker2">
                                                                <input type="text" class="form-control" id="startdate2"
                                                                       placeholder="开始日期" name="start2"/><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate2">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker2">
                                                                <input type="text" class="form-control" name="end2"
                                                                       id="enddate2" placeholder="结束日期"/><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2"
                                                               for="startDate3">结束时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker3">
                                                                <input type="text" class="form-control" id="startdate3"
                                                                       placeholder="开始日期" name="start"/><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate3">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker3">
                                                                <input type="text" class="form-control" name="end"
                                                                       id="enddate3" placeholder="结束日期"/><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div id="toolbar" class="btn-group">
                        <shiro:hasPermission name="172_QUERY">
                            <button type="button" id="btn_query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
                        </shiro:hasPermission>
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="doOrderTaskTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>
