<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<input type="hidden" class="inmodule" value="cityOrderConfModules">
<div class="row">
	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<div class="main-box-body clearfix">
					<header class="main-box-header clearfix">
						<div class="panel-group" style="margin-bottom: 0px;"
							id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="a-clear-search" id="clearSearch"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></a>
										<a class="accordion-toggle a-search" data-toggle="collapse"
											data-parent="#accordion" href="#cityOrderConfManagerCollapse"> 查询条件 </a>
									</h4>
								</div>
								<div id="cityOrderConfManagerCollapse" class="panel-collapse collapse in">
									<div class="panel-body">
										<form id="formSearch" class="form-horizontal" role="form">
											<div class="row">
												<div class="form-group  col-xs-6">
													<label for="cityName" class="control-label col-xs-2">城市名称</label>
													<div class="col-xs-10">
														<input type="text" class="form-control" id="cityName" style="width: 240px">
													</div>
												</div>
												<div class="form-group col-xs-6">
													<label for="provinceId" class="control-label col-xs-2">省份</label>
													<div class="col-lg-2">
														<select id='provinceId' class="js-example-data-array" style="width: 240px;">
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-xs-6">
													<label for="orderType" class="control-label col-xs-2">订单类型</label>
													<div class="col-xs-10">
														<select id='orderType' style="width: 240px" class="js-example-data-array form-control">
														</select>
													</div>
												</div>
												<div class="form-group col-xs-6">
													<label for="costType" class="control-label col-xs-2">费用类别</label>
													<div class="col-xs-10">
														<select id='costType' style="width: 240px" class="js-example-data-array form-control">
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="form-group col-xs-6">
													<label for="createUser" class="control-label col-xs-2">创建人（登录名）</label>
													<div class="col-xs-10">
														<input type="text" class="form-control" id="createUser" style="width: 240px">
													</div>
												</div>
												<div class="form-group col-xs-6">
													<label for="state" class="control-label col-xs-2">状态</label>
													<div class="col-xs-10">
														<select id='state' style="width: 240px" class="js-example-data-array form-control">
														</select>
													</div>
												</div>
											</div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="createDate" class="control-label col-xs-2">创建时间</label>
                                                    <div class="col-xs-4">
                                                        <div class="input-group date" id="createStartDatePicker">
                                                            <input type="text" class="form-control" id="createStartDate" placeholder="开始日期">
                                                            <span class="input-group-addon"> 
                                                                 <span class="glyphicon-calendar glyphicon"></span>
															</span>
                                                        </div>
                                                    </div>
                                                    <label class="control-label col-xs-1" for="endDate">至</label>
                                                    <div class="col-xs-4">
                                                        <div class="input-group date" id="createEndDatePicker">
                                                            <input type="text" class="form-control" id="createEndDate" placeholder="结束时间">
                                                            <span class="input-group-addon">
                                                            	<span class="glyphicon-calendar glyphicon"></span> 
                                                            </span>
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

					<div class="table-responsive">
						<div class="panel-body" style="padding-bottom: 0px;">
							<div id="toolbar" class="btn-group">
								<shiro:hasPermission name="204_QUERY">
									<button id="btn_query" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="204_ADD">
									<button id="btn_add" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="204_EDIT">
									<button id="btn_edit" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="204_DELETE">
									<button id="btn_delete" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="204_ENABLE">
									<button id="btn_enable" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>启用
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="204_FROZEN">
									<button id="btn_disable" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>禁用
									</button>
								</shiro:hasPermission>
							</div>
							<table id="offerTable" class="table">
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="addCityOrderConf.jsp"></jsp:include>
<jsp:include page="editCityOrderConf.jsp"></jsp:include>
