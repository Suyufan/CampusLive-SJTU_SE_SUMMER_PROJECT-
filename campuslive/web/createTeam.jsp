<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/15
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<%
    HttpSession s = request.getSession();
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
    <form action="createTeam" method="post">
        <div class="col-lg-12" id="basicInfo">
            <div class="alert alert-info">
                <h2>团队信息</h2>
            </div>
            <hr>
            <div class="form-group">
                <label class="col-sm-3 control-label">团队名称:</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="teamName" placeholder="请输入团队名称" required/>
                </div>
            </div>
            <br>
            <div class="form-group">
                <label class="col-sm-3 control-label">团队信息:</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="teamDesc" rows="50" required>

                    </textarea>
                </div>
            </div>
        </div>
        <br>
        <hr>
        <input type="hidden" class="form-control" name="userId" value="<%=user.getUserId() %>"/>
        <input type="submit" class="form-control btn-primary" value="提交"/>
    </form>
</div>
<%
    }
%>

<jsp:include page="footer.jsp"/>
