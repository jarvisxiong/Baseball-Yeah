<%@ page import="com.rofour.baseball.dao.user.bean.UserManagerLoginBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="taskOrderModules">
<div class="row">
    <div class="row">
        <div class="col-lg-12">
            <div class="row">
                <div class="col-lg-12">
                    <ol class="breadcrumb">
                        <li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                        <li class="active"><span>订单管理</span></li>
                    </ol>
                    <h1>任务管理</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="main-box clearfix">
                        <div class="form-group col-lg-12 group-activity"></div>
                        <div class="form-group col-lg-12 group-activity">
                            <label for="theme" class="col-lg-2 control-label add-activity">下单人：</label>
                            <div class="col-lg-2">
                                <input type="text" class="form-control"
                                       value="<%=((UserManagerLoginBean) session.getAttribute("user")).getUserName()%>" readonly="readonly">
                            </div>
                            <label for="theme" class="col-lg-2 control-label add-activity">部门：</label>
                            <div class="col-lg-2">
                                <input type="text" class="form-control"
                                       value="<%=((UserManagerLoginBean) session.getAttribute("user")).getDeptName()%>" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-group col-lg-12 group-activity">
                            <label for="theme" class="col-lg-2 control-label add-activity">主题：</label>
                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="theme" placeholder="主题" maxlength="10">
                            </div>
                        </div>
                        <%--<div class="form-group col-lg-12 group-activity">--%>
                        <%--<label for="description" class="col-lg-2 control-label add-activity">描述：</label>--%>
                        <%--<div class="col-lg-6  ">--%>
                        <%--<textarea type="text" class="form-control" id="description" placeholder="描述" maxlength="500"--%>
                        <%--data-bv-field="add_content"></textarea>--%>

                        <%--</div>--%>
                        <%--</div>--%>
                        <div class="form-group col-lg-12 group-activity">
                            <label for="claim" class="col-lg-2 control-label add-activity">要求：</label>
                            <div class="col-lg-6">
                                            <textarea type="text" class="form-control" id="claim" placeholder="要求" maxlength="500"
                                                      data-bv-field="add_content"></textarea>
                            </div>
                        </div>

                        <div class="form-group col-lg-12 group-activity">
                            <label for="rule" class="col-lg-2 control-label add-activity">任务规则(评分标准)：</label>
                            <div class="col-lg-6">
                                            <textarea type="text" class="form-control" id="rule" placeholder="任务规则(评分标准)" maxlength="500"
                                                      data-bv-field="add_content"></textarea>
                            </div>
                        </div>

                        <div class="form-group col-lg-12 group-activity">
                            <label for="taskLevel" class="col-lg-2 control-label add-activity">任务等级:</label>
                            <div class="col-xs-6">
                                <select id='taskLevel' class="form-control" data-bind='value: activityType' style="background-color: green;">
                                    <option value="1" selected="selected">绿色</option>
                                    <option value="2">蓝色</option>
                                    <option value="3">紫色</option>
                                    <option value="4">金色</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-lg-12 group-activity">
                            <label class="col-lg-2 control-label add-activity">封面图片:</label>
                            <div class="col-lg-6  ">
                                <div id="cover" class="mydivdrop" action="/manage/focuspic/uploadFile"
                                     style=" margin-bottom: 10px;">
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-lg-12 group-activity">
                            <label class="col-lg-2 control-label add-activity">图片:</label>
                            <div class="col-lg-6  ">
                                <div id="myId" class="mydivdrop" action="/manage/focuspic/uploadFile"
                                     style=" margin-bottom: 10px;">
                                </div>
                            </div>
                        </div>


                        <div class="form-group col-lg-12 group-activity">
                            <label  class="col-lg-2 control-label add-activity">是否需要图片:</label>
                            <div class="radio col-lg-6">
                                <input type="radio" name="addEnabled1" id="addYEnabled" value="option1"
                                       checked=""> <label for="addYEnabled">是</label> <input
                                    type="radio" name="addEnabled1" id="addNEnabled" value="option2">
                                <label for="addNEnabled"> 否</label>
                            </div>
                        </div>

                        <div class="form-group col-lg-12 group-activity">
                            <label class="col-lg-2 control-label add-activity">是否需要任务报告:</label>
                            <div class="radio col-lg-6">
                                <input type="radio" name="isRemark" id="addYIsRemark" value="option1"
                                       checked=""> <label for="addYIsRemark">是</label> <input
                                    type="radio" name="isRemark" id="addNIsRemark" value="option2" checked="checked">
                                <label for="addNIsRemark">否 </label>
                            </div>
                        </div>

                        <div class="form-group col-lg-12 group-activity">
                            <div class="col-lg-offset-2 col-lg-10">
                                <button type="button" data-bind='click: addContact' id="taskSave" class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>保存
                                </button>
                                <button type="button" data-bind="click: save" id="taskPub" class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>全部发布
                                </button>
                            </div>
                        </div>


                        <div style="padding:15px">
                            <div id="toolbar" class="btn-group">

                                <button id="btn_add" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                </button>

                                <button id="btn_delete" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-remove"
                                                              aria-hidden="true"></span>删除
                                </button>
                            </div>
                            <table id="orderSubTable" class="table">

                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="addModal.jsp"></jsp:include>


