define(['base'], function (base) {

    var validate = {
        fields: {
            propertyId: {
                validators: {
                    notEmpty: {
                        message: '属性字典编码不能为空'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9\u9fa5_\u9fa5-]+$/,
                        message: '属性字典编码只允许输入数字和字母以及下划线和横杠  '
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '属性字典编码过长'
                    }
                }
            },
            propertyShortcode: {
                validators: {
                    notEmpty: {
                        message: '属性字典简称不能为空'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9\u9fa5_\u9fa5-]+$/,
                        message: '属性简码只允许输入数字和字母以及下划线和横杠 '
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '属性字典简码过长'
                    }
                }
            },
            description: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 100,
                        message: '描述信息过长'
                    }
                }
            },
            callAlias: {
                validators: {
                    notEmpty: {
                        message: '调用别名不能为空'
                    },
                    regexp: {
                        regexp: /^[A-Za-z0-9\u9fa5_\u9fa5-]+$/,
                        message: '调用别名只允许输入数字和字母以及下划线和横杠 '
                    },
                    stringLength: {
                        min: 1,
                        max: 20,
                        message: '调用别名过长'
                    }
                }
            },
            propertyValue: {
                validators: {
                    notEmpty: {
                        message: '属性值不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 100,
                        message: '属性值过长'
                    }
                }
            },
            sortNo: {
                validators: {
                    notEmpty: {
                        message: '排序号不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '序号必须为数字格式'
                    },
                    stringLength: {
                        min: 1,
                        max: 4,
                        message: '排序号过长'
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
        var ecGcode = [];
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
            var inputMark = obj.find($.parseHTML("input:[name=" + name + "]"));
            if (inputMark.attr("type") == "checkbox") {
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
    function resetFormData(panel) {
        $("#txt_propertyId", panel).val("");
        $("#txt_propertyShortcode", panel).val("");
        $("#txt_callAlias", panel).val("");
        $("#txt_description", panel).val("");
        $("#txt_propertyValue", panel).val("");
        $("#txt_sortNo", panel).val("");
    }

    function delStore() {
        $.ajax({
            url: "/manage/propertydict/delete",
            dataType: 'json',
            data: {
                data: '{"propertyId":"' + dataObject.bootstrapTable("getSelections")[0].propertyId + '"}'
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
                sweetAlert("", XMLHttpRequest.responseText, "error");
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
                showFooter: false,
                clickToSelect: true,
                singleSelect: true,
                url: '/manage/propertydict/query',
                method: 'post',
                onPageChange: function (number, size) {

                    var allSelections = $("#expressTable", panel).bootstrapTable("getAllSelections");
                    if (allSelections.length > 0) {
                        for (var shux in allSelections[0]) {
                            var removerCheck = [];
                            for (var i = 0; i < allSelections.length; i++) {
                                removerCheck.push(allSelections[i][shux]);
                            }
                            $("#expressTable", panel).bootstrapTable("uncheckBy", {field: shux, values: removerCheck});
                        }
                    }
                },
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
                onSearch: function (text) {
                    //表格控件不支持高度自适应
                    var tableHeight = $('#expressTable', panel).find("thead").height() + $('#expressTable', panel).find("tbody").height()
                        + 3 + $('#expressTable', panel).parent().parent().parent().parent().find(".clearfix").height();
                    if ($('#expressTable', panel).parent().parent().parent().parent().find(".clearfix").height() == 0) {
                        tableHeight += 56;
                    }
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($('#expressTable', panel).parent().parent().parent().parent().find(".pull-right").height() + 20);
                    }
                    if ($('#expressTable', panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 155;
                    } else {
                        tableHeight += 6;
                    }
                    //tableHeight += 43;
                    $('#expressTable', panel).bootstrapTable('resetView', {"height": tableHeight});
                    if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }
                },
                toolbar: $('#toolbar', panel),
                onCheck: function (row) {
                    selectedRow = row;
                    bindFormData(row, panel);
                },
                onUncheck: function (row) {
                    selectedRow = null;
                },
                columns: [
                    {
                        checkbox: true,
                        width: 60
                    }, {
                        field: 'propertyId',
                        title: '属性字典编码',
                        width: 100,
                        sortable: true
                    }, {
                        field: 'propertyShortcode',
                        title: '属性字典简码',
                        width: 100,
                        sortable: true
                    }, {
                        field: 'callAlias',
                        title: '调用别名',
                        width: 100,
                        sortable: true
                    }, {
                        field: 'description',
                        title: '描述信息',
                        width: 200,
                        sortable: true
                    }, {
                        field: 'propertyValue',
                        title: '属性值',
                        width: 120,
                        sortable: true
                    },
                    {
                        field: 'sortNo',
                        title: '序号',
                        width: 80,
                        sortable: true
                    }
                ]
            });

            $("#update", panel).click(function () {
                isupdate = true;
                if (!dataObject.bootstrapTable("getSelections") || dataObject.bootstrapTable("getSelections").length <= 0) {
                    sweetAlert("", "请选择要操作的项", "warning");
                }
                else {
                    bindFormData(dataObject.bootstrapTable("getSelections")[0]);
                    $("#editModal", panel).modal('show');
                }
                $("#txt_propertyId", panel).attr("readonly", true);
                base.validator(validate, "#formBody", self.edit, panel);
            });
            
            $("#editModal", panel).on('hidden.bs.modal', function() {
                $("#formBody", panel).data('bootstrapValidator').destroy();
                $("#formBody", panel).data('bootstrapValidator', null);
                base.validator(validate, "#formBody", self.edit, panel);
            });

            $("#add", panel).click(function () {
                isupdate = false;
                resetFormData(panel);
                $("#editModal", panel).modal('show');
                $("#txt_propertyId", panel).attr("readonly", false);
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
        },
        edit: function (panel) {
            var selectedRow = dataObject.bootstrapTable("getSelections");
            var jsonArray = $("#formBody", panel).serializeArray();
            var jsonData = convertToJsonStr(jsonArray);
            if (!isupdate) {
                $.ajax({
                    url: '/manage/propertydict/add',
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
                            $("#editModal", panel).modal('hide');
                            $('#formBody', panel).data('bootstrapValidator').resetForm(true);
                            resetFormData(panel);
                        } else {
                            sweetAlert("", data.message, "info");
                        }
                    },
                    error: function (XMLHttpRequest) {
                        $("#modal2Desc", panel).html(XMLHttpRequest.responseText);
                        remadal2.open();
                        $("#btnSave", panel).attr("disabled", false);
                    }
                });
            } else {
                jsonData["propertyId"] = selectedRow[0].propertyId;
                $.ajax({
                    url: '/manage/propertydict/update',
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
                            $("#editModal", panel).modal('hide');
                            $('#formBody', panel).data('bootstrapValidator').resetForm(true);
                        } else {
                            sweetAlert("", data.message, "info");
                            $("#btnSave", panel).attr("disabled", false);
                        }
                    },
                    error: function (XMLHttpRequest) {
                        sweetAlert("", XMLHttpRequest.responseText, "info");
                        $("#btnSave", panel).attr("disabled", false);
                    }
                });
            }
        }
    };
});