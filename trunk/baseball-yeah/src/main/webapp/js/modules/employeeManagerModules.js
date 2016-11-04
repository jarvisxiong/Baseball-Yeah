define(
		[ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */

			return {
				init : function(args) {
					// / <summary>
					// / 模块初始化方法
					// / </summary>
					// / <param name="args">初始化时传入的参数</param>
					var self = this;

					base.datagrid(
									{
										url : '/user/managers/all',
										queryParams : function(params) {
											return $.extend(params,
															{
																loginName : $.trim($("#loginName",args).val()),
																contactTel : $.trim($("#contactTel",args).val()),
																address : $.trim($("#address",args).val()),
																deptId : $("#deptId",args).val()=="请选择"?"":$("#deptId",args).val(),
																dutyId : $("#dutyId",args).val()=="请选择"?"":$("#dutyId",args).val(),
																beEnabled : $("#beEnabled",args).val(),
																roleId : $("#role",args).val(),
																userName:$.trim($("#userName",args).val())
															});
										},
										columns : [
												{
													checkbox : true
												},
												{
													field : 'userManagerId',
													title : '管理Id',
													sortable : true,
													visible : false,
													width:100
												},
												{
													field : 'loginName',
													title : '帐号',
													sortable : true,
													width:100
												},
												{
													field : 'userName',
													title : '姓名',
													sortable : true,
													width:100
												},
												{
													field : 'userCode',
													title : '工号',
													sortable : true,
													visible : true,
													width:100
												},
												{
													field : 'deptId',
													title : '部门',
													sortable : true,
													visible : false,
													width:100
												},
												{
													field : 'dutyId',
													title : '职务',
													sortable : true,
													visible : false,
													width:100
												},
												{
													field : 'gender',
													title : '性别',
													sortable : true,
													width:100
												},
												{
													field : 'contactTel',
													title : '联系电话',
													sortable : true,
													width:100
												},
												{
													field : 'address',
													title : '地址',
													sortable : true,
													width:100
												},
												{
													field : 'deptName',
													title : '部门名称',
													sortable : true,
													width:100
												},
												{
													field : 'dutyName',
													title : '职务名称',
													sortable : true,
													width:100
												},
												{
													field : 'beDeleted',
													title : '是否删除',
													formatter : function(value,
															row, index) {
														return value == "1" ? "<a class='label label-warning'>已删除 </a>"
																: "<a class='label label-success' >可用</a>";
													},
													sortable : true,
													visible : false,
													width:100
												}, {
													field : 'beEnabled',
													title : '是否启用',
													formatter : function(value,
															row, index) {
														return value == "1" ? "<a class='label label-success'>启用</a>"
																: "<a class='label label-warning' >禁用</a>";
													},
													sortable : true,
													width:100
												},
												{
													field : 'roleIds',
													title : '拥有角色Id',
													sortable : true,
													visible : false,
													width:100
												},
												{
													field : 'roleNameStr',
													title : '拥有角色',
													sortable : false,
													width:150
												}]
									}, '#userTable',args);

					$.ajax({
						type : "POST",
						url : "/manage/duty/selectallduty",
						dataType : "json",
						success : function(data) {

							$("#dutyId",args).select2({
								data : data
							});
							$("#addDutyId",args).select2({
								data : data
							});
							$("#editDutyId",args).select2({
								data : data
							});
						}
					});

					$.ajax({
						type : "POST",
						url : "/manage/dept/selectalldept",
						dataType : "json",
						success : function(data) {

							$("#deptId",args).select2({
								data : data
							});
							$("#addDeptId",args).select2({
								data : data
							});
							$("#editDeptId",args).select2({
								data : data
							});
						}
					});
					
					$.ajax({
						type : "GET",
						url : "/manage/role/getRoleSelect",
						dataType : "json",
						success : function(DATA) {
							$("#role",args).select2({
								data : DATA
							});
							$("#addRole",args).select2({
								data : DATA
							});
							$("#editRole",args).select2({
								data : DATA
							});
						}
					});

					$("#btn_add",args).click(function() {
						self.add(args);
					});
					$("#btn_edit",args).click(function() {
						self.edit(args);
					});
					$("#btn_delete",args).click(function() {
						self.remove(args);
					});
					$("#btn_initPwd",args).click(function() {
						self.initPwd(args);
					});
					$("#btn_query",args).click(function() {
						// $("#userTable",args).bootstrapTable('refresh');
						$("#userTable", args).bootstrapTable('selectPage', 1);
					});
					
					$("#clearSearch",args).click(function () {
		                base.reset(".main-box-body");
		                $('#deptId',args).select2("val", "");
		                $('#dutyId',args).select2("val", "");
		            });
					
					$('#addRole',args).select2({
						placeholder : '请选择角色'
					});
					
					$('#addModal',args).on('shown.bs.modal', function () {
		                $('#addForm',args).data('bootstrapValidator').resetForm(true);
		         })
				},
				add : function(args) {
					var self = this;
					$('#addLoginName',args).val('');
					$('#addUserName',args).val('');
					$('#addUserCode',args).val('');
					$('#addDeptId',args).val('').trigger("change");
					$('#addPassword',args).val('');
					$('#addRePassword',args).val('');
					$('#addDutyId',args).val('').trigger("change");
					$('#addRole',args).val('').trigger("change");
					$('.addGender',args).val('');
					$('#addPhone',args).val('');
					$('#addAddress',args).val('');
					$('#addModal',args).modal({
					    keyboard: false,
					    backdrop:'static'
					});
					base.validator(
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
													 stringLength: {
									                        min: 0,
									                        max: 25,
									                        message: '账号长度不能超过25'
									                    }
												}
											},
											addUserName : {
												validators : {
													notEmpty : {
														message : '用户名不能为空'
													},
													 stringLength: {
									                        min: 0,
									                        max: 50,
									                        message: '用户名长度不能超过25'
									                    }
												}
											},
											addPhone : {
												validators : {
													 regexp: {
							                                regexp: /^[1]+[3,5,7,8]+\d{9}$/,
							                                message: '手机号格式不正确'
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
													 stringLength: {
									                        min: 6,
									                        max: 12,
									                        message: '密码6~12位'
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
																	'#addPassword',args)
																	.val();
															var rePassword = $(
																	'#addRePassword',args)
																	.val();
															return password == rePassword;
														}
													}
												}
											}
										}
									}, "#addForm", self.create,args)
				},
				create : function(args) {
					$.post("/user/managers/add",
									{
										"loginName" : $("#addLoginName",args).val(),
										"userName" : $("#addUserName",args).val(),
										"contactTel" : $("#addPhone",args).val(),
										"password" : $("#addPassword",args).val(),
										"address" : $("#addAddress",args).val(),
										"gender" : $("#addMan",args).prop('checked') ? "p_gender_male"
												: "p_gender_female",
										"roleIds" : $("#addRole",args).val()==null?"":$("#addRole").val().join(","),
										"userCode" : $("#addUserCode",args).val(),
										"dutyId" : $("#addDutyId",args).val() == "请选择" ? ""
												: $("#addDutyId",args).val(),
										"deptId" : $("#addDeptId",args).val() == "请选择" ? ""
												: $("#addDeptId",args).val()
									}, function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												base.success("增加成功");
												// $("#userTable",args).bootstrapTable('refresh');
												$("#userTable", args).bootstrapTable('selectPage', 1);
												$('#addModal',args).modal('hide');
											} else {
												base.error(data.message);
											}
										} else {
											base.error("数据加载失败!");
										}
									});
				},
				edit : function(args) {
					var self = this;
					var arrselections = $("#userTable",args).bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
						return;
					}
					if (arrselections.length <= 0) {
						sweetAlert("Oops...", "请选择有效数据!", "error");
						return;
					}
					$('#editUserManagerId',args).val(arrselections[0].userManagerId);
					$("#editLoginName",args).val(arrselections[0].loginName);
					$("#editUserName",args).val(arrselections[0].userName);
					$("#editAddress",args).val(arrselections[0].address);
					$("#editUserCode",args).val(arrselections[0].userCode);
					$("#editDeptId",args).val(arrselections[0].deptId).trigger("change");
					$("#editDutyId",args).val(arrselections[0].dutyId).trigger("change");
					$("#editPhone",args).val(arrselections[0].contactTel);
					$("#editBeEnabled",args).val(arrselections[0].beEnabled);
					$("#editRole",args).val(arrselections[0].roleIds).trigger("change");
					$('#editPassword',args).val('');
					$('#editRePassword',args).val('');
					
					if(arrselections[0].gender == "男"){
						$("#editMan",args).prop("checked","checked");
					}else{
						$("#editWoman",args).prop("checked","checked");
					}
					$('#editModal',args).modal({
					    keyboard: false,
					    backdrop:'static'
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
							},
						editPassword : {
								validators : {
									 stringLength: {
					                        min: 6,
					                        max: 12,
					                        message: '密码6~12位'
					                    }
								}
							},
							editRePassword : {
								validators : {
									required :function(){
										if($('#editPassword',args).val()==null || $('#editPassword',args).val()==''){
											return false;
										}else{
											return true;
										}
									},
									callback : {
										message : '两次输入密码不一致',
										callback : function(
												value,
												validator) {
											var password = $('#editPassword',args).val();
											var rePassword = $('#editRePassword',args).val();
											return password == rePassword;
										}
									}
								}
							}
						}
					}, "#editForm", self.update,args)
				},
				update : function(args) {
					$.post("/user/managers/updateManager",
									{
										"userManagerId" : $("#editUserManagerId",args).val(),
										"userName" : $("#editUserName",args).val(),
										"userCode" : $("#editUserCode",args).val(),
										"address" : $("#editAddress",args).val(),
										"beEnabled":$("#editBeEnabled",args).val(),
										"roleIds" : $("#editRole",args).val()==null?"":$("#editRole",args).val().join(","),
										"password" : $("#editPassword",args).val(),
										"contactTel" : $("#editPhone",args).val(),
										"dutyId" : $("#editDutyId",args).val() == "请选择" ? ""
												: $("#editDutyId",args).val(),
										"deptId" : $("#editDeptId",args).val() == "请选择" ? ""
												: $("#editDeptId",args).val(),
										"gender" : $("#editMan",args).prop('checked') ? "p_gender_male": "p_gender_female"
									}, function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												base.success("编辑成功");
												// $("#userTable",args).bootstrapTable('refresh');
												$("#userTable", args).bootstrapTable('selectPage', 1);
												$('#editModal',args).modal('hide');
												 $('#editForm',args).data('bootstrapValidator').resetForm(true);
												$('#addForm',args).data('bootstrapValidator').resetForm(true);
											} else {
												base.error(data.message);
												$('#editForm',args).find(".btn-primary").removeAttr("disabled");
											}
										} else {
											base.error("更新失败!");
										}
									});
				},
				remove : function(args) {
					var arrselections = $("#userTable",args).bootstrapTable(
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
										title : "注销用户",
										text : "您确定要注销此用户吗？"
									},
									function() {
										$.post("/user/managers/cancelManagerUser",
														{
															"userManagerId" : userManagerId
														},
														function(data, status) {
															if (status == "success") {
																var obj = JSON.parse(data);
																if (obj.success == 0) {
																	base.success("删除成功");
																	// $("#userTable",args).bootstrapTable('refresh');
																	$("#userTable", args).bootstrapTable('selectPage', 1);
																} else {
																	base.error(obj.message);
																}
															} else {
																base.error("注销失败");
															}
														});
									});
				},
				initPwd : function(args) {
					var arrselections = $("#userTable",args).bootstrapTable(
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
									// $("#userTable",args).bootstrapTable('refresh');
									$("#userTable", args).bootstrapTable('selectPage', 1);
								} else {
									base.error(obj.message);
								}
							} else {
								base.error("初始化失败!");
							}
						});
					});
				}
			};
		});