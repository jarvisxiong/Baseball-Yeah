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
                        <label class="col-lg-3 control-label label-operation">省份</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_provinceName" class="form-control" id="edit_provinceName" placeholder="省份" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">所属市区</label>
                        <div class="col-lg-7">
                            <input type="text" name="edit_cityName" class="form-control" id="edit_cityName" placeholder="所属市区" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">订单类型</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_orderType" class="form-control" id="edit_orderType" placeholder="订单类型" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">费用类型</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_costType" class="form-control" id="edit_costType" placeholder="费用类型" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">费用金额</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_costValue" class="form-control" id="edit_costValue" maxlength="4" placeholder="费用金额">
                            <input type='hidden' id='edit_cityOrderConfId' name="edit_cityOrderConfId">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;">
                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">备注</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_remark" class="form-control" id="edit_remark" placeholder="备注" disabled>
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
