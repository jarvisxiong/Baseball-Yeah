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
    <link rel="stylesheet" href="css/libs/fullcalendar.css" type="text/css"/>
    <link rel="stylesheet" href="css/libs/fullcalendar.print.css"
          type="text/css" media="print"/>
    <link rel="stylesheet" href="css/compiled/calendar.css" type="text/css"
          media="screen"/>
    <link rel="stylesheet" href="css/libs/morris.css" type="text/css"/>
    <link rel="stylesheet" href="css/libs/daterangepicker.css"
          type="text/css"/>
    <link rel="stylesheet" href="css/libs/jquery-jvectormap-1.2.2.css"
          type="text/css"/>
    <link type="image/x-icon" href="favicon.png" rel="shortcut icon"/>


    <script src="js/scripts.js"></script>

    <script src="<%=request.getContextPath()%>/js/validator/bootstrapValidator.min.js"></script>

    <script src="<%=request.getContextPath()%>/js/require.js"
            data-main="<%=request.getContextPath()%>/js/modules/main"></script>
    <%--<link--%>
            <%--href='http://fonts.useso.com/css?family=Open+Sans:400,600,700,300|Titillium+Web:200,300,400'--%>
            <%--rel='stylesheet' type='text/css'>--%>
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
            $(function () {
                require(['indexManagerModules'], function (store) {
                    store.init();
                });
            });
        });
    </script>

</head>
<body style="background: #2c3e50;">


<jsp:include page="common/header.jsp"></jsp:include>
<div id="page-wrapper" class="container">
    <jsp:include page="common/baseLeft.jsp"></jsp:include>
    <iframe name="mainFrame" id="mainFrame" frameborder="0"
            src="<%=request.getContextPath()%>/user/gotoIndex" scrolling="no"
            style="margin: 0 auto; width: 100%; height: 1500px;"></iframe>
    <jsp:include page="common/footer.jsp"></jsp:include>
</div>
<jsp:include page="common/tools.jsp"></jsp:include>

<jsp:include page="common/changeManagerPwd.jsp"></jsp:include>

<script src="js/demo-skin-changer.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.nanoscroller.min.js"></script>
<script src="js/demo.js"></script>

<script src="js/jquery-ui.custom.min.js"></script>
<script src="js/fullcalendar.min.js"></script>
<script src="js/jquery.slimscroll.min.js"></script>
<script src="js/raphael-min.js"></script>
<script src="js/morris.min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/daterangepicker.js"></script>
<script src="js/jquery-jvectormap-1.2.2.min.js"></script>
<script src="js/jquery-jvectormap-world-merc-en.js"></script>
<script src="js/gdp-data.js"></script>
<script src="js/flot/jquery.flot.js"></script>
<script src="js/flot/jquery.flot.min.js"></script>
<script src="js/flot/jquery.flot.pie.min.js"></script>
<script src="js/flot/jquery.flot.stack.min.js"></script>
<script src="js/flot/jquery.flot.resize.min.js"></script>
<script src="js/flot/jquery.flot.time.min.js"></script>
<script src="js/flot/jquery.flot.threshold.js"></script>


<script type="application/javascript">
    function isLoginOut() {
        $.post("/user/isLoginOut", function (data) {
            if (data.success == -1) {
                window.clearInterval(intervalId);//停止循环
                swal({
                    title: "会话已过期",
                    text: "",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonText: "确定",
                    closeOnCancel: false
                }, function (isConfirm) {
                    window.location.href = "/";
                });
            }
        });
    }
    var intervalId = setInterval("isLoginOut()", 6000);
</script>
<script type="text/javascript">
    $(function () {
        //如果宽度小左侧栏样式调整
        if (innerWidth < 1400) {
            $('#page-wrapper').attr("class", "container nav-small");
            $("#mainFrame").height(1700);
        }
    });
</script>

</body>
</html>
