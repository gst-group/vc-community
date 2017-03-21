<!DOCTYPE html>
<!-- saved from url=(0071)http://121.40.137.214:80/1/json.php?mod=admin&act=feedbackList&1465824969 -->
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>
<%@ page isELIgnored="false" %>
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
    <link rel="stylesheet" type="text/css" href="${basePath}/page/util/paging.css">
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
</head>
<body style="background:#ffffff;font-family:&#39;微软雅黑&#39;;">

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
            <a class="navbar-brand" href="${basePath}/page/index.jsp">Admin v2.0</a>
        </div>

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
                    <li>
                        <a href="${basePath}/page/index.jsp"><i class="fa fa-dashboard fa-fw"></i> 首页</a>

                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        发帖审核<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/Sensitive.do">敏感词检测</a></li>
                            <li><a href="${basePath}/admin/getSystem.do?id=1">关键字管理</a></li>
                            <li><a href="${basePath}/admin/getSystemdmin.do?id=2">条件搜索</a></li>
                        </ul>
                    </li>

                    <li><a href=""><i class="fa fa-empire fa-fw"></i> 帐号管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li class="hideIfNotSuper"><a href="${basePath}/page/adminList.jsp">管理员列表</a></li>
                            <li class="hideIfNotSuper"><a href="${basePath}/page/adminAdd.jsp">添加管理员</a></li>
                            <li><a href="${basePath}/page/adminPass.jsp">修改密码</a></li>
                        </ul>
                    </li>


                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/userList.do">用户列表</a></li>
                            <li><a href="${basePath}/page/UserAdd.jsp">新增用户</a></li>
                        </ul>
                    </li>


                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        讨论区管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/userGroup.do">讨论区列表</a></li>
                            <li><a href="${basePath}/admin/userGroupNew.do">新增讨论区</a></li>
                        </ul>
                    </li>


                    <li><a href="">
                        <i class="fa fa-empire fa-fw"></i> 话题管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/articleList.do">话题列表</a></li>
                            <li><a href="${basePath}/admin/articleAddView.do">话题发布</a></li>
                            <li ><a href="${basePath}/admin/allCommentArticle.do">评论列表</a></li>
                            <li>
                                <a href="${basePath}/admin/AlldeleteList.do">删除列表</a></li>
                        </ul>
                    </li>

                    <li><a href="">
                        <i class="fa fa-empire fa-fw"></i> 聊天管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/groupInfo.do">聊天群组列表</a></li>
                            <li><a href="${basePath}/admin/allUser.do">新增聊天群组</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 日记管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/diaryList.do">日记列表</a></li>
                        </ul>
                    </li>


                    <li><a href="">
                        <i class="fa fa-gittip fa-fw"></i> 推送通知<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/messageList.do">推送列表</a></li>
                            <li><a href="${basePath}/admin/sendPushView.do">新建推送</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 积分管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/ScoreInfo.do">积分规则修改</a></li>
                            <li><a href="${basePath}/admin/ScoreList.do">积分规则展示</a></li>
                            <li><a href="${basePath}/admin/ScoreUser.do">用户积分列表</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 商品管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/goodExchangeHistory.do">兑换列表</a></li>
                            <li><a href="${basePath}/admin/goodList.do">商品列表</a></li>
                        </ul>
                    </li>


                    <li><a href="">
                        <i class="fa fa-gittip fa-fw"></i> 用户反馈<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/admin/feedbackList.do" style="color:white;background-color:#DEDEDE"
                                   class="active">反馈列表</a></li>
                            <li><a href="${basePath}/page/Help.jsp">帮助</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 用户数据分析<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/userData.do">用户数据</a></li>
                            <li><a href="${basePath}/admin/ArticleCommentData.do">回帖数据</a></li>
                            <li><a href="${basePath}/admin/messageHistory.do">聊天数据</a></li>
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
            <div class="row">
                <h4 class="page-header">反馈列表</h4>
                <span style="margin-left:70%">
       <div style="margin-left:60%;position: relative;">
                    <button onclick="dep(1)" id="de"
                            style="display:Inline;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        批量删除
                    </button>
                    <button onclick="dep(2)" id="dep"
                            style="display:none;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        确认删除
                    </button>
                    <div style="display: inline-block;">
                        <input type="text" id="se" style="height:24px;padding-right: 24px; "/><button onclick="seach()"
                                style="width:24px;height:24px;background-image:url('${basePath}/img/search.png');border:none;vertical-align: top;margin-left: -24px;"></button>
                    </div>
                </div>
	</span>

                <div class="table-responsive" style="margin-top:1%">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户名</th>
                            <th>内容</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody><c:forEach items="${list}" var="list1" varStatus="status">
                            <tr>
                                <td>
                                    <input type="checkbox" id="${list1.id }" value="${list1.id }" style="display:none"
                                           name="cb">
                                    <c:forEach items="${idlist}" begin="${status.index }" end="${status.index }" var="s"
                                               varStatus="ssss">
                                        ${s }
                                    </c:forEach>
                                </td>
                                <c:forEach items="${userName}" var="list2" begin="${status.index }"
                                           end="${status.index }" varStatus="status">
                                    <td>${list2}</td>
                                </c:forEach>
                                <td>${list1.content}</td>
                                <td><a href="${basePath}/admin/feedbackInfo.do?id=${list1.id}">编辑</a>&nbsp;&nbsp;
                                    <c:if test="${list1.status==2||list1.status==3 }">
                                        <a hef="" onclick="visible(${list1.id})">取消隐藏</a>
                                    </c:if>
                                    <c:if test="${list1.status==0||list1.status==1 }">
                                        <a hef="" onclick="visible(${list1.id})">隐藏</a>
                                    </c:if>

                                    &nbsp;&nbsp;<a onclick="hf(${list1.id})">回复</a>&nbsp;&nbsp;
                                    <a onclick="del(${list1.id})">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${type!=2 }">

                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <div class="tcdPageCode">
                            </div>
                            <div id="dataTables-example_filter" class="dataTables_filter">
                                总页数:${pageCount},选择跳转页:<input size="8" type="text" value="${nowpage }" id="to_page">
                                <button onclick="toPage()" type="button" class="btn btn-default">确定</button>
                            </div>
                            <br><br>
                            <!-- <ul class="pagination">

                            </ul> -->
                        </div>
                    </c:if>
                    <!-- 收索后的页码 -->
                    <c:if test="${type==2 }">
                        <div class="tcdPageCode">
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
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

    <!-- Custom Theme JavaScript -->
    <script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>
    <script src="${basePath}/page/util/paging.js"></script>

    <script type="text/javascript">
        $(".tcdPageCode").createPage({
            pageCount: Number('${pageCount}'),
            current: Number('${nowpage}'),
            backFn: function (p) {
                if ('${type}' != 2) {
                    location.href = "${basePath}/admin/feedbackList.do?wp=" + p;
                } else {
                    location.href = "${basePath}/admin/feedSeach.do?key=${key}&wp=" + p;
                }
            }
        });

        function seachPage() {
            var page;
            location.href = "${basePath}/admin/feedSeach.do?key=${key }&wp=" + page;


        }

        function seach() {
            var seach = $("#se").val();
            location.href = "${basePath}/admin/feedSeach.do?key=" + seach;
        }
        function dep(type) {

            if (type == 1) {
                var div2 = document.getElementById("de");
                div2.style.display = "none";
                var div3 = document.getElementById("dep");
                div3.style.display = "Inline";
                <c:forEach  items="${list}"  var="list1" varStatus="status">
                var div4 = document.getElementById('${list1.id}');
                div4.style.display = "Inline";
                </c:forEach>;
            }

            if (type == 2) {
                if (confirm('确定删除选中反馈?') == false) {
                    return;
                }
                var value = "";
                var checks = document.getElementsByName("cb");
                for (var i = 0; i < checks.length; i++) {
                    if (checks[i].checked) {
                        value = checks[i].value;
                        var urls = "${basePath}/admin/feedback/delete.do?id=" + value;
                        $.ajax({
                            url: urls,
                            type: 'post',
                            dataType: "json",
                            success: function (data, textStatus) {
                            }
                        });
                    }
                }
                if (value != "") {
                    alert("删除成功");
                    location = location;
                }


            }
        }

        function hf(id) {
            var content = prompt("请输入您回复内容", "");

            if (content.length == 0) {
                return;
            }
            $
                    .ajax({
                        url: "${basePath}/admin/feedback/reply.do?id=" + id + "&reply=" + content,
                        type: 'post',
                        dataType: "json",
                        success: function (data, textStatus) {
                            if (data.message == "success") {
                                alert("回复成功");
                                location = location;
                            }
                        }
                    });
        }
        function visible(id) {
            $
                    .ajax({
                        url: "${basePath}/admin/feedback/visible.do?id=" + id,
                        type: 'post',
                        dataType: "json",
                        success: function (data, textStatus) {
                            if (data.message == "success") {
                                alert("操作成功");
                                location = location;
                            }
                        }
                    });
        }

        function del(id) {
            if (confirm('确定删除该反馈?') == false) {
                return;
            }
            $
                    .ajax({
                        url: "${basePath}/admin/feedback/delete.do?id=" + id,
                        type: 'post',
                        dataType: "json",
                        success: function (data, textStatus) {
                            if (data.message == "success") {
                                alert("删除成功");
                                history.go(0);
                            } else {
                                alert("网络错误");
                            }
                        }
                    });
        }
        function toPage() {
            location.href = "${basePath}/admin/feedbackList.do?wp=" + $('#to_page').val();
        }
        ;
    </script>


</div>
</body>
</html>