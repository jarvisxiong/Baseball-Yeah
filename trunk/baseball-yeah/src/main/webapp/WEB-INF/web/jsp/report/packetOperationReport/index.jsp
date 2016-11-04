<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head> 
	<style type="text/css"> 
	.col-lg-3{width:16%} 
	</style>
</head> 
<%-- <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/echart/echarts.min.js"></script>
 <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/echart/theme/macarons.js"></script> --%>

<input type="hidden" class="inmodule" value="indexOperationModules">
	<div class="main-box clearfix">
	    <div class="main-box-body clearfix">
	        <div class="row">
	            <div class="col-lg-12">
	                <ol class="breadcrumb">
	                    <li><a href="#"></a></li>
	                </ol>
	            </div>
	        </div> 
	        <div class="row">
	            <div class="col-lg-3 col-sm-6 col-xs-12">
	                <div class="main-box infographic-box" >
	                    <i  class="fa" style="background-image:url(img/report/total_puser.png)" ></i> 
	                    <a href="#"  id='deliverCount'> <span class="headline">小派数量</span></a>
	                    <span id="userNumbers" class="value">${data.puserNum}</span>
	                </div>
	            </div>
	            <div class="col-lg-3 col-sm-6 col-xs-12" style = "width:18%">
					<div class="main-box infographic-box">
						<i class="fa" style="background-image:url(img/report/active_puser.png)"></i> <a href="#" id='activityRateManager'><span
							class="headline">活跃小派(活跃度)</span></a> <span id="todayActiveRate"
																		class="value">${data.activePuserNum}(${data.activeDegree})</span>
					</div>
	            </div>
	            <div class="col-lg-3 col-sm-6 col-xs-12" style = "width:18%">
	                <div class="main-box infographic-box">
	                    <i class="fa" style="background-image:url(img/report/incr_puser.png)"></i> <span class="headline">新增小派(增长率)</span>
	                    <span id="todaySMS" class="value">${data.puserIncNum}(${data.puserIncRate})</span>
	                </div>
	            </div>
	            <div class="col-lg-3 col-sm-6 col-xs-12">
	                <div class="main-box infographic-box">
	                    <i class="fa"  style="background-image:url(img/report/incr_capacity.png)"></i> <a href="#"  id='transportCount'>  <span class="headline">运力新增</span></a>
	                    <span id="todayNewPhoneTotal" class="value">${data.shippingAbilityIncNum}(${data.shippingIncRate})</span>
	                </div>
	            </div>
	             <div class="col-lg-3 col-sm-6 col-xs-12">
	                <div class="main-box infographic-box">
	                    <i class="fa"  style="background-image:url(img/report/receive_order.png)"></i> <a href="#" id='receiveOrderRateManager'><span class="headline">接单率</span></a>
	                    <span id="todayNewPhoneTotal" class="value">${data.recOrderRate}</span>
	                </div>
	            </div>
				<div class="col-lg-3 col-sm-6 col-xs-12">
					<div class="main-box infographic-box">
						<i class="fa" style="background-image:url(img/report/good_comment.png)" ></i> <a href="#" id='commentRateManager'><span class="headline">小派好评率</span></a>
						<span id="todayCommentRate" class="value">${data.commentGoodRate}</span>
					</div>
				</div>
	        </div>
	
	       
	
	        <div class="row" style="margin-top: 20px;">
	             <div id="userTotalCount" style="height:300px;">
	   		 	</div>
	     	</div>
			 <div class="row" style="margin-top: 20px;">
				 <div  class="col-lg-3 col-sm-6 col-xs-12" style="width:33.3%">
				 	 <div class="main-box infographic-box">
		                <div  id="userIncrCount" style="height:400px;">
						</div>
					</div>
				 </div>
				 <div  class="col-lg-3 col-sm-6 col-xs-12" style="width:33.3%">
				 	 <div class="main-box infographic-box">
		                <div  id="capacityIncrCount" style="height:400px;">
						</div>
					</div>
				 </div>
				 <div  class="col-lg-3 col-sm-6 col-xs-12" style="width:33.4%">
				 	 <div class="main-box infographic-box">
		                <div  id="receiveOrder" style="height:400px;">
						</div>
					</div>
				 </div>
              </div>
			
			 
	    </div>
	</div>
	
	

<script type="text/javascript">

</script>