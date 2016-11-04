<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="row">
	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<div class="main-box-body clearfix">
					<header class="main-box-header clearfix">
						<%--<div class="row">--%>
							<%--<div class="col-lg-12">--%>
								<%--<ol class="breadcrumb">--%>
									<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
									<%--<li class="active"><span>基础设置</span></li>--%>
								<%--</ol>--%>
								<%--<h1>焦点图管理</h1>--%>
							<%--</div>--%>
						<%--</div>--%>
						<div class="panel-group" style="margin-bottom: 0px;">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a class="a-clear-search" id="clearSearch"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
										</a> <a class="accordion-toggle a-search" data-toggle="collapse" href="#allFocusPic-collapseOne">
											查询条件 </a>
									</h4>
								</div>
								<div id="allFocusPic-collapseOne" class="panel-collapse collapse in">
									<div class="panel-body">
										<form id="formSearch" class="form-horizontal" role="form">
											<div class="row">
												<div class="form-group col-xs-6">
													<label for="title" class="control-label col-xs-2">标题:</label>
													<div class="col-xs-10">
														<input type="text" class="form-control" id="title" style="width: 240px">
													</div>
												</div>

												<div class="form-group col-xs-6">
													<label for="adType" class="control-label col-xs-2">广告类型:</label>
													<div class="col-xs-10">
														<select id="adType" style="width: 240px" class="form-control" placeholder="广告类型">
															<option value="">全部</option>
															<option value="01">首页图片</option>
															<option value="02">其他</option>
														</select>
													</div>
												</div>
											</div>
											<div class="row">

												<div class="form-group col-xs-6">
													<label for="userType" class="control-label col-xs-2">用户类型:</label>
													<div class="col-xs-10">
														<select id="userType" style="width: 240px" class="form-control" placeholder="用户类型">
															<option value="">全部</option>
															<option value="01">货源</option>
															<option value="02">门店</option>
															<option value="03">众包</option>
															<option value="04">收件人</option>
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
								<shiro:hasPermission name="156_ADD">
									<button id="btn_add" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="156_DELETE">
									<button id="btn_delete" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="156_QUERY">
									<button id="btn_query" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
									</button>
								</shiro:hasPermission>
							</div>
							<table id="picTable" class="table">

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" class="inmodule" value="focusPicModules">
<jsp:include page="addFocusPic.jsp"></jsp:include>
