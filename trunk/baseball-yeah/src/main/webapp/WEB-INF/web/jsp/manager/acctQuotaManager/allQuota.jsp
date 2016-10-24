<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/comm.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>爱学派</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
<link
	href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>
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
		require([ 'quotaManagerModules' ], function(quota) {
			quota.init();
		});
	});
</script>

</head>
<body>
	<div id="page-wrapper" class="container">

		<div class="row">
			<form action="">
				<div class="row">
					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<div class="main-box-body clearfix">
									<header class="main-box-header clearfix">
										<div class="row">
											<div class="col-lg-12">
												<ol class="breadcrumb">
													<li><a
														href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
													<li class="active"><span>系统管理</span></li>
												</ol>
												<h1>指标配置管理</h1>
											</div>
										</div>
									</header>
									<div class="table-responsive">
										<div class="panel-body" style="padding-bottom: 0px;">
											<div id="toolbar" class="btn-group">
												<shiro:hasPermission name="162_ADD">
													<button id="btn_add" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
													</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="162_EDIT">
													<button id="btn_edit" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-pencil"
															aria-hidden="true"></span>修改
													</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="162_DELETE">
													<button id="btn_delete" type="button"
														class="btn btn-default">
														<span class="glyphicon glyphicon-remove"
															aria-hidden="true"></span>删除
													</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="162_QUERY">
													<button type="button" id="btn_query"
														class="btn btn-default">
														<span class="glyphicon glyphicon-search"
															aria-hidden="true"></span>查询
													</button>
												</shiro:hasPermission>
											</div>
											<table id="quotaTable" class="table">
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	</div>
	<jsp:include page="addQuota.jsp"></jsp:include>
	<jsp:include page="editQuota.jsp"></jsp:include>
</body>
</html>
