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
                    <h4 class="modal-title" id="myModalLabel">新增</h4>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">账号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="addLoginName"
                                   id="addLoginName" placeholder="账号"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">姓名</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" class="form-control" name="addUserName"
                                   id="addUserName" placeholder="姓名"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">工号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="addUserCode" class="form-control"
                                   id="addUserCode" placeholder="工号"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">部门</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='addDeptId' class="js-example-data-array"
                                    style="width: 283px"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">密码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="password" name="addPassword" class="form-control"
                                   id="addPassword" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">再次输入密码</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="password" name="addRePassword" class="form-control"
                                   id="addRePassword" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">职务</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="addDutyId" name="dutyId"
                                    class="js-example-data-array" style="width: 100%"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">角色</label>
                        <div class="col-lg-7 div-operation ">
                            <select id='addRole' class="js-example-data-array"
                                    style="width: 100%" multiple="multiple">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">性别</label>
                        <div class="radio">
                            <input type="radio" name="Gender" id="addMan" value="option1"
                                   checked=""> <label for="addMan">男</label> <input
                                type="radio" name="Gender" id="addWoman" value="option2">
                            <label for="addWoman"> 女</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">联系电话</label>
                        <div class="radio">
                            <div class="col-lg-7 div-operation ">
                                <input type="text" class="form-control" name="addPhone"
                                       id="addPhone" placeholder="联系电话"/>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">地址</label>
                        <div class="radio">
                            <div class="col-lg-7 div-operation ">
                                <input type="text" class="form-control" name="addAddress"
                                       id="addAddress" placeholder="地址"/>
                            </div>
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
