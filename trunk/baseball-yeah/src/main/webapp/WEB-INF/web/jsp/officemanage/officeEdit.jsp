<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
    <div class="row">
	<div class="col-lg-12">
		<div class="main-box clearfix">
			<div class="main-box-body clearfix">
				<div class="row">
					<input type="hidden" value="${userId}" id="userId" />
					<input type="hidden" value="${office}" id="office" />
					<input type="hidden" value="${college}" id="college" />
					<table class="table" id="info_table">
						<tr>
							<td>登录账号：</td>
							<td id="t_username"></td>
							<td rowspan="7" colspan="2">
								<div class="col-lg-7 div-operation ">
									<div id="icon" class="mydivdrop">
									   <img alt="未查询到头像信息"id="t_icon">
									</div>
									<br> 账号头像
								</div>
							</td>
						</tr>
						<tr>
							<td>账号状态：</td>
							<td id="t_beEnabled"></td>
						</tr>
						<tr>
							<td>职务审核状态：</td>
							<td id="t_auditState"></td>
						</tr>
						<tr>
							<td>姓名：</td>
							<td id="t_realName"></td>
						</tr>
						<tr>
							<td>身份证号：</td>
							<td id="t_idNo"></td>
						</tr>
						<tr>
							<td>性别：</td>
							<td id="t_sex"></td>
						</tr>
						<tr>
							<td>账户余额：</td>
							<td id="t_balance"></td>
						</tr>
						<tr>
							<td>订单量：</td>
							<td id="t_orderNum"></td>
						</tr>
						<tr>
							<td>好评率：</td>
							<td id="t_commentPercent"></td>
						</tr>
						<tr>
							<td>城市：</td>
							<td id="t_city"></td>
						</tr>
						<tr>
							<td>校区：</td>
							<td id="t_collegeArea"></td>
						</tr>
						<tr>
							<td>宿舍：</td>
							<td id="t_dormitoryAddress"></td>
							<td>手持身份证正面照：</td>
							<td>
							<div style="margin-top: 8;color: #2980b9;">
							<i class=" fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="img1">点击查看 </strong></div></td>
						</tr>
						<tr>
							<td>审核时间：</td>
							<td id="t_auditTime"></td>
							<td>身份证正面照：</td>
							<td><div style="margin-top: 8;color: #2980b9;"><i
                                class=" fa fa-file-image-o fa-fw fa-lg"></i><strong style="cursor: pointer;" id="img2">点击查看 </strong></div></td>
						</tr>
					</table>
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
                                    <h4 class="panel-title">下属小派
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="pTable" class="table">
                            </table>
                        </div>
                    </div>
                    <div class="pull-right" style="padding-right: 20px;" id="buttonDiv">
	                    <button id="btn_p_add" type="button"class="btn btn-default" style="width:92px;">
	                          <span class="glyphicon glyphicon-plus"aria-hidden="true"></span>增加小派
	                    </button>
	                    <button id="btn_p_del" type="button"class="btn btn-default" style="width:92px;">
	                          <span class="glyphicon glyphicon-remove"aria-hidden="true"></span>删除
	                    </button>
                    </div>
                </div>
            </div>
        </div>
        
         <div class="row" id="storeDiv">
            <div class="col-lg-12">
                <div class="main-box clearfix">
                    <header class="main-box-header clearfix">
                        <div class="panel-group" style="margin-bottom: 0px;">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">负责商户
                                    </h4>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div class="main-box-body clearfix">
                        <div class="row">
                            <table id="sTable" class="table">
                            </table>
                        </div>
                    </div>
                </div>
                <div class="pull-right" style="padding-right: 20px;">
                <button id="btn_s_add" type="button"class="btn btn-default" style="width:92px;">
                          <span class="glyphicon glyphicon-plus"aria-hidden="true"></span>增加商户
                    </button>
                <button id="btn_s_del" type="button"class="btn btn-default" style="width:92px;">
                      <span class="glyphicon glyphicon-remove"aria-hidden="true"></span>删除
                </button>
                </div>
            </div>
        </div>
    </div>
