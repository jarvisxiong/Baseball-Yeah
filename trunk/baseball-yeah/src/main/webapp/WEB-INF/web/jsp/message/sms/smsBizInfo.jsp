<%--
  Created by IntelliJ IDEA.
  User: wny
  Date: 2016-07-06
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="bizInfoModal" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">相关业务信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="form-group  col-xs-6">
                        <label class="col-xs-2 control-label label-operation">运单号</label>
                        <div class="col-xs-5 div-operation ">
                            <input type="text" class="form-control" name="biz_waybillNumber"
                                   id="biz_waybillNumber" placeholder="运单号" readonly style='width: 200px'/>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">

                        <label class="col-xs-2 control-label label-operation">快递公司</label>
                        <div class="col-xs-5 div-operation ">
                            <input type="text" class="form-control" name="biz_expressCompanyText"
                                   id="biz_expressCompanyText" placeholder="快递公司" readonly style='width: 200px'/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group  col-xs-6">
                        <label class="col-xs-2 control-label label-operation">扫描时间</label>
                        <div class="col-xs-5 div-operation ">
                            <input type="text" class="form-control" name="biz_scanTime"
                                   id="biz_scanTime" placeholder="扫描时间" readonly style='width: 200px'/>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-xs-2 control-label label-operation">上传时间</label>
                        <div class="col-xs-5 div-operation ">
                            <input type="text" class="form-control" name="biz_addTime"
                                   id="biz_addTime" placeholder="上传时间" readonly style='width: 200px'/>
                        </div>
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
