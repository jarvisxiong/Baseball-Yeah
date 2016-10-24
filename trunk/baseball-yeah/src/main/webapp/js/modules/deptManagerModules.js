define([ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */
			var validate = {
				fields : {
					deptCode : {
						validators : {
							notEmpty : {
								message : '部门编码不能为空'
							}
						}
					},
					deptName : {
						validators : {
							notEmpty : {
								message : '部门名称不能为空'
							}
						}
					}
				}
			};

			var datatable;

			return {
				init : function(args) {
					var self = this;
					datatable = base.datagrid({
						url : '/manage/dept/list',
						singleSelect : true,//单选
						showFooter : false,
						search : true,
						showExport: true,// 显示导出按钮
			            exportDataType: "all",// 导出类型
			            pagination : true, // 是否显示分页（*）
			    		sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			    		pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
			            toolbar : '#toolbar',
			            onPageChange : function(number, size){
		                	//表格控件不支持高度自适应
			            	var tableHeight = $('#deptTable').find("thead").height() + $('#deptTable').find("tbody").height()
	                        + 3 + $('#deptTable').parent().parent().parent().parent().find(".clearfix").height();
			            	if ($('#deptTable').bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
	                            tableHeight = 105;
	                        }
		                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
		                        tableHeight += ($('#deptTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
		                    }
		                    $('#deptTable').bootstrapTable('resetView', {"height": tableHeight});
		                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
		                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
		                    } else {
		                    	$(parent.document).find("#mainFrame").height(1150);
		                    }
		                },
						queryParams : function(params) { 
							return $.extend(params, { timeStamp : (new Date()).valueOf()}); 
						},
						columns : [ {
							checkbox : true
						}, {
							field : 'deptId',
							title : 'deptId',
							visible : false,
							searchable : false
						}, {
							field : 'deptCode',
							title : '部门编码',
							sortable : true
						}, {
							field : 'deptName',
							title : '部门名称',
							sortable : true
						} ]
					}, '#deptTable');

					$("#btn_add").click(function() {
						self.add();
					});
					$("#btn_edit").click(function() {
						self.edit();
					});
					$("#btn_delete").click(function() {
						self.remove();
					});
					$('#addModal').on('hidden.bs.modal', function(e) {
						self.hiden();
					});
				},
				add : function() {
					var self = this;
					$('#myModalLabel').html("新增");
					$('#addModal').modal();
					base.validator(validate, "#addForm", self.create);
				},
				create : function() {
					$.post("/manage/dept/add", {
						"deptCode" : $("#deptCode").val(),
						"deptName" : $("#deptName").val()
					}, function(data, status) {
						if (status == "success") {
							var obj = data;
							if (obj.success == 0) {
								$("#deptTable").bootstrapTable('refresh');
								$('#addModal').modal('hide');
								$('#addForm').data('bootstrapValidator').resetForm(true);
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
				edit : function() {
					var self = this;
					var arrselections = $("#deptTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					$("#deptCode").val(arrselections[0].deptCode);
					$("#deptName").val(arrselections[0].deptName);
					$("#deptId").val(arrselections[0].deptId);
					$('#myModalLabel').html("编辑");
					$('#addModal').modal();
					base.validator(validate, '#addForm', self.update);
				},
				update : function() {
					$.post("/manage/dept/update", {
						"deptCode" : $("#deptCode").val(),
						"deptName" : $("#deptName").val(),
						"deptId" : $("#deptId").val()
					}, function(data, status) {
						if (status == "success") {
							var obj = data;
							if (obj.success == 0) {
								$("#deptTable").bootstrapTable('refresh');
								$('#addModal').modal('hide');
								$('#editForm').data('bootstrapValidator').resetForm(true);
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
				remove : function() {
					var arrselections = $("#deptTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var deptId = arrselections[0].deptId;
					base.cancel({
						title : "删除部门信息",
						text : "您确定要删除此部门信息吗？"
					}, function() {
						$.post("/manage/dept/delete", {
							"deptId" : deptId
						}, function(data, status) {
							if (status == "success") {
								var obj = data;
								if (obj.success == 0) {
									$("#deptTable").bootstrapTable('refresh');
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
				hiden : function() {
					$("#deptCode").val('');
					$("#deptName").val('');
					$("#deptId").val('');
					$("#addForm").data('bootstrapValidator').destroy();
				}
			};
		});