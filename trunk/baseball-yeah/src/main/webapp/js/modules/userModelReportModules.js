define(['base'], function (base) {

    return {
        init: function () {
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
                        userName: $('#userName').val(),
                        storeId: $('#store').val(),
                        phone: $('#phoneNo').val(),
                        modelContent: $("#modelContent").val()
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
            }, '#userModelReportTable');

            $.ajax({
                type: "POST",
                url: "/store/exp/expstoreinfo",
                dataType: "json",
                success: function (data) {
                    $("#store").select2({
                        data: data
                    });
                }
            });

            $(window).resize(function () {
                $('#userModelReportTable').bootstrapTable('resetView');
            });

            $("#btn_query").click(function () {
                datatable.bootstrapTable('refresh', {"query": {"offset": 0}});
            });

            $("#btn_delete").click(function () {
                var arrselections = $("#userModelReportTable").bootstrapTable('getSelections');
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
                                $("#userModelReportTable").bootstrapTable('refresh');
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

            $(".text-change").on("input propertychange", function () {
                $(this).val($(this).val().trim());
            });

            $("#clearSearch").click(function () {
                base.reset(".main-box-header");
                $("#store").val(" ").trigger("change");
            });
        }
    };
});