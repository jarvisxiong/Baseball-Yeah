<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<link
	href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css" rel="stylesheet">

<html>
<head>
<title>支付宝支付</title>
</head>
<body>
	<div style="display: none" id="data">${data}</div>
	<div style="display: none" id="message">${message}</div>
	<div style="display: none" id="success">${success}</div>

</body>
    <script
    src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.js"></script>
<script type="text/javascript"
    src="<%=request.getContextPath()%>/js/jquery.js"></script>

<script type="text/javascript">
    $(function(){
        if($("#success").html() < 0){ //失败情况
            sweetAlert(
                    "糟了...",
                    $("#message").html(),
                    "warning");
        }else{
            function submit() {
                document.forms['payForm'].submit();
            }
            submit();
        }
    })


</script>
</html>
