// 对Date的扩展，将 Date 转化为指定格式的String
// 例子： 
// (new Date()).Format("yyyy-MM-dd HH:mm:ss.S") ==> 2006-07-02 08:09:04.423  
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

function getHight(search){
	var $table = $('#collegeTable');
	//表格控件不支持高度自适应
    var tableHeight = $table.find("thead").height() + $table.find("tbody").height()
        + 3 + $table.parent().parent().parent().parent().find(".clearfix").height();
    if($('#collegeTable').bootstrapTable('getData').length==0){//如果没有数据 给固定文字的高度
        tableHeight=105;
    }
    if (search) {//如果有自带的功能 把自带功能的元素高度加上
        tableHeight += ($table.parent().parent().parent().parent().find(".pull-left").height() + 20);
    }
    $table.bootstrapTable('resetView', {"height": tableHeight});
    if( tableHeight > 900){//当高度过高 刷新外面iframe高度
        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
    }else{
    	$(parent.document).find("#mainFrame").height(1150);
    }
}

$(function() {
	dataObject = $('#collegeTable').bootstrapTable(
					{
						url : '/manage/college/cs/query',
						method : 'post',
						striped : true, // 是否显示行间隔色
						//singleSelect : true,// 单选
						pagination : true, // 是否显示分页（*）
						sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
						pageList : [ 10, 20, 50, 100 ], // 可供选择的每页的行数（*）
						clickToSelect : true, // 是否启用点击选中行
						search : true,
						showExport : true,// 显示导出按钮
						exportDataType : "all",// 导出类型
						showRefresh : false,
						showFooter : false,
						toolbar : '#toolbar',
						height : 800,
						exportTypes : [ 'excel' ],
						exportOptions : {
							fileName : (new Date())
									.Format("yyyy-MM-dd HH:mm:ss")
									+ $('h1').text()
						},
						uniqueId : "collegeId", // 每一行的唯一标识，一般为主键列
						onLoadSuccess : function(data) {
							getHight(this.search);
						},
						onSearch : function(text){
							var data = $('#collegeTable').bootstrapTable('getData');
							getHight(this.search);
						},
						onPageChange : function(number, size){
							getHight(this.search);
						},
						singleSelect:true,
						columns : [
								{
									checkbox : true
								},{
									field : "status",
									title : "学校状态",
									align : 'center',
									formatter : function(value, row, index) {
										return value == "1" ? "<a class='label label-success'>启用 </a>"
												: "<a class='label label-warning' >禁用</a>";
									},
									searchFormatter : false,
									sortable : true
								},{
									field : "packetMode",
									title : "众包模式状态",
									align : 'center',
									formatter : function(value, row, index) {
										return value == "1" ? "<a class='label label-success'>开启 </a>"
												: "<a class='label label-warning'>关闭</a>";
									},
									searchFormatter : false,
									sortable : true
								}, {
									field : "collegeCode",
									title : "学校编号",
									sortable : true
								}, {
									field : "fullName",
									title : "学校全称",
									sortable : true,
									width : 250
								}, {
									field : "simpleName",
									title : "学校简称",
									sortable : true
								}, {
									field : "natureValue",
									title : "办学性质"
								}, {
									field : "collegeTypeValue",
									title : "所属类型"
								}, {
									field : "provinceName",
									title : "所属省份",
									sortable : true
								}, {
									field : "cityName",
									title : "所属市区",
									sortable : true
								}, {
									field : "countyName",
									title : "所属区县",
									sortable : true
								}, {
									field : "remark",
									title : "备注",
									width : 400
								}, {
									field : "collegeId",
									title : "collegeId",
									visible : false,
									searchable : false
								}, {
									field : "provinceId",
									title : "provinceId",
									visible : false,
									searchable : false
								}, {
									field : "cityId",
									title : "cityId",
									visible : false,
									searchable : false
								}, {
									field : "countyId",
									title : "countyId",
									visible : false,
									searchable : false
								}, {
									field : "natureCode",
									title : "natureCode",
									visible : false,
									searchable : false
								}, {
									field : "collegeTypeCode",
									title : "collegeTypeCode",
									visible : false,
									searchable : false
								} ]
					});

	$("#province").on(
			"change",
			function(e) {
				var provinceId = $("#province").val();
				if (provinceId == "" || provinceId == null) {
					$("#city").val('');
					$("#city").attr("disabled", true);
					return;
				}
				$("#city").attr("disabled", false);
				$.ajax({
					type : "POST",
					url : "/manage/city/selectprovincecities",
					data : {
						"provinceId" : provinceId
					},
					dataType : "json",
					success : function(data) {
						var control = $('#city');
						var opVal = control.val();
						if (opVal == null) {
							opVal = getfirstSelectInCurrentPage().cityId;
						}
						// control.select2('data', null);
						control.empty();// 清空下拉框
						control.append("<option value=''>请选择</option>");
						var isChange = false;
						// 绑定内容
						$.each(data, function(i, item) {
							if (opVal != null && opVal == item.cityId) {
								isChange = true;
							}
							control.append("<option value='" + item.cityId
									+ "'>" + item.cityName + "</option>");
						});
						// control.val(opVal);
						if (isChange) {
							control.val(opVal).trigger("change");
						} else {
							control.val('').trigger("change");
							$('#addForm').data('bootstrapValidator')
									.updateStatus('cityId', 'NOT_VALIDATED',
											null);
						}
					}
				});
			});

	$("#city").on(
			"change",
			function(e) {
				var cityId = $("#city").val();
				if (cityId == "" || cityId == null) {
					$("#county").val('').trigger("change");
					$('#addForm').data('bootstrapValidator').updateStatus(
							'countyId', 'NOT_VALIDATED', null);
					$("#county").attr("disabled", true);
					return;
				}
				$("#county").attr("disabled", false);
				$.ajax({
					type : "POST",
					url : "/manage/county/selectcitycounties",
					dataType : "json",
					data : {
						"cityId" : cityId
					},
					success : function(data) {
						var control = $('#county');
						var opVal = control.val();
						if (opVal == null) {
							opVal = getfirstSelectInCurrentPage().countyId;
						}
						var isChange = false;
						control.empty();// 清空下拉框
						control.append("<option value=''>请选择</option>");
						// 绑定内容
						$.each(data, function(i, item) {
							if (opVal != null && opVal == item.countyId) {
								isChange = true;
							}
							control.append("<option value='" + item.countyId
									+ "'>" + item.countyName + "</option>");
						});
						// control.val(opVal);
						if (isChange) {
							control.val(opVal).trigger("change");
						} else {
							control.val('').trigger("change");
							$('#addForm').data('bootstrapValidator')
									.updateStatus('countyId', 'NOT_VALIDATED',
											null);
						}
					}
				});
			});

	$("#btn_add").click(function() {
		$('#collegeId').val('');
		$("#myModalLabel").html("新增");
		$('#addModal').modal();
	});

	$("#btn_edit").click(function() {
		var selectsjson = getfirstSelectInCurrentPage();
		if (selectsjson == null) {
			sweetAlert("", "请选择有效数据", "warning");
			return;
		}
		bindFormData(selectsjson);
		$("#myModalLabel").html("修改");
		$('#addModal').modal();
	});

	$("#btn_query").click(function() {
		$("#collegeTable").bootstrapTable('refresh');
	});
	
	$("#btn_on").click(function(){
		var rows = $('#collegeTable').bootstrapTable('getSelections');
		if (rows.length < 1) {
			sweetAlert("", "请选择有效数据", "warning");
			return;
		}
		$('#paket_mode').modal();
	});
	
	$("#btn_mode").click(function() {
		var rows = $('#collegeTable').bootstrapTable('getSelections');
		var collegeIds = [];
		for(var i=0;i<rows.length;i++){
			collegeIds.push(rows[i].collegeId);
		}
		var mode = $("input[name='packetMode']:checked").val()
		onoffcontrol(collegeIds,mode);
	});

	$('#addModal').on('hidden.bs.modal', function() {
		clearForm('#addForm');
		$("#addForm").data('bootstrapValidator').destroy();
		validate();
	});

	$('#addModal').on('shown.bs.modal', function() {
		var provinceId = $("#province").val();
		var cityId = $("#city").val();
		if (provinceId == null || provinceId == "") {
			$("#city").attr("disabled", true);
		} else {
			$("#city").attr("disabled", false);
		}
		if (cityId == null || cityId == "") {
			$("#county").attr("disabled", true);
		} else {
			$("#county").attr("disabled", false);
		}
	});

	// 处理modal中检索框不能输入问题
	$.fn.modal.Constructor.prototype.enforceFocus = function() {};

	$(window).resize(function() {
		$('#collegeTable').bootstrapTable('resetView');
	});

	InitDictItem();
	validate();
});

