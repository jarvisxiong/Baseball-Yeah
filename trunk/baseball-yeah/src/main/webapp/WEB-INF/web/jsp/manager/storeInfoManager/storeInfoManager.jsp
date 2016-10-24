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

<script type="text/javascript">
	$(function() {

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
									<div class="table-responsive">
										<div class="row">
											<div class="col-lg-12">
												<ol class="breadcrumb">
													<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
													<li class="active"><span>基本设置</span></li>
												</ol>
												<h1>门店信息维护</h1>
											</div>
										</div>

										<div class="panel-body" style="padding-bottom: 0px;">
										
									</div>
								</div>
							</div>
						</div>
					</div>
			</form>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>
