<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="modal fade" id="showCapitalModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 1200px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">交易资金变动记录</h4>
			</div>
			<div class="modal-body">
				<div id="page-wrapper" class="container">
					<div class="row">
						<div class="row">
							<div class="col-lg-12">
								<div class="main-box clearfix">
									<header class="main-box-header clearfix">
										<div class="panel-group" style="margin-bottom: 0px;"
											id="accordion">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a class="a-clear-search" id="clearSearch"> <span
															class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
														</a> <a class="accordion-toggle a-search"
															data-toggle="collapse" data-parent="#accordion"
															href="#collapseOne"> 查询条件 </a>
													</h4>
												</div>
												<div id="collapseOne" class="panel-collapse collapse in">
													<div class="panel-body">
														<form id="formSearch" class="form-horizontal" role="form">
															<div class="row">

																<div class="form-group col-xs-8">
																	<div class="input-daterange">
																		<label class="control-label col-xs-2" for="startDate2">发生时间:</label>
																		<div class="col-xs-4">
																			<div class="input-group date" id="starttimePicker2">
																				<input type="text" class="form-control"
																					id="startdate2" placeholder="开始日期" name="start"></input><span
																					class="input-group-addon"><span
																					class="glyphicon-calendar glyphicon"></span> </span>
																			</div>
																		</div>
																		<label class="control-label col-xs-1" for="endDate2">至</label>
																		<div class="col-xs-4">
																			<div class="input-group date" id="endtimePicker2">
																				<input type="text" class="form-control" name="end"
																					id="enddate2" placeholder="结束日期"></input><span
																					class="input-group-addon"><span
																					class="glyphicon-calendar glyphicon"></span> </span>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="form-group col-xs-4">

																	<label for="payType" class="control-label col-xs-4">交易类型:</label>
																	<div class="col-xs-8">
																		<select id="payType" style="width: 240px"
																			class="form-control" placeholder="交易类型">
																			<option value="">全部</option>
																			<option value="1">充值</option>
																			<option value="2">提现</option>
																			<option value="3">收款</option>
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
									<div id="toolbar" class="btn-group">

										<button style="margin-left: 12px;" type="button"
											id="btn_query_capital" class="btn btn-default">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
										</button>


									</div>
									<div class="main-box-body clearfix">
										<div class="row">
											<table id="capitalChangeTable" class="table">
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>