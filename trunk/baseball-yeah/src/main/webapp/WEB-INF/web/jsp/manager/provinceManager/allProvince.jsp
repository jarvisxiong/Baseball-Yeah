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
			src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
	<script
			src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
<script
	src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/require.js"
	data-main="<%=request.getContextPath()%>/js/modules/main"></script>
<script type="text/javascript">
	$(function() {
		require([ 'provinceManagerModules' ], function(store) {
			store.init();
		});
	});
	
</script>

</head>
<body>
	<div id="page-wrapper" class="container">
		<div class="row">
			<form action="">
					<div class="row">
						<div class="col-lg-12">
							<div class="main-box clearfix">
								<div class="main-box-body clearfix">
									<div class="row">
										<div class="row">
											<div class="col-lg-12">
												<ol class="breadcrumb">
													<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
													<li class="active"><span>基础设置</span></li>
												</ol>
												<h1>省市区管理</h1>
											</div>
										</div>
									</div>
									<div class="table-responsive" id="mainTree">
										<div class="panel-body" style="padding-bottom: 0px;">
											</div>
											<div id="toolbar" class="btn-group">
												<shiro:hasPermission name="11_ADD">
												<button id="btn_add_a" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增区域信息
												</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="11_ADD">
												<button id="btn_add_p" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增省份信息
												</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="11_ADD">
												<button id="btn_add_c" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增城市信息
												</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="11_ADD">
												<button id="btn_add_co" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增区县信息
												</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="11_EDIT">
												<button id="btn_edit" type="button" class="btn btn-default">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
												</button>
												</shiro:hasPermission>
												<shiro:hasPermission name="11_DELETE">
												<button id="btn_delete" type="button"
													class="btn btn-default">
													<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
												</button>
												</shiro:hasPermission>
											</div>
											<ul id="treeDemo" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
			</form>
		</div>
	</div>
	<input type="hidden" id="type" value="" name="type">
	<input type="hidden" id="id" value="" name="id">
	<input type="hidden" id="pId" value="" name="pId">
  	<jsp:include page="addProvince.jsp"></jsp:include>
	<jsp:include page="editProvince.jsp"></jsp:include>
</body>
</html>
