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
            require([ 'userModelReportModules' ], function(siteDayReport) {
                siteDayReport.init();
            });
        });
    </script>
</head>
<body>
<div id="page-wrapper" class="container">
    <div class="row">
        <div class="main-box clearfix">

            <header class="main-box-header clearfix">
                <div class="row">
                    <div class="col-lg-12">
                        <ol class="breadcrumb">
                            <li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                            <li class="active"><span>报表管理</span></li>
                        </ol>
                        <h1>用户模版报表</h1>
                    </div>
                </div>
                <div class="panel-group" style="margin-bottom: 0px;" id="accordion">
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
                                            <label for="userName" class="control-label col-xs-2">用户名称:</label>
                                            <div class="col-xs-10">
                                                <input type="text" class="form-control" name="userName"
                                                       style="width: 240px" id="userName"/>
                                            </div>
                                        </div>

                                        <div class="form-group col-xs-6">
                                            <label for="phoneNo" class="control-label col-xs-2">手机号:</label>
                                            <div class="col-xs-10">
                                                <input type="text" class="form-control" name="phoneNo"
                                                       style="width: 240px" id="phoneNo"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="store" class="control-label col-xs-2">货源名称:</label>
                                            <div class="col-xs-10">
                                                <input type="text" class="form-control text-change" name="store"
                                                       style="width: 240px" id="store"/>
                                            </div>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label for="phoneNo" class="control-label col-xs-2">模板内容:</label>
                                            <div class="col-xs-10">
                                                <input type="text" class="form-control" name="phoneNo"
                                                       style="width: 240px" id="modelContent"/>
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
                    <shiro:hasPermission name="181_QUERY">
                        <button type="button" id="btn_query" class="btn btn-default">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                        </button>
                    </shiro:hasPermission>

                    <shiro:hasPermission name="181_DELETE">
                        <button id="btn_delete" type="button" class="btn btn-default">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                        </button>
                    </shiro:hasPermission>
                </div>

                <div class="table-responsive">
                    <table id="userModelReportTable" class="table">
                    </table>
                </div>

            </div>
        </div>

    </div>
</div>
</body>
</html>