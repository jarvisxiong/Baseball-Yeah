<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="cancelOrderForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="cancelOrderModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">取消订单</h4>
                </div>
                <div class="modal-body">
                    <input id="cancelOrderId" type="hidden" />
                    <input id="cancelOrderType" type="hidden" />
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">备注：</label>
                        <div class="radio">
                            <div class="col-lg-7 div-operation ">
                                <textarea type="text" class="form-control" name="cancelReason"
                                       id="cancelReason" placeholder="取消原因" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>提交
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
