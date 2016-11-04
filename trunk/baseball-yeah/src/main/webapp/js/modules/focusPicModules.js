define([ 'base', 'auditUserModules' ], function(base, audit) {
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
				url : '/manage/focuspic/getall',
				method : 'post',
				queryParams : function(params) {
					return $.extend(params, {
						adTitle : $("#title", panel).val(),
						adType : $("#adType", panel).val(),
						userType : $("#userType", panel).val()
					});
				},
				columns : [ {
					checkbox : true
				}, {
					field : 'adTitle',
					title : '标题',
					sortable : true
				}, {
					field : 'adType',
					title : '广告类型',
					formatter : function(value, row, index) {
						switch (value) {
						case 1:
							return "首页图片";
						case 2:
							return "其他";
						default:
							return "";
						}
					},
					sortable : true
				}, {
					field : 'userType',
					title : '用户类型',
					formatter : function(value, row, index) {
						switch (value) {
						case 1:
							return "货源";
						case 2:
							return "门店";
						case 3:
							return "众包";
						case 4:
							return "收件人";
						default:
							return "";
						}
					},
					sortable : true
				}, {
					field : 'url',
					title : '跳转url',
					sortable : true
				}, {
					field : 'createTime',
					title : '创建时间',
					sortable : true
				}, {
					field : 'adId',
					title : 'adId',
					visible : false
				} ]
			}, '#picTable', panel);

			$("#btn_add", panel).click(function() {
				self.add(panel);
			});
			$("#btn_delete", panel).click(function() {
				self.remove(panel);
			});
			$("#btn_query", panel).click(function() {
				// $("#picTable", panel).bootstrapTable('refresh');
				$("#picTable", panel).bootstrapTable('selectPage', 1);
			});

			$("#clearSearch", panel).click(function() {
				base.reset(".main-box-header");
			});
			// $("#buttonsave", panel).click(function() {
			// self.create(panel);
			// });

			$('#addModal', panel).on('shown.bs.modal', function() {
				$('#addForm', panel).data('bootstrapValidator').resetForm(true);
				window.myDropzone.removeAllFiles();
				window.dropsuccess = "";
			})
			window.myDropzone = new Dropzone("#myId", {
				url : "/manage/focuspic/uploadFile",
				maxFiles : 1,
				addRemoveLinks : true,
				dictRemoveFile : "删除",
				dictDefaultMessage : "将文件拖至此处或点击上传",
				dictMaxFilesExceeded : "只能上传一张图片",
				success : function(data, data2) {
					window.dropsuccess = data2.data.id;
				},
				init : function() {
					this.on("maxfilesexceeded", function(file) {
						this.removeFile(file);
						base.error("只能上传一张图片!");
					});
				}
			});

		},
		add : function(panel) {
			var self = this;
			$('#addModal', panel).modal({
				keyboard : false,
				backdrop : 'static'
			});
			base.validator({
				fields : {
					adTitle : {
						validators : {
							notEmpty : {
								message : '标题不能为空'
							}
						}
					},
					add_adType : {
						validators : {
							notEmpty : {
								message : '广告类型不能为空'
							}
						}
					},
					add_userType : {
						validators : {
							notEmpty : {
								message : '用户类型不能为空'
							}
						}
					}
				}
			}, "#addForm", self.create, panel)
		},
		create : function(panel) {
			if (dropsuccess == "") {
				base.error("请上传图片!");
				return;
			}

			$.post("/manage/focuspic/add", {
				adTitle : $("#add_adTitle", panel).val(),
				url : $("#add_url", panel).val(),
				adType : $("#add_adType", panel).val(),
				userType : $("#add_userType", panel).val(),
				adImg : dropsuccess
			}, function(data, status) {
				if (status == "success") {
					if (data.success == 0) {
						// $("#picTable", panel).bootstrapTable('refresh');
						$("#picTable", panel).bootstrapTable('selectPage', 1);
						$('#addModal', panel).modal('hide');
						base.success("添加成功！")
					} else {
						base.error(data.message);
					}
				} else {
					base.error("数据加载失败!");
				}
			});
		},
		remove : function(panel) {
			var arrselections = $("#picTable", panel).bootstrapTable('getSelections');

			if (arrselections.length <= 0) {
				base.error("请选择有效数据!");
				return;
			}

			base.cancel({
				title : "删除焦点图",
				text : "您确定要删除焦点图？"
			}, function() {
				$.post("/manage/focuspic/del", {
					"adId" : arrselections[0].adId
				}, function(data, status) {
					if (status == "success") {
						if (data.success == 0) {
							// $("#picTable", panel).bootstrapTable('refresh');
							$("#picTable", panel).bootstrapTable('selectPage', 1);
							base.success("删除成功！")
						} else {
							base.error(data.message);
						}
					} else {
						base.error("删除失败");
					}
				});
			});
		}
	};
});