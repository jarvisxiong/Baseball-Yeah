<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%--
<input type="hidden" class="inmodule" value="packetUserModules">
--%>
<div class="row">
    <form action="" id="packeterForm">
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
                                                   data-parent="#accordion" href="#collapseOne"> 查询
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <form id="formSearch" class="form-horizontal" role="form">
                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="userName" class="col-xs-2">登录账户</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" class="form-control" id="userName"
                                                                       style='width: 250px' />
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="realName" class="col-xs-2">姓名</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" class="form-control" id="realName"
                                                                style='width: 250px' />
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="sex" class="col-xs-2">性别</label>
                                                            <div class="col-xs-4">
                                                                <select id="sex" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">请选择
                                                                    <option value="p_gender_female">女
                                                                    <option value="p_gender_male">男
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="zhiwu" class="col-xs-2">职务</label>
                                                            <div class="col-lg-4">
                                                                <select id="zhiwu" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">请选择
                                                                    <option value="2">校园COO
                                                                    <option value="3">校园CEO
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <div class="input-daterange">
                                                                <label for="registeStartTimePicker" class="col-xs-2">注册时间</label>
                                                                <div class="col-xs-4">
                                                                    <div class="input-group date" id="registeStartTimePicker">
                                                                        <input type="text" class="form-control"
                                                                               id="registeStartDate" placeholder="开始日期" >
                                                                        </span> <span class="input-group-addon"> <span
                                                                            class="glyphicon-calendar glyphicon"></span>
																		</span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <div class="input-group date" id="registeEndTimePicker">
                                                                        <input type="text" class="form-control"
                                                                               id="registeEndDate" placeholder="结束日期" >
                                                                        </span> <span class="input-group-addon"><span
                                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <%--<div class="form-group col-xs-6">
                                                            <label for="state" class="col-xs-2">账户状态</label>
                                                            <div class="col-lg-4">
                                                                <select id="state" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">请选择
                                                                    <option value="0">正常
                                                                    <option value="1">冻结
                                                                </select>
                                                            </div>
                                                        </div>--%>
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
                                        </shiro:hasPermission>
                                    </div>
                                    <div id="toolbar1" class="btn-group">
                                        <shiro:hasPermission name="140_QUERY">
                                            <button type="button" id="btn_assignOrder"
                                                    class="btn btn-default">
												<span class="glyphicon glyphicon-user"
                                                      aria-hidden="true"></span>派单
                                            </button>
                                            <button id="btn_delPuser" type="button"
                                                    class="btn btn-default">
                                            <span class="glyphicon glyphicon-refresh"
                                                  aria-hidden="true"></span>删除
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                    <table id="userTable" class="table">
                                    </table>
                                </div>
                                <table id="selectedPacketer" class="table">

                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<input type="hidden" id="collegeId" value="${collegeId}">
<input type="hidden" id="orderIds" value='${orderIds}'>
<jsp:include page="../../crowdsource/smallPieDetail.jsp"/>
<!--学校信息-->
<jsp:include page="../../crowdsource/collegeDetail.jsp"/>
<!--职务信息-->
<jsp:include page="../../officemanage/partInfo.jsp"/>