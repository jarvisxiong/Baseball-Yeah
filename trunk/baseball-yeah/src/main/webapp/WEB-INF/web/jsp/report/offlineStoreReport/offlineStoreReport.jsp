<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<input type="hidden" class="inmodule" value="offlineStoreReportModules" /> 
 
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
									<%--<h1>线下直营门店运营报表</h1>--%>
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
											   data-parent="#accordion" href="#offlineStoreReportcollapseOne"> 查询条件
											</a>
										</h4>
									</div>

									<div id="offlineStoreReportcollapseOne" class="panel-collapse collapse in">
										<div class="panel-body">
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
	<style>
.login-dialog .modal-dialog {
	width: 900px;
}
</style>
	<jsp:include page="detail.jsp" /> 
