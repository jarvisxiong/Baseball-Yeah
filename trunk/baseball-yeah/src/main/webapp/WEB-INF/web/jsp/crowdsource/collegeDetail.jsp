<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="collegePieDetailModal" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel"><span style="font-weight: 800;font-size:18px;">学校详情</span></h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">学校编号：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schCollegeCode"></span>
                            <%--<input type="text" class="form-control" id="schCollegeCode" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">学校名称：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schFullName"></span>
                            <%--<input type="text" class="form-control" id="schFullName" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">办学性质：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schNature"></span>
                            <%--<input type="text" class="form-control" id="schNature" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">所属类型：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schCollegeType"></span>
                            <%--<input type="text" class="form-control" id="schCollegeType" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">所属省份：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schProvinceName"></span>
                            <%--<input type="text" class="form-control" id="schProvinceName" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">所属市区：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schCityName"></span>
                            <%--<input type="text" class="form-control" id="schCityName" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">所属县区：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schCountyName"></span>
                            <%--<input type="text" class="form-control" id="schCountyName" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">校园CEO：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schCeoName"></span>
                            <%--<input type="text" class="form-control" id="schCeoName" readonly>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"><span style="font-weight: 600;font-size:13px;">备注：</span></label>
                        <div class="col-lg-7 div-operation" style="padding-top: 13px;"><span id="schCollegeRemark"></span>
                            <%--<input type="text" class="form-control" id="schCollegeRemark" readonly>--%>
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
