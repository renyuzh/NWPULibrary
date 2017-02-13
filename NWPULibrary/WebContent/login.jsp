<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html lang="zh-CN" class="bootstrap-admin-vertical-centered">
<head>
    <title>西工大图书馆系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/bootstrap-3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/bootstrap-3.3.5/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/bootstrap-3.3.5/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" href="<%=basePath%>static/css/common.css">
    <script src="<%=basePath%>static/plugins/jquery-1.11.3/jquery.min.js"></script>
    <script src="<%=basePath%>static/plugins/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>static/js/login.js"></script>
     <script src="<%=basePath%>static/plugins/Particleground.js"></script>
    <style type="text/css">
        .alert{
            margin: 0 auto 20px;
            text-align: center;
        }
        body{height:100%;background:#16a085;overflow:hidden;}
        canvas{z-index:-1;position:absolute;}
    </style>
    <script type="text/javascript">
 $(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
});
    </script>
</head>
<body class="bootstrap-admin-without-padding">
<canvas class="pg-canvas" width="1366" height="599"></canvas>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <form class="bootstrap-admin-login-form" >
            <div class="form-group" style="color:#fff;text-align: center;font-size: 20px">西工大图书馆系统 </div>
                <div class="form-group">
                    <label class="control-label" for="username">账&nbsp;号</label>
                    <input type="text" class="form-control" id="username" placeholder="学号/管理员账号"/>
                    <label class="control-label" for="username" style="display:none;"></label>
                </div>
                <div class="form-group">
                    <label class="control-label" for="password">密&nbsp;码</label>
                    <input type="password" class="form-control" id="password" placeholder="密码"/>
                    <label class="control-label" for="username" style="display:none;"></label>
                </div>
                <div class="form-group">
                    <label for="role">身&nbsp;份</label>
                    <select class="form-control" id="role">
                        <option value="1">学生</option>
                        <option value="2">管理员</option>
                    </select>
                </div>
                <input type="button" class="btn btn-lg btn-primary" id="login_submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" style="background:#048f74;color:#f8f8f8;"/>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="infoModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" id="div_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
    <div>
    	<input type="hidden" id="basePath" value="<%=basePath%>"/>
    </div>
</div>
</body>
</html>