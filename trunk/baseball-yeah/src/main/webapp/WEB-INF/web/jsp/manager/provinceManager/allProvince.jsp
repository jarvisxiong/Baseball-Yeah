<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input type="hidden" class="inmodule" value="provinceManagerModules">

<div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="main-box-body clearfix">
				<%--<div class="row">--%>
					<%--<div class="row">--%>
						<%--<div class="col-lg-12">--%>
							<%--<ol class="breadcrumb">--%>
								<%--<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>--%>
								<%--<li class="active"><span>基础设置</span></li>--%>
							<%--</ol>--%>
							<%--<h1>省市区管理</h1>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
				<div class="table-responsive" id="mainTree">
					<div class="panel-body" style="padding-bottom: 0px;"></div>
					<div id="toolbar" class="btn-group">
						<shiro:hasPermission name="11_ADD">
							<button id="btn_add_a" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增区域信息
							</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="11_ADD">
							<button id="btn_add_p" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增省份信息
							</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="11_ADD">
							<button id="btn_add_c" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增城市信息
							</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="11_ADD">
							<button id="btn_add_co" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增区县信息
							</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="11_EDIT">
							<button id="btn_edit" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
							</button>
						</shiro:hasPermission>
						<shiro:hasPermission name="11_DELETE">
							<button id="btn_delete" type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
						</shiro:hasPermission>
					</div>
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="type" value="" name="type">
<input type="hidden" id="id" value="" name="id">
<input type="hidden" id="pId" value="" name="pId">
<jsp:include page="addProvince.jsp"></jsp:include>
<jsp:include page="editProvince.jsp"></jsp:include>
