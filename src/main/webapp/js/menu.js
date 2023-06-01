// // 获取点赞按钮元素
// const like = document.querySelector('.like-button');
//
// // 为按钮绑定点击事件处理程序
// like.addEventListener('click', () => {
//     alert('感谢您的支持！');
// });




// 获取点赞按钮元素
const like = document.querySelector('.like-button');

// 获取爱心容器元素
const heartContainer = document.querySelector('.heart-container');

// 为按钮绑定点击事件处理程序
like.addEventListener('click', () => {
    // 切换按钮的类名
    like.classList.toggle('liked');
    console.log("yes")
    // 创建一个新的元素表示爱心
    const heart = document.createElement('div');
    heart.className = 'heart';
    heart.innerHTML = '❤';

    // 将爱心添加到爱心容器中
    heartContainer.appendChild(heart);

    // 设置动画效果
    setTimeout(() => {
        heart.remove();
    }, 1000);
});