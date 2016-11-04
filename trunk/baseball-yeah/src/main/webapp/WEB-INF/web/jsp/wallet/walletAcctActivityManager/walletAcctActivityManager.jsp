<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="acctActivityModules"/>

<div class="row">
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <div class="main-box-body clearfix">
                        <header class="main-box-header clearfix">
                            <%--<div class="row">--%>
                                <%--<div class="col-lg-12">--%>
                                    <%--<ol class="breadcrumb">--%>
                                        <%--<li><a--%>
                                                <%--href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
                                        <%--<li class="active"><span>钱包管理</span></li>--%>
                                    <%--</ol>--%>
                                    <%--<h1>营销活动管理</h1>--%>
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
                                               data-parent="#accordion" href="#walletAcctActivityManagercollapseThree1"> 查询条件
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="walletAcctActivityManagercollapseThree1" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <form id="formSearch" class="form-horizontal" role="form">
                                                <div class="row">
                                                    <div class="form-group col-xs-6">
                                                        <label for="activityName"
                                                               class="control-label col-xs-2">活动名称：</label>
                                                        <div class="col-xs-10">
                                                            <input type="text" class="form-control" id="activityName"
                                                                   style="width: 250px">
                                                        </div>
                                                    </div>

                                                    <div class="form-group col-xs-6">
                                                        <label for="provinceId" class="control-label col-xs-2">省份：</label>
                                                        <div class="col-xs-2">
                                                            <select id="provinceId" class="js-example-data-array"
                                                                    style='width: 250px'></select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="form-group col-xs-6">
                                                        <div class="input-daterange">
                                                            <label for="submit_startdate"
                                                                   class="control-label col-xs-2">活动开始时间：</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="starttimePicker">
                                                                    <input type="text" class="form-control"
                                                                           id="submit_startdate"
                                                                           placeholder="开始日期"></input>

                                                                    </span> <span class="input-group-addon"> <span
                                                                        class="glyphicon-calendar glyphicon"></span>
																				</span>
                                                                </div>
                                                            </div>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="endtimePicker">
                                                                    <input type="text" class="form-control"
                                                                           id="submit_enddate"
                                                                           placeholder="结束日期"></input>

                                                                    </span> <span class="input-group-addon"><span
                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                                </div>
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
                                    <shiro:hasPermission name="160_ADD">
                                        <button id="btn_add" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-pencil"
                                                              aria-hidden="true"></span>新增
                                        </button>
                                        <%--<a id="btn_add" type="button" href="/wallet/gotoAddWalletAcctActivityManager"
                                           class="btn btn-default">
                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                        </a>--%>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="160_EDIT">
                                        <button id="btn_edit" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-pencil"
                                                              aria-hidden="true"></span>修改
                                        </button>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="160_DELETE">
                                        <button id="btn_delete" type="button"
                                                class="btn btn-default">
														<span class="glyphicon glyphicon-remove"
                                                              aria-hidden="true"></span>删除
                                        </button>
                                    </shiro:hasPermission>


                                    <shiro:hasPermission name="160_QUERY">
                                        <button type="button" id="btn_query"
                                                class="btn btn-default">
														<span class="glyphicon glyphicon-search"
                                                              aria-hidden="true"></span>查询
                                        </button>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="160_GENERATE">
                                        <button type="button" id="btn_generate"
                                                class="btn btn-default">
														<span class="glyphicon glyphicon-check"
                                                              aria-hidden="true"></span>生成代金券
                                        </button>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="160_ENABLE">
                                        <button type="button" id="btn_enable"
                                                class="btn btn-default">
														<span class="glyphicon glyphicon-ok-circle"
                                                              aria-hidden="true"></span>启用
                                        </button>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="160_FROZEN">
                                        <button type="button" id="btn_frozen"
                                                class="btn btn-default">
														<span class=" glyphicon glyphicon-remove-sign"
                                                              aria-hidden="true"></span>冻结
                                        </button>
                                    </shiro:hasPermission>

                                    </button>
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
</div>
 

