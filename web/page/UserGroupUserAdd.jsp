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
    <script>
        $(function () {
            $("#btnShow").click(function () {
                /*  var str="我是弹出对话框";
                 $(".form").html(str); */
                $("#BgDiv").css({display: "block", height: $(document).height()});
                var yscroll = document.documentElement.scrollTop;
                $("#DialogDiv").css("top", "100px");
                $("#DialogDiv").css("display", "block");
                document.documentElement.scrollTop = 0;
            });
            $("#btnClose").click(function () {
                $("#BgDiv").css("display", "none");
                $("#DialogDiv").css("display", "none");
            });
        });
    </script>
    <style type="text/css">
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
            font-size: 15px;
            color: #000000
        }

        #DialogDiv .form {
            padding: 10px;
        }

        #filter {
            text-align: left;
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


                    <li><a href=""><i class="fa fa-empire fa-fw"></i> 帐号管理<span class="fa arrow"></span></a>
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

    <!-- Page Content -->
    <div id="page-wrapper" style="min-height: 685px;">
        <div class="container-fluid">
            <div class="row-fluid">
                <h3>修改群组</h3>
                <div class="span4">
                    <div style="position:absolute;top:15%;left:22.5%"><h5>群组名称：${grpName}</h5></div>

                    <!--               <div style="position:absolute;top:20%;left:22.5%"><h5>群组用户列表</h5></div>
                     -->
                    <div id="DialogDiv" style="display:none; width:400px;height:400px">
                        <h2><a href="#" id="btnClose">关闭</a></h2>
                        <div>

                            <form name="filter">
                                <center>
                                    <table>
                                        <tr>
                                            <td height="30px">性别:</td>
                                            <td><input type="radio" value="0" name="sex">男<input type="radio" value="1"
                                                                                                 name="sex">女 <input
                                                    type="radio" name="sex" value="-1">保密 <p></td>
                                        </tr>
                                        <tr>
                                            <td height="30px">年龄:</td>
                                            <td><input type="text" name="ageS" style="width:50px"
                                                       value="${params.ageS}"/>-
                                                <input type="text" name="ageE" style="width:50px"
                                                       value="${params.ageE}"></td>
                                        </tr>
                                        <tr>
                                            <td height="30px">城市:</td>
                                            <td><input type="text" name="city" style="width:115px"
                                                       value="${params.city}"/></td>
                                        </tr>
                                        <tr>
                                            <td height="30px">学历:</td>
                                            <td><input type="text" name="degree" style="width:115px"
                                                       value="${params.degree}"/></td>
                                        </tr>
                                        <tr>
                                            <td height="30px">婚否:</td>
                                            <td><input type="radio" value="已婚" name="marriage">已婚
                                                <input type="radio" value="未婚" name="marriage">未婚
                                                <input type="radio" name="marriage" value="保密">保密
                                                <input type="hidden" name="grp" value="${grpName}"></td>
                                        </tr>
                                        <tr style="display:none">
                                            <td height="30px">搜索关键字：</td>
                                            <td><input type="text" style="width:115px" name="key"
                                                       value="${params.rawKey}"></td>
                                        </tr>
                                        <c:forEach items="${extendsInfo}" var="info">
                                            <c:if test="${info.name!='test'}">
                                                <tr>
                                                    <td>
                                                        <c:out value="${info.name}"/>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${info.type == 0}">
                                                                <input name="<c:out value="${info.name}"/>S" type="text"
                                                                       class="extendsInfoControl"
                                                                       style="width:50px"/>
                                                                <input name="<c:out value="${info.name}"/>E" type="text"
                                                                       class="extendsInfoControl"
                                                                       style="width:50px"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input name="<c:out value="${info.name}"/>" type="text"
                                                                       class="extendsInfoControl"
                                                                       style="width:103px"/>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:if>

                                        </c:forEach>
                                        <tr>
                                            <td colspan="2" height="30px">
                                                <center>
                                                    <button type="submit" class="btn"
                                                            style="margin-top:10px;background:#ed5f00;color:white;border:0px;width:100px;height:35px;">
                                                        筛选
                                                    </button>
                                                </center>
                                            </td>
                                        </tr>
                                    </table>
                                </center>
                                <%--
                                                性别:<input type="radio" value="0" name="sex">男<input type="radio" value="1" name="sex">女<!-- <input
                                                    type="radio" name="sex" value=" ">保密 --><p>
                                                年龄:<input type="text" name="ageS" style="width:50px" value="${params.get("ageS")}"/>-
                                                <input type="text" name="ageE" style="width:50px" value="${params.get("ageE")}"><br/>
                                                城市:<input type="text" name="city" style="width:100px" value="${params.get("city")}"/><br/>
                                                学历:<input type="text" name="degree" style="width:100px" value="${params.get("degree")}"/><br/>
                                                婚姻状况:
                                                <input type="radio" value="已婚" name="marriage">已婚
                                                <input type="radio" value="未婚" name="marriage">未婚
                                                <input type="radio" name="marriage" value="保密">保密
                                                <input type="hidden" name="grp" value="${grpName}"><br/>
                                                搜索关键字：<input type="text" name="key" value="${params.get("rawKey")}"><br/>
                                                <button type="submit" class="btn" style="background:#ed5f00;color:white;border:0px;width:100px;height:35px;">筛选</button>
                                                --%>  </form>

                        </div>

                    </div>

                    <button type="button" id="btnShow" class="btn"
                            style="background:#ed5f00;color:white;border:0px;width:100px;height:35px;float:right">属性新增

                </div>
                <!-- /.row -->
                <div style="width:1000px;height:400px;overflow-x:auto;margin-top:100px">
                    <table class="table span8">
                        <thead>
                        <tr>
                            <td style="border:1px #D1D1D1 solid;">姓名</td>
                            <td style="border:1px #D1D1D1 solid;">手机号</td>
                            <td style="border:1px #D1D1D1 solid;">昵称</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td style="border:1px #D1D1D1 solid;"><input type="checkbox"
                                                                             value="${user.id}"><span>${user.realname}</span>
                                </td>
                                <td style="border:1px #D1D1D1 solid;">${user.name}</td>
                                <td style="border:1px #D1D1D1 solid;">${user.nickname}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <button type="button" class="btn" onclick="return addUserToGroup()"
                    style="background:#ed5f00;color:white;border:0px;width:100px;height:35px;">提交
            </button>

        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div>

<!-- Bootstrap Core JavaScript -->
<script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script>var isSuperAdmin = ${sessionScope.adminUser.superAdmin?'true':'false'};</script>
<script src="/lk-vc/js/sb-admin-2.js"></script>

<!-- Custom Theme JavaScript -->
<script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>

<script src="http://121.40.137.214:80/statics//uploadify/jquery.uploadify.js" type="text/javascript"></script>
<script type="text/javascript">
    function addUserToGroup() {
        var ids = '';
        var checkedBox = [];
        $(".table :checkbox").each(function (idx, obj) {
            obj = $(obj);
            if (obj.prop('checked')) {
                ids += "," + obj.val();
                checkedBox.push(obj);
            }
        });
        ids = ids.substr(1);
        $.post(BASE_DIR + "admin/user/group/add.do", {grp: '${grpName}', ids: ids}, function (data) {
            if (data.code == 0) {
                location.reload();
            } else {
                alert(data.message);
            }
        })
    }

</script>


</body>
</html>