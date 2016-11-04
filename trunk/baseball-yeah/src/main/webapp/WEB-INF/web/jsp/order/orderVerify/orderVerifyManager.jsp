<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="auditOrderVerifyModules">

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
                                <%--<li><a--%>
                                <%--href="${pageContext.request.contextPath }/user/gotoIndex">首页</a></li>--%>
                                <%--<li class="active"><span>用户管理</span></li>--%>
                                <%--</ol>--%>
                                <%--<h1>众包用户审核</h1>--%>
                                <%--</div>--%>
                                <%--</div>--%>

                                <div class="panel-group" style="margin-bottom: 0px;">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="a-clear-search" id="clearSearch">
                                                    <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                                </a>
                                                <a class="accordion-toggle a-search" data-toggle="collapse"
                                                   data-parent="#accordion" href="#orderVerifycollapseOne"> 查询条件
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="orderVerifycollapseOne" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <form id="formSearch" class="form-horizontal" role="form">
                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="userName" class="col-xs-2">登录账号</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" class="form-control" id="userName"
                                                                       style='width: 250px'/>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="realName" class="col-xs-2">姓名</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" class="form-control" id="realName">
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="gender" class="col-xs-2">性别</label>
                                                            <div class="col-xs-4">
                                                                <select id="gender" class="form-control"
                                                                        style='width: 250px'>
                                                                    <option value="">不限
                                                                    <option value="p_gender_male">男
                                                                    <option value="p_gender_female">女
                                                                    <option value="p_gender_secret">保密
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="selcollage" class="col-xs-2">校区</label>
                                                            <div class="col-lg-4">
                                                                <select id='selcollage' class="js-example-data-array" style="width: 240px"></select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="selcity" class="col-xs-2">城市</label>
                                                            <div class="col-xs-2">
                                                                <select id="selcity" class="js-example-data-array"
                                                                        style='width: 250px'></select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <div class="input-daterange">
                                                                <label for="selcollage" class="col-xs-2">提交时间</label>
                                                                <div class="col-xs-4">
                                                                    <div class="input-group date" id="starttimePicker">
                                                                        <input type="text" class="form-control"
                                                                               id="submit_startdate" placeholder="开始日期"></input>

                                                                        </span> <span class="input-group-addon"> <span
                                                                            class="glyphicon-calendar glyphicon"></span>
																		</span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <div class="input-group date" id="endtimePicker">
                                                                        <input type="text" class="form-control"
                                                                               id="submit_enddate" placeholder="结束日期"></input>

                                                                        </span> <span class="input-group-addon"><span
                                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="idNo" class="col-xs-2">证件号码</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" id="idNo" class="form-control"
                                                                       style='width: 250px'>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-xs-6">
                                                            <label for="selcollage" class="col-xs-2">审核状态</label>
                                                            <div class="col-xs-4">
                                                                <select id='selState' class="form-control"
                                                                        style="width: 240px"></select>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="form-group col-xs-6">
                                                            <label for="selcollage" class="col-xs-2">审核时间</label>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="starttimePicker1">
                                                                    <input type="text" class="form-control"
                                                                           id="audit_startdate" placeholder="开始日期"></input>
																	<span class="input-group-addon"><span
                                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                                </div>
                                                            </div>
                                                            <div class="col-xs-4">
                                                                <div class="input-group date" id="endtimePicker1">
                                                                    <input type="text" class="form-control"
                                                                           id="audit_enddate" placeholder="结束日期"></input>
																	<span class="input-group-addon"><span
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
                            <div class="table-responsive">

                                <div class="panel-body" style="padding-bottom: 0px;">

                                    <div id="toolbar" class="btn-group">


                                        <shiro:hasPermission name="140_AUDIT">
                                            <button id="btn_audit" type="button"
                                                    class="btn btn-default">
                                                <span class="glyphicon glyphicon-send" aria-hidden="true"></span>审核
                                            </button>
                                        </shiro:hasPermission>

                                        <shiro:hasPermission name="140_QUERY">
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
    </form>
</div>

<div class="modal fade" style="padding-top: 20%" id="labelModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">小派标签</h4>
            </div>
            <div class="modal-body">
                <form id="labelForm" method="post" action="">
                    <input type="hidden" name="pUserId"/>
                    <input type="textarea" class="form-control" name="remark" maxlength="100"/>
                    <div class="modal-footer">
                        <a id="btnLabelSave" type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>提交
                        </a>
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="auditOrderUser.jsp"></jsp:include>
