<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="editForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="editModal" role="dialog" aria-labelledby="myModalLabel">
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
                        <label class="col-lg-3 control-label label-operation">角色</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" name="edit_roleTypeDesc" class="form-control" maxlength="11" id="edit_roleTypeDesc" placeholder="角色" disabled>
                            <input type="hidden" name="edit_roleType" class="form-control" maxlength="4" id="edit_roleType" placeholder="用户ID">
                            <input type="hidden" name="edit_id" class="form-control" maxlength="4" id="edit_id" placeholder="用户ID">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">手机号码</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" name="edit_phone" class="form-control" maxlength="11" id="edit_phone" placeholder="手机号码" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">姓名</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" name="edit_userName" class="form-control" maxlength="4" id="edit_userName" placeholder="姓名" disabled>
                            <input type="hidden" name="edit_userId" class="form-control" maxlength="4" id="edit_userId" placeholder="用户ID">
                            
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">学校校区</label>
                        <div class="col-lg-7 div-operation">
                            <select id='edit_collegeId' class="form-control select2" style="width: 100%" name="edit_collegeId"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">订单类型</label>
                        <div class="col-lg-7 div-operation">
                            <select id='edit_orderType' class="form-control select2" style="width: 100%" name="edit_orderType"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">费用类型</label>
                        <div class="col-lg-7 div-operation">
                            <select id='edit_costType' class="form-control select2" style="width: 100%" name="edit_costType"></select>
                        </div>
                    </div>
                    <div class="form-group" id="edit_supervisorId_div" style="display:none;">
                        <label class="col-lg-3 control-label label-operation">商户手机号</label>
                        <div class="col-lg-7 div-operation">
                            <select id='edit_supervisorId' class="form-control select2" style="width: 100%" name="edit_supervisorId"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">费用金额</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" name="edit_costValue" class="form-control" maxlength="4" id="edit_costValue" placeholder="费用金额">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;">
                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">备注</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" name="edit_remark" class="form-control" id="edit_remark" placeholder="备注">
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
