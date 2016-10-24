<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<form id="addForm" method="post" class="form-horizontal"
	action="">
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

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">用户id</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_userId"
								id="add_userId" placeholder="用户id" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">用户姓名</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_name"
								id="add_name" placeholder="用户姓名" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">联系方式</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_phone" class="form-control"
								id="add_phone" placeholder="联系方式">
						</div>
					</div>
                   			<div class="form-group">
						<label class="col-lg-3 control-label label-operation">内容</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_content"
								id="add_content" placeholder="内容" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">ip</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_ip"
								id="add_ip" placeholder="用户姓名" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">提交时间</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_submittedTime" class="form-control"
								id="add_submittedTime" placeholder="提交时间">
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
