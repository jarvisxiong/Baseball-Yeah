define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var validate = {
        fields: {
            thdId: {
                validators: {
                    notEmpty: {
                        message: '交易流水号不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 32,
                        message: '交易流水号必须在1到32位之间'
                    }
                }
            }
        }
    };

    window.operateEvents = {
       'click .seeCheck': function (e, value, row, index) {
            switch (row.state) {
                //case 0:
                //$('#apply').attr("checked","checked");
                //	break;
                case 1:
                    //$('#checking').attr("checked","checked");
                    $("#state").html("审核通过");
                    break;
                case 0:
                case 3: //审核失败，已到帐则不可再操作
                case 2:
                    sweetAlert("", "审核通过才可编辑", "warning");
                    return;
            }
            $('#exchangeId').val(row.exchangeId);
            $('#flowId').val(row.flowId);
            $('#createTime').html(row.createTime);
            $('#userName').html(row.userName);
            $('#transType').html(row.transType);
            $('#drawAmount').html(row.drawAmount / 100);
            $('#addModal').modal();
        },
        'click .seeUserInfo': function (e, value, row, index) { 
        	$('#drawAcctNo').html(row.acctNo);
            $('#nickName').html(row.nickname);
            $('#idNo').html(row.idNo);
            $('#openId').html(row.openId);
            if(row.transType == "支付宝"){
            	$('.myopenid').addClass("myvisible");
            } else {
            	$('.myopenid').removeClass("myvisible");
            }
            
            $('#showDrawUseModal').modal();
        }
    };

    var datatable;

    return {
        init: function (args) {
            var self = this;
            datatable = base.datagrid({
                url: '/wallet/draw/getdrawlist',
                queryParams: function (params) {
                    return $.extend(params, {
                        thdType: $("#transationid").val() != "all" ? $("#transationid").val() : null,
                        state: $("#orderstate").val(),
                        userId: $('#applyerid').val(),
                        checkerId: $('#checkerid').val(),
                        thdFlowId: $('#thdFlowId').val(),
                        applybgnTime: $('#apply_start_date').val(),
                        applyendTime: $('#apply_end_date').val(),
                        checkbgnTime: $('#check_start_date').val(),
                        checkendTime: $('#check_end_date').val(),
                        arrivebgnTime: $('#arrive_start_date').val(),
                        arriveendTime: $('#arrive_end_date').val(),
                        timeStamp: (new Date()).valueOf()
                    });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'createTime',
                        title: '提现日期',
                        sortable: true
                    },
                    {
                        field: 'state',
                        title: '提现状态',
                        sortable: true,
                        align: 'center',
                        formatter: function (value,
                                             row, index) {
                            switch (value) {
                                case 0:
                                    return "<a class='label label-info'>提交申请</a>";
                                case 1:
                                    return "<a class='label label-success'>处理中</a>";
                                case 2:
                                    return "<a class='label label-success'>已到帐</a>";
                                case 3:
                                    return "<a class='label label-warning'>审核失败</a>";
                            }
                        }
                    },
                    {
                        field: 'transType',
                        title: '账号类型',
                        sortable: true
                    },
                    /*{
                        field: 'acctNo',
                        title: '提现帐号',
                        sortable: true
                    },*/
                    {
                        field: 'drawAmount',
                        title: '提现金额(元)',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value / 100;
                        }
                    },
                    {
                        field: 'userName',
                        title: '申请人',
                        sortable: true,
                        align: 'center',
                        events: operateEvents,
                        formatter: function (value, row, index) {
                            return '<a href="#" class="seeUserInfo"><span class="label label-success">'+ value + '</span></a>';
                        }
                    },
                    {
                        field: 'checkEmp',
                        title: '审核人',
                        sortable: true
                    },
                    {
                        field: 'checkTime',
                        title: '审核时间',
                        sortable: true
                    },
                    {
                        field: 'thdFlowId',
                        title: '交易流水号',
                        sortable: true
                    },
                    {
                        field: 'arriTime',
                        title: '到账时间',
                        sortable: true
                    }]
            }, '#walletCheckTable');

            $.ajax({
                type: "POST",
                url: "/wallet/draw/getthdpayment",
                dataType: "json",
                success: function (data) {
                    $("#transationid").select2({
                        placeholder: '请选择交易类型',
                        allowClear: true,
                        data: data
                    });
                }
            });

            $("#orderstate").select2({
                placeholder: '请选择订单状态',
                allowClear: true
            });

            $('#btn_query').click(function () {
            	if($('#apply_start_date').val()==='' || $('#apply_end_date').val()===''){
            		base.error("提现日期不能为空!");
            		return;
            	}
                datatable.bootstrapTable('refresh');
            });

            $(window).resize(function () {
                $('#walletCheckTable').bootstrapTable('resetView');
            });
            
            $('#btnUpdate').click(function(){
            	var rows = $('#walletCheckTable').bootstrapTable('getSelections');
                if (rows.length < 1) {
                    sweetAlert("", "请选择有效数据", "warning");
                    return;
                }
            	var row = rows[0];
            	switch (row.state) {
	                case 1:
	                    $("#state").html("审核通过");
	                    break;
	                case 0:
	                case 3: //审核失败，已到帐则不可再操作
	                case 2:
	                    sweetAlert("", "审核通过才可编辑", "warning");
	                    return;
	            }
	            $('#exchangeId').val(row.exchangeId);
	            $('#flowId').val(row.flowId);
	            $('#createTime').html(row.createTime);
	            $('#userName').html(row.userName);
	            $('#transType').html(row.transType);
	            $('#drawAmount').html(row.drawAmount / 100);
	            $('#addModal').modal();
            });
            
            $("#clearSearch").click(function () {
                base.reset(".panel-body");
                $("#transationid").val(" ").trigger("change");
                $("#orderstate").val(" ").trigger("change");
            });

            $('#addModal').on('show.bs.modal', function () {
                base.validator(validate, "#checkForm", self.checkok);
            });
            
            $('#addModal').on('hidden.bs.modal', function () {
                $('#checkForm').data('bootstrapValidator').resetForm(true);
                $("#checkForm").data('bootstrapValidator').destroy();
            });

            $('#btnAudit').click(function(){
            	var rows = $('#walletCheckTable').bootstrapTable('getSelections');
                if (rows.length < 1) {
                    sweetAlert("", "请选择有效数据", "warning");
                    return;
                }
            	var row = rows[0];
	            $('#createTime1').html(row.createTime);
	            $('#userName1').html(row.userName);
	            $('#drawAmount1').html(row.drawAmount / 100);
	            $('#isApprove').prop('checked',false);
	            $('#isRefuse').prop('checked',false);
	            $('#checkDrawModal').modal();
            });
            
            $('#btnSave').click(function(){
            	self.drawcheck();
            });

        },
        checkok: function () {
            $.post("/wallet/draw/addthdid", {
                "exchangeId": $("#exchangeId").val(),
                "thdId": $('#thdId').val(),
                "flowId": $('#flowId').val()
            }, function (data, status) {
                if (status == "success") {
                    var obj = json_parse(data);
                    if (obj.success == 0) {
                        $("#walletCheckTable").bootstrapTable('refresh');
                        $('#addModal').modal('hide');
                        $('#checkForm').data('bootstrapValidator').resetForm(true);
                        base.success("保存成功！");
                    } else {
                        base.error(obj.message);
                    }
                } else {
                    base.error("数据加载失败!");
                }
            });
        },
        drawcheck: function() {
        	var self = this;
        	var rows = $('#walletCheckTable').bootstrapTable('getSelections');
            if (rows.length < 1) {
                sweetAlert("", "请选择有效数据", "warning");
                return;
            }
            var row = null;
        	if($('#isApprove').prop('checked')){
        		for (var i = 0; i < rows.length; i++) {
                    if (rows[i].state == 0) {
                    	row = rows[i];
                    }
                }
                if (row == null) {
                    sweetAlert("", "只有申请状态数据才可以审核", "warning");
                    return;
                }
        		self.drawcheckok(row);
        	} else if($('#isRefuse').prop('checked')){
        		for (var i = 0; i < rows.length; i++) {
                    if (rows[i].state == 0 || rows[i].state == 1) {
                    	row = rows[i];
                    }
                }
                if (row == null) {
                    sweetAlert("", "已审核失败或已到帐不可再审核", "warning");
                    return;
                }
        		self.drawcheckno(row);
        	}
        },
        drawcheckok: function(row) {
        	sweetAlert({
                title: "提示信息",
                text: "确认审核通过该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": row.exchangeId,
                    "isok": "1",
                    "flowId": row.flowId
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            $("#walletCheckTable").bootstrapTable('refresh');
                            $('#checkDrawModal').modal('hide');
                            base.success("审核通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
        drawcheckno: function(row) {
        	sweetAlert({
                title: "提示信息",
                text: "确认驳回该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": row.exchangeId,
                    "isok": "0",
                    "drawAmount":row.drawAmount,
                    "flowId": row.flowId
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            $("#walletCheckTable").bootstrapTable('refresh');
                            $('#checkDrawModal').modal('hide');
                            base.success("审核不通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
        batchcheckok: function () {
            var rows = $('#walletCheckTable').bootstrapTable('getSelections');
            if (rows.length < 1) {
                sweetAlert("", "请选择有效数据", "warning");
                return;
            }
            var exchangeIds = [];
            var flowIds = [];
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].state == 0) {
                    exchangeIds.push(rows[i].exchangeId);
                    flowIds.push(rows[i].flowId);
                }
            }
            if (exchangeIds.length < 1) {
                sweetAlert("", "只有申请状态数据才可以审核", "warning");
                return;
            }
            sweetAlert({
                title: "提示信息",
                text: "确认审核通过该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": exchangeIds[0],
                    "isok": "1",
                    "flowId": flowIds[0]
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            $("#walletCheckTable").bootstrapTable('refresh');
                            $('#addModal').modal('hide');
                            base.success("审核通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
        batchcheckno: function () {
            var rows = $('#walletCheckTable').bootstrapTable('getSelections');
            if (rows.length < 1) {
                sweetAlert("", "请选择有效数据", "warning");
                return;
            }
            var exchangeIds = [];
            var flowIds = [];
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].state == 0 || rows[i].state == 1) {
                    exchangeIds.push(rows[i].exchangeId);
                    flowIds.push(rows[i].flowId);
                }
            }
            if (exchangeIds.length < 1) {
                sweetAlert("", "已审核失败或已到帐不可再审核", "warning");
                return;
            }
            sweetAlert({
                title: "提示信息",
                text: "确认驳回该项申请?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                closeOnConfirm: true
            }, function () {
                $.post("/wallet/draw/check", {
                    "exchangeId": exchangeIds[0],
                    "isok": "0",
                    "flowId": flowIds[0]
                }, function (data, status) {
                    if (status == "success") {
                        var obj = json_parse(data);
                        if (obj.success == 0) {
                            $("#walletCheckTable").bootstrapTable('refresh');
                            $('#addModal').modal('hide');
                            base.success("审核不通过！");
                        } else {
                            base.error(obj.message);
                        }
                    } else {
                        base.error("数据加载失败!");
                    }
                });
            });
        },
    };
});