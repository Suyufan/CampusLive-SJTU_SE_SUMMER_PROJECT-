<%--
  Created by IntelliJ IDEA.
  User: 昱凡
  Date: 2016/7/4
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="header.jsp"/>
<link href="css/register.css" rel="stylesheet"/>
    <div class="container">
    <form class="form-register" action="register" method="post">
        <h2 class="form-signin-heading">Please register</h2>
        <h3><s:property value="message"/></h3>

        <label for="inputUsername" class="sr-only">Choose a username</label>
        <input type="text" id="inputUsername" class="form-control" name="username" placeholder="Username" required autofocus>

        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" class="form-control" name="password" placeholder="Password" required>

        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" class="form-control" name="email" placeholder="Email address" required>

        <label for="inputSchool" class="sr-only">School</label>
        <input type="text" id="inputSchool" class="form-control" name="school" placeholder="School" required>

        <label for="inputTelephone" class="sr-only">Tel Number</label>
        <input type="tel" id="inputTelephone" class="form-control" name="telephone" placeholder="Tel Number" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
        <button class="btn btn-lg btn-primary btn-block" type="reset">Reset</button>
    </form>
</div>

<jsp:include page="footer.jsp"/>