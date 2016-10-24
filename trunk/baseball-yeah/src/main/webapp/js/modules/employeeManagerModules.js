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
																loginName : $.trim($("#loginName").val()),
																contactTel : $.trim($("#contactTel").val()),
																address : $.trim($("#address").val()),
																deptId : $("#deptId").val()=="请选择"?"":$("#deptId").val(),
																dutyId : $("#dutyId").val()=="请选择"?"":$("#dutyId").val(),
																beEnabled : $("#beEnabled").val(),
																roleId : $("#role").val(),
																userName:$.trim($("#userName").val())
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
									}, '#userTable');

					$.ajax({
						type : "POST",
						url : "/manage/duty/selectallduty",
						dataType : "json",
						success : function(data) {

							$("#dutyId").select2({
								data : data
							});
							$("#addDutyId").select2({
								data : data
							});
							$("#editDutyId").select2({
								data : data
							});
						}
					});

					$.ajax({
						type : "POST",
						url : "/manage/dept/selectalldept",
						dataType : "json",
						success : function(data) {

							$("#deptId").select2({
								data : data
							});
							$("#addDeptId").select2({
								data : data
							});
							$("#editDeptId").select2({
								data : data
							});
						}
					});
					
					$.ajax({
						type : "GET",
						url : "/manage/role/getRoleSelect",
						dataType : "json",
						success : function(DATA) {
							$("#role").select2({
								data : DATA
							});
							$("#addRole").select2({
								data : DATA
							});
							$("#editRole").select2({
								data : DATA
							});
						}
					});

					$("#btn_add").click(function() {
						self.add();
					});
					$("#btn_edit").click(function() {
						self.edit();
					});
					$("#btn_delete").click(function() {
						self.remove();
					});
					$("#btn_initPwd").click(function() {
						self.initPwd();
					});
					$("#btn_query").click(function() {
						$("#userTable").bootstrapTable('refresh');
					});
					
					$("#clearSearch").click(function () {
		                base.reset(".main-box-body");
		                $('#deptId').select2("val", "");
		                $('#dutyId').select2("val", "");
		            });
					
					$('#addRole').select2({
						placeholder : '请选择角色'
					});
					
					$('#addModal').on('shown.bs.modal', function () {
		                $('#addForm').data('bootstrapValidator').resetForm(true);
		         })
				},
				add : function() {
					var self = this;
					$('#addLoginName').val('');
					$('#addUserName').val('');
					$('#addUserCode').val('');
					$('#addPassword').val('');
					$('#addRePassword').val('');
					$('#addDutyId').val('');
					$('.addGender').val('');
					$('#addPhone').val('');
					$('#addAddress').val('');
					$('#addModal').modal({
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
																	'#addPassword')
																	.val();
															var rePassword = $(
																	'#addRePassword')
																	.val();
															return password == rePassword;
														}
													}
												}
											}
										}
									}, "#addForm", self.create)
				},
				create : function() {
					$.post("/user/managers/add",
									{
										"loginName" : $("#addLoginName").val(),
										"userName" : $("#addUserName").val(),
										"contactTel" : $("#addPhone").val(),
										"password" : $("#addPassword").val(),
										"address" : $("#addAddress").val(),
										"gender" : $("#addMan").prop('checked') ? "p_gender_male"
												: "p_gender_female",
										"roleIds" : $("#addRole").val()==null?"":$("#addRole").val().join(","),
										"userCode" : $("#addUserCode").val(),
										"dutyId" : $("#addDutyId").val() == "请选择" ? ""
												: $("#addDutyId").val(),
										"deptId" : $("#addDeptId").val() == "请选择" ? ""
												: $("#addDeptId").val()
									}, function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												base.success("增加成功");
												$("#userTable").bootstrapTable('refresh');
												$('#addModal').modal('hide');
											} else {
												base.error(data.message);
											}
										} else {
											base.error("数据加载失败!");
										}
									});
				},
				edit : function() {
					var self = this;
					var arrselections = $("#userTable").bootstrapTable(
							'getSelections');
					if (arrselections.length > 1) {
						sweetAlert("Oops...", "只能选择一行进行编辑!", "error");
						return;
					}
					if (arrselections.length <= 0) {
						sweetAlert("Oops...", "请选择有效数据!", "error");
						return;
					}
					$('#editUserManagerId').val(arrselections[0].userManagerId);
					$("#editLoginName").val(arrselections[0].loginName);
					$("#editUserName").val(arrselections[0].userName);
					$("#editAddress").val(arrselections[0].address);
					$("#editUserCode").val(arrselections[0].userCode);
					$("#editDeptId").val(arrselections[0].deptId).trigger("change");
					$("#editDutyId").val(arrselections[0].dutyId).trigger("change");
					$("#editPhone").val(arrselections[0].contactTel);
					$("#editBeEnabled").val(arrselections[0].beEnabled);
					$("#editRole").val(arrselections[0].roleIds).trigger("change");
					$('#editPassword').val('');
					$('#editRePassword').val('');
					
					if(arrselections[0].gender == "男"){
						$("#editMan").prop("checked","checked");
					}else{
						$("#editWoman").prop("checked","checked");
					}
					$('#editModal').modal({
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
										if($('#editPassword').val()==null || $('#editPassword').val()==''){
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
											var password = $('#editPassword').val();
											var rePassword = $('#editRePassword').val();
											return password == rePassword;
										}
									}
								}
							}
						}
					}, "#editForm", self.update)
				},
				update : function() {
					$.post("/user/managers/updateManager",
									{
										"userManagerId" : $("#editUserManagerId").val(),
										"userName" : $("#editUserName").val(),
										"userCode" : $("#editUserCode").val(),
										"address" : $("#editAddress").val(),
										"beEnabled":$("#editBeEnabled").val(),
										"roleIds" : $("#editRole").val()==null?"":$("#editRole").val().join(","),
										"password" : $("#editPassword").val(),
										"contactTel" : $("#editPhone").val(),
										"dutyId" : $("#editDutyId").val() == "请选择" ? ""
												: $("#editDutyId").val(),
										"deptId" : $("#editDeptId").val() == "请选择" ? ""
												: $("#editDeptId").val(),
										"gender" : $("#editMan").prop('checked') ? "p_gender_male": "p_gender_female"
									}, function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												base.success("编辑成功");
												$("#userTable").bootstrapTable('refresh');
												$('#editModal').modal('hide');
												 $('#editForm').data('bootstrapValidator').resetForm(true);
												$('#addForm').data('bootstrapValidator').resetForm(true);
											} else {
												base.error(data.message);
												$('#editForm').find(".btn-primary").removeAttr("disabled");
											}
										} else {
											base.error("更新失败!");
										}
									});
				},
				remove : function() {
					var arrselections = $("#userTable").bootstrapTable(
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
																	$("#userTable").bootstrapTable('refresh');
																} else {
																	base.error(obj.message);
																}
															} else {
																base.error("注销失败");
															}
														});
									});
				},
				initPwd : function() {
					var arrselections = $("#userTable").bootstrapTable(
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
									$("#userTable").bootstrapTable('refresh');
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