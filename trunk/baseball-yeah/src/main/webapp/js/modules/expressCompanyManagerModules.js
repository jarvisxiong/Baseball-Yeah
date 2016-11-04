define([ 'base' ], function(base) {
	/**
	 * 私有成员定义区域
	 */

	var validate = {
		fields : {
			fullname : {
				validators : {
					notEmpty : {
						message : '快递公司全称不能为空'
					}
				}
			},
			simplename : {
				validators : {
					notEmpty : {
						message : '快递公司简称不能为空'
					}
				}
			},
			code : {
				validators : {
					notEmpty : {
						message : '快递公司编码不能为空'
					}
				}
			}
		}
	};

	return {
		init : function(panel) {
			var self = this;
			base.datagrid({
				url : '/manage/express/query',
				singleSelect : true,// 单选
				search : true,
				showRefresh : true,
				pagination : true, // 是否显示分页（*）
				sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
				showFooter : false,
				// hight : getHeight(),
				onPageChange : function(number, size) {
					// 表格控件不支持高度自适应
					var tableHeight = $('#expressCompanyTable', panel).find("thead", panel).height() + $('#expressCompanyTable', panel).find("tbody", panel).height() + 3 + $('#expressCompanyTable', panel).parent().parent().parent().parent().find(".clearfix", panel).height();
					if ($('#expressCompanyTable', panel).bootstrapTable('getData', panel).length == 0) {// 如果没有数据
						// 给固定文字的高度
						tableHeight = 105;
					}
					if (this.search || this.showExport) {// 如果有自带的功能
						// 把自带功能的元素高度加上
						tableHeight += ($('#expressCompanyTable', panel).parent().parent().parent().parent().find(".pull-left", panel).height() + 20);
					}
					$('#expressCompanyTable', panel).bootstrapTable('resetView', {
						"height" : tableHeight
					});
					if (tableHeight > 900) {// 当高度过高 刷新外面iframe高度
						$(parent.document).find("#mainFrame", panel).height(document.body.scrollHeight);
					} else {
						$(parent.document).find("#mainFrame", panel).height(1150);
					}
					var allSelections =  $('#expressCompanyTable', panel).bootstrapTable("getAllSelections");
					if (allSelections.length > 0) {
						for (var shux in allSelections[0]) {
							var removerCheck = [];
							for (var i = 0; i < allSelections.length; i++) {
								removerCheck.push(allSelections[i][shux]);
							}
							$('#expressCompanyTable', panel).bootstrapTable("uncheckBy", {field: shux, values: removerCheck});
						}
					}
				},
				queryParams : function(params) {
					return $.extend(params, {
						timeStamp : (new Date()).valueOf()
					});
				},
				columns : [ {
					checkbox : true,
					searchable : false
				}, {
					field : 'expresscompanyid',
					title : 'expresscompanyid',
					visible : false,
					searchable : false
				}, {
					field : 'fullname',
					title : '全称',
					sortable : true,
					width : 500
				}, {
					field : 'simplename',
					title : '简称',
					sortable : true,
					width : 500
				}, {
					field : 'code',
					title : '编码',
					sortable : true,
					width : 160
				}, {
					field : 'logo',
					title : 'LOGO',
					width : 200
				}, {
					field : 'ecGcode',
					title : '编码(old)',
					width : 160
				}, {
					field : 'beenabled',
					title : '是否启用',
					align : 'center',
					searchable : false,
					searchFormatter : false,
					formatter : function(value, row, index) {
						return value == "1" ? "<a class='label label-success'>已启用 </a>" : "<a class='label label-warning' >未启用</a>";
					}
				} ]
			}, '#expressCompanyTable', panel);

			$("#btn_add", panel).click(function() {
				self.add(panel);
			});
			$("#btn_edit", panel).click(function() {
				self.edit(panel);
			});
			$("#btn_delete", panel).click(function() {
				self.remove(panel);
			});
			$('#addModal', panel).on('hidden.bs.modal', function(e) {
				self.hiden(panel);
			});
		},
		add : function(panel) {
			var self = this;
			$('#myModalLabel', panel).html("新增");
			$('#addModal', panel).modal();
			base.validator(validate, "#addForm", self.create, panel);
		},
		create : function(panel) {
			$.post("/manage/express/add", {
				"fullname" : $("#fullname", panel).val(),
				"simplename" : $("#simplename", panel).val(),
				"code" : $("#code", panel).val(),
				"ecGcode" : $("#ecGcode", panel).val(),
				"hotline" : $("#hotline", panel).val(),
				"logo" : $("#logo", panel).val(),
				"beenabled" : $("#beenabled", panel).prop('checked') ? "1" : "0"
			}, function(data, status) {
				if (status == "success") {
					var obj = data;
					if (obj.success == 0) {
						$("#expressCompanyTable", panel).bootstrapTable('refresh');
						$('#addModal', panel).modal('hide');
						$('#addForm', panel).data('bootstrapValidator').resetForm(true);
						base.success("添加成功！");
					} else {
						base.error(obj.message);
					}
				} else {
					base.error("数据加载失败!");
				}
			});
		},
		// 更新
		edit : function(panel) {
			var self = this;
			var arrselections = $("#expressCompanyTable", panel).bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				base.error("只能选择一行进行编辑!");
				return;
			}
			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}
			$("#fullname", panel).val(arrselections[0].fullname);
			$("#simplename", panel).val(arrselections[0].simplename);
			$("#code", panel).val(arrselections[0].code);
			$("#ecGcode", panel).val(arrselections[0].ecGcode);
			$("#hotline", panel).val(arrselections[0].hotline);
			$("#logo", panel).val(arrselections[0].logo);
			$("#beenabled", panel).prop("checked", arrselections[0].beenabled == "0" ? false : true);
			$("#expresscompanyid", panel).val(arrselections[0].expresscompanyid);
			$('#myModalLabel', panel).html("编辑");
			$('#addModal', panel).modal();
			base.validator(validate, '#addForm', self.update, panel);
		},
		update : function(panel) {
			$.post("/manage/express/update", {
				"fullname" : $("#fullname", panel).val(),
				"simplename" : $("#simplename", panel).val(),
				"code" : $("#code", panel).val(),
				"ecGcode" : $("#ecGcode", panel).val(),
				"hotline" : $("#hotline", panel).val(),
				"logo" : $("#logo", panel).val(),
				"beenabled" : $("#beenabled", panel).prop("checked") ? "1" : "0",
				"expresscompanyid" : $("#expresscompanyid", panel).val()
			}, function(data, status) {
				if (status == "success") {
					var obj = data;
					if (obj.success == 0) {
						$("#expressCompanyTable", panel).bootstrapTable('refresh');
						$('#addModal', panel).modal('hide');
						$('#addForm', panel).data('bootstrapValidator').resetForm(true);
						base.success("更新成功！");
					} else {
						base.error(obj.message);
					}
				} else {
					base.error("更新失败!");
				}
			});
		},
		// 删除
		remove : function(panel) {
			var arrselections = $("#expressCompanyTable", panel).bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				base.error("只能选择一行进行编辑!");
				return;
			}
			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}
			var expresscompanyid = arrselections[0].expresscompanyid;
			base.cancel({
				title : "删除快递公司信息",
				text : "您确定要删除此快递公司信息吗？"
			}, function() {
				$.post("/manage/express/delete", {
					"expressCompanyId" : expresscompanyid
				}, function(data, status) {
					if (status == "success") {
						var obj = data;
						if (obj.success == 0) {
							$("#expressCompanyTable", panel).bootstrapTable('refresh');
							base.success("删除成功！");
						} else {
							base.error(obj.message);
						}
					} else {
						base.error("删除失败");
					}
				});
			});
		},
		// model关闭
		hiden : function(panel) {
			$("#fullname", panel).val("");
			$("#simplename", panel).val("");
			$("#code", panel).val("");
			$("#ecGcode", panel).val("");
			$("#hotline", panel).val("");
			$("#logo", panel).val("");
			$("#beenabled", panel).prop("checked", true);
			$("#expresscompanyid", panel).val("");
			$("#addForm", panel).data('bootstrapValidator').destroy();
		}
	};
});