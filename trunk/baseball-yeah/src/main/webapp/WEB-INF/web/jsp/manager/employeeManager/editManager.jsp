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
					<input type="hidden" name="userManagerId" id="editUserManagerId" />
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">账号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="editLoginName"
								id="editLoginName" placeholder="账号" disabled="disabled" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">姓名</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="editUserName"
								id="editUserName" placeholder="姓名" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">工号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="editUserCode" class="form-control"
								id="editUserCode" placeholder="工号" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">部门</label>
						<div class="col-lg-7 div-operation ">
							<select id="editDeptId" name="editDeptId"
								class="js-example-data-array" style="width: 283px"></select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">职务</label>
						<div class="col-lg-7 div-operation ">
							<select id="editDutyId" name="dutyId"
								class="js-example-data-array" style="width: 283px"></select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">角色</label>
						<div class="col-lg-2">
							<select id='editRole' class="js-example-data-array"
								style="width: 283px;" multiple>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">密码</label>
						<div class="col-lg-7 div-operation ">
							<input type="password" name="editPassword" class="form-control"
								id="editPassword" placeholder="密码">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">再次输入密码</label>
						<div class="col-lg-7 div-operation ">
							<input type="password" name="editRePassword" class="form-control"
								id="editRePassword" placeholder="密码">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">是否启用</label>
						<div class="col-lg-7 div-operation">

							<select id='editBeEnabled' name="editBeEnabled"
									class="form-control" style="width: 100%">
								<option value="1">启用</option>
								<option value="0">禁用</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">性别</label>
						<div class="radio">
							<input type="radio" name="editGender" id="editMan"
								value="option1" checked=""> <label for="editMan">男</label>
							<input type="radio" name="editGender" id="editWoman"
								value="option2"> <label for="editWoman"> 女</label>
						</div>

					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">联系电话</label>
						<div class="radio">
							<div class="col-lg-7 div-operation ">
								<input type="text" class="form-control" name="editPhone"
									id="editPhone" placeholder="联系电话" />
							</div>
						</div>

					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">地址</label>
						<div class="radio">
							<div class="col-lg-7 div-operation ">
								<input type="text" class="form-control" name="editAddress"
									id="editAddress" placeholder="地址" />
							</div>
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
<input type='hidden' id='userId'>