<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<script type="text/javascript">
	$(function() {
		
	});
</script>
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
						<label class="col-lg-3 control-label label-operation">角色名称</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" id="editRoleName"
								name="editRoleName" placeholder="角色名称" disabled="disabled" style="width: 300px" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">角色类型</label>
						<div class="col-lg-7 div-operation ">
							<select id='editRoleType' name="editRoleType"
									class="js-example-data-array select2-selection__rendered form-control" style="width: 300px">
								<option value="">请选择</option>
								<option value="1">系统管理员</option>
								<option value="0">超级系统管理员</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">是否系统权限</label>
						<div class="col-lg-7 div-operation ">
							<select id='editBeSysPrivilege' name="editBeSysPrivilege"
									class="js-example-data-array select2-selection__rendered form-control" style="width: 300px">
								<option value="">请选择</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
					</div>
				</div>
				
				<div id="editPermission" class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myEditModalLabel">所有权限</h4>
					<jsp:include page="../../common/editMenuTree.jsp"></jsp:include>
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

