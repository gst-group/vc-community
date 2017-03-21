<!DOCTYPE html>
<!-- saved from url=(0068)http://121.40.137.214:80/1/json.php?mod=admin&act=adminList&1465824400 -->
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>
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
    <link
            href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"
            rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link
            href="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.css"
            rel="stylesheet">

    <!-- Custom CSS -->
    <link
            href="/lk-vc/css/sb-admin-2.css"
            rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">
    <link rel="stylesheet" type="text/css" href="${basePath}/page/util/paging.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        var wantpage;
        var nowpage;
        var pagecount;
        var obj;
        window.onload = function () {
            var admin = '<%=session.getAttribute("adminUser")%>';
            if (admin == "null") {
                location.href = "${basePath}/page/Login.jsp";
            }

            nowpage = 1;
            load(1);
        }

        function load(wantpage) {

            nowpage = wantpage;
            $
                    .ajax({
                        url: "${basePath}/admin/adminList.do?wantpage=" + wantpage,
                        type: 'post',
                        dataType: "json",
                        success: function (data, textStatus) {
                            var html = "<table class='table table-striped table-bordered table-hover'><thead><tr>"
                                    + "<th>编号</th>"
                                    + "<th>账号</th>"
                                    + "<th>昵称</th>"
                                    + "<th>密码</th>" + "<th>操作</th></tr></thead>";
                            //获取管理员数据
                            pagecount = data.pageCount;
                            obj = data.extraList;
                            var nowpage = data.nowpage;
                            var ym = "<a href='#' onclick='ym(" + nowpage + ")'>" + nowpage + "</a>";

                            $("#ym").html(ym);

                            for (var i = 0; i < obj.length; i++) {
                                var id = obj[i].id;
                                var name = obj[i].name;
                                var nickname = obj[i].nickname;

                                var pwd = obj[i].password;
                                var extra = obj[i].extra;
                                //0可修改1不可修改2申请可修改

                              /*  if(extra==0){
                                    var scoreYesOrNo="&nbsp;&nbsp;<a   onclick='y(\""
                                            + id + "\")'>禁止积分修改</a>" ;
                                }else if(extra==1){
                                    var scoreYesOrNo="";
                                }else if(extra==2){
                                    var scoreYesOrNo="&nbsp;&nbsp;<a   onclick='y(\""
                                            + id + "\")'>允许积分修改</a>" ;
                                };*/
                                html = html
                                        + "<tbody><td>"
                                        + id
                                        + "</td>"
                                        + "<td>"
                                        + name
                                        + "</td>"
                                        + "<td>"
                                        + nickname
                                        + "</td>"
                                        + "<td>"
                                        + pwd
                                        + "</td>"
                                        + "<td><a href='#' onclick='edit("
                                        + i
                                        + ")'>修改</a>&nbsp;&nbsp;<a  onclick='del(\""
                                        + id + "\")'>删除</a>" +
                                        "&nbsp;&nbsp;<a  onclick='exports(\""
                                        + id + "\")'>导出</a>" +
/*
                                                scoreYesOrNo+
*/
                                        "</td></tr></tbody>";
                            }

                            var a = parseInt(pagecount);
                            $("#adminList").html(html);
                            $("#pageCount").html(pagecount);
                        },
                        error: function (data) {
                            location.href = "${basePaeh}/lk-vc/page/Login.jsp";
                        }
                    });
        }
        ;
    </script>
</head>
<body style="background: #ffffff; font-family: &amp; #39;">

<div id="wrapper">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation"
         style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
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
                        </div> <!-- /input-group -->
                    </li>

                    <li>
                        <c:if test="${visible==0}">

                        <a href="${basePath}/page/index.jsp"><i class="fa fa-dashboard fa-fw"></i> 首页</a>
                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        发帖审核<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/Sensitive.do">敏感词检测</a></li>
                            <li><a href="${basePath}/admin/getSystem.do?id=1">关键字管理</a></li>
                            <li><a href="${basePath}/admin/getSystemdmin.do?id=2">条件搜索</a></li>
                        </ul>
                    </li>
                    </c:if>



                    <li><a href=""><i class="fa fa-empire fa-fw"></i> 帐号管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li class="hideIfNotSuper"><a href="${basePath}/page/adminList.jsp" style="color:white;background-color:#DEDEDE"
                                   class="active">管理员列表</a></li>
                            <li class="hideIfNotSuper"><a href="${basePath}/page/adminAdd.jsp">添加管理员</a></li>
