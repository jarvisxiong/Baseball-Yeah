<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
					<input id="editOfferAreaId" type="hidden" />
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">区域名称</label>
						<div class="radio">
							<div class="col-lg-7 div-operation ">
								<input type="text" class="form-control" name="editOfferAreaName"
									   id="editOfferAreaName" placeholder="联系电话" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">省份</label>
						<div class="col-lg-7 div-operation">
							<select id='editProvinceIds' name="editProvinceIds" class="js-example-data-array"
									style="width: 283px;" multiple>
							</select>
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
