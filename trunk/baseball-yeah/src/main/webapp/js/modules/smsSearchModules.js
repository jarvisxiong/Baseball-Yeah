/**
 * Created by wny on 2016-06-17.
 */
define(['base'], function (base) {
    var datatable;
    var _sendStoreID=null;
    var _type=null;
    var _status=null;
    var _startSubmitTime;
    var _endSubmitTime;
    var _keyWord=null;
    var _sendPhone=null;
    var _bizId;
    var _bizType;
    return {
        init: function (panel) {
            var self = this;

            //开始时间
            $('#starttimePicker',panel).datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: true,
                minView: 2
            })

            //结束时间
            $('#endtimePicker',panel).datetimepicker({
                format: 'yyyy-mm-dd hh:ii:ss',
                autoclose: true,
                pickTime: true,
                minView: 2
            });

            /*** 加载消息类型列表 ***/
            $.ajax({
                type: "POST",
                url: "/manage/propertydict/queryByAlias?alias=message_type_id",
                dataType: "json",
                success: function (data) {
                    $("#selmsgType",panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selmsgType',panel).select2("val", null);
                }
            });
            /*** 加载短信状态列表 ***/
            $.ajax({
                type: 'GET',
                url: '/message/sms/getSmsStatus',
                dataType: 'json',
                success: function (data) {
                    $("#selsmsStatus",panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selsmsStatus',panel).select2("val", null);
                }
            });
            /*** 加载门店列表 ***/
            $.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {
                    $("#selstore",panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selstore',panel).select2("val", null);
                }
            });

            $("#startdate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));
            $("#enddate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59).Format("yyyy-MM-dd HH:mm:ss"));
            _sendStoreID = $("#selstore",panel).val() == " " ? null : parseInt($("#selstore",panel).val());
            _type = $("#selmsgType",panel).val() == " " ? null : $("#selmsgType",panel).val();
            _status = $("#selsmsStatus",panel).val() == " " ? null : parseInt($("#selsmsStatus",panel).val());
            _startSubmitTime = $("#startdate",panel).val();
            _endSubmitTime = $("#enddate",panel).val();
            _keyWord = $("#txtRvPhone",panel).val() == "" ? null : $("#txtRvPhone",panel).val();
            _sendPhone = $("#txtSendPhone",panel).val() == "" ? null : $("#txtSendPhone",panel).val();
            function queryList() {
                datatable = base.datagrid({
                    url: '/message/sms/querySmsGrid',
                    method: 'post',
                    queryParams: function (params) {
                        return $.extend(params, {
                            sendStoreID: _sendStoreID,
                            type: _type,
                            status: _status,
                            startSubmitTime: _startSubmitTime,
                            endSubmitTime: _endSubmitTime,
                            keyWord: _keyWord,
                            sendPhone: _sendPhone
                        });
                    },
                    columns: [
                        {
                            field: 'phone',
                            title: '手机号码',
                            sortable: true
                        },
                        {
                            field: 'messageTypeText',
                            title: '消息类型',
                            sortable: true
                        },

                        {
                            field: 'smsVendorName',
                            title: '服务商通道',
                            sortable: true
                        },
                        {
                            field: 'content',
                            title: '短信内容',
                            sortable: true

                        },
                        {
                            field: 'submitTime',
                            title: '提交时间',
                            sortable: true,
                            formatter: function (value,
                                                 row, index) {
                                return value == null || value == "" ? "" : new Date(parseInt(value)).Format("yyyy-MM-dd HH:mm:ss");
                            }

                        },
                        {
                            field: 'status',
                            title: '发送状态',
                            visible: true,
                            sortable: true,
                            formatter: function (value,
                                                 row, index) {
                                return value == "0" ? "发送中" : (value == "1" ? "已发送" : (value == "2" ? "成功" : "失败"));
                            }
                        },
                        {
                            field: 'returnStatus',
                            title: '回执状态',
                            visible: true,
                            sortable: true
                        },
                        {
                            field: 'returnContent',
                            title: '回执说明',
                            visible: true,
                            sortable: true
                        },
                        {
                            field: 'lastGetDataTime',
                            title: '回执时间',
                            visible: true,
                            sortable: true,
                            formatter: function (value,
                                                 row, index) {
                                return value == null || value == "" ? "" : new Date(parseInt(value)).Format("yyyy-MM-dd HH:mm:ss");
                            }
                        },
                        {
                            field: 'sendUserText',
                            title: '发送人',
                            sortable: true
                        },
                        {
                            field: 'sendStoreText',
                            title: '发送商户',
                            sortable: true
                        },
                        {
                            field: 'retryCount',
                            title: '尝试发送次数',
                            visible: true,
                            sortable: true
                        },
                        {
                            field: 'bizId',
                            title: '相关业务信息',
                            align: 'center',
                            visible: true,
                            formatter: function (value,
                                                 row, index) {
                                if (row.messageTypeId == "p_noticetype_come") {
                                    return '<a href="#" class="table-link"><span class="fa-stack"><i class="fa fa-square fa-stack-2x"></i><i class="fa fa-search-plus fa-stack-1x fa-inverse"></i></span></a>';
                                }
                                else {
                                    return "";
                                }

                            },
                            width: 150
                        },
                        {
                            field: 'messageTypeId',
                            title: '消息类型id',
                            sortable: false,
                            visible: false
                        }
                    ],
                    onClickCell: function (field, value, row) {
                        switch (field) {
                            case "bizId":
                                if (row.messageTypeId == "p_noticetype_come") {
                                    self.scanInfo(value, "p_noticetype_come",panel);
                                }
                                return;

                        }
                    }
                }, '#userTable',panel);
                //datatable.bootstrapTable('refresh');
                datatable.bootstrapTable('selectPage', 1);
                $(window).resize(function () {
                    $('#wayBillLogTable',panel).bootstrapTable('resetView');
                });
            }

            $("#btn_query",panel).click(function () {
                _sendStoreID = $("#selstore",panel).val() == " " ? null : parseInt($("#selstore",panel).val());
                _type = $("#selmsgType",panel).val() == " " ? null : $("#selmsgType",panel).val();
                _status = $("#selsmsStatus",panel).val() == " " ? null : parseInt($("#selsmsStatus",panel).val());
                _startSubmitTime =$("#startdate",panel).val();
                _endSubmitTime = $("#enddate",panel).val();
                _keyWord = $("#txtRvPhone",panel).val() == "" ? null : $("#txtRvPhone",panel).val();
                _sendPhone =$("#txtSendPhone",panel).val() == "" ? null : $("#txtSendPhone",panel).val();
                if ((new Date(Date.parse(_endSubmitTime.replace(/-/g, "/"))).getTime() - new Date(Date.parse(_startSubmitTime.replace(/-/g, "/"))).getTime()) < 0) {
                    sweetAlert("", "结束时间不能小于开始时间!", "info");
                    return;
                }
                if (((new Date(Date.parse(_endSubmitTime.replace(/-/g, "/"))).getTime() - new Date(Date.parse(_startSubmitTime.replace(/-/g, "/"))).getTime()) / 1000 / 60 / 60 / 24) > 30) {
                    sweetAlert("", "开始时间和结束时间最多相隔30天!", "info");
                    return;
                }
                if ((_type == null)&&( isNaN(_sendStoreID)||_sendStoreID == null ||$.trim(_sendStoreID)=="") && (isNaN(_status)||_status == null || $.trim(_status))&&( isNaN(_status)|_status == null || $.trim(_status)=="")&&(_sendPhone==NaN|| _sendPhone == null || $.trim(_sendPhone)=="")&&(isNaN(_keyWord)|| _keyWord == null || $.trim(_keyWord)=="")) {
                    sweetAlert("", "除时间外至少设置一个查询条件!", "info");
                    return;
                }
                queryList();
            });
            $("#clearSearch",panel).click(function () {

                $("#startdate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 00, 00, 00).Format("yyyy-MM-dd HH:mm:ss"));
                $("#enddate",panel).val(new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate(), 23, 59, 59).Format("yyyy-MM-dd HH:mm:ss"));
                $("#txtRvPhone",panel).val("");
                $("#txtSendPhone",panel).val("");
                $("#selstore",panel).select2("val", null);
                $("#selmsgType",panel).select2("val", null);
                $("#selsmsStatus",panel).select2("val", null);
            });
        },

        scanInfo: function (bizId, bizType,panel) {

            _bizId = bizId;
            _bizType = bizType;
            $.ajax({
                type: "POST",
                url: "/message/sms/queryBizInfo",
                dataType: "json",
                data: "{\"bizId\":" + _bizId + ",\"type\":\"" + _bizType + "\"}",
                contentType: "application/json",
                success: function (data) {
                    if (data != null) {

                        $("#biz_waybillNumber",panel).val(data.waybillNumber);
                        $("#biz_expressCompanyText",panel).val(data.expressCompanyText);
                        $("#biz_addTime",panel).val(data.addTime);
                        $("#biz_scanTime",panel).val(data.scanTime);

                    } else {
                        $("#biz_waybillNumber",panel).val("");
                        $("#biz_expressCompanyText",panel).val("");
                        $("#biz_addTime",panel).val("");
                        $("#biz_scanTime",panel).val("");
                    }
                    $('#bizInfoModal',panel).modal({
                        keyboard: false,
                        backdrop: 'static'
                    });
                }
            });
        }


    }
});