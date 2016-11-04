<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div class="row">
	<div class="row">
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<header class="main-box-header clearfix">
					<%--<div class="row">--%>
						<%--<div class="col-lg-12">--%>
							<%--<ol class="breadcrumb">--%>
								<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
								<%--<li class="active"><span>基础设置</span></li>--%>
							<%--</ol>--%>
							<%--<h1>快递站点维护</h1>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="panel-group" style="margin-bottom: 0px;">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="clearSearch"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a> <a class="accordion-toggle a-search" data-toggle="collapse" href="#expressManager-collapseOne"> 查询条件 </a>
								</h4>
							</div>
							<div id="expressManager-collapseOne" class="panel-collapse collapse in">
								<div class="panel-body">
									<form id="formSearch" class="form-horizontal" role="form">
										<div class="row">
											<div class="form-group col-xs-6">
												<label for="phone" class="control-label col-xs-2">手机号：</label>
												<div class="col-xs-10" style="margin-top: 8px;">
													<input type="text" id="phone" style="width: 240px" class="form-control" placeholder="手机号" />
												</div>
											</div>
											<div class="form-group col-xs-6">
												<label for="storeName" class="control-label col-xs-2">站点名称：</label>
												<div class="col-xs-10" style="margin-top: 8px;">
													<input type="text" id="storeName" style="width: 240px" class="form-control" placeholder="站点名称" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group col-xs-6">
												<label for="expressId" class="control-label col-xs-2">主营快递公司:</label>
												<div class="col-xs-10" style="margin-top: 8px;">
													<select id='expressId' style="width: 240px" class="js-example-data-array form-control">
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
				<div id="toolbar" class="btn-group">
					<shiro:hasPermission name="14_QUERY">
						<button id="query" class="btn btn-default">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="14_ADD">
						<button data-toggle="modal" id="add" class="btn btn-default">
							<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="14_EDIT">
						<button data-toggle="modal" id="update" class="btn btn-default">
							<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="14_DELETE">
						<button id="remove" class="btn btn-default">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
						</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="14_PACKETPATTEN">
						<button id="check" class="btn btn-default">
							<span class="glyphicon glyphicon-user" aria-hidden="true"></span>寄/派件模式
						</button>
					</shiro:hasPermission>
				</div>
				<div class="main-box-body clearfix">
					<div class="row">
						<table id="expressTable" data-show-export="true">
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" class="inmodule" value="expressManagerModules">

<div class="modal fade" id="editModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">快递站点操作</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="formBody">
					<div class="form-group">
						<label for="storeCode">快递站点编码</label> <input type="text" class="form-control" id="txt_storeCode" name="storeCode"
							placeholder="快递站点编码">
					</div>
					<div class="form-group">
						<label for="storeName">快递站点名称</label> <input type="text" class="form-control" id="txt_storeName" name="storeName"
							placeholder="快递站点名称">
					</div>
					<div class="form-group">
						<label>状态</label> <select class="form-control" id="sel_status" name="status">
							<option value="1">启用</option>
							<option value="0">禁用</option>
						</select>
					</div>
					<div class="form-group">
						<label>是否开启派件模式</label>
						<div class="radio">
							<input type="radio" name="packetModeMgr" id="pmode" value="1" checked> <label for="pmode">开启</label> <input
								type="radio" name="packetModeMgr" id="pmode2" value="0"><label for="pmode2"> 关闭</label>
						</div>
					</div>
					<div class="form-group">
						<label>是否开启寄件模式</label>
						<div class="radio">
							<input type="radio" name="packetModeSend" id="pmode3" value="1" checked> <label for="pmode3">开启</label> <input
								type="radio" name="packetModeSend" id="pmode4" value="0"><label for="pmode4"> 关闭</label>
						</div>
					</div>
					<div class="form-group">
						<label for="phone">手机号</label> <input type="text" class="form-control" id="txt_phone" name="phone"
							placeholder="手机号">
					</div>
					<div class="form-group">
						<label for="supervisorName">负责人</label> <input type="text" class="form-control" id="txt_supervisorName"
							name="supervisorName" placeholder="负责人">
					</div>
					<div class="form-group form-group-select2">
						<label>学校名称</label> <select style="width: 566px" id="sel1Multi" name="colList" multiple>
						</select>
					</div>
					<div class="form-group form-group-select2">
						<label>主营快递公司</label>
                        <div>
                            <select class="js-example-data-array" style="width: 566px" id="sel3Multi" name="defaultECId">
                            </select>
                        </div>
					</div>
					<div class="form-group form-group-select2">
						<label>兼营快递公司</label> <select style="width: 566px" id="sel2Multi" name="ecList" multiple>
						</select>
					</div>
					<div class="form-group">
						<label for="location">联系地址</label>
						<textarea class="form-control" id="txt_location" name="location" rows="3"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnReset" class="btn btn-default">重置</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" id="btnSave" class="btn btn-primary">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="paket_mode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">寄/派件模式</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>派件模式:</label>
					<div class="radio">
						<input type="radio" name="mgrMode" id="expressManager_p1" value="1" checked> <label for="expressManager_p1">开启</label> <input
							type="radio" name="mgrMode" id="expressManager_p2" value="0"><label for="expressManager_p2"> 关闭</label>
					</div>
				</div>
				<div class="form-group">
					<label>寄件模式:</label>
					<div class="radio">
						<input type="radio" name="sendMode" id="expressManager_p3" value="1" checked> <label for="expressManager_p3">开启</label> <input
							type="radio" name="sendMode" id="expressManager_p4" value="0"><label for="expressManager_p4"> 关闭</label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="btn_mode" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</div>
</div>