<%--
                            <li><a href="${basePath}/page/adminPass.jsp">修改密码</a></li>
--%>
                        </ul>
                    </li>
                    <c:if test="${visible==0}">

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
                    </c:if>

                </ul>
            </div>
        </div>
    </nav>
    <!-- Page Content -->
    <div id="page-wrapper" style="min-height: 685px;">
        <div class="container-fluid">
            <div class="row">
                <h4 class="page-header">管理员列表</h4>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover"
                           id="adminList">

                    </table>
                    <div class="dataTables_paginate paging_simple_numbers"
                         id="dataTables-example_paginate">
                        <div class="tcdPageCode">
                        </div>
                        <div id="dataTables-example_filter" class="dataTables_filter">
                            总页数:<span id="pageCount"></span>,选择跳转页:<input size="8"
                                                                          type="text" value="1" id="to_page">
                            <button onclick="toPage()" type="button" class="btn btn-default">确定</button>
                        </div>
                        <br>
                        <br>
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
    <script>var isSuperAdmin = ${sessionScope.adminUser.superAdmin?'true':'false'};</script>
    <script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>

    <script src="${basePath}/page/util/paging.js"></script>

    <script type="text/javascript">
        load(1);
        $(".tcdPageCode").createPage({
            pageCount: Number('<%=session.getAttribute("nowpage")%>'),
            current: Number('<%=session.getAttribute("pagecount")%>'),
            backFn: function (p) {
                load(p);
            }
        });

        function toPage() {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > pagecount) {
                alert("超出范围！");
            } else {
                load(topage);
            }

        }
        ;
        function tops() {
            if (nowpage == 1) {
                alert("已是第一页");
                return;
            }
            load(nowpage - 1);
        }
        ;
        function y(id){
            $.ajax({
                url: "${basePath}/admin/ScoreRole.do?id=" + id,
                type: 'post',
                dataType: "text",
                success: function (data) {
                    if(data=="success"){
                        alert("操作成功");

                        load(nowpage);
                    }else{
                        alert("网络错误");
                    }
                }
                });
            load(nowpage);
        }
        function nextx() {
            if (pagecount == nowpage) {
                alert("已是最后一页");
                return;
            }
            load(nowpage + 1);
        }
        ;
        function first() {
            load(1);
        }
        ;
        function last() {
            load(pagecount);
        }
        ;

        function edit(i) {
            var id = obj[i].id;
            var name = obj[i].name;
            var pwd = obj[i].password;
            var nickname = obj[i].nickname;
            window.location.href = "${basePath}/page/ChangAdmin.jsp?-"
                    + id + "-" + name + "-" + pwd+"-"+nickname;
        }
        ;
        function del(id) {
            var ro = '${sessionScope.adminUser.role }'
            if (ro != 3) {
                alert("权限不足");
                return;
            }
            if (confirm("确定删除该用户及其相关信息？")) {
                $.ajax({
                    url: "${basePath}/admin/admin/delete.do?id=" + id,
                    type: 'post',
                    dataType: "text",
                    success: function (data) {

                        if (data == "NO") {
                            alert("无法删除当前登录用户");
                        } else if (data == "success") {
                            alert("删除成功");
                            location = location;
                        } else if (data = "role") {
                            alert("网络错误");
                        }
                    },
                    error: function (data) {
                        alert("网络错误！");
                    }
                });
            } else return false;
        }
        ;
        function ym(page) {
            load(page);
        }

function exports(id){
    location.href="${basePath}/admin/exportAdmin.do?id=" + id;
}
    </script>


</div>
</body>
</html>