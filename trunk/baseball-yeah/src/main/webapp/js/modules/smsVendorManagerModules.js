define(
		[ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */
			var add_validate = {
	                fields: {
	                	add_smsVendorId: {
	                        validators: {
	                            notEmpty: {
	                                message: '服务商ID不能为空'
	                            }
	                        }
	                    },
	                    add_smsVendorCode: {
	                        validators: {
	                            notEmpty: {
	                                message: '服务商编号不能为空'
	                            }
	                        }
	                    },
	                    add_smsVendorName: {
	                        validators: {
	                            notEmpty: {
	                                message: '服务商名称不能为空'
	                            }
	                        }
	                    },
	                    add_loginName: {
	                        validators: {
	                            notEmpty: {
	                                message: '账号不能为空'
	                            }
	                        }
	                    },
	                    add_password: {
	                        validators: {
	                            notEmpty: {
	                                message: '密码不能为空'
	                            }
	                        }
	                    },
	                    add_weight: {
	                        validators: {
	                        	 regexp: {
	                                 regexp: /^\d{1,10}$/,
	                                 message: '请输入1-10位数字'
	                             },
	                             notEmpty: {
		                                message: '权重不能为空'
		                         }
	                        }
	                    },
	                    add_threshold: {
	                        validators: {
	                        	regexp: {
	                                 regexp: /^\d{1,10}$/,
	                                 message: '请输入1-10位数字'
	                             },
	                        	notEmpty: {
	                                message: '阀值不能为空'
	                            }
	                        }
	                    },
	                    // add_channelCode: {
	                    //     validators: {
	                    //     	notEmpty: {
	                    //             message: '通道号不能为空'
	                    //         }
	                    //     }
	                    // },
	                    add_interfaceAddress: {
	                        validators: {
	                        	notEmpty: {
	                                message: '接口地址不能为空'
	                            }
	                        }
	                    }
	                }
	            };
			var edit_validate = {
	                fields: {
	                    edit_smsVendorCode: {
	                        validators: {
	                            notEmpty: {
	                                message: '服务商编号不能为空'
	                            }
	                        }
	                    },
	                    edit_smsVendorName: {
	                        validators: {
	                            notEmpty: {
	                                message: '服务商名称不能为空'
	                            }
	                        }
	                    },
	                    edit_loginName: {
	                        validators: {
	                            notEmpty: {
	                                message: '账号不能为空'
	                            }
	                        }
	                    },
	                    edit_password: {
	                        validators: {
	                            notEmpty: {
	                                message: '密码不能为空'
	                            }
	                        }
	                    },
	                    edit_weight: {
	                        validators: {
	                        	 regexp: {
	                                 regexp: /^\d{1,10}$/,
	                                 message: '请输入1-10位数字'
	                             },
	                             notEmpty: {
		                                message: '权重不能为空'
		                         }
	                        }
	                    },
	                    edit_threshold: {
	                        validators: {
	                        	regexp: {
	                                 regexp: /^\d{1,10}$/,
	                                 message: '请输入1-10位数字'
	                             },
	                        	notEmpty: {
	                                message: '阀值不能为空'
	                            }
	                        }
	                    },
	                    // edit_channelCode: {
	                    //     validators: {
	                    //     	notEmpty: {
	                    //             message: '通道号不能为空'
	                    //         }
	                    //     }
	                    // },
	                    edit_interfaceAddress: {
	                        validators: {
	                        	notEmpty: {
	                                message: '接口地址不能为空'
	                            }
	                        }
	                    }
	                }
	            };
			return {
				init : function(panel) {
					var self = this;

					base.datagrid({
						url : '/manage/smsvendor/getlist',
						method : "post",
						queryParams: function (params) {
		                    return $.extend(params,
		                        {
		                    	smsVendorId: $(
		                                "#smsVendorId",panel).val(),
                                smsVendorName: $(
		                                "#smsVendorName",panel).val()
		                        });
		                },
						columns : [ {
							checkbox : true
						}, {
							field : 'smsVendorId',
							title : 'Id',
							sortable : true,
						},  {
							field : 'smsVendorCode',
							title : '编号',
							sortable : true
						}, {
							field : 'smsVendorName',
							title : '服务商名称',
							sortable : true
						}, {
							field : 'loginName',
							title : '账号',
							sortable : true
						}, {
							field : 'password',
							title : '密码',
							sortable : true
						}, {
							field : 'level',
							title : '等级',
							sortable : true
						}, {
							field : 'weight',
							title : '权重',
							sortable : true
						}, {
							field : 'threshold',
							title : '阀值',
							sortable : true 
						}, {
							field : 'channelCode',
							title : '通道号',
							sortable : true  
						}, {
							field : 'interfaceAddress',
							title : '接口地址',
							sortable : true  
						},{
							field : 'status',
							title : '状态',
							formatter: function (value,
                                    row, index) {
			                   return value == "1" ? "启用" : "未启用";
							},
							sortable : true 
						}]
					}, '#vendorTable',panel);
					
					
					$("#btn_add",panel).click(function() {
						self.add(panel);
					});
					$("#btn_edit",panel).click(function() {
						self.edit(panel);
					});
					$("#btn_delete",panel).click(function() {
						self.remove(panel);
					});
					$("#btn_query",panel).click(function() {
						$("#vendorTable", panel).bootstrapTable('selectPage', 1);
					});
					$('#addModal',panel).on('shown.bs.modal', function () {
		                $('#addForm',panel).data('bootstrapValidator').resetForm(true);
		            });
					$("#btnClose", panel).click(function () {
						$("#editForm", panel).data("bootstrapValidator").resetForm(true);
					});
					$("#clearSearch", panel).click(function () {
		                base.reset(".main-box-header");
		            });
					$("#editModal", panel).on('hidden.bs.modal', function() {
		                $("#editForm", panel).data('bootstrapValidator').destroy();
		                $("#editForm", panel).data('bootstrapValidator', null);
		            });
				},
				add : function(panel) {
					var self = this;
	                $('#addModal',panel).modal({
	    			    keyboard: false,
	    			    backdrop:'static'
	    			});
					base.validator(add_validate, "#addForm", self.create,panel)
				},
				create : function(panel) {
					$.post("/manage/smsvendor/add",
							{
								"smsVendorId" : $("#add_smsVendorId",panel).val(),
								"smsVendorCode" : $("#add_smsVendorCode",panel).val(),
								"smsVendorName" : $("#add_smsVendorName",panel).val(),
								"loginName" : $("#add_loginName",panel).val(),
								"password" : $("#add_password",panel).val(),
								"level" : $("#add_level",panel).val(),
								"weight" : $("#add_weight",panel).val(),
								"threshold" : $("#add_threshold",panel).val(),
								"channelCode" : $("#add_channelCode",panel).val(),
								"interfaceAddress" : $("#add_interfaceAddress",panel).val(),
								"status" : $("#add_status",panel).prop('checked') ? "1" : "0",
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										base.success("添加成功");
										$("#vendorTable",panel).bootstrapTable('refresh');
										$('#addModal',panel).modal('hide');
									} else {
										base.error(obj.message);
									}
								} else {
									base.error("数据加载失败!");
								}
							});
				},
				
				//更新
				edit : function(panel) {
					var self = this;
					var arrselections = $("#vendorTable",panel).bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
						return;
					}
					if (arrselections.length <= 0) {
						sweetAlert("Oops...", "请选择有效数据!", "error");
						return;
					}
					$("#edit_smsVendorCode",panel).val(arrselections[0].smsVendorCode);
					$("#edit_smsVendorName",panel).val(arrselections[0].smsVendorName);
					$("#edit_loginName",panel).val(arrselections[0].loginName);
					$("#edit_password",panel).val(arrselections[0].password);
					$("#edit_weight",panel).val(arrselections[0].weight);
					$("#edit_threshold",panel).val(arrselections[0].threshold);
					$("#edit_channelCode",panel).val(arrselections[0].channelCode);
					$("#edit_level",panel).val(arrselections[0].level).trigger("change");
					$("#edit_interfaceAddress",panel).val(arrselections[0].interfaceAddress);
					$("#edit_status",panel).prop("checked",arrselections[0].status =="1" ? true : false);
					$("#edit_status_no",panel).prop("checked",arrselections[0].status =="0" ? true : false);
					$("#vendorId",panel).val(arrselections[0].smsVendorId);
					$('#editModal',panel).modal({
	    			    keyboard: false,
	    			    backdrop:'static'
	    			});
					base.validator(edit_validate, '#editForm', self.update,panel)
				},
				update : function(panel) {
					$.post("/manage/smsvendor/update",
							{
								"smsVendorCode" : $("#edit_smsVendorCode",panel).val(),
								"smsVendorName" : $("#edit_smsVendorName",panel).val(),
								"loginName" : $("#edit_loginName",panel).val(),
								"password" : $("#edit_password",panel).val(),
								"weight" : $("#edit_weight",panel).val(),
								"threshold" : $("#edit_threshold",panel).val(),
								"channelCode" : $("#edit_channelCode",panel).val(),
								"level" : $("#edit_level",panel).val(),
								"interfaceAddress" : $("#edit_interfaceAddress",panel).val(),
								"status" : $("#edit_status",panel).prop('checked') ? "1" : "0",
								"smsVendorId" : $("#vendorId",panel).val()
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										base.success("更新成功");
										$("#vendorTable",panel).bootstrapTable('refresh');
										$('#editModal',panel).modal('hide');
										$('#editForm',panel).data('bootstrapValidator').resetForm(true);
									} else {
										base.error(obj.message);
									}
								} else {
									base.error("更新失败!");
								}
							});
				},
				//删除
				remove : function(panel) {
					var arrselections = $("#vendorTable",panel).bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var smsVendorId = arrselections[0].smsVendorId;

					base.cancel({
						title : "删除",
						text : "您确定要删除此服务商信息吗？"
					}, function() {
						$.post("/manage/smsvendor/delete", {
							"smsVendorId" : smsVendorId
						}, function(data, status) {
							if (status == "success") {
								var obj = data;
								if (obj.success == 0) {
									base.success("删除成功");
									$("#vendorTable",panel).bootstrapTable('refresh');
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