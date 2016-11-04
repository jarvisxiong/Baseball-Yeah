<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<input type="hidden" class="inmodule" value="packetStuManagerModules">
<script type="text/javascript">
	$(function() {
		if ('${phone}' != null && '${phone}' != '') {
			$('#phone',$('#indextab').tabs('getSelected')).val('${phone}');
		}
	});
</script>
<div class="row">
	<form action="">
		<div class="row">
			<div class="row">
				<div class="col-lg-12">
					<div class="main-box clearfix">

						<div class="main-box-body clearfix">

							<header class="main-box-header clearfix">
								<%--<div class="row">--%>
									<%--<div class="col-lg-12">--%>
										<%--<ol class="breadcrumb">--%>
											<%--<li><a--%>
												<%--href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
											<%--<li class="active"><span>用户管理</span></li>--%>
										<%--</ol>--%>
										<%--<h1>众包用户管理</h1>--%>
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
												   data-parent="#accordion" href="#packetStucollapseOne"> 查询条件
												</a>
											</h4>
										</div>
										<div id="packetStucollapseOne" class="panel-collapse collapse in">
											<div class="panel-body">
												<form id="formSearch" class="form-horizontal" role="form">
													<div class="row">
														<div class="form-group col-xs-6">
															<label for="realName" class="control-label col-xs-2">真实姓名:</label>
															<div class="col-xs-10">
																<input type="text" class="form-control"
																	name="realName" style="width: 240px" id="realName" />
															</div>
														</div>

														<div class="form-group col-xs-6">
															<label for="gender" class="control-label col-xs-2">性别:</label>
															<div class="col-xs-10">
																<select id='gender' style="width: 240px"
																	class="form-control">
																	<option value="">不限
																	<option value="p_gender_male">男
																	<option value="p_gender_female">女
																	<option value="p_gender_secret">保密
																</select>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group col-xs-6">
															<label for="selcollage" class="control-label col-xs-2">校区:</label>
															<div class="col-xs-10">
																<select id='selcollage' class="js-example-data-array"
																	style="width: 240px"></select>
															</div>
														</div>

														<div class="form-group col-xs-6">
															<label for="selcity" class="control-label col-xs-2">城市:</label>
															<div class="col-xs-10">
																<select id="selcity" class="js-example-data-array"
																	style='width: 240px'>
																</select>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="form-group col-xs-6">
															<div class="input-daterange">
																<label class="control-label col-xs-2" for="startDate">注册日期:</label>
																<div class="col-xs-4">
																	<div class="input-group date" id="starttimePicker">
																		<input type="text" class="form-control"
																			id="startDate" ></input> <span class="input-group-addon"><span
																			class="glyphicon-calendar glyphicon"></span> </span>
																	</div>
																</div>
																<label class="control-label col-xs-1" for="endDate">至</label>
																<div class="col-xs-4">
																	<div class="input-group date" id="endtimePicker">
																		<input type="text" class="form-control" id="endDate"
																			></input> <span class="input-group-addon"><span
																			class="glyphicon-calendar glyphicon"></span> </span>
																	</div>
																</div>
															</div>
														</div>
														<div class="form-group col-xs-6">
															<label for="phone" class="control-label col-xs-2">手机号码:</label>
															<div class="col-xs-10">
																<input type="text" class="form-control" name="phone"
																	style="width: 240px" id="phone" />
															</div>
														</div>
													</div>
													<div class="row">


														<div class="form-group col-xs-6">
															<label for="verifyStatus"
																class="control-label col-xs-2">审核状态:</label>
															<div class="col-xs-10">
																<select id='verifyStatus' style="width: 240px"
																	class="form-control">
																	<option value="">全部</option>
																	<option value="0">未完善</option>
																	<option value="1">审核中</option>
																	<option value="2">审核通过</option>
																	<option value="3">审核失败</option>
																</select>
															</div>
														</div>
														<div class="form-group col-xs-6">
															<label for="grabOrderMode"
																class="control-label col-xs-2">账号状态:</label>
															<div class="col-xs-10">
																<select id='grabOrderMode' style="width: 240px"
																	class="form-control">
																	<option value="">全部</option>
																	<option value="0">封存</option>
																	<option value="1">激活</option>
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
							<div class="table-responsive">
								<div class="panel-body" style="padding-bottom: 0px;">
									<div id="toolbar" class="btn-group">

										<%--<shiro:hasPermission name="143_SEALED">--%>
											<%--<button id="btn_sealed" type="button"--%>
												<%--class="btn btn-default">--%>
												<%--<span class="glyphicon glyphicon-remove"--%>
													<%--aria-hidden="true"></span>账号封存--%>
											<%--</button>--%>
										<%--</shiro:hasPermission>--%>
										<shiro:hasPermission name="143_ACTIVATION">
											<button id="btn_activation" type="button"
												class="btn btn-default">
												<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>账号激活
											</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="143_DELETE">
											<button id="btn_delete" type="button"
												class="btn btn-default">
												<span class="glyphicon glyphicon-remove"
													aria-hidden="true"></span>删除
											</button>
										</shiro:hasPermission>
										<shiro:hasPermission name="143_QUERY">
											<button type="button" id="btn_query"
												class="btn btn-default">
												<span class="glyphicon glyphicon-search"
													aria-hidden="true"></span>查询
											</button>
											<%--<button id="btn_reset" type="button"--%>
												<%--class="btn btn-default">--%>
												<%--<span class="glyphicon glyphicon-refresh"--%>
													<%--aria-hidden="true"></span>重置--%>
											<%--</button>--%>
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
	</form>
</div>
<jsp:include page="grabMode.jsp"></jsp:include>