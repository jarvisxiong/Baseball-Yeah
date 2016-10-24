<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">增加</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-xs-3 control-label">模板编码</label>
                        <div class="col-xs-8 div-operation ">
                            <input type="text" class="form-control" name="add_tmplCode" id="add_tmplCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">模板名称</label>
                        <div class="col-xs-8 div-operation ">
                            <input type="text" class="form-control" name="add_tmplName" id="add_tmplName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">模板内容</label>
                        <div class="col-xs-8 div-operation">
                            <textarea rows="12" class="form-control" name="add_tmplContent" id="add_tmplContent"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">消息类型</label>
                        <div class="col-xs-8 div-operation">
                            <select class="form-control" style="width: 100%" id="add_msg_type">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">扩展内容</label>
                        <div class="col-xs-8 div-operation ">
                            <input type="text" class="form-control" name="add_extendContent" id="add_extendContent"/>
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
