<%@ page import="Entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Activity" %><%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/10
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<%
    HttpSession s = request.getSession();
    User user = (User) s.getAttribute("user");
    Integer type = 0;

    if (request.getParameter("type") != null){
        type = Integer.parseInt(request.getParameter("type"));
    }
    List<Activity> activities = null;
%>
<link href="css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
<script src="js/star-rating.js" type="text/javascript"></script>

<link href="css/myactivity.css" rel="stylesheet"/>
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
<%
    if (user != null){
        switch (type){
            case 1:
                activities = (List<Activity>) session.getAttribute("notStartedActivity");
%>
                <h2>未开始的活动</h2>
                <div class="row">
<%
                break;
            case 2:
                activities = (List<Activity>) session.getAttribute("finishedActivity");
%>
                <h2>已结束的活动</h2>
                <div class="row">
<%
                break;
            case 3:
                activities = (List<Activity>) session.getAttribute("allActivity");
%>
                <h2>所有活动</h2>
                <div class="row">
<%
                break;
            case 4:
                activities = (List<Activity>) session.getAttribute("createdActivity");
%>
                <h2>我创建的活动</h2>
                <div class="row">
<%
                break;
            default:
                activities = (List<Activity>) session.getAttribute("allActivity");
%>
                <h2>所有活动</h2>
                <div class="row">
<%
                break;
        }
        if (activities != null && type != 4){
            for(Activity activity : activities){
%>
                    <div class="col-sm-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading"><%=activity.getActName() %></div>
                            <div class="panel-body"><img src="<%=activity.getPostPath() %>" class="img-responsive" style="width:400px;height:350px" alt="Image"></div>
                            <div class="panel-footer">

                                <form action="getAct" method="get">
                                    <input type="hidden" name="actId" value="<%=activity.getActId() %>" />
                                    <p><button type="submit" class="btn btn-default btn-block" >View Details >></button></p>
                                </form>
                                <button class="btn btn-default btn-block judge">评分</button>
                                <div id="judge" style="display: none">
                                    <%--<select id="score" style="width: 50%">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>--%>
                                    <input id="input-21b" value="0" type="number" class="rating" min=0 max=5 step=1 data-size="xs">
                                    <input class="actId" type="hidden" value="<%=activity.getActId() %>"/>
                                    <input class="userId" type="hidden" value="<%=user.getUserId() %>"/>
                                    <button class="btn btn-default score" style="width: 30%">提交评分</button>
                                </div>
                            </div>
                        </div>
                    </div>
<%
            }
        }
        else if (type == 4){
%>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>活动海报</th>
                            <th>活动名称</th>
                            <th>状态</th>
                            <th>编辑</th>
                            <th>已报名人数</th>
                            <th>查看已报名成员</th>
                            <th>删除活动</th>
                        </tr>
                        </thead>
                        <tbody>
<%
            for (Activity activity : activities){
%>
                        <tr>
                            <td><img src="<%=activity.getPostPath() %>" alt="post" width="150px" height="200px"/></td>
                            <td><%=activity.getActName() %></td>
                            <td>
<%
                if (activity.getActStat() < 6){
%>
                                <label class="glyphicon glyphicon-ok"></label>审核通过
<%
                }
                else if (activity.getActStat() == 6){
%>
                                <label class="glyphicon glyphicon-minus"></label>审核中
<%
                }
                else {
%>
                                <label class="glyphicon glyphicon-remove"></label>审核未通过
<%
                }
%>
                            </td>
                            <td>
                                <a href="getOneAct?actId=<%=activity.getActId() %>">点击编辑活动信息</a>
                            </td>
                            <td><%=activity.getCurrentMember() %>/<%=activity.getMaxMember() %></td>
                            <td>
                                <a href="getActMem?actId=<%=activity.getActId() %>">点击查看已报名成员</a>
                            </td>
                            <td>
                                <input type="hidden" class="actId" value="<%=activity.getActId() %>"/>
                                <input type="hidden" class="username" value="<%=user.getUsername() %>"/>
                                <button class="btn btn-default delete">删除</button>
                            </td>
                        </tr>
<%
            }
%>
                        </tbody>
                    </table>
<%
        }
        else {
%>
            <p>暂时无活动</p>
<%
        }
    }
%>
                </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(".judge").click(function () {
        $(this).hide();
        $(this).parent().find("#judge").toggle();
    });

    $(".score").click(function () {
        var userId = $(this).parent().find(".userId").val();
        var actId = $(this).parent().find(".actId").val();
        var score = $(this).parent().find(".rating").val();

        $.ajax({
            type : 'post',
            url : 'scoreAct',
            data : {
                userId : userId,
                actId : actId,
                score : score
            },
            success : function () {
                alert("评分成功！");
            }
        });

        $(this).parent().toggle();
    });


    $(".delete").click(function () {
        var username = $(this).parent().find(".username").val();
        var actId = $(this).parent().find(".actId").val();

        $.ajax({
            type : 'post',
            url : 'deleteAct',
            data : {
                actId : actId,
                username : username
            },
            success : function () {
                alert("删除成功！");
            }
        });

        $(this).parent().parent().hide(300);

    })
</script>
<jsp:include page="footer.jsp"/>
