define(
		[ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */
			function getHeight() {
		        return $(window).height() - $('h1').outerHeight(true);
		    }
			
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

			var datatable;

			return {
				init : function(args) {
					var self = this;
					datatable = base.datagrid({
										url : '/manage/express/query',
										singleSelect : true,// 单选
										search : true,
										showRefresh : true,
										pagination : true, // 是否显示分页（*）
										sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
										pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
										striped : true, // 是否显示行间隔色
										showExport: true,// 显示导出按钮
							            exportDataType: "all",// 导出类型
							            showFooter : false,
							            //hight : getHeight(),
							            toolbar : '#toolbar',
							            onPageChange : function(number, size){
						                	//表格控件不支持高度自适应
							            	var tableHeight = $('#expressCompanyTable').find("thead").height() + $('#expressCompanyTable').find("tbody").height()
					                        + 3 + $('#expressCompanyTable').parent().parent().parent().parent().find(".clearfix").height();
							            	if ($('#expressCompanyTable').bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
					                            tableHeight = 105;
					                        }
						                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
						                        tableHeight += ($('#expressCompanyTable').parent().parent().parent().parent().find(".pull-left").height() + 20);
						                    }
						                    $('#expressCompanyTable').bootstrapTable('resetView', {"height": tableHeight});
						                    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
						                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
						                    } else {
						                    	$(parent.document).find("#mainFrame").height(1150);
						                    }
						                },
										queryParams : function(params) { 
											return $.extend(params, { timeStamp : (new Date()).valueOf()}); 
										},
										columns : [
												{
													checkbox : true,
													searchable : false
												},
												{
													field : 'expresscompanyid',
													title : 'expresscompanyid',
													visible : false,
													searchable : false
												},
												{
													field : 'fullname',
													title : '全称',
													sortable : true,
													width : 500
												},
												{
													field : 'simplename',
													title : '简称',
													sortable : true,
													width : 500
												},
												{
													field : 'code',
													title : '编码',
													sortable : true,
													width : 160
												},
												{
													field : 'logo',
													title : 'LOGO',
													width : 200
												},
												{
													field : 'ecGcode',
													title : '编码(old)',
													width : 160
												},
												{
													field : 'beenabled',
													title : '是否启用',
													align : 'center',
													searchable : false,
													searchFormatter : false,
													formatter : function(value,
															row, index) {
														return value == "1" ? "<a class='label label-success'>已启用 </a>"
																: "<a class='label label-warning' >未启用</a>";
													}
												} ]
									}, '#expressCompanyTable');

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
					$.post("/manage/express/add", {
						"fullname" : $("#fullname").val(),
						"simplename" : $("#simplename").val(),
						"code" : $("#code").val(),
						"ecGcode" : $("#ecGcode").val(),
						"hotline" : $("#hotline").val(),
						"logo" : $("#logo").val(),
						"beenabled" : $("#beenabled").prop('checked') ? "1" : "0"
					}, function(data, status) {
						if (status == "success") {
							var obj = data;
							if (obj.success == 0) {
								$("#expressCompanyTable").bootstrapTable('refresh');
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
					var arrselections = $("#expressCompanyTable")
							.bootstrapTable('getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					$("#fullname").val(arrselections[0].fullname);
					$("#simplename").val(arrselections[0].simplename);
					$("#code").val(arrselections[0].code);
					$("#ecGcode").val(arrselections[0].ecGcode);
					$("#hotline").val(arrselections[0].hotline);
					$("#logo").val(arrselections[0].logo);
					$("#beenabled").prop("checked",
							arrselections[0].beenabled == "0" ? false : true);
					$("#expresscompanyid").val(
							arrselections[0].expresscompanyid);
					$('#myModalLabel').html("编辑");
					$('#addModal').modal();
					base.validator(validate, '#addForm', self.update);
				},
				update : function() {
					$.post("/manage/express/update", {
						"fullname" : $("#fullname").val(),
						"simplename" : $("#simplename").val(),
						"code" : $("#code").val(),
						"ecGcode" : $("#ecGcode").val(),
						"hotline" : $("#hotline").val(),
						"logo" : $("#logo").val(),
						"beenabled" : $("#beenabled").prop("checked") ? "1" : "0",
						"expresscompanyid" : $("#expresscompanyid").val()
					}, function(data, status) {
						if (status == "success") {
							var obj = data;
							if (obj.success == 0) {
								$("#expressCompanyTable").bootstrapTable('refresh');
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
					var arrselections = $("#expressCompanyTable")
							.bootstrapTable('getSelections');
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
									$("#expressCompanyTable").bootstrapTable('refresh');
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
					$("#fullname").val("");
					$("#simplename").val("");
					$("#code").val("");
					$("#ecGcode").val("");
					$("#hotline").val("");
					$("#logo").val("");
					$("#beenabled").prop("checked", true);
					$("#expresscompanyid").val("");
					$("#addForm").data('bootstrapValidator').destroy();
				}
			};
		});