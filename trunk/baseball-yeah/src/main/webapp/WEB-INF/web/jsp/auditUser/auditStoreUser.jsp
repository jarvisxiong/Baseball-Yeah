<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .img-dialog .modal-dialog {
        width: 800px;
    }
</style>
<form id="auditForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="auditModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel"><label style="width: 200px" id="lableUserName"></label>
                        <label
                                id="lableVerifyStatus"></label></h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">姓名:</label>

                        <div class="col-lg-5 div-operation">
                            <input type="text" class="form-control" name="aduitrealName"
                                   id="aduitrealName"/>
                        </div>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref1" id="auditStoref1" value="option1" checked=""> <label for="auditStoref1"
                        >正确</label> <input type="radio" name="auditStoref1" id="auditStorefe1" value="option2"><label for="auditStorefe1"> 错误</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">身份证号：</label>
                        <div class="col-lg-5 div-operation">
                            <input type="text" class="form-control" name="aduitidentityNumber"
                                   id="aduitidentityNumber"/>
                        </div>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref2" id="auditStoref2" value="option1" checked=""> <label for="auditStoref2"
                        >正确</label> <input type="radio" name="auditStoref2" id="auditStorefe2" value="option2"><label for="auditStorefe2"> 错误</label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">手持身份证正面照片</label>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref7" id="auditStoref7" value="option1" checked=""> <label for="auditStoref7"
                        >正确</label> <input type="radio" name="auditStoref7" id="auditStorefe7" value="option2"><label for="auditStorefe7"> 错误</label>
                        </div>
                        <div class="col-lg-3" style="margin-top: 8;color: #2980b9;"><i
                                class=" fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg1">点击查看 </strong></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">身份证正面照片</label>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref8" id="auditStoref8" value="option1" checked=""> <label for="auditStoref8"
                        >正确</label> <input type="radio" name="auditStoref8" id="auditStorefe8" value="option2"><label for="auditStorefe8"> 错误</label>
                        </div>

                        <div class="col-lg-3" style="margin-top: 8;color: #2980b9;"><i
                                class="fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg2">点击查看 </strong></div>
                        <%--<img src="" id="img2" style="padding-left: 30;width: 550;height: 350;display: none;">--%>
                        <%--<a href="http://zd-user-auth-test.oss-cn-hangzhou.aliyuncs.com/exp/600132/other.JPEG?Expires=1463032182&OSSAccessKeyId=2KKWBU9puaZzhtY3&Signature=9FgV8ue47kdieuylAIIfWHQXfWY%3D" class="photo-box image-link" style="background-image: url(http://zd-user-auth-test.oss-cn-hangzhou.aliyuncs.com/exp/600132/other.JPEG?Expires=1463032182&OSSAccessKeyId=2KKWBU9puaZzhtY3&Signature=9FgV8ue47kdieuylAIIfWHQXfWY%3D);">低调低调</a>--%>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">快递公司：</label>
                        <div class="col-lg-5 div-operation">
                            <input type="text" class="form-control" name="aduitCompanyName"
                                   id="aduitCompanyName"/>
                        </div>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref3" id="auditStoref3" value="option1" checked=""> <label for="auditStoref3"
                        >正确</label> <input type="radio" name="auditStoref3" id="auditStorefe3" value="option2"><label for="auditStorefe3"> 错误</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">站点编号：</label>
                        <div class="col-lg-5 div-operation">
                            <input type="text" class="form-control" name="aduitStoreCode"
                                   id="aduitStoreCode"/>
                        </div>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref4" id="auditStoref4" value="option1" checked=""> <label for="auditStoref4"
                        >正确</label> <input type="radio" name="auditStoref4" id="auditStorefe4" value="option2"><label for="auditStorefe4"> 错误</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">站点名称：</label>
                        <div class="col-lg-5 div-operation">
                            <input type="text" class="form-control" name="aduitStoreName"
                                   id="aduitStoreName"/>
                        </div>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref5" id="auditStoref5" value="option1" checked=""> <label for="auditStoref5"
                        >正确</label> <input type="radio" name="auditStoref5" id="auditStorefe5" value="option2"><label for="auditStorefe5"> 错误</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">站点地址：</label>
                        <div class="col-lg-5 div-operation">
                            <input type="text" class="form-control" name="aduitLocation"
                                   id="aduitLocation"/>
                        </div>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref6" id="auditStoref6" value="option1" checked=""> <label for="auditStoref6"
                        >正确</label> <input type="radio" name="auditStoref6" id="auditStorefe6" value="option2"><label for="auditStorefe6"> 错误</label>
                        </div>
                    </div>
                    <!-- <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">许可证正面照片：</label>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref9" id="auditStoref9" value="option1" checked=""> <label for="auditStoref9"
                        >正确</label> <input type="radio" name="auditStoref9" id="auditStorefe9" value="option2"><label for="auditStorefe9"> 错误</label>
                        </div>
                        <div class="col-lg-3" style="margin-top: 8;color: #2980b9;"><i
                                class="fa fa-file-image-o fa-fw fa-lg"></i><strong id="labelimg3" style="cursor: pointer;">点击查看 </strong></div>
                    </div> -->

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">校区信息：</label>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="auditStoref10" id="auditStoref10" value="option1" checked=""> <label for="auditStoref10"
                        >正确</label> <input type="radio" name="auditStoref10" id="auditStorefe10" value="option2"><label for="auditStorefe10">
                            错误</label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation"></label>
                        <div class="col-lg-8">
                            <table id="auditcollage">
                                <thead>
                                <tr>
                                    <th data-field="fullName">高校校区</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">备注：</label>
                        <div class="col-lg-8">
                            <textarea class="form-control" id="verifyRemark" rows="5"></textarea>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" id="auditPost" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
<input type='hidden' id='userId'>