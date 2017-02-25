<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User.java: 昱凡
  Date: 2016/7/6
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Campus Live</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="css/wangEditor.min.css" rel="stylesheet">

    <script src="js/jquery-3.0.0.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/bootstrap.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="js/bootstrap-datetimepicker.js" type="text/javascript" charset="UTF-8"></script>

    <script src="js/wangEditor.js" type="text/javascript" charset="UTF-8"></script>



    <%--<link href="css/wangEditor.min.css" rel="stylesheet">
    <link href="http://cdn.gbtags.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="http://cdn.gbtags.com/summernote/0.5.2/summernote.css" rel="stylesheet"/>--%>
</head>
<body>
<%
    HttpSession s = request.getSession();
	User user = (User) s.getAttribute("user");
%>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">CampusLive</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="index">首页</a></li>
                <li><a href="getAllActivity">所有活动</a></li>
            </ul>
            <form class="navbar-form navbar-left" role="search" action="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="搜索活动或团队" name="searchWord"/>
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <%
                if (user == null){
            %>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form class="nav navbar-form" action="login" method="post">
                        <input type="text" class="form-control" placeholder="Username" name="username" required autofocus/>
                        <input type="password" class="form-control" placeholder="Password" name="password" required>
                        <button type="submit" class="btn btn-default">登陆</button>
                    </form>
                </li>
                <li><a href="register.jsp">注册</a></li>
            </ul>

            <%
                }
                else {
                    Long unreadMessageNumber = (Long) s.getAttribute("unreadMessageNumber");
                    Long readMessageNumber = (Long) s.getAttribute("readMessageNumber");
                    Long sendMessageNumber = (Long) s.getAttribute("sendMessageNumber");
            %>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="editUser.jsp"><span class="glyphicon glyphicon-user"></span><%=user.getUsername() %></a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="glyphicon glyphicon-plus"></span>创建
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="getTagAndGroup">活动</a></li>
                        <li><a href="createTeam.jsp">团队</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                    我的活动<span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <form action="notStarted" method="post">
                            <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                            <button type="submit" class="btn btn-link">未开始的活动</button>
                        </form>
                    </li>
                    <li>
                        <form action="finished" method="post">
                            <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                            <button type="submit" class="btn btn-link">已结束的活动</button>
                        </form>
                    </li>
                    <li>
                        <form action="all" method="post">
                            <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                            <button type="submit" class="btn btn-link">所有活动</button>
                        </form>
                    </li>
                    <li role="separator" class="divider"></li>
                    <li>
                        <form action="created" method="post">
                            <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                            <button type="submit" class="btn btn-link">我创建的活动</button>
                        </form>
                        <form action="getCreatedTeam" method="post">
                            <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                            <button type="submit" class="btn btn-link">我创建的团队</button>
                        </form>
                    </li>
                </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span class="glyphicon glyphicon-envelope"></span>我的消息 <span class="badge"><%=unreadMessageNumber%></span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="getUnread?username=<%=user.getUsername() %>">未读消息 <span class="badge"><%=unreadMessageNumber%></span></a></li>
                        <li><a href="getRead?username=<%=user.getUsername() %>">已读消息 <span class="badge"><%=readMessageNumber%></span></a></li>
                        <li><a href="getSend?username=<%=user.getUsername() %>">已发送的消息 <span class="badge"><%=sendMessageNumber%></span></a></li>
                    </ul>
                </li>
                <li>
                    <a href="logout"><span class="glyphicon glyphicon-log-out" />退出</a>
                </li>
            </ul>
            <%
                }
            %>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<%--
<div class="alert alert-warning" role="alert">
    <s:property value="message" />
</div>--%>
