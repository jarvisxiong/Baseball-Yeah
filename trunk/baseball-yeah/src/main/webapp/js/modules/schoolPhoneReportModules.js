define([ 'base' ], function(base) {
	var datatable;
	var initModalTable=function(row){
		$("#showModal").modal('show');
		 dataObject= base.datagrid({
			url : '/report/waybill/phoneCountDetail',
			method : "post",
			contentType: "application/x-www-form-urlencoded",
			dataType: "json",
			height:760,
			striped:true,
			pageSize:15,
			cache:false,
			pagination:true,
			search:true,
			showExport:true,
			exportDataType:'all',
			sidePagination:"client",
			onSearch: function (text) {
            	//表格控件不支持高度自适应
                var tableHeight = $('#schoolPhoneDetailReport').find("thead").height() + $('#schoolPhoneDetailReport').find("tbody").height()
                    + 3 + $('#schoolPhoneDetailReport').parent().parent().parent().parent().find(".clearfix").height();
				if ($('#schoolPhoneDetailReport').parent().parent().parent().parent().find(".clearfix").height() == 0) {
					tableHeight += 56;
				}
                if (this.search) {//如果有自带的功能 把自带功能的元素高度加上
                    tableHeight += ($('#schoolPhoneDetailReport').parent().parent().parent().parent().find(".pull-right").height() + 20);
                }
                if($('#schoolPhoneDetailReport').bootstrapTable('getData').length==0){//如果没有数据 给固定文字的高度
			        tableHeight=105;
			    }
				tableHeight+=43;
                $('#schoolPhoneDetailReport').bootstrapTable('resetView', {"height": tableHeight});
                if( tableHeight > 900){//当高度过高 刷新外面iframe高度
                    $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                }
            },
			queryParams : function(params) {
				return $.extend(params, {
					  data:'{"collegeId":"'+row.collegeId+'"}',
				});
			},
			columns : [ {
				field : 'collegeName',
				title : '学校名称',
				sortable : true,
				footerFormatter: function(data) {
			        return "<span style='color:red'>合计："+data.length+"</span>";
			    }
			}, {
				field : 'phone',
				title : '手机号',
				sortable : true
			} ]
		},"#schoolPhoneDetailReport");
		 dataObject.bootstrapTable('refresh',{query: { data:'{"collegeId":"'+row.collegeId+'"}'}});
	}
	return {
		init : function(args) {
			var self = this;
			datatable = base.datagrid({
				url : '/report/waybill/phoneCountInfo',
				method : "get",
				//contentType: "application/x-www-form-urlencoded",
				sidePagination:"client",
				height:800,
				pageSize:10,
				showFooter:true,
				showExport:true,
				pagination:true,
				exportDataType:'all',
				exportTypes:['excel'],
				onLoadSuccess:function(data){
					  //表格控件不支持高度自适应
                    var tableHeight = 105+$("#schoolPhoneReportTable").find("thead").height() + $("#schoolPhoneReportTable").find("tbody").height()
                    +$("#schoolPhoneReportTable").parent().parent().parent().parent().find(".clearfix").height();
                    if (!data||data.length == 0) {//如果没有数据 给固定文字的高度
                        tableHeight = 105;
                    }
                   /* if (this.search || this.showExport) {//如果有自带的功能 把自带功能的元素高度加上
                        if ($("#schoolPhoneReportTable").bootstrapTable('getData').length == 0) {//如果没有数据 给固定文字的高度
                            tableHeight = 105;
                        }
                        tableHeight += ($("#schoolPhoneReportTable").parent().parent().parent().parent().find(".pull-left").height() + 20);
                    }*/
                    $("#schoolPhoneReportTable").bootstrapTable('resetView', {"height": tableHeight});
                    /*if (tableHeight > 900) {//当高度过高 刷新外面iframe高度
                        $(parent.document).find("#mainFrame").height(document.body.scrollHeight);
                    }*/
				},
				onDblClickRow:  function(row){initModalTable(row);},
				queryParams : function(params) {
					return $.extend(params, {
						  data:'{"collegeId":"'+$("#collegeId").val()+'"}',
					});
				},
				columns : [ {
					field : 'collegeName',
					title : '学校名称',
					sortable : true,
					footerFormatter: function(data) {
				        return "<span style='color:red'>合计："+data.length+"</span>";
				    }

				}, {
					field : 'phoneCount',
					title : '手机号统计数',
					sortable : true,
					footerFormatter:  function(data) {
				        field = this.field;
				        var total_sum = data.reduce(function(sum, row) {
				            return (sum) + (row[field] || 0);
				        }, 0);
				        return "<span style='color:red'>"+total_sum+"</span>";
				    }
				}
				]
			}, '#schoolPhoneReportTable');
			$("#btn_query").click(function() {
				datatable.bootstrapTable('refresh');
			});
			
			  $(window).resize(function () {
			        $('#schoolPhoneReportTable').bootstrapTable('resetView');
			    });
			 $("#schoolPhoneReportTable #fixed-table-footer").click(function(){
				 initModalTable();
			 });
			
				
		}
	};
	
});