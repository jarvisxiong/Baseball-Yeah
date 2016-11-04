<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="orderStatisticsModules">

<div class="row">
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<div class="main-box-body clearfix">
						<div class="table-responsive">

							<div class="panel-body" style="padding-bottom: 0px;">
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
										<div class="panel-body" style="width: 100%;">
											<form id="formSearch" class="form-horizontal">

												<div class="row">
													<div class="form-group col-xs-6">
                                                            <label for="selcollage" class="control-label col-xs-2">校区：</label>
                                                            <div class="col-xs-10">
                                                                <input type="text" class="form-control" id='selcollage' style="width: 240px" ></input >
                                                            </div>
                                                     </div>
													<div class="form-group col-xs-6">
														<label for="collegeCEO" class="control-label col-xs-2">校园CEO：</label>
														<div class="col-xs-10">
                                                                <select id='collegeCEO' name="collegeCEO"
                                                                        class="form-control" style="width: 240px">
                                                                </select>
                                                            </div>
													</div>
												</div>

												<div class="row">
													<div class="form-group col-xs-6">
														<label for="createStartDate" class="control-label col-xs-2">日期：</label>
														<div class="col-xs-4">
															<div class="input-group date"
																id="createStartDatePicker">
																<input type="text" class="form-control"
																	name="createStartDate" id="createStartDate"
																	placeholder="开始日期"></input> <span
																	class="input-group-addon"> <span
																	class="glyphicon-calendar glyphicon"></span>
																</span>
															</div>
														</div>
														<label class="control-label col-xs-1" for="createEndDate">至</label>
														<div class="col-xs-4">
															<div class="input-group date" id="createEndDatePicker">
																<input type="text" class="form-control"
																	id="createEndDate" placeholder="结束日期"></input><span
																	class="input-group-addon"><span
																	class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>

								<div id="toolbar" class="btn-group">

									<shiro:hasPermission name="188_QUERY">
										<button type="button" id="btn_query" class="btn btn-default">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
										</button>
									</shiro:hasPermission>

									</div>
									<table id="userTable" class="table">
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>