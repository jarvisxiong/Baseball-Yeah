<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="editForm" method="post" class="form-horizontal"
      action="">
    <div class="modal fade" id="editModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document" style="width: 800px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group ">
                        <label for="detailtheme" class="col-md-3 control-label">主题：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="detailtheme" placeholder="主题" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group ">
                        <label for="claim" class="col-md-3 control-label">要求：</label>
                        <div class="col-lg-6">
                                            <textarea type="text" class="form-control" id="claim" placeholder="要求" maxlength="500"
                                                      data-bv-field="add_content" readonly="readonly"></textarea>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label for="rule" class="col-md-3 control-label">任务规则(评分标准)：</label>
                        <div class="col-lg-6">
                                            <textarea type="text" class="form-control" id="rule" placeholder="任务规则(评分标准)" maxlength="500" readonly="readonly"
                                                      data-bv-field="add_content"></textarea>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-md-3 control-label">任务等级:</label>
                        <div class="col-xs-6">
                            <select id='edittaskLevel' class="form-control" data-bind='value: activityType'>
                                <option value="1" selected="selected">绿色</option>
                                <option value="2">蓝色</option>
                                <option value="3">紫色</option>
                                <option value="4">金色</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-md-3 control-label">封面图片:</label>
                        <div class="col-lg-6  ">
                            <div style="margin-top: 8;color: #2980b9;"><i
                                    class="fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg1">点击查看 </strong></div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-md-3 control-label">图片:</label>
                        <div class="col-lg-6 ">
                            <div style="margin-top: 8;color: #2980b9;"><i
                                    class="fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg2">点击查看 </strong></div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label  class="col-md-3 control-label label-operation">是否需要图片:</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="editIsImg"  maxlength="20" readonly="readonly">
                        </div>
                    </div>

                    <div class="form-group ">
                        <label class="col-md-3 control-label label-operation">是否需要任务报告:</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="editIsRemark"  maxlength="20" readonly="readonly">
                        </div>
                    </div>

                    <div class="form-group ">
                        <div class="input-daterange">
                            <label class="col-lg-2 control-label label-operation">活动开始时间</label>
                            <div class="col-lg-7 div-operation">
                                <div class="input-group date" id="editstarttimePicker">
                                    <input type="text" class="form-control" data-bind='value: startTime' name="startTime"
                                           id="effectiveStartDate"></input><span class="input-group-addon"><span
                                        class="glyphicon-calendar glyphicon"></span> </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group ">
                        <div class="input-daterange">
                            <label class="col-lg-2 control-label label-operation">活动结束时间</label>
                            <div class="col-lg-7 div-operation ">
                                <div class="input-group date" id="editendtimePicker">
                                    <input type="text" class="form-control" id="effectiveEndDate" data-bind='value: endTime'
                                    ></input><span class="input-group-addon"><span
                                        class="glyphicon-calendar glyphicon"></span> </span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="form-group ">
                        <div class="input-daterange">
                            <label class="col-lg-2 control-label label-operation">任务完成时间</label>
                            <div class="col-lg-7 div-operation ">
                                <div class="input-group date" id="deadlinePicker">
                                    <input type="text" class="form-control" data-bind='value: startTime'
                                           id="deadlineTime"></input><span class="input-group-addon"><span
                                        class="glyphicon-calendar glyphicon"></span> </span>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label ">任务每单赏金</label>

                        <div class="col-md-2 ">
                            <input type="text" name="taskUnitPrice" class="form-control" maxlength="5"
                                   id="taskUnitPrice" placeholder="赏金">
                        </div>
                        <label class="col-md-1 control-label">数量</label>
                        <div class="col-md-2">
                            <input type="text" name="unitTotal" class="form-control" maxlength="6"
                                   id="unitTotal" placeholder="数量">
                        </div>
                        <div class="col-md-3" style=" padding-top: 5px;color: #3498db;margin-left: 7%;">
                            总共 <i id="moneyTotal"></i>（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label label-operation">每人接单上限</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="upperLimit" class="form-control" maxlength="6"
                                   id="upperLimit" placeholder="每人接单上限">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">每单多少件</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="taskUnitNum" class="form-control" maxlength="6"
                                   id="taskUnitNum" placeholder="每单多少件">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;display: flex;">
                            总共 <i id="numberTotal"></i>（件）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-2 control-label label-operation">学校</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='add_College' class="js-example-data-array" style="width: 100%"
                                    name="add_College"></select></div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
