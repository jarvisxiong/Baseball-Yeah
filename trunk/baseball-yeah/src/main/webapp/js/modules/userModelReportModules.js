define(['base'], function (base) {

    return {
        init: function (panel) {
            var self = this;
            datatable = base.datagrid({
                url: '/report/storesms/smsmodelreport',
                method: 'get',
                sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
                pageList: [10, 20, 50, 100], // 可供选择的每页的行数（*）
                search: false,
                showRefresh: false,
                showExport: true,//显示导出按钮
                exportDataType: "all",//导出类型
                height: 800,
                queryParams: function (params) {
                    return $.extend(params, {
                        userName: $('#userName', panel).val(),
                        storeId: $('#store', panel).val(),
                        phone: $('#phoneNo', panel).val()
                    });
                },
                columns: [{
                    checkbox: true
                }, {
                    field: 'storeName',
                    title: '货源名称',
                    sortable: true,
                    width: 300
                }, {
                    field: 'userName',
                    title: '用户名',
                    sortable: true,
                    width: 100
                }, {
                    field: 'phone',
                    title: ' 手机号',
                    sortable: true,
                    width: 100
                }, {
                    field: 'modelName',
                    title: '模板名称',
                    sortable: true,
                    width: 200
                }, {
                    field: 'modelContent',
                    title: '模板内容',
                    sortable: true
                }]
            }, '#userModelReportTable', panel);

            $.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {
                    $("#store", panel).select2({
                        data: data
                    });
                }
            });

            $(window).resize(function () {
                $('#userModelReportTable', panel).bootstrapTable('resetView');
            });

            $("#btn_query", panel).click(function () {
                datatable.bootstrapTable('selectPage', 1);
            });

            $("#btn_delete", panel).click(function () {
                var arrselections = $("#userModelReportTable", panel).bootstrapTable('getSelections');
                if (arrselections.length > 1) {
                    base.error("只能选择一行进行编辑!");
                    return;
                }
                if (arrselections.length <= 0) {
                    base.error("请选择有效数据!");
                    return;
                }
                var smsModelId = arrselections[0].smsModelId;
                base.cancel({
                    title: "删除用户模板信息",
                    text: "您确定要删除此用户模板吗？"
                }, function () {
                    $.post("/message/usersmsmodel/del", {
                        "smsModelId": smsModelId
                    }, function (data, status) {
                        if (status == "success") {
                            var obj = data;
                            if (obj.success == 0) {
                                $("#userModelReportTable", panel).bootstrapTable('refresh');
                                base.success("删除成功！");
                            } else {
                                base.error(obj.message);
                            }
                        } else {
                            base.error("删除失败");
                        }
                    });
                });
            });

            $(".text-change", panel).on("input propertychange", function () {
                $(this).val($(this).val().trim());
            });

            $("#clearSearch", panel).click(function () {
                base.reset($(".main-box-header",panel));
                $("#store", panel).val(" ").trigger("change");
            });
        }
    };
});