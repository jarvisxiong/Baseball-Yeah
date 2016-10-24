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
                        <label class="col-lg-3 control-label label-operation">商户名称</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" class="form-control" name="edit_store"
                                   id="edit_store"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">商户手机</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" class="form-control" name="edit_phone"
                                   id="edit_phone"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">详细地址</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_address"
                                   id="edit_address"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">学校</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="edit_college" name="edit_college" multiple>
                                </select>
                            </div>
                        </div>
                    </div>

                    <%--<div class="form-group">
                        <label class="col-lg-3 control-label label-operation">省份</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="edit_province" name="edit_province">
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">城市</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="edit_city" name="edit_city">
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">区</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="edit_county" name="edit_county">
                                </select>
                            </div>
                        </div>
                    </div>--%>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
                <input type="hidden" id="edit_pickupaddressid">
            </div>
        </div>
    </div>
</form>