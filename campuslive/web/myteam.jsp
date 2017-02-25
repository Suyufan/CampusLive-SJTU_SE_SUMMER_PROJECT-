<%@ page import="java.util.List" %>
<%@ page import="Entity.Team" %>
<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/15
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<%
    HttpSession s = request.getSession();
    List<Team> teams = (List<Team>) s.getAttribute("createdTeams");
    User user = (User) s.getAttribute("user");

    if (user != null){
%>
<style type="text/css">
    body{
        padding-top: 56px;
    }
    .container-fluid{
        width: 100%;
        height: auto;
    }
</style>
<div class="container-fluid">
    <h2>管理你的团队</h2>
    <p>点击编辑或添加成员修改团队信息</p>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>团队编号</th>
                <th>团队名称</th>
                <th>编辑</th>
                <th>添加团队成员</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Team team : teams){
        %>
            <tr>
                <td><%=team.getTeamId() %></td>
                <td><%=team.getTeamName() %></td>
                <td>
                    <a href="getTeamInfo?teamId=<%=team.getTeamId() %>">点击编辑团队信息</a>
                </td>
                <td>
                    <a href="getMem?teamId=<%=team.getTeamId() %>">点击添加团队成员</a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<%
    }
%>

<jsp:include page="footer.jsp"/>
