<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<div class="modal fade" id="editModal" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">

                <div class="col-xs-12 ">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">明细</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div>
                                    <table class="table table-user-information">
                                        <tbody>
                                        <tr>
                                            <td>任务单号:</td>
                                            <td id="deatiltaskNo"></td>
                                            <td>发布时间:</td>
                                            <td id="publishTime">

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>级别</td>
                                            <td id="level">

                                            </td>
                                            <td>要求完成时间</td>
                                            <td id="deadline"></td>
                                        </tr>
                                        <tr>
                                        <tr>
                                            <td>
                                                要求:
                                            </td>
                                            <td id="clame" colspan="3">

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>发布对象:</td>
                                            <td id="college"></td>
                                            <td>小派姓名:</td>
                                            <td id="deatilwinner"></td>
                                        </tr>
                                        <tr>
                                            <td>手机号:</td>
                                            <td id="deatilphone"></td>
                                            <td>接单时间:</td>
                                            <td id="acceptTime"></td>
                                        </tr>
                                        <td>提交审核时间:</td>
                                        <td id="audiTime"></td>
                                        <td colspan="2"></td>
                                        </tr>
                                        <tr>
                                            <td>
                                                完成附件:
                                            </td>
                                            <td colspan="3">
                                                <div id="hide_pic" class="col-lg-3" style="margin-top: 8px;color: #2980b9;"><i
                                                        class="fa fa-file-image-o fa-fw fa-lg"></i><strong
                                                        style="cursor: pointer;" id="imageShow">点击查看 </strong>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>备注:</td>
                                            <td id="remark" colspan="3">

                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>


                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label class="control-label col-xs-4">评价打分:
                                        (<a tabindex="0" role="button" id="rule">评分规则</a>)
                                    </label>
                                    <div id="star" class="col-xs-8">

                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                               <textarea id="txt_appraise" class="form-control" placeholder="填写评价内容"
                                                         rows="5">

                                               </textarea>
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


<jsp:include page="browsePictures.jsp"></jsp:include>

