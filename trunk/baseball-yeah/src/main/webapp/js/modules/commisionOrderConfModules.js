define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    return {
        init: function (args) {
            var self = this;
            base.datagrid({
                url: '/manage/commisionOrderConf/getCommisionOrderConfList',
                singleSelect:false,
                method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                    		collegeId:$("#collegeId", args).val(),
                    		state: $("#state", args).val(),
                    		roleType: $("#roleType", args).val(),
                    		orderType: $("#orderType", args).val(),
                    		phone: $("#phone", args).val(),
                    		createUserName: $("#createUserName", args).val(),
                    		createStartDate : $('#createStartDate', args).val(),
    						createEndDate : $('#createEndDate', args).val()
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
                        field: 'roleType',
                        title: 'roleType',
                        visible: false
                    },
                    {
                        field: 'userId',
                        title: 'userId',
                        visible: false
                    },
                    {
                        field: 'orderType',
                        title: 'orderType',
                        visible: false
                    },
                    {
                        field: 'costType',
                        title: 'costType',
                        visible: false
                    },
                    {
                        field: 'collegeId',
                        title: 'collegeId',
                        visible: false
                    },
                    {
                        field: 'collegeId',
                        title: 'collegeId',
                        visible: false
                    },
                    {
                        field: 'phone',
                        title: '手机号'
                    },
                    {
                        field: 'userName',
                        title: '姓名'
                    },
                    {
                        field: 'roleTypeDesc',
                        title: '角色'
                    },
                    {
                        field: 'orderTypeDesc',
                        title: '订单类型'
                    },
                    {
                        field: 'stateDesc',
                        title: '状态'
                    },
                    {
                        field: 'costTypeDesc',
                        title: '费用类型'
                    },
                    {
                        field: 'costValueYuan',
                        title: '抽成费用（元）'
                    },
                    {
                        field: 'supervisorPhone',
                        title: '商户手机号'
                    },                    
                    {
                        field: 'supervisorName',
                        title: '商户姓名'
                    },
                    {
                        field: 'collegeName',
                        title: '校区'
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
            }, '#commisionTable', args);

            //加载角色列表
            $.ajax({
                type: "GET",
                url: "/manage/commonSelectData/getRoleList",
                dataType: "json",
                success: function (DATA) {
                    $("#roleType",args).select2({data: DATA});
                    $("#add_roleType",args).select2({data: DATA});
                }
            });
            
            //加载订单类型列表
            $.ajax({
                type: "GET",
                url: "/manage/commonSelectData/getOrderType",
                dataType: "json",
                success: function (DATA) {
                    $("#orderType",args).select2({data: DATA});
                    $("#add_orderType",args).select2({data: DATA});
                    $("#edit_orderType",args).select2({data: DATA});
                }
            });
            
            //加载状态列表
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
                url: "/manage/propertydict/queryByAlias?alias=COMMISION_COST",
                dataType: "json",
                success: function (DATA) {
                	$('#add_costType',args).select2({data: DATA});
                    $('#edit_costType',args).select2({data: DATA});
                }
            });
            
            //请求校区信息
            $.ajax({
                type: "GET",
                url: "/manage/college/getCollageForSel",
                dataType: "json",
                success: function (data) {
                    $("#collegeId", args).select2({
                        data: data.data
                    });
                    $("#add_collegeId", args).select2({
                        data: data.data
                    });
                    $("#edit_collegeId", args).select2({
                        data: data.data
                    });
                }
            });
            
            //利用select2初始化样式
            $('#add_supervisorId',args).select2();
            $('#edit_supervisorId',args).select2();
            
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
                $("#collegeId",args).val(" ").trigger("change");
                $("#state",args).val(" ").trigger("change");
                $("#roleType",args).val(" ").trigger("change");
                $("#orderType",args).val(" ").trigger("change");
                $("#phone",args).val("");
                $("#createUserName",args).val("");
                $("#createEndDate",args).val("");
                $("#createStartDate",args).val("");
            });
            $("#btn_query", args).click(function () {
                $("#commisionTable",args).bootstrapTable('refresh');
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
            //展示增加的弹出框的时候，初始化操作
            $('#addModal', args).on('shown.bs.modal', function () {
                $("#add_roleType",args).val(" ").trigger("change");
                $("#add_phone", args).val("");
                $("#add_userName", args).val("");
                $("#add_userId", args).val("");
                $("#add_collegeId", args).empty();
                $("#add_orderType",args).val(" ").trigger("change");
                $('#add_costType',args).val(" ").trigger("change");
                $('#add_supervisorId',args).val(" ").trigger("change");
                $("#add_costValue", args).val("");
            	$("#add_remark", args).val("");
            	$('#addForm', args).data('bootstrapValidator').resetForm(true);
            	$("#add_userName",args).attr("disabled", true);
            	$("#add_phone",args).attr("disabled", true);
            });
            
            //改变角色的时候重新校验手机号码
            $("#add_roleType",args).on("change",
                function (e) {
            		$this = $.trim($(this).val());
            		if($this == ""){
            			$("#add_phone",args).attr("disabled", true);
            			$("#add_userName", args).val("");
                        $("#add_userId", args).val("");
                        $("#add_collegeId", args).val(" ").trigger("change");
            			return;
            		}
            		$("#add_phone",args).attr("disabled", false);
            		if($this == "store"){
            			$("#add_phone", args).val("");
            			$("#add_userName", args).val("");
                        $("#add_userId", args).val("");
                        //商户的时候无条件初始化出学校列表
                        self._initCollegeSelect(args);
	                }else{
	                    //CEO,COO的时候带出姓名和学校，角色和学校肯定有值
	                	var phone = $("#add_phone", args).val();
	                	if(phone.length == 11){
	                		self._initUserInfo(phone,args,self);
	                	}
	                }
            	}
            );
            
            //改变手机号码之后，去后台查询，带出姓名，学校
            $("#add_phone",args).on("change",
                function (e) {
                    var phone = $(this).val();
                    if (phone.length != 11) {
                        return;
                    }
                    //商户的时候无条件初始化出学校列表
                    self._initCollegeSelect(args);
                    //CEO,COO的时候带出姓名和学校，角色和学校肯定有值
                    self._initUserInfo(phone,args,self);
            	}
            );
            
            //费用类型为导入费，角色为COO的时候
            $("#add_costType",args).on("change",
                function (e) {
            		var $this = $.trim($(this).val());
            		var roleType = $("#add_roleType",args).val();
            		self._initSupervisorSelect(roleType,$this,null,args);
            	}
            );
            
            //费用类型为导入费，角色为COO的时候
            $("#edit_costType",args).on("change",
                function (e) {
            		//费用类型 角色，用户ID，商户ID args
            		var $this = $.trim($(this).val());
            		var roleType = $("#edit_roleType",args).val();
            		var userId = $("#edit_userId",args).val();
            		var edit_userId = $("#edit_roleType",args).val();
            		var supervisorId = $("#edit_supervisorId",args).val();
            		self._initSupervisorSelectForEdit(roleType,$this,userId,null,args);
            	}
            );

        },
        _initSupervisorSelect: function(roleType,costType,userId,args){
        	//初始化出商户信息，前提是学校需要有值，角色为COO，费用为导入费
        	if(userId == null){
        		userId = $("#add_userId", args).val();
        	}
        	if(costType == "1012" && roleType == "coo"){
        		if($.trim(userId)==""){
        			$("#add_supervisorId", args).val("");
        			$("#add_supervisorId_div",args).hide();
        			return;
            	}
        		$("#add_supervisorId_div",args).show();
                $.ajax({
                    type: "GET",
                    url: "/manage/commisionOrderConf/getSupervisorList",
                    dataType: "json",
                    data: {
                        "userId": $("#add_userId",args).val()
                    },
                    success: function (data) {
                    	var control = $('#add_supervisorId',args);
                    	control.empty();
                    	control.append("<option value=' '>请选择</option>");
                        $.each(data, function (i, item) {
                            control.append("<option value='" + item.id + "'>" + item.text + "</option>");
                        });
                    }
                });
    		}else{
    			$("#add_supervisorId", args).val("");
    			$("#add_supervisorId_div",args).hide();
    		}
        },
        _initCollegeSelect: function(args){
        	//商户的时候初始化出学校列表
        	if($("#add_roleType",args).val() == "store"){
                //请求校区信息
                $.ajax({
                    type: "GET",
                    url: "/manage/college/getCollageForSel",
                    dataType: "json",
                    success: function (data) {
                    	var control = $('#add_collegeId',args);
                    	control.empty();
                        $.each(data.data, function (i, item) {
                            control.append("<option value='" + item.id + "'>" + item.text + "</option>");
                        });
                        control.val(" ").trigger("change");
                    }
                });
            }
        },
        _initUserInfo: function(phone,args,_this){
        	//CEO,COO的时候带出姓名和学校
        	//因为初始出学校，所以会顺便去查看商户是否能够初始，如果条件充分则顺便初始出商户或者隐藏商户
            var roleType = $("#add_roleType",args).val();
            var costType = $("#add_costType",args).val();
            $.ajax({
                type: "POST",
                url: "/manage/commisionOrderConf/getUserInfoByPhone",
                data: {
                    "phone": phone,
                    "roleType" : roleType
                },
                dataType: "json",
                success: function (data) {
                	if(data.userId==""){
                		if(data.message !=""){
                			base.error(data.message);
                		}else{
                			base.error("输入的手机号码和角色不对应，请确认！");
                			$("#add_userId", args).val("");
                			$("#add_userName", args).val("");
                			$('#add_collegeId',args).val(" ").trigger("change");
                		}
                		//顺便去看下是否能显示商户
                		_this._initSupervisorSelect(roleType,costType,null,args);
                		return ;
                	}
                	$("#add_userId", args).val(data.userId);
                	$("#add_userName", args).val(data.userName);
                	if(roleType != "store"){
                    	var control = $('#add_collegeId',args);
                    	control.empty();
                    	control.append("<option value='" + data.collegeId + "'>" + data.collegeName + "</option>");
                    	control.val(data.collegeId).trigger("change");
                    	//顺便去看下是否能显示商户
                    	_this._initSupervisorSelect(roleType,costType,data.userId,args);
                	}
                }
            });
        },
        add: function (args) {
            var self = this;
            $('#addModal', args).modal({
                keyboard: false,
                backdrop: 'static'
            });
            base.validator({
                fields: {
                	add_roleType: {
                        validators: {
                            notEmpty: {
                                message: '角色不能为空'
                            }
                        }
                    },
                    add_phone: {
                        validators: {
                            notEmpty: {
                                message: '手机号码不能为空'
                            },
							regexp : {
								regexp : /^[1]+[3,5,7,8]+\d{9}/,
								message : '手机号格式不正确'
							}
                        }
                    },
                    add_userId: {
                        validators: {
                            notEmpty: {
                                message: '姓名不能为空'
                            }
                        }
                    },
                    add_collegeId: {
                        validators: {
                            notEmpty: {
                                message: '学校不能为空'
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
                            },
                            callback: {
                                message: 'CEO没有导入费，商户没有服务费！',
                                callback: function(value, validator) {
                                	var roleType = $("#add_roleType", args).val();
                                	if((roleType=="ceo" && value=="1012") || (roleType=="store" && value=="1013")){
                                		return false;
                                	}else{
                                		return true;
                                	}
                                }
                            }
                        }
                    },
                    add_supervisorId: {
                    	validators: {
                    		callback: {
                                message: 'COO导入费的时候商户手机号不能为空',
                                callback: function(value, validator) {
                                	if($("#add_roleType", args).val()=="coo" && $("#add_costType", args).val()=="1012"){
                                		if(null==value || $.trim(value) == ""){
                                    		return false;
                                		}else{
                                			return true;
                                		}
                                	}else{
                                		return true;
                                	}
                                }
                            }
                    	}
                    },
                    add_costValue: {
                        validators: {
                            notEmpty: {
                                message: '费用金额不能为空'
                            },
                            numeric: {
                            	message: '只能输入整数数值'
                    		},
                    		between: {
                    			message: "费用金额在0到10元之间，允许小数",
                                min: "0",
                                max: "10"
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
        	var userId = $("#add_userId",args).val();
        	if(userId == ""){
        		base.error("输入的手机号码和角色不对应，请确认！");
        		return ;
        	}
        	if($("#add_roleType", args).val()=="ceo" && $("#add_costType", args).val()=="1012"){
        		base.error("CEO没有导入费！");
        		return ;
        	}
        	if($("#add_roleType", args).val()=="store" && $("#add_costType", args).val()=="1013"){
        		base.error("商户没有服务费！");
        		return ;
        	}
        	
        	$.ajax({
                type: "POST",
                url: "/manage/commisionOrderConf/add",
                data: {
                	"userId": $("#add_userId", args).val(),
                    "supervisorId": $("#add_supervisorId", args).val(),
                    "collegeId": $("#add_collegeId", args).val(),
                    "roleType": $("#add_roleType", args).val(),
                    "orderType": $("#add_orderType", args).val(),
                    "costType": $("#add_costType", args).val(),
                    "costValueYuan": $("#add_costValue", args).val(),
                    "remark": $("#add_remark", args).val()
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            base.success("添加成功！");
                            $("#commisionTable",args).bootstrapTable('refresh');
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
        _initSupervisorSelectForEdit: function(roleType,costType,userId,supervisorId,args){
        	if(roleType == "coo" && costType == "1012"){
        		$("#edit_supervisorId_div",args).show();
                $.ajax({
                    type: "GET",
                    url: "/manage/commisionOrderConf/getSupervisorList",
                    dataType: "json",
                    data: {
                        "userId": userId
                    },
                    success: function (data) {
                    	var control = $('#edit_supervisorId',args);
                    	control.empty();
                    	control.append("<option value=' '>请选择</option>");
                        $.each(data, function (i, item) {
                            control.append("<option value='" + item.id + "'>" + item.text + "</option>");
                        });
                        if(null != supervisorId){
                        	control.val(supervisorId).trigger("change");
                        }
                    }
                });
    		}else{
    			$("#edit_supervisorId", args).val("");
    			$("#edit_supervisorId_div",args).hide();
    		}
        },
        edit: function (args) {
            var self = this;
            var arrselections = $("#commisionTable", args).bootstrapTable('getSelections');
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
            $("#edit_id", args).val(arrselections[0].id);
            $("#edit_roleTypeDesc", args).val(arrselections[0].roleTypeDesc);
            $("#edit_roleType", args).val(arrselections[0].roleType);
            $("#edit_phone", args).val(arrselections[0].phone);
            $("#edit_userId", args).val(arrselections[0].userId);
            $("#edit_userName", args).val(arrselections[0].userName);
            $("#edit_collegeId", args).val(arrselections[0].collegeId).trigger("change");
            $("#edit_collegeId", args).attr("disabled", true);
            $("#edit_orderType",args).val(arrselections[0].orderType).trigger("change");
            $('#edit_costType',args).val(arrselections[0].costType).trigger("change");
            //如果符合条件再去拿一次商户信息
            self._initSupervisorSelectForEdit(arrselections[0].roleType,arrselections[0].costType,
            		arrselections[0].userId,arrselections[0].supervisorId,args);
            $("#edit_costValue", args).val(arrselections[0].costValueYuan);
            $("#edit_remark", args).val(arrselections[0].remark);
            base.validator({
                fields: {
                	edit_orderType: {
                        validators: {
                            notEmpty: {
                                message: '订单类型不能为空'
                            }
                        }
                    },
                    edit_costType: {
                        validators: {
                            notEmpty: {
                                message: '费用类型不能为空'
                            },
                            callback: {
                                message: 'CEO没有导入费，商户没有服务费！',
                                callback: function(value, validator) {
                                	var roleType = $("#edit_roleType", args).val();
                                	if((roleType=="ceo" && value=="1012") || (roleType=="store" && value=="1013")){
                                		return false;
                                	}else{
                                		return true;
                                	}
                                }
                            }
                        }
                    },
                	edit_costValue: {
                        validators: {
                            notEmpty: {
                                message: '费用金额不能为空'
                            },
                            numeric: {
                            	message: '只能输入整数数值'
                    		},
                    		between: {
                    			message: "费用金额在0到10元之间，允许小数",
                                min: "0",
                                max: "10"
                    		}
                        }
                    },
                    edit_supervisorId: {
                    	validators: {
                    		callback: {
                                message: 'COO导入费的时候商户手机号不能为空',
                                callback: function(value, validator) {
                                	if($("#edit_roleType", args).val()=="coo" && $("#edit_costType", args).val()=="1012"){
                                		if(null==value || $.trim(value) == ""){
                                    		return false;
                                		}else{
                                			return true;
                                		}
                                	}else{
                                		return true;
                                	}
                                }
                            }
                    	}
                    }
                }
            }, '#editForm', self.update, args);
        },
        update: function (args) {
            //业务校验
        	if($("#edit_roleType", args).val()=="ceo" && $("#edit_costType", args).val()=="1012"){
        		base.error("CEO没有导入费！");
        		return ;
        	}
        	if($("#edit_roleType", args).val()=="store" && $("#edit_costType", args).val()=="1013"){
        		base.error("商户没有服务费！");
        		return ;
        	}
        	$.ajax({
                type: "POST",
                url: "/manage/commisionOrderConf/update",
                data: {
                    "id": $("#edit_id", args).val(),
                    "userId": $("#edit_userId", args).val(),
                    "roleType": $("#edit_roleType", args).val(),
                    "collegeId": $("#edit_collegeId", args).val(),
                    "orderType": $("#edit_orderType", args).val(),
                    "costType": $("#edit_costType", args).val(),
                    "supervisorId": $("#edit_supervisorId", args).val(),
                    "costValueYuan": $("#edit_costValue", args).val(),
                    "remark": $("#edit_remark", args).val()
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            base.success("修改成功！");
                            $("#commisionTable", args).bootstrapTable('selectPage', 1);
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
            var arrselections = $("#commisionTable", args).bootstrapTable('getSelections');
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
                    url: "/manage/commisionOrderConf/del",
                    data: {
                        "id": arrselections[0].id
                    },
                    dataType: "json",
                    success: function (data, status) {
                    	if (status == "success") {
                            if (data.success == 0) {
                                $("#commisionTable", args).bootstrapTable('selectPage', 1);
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
            var arrselections = $("#commisionTable", args).bootstrapTable('getSelections');
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
                url: "/manage/commisionOrderConf/enableOrDisable",
                data: {
                	"ids": JSON.stringify(orderIds),
                    "state": 1
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            $("#commisionTable", args).bootstrapTable('selectPage', 1);
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
            var arrselections = $("#commisionTable", args).bootstrapTable('getSelections');
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
                url: "/manage/commisionOrderConf/enableOrDisable",
                data: {
                    "ids": JSON.stringify(orderIds),
                    "state": 0
                },
                dataType: "json",
                success: function (data, status) {
                	if (status == "success") {
                        if (data.success == 0) {
                            $("#commisionTable", args).bootstrapTable('selectPage', 1);
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