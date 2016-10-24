define(
		[ 'base' ],
		function(base, audit) {
			/**
			 * 私有成员定义区域
			 */

			return {
				init : function(args) {
					var self = this;
					base
							.datagrid(
									{
										url : '/waybill/problem/query',
										method : 'get',
										sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
										pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
										queryParams : function(params) {
											return $.extend(params, {
												updateStartTime : $('#startdate').val(),
												updateEndTime : $('#enddate').val(),
												problemTypeGroup : $('#sel_group_search').val()
											});
										},
										columns : [
												{
													checkbox : true
												},
												{
													field : 'problemTypeCode',
													title : '异常件类型编码',
													sortable : true,
													width : 150
												},
												{
													field : 'isEnabled',
													title : '是否启用',
													width : 80,
													searchFormatter : false,
													formatter : function(value,
															row, index) {
														if (value == 1) {
															return '<a class="label label-success" style="font-size:12px;">已启用</a>';

														}
														return '<a class="label label-warning" style="font-size:12px;">已关闭</a>';
													}
												},
												{
													field : 'problemTypeName',
													title : '异常件类型名称',
													sortable : true,
													width : 150
												},
												{
													field : 'groupName',
													title : '异常件类型所属组',
													sortable : true,
													width : 150
												},
												{
													field : 'nickName',
													title : '登记人姓名',
													sortable : true,
													width : 150
												},
												{
													field : 'updateTime',
													title : '登记时间',
													sortable : true,
													width : 150
												},
												{
													field : 'sortNo',
													title : '序号',
													sortable : true,
													width : 150
												},
												{
													field : 'doReturn',
													title : '是否必须退件',
													width : 80,
													searchFormatter : false,
													formatter : function(value,
															row, index) {
														if (value == 1) {
															return '<a class="label label-success" style="font-size:12px;">是</a>';

														}
														return '<a class="label label-warning" style="font-size:12px;">否</a>';
													}
												},
												{
													field : 'doUplaodfile',
													title : '是否需要上传附件',
													width : 80,
													searchFormatter : false,
													formatter : function(value,
															row, index) {
														if (value == 1) {
															return '<a class="label label-success" style="font-size:12px;">是</a>';

														}
														return '<a class="label label-warning" style="font-size:12px;">否</a>';
													}
												} ]
									}, '#waybillProblemTypeTable');
					// 开始时间
					$('#starttimePicker').datetimepicker({
						format : 'yyyy-mm-dd hh:mm:ss',
						autoclose : true,
						pickTime : false,
						minView : 2
					})

					// 结束时间
					$('#endtimePicker').datetimepicker({
						format : 'yyyy-mm-dd hh:mm:ss',
						autoclose : true,
						pickTime : false,
						minView : 2
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
					$("#btn_query").click(
							function() {
								$("#waybillProblemTypeTable").bootstrapTable(
										'refresh');
							});
					$("#clearSearch").click(function() {
						base.reset(".main-box-header");
					});

					$('#addModal').on(
							'shown.bs.modal',
							function() {
								$('#addForm').data('bootstrapValidator')
										.resetForm(true);
							})

				},
				add : function() {
					var self = this;
					$('#addModal').modal({
						keyboard : false,
						backdrop : 'static'
					});
					base.validator({
						fields : {
							add_problemTypeCode : {
								validators : {
									notEmpty : {
										message : '编码不能为空'
									}
								}
							},
							add_problemTypeName : {
								validators : {
									notEmpty : {
										message : '名称不能为空'
									}
								}
							},
							add_sortNo : {
								validators : {
									notEmpty : {
										message : '序号不能为空'
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
					}, "#addForm", self.create)
				},
				create : function() {
					var json = {
						problemTypeCode : $("#add_problemTypeCode").val(),
						problemTypeName : $("#add_problemTypeName").val(),
						problemTypeGroup : $("#sel_group").val(),
						isEnabled : $("#enabled1").prop("checked") == true ? 1
								: 0,
						doReturn : $("#doReturn1").prop("checked") == true ? 1
								: 0,
						doUplaodfile : $("#doUplaodfile1").prop("checked") == true ? 1
								: 0,
						sortNo : $("#add_sortNo").val()
					}
					$.post("/waybill/problem/insert", {
						data : JSON.stringify(json)
					}, function(data, status) {
						if (status == "success") {
							if (data.success == 0) {
								$("#waybillProblemTypeTable").bootstrapTable(
										'refresh');
								$('#addModal').modal('hide');
								base.success("添加成功！")
							} else {
								base.error(data.message);
							}
						} else {
							base.error("数据加载失败!");
						}
					});
				},
				edit : function() {

					var self = this;
					var arrselections = $("#waybillProblemTypeTable")
							.bootstrapTable('getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					$("#update_problemTypeCode").val(
							arrselections[0].problemTypeCode);
					$("#update_sortNo").val(arrselections[0].sortNo);
					$("#update_problemTypeName").val(
							arrselections[0].problemTypeName);
					$("#update_sel_group").val(
							arrselections[0].problemTypeGroup);
					arrselections[0].isEnabled == 1 ? $("#update_enabled1")
							.prop("checked", true) : $("#update_enabled2")
							.prop("checked", true);
					arrselections[0].doReturn == 1 ? $("#update_doReturn1")
							.prop("checked", true) : $("#update_doReturn2")
							.prop("checked", true);
					arrselections[0].doUplaodfile == 1 ? $(
							"#update_doUplaodfile1").prop("checked", true) : $(
							"#update_doUplaodfile2").prop("checked", true);
					$('#editModal').modal({
						keyboard : false,
						backdrop : 'static'
					});
					base.validator({
						fields : {
							update_problemTypeCode : {
								validators : {
									notEmpty : {
										message : '编码不能为空'
									}
								}
							},
							update_problemTypeName : {
								validators : {
									notEmpty : {
										message : '名称不能为空'
									}
								}
							},
							add_sortNo : {
								validators : {
									notEmpty : {
										message : '序号不能为空'
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
					}, "#editForm", self.update)

				},
				update : function() {
					var json = {
						problemTypeCode : $("#update_problemTypeCode").val(),
						problemTypeName : $("#update_problemTypeName").val(),
						problemTypeGroup : $("#update_sel_group").val(),
						sortNo : $("#update_sortNo").val(),
						isEnabled : $("#update_enabled1").prop("checked") == true ? 1
								: 0,
						doReturn : $("#update_doReturn1").prop("checked") == true ? 1
								: 0,
						doUplaodfile : $("#update_doUplaodfile1").prop(
								"checked") == true ? 1 : 0
					}

					$.post("/waybill/problem/update", {
						data : JSON.stringify(json)
					}, function(data, status) {
						if (status == "success") {
							if (data.success == 0) {
								$("#waybillProblemTypeTable").bootstrapTable(
										'refresh');
								$('#editModal').modal('hide');
								$('#editForm').data('bootstrapValidator')
										.resetForm(true);
								base.success("更新成功！")
							} else {
								base.error(data.message);
							}
						} else {
							base.error("更新失败!");
						}
					});
				},
				remove : function() {
					var arrselections = $("#waybillProblemTypeTable")
							.bootstrapTable('getSelections');

					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}

					base
							.cancel(
									{
										title : "删除操作",
										text : "您确定要删除该项吗？"
									},
									function() {
										$
												.post(
														"/waybill/problem/delete",
														{
															"problemTypeCode" : arrselections[0].problemTypeCode
														},
														function(data, status) {
															if (status == "success") {
																if (data.success == 0) {
																	$(
																			"#waybillProblemTypeTable")
																			.bootstrapTable(
																					'refresh');
																	base
																			.success("删除成功！")
																} else {
																	base
																			.error(data.message);
																}
															} else {
																base
																		.error("删除失败");
															}
														});
									});
				}
			};
		});