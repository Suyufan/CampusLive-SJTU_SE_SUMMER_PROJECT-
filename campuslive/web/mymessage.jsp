<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Message" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/20
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<style type="text/css">
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}

    /* Set gray background color and 100% height */
    .sidenav {
        background-color: #f1f1f1;
        height: 100%;
    }

    /* Set black background color, white text and some padding */
    footer {
        background-color: #555;
        color: white;
        padding: 15px;
    }

    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
        .sidenav {
            height: auto;
            padding: 15px;
        }
        .row.content {height: auto;}
    }
</style>

<%
    HttpSession s = request.getSession();
    User user = (User) s.getAttribute("user");
    Integer type = 0;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    if (request.getParameter("type") != null){
        type = Integer.parseInt(request.getParameter("type"));
    }

    if (user != null){
        List<Message> messages = null;
        switch (type) {
            case 0:
                messages = (List<Message>) session.getAttribute("unreadMessages");
                break;
            case 1:
                messages = (List<Message>) session.getAttribute("readMessages");
                break;
            case 2:
                messages = (List<Message>) session.getAttribute("sendMessages");
                break;
            default:
                messages = (List<Message>) session.getAttribute("unreadMessages");
                break;
        }

%>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <h4>我的消息</h4><hr>
            <ul class="nav nav-pills nav-stacked">
                <li><a href="getUnread?username=<%=user.getUsername() %>"><strong>未读的消息</strong></a></li>
                <li><a href="getRead?username=<%=user.getUsername() %>"><strong>已读的消息</strong></a></li>
                <li><a href="getSend?username=<%=user.getUsername() %>"><strong>我发送的消息</strong></a></li>
            </ul><br>
        </div>
        <div class="col-sm-9">
            <h2>我的消息</h2>
<%
        for (Message message : messages){
%>
            <div class="msg">
            <div class="col-sm-2 text-center">
                <img src="pic/head.jpg" class="img-circle" height="65" width="65" alt="Avatar">
            </div>
            <div class="col-sm-10">
                <h4>发件人：<%=message.getSenderName() %> ----> 收件人：<%=message.getReceiverName() %></h4>
                <small><%=df.format(message.getSendTime().getTime()) %></small><hr>
                <p><%=message.getMessageContent() %></p>
                <br>
                <input type="hidden" class="messageId" value="<%=message.getMessageId() %>"/>
<%
            if (type != 1 && type != 2){
%>
                <button class="btn btn-default mark">标记为已读</button>
<%
            }
%>
                <hr>
            </div>
            </div>
<%
        }
%>
        </div>
    </div>
</div>

<%
    }
    else {
%>
<div class="alert-danger">
    请先登陆或注册
</div>
<%
    }
%>

<script type="text/javascript">
    $(".mark").click(function () {
        var messageId = $(this).parent().find(".messageId").val();

        $(this).parent().parent().hide(500);

        $.ajax({
            type : 'post',
            url : 'setRead',
            data : {
                messageId : messageId
            },
            success : function () {

            }
        });
    });
</script>

<jsp:include page="footer.jsp"/>
