<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <!--    以让网页更加适应于不同的移动设备，并提供更好的用户体验-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 禁用浏览器缓存-->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- 引入 Magnific Popup 相关文件,实现图片放大 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
    <title>反馈</title>
    <!-- 引入CSS文件 -->
    <link rel="stylesheet" href="../css/chat.css">
    <link rel="stylesheet" href="../css/menu.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>聊天窗口</title>
    <style>
        .emoji-box {
            width: 200px;
            height: 1000px;
            display: flex;
            flex-wrap: wrap;
            transform: scale(0.5);
            margin-top: 0px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 2px 5px;
            float: left;
        rgba(0, 0, 0, 0.3);
            display: block;
            background-color: chartreuse;
        }
        .emoji-box1{
            height: 20px;
            width: 20px;
            vertical-align: -5px;
        }
        .emoji_btn{
            background: url("../image/emoji.png");
        }
        .text_view{
            height: 20%;
            background-color: white;
            margin-top: 2px;
            /* 去除边框 */
            border: none;
            resize: none;
            /* 去除选中后的边框 */
            outline:none;
            /* 鼠标样式 */
            cursor:text;
            /* font-size: 15px; */
            text-indent:4px;
            /* 设置滚动条 */
            overflow-y:auto;
            /* overflow-x:scroll; */

        }
        .chatStyle{

        }
        .chatList{

        }
        .userlist{
            background-color: aqua;
            padding: 10px;
        }
        .userlist1{
            padding: 10px;
            background-color:red;
        }
        .ab{
            color: red;
            border: solid;
        }

        .div2{
            background-color: aquamarine;
            padding: 10px;
            display: inline-block;
        }
    </style>
    <script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        var emojis=[
            "${pageContext.request.contextPath}/emoji/1.png",
            "${pageContext.request.contextPath}/emoji/2.png",
            "${pageContext.request.contextPath}/emoji/3.png",
            "${pageContext.request.contextPath}/emoji/4.png",
            "${pageContext.request.contextPath}/emoji/5.png",
            "${pageContext.request.contextPath}/emoji/6.png",
            "${pageContext.request.contextPath}/emoji/7.png",
            "${pageContext.request.contextPath}/emoji/8.png",
            "${pageContext.request.contextPath}/emoji/9.png",
            "${pageContext.request.contextPath}/emoji/10.png",
            "${pageContext.request.contextPath}/emoji/11.png",
            "${pageContext.request.contextPath}/emoji/12.png",
            "${pageContext.request.contextPath}/emoji/13.png",
            "${pageContext.request.contextPath}/emoji/14.png",
            "${pageContext.request.contextPath}/emoji/15.png",
            "${pageContext.request.contextPath}/emoji/16.png",
            "${pageContext.request.contextPath}/emoji/17.png",
            "${pageContext.request.contextPath}/emoji/18.png",
            "${pageContext.request.contextPath}/emoji/58.png",
            "${pageContext.request.contextPath}/emoji/20.png",
            "${pageContext.request.contextPath}/emoji/21.png",
            "${pageContext.request.contextPath}/emoji/22.png",
            "${pageContext.request.contextPath}/emoji/23.png",
            "${pageContext.request.contextPath}/emoji/24.png",
            "${pageContext.request.contextPath}/emoji/25.png",
            "${pageContext.request.contextPath}/emoji/26.png",
            "${pageContext.request.contextPath}/emoji/27.png",
            "${pageContext.request.contextPath}/emoji/28.png",
            "${pageContext.request.contextPath}/emoji/29.png",
            "${pageContext.request.contextPath}/emoji/30.png",
            "${pageContext.request.contextPath}/emoji/31.png",
            "${pageContext.request.contextPath}/emoji/32.png",
            <%--"${pageContext.request.contextPath}/emoji/33.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/34.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/35.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/36.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/37.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/38.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/39.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/40.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/41.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/42.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/43.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/44.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/45.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/46.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/47.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/48.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/49.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/50.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/51.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/52.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/53.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/54.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/55.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/56.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/57.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/58.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/59.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/60.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/61.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/62.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/63.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/64.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/65.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/66.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/67.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/68.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/69.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/70.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/71.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/72.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/73.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/74.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/75.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/76.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/77.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/78.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/79.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/80.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/71.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/72.png",--%>
            <%--"${pageContext.request.contextPath}/emoji/83.png",--%>
            "${pageContext.request.contextPath}/emoji/84.png"
        ]
        var toname;
        var local = window.localStorage;
        var userCellStyle =
            "clear:both;\
            padding: 10px;\
            border-color: black;\
            border-style: solid;\
            cursor:pointer;\
            text-align: center;\
            background-color : transparent;";
        var ws;
        var ws_url="ws://localhost:8080/chatroom"
        $(function (){
            ws_connect();
            $("#send").click(function (){
                ws_sendMsg();
            })
            $("#send").click(function (){
                ws_sendImg();
            })
        })
        function ws_connect() {
            $.get('/user/get_name', function(username) {
                if ('WebSocket' in window) {
                    ws = new WebSocket(ws_url+"?username="+username);
                } else if ('MozWebSocket' in window) {
                    ws = new MozWebSocket(ws_url+"?username="+username);
                } else {
                    console.log('Error: WebSocket is not supported by this browser.');
                    return;
                }

                ws.onopen = function () {
                    $("#recode").append("<center><div style='color: darkviolet' onclick='listchat()'>显示历史记录</div></center>")
                    console.log('Info: WebSocket connection opened.');
                };

                ws.onclose = function () {
                    console.log('Info: WebSocket closed.');
                }
                ws.onmessage=function (message){
                    if ("string"==typeof(message.data))//文本消息
                    {

                        var msg=message.data;
                        obj=JSON.parse(msg);
                        if(obj.type=="s")
                        {
                            $("#recode").append("<center><div style='color: red'>"+obj.msgDateStr+" "+obj.msgInfo+"</div></center><br/><br/>");
                            var userhtml="<div>用户列表：</div>"
                            userhtml=userhtml+"<div class='userlist' onclick='chat(1)' id='1'>jeazim(管理员)</div><br/>"
                            userhtml=userhtml+"<div class='userlist' onclick='chat(12)' id='2'>zh1ng(用户)</div><br/>"
                            userhtml=userhtml+"<div class='userlist' onclick='chat(3)' id='3'>shutao(用户)</div><br/>"
                            userhtml=userhtml+"<div class='userlist' onclick='chat(3)' id='3'>wancl(用户)</div><br/>"
                            userhtml=userhtml+"<div class='userlist' onclick='chat(3)' id='3'>liwenjie(用户)</div><br/>"
                            <%--var userlist=obj.userList;--%>
                            <%--var i;--%>
                            <%--for(i=0;i<userlist.length;i++)--%>
                            <%--{--%>
                            <%--    if("${sessionScope.name}"!=userlist[i]) {--%>
                            <%--        // document.getElementById()--%>
                            <%--        name1=i;--%>
                            <%--        console.log("i="+i)--%>
                            <%--        name2=userlist[i];--%>
                            <%--        console.log("name="+name2);--%>
                            <%--        userhtml=userhtml+"<div class='userlist' onclick='chat(name2)' id='abcdeas'>"+userlist[i]+"</div><br/>"--%>
                            <%--    }--%>
                            <%--}--%>
                            $("#userList").html(userhtml)
                        }else if(obj.type=="img"&&obj.msgSender==username){
                            di="<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><br/><div class='ab' style='float: right'>"+username+"</div><br/><br/>";
                            // $("#record").append("<div style='float: right'>"+obj.msgSender+"</div><br/><br/>")
                            // $("#record").append("<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><div style='float: right'><span class='ab'>"+obj.msgSender+"</span></div><br/><br/>");
                        }else if (obj.type=="img"&&obj.msgSender!=username){
                            di="<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><br/><div class='ab'>"+username+"</div><br/><br/>";
                            // $("#record").append("<div>"+obj.msgSender+"</div><br/><br/>")
                            // $("#record").append("<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><div><span class='ab'>"+obj.msgSender+"</span>"+":&nbsp;"+"</div><br/><br/>");
                        }else if(obj.msgSender==username&&obj.type=="p"){
                            $("#recode").append("<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><div style='float: right'><span class='div2'>"+obj.msgInfo+"</span>"+":&nbsp;"+"<span class='ab'>"+obj.msgSender+"</span></div><br/><br/>");
                            var x=local.getItem("userchat");
                            if (x==null) local.setItem("userchat","<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><div><span class='ab'>"+obj.msgSender+"</span>"+":&nbsp;"+"<span class='div2'>"+obj.msgInfo+"</span></div><br/><br/>");
                            else local.setItem("userchat",x+"<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><div><span class='ab'>"+obj.msgSender+"</span>"+":&nbsp;"+"<span class='div2'>"+obj.msgInfo+"</span></div><br/><br/>");
                        }else if (obj.msgSender!=username&&obj.type=="p"){
                            $("#recode").append("<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><div><span class='ab'>"+obj.msgSender+"</span>"+":&nbsp;"+"<span class='div2'>"+obj.msgInfo+"</span></div><br/><br/>");
                        }
                    }else {   //图片
                        var reader=new FileReader();
                        reader.readAsDataURL(message.data);
                        reader.onload=function (evt){
                            if(evt.target.readyState==FileReader.DONE){
                                var url=evt.target.result;
                                if(obj.msgSender==username){
                                    $("#recode").append("<center><div style='color: green' >"+obj.msgDateStr+"</div></center><br/><br/><span class='ab' style='float:right'>"+username+"</span><br/><br/><img src='"+url+"' style='max-height:100px; max-width:100px;float: right'/><br/><br/><br/><br/><br/><br/>")
                                }else {
                                    $("#recode").append("<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><br/><span class='ab'>"+obj.msgSender+"</span><br/><br/><img src='"+url+"' style='max-height:100px; max-width:100px;'/><br/><br/>")
                                }
                                // $("#record").append("<center><div style='color: green'>"+obj.msgDateStr+"</div></center><br/><br/><span class='ab'>"+obj.msgSender+"</span><br/><br/><img src='"+url+"' style='max-height:100px; max-width:100px;'/><br/><br/>");
                            }
                        }
                    }
                }

            });


        }
        function ws_sendMsg()
        {
            var msg=$("#msg").val();
            if (msg!="")
                ws.send(msg);
            msg=$("#msg").val("");
        }
        function ws_sendImg()
        {
            var file=$("#img")[0].files[0];
            if(file){
                var reader=new FileReader();
                reader.readAsArrayBuffer(file);
                reader.onload=function (evt){
                    ws.send(evt.target.result);
                }
                $("#img").val("");
            }else {
                $("#img").val("");
            }
        }
        function chat(str){

        }
        function storage() {
            var x=local.getItem("userchat")
            console.log(x);
            $("#recode").append(x);
        }
        function listchat(){
            storage();
        }
        function showEmojis11() {
            // 创建表情选择框的HTML元素
            var emojiBox = document.createElement('div');
            emojiBox.id = 'emoji-box';
            emojiBox.style.position = 'absolute';
            emojiBox.style.top = '50px';
            emojiBox.style.left = '50px';
            emojiBox.style.backgroundColor = '#fff';
            emojiBox.style.border = '1px solid #ccc';
            emojiBox.style.padding = '10px';
            for (var i = 0; i < emojis.length; i++) {
                var emoji = document.createElement('span');
                emoji.innerHTML = emojis[i];
                emoji.style.fontSize = '24px';
                emoji.style.marginRight = '10px';
                emoji.style.cursor = 'pointer';
                emoji.onclick = function() {
                    // 将选中的表情添加到文本框中
                    var textarea = document.getElementById('textarea');
                    textarea.value += this.innerHTML;
                };
                emojiBox.appendChild(emoji);
            }
            // 将表情选择框添加到页面中
            var body = document.getElementsByTagName('body')[0];
            body.appendChild(emojiBox);
        }
        function showEmojis() {
            var container=document.getElementById("emoji-box");
            container.innerHTML="";
            for(var i=0;i<emojis.length;i++){
                var img=document.createElement("img");
                img.src=emojis[i];
                img.onclick=function (){
                    inserEmoji(this.src);
                    container.innerHTML="";
                };
                container.appendChild(img);
            }
        }

        function inserEmoji(src) {
            var content=document.getElementById("msg");
            var cursorPos=content.selectionStart;
            var textBefore=content.value.substring(0,cursorPos);
            var textAfter=content.value.substring(cursorPos,content.value.length);
            content.value=textBefore+"<img class='emoji-box1' src="+src+" >"+textAfter;
        }
    </script>
