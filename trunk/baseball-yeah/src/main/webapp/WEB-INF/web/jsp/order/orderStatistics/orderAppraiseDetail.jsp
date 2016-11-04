<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="auditForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="commentModal" role="dialog" aria-labelledby="commentModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="commentModalLabel">评价详情</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">订单号：</label>
                        <label class="col-lg-8 control-label label-operation" id="mOrderId" style="text-align: left"></label>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">评价等级：</label>
                        <label class="col-lg-8 control-label label-operation">
                            <div style="color:#FABA03;text-align: left" id="divScore">
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                                <i class="fa fa-star-o"></i>
                            </div>
                        </label>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">评语：</label>
                        <div class="col-lg-8 div-operation">
                            <textarea class="form-control" readonly id="mComment" rows="3"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">评图：</label>
                        <div class="col-lg-8 div-operation">
                            <div id="imgList" class="mydivdrop">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>

