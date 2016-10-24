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
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
    <script type="text/javascript">
        $(function () {
            require(['siteReportMonitor'], function (siteReport) {
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
                                <h1>后台监控报告</h1>
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
                                                    <div class="input-group date" id="starttimePicker">
                                                        <input type="text" class="form-control" name="start"
                                                               id="startdate" placeholder="开始日期" readonly></input> <span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                    至
                                                    <div class="input-group date" id="endtimePicker">
                                                        <input type="text" class="form-control" name="end"
                                                               id="enddate" placeholder="结束日期" readonly></input><span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="input-group">
                                                    <div class="input-group">
                                                        <select id='selcollage' style="width: 200px"
                                                                class="js-example-data-array form-control">
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
                                <li class="active"><a href="#tab-Home" data-toggle="tab" id="myTabHome">明细</a></li>
                                <li><a href="#tab-AllPhone" data-toggle="tab" id="myTabAllPhone">号码总数</a></li>
                                <li><a href="#tab-AllRe" data-toggle="tab" id="myTabAllRe">注册总数</a></li>
                                <li><a href="#tab-UnRe" data-toggle="tab" id="myTabUnRe">未注册总数</a></li>
                                <li><a href="#tab-NewPhone" data-toggle="tab" id="myTabNewPhone">新增号码</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="tab-Home">
                                    <div id="toolbar" class="btn-group">
                                        号码总数<strong id="totalNum">0</strong> 注册总数 <strong id="totalRegNum">0</strong>
                                        未注册总数 <strong id="totalUnregNum">0</strong> 总注册率<strong id="rate">0</strong>
                                    </div>
                                    <table id="taotalContent" class="table">

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="tab-AllPhone">
                                    <table id="detailAllPhone" class="table">

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="tab-AllRe">
                                    <table id="detailAllRe" class="table">

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="tab-UnRe">
                                    <table id="detailUnRe" class="table">

                                    </table>
                                </div>
                                <div class="tab-pane fade" id="tab-NewPhone">
                                    <table id="detailNewPhone" class="table">

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
</body>
</html>
