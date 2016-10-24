<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/comm.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>系统参数设置</title>
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
	<script
			src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
	<script
			src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
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
										<h1>系统参数维护</h1>
									</div>
								</div>
							</div>
							<div class="table-responsive">
								<div id="toolbar" class="btn-group">
									<shiro:hasPermission name="29_ADD">
										<button data-toggle="modal" id="add" class="btn btn-default">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="29_EDIT">
										<button data-toggle="modal" id="update"
											class="btn btn-default">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
										</button>
									</shiro:hasPermission>
									<shiro:hasPermission name="29_DELETE">
										<button id="remove" class="btn btn-default">
											<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
										</button>
									</shiro:hasPermission>
								</div>
								<table id="expressTable" data-click-to-select="true" style="word-break:break-all;">

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
					<h4 class="modal-title">系统参数操作</h4>
				</div>
				<div class="modal-body">
					<form role="form" id="formBody">
						<div class="form-group">
							<label for="parameterName">参数名</label> <input type="text"
								class="form-control" id="txt_parameterName" name="parameterName"
								placeholder="参数名">
						</div>
						<div class="form-group">
							<label for="value">参数值</label> <input type="text"
								class="form-control" id="txt_value" name="value"
								placeholder="参数值">
						</div>
						<div class="form-group">
							<label for="description"> 描述信息</label> <input type="text"
								class="form-control" id="txt_description" name="description"
								placeholder="描述信息">
						</div>
						<div class="form-group">

							
								<!-- <!-- <label class="checkbox-inline"> <input type="checkbox"
									id="chk_beEnabled"> 是否启用
								</label>
								<input type="checkbox" id="chk_beEnabled" checked="checked"
									name="beEnabled" /> <label>是否启用</label> -->
						<label>是否启用</label> <select class="form-control" id="sel_beEnabled"
								name="beEnabled">
								<option value="1">启用</option>
								<option value="0">禁用</option>
							</select>

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
						var editRow=dataObject.bootstrapTable("getSelections")[0];
						 bindFormData(editRow);
						/*  if(editRow.beEnabled=="1"){
								$("#chk_beEnabled").attr("checked",true);
								
							}
							else{$("#chk_beEnabled").attr("checked",false)} */
						$("#editModal").modal('show');
						}
				});
				$("#add").click(function()
						{
					    isupdate=false;
					     resetFormData("#formBody");
						$("#editModal").modal('show');
						
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
		// 模态框隐藏事件
			$('#editModal').on('hidden.bs.modal', function (e) {
				$("#formBody").data('bootstrapValidator').destroy();
				validate();
	          });
			validate();
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
			url:'/manage/syspara/getlist',
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
				
				/* if(row.beEnabled=="1"){
					$("#chk_beEnabled").attr("checked","checked");
					
				}
				else{$("#chk_beEnabled").attr("checked",false)} */
			}, 
		    columns : [ 
		                {
				checkbox:true,
				width:60
			},{
				field : 'parameterName',
				title : '参数名',
				width:100,
				sortable : true
			}, {
				field : 'value',
				title : '参数值',
				width:100,
				sortable : true
			}, {
				field : 'description',
				title : '描述信息',
				width:200,
				sortable : true,
				searchFormatter:false
			}, {
				field : 'beEnabled',
				title : '是否启用',
				width:80,
				sortable : true,
				searchFormatter:false,
				align:'center',
				formatter:function(value,row,index){
					if(value==1)
						{
						return '<a class="label label-success" style="font-size:12px; cursor:pointer;">启用</a>';
						
						}
					  return '<a class="label label-warning" style="font-size:12px; cursor:poniter;">禁用</a>';
				}
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
		        	parameterName: {
		                validators: {
		                    notEmpty: {
		                        message: '参数名不能为空'
		                    },
		                    stringLength : {
								min : 1,
								max : 30,
								message : '参数名过长'
							}
		                }
		            },
		            value: {
		                validators: {
		                    notEmpty: {
		                        message: '值不能为空'
		                    },
		                    stringLength : {
								min : 1,
								max : 1000,
								message : '值过长'
							}
		                }
		            },
		            description: {
		                validators: {
		                    stringLength : {
								min : 1,
								max : 500,
								message : '描述信息过长'
							}
		                }
		            } 
		        }
		}).on('success.form.bv', function (e) {
			e.preventDefault();
			edit();
		});
	}
	/**
	编辑操作
	*/
	function edit()
	{
		var selectedRow= dataObject.bootstrapTable("getSelections");
		var jsonArray=$("#formBody").serializeArray();
		var jsonData=convertToJsonStr(jsonArray);
		if(!isupdate) 
	    {
		
		$.ajax({
				url:'/manage/syspara/add',
				dataType:'JSON',
				type:'post',
				data:{data:JSON.stringify(jsonData)},
				success:function(data){
				 if(data.success==0)
					 {
					 sweetAlert("","新增成功", "success");
					 dataObject.bootstrapTable('refresh', {silent: true});
					 $("#editModal").modal('hide');
					 resetFormData("#formBody");
					 }
				 else{
					 sweetAlert("",data.message, "info");	 
				 }
				},
				error:function(XMLHttpRequest){
					 sweetAlert("",XMLHttpRequest.responseText, "error");
					 $("#btnSave").attr("disabled",false);
				}
			
			});  
	    }
		else
			{
			jsonData["sysParameterId"]=selectedRow[0].sysParameterId;
			 $.ajax({
					url:'/manage/syspara/update',
					dataType:'JSON',
					type:'post',
					data:{data:JSON.stringify(jsonData)},
					success:function(data){
					 if(data.success==0)
						 {
						 sweetAlert("","修改成功", "success");
						 dataObject.bootstrapTable('refresh', {silent: true});
						 $("#editModal").modal('hide');
						 }
					 else{
					 sweetAlert("",data.message, "info");
					 }
					},
					error:function(XMLHttpRequest){
						 sweetAlert("",XMLHttpRequest.responseText, "error");
						 $("#btnSave").attr("disabled",false);
					}
				});  
			}
	}
	/**
	 * 将表单对象转为json对象
	 */
	function convertToJsonStr(formValues) {
		
		 var result = {};
		
		    $.each(formValues, function() {
		    	result[this.name] = this.value;
		    });
		  /*   result["beEnabled"]=0;
	          if($("#chk_beEnabled").is(":checked")){
	        	  result["beEnabled"]=1;
	          } */
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
								var inputMark = obj.find("input[name='" + name
										+ "']");
								if (inputMark.attr("type") == "checkbox") {
									if(inputMark.length>1){
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
									}
								}
									 else if (inputMark.attr("type") == "textarea") {
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
			$("#txt_parameterName").val("");
			$("#txt_value").val("");
			$("#txt_description").val("");
		/* 	$(':input', formName).not(':button, :submit, :reset, :hidden').val(
					'').removeAttr('checked').removeAttr('selected'); */
		}
		
		function delStore() {
			var selectedRow = dataObject.bootstrapTable("getSelections");
			 
				$.ajax({
					url : "/manage/syspara/delete",
					dataType : 'json',
					data : {
						data : '{"sysParameterId":"'
								+ selectedRow[0].sysParameterId + '"}'
					},
					type : 'post',
					success : function(data) {
						if (data.success == 0) {
							sweetAlert("","删除成功", "success");
							dataObject.bootstrapTable('refresh', {
								silent : true
							});
						} else {
							sweetAlert("",data.message, "info");
						}
					},
					error : function(XMLHttpRequest) {
						 sweetAlert("",XMLHttpRequest.responseText, "warning");
					}
				});
			}
	
	</script>
</body>
</html>