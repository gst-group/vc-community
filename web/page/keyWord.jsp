<!DOCTYPE html>
<!-- saved from url=(0072)http://121.40.137.214:80/1/json.php?mod=admin&act=newNotifyPage&1465824995 -->
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <link href="/lk-vc/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/modernizr.custom.js"></script>
    <!-- STYLES -->

    <script type="text/javascript" src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/lk-vc/page/loadContent.js"></script>

    <style type="text/css">
        .a-upload {
            padding: 4px 10px;
            height: 20px;
            line-height: 20px;
            position: relative;
            cursor: pointer;
            color: #888;
            background: #fafafa;
            border: 1px solid #ddd;
            border-radius: 4px;
            overflow: hidden;
            display: inline-block;
            *display: inline;
            *zoom: 1
        }

        .a-upload input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
            filter: alpha(opacity=0);
            cursor: pointer
        }

        .a-upload:hover {
            color: #444;
            background: #eee;
            border-color: #ccc;
            text-decoration: none
        }

        /*file*/
        .file {
            position: relative;
            display: inline-block;
            border-radius: 4px;
            padding: 4px 12px;
            overflow: hidden;
            text-decoration: none;
            text-indent: 0;
            line-height: 20px;
        }

        .file input {
            position: absolute;
            font-size: 100px;
            right: 0;
            top: 0;
            opacity: 0;
        }

        .file:hover {
            text-decoration: none;
        }

        body, h2, ul {
            margin: 0;
            padding: 0;
        }

        #BgDiv {
            background-color: #e3e3e3;
            position: absolute;
            z-index: 99;
            left: 0;
            top: 0;
            display: none;
            width: 100%;
            height: 1000px;
            opacity: 0.5;
            filter: alpha(opacity=50);
            -moz-opacity: 0.5;
        }

        #DialogDiv {
            position: absolute;
            width: 400px;
            left: 50%;
            top: 50%;
            margin-left: -200px;
            height: auto;
            z-index: 100;
            background-color: #fff;
            border: 1px #8FA4F5 solid;
            padding: 1px;
        }

        #DialogDiv h2 {
            height: 25px;
            font-size: 14px;
            position: relative;
            padding-left: 10px;
            line-height: 25px;
        }

        #DialogDiv h2 a {
            text-decoration: none;
            position: absolute;
            right: 5px;
            font-size: 12px;
            color: #000000
        }

        #DialogDiv .form {
            padding: 10px;
        }

        ul {
            list-style: none;
        }

        ul li {
            list-style: none;
        }

        #filter {
            text-align: left;
        }

        #list1 {
            border-right: 1px solid black;
            text-align: left;
            width: 120px;
            display: inline-block;
        }

        #list2 {
            text-align: left;
            width: 180px;
            display: inline-block;
            max-height: 170px;
            overflow-x: hidden;
        }
    </style>


    <link href="http://121.40.137.214:80/statics/uploadify/uploadify.css" rel="stylesheet" type="text/css">
</head>
<body style="background:#ffffff;font-family:&#39;å¾®è½¯éé»&#39;;&#39;">
<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" >Admin v2.0</a>
        </div>
        <div style="float: right;margin-top: 20px;margin-right: 50px"><a href="${basePath}/lk-vc/admin/logoutWeb.do">切换账户</a></div>

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" placeholder="Search...">
                            <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                        </div>
                        <!-- /input-group -->
                    </li>
                    <li><c:if test="${visible==0}">

                        <a href="${basePath}/lk-vc/page/index.jsp" ><i class="fa fa-dashboard fa-fw"></i> 首页</a>
                    </c:if>
                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        发帖审核<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/Sensitive.do">敏感词检测</a></li>
                            <li><a href="${basePath}/lk-vc/admin/getSystem.do?id=1"class="active"
                                   style="color:white;background-color:#DEDEDE">关键字管理</a></li>
                            <li><a href="${basePath}/lk-vc/admin/getSystemdmin.do?id=2">条件搜索</a></li>
                        </ul>
                    </li>

                    <c:if test="${visible==0}">

                    <li><a href=""><i class="fa fa-empire fa-fw"></i> 帐号管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li class='hideIfNotSuper'><a href="${basePath}/lk-vc/page/adminList.jsp">管理员列表</a></li>
                            <li class='hideIfNotSuper'><a href="${basePath}/lk-vc/page/adminAdd.jsp">添加管理员</a></li>
                            <li><a href="${basePath}/lk-vc/page/adminPass.jsp">修改密码</a></li>
                        </ul>
                    </li>
