<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--<%@ include file="/common/comm.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>爱学派</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/libs/select2.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/axp/base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugs/JSON-js-master/json_parse.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
<link
	href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>

<script
	src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
<script src="<%=request.getContextPath()%>/js/require.js"
	data-main="<%=request.getContextPath()%>/js/modules/main"></script>
<script type="text/javascript">
	$(function() {
		$(function() {
			require([ 'employeeManagerModules' ], function(store) {
				store.init();
			});
		});
	});
</script>

</head>
<body>
	<div id="page-wrapper" class="container">
		<div class="row">
			<form action="">--%>

				<%-- <a href="<%=request.getContextPath()%>/user/testExcl">testExcl</a> --%>
				<input type="hidden" class="inmodule" value="employeeManagerModules">
				<div class="row">
					<div class="col-lg-12">
						<div class="main-box clearfix">
							<div class="main-box-body clearfix">
								<%--<div class="row">--%>
									<%--<div class="row">--%>
										<%--<div class="col-lg-12">--%>
											<%--<ol class="breadcrumb">--%>
												<%--<li><a--%>
													<%--href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
												<%--<li class="active"><span>系统权限</span></li>--%>
											<%--</ol>--%>
											<%--<h1>系统用户管理</h1>--%>
										<%--</div>--%>
									<%--</div>--%>
								<%--</div>--%>
								<div class="table-responsive">

									<div class="panel-body" style="padding-bottom: 0px;">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a class="a-clear-search" id="clearSearch">
														<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
													</a>
													<a class="accordion-toggle a-search" data-toggle="collapse"
													   data-parent="#accordion" href="#employeeManagercollapseOne"> 查询条件
													</a>
												</h4>
											</div>
											<div id="employeeManagercollapseOne" class="panel-collapse collapse in">
											<div class="panel-body" style="width: 100%;">
												<form id="formSearch" class="form-horizontal">
													<div class="row">
														<div class="form-group col-xs-6">
															<label for="loginName" class="control-label col-xs-2">账号</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" name="loginName"
																	id="loginName" style="width: 240px">
															</div>
														</div>
														<div class="form-group col-xs-6">
															<label for="userName" class="control-label col-xs-2">姓名</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" name="userName"
																	id="userName" style="width: 240px">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group col-xs-6">
															<label for="contactTel" class="control-label col-xs-2">联系电话</label>
															<div class="col-xs-10">
																<input type="text" class="form-control"
																	name="contactTel" id="contactTel" style="width: 240px">
															</div>
														</div>
														<div class="form-group col-xs-6">
															<label for="address" class="control-label col-xs-2">地址</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" name="address"
																	id="address" style="width: 240px">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group col-xs-6">
															<label for="deptId" class="control-label col-xs-2">部门</label>
															<div class="col-xs-10">
																<select id='deptId' name="deptId"
																	class="form-control" style="width: 240px"></select>
															</div>
														</div>
														<div class="form-group col-xs-6">
															<label for="dutyId" class="control-label col-xs-2">职务</label>
															<div class="col-xs-10">
																<select id='dutyId' name="dutyId"
																	class="form-control" style="width: 240px"></select>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group col-xs-6">
															<label for="beEnabled" class="control-label col-xs-2">是否启用</label>
															<div class="col-xs-10">
																<select id='beEnabled' name="beEnabled"
																	class="form-control" style="width: 240px">
																	<option value="">请选择</option>
																	<option value="1">启用</option>
																	<option value="0">禁用</option>
																</select>
															</div>
														</div>
													</div>
												</form>
											</div>
											</div>
										</div>

										<div id="toolbar" class="btn-group">
											<shiro:hasPermission name="16_ADD">
												<button id="btn_add" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
												</button>
											</shiro:hasPermission>

											<shiro:hasPermission name="16_EDIT">
												<button id="btn_edit" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
												</button>
											</shiro:hasPermission>

											<shiro:hasPermission name="16_DELETE">
												<button id="btn_delete" type="button"
													class="btn btn-default">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
												</button>
											</shiro:hasPermission>


											<shiro:hasPermission name="16_INITPWD">
												<button id="btn_initPwd" type="button"
													class="btn btn-default">
													<span class="glyphicon glyphicon-refresh"
														aria-hidden="true"></span>初始化密码
												</button>
											</shiro:hasPermission>
											
											<shiro:hasPermission name="16_QUERY">
												<button type="button" id="btn_query" class="btn btn-default">
													<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
												</button>
												<%--<button id="btn_reset" type="button" class="btn btn-default">--%>
													<%--<span class="glyphicon glyphicon-refresh"--%>
														<%--aria-hidden="true"></span>重置--%>
												<%--</button>--%>
											</shiro:hasPermission>
										</div>
										<table id="userTable" class="table">
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			<%--</form>

		</div>
	</div>
	</div>--%>
	<jsp:include page="addManager.jsp"></jsp:include>
	<jsp:include page="editManager.jsp"></jsp:include>
<%--	<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>--%>
