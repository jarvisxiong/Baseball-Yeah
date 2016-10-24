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

        $(function () {
            require(['companyOfferPriceModules'], function (pic) {
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
                                            <h1>快递公司报价管理</h1>
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
                                                                <label for="offerName" class="control-label col-xs-2">报价名称:</label>
                                                                <div class="col-xs-10">
                                                                    <input type="text" class="form-control" id="offerName"
                                                                           style="width: 240px">
                                                                </div>
                                                            </div>

                                                            <div class="form-group col-xs-6">
                                                                <label for="selSendArea" class="control-label col-xs-2">寄件人区域:</label>
                                                                <div class="col-xs-10">
                                                                    <select id='selSendArea' style="width: 240px"
                                                                            class="js-example-data-array form-control">
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="form-group col-xs-6">
                                                                <label for="selDespArea" class="control-label col-xs-2">收件人区域:</label>
                                                                <div class="col-xs-10">
                                                                    <select id='selDespArea' style="width: 240px"
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

                                <div class="table-responsive">

                                    <div class="panel-body" style="padding-bottom: 0px;">


                                        <div id="toolbar" class="btn-group">
                                            <shiro:hasPermission name="159_ADD">
                                                <button id="btn_add" type="button" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="159_EDIT">
                                                <button id="btn_edit" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-pencil"
                                                              aria-hidden="true"></span>修改
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="159_DELETE">
                                                <button id="btn_delete" type="button" class="btn btn-default">
                                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                                                </button>
                                            </shiro:hasPermission>
                                            <button id="btn_copy" type="button" class="btn btn-default">
                                                   	<span class="glyphicon glyphicon-magnet"
                                                          aria-hidden="true"></span>复制
                                            </button>
                                            <shiro:hasPermission name="159_QUERY">
                                                <button id="btn_query" type="button" class="btn btn-default">
                                                   	<span class="glyphicon glyphicon-search"
                                                          aria-hidden="true"></span>查询
                                                </button>
                                            </shiro:hasPermission>
                                        </div>
                                        <table id="offerTable" class="table">

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

<jsp:include page="addCompanyOfferPrice.jsp"></jsp:include>
<jsp:include page="editCompantOfferPrice.jsp"></jsp:include>
<jsp:include page="copyCompanyOfferPrice.jsp"></jsp:include>
</body>
</html>
