<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
.infographic-box .user-defined-value {
    font-size: 1.6em;
    font-weight: 600;
    margin-top: -5px;
    display: block;
    text-align: right;
}
</style>

<input type="hidden" class="inmodule" value="orderAnalysisModules">
<div class="main-box clearfix">
    <div class="main-box-body clearfix">
        <div class="row">
            <div class="col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="#"></a></li>
                </ol>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-sm-4 col-xs-12">
                <div class="main-box infographic-box" style="height:118px;">
                	<i class="fa fa-shopping-cart emerald-bg"></i>
                    <span class="user-defined-value">订单量</span>
                    <span class="headline"><span id="orderNum">0</span>单</span>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4 col-xs-12">
                <div class="main-box infographic-box" style="height:118px;">
                	<i class="fa fa-plus red-bg"></i>
                    <span class="user-defined-value">订单增长</span>
                    <span class="headline">环比增长：<span id="orderNumQoq">0.00%</span></span>
                    <span class="headline">同比增长：<span id="orderNumYoy">0.00%</span></span>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4 col-xs-12">
                <div class="main-box infographic-box" style="height:118px;">
                	<i class="fa fa-user yellow-bg"></i>
                    <span class="user-defined-value">工作小派（运力）</span>
                    <span class="headline"><span id="winnerNum">0</span>人（<span id="shippingAbilityNum">0</span>）</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-sm-4 col-xs-12">
                <div class="main-box infographic-box" style="height:118px;">
                	<i class="fa fa-bullseye green-bg"></i>
                    <span class="user-defined-value">订单来源</span>
                    <span class="headline">微信服务号：<span id="orderSourceWeixin">0.00%</span></span>
                    <span class="headline">短信：<span id="orderSourceSms">0.00%</span></span>
                    <span class="headline">其他：<span id="orderSourceOther">0.00%</span></span>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4 col-xs-12">
                <div class="main-box infographic-box" style="height:118px;">
                	<i class="fa fa-book purple-bg"></i>
                    <span class="user-defined-value">订单类型</span>
                    <span class="headline">代取件：<span id="orderTypeTake">0.00%</span></span>
                    <span class="headline">代寄件：<span id="orderTypeSend">0.00%</span></span>
                </div>
            </div>
            <div class="col-lg-4 col-sm-4 col-xs-12">
                <div class="main-box infographic-box" style="height:118px;">
                	<i class="fa fa-clock-o gray-bg"></i>
                    <span class="user-defined-value">接单时效（±）</span>
                    <span class="headline"><span id="takeOrderMinsAvg">0</span>分钟（<span id="takeOrderMins">+0</span>分钟）</span>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
            	<div id="chart1" style="height:500px;"></div>
            </div>
        </div>

        <div class="row" style="margin-top: 20px;">
            <div class="col-lg-6">
                <div id="chart2" style="height:500px;"></div>
            </div>
            <div class="col-lg-6">
                <div id="chart3" style="height:500px;"></div>
            </div>
        </div>

		<div class="row" style="margin-top: 20px;">
            <div class="col-lg-6">
                <div id="chart4" style="height:500px;"></div>
            </div>
            <div class="col-lg-6">
                <div id="chart5" style="height:500px;"></div>
            </div>
        </div>
    </div>
</div>