<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派快递站点</title>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/axp/storeUser.js"></script>

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/axp/base.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/JSON-js-master/json_parse.js"></script>

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/remodal/remodal.min.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/remadal/remodal-default-theme.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/remadal/remodal.css">
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
</head>
<body>
<div id="page-wrapper" class="container">
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                        <div class="row">
                            <div class="col-lg-12">
                                <ol class="breadcrumb">
                                    <li><a
                                            href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                    <li class="active"><span>基础设置</span></li>
                                </ol>
                                <h1>快递站点维护</h1>
                            </div>
                        </div>
                        <div class="panel-group" style="margin-bottom: 0px;"
                             id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a-clear-search" id="clearSearch"> <span
                                                class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                        </a> <a class="accordion-toggle a-search" data-toggle="collapse"
                                                data-parent="#accordion" href="#collapseOne"> 查询条件 </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <form id="formSearch" class="form-horizontal" role="form">
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="phone" class="control-label col-xs-2">手机号：</label>
                                                    <div class="col-xs-10" style="margin-top: 8px;">
                                                        <input type="text" id="phone" style="width: 240px"
                                                               class="form-control" placeholder="手机号"/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <label for="storeName" class="control-label col-xs-2">站点名称：</label>
                                                    <div class="col-xs-10" style="margin-top: 8px;">
                                                        <input type="text" id="storeName" style="width: 240px"
                                                               class="form-control" placeholder="站点名称"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="expressId"
                                                           class="control-label col-xs-2">默认快递公司:</label>
                                                    <div class="col-xs-10" style="margin-top: 8px;">
                                                        <select id='expressId' style="width: 240px"
                                                                class="js-example-data-array form-control">
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div id="toolbar" class="btn-group">
                        <shiro:hasPermission name="14_QUERY">
                            <button id="query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="14_ADD">
                            <button data-toggle="modal" id="add" class="btn btn-default">
                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="14_EDIT">
                            <button data-toggle="modal" id="update" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="14_DELETE">
                            <button id="remove" class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                            </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="14_PACKETPATTEN">
                            <button id="check" class="btn btn-default">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>寄/派件模式
                            </button>
                        </shiro:hasPermission>
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="expressTable" data-show-export="true">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title">快递站点操作</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="formBody">
                    <div class="form-group">
                        <label for="storeCode">快递站点编码</label> <input type="text"
                                                                     class="form-control" id="txt_storeCode"
                                                                     name="storeCode"
                                                                     placeholder="快递站点编码">
                    </div>
                    <div class="form-group">
                        <label for="storeName">快递站点名称</label> <input type="text"
                                                                     class="form-control" id="txt_storeName"
                                                                     name="storeName"
                                                                     placeholder="快递站点名称">
                    </div>
                    <div class="form-group">
                        <label>状态</label> <select class="form-control" id="sel_status"
                                                  name="status">
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                    </div>
                    <div class="form-group">
                        <label>是否开启派件模式</label>
                        <div class="radio">
                            <input type="radio" name="packetModeMgr" id="pmode" value="1"
                                   checked> <label for="pmode">开启</label> <input
                                type="radio" name="packetModeMgr" id="pmode2" value="0"><label
                                for="pmode2"> 关闭</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>是否开启寄件模式</label>
                        <div class="radio">
                            <input type="radio" name="packetModeSend" id="pmode3" value="1"
                                   checked> <label for="pmode3">开启</label> <input
                                type="radio" name="packetModeSend" id="pmode4" value="0"><label
                                for="pmode4"> 关闭</label>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label>是否开启打烊模式</label>
                        <div class="radio">
                            <input type="radio" name="closeMode" id="pmode5" value="1"
                                  > <label for="pmode5">开启</label> <input
                                type="radio" name="closeMode" id="pmode6" value="0" checked><label
                                for="pmode6"> 关闭</label>
                        </div>
                    </div>                    
                    <div class="form-group">
                        <label for="phone">手机号</label> <input type="text"
                                                              class="form-control" id="txt_phone" name="phone"
                                                              placeholder="手机号">
                    </div>
                    <div class="form-group">
                        <label for="supervisorName">负责人</label> <input type="text"
                                                                       class="form-control" id="txt_supervisorName"
                                                                       name="supervisorName" placeholder="负责人">
                    </div>
                    <div class="form-group form-group-select2">
                        <label>学校名称</label> <select style="width: 566px" id="sel1Multi"
                                                    name="colList" multiple>
                    </select>
                    </div>
                    <div class="form-group form-group-select2">
                        <label>快递公司名称(默认)</label> <select style="width: 566px"
                                                          id="sel3Multi" name="defaultECId">
                    </select>
                    </div>
                    <div class="form-group form-group-select2">
                        <label>快递公司名称</label> <select style="width: 566px" id="sel2Multi"
                                                      name="ecList" multiple>
                    </select>
                    </div>

                    <div class="form-group">
                        <label for="location">联系地址</label>
							<textarea class="form-control" id="txt_location" name="location"
                                      rows="3"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" id="btnReset"
                                onclick="resetFormData('#formBody');" class="btn btn-default">重置
                        </button>
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                        <button type="submit" id="btnSave" class="btn btn-primary">保存</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>


