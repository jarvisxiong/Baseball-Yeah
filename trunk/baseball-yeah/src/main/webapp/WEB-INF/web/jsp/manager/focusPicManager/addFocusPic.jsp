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

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">标题</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="adTitle"
								maxlength="40" id="add_adTitle" placeholder="标题" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">跳转url</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_url"
								maxlength="40" id="add_url" placeholder="跳转url" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">广告类型</label>
						<div class="col-lg-7 div-operation ">
							<select id="add_adType" class="form-control" name="add_adType"
								placeholder="广告类型">
								<option value="">全部</option>
								<option value="01">首页图片</option>
								<option value="02">其他</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">用户类型</label>
						<div class="col-lg-7 div-operation ">
							<select id="add_userType" class="form-control"
								name="add_userType" placeholder="用户类型">
								<option value="">全部</option>
								<option value="01">货源</option>
								<option value="02">门店</option>
								<option value="03">众包</option>
								<option value="04">收件人</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">图片</label>
						<div class="col-lg-7 div-operation ">
							<div id="myId" class="mydivdrop"
								action="/manage/focuspic/uploadFile"></div>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
					</button>
					<button type="submit" class="btn btn-primary" id="buttonsave">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
					</button>
				</div>
			</div>
		</div>
	</div>
</form>
