define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */

    var initialData = [
        {
            policyName: "", policyPriority: "", policyType: "", effectDays: "", remark: "", deliverys: [], receives: [], uses: []
        }
    ];

    window.isEdit = false;
    window.dosubscribe = true;

    function isFunction(obj) {
        return Object.prototype.toString.call(obj) === '[object Function]'
    }

    function refParentPage(panel) {
        panel = panel || $('#indextab').tabs('getSelected');
        $("#userTable", panel).bootstrapTable('refresh', {"query": {"offset": 0}});
    }

    var times = 0

    function show(panel) {
        $("#activitySave", panel).removeAttr("disabled");
    }

    function totalCalculation() {
        var panel = $('#indextab').tabs('getSelected');
        var total = 0;
        $(".btn_delivery_add", panel).each(function () {
            $(this).parent().find(".row").each(function () {
                total += $(this).find(".faceValue").val() * $(this).find(".totalAmount").val();
            })
        })
        $("#reckon", panel).text(total);
    }

    //根据数据 遍历给opertion 赋值
    function setOperation(model) {

    }

    var selectfunction = function (that) {
        var $row = $(that).parent().parent();
        switch ($(that).val()) {
            case "LEAD_TIMES":
                $row.find(".quotaDivTextValue").show();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").hide();

                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='<' selected='selected'>小于</option>");
                $row.find(".selop").append("<option value='<='>小于等于</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();//手动触发事件 后面还是改为ko控件好
                break;
            case "ORDER_COUNT":
                $row.find(".quotaDivTextValue").show();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").hide();
                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='=' selected='selected'>等于</option>");
                $row.find(".selop").append("<option value='<='>小于等于</option>");
                $row.find(".selop").append("<option value='<' >小于</option>");
                $row.find(".selop").append("<option value='>='>大于等于</option>");
                $row.find(".selop").append("<option value='>'>大于</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();
                break;
            case "END_TIME":
                $row.find(".quotaTimeValue").datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });
                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").show();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").hide();
                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='<' selected='selected'>小于</option>");
                $row.find(".selop").append("<option value='<='>小于等于</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();
                break;
            case "START_TIME":
                $row.find(".quotaTimeValue").datetimepicker(
                    {
                        format: 'yyyy-mm-dd',
                        autoclose: true,
                        pickTime: false,
                        minView: 2
                    });
                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").show();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").hide();


                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='>' selected='selected'>大于</option>");
                $row.find(".selop").append("<option value='>='>大于等于</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();
                break;
            case "PROVINCE_ID":
                $row.find(".quotaProvinceField").select2({
                    data: window.provinceData
                });
                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").show();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").hide();

                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='=' selected='selected'>等于</option>");
                $row.find(".selop").append("<option value='in'>包含</option>");
                $row.find(".selop").append("<option value='notin'>不包含</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();
                break;
            case "CAN_USE":
                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").show();
                $row.find(".quotaDivOrderTypeValue").hide();
                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='=' selected='selected'>等于</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();

                break;
            case "IS_NEW":
                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").show();
                $row.find(".quotaDivOrderTypeValue").hide();
                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='=' selected='selected'>等于</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();

                break;
            case "COLLEGE_ID":
                if (window.isEdit) {
                    $row.find(".quotaCollegeField").select2({
                        // data: window.collegeData
                        ajax: {
                            url: "/manage/college/getcollageajaxsel",
                            data: function (params) {
                                return {
                                    fullName: params.term,
                                    editCollegeId: $row.find(".inCollege").val()
                                };
                            },
                            processResults: function (data) {
                                return {
                                    results: data.data
                                };
                            }
                        }
                    });
                } else {
                    $row.find(".quotaCollegeField").select2({
                        ajax: {
                            url: "/manage/college/getcollageajaxsel",
                            data: function (params) {
                                return {
                                    fullName: params.term
                                };
                            },
                            processResults: function (data) {
                                return {
                                    results: data.data
                                };
                            }
                        }
                    });
                }

                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").show();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").hide();
                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='=' selected='selected'>等于</option>");
                $row.find(".selop").append("<option value='in'>包含</option>");
                $row.find(".selop").append("<option value='notin'>不包含</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();
                break;
            case "CITY_ID":
                $row.find(".quotaCityField").select2({
                    data: window.cityData
                });
                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").show();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").hide();
                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='=' selected='selected'>等于</option>");
                $row.find(".selop").append("<option value='in'>包含</option>");
                $row.find(".selop").append("<option value='notin'>不包含</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();
                break;
            case "ORDER_TYPE":
                $row.find(".quotaDivTextValue").hide();
                $row.find(".quotaDivProvinceValue").hide();
                $row.find(".quotaDivCollegeValue").hide();
                $row.find(".quotaDivCityValue").hide();
                $row.find(".quotaDivTimeValue").hide();
                $row.find(".quotaDivBoolValue").hide();
                $row.find(".quotaDivOrderTypeValue").show();
                $row.find(".selop").empty();
                $row.find(".selop").append("<option value='=' selected='selected'>等于</option>");
                $row.find(".selop").append("<option value='in'>包含</option>");
                if (window.isEdit) {
                    $row.find(".selop").val($row.find(".operasel").val());
                }
                $row.find(".selop").change();
                break;
        }
    }

    var Delivery = function (dto) {

        var self = this;
        self.faceValue = ko.observable();
        self.scale = ko.observable();
        self.totalAmount = ko.observable();
        //面额
        self.faceValue.subscribe(function (val) {
            var panel = $('#indextab').tabs('getSelected');
            if (window.dosubscribe) {
                if (window.isEdit) {
                    return;
                }
                var amount = $("#putAmount", panel).val();
                if (self.scale()) {
                    var tta = amount * self.scale() / 100 / self.faceValue();
                    var re = /^[0-9]*$/;
                    if (!re.test(tta)) {
                        base.error("计算结果有小数位！");
                        self.totalAmount(0);
                        totalCalculation();
                        return;
                    }
                    if (tta != self.totalAmount()) {
                        self.totalAmount(tta);
                        totalCalculation();
                    }
                }
            }
        })
        //输入百分比触发的事件 计算金额和相关校验
        self.scale.subscribe(function (val) {
            var panel = $('#indextab').tabs('getSelected');
            if (window.dosubscribe) {
                if (window.isEdit) {
                    return;
                }
                if (!$("#putAmount", panel).val()) {
                    base.error("请输入投放总金额!");
                    self.scale(0);
                    return;
                }
                var amount = $("#putAmount", panel).val();
                var tta = amount * val / 100 / self.faceValue();
                var re = /^[0-9]*$/;
                if (!re.test(tta)) {
                    base.error("计算结果有小数位！");
                    self.totalAmount(0);
                    return;
                }
                if (tta != self.totalAmount()) {
                    self.totalAmount(tta);
                }
                totalCalculation();
            }
        });
        //张数
        self.totalAmount.subscribe(function (val) {
            var panel = $('#indextab').tabs('getSelected');
            if (window.dosubscribe) {
                if (window.isEdit) {
                    return;
                }
                if (!$("#putAmount", panel).val()) {
                    base.error("请输入投放总金额!");
                    return;
                }
                var amount = $("#putAmount", panel).val();
                if (!self.faceValue()) {
                    base.error("请输入面额!");
                    return;
                }
                var ssc = (self.faceValue() * val / amount * 100);
                if (ssc != self.scale()) {
                    self.scale(ssc);
                }
                totalCalculation();
            }
        });
        if (dto) {
            self.faceValue(dto.faceValue);
            self.scale(dto.scale);
            self.totalAmount(dto.totalAmount);
        }
    }
    var ContactsModel = function (contacts) {
        var self = this;
        self.activityId = ko.observable();
        self.contacts = ko.observableArray(ko.utils.arrayMap(contacts, function (contact) {
            return {
                policyId: contact.policyId,
                policyName: contact.policyName,
                policyPriority: contact.policyPriority,
                policyType: contact.policyType,
                effectDays: contact.effectDays,
                remark: contact.remark,
                deliverys: ko.observableArray(contact.deliverys),
                receives: ko.observableArray(contact.receives),
                uses: ko.observableArray(contact.uses)
            };
        }));
        self.activityName = ko.observable();
        self.remark = ko.observable();
        self.activityType = ko.observable();
        self.channelId = ko.observable();
        self.putAmount = ko.observable();

        //投放金额
        self.putAmount.subscribe(function (amount) {
            var re = /^[0-9]*$/;
            if (!re.test(amount)) {
                base.error("请输入数字！");
                self.putAmount(0);
                return;
            }
            $.each(self.contacts(), function () {
                $.each(this.deliverys(), function () {
                    if (isFunction(this.scale)) {
                        if (this.scale()) {
                            var tta = amount * this.scale() / 100 / this.faceValue();
                            var re = /^[0-9]*$/;
                            if (!re.test(tta)) {
                                base.error("计算结果有小数位！");
                                this.totalAmount(0);
                                totalCalculation();
                            }
                            if (tta != this.totalAmount()) {
                                this.totalAmount(tta);
                                totalCalculation();
                            }
                        }
                    }
                    totalCalculation();
                })
            });
        })
        self.startTime = ko.observable();
        self.endTime = ko.observable();
        self.provinceId = ko.observable();
        // self.reckon =
        //添加策略
        self.addContact = function () {
            self.contacts.push({
                policyId: "",
                policyName: "",
                policyPriority: "",
                policyType: "",
                effectDays: "",
                deliverys: ko.observableArray(),
                receives: ko.observableArray(),
                uses: ko.observableArray()
            });
        };
        self.removeContact = function (contact) {
            self.contacts.remove(contact);
        };

        self.addDelivery = function (contact) {
            contact.deliverys.push(new Delivery());
        };
        self.addReceive = function (contact) {
            var pridto = {
                quotaField: "",
                operator: "",
                quotaTextValue: "",
                quotaProvinceField: "",
                quotaCollegeField: "",
                quotaCityField: "",
                quotaTimeValue: "",
                quotaBoolValue: "",
                quotaDivOrderTypeValue: ""
            };
            contact.receives.push(pridto);
            $(".selpolicy").each(function () {
                var that = this;
                $(this).change(function () {
                    selectfunction(that);
                })
            });
        };
        self.addUse = function (contact) {
            var pridto = {
                quotaField: "",
                operator: "",
                quotaTextValue: "",
                quotaProvinceField: "",
                quotaCollegeField: "",
                quotaCityField: "",
                quotaTimeValue: "",
                quotaBoolValue: "",
                quotaDivOrderTypeValue: ""
            };
            contact.uses.push(pridto);

            $(".selpolicy").each(function () {
                var that = this;
                $(this).change(function () {
                    selectfunction(that);
                })
            });

        };

        //删除投放规则中的一条记录
        self.removeDelivery = function (dto) {
            $.each(self.contacts(), function () {
                this.deliverys.remove(dto)
            })
        };
        //删除领取规则中的一条记录
        self.removeReceives = function (dto) {
            $.each(self.contacts(), function () {
                this.receives.remove(dto)
            })
        };
        //删除使用规则中的一条记录
        self.removeUse = function (dto) {
            $.each(self.contacts(), function () {
                this.uses.remove(dto)
            })
        };

        self.save = function (panel) {
            //多选时下拉控件配合ko使用获取不到值 先临时用$获取
            $("#activitySave", panel).attr("disabled", "disabled");//设置button不可用
            var t = setTimeout(show(panel), 3000);
            var postDate = JSON.stringify(ko.toJS(self), null, 2);
            $.ajax({
                type: "POST",
                url: "/wallet/activity/add",
                data: postDate,
                contentType: "application/json",
                success: function (data) {
                    if (data.success == 0) {
                        // window.location.href = "/wallet/gotoWalletAcctActivityManager";
                        base.colseTab("添加营销活动", "营销活动管理管理", refParentPage);
                    } else {
                        base.error(data.message);
                    }
                }
            });
        };
        self.update = function () {
            var postDate = JSON.stringify(ko.toJS(self), null, 2);
            $.ajax({
                type: "POST",
                url: "/wallet/activity/update",
                data: postDate,
                contentType: "application/json",
                success: function (data) {
                    if (data.success == 0) {
                        // window.location.href = "/wallet/gotoWalletAcctActivityManager";
                        base.colseTab("修改营销活动", "营销活动管理管理", refParentPage);
                    } else {
                        base.error(data.message);
                    }
                }
            });
        };

    };

    window.progress = function () {

        window.protime += 10;
        if (window.protime > 90) {
            window.clearInterval(window.intervalpro);//停止循环
        } else {
            $("#progressActivity").width(window.protime + "%");
        }
    }
    window.laterfunction = function () {
        var panel = $('#indextab').tabs('getSelected');
        $("#progressActivity").remove();
        base.success("生成成功");
        $(".confirm").removeAttr("disabled");
        $("#userTable", panel).bootstrapTable('refresh');
    }

    return {
        init: function (panel) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;

            ko.bindingHandlers.selProvince = {
                init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
                    var valuWe = valueAccessor()
                    $(element).select2({
                        data: window.provinceData
                    }).on("change", function () {
                        var value = valueAccessor(), allBindings = allBindingsAccessor();
//                    官方demo用的是valueAccessor 这里为什么不行？
                        if ($(element).val()) {
                            viewModel.quotaProvinceField = $(element).val().join(",");
                        }

                    });
                    ;
                },
                update: function (element, valueAccessor) {
                    $(element).val(valueAccessor().split(",")).trigger("change");
                }
            };
            ko.bindingHandlers.selCity = {
                init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
                    var valuWe = valueAccessor()
                    $(element).select2({
                        data: window.cityData
                    }).on("change", function () {
                        var value = valueAccessor(), allBindings = allBindingsAccessor();
                        if ($(element).val()) {
                            viewModel.quotaCityField = $(element).val().join(",");
                        }
                    });
                },
                update: function (element, valueAccessor) {
                    $(element).val(valueAccessor().split(",")).trigger("change");
                }
            };
            ko.bindingHandlers.selCollege = {
                init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
                    var valuWe = valueAccessor()
                    $(element).select2({
                        data: window.collegeData
                    }).on("change", function () {
                        var value = valueAccessor(), allBindings = allBindingsAccessor();
                        if ($(element).val()) {
                            viewModel.quotaCollegeField = $(element).val().join(",");
                        }
                    });
                },
                update: function (element, valueAccessor) {
                    $(element).val(valueAccessor().split(",")).trigger("change");
                }
            };
            ko.bindingHandlers.selOrderType = {
                init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
                    var valuWe = valueAccessor()
                    $(element).select2({
                        data: [{"id": "1", "text": "派件"}, {"id": "2", "text": "代取件"}, {"id": "3", "text": "寄件"}, {"id": "4", "text": "跑腿"}]
                    }).on("change", function () {
                        var value = valueAccessor(), allBindings = allBindingsAccessor();
                        if ($(element).val()) {
                            viewModel.quotaDivOrderTypeValue = $(element).val().join(",");
                        }
                    });
                },
                update: function (element, valueAccessor) {
                    $(element).val(valueAccessor().split(",")).trigger("change");
                }
            };


            $.ajax({
                type: "POST",
                url: "/manage/province/getprovinces",
                dataType: "json",
                success: function (data) {
                    $("#provinceId", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#provinceId', panel).select2("val", null);
                }
            });
            window.isEdit = false;
            //开始时间
            $('#starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2
            })

            //结束时间
            $('#endtimePicker', panel).datetimepicker(
                {
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                });

            base.datagrid({
                url: '/wallet/activity/getall',
                method: 'post',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            activityName: $("#activityName", panel).val(),
                            provinceId: $("#provinceId", panel).val(),
                            startStartTime: $('#submit_startdate', panel).val(),
                            endStartTime: $('#submit_enddate', panel).val() == "" ? "" : $('#submit_enddate', panel).val() + " 23:59:59"
                        });
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'activityName',
                        title: '活动名称',
                        sortable: true,
                        width: 200
                    }
                    ,
                    {
                        field: 'activityType',
                        title: '活动类型',
                        sortable: true,
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "代金券";
                                case 1:
                                    return "红包";
                            }
                        }
                    }
                    , {
                        field: 'channelId',
                        title: '渠道',
                        sortable: true,
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "指端";
                            }
                        }
                    },
                    {
                        field: 'provinceName',
                        title: '省份'
                    },
                    {
                        field: 'startTime',
                        title: '开始时间',
                        sortable: true,
                        formatter: function (value, row, index) {
                            return value.substr(0, 10);
                        }
                    }, {
                        field: 'endTime',
                        title: '结束时间',
                        formatter: function (value, row, index) {
                            return value.substr(0, 10);
                        },
                        sortable: true
                    },
                    {
                        field: 'state',
                        title: '状态',
                        formatter: function (value, row, index) {
                            switch (value) {
                                case 0:
                                    return "新创建";
                                case 1:
                                    return "已生成";
                                case 2:
                                    return "启用";
                                default :
                                    return "冻结";
                            }
                        },
                        sortable: true
                    },
                    {
                        field: 'putAmount',
                        title: '投入总金额',
                        formatter: function (value, row, index) {
                            return value / 100;
                        },
                        sortable: true
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        sortable: true
                    },
                    {
                        field: 'remark',
                        title: '备注'
                    },
                    {
                        field: 'activityId',
                        title: 'activityId',
                        visible: false
                    }
                ]
            }, '#userTable', panel);

            $("#btn_edit", panel).click(function () {

                var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
                if (arrselections.length > 1) {
                    sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                    return;
                }
                if (arrselections.length <= 0) {
                    sweetAlert("Oops...", "请选择有效数据!", "error");
                    return;
                }
                base.openTab("修改营销活动", "/wallet/gotoEditWalletAcctActivityManager?activityId=" + arrselections[0].activityId, self.editInit, self);
            });
            $("#btn_add", panel).click(function () {
                base.openTab("添加营销活动", "/wallet/gotoAddWalletAcctActivityManager", self.addInit, self);
            });
            $("#btn_delete", panel).click(function () {
                self.remove(panel);
            });
            $("#btn_query", panel).click(function () {
                $("#userTable", panel).bootstrapTable('refresh');
            });
            $("#btn_generate", panel).click(function () {
                self.generate(panel);
            });
            $("#btn_enable", panel).click(function () {
                self.benable(panel);
            });
            $("#btn_frozen", panel).click(function () {
                self.frozen(panel);
            });

            $("#clearSearch", panel).click(function () {
                base.reset(".main-box-header");
                $('#provinceId', panel).select2("val", null);
            });


        },
        addInit: function (panel) {
            panel = panel || $('#indextab').tabs('getSelected');
            initialData = [
                {
                    policyName: "", policyPriority: "", policyType: "", effectDays: "", remark: "", deliverys: [], receives: [], uses: []
                }
            ];

            ko.applyBindings(new ContactsModel(initialData), panel[0]);
            this.initData(panel);
        },
        editInit: function (activityId, panel) {


            var self = this;
            panel = panel || $('#indextab').tabs('getSelected');
            activityId = activityId || $("#activityId", panel).val();
            window.isEdit = true;
            $.ajax({
                type: "POST",
                dataType: "json",
                url: "/wallet/activity/getdto?activityId=" + activityId,
                success: function (data) {
                    var koModel = new ContactsModel(data.contacts);
                    koModel.activityName(data.activityName);
                    koModel.remark(data.remark);
                    koModel.activityType(data.activityType);
                    koModel.channelId(data.channelId);
                    koModel.putAmount(data.putAmount);
                    koModel.startTime(data.startTime.substr(0, 10));//时间做截取操作
                    koModel.endTime(data.endTime.substr(0, 10));
                    koModel.provinceId(data.provinceId);
                    koModel.activityId(activityId);
                    window.dosubscribe = false;
                    $.each(koModel.contacts(), function () {
                        var arrdelve = ko.observableArray();
                        $.each(this.deliverys(), function () {
                            arrdelve.push(new Delivery(this));
                        })
                        this.deliverys = arrdelve;
                    });
                    window.dosubscribe = true;
                    window.boolData = [{id: "0", text: "否"}, {id: "1", text: "是"}];
                    //只有新生成状态可以编辑
                    if (data.state == 1 || data.state == 2) {
                        $("#btnUpdate", panel).hide();
                    }

                    $.when(
                        $.ajax({
                            type: "POST",
                            url: "/manage/province/getprovinces",
                            dataType: "json",
                            success: function (data) {
                                window.provinceData = data;//放到全局变量
                                $("#provinceId", panel).select2({
                                    data: data,
                                    placeholder: '请选择',
                                    allowClear: true
                                });
                            }
                        }),
                        $.ajax({
                            type: "POST",
                            url: "/wallet/activity/getColegedto?activityId=" + activityId,
                            dataType: "json",
                            success: function (data) {
                                window.collegeData = data;
                            }
                        }),
                        $.ajax({
                            type: "POST",
                            url: "/manage/province/getcity",
                            dataType: "json",
                            success: function (data) {
                                window.cityData = data;
                            }
                        })
                    ).then(function (a, b) {  // 或者也可以使用 ".done" 等待异步都加载完成

                        ko.applyBindings(koModel, panel[0]);

                        $("#provinceId", panel).val(koModel.provinceId()).trigger("change");
                        //开始时间
                        $('#starttimePicker', panel).datetimepicker({
                            format: 'yyyy-mm-dd',
                            autoclose: true,
                            pickTime: false,
                            minView: 2
                        })
                        $('#starttimePicker', panel).click();
                        //结束时间
                        $('#endtimePicker', panel).datetimepicker(
                            {
                                format: 'yyyy-mm-dd',
                                autoclose: true,
                                pickTime: false,
                                minView: 2
                            });
                        $('#starttimePicker', panel).click();
                        $(".selpolicy").each(function () {
                            selectfunction(this);
                            var that = this;
                            $(this).change(function () {
                                selectfunction(that);
                            })
                        });

                        totalCalculation();
                        window.isEdit = false;

                        // 冻结状态下可以修改 领取规则 使用规则
                        if (data.state == 3) {
                            $("#btn_add", panel).hide();
                            $("#btnUpdate", panel).show();
                            $("#mainActivity", panel).find("select").attr("disabled", "disabled");
                            $("#mainActivity", panel).find("input").attr("readonly", "readonly");
                            $("#mainActivity", panel).find("textarea").attr("readonly", "readonly");
                            $(".editActivityEnable", panel).find("input").attr("readonly", "readonly");
                            $(".editDeliveryEnable", panel).find("input").attr("readonly", "readonly");
                            $(".editDeliveryEnable", panel).find("#btn_delivery_add").hide();
                            $(".editActivityEnable", panel).find("select").attr("disabled", "disabled");
                            $(".editDeliveryEnable", panel).find("select").attr("disabled", "disabled");
                        }
                    });
                }
            });
        },
        initData: function (panel) {
            window.boolData = [{id: "0", text: "否"}, {id: "1", text: "是"}];
            $.ajax({
                type: "POST",
                url: "/manage/province/getprovinces",
                dataType: "json",
                success: function (data) {
                    window.provinceData = data;//放到全局变量
                    $("#provinceId", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#provinceId', panel).select2("val", null);
                }
            });
            $.ajax({
                type: "POST",
                url: "/manage/college/getcollageforsel",
                dataType: "json",
                success: function (data) {
                    window.collegeData = data.data;
                }
            });
            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    window.cityData = data;
                }
            });
            //开始时间
            $('#starttimePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                pickTime: false,
                minView: 2
            })
            //结束时间
            $('#endtimePicker', panel).datetimepicker(
                {
                    format: 'yyyy-mm-dd',
                    autoclose: true,
                    pickTime: false,
                    minView: 2
                });
        },
        edit: function (panel) {
            var self = this;
            var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
            if (arrselections.length > 1) {
                sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
                return;
            }
            if (arrselections.length <= 0) {
                sweetAlert("Oops...", "请选择有效数据!", "error");
                return;
            }
            window.location.href = "/wallet/gotoEditWalletAcctActivityManager?activityId=" + arrselections[0].activityId;
        },
        remove: function (panel) {
            var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var activityId = arrselections[0].activityId;
            var state = arrselections[0].state;
            if (state == 2) {
                base.error("启用状态不能删除!");
                return;
            }
            base.cancel({title: "删除活动", text: "您确定要删除此活动吗？"}, function () {
                $.post("/wallet/activity/del", {"activityId": activityId}, function (data) {
                    if (data.success == 0) {
                        base.success("删除成功");
                        $("#userTable", panel).bootstrapTable('refresh');
                    } else {
                        base.error(data.message);
                    }
                });
            });
        },
        generate: function (panel) {
            var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var activityId = arrselections[0].activityId;
            var state = arrselections[0].state;
            if (state != 0) {
                base.error("只有新添加状态可以生成代金券!");
                return;
            }
            base.cancel({title: "生成代金券", text: "您确定要生成吗？"}, function () {
                $(".sa-button-container").after("<div class='progress-bar progress-bar-striped active' id='progressActivity' role='progressbar' aria-valuenow='10' aria-valuemin='0' aria-valuemax='100' style='height: 20px;margin-top: 10px;width: 10%;'><span class='sr-only' style=' height:20px;'>45% Complete</span></div>");
                window.protime = 10;
                window.intervalpro = setInterval("progress()", 1000);

                $.post("/wallet/activity/generate", {"activityId": activityId}, function (data) {
                    if (data.success == 0) {
                        window.clearInterval(window.intervalpro);//停止循环
                        $("#progressActivity").width("100%");

                        setTimeout('laterfunction()', 1000); // 一秒后执行
                    } else {
                        base.error(data.message);
                    }
                });
            });
        },
        benable: function (panel) {
            var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var activityId = arrselections[0].activityId;
            var state = arrselections[0].state;
            if (state == 1 || state == 3) {
                base.cancel({title: "状态修改", text: "您确定要启用此活动吗？"}, function () {
                    $.post("/wallet/activity/setstate", {"activityId": activityId, "state": 2}, function (data) {
                        if (data.success == 0) {
                            base.success("启用成功");
                            $("#userTable", panel).bootstrapTable('refresh');
                        } else {
                            base.error(data.message);
                        }
                    });
                });
            } else {
                base.error("只能启用冻结或生成状态下的活动!");
                return;
            }
        },
        frozen: function (panel) {
            var arrselections = $("#userTable", panel).bootstrapTable('getSelections');
            if (arrselections.length <= 0) {
                base.error("请选择有效数据!");
                return;
            }
            var activityId = arrselections[0].activityId;
            var state = arrselections[0].state;
            if (state == 1 || state == 2) {
                base.cancel({title: "状态修改", text: "您确定要冻结此活动吗？"}, function () {
                    $.post("/wallet/activity/setstate", {"activityId": activityId, "state": 3}, function (data) {
                        if (data.success == 0) {
                            base.success("冻结成功");
                            $("#userTable", panel).bootstrapTable('refresh');
                        } else {
                            base.error(data.message);
                        }
                    });
                });

            } else {
                base.error("只能冻结启用或生成状态下的活动!");
                return;
            }

        }
    };

});