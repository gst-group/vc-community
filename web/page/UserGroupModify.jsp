<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- saved from url=(0070)http://121.40.137.214:80/1/json.php?mod=admin&act=newUserPage&1465824954 -->
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Admin 2 - Bootstrap Admin Theme</title>
    <!-- jQuery -->
    <script src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="/lk-vc/page/loadContent.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.css"
          rel="stylesheet">

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
    <link href="http://121.40.137.214:80/statics/uploadify/uploadify.css" rel="stylesheet" type="text/css">
    <style>
        table input[type=checkbox] {
            visibility: hidden;
        }

        table.showCheckbox input[type=checkbox] {
            visibility: visible;
        }
    </style>
</head>
<body>
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
        <div style="float: right;margin-top: 20px;margin-right: 50px"><a href="${basePath}/admin/logoutWeb.do">切换账户</a></div>

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
                    <li>                    <c:if test="${visible==0}">

                        <a href="${basePath}/lk-vc/page/index.jsp"><i class="fa fa-dashboard fa-fw"></i> 首页</a>
                    </c:if>
                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        发帖审核<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/Sensitive.do">敏感词检测</a></li>
                            <li><a href="${basePath}/lk-vc/admin/getSystem.do?id=1">关键字管理</a></li>
                            <li><a href="${basePath}/lk-vc/admin/getSystemdmin.do?id=2">条件搜索</a></li>
                        </ul>
                    </li>


                    <c:if test="${visible==0}">

                        li><a href=""><i class="fa fa-empire fa-fw"></i> 帐号管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li class='hideIfNotSuper'><a href="${basePath}/lk-vc/page/adminList.jsp">管理员列表</a></li>
                            <li class='hideIfNotSuper'><a href="${basePath}/lk-vc/page/adminAdd.jsp">添加管理员</a></li>
                            <li><a href="${basePath}/lk-vc/page/adminPass.jsp">修改密码</a></li>
                        </ul>
                    </li>

</c:if>
                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/userList.do">用户列表</a></li>
                            <li><a href="${basePath}/lk-vc/page/UserAdd.jsp">新增用户</a></li>
                        </ul>
                    </li>


                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        讨论区管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/lk-vc/admin/userGroup.do" class="active"
                                   style="color:white;background-color:#DEDEDE">讨论区列表</a></li>
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
                                <a href="${basePath}/vc-iamge/admin/AlldeleteList.do">删除列表</a></li>
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

    <!-- Page Content -->
    <div id="page-wrapper" style="min-height: 685px;">
        <div class="container-fluid">
            <h3>修改群组</h3>
            <div style="padding-top:50px"><h4>群组名称：${grpName}</h4></div>
            <div>
                <span style="float:left">当前组成员</span>
                <span style="float:right"><button
                        style="background:#ed5f00;color:white;border:0px;width:100px;height:35px;"><a
                        style="color:white;text-decoration: none" href="#"
                        onclick="deleteSelected(this)">删除全部</a></button>
           <button
                   style="background:#ed5f00;color:white;border:0px;width:100px;height:35px;"> <a
                   style="color:white;text-decoration: none"
                   href="/lk-vc/admin/userGroupUserAdd.do?grp=${grpName}">添加用户</a></button>
            </span>

                <p>
            </div>
            <!-- /.row -->
            <div style="width:1000px;height:400px;overflow-x:auto;margin-top:100px">
                <table class="table" style="margin-top:5%">
                    <thead>
                    <tr>
                        <td style="height:50px;border:1px #D1D1D1 solid;">
                            <center>姓名</center>
                        </td>
                        <td style="height:50px;border:1px #D1D1D1 solid;">
                            <center>手机号</center>
                        </td>
                        <td style="height:50px;border:1px #D1D1D1 solid;">
                            <center>昵称</center>
                        </td>
                        <td style="height:50px;border:1px #D1D1D1 solid;">
                            <center>操作</center>
                        </td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td style="height:50px;border:1px #D1D1D1 solid;">
                                <center><input type="checkbox" value="${user.id}">${user.realname}&nbsp;&nbsp;&nbsp;
                                </center>
                            </td>
                            <td style="height:50px;border:1px #D1D1D1 solid;">
                                <center>${user.name}</center>
                            </td>
                            <td style="height:50px;border:1px #D1D1D1 solid;">
                                <center>${user.nickname}</center>
                            </td>
                            <td style="height:50px;border:1px #D1D1D1 solid;">
                                <center><a href="#" onclick="deleteUserGroup(this,${user.id})">删除</a></center>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div>

<!-- Bootstrap Core JavaScript -->
<script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/lk-vc/page/loadContent.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script>var isSuperAdmin = ${sessionScope.adminUser.superAdmin?'true':'false'};</script>
<script src="/lk-vc/js/sb-admin-2.js"></script>

<!-- Custom Theme JavaScript -->
<script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>

<script src="http://121.40.137.214:80/statics//uploadify/jquery.uploadify.js" type="text/javascript"></script>
<script type="text/javascript">
    var deleteGroupUserUrl = BASE_DIR + "admin/user/group/delete.do";
    function deleteUserGroup(ele, userId) {
        $.post(deleteGroupUserUrl, {ids: userId}, function (data) {
            if (data.code == 0) {
                $(ele).parents('tr').remove();
            } else {
                alert(data.message);
            }
        })
    }

    function deleteSelected(ele) {
        ele = $(ele);
        if ($('.table.showCheckbox').length == 0) {
            $(".table").addClass("showCheckbox");
            ele.html("确认删除");
        } else {
            var table = $(".table.showCheckbox");
            table.removeClass("showCheckbox");
            ele.html("删除全部");
            var ids = "";
            var checkedBoxes = [];
            table.find(":checkbox").each(function (idx, obj) {
                obj = $(obj);
                var isChecked = obj.prop("checked");
                if (isChecked) {
                    checkedBoxes.push(obj);
                    ids += "," + obj.val();
                }
            });
            ids = ids.substr(1);
            $.post(deleteGroupUserUrl, {ids: ids}, function (data) {
                if (data.code == 0) {
                    for (var checkedBoxIdx in checkedBoxes) {
                        if (checkedBoxes.hasOwnProperty(checkedBoxIdx)) {
                            checkedBoxes[checkedBoxIdx].parents('tr').remove();
                        }
                    }
                } else {
                    alert(data.message);
                }
            })
        }
    }

</script>


</body>
</html>