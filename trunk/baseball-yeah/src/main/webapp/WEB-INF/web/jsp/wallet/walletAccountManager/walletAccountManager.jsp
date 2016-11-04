<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<input type="hidden" class="inmodule" value="walletAccountManagerModules" />   
  <input id="acctId1" type="hidden" value=""/>
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
                                <%--<h1>钱包帐号管理</h1>--%>
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
                                           data-parent="#accordion" href="#walletAccountManagercollapse"> 查询条件
                                        </a>
                                    </h4>
                                </div>
                                <div id="walletAccountManagercollapse" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="userName" class="control-label col-xs-2">钱包帐号：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="userName" style="width: 240px"
                                                               class="form-control" placeholder="钱包帐号"/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">

                                                    <label for="activeState" class="control-label col-xs-2">帐号状态:</label>
                                                    <div class="col-xs-10">
                                                        <select id='activeState' style="width: 240px"
                                                                class="form-control" placeholder="帐号状态">
                                                            <option value="">全部</option>
                                                            <option value="1">激活</option>
                                                            <option value="0">未激活</option>
                                                            <option value="2">冻结</option>
                                                        </select>

                                                    </div>
                                                </div>
                                            </div>

                                            <div class="row">

                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate">激活时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker">
                                                                <input type="text" class="form-control" id="startdate"
                                                                       placeholder="开始日期" name="start" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker">
                                                                <input type="text" class="form-control" name="end"
                                                                       id="enddate" placeholder="结束日期" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">

                                                    <label for="state" class="control-label col-xs-2">锁定状态:</label>
                                                    <div class="col-xs-10">
                                                        <select id='state' style="width: 240px"
                                                                class="form-control" placeholder="锁定状态">
                                                            <option value="">全部</option>
                                                            <option value="1">已解锁</option>
                                                            <option value="0">已锁定</option>
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


                        <!--  <button id="btn_unlock" type="button"
                                 class="btn btn-default">
                                 <span class="glyphicon glyphicon-ok-circle"
                                       aria-hidden="true"></span>帐号解锁
                         </button> -->
                        <shiro:hasPermission name="147_ACCOUNTFREZE">
                            <button id="btn_lock" type="button"
                                    class="btn btn-default">
													<span class="glyphicon glyphicon-remove-circle"
                                                          aria-hidden="true"></span>帐号冻结
                            </button>
                            <button id="btn_unlock" type="button"
                                    class="btn btn-default">
													<span class="glyphicon glyphicon-ok-circle"
                                                          aria-hidden="true"></span>帐号解冻
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="147_QUERY">
                            <button type="button" id="btn_query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
								<%--<button id="btn_reset" type="button" class="btn btn-default">--%>
									<%--<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>重置--%>
								<%--</button>--%>
							</shiro:hasPermission>
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="walletAccountTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
 
<jsp:include page="walletAccount.jsp"></jsp:include>
<jsp:include page="capitalChange.jsp"></jsp:include> 