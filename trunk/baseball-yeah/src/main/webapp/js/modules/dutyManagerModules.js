define([ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */
			var validate = {
				fields : {
					rankNo : {
						validators : {
							notEmpty : {
								message : '序号不能为空'
							},
							regexp : {
								regexp : /^[1-9]\d*$/,
								message : '序号格式不正确'
							},
							lessThan:{
								value : 255,
								inclusive : true,
								message : '序号不能大于255'
							}
						}
					},
					dutyName : {
						validators : {
							notEmpty : {
								message : '职务名称不能为空'
							},
							stringLength: {
	                            min: 1,
	                            max: 30,
	                            message: '职务名称长度必须在1到30位之间'
	                        },
						}
					}
				}
			};

			var datatable;

			return {
				init : function(args) {
					var self = this;
					datatable = base.datagrid({
						url : '/manage/duty/list',
						queryParams : function(params) { 
							return $.extend(params, { timeStamp : (new Date()).valueOf()}); 
						},
						singleSelect : true,// 单选
						showFooter : false,
						search : true,
						showExport: true,// 显示导出按钮
			            exportDataType: "all",// 导出类型
			            toolbar : '#toolbar',
			            onPageChange : function(number, size){
		                	//表格控件不支持高度自适应
			            	var tableHeight = $('#dutyTable').find("thead").height() + $('#dutyTable').find("tbody").height()
	                        + 3 + $('#dutyTable').parent().parent().parent().parent().find(".clearfix").height();
			            	if ($('#dutyTable').bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
	                            tableHeight = 105;
	                        }
		                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
		                        tableHeight += ($('#dutyTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
		                    }
		                    $('#dutyTable').bootstrapTable('resetView', {"height": tableHeight});
		                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
		                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
		                    } else {
		                    	$(parent.document).find("#mainFrame").height(1150);
		                    }
		                },
			            pagination : true, // 是否显示分页（*）
			    		sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			    		pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
						columns : [ {
							checkbox : true
						}, {
							field : 'dutyId',
							title : 'dutyId',
							visible : false,
							searchable : false
						}, {
							field : 'rankNo',
							title : '序号',
							sortable : true
						}, {
							field : 'dutyName',
							title : '职务名称',
							sortable : true
						} ]
					}, '#dutyTable');

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
					$.post("/manage/duty/add", {
						"rankNo" : $("#rankNo").val(),
						"dutyName" : $("#dutyName").val()
					}, function(data, status) {
						if (status == "success") {
							var obj = data;
							if (obj.success == 0) {
								$("#dutyTable").bootstrapTable('refresh');
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
					var arrselections = $("#dutyTable").bootstrapTable('getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑！");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据！");
						return;
					}
					$("#rankNo").val(arrselections[0].rankNo);
					$("#dutyName").val(arrselections[0].dutyName);
					$("#dutyId").val(arrselections[0].dutyId);
					$('#myModalLabel').html("编辑");
					$('#addModal').modal();
					base.validator(validate, '#addForm', self.update);
				},
				update : function() {
					$.post("/manage/duty/update", {
						"rankNo" : $("#rankNo").val(),
						"dutyName" : $("#dutyName").val(),
						"dutyId" : $("#dutyId").val()
					}, function(data, status) {
						if (status == "success") {
							var obj = data;
							if (obj.success == 0) {
								$("#dutyTable").bootstrapTable('refresh');
								$('#addModal').modal('hide');
								$('#addForm').data('bootstrapValidator').resetForm(true);
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
					var arrselections = $("#dutyTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var dutyId = arrselections[0].dutyId;
					base.cancel({
						title : "删除职务信息",
						text : "您确定要删除此职务信息吗？"
					}, function() {
						$.post("/manage/duty/delete", {
							"dutyId" : dutyId
						}, function(data, status) {
							if (status == "success") {
								var obj = data;
								if (obj.success == 0) {
									$("#dutyTable").bootstrapTable('refresh');
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
					$("#rankNo").val('');
					$("#dutyName").val('');
					$("#dutyId").val('');
					$("#addForm").data('bootstrapValidator').destroy();
				}
			};
		});