</head>
<body>

<!--<div class="background"></div>-->
<!-- 导航栏容器 -->
<nav class="navbar">
    <div class="navbar-left">
        <a href="/course/menu_page">菜单首页</a>
        <a href="/course/statistics_page">课时统计图</a>
        <a href="/course/exportToExcel_page">导出Excel</a>
        <a href="/course/chat_page">反馈</a>
        <a href="/course/about_page">关于我们</a>
        <a href="/course/main_page">返回</a>
    </div>
    <div class="navbar-right">
        <button class="like-button">点赞</button>
        <div class="heart-container"></div>
    </div>
</nav>
<!-- 页面主体内容 -->
<div id="emoji-box" class="emoji-box" style="border: green;display: block;width: 200px;height: 700px;background-color: bisque;float: left"></div>
<center>
    <center><h1>反馈页面</h1></center>
    <table style="border: 1px solid #00F;">
        <tbody>
        <tr>
            <td colspan="2" align="center" bgcolor="#87ceeb">
                <h3>你好：${sessionScope.user.username}</h3>
            </td>
        </tr>
        <tr>
            <td width="800px" height="500px"
                style="border: 1px solid #00F; vertical-align: top;" id="content"
                name="content">
                <div class="chatList" style="display:block" id="tbrecode">
                    <div class="chatStyle" style="background-color: white; height: 500px;display: block;width: 800px;overflow:auto" id="recode">
                    </div>
                    <%--                    <div id="emoji-box" style="border: green;display: block;width: 800px;height: 70px;background-color: bisque"></div>--%>
                    <%--                    <div style="background-color:white; height: 100px;width: 800px;display: block "  id="msg" contenteditable="true"></div>--%>
                </div>
            </td>
            <td width="100px"
                style="border: 1px solid #00F; vertical-align: top;"rowspan="2">
                <div style="overflow: auto; background-color: #7edbdc;height: 500px;display: block" id="userList">
                    <%--                <table id="tbuserList">--%>
                    <%--                    <tbody id="userList"--%>
                    <%--                           style="display: block; height: 500px; overflow: auto;" />--%>
                    <%--                </table>--%>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <table>
        <tfoot>
        <tr>
            <td height="50px"><textarea rows="5" cols="40" id="msg" style="resize: none"></textarea></td>
            <%--            <input id="msg" name="msg" style="width: 100%;height: 50px" placeholder="信息输入" />--%>
            <td colspan="2" align="right">
                <button class="emoji_btn" style="width: 30px;height: 30px" onclick="showEmojis()"></button>
                <button style="margin: 0 30px 0 30px" id="send" name="send">发送消息</button>
                <input type="file" id="img" style="width: 200px; height: 30px" />
                <%--                <button id="uploadImg" name="uploadImg">发送图片</button>--%>
                <%--                <button style="margin: 0 30px 0 30px" id="disconnect"--%>
                <%--                        name="disconnect">Disconnect</button>--%>
            </td>
        </tr>
        </tfoot>
    </table>
</center>
<!-- 引入JS文件 -->
<script src="../js/chat.js"></script>
<script src="../js/menu.js"></script>
<%--<%Object name=session.getAttribute("name");%>--%>
<%-- ***********************************************************聊天窗口**********************************************************--%>

</body>
</html>