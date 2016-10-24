<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">增加</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">商户名称</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" class="form-control" name="add_store"
                                   id="add_store" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">商户手机</label>
                        <div class="col-lg-7 div-operation">
                            <input type="text" class="form-control" name="add_phone"
                                   id="add_phone" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">详细地址</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_address"
                                   id="add_address" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">学校</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="add_college" name="add_college" multiple>
                                </select>
                            </div>
                        </div>
                    </div>

                   <%-- <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">省份</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="add_province" name="add_province">
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">城市</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="add_city" name="add_city">
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">区</label>
                        <div class="col-lg-7 div-operation">
                            <div class="form-group-select2">
                                <select style="width: 283px" id="add_county" name="add_county">
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
            </div>
        </div>
    </div>
</form>
