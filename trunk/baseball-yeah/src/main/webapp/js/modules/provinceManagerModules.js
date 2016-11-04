define(['base'], function (base) {
    /**
       * 私有成员定义区域
       */
	function setting(panel){
			return {check : {
				enable : false,
				chkStyle :"checkbox"
			},
			data : {
				simpleData : {
					idKey: "id",
					pIdKey: "pId",
					enable : true
				}
			},
			callback: {
				onClick: function(event,treeId,treeNode){
					$("#id",panel).val(treeNode.id);//当前选中的id
					$("#pId",panel).val(treeNode.pId);//当前选中的pId
				},
				onExpand: function(event,treeId,treeNode){
					var tableHeight = $("#mainTree",panel).height();
					if (tableHeight > 900) {
                        $(parent.document).find("#mainFrame",panel).height(document.body.scrollHeight);
                    }
				}
			},
			async: {
				enable: true,
				url: function (treeId, treeNode){
					var url = "";
					//区域id
					if(treeNode.id.indexOf("a_") != -1){
						url = "/manage/province/selectprovincesbyareaid?areaId="+treeNode.id.slice(2);
					}
					//省份id
					else if(treeNode.id.indexOf("p_") != -1){
						url = "/manage/city/selectcitiesbyprovinceid?provinceId="+treeNode.id.slice(2);
					}
					//城市id
					else if(treeNode.id.indexOf("c_") != -1){
						url = "/manage/county/selectcountiesbycityid?cityId="+treeNode.id.slice(2);
					}
				    return url;
				},
				autoParam: ["id", "name"],
				type:"post"
			}
		}};
    function initTree(panel){
    	$.get("/manage/area/getall", function(data, status) {
			$(document).ready(function() {
				$.fn.zTree.init($("#treeDemo",panel), setting(panel), data);
			});
    	});
    	//请求区域id和name
		$.ajax({
                type: "GET",
                url: "/manage/area/getarea",
                dataType: "json",
                success: function (data) {
                	$("#add_p_areaId",panel).empty();
                    $("#add_p_areaId",panel).select2({
                        data: data
                    });
                    $("#edit_p_areaId",panel).empty();
                    $("#edit_p_areaId",panel).select2({
                        data: data
                    });
                }
        });
    	 //请求省份id和name
		$.ajax({
                type: "GET",
                url: "/manage/province/getprovince",
                dataType: "json",
                success: function (data) {
                	$("#add_c_provinceId",panel).empty();
                    $("#add_c_provinceId",panel).select2({
                        data: data
                    });
                    $("#edit_c_provinceId",panel).empty();
                    $("#edit_c_provinceId",panel).select2({
                        data: data
                    });
                    $("#add_co_provinceId",panel).empty();
                    $("#add_co_provinceId",panel).select2({
                        data: data
                    });
                    $("#edit_co_provinceId",panel).empty();
                    $("#edit_co_provinceId",panel).select2({
                        data: data
                    });
                }
        });
		//请求城市id和name
		$.ajax({
                type: "GET",
                url: "/manage/city/getcity",
                dataType: "json",
                success: function (data) {
                	$("#add_co_cityId",panel).empty();
                    $("#add_co_cityId",panel).select2({
                        data: data
                    });
                    $("#edit_co_cityId",panel).empty();
                    $("#edit_co_cityId",panel).select2({
                        data: data
                    });
                }
        });
    }
    function add_a_validate(panel) {
            return {fields: {
            	add_a_areaName: {
                    validators: {
                        notEmpty: {
                            message: '区域名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                add_a_businessPrincipal: {
                    validators: {
                    	notEmpty: {
                            message: '商务负责人不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                add_a_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
                    	regexp: {
                            regexp: /^\d{1,5}$/,
                            message: '序号格式不正确'
                        },
                        callback: {
	                        message: '序号格式不正确,请输入0-32767内数字',
	                        callback: function(value, validator) {
	                        	var sortNo = $("#add_a_sortNo",panel).val();
	                        	if(sortNo && sortNo>0 && sortNo < 32767){
	                        		return true;
	                        	} 
	                        	return false;
	                        }
	                    }
                    }
                },
                add_a_contactPhone: {
                    validators: {
                    	notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^[1]+[3,4,5,7,8]+\d{9}$/,
                            message: '手机号格式不正确'
                        }
                    }
                }
            }
        }};
    function edit_a_validate(panel){
            return {fields: {
            	edit_a_areaName: {
                    validators: {
                        notEmpty: {
                            message: '区域名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                edit_a_businessPrincipal: {
                    validators: {
                    	notEmpty: {
                            message: '商务负责人不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                edit_a_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
                        regexp: {
                            regexp: /^\d{1,5}$/,
                            message: '序号格式不正确'
                        },
                        callback: {
	                        message: '序号格式不正确,请输入0-32767内数字',
	                        callback: function(value, validator) {
	                        	var sortNo = $("#edit_a_sortNo",panel).val();
	                        	if(sortNo && sortNo>0 && sortNo < 32767){
	                        		return true;
	                        	} 
	                        	return false;
	                        }
	                    }
                    }
                },
                edit_a_contactPhone: {
                    validators: {
                    	notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^[1]+[3,4,5,7,8]+\d{9}$/,
                            message: '手机号格式不正确'
                        }
                    }
                }
            }
        }};
	function add_p_validate(panel) {
            return {fields: {
            	add_p_provinceName: {
                    validators: {
                        notEmpty: {
                            message: '省份名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                add_p_areaId: {
                    validators: {
                         notEmpty: {
                             message: '归属区域不能为空'
                         }
                    }
                },
                add_p_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
		                regexp: {
		                    regexp: /^\d{1,5}$/,
		                    message: '序号格式不正确'
		                },
		                callback: {
		                    message: '序号格式不正确,请输入0-32767内数字',
		                    callback: function(value, validator) {
		                    	var sortNo = $("#add_p_sortNo",panel).val();
		                    	if(sortNo && sortNo>0 && sortNo < 32767){
		                    		return true;
		                    	} 
		                    	return false;
		                    }
		                }
                    }
                }
            }
        }};
	function edit_p_validate(panel) {
            return {fields: {
            	edit_p_provinceName: {
                    validators: {
                        notEmpty: {
                            message: '省份名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                edit_p_areaId: {
                    validators: {
                         notEmpty: {
                             message: '归属区域不能为空'
                         }
                    }
                },
                edit_p_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
		                regexp: {
		                    regexp: /^\d{1,5}$/,
		                    message: '序号格式不正确'
		                },
		                callback: {
		                    message: '序号格式不正确,请输入0-32767内数字',
		                    callback: function(value, validator) {
		                    	var sortNo = $("#edit_p_sortNo",panel).val();
		                    	if(sortNo && sortNo>0 && sortNo < 32767){
		                    		return true;
		                    	} 
		                    	return false;
		                    }
		                }
                    }
                }
            }
        }};
	function add_c_validate(panel){
            return {fields: {
            	add_c_cityName: {
                    validators: {
                        notEmpty: {
                            message: '城市名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                add_c_provinceId: {
                    validators: {
                    	notEmpty: {
                            message: '归属省份不能为空'
                        }
                    }
                },
                add_c_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
                        regexp: {
                            regexp: /^\d{1,5}$/,
                            message: '序号格式不正确'
                        },
                        callback: {
	                        message: '序号格式不正确,请输入0-32767内数字',
	                        callback: function(value, validator) {
	                        	var sortNo = $("#add_c_sortNo",panel).val();
	                        	if(sortNo && sortNo>0 && sortNo < 32767){
	                        		return true;
	                        	} 
	                        	return false;
	                        }
	                    }
                    }
                },
                add_c_telZoneCode: {
                    validators: {
                    	regexp: {
                            regexp: /^\d{1,10}$/,
                            message: '电话区号为1-10为数字'
                        }
                    }
                },
                add_c_postCode: {
                    validators: {
                    	regexp: {
                    		regexp: /^\d{1,10}$/,
                    		message: '邮编为1-10为数字'
                        }
                    }
                }
            }
        }};
	function edit_c_validate(panel){
            return {fields: {
            	edit_c_cityName: {
                    validators: {
                        notEmpty: {
                            message: '城市名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                edit_c_provinceId: {
                    validators: {
                    	notEmpty: {
                            message: '归属省份不能为空'
                        }
                    }
                },
                edit_c_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
                        regexp: {
                            regexp: /^\d{1,5}$/,
                            message: '序号格式不正确'
                        },
                        callback: {
	                        message: '序号格式不正确,请输入0-32767内数字',
	                        callback: function(value, validator) {
	                        	var sortNo = $("#edit_c_sortNo",panel).val();
	                        	if(sortNo && sortNo>0 && sortNo < 32767){
	                        		return true;
	                        	} 
	                        	return false;
	                        }
	                    }
                    }
                },
                edit_c_telZoneCode: {
                    validators: {
                    	regexp: {
                            regexp: /^\d{1,10}$/,
                            message: '电话区号为1-10为数字'
                        }
                    }
                },
                edit_c_postCode: {
                    validators: {
                    	regexp: {
                    		regexp: /^\d{1,10}$/,
                    		message: '邮编为1-10为数字'
                        }
                    }
                }
            }
        }};
	function add_co_validate(panel) {
            return {fields: {
            	add_co_countyName: {
                    validators: {
                        notEmpty: {
                            message: '区县名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                add_co_cityId: {
                    validators: {
                    	notEmpty: {
                            message: '归属城市不能为空'
                        }
                    }
                },
                add_co_provinceId: {
                    validators: {
                    	notEmpty: {
                            message: '归属省份不能为空'
                        }
                    }
                },
                add_co_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
                        regexp: {
                            regexp: /^\d{1,5}$/,
                            message: '序号格式不正确'
                        },
                        callback: {
	                        message: '序号格式不正确,请输入0-32767内数字',
	                        callback: function(value, validator) {
	                        	var sortNo = $("#add_co_sortNo",panel).val();
	                        	if(sortNo && sortNo>0 && sortNo < 32767){
	                        		return true;
	                        	} 
	                        	return false;
	                        }
	                    }
                    }
                },
                add_co_postCode: {
                    validators: {
                    	regexp: {
                    		regexp: /^\d{1,10}$/,
                    		message: '邮编为1-10为数字'
                        }
                    }
                }
            }
        }};
	function edit_co_validate(panel) {
            return {fields: {
            	edit_co_countyName: {
                    validators: {
                        notEmpty: {
                            message: '区县名称不能为空'
                        },
                        regexp: {
                            regexp: /^[\u4E00-\u9FA5a-zA-Z0-9]{1,20}$/,
                            message: '不允许含有空格等其他特殊字符'
                        }
                    }
                },
                edit_co_cityId: {
                    validators: {
                    	notEmpty: {
                            message: '归属城市不能为空'
                        }
                    }
                },
                edit_co_provinceId: {
                    validators: {
                    	notEmpty: {
                            message: '归属省份不能为空'
                        }
                    }
                },
                edit_co_sortNo: {
                    validators: {
                    	notEmpty: {
                            message: '序号不能为空'
                        },
                        regexp: {
                            regexp: /^\d{1,5}$/,
                            message: '序号格式不正确'
                        },
                        callback: {
	                        message: '序号格式不正确,请输入0-32767内数字',
	                        callback: function(value, validator) {
	                        	var sortNo = $("#edit_co_sortNo",panel).val();
	                        	if(sortNo && sortNo>0 && sortNo < 32767){
	                        		return true;
	                        	} 
	                        	return false;
	                        }
	                    }
                    }
                },
                edit_co_postCode: {
                    validators: {
                    	regexp: {
                    		regexp: /^\d{1,10}$/,
                    		message: '邮编为1-10为数字'
                        }
                    }
                }
            }
        }};
    return {
        init: function (panel) {
        	initTree(panel);
        	var self = this;
        	 $("#btn_add_a",panel).click(function () {
 				$("#type",panel).val("area");
                 self.add(panel);
             });
            $("#btn_add_p",panel).click(function () {
				$("#type",panel).val("province");
                self.add(panel);
            });
            $("#btn_add_c",panel).click(function () {
            	$("#type",panel).val("city");
                self.add(panel);
            });
            $("#btn_add_co",panel).click(function () {
            	$("#type",panel).val("country");
                self.add(panel);
            });
            $("#btn_edit",panel).click(function () {
                self.edit(panel);
            });
            $("#btn_delete",panel).click(function () {
                self.remove(panel);
            });
            $('#a_addModal',panel).on('shown.bs.modal', function () {
                $('#a_addForm',panel).data('bootstrapValidator').resetForm(true);
            });
            $('#a_editModal',panel).on('hide.bs.modal', function () {
                $('#a_editForm',panel).data('bootstrapValidator').resetForm(true);
            });
            $('#p_addModal',panel).on('shown.bs.modal', function () {
            	$("#add_p_areaId",panel).val(" ").trigger("change");
                $('#p_addForm',panel).data('bootstrapValidator').resetForm(true);
            });
            $('#p_editModal',panel).on('hide.bs.modal', function () {
                $('#p_editForm',panel).data('bootstrapValidator').resetForm(true);
            });
            $('#c_addModal',panel).on('shown.bs.modal', function () {
            	$("#add_c_provinceId",panel).val(" ").trigger("change");
                $('#c_addForm',panel).data('bootstrapValidator').resetForm(true);
            });
            $('#pc_editModal',panel).on('hide.bs.modal', function () {
                $('#c_editForm',panel).data('bootstrapValidator').resetForm(true);
            });
            $('#co_addModal',panel).on('shown.bs.modal', function () {
            	$("#add_co_provinceId",panel).val(" ").trigger("change");
            	$("#add_co_cityId",panel).val(" ").trigger("change");
                $('#co_addForm',panel).data('bootstrapValidator').resetForm(true);
            });
            $('#co_editModal',panel).on('hide.bs.modal', function () {
                $('#co_editForm',panel).data('bootstrapValidator').resetForm(true);
            });
            var getCityAsyc = function(){
				var provinceId = this.value;
				//请求城市id和name
				$.ajax({
		                type: "GET",
		                url: "/manage/city/getcity?provinceId="+provinceId,
		                dataType: "json",
		                success: function (data) {
		                	$("#add_co_cityId",panel).empty();
		                    $("#add_co_cityId",panel).select2({
		                        data: data
		                    });
		                    var oldId = $("#edit_co_cityId_old",panel).val();
		                    $("#edit_co_cityId",panel).empty();
		                    $("#edit_co_cityId",panel).select2({
		                        data: data
		                    });
		                    if(oldId && oldId != ""){
		                    	$("#edit_co_cityId",panel).val(oldId).trigger("change");
		                    }
		                }
	            });
			}
			$("#add_co_provinceId",panel).on("change", getCityAsyc);
			$("#edit_co_provinceId",panel).on("change",getCityAsyc);
        },
        add: function (panel) {
            var self = this;
            var type = $("#type",panel).val();
            if(type == "area"){
            	$('#a_addModal',panel).modal({
      			    keyboard: false,
      			    backdrop:'static'
      			});
            	base.validator(add_a_validate(panel), "#a_addForm", self.create,panel);
            }else if(type == "province"){
            	$('#p_addModal',panel).modal({
      			    keyboard: false,
      			    backdrop:'static'
      			});
            	base.validator(add_p_validate(panel), "#p_addForm", self.create,panel);
            }else if(type == "city"){
            	$('#c_addModal',panel).modal({
      			    keyboard: false,
      			    backdrop:'static'
      			});
            	base.validator(add_c_validate(panel), "#c_addForm", self.create,panel);
            }else if(type == "country"){
            	$('#co_addModal',panel).modal({
      			    keyboard: false,
      			    backdrop:'static'
      			});
            	base.validator(add_co_validate(panel), "#co_addForm", self.create,panel);
            }
        },
        create: function (panel) {
        	var type = $("#type",panel).val();
        	if(type == "area"){
           	 $.post("/manage/area/add",
                        {"areaName": $("#add_a_areaName",panel).val(),
                            "businessPrincipal": $("#add_a_businessPrincipal",panel).val(),
                            "sortNo": $("#add_a_sortNo",panel).val(),
                            "contactPhone": $("#add_a_contactPhone",panel).val()
                        },function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                	base.success("添加成功");
                              	    $("#a_addModal",panel).modal('hide');
                              	    initTree(panel);
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                        }
                });
           }else if(type == "province"){
            	 $.post("/manage/province/insertprovince",
                         {"provinceName": $("#add_p_provinceName",panel).val(),
                             "areaId": $("#add_p_areaId",panel).val(),
                             "sortNo": $("#add_p_sortNo",panel).val()
                         },function (data, status) {
                             if (status == "success") {
                                 if (data.success == 0) {
                                	 base.success("添加成功");
                               	    $("#p_addModal",panel).modal('hide');
                               	    initTree(panel);
                                 } else {
                                     base.error(data.message);
                                 }
                             } else {
                                 base.error("数据加载失败!");
                             }
                 });
            }else if(type == "city"){
            	$.post("/manage/city/insertcity",
                        {   "cityName": $("#add_c_cityName",panel).val(),
                            "provinceId": $("#add_c_provinceId",panel).val(),
                            "sortNo": $("#add_c_sortNo",panel).val(),
                            "telZoneCode": $("#add_c_telZoneCode",panel).val(),
                            " postCode": $("#add_c_postCode",panel).val()
                        },function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                	base.success("添加成功");
                              	    $("#c_addModal",panel).modal('hide');
                              	    initTree(panel);
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                            }
                });
            }else if(type == "country"){
            	$.post("/manage/county/insertcounty",
                        {   "countyName": $("#add_co_countyName",panel).val(),
                            "cityId": $("#add_co_cityId",panel).val(),
                            "sortNo": $("#add_co_sortNo",panel).val(),
                            "postCode": $("#add_co_postCode",panel).val(),
                        },function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                	base.success("添加成功");
                              	    $("#co_addModal",panel).modal('hide');
                              	    initTree(panel);
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                            }
                });
            }
           
        },
        edit: function (panel) {
        	var self = this;
            var id = $("#id",panel).val();
        	if(!id || id == ""){
        		sweetAlert("Oops...", "请选择一行进行修改!", "error");
				return;
        	}
        	var url = "";
        	if(id.indexOf("a_") != -1){
        		$.post("/manage/area/getbyid",
                        {"areaId": id.slice(2)},function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                              	    $("#edit_a_areaName",panel).val(data.data.areaName);
                              	    $("#edit_a_businessPrincipal",panel).val(data.data.businessPrincipal);
                              	    $("#edit_a_contactPhone",panel).val(data.data.contactPhone);
                              	    $("#edit_a_sortNo",panel).val(data.data.sortNo);
                              	    $("#edit_a_areaId",panel).val(data.data.areaId);
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                        }
                });
        		$('#a_editModal',panel).modal({
    			    keyboard: false,
    			    backdrop:'static'
    			});
        		base.validator(edit_a_validate(panel), '#a_editForm', self.update,panel);
        	}else if(id.indexOf("p_") != -1){
        		$.post("/manage/province/selectprovince",
                        {"provinceId": id.slice(2)},function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                              	    $("#edit_p_provinceName",panel).val(data.data.provinceName);
                              	    $("#edit_p_provinceId",panel).val(data.data.provinceId);
                              	    $("#edit_p_sortNo",panel).val(data.data.sortNo);
                              	    $("#edit_p_areaId",panel).val(data.data.areaId).trigger("change");
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                        }
                });
        		$('#p_editModal',panel).modal({
    			    keyboard: false,
    			    backdrop:'static'
    			});
        		base.validator(edit_p_validate(panel), '#p_editForm', self.update,panel);
        	}else if(id.indexOf("c_") != -1){
        		$.post("/manage/city/selectcity",
                        {"cityId": id.slice(2)},function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                              	    $("#edit_c_cityName",panel).val(data.data.cityName);
                              	    $("#edit_c_sortNo",panel).val(data.data.sortNo);
                              	    $("#edit_c_provinceId",panel).val(data.data.provinceId).trigger("change");
                              	    $("#edit_c_telZoneCode",panel).val(data.data.telZoneCode);
                              	    $("#edit_c_postCode",panel).val(data.data.postCode);
                              		$("#edit_c_cityId",panel).val(data.data.cityId);
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                        }
                });
        		$('#c_editModal',panel).modal({
    			    keyboard: false,
    			    backdrop:'static'
    			});
        		base.validator(edit_c_validate(panel), '#c_editForm', self.update,panel)
        	}else if(id.indexOf("co_") != -1){
        		$.post("/manage/county/selectcounty",
                        {"countyId": id.slice(3)},function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                              	    $("#edit_co_countyName",panel).val(data.data.countyName);
                              	    $("#edit_co_cityId_old",panel).val(data.data.cityId);
                              	    $("#edit_co_provinceId",panel).val(data.data.provinceId).trigger("change");
                              	    $("#edit_co_postCode",panel).val(data.data.postCode);
                              	    $("#edit_co_sortNo",panel).val(data.data.sortNo);
                              	    $("#edit_co_countyId",panel).val(data.data.countyId);
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("数据加载失败!");
                        }
                });
        		$('#co_editModal',panel).modal({
    			    keyboard: false,
    			    backdrop:'static'
    			});
        		base.validator(edit_co_validate(panel), '#co_editForm', self.update,panel)
        	}
        },
        update: function (panel) {
        	var id = $("#id",panel).val();
        	if(id.indexOf("a_") != -1){
        		$.post(
                        "/manage/area/upd",
                        {   "areaId": $("#edit_a_areaId",panel).val(),
                            "areaName": $("#edit_a_areaName",panel).val(),
                            "businessPrincipal": $("#edit_a_businessPrincipal",panel).val(),
                            "sortNo": $("#edit_a_sortNo",panel).val(),
                            "contactPhone": $("#edit_a_contactPhone",panel).val(),
                        },
                        function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                	base.success("更新成功");
                                	initTree(panel);
                                    $("#a_editModal",panel).modal('hide');
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("更新失败!");
                            }
                        });
        	}else if(id.indexOf("p_") != -1){
        		$.post(
                        "/manage/province/updateprovince",
                        {   "areaId": $("#edit_p_areaId",panel).val(),
                            "provinceName": $("#edit_p_provinceName",panel).val(),
                            "provinceId": $("#edit_p_provinceId",panel).val(),
                            "sortNo": $("#edit_p_sortNo",panel).val(),
                        },
                        function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                	base.success("更新成功");
                                	initTree(panel);
                                    $("#p_editModal",panel).modal('hide');
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("更新失败!");
                            }
                        });
        	}else if(id.indexOf("c_") != -1){
        		$.post(
                        "/manage/city/updatecity",
                        {   "cityId": $("#edit_c_cityId",panel).val(),
                            "cityName": $("#edit_c_cityName",panel).val(),
                            "provinceId": $("#edit_c_provinceId",panel).val(),
                            "sortNo": $("#edit_c_sortNo",panel).val(),
                            "postCode": $("#edit_c_postCode",panel).val(),
                            "telZoneCode": $("#edit_c_telZoneCode",panel).val()
                        },
                        function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                	base.success("更新成功");
                                	initTree(panel);
                                    $("#c_editModal",panel).modal('hide');
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("更新失败!");
                            }
                        });
        	}else if(id.indexOf("co_") != -1){
        		$.post(
                        "/manage/county/updatecounty",
                        {   "cityId": $("#edit_co_cityId",panel).val(),
                            "countyName": $("#edit_co_countyName",panel).val(),
                            "sortNo": $("#edit_co_sortNo",panel).val(),
                            "postCode": $("#edit_co_postCode",panel).val(),
                            "countyId": $("#edit_co_countyId",panel).val(),
                        },
                        function (data, status) {
                            if (status == "success") {
                                if (data.success == 0) {
                                	base.success("更新成功");
                                	initTree(panel);
                                    $("#co_editModal",panel).modal('hide');
                                } else {
                                    base.error(data.message);
                                }
                            } else {
                                base.error("更新失败!");
                            }
                        });
        	}
        },
        remove: function (panel) {
        	var id = $("#id",panel).val();
        	if(!id || id == ""){
        		sweetAlert("Oops...", "请选择一行进行删除!", "error");
				return;
        	}
        	var title = "";
        	var url = "";
        	if(id.indexOf("a_") != -1){
        		title = "区域信息";
        		url = "/manage/area/del?areaId="+id.slice(2);
        	}else if(id.indexOf("p_") != -1){
        		title = "省份信息";
        		url = "/manage/province/deleteprovince?provinceId="+id.slice(2);
        	}else if(id.indexOf("c_") != -1){
        		title = "城市信息";
        		url = "/manage/city/deletecity?cityId="+id.slice(2);
        	}else if(id.indexOf("co_") != -1){
        		title = "区县信息";
        		url = "/manage/county/deletecounty?countyId="+id.slice(3);
        	}
			base.cancel({
				title : "删除",
				text : "您确定要删除此条"+title+"吗？注意:此"+title+"下的子条目也将被删除!!!"
			}, function() {
				$.post(url, function(data, status) {
					if (status == "success") {
						var obj = data;
						if (obj.success == 0) {
							base.success("删除成功");
							initTree(panel);
						} else {
							base.error(obj.message);
						}
					} else {
						base.error("删除失败");
					}
				});
			});
        }
    };
});