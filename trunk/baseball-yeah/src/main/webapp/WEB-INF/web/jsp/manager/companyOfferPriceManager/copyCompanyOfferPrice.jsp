<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="copyForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="copyModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">复制</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">报价名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="copy_offerName" maxlength="40"
                                   id="copy_offerName" placeholder="报价名称"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">首重费用</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="copy_initialWeight" class="form-control" maxlength="6"
                                   id="copy_initialWeight" placeholder="首重费用">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;">
                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">续重费用</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="copy_additionalWeight" class="form-control" maxlength="6"
                                   id="copy_additionalWeight" placeholder="续重费用">
                        </div>
                        <div class="col-lg-3" style=" padding-top: 5px;color: #3498db;">
                            <i class="fa fa-info-circle fa-fw fa-lg"></i>单位（元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">寄件人区域</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='copy_sendAreaId' class="js-example-data-array" style="width: 100%"
                                    name="copy_sendAreaId"></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">收件人区域</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='copy_dispAreaId' class="js-example-data-array" style="width: 100%"
                                    name="copy_dispAreaId"></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">快递品牌</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='copy_EcList' class="js-example-data-array" style="width: 100%"
                                    name="copy_EcList" multiple></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">学校</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='copy_Collage' class="js-example-data-array" style="width: 100%"
                                    name="copy_Collage" multiple></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">排序</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="copy_sortNo" class="form-control" maxlength="6"
                                   id="copy_sortNo" placeholder="排序">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">是否启用</label>
                        <div class="radio">
                            <input type="radio" name="copyEnabled" id="copyYEnabled" value="option1"
                                   checked=""> <label for="copyYEnabled">启用</label> <input
                                type="radio" name="copyEnabled" id="copyNEnabled" value="option2">
                            <label for="copyNEnabled"> 不启用</label>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default "  id="btnClose" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
<input type='hidden' id='copy_offerId'>