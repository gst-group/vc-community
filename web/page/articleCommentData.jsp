<!DOCTYPE html>
<!-- saved from url=(0071)http://121.40.137.214:80/1/json.php?mod=admin&act=articlesList&1465824919 -->
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

    <!-- MetisMenu CSS -->
    <link href="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/lk-vc/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${basePath}/page/util/paging.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
                    </li>
                    <li>
<c:if test="${visible==0}">
                        <a href="${basePath}/page/index.jsp"><i class="fa fa-dashboard fa-fw"></i> 首页</a>
</c:if>

                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        发帖审核<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/Sensitive.do">敏感词检测</a></li>
                            <li><a href="${basePath}/admin/getSystem.do?id=1">关键字管理</a></li>
                            <li><a href="${basePath}/admin/getSystemdmin.do?id=2">条件搜索</a></li>
                        </ul>
                    </li>

<c:if test="${visible==0}">
                    <li><a href=""><i class="fa fa-empire fa-fw"></i> 帐号管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li class="hideIfNotSuper"><a href="${basePath}/page/adminList.jsp">管理员列表</a></li>
                            <li class="hideIfNotSuper"><a href="${basePath}/page/adminAdd.jsp">添加管理员</a></li>
                            <li><a href="${basePath}/page/adminPass.jsp">修改密码</a></li>
                        </ul>
                    </li>
</c:if>

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
<c:if test="${visible==0}">
                    <li><a href="">
                        <i class="fa fa-empire fa-fw"></i> 聊天管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/groupInfo.do">聊天群组列表</a></li>
                            <li><a href="${basePath}/admin/allUser.do">新增聊天群组</a></li>
                        </ul>
                    </li>
</c:if>

                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 日记管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/diaryList.do">日记列表</a></li>
                        </ul>
                    </li>

<c:if test="${visible==0}">
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
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/feedbackList.do">反馈列表</a></li>
                            <li><a href="${basePath}/page/Help.jsp">帮助</a></li>
                        </ul>
                    </li>
</c:if>

                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 用户数据分析<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/userData.do">用户数据</a></li>
                            <li><a href="${basePath}/admin/ArticleCommentData.do" class="active"
                                   style="color:white;background-color:#DEDEDE">回帖数据</a></li>
<c:if test="${visible==0}">
    <li><a href="${basePath}/admin/messageHistory.do">聊天数据</a></li>
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
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <h4 class="page-header">回贴数据</h4>
                <span style="margin-left:80%">
        
         <button onclick="dep(1)" id="de"
                 style="display:Inline;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">批量删除</button>
        <button onclick="dep(2)" id="dep"
                style="display:none;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">确认删除</button>

        </span>
                <div class="table-responsive">
                    <div style="overflow: auto; overflow-y:hidden;margin-top:1%">
                        <table style="width:2048px" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th style="width:100px">编号</th>
                                <th style="width:200px">帖子名称</th>
                                <th style="width:200px">所属讨论区</th>
                                <th style="width:200px">帖子时间</th>
                                <th style="width:200px">发帖昵称</th>
                                <th style="width:200px">发帖手机号</th>
                                <th style="width:200px">浏览来源</th>
                                <th style="width:200px">浏览昵称</th>
                                <th style="width:200px">浏览手机号</th>
                                <th style="width:200px">浏览时间</th>
                                <th style="width:200px">浏览结束时间</th>
                                <th style="width:200px">浏览时长</th>
                                <th style="width:200px">是否回复</th>
                                <th style="width:200px">回帖字数</th>
                                <th style="width:200px">是否收藏</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${li}" var="info" varStatus="status">
                            <tbody>
                            <tr>
                                <td><input type="checkbox" style="display:none" id="${info.articleCommentId }"
                                           value="${info.articleCommentId }" name="cb">${info.id}</td>
                                <td>${info.articleTitle}</td>
                                <td>${info.group}</td>
                                <td>${info.ArticleTime}</td>
                                <td>${info.user1.nickname}</td>
                                <td>${info.user1.name}</td>
                                <td>
                                <c:choose>
                                    <c:when test="${info.source==1}">
                                        提醒
                                    </c:when>
                    <c:otherwise>讨论区</c:otherwise>
                                </c:choose>

                                </td>
                                <td>${info.user2.nickname}</td>
                                <td>${info.user2.name}</td>
                                <td>${info.BeginTime}</td>
                                <td>${info.EndTime}</td>
                                <td>${info.ViewLength }</td>
                                <td>
                                    <c:if test="${info.acLength!=0}">
                                        已回复
                                    </c:if>
                                    <c:if test="${info.acLength==0}">
                                        未回复
                                    </c:if>
                                </td>
                                <td>${info.acLength}</td>
                                <td>
                                    <c:if test="${info.articleFollow!=0}">
                                        已收藏
                                    </c:if>
                                    <c:if test="${info.articleFollow==0}">
                                        未收藏
                                    </c:if>
                                </td>
                            </tr>

                            </tbody>
                            </c:forEach>

                        </table>
                    </div>
                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                        <div class="tcdPageCode">
                        </div>

                        <div id="dataTables-example_filter" class="dataTables_filter">
                            总页数:${count}选择跳转页:<input size="8" type="text" value="${nowpage}" id="to_page">
                            <button onclick="toPage()" type="button" class="btn btn-default">确定</button>
                        </div>
                        <span style="float:right;margin-right:110px">
                            <button onclick="exports()" style="background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">导出</button></span>
                        <br><br>
                        <!-- <ul class="pagination">

                        </ul> -->
                    </div>

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

        function dep(type) {

            if (type == 1) {
                var div2 = document.getElementById("de");
                div2.style.display = "none";
                var div3 = document.getElementById("dep");
                div3.style.display = "Inline";
                <c:forEach  items="${li}"  var="info" varStatus="status">
                var div4 = document.getElementById('${info.articleCommentId}');
                div4.style.display = "Inline";
                </c:forEach>;
            }

            if (type == 2) {
                if (confirm('确定删除该数据?') == false) {
                    return;
                }
                var value = "";
                var checks = document.getElementsByName("cb");
                for (var i = 0; i < checks.length; i++) {
                    if (checks[i].checked) {
                        value = checks[i].value;
                        var urls = "${basePath}/admin/delArticleComment.do?id=" + value;
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
                    alert("操作成功");
                    location = location;
                }

            }

        }
        $(".tcdPageCode").createPage({
            pageCount: Number('${count}'),
            current: Number('${nowpage}'),
            backFn: function (p) {
                location.href = "${basePath}/admin/ArticleCommentData.do?wantpage=" + p;
            }
        });

        function del(id) {

            var urls = "${basePath}/admin/article/comment/delete.do?id=" + id;
            $.ajax({
                url: urls,
                type: 'post',
                dataType: "text",
                success: function (data, textStatus) {
                    alert("删除成功");
                    location = location;
                }
            });
        }
        function toPage() {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > Number('${count}')) {
                alert(topage);
                alert('${count}');
                alert("超出范围！");
            } else {
                location.href = "${basePath}/admin/ArticleCommentData.do?wantpage=" + topage;
            }
        }

        function exports() {
            location.href = "${basePath}/admin/ArticleCommentDataExport.do?wantpage=" +${nowpage};
        }
    </script>
</body>
</html>
