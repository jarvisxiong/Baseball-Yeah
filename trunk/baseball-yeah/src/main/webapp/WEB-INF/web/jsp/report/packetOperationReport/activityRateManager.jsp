<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="main-box clearfix">
    <div class="main-box-body clearfix">
        <header class="row main-box-header clearfix">
            <div class="panel-group" style="margin-bottom: 0px;">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="a-clear-search" id="clearSearch"> <span
                                    class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                            </a> <a class="accordion-toggle a-search" data-toggle="collapse"
                                    href="#activityRateManager-collapseOne"> 查询条件 </a>
                        </h4>
                    </div>
                    <div id="activityRateManager-collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <form id="formSearch" class="form-horizontal" role="form">
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="monthSelect" class="control-label col-xs-2">月份：</label>
                                        <div class="control-label col-xs-4">
                                            <div class="input-group date" id="monthSelectPicker">
                                                <input type="text" class="form-control"
                                                       name="createStartDate"
                                                       id="monthSelect" placeholder="选择月份">
                                                <span class="input-group-addon"> <span
                                                        class="glyphicon-calendar glyphicon"></span>
														</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-xs-6">
                                        <label for="campus" class="control-label col-xs-2">校区：</label>
                                        <div class="control-label col-xs-10" style="text-align: left;">
                                            <select style="width: 450px" id="campus" multiple>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group col-xs-6">
                                        <label for="startDate" class="control-label col-xs-2">日期：</label>
                                        <div class="control-label col-xs-4">
                                            <div class="input-group date" id="startDatePicker">
                                                <input type="text" class="form-control"
                                                       name="createStartDate"
                                                       id="startDate" placeholder="开始日期">
                                                <span class="input-group-addon"> <span
                                                        class="glyphicon-calendar glyphicon"></span>
														</span>
                                            </div>
                                        </div>
                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                        <div class="control-label col-xs-4">
                                            <div class="input-group date" id="endDatePicker">
                                                <input type="text" class="form-control" id="endDate"
                                                       placeholder="结束日期"><span
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
        <div class="row table-responsive">
            <div class="panel-body" style="padding-bottom: 0px;">
                <div id="toolbar" class="btn-group">
                    <button id="query" class="btn btn-default">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                    </button>
                </div>
                <table id="activityTable" data-show-export="true">

                </table>
            </div>
        </div>
    </div>
</div>
<input type="hidden" class="inmodule" value="activityRateManagerModules">