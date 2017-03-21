<!DOCTYPE html>
<!-- saved from url=(0068)http://121.40.137.214:80/1/json.php?mod=admin&act=groupList&1465824931 -->
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
    <script>


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
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/admin/groupInfo.do" class="active"
                                   style="color:white;background-color:#DEDEDE">聊天群组列表</a></li>
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
                            <li><a href="${basePath}/admin/ArticleCommentData.do">回帖数据</a></li>
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
    <div id="page-wrapper" style="min-height: 685px;">
        <div class="container-fluid">
            <div class="row">
                <h4 class="page-header">群组成员</h4>

                <div style="margin-left:85%"><input type="button" value="批量删除" onclick="delMay(1)" id="de"
                                                    style="display:Inline;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                    <input type="button" value="确认删除" onclick="delMay(2)" id="del"
                           style="display:none;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                </div>
                <div class="table-responsive" style="margin-top:1%">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>昵称</th>
                            <th>电话</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${li}" var="group" varStatus="status">
                            <tbody>
                            <tr>
                                <td>
                                    <c:if test="${owner==group.tel}">
                                    <span id="${group.id }" style="display:none">(禁用)</span>${group.id }</td>
                                </c:if>

                                <c:if test="${owner!=group.tel}">
                                    <input style="display:none" type="checkbox" name="test" id="${group.id }"
                                           value="${group.tel }">${group.id }</td>
                                </c:if>

                                <td>${group.nickname }</td>
                                <td>${group.tel }</td>
                                <td>
                                    <c:if test="${owner==group.tel}">
                                    对群主禁用
                                </td>
                                </c:if>
                                <c:if test="${owner!=group.tel}">
                                    <input type="button" onclick="deleteUsers(${group.tel })"
                                           style="background:#abcdef;color:#fff;border:0px;width:70px;height:30px;"
                                           value="移除"></td>
                                </c:if>
                                </td>
                            </tr>

                            </tbody>
                        </c:forEach>

                    </table>
                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                        <ul class="pagination">
                            <li class="paginate_button " tabindex="0"><a
                                    href="${basePath}/admin/group.do?id=${id}">首页</a></li>
                            <c:if test="${nowpage-1>0}">
                                <li class="paginate_button previous " tabindex="0"><a
                                        href="${basePath}/admin/group.do?id=${id }&wantpage=${nowpage-1}">上一页</a></li>

                            </c:if>
                            <c:if test="${nowpage-1<=0}">
                                <li class="paginate_button previous " tabindex="0"><a href="" onclick="tops()">上一页</a>
                                </li>

                            </c:if>
                            <li>
                                <%-- <c:forEach  begin="1" end="${count}" varStatus="status">
                                <a href="${basePath}/admin/group.do?id=${id }&wantpage=${status.index }">${status.index }</a>
                                </c:forEach> --%>
                                <a href="${basePath}/admin/group.do?id=${id }&wantpage=${nowpage }">${nowpage}</a>

                            </li>
                            <c:if test="${nowpage+1>count}">
                                <li class="paginate_button next " tabindex="0"><a href="" onclick="nextx()">下一页</a></li>
                            </c:if>
                            <c:if test="${nowpage+1<=count}">
                                <li class="paginate_button next " tabindex="0"><a
                                        href="${basePath}/admin/group.do?id=${id }&wantpage=${nowpage+1}">下一页</a></li>
                            </c:if>
                            <li class="paginate_button " tabindex="0"><a
                                    href="${basePath}/admin/group.do?id=${id }&wantpage=${count}">尾页</a></li>
                        </ul>
                        <div id="dataTables-example_filter" class="dataTables_filter">
                            总页数:${count}选择跳转页:<input size="8" type="text" value="${nowpage}" id="to_page">
                            <button onclick="toPage()" type="button" class="btn btn-default">确定</button>
                        </div>
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

    <script type="text/javascript">
        function delMay(type) {
            if (type == 1) {
                var div1 = document.getElementById('de');
                div1.style.display = "none";

                var div2 = document.getElementById('del');
                div2.style.display = "block";


                <c:forEach  items="${li}"  var="group" varStatus="status">
                var div3 = document.getElementById('${group.id}');
                div3.style.display = "inline";
                </c:forEach>
            } else if (type == 2) {
                obj = document.getElementsByName("test");
                check_val = [];
                for (k in obj) {
                    if (obj[k].checked)
                        check_val.push(obj[k].value);
                }
                if (confirm("是否将选中成员移除出群组?")) {
                    var gid =${id};
                    $
                            .ajax({
                                url: "${basePath}/admin/deleteGroupUser.do?tels=" + check_val + "&gid=" + gid,
                                type: 'post',
                                dataType: "text",
                                success: function (data, textStatus) {
                                    if (data == "success") {
                                        alert("删除成功");
                                        location = location;
                                    }
                                }
                            });
                } else {

                }
            }
        }

        function toPage() {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > '${count}') {
                alert("超出范围！");
            } else {
                location.href = "${basePath}/admin/group.do?id=${id}&wantpage=" + topage;
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
        function deleteUsers(tel) {

            if (confirm("是否将该成员移除出群组?")) {
                var gid =${id};
                $
                        .ajax({
                            url: "${basePath}/admin/deleteGroupUser.do?tel=" + tel + "&gid=" + gid,
                            type: 'post',
                            dataType: "text",
                            success: function (data, textStatus) {
                                if (data == "success") {
                                    alert("删除成功");
                                    location = location;
                                }
                            }
                        });
            } else {

            }
        }
    </script>


</div>
</body>
</html>