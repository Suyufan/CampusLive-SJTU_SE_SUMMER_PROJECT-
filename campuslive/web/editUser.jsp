<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/18
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<%
    HttpSession s = request.getSession();
    User user = (User) s.getAttribute("user");
    if (user != null){
%>
<link href="css/register.css" rel="stylesheet"/>
<div class="container">
    <h2>编辑你的个人信息</h2>
    <form class="form-register" action="updateUser" method="post">
        <input type="hidden" name="userId" value="<%=user.getUserId() %>">
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" name="email" value="<%=user.getEmail()%>" required>

        <label for="inputSchool" class="sr-only">School</label>
        <input type="text" id="inputSchool" class="form-control" name="school" value="<%=user.getSchool()%>" required>

        <label for="inputTelephone" class="sr-only">Tel Number</label>
        <input type="tel" id="inputTelephone" class="form-control" name="telephone" value="<%=user.getTelephone()%>" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
    </form>
</div>
<%
    }
%>


<jsp:include page="footer.jsp"/>
