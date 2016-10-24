<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="addForm" method="post" class="form-horizontal"
      action="">
    <div class="modal fade" id="addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">新增</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">快递站点</label>
                        <div class="col-lg-7 div-operation ">
                            <select
                                    id='add_store' class="js-example-data-array" style="width: 100%"
                                    name="add_store"></select></div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">用户昵称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="add_nickname" maxlength="40"
                                   id="add_nickname" placeholder="用户昵称"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation ">手机号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_phone" class="form-control"
                                   id="add_phone" placeholder="手机号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">密码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="password" name="add_pwd" class="form-control"
                                   id="add_pwd" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">性别</label>
                        <div class="radio">
                            <input type="radio" name="addRadios" id="addMan" value="option1"
                                   checked=""> <label for="addMan">男</label> <input
                                type="radio" name="addRadios" id="addWoman" value="option2">
                            <label for="addWoman"> 女</label>
                        </div>

                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">负责人</label>
                        <div class="radio">
                            <input type="radio" name="addSupervisors" id="addYSupervisor" value="option1"
                                   checked=""> <label for="addYSupervisor">是</label> <input
                                type="radio" name="addSupervisors" id="addNSupervisor" value="option2">
                            <label for="addNSupervisor"> 否</label>
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
