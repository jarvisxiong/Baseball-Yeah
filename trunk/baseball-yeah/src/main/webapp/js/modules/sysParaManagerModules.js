define(['base'], function (base) {

    var validate = {
        fields: {
            parameterName: {
                validators: {
                    notEmpty: {
                        message: '参数名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 30,
                        message: '参数名过长'
                    }
                }
            },
            value: {
                validators: {
                    notEmpty: {
                        message: '值不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1000,
                        message: '值过长'
                    }
                }
            },
            description: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '描述信息过长'
                    }
                }
            }
        }
    };

    var selectedRow = null;
    var isupdate = false;

    /**
     * 将表单对象转为json对象
     */
    function convertToJsonStr(formValues) {
        var result = {};
        $.each(formValues, function () {
            result[this.name] = this.value;
        });
        return result;
    }

    /**
     绑定表单的值
     */

    function bindFormData(jsonData, panel) {
        if (!jsonData)
            return;
        var obj = $("#formBody", panel);
        $.each(jsonData, function (name, value) {
            var inputMark = obj.find("input[name='" + name + "']");
            if (inputMark.attr("type") == "checkbox") {
                if (inputMark.length > 1) {
                    inputMark.each(function () {
                        if (Object.prototype.toString.apply(value) == ['object Array']) {
                            for (var i = 0; i < value.length; i++) {
                                if ($(this).val() == value[i])
                                    $(this).attr("checked", "checked");
                            }
                        } else {
                            if ($(this).val() == value)
                                $(this).attr("checked", "checked");
                        }
                    });
                }
            }
            else if (inputMark.attr("type") == "textarea") {
                obj.find("[name=" + name + "]").html(value);
            } else {
                obj.find("[name=" + name + "]").val(value);
            }
        });
    }

    /**
     重置表单
     */

    function resetFormData(panel) {
        $("#txt_parameterName", panel).val("");
        $("#txt_value", panel).val("");
        $("#txt_description", panel).val("");
    }

    function delStore() {
        var selectedRow = dataObject.bootstrapTable("getSelections");
        $.ajax({
            url: "/manage/syspara/delete",
            dataType: 'json',
            data: {
                data: '{"sysParameterId":"'
                + selectedRow[0].sysParameterId + '"}'
            },
            type: 'post',
            success: function (data) {
                if (data.success == 0) {
                    sweetAlert("", "删除成功", "success");
                    dataObject.bootstrapTable('refresh', {
                        silent: true
                    });
                } else {
                    sweetAlert("", data.message, "info");
                }
            },
            error: function (XMLHttpRequest) {
                sweetAlert("", XMLHttpRequest.responseText, "warning");
            }
        });
    }

    return {
        init: function (panel) {
            var self = this;
            dataObject = $('#expressTable', panel).bootstrapTable({
                height: '960',
                striped: true,
                pagination: true,
                pageSize: 20,
                pageList: [20, 30, 40, 60],
                search: true,
                showRefresh: true,
                clickToSelect: true,
                singleSelect: true,
                url: '/manage/syspara/getlist',
                method: 'post',
                onLoadSuccess: function (data) {
                    var tableHeight = $('#expressTable', panel).find("thead").height() + $('#expressTable', panel).find("tbody").height()
                        + 3 + $('#expressTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if (this.search) {
                        tableHeight += ($('#expressTable', panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $('#expressTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
                },
                toolbar: $('#toolbar', panel),
                onCheck: function (row) {
                    selectedRow = row;
                    bindFormData(row, panel);
                },
                columns: [
                    {
                        checkbox: true,
                        width: 60
                    }, {
                        field: 'parameterName',
                        title: '参数名',
                        width: 100,
                        sortable: true
                    }, {
                        field: 'value',
                        title: '参数值',
                        width: 100,
                        sortable: true
                    }, {
                        field: 'description',
                        title: '描述信息',
                        width: 200,
                        sortable: true,
                        searchFormatter: false
                    }, {
                        field: 'beEnabled',
                        title: '是否启用',
                        width: 80,
                        sortable: true,
                        searchFormatter: false,
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value == 1) {
                                return '<a class="label label-success" style="font-size:12px; cursor:pointer;">启用</a>';
                            }
                            return '<a class="label label-warning" style="font-size:12px; cursor:pointer;">禁用</a>';
                        }
                    }
                ]
            });

            $("#update", panel).click(function () {
                isupdate = true;
                if (!dataObject.bootstrapTable("getSelections") || dataObject.bootstrapTable("getSelections").length <= 0) {
                    sweetAlert("", "请选择要操作的项", "warning");
                }
                else {
                    var editRow = dataObject.bootstrapTable("getSelections")[0];
                    bindFormData(editRow, panel);
                    $("#editModal", panel).modal('show');
                    base.validator(validate, "#formBody", self.edit, panel);
                }
            });
            $("#add", panel).click(function () {
                isupdate = false;
                resetFormData("#formBody");
                $("#editModal", panel).modal('show');
                base.validator(validate, "#formBody", self.edit, panel);
            });
            $("#remove", panel).click(function () {
                if (!dataObject.bootstrapTable("getSelections") || dataObject.bootstrapTable("getSelections").length <= 0) {
                    sweetAlert("", "请选择要操作的项", "warning");
                }
                else {
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

            $("#btnReset", panel).click(function () {
                resetFormData(panel);
            });
            
            $("#editModal", panel).on('hidden.bs.modal', function() {
                $("#formBody", panel).data('bootstrapValidator').destroy();
                $("#formBody", panel).data('bootstrapValidator', null);
            });
        },
        edit: function (panel) {
            var selectedRow = dataObject.bootstrapTable("getSelections");
            var jsonArray = $("#formBody", panel).serializeArray();
            var jsonData = convertToJsonStr(jsonArray);
            if (!isupdate) {
                $.ajax({
                    url: '/manage/syspara/add',
                    dataType: 'JSON',
                    type: 'post',
                    data: {data: JSON.stringify(jsonData)},
                    success: function (data) {
                        if (data.success == 0) {
                            sweetAlert("", "新增成功", "success");
                            dataObject.bootstrapTable('refresh', {silent: true});
                            $("#editModal", panel).modal('hide');
                            resetFormData("#formBody");
                        }
                        else {
                            sweetAlert("", data.message, "info");
                        }
                    },
                    error: function (XMLHttpRequest) {
                        sweetAlert("", XMLHttpRequest.responseText, "error");
                        $("#btnSave", panel).attr("disabled", false);
                    }

                });
            }
            else {
                jsonData["sysParameterId"] = selectedRow[0].sysParameterId;
                $.ajax({
                    url: '/manage/syspara/update',
                    dataType: 'JSON',
                    type: 'post',
                    data: {data: JSON.stringify(jsonData)},
                    success: function (data) {
                        if (data.success == 0) {
                            sweetAlert("", "修改成功", "success");
                            dataObject.bootstrapTable('refresh', {silent: true});
                            $("#editModal", panel).modal('hide');
                        }
                        else {
                            sweetAlert("", data.message, "info");
                        }
                    },
                    error: function (XMLHttpRequest) {
                        sweetAlert("", XMLHttpRequest.responseText, "error");
                        $("#btnSave", panel).attr("disabled", false);
                    }
                });
            }
        }
    };
});