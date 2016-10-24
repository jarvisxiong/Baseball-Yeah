<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派</title>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js" data-main="<%=request.getContextPath()%>/js/modules/main"></script>


    <script type="text/javascript">
        $(function () {
            $(function () {
                require(['msgConfigManagerModules'], function (msgConfig) {
                    msgConfig.init();
                });
            });
        });
    </script>

</head>
<body>
<div id="page-wrapper" class="container">
    <div class="row">
        <form action="">

            <div class="row">
                <div class="col-lg-12">
                    <div class="main-box clearfix">
                        <div class="main-box-body clearfix">
                            <header class="main-box-header clearfix">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <ol class="breadcrumb">
                                            <li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                            <li class="active"><span>系统设置</span></li>
                                        </ol>
                                        <h1>消息配置维护</h1>
                                    </div>
                                </div>
                                <div class="panel-group" style="margin-bottom: 0px;">
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
                                                        <label for="search_messageTypeId" class="control-label col-xs-2">消息类型:</label>
                                                        <div class="col-xs-10">
                                                            <select id='search_messageTypeId' class="js-example-data-array"
                                                                    style="width: 100%"></select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-xs-6">
                                                        <label for="search_sendTypeId" class="control-label col-xs-2">发送类型:</label>
                                                        <div class="col-xs-10">
                                                            <select id='search_sendTypeId' class="js-example-data-array"
                                                                    style="width: 100%"></select>
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
                                        <shiro:hasPermission name="19_ADD">
                                            <button id="btn_add" type="button" class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                            </button>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="19_EDIT">
                                            <button id="btn_edit" type="button" class="btn btn-default">
                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                            </button>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="19_DELETE">
                                            <button id="btn_delete" type="button" class="btn btn-default">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                            </button>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="19_QUERY">
                                            <button type="button" id="btn_query" class="btn btn-default">
                                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                    <table id="msgConfigTable" class="table">
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<form id="addForm" method="post" class="form-horizontal"
      action="">
    <div class="modal fade" id="addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">新增消息配置信息</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="add_messageTypeId">消息类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_messageTypeId" class="js-example-data-array"
                                    style="width: 100%" name="add_messageTypeId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="add_sendTypeId">发送类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_sendTypeId" class="js-example-data-array"
                                    style="width: 100%" name="add_sendTypeId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="add_level">等级</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_level" class="form-control"
                                   id="add_level" placeholder="等级 "/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否及时发送</label>
                        <div class="radio">
                            <input type="radio" name="add_beImmediateSend" id="add_beImmediateSend" value="option1"
                                   checked="checked"> <label for="add_beImmediateSend">是</label> <input
                                type="radio" name="add_beImmediateSend" id="add_beImmediateSend_no" value="option2">
                            <label for="add_beImmediateSend_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="add_maxLength">最大长度</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_maxLength" class="form-control"
                                   id="add_maxLength" placeholder="最大长度 "/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否启用</label>
                        <div class="radio">
                            <input type="radio" name="add_beEnabled" id="add_beEnabled" value="option1"
                                   checked="checked"> <label for="add_beEnabled">是</label> <input
                                type="radio" name="add_beEnabled" id="add_beEnabled_no" value="option2">
                            <label for="add_beEnabled_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="add_sendRoleId">发送角色ID</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_sendRoleId" class="form-control"
                                   id="add_sendRoleId" placeholder="发送角色ID"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="add_extendCode">扩展码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_extendCode" class="form-control"
                                   id="add_extendCode" placeholder="扩展码"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>

<form id="editForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="editModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">消息配置信息编辑</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="edit_messageTypeId">消息类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_messageTypeId" class="js-example-data-array"
                                    style="width: 100%" name="edit_messageTypeId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="edit_sendTypeId">发送类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_sendTypeId" class="js-example-data-array"
                                    style="width: 100%" name="edit_sendTypeId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="edit_level">等级</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_level" class="form-control"
                                   id="edit_level" placeholder="等级 "/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否及时发送</label>
                        <div class="radio">
                            <input type="radio" name="edit_beImmediateSend" id="edit_beImmediateSend" value="option1"
                                   checked="checked"> <label for="edit_beImmediateSend">是</label> <input
                                type="radio" name="edit_beImmediateSend" id="edit_beImmediateSend_no" value="option2">
                            <label for="edit_beImmediateSend_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="edit_maxLength">最大长度</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_maxLength" class="form-control"
                                   id="edit_maxLength" placeholder="最大长度 "/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否启用</label>
                        <div class="radio">
                            <input type="radio" name="edit_beEnabled" id="edit_beEnabled" value="option1"
                                   checked="checked"> <label for="edit_beEnabled">是</label> <input
                                type="radio" name="edit_beEnabled" id="edit_beEnabled_no" value="option2">
                            <label for="edit_beEnabled_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="edit_sendRoleId">发送角色ID</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_sendRoleId" class="form-control"
                                   id="edit_sendRoleId" placeholder="发送角色ID"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation" for="edit_extendCode">扩展码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_extendCode" class="form-control"
                                   id="edit_extendCode" placeholder="扩展码"/>
                        </div>
                    </div>
                    <input type='hidden' id='messageConfigId'>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>
