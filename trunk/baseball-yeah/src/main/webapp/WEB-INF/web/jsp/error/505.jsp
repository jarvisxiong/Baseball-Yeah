<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>爱学派</title>

<link type="image/x-icon" href="favicon.png" rel="shortcut icon" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/axp/index.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/echarts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/china.js"></script>

<script type="text/javascript">
	
</script>

</head>
<body id="error-page">

	<div class="container">
		<div class="row" style="text-align:center">
			<div class="col-xs-12">
				<div id="error-box">
					<div class="row">
						<div class="col-xs-12">
							<div id="error-box-inner">
								<img src="<%=request.getContextPath()%>/img/error-500-v1.png" alt="Error 500" />
							</div>
							<h1>ERROR 500</h1>
							<p>非常抱歉，出现错误。</p>
							<p>${errMsg}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>


	<script src="js/scripts.js"></script>

</body>
</html>
