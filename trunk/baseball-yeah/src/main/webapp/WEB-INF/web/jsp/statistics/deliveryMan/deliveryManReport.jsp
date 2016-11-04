<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript">
	$(function() {
		if ('${flag}' != null && '${flag}' != '') {
			$('#flag',$('#indextab').tabs('getSelected')).val('${flag}');
		}
	});
</script>
<input type="hidden" class="inmodule" value="deliveryManReportModules" />
 
 
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
									  <a class="a-clear-search" id="clearSearch">
                                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                        </a>
										 <a class="accordion-toggle a-search" data-toggle="collapse"
											data-parent="#accordion" href="#collapseOne"> 查询条件 </a>
									</h4>
								</div>
								<div id="collapseThree" class="panel-collapse collapse in">
									<div class="panel-body"> 
									<!-- 查询条件第一行 -->
											<div class="row"> 											
												<div class="form-group col-xs-6">
													<label for="selectmonth" class="control-label col-xs-2"
														style="margin-top: 0px;">月份:</label>
													<div class="col-xs-10">
														<select id='selectmonth' style="width: 240px"
															class="form-control">
															<option value="请选择">请选择</option>
															<option value="1月">1月</option>
															<option value="2月">2月</option>
															<option value="3月">3月</option>
															<option value="4月">4月</option>
															<option value="5月">5月</option>
															<option value="6月">6月</option>
															<option value="7月">7月</option>
															<option value="8月">8月</option>
															<option value="9月">9月</option>
															<option value="10月">10月</option>
															<option value="11月">11月</option>
															<option value="12月">12月</option>
														</select>
													</div>
												</div>
												<div class="form-group col-xs-6">
													<div class="input-daterange">
														<label for="selcollege" class="control-label col-xs-2" 
															style="margin-top: 0px;">校区:</label>
														<div class="col-xs-10">
														<select id='selcollege' style="width: 240px"
															class="form-control">
															<option value="">全部</option>
														</select>
													</div>
													</div>
												</div>
											</div>
                                           <!-- 查询条件第二行 -->
                                            <div class="row">
												
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
														<div class="col-xs-4">
															<div class="input-group date" id="endtimePicker">
																<input type="text" class="form-control" name="end"
																	id="enddate" placeholder="选择日期"></input> <span
																	class="input-group-addon"><span
																	class="glyphicon-calendar glyphicon"></span> </span>
															</div>
														</div>
													</div>
												</div>
												
												<div class="form-group col-xs-6">
													<label for="selprovince" class="control-label col-xs-2"
														style="margin-top: 0px;">省份:</label>
													<div class="col-xs-10">
														<select id='selprovince' style="width: 240px"
															class="form-control">
															<option value="">全部</option>
														</select>
													</div>
												</div>
											</div>
										 <!-- 查询条件第三行 -->
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
													<label for="selregion" class="control-label col-xs-2"
														style="margin-top: 0px;">大区:</label>
													<div class="col-xs-10">
														<select id='selregion' style="width: 240px"
															class="form-control">
															<option value="">全部</option>
														</select>
													</div>
												</div>
											</div>
											<input type="hidden" value="" id="flag"/>
									</div>
								</div>
							</div>
						</div>
						</header>
						<div id="toolbar" class="btn-group">
							<shiro:hasPermission name="144_QUERY">
								<button type="button" id="btn_query" class="btn btn-default">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
								</button>
							</shiro:hasPermission>
							
						</div>
						<div class="main-box-body clearfix">
							<div class="row">
								<table id="deliveryManTable" class="table">

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	 