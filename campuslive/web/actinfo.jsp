<%@ page import="Dao.ActivityDao" %>
<%@ page import="Entity.Activity" %>
<%@ page import="Dao.UserDao" %>
<%@ page import="Entity.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Usercomment" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/7
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<link href="css/actinfo.css" rel="stylesheet"/>
<%--
<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?73c27e26f610eb3c9f3feb0c75b03925";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>--%>

<%
    /*int actId = Integer.parseInt(request.getParameter("actId"));
    ActivityDao activityDao = new ActivityDao();*/
    HttpSession s = request.getSession();
    User user = (User) s.getAttribute("user");
    User host = (User) s.getAttribute("host");
    Activity activity = (Activity) s.getAttribute("activity");
    List<Usercomment> comments = (List<Usercomment>) s.getAttribute("comments");
    List<User> allUser = (List<User>) s.getAttribute("allUser");

    if (activity != null){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <div class="panel panel-primary">
                <div class="panel-heading">活动信息</div>
                <div class="panel-body">活动时间：<br><%=df.format(activity.getActStartTime().getTime()) %> -- <br><%=df.format(activity.getActEndTime().getTime()) %></div>
                <div class="panel-body">报名时间：<br><%=df.format(activity.getRegStartTime().getTime()) %> -- <br><%=df.format(activity.getRegEndTime().getTime()) %></div>
                <div class="panel-body">活动地点：<%=activity.getActLocation() %></div>
                <div class="panel-body">人数：<%=activity.getCurrentMember() %>/<%=activity.getMaxMember() %></div>
                <div class="panel-body">主办方：<%=host.getUsername() %></div>
            </div>
            <%
                if (user != null){
                    int actStat = activity.getActStat();
                    List<Integer>member = (List<Integer>) s.getAttribute("member");
                    switch (actStat){
                        case 0:
            %>
            <button type="button" class="btn btn-default disabled">报名未开始</button>
            <%
                            break;
                        case 1:
                            if (member.contains(user.getUserId())){
            %>
            <form action="leave" method="post">
                <input type="hidden" name="actId" value="<%=activity.getActId() %>" />
                <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                <button type="submit" class="btn btn-default">已报名，点击退出</button>
            </form>
            <%
                            }
                            else {
            %>
            <form action="join" method="post">
                <input type="hidden" name="actId" value="<%=activity.getActId() %>" />
                <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                <button type="submit" class="btn btn-default">马上报名</button>
            </form>
            <%
                            }
                            break;
                        case 2:
                            if (member.contains(user.getUserId())){
            %>
            <form action="leave" method="post">
                <input type="hidden" name="actId" value="<%=activity.getActId() %>" />
                <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                <button type="submit" class="btn btn-default">已报名，点击退出</button>
            </form>
            <%
                            }
                            else {
            %>
            <form action="join" method="post">
                <input type="hidden" name="actId" value="<%=activity.getActId() %>" />
                <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                <button type="submit" class="btn btn-default disabled">人数已满</button>
            </form>
            <%
                            }
                            break;
                        case 3:
                            if (member.contains(user.getUserId())){
            %>
            <form action="leave" method="post">
                <input type="hidden" name="actId" value="<%=activity.getActId() %>" />
                <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                <button type="submit" class="btn btn-default">已报名，点击退出</button>
            </form>
            <%
                            }
                            else {
            %>
            <button type="button" class="btn btn-default disabled">报名已结束</button>
            <%
                            }
                            break;
                        case 4:
            %>
            <button type="button" class="btn btn-default disabled">正在进行</button>
            <%
                            break;
                        case 5:
            %>
            <button type="button" class="btn btn-default disabled">活动已结束</button>
            <%
                            break;
                    }
                    if (actStat == 1){
            %>
            <button class="btn btn-default send">邀请其他用户</button>
            <div id="invite" style="display: none">
                <label for="inviteUser">Select User to invite</label>
                <input class="senderName" type="hidden" value="<%=user.getUsername() %>"/>
                <select class="form-control" id="inviteUser">
            <%
                for (User user1 : allUser){
            %>
                    <option><%=user1.getUsername() %></option>
            <%
                }
            %>
                </select>
                <hr>
                <textarea class="content" rows="4" style="width: 100%"><%=user.getUsername()%>邀请你参与活动<%=activity.getActName() %>,活动链接localhost:8080/campus/gatAct?actId=<%=activity.getActId()%>
                </textarea>
                <hr>
                <button class="btn btn-default sendInvite">发送邀请</button>
            </div>
            <%
                    }

                }
                else {
            %>
            <button type="button" class="btn btn-default disabled">登陆后方可报名</button>
            <%
                }
            %>
        </div>

        <div class="col-sm-9">
            <h4><small>详细信息</small></h4>
            <hr>
            <h2><%=activity.getActName() %></h2>
            <h5>
                <%
                    List<String> tags = (List<String>) s.getAttribute("tags");
                    for (String tag : tags){
                %>
                <span class="label label-default"><%=tag %></span>
                <%
                    }
                %>
            </h5><br>
            <img src="<%=activity.getPostPath() %>" alt="image defaults" style="width:870px; height: auto">
            <br><br>

            <h4><small>活动描述</small></h4>
            <hr>
            <%--<h5><span class="glyphicon glyphicon-time"></span> Post by John Doe, Sep 24, 2015.</h5>
            <h5><span class="label label-success">Lorem</span></h5><br>--%>
            <p><%=activity.getActDesc() %></p>
            <hr>
<%
        if (user != null){
%>
            <h4>评论：</h4>
            <form role="form" action="saveComment" method="post">
                <div class="form-group">
                    <textarea class="form-control" rows="3" name="commentWord" required></textarea>
                </div>
                <input type="hidden" name="userId" value="<%=user.getUserId() %>"/>
                <input type="hidden" name="actId" value="<%=activity.getActId() %>"/>
                <input type="hidden" name="username" value="<%=user.getUsername() %>"/>
                <button type="submit" class="btn btn-success">提交</button>
            </form>
            <br><br>
<%

%>
            <p><span class="badge"></span> Comments:</p><br>

            <div class="row">
<%
    if (comments != null){
        for (Usercomment comment : comments){
%>
                <div class="col-sm-2 text-center">
                    <img src="pic/head.jpg" class="img-circle" height="65" width="65" alt="Avatar">
                </div>
                <div class="col-sm-10">
                    <h4><strong><%=comment.getUsername() %></strong>  <small><%=df.format(comment.getCommentTime().getTime()) %></small></h4>
                    <p><%=comment.getCommentWord() %></p>
                    <br>
                    <input type="hidden" class="commentId" value="<%=comment.getCommentId() %>"/>
                    <button class="btn btn-default showreply">回复</button>
                    <button class="btn btn-danger report">举报</button>
                    <hr>
                    <div class="response row" style="display: none" >
                        <form action="saveComment" method="post">
                            <input type="hidden" name="username" value="<%=user.getUsername() %>"/>
                            <input type="hidden" name="userId" value="<%=user.getUserId() %>"/>
                            <input type="hidden" name="responseId" value="<%=comment.getUserId() %>"/>
                            <input type="hidden" name="actId" value="<%=comment.getActId() %>"/>
                            <textarea class="form-control" rows="3" name="commentWord" required>回复 @<%=comment.getUsername() %> :
                            </textarea>
                            <button class="btn btn-default reply" type="submit" >提交回复</button>
                        </form>
                        <hr>
                    </div>
                </div>

<%
        }
    }
%>
                <%--<div class="col-sm-2 text-center">
                    <img src="pic/head.jpg" class="img-circle" height="65" width="65" alt="Avatar">
                </div>
                <div class="col-sm-10">
                    <h4>John Row <small>Sep 25, 2015, 8:25 PM</small></h4>
                    <p>I am so happy for you man! Finally. I am looking forward to read about your trendy life. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    <br>
                    <p><span class="badge">1</span> Comment:</p><br>
                    <div class="row">
                        <div class="col-sm-2 text-center">
                            <img src="pic/head.jpg" class="img-circle" height="65" width="65" alt="Avatar">
                        </div>
                        <div class="col-xs-10">
                            <h4>Nested Bro <small>Sep 25, 2015, 8:28 PM</small></h4>
                            <p>Me too! WOW!</p>
                            <br>
                        </div>
                    </div>
                </div>--%>

            </div>
        </div>
    </div>
</div>
<%
        }
    }

