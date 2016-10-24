<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <label class="col-lg-3 control-label ">参数编码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_parasCode" id="edit_parasCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">参数名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_parasName" id="edit_parasName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">消息类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="edit_msgType" name="edit_msgType" class="form-control" style='width: 100%'></select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否可空</label>
                        <div class="radio">
                            <input type="radio" name="parasCanNull" id="editIsNull" value="1" checked="">
                            <label for="editIsNull">是</label>
                            <input type="radio" name="parasCanNull" id="editNotNull" value="0">
                            <label for="editNotNull"> 否</label>
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
