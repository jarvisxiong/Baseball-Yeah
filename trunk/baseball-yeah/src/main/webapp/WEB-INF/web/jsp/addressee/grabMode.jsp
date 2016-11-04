<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="grabForm" method="post" class="form-horizontal"
      action="">
    <div class="modal fade" id="grabModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">账号激活</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">手机号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="grab_phone" class="form-control"
                                   id="grab_phone" placeholder="手机号"  readonly="readonly">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">真实姓名</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="grab_realName" class="form-control"
                                   id="grab_realName" placeholder="真实姓名"  readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">账户状态</label>
                        <div class="radio">
                            <input type="radio" name="grabSupervisors_addressee" id="grabY_addressee" value="option1"
                                   checked=""> <label for="grabY_addressee">激活</label> <input
                                type="radio" name="grabSupervisors_addressee" id="grabN_addressee" value="option2">
                            <label for="grabN_addressee"> 封存</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
                    </button>
                    <button type="button" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
<input type='hidden' id='userId'>
