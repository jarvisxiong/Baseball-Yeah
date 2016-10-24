<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="editForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="editModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">监控项名称：</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="editmonitorName"
                                   id="editmonitorName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">监控SQL：</label>
                        <div class="col-lg-7 div-operation ">
                            <textarea rows="10" class="form-control" name="editmonitorSql" id="editmonitorSql"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">阀值：</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" class="form-control" name="editthreshold"
                                   id="editthreshold"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">开始监控时间：</label>
                        <div class="col-lg-7 div-operation ">
                            <div class="input-daterange">
                                <div class="input-group date" id="editstarttimePicker">
                                    <input type="text" class="form-control" name="editstartTime" id="editstartTime"/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon-calendar glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">结束监控时间：</label>
                        <div class="col-lg-7 div-operation ">
                            <div class="input-daterange">
                                <div class="input-group date" id="editendtimePicker">
                                    <input type="text" class="form-control" name="editendTime" id="editendTime"/>
                                    <span class="input-group-addon">
                                        <span class="glyphicon-calendar glyphicon"></span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">执行间隔：</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="editrunInterval"
                                   id="editrunInterval"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-1"></div>
                        <div class="col-lg-3">
                            <button id="btn_contact_edit" type="button" class="btn btn-default">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增联系人
                            </button>
                        </div>
                    </div>

                    <div id="contactEditDiv" class="form-group">

                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
                <input type="hidden" id="monitorId">
            </div>
        </div>
    </div>
</form>