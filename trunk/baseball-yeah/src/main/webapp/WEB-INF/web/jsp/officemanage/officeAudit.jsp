<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="officeAuditModules">
<div class="row">
    <div class="row">
        <div class="col-lg-12">
            <div class="main-box clearfix">
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
                                       data-parent="#accordion" href="#officeCollapse"> 查询
                                    </a>
                                </h4>
                            </div>
                            <div id="officeCollapse" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="username" class="control-label col-xs-2">登录帐号：</label>
                                            <div class="col-xs-10">
                                                <input type="text" id="username" style="width: 240px"
                                                       class="form-control"/>
                                            </div>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label for="realName" class="control-label col-xs-2">姓名:</label>
                                            <div class="col-xs-10">
                                                <input type="text" id="realName" style="width: 240px"
                                                       class="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="sex" class="control-label col-xs-2">性别:</label>
                                            <div class="col-xs-10">
                                                <select id='sex' style="width: 240px"
                                                        class="form-control">
                                                    <option value="">请选择</option>
                                                    <option value="p_gender_male">男</option>
                                                    <option value="p_gender_female">女</option>
                                                    <option value="p_gender_secret">保密</option>
                                                </select>

                                            </div>
                                        </div>

                                        <div class="form-group col-xs-6">
                                            <label for="collegeArea" class="control-label col-xs-2">校区：</label>
                                            <div class="col-xs-10">
                                                <select id="collegeArea" class="js-example-data-array"
                                                        style="width: 240px" name="collegeArea">
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="office" class="control-label col-xs-2">职务:</label>
                                            <div class="col-xs-10">
                                                <select id='office' style="width: 240px" class="form-control">
                                                    <option value="">请选择</option>
                                                    <option value="2">校园COO</option>
                                                    <option value="1">校园CEO</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <label for="auditor" class="control-label col-xs-2">审核人：</label>
                                            <div class="col-xs-10">
                                                <input type="text" id="auditor" style="width: 240px" class="form-control"/>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="auditState" class="control-label col-xs-2">审核状态:</label>
                                            <div class="col-xs-10">
                                                <select id='auditState' style="width: 240px" class="form-control">
                                                    <option value="">全部</option>
                                                    <option value="0" selected="selected">待审核</option>
                                                    <option value="2">审核拒绝</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <div class="input-daterange">
                                                <label class="control-label col-xs-2" for="startDate">注册时间:</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="starttimePicker">
                                                        <input type="text" class="form-control" id="startDate" placeholder="开始日期" name="start"></input>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon-calendar glyphicon"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                                <label class="control-label col-xs-1" for="endDate">至</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="endtimePicker">
                                                        <input type="text" class="form-control" name="end" id="endDate" placeholder="结束日期"></input>
                                                        <span class="input-group-addon">
                                                            <span class="glyphicon-calendar glyphicon"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="btn-group">
                        <shiro:hasPermission name="194_QUERY">
                            <button type="button" id="btn_query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
                            <button id="btn_approve" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>通过
                            </button>
                            <button id="btn_refuse" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>驳回
                            </button>
                        </shiro:hasPermission>
                    </div>
                </header>
                <div class="main-box-body clearfix">
                    <div class="row">
                        <table id="officeAuditTable" class="table">

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="./../crowdsource/smallPieDetail.jsp"></jsp:include>
<jsp:include page="./../crowdsource/collegeDetail.jsp"></jsp:include>
<jsp:include page="partInfo.jsp"></jsp:include>