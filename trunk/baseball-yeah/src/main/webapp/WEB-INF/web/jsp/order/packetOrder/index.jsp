<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派</title>

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/axp/base.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
            rel="stylesheet">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
    <script type="text/javascript">
        $(function () {
            $(function () {
                require(['packetOrderModules'], function (userOrder) {
                    userOrder.init();
                });
            });
        });
    </script>

</head>
<body>
<div id="page-wrapper" class="container">
    <div class="row">
        <form action="">--%>
            <input type="hidden" class="inmodule" value="packetOrderModules">
            <div class="row">
                <div class="col-lg-12">
                    <div class="main-box clearfix">
                        <div class="main-box-body clearfix">
                            <%--<div class="row">--%>
                                <%--<div class="row">--%>
                                    <%--<div class="col-lg-12">--%>
                                        <%--<ol class="breadcrumb">--%>
                                            <%--<li><a--%>
                                                    <%--href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
                                            <%--<li class="active"><span>订单管理</span></li>--%>
                                        <%--</ol>--%>
                                        <%--<h1>派件订单管理</h1>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <div class="table-responsive">

                                <div class="panel-body" style="padding-bottom: 0px;">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="a-clear-search" id="clearSearch"> <span
                                                        class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                                </a> <a class="accordion-toggle a-search"
                                                        data-toggle="collapse" data-parent="#accordion"
                                                        href="#packetOrdercollapseOne"> 查询条件 </a>
                                            </h4>
                                        </div>
                                        <div id="packetOrdercollapseOne" class="panel-collapse collapse in">
                                            <div class="panel-body" style="width: 100%;">
                                                <form id="formSearch" class="form-horizontal">

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="orderId" class="control-label col-xs-2">订单号</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control" name="orderId"
                                                                       style="width: 240px" id="orderId">
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="state" class="control-label col-xs-2">订单状态</label>
                                                            <div class="col-xs-10">
                                                                <select id='state' name="state"
                                                                        class="js-example-data-array form-control"
                                                                        style="width: 240px">
                                                                    <option value="">请选择</option>
                                                                    <option value="1">创建</option>
                                                                    <option value="100">待接单</option>
                                                                    <option value="2">已接单</option>
                                                                    <option value="3">配送中</option>
                                                                    <option value="4">处理中</option>
                                                                    <option value="5">完成</option>
                                                                    <option value="6">取消</option>
                                                                    <option value="7">异常</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="createDate" class="control-label col-xs-2">创建时间</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date"
                                                                     id="createStartDatePicker">
                                                                    <input type="text" class="form-control"
                                                                           name="createStartDate" id="createStartDate"
                                                                           placeholder="开始日期"></input> <span
                                                                        class="input-group-addon"> <span
                                                                        class="glyphicon-calendar glyphicon"></span>
																		</span>
                                                                </div>
                                                            </div>
                                                            <label class="control-label col-xs-1" for="endDate">至</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="createEndDatePicker">
                                                                    <input type="text" class="form-control"
                                                                           id="createEndDate" placeholder="结束时间"></input><span
                                                                        class="input-group-addon"><span
                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="deliveryEndDate"
                                                                   class="control-label col-xs-2">签收时间</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date"
                                                                     id="deliveryStartDatePicker">
                                                                    <input type="text" class="form-control"
                                                                           name="deliveryStartDate" id="deliveryStartDate"
                                                                           placeholder="开始时间"></input> <span
                                                                        class="input-group-addon"><span
                                                                        class="glyphicon-calendar glyphicon"> </span> </span>
                                                                </div>
                                                            </div>
                                                            <label class="control-label col-xs-1" for="endDate">至</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date"
                                                                     id="deliveryEndDatePicker">
                                                                    <input type="text" class="form-control"
                                                                           id="deliveryEndDate" placeholder="结束时间"></input><span
                                                                        class="input-group-addon"><span
                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="deliveryStartDate" class="control-label col-xs-2">确认取件时间</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date"
                                                                     id="deliverySStartDatePicker">
                                                                    <input type="text" class="form-control"
                                                                           name="deliverySStartDate" id="deliverySStartDate"
                                                                           placeholder="开始日期"></input> <span
                                                                        class="input-group-addon"> <span
                                                                        class="glyphicon-calendar glyphicon"></span>
																		</span>
                                                                </div>
                                                            </div>
                                                            <label class="control-label col-xs-1" for="endDate">至</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="deliverySEndDatePicker">
                                                                    <input type="text" class="form-control"
                                                                           id="deliverySEndDate" placeholder="结束时间"></input><span
                                                                        class="input-group-addon"><span
                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="payId" class="control-label col-xs-2">支付流水号</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control" name="payId"
                                                                       style="width: 240px" id="payId">
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="phone" class="control-label col-xs-2">众包人手机号码</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control" name="phone"
                                                                       style="width: 240px" id="phone">
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="mobile" class="control-label col-xs-2">收件人手机号码</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control" name="mobile"
                                                                       style="width: 240px" id="mobile">
                                                            </div>
                                                        </div>

                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="payType" class="control-label col-xs-2">支付方式</label>
                                                            <div class="col-xs-10">
                                                                <select id='payType' name="payType"
                                                                        class="form-control" style="width: 240px">
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="payType" class="control-label col-xs-2">学校</label>
                                                            <div class="col-xs-10">
                                                                <select id='collegeId' name="collegeId"
                                                                        class="form-control" style="width: 240px">
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="toolbar" class="btn-group">

                                        <shiro:hasPermission name="168_QUERY">
                                            <button type="button" id="btn_query" class="btn btn-default">
                                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                            </button>
                                        </shiro:hasPermission>

                                    </div>
                                    <table id="userTable" class="table">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
       <%-- </form>

    </div>
</div>
</div>--%>
<jsp:include page="orderDetail.jsp"></jsp:include>
<%--<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>--%>
<%--</body>
</html>--%>
