<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="MyTag" uri="http://com.npu.library/MyTag"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="zh-CN" class="ax-vertical-centered">
<head>
  
</head>
<body class="bootstrap-admin-with-small-navbar">
   <nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation" style="background:#438eb9">
        <div class="container" style="background:#438eb9">
            <div class="row" style="background:#438eb9">
                <div class="col-lg-12" style="background:#438eb9">
                    <div class="collapse navbar-collapse main-navbar-collapse" style="background:#438eb9">
                        <a class="navbar-brand" href="#"><strong style="color:#fff !important;">欢迎使用西北工业大学图书馆系统</strong></a>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown" style="color:#fff !important;"> <i class="glyphicon glyphicon-user"></i> 欢迎您，<MyTag:name/><i class="caret"></i></a>
                                <ul class="dropdown-menu">
                                    <li><a href="<%=basePath%>student/student" style="color:#000 !important;">修改</a></li>
                                    <li role="presentation" class="divider"></li>
                                    <li><a href="<%=basePath%>logout" style="color:#000 !important;">退出</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</body>
</html>