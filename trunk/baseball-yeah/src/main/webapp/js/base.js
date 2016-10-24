define(function () {

    return {
        datagrid: function (options, target) {
            // / <summary>
            // / 创建表格
            // / </summary>
            // / <param name="options">配置信息</param>
            // / <param name="target">dom或jquery对象</param>
            var params = {
                // 请求后台的URL（*）
                method: 'get', // 请求方式（*）
                striped: true, // 是否显示行间隔色
                pagination: true, // 是否显示分页（*）
                sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
                pageList: [10, 25, 50, 100], // 可供选择的每页的行数（*）   
                singleSelect: true,
                showExport: true,// 显示导出按钮
                exportDataType: "all",// 导出类型
                toolbar: "#toolbar",
                exportTypes: ['excel'],
                exportOptions: {
                    fileName: (new Date()).Format("yyyy-MM-dd HH:mm:ss") + $('h1').text()
                },
                height: 700,
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = $(target).find("thead").height() + $(target).find("tbody").height()
                        + 3 + $(target).parent().parent().parent().parent().find(".clearfix").height();
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        if ($(target).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                            tableHeight = 105;
                        }
                        tableHeight += ($(target).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $(target).bootstrapTable('resetView', {"height": tableHeight});
                    if ((tableHeight + $(".panel-default").height() || 300) > 1350) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1500);
                    }
                },
                onSearch: function (text) {
                    //表格控件不支持高度自适应
                    var tableHeight = $(target).find("thead").height() + $(target).find("tbody").height()
                        + 3 + $(target).parent().parent().parent().parent().find(".clearfix").height();
                    if ($(target).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($(target).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $(target).bootstrapTable('resetView', {"height": tableHeight});
                    if ((tableHeight + $(".panel-default").height() || 300) > 1350) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    } else {
                        $(parent.document).find("#mainFrame").height(1500);
                    }
                },
                clickToSelect: true
            };
            $.extend(params, options);

            var self = this;
            var datagrid = $(target).bootstrapTable(params);

            return datagrid;
        },
        error: function (message) {
            sweetAlert(
                "糟了...",
                message,
                "warning");
        },
        success: function (message) {
            swal("恭喜!", message, "success");
        },
        cancel: function (options, fn) {
            var params = {
                title: "",
                text: "请传入text",
                type: "warning",
                showCancelButton: true,
                animation: 'slide-from-top',
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确定!",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            };
            $.extend(params, options);
            sweetAlert(params, fn);
        },
        validator: function (options, target, fn) {
            var params = {
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                }
            };
            $.extend(params, options);
            //判断事件有没有绑定 防止重复绑定
            var dataevent = jQuery._data($(target)[0], "events");
            if (dataevent) {
                dataevent.success || $(target).bootstrapValidator(params).on('success.form.bv', function (e) {
                    // Prevent form submission
                    e.preventDefault();
                    fn();
                });
            } else {
                $(target).bootstrapValidator(params).on('success.form.bv', function (e) {
                    // Prevent form submission
                    e.preventDefault();
                    fn();
                });
            }
            ;
        },
        reset: function (target) {
            $(target).find(':input').not(':button, :submit, :reset').val('')
                .removeAttr('checked').removeAttr('selected');
        },
        property: function (code, extentionkey, extentionval) {
            var property = {
                data: {
                    '01': '到件扫描',
                    '02': '收件人下单',
                    '03': '众包抢单',
                    '04': '众包取件',
                    '05': '收件人付款',
                    '06': '取消订单',
                    '07': '签收',
                    '08': '问题件'
                },
                set: function (key, value) {
                    this.data[key] = value;
                },
                get: function (key) {
                    return this.data[key];
                }
            };
            if (extentionkey && extentionval) {
                property.data[extentionkey] = extentionval
            }
            return property.data[code] || code;
        },
        loadRemoteSelect: function (selector, url,params) {
            //加载前先清空
            $(selector).empty();
            //先初始化
             $(selector).select2({
                 placeholder: {
                     id: '',
                    text: '请选择'
                },
                 allowClear: true
             });
            //加载服务端数据
            $.ajax({
                type: "POST",
                url: url,
                dataType: "json",
                data:params,
                success: function (data) {
                    $(selector).select2({
                        data:data,
                        allowClear: true,
                        placeholder: {
                            id: '',
                            text: '请选择'
                        }
                    });
                    $(selector).val("").trigger("change");
                }
            });

        }
    };
});