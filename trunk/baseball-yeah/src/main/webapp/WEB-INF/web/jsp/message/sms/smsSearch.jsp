<%--
  Created by IntelliJ IDEA.
  User: wny
  Date: 2016-06-16
  Time: 19:19
  短信查询
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp"%>

<html>
<head>
    <meta charset="UTF-8" />
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
            require(['smsSearchModules'], function (offlineStore) {
                offlineStore.init();
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

                    <header class="main-box-header clearfix">
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="${pageContext.request.contextPath }/user/gotoIndex">首页</a></li>
                                    <li class="active"><span>客服管理</span></li>
                                </ol>
                                <h1>短信记录查询</h1>
                            </div>
                        </div>

                        <div class="panel-group" style="margin-bottom: 0px;">
                            <div class="panel panel-default" >
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
                                                    <label for="startdate" class="col-xs-2">日期：</label>
                                                    <div class="col-xs-4">
                                                        <div class="input-group date" id="starttimePicker">
                                                             <input type="text" class="form-control" name="start"
                                                                   id="startdate" placeholder="开始日期"></input>
																<span class="input-group-addon"><span
                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                        </div>
                                                    </div>

                                                    <div class="col-xs-4">
                                                        <div class="input-group date" id="endtimePicker">
                                                            <input type="text" class="form-control" name="end"
                                                                   id="enddate" placeholder="结束日期"></input><span
                                                                class="input-group-addon"><span
                                                                class="glyphicon-calendar glyphicon"></span> </span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="selstore" class="col-xs-2">商户名称：</label>
                                                    <div class="col-xs-10">
                                                        <select id="selstore" class="js-example-data-array" style='width: 250px'>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="selmsgType"  class="col-xs-2">消息类型：</label>
                                                    <div class="col-xs-10">
                                                        <select id="selmsgType" class="js-example-data-array" style='width: 250px'>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <label for="selsmsStatus" class="col-xs-2">短信状态：</label>
                                                    <div class="col-xs-10">
                                                        <select id="selsmsStatus" class="js-example-data-array" style='width: 250px'>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="txtRvPhone"  class="col-xs-2">接收人手机号码：</label>
                                                    <div class="col-xs-5">
                                                        <input type="text" name="txtRvPhone" class="form-control"
                                                               id="txtRvPhone" placeholder="手机号码 " style='width: 250px'/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <label for="txtSendPhone" class="col-xs-2">发送人手机号：</label>
                                                    <div class="col-xs-5">
                                                        <input type="text" name="txtSendPhone" class="form-control"
                                                               id="txtSendPhone" placeholder="手机号码 " style='width: 250px'/>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>

                    <%-- Buttons and data start --%>
                    <div class="table-responsive">
                        <div class="panel-body" style="padding-bottom: 0px;">
                            <div id="toolbar" class="btn-group">
                                <button type="button" id="btn_query" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                </button>
                                <%--<button id="btn_reset" type="button" class="btn btn-default">--%>
                                    <%--<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>清空--%>
                                <%--</button>--%>
                            </div>
                            <table id="userTable" class="table"></table>
                        </div>
                    </div>
                    <%-- Buttons and data end --%>
                </div>
            </div>
        </div>
    </div>
</div>
<style>
    .login-dialog .modal-dialog {
        width: 900px;
    }
</style>

<jsp:include page="smsBizInfo.jsp"></jsp:include>
</body>
</html>