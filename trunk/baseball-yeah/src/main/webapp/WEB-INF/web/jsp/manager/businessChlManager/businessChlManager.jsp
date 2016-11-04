<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="row">
    <div class="row">
        <div class="col-lg-12">
            <div class="main-box clearfix">
                <div class="main-box-body clearfix">
                    <header class="main-box-header clearfix">
                        <%--<div class="row">--%>
                        <%--<div class="col-lg-12">--%>
                        <%--<ol class="breadcrumb">--%>
                        <%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
                        <%--<li class="active"><span>基础设置</span></li>--%>
                        <%--</ol>--%>
                        <%--<h1>商户渠道管理</h1>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="panel-group" style="padding-bottom: 0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a-clear-search" id="clearSearch"> <span
                                                class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                        </a> <a class="accordion-toggle a-search" data-toggle="collapse"
                                                href="#businessChlManager-collapseOne"> 查询条件 </a>
                                    </h4>
                                </div>
                                <div id="businessChlManager-collapseOne" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <form id="formSearch" class="form-horizontal">
                                            <div class="row">
                                                <div class="form-group  col-xs-6">
                                                    <label for="channelId" class="control-label col-xs-2">渠道ID</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" id="channelId"
                                                               style="width: 240px">
                                                    </div>
                                                </div>
                                                <div class="form-group  col-xs-6">
                                                    <label for="channelName" class="control-label col-xs-2">渠道名称</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" id="channelName"
                                                               style="width: 240px">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group  col-xs-6">
                                                    <label for="state" class="control-label col-xs-2">状态</label>
                                                    <div class="col-xs-10">
                                                        <select id="state" class="js-example-data-array form-control"
                                                                style="width: 240px">
                                                            <option value="">请选择</option>
                                                            <option value="1">启用</option>
                                                            <option value="0">禁用</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group  col-xs-6">
                                                    <label for="nickName" class="control-label col-xs-2">维护人</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" id="nickName"
                                                               style="width: 240px">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group  col-xs-6">
                                                    <label for="connectPhone"
                                                           class="control-label col-xs-2">联系电话</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" id="connectPhone"
                                                               style="width: 240px">
                                                    </div>
                                                </div>
                                                <div class="form-group  col-xs-6">
                                                    <label for="contacts" class="control-label col-xs-2">联系人</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" id="contacts"
                                                               style="width: 240px">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="createStartDatePicker" class="control-label col-xs-2">创建时间</label>
                                                    <div class="col-xs-4">
                                                        <div class="input-group date" id="createStartDatePicker">
                                                            <input type="text" class="form-control"
                                                                   name="createStartDate"
                                                                   id="createStartDate" placeholder="开始日期">
                                                            <span class="input-group-addon"> <span
                                                                    class="glyphicon-calendar glyphicon"></span>
														</span>
                                                        </div>
                                                    </div>
                                                    <label class="control-label col-xs-1" for="createEndDatePicker">至</label>
                                                    <div class="col-xs-4">
                                                        <div class="input-group date" id="createEndDatePicker">
                                                            <input type="text" class="form-control" id="createEndDate"
                                                                   placeholder="结束时间"><span
                                                                class="input-group-addon"><span
                                                                class="glyphicon-calendar glyphicon"></span> </span>
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
                            <div id="toolbar" class="btn-group">
                                <shiro:hasPermission name="173_ADD">
                                    <button id="btn_add" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="173_DELETE">
                                    <button id="btn_delete" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="173_QUERY">
                                    <button type="button" id="btn_query" class="btn btn-default">
                                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                    </button>
                                </shiro:hasPermission>
                                <%--<shiro:hasPermission name="8_QUERY">--%>
                                <%--<button id="btn_reset" type="button" class="btn btn-default">--%>
                                <%--<span class="glyphicon glyphicon-refresh"--%>
                                <%--aria-hidden="true"></span>重置--%>
                                <%--</button>--%>
                                <%--</shiro:hasPermission>--%>
                            </div>
                            <table id="userTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" class="inmodule" value="storeChannelManagerModules">
<jsp:include page="addBusinessChl.jsp"></jsp:include>
