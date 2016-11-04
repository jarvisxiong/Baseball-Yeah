<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="deptManagerModules">
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
                                        <%--<li class="active"><span>基础设置</span></li>--%>
                                    <%--</ol>--%>
                                    <%--<h1>部门维护</h1>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="table-responsive">

                            <div id="toolbar" class="btn-group">
                                <shiro:hasPermission name="20_ADD">
                                    <button id="btn_add" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                    </button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="20_EDIT">
                                    <button id="btn_edit" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                                    </button>
                                </shiro:hasPermission>

                                <shiro:hasPermission name="20_DELETE">
                                    <button id="btn_delete" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                    </button>
                                </shiro:hasPermission>
                            </div>

                            <div>
                                <table id="deptTable" class="table">

                                </table>
                            </div>

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
                    <input type="hidden" name="deptId" id="deptId"/>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="deptCode" class="col-lg-2 control-label">部门编号</label>
                        <div class="col-lg-9">
                            <input type="text" name='deptCode' class="form-control"
                                   id="deptCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="deptName" class="col-lg-2 control-label">部门名称</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" id="deptName"
                                   name="deptName"/>
                        </div>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" id="btnSave" class="btn btn-primary">保存</button>
                </div>

            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        $(window).resize(function () {
            $('#deptTable').bootstrapTable('resetView');
        });
    });
</script>
