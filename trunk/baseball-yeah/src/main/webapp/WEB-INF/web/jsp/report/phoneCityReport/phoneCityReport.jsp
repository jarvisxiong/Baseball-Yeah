<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="phoneCityReportModules" />
 
 
		<div class="row">
			<div class="row">
				<div class="col-lg-12">
					<div class="main-box clearfix">
						<header class="main-box-header clearfix">
						<%--<div class="row">--%>
							<%--<div class="col-lg-12">--%>
								<%--<ol class="breadcrumb">--%>
									<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
									<%--<li class="active"><span>报表管理</span></li>--%>
								<%--</ol>--%>
								<%--<h1>手机号码数据报表</h1>--%>
							<%--</div>--%>
						<%--</div>--%>
						<div class="panel-group" style="margin-bottom: 0px;"
							id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="a-clear-search" id="clearSearch"> <span
											class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
										</a> <a class="accordion-toggle a-search" data-toggle="collapse"
											data-parent="#accordion" href="#phoneCityReportcollapseOne"> 查询条件 </a>
									</h4>
								</div>
								<div id="phoneCityReportcollapseOne" class="panel-collapse collapse in">
									<div class="panel-body"> 
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
	 