//表单提交
$(function () {
    $("#signup-form").submit(function (event) {
        // 阻止默认的表单提交事件
        event.preventDefault();
        console.log("---------------change--------------")
        // 获取表单数据
        var formData = new FormData(this);

        // 使用 AJAX 发送登录请求
        $.ajax({
            url: "/course/update_solve",
            method: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(result){
                if (result.msg==="success") {
                    let prompt = confirm("修改成功!是否继续修改？");
                    if(prompt===false)
                    {
                        location.href = "/course/main_page";
                        return false;
                    }
                } else if(result.msg==="imageFail"){
                    alert("修改失败：图片上传有误");
                }else{
                    alert("修改失败：课程重复");
                }
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
});
//数据回显
$(function(){
    // 获取下拉列表元素
    var select = $("#college");
    // 发送AJAX请求获取学院列表
    $.ajax({
        url: "/school/get_option",
        type: "GET",
        dataType: "json",
        success: function(result) {
            $.each(result.data, function(index, item) {
                select.append("<option value='" + item.id + "'>" + item.schoolName + "</option>");
            });
        }
    }).done(function() {
        //回显数据
        $.ajax({
            url: '/course/get_course',
            method: 'GET',
            success: function(data) {
                // 使用接收到的数据填充表单字段
                $('#image-preview').html('<img src="../image/' + data.image + '">');
                $('#course-id').val(data.id);
                $('#course-name').val(data.name);
                $('#course-hours').val(data.hours);
                $('#college').val(data.schools);
            },
        });
    });
});
//返回
document.getElementById("btn-exit").onclick = function (){
    location.href = "/course/main_page";
    return false;
}
//显示图片
$('#course-image').on('change', function() {
    var file = this.files[0];
    var reader = new FileReader();
    reader.onload = function(e) {
        $('#image-preview').html('<img src="' + e.target.result + '">');
    }
    reader.readAsDataURL(file);
});

// 将 ID 输入框变为只读
$('#course-id').prop('readonly', true);