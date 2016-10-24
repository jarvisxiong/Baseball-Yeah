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
	src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
<link
	href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugs/JSON-js-master/json_parse.js"></script>
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
<style type="text/css">
.input-group>.form-control {
	line-height: 2
}

.form-control-feedback {
	top: 40px
}

.myfont {
	font-size: 1em;
}

.myvisible {
	display: none;
}

.myopenid {
	
}
</style>
<script type="text/javascript">
	$(function() {
		var date = new Date();
		//开始时间
		$('#applystarttimePicker').datetimepicker(
				{
					format : 'yyyy-mm-dd hh:ii:ss',
					autoclose : true,
					startView : 2,
					minView : 0,
					todayHighlight : true,
					endDate : new Date(date.getFullYear(), date.getMonth(),
							date.getDate(), 23, 59, 59)
				}).on(
				'changeDate',
				function(e) {
					var startTime = e.date;
					$('#applyendtimePicker').datetimepicker('setStartDate',
							startTime);
					$('#checkstarttimePicker').datetimepicker('setStartDate',
							startTime);
					$('#arrivestarttimePicker').datetimepicker('setStartDate',
							startTime);
				});

		//结束时间
		$('#applyendtimePicker').datetimepicker(
				{
					format : 'yyyy-mm-dd hh:ii:ss',
					autoclose : true,
					startView : 2,
					minView : 0,
					useCurrent : false,
					todayHighlight : true,
					endDate : new Date(date.getFullYear(), date.getMonth(),
							date.getDate(), 23, 59, 59)
				}).on('changeDate', function(e) {
			var endTime = e.date;
			$('#applystarttimePicker').datetimepicker('setEndDate', endTime);
			$('#checkendtimePicker').datetimepicker('setEndDate', endTime);
			$('#arriveendtimePicker').datetimepicker('setEndDate', endTime);
		});

		$('#checkstarttimePicker').datetimepicker(
				{
					format : 'yyyy-mm-dd hh:ii:ss',
					autoclose : true,
					startView : 2,
					minView : 0,
					todayHighlight : true,
					endDate : new Date(date.getFullYear(), date.getMonth(),
							date.getDate(), 23, 59, 59)
				}).on('changeDate', function(e) {
			var startTime = e.date;
			$('#checkendtimePicker').datetimepicker('setStartDate', startTime);
		});

		$('#checkendtimePicker').datetimepicker(
				{
					format : 'yyyy-mm-dd hh:ii:ss',
					autoclose : true,
					startView : 2,
					minView : 0,
					todayHighlight : true,
					endDate : new Date(date.getFullYear(), date.getMonth(),
							date.getDate(), 23, 59, 59)
				}).on('changeDate', function(e) {
			var endTime = e.date;
			$('#checkstarttimePicker').datetimepicker('setEndDate', endTime);
		});

		$('#arrivestarttimePicker').datetimepicker(
				{
					format : 'yyyy-mm-dd hh:ii:ss',
					autoclose : true,
					startView : 2,
					minView : 0,
					todayHighlight : true,
					endDate : new Date(date.getFullYear(), date.getMonth(),
							date.getDate(), 23, 59, 59)
				}).on(
				'changeDate',
				function(e) {
					var startTime = e.date;
					$('#arriveendtimePicker').datetimepicker('setStartDate',
							startTime);
				});

		$('#arriveendtimePicker').datetimepicker(
				{
					format : 'yyyy-mm-dd hh:ii:ss',
					autoclose : true,
					startView : 2,
					minView : 0,
					todayHighlight : true,
					endDate : new Date(date.getFullYear(), date.getMonth(),
							date.getDate(), 23, 59, 59)
				}).on('changeDate', function(e) {
			var endTime = e.date;
			$('#arrivestarttimePicker').datetimepicker('setEndDate', endTime);
		});

		require([ 'walletDrawMoneyManager' ], function(wallet) {
			wallet.init();
		});
	});
