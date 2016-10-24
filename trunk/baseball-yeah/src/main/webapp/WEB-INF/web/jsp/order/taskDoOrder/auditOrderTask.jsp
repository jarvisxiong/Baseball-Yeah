<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>做单任务审核</title>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/moment.min.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
            rel="stylesheet">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/raty/jquery.raty.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
</head>
<style>
    #rule {
        color: #3498db;
        cursor: pointer;
    }
</style>
<script>
    $(function () {

        require(['doOrderTaskDetailModules'], function (detail) {
            var orderId=$("#inputOerder").val();
            detail.init(orderId);
        });
    });
</script>
<body>
<input type="hidden" id="inputOerder" value="${orderId}">
<div id="page-wrapper" class="container">
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a
                                            href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                    <li ><span>任务订单管理</span></li>
                                    <li ><a
                                            href="<%=request.getContextPath()%>/order/gotoDoOrderTaskManager">做单任务管理</a>
                                    </li>
                                    <li class="active">
                                            做单任务审核
                                    </li>
                                </ol>
                                <h1>做单任务审核</h1>
                            </div>
                        </div>
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
</div>
<jsp:include page="browsePictures.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
</html>
