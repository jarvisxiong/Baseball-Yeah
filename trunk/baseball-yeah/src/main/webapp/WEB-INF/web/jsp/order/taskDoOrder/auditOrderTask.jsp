<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style>
    #rule {
        color: #3498db;
        cursor: pointer;
    }
</style>

<input type="hidden" class="inmodule" value="doOrderTaskDetailModules">
<input type="hidden" id="inputOerder" value="${orderId}">
<div class="row">
    <div class="row">
        <div class="col-lg-12">
            <div class="main-box clearfix">
                <header class="main-box-header clearfix">

                </header>
                <div class="main-box-body clearfix">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">审核</h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <%--<div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> </div>--%>
                                    <div class=" col-md-9 col-lg-9 ">
                                        <table class="table table-user-information">
                                            <tbody>
                                            <tr>
                                                <td>任务单号:</td>
                                                <td id="taskNo"></td>
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
                                                <td id="winner"></td>
                                            </tr>
                                            <tr>
                                                <td>手机号:</td>
                                                <td id="phone"></td>
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
                                <form>
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
                                    <div style="text-align: center;">
                                        <a href="#" id="btn_save" class="btn btn-primary">保存记录</a>
                                        <a href="#" id="btn_audit" class="btn btn-primary">提交审核</a>
                                    </div>
                                </form>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="browsePictures.jsp"></jsp:include>

