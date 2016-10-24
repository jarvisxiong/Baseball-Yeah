define([ 'base' ], function(base) {
	/**
	 * 私有成员定义区域
	 */

	return {
		init : function(args) {
			// / <summary>
			// / 模块初始化方法
			// / </summary>
			// / <param name="args">初始化时传入的参数</param>
			var self = this;

			base.datagrid({
				url : '/manage/quota/selectall',
				method : 'post',
				singleSelect : false,
				columns : [ {
					checkbox : true
				}, {
					field : 'quotaId',
					title : '指标ID',
					sortable : true
				},{
					field : 'quotaName',
					title : '指标名称',
					sortable : true
				}, {
					field : 'fieldName', 
					title : '对应字段',
					sortable : true
				} ]
			}, '#quotaTable');

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
				$("#quotaTable").bootstrapTable('refresh');
			});
			$('#addModal').on('shown.bs.modal', function() {
				$('#addForm').data('bootstrapValidator').resetForm(true);
			});
		},
		add : function() {
			var self = this;
			$('#addModal').modal({
				keyboard : false,
				backdrop : 'static'
			});
			base.validator({
				fields : {
					add_quotaid : {
						validators : {
							notEmpty : {
								message : '指标ID不能为空'
							}
						}
					},
					add_quotaname : {
						validators : {
							notEmpty : {
								message : '指标名称不能为空'
							}
						}
					},
					add_fieldname : {
						validators : {
							notEmpty : {
								message : '对应字段不能为空'
							}
						}
					}
				}
			}, "#addForm", self.create);
		},
		create : function() {
			$.post("/manage/quota/add", {
				"quotaId" : $("#add_quotaid").val(),
				"quotaName" : $("#add_quotaname").val(),
				"fieldName" : $("#add_fieldname").val()
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						base.success("添加成功！");
						$("#quotaTable").bootstrapTable('refresh');
						$("#addModal").modal('hide');
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
			var arrselections = $("#quotaTable").bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
				return;
			}
			if (arrselections.length <= 0) {
				sweetAlert("Oops...", "请选择有效数据!", "error");
				return;
			}
			$('#editModal').modal({
				keyboard : false,
				backdrop : 'static'
			});
			$("#edit_quotaname").val(arrselections[0].quotaName);
			$("#edit_fieldname").val(arrselections[0].fieldName);
			$("#edit_quotaid").val(arrselections[0].quotaId);

			$('#editForm').bootstrapValidator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					edit_quotaid : {
						validators : {
							notEmpty : {
								message : '指标ID不能为空'
							},
                            regexp: {
                                regexp: /^[0-9]*$/,
                                message: '必须为数字'
                            },
							lessThan:{
								value : 2147483647,
								inclusive : true,
								message : '指标ID值超出范围'
							}
						}
					},
					edit_quotaname : {
						validators : {
							notEmpty : {
								message : '指标名称不能为空'
							}
						},
						stringLength: {
                            min: 1,
                            max: 50,
                            message: '指标名称长度必须在1到50位之间'
                        }
					},
					edit_fieldname : {
						validators : {
							notEmpty : {
								message : '对应字段不能为空'
							}
						},
						stringLength: {
                            min: 1,
                            max: 50,
                            message: '对应字段长度必须在1到50位之间'
                        }
					}
				}
			}).on('success.form.bv', function(e) {
				e.preventDefault();
				self.update();
			});
			$('#editModal').modal();
		},
		update : function() {
			$.post("/manage/quota/update", {
				"quotaId" : $("#edit_quotaid").val(),
				"quotaName" : $("#edit_quotaname").val(),
				"fieldName" : $("#edit_fieldname").val(),
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						base.success("更新成功！");
						$("#quotaTable").bootstrapTable('refresh');
						$("#editModal").modal('hide');
						$("#editForm").data("bootstrapValidator").resetForm(true);
					} else {
						base.error(data.message);
					}
				} else {
					base.error("更新失败!");
				}
			});
		},
		remove : function() {
			var arrselections = $("#quotaTable").bootstrapTable('getSelections');
			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}
			var quotaIds = [];
			for (var i = 0; i < arrselections.length; i++) {
				quotaIds.push(arrselections[i].quotaId);
			}
			base.cancel({
				title : "删除",
				text : "您确定要删除吗？"
			}, function() {
				$.post("/manage/quota/delbyids", {
					"quotaIds" : quotaIds.join()
				}, function(data, status) {
					if (status == "success") {
						if (data.success == 0) {
							base.success("删除成功！");
							$("#quotaTable").bootstrapTable('refresh');
						} else {
							base.error(data.message);
						}
					} else {
						base.error("删除失败");
					}
				},'json');
			});
		},
	};
});