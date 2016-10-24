<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="a_addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="a_addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">增加区域</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">区域名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_a_areaName" class="form-control"
                                   id="add_a_areaName" placeholder="区域名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">商务负责人</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_a_businessPrincipal" class="form-control"
                                   id="add_a_businessPrincipal" placeholder="商务负责人">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">联系电话</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_a_contactPhone" class="form-control"
                                   id="add_a_contactPhone" placeholder="联系电话">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">序号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_a_sortNo" class="form-control"
                                   id="add_a_sortNo" placeholder="序号">
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
<form id="p_addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="p_addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">增加省份</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">省份名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_p_provinceName" class="form-control"
                                   id="add_p_provinceName" placeholder="省份名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">归属区域</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_p_areaId" class="js-example-data-array"
                                    style="width: 100%" name="add_p_areaId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">序号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_p_sortNo" class="form-control"
                                   id="add_p_sortNo" placeholder="序号">
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
<form id="c_addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="c_addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">增加城市</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">城市名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_c_cityName" class="form-control"
                                   id="add_c_cityName" placeholder="城市名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">归属省份</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_c_provinceId" class="js-example-data-array"
                                    style="width: 100%" name="add_c_provinceId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">电话区号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_c_telZoneCode" class="form-control"
                                   id="add_c_telZoneCode" placeholder="电话区号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">邮编</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_c_postCode" class="form-control"
                                   id="add_c_postCode" placeholder="邮编">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">序号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_c_sortNo" class="form-control"
                                   id="add_c_sortNo" placeholder="序号">
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
<form id="co_addForm" method="post" class="form-horizontal" action="">
    <div class="modal fade" id="co_addModal" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">增加区县</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">区县名称</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_co_countyName" class="form-control"
                                   id="add_co_countyName" placeholder="区县名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">归属省份</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_co_provinceId" class="js-example-data-array"
                                    style="width: 100%" name="add_co_provinceId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">归属城市</label>
                        <div class="col-lg-7 div-operation ">
                            <select id="add_co_cityId" class="js-example-data-array"
                                    style="width: 100%" name="add_co_cityId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">邮编</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_co_postCode" class="form-control"
                                   id="add_co_postCode" placeholder="邮编">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label label-operation">序号</label>
                        <div class="col-lg-7 div-operation ">
                            <input type="text" name="add_co_sortNo" class="form-control"
                                   id="add_co_sortNo" placeholder="序号">
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