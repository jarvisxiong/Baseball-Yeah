<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="detailForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="detailModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">短信模板审核</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="deCreateUser">
                    <input type="hidden" id="deCreateUserPhone">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">模板编号</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailSmsModelId" readonly></div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">模板名称</label>
                        <div class="col-lg-3" style="width: 360px;"><textarea type="text" class="form-control" rows="1" id="detailTemplateName" readonly/></div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">模板内容</label>
                        <div class="col-lg-3" style="width: 360px;"><textarea type="text" class="form-control" rows="4" id="detailModelContent" readonly/></div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">效果展示</label>
                        <div class="col-lg-3" style="width: 360px;"><textarea type="text" class="form-control" rows="5" id="detailModelEffect" readonly/></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">原因</label>
                        <div class="col-lg-3" style="width: 360px;"><textarea type="text" class="form-control" rows="1" id="detailOptReason" maxlength="255"/></div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="btnRefuse">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>驳回
                    </button>
                    <button type="button" class="btn btn-primary" id="btnPass">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>通过
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
