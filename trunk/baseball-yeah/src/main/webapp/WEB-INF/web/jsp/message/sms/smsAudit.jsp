<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="smsAudit">

<div class="row">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <div class="main-box-body clearfix">

                <header class="main-box-header clearfix">
                    <%--<div class="row">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<ol class="breadcrumb">--%>
                                <%--<li><a href="${pageContext.request.contextPath }/user/gotoIndex">首页</a></li>--%>
                                <%--<li class="active"><span>基础设置</span></li>--%>
                            <%--</ol>--%>
                            <%--<h1>短信模板审核</h1>--%>
                        <%--</div>--%>
                    <%--</div>--%>

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
                                                <label for="createStartTime" class="control-label col-xs-2">提交日期：</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="createStartTimePicker">
                                                        <input type="text" class="form-control" name="createStartTime"
                                                               id="createStartTime" placeholder="提交开始日期">
												            <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
                                                    </div>
                                                </div>
                                                <label class="control-label col-xs-1" for="createEndTime">至</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="createEndTimePicker">
                                                        <input type="text" class="form-control" name="createEndTime"
                                                               id="createEndTime" placeholder="提交截止日期"><span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-6">
                                                <label for="stateNum" class="control-label col-xs-2">审核状态：</label>
                                                <div class="col-xs-10">
                                                    <select id="stateNum" name="stateNum" class="js-example-data-array  form-control" style='width: 250px'>
                                                        <option value="">请选择</option>
                                                        <option value="1">审核通过</option>
                                                        <option value="2" selected>审核中</option>
                                                        <option value="3">审核不通过</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-xs-6">
                                                <label for="auditStartTime" class="control-label col-xs-2">审核日期：</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="auditStartTimePicker">
                                                        <input type="text" class="form-control" name="auditStartTime"
                                                               id="auditStartTime" placeholder="审核开始日期">
												        <span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>
                                                <label class="control-label col-xs-1" for="auditEndTime">至</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="auditEndTimePicker">
                                                        <input type="text" class="form-control" name="auditEndTime"
                                                               id="auditEndTime" placeholder="审核截止日期"><span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-6">
                                                <label for="collegeId" class="control-label col-xs-2">校区名称：</label>
                                                <div class="col-xs-10">
                                                    <select id='collegeId' name="collegeId"
                                                            class="form-control" style="width: 240px">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>


                                        <div class="row">
                                            <div class="form-group col-xs-6">
                                                <label for="modelContent" class="control-label col-xs-2">内容检索：</label>
                                                <div class="col-xs-10">
                                                    <input type="text" class="form-control" name="modelContent"
                                                           style="width: 240px" id="modelContent">
                                                </div>
                                            </div>
                                            <div class="form-group col-xs-6">
                                                <label for="createUserPhone" class="control-label col-xs-2">创建人手机号码：</label>
                                                <div class="col-xs-10">
                                                    <input type="text" class="form-control" name="createUserPhone"
                                                           style="width: 240px" id="createUserPhone">
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

                            <%--<button id="btn_clear" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>清空
                            </button>--%>
                            <shiro:hasPermission name="182_AUDIT">
                                <button id="btn_pass" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>通过
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="182_AUDIT">
                                <button id="btn_Refuse" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>驳回
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="182_DELETE">
                                <button id="btn_del" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="182_QUERY">
                                <button type="button" id="btn_query" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                </button>
                            </shiro:hasPermission>
                        </div>
                        <table id="templetTable" class="table"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="smsAuditDetail.jsp"></jsp:include>
<style>
    .login-dialog .modal-dialog {
        width: 900px;
    }
</style>