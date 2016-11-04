<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="row">
	<input type="hidden" value="${userId}" id="pu_userId" />
    <input type="hidden" value="${office}" id="pu_office" />
    <input type="hidden" value="${college}" id="pu_college" />
		<div class="col-lg-12">
			<div class="main-box clearfix">
				<header class="main-box-header clearfix">
					<div class="panel-group" style="margin-bottom: 0px;" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a class="a-clear-search" id="pu_clearSearch"> <span
										class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
									</a> <a class="accordion-toggle a-search" data-toggle="collapse"
										data-parent="#accordion" href="#puserAddCollapse"> 查询 </a>
								</h4>
							</div>
							<div id="puserAddCollapse" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="row">
										<div class="form-group col-xs-6">
											<label for="pu_username" class="control-label col-xs-2">登录帐号：</label>
											<div class="col-xs-10">
												<input type="text" id="pu_username" style="width: 240px"
													class="form-control" />
											</div>
										</div>
										<div class="form-group col-xs-6">

											<label for="pu_realName" class="control-label col-xs-2">姓名:</label>
											<div class="col-xs-10">
												<input type="text" id="pu_realName" style="width: 240px"
													class="form-control" />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label for="pu_sex" class="control-label col-xs-2">性别:</label>
											<div class="col-xs-10">
												<select id='pu_sex' style="width: 240px" class="form-control">
													<option value="">请选择</option>
													<option value="p_gender_male">男</option>
													<option value="p_gender_female">女</option>
													<option value="p_gender_secret">保密</option>
												</select>

											</div>
										</div>
                                        <div class="form-group col-xs-6">
                                            <div class="input-daterange">
                                                <label class="control-label col-xs-2" for="pu_startDate">注册时间:</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="pu_starttimePicker">
                                                        <input type="text" class="form-control" id="pu_startDate"
                                                            placeholder="开始日期" name="start"></input><span
                                                            class="input-group-addon"><span
                                                            class="glyphicon-calendar glyphicon"></span> </span>
                                                    </div>
                                                </div>
                                                <label class="control-label col-xs-1" for="pu_endDate">至</label>
                                                <div class="col-xs-4">
                                                    <div class="input-group date" id="pu_endtimePicker">
                                                        <input type="text" class="form-control" name="end"
                                                            id="pu_endDate" placeholder="结束日期"></input><span
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
				<div id="pu_toolbar" class="btn-group">
					<button type="button" id="btn_pu_query" class="btn btn-default">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
					</button>
				</div>
				<div class="main-box-body clearfix">
					<div class="row">
						<table id="puserAddTable" class="table">
						</table>
					</div>
				</div>

			</div>
			<div class="pull-right" style="padding-right: 20px;">
				<button id="btn_pu_done" type="button" class="btn btn-default"
					style="width: 92px;">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>完成
				</button>
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
								<h4 class="panel-title">已选择小派</h4>
							</div>
						</div>
					</div>
				</header>
				<div class="main-box-body clearfix">
					<div class="row">
						<table id="puserSelectedTable" class="table">
						</table>
					</div>
				</div>
			</div>
			<div class="pull-right" style="padding-right: 20px;">
				<button id="btn_pu_del" type="button" class="btn btn-default"
					style="width: 92px;">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
				</button>
			</div>
		</div>
	</div>
</div>
