<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="row">
	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<div class="main-box-body clearfix">
					<%--<div class="row">--%>
						<%--<div class="col-lg-12">--%>
							<%--<ol class="breadcrumb">--%>
								<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
								<%--<li class="active"><span>基础设置</span></li>--%>
							<%--</ol>--%>
							<%--<h1>异常件类型管理</h1>--%>
						<%--</div>--%>
					<%--</div>--%>
				</div>
				<div class="table-responsive">
					<div class="panel-body" style="padding-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="clearSearch"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a> <a class="accordion-toggle a-search" data-toggle="collapse" href="#waybillProblemType-collapseOne"> 查询条件 </a>
								</h4>
							</div>
							<div id="waybillProblemType-collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<form id="formSearch" class="form-horizontal" role="form">
										<div class="row">
											<div class="form-group col-xs-6">
												<label for="version" class="control-label col-xs-2">所属组别：</label>
												<div class="col-xs-10">
													<select class="form-control" id="sel_group_search" style="width: 240px;" name="sel_group_search">
														<option value="">全部</option>
														<option value="1">货源</option>
														<option value="2">门店</option>
														<option value="3">众包</option>
														<option value="4">收件人</option>
													</select>
												</div>
											</div>
											<div class="form-group col-xs-6">
												<div class="input-daterange">
													<label class="control-label col-xs-2" for="startDate">登记时间:</label>
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
						<div id="toolbar" class="btn-group">
							<shiro:hasPermission name="163_ADD">
								<button type="button" id="btn_add" class="btn btn-default">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="163_EDIT">
								<button type="button" id="btn_edit" class="btn btn-default">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="163_DELETE">
								<button type="button" id="btn_delete" class="btn btn-default">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="163_QUERY">
								<button type="button" id="btn_query" class="btn btn-default">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
								</button>
							</shiro:hasPermission>
						</div>
						<table id="waybillProblemTypeTable" class="table">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" class="inmodule" value="waybillProblemTypeModules">

<!-- 新增 -->
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
						<label>异常类型编码</label> <input type="text" class="form-control" name="add_problemTypeCode" maxlength="40"
							id="add_problemTypeCode" placeholder="异常类型编码" />
					</div>
					<div class="form-group">
						<label>异常类型名称</label> <input type="text" name="add_problemTypeName" class="form-control" maxlength="40"
							id="add_problemTypeName" placeholder="异常类型名称">
					</div>
					<div class="form-group">
						<label>序号</label> <input type="text" class="form-control" name="add_sortNo" maxlength="40" id="add_sortNo"
							placeholder="序号" />
					</div>
					<div class="form-group">
						<label>所属类别</label> <select class="form-control" id="sel_group" name="group">
							<option value="1">货源</option>
							<option value="2">门店</option>
							<option value="3">众包</option>
							<option value="4">收件人</option>
						</select>
					</div>
					<%--<div class="form-group">--%>
						<%--<label>是否需要上传附件</label>--%>
						<%--<div class="radio">--%>
							<%--<input type="radio" name="doUplaodfile" id="doUplaodfile1" value="1" checked> <label for="doUplaodfile1">是</label>--%>
							<%--<input type="radio" name="doUplaodfile" id="doUplaodfile2" value="0"><label for="doUplaodfile2">--%>
								<%--否</label>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="form-group">
						<label>是否启用</label>
						<div class="radio">
							<input type="radio" name="enabled" id="enabled1" value="1" checked> <label for="enabled1">是</label> <input
								type="radio" name="enabled" id="enabled2" value="0"><label for="enabled2"> 否</label>
						</div>
					</div>
					<%--<div class="form-group">--%>
						<%--<label>是否必须做退件</label>--%>
						<%--<div class="radio">--%>
							<%--<input type="radio" name="doReturn" id="doReturn1" value="1" checked> <label for="doReturn1">是</label> <input--%>
								<%--type="radio" name="doReturn" id="doReturn2" value="0"><label for="doReturn2"> 否</label>--%>
						<%--</div>--%>
					<%--</div>--%>
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

<!-- 修改 -->
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
						<label>异常类型编码</label> <input type="text" readonly="readonly" class="form-control" name="update_problemTypeCode"
							maxlength="40" id="update_problemTypeCode" placeholder="异常类型编码" />
					</div>
					<div class="form-group">
						<label>异常类型名称</label> <input type="text" name="update_problemTypeName" class="form-control" maxlength="40"
							id="update_problemTypeName" placeholder="异常类型名称">
					</div>
					<div class="form-group">
						<label>序号</label> <input type="text" class="form-control" name="update_sortNo" maxlength="40" id="update_sortNo"
							placeholder="序号" />
					</div>
					<div class="form-group">
						<label>所属类别</label> <select class="form-control" id="update_sel_group" name="group">
							<option value="1">货源</option>
							<option value="2">门店</option>
							<option value="3">众包</option>
							<option value="4">收件人</option>
						</select>
					</div>
					<%--<div class="form-group">--%>
						<%--<label>是否需要上传附件</label>--%>
						<%--<div class="radio">--%>
							<%--<input type="radio" name="update_doUplaodfile" id="update_doUplaodfile1" value="1" checked> <label--%>
								<%--for="update_doUplaodfile1">是</label> <input type="radio" name="update_doUplaodfile" id="update_doUplaodfile2"--%>
								<%--value="0"><label for="update_doUplaodfile2"> 否</label>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="form-group">
						<label>是否启用</label>
						<div class="radio">
							<input type="radio" name="update_enabled" id="update_enabled1" value="1" checked> <label
								for="update_enabled1">是</label> <input type="radio" name="update_enabled" id="update_enabled2" value="0"><label
								for="update_enabled2"> 否</label>
						</div>
					</div>
					<%--<div class="form-group">--%>
						<%--<label>是否必须做退件</label>--%>
						<%--<div class="radio">--%>
							<%--<input type="radio" name="update_doReturn" id="update_doReturn1" value="1" checked> <label--%>
								<%--for="update_doReturn1">是</label> <input type="radio" name="update_doReturn" id="update_doReturn2" value="0"><label--%>
								<%--for="update_doReturn2"> 否</label>--%>
						<%--</div>--%>
					<%--</div>--%>
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