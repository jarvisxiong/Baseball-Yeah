<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="changeForm" method="post" class="form-horizontal" action="">
	<div class="modal fade" id="changeModal" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改密码</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">旧密码</label>
						<div class="col-lg-7 div-operation ">
							<input type="password" name="oldPassword" class="form-control"
								id="oldPassword" placeholder="请输入旧密码">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">新密码</label>
						<div class="col-lg-7 div-operation ">
							<input type="password" name="newPassword" class="form-control"
								id="newPassword" placeholder="请输入新密码">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">再次输入新密码</label>
						<div class="col-lg-7 div-operation ">
							<input type="password" name="reNewPassword" class="form-control"
								id="reNewPassword" placeholder="请再次输入新密码">
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
