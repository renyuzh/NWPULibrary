<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="zh-CN" class="ax-vertical-centered">
<head>

</head>
<body class="bootstrap-admin-with-small-navbar">
    <div id="footer" class="container">
<nav class="navbar navbar-default navbar-fixed-bottom" style="background:#438eb9">
    <div class="navbar-inner navbar-content-center" style="text-align: center" style="background:#438eb9">
        <p class="text-muted credit" style="padding-top: 15px;color:#fff;">
            © 2016 Powered by 张仁宇
        </p>
    </div>
</nav>
</div>
</body>
</html>