<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <input type="hidden" class="inmodule" value="voucherReportModules" /> -->
<input type="hidden" class="inmodule" value="officemanageAddCeoModules" />   
<input type="hidden" id="flag" value="${flag }"/>  
    <div class="row">
        <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                      
                        <div class="panel-group" style="margin-bottom: 0px;"
                             id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="a-clear-search" id="clearSearch">
                                            <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
                                        </a>
                                        <a class="accordion-toggle a-search" data-toggle="collapse"
                                           data-parent="#accordion" href="#walletAccountManagercollapse"> 查询
                                        </a>
                                    </h4>
                                </div>
                                <div id="walletAccountManagercollapse" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                     <div class="row">  
                                                <div class="form-group col-xs-6">
                                                    <label for="phone" class="control-label col-xs-2">登录帐号：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="phone" style="width: 240px"
                                                               class="form-control" "/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">

                                                    <label for="realName" class="control-label col-xs-2">姓名:</label>
                                                   <div class="col-xs-10">
                                                        <input type="text" id="realName" style="width: 240px"
                                                               class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>   
                                            <div class="row"> 
                                                <div class="form-group col-xs-6">
                                                    <label for="gender" class="control-label col-xs-2">性别:</label>
                                                    <div class="col-xs-10">
                                                        <select id='gender' style="width: 240px"
                                                                class="form-control" placeholder="性别">
                                                            <option value="">请选择</option>
                                                            <option value="p_gender_male">男</option>
                                                            <option value="p_gender_female">女</option>
                                                            <option value="p_gender_secret">保密</option>
                                                        </select>
                                                    </div>
                                                </div>  
                                                <div class="form-group col-xs-6">
														<label for="collegeId" class="col-xs-2">校区：</label>
														<div class="col-xs-10">
															<select id="collegeId" class="js-example-data-array" style='width: 250px'>
															</select>
														</div>
													</div>
                                            </div> 

                                            <div class="row">   
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate">注册时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker1">
                                                                <input type="text" class="form-control" id="startDate"
                                                                       placeholder="开始日期" name="startdate" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker1">
                                                                <input type="text" class="form-control" name="endDate"
                                                                       id="endDate" placeholder="结束日期" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div class="btn-group">  
                            <button type="button" id="btn_query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
<!-- 								<button id="btn_reset" type="button" class="btn btn-default"> -->
<!-- 									<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>清空 -->
<!-- 								</button>  -->
<!--                             </button>    -->
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="acctTable" class="table">

                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
	                    <button type="button" id="btn_add" class="btn btn-default" data-dismiss="modal">
	                        <span class="glyphicon" aria-hidden="true"></span>确定
	                    </button>
<!-- 	                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btn_back"> -->
<!-- 	                        <span class="glyphicon" aria-hidden="true"></span>返回 -->
<!-- 	                    </button> -->
                	</div>                    
                </div>
            </div>
        </div>
    </div>
<jsp:include page="./../order/packetOrder/packetOrderDetail.jsp"></jsp:include>
<jsp:include page="./../crowdsource/collegeDetail.jsp"></jsp:include>
<jsp:include page="./../crowdsource/smallPieDetail.jsp"></jsp:include>