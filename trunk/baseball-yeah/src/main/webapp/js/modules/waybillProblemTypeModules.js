define([ 'base' ], function(base, audit) {
	/**
	 * 私有成员定义区域
	 */
	return {
		init : function(panel) {
			var self = this;
			base.datagrid({
				url : '/waybill/problem/query',
				sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
				onPageChange : function(number, size) {
					// client分页方式表格控件不支持高度自适应
					var tableHeight = $('#waybillProblemTypeTable', panel).find("thead", panel).height() + $('#waybillProblemTypeTable', panel).find("tbody", panel).height() + 3 + $('#waybillProblemTypeTable', panel).parent().parent().parent().parent().find(".clearfix", panel).height();
					if ($('#waybillProblemTypeTable', panel).bootstrapTable('getData', panel).length == 0) {// 如果没有数据
						// 给固定文字的高度
						tableHeight = 105;
					}
					if (this.search || this.showExport) {// 如果有自带的功能
						// 把自带功能的元素高度加上
						tableHeight += ($('#waybillProblemTypeTable', panel).parent().parent().parent().parent().find(".pull-left", panel).height() + 20);
					}
					$('#waybillProblemTypeTable', panel).bootstrapTable('resetView', {
						"height" : tableHeight
					});
					if (tableHeight > 900) {// 当高度过高 刷新外面iframe高度
						$(parent.document).find("#mainFrame", panel).height(document.body.scrollHeight);
					} else {
						$(parent.document).find("#mainFrame", panel).height(1150);
					}
				},
				queryParams : function(params) {
					return $.extend(params, {
						updateStartTime : $('#startdate', panel).val(),
						updateEndTime : $('#enddate', panel).val(),
						problemTypeGroup : $('#sel_group_search', panel).val()
					});
				},
				columns : [ {
					checkbox : true
				}, {
					field : 'problemTypeCode',
					title : '异常件类型编码',
					sortable : true,
					width : 150
				}, {
					field : 'isEnabled',
					title : '是否启用',
					width : 80,
					searchFormatter : false,
					formatter : function(value, row, index) {
						if (value == 1) {
							return '<a class="label label-success" style="font-size:12px;">已启用</a>';
						}
						return '<a class="label label-warning" style="font-size:12px;">已关闭</a>';
					}
				}, {
					field : 'problemTypeName',
					title : '异常件类型名称',
					sortable : true,
					width : 150
				}, {
					field : 'groupName',
					title : '异常件类型所属组',
					sortable : true,
					width : 150
				}, {
					field : 'nickName',
					title : '登记人姓名',
					sortable : true,
					width : 150
				}, {
					field : 'updateTime',
					title : '登记时间',
					sortable : true,
					width : 150
				}, {
					field : 'sortNo',
					title : '序号',
					sortable : true,
					width : 150
				}
					]
			}, '#waybillProblemTypeTable', panel);
			// 开始时间
			$('#starttimePicker', panel).datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				pickTime : false,
				minView : 2
			})

			// 结束时间
			$('#endtimePicker', panel).datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				pickTime : false,
				minView : 2
			});

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
				$("#waybillProblemTypeTable", panel).bootstrapTable('selectPage', 1);
				$("#waybillProblemTypeTable", panel).bootstrapTable('refresh');
			});
			$("#clearSearch", panel).click(function() {
				base.reset(".main-box-header");
			});

			$('#addModal', panel).on('shown.bs.modal', function() {
				$('#addForm', panel).data('bootstrapValidator').resetForm(true);
			});
			$("#editModal", panel).on('hidden.bs.modal', function() {
                $("#editForm", panel).data('bootstrapValidator').destroy();
                $("#editForm", panel).data('bootstrapValidator', null);
            });

		},
		add : function(panel) {
			var self = this;
			$('#addModal', panel).modal({
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
							between : {
								min : 1,
								max : 9999,
								message : '排序号过长'
							}
						}
					}
				}
			}, "#addForm", self.create, panel);
			// 初始化单选框
			$("#enabled1", panel).prop("checked", true);
			$("#doReturn1", panel).prop("checked", true);
			$("#doUplaodfile1", panel).prop("checked", true);
		},
		create : function(panel) {
			var json = {
				problemTypeCode : $("#add_problemTypeCode", panel).val(),
				problemTypeName : $("#add_problemTypeName", panel).val(),
				problemTypeGroup : $("#sel_group", panel).val(),
				isEnabled : $("#enabled1", panel).prop("checked") == true ? 1 : 0,
				doReturn : $("#doReturn1", panel).prop("checked") == true ? 1 : 0,
				doUplaodfile : $("#doUplaodfile1", panel).prop("checked") == true ? 1 : 0,
				sortNo : $("#add_sortNo", panel).val()
			}
			$.post("/waybill/problem/insert", {
				data : JSON.stringify(json)
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						$("#waybillProblemTypeTable", panel).bootstrapTable('selectPage', 1);
						$("#waybillProblemTypeTable", panel).bootstrapTable('refresh');
						$('#addModal', panel).modal('hide');
						base.success("添加成功！")
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
			var arrselections = $("#waybillProblemTypeTable", panel).bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				base.error("只能选择一行进行编辑!");
				return;
			}
			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}
			$("#update_problemTypeCode", panel).val(arrselections[0].problemTypeCode);
			$("#update_sortNo", panel).val(arrselections[0].sortNo);
			$("#update_problemTypeName", panel).val(arrselections[0].problemTypeName);
			$("#update_sel_group", panel).val(arrselections[0].problemTypeGroup);
			arrselections[0].isEnabled == 1 ? $("#update_enabled1", panel).prop("checked", true) : $("#update_enabled2", panel).prop("checked", true);
			arrselections[0].doReturn == 1 ? $("#update_doReturn1", panel).prop("checked", true) : $("#update_doReturn2", panel).prop("checked", true);
			arrselections[0].doUplaodfile == 1 ? $("#update_doUplaodfile1", panel).prop("checked", true) : $("#update_doUplaodfile2", panel).prop("checked", true);
			$('#editModal', panel).modal({
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
					update_sortNo : {
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
			}, "#editForm", self.update, panel)
		},
		update : function(panel) {
			var json = {
				problemTypeCode : $("#update_problemTypeCode", panel).val(),
				problemTypeName : $("#update_problemTypeName", panel).val(),
				problemTypeGroup : $("#update_sel_group", panel).val(),
				sortNo : $("#update_sortNo", panel).val(),
				isEnabled : $("#update_enabled1", panel).prop("checked") == true ? 1 : 0,
				doReturn : $("#update_doReturn1", panel).prop("checked") == true ? 1 : 0,
				doUplaodfile : $("#update_doUplaodfile1", panel).prop("checked") == true ? 1 : 0
			}

			$.post("/waybill/problem/update", {
				data : JSON.stringify(json)
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						$("#waybillProblemTypeTable", panel).bootstrapTable('selectPage', 1);
						$("#waybillProblemTypeTable", panel).bootstrapTable('refresh');
						$('#editModal', panel).modal('hide');
						$('#editForm', panel).data('bootstrapValidator').resetForm(true);
						base.success("更新成功！")
					} else {
						base.error(data.message);
					}
				} else {
					base.error("更新失败!");
				}
			});
		},
		remove : function(panel) {
			var arrselections = $("#waybillProblemTypeTable", panel).bootstrapTable('getSelections');

			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}

			base.cancel({
				title : "删除操作",
				text : "您确定要删除该项吗？"
			}, function() {
				$.post("/waybill/problem/delete", {
					"problemTypeCode" : arrselections[0].problemTypeCode
				}, function(data, status) {
					if (status == "success") {
						if (data.success == 0) {
							$("#waybillProblemTypeTable", panel).bootstrapTable('selectPage', 1);
							$("#waybillProblemTypeTable", panel).bootstrapTable('refresh');
							base.success("删除成功！")
						} else {
							base.error(data.message);
						}
					} else {
						base.error("删除失败");
					}
				});
			});
		}
	};
});