<div class="modal fade" id="paket_mode" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title">寄/派件模式</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>派件模式:</label>
                    <div class="radio">
                        <input type="radio" name="mgrMode" id="p1" value="1" checked>
                        <label for="p1">开启</label> <input type="radio"
                                                          name="mgrMode" id="p2" value="0"><label for="p2">
                        关闭</label>
                    </div>
                </div>
                <div class="form-group">
                    <label>寄件模式:</label>
                    <div class="radio">
                        <input type="radio" name="sendMode" id="p3" value="1" checked>
                        <label for="p3">开启</label> <input type="radio"
                                                          name="sendMode" id="p4" value="0"><label for="p4">
                        关闭</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="btn_mode" class="btn btn-primary">确定</button>
                </div>

            </div>

        </div>
    </div>
</div>


<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>
</body>
<script type="text/javascript">
    var selectedRow = null;
    var isupdate = false;
    //将表单数据转化为json
    $(function () {
        $.ajaxSetup({
            cache: false
        });
        init();
        $("#check")
                .click(
                        function () {
                            if (!dataObject.bootstrapTable("getSelections")
                                    || dataObject
                                            .bootstrapTable("getSelections").length <= 0) {
                                sweetAlert("", "请选择要操作的项", "warning");
                            } else {
                                $("#paket_mode").modal('show');
                            }
                        });
        $("#btn_mode").click(function () {
            packet_mode();
        });
        $("#update").click(
                function () {
                    isupdate = true;
                    var editRow = dataObject.bootstrapTable("getSelections");
                    if (!editRow || editRow.length <= 0) {
                        sweetAlert("", "请选择要操作的项", "warning");
                    } else if (editRow.length > 1) {
                        sweetAlert("", "只能选择操作一项", "warning");
                    } else {
                        $("#editModal").modal('show');
                        bindFormData(editRow[0]);
                        //绑定radio
                        editRow[0].packetModeMgr == 1 ? $("#pmode").prop(
                                "checked", true) : $("#pmode2").prop("checked",
                                true);
                        editRow[0].packetModeSend == 1 ? $("#pmode3").prop(
                                "checked", true) : $("#pmode4").prop("checked",
                                true);                        
                        editRow[0].closeMode == 1 ? $("#pmode5").prop(
                                "checked", true) : $("#pmode6").prop("checked",
                                true);
                        //$("input[name='packetModeMgr'][value="+editRow[0].packetModeMgr+"]").prop("checked",true);
                        //绑定下拉框
                        if (editRow[0].ecGcode) {
                            var ecArray = editRow[0].ecGcode.split(",");

                            control.val(ecArray).trigger("change");

                        } else {
                            control.val("").trigger("change");
                        }
                        if (editRow[0].colCode) {
                            var colArray = editRow[0].colCode.split(",");

                            control2.val(colArray).trigger("change");

                        } else {
                            control2.val("").trigger("change");
                        }
                        if (editRow[0].defaultECId) {
                            control3.val(editRow[0].defaultECId).trigger(
                                    "change");
                        } else {
                            control3.val("").trigger("change");
                        }
                    }
                });
        $("#add").click(function () {
            isupdate = false;
            resetFormData("formBody");
            $("#editModal").modal('show');

        });
        $("#query").click(function () {
            $("#expressTable").bootstrapTable('refresh', {
                silent: true
            });
        });
        $("#remove")
                .click(
                        function () {
                            if (!dataObject.bootstrapTable("getSelections")
                                    || dataObject
                                            .bootstrapTable("getSelections").length <= 0) {
                                sweetAlert("", "请选择要操作的项", "warning");
                            } else {
                                sweetAlert({
                                    title: "提示信息",
                                    text: "确认删除该项信息?",
                                    type: "warning",
                                    showCancelButton: true,
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "确定",
                                    cancelButtonText: "取消",
                                    closeOnConfirm: false
                                }, function () {
                                    delStore();
                                });

                            }
                        });
    });
    /*
     表单验证
     */
    function validate() {
        $("#formBody").bootstrapValidator({
            message: '验证未通过',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                storeName: {
                    validators: {
                        notEmpty: {
                            message: '快递站点名称不能为空'
                        }
                    }
                },
                storeCode: {
                    validators: {
                        notEmpty: {
                            message: '快递站点编号不能为空'
                        }
                    }
                },
                /*  supervisorName: {
                 message: '负责人名称验证失败',
                 validators: {
                 notEmpty: {
                 message: '负责人不能为空'
                 },
                 stringLength: {
                 min: 1,
                 max: 20,
                 message: '负责人名称过长'
                 }
                 }
                 }, */
                phone: {
                    message: '手机号验证失败',
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^1[3|4|5|7|8]\d{9}$/,
                            message: '手机号格式不正确'
                        },
                    }
                }
            }
        }).on('success.form.bv', function (e) {
            e.preventDefault();
            edit();
        });
    }
    /*
     编辑操作
     */
    function edit() {
        var selectedRow = dataObject.bootstrapTable("getSelections");
        var jsonArray = $("#formBody").serializeArray();
        var jsonData = convertToJsonStr(jsonArray);
        if (!isupdate) {
            $.ajax({
                url: '/store/exp/addexpinfo',
                dataType: 'JSON',
                type: 'post',
                data: {
                    data: JSON.stringify(jsonData)
                },
                success: function (data) {
                    if (data.success == 0) {
                        sweetAlert("", "新增成功", "success");
                        dataObject.bootstrapTable('refresh', {
                            silent: true
                        });
                        $("#editModal").modal('hide');
                        $("#btnSave").attr("disabled", false);
                        resetFormData("#formBody");
                        $("#btnSave").attr("disabled", false);
                    } else {
                        sweetAlert("", data.message, "info");
                        $("#btnSave").attr("disabled", false);
                    }
                },
                error: function (XMLHttpRequest) {
                    sweetAlert("", XMLHttpRequest.responseText, "info");
                    $("#btnSave").attr("disabled", false);
                }
            });
        } else {
            jsonData["storeId"] = dataObject.bootstrapTable("getSelections")[0].storeId;
            $.ajax({
                url: '/store/exp/updateexpinfo',
                dataType: 'JSON',
                type: 'post',
                data: {
                    data: JSON.stringify(jsonData)
                },
                success: function (data) {
                    if (data.success == 0) {

                        sweetAlert("", "修改成功", "success");
                        dataObject.bootstrapTable('refresh', {
                            silent: true
                        });
                        $("#editModal").modal('hide');
                        $("#btnSave").attr("disabled", false);
                    } else {
                        sweetAlert("", data.message, "info");
                    }
                },
                error: function (XMLHttpRequest) {
                    sweetAlert("", XMLHttpRequest.responseText, "info");
                    $("#btnSave").attr("disabled", false);
                }
            });
        } 
    }
    function init() {
        // 模态框隐藏事件
        $('#editModal').on('hidden.bs.modal', function (e) {
            $("#formBody").data('bootstrapValidator').resetForm(true);
        });
        validate();
        loadSelect();

        dataObject = $('#expressTable')
                .bootstrapTable(
                        {
                            height: '1200',
                            striped: true,
                            sortName: 'storeName',
                            pagination: true,
                            pageSize: 20,
                            pageList: [20, 30, 40, 60],
                            /* search : true, */
                            showRefresh: true,
                            clickToSelect: true,
                            exportDataType: 'all',
                            contentType: 'application/x-www-form-urlencoded',
                            exportTypes: ['excel'],
                            url: '/store/exp/postExpInfoList',
                            method: 'post',
                            toolbar: '#toolbar',
                            queryParams: function (params) {
                                return $.extend(params,
                                        {
                                            phone: $("#phone").val(),
                                            storeName: $('#storeName').val(),
                                            expressCompanyId: $('#expressId').val()
                                        });
                            },
                            /* onLoadSuccess : function(data) {
                             var tableHeight = $('#expressTable').find(
                             "thead").height()
                             + $('#expressTable').find("tbody")
                             .height()
                             + 3
                             + $('#expressTable').parent().parent()
                             .parent().parent().find(
                             ".clearfix").height();
                             if (this.search) {
                             tableHeight += ($('#expressTable').parent()
                             .parent().parent().parent().find(
                             ".pull-left").height() + 20);
                             }
                             $('#expressTable').bootstrapTable('resetView',
                             {
                             "height" : tableHeight
                             });
                             if (tableHeight > 900) {
                             $(parent.document).find("#mainFrame")
                             .height(document.body.scrollHeight);
                             }
                             }, */
                            onSearch: function (text) {
                                //表格控件不支持高度自适应
                                var tableHeight = $('#expressTable').find(
                                                "thead").height()
                                        + $('#expressTable').find("tbody")
                                                .height()
                                        + 3
                                        + $('#expressTable').parent().parent()
                                                .parent().parent().find(
                                                        ".clearfix").height();
                                if ($('#expressTable')
                                                .bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                                    tableHeight = 105;
                                }
                                if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                                    tableHeight += ($('#expressTable').parent()
                                            .parent().parent().parent().find(
                                                    ".pull-left").height() + 20);
                                }
                                $('#expressTable').bootstrapTable('resetView',
                                        {
                                            "height": tableHeight
                                        });
                                if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                                    $(parent.document).find("#mainFrame")
                                            .height(document.body.scrollHeight);
                                }
                            },
                            onCheck: function (row) {
                                selectedRow = row;
                                bindFormData(row);
                                //绑定下拉框
                                if (row.ecGcode) {
                                    var ecArray = row.ecGcode.split(",");

                                    control.val(ecArray).trigger("change");

                                } else {
                                    control.val("").trigger("change");
                                }
                                if (row.colCode) {
                                    var colArray = row.colCode.split(",");

                                    control2.val(colArray).trigger("change");

                                } else {
                                    control2.val("").trigger("change");
                                }

                            },
                            singleSelect: true,
                            columns: [
                                {
                                    checkbox: true,
                                    width: 40
                                },
                                {
                                    field: 'status',
                                    title: '快递站点状态',
                                    width: 80,
                                    align: 'left',
                                    searchFormatter: false,
                                    formatter: function (value, row, index) {
                                        if (value == 1) {
                                            return '<a class="label label-success" style="font-size:12px;">启用</a>';

                                        }
                                        return '<a class="label label-warning" style="font-size:12px;">禁用</a>';
                                    }
                                },
                                {
                                    field: 'storeName',
                                    title: '快递站点名称',
                                    width: 100,
                                    sortable: true
                                },
                                {
                                    field: 'storeCode',
                                    title: '快递站点编号',
                                    width: 100,
                                    sortable: true
                                },
                                {
                                    field: 'supervisorName',
                                    title: '负责人',
                                    width: 80
                                },
                                {
                                    field: 'ecName',
                                    title: '快递公司名称',
                                    width: 120
                                },
                                {
                                    field: 'defaultECName',
                                    title: '快递公司名称(默认)',
                                    width: 120
                                },
                                {
                                    field: 'colName',
                                    title: '学校名称',
                                    width: 120
                                },
                                {
                                    field: 'location',
                                    title: '地址',
                                    width: 200
                                },
                                {
                                    field: 'phone',
                                    title: '手机号',
                                    width: 100
                                },
                                {
                                    field: 'packetModeMgr',
                                    title: '派件模式',
                                    width: 80,
                                    searchFormatter: false,
                                    formatter: function (value, row, index) {
                                        if (value == 1) {
                                            return '<a class="label label-success" style="font-size:12px;">已启用</a>';

                                        }
                                        return '<a class="label label-warning" style="font-size:12px;">已关闭</a>';
                                    }
                                },
                                {
                                    field: 'packetModeSend',
                                    title: '寄件模式',
                                    width: 80,
                                    searchFormatter: false,
                                    formatter: function (value, row, index) {
                                        if (value == 1) {
                                            return '<a class="label label-success" style="font-size:12px;">已启用</a>';

                                        }
                                        return '<a class="label label-warning" style="font-size:12px;">已关闭</a>';
                                    }
                                },
                                {
                                    field: 'closeMode',
                                    title: '打烊模式',
                                    width: 80,
                                    searchFormatter: false,
                                    formatter: function (value, row, index) {
                                        if (value == 1) {
                                            return '<a class="label label-success" style="font-size:12px;">已启用</a>';

                                        }
                                        return '<a class="label label-warning" style="font-size:12px;">已关闭</a>';
                                    }
                                }
                            ]
                        });
        /**
         加载合作公司
         */
        $
                .ajax({
                    url: "/manage/express/queryenabled",
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        $
                                .each(
                                        data.data,
                                        function (i, item) {
                                            control
                                                    .append("<option value='" + item.expresscompanyid + "'>&nbsp;"
                                                            + item.simplename
                                                            + "</option>");
                                            control3
                                                    .append("<option value='" + item.expresscompanyid + "'>&nbsp;"
                                                            + item.simplename
                                                            + "</option>");
                                            control4
                                                    .append("<option value='" + item.expresscompanyid + "'>&nbsp;"
                                                            + item.simplename
                                                            + "</option>");
                                        });
                        $('#expressId').select2("val", null);
                    }
                });

        /**
         加载学校
         */
        $.ajax({
            url: "/manage/college/queryselect",
            dataType: 'json',
            type: 'post',
            success: function (data) {
                $.each(data.data, function (i, item) {
                    control2.append("<option value='" + item.id + "'>&nbsp;"
                            + item.text + "</option>");
                });
            }
        });

        dataObject.on('page-change.bs.table', function () {
            dataObject.bootstrapTable('uncheckAll');
            selectedRow = null;
        });
    }
    /**
     * 将表单对象转为json对象
     */
    function convertToJsonStr(formValues) {

        var result = {};
        var ecGcode = [];
        var colList = [];
        $.each(formValues, function () {
            if (this.name == "ecList") {
                ecGcode.push(this.value);
                result[this.name] = ecGcode;
            } else if (this.name == "colList") {
                colList.push(this.value);
                result[this.name] = colList;
            } else if (this.name == "packetModeMgr") {
                $("#pmode").prop("checked") == true ? result[this.name] = 1
                        : result[this.name] = 0;
            }
            else if (this.name == "packetModeSend") {
                $("#pmode3").prop("checked") == true ? result[this.name] = 1
                        : result[this.name] = 0;
            }
            else if (this.name == "closeMode") {
                $("#pmode5").prop("checked") == true ? result[this.name] = 1
                        : result[this.name] = 0;
            }
            else {
                result[this.name] = this.value;
            }
        });
        return result;
    }
    /**
     绑定表单的值
     */
    function bindFormData(jsonData) {
        if (!jsonData)
            return;
        var obj = $("#formBody");
        $
                .each(
                        jsonData,
                        function (name, value) {
                            var inputMark = obj.find($.parseHTML("input:[name="
                                    + name + "]"));
                            if (inputMark.attr("type") == "checkbox") {
                                inputMark
                                        .each(function () {
                                            if (Object.prototype.toString
                                                            .apply(value) == ['object Array']) {
                                                for (var i = 0; i < value.length; i++) {
                                                    if ($(this).val() == value[i])
                                                        $(this).attr("checked",
                                                                "checked");
                                                }
                                            } else {
                                                if ($(this).val() == value)
                                                    $(this).attr("checked",
                                                            "checked");
                                            }

                                        });
                            } else if (inputMark.attr("type") == "textarea") {
                                obj.find("[name=" + name + "]").html(value);
                            } else {
                                obj.find("[name=" + name + "]").val(value);
                            }
                        });
    }
    /**
     重置表单
     */
    function resetFormData(formName) {
        $("#txt_storeCode").val("");
        $("#txt_storeName").val("");
        $("#txt_phone").val("");
        $("#txt_supervisorName").val("");
        $("#txt_location").val("");
        /* $(':input',formName)
         .not(':button, :submit, :reset, :hidden')
         .val('')
         .removeAttr('checked')
         .removeAttr('selected'); */
        control.val(null).trigger("change");
        control2.val(null).trigger("change");
        control3.val(null).trigger("change");
    }
    function delStore() {
        var delRow = dataObject.bootstrapTable("getSelections");
        var ids = [];
        for (var i = 0; i < delRow.length; i++) {
            ids.push(delRow[i].storeId);
        }

        $.ajax({
            url: "/store/exp/delbatch",
            dataType: 'json',
            data: {
                data: '{"ids":[' + ids + ']}'
            },
            type: 'post',
            success: function (data) {
                if (data.success == 0) {
                    sweetAlert("恭喜", "删除成功", "success");
                    dataObject.bootstrapTable('refresh', {
                        silent: true
                    });
                } else {
                    sweetAlert("", data.message, "info");
                }

            },
            error: function (XMLHttpRequest) {
                sweetAlert("", XMLHttpRequest.responseText, "error");
            }
        });

    }
    /**
     * 开关众包模式
     */
    function packet_mode() {
        var delRow = dataObject.bootstrapTable("getSelections");
        var ids = [];
        for (var i = 0; i < delRow.length; i++) {
            ids.push(delRow[i].storeId);
        }
        var mgrMode = $("input[name='mgrMode']:checked").val();
        var sendMode = $("input[name='sendMode']:checked").val();
        $.ajax({
            url: "/store/exp/checkpacketmode",
            dataType: 'json',
            data: {
                ids: ids.join(","),
                mgrMode: mgrMode,
                sendMode: sendMode
            },
            type: 'post',
            success: function (data) {
                if (data.success == 0) {
                    sweetAlert("恭喜", "更改成功", "success");
                    dataObject.bootstrapTable('refresh', {
                        silent: true
                    });
                    $("#paket_mode").modal("hide");
                } else {
                    sweetAlert("", data.message, "info");
                }

            },
            error: function (XMLHttpRequest) {
                sweetAlert("", XMLHttpRequest.responseText, "error");
            }
        });

    }
    function loadSelect() {
        control = $('#sel2Multi');
        control2 = $('#sel1Multi');
        control3 = $('#sel3Multi');
        control4 = $("#expressId");
        //$('#sel2').select2();
        control.select2({
            placeholder: '选择合作商',
            allowClear: true
        });
        control2.select2({
            placeholder: '选择学校',
            allowClear: true
        });
        control3.select2({
            placeholder: '选择默认快递公司',
            allowClear: true
        });
        control4.select2({
            placeholder: '选择默认快递公司',
            allowClear: true
        });
    }
</script>
</html>
