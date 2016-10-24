<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="detailForm" method="post" class="form-horizontal" action="">
	<div class="modal fade" id="detailModal" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 1100px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">订单明细</h4>
				</div>
				<div class="modal-body">
                    <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="templateName1" class="control-label col-xs-1" style=" margin-top: 0;">收件人手机号</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailMoblile" readonly></div>
                        <label for="templateName2" class="col-lg-1 control-label" style=" margin-top: 0;">订单号</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailOrderId" readonly></div>
                        <label for="templateName3" class="col-lg-1 control-label" style=" margin-top: 0;">总金额(元)</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailTotalMoney" readonly></div>
                    </div>
                    
                     <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="templateName1" class="control-label col-xs-1" style=" margin-top: 0;">优惠后金额(元)</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailRebateMoney" readonly></div>
                        <label for="templateName2" class="col-lg-1 control-label" style=" margin-top: 0;">最终金额(元)</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailFinalMoney" readonly></div>
                        <label for="templateName3" class="col-lg-1 control-label" style=" margin-top: 0;">支付金额(元)</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailPayMoney" readonly></div>
                    </div>
                    
                     <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="templateName1" class="control-label col-xs-1" style=" margin-top: 0;">真实姓名</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailRealName" readonly></div>
                        <label for="templateName2" class="col-lg-1 control-label" style=" margin-top: 0;">运单号</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailWaybillNo" readonly></div>
                        <label for="templateName3" class="col-lg-1 control-label" style=" margin-top: 0;">性别</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailSex" readonly></div>
                    </div>
                    
                     <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="templateName1" class="control-label col-xs-1" style=" margin-top: 0;">当前状态</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailState" readonly></div>
                        <label for="templateName2" class="col-lg-1 control-label" style=" margin-top: 0;">城市</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailCityName" readonly></div>
                        <label for="templateName3" class="col-lg-1 control-label" style=" margin-top: 0;">送货地址</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailAddress" readonly></div>
                    </div>
                    
                     <div class="form-group" style="padding-right: 20px; margin-bottom: 5px;">
                        <label for="templateName1" class="control-label col-xs-1" style=" margin-top: 0;">学校</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailCollegeName" readonly></div>
                        <label for="templateName2" class="col-lg-1 control-label" style=" margin-top: 0;">门店名称</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailStoreName" readonly></div>
                        <label for="templateName3" class="col-lg-1 control-label" style=" margin-top: 0;">门店地址</label>
                        <div class="col-lg-3"><input type="text" class="form-control" id="detailLocation" readonly></div>
                    </div>

					<table id="detailTable" class="table"></table>


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
