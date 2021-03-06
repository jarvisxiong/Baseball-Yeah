define(['base'], function (base) {
    /**
     * 私有成员定义区域
     */
    var datatable;
    return {
        init: function (panel) {
            var self = this;
            $('#starttimePicker', panel).datetimepicker({
       			format : 'yyyy-mm-dd',
       			autoclose : true,
       			startView : 2,
       			minView : 0,
       			todayHighlight : true,
       			endDate : new Date()
       		});
            $("#startdate", panel).val((new Date((new Date()).getTime())).Format("yyyy-MM-dd"));
            datatable = base.datagrid({
                url: '/report/phonecount/cityPhoneCount',
                queryParams: function (params) {
                    return $.extend(params,
                        {
                            selectDate: $(
                                "#startdate", panel)
                                .val(),
                             cityId:$("#selcity", panel).val()
                        });
                },
            	method : "post",
    			contentType: "application/x-www-form-urlencoded",
    			dataType: "json",
    			cache:false,
    			sidePagination:"client",
    			onLoadSuccess:function(data){
					  //表格控件不支持高度自适应
                  var tableHeight = 105+$("#phoneCityTable", panel).find("thead").height() + $("#phoneCityTable", panel).find("tbody").height()
                  +$("#phoneCityTable", panel).parent().parent().parent().parent().find(".clearfix").height();
                  if (!data||data.length == 0) {//如果没有数据 给固定文字的高度
                      tableHeight = 105;
                  }
             
                  $("#phoneCityTable", panel).bootstrapTable('resetView', {"height": tableHeight});
                 
				},
                columns: [
                    {
                        field: 'cityName',
                        title: '城市',
                        sortable: true,
                        width: 150
                    },
                    /*{
                        title: '日期',
                        sortable: true,
                        width: 150,
                        formatter:function(value, row, index){
                        	return $("#startdate").val();
                        }
                    },*/
                    {
                        field: 'colName',
                        title: '学校名称',
                        sortable: true
                    },
                    {
                        field: 'userName',
                        title: '货源帐号',
                        sortable: true
                    },
                    {
                        field: 'totalPhoneNum',
                        title: '手机号总数',
                        sortable: true
                    },
                    {
                        field: 'totalRegNum',
                        title: '注册总数',
                        sortable: true
                    },
                    {
                    	field:'regRate',
                    	title:'注册率',
                    	 sortable: true,
                    	 formatter:function(value,row,index){
                    		 return '<span style="color:red;">'+row.regRate.toPercent()+'</sapn>';
                    	 }
                    },
                    {
                        field: 'newNum',
                        title: '新增号码数',
                        sortable: true,
                        width: 150
                    },
                    {
                        field: 'growRate',
                        title: '增长率',
                        sortable: true,
                        width: 150,
                        formatter:function(value,row,index){
                   		 return '<span style="color:green;">'+row.growRate.toPercent()+'</sapn>';
                   	 }
                    }
                    /*,
                    {
                        title: '操作',
                        width: 150,
                        formatter:function(value, row, index)
                        {
                        	return '<a onclick="auditModal();" style="cursor:pointer; color:blue;">审核</a>'
                        }
                    }*/
                  ]
            }, '#phoneCityTable', panel);
            $("#btn_query", panel).click(function(){
           	 datatable.bootstrapTable('refresh');
            });
            $("#clearSearch", panel).click(function () {
                base.reset(".main-box-header");
                $('#selcity', panel).select2("val", null);
            });
            $.ajax({
                type: "POST",
                url: "/manage/province/getcity",
                dataType: "json",
                success: function (data) {
                    $("#selcity", panel).select2({
                        data: data,
                        placeholder: '请选择',
                        allowClear: true
                    });
                    $('#selcity', panel).select2("val", null);
                }
            });
        }
    };
});
