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
				require([ 'offerPriceModules' ], function(offerPrice) {
					offerPrice.init();
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
			<input type="hidden" class="inmodule" value="offerPriceModules">
			<div class="row">
				<div class="col-lg-12">
					<div class="main-box clearfix">
						<div class="main-box-body clearfix">
							<div class="row">
								<%--<div class="row">--%>
									<%--<div class="col-lg-12">--%>
										<%--<ol class="breadcrumb">--%>
											<%--<li><a--%>
													<%--href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
											<%--<li class="active"><span>基础设置</span></li>--%>
										<%--</ol>--%>
										<%--<h1>区域管理</h1>--%>
									<%--</div>--%>
								<%--</div>--%>
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
												   data-parent="#accordion" href="#offerPriceManagercollapseOne"> 查询条件
												</a>
											</h4>
										</div>
										<div id="offerPriceManagercollapseOne" class="panel-collapse collapse in">
											<div class="panel-body" style="width: 100%;">
												<form id="formSearch" class="form-horizontal">
													<div class="row">
														<div class="form-group  col-xs-6">
															<label for="offerAreaName" class="control-label col-xs-2">区域名称</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" id="offerAreaName"
																	   style="width: 240px">
															</div>
														</div>

														<div class="form-group col-xs-6">
															<label for="provinceIds" class="control-label col-xs-2">省份</label>
															<div class="col-lg-2">
																<select id='provinceIds' class="js-example-data-array"
																		style="width: 283px;">
																</select>
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>

									<div id="toolbar" class="btn-group">
										<shiro:hasPermission name="158_ADD">
											<button id="btn_add" type="button" class="btn btn-default">
												<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
											</button>
										</shiro:hasPermission>

										<shiro:hasPermission name="158_EDIT">
											<button id="btn_edit" type="button" class="btn btn-default">
												<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
											</button>
										</shiro:hasPermission>

										<shiro:hasPermission name="158_DELETE">
											<button id="btn_delete" type="button"
													class="btn btn-default">
												<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
											</button>
										</shiro:hasPermission>

										<shiro:hasPermission name="158_QUERY">
											<button type="button" id="btn_query" class="btn btn-default">
												<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
											</button>
										</shiro:hasPermission>
									</div>
									<table id="areaTable" class="table">
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
<%--		</form>

	</div>
</div>
</div>--%>
<jsp:include page="addArea.jsp"></jsp:include>
<jsp:include page="editArea.jsp"></jsp:include>
<%--<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>--%>
