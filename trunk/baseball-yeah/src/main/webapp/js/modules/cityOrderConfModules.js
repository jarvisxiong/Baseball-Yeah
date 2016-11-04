define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    return {
        init: function (args) {
            var self = this;
            base.datagrid({
                url: '/manage/cityOrderConf/getall',
                singleSelect:false,
                method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                    		cityName:$("#cityName", args).val(),
                    		provinceId: $("#provinceId", args).val(),
                    		orderType: $("#orderType", args).val(),
                    		costType: $("#costType", args).val(),
                    		state: $("#state", args).val(),
                    		createUserName: $("#createUser", args).val(),
                    		createStartDate : $('#createStartDate', args).val(),
    						createEndDate : $('#createEndDate', args).val(),
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: 'id',
                        visible: false
                    },
                    {
                        field: 'state',
                        title: '状态',
                        visible: false
                    },
                    {
                        field: 'provinceName',
                        title: '省份'
                    },
                    {
                        field: 'cityName',
                        title: '城市'
                    },
                    {
                        field: 'orderType',
                        title: '订单类型'
                    },
                    {
                        field: 'stateDesc',
                        title: '状态'
                    },
                    {
                        field: 'propertyValue',
                        title: '费用类型'
                    },
                    {
                        field: 'costValue',
                        title: '费用金额（元）',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value;
                        }
                    },
                    {
                        field: 'createUserName',
                        title: '创建人'
                    },
                    {
                        field: 'createDate',
                        title: '创建时间'
                    },
                    {
                        field: 'remark',
                        title: '备注'
                    }]
            }, '#offerTable', args);
            /*** 加载省份列表 ***/
            $.ajax({
                type: "GET",
                url: "/manage/province/getprovince",
                dataType: "json",
                success: function (DATA) {
                    $("#provinceId",args).select2({data: DATA});
                    $("#add_provinceId",args).select2({data: DATA});
                }
            });
            /*** 加载订单类型列表 ***/
            $.ajax({
                type: "GET",
                url: "/manage/commonSelectData/getOrderType",
                dataType: "json",
                success: function (DATA) {
                    $("#orderType",args).select2({data: DATA});
                    $("#add_orderType",args).select2({data: DATA});
                }
            });
            /*** 加载状态列表 ***/
            $.ajax({
                type: "GET",
                url: "/manage/commonSelectData/getStateList",
                dataType: "json",
                success: function (DATA) {
                    $("#state",args).select2({data: DATA});
                }
            });
            /*** 加载费用类型列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/propertydict/queryByAlias?alias=FINANCE_COST",
                dataType: "json",
                success: function (DATA) {
                    $('#costType',args).select2({data: DATA});
                    $('#add_costType',args).select2({data: DATA});
                }
            });
            
            //设置城市样式
            $('#add_cityId',args).select2();
            
			var date = new Date();
			// 开始时间
			$('#createStartDatePicker', args).datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				startView : 2,
				minView : 0,
				todayHighlight : true,
				endDate : new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
			}).on('changeDate', function(e) {
				var startTime = e.date;
				$('#createEndDatePicker', args).datetimepicker('setStartDate', startTime);
			});

			$('#createEndDatePicker', args).datetimepicker({
				format : 'yyyy-mm-dd hh:ii:ss',
				autoclose : true,
				startView : 2,
				minView : 0,
				todayHighlight : true,
				endDate : new Date(date.getFullYear(), date.getMonth(), date.getDate(), 23, 59, 59)
			}).on('changeDate', function(e) {
				var endTime = e.date;
				$('#createStartDatePicker', args).datetimepicker('setEndDate', endTime);
			});
            
            $("#clearSearch", args).click(function () {
                base.reset(".main-box-header");
                $("#cityName",args).val("");
                $("#provinceId",args).val(" ").trigger("change");
                $("#orderType",args).val(" ").trigger("change");
                $("#costType",args).val(" ").trigger("change");
                $("#state",args).val(" ").trigger("change");
                $("#createUser",args).val("");
                $("#createStartDate",args).val("");
            });
            $("#btn_query", args).click(function () {
                $("#offerTable",args).bootstrapTable('refresh');
            });
            $("#btn_add", args).click(function () {
                self.add(args);
            });
            $("#btn_edit", args).click(function () {
                self.edit(args);
            });
            $("#btn_delete", args).click(function () {
                self.remove(args);
            });
            $("#btn_enable", args).click(function () {
                self.enableFunc(args);
            });
            $("#btn_disable", args).click(function () {
                self.disableFunc(args);
            });
            
            
            $("#btnClose", args).click(function () {
                $("#editForm", args).data("bootstrapValidator").resetForm(true);
            });
            $('#addModal', args).on('shown.bs.modal', function () {
                $("#add_provinceId",args).val(" ").trigger("change");
                $("#add_cityId", args).val(" ").trigger("change");
                $("#add_orderType",args).val(" ").trigger("change");
                $('#add_costType',args).val(" ").trigger("change");
            	$("#add_costValue", args).val("");
            	$("#add_remark", args).val("");
            	$('#addForm', args).data('bootstrapValidator').resetForm(true);
            	$("#add_cityId",args).attr("disabled", true);
            });
            
            $("#add_provinceId",args).on("change",
                function (e) {
                    var provinceId = $("#add_provinceId",args).val();
                    if (provinceId == "" || provinceId == null) {
                        $("#add_cityId",args).val('');
                        $("#add_cityId",args).attr("disabled", true);
                        return;
                    }
                    $("#add_cityId",args).attr("disabled", false);
                    $.ajax({
                        type: "POST",
                        url: "/manage/city/selectprovincecities",
                        data: {
                            "provinceId": provinceId
                        },
                        dataType: "json",
                        success: function (data) {
                        	var control = $('#add_cityId',args);
                            control.empty();
                            control.append("<option value=' '>请选择</option>");
                            var isChange = false;
                            $.each(data, function (i, item) {
                                control.append("<option value='" + item.cityId + "'>" + item.cityName + "</option>");
                            });
                        }
                    });
            	}
            );
        },
        add: function (args) {
            var self = this;
            $('#addModal', args).modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                	add_provinceId: {
                        validators: {
                            notEmpty: {
                                message: '省份不能为空'
                            }
                        }
                    },
                	add_cityId: {
                        validators: {
                            notEmpty: {
                                message: '所属市区不能为空'
                            }
                        }
                    },
                    add_orderType: {
                        validators: {
                            notEmpty: {
                                message: '订单类型不能为空'
                            }
                        }
                    },
                    add_costType: {
                        validators: {
                            notEmpty: {
                                message: '费用类型不能为空'
                            }
                        }
                    },
                    add_costValue: {
                        validators: {
                            notEmpty: {
                                message: '费用金额不能为空'
                            },
                            integer: {
                            	message: '只能输入整数数值'
                    		},
                    		between: {
                    			message: "费用金额在1到1000元之间",
                                min: "1",
                                max: "1000"
                    		}
                        }
                    },
                    add_remark: {
                    	validators: {
                            stringLength : {
                                min : 0,
                                max : 50,
                                message : '备注长度不能超过50'
                            }
                        }
                    }
                }
            }, "#addForm", self.create, args);
        },
        create: function (args) {
        	$.ajax({
                type: "POST",
                url: "/manage/cityOrderConf/add",
                data: {
                	"cityId": $("#add_cityId", args).val(),
                    "orderType": $("#add_orderType", args).val(),
                    "costType": $("#add_costType", args).val(),
                    "costValue": $("#add_costValue", args).val(),
                    "remark": $("#add_remark", args).val()
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            base.success("添加成功！");
                            //$("#offerTable", args).bootstrapTable('selectPage', 1);
                            $("#offerTable",args).bootstrapTable('refresh');
                            $("#addModal", args).modal('hide');
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("添加失败!");
                    }
                }
            });
        },
        edit: function (args) {
            var self = this;
            var arrselections = $("#offerTable", args).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                base.error("只能选择一行进行编辑!");
                return;
            }
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            if(arrselections[0].state == 1){
            	base.error("已经启用的记录，不能修改，删除，只能禁用！");
            	return;
            }
            $('#editModal', args).modal({
                keyboard: false,
                backdrop: 'static'
            });
            $('#editModal', args).modal();
            $("#edit_provinceName", args).val(arrselections[0].provinceName);
            $("#edit_cityName", args).val(arrselections[0].cityName);
            $("#edit_orderType", args).val(arrselections[0].orderType);
            $("#edit_costType", args).val(arrselections[0].propertyValue);
            $("#edit_costValue", args).val(arrselections[0].costValue);
            $("#edit_cityOrderConfId", args).val(arrselections[0].id);
            $("#edit_remark", args).val(arrselections[0].remark);
            base.validator({
                fields: {
                	edit_costValue: {
                        validators: {
                            notEmpty: {
                                message: '费用金额不能为空'
                            },
                            integer: {
                            	message: '只能输入整数数值'
                    		},
                    		between: {
                    			message: "费用金额在1到1000元之间",
                                min: "1",
                                max: "1000"
                    		}
                        }
                    }
                }
            }, '#editForm', self.update, args);
        },
        update: function (args) {
            $.ajax({
                type: "POST",
                url: "/manage/cityOrderConf/update",
                data: {
                    "id": $("#edit_cityOrderConfId", args).val(),
                    "costValue": $("#edit_costValue", args).val()
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            base.success("修改成功！");
                            $("#offerTable", args).bootstrapTable('selectPage', 1);
                            $("#editModal", args).modal('hide');
                            $("#editForm", args).data("bootstrapValidator").resetForm(true);
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("修改失败!");
                    }
                }
            });
        },
        remove: function (args) {
            var arrselections = $("#offerTable", args).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            if(arrselections.length >1){
            	base.error("删除不支持批量操作，请逐条删除。");
            	return;
            }
            if(arrselections[0].state == 1){
            	base.error("已经启用的记录，不能修改，删除，只能禁用！");
            	return;
            }
            base.cancel({title: "删除", text: "您确定要删除吗？"}, function () {
                $.ajax({
                    type: "POST",
                    url: "/manage/cityOrderConf/del",
                    data: {
                        "id": arrselections[0].id
                    },
                    dataType: "json",
                    success: function (data, status) {
                    	if (status == "success") {
                            if (data.success == 0) {
                                $("#offerTable", args).bootstrapTable('selectPage', 1);
                                base.success("删除成功！");
                            } else {
                                base.error(data.message);
                            }
                        } else {
                            base.error("删除失败！");
                        }
                    }
                });
            });
        },
        enableFunc: function (args,flag) {
            var arrselections = $("#offerTable", args).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            for(var i=0;i<arrselections.length;i++){
                if(arrselections[i].state == 1){
                	base.error("部分记录已经是启用状态，请选择禁用的记录进行启用操作");
                	return;
                }
            }
            var orderIds = new Array();
            for(var i=0;i<arrselections.length;i++){
                orderIds[i] = arrselections[i].id;
            }
            i=0;
            $.ajax({
                type: "POST",
                url: "/manage/cityOrderConf/enableOrDisable",
                data: {
                	"ids": JSON.stringify(orderIds),
                    "state": 1
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            $("#offerTable", args).bootstrapTable('selectPage', 1);
                            base.success("启用成功！");
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("启用失败");
                    }
                }
            });
        },
        disableFunc: function (args,flag) {
            var arrselections = $("#offerTable", args).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            for(var i=0;i<arrselections.length;i++){
                if(arrselections[i].state == 0){
                	base.error("部分记录已经是禁用状态，请选择启用的记录进行禁用操作");
                	return;
                }
            }
            var orderIds = new Array();
            for(var i=0;i<arrselections.length;i++){
                orderIds[i] = arrselections[i].id;
            }
            i=0;
            $.ajax({
                type: "POST",
                url: "/manage/cityOrderConf/enableOrDisable",
                data: {
                    "ids": JSON.stringify(orderIds),
                    "state": 0
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            $("#offerTable", args).bootstrapTable('selectPage', 1);
                            base.success("禁用成功！");
                        } else {
                            base.error(data.message);
                        }
                    } else {
                        base.error("禁用失败");
                    }
                }
            });
        }
    };
});