define([ 'base' ], function(base) {
	/**
	 * 私有成员定义区域
	 */
	return {
		init : function(panel) {
			var self = this;
			base.datagrid({
				url : '/manage/goodstype/query',
				method : 'get',
				sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				onPageChange : function(number, size) {
					// client分页方式表格控件不支持高度自适应
					var tableHeight = $('#goodsTypeTable', panel).find("thead", panel).height() + $('#goodsTypeTable', panel).find("tbody", panel).height() + 3 + $('#goodsTypeTable', panel).parent().parent().parent().parent().find(".clearfix", panel).height();
					if ($('#goodsTypeTable', panel).bootstrapTable('getData', panel).length == 0) {// 如果没有数据
						// 给固定文字的高度
						tableHeight = 105;
					}
					if (this.search || this.showExport) {// 如果有自带的功能
						// 把自带功能的元素高度加上
						tableHeight += ($('#goodsTypeTable', panel).parent().parent().parent().parent().find(".pull-left", panel).height() + 20);
					}
					$('#goodsTypeTable', panel).bootstrapTable('resetView', {
						"height" : tableHeight
					});
					if (tableHeight > 900) {// 当高度过高 刷新外面iframe高度
						$(parent.document).find("#mainFrame", panel).height(document.body.scrollHeight);
					} else {
						$(parent.document).find("#mainFrame", panel).height(1150);
					}
					var allSelections =  $('#goodsTypeTable', panel).bootstrapTable("getAllSelections");
					if (allSelections.length > 0) {
						for (var shux in allSelections[0]) {
							var removerCheck = [];
							for (var i = 0; i < allSelections.length; i++) {
								removerCheck.push(allSelections[i][shux]);
							}
							$('#goodsTypeTable', panel).bootstrapTable("uncheckBy", {field: shux, values: removerCheck});
						}
					}
				},
				search : true,
				sortOrder : 'desc',
				sortName : 'updateTime',
				columns : [ {
					checkbox : true
				}, {
					field : 'name',
					title : '物品类型名称',
					sortable : true,
					width : 150
				}, {
					field : 'updateTime',
					title : '更新时间',
					sortable : true,
					width : 150
				}, {
					field : 'description',
					title : '描述',
					sortable : true,
					width : 150
				} ]
			}, '#goodsTypeTable', panel);
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
				$("#goodsTypeTable", panel).bootstrapTable('refresh');
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
			base.reset("#addModal");
			var self = this;
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
					add_name : {
						validators : {
							notEmpty : {
								message : '物品类型名称不能为空'
							},
							stringLength : {
								max : 30,
								message : '物品类型名称不能超过30'
							}
						}
					},
					add_description : {
						validators : {
							stringLength : {
								max : 300,
								message : '物品类型描述不能超过300'
							}
						}
					}
				}
			}, "#addForm", self.create, panel)
		},
		create : function(panel) {
			var json = {
				name : $("#add_name", panel).val(),
				description : $("#add_description", panel).val()
			}
			$.post("/manage/goodstype/insert", {
				data : JSON.stringify(json)
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						$("#goodsTypeTable", panel).bootstrapTable('refresh');
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
			var arrselections = $("#goodsTypeTable", panel).bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				base.error("只能选择一行进行编辑!");
				return;
			}
			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}
			$("#edit_name", panel).val(arrselections[0].name);
			$("#edit_description", panel).val(arrselections[0].description);
			$("#goodsTypeId", panel).val(arrselections[0].goodsTypeId);
			$('#editModal', panel).modal({
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
					edit_name : {
						validators : {
							notEmpty : {
								message : '物品类型名称不能为空'
							},
							stringLength : {
								min : 0,
								max : 30,
								message : '物品类型名称不能超过30'
							}
						}
					},
					edit_description : {
						validators : {
							stringLength : {
								min : 0,
								max : 300,
								message : '物品类型描述不能超过300'
							}
						}
					}
				}
			}, "#editForm", self.update, panel)
		},
		update : function(panel) {
			var json = {
				name : $("#edit_name", panel).val(),
				description : $("#edit_description", panel).val(),
				goodsTypeId : $("#goodsTypeId", panel).val()
			}

			$.post("/manage/goodstype/update", {
				data : JSON.stringify(json)
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						$("#goodsTypeTable", panel).bootstrapTable('refresh');
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
			var arrselections = $("#goodsTypeTable", panel).bootstrapTable('getSelections');

			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}

			base.cancel({
				title : "删除操作",
				text : "您确定要删除该项吗？"
			}, function() {
				$.post("/manage/goodstype/delete", {
					"goodsTypeId" : arrselections[0].goodsTypeId
				}, function(data, status) {
					if (status == "success") {
						if (data.success == 0) {
							$("#goodsTypeTable", panel).bootstrapTable('refresh');
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