<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="smallPieDetailModal" role="dialog" aria-labelledby="myModalLabel" style="z-index:1042;" >
        <div class="modal-dialog" role="document" style="width: 900px;">
            <div class="modal-content" id="smallPieDetailContent"  >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel"><span style="font-weight: 800;font-size:18px;">小派详情</span></h4>
                </div>
                <div class="modal-body" style="margin-left: 20px;">
                    <div style="width:100%;margin:0 auto;">
                        <div style="float:left; width:70%;">
                            <div class="form-group">
                                <label class="col-lg-1 control-label" style="width: 15%;"><span style="font-weight: 600;font-size:13px;">手机号：</span></label>
                                <div class="col-lg-3"  style="width: 30%;padding-top: 13px;"><span id="detailPhone" ></span>
                                    <%--<input type="text" class="form-control" id="detailPhone" readonly>--%>
                                </div>
                                <label class="col-lg-1 control-label" style="width: 15%;"><span style="font-weight: 600;font-size:13px;">账户状态：</span></label>
                                <div class="col-lg-3"  style="width: 30%;padding-top: 13px;"><span id="detailUserState" ></span>
                                    <%--<input type="text" class="form-control" id="detailUserState" readonly>--%>
                                </div>
                                <label class="col-lg-1 control-label"></label><div class="col-lg-3"></div>
                            </div>
<%--                            <div class="form-group">
                                <label class="col-lg-1 control-label" style="width: 15%;">来源：</label>
                                <div class="col-lg-3"  style="width: 30%;padding-top: 13px;"><span id="detailSource" ></span>
                                    &lt;%&ndash;<input type="text" class="form-control" id="detailSource" readonly>&ndash;%&gt;
                                </div>

                                <label class="col-lg-1 control-label"></label><div class="col-lg-3"></div>
                            </div>--%>
                            <div class="form-group">
                                <label class="col-lg-1 control-label" style="width: 15%;"><span style="font-weight: 600;font-size:13px;">真实姓名：</span></label>
                                <div class="col-lg-3"  style="width: 30%;padding-top: 13px;"><span id="detailUserName" ></span>
                                    <%--<input type="text" class="form-control" id="detailUserName" readonly>--%>
                                </div>
                                <label class="col-lg-1 control-label" style="width: 15%;"><span style="font-weight: 600;font-size:13px;">性别：</span></label>
                                <div class="col-lg-2"  style="width: 30%;padding-top: 13px;"><span id="detailSex" ></span>
                                    <%--<input type="text" class="form-control" id="detailSex" readonly>--%>
                                </div>
                                <label class="col-lg-1 control-label"></label><div class="col-lg-3"></div>
                            </div>
                            <div  class="form-group">
                                <label class="col-lg-1 control-label" style="width: 15%;"><span style="font-weight: 600;font-size:13px;">身份证号：</span></label>
                                <div class="col-lg-2"  style="width: 30%;padding-top: 13px;"><span id="detailCardNo" ></span>
                                    <%--<input type="text" class="form-control" id="detailCardNo" readonly>--%>
                                </div>
                                <label class="col-lg-1 control-label"></label><div class="col-lg-2"></div>
                                <label class="col-lg-1 control-label"></label><div class="col-lg-3"></div>
                            </div>
                        </div>
                        <div style="float:right;width:30%;">
                            <div class="form-group">
                                <div class="col-lg-1 div-operation" id="detailAccountImg"></div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group" style="float:left; width:100%;">
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">账户余额：</span></label>
                        <div class="col-lg-2"  style="width: 22%;padding-top: 13px;"><span id="detailWalletBalance" ></span>
                            <%--<input type="text" class="form-control" id="detailWalletBalance" readonly>--%>
                        </div>
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">订单量：</span></label>
                        <div class="col-lg-2"  style="width: 22%;padding-top: 13px;"><span id="detailOrderNum" ></span>
                            <%--<input type="text" class="form-control" id="detailOrderNum" readonly>--%>
                        </div>
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">好评率：</span></label>
                        <div class="col-lg-2"  style="width: 22%;padding-top: 13px;"><span id="detailFavourable" ></span>
                            <%--<input type="text" class="form-control" id="detailFavourable" readonly>--%>
                        </div>
                    </div>

                    <div class="form-group" style="float:left; width:100%;">
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">城市：</span></label>
                        <div class="col-lg-3"  style="width: 22%;padding-top: 13px;"><span id="detailCityName" ></span>
                            <%--<input type="text" class="form-control" id="detailCityName" readonly>--%>
                        </div>
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">校区：</span></label>
                        <div class="col-lg-3"  style="width: 22%;padding-top: 13px;"><span id="detailCollegeName" ></span>
                            <%--<input type="text" class="form-control" id="detailCollegeName" readonly>--%>
                        </div>
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">宿舍：</span></label>
                        <div class="col-lg-3"  style="width: 22%;padding-top: 13px;"><span id="detailDorm" ></span>
                            <%--<input type="text" class="form-control" id="detailDorm" readonly>--%>
                        </div>
                    </div>

                    <div class="form-group" style="float:left; width:100%;">
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">审核时间：</span></label>
                        <div class="col-lg-4"  style="width: 22%;padding-top: 13px;"><span id="detailAuditTime" ></span>
                            <%--<input type="text" class="form-control" id="detailAuditTime" readonly>--%>
                        </div>
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">审核状态：</span></label>
                        <div class="col-lg-4"  style="width: 22%;padding-top: 13px;"><span id="detailAuditState" ></span>
                            <%--<input type="text" class="form-control" id="detailAuditState" readonly>--%>
                        </div>
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">标签：</span></label>
                        <div class="col-lg-4"  style="width: 22%;padding-top: 13px;"><span id="detailAuditRemark" ></span>
                            <%--<input type="text" class="form-control" id="detailAuditRemark" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group" style="float:left; width:100%;">
                        <label class="col-lg-1 control-label" style="width: 11%;"><span style="font-weight: 600;font-size:13px;">详细地址：</span></label>
                        <div class="col-lg-6" style="width: 53%;padding-top: 13px;"><span id="detailPath" ></span>
                            <%--<input type="text" class="form-control" id="detailPath" readonly>--%>
                        </div>
                        <label class="col-lg-1 control-label"></label><div class="col-lg-3"></div>
                        <label class="col-lg-1 control-label"></label><div class="col-lg-3"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label"><span style="font-weight: 600;font-size:13px;">手持身份证正面照：</span></label>
                        <div class="col-lg-7" style="padding-top: 13px;color: #2980b9;" id="divlabel1">
                            <i class="fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg1">点击查看 </strong>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label"><span style="font-weight: 600;font-size:13px;">身份证正面照：</span></label>
                        <div class="col-lg-7" style="padding-top: 13px;color: #2980b9;" id="divlabel2">
                            <i class="fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg2">点击查看 </strong>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
