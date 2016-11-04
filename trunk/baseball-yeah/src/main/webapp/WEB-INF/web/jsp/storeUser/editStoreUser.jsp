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
                        <label class="col-lg-3 control-label label-operation">快递站点</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='edit_store' class="js-example-data-array" name="edit_store"
                                    style="width: 100%"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">用户昵称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="edit_nickname" maxlength="40"
                                   id="edit_nickname" placeholder="用户昵称"/>
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
                        <label class="col-lg-3 control-label label-operation">手机号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_phone" class="form-control"
                                   id="edit_phone" placeholder="手机号" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">真实姓名</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_realName" class="form-control"
                                   id="edit_realName" placeholder="真实姓名" readonly="readonly"maxlength="20">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">身份证</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="edit_idNo" class="form-control"
                                   id="edit_idNo" placeholder="身份证" readonly="readonly" maxlength="20">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">角色</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text"  class="form-control"
                                   id="edit_beSupervisor"  readonly="readonly"maxlength="20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">性别</label>
                        <div class="radio">
                            <input type="radio" name="editRadios" id="editMan" value="option1"
                                   checked=""> <label for="editMan">男</label> <input
                                type="radio" name="editRadios" id="editWoman" value="option2">
                            <label for="editWoman"> 女</label>
                        </div>
                    </div>


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
<input type='hidden' id='userId'>