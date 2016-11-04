<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<form id="addForm" method="post" class="form-horizontal" action="">
	<div class="modal fade" id="addModal" role="dialog"
		aria-labelledby="myModalLabel">
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
					<input type="hidden" id="editRoleID" name="roleId" />
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">渠道名称</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" id="addChannelName"
								name="addChannelName" placeholder="渠道名称" style="width: 300px" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">渠道编码</label>
						<div class="col-lg-6">
							<input type="text" readonly="readonly"
								onchange="changeSpreadUrl();" class="form-control"
								id="addChannelCode" name="addChannelCode" placeholder="渠道编码"
								style="width: 300px" />
						</div>
						<div>
							<input type="button" value="刷新渠道编码"
								style="margin-left: 10px; margin-top: 5px"
								onclick="refreshChannelCode();"> </input>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">联系人</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" id="addContacts"
								name="addContacts" placeholder="联系人" style="width: 300px" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">联系电话</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" id="addContactPhone"
								name="addContactPhone" placeholder="联系电话" style="width: 300px" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">目标URL</label>
						<div class="col-lg-6">
							<input type="text" onchange="changeSpreadUrl();"
								class="form-control" id="addTargetUrl" name="addTargetUrl"
								placeholder="目标URL,必须以http://开头" style="width: 300px" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">推广URL</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" id="addSpreadUrl"
								name="addSpreadUrl" placeholder="推广URL,必须以http://开头"
								style="width: 300px" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">备注</label>
						<div class="col-lg-7 div-operation ">
							<textarea type="text" class="form-control" style="height: 100px;"
								id="addRemark" name="addRemark" placeholder="备注"
								data-bind='value: remark'></textarea>
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
	<input type="hidden" id="checkChannelCode" />
</form>
