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
				init : function(panel) {
					var self = this;

					base.datagrid({
						url : '/manage/msgconfig/selectallconfig',
						method : "post",
						queryParams: function (params) {
		                    return $.extend(params,
		                        {
		                    	messageTypeId: $(
		                                "#search_messageTypeId",panel).val(),
                                sendTypeId: $(
		                                "#search_sendTypeId",panel).val()
		                        });
		                },
						columns : [ {
							checkbox : true
						}, {
							field : 'messageTypeId',
							title : '消息类型',
							formatter: function (value,
                                    row, index) {
								var result = value;
								if(value == "p_noticetype_check"){
									result = "校验通知";
								}else if(value == "p_noticetype_come"){
									result = "到件通知";
								}else if(value == "p_noticetype_order"){
									result = "订单通知";
								}else if(value == "p_notice_monitor"){
									result = "监控通知";
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
					}, '#msgConfigTable',panel);
					//请求通知类型
					 $.ajax({
			                type: "GET",
			                url: "/manage/msgconfig/getMsgTypeId",
			                dataType: "json",
			                success: function (data) {

			                    $("#search_messageTypeId",panel).select2({
			                        data: data
			                    });
			                    $("#add_messageTypeId",panel).select2({
			                        data: data
			                    });
			                    $("#edit_messageTypeId",panel).select2({
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

			                    $("#search_sendTypeId",panel).select2({
			                        data: data
			                    });
			                    $("#add_sendTypeId",panel).select2({
			                        data: data
			                    });
			                    $("#edit_sendTypeId",panel).select2({
			                        data: data
			                    });
			                }
			            });
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
						$("#msgConfigTable", panel).bootstrapTable('selectPage', 1);
					});
					$('#addModal',panel).on('shown.bs.modal', function () {
						$("#add_messageTypeId",panel).val(" ").trigger("change");
						$("#add_sendTypeId",panel).val(" ").trigger("change");
		                $('#addForm',panel).data('bootstrapValidator').resetForm(true);
		            });
					$("#clearSearch", panel).click(function () {
		                base.reset(".main-box-header");
		                $('#search_messageTypeId', panel).val(" ").trigger("change");
		                $('#search_sendTypeId', panel).val(" ").trigger("change");
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
					$.post("/manage/msgconfig/addconfig",
							{
								"messageTypeId" : $("#add_messageTypeId",panel).val(),
								"sendTypeId" : $("#add_sendTypeId",panel).val(),
								"level" : $("#add_level",panel).val(),
								"beImmediateSend" : $("#add_beImmediateSend",panel).prop('checked') ? "1" : "0",
								"maxLength" : $("#add_maxLength",panel).val(),
								"sendRoleId" : $("#add_sendRoleId",panel).val(),
								"extendCode" : $("#add_extendCode",panel).val(),
								"beEnabled" : $("#add_beEnabled",panel).prop('checked') ? "1" : "0",
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										 base.success("添加成功！");
										$("#msgConfigTable",panel).bootstrapTable('refresh');
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
	        
					var arrselections = $("#msgConfigTable",panel).bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
						return;
					}
					if (arrselections.length <= 0) {
						sweetAlert("Oops...", "请选择有效数据!", "error");
						return;
					}
			        $('#editModal',panel).modal({
	    			    keyboard: false,
	    			    backdrop:'static'
	    			});
					$("#edit_messageTypeId",panel).val(arrselections[0].messageTypeId).trigger("change");
					$("#edit_sendTypeId",panel).val(arrselections[0].sendTypeId).trigger("change");
					$("#edit_maxLength",panel).val(arrselections[0].maxLength);
					$("#edit_sendRoleId",panel).val(arrselections[0].sendRoleId);
					$("#edit_extendCode",panel).val(arrselections[0].extendCode);
					$("#edit_level",panel).val(arrselections[0].level);
					$("#edit_beImmediateSend",panel).prop("checked",arrselections[0].beImmediateSend =="1" ? true : false);
					$("#edit_beImmediateSend_no",panel).prop("checked",arrselections[0].beImmediateSend =="0" ? true : false);
					$("#edit_beEnabled",panel).prop("checked",arrselections[0].beEnabled =="1" ? true : false);
					$("#edit_beEnabled_no",panel).prop("checked",arrselections[0].beEnabled =="0" ? true : false);
					
					$("#messageConfigId",panel).val(arrselections[0].messageConfigId);

					base.validator(edit_validate, '#editForm', self.update,panel)
				},
				update : function(panel) {
					$.post("/manage/msgconfig/updateconfig",
							{
								"messageTypeId" : $("#edit_messageTypeId",panel).val(),
								"sendTypeId" : $("#edit_sendTypeId",panel).val(),
								"maxLength" : $("#edit_maxLength",panel).val(),
								"sendRoleId" : $("#edit_sendRoleId",panel).val(),
								"extendCode" : $("#edit_extendCode",panel).val(),
								"level" : $("#edit_level",panel).val(),
								"beImmediateSend" : $("#edit_beImmediateSend",panel).prop('checked') ? "1" : "0",
								"beEnabled" : $("#edit_beEnabled",panel).prop('checked') ? "1" : "0",
								"messageConfigId" : $("#messageConfigId",panel).val()
							}, function(data, status) {
								if (status == "success") {
									var obj = data;
									if (obj.success == 0) {
										 base.success("更新成功！");
										$("#msgConfigTable",panel).bootstrapTable('refresh');
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
					var arrselections = $("#msgConfigTable",panel).bootstrapTable(
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
						$.post("/manage/msgconfig/deleteconfig", {
							"messageConfigId" : messageConfigId
						}, function(data, status) {
							if (status == "success") {
								var obj = data;
								if (obj.success == 0) {
									 base.success("删除成功！");
									$("#msgConfigTable",panel).bootstrapTable('refresh');
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