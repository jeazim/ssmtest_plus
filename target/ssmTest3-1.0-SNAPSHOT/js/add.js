$(function () {
    $("#signup-form").submit(function (event) {
        // 阻止默认的表单提交事件
        event.preventDefault();

        // 获取表单数据
        var formData = new FormData(this);

        // 使用 AJAX 发送登录请求
        $.ajax({
            url: "/course/add_solve",
            method: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(result){
                if (result.msg === "success") {
                    let prompt = confirm("添加成功！是否继续添加？");
                    if(prompt === true){
                        //刷新界面
                        document.getElementById("signup-form").reset();
                    }else {
                        location.href = "/course/main_page";
                        return false;
                    }
                }else if(result.msg === "imageFail"){
                    alert("添加失败：图片上传有误");
                }else{
                    alert("添加失败：添加的课程已存在");
                }
            },
            //请求失败，包含具体的错误信息
            error: function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    });
});

$(function(){
    var select = $("#college");
    $.ajax({
        url: "/school/get_option",
        type: "GET",
        dataType: "json",
        success: function(result) {
            $.each(result.data, function(index, item) {
                $("#college").append("<option value='" + item.id + "'>" + item.schoolName + "</option>");
            });
        }
    });
});

$("#btn-exit").click(function (){
    location.href = "/course/main_page";
    return false;
});

$('#course-image').on('change', function() {
    var file = this.files[0];
    var reader = new FileReader();
    reader.onload = function(e) {
        $('#image-preview').html('<img src="' + e.target.result + '">');
    }
    reader.readAsDataURL(file);
});
