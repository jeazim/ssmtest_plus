function updatechat() {
    // 发起 AJAX 请求获取表格数据
    $.ajax({
        url: '/course/updatechat_solve',
        method: 'GET',
        success: function(result) {
            // 获取要填充数据的 tbody 元素
            const tbody = $('table tbody');
            $.each(result.data, function(index, item) {
                // 请求成功，遍历数据并生成表格行
                const tr = $('<tr>');
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
                tbody.append(tr);
            });
        },
        error: function() {
            // 请求失败
            console.error('请求失败');
        }
    });
}


$(function () {

});
