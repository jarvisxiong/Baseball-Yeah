<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="row">
	<div class="row">
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<div class="main-box-body clearfix">
						<%--<div class="row">--%>
							<%--<div class="col-lg-12">--%>
								<%--<ol class="breadcrumb">--%>
									<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
									<%--<li class="active"><span>系统权限</span></li>--%>
								<%--</ol>--%>
								<%--<h1>角色权限管理</h1>--%>
							<%--</div>--%>
						<%--</div>--%>
					</div>
					<div class="table-responsive">
						<div class="panel-body" style="padding-bottom: 0px;">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="a-clear-search" id="clearSearch"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
										</a> <a class="accordion-toggle a-search" data-toggle="collapse" href="#peoplePowerManager-collapseOne"> 查询条件
										</a>
									</h4>
								</div>
								<div id="peoplePowerManager-collapseOne" class="panel-collapse collapse in">
									<div class="panel-body">
										<form id="formSearch" class="form-horizontal">
											<div class="row">
												<div class="form-group  col-xs-6">
													<label for="roleName" class="control-label col-xs-2">角色名称</label>
													<div class="col-xs-10">
														<input type="text" class="form-control" id="roleName" style="width: 240px">
													</div>
												</div>
												<div class="form-group  col-xs-6">
													<label for="roleType" class="control-label col-xs-2">角色类型</label>
													<div class="col-xs-10">
														<select id="roleType" class="js-example-data-array form-control" style="width: 240px">
															<option value="">请选择</option>
															<option value="1">系统管理员</option>
															<option value="0">超级系统管理员</option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group  col-xs-6">
													<label for="beSysPrivilege" class="control-label col-xs-2">是否系统权限</label>
													<div class="col-xs-10">
														<select id='beSysPrivilege' class="js-example-data-array select2-selection__rendered form-control"
															style="width: 240px">
															<option value="">请选择</option>
															<option value="1">是</option>
															<option value="0">否</option>
														</select>
													</div>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
							<div id="toolbar" class="btn-group">
								<shiro:hasPermission name="8_ADD">
									<button id="btn_add" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="8_EDIT">
									<button id="btn_edit" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="8_DELETE">
									<button id="btn_delete" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="8_QUERY">
									<button type="button" id="btn_query" class="btn btn-default">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
									</button>
								</shiro:hasPermission>
								<%--<shiro:hasPermission name="8_QUERY">--%>
								<%--<button id="btn_reset" type="button" class="btn btn-default">--%>
								<%--<span class="glyphicon glyphicon-refresh"--%>
								<%--aria-hidden="true"></span>重置--%>
								<%--</button>--%>
								<%--</shiro:hasPermission>--%>
							</div>
							<table id="userTable" class="table">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" class="inmodule" value="peoplePowerManagerModules">
<jsp:include page="addRole.jsp"></jsp:include>
<jsp:include page="editRole.jsp"></jsp:include>
