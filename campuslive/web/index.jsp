<%@ page import="java.util.List" %>
<%@ page import="Entity.Activity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Entity.User" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/2
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
<style type="text/css">
    #btns form{
        display: inline;
    }
</style>
<%
    HttpSession s = request.getSession();
    User user = (User) s.getAttribute("user");
    List<Activity> latest = (List<Activity>) s.getAttribute("latest");
    List<Activity> hottest = (List<Activity>) s.getAttribute("hottest");
    List<Activity> frontpage = (List<Activity>) s.getAttribute("frontpage");
    List<Activity> recommandAct = (List<Activity>) s.getAttribute("recommandAct");

    int pagesize = frontpage.size();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <%
            for(int i=0; i<pagesize; i++){
        %>
        <li data-target="#myCarousel" data-slide-to="<%=i %>" class></li>
        <%
            }
        %>
        <%--<li data-target="#myCarousel" data-slide-to="0" class></li>
        <li data-target="#myCarousel" data-slide-to="1" class></li>
        <li data-target="#myCarousel" data-slide-to="2" class></li>--%>
    </ol>
    <div class="carousel-inner" role="listbox">
        <%
            int f = 0;
            for (Activity activity : frontpage){
                String a = "active";
        %>
        <div class="item <%=f==0?a:"" %>">
            <img src="<%=activity.getPostPath() %>" alt="img placeholder">
            <div class="container">
                <div class="carousel-caption">
                    <h1><%=activity.getActName() %></h1>
                    <form action="getAct" method="get">
                        <p>活动时间：<%=df.format(activity.getActStartTime().getTime()) %> -- <%=df.format(activity.getActEndTime().getTime()) %></p>
                        <p>报名时间：<%=df.format(activity.getRegStartTime().getTime()) %> -- <%=df.format(activity.getRegEndTime().getTime()) %></p>
                        <input type="hidden" name="actId" value="<%=activity.getActId() %>" />
                        <p><button type="submit" class="btn btn-default" >View Details >></button></p>
                    </form>
                </div>
            </div>
        </div>
        <%
                f++;
            }
        %>

        <%--<div class="item active">
            <img src="pic/post/sunset.jpg" alt="Chania">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Head Line</h1>
                    <form action="getAct" method="get">
                        <p>This is an example test line for the UI model and also a placeholder for further modification</p>
                        <input type="hidden" name="actId" value="1" />
                        <p><button type="submit" class="btn btn-default" >View Details >></button></p>
                    </form>
                </div>
            </div>
        </div>

        <div class="item">
            <img src="pic/post/tiger.jpg" alt="Chania">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Head Line</h1>
                    <form action="getAct" method="get">
                        <p>This is an example test line for the UI model and also a placeholder for further modification</p>
                        <input type="hidden" name="actId" value="2" />
                        <p><button type="submit" class="btn btn-default" >View Details >></button></p>
                    </form>
                </div>
            </div>
        </div>

        <div class="item">
            <img src="pic/post/water.jpg" alt="Flower">
            <div class="container">
                <div class="carousel-caption">
                    <h1>Head Line</h1>
                    <form action="getAct" method="get">
                        <p>This is an example test line for the UI model and also a placeholder for further modification</p>
                        <input type="hidden" name="actId" value="3" />
                        <p><button type="submit" class="btn btn-default" >View Details >></button></p>
                    </form>
                </div>
            </div>
        </div>--%>


    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<p><br></p>
<%
    if (user != null){
%>
<div class="container marketing">
    <h2>猜你喜欢</h2><hr>
    <div class="row">
        <%
            for (Activity activity : recommandAct){
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
                    </p>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
<%
    }
%>

<div class="container marketing">
    <h2>最新活动</h2><hr>
    <div class="row">
        <%
        for (Activity activity : latest){
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
                    </p>
                </div>
            </div>
        </div>
        <%
            }
        %>

    </div>
</div>

<div class="container marketing">
    <h2>最热活动</h2><hr>
    <div class="row">
        <%
            for (Activity activity : hottest){
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
                        <br><a href="#" class="btn btn-default disabled" role="button">请先登录或注册</a>
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
</div>

<jsp:include page="footer.jsp" />


<%--<script src="js/jquery-3.0.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>--%>
