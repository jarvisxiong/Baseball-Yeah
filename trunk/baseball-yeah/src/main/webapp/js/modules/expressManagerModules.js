define([ 'base' ], function(base) {
	/**
	 * 私有成员定义区域
	 */
	var selectedRow = null;
	var isupdate = false;
	var self;
	return {
		init : function(panel) {
			// / <summary>
			// / 模块初始化方法
			// / </summary>
			// / <param name="args">初始化时传入的参数</param>
			self = this;
			$.ajaxSetup({
				cache : false
			});
			// 模态框隐藏事件
			$('#editModal', panel).on('hidden.bs.modal', function(e) {
				$("#formBody", panel).data('bootstrapValidator').resetForm(true);
			});
			self.validate(panel);
			self.loadSelect(panel);
			base.datagrid({
				url : '/store/exp/postExpInfoList',
				height : '1200',
				sortName : 'storeName',
				showRefresh : true,
				sidePagination : "client",
				contentType : 'application/x-www-form-urlencoded',
				method : 'post',
				onPageChange : function(number, size) {
					// client分页方式表格控件不支持高度自适应
					var tableHeight = $('#expressTable', panel).find("thead", panel).height() + $('#expressTable', panel).find("tbody", panel).height() + 3 + $('#expressTable', panel).parent().parent().parent().parent().find(".clearfix", panel).height();
					if ($('#expressTable', panel).bootstrapTable('getData', panel).length == 0) {// 如果没有数据
						// 给固定文字的高度
						tableHeight = 105;
					}
					if (this.search || this.showExport) {// 如果有自带的功能
						// 把自带功能的元素高度加上
						tableHeight += ($('#expressTable', panel).parent().parent().parent().parent().find(".pull-left", panel).height() + 20);
					}
					$('#expressTable', panel).bootstrapTable('resetView', {
						"height" : tableHeight
					});
					if (tableHeight > 900) {// 当高度过高 刷新外面iframe高度
						$(parent.document).find("#mainFrame", panel).height(document.body.scrollHeight);
					} else {
						$(parent.document).find("#mainFrame", panel).height(1150);
					}
					var allSelections =  $('#expressTable', panel).bootstrapTable("getAllSelections");
					if (allSelections.length > 0) {
						for (var shux in allSelections[0]) {
							var removerCheck = [];
							for (var i = 0; i < allSelections.length; i++) {
								removerCheck.push(allSelections[i][shux]);
							}
							$('#expressTable', panel).bootstrapTable("uncheckBy", {field: shux, values: removerCheck});
						}
					}
				},
				queryParams : function(params) {
					return $.extend(params, {
						phone : $("#phone", panel).val(),
						storeName : $('#storeName', panel).val(),
						expressCompanyId : $('#expressId', panel).val()
					});
				},
				onCheck : function(row) {
					selectedRow = row;
					self.bindFormData(panel, row);
					// 绑定下拉框
					if (row.ecGcode) {
						var ecArray = row.ecGcode.split(",");
						sel2Control.val(ecArray).trigger("change");
					} else {
						sel2Control.val("").trigger("change");
					}
					if (row.colCode) {
						var colArray = row.colCode.split(",");
						sel1Control.val(colArray).trigger("change");
					} else {
						sel1Control.val("").trigger("change");
					}
				},
				columns : [ {
					checkbox : true,
					width : 40
				}, {
					field : 'status',
					title : '快递站点状态',
					width : 80,
					align : 'left',
					searchFormatter : false,
					formatter : function(value, row, index) {
						if (value == 1) {
							return '<a class="label label-success" style="font-size:12px;">启用</a>';

						}
						return '<a class="label label-warning" style="font-size:12px;">禁用</a>';
					}
				}, {
					field : 'storeName',
					title : '快递站点名称',
					width : 100,
					sortable : true
				}, {
					field : 'storeCode',
					title : '快递站点编号',
					width : 100,
					sortable : true
				}, {
					field : 'supervisorName',
					title : '负责人',
					width : 80
				}, {
					field : 'ecName',
					title : '兼营快递公司',
					width : 120
				}, {
					field : 'defaultECName',
					title : '主营快递公司',
					width : 120
				}, {
					field : 'colName',
					title : '学校名称',
					width : 120
				}, {
					field : 'location',
					title : '地址',
					width : 200
				}, {
					field : 'phone',
					title : '手机号',
					width : 100
				}, {
					field : 'packetModeMgr',
					title : '派件模式',
					width : 80,
					searchFormatter : false,
					formatter : function(value, row, index) {
						if (value == 1) {
							return '<a class="label label-success" style="font-size:12px;">已启用</a>';
						}
						return '<a class="label label-warning" style="font-size:12px;">已关闭</a>';
					}
				}, {
					field : 'packetModeSend',
					title : '寄件模式',
					width : 80,
					searchFormatter : false,
					formatter : function(value, row, index) {
						if (value == 1) {
							return '<a class="label label-success" style="font-size:12px;">已启用</a>';
						}
						return '<a class="label label-warning" style="font-size:12px;">已关闭</a>';
					}
				} ]
				/*,
				onPageChange: function(number, size) {
					var t = $('#expressTable', panel);
					var editRow = t.bootstrapTable("getSelections");
					if (editRow && editRow.length > 0) {
						t.bootstrapTable("uncheckBy", {
							field: "phone",
							values: [editRow[0].phone]
						});
					}
				}*/
			}, '#expressTable', panel);

			/**
			 * 加载合作公司
			 */
			$.ajax({
				url : "/manage/express/queryenabled",
				dataType : 'json',
				type : 'post',
				success : function(data) {
					$.each(data.data, function(i, item) {
						sel2Control.append("<option value='" + item.expresscompanyid + "'>&nbsp;" + item.simplename + "</option>");
						sel3Control.append("<option value='" + item.expresscompanyid + "'>&nbsp;" + item.simplename + "</option>");
						expressControl.append("<option value='" + item.expresscompanyid + "'>&nbsp;" + item.simplename + "</option>");
					});

					expressControl.select2("val", null);
				}
			});

			/**
			 * 加载学校
			 */
			$.ajax({
				url : "/manage/college/queryselect",
				dataType : 'json',
				type : 'post',
				success : function(data) {
					$.each(data.data, function(i, item) {
						sel1Control.append("<option value='" + item.id + "'>&nbsp;" + item.text + "</option>");
					});
				}
			});

			/*$('#expressTable', panel).on('page-change.bs.table', function() {
				$('#expressTable', panel).bootstrapTable('uncheckAll');
				selectedRow = null;
			});*/

			$("#check", panel).click(function() {
				var checkRow = $('#expressTable', panel).bootstrapTable("getSelections");
				if (!checkRow || checkRow.length <= 0) {
					sweetAlert("", "请选择要操作的项", "warning");
				} else {
					$("#paket_mode", panel).modal('show');
					// 绑定radio
					checkRow[0].packetModeMgr == 1 ? $("#p1", panel).prop("checked", true) : $("#p2", panel).prop("checked", true);
					checkRow[0].packetModeSend == 1 ? $("#p3", panel).prop("checked", true) : $("#p4", panel).prop("checked", true);
				}
			});
			$("#btn_mode", panel).click(function() {
				self.packet_mode(panel);
			});
			$("#update", panel).click(function() {
				isupdate = true;
				var editRow = $('#expressTable', panel).bootstrapTable("getSelections");
				if (!editRow || editRow.length <= 0) {
					sweetAlert("", "请选择要操作的项", "warning");
				} else if (editRow.length > 1) {
					sweetAlert("", "只能选择操作一项", "warning");
				} else {
					$("#editModal", panel).modal('show');
					self.bindFormData(panel, editRow[0]);
					// 绑定radio
					editRow[0].packetModeMgr == 1 ? $("#pmode", panel).prop("checked", true) : $("#pmode2", panel).prop("checked", true);
					editRow[0].packetModeSend == 1 ? $("#pmode3", panel).prop("checked", true) : $("#pmode4", panel).prop("checked", true);
					// $("input[name='packetModeMgr'][value="+editRow[0].packetModeMgr+"]",
					// panel).prop("checked",true);
					// 绑定下拉框
					if (editRow[0].ecGcode) {
						var ecArray = editRow[0].ecGcode.split(",");
						sel2Control.val(ecArray).trigger("change");
					} else {
						sel2Control.val("").trigger("change");
					}
					if (editRow[0].colCode) {
						var colArray = editRow[0].colCode.split(",");
						sel1Control.val(colArray).trigger("change");
					} else {
						sel1Control.val("").trigger("change");
					}
					if (editRow[0].defaultECId) {
						sel3Control.val(editRow[0].defaultECId).trigger("change");
					} else {
						sel3Control.val("").trigger("change");
					}
				}
			});
			$("#add", panel).click(function() {
				isupdate = false;
				self.resetFormData(panel, "formBody");
				$("#editModal", panel).modal('show');

			});
			$("#query", panel).click(function() {
				$("#expressTable", panel).bootstrapTable('refresh', {
					silent : true
				});
			});
			$("#remove", panel).click(function() {
				if (!$('#expressTable', panel).bootstrapTable("getSelections") || $('#expressTable', panel).bootstrapTable("getSelections").length <= 0) {
					sweetAlert("", "请选择要操作的项", "warning");
				} else {
					sweetAlert({
						title : "提示信息",
						text : "确认删除该项信息?",
						type : "warning",
						showCancelButton : true,
						confirmButtonColor : "#DD6B55",
						confirmButtonText : "确定",
						cancelButtonText : "取消",
						closeOnConfirm : false
					}, function() {
						self.delStore();
					});

				}
			});
			$("#btnReset", panel).click(function() {
				self.resetFormData('#formBody');
			});
			$("#editModal", panel).on('hidden.bs.modal', function() {
                $("#formBody", panel).data('bootstrapValidator').destroy();
                $("#formBody", panel).data('bootstrapValidator', null);
            });
		},
		/*
		 * 表单验证
		 */
		validate : function(panel) {
			base.validator({
				message : 'This value is not valid',
				feedbackIcons : {
					valid : 'glyphicon glyphicon-ok',
					invalid : 'glyphicon glyphicon-remove',
					validating : 'glyphicon glyphicon-refresh'
				},
				fields : {
					storeName : {
						validators : {
							notEmpty : {
								message : '快递站点名称不能为空'
							}
						}
					},
					storeCode : {
						validators : {
							notEmpty : {
								message : '快递站点编号不能为空'
							}
						}
					},
					/*
					 * supervisorName: { message: '负责人名称验证失败', validators: {
					 * notEmpty: { message: '负责人不能为空' }, stringLength: { min: 1,
					 * max: 20, message: '负责人名称过长' } } },
					 */
					phone : {
						message : '手机号验证失败',
						validators : {
							notEmpty : {
								message : '手机号不能为空'
							},
							regexp : {
								regexp : /^1[3|4|5|7|8]\d{9}$/,
								message : '手机号格式不正确'
							},
						}
					}
				}
			}, '#formBody', self.edit, panel);
		},
		/*
		 * 编辑操作
		 */
		edit : function(panel) {
			var selectedRow = $('#expressTable', panel).bootstrapTable("getSelections");
			var jsonArray = $("#formBody", panel).serializeArray();
			var jsonData = self.convertToJsonStr(panel, jsonArray);
			if (!isupdate) {
				$.ajax({
					url : '/store/exp/addexpinfo',
					dataType : 'JSON',
					type : 'post',
					data : {
						data : JSON.stringify(jsonData)
					},
					success : function(data) {
						if (data.success == 0) {
							sweetAlert("", "新增成功", "success");
							$('#expressTable', panel).bootstrapTable('refresh', {
								silent : true
							});
							$("#editModal", panel).modal('hide');
							$("#btnSave", panel).attr("disabled", false);
							self.resetFormData(panel, "#formBody");
							$("#btnSave", panel).attr("disabled", false);
						} else {
							sweetAlert("", data.message, "info");
							$("#btnSave", panel).attr("disabled", false);
						}
					},
					error : function(XMLHttpRequest) {
						sweetAlert("", XMLHttpRequest.responseText, "info");
						$("#btnSave", panel).attr("disabled", false);
					}
				});
			} else {
				jsonData["storeId"] = $('#expressTable', panel).bootstrapTable("getSelections")[0].storeId;
				$.ajax({
					url : '/store/exp/updateexpinfo',
					dataType : 'JSON',
					type : 'post',
					data : {
						data : JSON.stringify(jsonData)
					},
					success : function(data) {
						if (data.success == 0) {

							sweetAlert("", "修改成功", "success");
							$('#expressTable', panel).bootstrapTable('refresh', {
								silent : true
							});
							$("#editModal", panel).modal('hide');
							$("#btnSave", panel).attr("disabled", false);
						} else {
							sweetAlert("", data.message, "info");
						}
					},
					error : function(XMLHttpRequest) {
						sweetAlert("", XMLHttpRequest.responseText, "info");
						$("#btnSave", panel).attr("disabled", false);
					}
				});
			}
		},
		/**
		 * 将表单对象转为json对象
		 */
		convertToJsonStr : function(panel, formValues) {
			var result = {};
			var ecGcode = [];
			var colList = [];
			$.each(formValues, function() {
				if (this.name == "ecList") {
					ecGcode.push(this.value);
					result[this.name] = ecGcode;
				} else if (this.name == "colList") {
					colList.push(this.value);
					result[this.name] = colList;
				} else if (this.name == "packetModeMgr") {
					$("#pmode", panel).prop("checked") == true ? result[this.name] = 1 : result[this.name] = 0;
				} else if (this.name == "packetModeSend") {
					$("#pmode3", panel).prop("checked") == true ? result[this.name] = 1 : result[this.name] = 0;
				} else {
					result[this.name] = this.value;
				}
			});
			return result;
		},
		/**
		 * 绑定表单的值
		 */
		bindFormData : function(panel, jsonData) {
			if (!jsonData)
				return;
			var obj = $("#formBody");
			$.each(jsonData, function(name, value) {
				var inputMark = obj.find($.parseHTML("input:[name=" + name + "]"));
				if (inputMark.attr("type") == "checkbox") {
					inputMark.each(function() {
						if (Object.prototype.toString.apply(value) == [ 'object Array' ]) {
							for (var i = 0; i < value.length; i++) {
								if ($(this).val() == value[i])
									$(this).attr("checked", "checked");
							}
						} else {
							if ($(this).val() == value)
								$(this).attr("checked", "checked");
						}

					});
				} else if (inputMark.attr("type") == "textarea") {
					obj.find("[name=" + name + "]", panel).html(value);
				} else {
					obj.find("[name=" + name + "]", panel).val(value);
				}
			});
		},
		/**
		 * 重置表单
		 */
		resetFormData : function(panel, formName) {
			$("#txt_storeCode", panel).val("");
			$("#txt_storeName", panel).val("");
			$("#txt_phone", panel).val("");
			$("#txt_supervisorName", panel).val("");
			$("#txt_location", panel).val("");
			/*
			 * $(':input',formName) .not(':button, :submit, :reset, :hidden')
			 * .val('') .removeAttr('checked') .removeAttr('selected');
			 */
			sel2Control.val(null).trigger("change");
			sel1Control.val(null).trigger("change");
			sel3Control.val(null).trigger("change");
		},
		delStore : function(panel) {
			var delRow = $('#expressTable', panel).bootstrapTable("getSelections");
			var ids = [];
			for (var i = 0; i < delRow.length; i++) {
				ids.push(delRow[i].storeId);
			}

			$.ajax({
				url : "/store/exp/delbatch",
				dataType : 'json',
				data : {
					data : '{"ids":[' + ids + ']}'
				},
				type : 'post',
				success : function(data) {
					if (data.success == 0) {
						sweetAlert("恭喜", "删除成功", "success");
						$('#expressTable', panel).bootstrapTable('refresh', {
							silent : true
						});
					} else {
						sweetAlert("", data.message, "info");
					}
				},
				error : function(XMLHttpRequest) {
					sweetAlert("", XMLHttpRequest.responseText, "error");
				}
			});

		},
		/**
		 * 开关众包模式
		 */
		packet_mode : function(panel) {
			var delRow = $('#expressTable', panel).bootstrapTable("getSelections");
			var ids = [];
			for (var i = 0; i < delRow.length; i++) {
				ids.push(delRow[i].storeId);
			}
			var mgrMode = $("input[name='mgrMode']:checked", panel).val();
			var sendMode = $("input[name='sendMode']:checked", panel).val();
			$.ajax({
				url : "/store/exp/checkpacketmode",
				dataType : 'json',
				data : {
					ids : ids.join(","),
					mgrMode : mgrMode,
					sendMode : sendMode
				},
				type : 'post',
				success : function(data) {
					if (data.success == 0) {
						sweetAlert("恭喜", "更改成功", "success");
						$('#expressTable', panel).bootstrapTable('refresh', {
							silent : true
						});
						$("#paket_mode", panel).modal("hide");
					} else {
						sweetAlert("", data.message, "info");
					}
				},
				error : function(XMLHttpRequest) {
					sweetAlert("", XMLHttpRequest.responseText, "error");
				}
			});
		},
		loadSelect : function(panel) {
			sel2Control = $('#sel2Multi', panel);
			sel1Control = $('#sel1Multi', panel);
			sel3Control = $('#sel3Multi', panel);
			expressControl = $("#expressId", panel);
			// $('#sel2', panel).select2();
			sel1Control.select2({
				placeholder : '选择学校',
				allowClear : true
			});
			sel2Control.select2({
				placeholder : '选择合作商',
				allowClear : true
			});
			sel3Control.select2({
				placeholder : '选择默认快递公司',
				allowClear : true
			});
			expressControl.select2({
				placeholder : '选择默认快递公司',
				allowClear : true
			});
		}
	};
});