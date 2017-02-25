<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/18
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<%
    HttpSession s = request.getSession();
    User user = (User) s.getAttribute("user");
    List<User> members = (List<User>) s.getAttribute("actMem");

    if (user != null){
%>
<style type="text/css">
    body {
        padding-top: 52px;
    }

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

    .col-sm-3{
        padding-top: 40px;
    }
    .col-sm-9{
        padding-top: 5px;
    }
</style>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <h4>我的活动</h4>
            <ul class="nav nav-pills nav-stacked">
                <li>
                    <form action="notStarted" method="post">
                        <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                        <button type="submit" class="btn btn-primary btn-block">未开始的活动</button>
                    </form>
                </li>
                <li>
                    <form action="finished" method="post">
                        <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                        <button type="submit" class="btn btn-primary btn-block">已结束的活动</button>
                    </form>
                </li>
                <li>
                    <form action="all" method="post">
                        <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                        <button type="submit" class="btn btn-primary btn-block">所有活动</button>
                    </form>
                </li>
                <hr>
                <li>
                    <form action="created" method="post">
                        <input type="hidden" name="userId" value="<%=user.getUserId() %>">
                        <button type="submit" class="btn btn-primary btn-block">我创建的活动</button>
                    </form>
                </li>
            </ul><br>
        </div>
        <div class="col-sm-9">
            <h2>成员名单与信息</h2>
            <hr>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>成员编号</th>
                    <th>成员昵称</th>
                    <th>邮箱</th>
                    <th>手机</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (User mem : members){
                %>
                <tr>
                    <td><%=mem.getUserId()%></td>
                    <td><%=mem.getUsername() %></td>
                    <td><%=mem.getEmail() %></td>
                    <td><%=mem.getTelephone() %></td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%
    }
%>

<jsp:include page="footer.jsp"/>