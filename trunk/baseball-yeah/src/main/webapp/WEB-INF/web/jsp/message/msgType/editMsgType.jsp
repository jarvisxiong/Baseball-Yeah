<%--
  Created by IntelliJ IDEA.
  User: wny
  Date: 2016-09-02
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="editForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="editModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label">类型编码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_typeCode" id="edit_typeCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">类型名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_typeName" id="edit_typeName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">类型显示标题</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" class="form-control" name="edit_typeTitle" id="edit_typeTitle"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">类型头像url</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" class="form-control" name="edit_headUrl" id="edit_headUrl"/>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label">消息接收角色</label>
                        <div class="col-lg-7 div-operation">
                            <select class="form-control" style="width: 100%" id="edit_selRole">
                                <option value="1" selected="selected">axp所有类型用户</option>
                                <option value="2">非axp用户</option>
                                <option value="3">货源用户</option>
                                <option value="4">众包用户</option>
                                <option value="5">收件人用户</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">所属一级消息类型</label>
                        <div class="col-lg-7 div-operation">
                            <select class="form-control" style="width: 100%" id="edit_selParentType" name="edit_selParentType">
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
