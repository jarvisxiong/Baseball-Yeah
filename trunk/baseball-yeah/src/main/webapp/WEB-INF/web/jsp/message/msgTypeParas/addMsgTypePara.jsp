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
                        <label class="col-lg-3 control-label">参数编码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_parasCode" id="add_parasCode"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">参数名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_parasName" id="add_parasName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">用户类型</label>
                        <div class="col-lg-7 div-operation">
                            <select class="form-control" style="width: 100%" id="add_msg_type">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">是否可空</label>
                        <div class="radio">
                            <input type="radio" name="addparasRadios" id="addIsNull" value="option1" checked=""><label
                                for="addIsNull">是</label>
                            <input type="radio" name="addparasRadios" id="addNotNull" value="option2"><label
                                for="addNotNull"> 否</label>
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
