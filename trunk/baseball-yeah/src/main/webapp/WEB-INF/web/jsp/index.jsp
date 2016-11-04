<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派后台管理系统</title>

    <script src="js/jquery.js"></script>
    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" href="css/libs/nanoscroller.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="css/compiled/layout.css">
    <link rel="stylesheet" type="text/css" href="css/compiled/elements.css">
    <link href="plugs/sweet-alert/css/sweet-alert.css" rel="stylesheet">
    <script src="plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <link rel="stylesheet" href="css/libs/morris.css" type="text/css"/>
    <link rel="stylesheet" href="css/libs/daterangepicker.css"
          type="text/css"/>
    <link rel="stylesheet" href="css/libs/jquery-jvectormap-1.2.2.css"
          type="text/css"/>
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>

    <script src="js/scripts.js"></script>
    <link href="/js/easyui/css/linkbutton.css" rel="stylesheet"/>
    <link href="/js/easyui/css/panel.css" rel="stylesheet"/>
    <link href="/js/easyui/css/tree.css" rel="stylesheet"/>
    <link href="/js/easyui/css/combo.css" rel="stylesheet"/>
    <link href="/js/easyui/css/tabs.css" rel="stylesheet"/>
    <link href="/js/easyui/css/textbox.css" rel="stylesheet"/>
    <link href="/js/easyui/css/validatebox.css" rel="stylesheet"/>
    <script src="js/bootstrap.js"></script>
    <script
            src="<%=request.getContextPath()%>/plugs/bootstrap3-dialog-master/src/js/bootstrap-dialog.js"></script>

    <link
            href="<%=request.getContextPath()%>/plugs/bootstrap3-dialog-master/src/css/bootstrap-dialog.css"
            type="text/css" rel="stylesheet"/>
    <link
            href="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"
            rel="stylesheet">
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/bootstrap-datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>

    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/select2.full.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/select2/i18n/zh-CN.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/bootstrap-table.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/locale/bootstrap-table-zh-CN.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script
            src="<%=request.getContextPath()%>/js/extensions/export/tableExport.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/JSON-js-master/json_parse.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/plugs/knockout/knockout-3.4.0.js"></script>
    <script src="<%=request.getContextPath()%>/js/slide/js/pic.js"></script>
    <link rel="stylesheet" href="/css/libs/select2.min.css">
    <link rel="stylesheet" href="/css/libs/bootstrap-table.min.css">
    <link rel="stylesheet" href="/plugs/sweet-alert/css/sweet-alert.css">

    <script src="/js/easyui/jquery.parser.js"></script>
    <script src="/js/easyui/jquery.tooltip.js"></script>

    <script src="/js/easyui/jquery.linkbutton.js"></script>
    <script src="/js/easyui/jquery.validatebox.js"></script>
    <script src="/js/easyui/jquery.panel.js"></script>
    <script src="/js/easyui/jquery.textbox.js"></script>
    <script src="/js/easyui/jquery.tree.js"></script>
    <script src="/js/easyui/jquery.combo.js"></script>
    <script src="/js/easyui/jquery.combotree.js"></script>
    <script src="/js/easyui/jquery.tabs.js"></script>

    <script src="/js/raty/jquery.raty.min.js"></script>
    <script src="/js/validator/bootstrapValidator.min.js"></script>
    <script type="text/javascript"
            src="/plugs/echart/echarts.min.js"></script>
    <script type="text/javascript"
            src="/plugs/echart/theme/macarons.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugs/dropzone/dropzone.min.css">
    <script
            src="<%=request.getContextPath()%>/plugs/dropzone/dropzone.js"></script>
    <script src="/js/require.js" data-main="/js/modules/main"></script>


    <!-- ztree开始 -->
    <link rel="stylesheet" href="/plugs/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/plugs/zTree_v3-master/js/jquery.ztree.all.min.js"></script>
    <!-- ztree结束 -->

    <!-- [if lt IE 8]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif] -->
    <style type="text/css">
        .sweet-alert {
            background-color: white;
            font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
            width: 478px;
            padding: 17px;
            border-radius: 5px;
            text-align: center;
            position: fixed;
            left: 50%;
            top: 50%;
            margin-left: -256px;
            margin-top: -20%;
            overflow: hidden;
            display: none;
            z-index: 99999;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            require(['indexManagerModules'], function (store) {
                store.init();
            });

            //如果宽度小左侧栏样式调整
            if (innerWidth < 1400) {
                $('#page-wrapper').attr("class", "container nav-small");
                $("#mainFrame").height(1700);
            }
        })
        function openTab(obj) {
            if ($('#indextab').tabs('exists', obj.innerText)) {
                $('#indextab').tabs('select', obj.innerText);
            } else {
                $('#indextab').tabs('add', {
                    title: obj.innerText,
                    href: $(obj).attr("tabhref"),
                    closable: true,
                    bodyCls: 'content-doc',
                    onLoad: function (panel) {
                        var selecttab = $('#indextab').tabs('getSelected');
                        var module = selecttab.find(".inmodule").val();
                        if (module) {
                            require([selecttab.find(".inmodule").val()], function (module) {
                                module.init(selecttab);
                            });
                        } else {
                            if (typeof(module) == "undefined") {
                                window.location.href = "/";
                            }
                        }
                    }
                });
            }
        }

    </script>

    <script src="js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="/js/ckeditor/config.js?t=G4CD"></script>
    <link rel="stylesheet" type="text/css" href="js/ckeditor/skins/moono/editor.css?t=G4CD">
    <script type="text/javascript" src="js/ckeditor/lang/zh-cn.js?t=G4CD"></script>
    <script type="text/javascript" src="/js/ckeditor/styles.js?t=G4CD"></script>
    <script type="text/javascript" src="/js/ckeditor/plugins/lineutils/plugin.js?t=G4CD"></script>
    <script type="text/javascript" src="/js/ckeditor/plugins/widget/plugin.js?t=G4CD"></script>
    <script type="text/javascript" src="/js/ckeditor/plugins/codesnippet/plugin.js?t=G4CD"></script>
    <script type="text/javascript" src="js/ckeditor/plugins/codesnippet/lang/zh-cn.js?t=G4CD"></script>
    <script type="text/javascript" src="js/ckeditor/plugins/widget/lang/zh-cn.js?t=G4CD"></script>
    <script type="text/javascript" src="/js/ckeditor/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
</head>
<body style="background: #2c3e50;">

<jsp:include page="common/header.jsp"></jsp:include>
<div id="page-wrapper" class="container">
    <jsp:include page="common/baseLeft.jsp"></jsp:include>
    <div id="indextab" class="easyui-tabs" style="overflow-x: auto; background-color: #FFF;">
        <div title="首页" style="padding:20px;display:none;">
            <jsp:include page="common/indexContent.jsp"></jsp:include>
        </div>
    </div>
    <jsp:include page="common/footer.jsp"></jsp:include>
</div>
<jsp:include page="common/tools.jsp"></jsp:include>

<jsp:include page="common/changeManagerPwd.jsp"></jsp:include>

<script src="js/demo-skin-changer.js"></script>
<script src="js/jquery.nanoscroller.min.js"></script>
<script src="js/fullcalendar.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/raphael-min.js"></script>
<script src="js/morris.min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/demo.js"></script>
<script src="js/daterangepicker.js"></script>
<script src="js/jquery-jvectormap-1.2.2.min.js"></script>
<script src="js/jquery-jvectormap-world-merc-en.js"></script>
<script src="js/gdp-data.js"></script>



</body>
</html>
