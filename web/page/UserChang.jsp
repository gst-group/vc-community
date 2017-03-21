<!DOCTYPE html>
<!-- saved from url=(0070)http://121.40.137.214:80/1/json.php?mod=admin&act=newUserPage&1465824954 -->
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="base.jsp" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <link href="http://121.40.137.214:80/statics/uploadify/uploadify.css" rel="stylesheet" type="text/css">
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
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/admin/userList.do" class="active"
                                   style="color:white;background-color:#DEDEDE">用户列表</a></li>
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
                <h4 class="page-header">修改用户</h4>
                <form action="${basePath}/lk-vc/admin/user/new.do" class="form-horizontal" enctype="multipart/from-data"
                      method="post" style="width:90%" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户手机号：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" name="name" value="${tel}"
                                                     id="name"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">真实姓名：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" name="name" value="${name}"
                                                     id="reaname"></div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="padding-top:7px;">用户密码：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" name="password" value="${pwd}"
                                                     id="score"></div>
                    </div>
                   
                    <input type="hidden" class="form-control" name="password" value="${group}" id="group">
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="padding-top:7px;">昵称：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" name="nickname"
                                                     value="${nickname}" id="nickname"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="padding-top:7px;">邮箱：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" name="email" value="${email }"
                                                     id="email"></div>
                    </div>
                    <div class="form-group">

                        <label class="col-sm-2 control-label" style="padding-top:7px;">性别：</label>
                        <div class="col-sm-5"><select class="form-control" name="sex" id="sex">

                            <c:if test="${sex==0 }">
                                <option value="0">男</option>
                                <option value="1">女</option>
                                <option value="null">保密</option>
                            </c:if>
                            <c:if test="${sex==1 }">
                                <option value="1">女</option>
                                <option value="0">男</option>
                                <option value="null">保密</option>
                            </c:if>
                            <c:if test="${sex!=0&&sex!=1 }">
                                <option value="null">保密</option>
                                <option value="0">男</option>
                                <option value="1">女</option>
                            </c:if>

                        </select></div>
                    </div>

                    <!-- 附属属性 -->
                    <c:forEach items="${clist}" var="cl" varStatus="status">
                        <c:if test="${cl!='test' }">
                            <div class="form-group">
                                <label class="col-sm-2 control-label" style="padding-top:7px;">${cl }：</label>
                                <div class="col-sm-5">
                                    <c:set var='myname' value="${cl}" scope="page"/>
                                    <c:forEach items="${all}" var="all" varStatus="ss">
                                        <input type="text" class="form-control" value="${all[myname] }" id="${status.count+10}">
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <input type="hidden" value="0" id="test">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" onclick="add()" class="btn btn-default" id="save_jobs"
                                    style="background:#ed5f00;color:white;border:0px;font-size:15px;height:30px;width:100px">
                                保存
                            </button>
                        </div>
                    </div>

                </form>
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

<!-- Custom Theme JavaScript -->
<script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>


<script type="text/javascript">
    function add() {
        /* 获取附属属性的值 */
        var array = new Array();
        <c:forEach items="${clist}" var="a">
        array.push('${a}');
        </c:forEach>;
        var array2 = new Array();
        var j=10;
        for (var i = 0; i < array.length; i++) {
            j++;
            array2.push($("#"+j).val());

        }
        var reaname=$("#reaname").val();
        var nickname = $("#nickname").val();
        var score = $("#score").val();
        var email = $("#email").val();
        var sex = $("#sex").val();
        if (sex == "null") {
            sex = "3";
        }
        var name = $("#name").val();
        var group = $("#group").val();
        $.ajax({
            url: "${basePath}/admin/UserChang.do?id=${id}&group=" + group+"&realname="+reaname + "&nickname=" + nickname + "&password=" + score + "&email=" + email + "&sex=" + sex + "&name=" + name + "&array=" + array + "&array2=" + array2,
            type: 'post',
            dataType: "text",
            success: function (data, textStatus) {
                if (data == "success") {
                    alert("修改成功");
                    location.href = "${basePath}/admin/userList.do";
                } else {
                    alert(data.message);
                }
            }
        });
    }
</script>


</body>
</html>