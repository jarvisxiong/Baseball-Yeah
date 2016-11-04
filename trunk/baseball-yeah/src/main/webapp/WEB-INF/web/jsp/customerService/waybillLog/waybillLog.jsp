<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<style type="text/css">
    #formSearch label {
        margin-top: 2px;
    }
</style>

<input type="hidden" class="inmodule" value="wayBillLogModules">
<div class="row">
	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<header class="main-box-header clearfix">
				<%--<div class="row">--%>
					<%--<div class="col-lg-12">--%>
						<%--<ol class="breadcrumb">--%>
							<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
							<%--<li class="active"><span>客服管理</span></li>--%>
						<%--</ol>--%>
						<%--<h1>运单明细</h1>--%>
					<%--</div>--%>
				<%--</div>--%>
				<div class="panel-group" style="margin-bottom: 0px;"
					id="accordion">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
							      <a class="a-clear-search" id="clearSearch">
                                          <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                      </a>
								  <a class="accordion-toggle a-search" data-toggle="collapse"
                                         data-parent="#accordion" href="#wayBillLogcollapseThree"> 查询条件
                                      </a>
							</h4>
						</div>
						<div id="wayBillLogcollapseThree" class="panel-collapse collapse in">
							<div class="panel-body">
								<form id="formSearch" class="form-horizontal" role="form">
									<div class="row">
										<div class="form-group col-xs-6">
                                                  <label for="wayBillNo" class="control-label col-xs-2">运单号：</label>
                                                  <div class="col-xs-10">
                                                      <input type="text" id="wayBillNo" style="width: 240px"
                                                             class="form-control" placeholder="运单号"/>
                                                  </div>
                                              </div>
										<!--  <div class="form-group col-xs-6">
                                                  <div class="input-daterange">
                                                      <label class="control-label col-xs-2" for="startDate">记录时间:</label>
                                                      <div class="col-xs-4">
                                                          <div class="input-group date" id="starttimePicker">
                                                              <input type="text" class="form-control" id="startdate"
                                                                     placeholder="开始日期" name="start" ></input><span
                                                                  class="input-group-addon"><span
                                                                  class="glyphicon-calendar glyphicon"></span> </span>
                                                          </div>
                                                      </div>
                                                      <label class="control-label col-xs-1" for="endDate">至</label>
                                                      <div class="col-xs-4">
                                                          <div class="input-group date" id="endtimePicker">
                                                              <input type="text" class="form-control" name="end"
                                                                     id="enddate" placeholder="结束日期" ></input><span
                                                                  class="input-group-addon"><span
                                                                  class="glyphicon-calendar glyphicon"></span> </span>
                                                          </div>
                                                      </div>
                                                  </div>
                                              </div> -->
                                              <div class="form-group col-xs-6">

                                                  <label for="operation" class="control-label col-xs-2">运单操作类型：</label>
                                                  <div class="col-xs-10">
                                                      <select id='operation' style="width: 240px"
                                                              class="form-control" placeholder="运单操作类型">
                                                          <option value="">全部</option>
                                                          <option value="01">到件扫描</option>
                                                          <option value="02">收件人下单</option>
                                                          <option value="03">众包抢单</option>
                                                          <option value="04">众包取件</option>
                                                          <option value="05">收件人付款</option>
                                                          <option value="06">取消订单</option>
                                                          <option value="07">签收</option>
                                                          <option value="08">问题件</option>
                                                      </select>

                                                  </div>
                                              </div>
									</div>
									<div class="row">
									     <div class="form-group col-xs-6">
															<label for="expressId" class="control-label col-xs-2">快递公司名称：</label>
															<div class="col-xs-10">
																<select id='expressId' style="width: 240px"
																	class="js-example-data-array form-control">
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
				<div id="toolbar" class="btn-group" style=" margin-left: 14px;" >
					<shiro:hasPermission name="151_QUERY">
						<button type="button" id="btn_query" class="btn btn-default">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
						</button>
					 </shiro:hasPermission>
				</div>
				<div class="main-box-body clearfix">
					<div class="row">
						<table id="wayBillLogTable" class="table">

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>