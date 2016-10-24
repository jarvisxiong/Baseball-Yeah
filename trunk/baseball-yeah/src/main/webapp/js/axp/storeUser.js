$(document)
		.ready(
				function () {

				    $('#userTable')
							.bootstrapTable(
									{
									    url: '/user/GridData', // 请求后台的URL（*）
									    method: 'get', // 请求方式（*）
									    toolbar: '#toolbar', // 工具按钮用哪个容器
									    striped: true, // 是否显示行间隔色
									    pagination: true, // 是否显示分页（*）
									    queryParams: function (params) {
									        return $.extend(params,
													{
													    nickname: $(
																"#nickname")
																.val(),
													    phone: $("#phone")
																.val(),
													    storeId: $("#selstore").val() == "请选择" ? "" : $("#selstore").val()
													});
									    },// 传递参数（*）
									    sidePagination: "server", // 分页方式：client客户端分页，server服务端分页（*）
									    pageList: [10, 25, 50, 100], // 可供选择的每页的行数（*）
									    clickToSelect: true, // 是否启用点击选中行
									    height: 500, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
									    columns: [
												{
												    checkbox: true
												},
												{
												    field: 'storeName',
												    title: '快递站点',
												    sortable: true
												},
												{
												    field: 'verifyStatus',
												    title: '实名审核状态',
												    formatter: function (value,
															row, index) {
												        return value == "1" ? "<a class='label label-success'>已审核 </a>"
																: "<a class='label label-warning' >未审核</a>";
												    },
												    sortable: true
												},
												{
												    field: 'beSupervisor',
												    title: '是否负责人',
												    formatter: function (value,
															row, index) {
												        return value == "1" ? "<a class='label label-success'>是负责人 </a>"
																: "<a class='label label-warning' >非负责人</a>";
												    },
												    sortable: true
												},
												{
												    field: 'nickname',
												    title: '用户昵称',
												    sortable: true
												},
												{
												    field: 'userName',
												    title: '用户名',
												    sortable: true
												},
												{
												    field: 'phone',
												    title: '手机号',
												    sortable: true
												},
												{
												    field: 'age',
												    title: '年龄',
												    sortable: true
												},
												{
												    field: 'signupIp',
												    title: '注册IP',
												    sortable: true
												},
												{
												    field: 'signupTime',
												    title: '注册时间',
												    sortable: true
												},
												{
												    field: 'gender',
												    title: '性别',
												    formatter: function (value,
															row, index) {
												        return value == "p_gender_male" ? "男"
																: "女";
												    },
												    sortable: true
												}, {
												    field: 'userId',
												    title: 'userId',
												    visible: false
												}, ]

									});

				    $("#btn_add").click(function () {
				        $('#addModal').modal();
				        $('#addForm')
				        .bootstrapValidator({
				            message: 'This value is not valid',
				            feedbackIcons: {
				                valid: 'glyphicon glyphicon-ok',
				                invalid: 'glyphicon glyphicon-remove',
				                validating: 'glyphicon glyphicon-refresh'
				            },
				            fields: {
				                add_nickname: {
				                    validators: {
				                        notEmpty: {
				                            message: '用户昵称不能为空'
				                        }
				                    }
				                },
				                add_userName: {
				                    validators: {
				                        notEmpty: {
				                            message: '用户名不能为空'
				                        }
				                    }
				                },
				                add_phone: {
				                    validators: {
				                        notEmpty: {
				                            message: '手机号不能为空'
				                        },
				                        regexp: {
				                            regexp: /^[1]+[3,5,8]+\d{9}/,
				                            message: '手机号格式不正确'
				                        }
				                    }
				                },
				                add_pwd: {
				                    validators: {
				                        notEmpty: {
				                            message: '密码不能为空'
				                        }
				                    }
				                }
				            }
				        })
				        .on('success.form.bv', function (e) {
				            // Prevent form submission
				            e.preventDefault();

				            // Get the form instance
				            var $form = $(e.target);

				            // Get the BootstrapValidator instance
				            var bv = $form.data('bootstrapValidator');

				            $
							.post(
									"insertselective",
									{
									    "nickname": $(
												"#add_nickname")
												.val(),
									    "userName": $(
												"#add_userName")
												.val(),
									    "phone": $(
												"#add_phone")
												.val(),
									    "accountPwd": $(
												"#add_pwd")
												.val(),
									    "gender": $(
												"#addMan")
												.prop(
														'checked') ? "p_gender_male"
												: "p_gender_female",
									    "beSupervisor": 0,
									    "storeId": $("#add_store").val() == "请选择" ? "" : $("#add_store").val()
									},
									function (data, status) {
									    if (status == "success") {
									        var obj = JSON
													.parse(data);
									        if (obj.success == 0) {
									            window.location
														.reload();
									        } else {
									            sweetAlert(
														"Oops...",
														obj.message,
														"error");
									        }
									    } else {

									        sweetAlert(
													"Oops...",
													"数据加载失败!",
													"error");
									    }
									});
				        })
				    });

				    $("#btn_query").click(function () {
				        $("#userTable").bootstrapTable('refresh');
				    });

				    $("#btn_edit")
				        .click(
				            function () {
				                var arrselections = $("#userTable")
				                    .bootstrapTable('getSelections');
				                if (arrselections.length > 1) {
				                    sweetAlert("Oops...",
				                        "只能选择一行进行编辑!", "error");
				                    return;
				                }
				                if (arrselections.length <= 0) {
				                    sweetAlert("Oops...", "请选择有效数据!",
				                        "error");
				                    return;
				                }
				                $("#edit_nickname").val(
				                    arrselections[0].nickname);
				                $("#edit_userName").val(
				                    arrselections[0].userName);
				                $("#edit_phone").val(
				                    arrselections[0].phone);
				                $("#userId").val(
				                    arrselections[0].userId)
				                $("#edit_store").val(arrselections[0].storeId).trigger("change");
				                $("#editMan")
				                    .prop(
				                        "checked",
				                        arrselections[0].gender == "p_gender_male" ? true
				                        : false);
				                $("#editWoman")
				                    .prop(
				                        "checked",
				                        arrselections[0].gender == "p_gender_male" ? false
				                        : true);
				                $('#editModal').modal();

				                $('#editForm')
				                    .bootstrapValidator({
				                        message: 'This value is not valid',
				                        feedbackIcons: {
				                            valid: 'glyphicon glyphicon-ok',
				                            invalid: 'glyphicon glyphicon-remove',
				                            validating: 'glyphicon glyphicon-refresh'
				                        },
				                        fields: {
				                            edit_nickname: {
				                                validators: {
				                                    notEmpty: {
				                                        message: '用户昵称不能为空'
				                                    }
				                                }
				                            },
				                            edit_userName: {
				                                validators: {
				                                    notEmpty: {
				                                        message: '用户名不能为空'
				                                    }
				                                }
				                            },
				                            edit_phone: {
				                                validators: {
				                                    notEmpty: {
				                                        message: '手机号不能为空'
				                                    },
				                                    regexp: {
				                                        regexp: /^[1]+[3,5,8]+\d{9}/,
				                                        message: '手机号格式不正确'
				                                    }
				                                }
				                            }
				                        }
				                    })
				                    .on('success.form.bv', function (e) {
				                        // Prevent form submission
				                        e.preventDefault();

				                        // Get the form instance
				                        var $form = $(e.target);

				                        // Get the BootstrapValidator instance
				                        var bv = $form.data('bootstrapValidator');

				                        $
				                            .post(
				                                "updatebyprimarykeyselective",
				                                {
				                                    "nickname": $(
				                                            "#edit_nickname")
				                                        .val(),
				                                    "userName": $(
				                                            "#edit_userName")
				                                        .val(),
				                                    "phone": $(
				                                            "#edit_phone")
				                                        .val(),
				                                    "accountPwd": $(
				                                            "#edit_pwd")
				                                        .val(),
				                                    "gender": $(
				                                            "#editMan")
				                                        .prop(
				                                            'checked') ? "p_gender_male"
				                                        : "p_gender_female",
				                                    "beSupervisor": 0,
				                                    "storeId": $("#edit_store").val(),
				                                    "userId": $(
				                                            "#userId")
				                                        .val()
				                                },
				                                function (data, status) {
				                                    if (status == "success") {
				                                        var obj = JSON
				                                            .parse(data);
				                                        if (obj.success == 0) {
				                                            window.location
				                                                .reload();
				                                        } else {
				                                            sweetAlert(
				                                                "Oops...",
				                                                obj.message,
				                                                "error");
				                                        }
				                                    } else {

				                                        sweetAlert(
				                                            "Oops...",
				                                            "数据加载失败!",
				                                            "error");
				                                    }
				                                });
				                    })
				            });

				    $("#btn_delete").click(
                            function () {
                                var arrselections = $("#userTable")
                                        .bootstrapTable('getSelections');
                                if (arrselections.length > 1) {
                                    sweetAlert("Oops...", "只能选择一行进行编辑!",
                                            "error");
                                    return;
                                }
                                if (arrselections.length <= 0) {
                                    sweetAlert("Oops...", "请选择有效数据!", "error");
                                    return;
                                }
                                userId = arrselections[0].userId;
                                sweetAlert({
                                    title: "注销用户",
                                    text: "您确定要注销此用户吗？",
                                    type: "warning",
                                    showCancelButton: true,
                                    animation: 'slide-from-top',
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "注销!",
                                    cancelButtonText: "取消",
                                    closeOnConfirm: false
                                }, function () {
                                    $.post("cancelUser", {
                                        "userId": userId
                                    }, function (data, status) {
                                        if (status == "success") {
                                            var obj = JSON.parse(data);
                                            if (obj.success == 0) {
                                                window.location.reload();
                                            } else {
                                                sweetAlert("Oops...",
                                                        obj.message, "error");
                                            }
                                        } else {
                                            $.growlUI('提示', '数据加载失败');
                                        }
                                    });
                                });

				
					$.ajax({  
					    type: "POST",  
					    url:"/store/exp/expstoreinfo",
					    dataType:"json",
					    success: function(data){
					    	$("#selstore").select2({
								data : data
							});
					    	$("#add_store").select2({
								data : data
							});
					    	$("#edit_store").select2({
								data : data
							});
					   } 
					});    
				

				    $.ajax({
				        type: "POST",
				        url: "/store/exp/expstoreinfo",
				        dataType: "json",
				        success: function (data) {

				            $("#selstore").select2({
				                data: data
				            });
				            $("#add_store").select2({
				                data: data
				            });
				            $("#edit_store").select2({
				                data: data
				            });
				        }
				    });

				    $("#btn_initPwd").click(
                            function () {
                                var arrselections = $("#userTable")
                                        .bootstrapTable('getSelections');
                                if (arrselections.length > 1) {
                                    sweetAlert("Oops...", "只能选择一行进行编辑!",
                                            "error");
                                    return;
                                }
                                if (arrselections.length <= 0) {
                                    sweetAlert("Oops...", "请选择有效数据!", "error");
                                    return;
                                }
                                var userName = arrselections[0].userName;
                                sweetAlert({
                                    title: "重置密码",
                                    text: "您确定要重置密码吗？",
                                    type: "warning",
                                    showCancelButton: true,
                                    animation: 'slide-from-top',
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "确定!",
                                    cancelButtonText: "取消",
                                    closeOnConfirm: false
                                }, function () {
                                    $.post("initPwd", {
                                        "userName": userName
                                    }, function (data, status) {
                                        if (status == "success") {
                                            var obj = JSON.parse(data);
                                            if (obj.success == 0) {
                                                window.location.reload();
                                            } else {
                                                sweetAlert("Oops...",
                                                        obj.message, "error");
                                            }
                                        } else {
                                            $.growlUI('提示', '数据加载失败');
                                        }
                                    });
                                });

                            });

				});
