<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<input type="hidden" class="inmodule" value="receiveOrderRateModules" />
 
<div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="main-box-body clearfix">
				<header class="main-box-header clearfix">
					<div class="panel-group" style="margin-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="btn_reset">
										<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a>
									<a class="accordion-toggle a-search" data-toggle="collapse"
									   data-parent="#accordion" href="#collapseOne"> 查询条件
									</a>
								</h4>
							</div>

							<div id="collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
										<div class="row">
											<div class="form-group col-xs-6">
												<label for="selcollage" class="control-label col-xs-2">校区：</label>
						                        <div class="col-lg-4">
                                                    <input  id='selcollage'></input >
                                                </div>
						                        <!-- <div class="col-xs-10 div-operation ">
						                            <select id='collegeId' class="js-example-data-array" style="width: 100%"
						                                    name="collegeId" multiple="multiple"></select>
						                        </div> -->
											</div>
											<div class="form-group col-xs-6">
												<label for="startDatePicker" class="control-label col-xs-2">日期：</label>
												<div class="col-xs-4">
													<div class="input-group date" id="startDatePicker">
														<input type="text" class="form-control" name="startDate" readonly id="startDate" placeholder="开始日期" ></input>
														<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span></span>
													</div>
												</div>

												<div class="col-xs-4">
													<div class="input-group date" id="endDatePicker">
														<input type="text" class="form-control" name="endDate" readonly id="endDate" placeholder="结束日期" ></input>
														<span class="input-group-addon"><span class="glyphicon-calendar glyphicon"></span> </span>
													</div>
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
							<shiro:hasPermission name="188_QUERY">
								<button type="button" id="btn_query" class="btn btn-default">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
								</button>
							</shiro:hasPermission>
							<%-- <shiro:hasPermission name="188_QUERY">
								<button id="btn_reset" type="button" class="btn btn-default">
									<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>清空
								</button>
							</shiro:hasPermission> --%>
						</div>
						<table id="receiveOrderRateTable" class="table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>