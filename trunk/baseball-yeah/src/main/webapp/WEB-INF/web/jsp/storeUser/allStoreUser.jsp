<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="storeUserModules">
<div class="row">
    <div class="row">
        <div class="col-lg-12">
            <div class="main-box clearfix">

                <div class="main-box-body clearfix">
                    <header class="main-box-header clearfix">

                        <div class="panel-group" style="margin-bottom: 0px;"
                             id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a-clear-search" id="clearSearch">
                                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                        </a>
                                        <a class="accordion-toggle a-search" data-toggle="collapse"
                                           data-parent="#accordion" href="#storeUsercollapse1"> 查询条件
                                        </a>
                                    </h4>
                                </div>
                                <div id="storeUsercollapse1" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <form id="formSearch" class="form-horizontal" role="form">
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="storeName" class="control-label col-xs-2">站点名称:</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control"
                                                               name="storeName" style="width: 240px" id="storeName"/>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="selstore" class="control-label col-xs-2">站点编号:</label>
                                                    <div class="col-xs-10">
                                                        <select id='selstore' style="width: 240px"
                                                                class="js-example-data-array form-control">

                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="idNo" class="control-label col-xs-2">身份证号码:</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" name="idNo"
                                                               style="width: 240px" id="idNo"/>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="expressId" class="control-label col-xs-2">快递公司名称:</label>
                                                    <div class="col-xs-10">
                                                        <select id='expressId' style="width: 240px"
                                                                class="js-example-data-array form-control">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="phone" class="control-label col-xs-2">手机号码:</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control" name="phone"
                                                               style="width: 240px" id="phone"/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <label for="beSupervisor"
                                                           class="control-label col-xs-2">角色:</label>
                                                    <div class="col-xs-10">
                                                        <select id='beSupervisor' style="width: 240px"
                                                                class="form-control">
                                                            <option value="">全部</option>
                                                            <option value="0">员工</option>
                                                            <option value="1">负责人</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate">注册日期:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker">
                                                                <input type="text" class="form-control"
                                                                       id="startDate"></input><span class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker">
                                                                <input type="text" class="form-control" id="endDate"
                                                                ></input><span class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="verifyStatus"
                                                           class="control-label col-xs-2">审核状态:</label>
                                                    <div class="col-xs-10">
                                                        <select id='verifyStatus' style="width: 240px"
                                                                class="form-control">
                                                            <option value="">全部</option>
                                                            <option value="0">未完善</option>
                                                            <option value="1">审核中</option>
                                                            <option value="2">审核通过</option>
                                                            <option value="3">审核失败</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="selcity" class="control-label col-xs-2">城市:</label>
                                                    <div class="col-xs-10">
                                                        <select id='selcity' style="width: 240px"
                                                                class="form-control">
                                                            <option value="">全部</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <label for="realName" class="control-label col-xs-2">真实姓名:</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" class="form-control"
                                                               name="realName" style="width: 240px" id="realName"/>
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
                                <shiro:hasPermission name="6_ADD">
                                    <button id="btn_add" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="6_EDIT">
                                    <button id="btn_edit" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-pencil"
                                                              aria-hidden="true"></span>修改
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="6_DELETE">
                                    <button id="btn_delete" type="button"
                                            class="btn btn-default">
														<span class="glyphicon glyphicon-remove"
                                                              aria-hidden="true"></span>删除
                                    </button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="6_INITPWD">
                                    <button id="btn_initPwd" type="button"
                                            class="btn btn-default">
														<span class="glyphicon glyphicon-random"
                                                              aria-hidden="true"></span>初始化密码
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="6_QUERY">
                                    <button type="button" id="btn_query"
                                            class="btn btn-default">
														<span class="glyphicon glyphicon-search"
                                                              aria-hidden="true"></span>查询
                                    </button>
                                    <%--<button id="btn_reset" type="button"--%>
                                    <%--class="btn btn-default">--%>
                                    <%--<span class="glyphicon glyphicon-refresh"--%>
                                    <%--aria-hidden="true"></span>重置--%>
                                    <%--</button>--%>
                                </shiro:hasPermission>
                            </div>
                            <div>
                                <table id="userTable" class="table">

                                </table>
                            </div>
                        </div>
                    </div>
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
<jsp:include page="addStoreUser.jsp"></jsp:include>
<jsp:include page="editStoreUser.jsp"></jsp:include>
<jsp:include page="messageTemplate.jsp"></jsp:include>
<jsp:include page="sms.jsp"></jsp:include>
<jsp:include page="auditStoreUser.jsp"></jsp:include>
