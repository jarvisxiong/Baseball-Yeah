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
                            <input type="radio" name="f1" id="f1" value="option1" checked=""> <label for="f1"
                        >正确</label> <input type="radio" name="f1" id="fe1" value="option2"><label for="fe1"> 错误</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">身份证号：</label>
                        <div class="col-lg-5 div-operation">
                            <input type="text" class="form-control" name="aduitidentityNumber"
                                   id="aduitidentityNumber"/>
                        </div>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="f2" id="f2" value="option1" checked=""> <label for="f2"
                        >正确</label> <input type="radio" name="f2" id="fe2" value="option2"><label for="fe2"> 错误</label>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">手持身份证正面照片</label>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="f7" id="f7" value="option1" checked=""> <label for="f7"
                        >正确</label> <input type="radio" name="f7" id="fe7" value="option2"><label for="fe7"> 错误</label>
                        </div>
                        <div class="col-lg-3" style="margin-top: 8;color: #2980b9;"><i
                                class=" fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg1">点击查看 </strong></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">身份证正面照片</label>
                        <div class="radio" style="float: left;">
                            <input type="radio" name="f8" id="f8" value="option1" checked=""> <label for="f8"
                        >正确</label> <input type="radio" name="f8" id="fe8" value="option2"><label for="fe8"> 错误</label>
                        </div>

                        <div class="col-lg-3" style="margin-top: 8;color: #2980b9;"><i
                                class="fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="labelimg2">点击查看 </strong></div>
                        <%--<img src="" id="img2" style="padding-left: 30;width: 550;height: 350;display: none;">--%>
                        <%--<a href="http://zd-user-auth-test.oss-cn-hangzhou.aliyuncs.com/exp/600132/other.JPEG?Expires=1463032182&OSSAccessKeyId=2KKWBU9puaZzhtY3&Signature=9FgV8ue47kdieuylAIIfWHQXfWY%3D" class="photo-box image-link" style="background-image: url(http://zd-user-auth-test.oss-cn-hangzhou.aliyuncs.com/exp/600132/other.JPEG?Expires=1463032182&OSSAccessKeyId=2KKWBU9puaZzhtY3&Signature=9FgV8ue47kdieuylAIIfWHQXfWY%3D);">低调低调</a>--%>
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
<input type='hidden' id='userType'>