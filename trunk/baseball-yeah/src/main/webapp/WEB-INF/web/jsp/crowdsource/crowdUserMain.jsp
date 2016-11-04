<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="crowdUserMainModules" />
<input type="hidden" class="inmodule" value="${userId}" id="crowdUserId">
<input type="hidden" class="inmodule" value="${searchType}" id="crowdSearchType">
<input type="hidden" class="inmodule" value="${collegeId}" id="crowdCollegeId">
<input type="hidden" class="inmodule" value="${collegeName}" id="crowdCollegeName">
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
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="loginAccount" class="control-label col-xs-2">登录账号：</label>
                                            <div class="col-xs-10">
                                                <input type="text" class="form-control" name="loginAccount" style="width: 240px" id="loginAccount">
                                            </div>
                                        </div>

                                        <div class="form-group col-xs-6">
                                            <label for="userName" class="control-label col-xs-2">姓名：</label>
                                            <div class="col-xs-10">
                                                <input type="text" class="form-control" name="userName" style="width: 240px" id="userName">
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="sex" class="control-label col-xs-2">性别：</label>
                                            <div class="col-xs-10">
                                                <select id="sex" name="sex" class="js-example-data-array  form-control" style='width: 250px'>
                                                    <option value="">请选择</option>
                                                    <option value="p_gender_male">男</option>
                                                    <option value="p_gender_female">女</option>
                                                    <option value="p_gender_secret">保密</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group col-xs-6">
                                            <label for="collegeId" class="col-xs-2">校区：</label>
                                            <div class="col-xs-4" id="collegeTitle">
                                                    <select id="collegeId" class="js-example-data-array" style='width: 250px'>
                                                    </select>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="userState" class="control-label col-xs-2">账户状态：</label>
                                            <div class="col-xs-10">
                                                <select id="userState" name="userState" class="js-example-data-array  form-control" style='width: 250px'>
                                                    <option value="">请选择</option>
                                                    <option value="1">启用</option>
                                                    <option value="0">禁用</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label for="registerStartTimePicker" class="col-xs-2">注册时间：</label>
                                            <div class="col-xs-4">
                                                <div class="input-group date" id="registerStartTimePicker">
                                                    <input type="text" class="form-control" name="registerStartTime" id="registerStartTime" placeholder="起始日期" ></input>
                                                    <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
                                                </div>
                                            </div>

                                            <div class="col-xs-4">
                                                <div class="input-group date" id="registerEndTimePicker">
                                                    <input type="text" class="form-control" name="registerEndTime" id="registerEndTime" placeholder="截止日期" ></input>
                                                    <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </header>

                <%-- Buttons and data start --%>
                <div class="table-responsive">
                    <div class="panel-body" style="padding-bottom: 0px;">
                        <div id="toolbar" class="btn-group">
                            <shiro:hasPermission name="143_QUERY">
                                <button type="button" id="btn_query" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="143_EDIT">
                                <button id="btn_edit" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-pencil"
                                                              aria-hidden="true"></span>修改
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="143_ACTIVATION">
                                <button type="button" id="btn_enable" class="btn btn-default">
                                    <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>启用
                                </button>
                            </shiro:hasPermission>

                            <shiro:hasPermission name="143_SEALED">
                                <button type="button" id="btn_disable" class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>禁用
                                </button>
                            </shiro:hasPermission>
                        </div>
                        <table id="crowdUserTable" class="table"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="smallPieDetail.jsp"></jsp:include>
<jsp:include page="collegeDetail.jsp"></jsp:include>
<jsp:include page="editUser.jsp"></jsp:include>
