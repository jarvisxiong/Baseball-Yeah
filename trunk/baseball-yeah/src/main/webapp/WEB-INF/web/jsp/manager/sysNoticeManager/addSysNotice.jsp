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
					<h4 class="modal-title" id="myModalLabel">增加</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">标题</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_caption"
								id="add_caption" placeholder="标题" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">网址</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_url"
								id="add_url" placeholder="网址" />
						</div>
					</div>

					<!-- <div class="form-group">
						<label class="col-lg-3 control-label label-operation">是否推送</label>
						<div class="radio">
							<input type="radio" name="addRadios" id="addPush" value="option1"
								checked=""> <label for="addPush">是</label> <input
								type="radio" name="addRadios" id="addNoPush" value="option2">
							<label for="addNoPush"> 否</label>
						</div>
					</div> -->

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">用户类型</label>
						<div class="col-lg-7 div-operation">
							<div class="form-group-select2">
								<select style="width: 283px" id="add_pushTypes" name="add_pushTypes" multiple>
									<option value="axp">货源</option>
									<option value="axp_packet">众包</option>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">内容</label>
						<div class="col-lg-7 div-operation ">
							<textarea type="text" name="add_content" class="form-control"
								id="add_content" placeholder="内容"></textarea>
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

<!-- <input type="hidden" id="sysNoticeId"> -->