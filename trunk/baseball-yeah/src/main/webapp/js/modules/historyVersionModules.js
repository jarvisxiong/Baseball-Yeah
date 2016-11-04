define([ 'base' ], function(base, audit) {
	/**
	 * 私有成员定义区域
	 */
	return {
		init : function(panel) {
			var self = this;

			base.datagrid({
				url : '/version/list',
				method : 'get',
				sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
				pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
				onPageChange : function(number, size) {
					// client分页方式表格控件不支持高度自适应
					var tableHeight = $('#historyVersionTable', panel).find("thead", panel).height() + $('#historyVersionTable', panel).find("tbody", panel).height() + 3 + $('#historyVersionTable', panel).parent().parent().parent().parent().find(".clearfix", panel).height();
					if ($('#historyVersionTable', panel).bootstrapTable('getData', panel).length == 0) {// 如果没有数据
						// 给固定文字的高度
						tableHeight = 105;
					}
					if (this.search || this.showExport) {// 如果有自带的功能
						// 把自带功能的元素高度加上
						tableHeight += ($('#historyVersionTable', panel).parent().parent().parent().parent().find(".pull-left", panel).height() + 20);
					}
					$('#historyVersionTable', panel).bootstrapTable('resetView', {
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
						version : $("#version", panel).val(),
						startTime : $('#startdate', panel).val(),
						endTime : $('#enddate', panel).val()
					});
				},
				columns : [ {
					checkbox : true
				}, {
					field : 'version',
					title : '版本号',
					sortable : true,
					width : 150
				}, {
					field : 'title',
					title : '标题',
					sortable : true,
					width : 150
				}, /*
					 * { field: 'content', title: '内容', sortable: true, width:
					 * 500, formatter:function(value,row,index){ var
					 * str=row.content if(str.length>100){
					 * str=str.substring(0,100)+"</br>"+"<p>.....</p>"; }
					 * return str; } } ,
					 */
				{
					field : 'createTime',
					title : '创建时间',
					sortable : true,
					width : 150
				}, {
					field : 'sortNo',
					title : '序号',
					sortable : true,
					width : 150
				} ]
			}, '#historyVersionTable', panel);
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
				$("#historyVersionTable", panel).bootstrapTable('selectPage', 1);
				$("#historyVersionTable", panel).bootstrapTable('refresh');
			});
			$("#clearSearch", panel).click(function() {
				base.reset(".main-box-header");
			});

			$('#addModal', panel).on('shown.bs.modal', function() {
				$('#addForm', panel).data('bootstrapValidator').resetForm(true);
			});

			/*
			 * $('#edit_content', panel).ckeditor();//修改的cke控件 $('#add_content',
			 * panel).ckeditor();//修改的cke控件
			 */
			CKEDITOR.replace('add_content');
			CKEDITOR.replace('edit_content');
			// CKEDITOR.on('instanceReady', function(e) {
			// console.log(e.editor.name + '加载完毕！')
			// })
		},
		add : function(panel) {
			CKEDITOR.instances["add_content"].setData("");
			var self = this;
			$('#addModal', panel).modal({
				keyboard : false,
				backdrop : 'static'
			});
			base.validator({
				fields : {
					add_version : {
						validators : {
							notEmpty : {
								message : '版本号不能为空'
							}
						}
					},
					add_title : {
						validators : {
							notEmpty : {
								message : '标题不能为空'
							}
						}
					},
					add_content : {
						validators : {
							notEmpty : {
								message : '内容不能为空'
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
								min : -128,
								max : 127,
								message : '排序号过大'
							}
						}
					}
				}
			}, "#addForm", self.create, panel)
		},
		create : function(panel) {
			$.post("/version/add", {
				"version" : $("#add_version", panel).val(),
				"title" : $("#add_title", panel).val(),
				"content" : $("#add_content", panel).val(),
				"sortNo" : $("#add_sortNo", panel).val()
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						$("#historyVersionTable", panel).bootstrapTable('selectPage', 1);
						$("#historyVersionTable", panel).bootstrapTable('refresh');
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
			var arrselections = $("#historyVersionTable", panel).bootstrapTable('getSelections');
			if (arrselections.length > 1) {
				base.error("只能选择一行进行编辑!");
				return;
			}
			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}
			$("#edit_version", panel).val(arrselections[0].version);
			$("#edit_sortNo", panel).val(arrselections[0].sortNo);
			$("#edit_title", panel).val(arrselections[0].title);
			CKEDITOR.instances["edit_content"].setData(arrselections[0].content);
			/* $("#edit_content", panel).val(); */
			$("#versionId", panel).val(arrselections[0].versionId);
			$('#editModal', panel).modal({
				keyboard : false,
				backdrop : 'static'
			});
			base.validator({
				fields : {
					add_version : {
						validators : {
							notEmpty : {
								message : '版本号不能为空'
							}
						}
					},
					add_title : {
						validators : {
							notEmpty : {
								message : '标题不能为空'
							}
						}
					},
					add_content : {
						validators : {
							notEmpty : {
								message : '内容不能为空'
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
								max : 3,
								message : '排序号过长'
							}
						}
					}
				}
			}, "#editForm", self.update, panel)

		},
		update : function(panel) {
			$.post("/version/update", {
				"version" : $("#edit_version", panel).val(),
				"title" : $("#edit_title", panel).val(),
				"content" : $("#edit_content", panel).val(),
				"sortNo" : $("#edit_sortNo", panel).val(),
				"versionId" : $("#versionId", panel).val()
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						$("#historyVersionTable", panel).bootstrapTable('selectPage', 1);
						$("#historyVersionTable", panel).bootstrapTable('refresh');
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
			var arrselections = $("#historyVersionTable", panel).bootstrapTable('getSelections');

			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}

			base.cancel({
				title : "删除操作",
				text : "您确定要删除该项吗？"
			}, function() {
				$.post("/version/remove", {
					"versionId" : arrselections[0].versionId
				}, function(data, status) {
					if (status == "success") {
						if (data.success == 0) {
							$("#historyVersionTable", panel).bootstrapTable('selectPage', 1);
							 $("#historyVersionTable", panel).bootstrapTable('refresh');
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