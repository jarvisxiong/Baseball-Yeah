<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<script type="text/javascript">
	$(function() {
		if ('${flag}' != null && '${flag}' != '') {
			$('#flag',$('#indextab').tabs('getSelected')).val('${flag}');
		}
	});
</script>
<input type="hidden" class="inmodule" value="transportReportModules" />

<div class="row">
	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<header class="main-box-header clearfix">
					<div class="panel-group" style="margin-bottom: 0px;" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="clearSearch"> <span
										class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a> <a class="accordion-toggle a-search" data-toggle="collapse"
										data-parent="#accordion" href="#collapseOne"> 查询条件 </a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse in">
								<div class="panel-body">
									<!-- 查询条件第一行 -->
									<div class="row">
										<div class="form-group col-xs-6">
											<label for="selcollege" class="control-label col-xs-2">校区：</label>
											<div class="col-lg-4">
												<input id='selcollege'></input>
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

									</div>
									<input type="hidden" value="" id="flag" />
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
						<table id="transportReportTable" class="table">

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
