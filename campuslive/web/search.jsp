<%@ page import="Entity.User" %>
<%@ page import="Entity.Activity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Team" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/20
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<h2 align="center">搜索结果</h2>
<hr>
<style type="text/css">
    #btns form{
        display: inline;
    }
    body{
        padding-top: 50px;
    }
</style>
<div class="container marketing">
    <div class="row">
        <h2>相关活动</h2><hr>
        <%
            HttpSession s = request.getSession();
            User user = (User) s.getAttribute("user");
            List<Activity> searchResult = (List<Activity>) s.getAttribute("searchResult");
            List<Team> teamResult = (List<Team>) s.getAttribute("teamResult");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            for (Activity activity : searchResult){
        %>
        <div class="col-sm-6 col-md-4">
            <div class="thumbnail">
                <img src="<%=activity.getPostPath() %>" alt="image placeholder" align="center" style="width: 350px; height: 300px">
                <div class="caption">
                    <h3><%=activity.getActName() %></h3>
                    <p>活动时间：<br><%=df.format(activity.getActStartTime().getTime()) %> -- <%=df.format(activity.getActEndTime().getTime()) %></p>
                    <p>报名时间：<br><%=df.format(activity.getRegStartTime().getTime()) %> -- <%=df.format(activity.getRegEndTime().getTime()) %></p>
                    <p>人数：<%=activity.getCurrentMember() %>/<%=activity.getMaxMember() %></p>
                    <p>
                    <div id="btns">
                        <a href="getAct?actId=<%=activity.getActId() %>" class="btn btn-primary" role="button">View Detail >></a>
                        <%
                            if (activity.getActStat() == 1){
                                if (user == null){
                        %>
                        <a href="#" class="btn btn-default disabled" role="button">请先登录或注册</a>
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
                        }
                        else {
                            if (user == null){
                        %>
                        <a href="#" class="btn btn-default disabled" role="button">请先登录或注册</a>
                        <%
                        }
                        else {
                        %>
                        <a href="#" class="btn btn-default disabled" role="button">无法报名</a>
                        <%
                                }
                            }
                        %>
                    </div>
                    </p>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div class="row">
        <h2>相关团队</h2><hr>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>团队编号</th>
                <th>团队名称</th>
                <th>查看团队活动</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Team team : teamResult){
            %>
            <tr>
                <td><%=team.getTeamId() %></td>
                <td><%=team.getTeamName() %></td>
                <td><a href="getTeamAct?teamId=<%=team.getTeamId() %>">点击查看团队举办的活动</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="footer.jsp"/>