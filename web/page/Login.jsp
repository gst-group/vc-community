<!DOCTYPE html>
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>
<%@ page isELIgnored="false" %>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin 2 - Bootstrap Admin Theme</title>
    <!-- Bootstrap Core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/vcbbs-image/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">请登录</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input id="username" class="form-control" placeholder="username" name="username"
                                       type="text" autofocus>
                            </div>
                            <div class="form-group">
                                <input id="password" class="form-control" placeholder="Password" name="password"
                                       type="password" value="">
                            </div>
                            <a onclick="login();" class="btn btn-lg btn-success btn-block">登录</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
<%-- <script type="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
 --%>
<script>
    function login() {
        var name = document.getElementById("username").value;
        var pwd = document.getElementById("password").value;
        var urls = '${basePath}/admin/login.do?username=' + name + "&password=" + pwd;
        $.ajax({
            url: urls,
            type: 'post',
            dataType: "json",
            success: function (data) {
                var msg = data.message;
                if (msg == "success") {
                    if (location.search.indexOf("redirectURL") != -1) {
                        location.href = decodeURIComponent(location.search.substr(location.search.indexOf("redirectURL") + "redirectURL".length + 1));
                    } else {
                       if(data.extra.rootType==0){
                           location.href = "${basePath}/page/adminAdd.jsp";
                       }else{
                           location.href = "${basePath}/admin/Sensitive.do";
                       }
                    }
                } else if(msg=="权限不足"){
                    alert("账号或密码错误");
                }else{
                    alert(msg);
                }

            }, error: function (data, textStatus) {

            }
        });
    }


</script>
</body>

</html>
