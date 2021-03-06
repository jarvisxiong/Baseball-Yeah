define(
		[ 'base','packetStuManagerModules' ],
		function(base,packet) {
			/**
			 * 私有成员定义区域
			 */
			
			var packetinit=function (){
				
				packet.init();
			}

			return {
				init : function(panel) {
					// / <summary>
					// / 模块初始化方法
					// / </summary>
					// / <param name="args">初始化时传入的参数</param>
					var self = this;
					var date = new Date();
					// 开始时间
					$('#createStartDatePicker',panel).datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						startView : 2,
						minView : 0,
						todayHighlight : true,
						endDate : new Date(date.getFullYear(), date.getMonth(),
								date.getDate(), 23, 59, 59)
					}).on('changeDate',function(e) {
						var startTime = e.date;
						$('#createEndDatePicker',panel).datetimepicker('setStartDate',startTime);
					});

					$('#createEndDatePicker',panel).datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						startView : 2,
						minView : 0,
						todayHighlight : true,
						endDate : new Date(date.getFullYear(), date.getMonth(),
								date.getDate(), 23, 59, 59)
					}).on('changeDate',function(e) {
						var startTime = e.date;
						$('#createStartDatePicker',panel).datetimepicker('setEndDate',startTime);
					});
					
					// 结束时间
					$('#deliveryStartDatePicker',panel).datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						startView : 2,
						minView : 0,
						todayHighlight : true,
						endDate : new Date(date.getFullYear(), date.getMonth(),
								date.getDate(), 23, 59, 59)
					}).on('changeDate',function(e) {
						var startTime = e.date;
						$('#deliveryEndDatePicker',panel).datetimepicker('setStartDate',startTime);
					});
					
					// 结束时间
					$('#deliveryEndDatePicker',panel).datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						startView : 2,
						minView : 0,
						todayHighlight : true,
						endDate : new Date(date.getFullYear(), date.getMonth(),
								date.getDate(), 23, 59, 59)
					}).on('changeDate',function(e) {
						var startTime = e.date;
						$('#deliveryStartDatePicker',panel).datetimepicker('setEndDate',startTime);
					});

					// 结束时间
					$('#deliverySStartDatePicker',panel).datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						startView : 2,
						minView : 0,
						todayHighlight : true,
						endDate : new Date(date.getFullYear(), date.getMonth(),
								date.getDate(), 23, 59, 59)
					}).on('changeDate',function(e) {
						var startTime = e.date;
						$('#deliverySEndDatePicker',panel).datetimepicker('setStartDate',startTime);
					});
					
					// 结束时间
					$('#deliverySEndDatePicker',panel).datetimepicker({
						format : 'yyyy-mm-dd hh:ii:ss',
						autoclose : true,
						startView : 2,
						minView : 0,
						todayHighlight : true,
						endDate : new Date(date.getFullYear(), date.getMonth(),
								date.getDate(), 23, 59, 59)
					}).on('changeDate',function(e) {
						var startTime = e.date;
						$('#deliverySStartDatePicker',panel).datetimepicker('setEndDate',startTime);
					});

					base.datagrid(
									{
										url : '/order/user/getAll',
										queryParams : function(params) {
											return $.extend(params, {
												createStartDate : $('#createStartDate',panel)
														.val(),
												createEndDate : $('#createEndDate',panel)
														.val(),
												deliveryStartDate : $(
														'#deliveryStartDate',panel)
														.val(),
												deliveryEndDate : $(
														'#deliveryEndDate',panel)
														.val(),
												deliverySStartDate : $(
														'#deliverySStartDate',panel)
														.val(),
												deliverySEndDate : $(
														'#deliverySEndDate',panel)
														.val(),
												orderId : $.trim($("#orderId",panel)
														.val()),
												state : $.trim($("#state",panel)
														.val()),
												payType : $.trim($("#payType",panel)
														.val()),
												payId : $.trim($("#payId",panel)
														.val()),
												phone : $.trim($("#phone",panel)
														.val()),
												collegeId : $.trim($("#collegeId",panel)
														.val()),
												mobile : $("#mobile",panel).val()
											});
										},
										onLoadSuccess : function(data){
											$('.orderIdDetail',panel).click(function(){
												self.showDetail($.trim($(this).text()),panel);
											});
											$('.phoneUserManager',panel).click(function(){
												var phone = $.trim($(this).text());
												var title = $(this).attr('title');
												var href = '/order/gotoPacketStuManager?phone='+phone;
												base.openTab(title,href,packetinit);
											});
										},
										columns : [
												{
													checkbox : true
												},
												{
													field : 'orderId',
													title : '订单号',
													sortable : true,
													formatter : function(value,
															row, index) {
														return "<a href='#' class='orderIdDetail'>" + value + "</a>";
													},
													width : 400
												},
												{
													field : 'state',
													title : '订单状态',
													formatter : function(value,
															row, index) {
														if (value == "1") {
															return "<a class='label label-primary'>创建</a>";
														} else if (value == "2") {
															return "<a class='label label-success'>已接单</a>";
														} else if (value == "3") {
															return "<a class='label label-info'>配送中</a>";
														} else if (value == "4") {
															return "<a class='label label-info'>处理中</a>";
														} else if (value == "5") {
															return "<a class='label label-success'>完成</a>";
														} else if (value == "6") {
															return "<a class='label label-default'>取消</a>";
														} else if (value == "7") {
															return "<a class='label label-danger'>异常</a>";
														}
													},

													sortable : true,
													width : 400
												},
											{
												field: 'lockState',
												title: '是否锁定',
												formatter: function (value, row, index) {
													switch (value) {
														case 0:
															return "否";
														case 1:
															return "收件人分单锁定";
														case 2:
															return "货源分单锁定";
														case 3:
															return "众包分单锁定";
														case 4:
															return "系统分单锁定";
														case 5:
															return "预支付未付款锁定";
													}
												},
												width: 80
											},
												{
													field : 'payType',
													title : '支付方式',
													sortable : true,
													formatter : function(value,
															row, index) {
														if(value == "1"){
															return "<a class='label label-primary'>微信</a>";
														}else if(value == "2"){
															return "<a class='label label-success' >支付宝</a>";
														}else if(value == "3"){
															return "<a class='label label-info' >指端支付</a>";
														}else{
															return "<a class='label label-default' >未知方式</a>";
														}
													},
													visible : true,
													width : 400
												}, {
													field : 'finalMoney',
													title : '最终金额',
													formatter : function(value,
															row, index) {
														return value==""?"":value+"元";
													},
													align:'center',
													sortable : true,
													width : 400
												}, {
													field : 'payId',
													title : '交易流水',
													formatter : function(value,
															row, index) {
														return value==0?'':value;
													},
													align:'center',
													sortable : true,
													width : 400
												},{
													field : 'fullName',
													title : '学校',
													align:'center',
													sortable : true,
													width :400
												}, {
													field : 'phone',
													title : '众包人手机号码',
													formatter : function(value,row, index) {
														return "<a title='众包用户管理' href='#' class='phoneUserManager'>"+value+"</a>";
													},
													align:'center',
													sortable : true,
													width : 400
												},{
													field : 'realName',
													title : '众包人员姓名',
													align:'center',
													sortable : true,
													width : 400
												},
												{
													field : 'createDate',
													title : '创建日期',
													sortable : true,
													align:'center',
													width : 400
												}, {
													field : 'deliveryStartDate',
													title : '确认取件时间',
													sortable : true,
													align:'center',
													width : 400
												}, {
													field : 'deliveryEndDate',
													title : '签收时间',
													sortable : true,
													align:'center',
													width : 400
												}, {
													field : 'mobile',
													title : '收件人号码',
													align:'center',
													sortable : true,
													width : 400
												},{
													field : 'consignee',
													title : '收件人姓名',
													align:'center',
													sortable : true,
													width : 400
												},{
												field : 'commodityCode',
												title : '代金券id',
												align:'center',
												formatter : function(value,row, index) {
													if (row.type !=null && row.type=="1008"){
														return value;
													}else {
														return "未使用代金券";
													}
												},
												sortable : true,
												width : 400
											},{
												field : 'rebatePrice',
												title : '代金券金额',
												formatter : function(value,row, index) {
													if (row.type !=null && row.type=="1008"){
														return value+"元";
													}else {
														return "";
													}
												},
												align:'center',
												sortable : true,
												width : 400
											}
										/*
										 * { field : 'beEnabled', title :
										 * '是否启用', formatter : function(value,
										 * row, index) { return value == "1" ? "<a
										 * class='label label-success'>启用</a>" : "<a
										 * class='label label-warning' >禁用</a>"; },
										 * sortable : true, width:100 }
										 */
										]
									}, '#userTable',panel);
					
					$.ajax({
						type : "POST",
						url : "/order/user/selectAllPayType",
						dataType : "json",
						success : function(data) {
							$.each(data,function(index,obj){
								if(obj.id == 4){
									data.splice(index,1);
								}
							});
							$("#payType",panel).select2({
								data : data
							});
							 $('#collegeId',panel).select2("val", "");
						}
					});
					
					$.ajax({
						type : "POST",
						url : "/manage/college/getCollageForSel",
						dataType : "json",
						success : function(data) {
							$("#collegeId",panel).select2({
								data : data.data
							});
						}
					});
					
					$("#btn_add",panel).click(function() {
						self.add(panel);
					});
					$("#btn_edit",panel).click(function() {
						self.edit(panel);
					});
					$("#btn_delete",panel).click(function() {
						self.remove(panel);
					});
					$("#btn_initPwd",panel).click(function() {
						self.initPwd(panel);
					});
					$("#btn_query",panel).click(function() {
						//$("#userTable",panel).bootstrapTable('refresh',{"query": {"offset": 0}});
						var orderId = $.trim($("#orderId",panel).val());
						var payId = $.trim($("#payId",panel).val());
						if(orderId && isNaN(orderId)){
							sweetAlert("", "输入的订单号格式不正确", "info");
							return false;
						}
						if(payId && isNaN(payId)){
							sweetAlert("", "输入的支付流水号格式不正确", "info");
							return false;
						}
						$("#userTable", panel).bootstrapTable('selectPage', 1);
					});

					$("#clearSearch",panel).click(function () {
		                base.reset($(".main-box-body",panel));
		                $('#payType',panel).select2("val", "");
		                $('#collegeId',panel).select2("val", "");
		            });
					
					$('#addRole',panel).select2({
						placeholder : '请选择角色'
					});

					$('#addModal',panel).on(
							'shown.bs.modal',
							function() {
								$('#addForm',panel).data('bootstrapValidator')
										.resetForm(true);
							});
				},
				add : function(panel) {
					var self = this;
					$('#addLoginName',panel).val('');
					$('#addUserName',panel).val('');
					$('#addUserCode',panel).val('');
					$('#addPassword',panel).val('');
					$('#addRePassword',panel).val('');
					$('#addDutyId',panel).val('');
					$('.addGender',panel).val('');
					$('#addPhone',panel).val('');
					$('#addAddress',panel).val('');
					$('#addModal',panel).modal({
						keyboard : false,
						backdrop : 'static'
					});
					base
							.validator(
									{
										message : 'This value is not valid',
										feedbackIcons : {
											valid : 'glyphicon glyphicon-ok',
											invalid : 'glyphicon glyphicon-remove',
											validating : 'glyphicon glyphicon-refresh'
										},
										fields : {
											addLoginName : {
												validators : {
													notEmpty : {
														message : '账号不能为空'
													},
													stringLength : {
														min : 0,
														max : 25,
														message : '账号长度不能超过25'
													}
												}
											},
											addUserName : {
												validators : {
													notEmpty : {
														message : '用户名不能为空'
													},
													stringLength : {
														min : 0,
														max : 50,
														message : '用户名长度不能超过25'
													}
												}
											},
											addPhone : {
												validators : {
													regexp : {
														regexp : /^[1]+[3,5,7,8]+\d{9}$/,
														message : '手机号格式不正确'
													}
												}
											},
											addUserCode : {
												validators : {
													notEmpty : {
														message : '工号不能为空'
													}
												}
											},
											addPassword : {
												validators : {
													notEmpty : {
														message : '密码不能为空'
													},
													stringLength : {
														min : 6,
														max : 12,
														message : '密码6~12位'
													}
												}
											},
											addRePassword : {
												validators : {
													notEmpty : {
														message : '请再次输入密码'
													},
													callback : {
														message : '两次输入密码不一致',
														callback : function(
																value,
																validator) {
															var password = $(
																	'#addPassword',panel)
																	.val();
															var rePassword = $(
																	'#addRePassword',panel)
																	.val();
															return password == rePassword;
														}
													}
												}
											}
										}
									}, "#addForm", self.create, panel)
				},
				create : function(panel) {
					$
							.post(
									"/user/managers/add",
									{
										"loginName" : $("#addLoginName",panel).val(),
										"userName" : $("#addUserName",panel).val(),
										"contactTel" : $("#addPhone",panel).val(),
										"password" : $("#addPassword",panel).val(),
										"address" : $("#addAddress",panel).val(),
										"gender" : $("#addMan",panel).prop('checked') ? "p_gender_male"
												: "p_gender_female",
										"roleIds" : $("#addRole",panel).val() == null ? ""
												: $("#addRole",panel).val().join(","),
										"userCode" : $("#addUserCode",panel).val(),
										"dutyId" : $("#addDutyId",panel).val() == "请选择" ? ""
												: $("#addDutyId",panel).val(),
										"deptId" : $("#addDeptId",panel).val() == "请选择" ? ""
												: $("#addDeptId",panel).val()
									}, function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												base.success("增加成功");
												$("#userTable",panel).bootstrapTable(
														'refresh');
												$('#addModal',panel).modal('hide');
											} else {
												base.error(data.message);
											}
										} else {
											base.error("数据加载失败!");
										}
									});
				},
				edit : function(panel) {
					var self = this;
					var arrselections = $("#userTable",panel).bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
						return;
					}
					if (arrselections.length <= 0) {
						sweetAlert("Oops...", "请选择有效数据!", "error");
						return;
					}
					$('#editUserManagerId',panel).val(arrselections[0].userManagerId);
					$("#editLoginName",panel).val(arrselections[0].loginName);
					$("#editUserName",panel).val(arrselections[0].userName);
					$("#editAddress",panel).val(arrselections[0].address);
					$("#editUserCode",panel).val(arrselections[0].userCode);
					$("#editDeptId",panel).val(arrselections[0].deptId).trigger(
							"change");
					$("#editDutyId",panel).val(arrselections[0].dutyId).trigger(
							"change");
					$("#editPhone",panel).val(arrselections[0].contactTel);
					$("#editBeEnabled",panel).val(arrselections[0].beEnabled);
					$("#editRole",panel).val(arrselections[0].roleIds).trigger(
							"change");

					if (arrselections[0].gender == "男") {
						$("#editMan",panel).prop("checked", "checked");
					} else {
						$("#editWoman",panel).prop("checked", "checked");
					}
					$('#editModal',panel).modal({
						keyboard : false,
						backdrop : 'static'
					});

					base.validator({
						message : 'This value is not valid',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							editUserName : {
								validators : {
									notEmpty : {
										message : '用户名不能为空'
									}
								}
							},
							editUserCode : {
								validators : {
									notEmpty : {
										message : '用户工号不能为空'
									}
								}
							},
							editPhone : {
								validators : {
									regexp : {
										regexp : /^[1]+[3,5,7,8]+\d{9}$/,
										message : '手机号格式不正确'
									}
								}
							}
						}
					}, "#editForm", self.update, panel)
				},
				update : function(panel) {
					$
							.post(
									"/user/managers/updateManager",
									{
										"userManagerId" : $(
												"#editUserManagerId",panel).val(),
										"userName" : $("#editUserName",panel).val(),
										"userCode" : $("#editUserCode",panel).val(),
										"address" : $("#editAddress",panel).val(),
										"beEnabled" : $("#editBeEnabled",panel).val(),
										"roleIds" : $("#editRole",panel).val() == null ? ""
												: $("#editRole",panel).val()
														.join(","),
										"contactTel" : $("#editPhone",panel).val(),
										"dutyId" : $("#editDutyId",panel).val() == "请选择" ? ""
												: $("#editDutyId",panel).val(),
										"deptId" : $("#editDeptId",panel).val() == "请选择" ? ""
												: $("#editDeptId",panel).val(),
										"gender" : $("#editMan",panel)
												.prop('checked') ? "p_gender_male"
												: "p_gender_female"
									},
									function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												base.success("编辑成功");
												$("#userTable",panel).bootstrapTable(
														'refresh');
												$('#editModal',panel).modal('hide');
												$('#editForm',panel).data(
														'bootstrapValidator')
														.resetForm(true);
												$('#addForm',panel).data(
														'bootstrapValidator')
														.resetForm(true);
											} else {
												base.error(data.message);
												$('#editForm',panel).find(
														".btn-primary")
														.removeAttr("disabled");
											}
										} else {
											base.error("更新失败!");
										}
									});
				},
				remove : function(panel) {
					var arrselections = $("#userTable",panel).bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var userManagerId = arrselections[0].userManagerId;

					base
							.cancel(
									{
										title : "注销用户",
										text : "您确定要注销此用户吗？"
									},
									function() {
										$
												.post(
														"/user/managers/cancelManagerUser",
														{
															"userManagerId" : userManagerId
														},
														function(data, status) {
															if (status == "success") {
																var obj = JSON
																		.parse(data);
																if (obj.success == 0) {
																	base
																			.success("删除成功");
																	$(
																			"#userTable",panel)
																			.bootstrapTable(
																					'refresh');
																} else {
																	base
																			.error(obj.message);
																}
															} else {
																base
																		.error("注销失败");
															}
														});
									});
				},
				initPwd : function(panel) {
					var arrselections = $("#userTable",panel).bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						base.error("只能选择一行进行编辑!");
						return;
					}
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var userManagerId = arrselections[0].userManagerId;
					base.cancel({
						title : "初始化密码",
						text : "您确定要初始化密码吗？"
					}, function() {
						$.post("/user/managers/initManagerPwd", {
							"userManagerId" : userManagerId
						}, function(data, status) {
							if (status == "success") {
								var obj = JSON.parse(data);
								if (obj.success == 0) {
									base.success("初始化密码为：111111，成功");
									$("#userTable",panel).bootstrapTable('refresh');
								} else {
									base.error(obj.message);
								}
							} else {
								base.error("初始化失败!");
							}
						});
					});
				},
				showDetail : function(orderId, panel){
					$('#detailMoblile',panel).val("");
					$('#detailOrderId',panel).val("");
					$('#detailRealName',panel).val("");
					$('#detailWaybillNo',panel).val("");
					$('#detailSex',panel).val("");
					$('#detailState',panel).val("");
					$('#detailCityName',panel).val("");
					$('#detailAddress',panel).val("");
					$('#detailCollegeName',panel).val("");
					$('#detailStoreName',panel).val("");
					$('#detailLocation',panel).val("");
					$('#detailTotalMoney',panel).val("");
					$('#detailRebateMoney',panel).val("");
					$('#detailFinalMoney',panel).val("");
					$('#detailPayMoney',panel).val("");
					
					$.post("/order/user/orderView", {
						"orderId" : orderId,
						"type" : "send"
					}, function(data, status) {
						if (status == "success") {
							$('#detailMoblile',panel).val(data.mobile);
							$('#detailOrderId',panel).val(orderId);
							$('#detailRealName',panel).val(data.consignee);
							$('#detailWaybillNo',panel).val(data.waybillNo);
							if(data.sex=="p_gender_male"){
								$('#detailSex',panel).val("男");
							}else if(data.sex=="p_gender_female"){
								$('#detailSex',panel).val("女");
							}else if(data.sex=="p_gender_secret"){
								$('#detailSex',panel).val("保密");
							}
							$('#detailState',panel).val(data.stateStr);
							$('#detailCityName',panel).val(data.cityName);
							$('#detailAddress',panel).val(data.address);
							$('#detailCollegeName',panel).val(data.fullName);
							$('#detailStoreName',panel).val(data.storeName);
							$('#detailLocation',panel).val(data.location);
							$('#detailTotalMoney',panel).val(data.totalMoney);
							$('#detailRebateMoney',panel).val(data.rebateMoney);
							$('#detailFinalMoney',panel).val(data.finalMoney);
							$('#detailPayMoney',panel).val(data.payMoney);
						} else {
							base.error("初始化失败!");
						}
					});

					$('#detailTable',panel).bootstrapTable({
						 // 请求后台的URL（*）
				        url : '/order/user/orderDetail',
				        striped: true, // 是否显示行间隔色
				        singleSelect: true,
				        queryParams : function(pa) {
							return {"orderId" : orderId};
						},
				        height: 320,
						columns : [ {
							field : 'createDateStr',
							title : '操作时间',
							width : 100
						}, {
							field : 'content',
							title : '内容',
							width : 100
						}
						]
					}).bootstrapTable('refresh',{query: {"orderId" : orderId}});

					$('#detailModal',panel).modal();
				}
			};
		});