define([ 'base' ],function(base) {
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

					base.datagrid({
						url : '/manage/role/selectAllRole',
						sScrollY : true,
						queryParams : function(params) {
							return $.extend(params,
									{
										roleName : $.trim($("#roleName").val()),
										roleType : $("#roleType").val() == "请选择" ? "" : $("#roleType").val(),
										beSysPrivilege : $("#beSysPrivilege").val() == "请选择" ? "" : $("#beSysPrivilege").val()
									});
						},
						columns : [ {
							checkbox : true
						}, {
							field : 'roleId',
							title : '角色Id',
							sortable : true,
							visible : false,
							width:800
						}, {
							field : 'roleName',
							title : '角色名称',
							sortable : true,
							width:800
						}, {
							field : 'roleType',
							title : '角色类型',
							formatter : function(value,row, index) {
								return value == "1" ? "<a class='label label-success'>系统管理员</a>"
										: "<a class='label label-success' >超级系统管理员</a>";
							},
							sortable : true,
							width:800
						}, {
							field : 'beSysPrivilege',
							title : '是否系统权限',
							formatter : function(value,row, index) {
								return value == "1" ? "<a class='label label-success'>系统权限 </a>"
										: "<a class='label label-warning' >非系统权限</a>";
							},
							sortable : true,
							width:800
						} ]
					}, '#userTable');


					$("#btn_add").click(function() {
						self.add();
					});
					$("#btn_edit").click(function() {
						self.edit();
					});
					$("#btn_delete").click(function() {
						self.remove();
					});
					$("#btn_query").click(function() {
						$("#userTable").bootstrapTable('refresh');
					});
					
					$("#clearSearch").click(function () {
						$('#roleName').val('');
		                $('#roleType').val( "");
		                $('#beSysPrivilege').val( "");
		            });
					
					$('#addModal').on('shown.bs.modal', function () {
			                $('#addForm').data('bootstrapValidator').resetForm(true);
			         })
				},
				add : function() {
					var self = this;
					$('#addRoleName').val('');
					$('#addRoleType').val('').trigger("change");
					$('#addBeSysPrivilege').val('').trigger("change");
					$.fn.zTree.getZTreeObj("addTreeDemo").checkAllNodes(false);
					$('#addModal').modal({
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
							addRoleName : {
								validators : {
									notEmpty : {
										message : '角色名不能为空'
									}
								}
							},
							addRoleType : {
								validators : {
									notEmpty : {
										message : '角色类型不能为空'
									}
								}
							},
							addBeSysPrivilege : {
								validators : {
									notEmpty : {
										message : '是否系统权限不能为空'
									}
								}
							}
						}
					}, '#addForm', self.create)
				},
				create : function() {
					var zTree = $.fn.zTree.getZTreeObj("addTreeDemo");
					var checkNodes=new Array();
					var nodes = zTree.getChangeCheckedNodes();
					for(var i=0;i<nodes.length;i++){
						checkNodes[i]=nodes[i].idStr;
					}
					$.post(
									"/manage/role/addRole",
									{
										"roleName" : $("#addRoleName").val(),
										"roleType" : $("#addRoleType").val() == "请选择" ? "" : $("#addRoleType").val(),
										"beSysPrivilege" : $("#addBeSysPrivilege").val() == "请选择" ? "" : $("#addBeSysPrivilege").val(),
										"checkNodes":checkNodes.toString()
									}, function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												swal("增加成功");
												$('#addModal').modal('hide');
												$("#userTable").bootstrapTable('refresh');
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
					$('#editRoleID').val(arrselections[0].roleId);
					$('#editRoleName').val(arrselections[0].roleName);
					$('#editRoleType').val(arrselections[0].roleType).trigger("change");
					$('#editBeSysPrivilege').val(arrselections[0].beSysPrivilege).trigger("change");
					
					var zTree = $.fn.zTree.getZTreeObj("editTreeDemo");
					zTree.checkAllNodes(false);
					$.get("/manage/menu/getRoleMenu", {
						"roleId":arrselections[0].roleId
					},function(data,status) {
						if(status== "success"){
							for(var i=0;i<data.length;i++){
								zTree.checkNode(zTree.getNodeByParam("idStr", data[i].menuIdStr), true,false);
							}
						}
					});
					
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
							editRoleName : {
								validators : {
									notEmpty : {
										message : '角色名不能为空'
									}
								}
							},
							editRoleType : {
								validators : {
									notEmpty : {
										message : '角色类型不能为空'
									}
								}
							},
							editBeSysPrivilege : {
								validators : {
									notEmpty : {
										message : '是否系统权限不能为空'
									}
								}
							}
						}
					},'#editForm', self.update)
				},
				update : function() {
					var zTree = $.fn.zTree.getZTreeObj("editTreeDemo");
					var checkNodes=new Array();
					var nodes = zTree.getChangeCheckedNodes();
					for(var i=0;i<nodes.length;i++){
						checkNodes[i]=nodes[i].idStr;
					}
					$.post(
									"/manage/role/updateRoleAndPermission",
									{
										"roleId" : $('#editRoleID').val(),
										"roleName" : $("#editRoleName").val(),
										"roleType" : $("#editRoleType").val(),
										"beSysPrivilege" : $("#editBeSysPrivilege").val() == "请选择" ? "" : $("#editBeSysPrivilege").val(),
										"checkNodes":checkNodes.toString()
									}, function(data, status) {
										if (status == "success") {
											if (data.success == 0) {
												$('#editModal').modal('hide');
												$("#userTable").bootstrapTable('refresh');
												$('#editForm').data('bootstrapValidator').resetForm(true);
												base.success("编辑成功");
											} else {
												base.error(data.message);
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
					var roleId = arrselections[0].roleId;

					base.cancel({
						title : "删除角色",
						text : "您确定要删除此角色吗？"
					}, function() {
						$.post("/manage/role/deleteRole", {
							"roleId" : roleId
						}, function(data, status) {
							if (status == "success") {
								if (data.success == 0) {
									base.success("删除成功");
									$("#userTable").bootstrapTable('refresh');
								} else {
									base.error(data.message);
								}
							} else {
								base.error("删除失败");
							}
						});
					});
				},
			};
		});

