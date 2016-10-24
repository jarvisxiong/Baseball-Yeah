define(
		[ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */
			var add_validate = {
	                fields: {
	                	add_messageTypeId: {
	                        validators: {
	                            notEmpty: {
	                                message: '消息类型不能为空'
	                            }
	                        }
	                    },
	                    add_sendTypeId: {
	                        validators: {
	                            notEmpty: {
	                                message: '发送类型不能为空'
	                            }
	                        }
	                    },
	                    add_level: {
	                        validators: {
	                        	regexp: {
	                                 regexp: /^\d{1,2}$/,
	                                 message: '等级格式不正确,必须为1-2位数字'
	                             },
	                            notEmpty: {
	                                message: '等级不能为空'
	                            }
	                        }
	                    },
	                    add_maxLength: {
	                        validators: {
	                        	regexp: {
	                                 regexp: /^\d+$/,
	                                 message: '最大长度格式不正确,必须为数字'
	                             },
	                            notEmpty: {
	                                message: '最大长度不能为空'
	                            }
	                        }
	                    },
	                    add_sendRoleId: {
	                        validators: {
	                        	 regexp: {
	                                 regexp: /^\d+$/,
	                                 message: '发送角色ID格式不正确,必须为数字'
	                             },
	                             notEmpty: {
	                                message: '发送角色ID不能为空'
	                             }
	                        }
	                    },
	                    add_extendCode: {
	                        validators: {
	                        	notEmpty: {
	                                message: '扩展码不能为空'
	                            }
	                        }
	                    }
	                }
	            };
			var edit_validate = {
	                fields: {
	                	edit_messageTypeId: {
	                        validators: {
	                            notEmpty: {
	                                message: '消息类型不能为空'
	                            }
	                        }
	                    },
	                    edit_sendTypeId: {
	                        validators: {
	                            notEmpty: {
	                                message: '发送类型不能为空'
	                            }
	                        }
	                    },
	                    edit_level: {
	                        validators: {
	                        	regexp: {
	                        		regexp: /^\d{1,2}$/,
	                                 message: '等级格式不正确,必须为1-2位数字'
	                             },
	                            notEmpty: {
	                                message: '等级不能为空'
	                            }
	                        }
	                    },
	                    edit_maxLength: {
	                        validators: {
	                        	regexp: {
	                                 regexp: /^\d+$/,
	                                 message: '最大长度格式不正确,必须为数字'
	                             },
	                            notEmpty: {
	                                message: '最大长度不能为空'
	                            }
	                        }
	                    },
	                    edit_sendRoleId: {
	                        validators: {
	                        	 regexp: {
	                                 regexp: /^\d+$/,
	                                 message: '发送角色ID格式不正确,必须为数字'
	                             },
	                             notEmpty: {
	                                message: '发送角色ID不能为空'
	                             }
	                        }
	                    },
	                    edit_extendCode: {
	                        validators: {
	                        	notEmpty: {
	                                message: '扩展码不能为空'
	                            }
	                        }
	                    }
	                }
	            };
			return {
				init : function(args) {
					var self = this;

					base.datagrid({
						url : '/manage/msgconfig/selectallconfig',
						method : "post",
						queryParams: function (params) {
		                    return $.extend(params,
		                        {
		                    	messageTypeId: $(
		                                "#search_messageTypeId").val(),
                                sendTypeId: $(
		                                "#search_sendTypeId").val()
		                        });
		                },
						columns : [ {
							checkbox : true
						}, {
							field : 'messageTypeId',
							title : '消息类型',
							formatter: function (value,
                                    row, index) {
								var result;
								if(value == "p_noticetype_check"){
									result = "校验通知";
								}else if(value == "p_noticetype_come"){
									result = "到件通知";
								}
			                   return result;
							},
							sortable : true,
                            width:400
						}, {
							field : 'sendTypeId',
							title : '发送类型',
							formatter: function (value,
                                    row, index) {
								var result;
								if(value == "p_sendtype_axp"){
									result = "AXP推送";
								}else if(value == "p_sendtype_sms"){
									result = "短信";
								}else if(value == "p_sendtype_wx"){
									result = "微信推送";
								}
			                   return result;
							},
							sortable : true,
                            width:400
						}, {
							field : 'level',
							title : '等级',
							sortable : true,
                            width:400
						}, {
							field : 'beImmediateSend',
							title : '是否及时发送',
							formatter: function (value,
                                    row, index) {
			                   return value == "1" ? "是" : "否";
							},
							sortable : true,
                            width:400
						}, {
							field : 'maxLength',
							title : '最大长度',
							sortable : true,
                            width:400
						}, {
							field : 'beEnabled',
							title : '是否启用',
							formatter: function (value,
                                    row, index) {
			                   return value == "1" ? "启用" : "未启用";
							},
							sortable : true,
                            width:400
						}, {
							field : 'sendRoleId',
							title : '发送角色ID',
							sortable : true,
                            width:400 
						}, {
							field : 'extendCode',
							title : '扩展码',
							sortable : true,
                            width:400  
						}]
					}, '#msgConfigTable');
					//请求通知类型
					 $.ajax({
			                type: "GET",
			                url: "/manage/msgconfig/getMsgTypeId",
			                dataType: "json",
			                success: function (data) {

			                    $("#search_messageTypeId").select2({
			                        data: data
			                    });
			                    $("#add_messageTypeId").select2({
			                        data: data
			                    });
			                    $("#edit_messageTypeId").select2({
			                        data: data
			                    });
			                }
			            });
					//请求息发送类型
					 $.ajax({
			                type: "GET",
			                url: "/manage/msgconfig/getSendTypeId",
			                dataType: "json",
			                success: function (data) {

			                    $("#search_sendTypeId").select2({
			                        data: data
			                    });
			                    $("#add_sendTypeId").select2({
			                        data: data
			                    });
			                    $("#edit_sendTypeId").select2({
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
						$("#msgConfigTable").bootstrapTable('refresh');
					});
					$('#addModal').on('shown.bs.modal', function () {
						$("#add_messageTypeId").val(" ").trigger("change");
						$("#add_sendTypeId").val(" ").trigger("change");
		                $('#addForm').data('bootstrapValidator').resetForm(true);
		            });
				},
				add : function() {
					var self = this;
	                $('#addModal').modal({
	    			    keyboard: false,
	    			    backdrop:'static'
	    			});
					base.validator(add_validate, "#addForm", self.create)
				},
				create : function() {
					$.post("addconfig",
							{
								"messageTypeId" : $("#add_messageTypeId").val(),
								"sendTypeId" : $("#add_sendTypeId").val(),
								"level" : $("#add_level").val(),
								"beImmediateSend" : $("#add_beImmediateSend").prop('checked') ? "1" : "0",
								"maxLength" : $("#add_maxLength").val(),
								"sendRoleId" : $("#add_sendRoleId").val(),
								"extendCode" : $("#add_extendCode").val(),
								"beEnabled" : $("#add_beEnabled").prop('checked') ? "1" : "0",
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										 base.success("添加成功！");
										$("#msgConfigTable").bootstrapTable('refresh');
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
	        
					var arrselections = $("#msgConfigTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
						return;
					}
					if (arrselections.length <= 0) {
						sweetAlert("Oops...", "请选择有效数据!", "error");
						return;
					}
			        $('#editModal').modal({
	    			    keyboard: false,
	    			    backdrop:'static'
	    			});
					$("#edit_messageTypeId").val(arrselections[0].messageTypeId).trigger("change");
					$("#edit_sendTypeId").val(arrselections[0].sendTypeId).trigger("change");
					$("#edit_maxLength").val(arrselections[0].maxLength);
					$("#edit_sendRoleId").val(arrselections[0].sendRoleId);
					$("#edit_extendCode").val(arrselections[0].extendCode);
					$("#edit_level").val(arrselections[0].level);
					$("#edit_beImmediateSend").prop("checked",arrselections[0].beImmediateSend =="1" ? true : false);
					$("#edit_beImmediateSend_no").prop("checked",arrselections[0].beImmediateSend =="0" ? true : false);
					$("#edit_beEnabled").prop("checked",arrselections[0].beEnabled =="1" ? true : false);
					$("#edit_beEnabled_no").prop("checked",arrselections[0].beEnabled =="0" ? true : false);
					
					$("#messageConfigId").val(arrselections[0].messageConfigId);

					base.validator(edit_validate, '#editForm', self.update)
				},
				update : function() {
					$.post("updateconfig",
							{
								"messageTypeId" : $("#edit_messageTypeId").val(),
								"sendTypeId" : $("#edit_sendTypeId").val(),
								"maxLength" : $("#edit_maxLength").val(),
								"sendRoleId" : $("#edit_sendRoleId").val(),
								"extendCode" : $("#edit_extendCode").val(),
								"level" : $("#edit_level").val(),
								"beImmediateSend" : $("#edit_beImmediateSend").prop('checked') ? "1" : "0",
								"beEnabled" : $("#edit_beEnabled").prop('checked') ? "1" : "0",
								"messageConfigId" : $("#messageConfigId").val()
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										 base.success("更新成功！");
										$("#msgConfigTable").bootstrapTable('refresh');
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
					var arrselections = $("#msgConfigTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var messageConfigId = arrselections[0].messageConfigId;

					base.cancel({
						title : "删除",
						text : "您确定要删除此消息配置信息吗？"
					}, function() {
						$.post("deleteconfig", {
							"messageConfigId" : messageConfigId
						}, function(data, status) {
							if (status == "success") {
								var obj = data;
								if (obj.success == 0) {
									 base.success("删除成功！");
									$("#msgConfigTable").bootstrapTable('refresh');
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