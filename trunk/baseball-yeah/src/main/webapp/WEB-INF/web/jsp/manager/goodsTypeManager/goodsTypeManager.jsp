<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>物品类型</title>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/moment.min.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
            rel="stylesheet">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
    <script type="text/javascript">
        $(function () {

            require(['goodsTypeModules'], function (goodsTypeTable) {
                goodsTypeTable.init();
            });
        });
    </script>
    <style type="text/css">
        #formSearch label {
            margin-top: 2px;
        }
    </style>
</head>

<body>
<div id="page-wrapper" class="container">
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                    <li class="active"><span>基础设置</span></li>
                                </ol>
                                <h1>物品类型</h1>
                            </div>
                        </div>
                    </header>
                    <div id="toolbar" class="btn-group">
                        <shiro:hasPermission name="165_ADD">
                            <button type="button" id="btn_add" class="btn btn-default">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="165_EDIT">
                            <button type="button" id="btn_edit" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="165_DELETE">
                            <button type="button" id="btn_delete" class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                            </button>
                        </shiro:hasPermission>
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="goodsTypeTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 新增 -->

<div class="modal fade" id="addModal" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增</h4>
            </div>
            <div class="modal-body">
                <form id="addForm" method="post" action="">
                    <div class="form-group">
                        <label>物品类型名称</label> <input type="text" class="form-control"
                                                     name="add_name" maxlength="40"
                                                     id="add_name" placeholder="物品类型名称"/>

                    </div>
                    <div class="form-group">
                        <label>描述</label>
                        <textarea rows="6" cols="" name="add_description" class="form-control"
                                  id="add_description" placeholder="描述"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                        </button>
                        <button type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                        </button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>


<!-- 修改 -->

<div class="modal fade" id="editModal" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">编辑</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" method="post" action="">
                    <div class="modal-body">
                        <div class="form-group">
                            <label>物品类型名称</label> <input type="text" class="form-control"
                                                         name="edit_name" maxlength="40"
                                                         id="edit_name" placeholder="物品类型名称"/>

                        </div>
                        <div class="form-group">
                            <label>描述</label>
                        <textarea rows="" cols="" name="edit_description" class="form-control"
                                  id="edit_description" placeholder="描述"></textarea>
                        </div>
                    </div>


                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                        </button>
                        <button type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                        </button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<input type="hidden" value="" id="goodsTypeId"/>
<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>