<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/comm.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>爱学派</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/libs/select2.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/axp/base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugs/JSON-js-master/json_parse.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
<link
	href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
<script src="<%=request.getContextPath()%>/js/require.js"
	data-main="<%=request.getContextPath()%>/js/modules/main"></script>
		<script
	src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
<script
	src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>


</head>
<body>
	<div id="page-wrapper" class="container">
		<div class="row">


			<div class="row">
				<div class="col-lg-12">
					<div class="main-box clearfix">
						<div class="main-box-body clearfix">
							<div class="row">
								<div class="row">
									<div class="col-lg-12">
										<ol class="breadcrumb">
											<li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
											<li class="active"><span>报表管理</span></li>
										</ol>
										<h1>学校手机号统计</h1>
									</div>
								</div>
							</div>
							<div class="table-responsive">
                                 <div class="panel-body" style="padding-bottom: 0px;">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">查询条件</div>
                                            <div class="panel-body">
                                                <form id="formSearch" class="form-horizontal">
                                                    <div class="form-group">
                                                        <label for="nickname"
                                                               class="col-lg-1 control-label label-search" style="float:left;">学校名称:</label>
                                                        <div class="col-lg-2">
                                                        <div style="margin-top: 10px; float:left;">
                                                        
                                                       <select style="width:200px;" id="collegeId">
							                            </select>
																<!-- <select id='collegeId' style="width: 200px"
																	class="js-example-data-array form-control">
																</select> -->
														</div>
                                                        </div>
                                                        <div class="col-log-2">
                                                         <div style="margin-top: 5px;float:left;margin-left:5px;">
																<button type="button" id="btn_query"
																	class="btn btn-success">查询</button>
															</div>
															</div>
                                                    </div>

                                                </form>
                                            </div>
                                        </div>
								<table id="schoolPhoneReportTable"  class="table">
								</table>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="showModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog" style="width:800px;">
			<div class="modal-content" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">手机号统计明细</h4>
				</div>
				<div class="modal-body" >
					<table id="schoolPhoneDetailReport" class="table">
				    </table>
				</div>
				
			</div>
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
	<script type="text/javascript">
	$(function() {
		
		$("#collegeId").select2({
			placeholder: '选择学校',
			allowClear: true
		});
		 /**
		加载学校
		 */
		$.ajax({
			url : "/manage/college/queryselect",
			dataType : 'json',
			type : 'post',
			success : function(data) {
				$.each(data.data, function(i, item) {
					$("#collegeId").append("<option value='" + item.id + "'>&nbsp;"
							+ item.text + "</option>");
				});
				$('#collegeId').select2("val", null); 
			}
		});
		$("#btn_query").click(function(){
			require([ 'schoolPhoneReportModules' ], function(schoolPhoneReport) {
				schoolPhoneReport.init();
			});
		});
	});
</script>
</body>
</html>
