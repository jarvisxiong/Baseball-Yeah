define(
    ['base'],
    function (base) {
        /**
         * 私有成员定义区域
         */

        return {
            init: function (args) {
                // / <summary>
                // / 模块初始化方法
                // / </summary>
                // / <param name="args">初始化时传入的参数</param>
                var self = this;

                $("#changePwd").click(function () {
                    self.edit();
                });
                $('#indextab').tabs({
                    border: false,
                    tabHeight: 36,
                    onSelect: function (title) {

                    }
                });
            },
            edit: function () {
                var self = this;
                $("#newPassword").val(""),
                    $("#reNewPassword").val(""),
                    $('#changeModal').modal({
                        keyboard: false,
                        backdrop: 'static'
                    });
                base.validator(
                    {
                        message: 'This value is not valid',
                        feedbackIcons: {
                            valid: 'glyphicon glyphicon-ok',
                            invalid: 'glyphicon glyphicon-remove',
                            validating: 'glyphicon glyphicon-refresh'
                        },
                        fields: {
                            oldPassword: {
                                validators: {
                                    notEmpty: {
                                        message: '旧密码不能为空'
                                    }
                                }
                            },
                            newPassword: {
                                validators: {
                                    notEmpty: {
                                        message: '新密码不能为空'
                                    },
                                    stringLength: {
                                        min: 6,
                                        max: 12,
                                        message: '密码6~12位'
                                    }
                                }
                            },
                            reNewPassword: {
                                validators: {
                                    notEmpty: {
                                        message: '请再次输入密码'
                                    },
                                    callback: {
                                        message: '两次输入密码不一致',
                                        callback: function (value, validator) {
                                            var password = $('#newPassword').val();
                                            var rePassword = $('#reNewPassword').val();
                                            return password == rePassword;
                                        }
                                    }
                                }
                            }
                        }
                    }, "#changeForm", self.update)
            },
            update: function () {
                $.post("/user/managers/changeManagerPwd",
                    {
                        "oldPwd": $("#oldPassword").val(),
                        "newPwd": $("#reNewPassword").val()
                    },
                    function (data, status) {
                        if (status == "success") {
                            if (data.success == 0) {
                                base.success("修改密码成功");
                                $('#changeForm').data('bootstrapValidator').resetForm(true);
                                $('#changeModal').modal('hide');
                            } else {
                                base.error(data.message);
                                $('#changeForm').find(".btn-primary").removeAttr("disabled");
                            }
                        } else {
                            base.error("修改密码失败!");
                        }
                    });
            },
        };
    });