</script>
</head>
<body>
	<div id="page-wrapper" class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<header class="main-box-header clearfix">
						<div class="row">
							<div class="col-lg-12">
								<ol class="breadcrumb">
									<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
									<li class="active"><span>充值提现</span></li>
								</ol>
								<h1>用户提现申请审核</h1>
							</div>
						</div>
					</header>

					<div class="main-box-body clearfix">
						<div class="table-responsive">
							<div class="row">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="a-clear-search" id="clearSearch"> <span
												class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
											</a> <a class="accordion-toggle a-search" data-toggle="collapse"
												data-parent="#accordion" href="#collapseOne"> 查询条件 </a>
										</h4>
									</div>
									<div id="collapseOne" class="panel-collapse collapse in">
										<div class="panel-body">
											<form id="formSearch" class="form-horizontal" role="form">
												<div class="row">
													<div class="form-group col-xs-6">
														<label class="control-label col-xs-2" for="transationid">账号类型</label>
														<div class="col-xs-10">
															<select id='transationid' style="width: 240px"
																class="js-example-data-array">
															</select>
														</div>
													</div>

													<div class="form-group col-xs-6">
														<label class="control-label col-xs-2" for="orderstate">提现状态</label>
														<div class="col-xs-10">
															<select id='orderstate' style="width: 240px"
																class="js-example-data-array">
																<option value="0">提交申请</option>
																<option value="1">处理中</option>
																<option value="3">审核失败</option>
																<option value="2">已到帐</option>
															</select>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="form-group col-xs-6">
														<div class="input-daterange">
															<label class="control-label col-xs-2"
																for="apply_start_date">提现日期</label>
															<div class="col-xs-4">
																<div class="input-group date" id="applystarttimePicker">
																	<input type="text" class="form-control"
																		id="apply_start_date"></input> <span
																		class="input-group-addon"> <span
																		class="glyphicon-calendar glyphicon"></span>
																	</span>
																</div>
															</div>
															<label class="control-label col-xs-1"
																for="apply_end_date">至</label>
															<div class="col-xs-4">
																<div class="input-group date" id="applyendtimePicker">
																	<input type="text" class="form-control"
																		id="apply_end_date"></input> <span
																		class="input-group-addon"><span
																		class="glyphicon-calendar glyphicon"></span> </span>
																</div>
															</div>
														</div>
													</div>

													<div class="form-group col-xs-6">
														<div class="input-daterange">
															<label class="control-label col-xs-2"
																for="check_start_date">审核时间</label>
															<div class="col-xs-4">
																<div class="input-group date" id="checkstarttimePicker">
																	<input type="text" class="form-control"
																		id="check_start_date"></input> <span
																		class="input-group-addon"><span
																		class="glyphicon-calendar glyphicon"></span> </span>
																</div>
															</div>
															<label class="control-label col-xs-1"
																for="check_end_date">至</label>
															<div class="col-xs-4">
																<div class="input-group date" id="checkendtimePicker">
																	<input type="text" class="form-control"
																		id="check_end_date"></input> <span
																		class="input-group-addon"><span
																		class="glyphicon-calendar glyphicon"></span> </span>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="form-group col-xs-6">
														<label class="control-label col-xs-2" for="applyerid">申请人</label>
														<div class="col-xs-10">
															<input type="text" id="applyerid" style="width: 240px"
																class="form-control" />
														</div>
													</div>
													<div class="form-group col-xs-6">
														<label class="control-label col-xs-2" for="checkerid">审核人</label>
														<div class="col-xs-10">
															<input type="text" id="checkerid" style="width: 240px"
																class="form-control" />
														</div>
													</div>
												</div>

												<div class="row">
													<div class="form-group col-xs-6">
														<label class="control-label col-xs-2" for="thdFlowId">交易流水号</label>
														<div class="col-xs-10">
															<input type="text" id="thdFlowId" class="form-control" />
														</div>
													</div>

													<div class="form-group col-xs-6">
														<div class="input-daterange">
															<label class="control-label col-xs-2"
																for="arrive_start_date">到账时间</label>
															<div class="col-xs-4">
																<div class="input-group date" id="arrivestarttimePicker">
																	<input type="text" class="form-control"
																		id="arrive_start_date"></input><span
																		class="input-group-addon"><span
																		class="glyphicon-calendar glyphicon"></span> </span>
																</div>
															</div>
															<label class="control-label col-xs-1"
																for="arrive_end_date">至</label>
															<div class="col-xs-4">
																<div class="input-group date" id="arriveendtimePicker">
																	<input type="text" class="form-control"
																		id="arrive_end_date"></input> <span
																		class="input-group-addon"> <span
																		class="glyphicon-calendar glyphicon"></span>
																	</span>
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
							<div class="row">
								<div id="toolbar" class="btn-group">
									<!-- <button id="btn_sel_ok" type="button" class="btn btn-default">
									   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>通过
									</button>
									<button id="btn_sel_no" type="button" class="btn btn-default">
									   <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>驳回
									</button> -->
									<shiro:hasPermission name="136_AUDIT">
										<button id="btnAudit" type="button" class="btn btn-default">
											<span class="glyphicon glyphicon-send" aria-hidden="true"></span>
											审核
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="136_ARRIVALACCOUNT">
										<button id="btnUpdate" type="button" class="btn btn-default">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
											到账
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="136_QUERY">
										<button type="button" id="btn_query" class="btn btn-default">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
											查询
										</button>
									</shiro:hasPermission>
								</div>
							</div>
							<table id="walletCheckTable" class="table">

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<form id="checkForm" method="post" class="form-horizontal" role="form"
		action="">
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" style="border: none;" id="myModalLabel">订单详情</h4>
						<input type="hidden" name=exchangeId id="exchangeId" /> <input
							type="hidden" name="flowId" id="flowId" />
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">申请人:</label>
								<div class="col-xs-9">
									<span id="userName" class="form-control"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">申请时间:</label>
								<div class="col-xs-9">
									<span id="createTime" class="form-control"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">提现金额:</label>
								<div class="col-xs-9">
									<span id="drawAmount" class="form-control"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">提现状态:</label>
								<div class="col-xs-9">
									<span id="state" class="form-control"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2" for="thdId">交易流水号:</label>
								<div class="col-xs-9">
									<input class="form-control" type="text" id="thdId" name="thdId"
										autocomplete="off">
								</div>
							</div>
						</div>
						<%-- <div class="form-group>
							<label class="control-label col-xs-2" for="remark">备注:</label>
                            <textarea class="form-control" id="remark" rows="3"></textarea>
                            <span class="help-block"><i class="icon-remove-sign"></i>备注内容控制在20个汉字以内</span>
                        </div> --%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" id="btnOk" class="btn btn-primary">通过</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form id="checkDrawForm" method="post" class="form-horizontal"
		role="form" action="">
		<div class="modal fade" id="checkDrawModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel1" data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" style="border: none;" id="myModalLabel1">订单详情</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">申请人:</label>
								<div class="col-xs-9">
									<span id="userName1" class="form-control"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">申请时间:</label>
								<div class="col-xs-9">
									<span id="createTime1" class="form-control"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">提现金额:</label>
								<div class="col-xs-9">
									<span id="drawAmount1" class="form-control"></span>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-2">提现状态:</label>
								<div class="col-xs-9">
									<div class="radio" style="float: left;">
										<input type="radio" name="drawState" id="isApprove" value="0" />
										<label for="isApprove">通过</label> <input type="radio"
											name="drawState" id="isRefuse" value="1"> <label
											for="isRefuse">驳回</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" id="btnSave" class="btn btn-primary">保存</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form id="showDrawUserForm" method="post" class="form-horizontal"
		role="form" action="">
		<div class="modal fade" id="showDrawUseModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel2" data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" style="border: none;" id="myModalLabel2">申请人信息</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-4">提现帐号:</label>
								<div class="col-xs-7">
									<!-- <span id="drawAcctNo" class="form-control"></span> -->
									<label id="drawAcctNo" class="control-label myfont"></label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-4">姓名:</label>
								<div class="col-xs-7">
									<!-- <span id="nickName" class="form-control"></span> -->
									<label id="nickName" class="control-label myfont"></label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-xs-4">身份证号:</label>
								<div class="col-xs-7">
									<!-- <span id="idNo" class="form-control"></span> -->
									<label id="idNo" class="control-label myfont"></label>
								</div>
							</div>
						</div>
						<div class="row myopenid">
							<div class="form-group">
								<label class="control-label col-xs-4">微信openId:</label>
								<div class="col-xs-7">
									<!-- <span id="openId" class="form-control"></span> -->
									<label id="openId" class="control-label myfont"></label>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		Date.prototype.Format = function(fmt) { //author: meizz 
			var o = {
				"M+" : this.getMonth() + 1, //月份 
				"d+" : this.getDate(), //日 
				"H+" : this.getHours(), //小时 
				"m+" : this.getMinutes(), //分 
				"s+" : this.getSeconds(), //秒 
				"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
				"S" : this.getMilliseconds()
			//毫秒 
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
			return fmt;
		}
		$(function() {
			var date = new Date();
			$('#apply_start_date').val(date.Format("yyyy-MM-dd") + " 00:00:00");
			$('#apply_end_date').val(date.Format("yyyy-MM-dd") + " 23:59:59");
		});
	</script>
	<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>
