<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/comm.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱学派属性字典</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/libs/select2.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
<link rel="stylesheet"
	"
	href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/axp/storeUser.js"></script>

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
<script
	src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/remodal/remodal.min.js"></script>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/remadal/remodal-default-theme.css">
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/remadal/remodal.css">
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
											<li><span>系统设置</span></li>
										</ol>
										<h1>属性字典维护</h1>
									</div>
								</div>
							</div>
							<div class="table-responsive">
								<div id="toolbar" class="btn-group">
									<shiro:hasPermission name="28_ADD">
										<button data-toggle="modal" id="add" class="btn btn-default">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="28_EDIT">
										<button data-toggle="modal" id="update"
											class="btn btn-default">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="28_DELETE">
										<button id="remove" class="btn btn-default">
											<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
										</button>
									</shiro:hasPermission>
								</div>
								<table id="expressTable"  data-click-to-select="true" style="word-break:break-all;">

								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>



	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">属性字典操作</h4>
				</div>
				<div class="modal-body">
					<form role="form" id="formBody">
						<div class="form-group">
							<label for="propertyId">属性字典编码</label> <input type="text"
								class="form-control" id="txt_propertyId" name="propertyId"
								placeholder="属性字典编码">
						</div>
						<div class="form-group">
							<label for="propertyShortcode">属性简码</label> <input type="text"
								class="form-control" id="txt_propertyShortcode" name="propertyShortcode"
								placeholder="属性简码">
						</div>
						<div class="form-group">
							<label for="callAlias">调用别名</label> <input type="text"
								class="form-control" id="txt_callAlias" name="callAlias"
								placeholder="调用别名">
						</div>
						<div class="form-group">
							<label for="description"> 描述信息</label> <input type="text"
								class="form-control" id="txt_description" name="description"
								placeholder="描述信息">
						</div>
						<div class="form-group">
							<label for="propertyValue">属性值</label> <input type="text"
								class="form-control" id="txt_propertyValue"
								name="propertyValue" placeholder="属性值">
						</div>
						<div class="form-group">
							<label for="sortNo">序号</label> <input type="text"
								class="form-control" id="txt_sortNo"
								name="sortNo" placeholder="序号">
						</div>
						<div class="modal-footer">
						<button type="button" id="btnReset" onclick="resetFormData('#formBody');" class="btn btn-default">重置</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" id="btnSave" class="btn btn-primary">保存</button>
						</div>
					</form>
				</div>
				
			</div>
		</div>
	</div>
    
   
	<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
	<script type="text/javascript">
	var selectedRow= null;
	var isupdate=false;
	$(function(){
		 //$.ajaxSetup({ cache: false }); 
		init();
		$("#update").click(function()
		{
			isupdate=true;
			if(!dataObject.bootstrapTable("getSelections")||dataObject.bootstrapTable("getSelections").length<=0)
				{
				 sweetAlert("","请选择要操作的项", "warning");
				}
			else
				{
				bindFormData(dataObject.bootstrapTable("getSelections")[0]);
				$("#editModal").modal('show');
				}
			$("#txt_propertyId").attr("readonly",true);
		});
		$("#add").click(function()
				{
			isupdate=false;
			    resetFormData("#formBody");
				$("#editModal").modal('show');
				  $("#txt_propertyId").attr("readonly",false);
				});
		$("#remove").click(function()
				{
			if(!dataObject.bootstrapTable("getSelections")||dataObject.bootstrapTable("getSelections").length<=0)
				{
				sweetAlert("","请选择要操作的项", "warning");
				}
			else  {
				sweetAlert({
					  title: "提示信息",
					  text: "确认删除该项信息?",
					  type: "warning",
					  showCancelButton: true,
					  confirmButtonColor: "#DD6B55",
					  confirmButtonText: "确定",
					  cancelButtonText: "取消",
					  closeOnConfirm: false
					}, function(){
						delStore();
					});
				
			}
				});
	});
	function init()
	{
		   validate();
		// 模态框隐藏事件
			$('#editModal').on('hidden.bs.modal', function (e) {
				$("#formBody").data('bootstrapValidator').destroy();
				validate();
	          });
		   dataObject=$('#expressTable').bootstrapTable({
			height:'960',
			striped:true,
			pagination:true,
			pageSize:20,
			pageList:[20,30,40,60],
			search:true,
			showRefresh:true,
			clickToSelect:true,
			singleSelect:true,
			url:'/manage/propertydict/query',
			method:'post',
			onLoadSuccess: function (data) {
	            var tableHeight = $('#expressTable').find("thead").height() + $('#expressTable').find("tbody").height()
	                + 3 + $('#expressTable').parent().parent().parent().parent().find(".clearfix").height();
	            if (this.search) {
	                tableHeight += ($('#expressTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
	            }
	            $('#expressTable').bootstrapTable('resetView', {"height": tableHeight});
	            if( tableHeight > 900){
	                $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
	            }
	        },
			toolbar:'#toolbar',
			 onCheck:function(row){
				 selectedRow=row;
				bindFormData(row);
			}, 
			onUncheck:function(row)
			{
				selectedRow= null;
			},
		    columns : [ 
		                {
				checkbox:true,
				width:60
			
			},{
				field : 'propertyId',
				title : '属性字典编码',
				width:100,
				sortable : true
			}, {
				field : 'propertyShortcode',
				title : '属性字典简码',
				width:100,
				sortable : true
			}, {
				field : 'callAlias',
				title : '调用别名',
			    width:100,
				sortable : true
			}, {
				field : 'description',
				title : '描述信息',
				width:200,
				sortable : true
			}, {
				field : 'propertyValue',
				title : '属性值',
				width:120,
				sortable : true
			}, 
			{
				field : 'sortNo',
				title : '序号',
				width:80,
				sortable : true
			}
			]
		});

	}
	
	function validate()
	{
		$("#formBody").bootstrapValidator({
			message: '验证未通过',
			 feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {
		        	propertyId: {
		                validators: {
		                    notEmpty: {
		                        message: '属性字典编码不能为空'
		                    },
		                    regexp : {
								regexp : /^[A-Za-z0-9\u9fa5_\u9fa5-]+$/,
								message : '属性字典编码只允许输入数字和字母以及下划线和横杠  '
							},
		              stringLength : {
								min : 1,
								max : 20,
								message : '属性字典编码过长'
							}
						}
					},
					propertyShortcode : {
						validators : {
							notEmpty : {
								message : '属性字典简称不能为空'
							},
							regexp : {
								regexp : /^[A-Za-z0-9\u9fa5_\u9fa5-]+$/,
								message : '属性简码只允许输入数字和字母以及下划线和横杠 '
							},
				              stringLength : {
										min : 1,
										max : 20,
										message : '属性字典简码过长'
									}
						}
					},
					description : {
						validators : {
				              stringLength : {
										min : 1,
										max : 100,
										message : '描述信息过长'
									}
						}
					},
					callAlias : {
						validators : {
							notEmpty : {
								message : '调用别名不能为空'
							},
							 regexp : {
									regexp : /^[A-Za-z0-9\u9fa5_\u9fa5-]+$/,
									message : '调用别名只允许输入数字和字母以及下划线和横杠 '
								},
				              stringLength : {
										min : 1,
										max : 20,
										message : '调用别名过长'
									}
						}
					},
					propertyValue : {
						validators : {
							notEmpty : {
								message : '属性值不能为空'
							},
							stringLength : {
								min : 1,
								max : 100,
								message : '属性值过长'
							}
						}
					},
					sortNo : {
						validators : {
							notEmpty : {
								message : '排序号不能为空'
							},
							regexp : {
								regexp : /^[0-9]*$/,
								message : '序号必须为数字格式'
							},
							stringLength : {
								min : 1,
								max : 4,
								message : '排序号过长'
							}
						}
					}
				}
			}).on('success.form.bv', function(e) {
				e.preventDefault();
				edit();
			});
		}
		/**
		编辑操作
		 */
		function edit() {
			var selectedRow= dataObject.bootstrapTable("getSelections");
			var jsonArray = $("#formBody").serializeArray();
			var jsonData = convertToJsonStr(jsonArray);
			if (!isupdate) {

				$.ajax({
					url : '/manage/propertydict/add',
					dataType : 'JSON',
					type : 'post',
					data : {
						data : JSON.stringify(jsonData)
					},
					success : function(data) {
						if (data.success == 0) {
							sweetAlert("", "新增成功", "success");
							dataObject.bootstrapTable('refresh', {
								silent : true
							});
							$("#editModal").modal('hide');
							resetFormData("#formBody");
						} else {
							sweetAlert("", data.message, "info");
						}
					},
					error : function(XMLHttpRequest) {
						$("#modal2Desc").html(XMLHttpRequest.responseText);
						remadal2.open();
						$("#btnSave").attr("disabled", false);
					}
				});
			} else {
				jsonData["propertyId"] = selectedRow[0].propertyId;
				$.ajax({
					url : '/manage/propertydict/update',
					dataType : 'JSON',
					type : 'post',
					data : {
						data : JSON.stringify(jsonData)
					},
					success : function(data) {
						if (data.success == 0) {
							sweetAlert("", "修改成功", "success");
							dataObject.bootstrapTable('refresh', {
								silent : true
							});
							$("#editModal").modal('hide');
						} else {
							sweetAlert("", data.message, "info");
							$("#btnSave").attr("disabled", false);
						}
					},
					error : function(XMLHttpRequest) {
						sweetAlert("", XMLHttpRequest.responseText, "info");
						$("#btnSave").attr("disabled", false);
					}
				});
			}
		}
		/**
		 * 将表单对象转为json对象
		 */
		function convertToJsonStr(formValues) {

			var result = {};
			var ecGcode = [];
			$.each(formValues, function() {
				result[this.name] = this.value;
			});
			return result;
		}
		/**
		绑定表单的值
		 */
		function bindFormData(jsonData) {
			if (!jsonData)
				return;
			var obj = $("#formBody");
			$
					.each(
							jsonData,
							function(name, value) {
								var inputMark = obj
										.find($.parseHTML("input:[name=" + name
												+ "]"));
								if (inputMark.attr("type") == "checkbox") {
									inputMark
											.each(function() {
												if (Object.prototype.toString
														.apply(value) == [ 'object Array' ]) {
													for (var i = 0; i < value.length; i++) {
														if ($(this).val() == value[i])
															$(this).attr(
																	"checked",
																	"checked");
													}
												} else {
													if ($(this).val() == value)
														$(this).attr("checked",
																"checked");
												}

											});
								} else if (inputMark.attr("type") == "textarea") {
									obj.find("[name=" + name + "]").html(value);
								} else {
									obj.find("[name=" + name + "]").val(value);
								}
							});
		}
		/**
		重置表单
		 */
		function resetFormData(formName) {
			$("#txt_propertyId").val("");
			$("#txt_propertyShortcode").val("");
			$("#txt_callAlias").val("");
			$("#txt_description").val("");
			$("#txt_propertyValue").val("");
			$("#txt_sortNo").val("");
			/* $(':input',formName)  
			 .not(':button, :submit, :reset, :hidden')  
			 .val('')  
			 .removeAttr('checked')  
			 .removeAttr('selected');   */
		}
		function delStore() {

			$
					.ajax({
						url : "/manage/propertydict/delete",
						dataType : 'json',
						data : {
							data : '{"propertyId":"'
									+ dataObject
											.bootstrapTable("getSelections")[0].propertyId
									+ '"}'
						},
						type : 'post',
						success : function(data) {
							if (data.success == 0) {
								sweetAlert("", "删除成功", "success");
								dataObject.bootstrapTable('refresh', {
									silent : true
								});
							} else {
								sweetAlert("", data.message, "info");
							}
						},
						error : function(XMLHttpRequest) {
							sweetAlert("", XMLHttpRequest.responseText, "error");
						}
					});
		}
	</script>
</body>
</html>