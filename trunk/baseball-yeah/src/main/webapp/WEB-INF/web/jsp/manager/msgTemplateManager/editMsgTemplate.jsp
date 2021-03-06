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
					<h4 class="modal-title" id="myModalLabel">修改</h4>
				</div>
				<div class="modal-body">
          <div class="form-group">
						<label class="col-lg-3 control-label label-operation">模板ID</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="edit_messageTemplateId"
								id="edit_messageTemplateId" placeholder="ID" disabled="disabled"/>
						</div>
					</div>
				<div class="form-group">
						<label class="col-lg-3 control-label label-operation">名称</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="edit_messageTemplateName"
								id="edit_messageTemplateName" placeholder="名称" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">短信模版</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="edit_messageTemplate"
								id="edit_messageTemplate" placeholder="短信模版" />
						</div>
					</div>


					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">优先级</label>
						<div class="col-lg-7 div-operation ">
									<select id='edit_priority' name="add_priority"
											class="form-control" style="width: 100%">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
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
<input type='hidden' id='messageTemplateId'>