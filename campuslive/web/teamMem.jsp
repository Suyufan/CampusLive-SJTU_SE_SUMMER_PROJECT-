<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/17
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<%
    HttpSession s = request.getSession();
    User user = (User) s.getAttribute("user");

    List<User> members = (List<User>) session.getAttribute("members");
    List<User> otherUsers = (List<User>) session.getAttribute("otherUsers");
    Integer teamId = (Integer) session.getAttribute("teamId");
    System.out.println(teamId);

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
    #tab{
        padding: 50px;
    }
</style>
<div class="container-fluid">
    <h2>管理你的团队成员</h2>
    <hr>
    <div class="col-lg-12" height="500px">
        <form action="addMem" method="get" id="sel">
            <div class="form-group">
                <label for="sel" class="col-sm-4">添加新成员：</label><br/>
                <div class="col-lg-4">
                    <select class="form-control col-sm-8" name="username">
                        <%
                            for (User otherUser : otherUsers){
                                if (otherUser.getUserId() != user.getUserId()){
                        %>
                        <option><%=otherUser.getUsername() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <input type="hidden" class="form-control" name="teamId" value="<%=teamId %>"/>
                <button class="btn btn-default" type="submit">添加</button>
                <p><br></p>
            </div>

        </form>
        <hr>
    </div>


    <table class="table table-striped" id="tab">
        <thead>
        <tr>
            <th>成员编号</th>
            <th>成员名称</th>
            <th>邮箱</th>
            <th>移出团队</th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (User member : members){
        %>
        <tr>
            <td><%=i %></td>
            <td><%=member.getUsername() %></td>
            <td><%=member.getEmail() %></td>
            <td>
                <a href="removeMem?teamId=<%=teamId %>&userId=<%=member.getUserId() %>">点击移出团队</a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
</div>
<%
    }
%>

<jsp:include page="footer.jsp"/>
