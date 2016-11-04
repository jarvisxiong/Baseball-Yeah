<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="addModal" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">省份</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='add_provinceId' name="add_provinceId" class="form-control select2" style="width: 100%;">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">所属市区</label>
                        <div class="col-lg-7 div-operation">
                            <select id='add_cityId' name="add_cityId" class="form-control select2" style="width: 100%;">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">订单类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='add_orderType' class="form-control select2" style="width: 100%" name="add_orderType"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">费用类型</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='add_costType' class="form-control select2" style="width: 100%" name="add_costType"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">费用金额</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_costValue" class="form-control" maxlength="4" id="add_costValue" placeholder="费用金额">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;">
                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">备注</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_remark" class="form-control" id="add_remark" placeholder="备注">
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
