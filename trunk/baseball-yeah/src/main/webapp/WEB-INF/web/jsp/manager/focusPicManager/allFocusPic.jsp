<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派</title>

    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/select2.min.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/css/libs/bootstrap-table.min.css">
    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <%--<link href="<%=request.getContextPath()%>/plugs/magnific-popup/magnific-popup.css"  rel="stylesheet">--%>
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
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/plugs/dropzone/dropzone.min.css">
    <script
            src="<%=request.getContextPath()%>/plugs/dropzone/dropzone.js"></script>
    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
    <script type="text/javascript">
        //        Dropzone.options.myId = {
        //            paramName: "file", // The name that will be used to transfer the file
        //            maxFiles:1,
        //            dictDefaultMessage:"将文件拖至此处或点击上传",
        //            accept: function(file, done) {
        //                if (file.name == "justinbieber.jpg") {
        //                    done("Naha, you don't.");
        //                }
        //                else { done(); }
        //            }
        //        };
        $(function () {
            require(['focusPicModules'], function (pic) {
                pic.init();
            });
        });
    </script>

</head>
<body>
<div id="page-wrapper" class="container">

    <div class="row">

        <form action="">
            <div class="row">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="main-box clearfix">
                            <div class="main-box-body clearfix">
                                <header class="main-box-header clearfix">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <ol class="breadcrumb">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/user/gotoIndex">首页</a></li>
                                                <li class="active"><span>基础设置</span></li>
                                            </ol>
                                            <h1>焦点图管理</h1>
                                        </div>
                                    </div>
                                    <div class="panel-group" style="margin-bottom: 0px;"
                                         id="accordion">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title">
                                                    <a class="a-clear-search" id="clearSearch">
                                                        <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                                    </a>
                                                    <a class="accordion-toggle a-search" data-toggle="collapse"
                                                       data-parent="#accordion" href="#collapseThree"> 查询条件
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseThree" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <form id="formSearch" class="form-horizontal" role="form">
                                                        <div class="row">
                                                            <div class="form-group col-xs-6">
                                                                <label for="title" class="control-label col-xs-2">标题:</label>
                                                                <div class="col-xs-10">
                                                                    <input type="text" class="form-control" id="title"
                                                                           style="width: 240px">
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-xs-6">
                                                                <label for="adType" class="control-label col-xs-2">广告类型:</label>
                                                                <div class="col-xs-10">
                                                                    <select id="adType" style="width: 240px" class="form-control" placeholder="广告类型">
                                                                        <option value="">全部</option>
                                                                        <option value="01">首页图片</option>
                                                                        <option value="02">其他</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">

                                                            <div class="form-group col-xs-6">
                                                                <label for="userType" class="control-label col-xs-2">用户类型:</label>
                                                                <div class="col-xs-10">
                                                                    <select id="userType" style="width: 240px" class="form-control" placeholder="用户类型">
                                                                        <option value="">全部</option>
                                                                        <option value="01">货源</option>
                                                                        <option value="02">门店</option>
                                                                        <option value="03">众包</option>
                                                                        <option value="04">收件人</option>
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

                                <div class="table-responsive">

                                    <div class="panel-body" style="padding-bottom: 0px;">


                                        <div id="toolbar" class="btn-group">
                                            <shiro:hasPermission name="156_ADD">
                                                <button id="btn_add" type="button" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="156_DELETE">
                                                <button id="btn_delete" type="button" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="156_QUERY">
                                                <button id="btn_query" type="button" class="btn btn-default">
                                                   	<span class="glyphicon glyphicon-search"
                                                          aria-hidden="true"></span>查询
                                                </button>
                                            </shiro:hasPermission>
                                        </div>
                                        <table id="picTable" class="table">

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="addFocusPic.jsp"></jsp:include>
</body>
</html>
