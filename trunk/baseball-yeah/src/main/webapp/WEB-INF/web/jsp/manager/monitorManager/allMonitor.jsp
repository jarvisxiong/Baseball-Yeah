<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="monitorModules">

<style type="text/css">
    .modal-dialog {
        width: 1000px;
    }

    .modal {
        left: 100px;
    }
</style>

<div id="page-wrapper" class="container">
    <div class="row">
        <form action="">
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
                                            <%--<h1>监控项维护</h1>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="panel-group" style="margin-bottom: 0px;">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a class="a-clear-search" id="clearSearch">
                                                        <span class="glyphicon glyphicon-refresh"
                                                              aria-hidden="true"></span>
                                                    </a>
                                                    <a class="accordion-toggle a-search" data-toggle="collapse"
                                                       data-parent="#accordion" href="#monitorManagercollapseOne"> 查询条件
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="monitorManagercollapseOne" class="panel-collapse collapse in">
                                                <div class="panel-body">

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="monitorName" class="control-label col-xs-2">监控名称:</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control"
                                                                       name="monitorName"
                                                                       style="width: 240px" id="monitorName"/>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </header>
                                <div class="table-responsive">
                                    <div class="panel-body" style="padding-bottom: 0px;">
                                        <div id="toolbar" class="btn-group">
                                            <shiro:hasPermission name="170_ADD">
                                                <button id="btn_add" type="button" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="170_EDIT">
                                                <button id="btn_edit" type="button" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="170_DELETE">
                                                <button id="btn_delete" type="button"
                                                        class="btn btn-default">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="170_QUERY">
                                                <button type="button" id="btn_query" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                                </button>
                                            </shiro:hasPermission>
                                        </div>
                                        <table id="monitorTable" class="table">
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</div>
<jsp:include page="addMonitor.jsp"></jsp:include>
<jsp:include page="editMonitor.jsp"></jsp:include>
