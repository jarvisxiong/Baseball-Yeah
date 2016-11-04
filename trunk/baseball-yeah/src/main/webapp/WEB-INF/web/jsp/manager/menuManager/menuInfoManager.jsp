<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="menuInfoManagerModules">

<div class="row">
    <div class="col-lg-12">
        <div class="main-box clearfix">
            <div class="main-box-body clearfix">
                <header class="main-box-header clearfix">
                    <%--<div class="row">--%>
                        <%--<div class="col-lg-12">--%>
                            <%--<ol class="breadcrumb">--%>
                                <%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
                                <%--<li class="active"><span>系统设置</span></li>--%>
                            <%--</ol>--%>
                            <%--<h1>菜单信息维护</h1>--%>
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
                                       data-parent="#accordion" href="#menuInfoManagercollapseOne"> 查询条件
                                    </a>
                                </h4>
                            </div>
                            <div id="menuInfoManagercollapseOne" class="panel-collapse collapse in">
                                <div class="panel-body">

                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <label for="caption" class="control-label col-xs-2">菜单标题:</label>
                                            <div class="col-xs-10">
                                                <input type="text" class="form-control" name="caption"
                                                       style="width: 240px" id="caption"/>
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
                            <shiro:hasPermission name="17_ADD">
                                <button id="btn_add" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="17_EDIT">
                                <button id="btn_edit" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="17_DELETE">
                                <button id="btn_delete" type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                </button>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="17_QUERY">
                                <button type="button" id="btn_query" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                                </button>
                            </shiro:hasPermission>
                        </div>
                        <table id="menuTable" class="table">
                        </table>
                    </div>
                </div>
            </div>
        </div>
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
                    <h4 class="modal-title" id="myModalLabel">新增菜单</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">菜单标题</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_caption"
                                   id="add_caption" placeholder="菜单标题"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">状态</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_menuStatusCode" class="js-example-data-array"
                                    style="width: 283px" name="add_menuStatusCode">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">开发状态</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_menuDevStatusCode" class="js-example-data-array"
                                    style="width: 283px" name="add_menuDevStatusCode">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否为叶子节点</label>
                        <div class="radio">
                            <input type="radio" name="add_beLeaf" id="add_beLeaf" value="option1"
                                   checked="checked"> <label for="add_beLeaf">是</label> <input
                                type="radio" name="add_beLeaf" id="add_beLeaf_no" value="option2">
                            <label for="add_beLeaf_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">路径</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_menuCtrlPath" class="form-control"
                                   id="add_menuCtrlPath" placeholder="路径"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">菜单项帮助</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_hint"
                                   id="add_hint" placeholder="菜单项帮助"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">上级菜单</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_parentId" class="js-example-data-array"
                                    style="width: 283px" name="add_parentId">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">菜单项类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_menuTypeId" class="js-example-data-array"
                                    style="width: 283px" name="add_menuTypeId">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">兄弟排序号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_siblingSortNo"
                                   id="add_siblingSortNo" placeholder="兄弟排序号"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">Dll文件</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_dllFile"
                                   id="add_dllFile" placeholder="Dll文件"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">图片文件</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_picFile"
                                   id="add_picFile" placeholder="图片文件"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">热键</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_hotKey"
                                   id="add_hotKey" placeholder="热键"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否必须显示</label>
                        <div class="radio">
                            <input type="radio" name="add_beMandatory" id="add_beMandatory" value="option1"
                                   checked="checked"> <label for="add_beMandatory">是</label> <input
                                type="radio" name="add_beMandatory" id="add_beMandatory_no" value="option2">
                            <label for="add_beMandatory_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">所属部门</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_belongSiteKind" class="js-example-data-array"
                                    style="width: 283px" name="add_belongSiteKind">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">动作</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_action" class="js-example-data-array"
                                    style="width: 283px" name="add_action" multiple="multiple">
                            </select>
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
                    <h4 class="modal-title" id="myModalLabel">菜单编辑</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">菜单标题</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_caption"
                                   id="edit_caption" placeholder="菜单标题"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">状态</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_menuStatusCode" class="js-example-data-array"
                                    style="width: 283px" name="edit_menuStatusCode">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">开发状态</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_menuDevStatusCode" class="js-example-data-array"
                                    style="width: 283px" name="edit_menuDevStatusCode">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否为叶子节点</label>
                        <div class="radio">
                            <input type="radio" name="edit_beLeaf" id="edit_beLeaf" value="option1"
                                   checked="checked"> <label for="edit_beLeaf">是</label> <input
                                type="radio" name="edit_beLeaf" id="edit_beLeaf_no" value="option2">
                            <label for="edit_beLeaf_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">路径</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_menuCtrlPath" class="form-control"
                                   id="edit_menuCtrlPath" placeholder="路径"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">菜单项帮助</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_hint"
                                   id="edit_hint" placeholder="菜单项帮助"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">上级菜单</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_parentId" class="js-example-data-array"
                                    style="width: 283px" name="edit_parentId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">菜单项类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_menuTypeId" class="js-example-data-array"
                                    style="width: 283px" name="edit_menuTypeId">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">兄弟排序号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_siblingSortNo"
                                   id="edit_siblingSortNo" placeholder="兄弟排序号"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">Dll文件</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_dllFile"
                                   id="edit_dllFile" placeholder="Dll文件"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">图片文件</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_picFile"
                                   id="edit_picFile" placeholder="图片文件"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">热键</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_hotKey"
                                   id="edit_hotKey" placeholder="热键"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否必须显示</label>
                        <div class="radio">
                            <input type="radio" name="edit_beMandatory" id="edit_beMandatory" value="option1"
                                   checked="checked"> <label for="edit_beMandatory">是</label> <input
                                type="radio" name="edit_beMandatory" id="edit_beMandatory_no" value="option2">
                            <label for="edit_beMandatory_no"> 否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">所属部门</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_belongSiteKind" class="js-example-data-array"
                                    style="width: 283px" name="edit_belongSiteKind">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">动作</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_action" class="js-example-data-array"
                                    style="width: 283px" name="edit_action" multiple="multiple">
                            </select>
                        </div>
                    </div>
                    <input type='hidden' id='menuId'>
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
