define(function () {

    return {
        datagrid: function (options, target, panel) {
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
                toolbar: $("#toolbar", panel),
                exportTypes: ['excel'],
                exportOptions: {
                    fileName: (new Date()).Format("yyyy-MM-dd HH:mm:ss") + $('h1').text()
                },
                height: 700,
                onLoadSuccess: function (data) {
                    //表格控件不支持高度自适应
                    var tableHeight = $(target, panel).find("thead").height() + $(target, panel).find("tbody").height()
                        + 59;
                    if (data.total == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        if ($(target, panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                            tableHeight = 105;
                        }
                        tableHeight += ($(target, panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $(target, panel).bootstrapTable('resetView', {"height": tableHeight});

                },
                onSearch: function (text) {
                    //表格控件不支持高度自适应
                    var tableHeight = $(target, panel).find("thead").height() + $(target).find("tbody").height()
                        + 3 + $(target, panel).parent().parent().parent().parent().find(".clearfix").height();
                    if ($(target, panel).bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                    if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                        tableHeight += ($(target, panel).parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }
                    $(target).bootstrapTable('resetView', {"height": tableHeight});

                },
                clickToSelect: true,
                onPageChange: function (number, size) {

                    var allSelections = $(target, panel).bootstrapTable("getAllSelections");
                    if (allSelections.length > 0) {
                        for (var shux in allSelections[0]) {
                            var removerCheck = [];
                            for (var i = 0; i < allSelections.length; i++) {
                                removerCheck.push(allSelections[i][shux]);
                            }
                            $(target, panel).bootstrapTable("uncheckBy", {field: shux, values: removerCheck});
                        }
                    }
                }
            };

            if (options && options.columns && options.columns.length > 0) {
                if (options.columns[0].checkbox) {
                    $.extend(params.exportOptions, {ignoreColumn: [0]});
                }
            }

            $.extend(params, options);

            var self = this;
            var datagrid = $(target, panel).bootstrapTable(params);

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
        validator: function (options, target, fn, panel) {
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
            var dataevent = jQuery._data($(target, panel)[0], "events");
            if (dataevent) {
                dataevent.success || $(target, panel).bootstrapValidator(params).on('success.form.bv', function (e) {
                    // Prevent form submission
                    e.preventDefault();
                    fn(panel);
                });
            } else {
                $(target, panel).bootstrapValidator(params).on('success.form.bv', function (e) {
                    // Prevent form submission
                    e.preventDefault();
                    fn(panel);
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
                    '08': '问题件',
                    '09': '众包转单',
                    '10': '退件'
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
        loadRemoteSelect: function (selector, url, params) {
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
                data: params,
                success: function (data) {
                    $(selector).select2({
                        data: data,
                        allowClear: true,
                        placeholder: {
                            id: '',
                            text: '请选择'
                        }
                    });
                    $(selector).val("").trigger("change");
                }
            });

        },
        /// <summary>
        /// 打开tab页面
        /// </summary>
        /// <param name="title">要打开的tab的名称</param>
        /// <param name="href">要打开的tab链接</param>
        /// <param name="fn">打开后要执行的函数（非必填）</param>
        /// <param name="context">打开后要执行的函数指定的上下文（非必填）</param>
        openTab: function (title, href, fn, context) {
            if ($('#indextab').tabs('exists', title)) {
                //如果已打开那么关闭重新打开
                $('#indextab').tabs('close', title);
                $('#indextab').tabs('add', {
                    title: title,
                    href: href,
                    closable: true,
                    bodyCls: 'content-doc',
                    onLoad: function () {
                        if (fn) {
                            if (context) {
                                fn.call(context);
                            } else {
                                fn();
                            }
                        }
                    }
                });
            } else {
                $('#indextab').tabs('add', {
                    title: title,
                    href: href,
                    closable: true,
                    bodyCls: 'content-doc',
                    onLoad: function () {
                        if (fn) {
                            if (context) {
                                fn.call(context);
                            } else {
                                fn();
                            }
                        }
                    }
                });
            }
        },
        /// <summary>
        /// 关闭tab页面
        /// </summary>
        /// <param name="title">要关闭的tab的名称</param>
        /// <param name="selectTab">关闭后要打开的tab页面名称（非必填）</param>
        /// <param name="fn">关闭后要执行的函数（非必填）</param>
        colseTab: function (title, selectTab, fn) {
            $('#indextab').tabs('close', title);
            if (selectTab) {
                $('#indextab').tabs('select', selectTab);
            }
            fn && fn();
        },
        /// <summary>
        /// 学校树
        /// </summary>
        /// <param name="target">$元素对象/param>
        /// <param name="options">控件其他参数（非必填）</param>
        collegeTree: function (target, options) {
            var params = {
                width: 240,
                multiple: true,
                url: "/manage/province/selectprovincefortree",
                onCheck: function (node, checked) {
                    if (checked) {
                        if (node.children.length > 0) {
                            for (var i = 0; i < node.children.length; i++) {
                                $(this).tree('expand', node.children[i].target);
                            }

                        } else {
                            $(this).tree('expand', node.target);
                        }
                    }
                },
                onClick: function (node) {
                    var checkeds = $(target).combotree('tree').tree('getChecked');	// get the tree object
                    var colleges = [];
                    for (var i = 0; i < checkeds.length; i++) {
                        if (checkeds[i].attributes) {
                            colleges.push(checkeds[i].text);
                        }
                    }
                    $(target).combotree('setText', colleges.join(","));
                },
                onLoadSuccess: function () {
                    var checkeds = $(target).combotree('tree').tree('getChecked');	// get the tree object
                    var colleges = [];
                    for (var i = 0; i < checkeds.length; i++) {
                        if (checkeds[i].attributes) {
                            colleges.push(checkeds[i].text);
                        }
                    }
                    $(target).combotree('setText', colleges.join(","));
                },
                onBeforeExpand: function (node, param) {
                    $(this).tree('options').url = "/manage/province/selectcollegefortree?parentId=" + node.id;
                }
            };
            options = options || {};
            $.extend(params, options);
            var collegetree = $(target).combotree(params);
            return collegetree;
        },
        /// <summary>
        /// 获取学校树
        /// </summary>
        /// <param name="target">$元素对象/param>
        getTreeValues: function (target) {
            var checkeds = $(target).combotree('tree').tree('getChecked');	// get the tree object
            var colleges = [];
            for (var i = 0; i < checkeds.length; i++) {
                if (checkeds[i].attributes) {
                    colleges.push(checkeds[i].id);
                }
            }
            return colleges;
        }
    };
});