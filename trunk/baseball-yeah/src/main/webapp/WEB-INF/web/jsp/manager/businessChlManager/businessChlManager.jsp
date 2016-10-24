<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/comm.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>爱学派</title>

	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/axp/base.js"></script>
	<link rel="stylesheet"
		  href="<%=request.getContextPath()%>/css/libs/select2.min.css">
	<link rel="stylesheet"
		  href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
	<link
			href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
			rel="stylesheet">
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
	<link
			href="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
			rel="stylesheet">
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
			src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
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
		$(function() {
			require([ 'storeChannelManagerModules' ], function(store) {
				store.init();
			});
		});
	});
</script>

</head>
<body>
	<div id="page-wrapper" class="container">
		<div class="row">
			<form action="">
				<%-- <a href="<%=request.getContextPath()%>/user/testExcl">testExcl</a> --%>
				<div class="row">

					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<div class="main-box-body clearfix">
									<div class="row">
										<div class="col-lg-12">
											<ol class="breadcrumb">
												<li><a
													href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
												<li class="active"><span>基础设置</span></li>
											</ol>
											<h1>商户渠道管理</h1>
										</div>
									</div>
								</div>
								<div class="table-responsive">

									<div class="panel-body" style="padding-bottom: 0px;">
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
												<form id="formSearch" class="form-horizontal">
													<div class="row">
														<div class="form-group  col-xs-6">
															<label for="channelId" class="control-label col-xs-2">渠道id</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" id="channelId"
																	style="width: 240px">
															</div>
														</div>
														<div class="form-group  col-xs-6">
															<label for="channelName" class="control-label col-xs-2">渠道名称</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" id="channelName"
																	   style="width: 240px">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group  col-xs-6">
															<label for="state" class="control-label col-xs-2">状态</label>
															<div class="col-xs-10">
																<select id="state" class="js-example-data-array form-control"
																		style="width: 240px">
																	<option value="">请选择</option>
																	<option value="0">启用</option>
																	<option value="1">禁用</option>
																</select>
															</div>
														</div>
														<div class="form-group  col-xs-6">
															<label for="nickName" class="control-label col-xs-2">维护人</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" id="nickName"
																	   style="width: 240px">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group  col-xs-6">
															<label for="connectPhone" class="control-label col-xs-2">联系电话</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" id="connectPhone"
																	   style="width: 240px">
															</div>
														</div>
														<div class="form-group  col-xs-6">
															<label for="contacts" class="control-label col-xs-2">联系人</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" id="contacts"
																	   style="width: 240px">
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group col-xs-6">
															<label for="createDate" class="control-label col-xs-2">创建时间</label>
															<div class="col-xs-4">
																<div class="input-group date"
																	 id="createStartDatePicker">
																	<input type="text" class="form-control"
																		   name="createStartDate" id="createStartDate"
																		   placeholder="开始日期"></input> <span
																		class="input-group-addon"> <span
																		class="glyphicon-calendar glyphicon"></span>
																		</span>
																</div>
															</div>
															<label class="control-label col-xs-1" for="endDate">至</label>
															<div class="col-xs-4">
																<div class="input-group date" id="createEndDatePicker">
																	<input type="text" class="form-control"
																		   id="createEndDate" placeholder="结束时间"></input><span
																		class="input-group-addon"><span
																		class="glyphicon-calendar glyphicon"></span> </span>
																</div>
															</div>
														</div>
													</div>
												</form>
											</div>
											</div>
										</div>

										<div id="toolbar" class="btn-group">
											<shiro:hasPermission name="173_ADD">
												<button id="btn_add" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
												</button>
											</shiro:hasPermission>

											<shiro:hasPermission name="173_DELETE">
												<button id="btn_delete" type="button"
													class="btn btn-default">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
												</button>
											</shiro:hasPermission>

											<shiro:hasPermission name="173_QUERY">
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
			</form>

		</div>
	</div>
	</div>
	<jsp:include page="addBusinessChl.jsp"></jsp:include>
	<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>

</body>
</html>
