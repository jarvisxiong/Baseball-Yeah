<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派</title>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <link href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
          rel="stylesheet">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
    <link href="<%=request.getContextPath()%>/plugs/compiled/layout.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugs/knockout/knockout-3.4.0.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
    <script type="text/javascript">
        $(function () {
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
            ko.bindingHandlers.selOrderType = {
                init: function (element, valueAccessor, allBindingsAccessor, viewModel) {
                    var valuWe = valueAccessor()

                    $(element).select2({
                        data: [{"id":"1","text":"派件"},{"id":"2","text":"代取件"},{"id":"3","text":"寄件"}]
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
            require(['acctActivityModules'], function (acct) {
                acct.addInit();
            });
        });
    </script>

</head>
<body style="background-color: #e7ebee;">
<div id="page-wrapper" class="container">
    <div class="row">
        <form action="">
            <div class="row">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                    <li class="active"><span>钱包管理</span></li>
                                </ol>
                                <h1>营销活动管理</h1>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="main-box clearfix">
                                    <div class="form-group col-lg-12 group-activity"></div>
                                    <div class="form-group col-lg-12 group-activity">
                                        <label for="activityName" class="col-lg-2 control-label add-activity">活动名称：</label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="activityName" placeholder="活动名称" data-bind='value: activityName'>
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-12 group-activity">
                                        <label for="activityType" class="col-lg-2 control-label add-activity">活动类型:</label>
                                        <div class="col-xs-6">
                                            <select id='activityType' class="form-control" data-bind='value: activityType'>
                                                <option value="0">代金券</option>
                                                <option value="1">红包</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-12 group-activity">
                                        <label for="channelId"
                                               class="col-lg-2 control-label add-activity">渠道:</label>
                                        <div class="col-xs-6">
                                            <select id='channelId' class="form-control" data-bind='value: channelId'>
                                                <option value="0">指端</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-12 group-activity">
                                        <label for="provinceId" class="col-lg-2 control-label add-activity">省份:</label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="provinceId" placeholder="省份" data-bind='value: provinceId'>
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-12 group-activity">
                                        <label for="putAmount" class="col-lg-2 control-label add-activity">总金额：</label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="putAmount" placeholder="总金额" data-bind='value: putAmount'>
                                        </div>
                                        <div class="col-lg-2" style=" padding-top: 5px;color: #3498db;">
                                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）；
                                            当前金额：
                                            <strong id="reckon"></strong>
                                        </div>
                                    </div>

                                    <div class="form-group col-lg-12 group-activity">
                                        <label for="putAmount" class="col-lg-2 control-label add-activity">备注：</label>
                                        <div class="col-lg-7 div-operation ">
                                            <textarea type="text" class="form-control" id="remark" placeholder="备注" data-bv-field="add_content" data-bind='value: remark'></textarea>

                                        </div>
                                    </div>

                                    <div class="form-group col-lg-12 group-activity">
                                        <div class="input-daterange">
                                            <label class="col-lg-2 control-label add-activity" for="startTime">活动时间:</label>
                                            <div class="col-xs-3">
                                                <div class="input-group date" id="starttimePicker">
                                                    <input type="text" class="form-control" data-bind='value: startTime'
                                                           id="startTime"></input><span class="input-group-addon"><span
                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                </div>
                                            </div>
                                            <div class="col-xs-3">
                                                <div class="input-group date" id="endtimePicker">
                                                    <input type="text" class="form-control" id="endTime" data-bind='value: endTime'
                                                    ></input><span class="input-group-addon"><span
                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-lg-12 group-activity">
                                        <div class="col-lg-offset-2 col-lg-10">
                                            <button type="button" data-bind='click: addContact' class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增策略
                                            </button>
                                            <button type="button" data-bind="click: save" id="activitySave" class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>保存
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div data-bind="foreach: contacts">

                                <div class="col-lg-12">
                                    <div class="main-box clearfix">
                                        <header class="main-box-header clearfix">

                                        </header>
                                        <div class="main-box-body clearfix">
                                            <div class="tabs-wrapper">
                                                <ul class="nav nav-tabs">
                                                    <li class="active"><a data-bind="attr: { href: '#tab-home' +  $index()}" data-toggle="tab">策略详细</a>
                                                    </li>
                                                    <li class=""><a data-bind="attr: { href: '#tab-delivery' +  $index()}"
                                                                    data-toggle="tab">投放规则</a></li>
                                                    <li class=""><a data-bind="attr: { href: '#tab-receive' +  $index()}"
                                                                    data-toggle="tab">领取规则</a></li>
                                                    <li class=""><a data-bind="attr: { href: '#tab-use' +  $index()}" data-toggle="tab">使用规则</a>
                                                    </li>
                                                    <a href="#" data-bind="click: $root.removeContact" class="table-link"
                                                       style="float: right; margin-top: 10px;margin-right: 10px;"><span class="fa-stack"><i
                                                            class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane fade active in" data-bind="attr: { id: 'tab-home' +  $index()}">

                                                        <div class="form-group col-lg-12 group-activity">
                                                            <label for="policyName" class="col-lg-2 control-label add-activity">策略名称：</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" class="form-control" data-bind='value: policyName' id="policyName"
                                                                       placeholder="策略名称">
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-lg-12 group-activity">
                                                            <label for="policyPriority" class="col-lg-2 control-label add-activity">优先级：</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" class="form-control" data-bind='value: policyPriority' id="policyPriority"
                                                                       placeholder="优先级" maxlength="1">
                                                            </div>
                                                            <div class="col-lg-2" style=" padding-top: 5px;color: #3498db;">
                                                                <i class="fa fa-info-circle fa-fw fa-lg"></i>从1开始 越小优先级越高
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-lg-12 group-activity">

                                                            <label for="policyType" class="col-lg-2 control-label add-activity">策略类型:</label>
                                                            <div class="col-xs-6">
                                                                <select id='policyType' class="form-control" data-bind='value: policyType'>
                                                                    <option value="1">代金券</option>
                                                                    <option value="2">红包</option>
                                                                    <option value="3">补贴</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group col-lg-12 group-activity" style="display: none;">
                                                            <label for="effectDays" class="col-lg-2 control-label add-activity">有效时长：</label>
                                                            <div class="col-lg-6">
                                                                <input type="text" class="form-control" data-bind='value: effectDays' id="effectDays"
                                                                       placeholder="有效时长" maxlength="3">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" data-bind="attr: { id: 'tab-delivery' +  $index()}">
                                                        <button id="btn_delivery_add" type="button" data-bind='click: $root.addDelivery'
                                                                class="btn btn-default btn_delivery_add">
                                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                        </button>
                                                        <div data-bind="foreach: deliverys">
                                                            <div class="row">
                                                                <div class="form-group">
                                                                    <label class="col-lg-1 control-label add-activity">金额：</label>
                                                                    <div class="col-lg-2">
                                                                        <select class="form-control faceValue" data-bind='value: faceValue'>
                                                                            <option value="1" selected="selected">1</option>
                                                                            <option value="2">2</option>
                                                                            <option value="3">3</option>
                                                                            <option value="4">4</option>
                                                                            <option value="5">5</option>
                                                                        </select>
                                                                    </div>
                                                                    <label class="col-lg-1 control-label add-activity">比例：</label>
                                                                    <div class="col-lg-2">
                                                                        <input type="text" data-bind='value: scale' class="form-control scale" placeholder="比例">
                                                                    </div>
                                                                    <label class="col-lg-1 control-label add-activity">数量：</label>
                                                                    <div class="col-lg-2">
                                                                        <input type="text" data-bind='value: totalAmount' class="form-control totalAmount"
                                                                               placeholder="数量">
                                                                    </div>
                                                                    <a href="#" data-bind='click: $root.removeDelivery' class="table-link"><span
                                                                            class="fa-stack"><i class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" data-bind="attr: { id: 'tab-receive' +  $index()}">
                                                        <button id="btn_use_add" type="button" data-bind='click: $root.addReceive'
                                                                class="btn btn-default">
                                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                        </button>
                                                        <div data-bind="foreach: receives">
                                                            <div class="row">
                                                                <div class="form-group col-lg-12 group-activity">
                                                                    <div class="col-lg-1"></div>
                                                                    <div class="col-lg-2">
                                                                        <select class="col-lg-4 form-control selpolicy" data-bind='value: quotaField'>
                                                                            <option value="ORDER_COUNT" selected="selected">订单量</option>
                                                                            <option value="CAN_USE">是否可用</option>
                                                                            <option value="IS_NEW">新用户</option>
                                                                            <option value="CITY_ID">城市</option>
                                                                            <option value="PROVINCE_ID">省份编号</option>
                                                                            <option value="COLLEGE_ID">学校编号</option>
                                                                            <option value="END_TIME">结束时间</option>
                                                                            <option value="START_TIME">开始时间</option>
                                                                            <option value="LEAD_TIMES">领取次数</option>
                                                                            <option value="ORDER_TYPE">订单类型</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-2">
                                                                        <select class="col-lg-4 form-control selop" data-bind='value: operator'>
                                                                            <option value="=" selected="selected">等于</option>
                                                                            <option value=">">大于</option>
                                                                            <option value="<">小于</option>
                                                                            <option value=">=">大于等于</option>
                                                                            <option value="<=">小于等于</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <div class="quotaDivTextValue">
                                                                            <input type="text" class="form-control quotaValue"
                                                                                   data-bind='value: quotaTextValue'>
                                                                        </div>
                                                                        <div class="quotaDivProvinceValue" style="display: none">
                                                                            <select style="width: 100%" class='js-example-data-array quotaProvinceField'
                                                                                    data-bind='selProvince: quotaProvinceField' multiple="multiple"></select>
                                                                        </div>
                                                                        <div class="quotaDivCollegeValue" style="display: none"><select
                                                                                style="width: 100%" class='js-example-data-array quotaCollegeField'
                                                                                data-bind='selCollege: quotaCollegeField' multiple="multiple"></select></div>
                                                                        <div class="quotaDivCityValue" style="display: none"><select
                                                                                style="width: 100%" class='js-example-data-array quotaCityField'
                                                                                data-bind='selCity : quotaCityField' multiple="multiple"></select></div>
                                                                        <div class="quotaDivTimeValue" style="display: none">
                                                                            <div class="input-group date ">
                                                                                <input type="text" class="form-control quotaTimeValue"
                                                                                       data-bind='value: quotaTimeValue'></input>
                                                                                <span class="input-group-addon"><span
                                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                                            </div>
                                                                        </div>
                                                                        <div class="quotaDivBoolValue" style="display: none">
                                                                            <select class="form-control quotaBoolValue" data-bind='value: quotaBoolValue'>
                                                                                <option value="0" selected="selected"> 否</option>
                                                                                <option value="1">是</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="quotaDivOrderTypeValue" style="display: none">
                                                                            <select style="width: 100%" class='js-example-data-array quotaDivOrderTypeValue'
                                                                                    data-bind='selOrderType : quotaDivOrderTypeValue' multiple="multiple"></select>

                                                                        </div>
                                                                    </div>
                                                                    <div class="col-lg-1">
                                                                        <a href="#" data-bind='click: $root.removeReceives' class="table-link"><span
                                                                                class="fa-stack"><i
                                                                                class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>
                                                                    </div>
                                                                </div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" data-bind="attr: { id: 'tab-use' +  $index() }">
                                                        <button id="btn_receive_add" type="button" data-bind='click: $root.addUse'
                                                                class="btn btn-default">
                                                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                        </button>
                                                        <div data-bind="foreach:uses ">
                                                            <div class="row">
                                                                <div class="form-group col-lg-12 group-activity">
                                                                    <div class="col-lg-1"></div>
                                                                    <div class="col-lg-2">
                                                                        <select class="col-lg-4 form-control selpolicy" data-bind='value: quotaField'>
                                                                            <option value="ORDER_COUNT" selected="selected">订单量</option>
                                                                            <option value="CAN_USE">是否可用</option>
                                                                            <option value="IS_NEW">新用户</option>
                                                                            <option value="CITY_ID">城市</option>
                                                                            <option value="PROVINCE_ID">省份编号</option>
                                                                            <option value="COLLEGE_ID">学校编号</option>
                                                                            <option value="END_TIME">结束时间</option>
                                                                            <option value="START_TIME">开始时间</option>
                                                                            <option value="ORDER_TYPE">订单类型</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-2">
                                                                        <select class="col-lg-4 form-control selop" data-bind='value: operator'>
                                                                            <option value="=" selected="selected">等于</option>
                                                                            <option value=">">大于</option>
                                                                            <option value="<">小于</option>
                                                                            <option value=">=">大于等于</option>
                                                                            <option value="<=">小于等于</option>
                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <div class="quotaDivTextValue">
                                                                            <input type="text" class="form-control quotaValue"
                                                                                   data-bind='value: quotaTextValue'>
                                                                        </div>
                                                                        <div class="quotaDivProvinceValue" style="display: none">
                                                                            <select style="width: 100%" class='js-example-data-array quotaProvinceField'
                                                                                    data-bind='selProvince : quotaProvinceField' multiple="multiple"></select>
                                                                        </div>
                                                                        <div class="quotaDivCollegeValue" style="display: none">
                                                                            <select style="width: 100%"
                                                                                    class='js-example-data-array quotaCollegeField'
                                                                                    data-bind='selCollege: quotaCollegeField' multiple="multiple"></select>
                                                                        </div>
                                                                        <div class="quotaDivCityValue" style="display: none"><select
                                                                                style="width: 100%" class='js-example-data-array quotaCityField'
                                                                                data-bind='selCity : quotaCityField' multiple="multiple"></select></div>
                                                                        <div class="quotaDivTimeValue" style="display: none">
                                                                            <div class="input-group date ">
                                                                                <input type="text" class="form-control quotaTimeValue"
                                                                                       data-bind='value: quotaTimeValue'></input>
                                                                                <span class="input-group-addon"><span
                                                                                        class="glyphicon-calendar glyphicon"></span> </span>
                                                                            </div>
                                                                        </div>
                                                                        <div class="quotaDivBoolValue" style="display: none">
                                                                            <select class="form-control quotaBoolValue" data-bind='value: quotaBoolValue'>
                                                                                <option value="0" selected="selected"> 否</option>
                                                                                <option value="1">是</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="quotaDivOrderTypeValue" style="display: none">
                                                                            <select style="width: 100%" class='js-example-data-array quotaDivOrderTypeValue'
                                                                                    data-bind='selOrderType : quotaDivOrderTypeValue' multiple="multiple"></select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-lg-1">
                                                                        <a href="#" data-bind='click: $root.removeUse' class="table-link"><span
                                                                                class="fa-stack"><i
                                                                                class="fa fa-square fa-stack-2x"></i>
                                                                        <i class="fa fa-times fa-stack-1x fa-inverse"></i></span></a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>


                        </div>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

