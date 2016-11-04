<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<input type="hidden" class="inmodule" value="smsVendorManagerModules">


<div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="main-box-body clearfix">

				<header class="main-box-header clearfix">
					<%--<div class="row">--%>
						<%--<div class="col-lg-12">--%>
							<%--<ol class="breadcrumb">--%>
								<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
								<%--<li class="active"><span>系统管理</span></li>--%>
							<%--</ol>--%>
							<%--<h1>短信服务商信息维护</h1>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="panel-group" style="margin-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="clearSearch"> <span
										class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a> <a class="accordion-toggle a-search" data-toggle="collapse"
										data-parent="#accordion" href="#smsVendorManagercollapseOne"> 查询条件 </a>
								</h4>
							</div>
							<div id="smsVendorManagercollapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="row">
										<div class="form-group col-xs-6">
											<label for="smsVendorId" class="control-label col-xs-2">服务商id:</label>
											<div class="col-xs-10">
												<input type="text" class="form-control" name="smsVendorId"
													style="width: 240px" id="smsVendorId" />
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label for="smsVendorName" class="control-label col-xs-2">服务商名称:</label>
											<div class="col-xs-10">
												<input type="text" class="form-control" name="smsVendorId"
													style="width: 240px" id="smsVendorName" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</header>

				<div class="table-responsive">

					<div class="panel-body" style="padding-bottom: 0px;">


						<div id="toolbar" class="btn-group">
							<shiro:hasPermission name="18_ADD">
								<button id="btn_add" type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="18_EDIT">
								<button id="btn_edit" type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="18_DELETE">
								<button id="btn_delete" type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="18_QUERY">
								<button type="button" id="btn_query" class="btn btn-default">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
								</button>
							</shiro:hasPermission>
						</div>
						<table id="vendorTable" class="table">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

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
					<h4 class="modal-title" id="myModalLabel">新增短信服务商信息</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_smsVendorId">服务商ID</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" class="form-control" name="add_smsVendorId"
								id="add_smsVendorId" placeholder="服务商ID" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_smsVendorCode">服务商编号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_smsVendorCode" class="form-control"
								id="add_smsVendorCode" placeholder="服务商编号" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_smsVendorName">服务商名称</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_smsVendorName" class="form-control"
								id="add_smsVendorName" placeholder="服务商名称 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_loginName">账号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_loginName" class="form-control"
								id="add_loginName" placeholder="账号 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_password">密码</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_password" class="form-control"
								id="add_password" placeholder="密码 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_level">等级</label>
						<div class="col-lg-7 div-operation ">
							<select id="add_level" class="form-control" style="width: 100%"
								name="add_level">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_weight">权重</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_weight" class="form-control"
								id="add_weight" placeholder="权重 " />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_threshold">阀值</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_threshold" class="form-control"
								id="add_threshold" placeholder="阀值 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_channelCode">通道号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_channelCode" class="form-control"
								id="add_channelCode" placeholder="通道号 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="add_interfaceAddress">接口地址</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="add_interfaceAddress"
								class="form-control" id="add_interfaceAddress"
								placeholder="接口地址" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">是否启用</label>
						<div class="radio">
							<input type="radio" name="add_status" id="add_status"
								value="option1" checked="checked"> <label
								for="add_status">是</label> <input type="radio" name="add_status"
								id="add_status_no" value="option2"> <label
								for="add_status_no"> 否</label>
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
					<h4 class="modal-title" id="myModalLabel">服务商信息编辑</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_smsVendorCode">服务商编号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_smsVendorCode" class="form-control"
								id="edit_smsVendorCode" placeholder="服务商编号" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_smsVendorName">服务商名称</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_smsVendorName" class="form-control"
								id="edit_smsVendorName" placeholder="服务商名称 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_loginName">账号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_loginName" class="form-control"
								id="edit_loginName" placeholder="账号 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_password">密码</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_password" class="form-control"
								id="edit_password" placeholder="密码 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_level">等级</label>
						<div class="col-lg-7 div-operation ">
							<select id="edit_level" class="form-control" style="width: 100%"
								name="edit_level">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_weight">权重</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_weight" class="form-control"
								id="edit_weight" placeholder="权重 " />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_threshold">阀值</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_threshold" class="form-control"
								id="edit_threshold" placeholder="阀值 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_channelCode">通道号</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_channelCode" class="form-control"
								id="edit_channelCode" placeholder="通道号 " />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation"
							for="edit_interfaceAddress">接口地址</label>
						<div class="col-lg-7 div-operation ">
							<input type="text" name="edit_interfaceAddress"
								class="form-control" id="edit_interfaceAddress"
								placeholder="接口地址" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label label-operation">是否启用</label>
						<div class="radio">
							<input type="radio" name="edit_status" id="edit_status"
								value="option1" checked="checked"> <label
								for="edit_status">是</label> <input type="radio"
								name="edit_status" id="edit_status_no" value="option2">
							<label for="edit_status_no"> 否</label>
						</div>
					</div>
					<input type='hidden' id='vendorId'>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="btnClose" data-dismiss="modal">
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
