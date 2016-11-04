<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="orderManagerModules">
<div class="row">
    <form action="">
        <div class="row">
            <div class="row">
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
                                                            <label for="orderId" class="col-xs-2">订单编号</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" class="form-control" id="orderId"
                                                                       style='width: 250px' />
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="orderState" class="col-xs-2">订单状态</label>
                                                            <div class="col-xs-4">
                                                                <select id="orderState" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">请选择
                                                                    <option value="1">待接单
                                                                    <option value="100">创建
                                                                    <option value="2">已接单
                                                                    <option value="3">配送中
                                                                    <option value="5">已完成
                                                                    <option value="6">已取消
                                                                    <option value="7">异常
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="createStartTimePicker" class="col-xs-2">创建时间</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="createStartTimePicker">
                                                                    <input type="text" class="form-control"
                                                                           id="createStartDate" placeholder="开始日期" >

                                                                    </span> <span class="input-group-addon"> <span
                                                                        class="glyphicon-calendar glyphicon"></span>
																		</span>
                                                                </div>
                                                            </div>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="createEndTimePicker">
                                                                    <input type="text" class="form-control"
                                                                           id="createEndDate" placeholder="结束日期" >

                                                                    </span> <span class="input-group-addon"><span
                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="payModel" class="col-xs-2">支付类型</label>
                                                            <div class="col-lg-4">
                                                                <select id="payModel" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">请选择
                                                                    <option value="2">当面付
                                                                    <option value="1">预付
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <div class="input-daterange">
                                                                <label for="updateStartTimePicker" class="col-xs-2">状态更新时间</label>
                                                                <div class="col-xs-4">
                                                                    <div class="input-group date" id="updateStartTimePicker">
                                                                        <input type="text" class="form-control"
                                                                               id="updateStartDate" placeholder="开始日期" >

                                                                        </span> <span class="input-group-addon"> <span
                                                                            class="glyphicon-calendar glyphicon"></span>
																		</span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <div class="input-group date" id="updateEndTimePicker">
                                                                        <input type="text" class="form-control"
                                                                               id="updateEndDate" placeholder="结束日期" >

                                                                        </span> <span class="input-group-addon"><span
                                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="winnerMobile" class="col-xs-2">小派手机号</label>
                                                            <div class="col-xs-2">
                                                                <input type="text" id="winnerMobile" class="form-control"
                                                                       style='width: 250px'>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="createUserMobile" class="col-xs-2">下单人手机号</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" id="createUserMobile" class="form-control"
                                                                       style='width: 250px'>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="selcollage" class="col-xs-2">校区</label>
                                                            <div class="col-lg-4">
                                                                <select id='selcollage' class="js-example-data-array"
                                                                        style="width: 240px"></select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="payType" class="col-xs-2">支付方式</label>
                                                            <div class="col-lg-4">
                                                                <select id="payType" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">请选择
                                                                    <option value="1">微信
                                                                    <option value="2">支付宝
                                                                    <option value="4">现金
                                                                    <option value="3">指端支付
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="orderType" class="col-xs-2">订单类型</label>
                                                            <div class="col-lg-4">
                                                                <select id="orderType" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">请选择
                                                                    <option value="1">代取
                                                                    <option value="2">代寄
                                                                    <option value="3">代跑腿
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
                            <div class="table-responsive">

                                <div class="panel-body" style="padding-bottom: 0px;">

                                    <div id="toolbar" class="btn-group">

                                        <%--<shiro:hasPermission name="140_AUDIT">
                                            <button id="btn_audit" type="button"
                                                    class="btn btn-default">
                                                <span class="glyphicon glyphicon-send" aria-hidden="true"></span>导出
                                            </button>
                                        </shiro:hasPermission>--%>

                                        <shiro:hasPermission name="140_QUERY">
                                            <button type="button" id="btn_query"
                                                    class="btn btn-default">
												<span class="glyphicon glyphicon-search"
                                                      aria-hidden="true"></span>查询
                                            </button>
                                            <button id="btn_reset" type="reset"
                                            class="btn btn-default">
                                            <span class="glyphicon glyphicon-refresh"
                                            aria-hidden="true"></span>清空
                                            </button>
                                            <button id="btn_statistic" type="button"
                                                    class="btn btn-default">
                                            <span class="glyphicon glyphicon-eye-open"
                                                  aria-hidden="true"></span>统计
                                            </button>
                                            <button id="btn_assign" type="button"
                                                    class="btn btn-default">
                                            <span class="glyphicon glyphicon-user"
                                                  aria-hidden="true"></span>派单
                                            </button>
                                            <button id="btn_cancel" type="button"
                                                    class="btn btn-default">
                                            <span class="glyphicon glyphicon-remove"
                                                  aria-hidden="true"></span>取消订单
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                    <table id="userTable" class="table">

                                    </table>
                                    <table id="showAmount" style="display:none;">
                                        <tr>
                                            <th style="width: 1296px; " data-field="0" tabindex="0" colspan="11">
                                                <div class="th-inner " align="right">合计：</div>
                                            </th>
                                            <th style="width: 131px; " data-field="rewardMoney" tabindex="0">
                                                <div class="th-inner " align="right"id="rMoney"></div>
                                            </th>
                                            <th style="width: 131px; " data-field="rebateMoney" tabindex="0">
                                                <div class="th-inner "align="right" id="dMoney"></div>
                                            </th>
                                            <th style="width: 131px; " data-field="finalMoney" tabindex="0">
                                                <div class="th-inner "align="right" id="fMoney"></div>
                                            </th>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!--订单详情-->
<jsp:include page="detail.jsp"/>
<!--取消订单-->
<jsp:include page="cancelOrder.jsp"/>
<!--学校信息-->
<jsp:include page="../../crowdsource/collegeDetail.jsp"/>
<!--小派信息-->
<jsp:include page="../../crowdsource/smallPieDetail.jsp"/>
<!--订单详情-->
<jsp:include page="../packetOrder/packetOrderDetail.jsp"/>
