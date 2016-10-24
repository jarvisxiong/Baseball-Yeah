<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/comm.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城市手机号码统计</title>
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
	src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
<link
	href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/moment.min.js"></script>
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
<script src="<%=request.getContextPath()%>/js/require.js"
	data-main="<%=request.getContextPath()%>/js/modules/main"></script>
<script type="text/javascript">
	$(function() {
		//开始时间
		require([ 'phoneCityReportModules' ], function(phoneCityTable) {
			phoneCityTable.init();
		});
	});
</script>
</head>

<body>
	<div id="page-wrapper" class="container">
		<div class="row">
			<div class="row">
				<div class="col-lg-12">
					<div class="main-box clearfix">
						<header class="main-box-header clearfix">
						<div class="row">
							<div class="col-lg-12">
								<ol class="breadcrumb">
									<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
									<li class="active"><span>报表管理</span></li>
								</ol>
								<h1>手机号码数据报表</h1>
							</div>
						</div>
						<div class="panel-group" style="margin-bottom: 0px;"
							id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="a-clear-search" id="clearSearch"> <span
											class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
										</a> <a class="accordion-toggle a-search" data-toggle="collapse"
											data-parent="#accordion" href="#collapseOne"> 查询条件 </a>
									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse in">
									<div class="panel-body">
										<form id="formSearch" class="form-horizontal" role="form">
											<div class="row">
												<div class="form-group col-xs-6">
													<label for="selcity" class="control-label col-xs-2"
														style="margin-top: 0px;">城市:</label>
													<div class="col-xs-10">
														<select id='selcity' style="width: 240px"
															class="form-control">
															<option value="">全部</option>
														</select>
													</div>
												</div>
												<div class="form-group col-xs-6">
													<div class="input-daterange">
														<label class="control-label col-xs-2" for="startDate"
															style="margin-top: 0px;">日期:</label>
														<div class="col-xs-4">
															<div class="input-group date" id="starttimePicker">
																<input type="text" class="form-control" name="start"
																	id="startdate" placeholder="选择日期"></input> <span
																	class="input-group-addon"><span
																	class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>
													</div>
												</div>
											</div>

										</form>
									</div>
								</div>
							</div>
						</div>
						</header>

						<!-- <div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="accordion-toggle collapsed in" data-toggle="collapse"
										data-parent="#accordion" href="#collapseThree"> 查询条件 </a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in">
								<div class="panel-body">
									<form id="formSearch" class="form-inline" role="form">
									 <div class="row">
									    <div class="form-group col-xs-6">
											<label for="phone">手机号码：</label> <input
												type="text" id="phone" class="form-control"
												placeholder="手机号" />
										</div>
									
										<div class="form-group col-xs-6">
											<div class="input-daterange">
												 <label for="phone">日期：</label>
												<div class="input-group date" id="starttimePicker">
													<input type="text" class="form-control" name="start"
														id="startdate" placeholder="选择日期"></input> <span
														class="input-group-addon"><span
														class="glyphicon-calendar glyphicon"></span> </span>
												</div>
												至
												<div class="input-group date" id="endtimePicker">
													<input type="text" class="form-control" name="end"
														id="enddate" placeholder="结束日期"></input><span
														class="input-group-addon"><span
														class="glyphicon-calendar glyphicon"></span> </span>
												</div>
											</div>
										</div>
										</div>
										<div class="row">
											
										 <div class="form-group col-xs-6">
                                                                <label for="selcity" class="control-label col-xs-2">城市:</label>
                                                                <div class="col-xs-10">
                                                                    <select id='selcity' style="width: 240px"
                                                                            class="form-control">
                                                                        <option value="">全部</option>
                                                                </select>
                                                                 </div>
                                                  </div>
                                             </div>
									</form>
								</div>
							</div>
						</div> -->
						<div id="toolbar" class="btn-group">
							<shiro:hasPermission name="144_QUERY">
								<button type="button" id="btn_query" class="btn btn-default">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
								</button>
								<%--<button id="btn_reset" type="button" class="btn btn-default">--%>
								<%--<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>重置--%>
								<%--</button>--%>
							</shiro:hasPermission>
						</div>
						<div class="main-box-body clearfix">
							<div class="row">
								<table id="phoneCityTable" class="table">

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>