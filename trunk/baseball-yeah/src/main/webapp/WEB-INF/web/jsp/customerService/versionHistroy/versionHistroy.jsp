<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="row">
	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<header class="main-box-header clearfix">
					<%--<div class="row">--%>
						<%--<div class="col-lg-12">--%>
							<%--<ol class="breadcrumb">--%>
								<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
								<%--<li class="active"><span>基础设置</span></li>--%>
							<%--</ol>--%>
							<%--<h1>历史版本</h1>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="panel-group" style="margin-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="clearSearch"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a> <a class="accordion-toggle a-search" data-toggle="collapse" href="#versionHistroy-collapseOne"> 查询条件 </a>
								</h4>
							</div>
							<div id="versionHistroy-collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<form id="formSearch" class="form-horizontal" role="form">
										<div class="row">
											<div class="form-group col-xs-6">
												<label for="version" class="control-label col-xs-2">版本号：</label>
												<div class="col-xs-10">
													<input type="text" id="version" style="width: 240px" class="form-control" placeholder="版本号" />
												</div>
											</div>
											<div class="form-group col-xs-6">
												<div class="input-daterange">
													<label class="control-label col-xs-2" for="startDate">创建时间:</label>
													<div class="col-xs-4">
														<div class="input-group date" id="starttimePicker">
															<input type="text" class="form-control" id="startdate" placeholder="开始日期" name="start"></input><span
																class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
														</div>
													</div>
													<label class="control-label col-xs-1" for="endDate">至</label>
													<div class="col-xs-4">
														<div class="input-group date" id="endtimePicker">
															<input type="text" class="form-control" name="end" id="enddate" placeholder="结束日期"></input><span
																class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</header>
				<div id="toolbar" class="btn-group">
					<shiro:hasPermission name="157_ADD">
						<button type="button" id="btn_add" class="btn btn-default">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="157_EDIT">
						<button type="button" id="btn_edit" class="btn btn-default">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="157_DELETE">
						<button type="button" id="btn_delete" class="btn btn-default">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
						</button>
					</shiro:hasPermission>

					<shiro:hasPermission name="157_QUERY">
						<button type="button" id="btn_query" class="btn btn-default">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
						</button>
					</shiro:hasPermission>
				</div>
				<div class="main-box-body clearfix">
					<div class="row">
						<table id="historyVersionTable" class="table">

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" class="inmodule" value="historyVersionModules">

<!-- 新增历史版本 -->
<div class="modal fade" id="addModal" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">新增</h4>
			</div>
			<div class="modal-body">
				<form id="addForm" method="post" action="">
					<div class="form-group">
						<label>版本号</label> <input type="text" class="form-control" name="add_version" maxlength="40" id="add_version"
							placeholder="版本号" />
					</div>
					<div class="form-group">
						<label>标题</label> <input type="text" name="add_title" class="form-control" maxlength="40" id="add_title"
							placeholder="标题">
					</div>
					<div class="form-group">
						<label>序号</label> <input type="text" class="form-control" name="add_sortNo" maxlength="40" id="add_sortNo"
							placeholder="序号" />
					</div>
					<div class="form-group">
						<label>内容</label>
						<textarea class="form-control ckeditor" name="add_content" id="add_content" rows="3"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<!-- 修改历史版本 -->
<div class="modal fade" id="editModal" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">编辑</h4>
			</div>
			<div class="modal-body">
				<form id="editForm" method="post" action="">
					<div class="form-group">
						<label>版本号</label> <input type="text" class="form-control" name="edit_version" maxlength="40" id="edit_version"
							placeholder="版本号" />
					</div>
					<div class="form-group">
						<label>标题</label> <input type="text" name="edit_title" class="form-control" maxlength="40" id="edit_title"
							placeholder="标题">

					</div>
					<div class="form-group">
						<label>序号</label> <input type="text" class="form-control" name="edit_sortNo" maxlength="40" id="edit_sortNo"
							placeholder="序号" />
					</div>
					<div class="form-group">
						<label>内容</label>
						<textarea class="form-control ckeditor" name="edit_content" id="edit_content" rows="3"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
						</button>
					</div>
					<input type='hidden' id='versionId'>
				</form>
			</div>
		</div>
	</div>
</div>