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
    <link rel="stylesheet" type="text/css" href="${basePath}/page/util/paging.css">
    <!-- Custom Fonts -->
    <link href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>

        function load(wantpage) {
            location.href = "${basePath}/admin/diaryList.do?wantpage=" + wantpage;

        }


    </script>
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
            <a class="navbar-brand" href="${basePath}/page/index.jsp">Admin v2.0</a>
        </div>
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">
                            <input type="text" class="form-control" id="se" placeholder="Search...">
                            <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" onclick="seach()">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                        </div>
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
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/admin/goodExchangeHistory.do"
                                   style="color:white;background-color:#DEDEDE" class="active">兑换列表</a></li>
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
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <h4 class="page-header">兑换列表<span style="padding-left:80%">
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
                        <input type="text" id="search" style="height:24px;padding-right: 24px; "/><button onclick="search()"
                                style="width:24px;height:24px;background-image:url('${basePath}/img/search.png');border:none;vertical-align: top;margin-left: -24px;"></button>
                    </div>
                </div>

                </h4>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>手机号</th>
                            <th>商品名称</th>
                            <th>时间</th>
                            <th>记录</th>
                        </tr>
                        </thead>
                        <tbody>


                        <c:forEach items="${info}" var="info" varStatus="status">
                            <tr>
                                <td><input type="checkbox" id="${info.id }" name="cb"
                                           value="${info.gid},${info.uid},${info.time}"
                                           style="display: none">${info.tel }</td>
                                <td>${info.name }</td>
                                <td>${info.time2 }</td>
                                <c:if test="${info.handled==0 }">
                                    <td><a onclick="chong(${info.tel })">充值</a>&nbsp;&nbsp;未处理</td>
                                </c:if>
                                <c:if test="${info.handled==1 }">
                                    <td>已处理</td>
                                </c:if>
                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>
                    <c:if test="${type==1}">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">

                            <div class="tcdPageCode">
                            </div>
                            <div id="dataTables-example_filter" class="dataTables_filter">
                                总页数:${pagecount}选择跳转页:<input size="8" type="text" value="${nowpage }" id="to_page">
                                <button onclick="toPage();return false;" type="button" class="btn btn-default">确定
                                </button>
                            </div>
                            <br><br>

                        </div>

                    </c:if>
                    <c:if test="${type==2}">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <div class="tcdPageCode">
                            </div>

                            <div id="dataTables-example_filter" class="dataTables_filter">
                                总页数:${pagecount}选择跳转页:<input size="8" type="text" value="${nowpage }" id="to_page">
                                <button onclick="toPage2();return false;" type="button" class="btn btn-default">确定
                                </button>
                            </div>
                            <br><br>

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
    <script>var isSuperAdmin = ${sessionScope.adminUser.superAdmin?'true':'false'};</script>
    <script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>
    <script src="${basePath}/page/util/paging.js"></script>

    <script type="text/javascript">
        $(".tcdPageCode").createPage({
            pageCount: Number('${pagecount}'),
            current: Number('${nowpage}'),
            backFn: function (p) {
                if ('${type}' == 1) {
                    location.href = "${basePath}/admin/goodExchangeHistory.do?wantpage=" + p;
                } else {
                    location.href = "${basePath}/admin/UserGood/search.do?uid=${uid}&wantpage=" + p;

                }
            }
        });
        function chong(tel) {
            var money = prompt("请输入充值金额", "");
            if (money == null) {
                return;
            }
            if (isNaN(money)) {
                alert("请输入数字！");
                return;
            } else {
                var urls = "${basePath}/admin/Chong.do?tel=" + tel + "&money=" + money;
                $.ajax({
                    url: urls,
                    type: 'post',
                    dataType: "json",
                    success: function (data, textStatus) {
                        alert("充值成功");
                        location.href = "${basePath}/admin/diaryList.do";
                    }
                });
            }

        }
        var nowpage = 1;
        var wantpage = 1;
        var pagecount =${pagecount};
        function toPage() {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > pagecount) {
                alert("超出范围！");
            } else {
                location.href = "${basePath}/admin/goodExchangeHistory.do?wantpage=" + topage;
            }

        }
        ;
        function toPage2() {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > pagecount) {
                alert("超出范围！");
            } else {
                location.href = "${basePath}/admin/UserGood/search.do?uid=${uid}&wantpage=" + topage;
            }

        }
        ;
        function tops() {
            alert("已是第一页");
        }
        ;
        function nextx() {
            alert("已是最后一页");
        }
        ;
        function first() {
            load(1);
            nowpage = 1;
        }
        ;
        function last() {
            load(pagecount);
            nowpage = pagecount;
        }
        ;
        function dep(type) {

            if (type == 1) {
                var div2 = document.getElementById("de");
                div2.style.display = "none";
                var div3 = document.getElementById("dep");
                div3.style.display = "Inline";
                <c:forEach  items="${info}"  var="info" varStatus="status">
                var div4 = document.getElementById('${info.id}');
                div4.style.display = "Inline";
                </c:forEach>;
            }

            if (type == 2) {
                if (confirm('确定删除选中信息?') == false) {
                    return;
                }
                var value = "";
                var checks = document.getElementsByName("cb");
                for (var i = 0; i < checks.length; i++) {
                    if (checks[i].checked) {
                        value = checks[i].value;
                        var arr = value.split(",");
                        var urls = "${basePath}/admin/delGoodExchang.do?uid=" + arr[1] + "&gid=" + arr[0] + "&time=" + arr[2];
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
        function search() {
            var seach = $("#search").val();
            if (isNaN(seach)) {
                alert("请输入正确手机号");
            } else {
                var urls = "${basePath}/admin/UserGood/search.do?uid=" + seach;
                location.href = urls;

            }


        }
    </script>
</body>
</html>
