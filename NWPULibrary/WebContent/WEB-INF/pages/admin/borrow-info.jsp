<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="zh-CN" class="ax-vertical-centered">
<head>
    <title>西工大图书馆系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/bootstrap-3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/bootstrap-3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/bootstrap-3.3.5/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/datatables-1.10.8/css/dataTables.bootstrap.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/common.css">
    <script src="<%=basePath%>static/plugins/jquery-1.11.3/jquery.min.js"></script>
    <script src="<%=basePath%>static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/plugins/bootstrap-3.3.5/js/bootstrap-dropdown.min.js"></script>
    <script src="<%=basePath%>static/plugins/datatables-1.10.8/js/jquery.dataTables.zh_CN.js"></script>
    <script src="<%=basePath%>static/plugins/datatables-1.10.8/js/dataTables.bootstrap.js"></script>
    <script src="<%=basePath%>static/js/common.js"></script>
    <script src="<%=basePath%>static/js/admin/borrow-info.js"></script>
</head>
<body class="bootstrap-admin-with-small-navbar">
 <jsp:include page="../banner.jsp"></jsp:include>
<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">
        <!-- left, vertical navbar -->
        <div class="col-md-12">
            <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
                <li>
                    <a href="<%=basePath%>admin/book"> 图书管理</a>
                </li>
                <li>
                    <a href="<%=basePath%>admin/bookType"> 图书分类管理</a>
                </li>
                <li>
                    <a href="<%=basePath%>admin/borrow"> 图书借阅</a>
                </li>
                <li>
                    <a href="<%=basePath%>admin/return"> 图书归还</a>
                </li>
                <li class="active">
                    <a href="<%=basePath%>admin/borrowInfo"> 借阅查询</a>
                </li>
                <li>
                    <a href="<%=basePath%>admin/admin">管理员管理</a>
                </li>
                <li>
                    <a href="<%=basePath%>admin/student"> 学生管理</a>
                </li>
                <li>
                    <a href="<%=basePath%>admin/setting">系统设置</a>
                </li>
            </ul>
        </div>
        <!-- content -->
        <div class="col-md-12">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default bootstrap-admin-no-table-panel">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">查询</div>
                        </div>
                        <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                            <form class="form-horizontal">
                                <div class="row">
                                    <div class="col-lg-5 form-group">
                                        <label class="col-lg-4 control-label" for="query_bno">图书编号</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="query_bno" type="text" value="">
                                            <label class="control-label" for="query_bno" style="display: none;"></label>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 form-group">
                                        <label class="col-lg-4 control-label" for="query_bname">图书名称</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="query_bname" type="text" value="">
                                            <label class="control-label" for="query_bname" style="display: none;"></label>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 form-group">
                                        <button type="button" class="btn btn-primary" id="btn_query" onclick="query()">查询</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-5 form-group">
                                        <label class="col-lg-4 control-label" for="query_sno">学号</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="query_sno" type="text" value="">
                                            <label class="control-label" for="query_sno" style="display: none;"></label>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 form-group">
                                        <label class="col-lg-4 control-label" for="query_sname">姓名</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="query_sname" type="text" value="">
                                            <label class="control-label" for="query_sname" style="display: none;"></label>
                                        </div>
                                    </div>
                                    <div class="col-lg-2 form-group">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <table id="data_list" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>图书编号</th>
                            <th>图书名称</th>
                            <th>作者</th>
                            <th>价格</th>
                            <th>学号</th>
                            <th>学生姓名</th>
                            <th>借阅日期</th>
                            <th>截止还书日期</th>
                            <th>超期天数</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div>
    	<input type="hidden" id="basePath" value="<%=basePath%>"/>
    </div>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>