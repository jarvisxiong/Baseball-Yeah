define([ 'base' ], function(base) {
	/**
	 * 私有成员定义区域
	 */
	return {
		init : function(panel) {
			// / <summary>
			// / 模块初始化方法
			// / </summary>
			// / <param name="args">初始化时传入的参数</param>
			var self = this;
			var date = new Date();
			// 开始时间
			$('#createStartDatePicker', panel).datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				startView : 2,
				minView : 0,
				todayHighlight : true,
				endDate : new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
			}).on('changeDate', function(e) {
				var startTime = e.date;
				$('#createEndDatePicker', panel).datetimepicker('setStartDate', startTime);
			});

			$('#createEndDatePicker', panel).datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				startView : 2,
				minView : 0,
				todayHighlight : true,
				endDate : new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
			}).on('changeDate', function(e) {
				var endTime = e.date;
				$('#createStartDatePicker', panel).datetimepicker('setEndDate', endTime);
			});

			base.datagrid({
				url : '/business/chl/queryAll',
				queryParams : function(params) {
					return $.extend(params, {
						channelId : $.trim($("#channelId", panel).val()),
						state : $("#state", panel).val() == "请选择" ? "" : $("#state", panel).val(),
						createStartDate : $('#createStartDate', panel).val(),
						createEndDate : $('#createEndDate', panel).val(),
						nickName : $('#nickName', panel).val(),
						connectPhone : $('#connectPhone', panel).val(),
						contacts : $('#contacts', panel).val(),
						state : $('#state', panel).val(),
						channelName : $('#channelName', panel).val()
					});
				},
				onLoadSuccess : function(data) {
					$('.state-success-for-click').each(function() {
						$(this).on("click", function() {
							var channelId = $(this).attr("data");
							self.enableChl(channelId, panel);
						});
					});
					$('.state-warning-for-click').each(function() {
						$(this).on("click", function() {
							var channelId = $(this).attr("data");
							self.cancleChl(channelId, panel);
						});
					});
				},
				columns : [ {
					checkbox : true
				}, {
					field : 'channelId',
					title : '渠道ID',
					sortable : true
				}, {
					field : 'channelName',
					title : '渠道名称',
					sortable : true
				}, {
					field : 'nickName',
					title : '维护人',
					formatter : function(value, row, index) {
						return value == null || value == "" ? "未设置昵称" : value;
					},
					sortable : true
				}, {
					field : 'createDate',
					title : '维护时间',
					sortable : true,
					width: 110,
					align: 'center'
				}, {
					field : 'contacts',
					title : '联系人',
					sortable : true
				}, {
					field : 'connectPhone',
					title : '联系电话',
					sortable : true
				}, {
					field : 'spreadUrl',
					title : '推广URL',
					sortable : true
				}, {
					field : 'targetUrl',
					title : '目标URL',
					sortable : true
				}, {
					field : 'remark',
					title : '备注',
					sortable : true
				}, {
					field : 'state',
					title : '操作',
					formatter : function(value, row, index) {
						return value == "0" ? "<a class='label label-success state-success-for-click' data='" + row.channelId + "'>启用</a>" : "<a class='label label-warning state-warning-for-click' data='" + row.channelId + "'>禁用</a>";
					},
					sortable : true
				}, ]
			}, '#userTable', panel);

			$("#btn_add", panel).click(function() {
				self.add(panel);
			});
			$("#btn_edit", panel).click(function() {
				self.edit(panel);
			});
			$("#btn_delete", panel).click(function() {
				self.remove(panel);
			});
			$("#btn_query", panel).click(function() {
				// $("#userTable", panel).bootstrapTable('refresh');
				$("#userTable", panel).bootstrapTable('selectPage', 1);
			});

			$("#clearSearch", panel).click(function() {
				base.reset(".main-box-body");
				$('#channelId', panel).val('');
				$('#channelName', panel).val("");
				$('#nickName', panel).val("");
				$('#connectPhone', panel).val("");
				$('#contacts', panel).val("");
				$('#state', panel).val("");
				$('#channelName', panel).val("");
				$('#createStartDate', panel).val("");
				$('#createEndDate', panel).val("");
			});

			$('#addModal', panel).on('shown.bs.modal', function() {
				$('#addForm', panel).data('bootstrapValidator').resetForm(true);
				$('#addTargetUrl', panel).val("http://");
				$('#addSpreadUrl', panel).val("http://");
				$.post("/business/chl/getRandomChlCode", "", function(data, status) {
					if (status == "success") {
						if (data != null && data != "") {
							$("#addChannelCode", panel).val(data.substring(1, 7));
						} else {
							base.error("获取渠道编码失败");
						}
					} else {
						base.error("获取渠道编码出错!");
					}
				});
			})
		},
		add : function(panel) {
			var self = this;

			var strRegex = "^(https|http|ftp|rtsp|mms)?://\\w+";
			var strRegex1 = "^(https|http|ftp|rtsp|mms)?://";
			$('#addRemark', panel).val("");

			$('#addModal', panel).modal({
				keyboard : false,
				backdrop : 'static'
			});

			base.validator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					addChannelName : {
						validators : {
							notEmpty : {
								message : '渠道名称不能为空'
							},
							stringLength : {
								min : 0,
								max : 25,
								message : '渠道名称长度不能超过25'
							}
						}
					},
					addChannelCode : {
						validators : {
							notEmpty : {
								message : '渠道编号不能为空'
							},
							callback : {
								message : '渠道编码已存在',
								callback : function(value, validator) {
									var res = true;
									if (value != null && value != "") {
										$.ajax({
											url : '/business/chl/ifExistChannelCode.htm',
											type : 'post',
											dataType : 'json',
											data : {
												"channelCode" : value
											},
											async : false,
											success : function(data, state) {
												if (state == "success") {
													if (data.success != 0) {
														res = false;
													}
												}
											},
										});
										return res;
									}
								}
							}
						}
					},
					addContacts : {
						validators : {
							notEmpty : {
								message : '联系人不能为空'
							}
						}
					},
					addSpreadUrl : {
						validators : {
							notEmpty : {
								message : '推广URL不能为空'
							},
							regexp : {
								regexp : strRegex1,
								message : '推广URL不正确'
							}
						}
					},
					addTargetUrl : {
						validators : {
							notEmpty : {
								message : '目标URL不能为空'
							},
							regexp : {
								regexp : strRegex,
								message : '目标URL不正确'
							},
							stringLength : {
								min : 0,
								max : 100,
								message : '备注长度不能超过100'
							}
						}
					},
					addContactPhone : {
						validators : {
							notEmpty : {
								message : '联系电话不能为空'
							},
							regexp : {
								regexp : /^[1]+[3,5,7,8]+\d{9}$/,
								message : '手机号格式不正确'
							}
						}
					},
					addRemark : {
						validators : {
							stringLength : {
								min : 0,
								max : 100,
								message : '备注长度不能超过100'
							}
						}
					}
				}
			}, '#addForm', self.create, panel);
		},
		create : function(panel) {
			$.post("/business/chl/addBussinessChl", {
				"channelName" : $("#addChannelName", panel).val(),
				"channelCode" : $("#addChannelCode", panel).val(),
				"spreadUrl" : $("#addSpreadUrl", panel).val(),
				"targetUrl" : $("#addTargetUrl", panel).val(),
				"remark" : $("#addRemark", panel).val(),
				"connectPhone" : $("#addContactPhone", panel).val(),
				"contacts" : $("#addContacts", panel).val()
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						swal("增加成功");
						$('#addModal', panel).modal('hide');
						// $("#userTable", panel).bootstrapTable('refresh');
						$("#userTable", panel).bootstrapTable('selectPage', 1);
					} else {
						base.error(data.message);
					}
				} else {
					base.error("数据加载失败!");
				}
			});
		},
		edit : function(panel) {
			var self = this;
			var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
				return;
			}
			if (arrselections.length <= 0) {
				sweetAlert("Oops...", "请选择有效数据!", "error");
				return;
			}
			$('#editRoleID', panel).val(arrselections[0].roleId);
			$('#editRoleName', panel).val(arrselections[0].roleName);
			$('#editRoleType', panel).val(arrselections[0].roleType).trigger("change");
			$('#editBeSysPrivilege', panel).val(arrselections[0].beSysPrivilege).trigger("change");

			$.get("/manage/menu/getRoleMenu", {
				"roleId" : arrselections[0].roleId
			}, function(data, status) {
			});

			// $('#editModal', panel).modal({
			// keyboard : false,
			// backdrop : 'static'
			// });
			base.validator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					editRoleName : {
						validators : {
							notEmpty : {
								message : '角色名不能为空'
							}
						}
					},
					editRoleType : {
						validators : {
							notEmpty : {
								message : '角色类型不能为空'
							}
						}
					},
					editBeSysPrivilege : {
						validators : {
							notEmpty : {
								message : '是否系统权限不能为空'
							}
						}
					}
				}
			}, '#editForm', self.update, panel)
		},
		update : function(panel) {
			var zTree = $.fn.zTree.getZTreeObj("editTreeDemo");
			var checkNodes = new Array();
			var nodes = zTree.getChangeCheckedNodes();
			for (var i = 0; i < nodes.length; i++) {
				checkNodes[i] = nodes[i].idStr;
			}
			$.post("/manage/role/updateRoleAndPermission", {
				"roleId" : $('#editRoleID', panel).val(),
				"roleName" : $("#editRoleName", panel).val(),
				"roleType" : $("#editRoleType", panel).val(),
				"beSysPrivilege" : $("#editBeSysPrivilege", panel).val() == "请选择" ? "" : $("#editBeSysPrivilege", panel).val(),
				"checkNodes" : checkNodes.toString()
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						// $('#editModal', panel).modal('hide');
						// $("#userTable", panel).bootstrapTable('refresh');
						$("#userTable", panel).bootstrapTable('selectPage', 1);
						// $('#editForm',
						// panel).data('bootstrapValidator').resetForm(true);
						base.success("编辑成功");
					} else {
						base.error(data.message);
					}
				} else {
					base.error("更新失败!");
				}
			});
		},
		remove : function(panel) {
			var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				base.error("只能选择一行进行编辑!");
				return;
			}
			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}
			var channelId = arrselections[0].channelId;

			base.cancel({
				title : "删除商户渠道",
				text : "您确定要删除此商户渠道吗？"
			}, function() {
				$.post("/business/chl/delBussinessChl", {
					"channelId" : channelId
				}, function(data, status) {
					if (status == "success") {
						if (data.success == 0) {
							base.success("删除成功");
							// $("#userTable", panel).bootstrapTable('refresh');
							$("#userTable", panel).bootstrapTable('selectPage', 1);
						} else {
							base.error(data.message);
						}
					} else {
						base.error("删除失败");
					}
				});
			});
		},
		enableChl : function(channelId, panel) {
			$.post("/business/chl/cancleBussinessChl", {
				"channelId" : channelId
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						// $('#editModal', panel).modal('hide');
						// $("#userTable", panel).bootstrapTable('refresh');
						$("#userTable", panel).bootstrapTable('selectPage', 1);
						// $('#editForm',
						// panel).data('bootstrapValidator').resetForm(true);
						base.success("启用成功");
					} else {
						base.error(data.message);
					}
				} else {
					base.error("启用失败!");
				}
			});
		},
		cancleChl : function(channelId, panel) {
			$.post("/business/chl/enabledBussinessChl", {
				"channelId" : channelId
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						// $('#editModal', panel).modal('hide');
						// $("#userTable", panel).bootstrapTable('refresh');
						$("#userTable", panel).bootstrapTable('selectPage', 1);
						// $('#editForm',
						// panel).data('bootstrapValidator').resetForm(true);
						base.success("禁用成功");
					} else {
						base.error(data.message);
					}
				} else {
					base.error("禁用失败!");
				}
			});
		}
	};
});

function changeSpreadUrl() {
	var channelCode = $('#addChannelCode').val();
	var targetUrl = $('#addTargetUrl').val();
	var holeTargetUrl = "";
	if (channelCode !== null && targetUrl != null && channelCode != "" && targetUrl != "" && targetUrl != "http://") {
		if (targetUrl.indexOf("?") >= 0) {
			holeTargetUrl = targetUrl + "&channel=" + channelCode;
		} else {
			holeTargetUrl = targetUrl + "?channel=" + channelCode;
		}
		$.post("/manage/url/getshorturl.htm", {
			"longurl" : encodeURIComponent(holeTargetUrl)
		}, function(data, state) {
			if (state == "success") {
				$('#addSpreadUrl').val(data);
			}
		});
	}
}

function refreshChannelCode() {
	$.post("/business/chl/getRandomChlCode", "", function(data, status) {
		if (status == "success") {
			if (data != null && data != "") {
				$("#addChannelCode").val(data.substring(1, 7));
				changeSpreadUrl();
			} else {
				base.error("获取渠道编码失败");
			}
		} else {
			base.error("获取渠道编码出错!");
		}
	});
}