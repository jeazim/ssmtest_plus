
function validateForm() {
    var inputs = document.getElementsByTagName("input");
    for (var i = 0; i < inputs.length; i++) {
        var input = inputs[i];
        if (input.required && input.value.trim() === "") {
            alert("请输入必填项！");
            return false;
        }
    }
    return true;
}


$(document).ready(function() {
    // 定义验证码字符集
    var captchaChars = '0123456789abcdefghijklmnopqrstuvwxyz0123456789QWERTYUIOPASDFGHJKLZXCVBNM';

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

    $(".login-form").submit(function (event) {
        // 阻止默认的表单提交事件
        event.preventDefault();
        //阻止空输入
        if(validateForm() !== true)return;

        // 获取用户名和密码
        var username = $("#username").val();
        var password = $("#password").val();
        var inputCaptcha = $('#captcha').val();

        //使用 AJAX 发送登录请求
        $.ajax({
            url: "/user/login_solve",
            method: "POST",
            dataType:'json',
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify({
                username: username, passWord: password,captcha:captcha.toLowerCase(),inputCaptcha:inputCaptcha.toLowerCase()
            }),
            success: function(result){
                if (result.msg==="success") {
                    alert("登录成功");
                    location.href = "/course/main_page";
                    return false;
                } else {
                    alert("登录失败: 账号不存在或密码验证码错误");
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
        console.log("success");
    });
    //注册跳转
    $(".register-button").on("click",function() {
        window.location.href = "/user/register_page";
    });



});

