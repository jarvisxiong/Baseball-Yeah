<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <h4 class="modal-title" id="myModalLabel">编辑</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">报价名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_offerName" maxlength="40"
                                   id="edit_offerName" placeholder="报价名称"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">首重费用</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_initialWeight" class="form-control" maxlength="6"
                                   id="edit_initialWeight" placeholder="首重费用">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;">
                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">续重费用</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_additionalWeight" class="form-control" maxlength="6"
                                   id="edit_additionalWeight" placeholder="续重费用">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;">
                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">寄件人区域</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='edit_sendAreaId' class="js-example-data-array" style="width: 100%"
                                    name="edit_sendAreaId"></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">收件人区域</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='edit_dispAreaId' class="js-example-data-array" style="width: 100%"
                                    name="edit_dispAreaId"></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">快递品牌</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='edit_EcList' class="js-example-data-array" style="width: 100%"
                                    name="edit_EcList" multiple></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">学校</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='edit_Collage' class="js-example-data-array" style="width: 100%"
                                    name="edit_Collage" multiple></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">排序</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_sortNo" class="form-control" maxlength="6"
                                   id="edit_sortNo" placeholder="排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否启用</label>
                        <div class="radio">
                            <input type="radio" name="editEnabled" id="editYEnabled" value="option1"
                                   checked=""> <label for="editYEnabled">启用</label> <input
                                type="radio" name="editEnabled" id="editNEnabled" value="option2">
                            <label for="editNEnabled"> 不启用</label>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default "  id="btnClose" data-dismiss="modal">
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
<input type='hidden' id='edit_offerId'>