<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="detailForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="packetDetailModal" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width: 900px;">
            <div class="modal-content">
                <div class="modal-header" style="height:60px;">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <div><h4 style="text-align:center;font-size: 20px;font-weight: 800">订单信息</h4></div>
                </div>
                <div class="modal-body">
                    <div class="modal-header"  style="padding-top: 0px;height:30px;padding-bottom: 2px;">
                        <h4 class="modal-title" id="myModalLabel"><span style="font-weight: 700">用户信息</span></h4>
                    </div>
                    <div class="form-group" style=" margin-bottom: 5px;">
                        <label for="orUserPhone" class="control-label col-xs-1" style="margin-top: 0;width: 11%;" id="orUserPhoneTitle">收件人手机号</label>
                        <div class="col-lg-4"><input type="text" class="form-control" id="orUserPhone" readonly></div>
                        <label for="orCollegeName" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">学校</label>
                        <div class="col-lg-4"><input type="text" class="form-control" id="orCollegeName" readonly></div>
                    </div>

                    <div class="form-group" style=" margin-bottom: 5px;">
                        <label for="orUserName" class="control-label col-xs-1" style="margin-top: 0;width: 11%;" id="orUserNameTitle">收件人姓名</label>
                        <div class="col-lg-4"><input type="text" class="form-control" id="orUserName" readonly></div>
                        <label for="orDeliAddress" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;" id="orDeliAddressTitle">送货地址</label>
                        <div class="col-lg-4"><input type="text" class="form-control" id="orDeliAddress" readonly></div>
                    </div>

                    <div class="form-group" style=" margin-bottom: 5px;">
                        <label for="orSex" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">性别</label>
                        <div class="col-lg-4"><input type="text" class="form-control" id="orSex" readonly></div>
                        <label for="orCityName" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">城市</label>
                        <div class="col-lg-4"><input type="text" class="form-control" id="orCityName" readonly></div>
                    </div>

                    <div class="form-group" style=" margin-bottom: 5px;" id="orPacketPickup">
                        <span id="orFetchAddressTitle">
                            <label for="orFetchAddress" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">取件地址</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orFetchAddress" readonly></div>
                        </span>
                        <label for="orExpressAddress" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">快递站点名称</label>
                        <div class="col-lg-4"><input type="text" class="form-control" id="orExpressAddress" readonly></div>
                    </div>

                    <div class="modal-header" style="padding-top: 8px;padding-bottom: 8px;">
                        <div><h4 class="modal-title"><span style="font-weight: 700">订单信息</span></h4></div>
                    </div>
                    <span id="orderDetailSpanId">
                        <div class="form-group" style=" margin-bottom: 5px;">
                            <label for="orOrderId" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">订单号</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orOrderId" readonly></div>
                            <label for="orPayMoney" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">实际支付金额</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orPayMoney" readonly></div>
                        </div>
                        <div class="form-group" style=" margin-bottom: 5px;">
                            <label for="orWaybillNo" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">运单号</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orWaybillNo" readonly></div>
                            <label for="orRebateType" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">优惠方式</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orRebateType" readonly></div>
                        </div>
                        <div class="form-group" style=" margin-bottom: 5px;">
                            <label for="orState" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">当前状态</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orState" readonly></div>
                            <label for="orPayMethod" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">付款方式</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orPayMethod" readonly></div>
                        </div>
                        <div class="form-group" style=" margin-bottom: 5px;">
                            <label for="orFeeMoney" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">打赏金额</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orFeeMoney" readonly></div>
                            <label for="orPayType" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">支付类型</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orPayType" readonly></div>
                        </div>
                        <div class="form-group" style=" margin-bottom: 5px;">
                            <label for="orRebateMoney" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">优惠金额</label>
                            <div class="col-lg-4"><input type="text" class="form-control" id="orRebateMoney" readonly></div>
                            <span id="orPickupCodeSpan">
                                <label for="orPickupCode" class="control-label col-xs-1" style="margin-top: 0;width: 11%;">取货码</label>
                                <div class="col-lg-4"><input type="text" class="form-control" id="orPickupCode" readonly></div>
                            </span>
                        </div>
                        <div class="form-group" style=" margin-bottom: 5px;">
                            <label for="orDistribionRemark" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">预约时间</label>
                            <div class="col-lg-6"><input type="text" class="form-control" id="orBookTime" readonly></div>
                        </div>
                        <div class="form-group" style=" margin-bottom: 5px;">
                            <label for="orDistribionRemark" class="col-lg-1 control-label" style="margin-top: 0;width: 11%;">配送要求</label>
                            <div class="col-lg-6"><textarea type="text" class="form-control" rows="1" id="orDistribionRemark" readonly></textarea></div>
                        </div>

                        <table id="packetDetailTableList" class="table">
                        </table>
                    </span>
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
