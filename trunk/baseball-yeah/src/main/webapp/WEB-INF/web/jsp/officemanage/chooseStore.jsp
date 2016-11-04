<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" class="inmodule" value="officemanageChooseModules">
<input type="hidden" id="userId" value="${userId }"/>
<input type="hidden" id="flag" value="${flag }"/>
    <div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="main-box-body clearfix">
				<div class="row"> 
			 
				</div>
			</div>
		</div>
	</div>

	<div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                        <div class="panel-group" style="margin-bottom: 0px;">
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
                                                    <label for="storeName" class="control-label col-xs-2">商户姓名：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="storeName" style="width: 240px"
                                                               class="form-control" "/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">

                                                    <label for="phone" class="control-label col-xs-2">商户手机:</label>
                                                   <div class="col-xs-10">
                                                        <input type="text" id="phone" style="width: 240px"
                                                               class="form-control"/>
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
                    <div class="pull-right" style="padding-right: 20px;">
	                	<c:if test="${flag == 1 }">
		                    <button id="btn_p_sure" type="button"class="btn btn-default" style="width:92px;">
		                          <span class="glyphicon glyphicon-plus"aria-hidden="true"></span>确定
		                    </button>
	                    </c:if> 
                    </div>
                </div>
            </div>
        </div>
        
         <div class="row">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                        <div class="panel-group" style="margin-bottom: 0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">已选择商户
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id=storeTable class="table">
                            </table>
                        </div>
                    </div>
                </div>
                <div class="pull-right" style="padding-right: 20px;"> 
                <c:if test="${flag == 1 }">
	                <button id="btn_coo_del" type="button"class="btn btn-default" style="width:92px;">
	                      <span class="glyphicon glyphicon-remove"aria-hidden="true"></span>删除
	                </button>
                </c:if>
                </div>
            </div>
        </div>
    </div>
