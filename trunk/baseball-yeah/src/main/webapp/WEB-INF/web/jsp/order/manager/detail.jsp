<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal fade" id="orderManagerDetail" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title" id="myModalLabel" align="center"><b>订单详情</b></h3>
            </div>
            <div class="modal-body">

                <div class="col-xs-12 ">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">用户信息</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div>
                                    <table class="table table-user-information">
                                        <tbody>
                                        <tr>
                                            <td id="mobileText">收件人/寄件人手机号：</td>
                                            <td id="mobile"></td>
                                            <td>学校：</td>
                                            <td id="collegeName">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td id="nameText">收件人/寄件人姓名：</td>
                                            <td id="consignee">
                                            </td>
                                            <td id="sendAddrText"> 送货地址：</td>
                                            <td id="address1"></td>
                                        </tr>
                                        <tr>
                                        <tr>
                                            <td>性别：</td>
                                            <td id="sex"> </td>
                                            <td id="city_addrText">取件地址：</td>
                                            <td id="address2"> </td>
                                        </tr>
                                        <tr>
                                            <td id="cityNameText">城市：</td>
                                            <td id="cityName"></td>
                                            <td>快递站点名称：</td>
                                            <td id="traderAddress"></td>
                                        </tr>
                                        </tbody>
                                    </table>


                                </div>
                            </div>
                        </div>
                        <div class="panel-heading">
                            <h3 class="panel-title">订单信息</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div>
                                    <table class="table table-user-information">
                                        <tbody>
                                        <tr>
                                            <td>订单号：</td>
                                            <td id="mgrOrderId"></td>
                                            <td>实际支付金额：</td>
                                            <td id="finalMoney"></td>
                                        </tr>
                                        <tr>
                                            <td>运单号：</td>
                                            <td id="waybillNo">
                                            </td>
                                            <td> 优惠方式：</td>
                                            <td id="perferWay"></td>
                                        </tr>
                                        <tr>
                                        <tr>
                                            <td>当前状态：</td>
                                            <td id="state"> </td>
                                            <td>付款方式：</td>
                                            <td id="payType"> </td>
                                        </tr>
                                        <tr>
                                            <td>打赏金额：</td>
                                            <td id="rewardMoney"></td>
                                            <td>支付类型：</td>
                                            <td id="payMode"></td>
                                        </tr>
                                        <tr>
                                            <td>优惠金额：</td>
                                            <td id="perferMoney"></td>
                                            <td>预约时间：</td>
                                            <td id="orderTime"></td>
                                        </tr>
                                        <tr id="pickUpTR">
                                            <td>取货码：</td>
                                            <td id="pickUpCode"></td>
                                        </tr>
                                        <tr>
                                            <td>配送要求：</td>
                                            <td id="remark" colspan="3"></td>
                                        </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div>
                                    <table class="table table-user-information">
                                        <tbody id="orderMgrFlowList">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
