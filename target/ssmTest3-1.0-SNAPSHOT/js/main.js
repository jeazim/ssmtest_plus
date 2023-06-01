// -*- coding: utf-8 -*-

// 删除
function deleteCourse(courseId) {
    let answer = confirm("你确定要删除此课程吗?");
    if (answer) {
        $.ajax({
            method: "POST",
            url: "/course/delete_solve",
            data: {id: courseId},
            success: function() {
                alert("删除成功");
                // 删除成功后刷新页面
                window.location.reload();
            },
            error: function() {
                alert("删除失败，请稍后再试");
            }
        });
    }

}

// 修改
function updateCourse(courseId) {
    // 跳转到编辑页面，并传递当前选中行的 ID 参数
    window.location.href = `/course/update_page?id=${courseId}&${Date.now()}`;
}


// ----------------------------------------分割线-----------------------------------------------------------


$(document).ready(function() {

    // 退出
    document.getElementById("btn1").onclick = function () {
        let confirmed = confirm("确定要退出吗？");
        if (confirmed) {
            location.href = "/user/exit_page";
        }
    };
    var admin=0;
    // 获取用户名与权限
    let welcome = $('#username');
    $.get('/user/get_name', function(username) {
        // 防止非法进入
        if (username === '') {
            window.location.href = "/user/login_page";
        } else {
            $.get('/user/get_pri', function(userPri){
                //权限管理，将所有admin类型的标签隐藏
                if(userPri===1){
                    welcome.html(`${username}(管理员)`);
                    admin=1;
                }
                else {
                    welcome.html(`${username}(用户)`);
                    $('.admin').hide();
                }
            });
        }
    });



    // 发起 AJAX 请求获取表格数据
    $.ajax({
        url: '/course/main_solve',
        method: 'GET',
        success: function(result) {
            // 获取要填充数据的 tbody 元素
            const tbody = $('table tbody');
            $.each(result.data, function(index, item) {
                // 请求成功，遍历数据并生成表格行
                const tr = $('<tr>');
                if(admin===1)
                    tr.html(`
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <!-- 在图片上添加 data-mfp-src 属性，指定原始图片路径 -->
                        <td><img src="../image/${item.image}" class="course-img" data-mfp-src="../image/${item.image}"></td>
                        <td>${item.hours}</td>
                        <td>${item.schools}</td>
                        <td>
                            <a class="update" onclick="updateCourse(${item.id})">编辑</a>
                            <a class="delete" onclick="deleteCourse(${item.id})">删除</a>
                        </td>
                    `);
                else
                    tr.html(`
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <!-- 在图片上添加 data-mfp-src 属性，指定原始图片路径 -->
                        <td><img src="../image/${item.image}" class="course-img" data-mfp-src="../image/${item.image}"></td>
                        <td>${item.hours}</td>
                        <td>${item.schools}</td>
                    `);
                tbody.append(tr);
            });
        },
        error: function() {
            // 请求失败
            console.error('请求失败');
        }
    });



    // 新增
    document.getElementById("add").onclick = function () {
        location.href = "/course/add_page";
    };
    //菜单
    document.getElementById("menu").onclick = function () {
        location.href = "/course/menu_page";
    };

    document.getElementById("search-btn").onclick = function () {
        var id=$("#id-input").val();
        var hours=$("#hours-input").val();
        var schools=$("#school-input").val();
        var name=$("#name-input").val();
        console.log("-----searchBegin----");
        // 发起 AJAX 请求获取表格数据
        $.ajax({
            url: "/course/select_solve",
            method: "POST",
            dataType: 'json',
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify({
                 id: id, hours: hours,schools:schools,name:name
            }),
            success: function(result) {
                // 获取要填充数据的 tbody 元素
                const tbody = $('table tbody');
                console.log("清空前");
                //清空表格数据
                tbody.empty();
                $.each(result.data, function(index, item) {
                    // 请求成功，遍历数据并生成表格行
                    const tr = $('<tr>');
                    if(admin===1){
                        tr.html(`
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <!-- 在图片上添加 data-mfp-src 属性，指定原始图片路径 -->
                        <td><img src="../image/${item.image}" class="course-img" data-mfp-src="../image/${item.image}"></td>
                        <td>${item.hours}</td>
                        <td>${item.schools}</td>
                        <td>
                            <a class="update" onclick="updateCourse(${item.id})">编辑</a>
                            <a class="delete" onclick="deleteCourse(${item.id})">删除</a>
                        </td>
                        `);
                    }
                    else {
                        tr.html(`
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <!-- 在图片上添加 data-mfp-src 属性，指定原始图片路径 -->
                        <td><img src="../image/${item.image}" class="course-img" data-mfp-src="../image/${item.image}"></td>
                        <td>${item.hours}</td>
                        <td>${item.schools}</td>
                        `);
                    }
                    tbody.append(tr);
                });
            },
            error: function() {
                // 请求失败
                console.error('请求失败');
            }
        });

    };

});
//
// // 获取文本框和按钮元素
// const idInput = $('#id-input');
// const nameInput = $('#name-input');
// const hoursInput = $('#hours-input');
// const schoolInput = $('#school-input');
// const searchBtn = $('#search-btn');
//
// // 监听按钮的点击事件
// searchBtn.click(function() {
//     // 获取用户输入的关键字
//     const idValue = idInput.val().trim();
//     const nameValue = nameInput.val().trim();
//     const hoursValue = hoursInput.val().trim();
//     const schoolValue = schoolInput.val().trim();
//
//     // 创建查询条件对象
//     const condition = {};
//     if (idValue !== '') {
//         condition['id'] = idValue;
//     }
//     if (nameValue !== '') {
//         condition['name'] = nameValue;
//     }
//     if (hoursValue !== '') {
//         condition['hours'] = hoursValue;
//     }
//     if (schoolValue !== '') {
//         condition['school'] = schoolValue;
//     }
//
//     // 发起 AJAX 请求获取筛选结果
//     $.ajax({
//         url: '/course/search_solve',
//         method: 'GET',
//         data: {condition: JSON.stringify(condition)},
//         success: function(result) {
//             // 清空表格数据
//             $('table tbody').empty();
//
//             // 遍历筛选结果并生成表格行
//             $.each(result.data, function(index, item) {
//                 const tr = $('<tr>');
//                 tr.html(`
//                     <td>${item.id}</td>
//                     <td>${item.name}</td>
//                     <td><img src="/pages/${item.image}" class="course-img"></td>
//                     <td>${item.hours}</td>
//                     <td>
//                         <a class="update" onclick="updateCourse(${item.id})">编辑</a>
//                         <a class="delete" onclick="deleteCourse(${item.id})">删除</a>
//                     </td>
//                 `);
//                 $('table tbody').append(tr);
//             });
//         },
//         error: function() {
//             // 请求失败
//             console.error('请求失败');
//         }
//     });
//
//     // 获取文本框和按钮元素
//     const idInput = $('#id-input');
//     const nameInput = $('#name-input');
//     const hoursInput = $('#hours-input');
//     const schoolInput = $('#school-input');
//     const searchBtn = $('#search-btn');

    // 监听按钮的点击事件
    // searchBtn.click(function() {
    //     // 获取用户输入的关键字
    //     const idValue = idInput.val().trim();
    //     const nameValue = nameInput.val().trim();
    //     const hoursValue = hoursInput.val().trim();
    //     const schoolValue = schoolInput.val().trim();

        // // 创建查询条件对象
        // const condition = {};
        // if (idValue !== '') {
        //     condition['id'] = idValue;
        // }
        // if (nameValue !== '') {
        //     condition['name'] = nameValue;
        // }
        // if (hoursValue !== '') {
        //     condition['hours'] = hoursValue;
        // }
        // if (schoolValue !== '') {
        //     condition['school'] = schoolValue;
        // }
        //
        // // 发起 AJAX 请求获取筛选结果
        // $.ajax({
        //     url: '/course/search_solve',
        //     method: 'GET',
        //     data: {condition: JSON.stringify(condition)},
        //     success: function(result) {
        //         // 清空表格数据
        //         $('table tbody').empty();
        //
        //         // 遍历筛选结果并生成表格行
        //         $.each(result.data, function(index, item) {
        //             const tr = $('<tr>');
        //             tr.html(`
        //             <td>${item.id}</td>
        //             <td>${item.name}</td>
        //             <td><img src="/pages/${item.image}" class="course-img"></td>
        //             <td>${item.hours}</td>
        //             <td>
        //                 <a class="update" onclick="updateCourse(${item.id})">编辑</a>
        //                 <a class="delete" onclick="deleteCourse(${item.id})">删除</a>
        //             </td>
        //         `);
        //             $('table tbody').append(tr);
        //         });
        //     },
        //     error: function() {
        //         // 请求失败
        //         console.error('请求失败');
        //     }
        // });
    // });
// });



