function note1() {
    // 获取要逐步加入的文字容器
    const animatedText = document.querySelector('.animated-text1');

    // 获取需要逐步加入的文字
    const text = '这里是一个关于我们的页面，用于课程管理，为使用者提供方便，学识尚浅，制作不易，还望海涵。';

    // 将文字转换成数组
    const textArray = text.split('');

    // 定义计数器
    let counter = 0;

    // 设置定时器，每隔一段时间向容器中添加一个文字
    const timer = setInterval(function() {
        // 判断是否已经添加完成所有文字
        if (counter < textArray.length) {
            // 向容器中添加一个文字
            animatedText.innerHTML += textArray[counter];
            // 延迟500毫秒执行下一次函数
            setTimeout(function() {
                // 给刚添加进来的文字添加.visible类，使其显示出来
                animatedText.lastChild.classList.add('visible');
            }, 100);
            // 计数器加1
            counter++;
        } else {
            // 如果已经添加完成，清除定时器
            clearInterval(timer);
            // 在第一个计时器被清除后调用第二个note()函数
            startSecondNote();
        }
    }, 200);
}

function startSecondNote() {
    // // 获取要逐步加入的文字容器
    // const animatedText = document.querySelector('.animated-text2');
    //
    // // 获取需要逐步加入的文字
    // const text = '我们的项目在课设前的javaWeb实验课就已经开始设计了，到现在已经有两个月了吧，学无止境，受益匪浅。';
    //
    // // 将文字转换成数组
    // const textArray = text.split('');
    //
    // // 定义计数器
    // let counter = 0;
    //
    // // 设置定时器，每隔一段时间向容器中添加一个文字
    // const timer = setInterval(function() {
    //     // 判断是否已经添加完成所有文字
    //     if (counter < textArray.length) {
    //         // 向容器中添加一个文字
    //         animatedText.innerHTML += textArray[counter];
    //         // 延迟500毫秒执行下一次函数
    //         setTimeout(function() {
    //             // 给刚添加进来的文字添加.visible类，使其显示出来
    //             animatedText.lastChild.classList.add('visible');
    //         }, 100);
    //         // 计数器加1
    //         counter++;
    //     } else {
    //         // 如果已经添加完成，清除定时器
    //         clearInterval(timer);
    //     }
    // }, 200);
}

$(function () {
    note1();
});
