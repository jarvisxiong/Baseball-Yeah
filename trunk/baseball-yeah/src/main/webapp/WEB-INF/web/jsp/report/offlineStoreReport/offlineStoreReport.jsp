<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/comm.jsp"%>
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
<link
	href="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
<script
	src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
<link
	href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/require.js"
	data-main="<%=request.getContextPath()%>/js/modules/main"></script>
<script type="text/javascript">
	$(function() {
		require([ 'offlineStoreReportModules' ], function(offlineStore) {
			offlineStore.init();
		});
	});
</script>

</head>
<body>
	<div id="page-wrapper" class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<div class="main-box-body clearfix">

						<header class="main-box-header clearfix">
							<div class="row">
								<div class="col-lg-12">
									<ol class="breadcrumb">
										<li><a
											href="${pageContext.request.contextPath }/user/gotoIndex">首页</a></li>
										<li class="active"><span>报表管理</span></li>
									</ol>
									<h1>线下直营门店运营报表</h1>
								</div>
							</div>

							<div class="panel-group" style="margin-bottom: 0px;">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="a-clear-search" id="clearSearch">
												<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
											</a>
											<a class="accordion-toggle a-search" data-toggle="collapse"
											   data-parent="#accordion" href="#collapseOne"> 查询条件
											</a>
										</h4>
									</div>

									<div id="collapseOne" class="panel-collapse collapse in">
										<div class="panel-body">
											<form id="formSearch" class="form-horizontal" role="form">
												<div class="row">
													<div class="form-group col-xs-6">
														<label for="selstore" class="col-xs-2">日期：</label>
														<div class="col-xs-4">
															<div class="input-group date" id="starttimePicker">
																<input type="text" class="form-control" name="start"
																	id="startdate" placeholder="开始日期" ></input> <span
																	class="input-group-addon"><span
																	class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>

														<div class="col-xs-4">
															<div class="input-group date" id="endtimePicker">
																<input type="text" class="form-control" name="end"
																	id="enddate" placeholder="结束日期" ></input><span
																	class="input-group-addon"><span
																	class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>
													</div>

													<div class="form-group col-xs-6">
														<label for="selstore" class="col-xs-2">门店名称：</label>
														<div class="col-xs-10">
															<select id="selstore" class="js-example-data-array"
																style='width: 250px'>
															</select>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="form-group col-xs-6">
														<label for="selcity" class="col-xs-2">城市：</label>
														<div class="col-xs-10">
															<select id="selcity" class="js-example-data-array"
																style='width: 250px'>
															</select>
														</div>
													</div>
													<div class="form-group col-xs-6">
														<label for="selexpress" class="col-xs-2">品牌：</label>
														<div class="col-xs-10">
															<select id="selexpress" class="js-example-data-array"
																style='width: 250px'>
															</select>
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</header>

						<%-- Buttons and data start --%>
						<div class="table-responsive">
							<div class="panel-body" style="padding-bottom: 0px;">
								<div id="toolbar" class="btn-group">
									<shiro:hasPermission name="145_QUERY">
										<button type="button" id="btn_query" class="btn btn-default">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
										</button>
										<%--<button id="btn_reset" type="button" class="btn btn-default">--%>
											<%--<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>重置--%>
										<%--</button>--%>
									</shiro:hasPermission>
								</div>
								<table id="userTable" class="table"></table>
							</div>
						</div>
						<%-- Buttons and data end --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<style>
.login-dialog .modal-dialog {
	width: 900px;
}
</style>
	<jsp:include page="detail.jsp" />
</body>
</html>