</c:if>

                    <li><a href=""><i class="fa fa-drupal fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/userList.do">用户列表</a></li>
                            <li><a href="${basePath}/lk-vc/page/UserAdd.jsp">新增用户</a></li>
                        </ul>
                    </li>


                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        讨论区管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/userGroup.do">讨论区列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/userGroupNew.do">新增讨论区</a></li>
                        </ul>
                    </li>


                    <li><a href="">
                        <i class="fa fa-empire fa-fw"></i> 话题管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/articleList.do">话题列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/articleAddView.do">话题发布</a></li>
                            <li ><a href="${basePath}/lk-vc/admin/allCommentArticle.do">评论列表</a></li>
                            <li>
                                <a href="${basePath}/lk-vc/admin/AlldeleteList.do">删除列表</a></li>
                        </ul>
                    </li>
                    <c:if test="${visible==0}">
                    <li><a href="">
                        <i class="fa fa-empire fa-fw"></i> 聊天管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/groupInfo.do">聊天群组列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/allUser.do">新增聊天群组</a></li>
                        </ul>
                    </li>
</c:if>

                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 日记管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/diaryList.do">日记列表</a></li>
                        </ul>
                    </li>

                    <c:if test="${visible==0}">
                    <li><a href="">
                        <i class="fa fa-gittip fa-fw"></i> 推送通知<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/messageList.do">推送列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/sendPushView.do">新建推送</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 积分管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/ScoreInfo.do">积分规则修改</a></li>
                            <li><a href="${basePath}/lk-vc/admin/ScoreList.do">积分规则展示</a></li>
                            <li><a href="${basePath}/lk-vc/admin/ScoreUser.do">用户积分列表</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 商品管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/goodExchangeHistory.do">兑换列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/goodList.do">商品列表</a></li>
                        </ul>
                    </li>


                    <li><a href="">
                        <i class="fa fa-gittip fa-fw"></i> 用户反馈<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/feedbackList.do">反馈列表</a></li>
                            <li><a href="${basePath}/lk-vc/page/Help.jsp">帮助</a></li>
                        </ul>
                    </li>
</c:if>

                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 用户数据分析<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/userData.do">用户数据</a></li>
                            <li><a href="${basePath}/lk-vc/admin/ArticleCommentData.do">回帖数据</a></li>
                            <c:if test="${visible==0}">
                            <li><a href="${basePath}/lk-vc/admin/messageHistory.do">聊天数据</a></li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>
    <div id="page-wrapper" style="min-height: 685px;">
        <div class="container-fluid">

            <div class="row">




                <h4 class="page-header">关键词</h4>
                <div>
                    <textarea id="SensitiveContent" rows="15" cols="100">${s.name}</textarea><br>
                  <input class="but" type="button"
                                   style="background:#ed5f00;color:white;border:0px;width:100px;height:40px;margin-left:30%"
                                   class="btn btn-default" onclick="save()" value="保存"/>
                </div>

            </div>

            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- jQuery -->
<script src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script>var isSuperAdmin = ${sessionScope.adminUser.superAdmin?'true':'false'};</script>
<script src="/lk-vc/js/sb-admin-2.js"></script>

<script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>

<script src="http://121.40.137.214:80/statics//uploadify/jquery.uploadify.js" type="text/javascript"></script>


<!-- JS -->
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/waypoints.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/waypoints-sticky.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/jquery.hideseek.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/rainbow-custom.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/jquery.anchor.js"></script>
<script src="${basePath}/lk-vc/page/util/javascripts/initializers.js"></script>

<script>

    function  save() {
        var html=$("#SensitiveContent").val();
        var sendConte={
           id:2,
            name:html
        }
        $.ajax({
            url:"${basePath}/lk-vc/admin/updateSystem.do",
            data:sendConte,
            type:"post",
            dataType:"json",
            success:function (data) {
                if(data=="success"){
                    alert("保存成功");
                }
            },error:function (data) {
                alert("保存成功");
            }
        });
    }
</script>
</body>
</html>