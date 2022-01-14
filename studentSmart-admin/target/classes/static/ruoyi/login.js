
$(function() {
    validateKickout();
    validateRule();
    $('.imgcode').click(function() {
        var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
        $(".imgcode").attr("src", url);
    });
});

$.validator.setDefaults({
    submitHandler: function() {
        login();
    }
});

function login() {
    $.modal.loading($("#btnSubmit").data("loading"));
    var username = $.common.trim($("input[name='username']").val()); /*trim，取数据 */
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe = $("input[name='rememberme']").is(':checked');
    $.ajax({
	/*发了个post请求，把数据传过去 */
        type: "post",
        url: ctx + "login",
        data: { /*封装成json对象,然后发请求 */
            "username": username,
            "password": password,
            "validateCode": validateCode,
            "rememberMe": rememberMe
        },
        /*如果成功的话 */
        success: function(r) {
            if (r.code == web_status.SUCCESS) {
                location.href = ctx + 'index'; //返回到/index Get请求
            } else { /*如果失败的话 */
            	$.modal.closeLoading(); /*关闭转圈圈的loading*/
            	$('.imgcode').click();  /*触发点击验证码的时间，相当于手动点击验证码的图并更新*/
            	$(".code").val(""); /*清空验证码对话框的值 */
            	$.modal.msg(r.msg); /*告诉你验证失败*/
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}

function validateKickout() {
    if (getParam("kickout") == 1) {
        layer.alert("<font color='red'>您已在别处登录，请您修改密码或重新登录</font>", {
            icon: 0,
            title: "系统提示"
        },
        function(index) {
            //关闭弹窗
            layer.close(index);
            if (top != self) {
                top.location = self.location;
            } else {
                var url  =  location.search;
                if (url) {
                    var oldUrl  = window.location.href;
                    var newUrl  = oldUrl.substring(0,  oldUrl.indexOf('?'));
                    self.location  = newUrl;
                }
            }
        });
    }
}

function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}