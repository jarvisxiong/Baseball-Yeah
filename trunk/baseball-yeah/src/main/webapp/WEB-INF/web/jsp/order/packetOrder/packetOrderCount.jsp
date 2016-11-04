<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="packetOrderCountModules" />
<input type="hidden" id="packetUserId" value="${packetUserId}"/>
<div class="row">
    <form action="">
        <div class="col-lg-12">
            <div class="main-box clearfix">
                <div class="main-box-body clearfix">
                    <header class="main-box-header clearfix">
                        <div class="panel-group" style="margin-bottom: 0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a-clear-search" id="clearSearch">
                                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                        </a>
                                        <a class="accordion-toggle a-search" data-toggle="collapse" data-parent="#accordion" href="#packetStucollapseOne">查询条件
                                        </a>
                                    </h4>
                                </div>
                                <div id="packetStucollapseOne" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <form id="formSearch" class="form-horizontal" role="form">
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="orderId" class="control-label col-xs-2">订单编号:</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" id="orderId" name="orderId" style="width: 240px"/>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="payMode" class="control-label col-xs-2">支付类型:</label>
                                                    <div class="col-xs-10">
                                                        <select id="payMode" style="width: 240px" class="form-control">
                                                            <option value="">不限
                                                            <option value="1">预付
                                                            <option value="2">当面付
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate">创建时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="startDatePicker">
                                                                <input type="text" class="form-control" id="startDate"/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon-calendar glyphicon"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endDatePicker">
                                                                <input type="text" class="form-control" id="endDate"/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon-calendar glyphicon"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="updStartDate">状态更新时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="updStartDatePicker">
                                                                <input type="text" class="form-control" id="updStartDate"/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon-calendar glyphicon"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="updEndDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="updEndDatePicker">
                                                                <input type="text" class="form-control" id="updEndDate"/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon-calendar glyphicon"></span>
                                                                </span>
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
                    <div class="table-responsive">
                        <div class="panel-body" style="padding-bottom: 0px;">
                            <div class="btn-group">
                                <shiro:hasPermission name="143_QUERY">
                                    <button type="button" id="btn_query" class="btn btn-default">
                                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                    </button>
                                </shiro:hasPermission>
                            </div>
                            <table id="orderTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="./packetOrderDetail.jsp"></jsp:include>
<jsp:include page="./../../crowdsource/collegeDetail.jsp"></jsp:include>
<jsp:include page="./../../crowdsource/smallPieDetail.jsp"></jsp:include>