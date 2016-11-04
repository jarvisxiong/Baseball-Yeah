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
                    <h4 class="modal-title" id="myModalLabel">编辑</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">登录账号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_loginAccount" class="form-control"
                                   id="edit_loginAccount" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">用户名</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_userName" class="form-control"
                                   id="edit_userName" placeholder="用户名" readonly="readonly"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">性别</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_sex" class="form-control"
                                   id="edit_sex" placeholder="手机号" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">城市</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_cityName" class="form-control"
                                   id="edit_cityName" placeholder="城市" readonly="readonly" maxlength="20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">学校</label>
                        <div class="col-lg-7 div-operation ">
                            <select type="text" name="edit_college" class="form-control"
                                    id="edit_college" style="width: 100%"></select>
                        </div>
                    </div>


                </div>
                <div class="modal-footer">
                    <a class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </a>
                    <a id="editUpdate" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </a>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="edit_userId">
</form>