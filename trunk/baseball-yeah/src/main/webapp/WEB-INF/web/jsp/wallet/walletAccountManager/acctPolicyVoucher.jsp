<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<input type="hidden" class="inmodule" value="acctPolicyVoucherModules" />    
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                        <%--<div class="row">--%>
                            <%--<div class="col-lg-12">--%>
                                <%--<ol class="breadcrumb">--%>
                                    <%--<li><a--%>
                                            <%--href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
                                    <%--<li class="active"><span>钱包</span></li>--%>
                                <%--</ol>--%>
                                <%--<h1>代金券管理</h1>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="panel-group" style="margin-bottom: 0px;"
                             id="accordion">
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
                                                    <label for="activityId" class="control-label col-xs-2">活动名称:</label>
                                                    <div class="col-xs-10">
                                                        <select id='activityId' class="js-example-data-array"
                                                                style="width: 250px"></select>
                                                    </div>
                                                </div>
                                               <div class="form-group col-xs-6">
                                                    <label for="policyId" class="control-label col-xs-2">策略名称:</label>
                                                    <div class="col-xs-10">
                                                        <select id='policyId' class="js-example-data-array"
                                                                style="width: 250px"></select>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">

                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate">领取时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker">
                                                                <input type="text" class="form-control" id="startdate1"
                                                                       placeholder="开始日期" name="start" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker">
                                                                <input type="text" class="form-control" name="end"
                                                                       id="enddate1" placeholder="结束日期" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                 <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate"> 使用时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker2">
                                                                <input type="text" class="form-control" id="startdate2"
                                                                       placeholder="开始日期" name="start" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker2">
                                                                <input type="text" class="form-control" name="end"
                                                                       id="enddate2" placeholder="结束日期" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">

                                                    <label for="state" class="control-label col-xs-2">状态:</label>
                                                    <div class="col-xs-10">
                                                        <select id='state' style="width: 240px"
                                                                class="form-control" placeholder="状态">
                                                            <option value="">全部</option>
                                                            <option value="0">已失效</option>
                                                            <option value="1">已领取</option>
                                                            <option value="2">已使用</option>
                                                            <option value="3">已生成</option>
                                                        </select>

                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div id="toolbar" class="btn-group">
                        <%-- <shiro:hasPermission name="147_QUERY"> --%>
                            <button type="button" id="btn_query" class="btn btn-default" style="margin-left: 15px;">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
						<%-- 	</shiro:hasPermission> --%>
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="voucherTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 
