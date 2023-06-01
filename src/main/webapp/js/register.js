$(document).ready(function() {
    //返回登录界面
    $(".login-button").on('click',function(){
        window.location.href="/user/login_page";
    });

    // 定义验证码字符集
    var captchaChars = '0123456789abcdefghijklmnopqrstuvwxyz';

    // 随机生成验证码字符
    function generateCaptcha() {
        var captcha = '';
        for (var i = 0; i < 4; i++) {
            captcha += captchaChars.charAt(Math.floor(Math.random() * captchaChars.length));
        }
        console.log(captcha);
        return captcha;
    }
    var captcha;
    //刷新验证码
    function getCaptcha(){
        captcha = generateCaptcha();
        $('.captcha-char').text(captcha);
    }

    // 生成初始验证码图片
    getCaptcha();

    // 监听验证码图片点击事件
    $('.captcha-img').on('click',function() {
        getCaptcha();
    });
    $('#tip').on('click',function() {
        // 生成新的验证码字符
        getCaptcha();
    });

    //注册用户
    $(".login-form").submit(function (event) {
        // 阻止默认的表单提交事件
        event.preventDefault();

        // 获取用户名和密码
        var username = $("#username").val();
        var password = $("#password").val();
        var confirmPassword=$("#confirmPassword").val();
        var inputCaptcha = $('#captcha').val();
        // console.log(username);

        if(confirmPassword === password){
            if(inputCaptcha === captcha){
                //使用 AJAX 发送登录请求
                $.ajax({
                    url: "/user/register_solve",
                    method: "POST",
                    dataType:'json',
                    contentType:'application/json;charset=utf-8',
                    data:JSON.stringify({
                        username: username, passWord: password
                    }),
                    success: function(result){
                        if (result.msg==="success") {
                            alert("注册成功！-->前往登录界面");
                            location.href = "/user/login_page";
                            return false;
                        } else {
                            alert("注册失败：用户名已存在！");
                            //刷新验证码
                            getCaptcha();
                        }
                    },
                    //请求失败，包含具体的错误信息
                    error : function(e){
                        console.log(e.status);
                        console.log(e.responseText);
                    }

                });
            }else{
                alert("验证码错误！");
                //刷新验证码
                getCaptcha();
            }
        }else{
            alert("输入的密码不一致！");
            //刷新验证码
            getCaptcha();
        }

    });
});