%>

<script type="text/javascript">
    $(".showreply").click(function () {
        $(this).parent().find(".response").toggle();
    });

    /*$(".reply").click(function () {
        var commentWord = $(this).parent().find("textarea").val();
        var userId = $(this).parent().parent().find(".userId").val();
        var actId = $(this).parent().parent().find(".actId").val();
        var username = $(this).parent().parent().find("strong").val();
        var responseId = $(this).parent().parent().find(".responseId").val();

        $.ajax({
            type : 'post',
            url : 'saveComment',
            data : {
                commentWord : commentWord,
                userId : userId,
                actId : actId,
                username : username,
                responseId : responseId
            },
            success : function () {
                alert("回复成功 ！")
            }
        });

    });*/


    $(".report").click(function () {
        var commentId = $(this).parent().find(".commentId").val();

        $.ajax({
            type : 'post',
            url : 'reportComment',
            data : {
                commentId : commentId
            },
            success : function () {
                alert("举报成功！");
            }
        });
    });


    $(".send").click(function () {
        $(this).parent().find("#invite").toggle();
    });


    $(".sendInvite").click(function () {
        var senderName = $(this).parent().find(".senderName").val();
        var receiverName = $(this).parent().find("#inviteUser").val();
        var messageWord = $(this).parent().find(".content").val();

        $.ajax({
            type : 'post',
            url : 'sendMessage',
            data : {
                senderName : senderName,
                receiverName : receiverName,
                messageWord : messageWord,
            },

            success : function () {
                alert("发送成功!");
            }
        });

        $(this).parent().toggle();

    });
</script>

<jsp:include page="footer.jsp"/>