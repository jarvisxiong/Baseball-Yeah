define(
		[ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */
			var actionData = [{"id":" ","text":"请选择"},{"id":"ADD","text":"增加"},{"id":"DELETE","text":"删除"},{"id":"EDIT","text":"编辑"},{"id":"QUERY","text":"查询"},{"id":"PRINT","text":"打印"},{"id":"EXPORT","text":"导出"},{"id":"AUDIT","text":"审核"},{"id":"INITPWD","text":"初始化密码"}];


			var add_validate = {
	                fields: {
	                	add_caption: {
	                        validators: {
	                            notEmpty: {
	                                message: '菜单项标题 不能为空'
	                            }
	                        }
	                    },
	                    add_hotKey: {
	                        validators: {
	                        }
	                    },
	                    add_action: {
	                        validators: {
	                        }
	                    },
	                    add_hint: {
	                        validators: {
	                        }
	                    },
	                    add_menuCtrlPath: {
	                        validators: {
			                    callback: {
			                        message: '路径不能为空',
			                        callback: function(value, validator) {
			                        	if($("#add_beLeaf").prop('checked') && $("#add_menuCtrlPath").val() && $("#add_menuCtrlPath").val().trim() != ""){
			                        		//叶子节点才有路径
			                        		return true;
			                        	} else if($("#add_beLeaf_no").prop('checked')){
			                        		return true;
			                        	}
			                        	return false;
			                        }
			                    }
	                        }
	                    },
	                    add_menuStatusCode: {
	                        validators: {
	                            notEmpty: {
	                                message: '菜单状态不能为空'
	                            }
	                        }
	                    },
	                    add_menuDevStatusCode: {
	                        validators: {
	                            notEmpty: {
	                                message: '菜单开发状态不能为空'
	                            }
	                        }
	                    },
	                    add_siblingSortNo: {
	                        validators: {
	                        	 regexp: {
	                                 regexp: /^\d+$/,
	                                 message: '兄弟排序号格式不正确'
	                             }
	                        }
	                    }
	                }
	            };
			var edit_validate = {
	                fields: {
	                	edit_caption: {
	                        validators: {
	                            notEmpty: {
	                                message: '菜单项标题 不能为空'
	                            }
	                        }
	                    },
	                    edit_hint: {
	                        validators: {
	                        }
	                    },
	                    edit_hotKey: {
	                        validators: {
	                        }
	                    },
	                    edit_action: {
	                        validators: {
	                        }
	                    },
	                    edit_menuCtrlPath: {
	                        validators: {
	                        	callback: {
			                        message: '路径不能为空',
			                        callback: function(value, validator) {
			                        	if($("#edit_beLeaf").prop('checked') && $("#edit_menuCtrlPath").val() && $("#edit_menuCtrlPath").val().trim() != ""){
			                        		//叶子节点才有路径
			                        		return true;
			                        	} else if($("#edit_beLeaf_no").prop('checked')){
			                        		return true;
			                        	}
			                        	return false;
			                        }
			                    }
	                        }
	                    },
	                    edit_menuStatusCode: {
	                        validators: {
	                            notEmpty: {
	                                message: '菜单状态不能为空'
	                            }
	                        }
	                    },
	                    edit_menuDevStatusCode: {
	                        validators: {
	                            notEmpty: {
	                                message: '菜单开发状态不能为空'
	                            }
	                        }
	                    },
	                    edit_siblingSortNo: {
	                        validators: {
	                        	 regexp: {
	                                 regexp: /^\d+$/,
	                                 message: '兄弟排序号格式不正确'
	                             }
	                        }
	                    }
	                }
	            };
			return {
				init : function(args) {
					var self = this;
					var pIds = null;
					var dIds = null;
					//请求菜单parentId
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getParent",
			                dataType: "json",
			                success: function (data) {
			                	pIds = data;
			                }
		            });
					//请求所属部门信息
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getDept",
			                dataType: "json",
			                success: function (data) {
			                	dIds = data;
			                }
		            });
					base.datagrid({
						url : '/manage/menu/getall',
						method: 'post',
						queryParams: function (params) {
		                    return $.extend(params,
		                        {
		                    		caption: $(
		                                "#caption").val(),
		                        });
		                },
						columns : [ {
							checkbox : true
						}, {
							field : 'menuId',
							title : 'Id',
							sortable : true
						},  {
							field : 'caption',
							title : '标题',
							sortable : true
						}, {
							field : 'menuCtrlPath',
							title : '路径',
							sortable : true
						}, {
							field : 'menuStatusCode',
							title : '状态',
							formatter: function (value,
                                    row, index) {
								var result;
								if(value == "p_menu_disable"){
									result = "未启用";
								}else if(value == "p_menu_maintain"){
									result = "维护";
								}else if(value == "p_menu_normal"){
									result = "正常";
								}
			                   return result;
							},
							sortable : true
						}, {
							field : 'menuDevStatusCode',
							title : '开发状态',
							formatter: function (value,
                                    row, index) {
								var result;
								if(value == "p_devstatus_done"){
									result = "完成";
								}else if(value == "p_devstatus_no"){
									result = "未开发";
								}else if(value == "p_menu_test"){
									result = "测试中";
								}else if(value == "p_menu_dev"){
									result = "开发中";
								}
			                   return result;
							},
							sortable : true
						}, {
							field : 'beLeaf',
							title : '是否叶子节点',
							formatter: function (value,
                                    row, index) {
			                   return value == "1" ? "是" : "否";
							},
							sortable : true,
							width :150
						}, {
							field : 'parentId',
							title : '上级菜单',
							sortable : true,
							formatter: function (value,
                                    row, index) {
								for(var i = 0;i<pIds.length;i++){
									var parentInfo = pIds[i];
									if(parentInfo.id != " "&&parentInfo.id == value){
										return parentInfo.text;
									}
								}
			                   return value;
							}
						}, {
							field : 'siblingSortNo',
							title : '兄弟排序号',
							sortable : true
						}, {
							field : 'belongSiteKind',
							title : '所属部门',
							sortable : true,
							formatter: function (value,
                                    row, index) {
								for(var i = 0;i<dIds.length;i++){
									var deptInfo = dIds[i];
									if(deptInfo.id != " "&& deptInfo.id == value){
										return deptInfo.text;
									}
								}
			                   return value;
							}
						} , {
							field : 'action',
							title : '动作',
							sortable : true
						} ]
					}, '#menuTable');

					$.ajax({
						type: "POST",
						url: "/manage/menu/getEnumPermission",
						dataType: "json",
						success: function (data) {
							$("#add_action").select2({
								data: data
							});
							$("#edit_action").select2({
								data: data
							});
						}
					});

					$('#add_beLeaf')
			        .on('click', function(e) {
			            $('#addForm')
			                .data('bootstrapValidator')
			                .updateStatus('add_menuCtrlPath', 'NOT_VALIDATED', null)
			                .validateField('add_menuCtrlPath');
			        });
					$('#edit_beLeaf')
			        .on('click', function(e) {
			            $('#editForm')
			                .data('bootstrapValidator')
			                .updateStatus('edit_menuCtrlPath', 'NOT_VALIDATED', null)
			                .validateField('edit_menuCtrlPath');
			        });
					 $('#add_beLeaf_no')
				        .on('click', function(e) {
				            $('#addForm')
				                .data('bootstrapValidator')
				                .updateStatus('add_menuCtrlPath', 'NOT_VALIDATED', null);
			        });
					 $('#edit_beLeaf_no')
				        .on('click', function(e) {
				            $('#editForm')
				                .data('bootstrapValidator')
				                .updateStatus('edit_menuCtrlPath', 'NOT_VALIDATED', null);
			        });
					//请求菜单状态
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getMenuStatus",
			                dataType: "json",
			                success: function (data) {

			                    $("#add_menuStatusCode").select2({
			                        data: data
			                    });
			                    $("#edit_menuStatusCode").select2({
			                        data: data
			                    });
			                }
		            });
					
					//请求菜单开发状态
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getMenuDevStatus",
			                dataType: "json",
			                success: function (data) {

			                    $("#add_menuDevStatusCode").select2({
			                        data: data
			                    });
			                    $("#edit_menuDevStatusCode").select2({
			                        data: data
			                    });
			                }
		            });
					
					//请求菜单类型
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getMenuType",
			                dataType: "json",
			                success: function (data) {

			                    $("#add_menuTypeId").select2({
			                        data: data
			                    });
			                    $("#edit_menuTypeId").select2({
			                        data: data
			                    });
			                }
		            });
					$("#btn_add").click(function() {
						self.add();
					});
					$("#btn_edit").click(function() {
						self.edit();
					});
					$("#btn_delete").click(function() {
						self.remove();
					});
					$("#btn_query").click(function() {
						$("#menuTable").bootstrapTable('refresh');
					});
					$('#addModal').on('shown.bs.modal', function () {
						$("#add_menuStatusCode").val(" ").trigger("change");
						$("#add_menuDevStatusCode").val(" ").trigger("change");
						$("#add_menuTypeId").val(" ").trigger("change");
						$("#add_parentId").val(" ").trigger("change");
						$("#add_belongSiteKind").val(" ").trigger("change");
						$("#add_action").val("").trigger("change");
		                $('#addForm').data('bootstrapValidator').resetForm(true);
		            });
				},
				add : function() {
					var self = this;
					//请求菜单parentId
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getParent",
			                dataType: "json",
			                success: function (data) {
			                    $("#add_parentId").select2({
			                        data: data
			                    });
			                }
		            });
					//请求所属部门信息
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getDept",
			                dataType: "json",
			                success: function (data) {
			                    $("#add_belongSiteKind").select2({
			                        data: data
			                    });
			                }
		            });
	                $('#addModal').modal({
	    			    keyboard: false,
	    			    backdrop:'static'
	    			});
					base.validator(add_validate, "#addForm", self.create)
				},
				create : function() {
					$.post("add",
							{
								"menuCtrlPath" : $("#add_beLeaf").prop('checked')?$("#add_menuCtrlPath").val():"",
								"menuStatusCode" : $("#add_menuStatusCode").val(),
								"menuDevStatusCode" : $("#add_menuDevStatusCode").val(),
								"caption" : $("#add_caption").val(),
								"beLeaf" : $("#add_beLeaf").prop('checked') ? "1" : "0",
								"hint" : $("#add_hint").val(),
								"parentId" : $("#add_parentId").val(),
								"menuTypeId" : $("#add_menuTypeId").val(),
								"siblingSortNo" : $("#add_siblingSortNo").val(),
								"dllFile" : $("#add_dllFile").val(),
								"picFile" : $("#add_picFile").val(),
								"hotKey" : $("#add_hotKey").val(),
								"beMandatory" : $("#add_beMandatory").prop('checked') ? "1" : "0",
								"belongSiteKind" : $("#add_belongSiteKind").val(),
								"action" : ( function(){
									var result = "";
									var arr = $("#add_action").val();
									if(arr && arr != ""){
										for(var i =0;i<arr.length;i++){
											if(i == arr.length-1){
												result = result+arr[i];
											}else{
												result = result+arr[i]+",";
											}
										}
									}
									return result;
								 })()
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										 base.success("添加成功！");
										$("#menuTable").bootstrapTable('refresh');
										$('#addModal').modal('hide');
									} else {
										base.error(obj.message);
									}
								} else {
									base.error("数据加载失败!");
								}
							});
				},
				
				//更新
				edit : function() {
					var self = this;
					var arrselections = $("#menuTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
						return;
					}
					if (arrselections.length <= 0) {
						sweetAlert("Oops...", "请选择有效数据!", "error");
						return;
					}
					//请求菜单parentId
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getParent",
			                dataType: "json",
			                data:{"menuId":arrselections[0].menuId},
			                success: function (data) {
			                    $("#edit_parentId").select2({
			                        data: data
			                    });
			                    $("#edit_parentId").val(arrselections[0].parentId ?arrselections[0].parentId:" ").trigger("change");
			                }
		            });
					//请求所属部门信息
					$.ajax({
			                type: "GET",
			                url: "/manage/menu/getDept",
			                dataType: "json",
			                success: function (data) {
			                    $("#edit_belongSiteKind").select2({
			                        data: data
			                    });
			                    $("#edit_belongSiteKind").val(arrselections[0].belongSiteKind?arrselections[0].belongSiteKind:" ").trigger("change");
			                }
		            });
			        $('#editModal').modal({
	    			    keyboard: false,
	    			    backdrop:'static'
	    			});
					$("#edit_menuCtrlPath").val(arrselections[0].menuCtrlPath);
					$("#edit_menuStatusCode").val(arrselections[0].menuStatusCode).trigger("change");
					$("#edit_menuDevStatusCode").val(arrselections[0].menuDevStatusCode).trigger("change");
					$("#edit_caption").val(arrselections[0].caption);
					$("#edit_beLeaf").prop("checked",arrselections[0].beLeaf =="1" ? true : false);
					$("#edit_beLeaf_no").prop("checked",arrselections[0].beLeaf =="0" ? true : false);
					$("#edit_hint").val(arrselections[0].hint);
					$("#edit_menuTypeId").val(arrselections[0].menuTypeId?arrselections[0].menuTypeId:" ").trigger("change");
					$("#edit_siblingSortNo").val(arrselections[0].siblingSortNo);
					$("#edit_dllFile").val(arrselections[0].dllFile);
					$("#edit_picFile").val(arrselections[0].picFile);
					$("#edit_hotKey").val(arrselections[0].hotKey);
					$("#edit_dllFile").val(arrselections[0].dllFile);
					$("#edit_action").val(( function(){
						var arr = arrselections[0].action;
						if(arr && arr != ""){
							return arr.split(",");
						}
						return "";
					 })()).trigger("change");
					$("#edit_beMandatory").prop("checked",arrselections[0].beMandatory =="1" ? true : false);
					$("#edit_beMandatory_no").prop("checked",arrselections[0].beMandatory =="0" ? true : false);
					$("#menuId").val(arrselections[0].menuId);

					base.validator(edit_validate, '#editForm', self.update)
				},
				update : function() {
					$.post("update",
							{
								"menuCtrlPath" : $("#edit_beLeaf").prop('checked')?$("#edit_menuCtrlPath").val():"",
								"menuStatusCode" : $("#edit_menuStatusCode").val(),
								"menuDevStatusCode" : $("#edit_menuDevStatusCode").val(),
								"caption" : $("#edit_caption").val(),
								"beLeaf" : $("#edit_beLeaf").prop('checked') ? "1" : "0",
								"hint" : $("#edit_hint").val(),
								"parentId" : $("#edit_parentId").val(),
								"menuTypeId" : $("#edit_menuTypeId").val(),
								"siblingSortNo" : $("#edit_siblingSortNo").val(),
								"dllFile" : $("#edit_dllFile").val(),
								"picFile" : $("#edit_picFile").val(),
								"hotKey" : $("#edit_hotKey").val(),
								"beMandatory" : $("#edit_beMandatory").prop('checked') ? "1" : "0",
								"belongSiteKind" : $("#edit_belongSiteKind").val(),
								"action" : ( function(){
									var result = "";
									var arr = $("#edit_action").val();
									if(arr && arr != ""){
										for(var i =0;i<arr.length;i++){
											if(i == arr.length-1){
												result = result+arr[i];
											}else{
												result = result+arr[i]+",";
											}
										}
									}
									return result;
								 })(),
								"menuId" : $("#menuId").val()
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										 base.success("更新成功！");
										$("#menuTable").bootstrapTable('refresh');
										$('#editModal').modal('hide');
										$('#editForm').data('bootstrapValidator').resetForm(true);
									} else {
										base.error(obj.message);
									}
								} else {
									base.error("更新失败!");
								}
							});
				},
				//删除
				remove : function() {
					var arrselections = $("#menuTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var menuId = arrselections[0].menuId;

					base.cancel({
						title : "删除",
						text : "您确定要删除此菜单吗？"
					}, function() {
						$.post("delete", {
							"menuId" : menuId
						}, function(data, status) {
							if (status == "success") {
								var obj = data;
								if (obj.success == 0) {
									 base.success("删除成功！");
									$("#menuTable").bootstrapTable('refresh');
								} else {
									base.error(obj.message);
								}
							} else {
								base.error("删除失败");
							}
						});
					});
				},
			};
		});