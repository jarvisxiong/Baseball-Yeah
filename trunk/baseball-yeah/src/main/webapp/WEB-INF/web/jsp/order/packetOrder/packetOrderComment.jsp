<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="packetOrderCommentModules">
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
                                        <a class="a-clear-search" id="clearCommentSearch">
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
                                                    <label for="customerPhone" class="control-label col-xs-2">下单人手机号:</label>
                                                    <div class="col-xs-5">
                                                        <input type="text" class="form-control" id="customerPhone" name="customerPhone" style="width: 240px"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="commentStartDate">评论时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="commentStartDatePicker">
                                                                <input type="text" class="form-control" id="commentStartDate"/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon-calendar glyphicon"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="commentEndDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="commentEndDatePicker">
                                                                <input type="text" class="form-control" id="commentEndDate"/>
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon-calendar glyphicon"></span>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="score" class="control-label col-xs-2">评分星级:</label>
                                                    <div class="col-xs-10">
                                                        <select id="score" style="width: 240px" class="form-control">
                                                            <option value="">不限
                                                            <option value="1">1星
                                                            <option value="2">2星
                                                            <option value="3">3星
                                                            <option value="4">4星
                                                            <option value="5">5星
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
                            <div class="btn-group">
                                <shiro:hasPermission name="143_QUERY">
                                    <button type="button" id="btnCommentQuery" class="btn btn-default">
                                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                    </button>
                                </shiro:hasPermission>
                            </div>
                            <table id="commentTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<form id="auditForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="commentModal" role="dialog" aria-labelledby="commentModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="commentModalLabel">评价详情</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">订单号：</label>
                        <label class="col-lg-8 control-label label-operation" id="mOrderId" style="text-align: left"></label>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">评分：</label>
                        <label class="col-lg-8 control-label label-operation">
                            <div style="color:#FABA03;text-align: left" id="divScore">
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                        </label>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">评语：</label>
                        <div class="col-lg-8 div-operation">
                            <textarea class="form-control" readonly id="mComment" rows="8"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">评图：</label>
                        <div class="col-lg-8 div-operation">
                            <div id="imgList" class="mydivdrop">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
<jsp:include page="./packetOrderDetail.jsp"></jsp:include>
<jsp:include page="./../../crowdsource/smallPieDetail.jsp"></jsp:include>