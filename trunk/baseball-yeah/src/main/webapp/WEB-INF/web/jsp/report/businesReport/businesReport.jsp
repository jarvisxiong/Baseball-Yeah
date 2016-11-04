<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<input type="hidden" class="inmodule" value="businesReportModules" />
 
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<div class="main-box-body clearfix">

						<header class="main-box-header clearfix">
							<%--<div class="row">--%>
								<%--<div class="col-lg-12">--%>
									<%--<ol class="breadcrumb">--%>
										<%--<li><a--%>
											<%--href="${pageContext.request.contextPath }/user/gotoIndex">首页</a></li>--%>
										<%--<li class="active"><span>报表管理</span></li>--%>
									<%--</ol>--%>
									<%--<h1>代金券报表</h1>--%>
								<%--</div>--%>
							<%--</div>--%>

							<div class="panel-group" style="margin-bottom: 0px;">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a class="a-clear-search" id="clearSearch">
												<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
											</a>
											<a class="accordion-toggle a-search" data-toggle="collapse"
											   data-parent="#accordion" href="#voucherReportcollapseOne"> 查询条件
											</a>
										</h4>
									</div>

									<div id="voucherReportcollapseOne" class="panel-collapse collapse in">
										<div class="panel-body">
												<div class="row">
													<div class="form-group col-xs-6">
														<label for="useStartTimePicker" class="col-xs-2">使用时间：</label>
														<div class="col-xs-4">
															<div class="input-group date" id="useStartTimePicker">
																<input type="text" class="form-control" name="useStartTime" readonly id="useStartTime" placeholder="开始日期" ></input>
																<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
															</div>
														</div>

														<div class="col-xs-4">
															<div class="input-group date" id="useEndTimePicker">
																<input type="text" class="form-control" name="useEndTime" readonly id="useEndTime" placeholder="结束日期" ></input>
																<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>
													</div>

													<div class="form-group col-xs-6">
														<label for="receiveStartTimePicker" class="col-xs-2">领用时间：</label>
														<div class="col-xs-4">
															<div class="input-group date" id="receiveStartTimePicker">
																<input type="text" class="form-control" name="receiveStartTime" id="receiveStartTime" placeholder="开始日期" ></input>
																<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
															</div>
														</div>

														<div class="col-xs-4">
															<div class="input-group date" id="receiveEndTimePicker">
																<input type="text" class="form-control" name="receiveEndTime" id="receiveEndTime" placeholder="结束日期" ></input>
																<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>
													</div>

												</div>

												<div class="row">
													<div class="form-group col-xs-6">
														<label for="cityId" class="col-xs-2">城市：</label>
														<div class="col-xs-10">
															<select id="cityId" class="js-example-data-array"
																style='width: 250px'>
															</select>
														</div>
													</div>
													<div class="form-group col-xs-6">
														<label for="collegeId" class="col-xs-2">校园名称：</label>
														<div class="col-xs-10">
															<select id="collegeId" class="js-example-data-array" style='width: 250px'>
															</select>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="form-group col-xs-6">
														<label for="expireStartTimePicker" class="col-xs-2">失效时间：</label>
														<div class="col-xs-4">
															<div class="input-group date" id="expireStartTimePicker">
																<input type="text" class="form-control" name="expireStartTime" id="expireStartTime" placeholder="开始日期" ></input>
																<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
															</div>
														</div>

														<div class="col-xs-4">
															<div class="input-group date" id="expireEndTimePicker">
																<input type="text" class="form-control" name="expireEndTime" id="expireEndTime" placeholder="结束日期" ></input>
																<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>
													</div>
													<div class="form-group col-xs-6">
														<label for="state" class="control-label col-xs-2">代金券状态：</label>
														<div class="col-xs-10">
															<select id="state" name="state" class="js-example-data-array  form-control" style='width: 250px'>
																<option value="">请选择</option>
																<option value="0">已失效</option>
																<option value="1">已领取</option><%--因使用时间不允许为空；一旦使用，状态即修改为已使用，所以，已领取条件查询数据为空--%>
																<option value="2">已使用</option>
																<%--因为left订单表，所以不存在此状态20161009<option value="3">已生成</option>--%>
																<%--<option value="1">创建</option>
																<option value="2">已接单</option>
																<option value="3">配送中</option>
																<option value="4">处理中</option>
																<option value="5">完成</option>
																<option value="6">取消</option>
																<option value="7">异常</option>--%>
															</select>
														</div>
													</div>
												</div>
										</div>
									</div>
								</div>
							</div>
						</header>

						<%-- Buttons and data start --%>
						<div class="table-responsive">
							<div class="panel-body" style="padding-bottom: 0px;">
								<div id="toolbar" class="btn-group">
									<shiro:hasPermission name="184_QUERY">
										<button type="button" id="btn_query" class="btn btn-default">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
										</button>
									</shiro:hasPermission>
								</div>
								<table id="voucherTable" class="table"></table>
							</div>
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
