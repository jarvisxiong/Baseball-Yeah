<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<input type="hidden" class="inmodule" value="officeManagerModules">
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
                                           data-parent="#accordion" href="#officeCollapse"> 查询
                                        </a>
                                    </h4>
                                </div>
                                <div id="officeCollapse" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                     <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="" class="control-label col-xs-2">登录帐号：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="username" style="width: 240px"
                                                               class="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="form-group col-xs-6">

                                                    <label for="" class="control-label col-xs-2">姓名:</label>
                                                   <div class="col-xs-10">
                                                        <input type="text" id="realName" style="width: 240px"
                                                               class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="" class="control-label col-xs-2">性别:</label>
                                                    <div class="col-xs-10">
                                                        <select id='sex' style="width: 240px"
                                                                class="form-control">
                                                            <option value="">请选择</option>
                                                            <option value="p_gender_male">男</option>
                                                            <option value="p_gender_female">女</option>
                                                            <option value="p_gender_secret">保密</option>
                                                        </select>

                                                    </div>
                                                </div>
                                                
                                                <div class="form-group col-xs-6">
                                                    <label for="" class="control-label col-xs-2">校区：</label>
                                                    <div class="col-xs-10">
                                                        <select id="collegeArea" class="js-example-data-array"
                                                        style="width: 240px" name="collegeArea">
                                                        </select>       
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <label for="" class="control-label col-xs-2">职务:</label>
                                                    <div class="col-xs-10">
                                                        <select id='office' style="width: 240px"
                                                                class="form-control">
                                                            <option value="">请选择</option>
                                                            <option value="2">校园COO</option>
                                                            <option value="3">校园CEO</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group col-xs-6">
                                                    <label for="" class="control-label col-xs-2">审核人：</label>
                                                    <div class="col-xs-10">
                                                        <input type="text" id="auditor" style="width: 240px"
                                                               class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row"> 

                                                <div class="form-group col-xs-6">
                                                    <div class="input-daterange">
                                                        <label class="control-label col-xs-2" for="startDate">注册时间:</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="starttimePicker">
                                                                <input type="text" class="form-control" id="startDate"
                                                                       placeholder="开始日期" name="start" ></input><span
                                                                    class="input-group-addon"><span
                                                                    class="glyphicon-calendar glyphicon"></span> </span>
                                                            </div>
                                                        </div>
                                                        <label class="control-label col-xs-1" for="endDate">至</label>
                                                        <div class="col-xs-4">
                                                            <div class="input-group date" id="endtimePicker">
                                                                <input type="text" class="form-control" name="end"
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
                        </div>
                    </header>
                    <div id="toolbar" class="btn-group">
                        <shiro:hasPermission name="194_QUERY">
                            <button type="button" id="btn_query" class="btn btn-default">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                            </button>
                             <button type="button" id="btn_enable"
                                                class="btn btn-default">
														<span class="glyphicon glyphicon-ok-circle"
                                                              aria-hidden="true"></span>激活
                                        </button>
                             <button type="button" id="btn_frozen"
                                                class="btn btn-default">
														<span class=" glyphicon glyphicon-remove-sign"
                                                              aria-hidden="true"></span>封停
                                        </button>
                            
                             <button id="btn_add_ceo" type="button" class="btn btn-default" onclick="openTab(this)"
                             tabhref="<%=request.getContextPath()%>/office/assist/toAddCeo" tabmodule="">
								<span class="glyphicon glyphicon-plus"
                                                              aria-hidden="true"></span>新增CEO
                             </button>
                             <button id="btn_add_coo" tabhref="<%=request.getContextPath()%>/office/assist/toAddCoo" tabmodule="" type="button" class="btn btn-default"  onclick="openTab(this)">
									<span class="glyphicon glyphicon-plus"
                                                              aria-hidden="true"></span>新增COO
                               </a>
                             </button>  
                             <button id="btn_office_edit" type="button" class="btn btn-default">
														<span class="glyphicon glyphicon-pencil"
                                                              aria-hidden="true"></span>职务关系维护
                             </button>
						     <button id="btn_lock" type="button"
                                    class="btn btn-default">
													<span class="glyphicon glyphicon-remove-circle"
                                                          aria-hidden="true"></span>解除职务
                             </button>
							</shiro:hasPermission>
                    </div>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="officeTable" class="table">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="./../crowdsource/smallPieDetail.jsp"></jsp:include>
<jsp:include page="./../crowdsource/collegeDetail.jsp"></jsp:include>
<jsp:include page="partInfo.jsp"></jsp:include>