<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="collegeManagerModules">

<div id="page-wrapper" class="container">
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <div class="main-box-body clearfix">
                        <%--<div class="row">--%>
                            <%--<div class="col-lg-12">--%>
                                <%--<ol class="breadcrumb">--%>
                                    <%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
                                    <%--<li class="active"><span>基础设置</span></li>--%>
                                <%--</ol>--%>
                                <%--<h1>学校管理</h1>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="table-responsive">

                            <div id="toolbar" class="btn-group">
                                <shiro:hasPermission name="15_ADD">
                                    <button id="btn_add" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                    </button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="15_EDIT">
                                    <button id="btn_edit" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                    </button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="15_DELETE">
                                    <button id="btn_delete" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                    </button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="15_QUERY">
                                    <button type="button" id="btn_query" class="btn btn-default">
                                        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
                                    </button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="15_PACKETPATTEN">
                                    <button type="button" id="btn_on" class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>众包模式
                                    </button>
                                </shiro:hasPermission>
                            </div>

                            <table id="collegeTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<form id="addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" data-backdrop="static">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" type="button" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" style="border: none;" id="myModalLabel">新增</h4>
                    <input type="hidden" name="collegeId" id="collegeId"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="collegeCode" class="col-lg-2 control-label">学校编号</label>
                        <div class="col-lg-9">
                            <input type="text" name='collegeCode' class="form-control"
                                   id="collegeCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="fullName" class="col-lg-2 control-label">学校全称</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" id="fullName"
                                   name="fullName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="simpleName" class="col-lg-2 control-label">学校简称</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" id="simpleName"
                                   name="simpleName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nature" class="col-lg-2 control-label">办学性质</label>
                        <div class="col-lg-9">
                            <select id='nature' name=natureCode style="width: 100%;"
                                    class="form-control select2"></select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="collegeType" class="col-lg-2 control-label">所属类型</label>
                        <div class="col-lg-9">
                            <select id='collegeType' name='collegeTypeCode'
                                    style="width: 100%;" class="form-control select2"></select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="province" class="col-lg-2 control-label">所属省份</label>
                        <div class="col-lg-9">
                            <select id='province' name='provinceId' style="width: 100%;"
                                    class="form-control select2">
                                <option value=" " selected="selected">请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="city" class="col-lg-2 control-label">所属市区</label>
                        <div class="col-lg-9">
                            <select id='city' name='cityId' style="width: 100%;"
                                    class="form-control select2">
                                <option value=" " selected="selected">请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="county" class="col-lg-2 control-label">所属区县</label>
                        <div class="col-lg-9">
                            <select id='county' name='countyId' style="width: 100%;"
                                    class="form-control select2">
                                <option value=" " selected="selected">请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="remark" class="col-lg-2 control-label">备注</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" id="remark"
                                   name="remark"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="status" class="col-lg-2 control-label">学校状态</label>
                        <div class="col-lg-9">
                            <div class="checkbox">
                                <label> <input type="checkbox" name="status"
                                               id="status" value="1" checked> 启用
                                </label>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" id="clearForm" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary" id="btnSave">保存</button>
                </div>
            </div>
        </div>
    </div>
</form>

<!-- Modal -->
<div class="modal fade" id="paket_mode" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title">众包模式</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="form_packet_mode">
                    <div class="form-group">
                        <div class="radio" style="float: left;">
                            <input type="radio" name="packetMode" id="schoolManager_p1" value="1" checked>
                            <label for="schoolManager_p1">开启众包模式</label> <input
                                type="radio" name="packetMode" id="schoolManager_p2" value="0"><label
                                for="schoolManager_p2"> 关闭众包模式</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">取消
                        </button>
                        <button type="button" id="btn_mode" class="btn btn-primary">确定</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
