<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="auditUserModules">

<div class="row">


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
                                                   data-parent="#accordion" href="#allUsercollapseThree"> 查询条件
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="allUsercollapseThree" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <form id="formSearch" class="form-horizontal" role="form">
                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="realName" class="control-label col-xs-2">姓名:</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control" id="realName"
                                                                       style="width: 250px">
                                                            </div>
                                                        </div>

                                                        <div class="form-group col-xs-6">
                                                            <label for="phone" class="control-label col-xs-2">手机号:</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control" id="phone"
                                                                       style="width: 250px">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">


                                                        <div class="form-group col-xs-6">
                                                            <label for="selcollage" class="control-label col-xs-2">快递公司名称:</label>
                                                            <div class="col-xs-10">
                                                                <select id='selcollage' class="js-example-data-array"
                                                                        style="width: 250px"></select>
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
                                        <shiro:hasPermission name="7_AUDIT">
                                            <button id="btn_audit" type="button"
                                                    class="btn btn-default">
                                                <span class="glyphicon glyphicon-send" aria-hidden="true"></span>审核
                                            </button>
                                        </shiro:hasPermission>

                                        <shiro:hasPermission name="7_QUERY">
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

<jsp:include page="auditStoreUser.jsp"></jsp:include>