<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <input type="hidden" class="inmodule" value="voucherReportModules" /> -->
<input type="hidden" class="inmodule" value="keywordModules" />  
<!--     <script type="text/javascript" -->
<%--             src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script> --%>
<!--     <script type="text/javascript" -->
<%--             src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script> --%>
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
                                                    <label for="wordContent" class="control-label col-xs-2">关键字：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="wordContent" style="width: 240px"
                                                               class="form-control" "/>
                                                    </div>
                                                </div> 
                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate">创建时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker">
                                                                <input type="text" class="form-control" id="startDate"
                                                                       placeholder="开始日期" name="startdate" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker">
                                                                <input type="text" class="form-control" name="endDate"
                                                                       id="endDate" placeholder="结束日期" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                      </div>    
                                      <div class="row">   
                                                <div class="form-group col-xs-6">
                                                    <label for="realName" class="control-label col-xs-2">创建人：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="realName" style="width: 240px"
                                                               class="form-control" "/>
                                                    </div>
                                                </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div class="btn-group"> 
                    	<shiro:hasPermission name="170_ADD">
                           <button id="btn_add" type="button" class="btn btn-default">
                               <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                           </button>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="170_DELETE">
                           <button id="btn_delete" type="button" class="btn btn-default">
                              <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                           </button>
                        </shiro:hasPermission>
                            <button type="button" id="btn_query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
<!--                             <button type="button" id="cover" class="btn btn-default mydivdrop" action="/manage/focuspic/uploadFile"> -->
<!--                                 <span class="glyphicon glyphicon-search" aria-hidden="true"></span>导入 -->
<!--                             </button> -->
<!--                             <form action="" method="post" enctype="application/x-www-form-urlencoded"> -->
<!--                             	<input type="file" id="wj" name="wj"/> -->
<!--                             </form> -->
<!--                             	<div id="cover" class="mydivdrop" action="/manage/focuspic/uploadFile" -->
<!--                                      style=" margin-bottom: 10px; height: 10px;"> -->
<!--                                 </div> -->
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="acctTable" class="table">

                            </table>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    </div> 
<jsp:include page="addKeyword.jsp"></jsp:include>