// 绑定select
function BindSelect(ctrlName, data) {
	var control = $('#' + ctrlName);
	// 设置Select2的处理
	control.select2({
		placeholder : "请选择",
		allowClear : true,
		// minimumResultsForSearch : -1,
		formatResult : function(row) {// 选中后select2显示的 内容
			return row.name;
		},
		escapeMarkup : function(m) {
			return m;
		}
	});
	control.empty();// 清空下拉框
	control.append("<option value=''>请选择</option>");
	// 绑定内容
	$.each(data, function(i, item) {
		control.append("<option value='" + item.id + "'>" + item.text
				+ "</option>");
	});
}

// select数据源初始化
function InitDictItem() {
	$.ajax({
		type : "POST",
		url : "/manage/college/getPropertyInfos",
		dataType : "json",
		success : function(data) {
			BindSelect('nature', data.data.nature);
			BindSelect('collegeType', data.data.collegeType);
			BindSelect('province', data.data.province);
			BindSelect('city', data.data.city);
			BindSelect('county', data.data.county);
		}
	});
}

// 保存
function presave() {
	var jsonArray = $("#addForm").serializeArray();
	var jsonData = convertToJsonStr(jsonArray);

	$.ajax({
		url : '/manage/college/presave',
		dataType : 'JSON',
		type : 'post',
		data : {
			data : JSON.stringify(jsonData)
		},
		success : function(data) {
			if (data.success == 0) {
				sweetAlert("", "保存成功", "success");
				dataObject.bootstrapTable('refresh', {
					silent : true
				});
				$("#addModal").modal('hide');
				$('#addForm').data('bootstrapValidator').resetForm(true);
			} else {
				sweetAlert("", data.message, "info");
			}
		},
		err : function(xhr, error, exception) {
			sweetAlert("", exception.toString(), "error");
		}
	});
}

