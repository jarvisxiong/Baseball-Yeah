<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<input type="hidden" class="inmodule" value="acctRefundManagerModules">
<div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="main-box-body clearfix">
				<header class="main-box-header clearfix">
					<%--<div class="row">--%>
						<%--<div class="col-lg-12">--%>
							<%--<ol class="breadcrumb">--%>
								<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
								<%--<li class="active"><span>系统设置</span></li>--%>
							<%--</ol>--%>
							<%--<h1>退款管理</h1>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="panel-group" style="margin-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="clearSearch"> <span
										class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a> <a class="accordion-toggle a-search" data-toggle="collapse"
										data-parent="#accordion" href="#walletAcctRefundManagercollapseOne"> 查询条件 </a>
								</h4>
							</div>
							<div id="walletAcctRefundManagercollapseOne" class="panel-collapse collapse in">
								<div class="panel-body">

									<div class="row">
										<div class="form-group col-xs-6">
											<label for="thdType" class="control-label col-xs-2">支付类型:</label>
											<div class="col-xs-10">
												<select id='thdType' class="js-example-data-array"
													style="width: 250px"></select>
											</div>
										</div>
										<div class="form-group col-xs-6">
											<label for="refundState" class="control-label col-xs-2">退款状态:</label>
											<div class="col-xs-10">
												<select id='refundState' class="js-example-data-array"
													style="width: 250px"></select>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label for="orderId" class="control-label col-xs-2">订单号:</label>
											<div class="col-xs-10">
												<input type="text" class="form-control" name="orderId"
													style="width: 240px" id="orderId" />
											</div>
										</div>
										<div class="form-group col-xs-6">
											<div class="input-daterange">
												<label class="control-label col-xs-2" for="startTime">更新时间:</label>
												<div class="col-xs-4">
													<div class="input-group date" id="starttimePicker">
														<input type="text" class="form-control" id="startTime"></input>
														<span class="input-group-addon"><span
															class="glyphicon-calendar glyphicon"></span> </span>
													</div>
												</div>
												<label class="control-label col-xs-1" for="endTime">至</label>
												<div class="col-xs-4">
													<div class="input-group date" id="endtimePicker">
														<input type="text" class="form-control" id="endTime"></input><span
															class="input-group-addon"><span
															class="glyphicon-calendar glyphicon"></span> </span>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</header>

				<div class="table-responsive">

					<div class="panel-body" style="padding-bottom: 0px;">


						<div id="toolbar" class="btn-group">
							<shiro:hasPermission name="164_QUERY">
								<button type="button" id="btn_query" class="btn btn-default">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
								</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="164_QUERY">
								<button type="button" id="btn_refund" class="btn btn-default">
									<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>退款
								</button>
							</shiro:hasPermission>
						</div>
						<table id="menuTable" class="table">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
