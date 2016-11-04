define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var date = new Date();
    date.setDate(date.getDate() + 1);
    var tomorrow = date.Format('yyyy-MM-dd');

    function setting(panel) {
        return {
            check: {
                enable: false,
                chkStyle: "checkbox"
            },
            data: {
                simpleData: {
                    idKey: "id",
                    pIdKey: "pId",
                    enable: true
                }
            },
            callback: {
                onAsyncSuccess: insertSelect,
                onClick: ShowTableOnClick,
                //折叠
                onCollapse: function (event, treeId, treeNode) {
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    if (treeNode && treeNode.length > 0 && treeNode.id != 'root') {
                        treeObj.removeChildNodes(treeNode);
                    }

                },
                onExpand: function (event, treeId, treeNode) {
                    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                    //treeObj.reAsyncChildNodes(treeNode, "refresh");

                    var tableHeight = $("#mainTree", panel).height();
                    if (tableHeight > 900) {
                        $(parent.document).find("#mainFrame", panel).height(document.body.scrollHeight);
                    }
                }
            },
            async: {
                enable: true,
                url: function (treeId, treeNode) {
                    var url = "";
                    //省id
                    if (treeNode.id.indexOf("root") != -1) {
                        url = "/manage/province/selectprovincesandsignin";
                        updateSelectAndTable("root")
                    }
                    //城市id
                    else if (treeNode.id.indexOf("p_") != -1) {
                        url = "/manage/province/selectcitysesandsignin/provinceid/" + treeNode.id.slice(2);
                        showCityTable(panel, treeNode.id.slice(2));

                    }
                    //学校
                    else if (treeNode.id.indexOf("c_") != -1) {
                        url = "/manage/college/selectcollegeandsignin/cityid/" + treeNode.id.slice(2);
                        showCollegeTable(panel, treeNode.id.slice(2), '')
                    }
                    //众包人员
                    else if (treeNode.id.indexOf("co_") != -1) {
                        url = "/manage/college/selectpuserandsignin/collegeid/" + treeNode.id.slice(3);
                        showPuserTable(panel, treeNode.id.slice(3), '')
                    }
                    return url;
                },
                autoParam: ["id", "name"],
                type: "get"
            }
        }
    }

    function initTree(panel) {
        $.get("manage/province/selectallsignin", function (data, status) {
            $(document).ready(function () {
                $.fn.zTree.init($("#treeDemo", panel), setting(panel), data);
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                var node = treeObj.getNodeByTId("treeDemo_1");
                treeObj.expandNode(node, true, true, true);

            });
        });


    }

    function cleanTable() {
        $('#provinceWorkSigninTable').bootstrapTable('destroy');
        $('#cityWorkSigninTable').bootstrapTable('destroy');
        $('#collegeWorkSigninTable').bootstrapTable('destroy');
        $('#puserWorkSigninTable').bootstrapTable('destroy');
    }

    function checkType(id) {
        //全国
        if (id.indexOf("root") != -1) {
            return 'root';
        }
        //省id
        if (id.indexOf("p_") != -1) {
            return 'province';
        }
        //市
        else if (id.indexOf("c_") != -1) {
            return 'city';
        }
        //学校
        else if (id.indexOf("co_") != -1) {
            return 'college';
        }
        return '';
    }

    function insertSelect(event, treeId, treeNode, msg) {
        var data = eval(msg);
        switch (checkType(data[0].id)) {
            case 'city':
                insertCity(data);
                break;
            case 'college':
                insertCollege(data);
                break;
            default:
        }
    }

    function updateSelectAndTable(id) {
        var type = checkType(id);
        var url = '';
        //var data = eval(msg);
        switch (type) {
            case 'root':
                url = "/manage/province/selectallcitiesandsignin";
                showProvinceTable('');
                break;
            case 'province':
                url = "/manage/province/selectcitysesandsignin/provinceid/" + id.slice(2);
                showCityTable('', id.slice(2), '');
                break;
            case 'city':
                url = "/manage/college/selectcollegeandsignin/cityid/" + id.slice(2);
                showCollegeTable('', id.slice(2), '');
                break;
            case 'college':
                url = "/manage/college/selectpuserandsignin/collegeid/" + id.slice(3);
                showPuserTable('', id.slice(3), '');
                break;
            default:
                return;
        }
        $.ajax({
            method: "GET",
            url: url
            //data: { name: "John", location: "Boston" }
        }).done(function (data) {
            switch (type) {
                case 'root':
                    insertCity(data);
                    break;
                case 'province':
                    insertCity(data);
                    break;
                case 'city':
                    insertCollege(data);
                    break;
                default:
            }
            //alert("Data Saved: " + data);
        });
    }

    function insertCity(data) {
        $("#cityId option").remove();
        $("#cityId").append("<option value=0>请选择</option>");

        for (var i = 0; i < data.length; i++) {
            $("#cityId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
        }
    }

    function insertCollege(data) {
        $("#collegeId option").remove();
        $("#collegeId").append("<option value='0'>请选择</option>");
        for (var i = 0; i < data.length; i++) {
            $("#collegeId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
        }
    }

    function ShowTableOnClick(event, treeId, treeNode) {
        updateSelectAndTable(treeNode.id)
    }

    function showProvinceTable(panel) {
        cleanTable();
        var date = new Date();
        date.setDate(date.getDate() + 1);
        var workDate = date.Format('yyyy-MM-dd');

        base.datagrid(
            {
                height: 460,
                showExport:false,
                sortable: false,
                toolbar:false,
                url: '/worksignin/getprovinces',
                queryParams: function (params) {
                    return $.extend(params, {
                        workDate: workDate
                    });
                },
                onLoadSuccess: function (data) {

                },
                columns: [
                    {
                        field: 'provinceName',
                        title: '省',
                        sortable: true,
                        width: 600
                    },
                    {
                        field: 'workSignin',
                        title: '运力数量',
                        sortable: true,
                        width: 600
                    }
                ]
            }, '#provinceWorkSigninTable', panel)
    }

    function showCityTable(panel, provinceId) {
        cleanTable();
        var date = new Date();
        date.setDate(date.getDate() + 1);
        var workDate = date.Format('yyyy-MM-dd');

        base.datagrid(
            {
                height: 460,
                showExport:false,
                sortable: false,
                toolbar:false,
                url: '/worksignin/getcities',
                queryParams: function (params) {
                    return $.extend(params, {
                        workDate: workDate,
                        provinceId: provinceId
                    });
                },
                onLoadSuccess: function (data) {

                },
                columns: [

                    {
                        field: 'cityName',
                        title: '市',
                        sortable: true,
                        width: 600
                    },
                    {
                        field: 'workSignin',
                        title: '运力数量',
                        sortable: true,
                        width: 600
                    }

                ]
            }, '#cityWorkSigninTable', panel)
    }

    function showCollegeTable(panel, cityId, workDate) {
        cleanTable();
        if (workDate == '') {
            workDate = tomorrow;
        }

        base.datagrid(
            {
                sortable: false,
                showExport:false,
                height: 460,
                toolbar:false,
                url: '/worksignin/getcolleges',
                queryParams: function (params) {
                    return $.extend(params, {
                        workDate: workDate,
                        cityId: cityId
                    });
                },
                onLoadSuccess: function (data) {
                },
                columns: [

                    {
                        field: 'fullName',
                        title: '学校',
                        sortable: true,
                        width: 600
                    },
                    {
                        field: 'workSignin',
                        title: '运力数量',
                        sortable: true,
                        width: 600
                    }

                ]
            }, '#collegeWorkSigninTable', panel)
    }

    function showPuserTable(panel, collegeId, workDate) {
        cleanTable();
        if (workDate == '') {
            workDate = tomorrow;
        }
        base.datagrid(
            {
                height: 460,
                showExport:false,
                sortable: false,
                toolbar:false,
                url: '/worksignin/getpusers',
                queryParams: function (params) {
                    return $.extend(params, {
                        workDate: workDate,
                        collegeId: collegeId
                    });
                },
                onLoadSuccess: function (data) {
                },
                columns: [

                    {
                        field: 'realName',
                        title: '小派名称',
                        sortable: true,
                        width: 600
                    },
                    {
                        field: 'createDate',
                        title: '签到时间',
                        formatter: function (value, row, index) {
                            if (value != null) {
                                var date = new Date(value);
                                return date.Format('yyyy-MM-dd HH:mm:ss');
                            } else {
                                return '';
                            }
                        },
                        sortable: true,
                        width: 600
                    }

                ]
            }, '#puserWorkSigninTable', panel)
    }

    return {
        init: function (panel) {
            initTree(panel);
            showProvinceTable(panel);
            var self = this;
            var date = new Date();
            // 开始时间
            $('#workDatePicker', panel).datetimepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                startView: 2,
                minView: 2,
                todayHighlight: true,
                endDate: new Date(date.getFullYear(), date.getMonth(), date.getDate())
            }).on('changeDate', function (e) {
                var startTime = e.date;
                $('#createEndDatePicker', panel).datetimepicker('setStartDate', startTime);
            });

            $("#btn_query_signin").click(function () {
                var workDate = $("#workDate").val();
                var collegeId = $("#collegeId").val();
                var cityId = $("#cityId").val();
                if (collegeId != null && collegeId != undefined && collegeId != 0) {
                    showPuserTable('', collegeId.slice(3), workDate)
                } else if (cityId != undefined && cityId != null&& cityId != 0) {
                    showCollegeTable('', cityId.slice(2), workDate)
                }else {
                    alert('请选择城市或学校');
                }

            });

            $("#cityId").change(function () {
                updateSelectAndTable($("#cityId").val());
            })

        }
    };
});