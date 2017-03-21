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

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>

        function load(wantpage) {
            $.ajax({
                url: "${basePath}/admin/diaryList.do",
                type: 'post',
                dataType: "json",
                success: function (data, textStatus) {


                }
            });
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
            <a class="navbar-brand" >Admin v2.0</a>
        </div>
        <div style="float: right;margin-top: 20px;margin-right: 50px"><a href="${basePath}/admin/logoutWeb.do">切换账户</a></div>
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
                    <li><c:if test="${visible==0}">

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
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/admin/diaryList.do" class="active"
                                   style="color:white;background-color:#DEDEDE">日记列表</a></li>
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
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <h4 class="page-header">日记详情</h4>
                <table>
                    <tr style="background-color: #ddd;">
                        <td class=" control-label">编号:</td>
                        <td class="col-sm-2 control-label">${diaryInfo.id }</td>
                    </tr>
                    <tr style="background-color: #eee;">
                        <td class=" control-label">作者:</td>
                        <td class="col-sm-8 control-label">${user.name }</td>
                    </tr>
                    <tr style="background-color: #ddd;">
                        <td class=" control-label">日期:</td>
                        <td class="col-sm-8 control-label">${user.time }</td>
                    </tr>
                    <tr style="background-color: #eee;">
                        <td class=" control-label">内容:</td>
                        <td class="col-sm-8 control-label">${diaryInfo.content }</td>
                    </tr>
                    <tr style="background-color: #ddd;">
                        <td class=" control-label">图片:</td>
                        <td class="col-sm-8 control-label">
                            <c:forEach items="${imglist}" var="imglist" varStatus="status">
                                <img src="${basePath}/${imglist }" width="50px" height="50px">
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">评论与回复:</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div style="width:100%; height:225px; overflow-y:scroll; border:black solid 1px;">
                                <table id="table" cellspacing="0" width="100%" border="1" id="dataTableDetail"
                                       align="center">
                                    <tr>
                                        <td width="20%">用户</td>
                                        <td width="55%">评论/回复内容</td>
                                        <td width="20%">评论/回复时间</td>
                                        <td width="5%">操作</td>
                                    </tr>
                                    <c:forEach items="${dc}" var="list" varStatus="status">
                                        <tr>
                                            <td width="80px"><c:out value='${list.name1}'/>
                                                <c:if test="${list.name2 != null}">
                                                    @<c:out value='${list.name2}'/>
                                                </c:if>
                                                <c:if test="${list.name2 == null}">
                                                    :
                                                </c:if>
                                            </td>
                                            <td width="200px">${list.content}</td>
                                            <td width="200px"><c:out value='${list.time}'/></td>

                                            <td><a href="" onclick="del(${list.id})">删除</a></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>

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
    var nowpage = 1;
    var wantpage = 1;
    var pagecount = 0;
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
            nowpage = topage;
        }

    }
    ;
    function tops() {
        load(nowpage - 1);
    }
    ;
    function nextx() {
        load(nowpage + 1);
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


    function toparticle(id) {
        var urls = "${basePath}/admin/article/top.do?id=" + id;
        $.ajax({
            url: urls,
            type: 'post',
            dataType: "json",
            success: function (data, textStatus) {
                alert("置顶成功");
                load(nowpage);
            }
        });
    }
    function del(id) {
        var urls = "${basePath}/admin/diary/comment/delete.do?id=" + id;
        $.ajax({
            url: urls,
            type: 'post',
            dataType: "json",
            success: function (data, textStatus) {
                alert("删除成功");
                location = location;
            }
        });
    }
    function seach() {
        var seach = $("#se").val();
        var urls = "${basePath}/article/search.do?key=" + seach + "&order=time";
        $.ajax({
            url: urls,
            type: 'post',
            dataType: "text",
            success: function (data, textStatus) {
                alert(data);
            }
        });
    }
</script>
</body>
</html>
