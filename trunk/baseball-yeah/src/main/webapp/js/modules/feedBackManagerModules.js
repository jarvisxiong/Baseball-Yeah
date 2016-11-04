define([ 'base' ],
		function(base) {
			/**
			 * 私有成员定义区域
			 */

			return {
				init : function(panel) {
					// / <summary>
					// / 模块初始化方法
					// / </summary>
					// / <param name="args">初始化时传入的参数</param>
					var self = this;

					base.datagrid({
						url : '/user/feedback/selectall',
						method : 'post',
						queryParams : function(params) {
							return $.extend(params, {
								phone : $("#phone",panel).val()
							});
						},
						columns : [ {
							checkbox : true
						}, {
							field : 'userId',
							title : '用户id',
							sortable : true,
							width : 400
						}, {
							field : 'name',
							title : '用户姓名',
							sortable : true,
							width : 400
						}, {
							field : 'phone',
							title : '用户联系方式',
							sortable : true,
							width : 400
						}, {
							field : 'content',
							title : '反馈信息内容',
							sortable : true,
							width : 1600

						}, {
							field : 'ip',
							title : '本地IP地址',
							sortable : true,
							width : 400
						}, {
							field : 'submittedTime',
							title : '提交时间',
							sortable : true,
							width : 400
						}, {
							field : 'feedbackId',
							title : '编号',
							visible : false
						} ]
					}, '#userTable',panel);

					$.ajax({
						type : "POST",
						url : "/store/exp/expstoreinfo",
						dataType : "json",
						success : function(data) {

							$("#selstore",panel).select2({
								data : data
							});
							$("#add_store",panel).select2({
								data : data
							});
							$("#edit_store",panel).select2({
								data : data
							});
						}
					});

					$("#btn_add",panel).click(function() {
						self.add(panel);
					});
					$("#btn_delete",panel).click(function() {
						self.remove(panel);
					});

					$("#btn_query",panel).click(function() {
						$("#userTable", panel).bootstrapTable('selectPage', 1);
					});

					$("#btn_add",panel).click(function() {
						$("#add_submittedTime",panel).datetimepicker({
							format : 'yyyy-mm-dd hh:ii:mm'
						});

					});
					$("#clearSearch", panel).click(function () {
		                base.reset(".main-box-header");
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
					$('#addModal',panel).modal({
						keyboard : false,
						backdrop : 'static'
					});
					base.validator({
						fields : {
							add_content : {
								validators : {
									notEmpty : {
										message : '内容不能为空'
									}
								}
							},
							add_ip : {
								validators : {
									notEmpty : {
										message : 'IP地址不能为空'
									}
								}
							},
							add_submittedTime : {
								validators : {
									notEmpty : {
										message : '提交时间不能为空'
									}
								}
							},
							add_phone : {
								validators : {
									notEmpty : {
										message : '手机号不能为空'
									},
									regexp : {
										regexp : /^[1]+[3,5,7,8]+\d{9}/,
										message : '手机号格式不正确'
									}
								}
							},
							add_name : {
								validators : {
									notEmpty : {
										message : '用户名称不能为空'
									}
								}
							},
							add_userId : {
								validators : {
									notEmpty : {
										message : '用户ID不能为空'
									}
								}
							}
						}
					}, "#addForm", self.create,panel);
				},
				create : function(panel) {
					$.post("/user/feedback/addfeedback", {
						"caption" : $("#add_caption",panel).val(),
						"userId" : $("#add_userId",panel).val(),
						"name" : $("#add_name",panel).val(),
						"publishTime" : $("#add_publishTime",panel).val(),
						"phone" : $("#add_phone",panel).val(),
						"content" : $("#add_content",panel).val(),
						"ip" : $("#add_ip",panel).val(),
						"submittedTime" : $("#submittedTime_ip",panel).val()

					}, function(data, status) {
						if (status == "success") {

							if (data.success == 0) {
								base.success("添加成功！");
								$("#userTable",panel).bootstrapTable('refresh');
								$("#addModal",panel).modal('hide');
							} else {
								base.error(data.message);
							}
						} else {
							base.error("数据加载失败!");
						}
					});
				},
				remove : function(panel) {
					var arrselections = $("#userTable",panel).bootstrapTable(
							'getSelections');
					if (arrselections.length <= 0) {
						base.error("请选择有效数据!");
						return;
					}
					var userInfos = [];

					for (var i = 0; i < arrselections.length; i++) {
						userInfos.push(arrselections[i].feedbackId);
					}
					base.cancel({
						title : "删除",
						text : "您确定要删除吗？"
					}, function() {
						$.post("/user/feedback/deletefeedback", {
							"feedbackIds" : userInfos.join(',')
						}, function(data, status) {
							if (status == "success") {

								if (data.success == 0) {
									base.success("删除成功！");
									$("#userTable",panel).bootstrapTable('refresh');
								} else {
									base.error(data.message);
								}
							} else {
								base.error("注销失败");
							}
						});
					});
				}
			};
		});