// 删除
function delcollege() {
	var currentPageItems = $('#collegeTable').bootstrapTable('getData', true);
	var selectedRow = [];
	$.each(currentPageItems, function() {
		if (this[0] == 1) {
			selectedRow.push(this);
		}
	});
	if (selectedRow.length < 1) {
		sweetAlert("", "请选择要删除的项", "warning");
		return;
	}
	if (selectedRow.length > 1) {
		sweetAlert("", "只能删除一行", "warning");
		return;
	}

	sweetAlert({
		title : "提示信息",
		text : "确认删除该项信息?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		confirmButtonText : "确定",
		cancelButtonText : "取消",
		closeOnConfirm : true
	}, function() {
		$.ajax({
			url : "/manage/college/delete",
			dataType : 'json',
			data : {
				data : '{"collegeId":"' + selectedRow[0].collegeId + '"}'
			},
			type : 'post',
			success : function(data) {
				if (data.success == 0) {
					sweetAlert("", "删除成功", "success");
					dataObject.bootstrapTable('refresh', {
						silent : true
					});
				} else {
					sweetAlert("", data.message, "error");
				}
			},
			error : function(xhr, error, exception) {
				sweetAlert("", exception.toString(), "error");
			}
		});
	});
}

// 获取当前页第一个选择行
function getfirstSelectInCurrentPage() {
	var currentPageItems = $('#collegeTable').bootstrapTable('getData', true);
	var selection = null;
	$.each(currentPageItems, function() {
		if (this[0] == 1) {
			selection = this;
			return false;
		}
	});
	return selection;
}

// 清空表单
function clearForm(form) {
	$(':input', form).not(':button, :submit, :reset').val('').removeAttr(
			'checked').removeAttr('selected');
	$('.select2-selection__clear').mousedown();
	$('#page-wrapper').mousedown();
}

// 将表单对象转为json对象
function convertToJsonStr(formValues) {
	var result = {};
	$.each(formValues, function() {
		if (this.name == "natureCode") {
			result["nature"] = this.value;
		} else if (this.name == "collegeTypeCode") {
			result["collegeType"] = this.value;
		} else {
			result[this.name] = this.value;
		}
	});
	if (!$("#status").prop("checked")) {
		result["status"] = 0;
	} else {
		result["status"] = 1;
	}
	return result;
}

// 绑定表单的值
function bindFormData(jsonData) {
	if (!jsonData)
		return;
	var obj = $("#addForm");
	$.each(jsonData, function(name, value) {
		var inputMark = obj.find("[name=" + name + "]");
		if (inputMark.attr("type") == "checkbox") {
			inputMark.prop("checked", value == "0" ? false : true);
		} else if (inputMark.attr("type") == "text"
				|| inputMark.attr("type") == "hidden") {
			inputMark.val(value);
		} else if (inputMark.hasClass("select2")) {
			inputMark.val(value).trigger("change");
		}
	});
}

function onoffcontrol(collegeIds,isok) {
	$.ajax({
		type : "POST",
		url : "/manage/college/changemode",
		dataType : "json",
		data : {
			collegeIds : JSON.stringify(collegeIds),
			isok : isok
		},
		success : function(data,status) {
			if (status == "success") {
				if (data.success == 0) {
					$("#collegeTable").bootstrapTable('refresh');
					$('#paket_mode').modal('hide');
					sweetAlert("", "操作成功", "success");
				} else {
					sweetAlert("", data.message, "error");
				}
			} else {
				sweetAlert("", "数据加载失败!", "error");
			}
		}
	});
}

// 表单验
function validate() {
	$("#addForm").bootstrapValidator({
		message : '验证未通过',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			collegeCode : {
				validators : {
					notEmpty : {
						message : '学校编号不能为空'
					}
				}
			},
			fullName : {
				validators : {
					notEmpty : {
						message : '学校全称不能为空'
					}
				}
			},
			simpleName : {
				validators : {
					notEmpty : {
						message : '学校简称不能为空'
					}
				}
			},
			provinceId : {
				validators : {
					notEmpty : {
						message : '省份不能为空'
					}
				}
			},
			cityId : {
				validators : {
					notEmpty : {
						message : '城市不能为空'
					}
				}
			},
			countyId : {
				validators : {
					notEmpty : {
						message : '县区不能为空'
					}
				}
			},
			natureCode : {
				validators : {
					notEmpty : {
						message : '办学性质不能为空'
					}
				}
			},
			collegeTypeCode : {
				validators : {
					notEmpty : {
						message : '学校类型不能为空'
					}
				}
			}
		}
	}).on('success.form.bv', function(e) {
		e.preventDefault();
		presave();
	});
}
