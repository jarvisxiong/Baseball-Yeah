<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<input type="hidden" class="inmodule" value="propertyDictManagerModules">

<div id="page-wrapper" class="container">
    <div class="row">

        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <div class="main-box-body clearfix">
                        <%--<div class="row">--%>
                            <%--<div class="row">--%>
                                <%--<div class="col-lg-12">--%>
                                    <%--<ol class="breadcrumb">--%>
                                        <%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
                                        <%--<li><span>系统设置</span></li>--%>
                                    <%--</ol>--%>
                                    <%--<h1>属性字典维护</h1>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="table-responsive">
                            <div id="toolbar" class="btn-group">
                                <shiro:hasPermission name="28_ADD">
                                    <button data-toggle="modal" id="add" class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="28_EDIT">
                                    <button data-toggle="modal" id="update"
                                            class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                    </button>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="28_DELETE">
                                    <button id="remove" class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                    </button>
                                </shiro:hasPermission>
                            </div>
                            <table id="expressTable" data-click-to-select="true" style="word-break:break-all;">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">属性字典操作</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="formBody">
                    <div class="form-group">
                        <label for="txt_propertyId">属性字典编码</label>
                        <input type="text" class="form-control" id="txt_propertyId" name="propertyId"
                               placeholder="属性字典编码">
                    </div>
                    <div class="form-group">
                        <label for="txt_propertyShortcode">属性简码</label>
                        <input type="text" class="form-control" id="txt_propertyShortcode"
                               name="propertyShortcode" placeholder="属性简码">
                    </div>
                    <div class="form-group">
                        <label for="txt_callAlias">调用别名</label>
                        <input type="text" class="form-control" id="txt_callAlias"
                               name="callAlias" placeholder="调用别名">
                    </div>
                    <div class="form-group">
                        <label for="txt_description">描述信息</label>
                        <input type="text" class="form-control" id="txt_description"
                               name="description" placeholder="描述信息">
                    </div>
                    <div class="form-group">
                        <label for="txt_propertyValue">属性值</label>
                        <input type="text" class="form-control" id="txt_propertyValue"
                               name="propertyValue" placeholder="属性值">
                    </div>
                    <div class="form-group">
                        <label for="txt_sortNo">序号</label>
                        <input type="text" class="form-control" id="txt_sortNo"
                               name="sortNo" placeholder="序号">
                    </div>
                    <div class="modal-footer">
                        <%--<button type="button" id="btnReset" class="btn btn-default">重置</button>--%>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" id="btnSave" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>