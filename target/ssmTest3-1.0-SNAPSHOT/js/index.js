$(document).ready(function() {

    // 隐藏初始界面
    $('#welcome').hide();

    // 显示初始界面动画效果
    $('#welcome').fadeIn(1500);

    // 绑定“开始”按钮的点击事件
    $('#startBtn').on('click', function() {
        // 在页面上随机生成一些烟花
        for (var i = 0; i < 20; i++) {
            var fireworks = $('<div class="firework"></div>');
            fireworks.css({
                'left': Math.random() * $(window).width(),
                'top': Math.random() * $(window).height()
            });
            $('body').append(fireworks);
            setTimeout(function () {
                $('.firework').remove(); // 移除烟花
                location.href="/user/login_page"
            }, 300);
        }
        $('.login-container').fadeOut('fast', function() {
            $('.success-container').fadeIn('fast');
            $('#username-text').text(username);

        });

    });
});