<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="smsVendorReconModules">

<div class="row">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <div class="main-box-body clearfix">

                <header class="main-box-header clearfix">
                    <%--<div class="row">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<ol class="breadcrumb">--%>
                                <%--<li><a href="${pageContext.request.contextPath }/user/gotoIndex">首页</a></li>--%>
                                <%--<li class="active"><span>客服管理</span></li>--%>
                            <%--</ol>--%>
                            <%--<h1>短信服务商对账</h1>--%>
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
                                       data-parent="#accordion" href="#smscollapseOne"> 查询条件
                                    </a>
                                </h4>
                            </div>

                            <div id="smscollapseOne" class="panel-collapse collapse in">
                                <div class="panel-body">
                                    <form id="formSearch" class="form-horizontal" role="form">
                                        <div class="row">
                                            <div class="form-group col-xs-6">
                                                <label for="startdate" class="col-xs-2">日期：</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="starttimePicker">
                                                        <input type="text" class="form-control" name="start"
                                                               id="startdate" placeholder="开始日期">
												<span class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>

                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="endtimePicker">
                                                        <input type="text" class="form-control" name="end"
                                                               id="enddate" placeholder="结束日期"><span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group col-xs-6">
                                                <label for="selsmsSumMethod" class="col-xs-2">统计方式：</label>
                                                <div class="col-xs-10">
                                                    <select id="selsmsSumMethod" class="js-example-data-array" style='width: 250px'>
                                                    </select>
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
                            <button type="button" id="btn_query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
                            <%--<button id="btn_reset" type="button" class="btn btn-default">--%>
                            <%--<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>清空--%>
                            <%--</button>--%>
                        </div>
                        <table id="userTable" class="table"></table>
                    </div>
                </div>
                <%-- Buttons and data end --%>
            </div>
        </div>
    </div>
</div>
<style>
    .login-dialog .modal-dialog {
        width: 900px;
    }
</style>