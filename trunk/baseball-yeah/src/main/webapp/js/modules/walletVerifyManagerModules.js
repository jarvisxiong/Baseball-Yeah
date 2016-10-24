define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
   
    return {
        init: function (args) {
            // / <summary>
            // / 模块初始化方法
            // / </summary>
            // / <param name="args">初始化时传入的参数</param>
            var self = this;
            datatable = base.datagrid({
                url: '/wallet/verify/postWalletVerifyList',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            userName: $(
                                "#userName")
                                .val(),
                            phone: $("#phone")
                                .val(),
                            verifyStartTime:$("#startdate2").val(),
                            verifyEndTime:$("#enddate2").val(),
                            userType:$("#userType").val(),
                            verifyStatus:$("#verifyStatus").val(),
                            signupStartTime:$("#startdate").val(),
                            signupEndTime:$("#enddate").val()
                        });
                },
            	method : "post",
    			contentType: "application/x-www-form-urlencoded",
    			dataType: "json",
    			cache:false,
    			sidePagination:"client",
    			onLoadSuccess:function(data){
					  //表格控件不支持高度自适应
                  var tableHeight = 105+$("#walletVerifyTable").find("thead").height() + $("#walletVerifyTable").find("tbody").height()
                  +$("#walletVerifyTable").parent().parent().parent().parent().find(".clearfix").height();
                  if (!data||data.length == 0) {//如果没有数据 给固定文字的高度
                      tableHeight = 105;
                  }
             
                  $("#walletVerifyTable").bootstrapTable('resetView', {"height": tableHeight});
                 
				},
				onPageChange:function(){
					datatable.bootstrapTable('refresh');
				},
			
                columns: [
                          {checkbox:true},
                    {
                        field: 'phone',
                        title: '登录帐号',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'realName',
                        title: '姓名',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'signupTime',
                        title: '提交时间',
                        sortable: true
                    },
                    {
                        field: 'verifyTime',
                        title: '审核时间',
                        sortable: true
                    },
                    {
                        field: 'auditName',
                        title: '审核人',
                        sortable: true
                    },
                    {
                    	field:'useTypeName',
                    	title:'版本类型'
                    },
                    {
                        field: 'verifyStatusName',
                        title: '状态',
                        sortable: true,
                        width: 150
                    }/*,
                    {
                        title: '操作',
                        width: 150,
                        formatter:function(value, row, index)
                        {
                        	return '<a onclick="auditModal();" style="cursor:pointer; color:blue;">审核</a>'
                        }
                    }*/
                  ]
            }, '#walletVerifyTable');
            $("#btn_query").click(function(){
           	 datatable.bootstrapTable('refresh');
            });
            $("#btn_audit").click(function () {
                self.audit();
            });
            $("#auditPost").click(function () {
                self.auditPost();
            })
         /*   $(function() {
	            $("#startdate").val((new Date()).Format("yyyy-MM-dd") + " 00:00:00");
	            $("#enddate").val((new Date()).Format("yyyy-MM-dd") + " 23:59:59");
	        });
          */
        },
        audit:function(){
        	
        	var self = this;
             var arrselections = $("#walletVerifyTable")
                 .bootstrapTable('getSelections');
             if (arrselections.length > 1) {
                 base.error("只能选择一行进行编辑!");
                 return;
             }
             if (arrselections.length <= 0) {
                 base.error("请选择有效数据!");
                 return;
             }
             if (arrselections[0].verifyStatus == 0) {
                 base.error("审核未提交!");
                 return;
             }
             $.post("/wallet/verify/getwalletauditinfo", {
                 "userId": arrselections[0].userId
             }, function (data) {
                 if (data.success == 0) {
                     self.getUserInfo(data.data)
                 }
                 else {
                     base.error(data.message);
                 }
             });
        },
        getUserInfo: function (model) {
            var self = this;
            $("#userId").val(model.userId);
            $("#userType").val(model.userType);
            $("#lableUserName").html(model.userName);
            switch (model.verifyStatus) {
                case    0:
                    $("#lableVerifyStatus").html("未完善");
                    break;
                case   1:
                    $("#lableVerifyStatus").html("审核中");
                    break;
                case    2:
                    $("#lableVerifyStatus").html("审核通过");
                    break;
                case    3:
                    $("#lableVerifyStatus").html("审核失败");
                    break;
            }
            if (model.auditResultInfo) {
                $("#f1").prop("checked", model.auditResultInfo.f1 == "0" ? true : false);
                $("#fe1").prop("checked", model.auditResultInfo.f1 == "0" ? false : true);
                $("#f2").prop("checked", model.auditResultInfo.f2 == "0" ? true : false);
                $("#fe2").prop("checked", model.auditResultInfo.f2 == "0" ? false : true);
             /*   $("#f3").prop("checked", model.auditResultInfo.f3 == "0" ? true : false);
                $("#fe3").prop("checked", model.auditResultInfo.f3 == "0" ? false : true);
                $("#f4").prop("checked", model.auditResultInfo.f4 == "0" ? true : false);
                $("#fe4").prop("checked", model.auditResultInfo.f4 == "0" ? false : true);
                $("#f5").prop("checked", model.auditResultInfo.f5 == "0" ? true : false);
                $("#fe5").prop("checked", model.auditResultInfo.f5 == "0" ? false : true);
                $("#f6").prop("checked", model.auditResultInfo.f6 == "0" ? true : false);
                $("#fe6").prop("checked", model.auditResultInfo.f6 == "0" ? false : true);*/
                $("#f7").prop("checked", model.auditResultInfo.f7 == "0" ? true : false);
                $("#fe7").prop("checked", model.auditResultInfo.f7 == "0" ? false : true);
                $("#f8").prop("checked", model.auditResultInfo.f8 == "0" ? true : false);
                $("#fe8").prop("checked", model.auditResultInfo.f8 == "0" ? false : true);
             /*   $("#f9").prop("checked", model.auditResultInfo.f9 == "0" ? true : false);
                $("#fe9").prop("checked", model.auditResultInfo.f9 == "0" ? false : true);
                $("#f10").prop("checked", model.auditResultInfo.f10 == "0" ? true : false);
                $("#fe10").prop("checked", model.auditResultInfo.f10 == "0" ? false : true);*/
            }else {
                $("#f1").prop("checked", true );
                $("#fe1").prop("checked", false);
                $("#f2").prop("checked", true);
                $("#fe2").prop("checked", false);
             /*   $("#f3").prop("checked", true);
                $("#fe3").prop("checked", false);
                $("#f4").prop("checked", true);
                $("#fe4").prop("checked",false);
                $("#f5").prop("checked",true);
                $("#fe5").prop("checked", false);
                $("#f6").prop("checked", true);
                $("#fe6").prop("checked", false);*/
                $("#f7").prop("checked", true);
                $("#fe7").prop("checked",false);
                $("#f8").prop("checked",true);
                $("#fe8").prop("checked", false);
            /*    $("#f9").prop("checked",true);
                $("#fe9").prop("checked", false);
                $("#f10").prop("checked", true);
                $("#fe10").prop("checked", false);*/
            }

            $("#aduitrealName").val(model.realName);
            $("#aduitidentityNumber").val(model.identityNumber);
            $("#verifyRemark").val(model.verifyRemark);
            $("#labelimg1").unbind("click");
            $("#labelimg2").unbind("click");
            if (model.photoList) {
                for (var i = 0; i < model.photoList.length; i++) {
                    if (model.photoList[i].type == 1) {
                        (function () {
                            var url = model.photoList[i].url;
                            $("#labelimg1").unbind("click");
                            $("#labelimg1").click(function () {
                                self.dialogImg("手持身份证正面照片", url)
                            });
                        })();
                    }
                    if (model.photoList[i].type == 2) {
                        (function () {
                            var url = model.photoList[i].url;
                            $("#labelimg2").unbind("click");
                            $("#labelimg2").click(function () {
                                self.dialogImg("身份证正面照片", url);
                            });
                        })();
                    }
                }
            }

           // $('#auditcollage').bootstrapTable('load', model.collegeList);


            $('#auditModal').modal();
        },
        auditPost: function () {
            $.post("/wallet/verify/walletaudit", {
                "userId": $("#userId").val(),
                "userType": $("#userType").val(),//用户类型 1 货源 2 众包
                "verifyUserId": "0001",//审核人
                "verifyUserName": "admin",//审核人
                "verifyRemark": $("#verifyRemark").val(),
                "verifyStatus": ($("#fe1").prop('checked') && "3") || ($("#fe2").prop('checked') && "3") 
                || ($("#fe7").prop('checked') && "3") || ($("#fe8").prop('checked') && "3")|| "2",
                "verifyInfo": '{"f1":' + ($("#f1").prop('checked') ? '"0"' : '"-1"' ) + ',"f2":' + ($("#f2").prop('checked') ? '"0"' : '"-1"') + ',"f3":"0","f4":"0","f5":"0","f6":"0","f7":' + ($("#f7").prop('checked') ? '"0"' : '"-1"') + ',"f8":' + ($("#f8").prop('checked') ? '"0"' : '"-1"') + ',"f9":"0","f10":"0"}'
            }, function (data) {
                    $("#walletVerifyTable").bootstrapTable('refresh');
                    $('#auditModal').modal('hide');
                    sweetAlert("提示信息",data.message,"info");
            });
        },
        dialogImg: function (title, url) {
            BootstrapDialog.show({
                cssClass: 'img-dialog',
                title: title,
                message: '<div style="text-align: center;"><img src="' + url + '" style="width: 670;height: 500;"></div>'
            });
        }

    };
});
