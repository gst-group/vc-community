<!DOCTYPE html>
<!-- saved from url=(0073)http://121.40.137.214:80/1/json.php?mod=admin&act=userlist&del=0&1465824946 -->
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
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


    <link rel="stylesheet" type="text/css" href="${basePath}/lk-vc/page/util/paging.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>


    <![endif]-->
    <script src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <style type="text/css">

        body, h2 {
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
            background-color: #8FA4F5;
            position: relative;
            padding-left: 10px;
            line-height: 25px;
        }

        #DialogDiv h2 a {
            position: absolute;
            right: 5px;
            font-size: 12px;
            color: #000000
        }

        #DialogDiv .form {
            padding: 10px;
        }

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
            width: 100px;
            position: relative;
            display: inline-block;
            background: #ed5f00;
            color: white;
            border: 0px;
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
            background: #ed5f00;
            color: white;
            border: 0px;
            text-decoration: none;
        }

        #tab td {
            font-size: 100px;
        }
    </style>
    <script type="text/javascript">
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
        function exportUser() {

            location.href = "${basePath}/lk-vc/admin/export.do";
            ;
        }
        var ids = "";
        function getid(id) {
            ids = id;

        }
        function checkedValues() {
            var arr = new Array();
            var checkbox = document.getElementsByName('cb');
            for (var i = 0; i < checkbox.length; i++) {
                if (checkbox[i].checked == true) {
                    arr.push(checkbox[i].value);

                }
            }
            $.ajax({
                url: "${basePath}/lk-vc/admin/UserQX.do?array=" + arr + "&uid=" + ids,
                type: 'post',
                dataType: "text",
                success: function (data, textStatus) {
                    if (data == "success") {
                        alert("添加成功");
                    }

                }
            });
        }
        window.onload = function () {
            var msg = '${msg1}';
            if (msg.length > 0) {
                if (msg == "success") {
                    alert("添加成功");
                } else {
                    alert(msg);
                }

            }
            var a = '${inserUsers}';
            if (a == "success") {
                alert("导入成功");
            } else if (a == "error") {
                var ms = "";
                <c:forEach items="${messageList}" var="msg">
                ms = ms + "导入失败:"+'${msg}\n';
                </c:forEach>;
                alert(ms);
            }
        };

    </script>

</head>
<body style="background:#ffffff;font-family:&#39;微软雅黑&#39;">
<div id="BgDiv"></div>
<div id="DialogDiv" style="display:none; width:400px;height:400px">
    <h2><a href="#" id="btnClose">关闭</a></h2>
    <div class="form">
        <div>
            <table style="font-size:15px">
                <c:forEach items="${grps}" var="list1" varStatus="status">
                    <tr>
                        <td width="150px">&nbsp;&nbsp;<input type="checkbox" name="cb" value="${list1 }">${list1 }</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div style="padding-top:100px">
        <center>
            <button style="width:100px;" onclick="checkedValues()">确定</button>
        </center>
    </div>
</div>


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
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/lk-vc/admin/userList.do" class="active"
                                   style="color:white;background-color:#DEDEDE">用户列表</a></li>
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
    <!-- Page Content -->
    <div id="page-wrapper" style="min-height: 685px;">
        <div class="container-fluid">
            <div class="row">


                <h4 class="page-header">用户列表<span style="padding-left:800px">
       <%--  <span>当前分组:
         <c:forEach  items="${group}"  var="groups" varStatus="status">
           <a href="">${groups.groupname }</a>&nbsp;&nbsp;
         </c:forEach>
      </span> --%>
                    <!-- <input type="button" value="新增权限" onclick="add()" style="background:#ed5f00;color:white;border:0px;height:30px;font-size:15px" ></span></span> -->
                </h4>
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
                                style="width:24px;height:24px;background-image:url('${basePath}/lk-vc/img/search.png');border:none;vertical-align: top;margin-left: -24px;"></button>
                    </div>
                </div>


                <div class="table-responsive" style="margin-top:2%">
                    <c:if test="${type!=1 }">
                        <table class="table table-striped table-bordered table-hover">
                            <tr>
                                <td>
                                    <center>编号</center>
                                </td>
                                <td>
                                    <center>姓名</center>
                                </td>
                                <td>
                                    <center>手机号</center>
                                </td>
                                <td>
                                    <center>昵称</center>
                                </td>
                                    <%-- <td><center>分组</center></td> --%>
                                <td>
                                    <center>用户积分</center>
                                </td>
                                <c:forEach items="${clist}" var="user" varStatus="status">
                                    <td>
                                        <center>${user }</center>
                                    </td>
                                </c:forEach>
                                <td>
                                    <center>操作</center>
                                </td>
                            </tr>
                            <tbody>
                            <tbody>
                            <c:forEach items="${all}" var="al" varStatus="status">
                                <tr>
                                    <c:forEach items="${idlist}" var="id" begin="${status.index }"
                                               end="${status.index }" varStatus="s">
                                        <td><input type="checkbox" value="${al.id }" name="cb" id="${al.id }"
                                                   style="display:none">
                                                ${id}
                                        </td>
                                    </c:forEach>
                                    <td>
                                        <center><c:out value='${al.realname }'/></center>
                                    </td>
                                    <td>
                                        <center>
                                        <c:out value='${al.name }'/>
                                        </center>
                                    </td>
                                    <td>
                                        <center><c:out value='${al.nickname }'/></center>
                                    </td>
                                        <%--                                    <td><center><c:out value='${al.group }'/></center></td>
                                         --%>
                                    <td>
                                        <center><c:out value='${al.score }'/></center>
                                    </td>
                                    <c:forEach items="${clist}" var="user" varStatus="s">
                                        <c:set var='myname' value="${user}" scope="page"/>
                                        <td>
                                            <center>
                                                <c:out value='${al[myname] }'/>
                                            </center>
                                        </td>
                                    </c:forEach>


                                    <td>
                                        <c:if test="${al.allow_article==1}">
                                            <a href="" onclick="huati(${al.id},1)">话题禁止</a>
                                        </c:if>
                                        <c:if test="${al.allow_article==0}">
                                            <a href="" onclick="huati(${al.id},0)">取消话题禁止</a>
                                        </c:if>
                                        &nbsp;&nbsp;
                                        <a href="${basePath}/lk-vc/admin/UserChangView.do?id=${al.id}">修改</a>&nbsp;&nbsp;<a
                                            onclick="del( ${al.id })">删除</a>&nbsp;&nbsp;<%-- <a onclick="getid(${al.id})" id="btnShow">增加权限</a> --%>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>

                            </tbody>
                        </table>

                    </c:if>
                    <c:choose>
                        <c:when test="${type==1 }">


                            <table class="table table-striped table-bordered table-hover">
                                <tr>
                                    <td>
                                        <center>编号</center>
                                    </td>
                                    <td>
                                        <center>姓名</center>
                                    </td>
                                    <td>
                                        <center>手机号</center>
                                    </td>
                                    <td>
                                        <center>昵称</center>
                                    </td>
                                        <%-- <td><center>分组</center></td> --%>
                                    <td>
                                        <center>用户积分</center>
                                    </td>
                                    <c:forEach items="${userFY[0].columnInfoViews}" var="user" varStatus="status">
                                        <td>
                                            <center>
                                                    ${user.columnName}
                                            </center>
                                        </td>
                                    </c:forEach>
                                    <td>
                                        <center>操作</center>
                                    </td>
                                </tr>
                                <tbody>
                                <c:forEach items="${userFY}" var="userInfo" varStatus="status">
                                    <tr>
                                        <td><input type="checkbox" value="${userInfo.id }" name="cb"
                                                   id="${userInfo.id }" style="display:none">${userInfo.id }</td>
                                        <td>${userInfo.realname }</td>
                                        <td>${userInfo.name }</td>
                                        <td>${userInfo.nickname }</td>
                                        <td>${userInfo.score }</td>
                                        <c:forEach items="${userInfo.columnInfoViews}" var="s" varStatus="status">
                                            <td>${s.cvalue }</td>
                                        </c:forEach>
                                        <td>
                                            <c:if test="${userInfo.allowArticle==true}">
                                                <a href="" onclick="huati(${userInfo.id},1)">话题禁止</a>
                                            </c:if>
                                            <c:if test="${userInfo.allowArticle==false}">
                                                <a href="" onclick="huati(${userInfo.id},0)">取消话题禁止</a>
                                            </c:if>
                                            &nbsp;&nbsp;
                                            <a href="${basePath}/lk-vc/admin/UserChangView.do?id=${userInfo.id}">修改</a>&nbsp;&nbsp;<a
                                                onclick="del( ${userInfo.id })">删除</a>&nbsp;&nbsp;<%-- <a onclick="getid(${al.id})" id="btnShow">增加权限</a> --%>
                                        </td>
                                    </tr>
                                </c:forEach>

                                </tbody>

                                </tbody>
                            </table>


                        </c:when>
                    </c:choose>

用户访问链接:http://vc-community.insight365.ai/lk-vc-h5/page/wxIndex.html?from=${UserURL}

                    <div class="tcdPageCode">
                    </div>
                    <div id="dataTables-example_filter" class="dataTables_filter">
                        总页数:${pagecount}选择跳转页:<input size="8" type="text" value="${nowpage }" id="to_page">
                        <button onclick="toPage()" type="button" class="btn btn-default">确定</button>
                    </div>


                    <div style="float:right;margin-right:200px">
                        <div style="float:left" id="ra">
                            <input type="radio" checked="checked" name="rad" value="1">当前页
                            <input type="radio" name="rad" value="2">所有用户
                            <button type="button" onclick="exports()" class="btn btn-default"
                                    style="background:#ed5f00;color:white;border:0px;font-size:15px;height:30px;width:70px">
                                导&nbsp;&nbsp;出
                            </button>
                        </div>
                        <!-- 导入扩展 -->

                        <div style="float:left;margin-left:30px">

                            <div style="float:right;margin-left:30%;">
                                <form action="${basePath}/lk-vc/admin/exportUser.do" method="post"
                                      ENCTYPE="multipart/form-data">
                                    <a href="javascript:;" class="file">
                                        <center>导入属性</center>
                                        <input type="file" name="file">
                                    </a>
                                    <input type="submit" value="导入属性" id="submitExport" style="display:none">
                                </form>
                                <%--   <form action="${basePath}/lk-vc/admin/exportlu.do" method="post" ENCTYPE="multipart/form-data">
                                   <div style="float:left;padding-left:10px"><button type="submit" class="btn btn-default" style="background:#ed5f00;color:white;border:0px;font-size:15px;height:30px;width:70px">导入</button></div>
                                  &nbsp;&nbsp;&nbsp;&nbsp;
                                  <div style="float:left"><input  type="File" name="file"></div>
                                  </form> --%>
                            </div>


                            <div>
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

        <!-- page -->
        <script src="${basePath}/lk-vc/page/util/paging.js"></script>
        <script>
            function  changUrl(item) {
                if(item==1) {
                    document.getElementById("UserUrl").style.display = "inline";
                    document.getElementById("UserUrl2").style.display = "inline";
                    document.getElementById("UserUrl1").style.display = "none";

                } else{
                    document.getElementById("UserUrl").style.display="none";
                    document.getElementById("UserUrl2").style.display = "none";
                    document.getElementById("UserUrl1").style.display = "inline";

                }
            }


            $(".tcdPageCode").createPage({
                pageCount: Number('${pagecount}'),
                current: Number('${nowpage}'),
                backFn: function (p) {
                    if ('${type}' == 1) {
                        location.href = "${basePath}/lk-vc/admin/UserSeach.do?key=${key}&wantpage=" + p;
                    } else {
                        location.href = "${basePath}/lk-vc/admin/userList.do?wantpage=" + p;

                    }
                }
            });
        </script>

        <script type="text/javascript">
            function seach() {
                var a = $("#se").val();
                if (a.length == 0) {
                    return;
                }

                location.href = "${basePath}/lk-vc/admin/UserSeach.do?key=" + a;
            }

            function exports() {
                var radio = document.getElementsByName("rad");
                for (var i = 0; i < radio.length; i++) {
                    if (radio[i].checked == true) {
                        var value = radio[i].value;
                        break;
                    }
                }
                location.href = "${basePath}/lk-vc/admin/export.do?page=${nowpage}&type=" + value;
            }

            function quanxian() {
                location.href = "${basePath}/lk-vc/admin/groupQuanxian.do";
            }
            function jifen() {
                location.href = "${basePath}/lk-vc/admin/ScoreUser.do";
            }


            function huati(id, a) {
                if (a == 1) {
                    var urls = "${basePath}/lk-vc/admin/user/allowArticle.do?id=" + id + "&enable=0"
                    $.ajax({
                        url: urls,
                        type: 'post',
                        dataType: "json",
                        success: function (data, textStatus) {
                            alert("成功禁止");
                            location = "${basePath}/lk-vc/page/";
                        }
                    });
                } else {
                    var urls = "${basePath}/lk-vc/admin/user/allowArticle.do?id=" + id + "&enable=1"
                    $.ajax({
                        url: urls,
                        type: 'post',
                        dataType: "json",
                        success: function (data, textStatus) {
                            alert("取消成功");
                            location.href="${basePath}/lk-vc/admin/userList.do?wantpage=${nowpage}";
                        }
                    });
                }

            }

            function del(id) {
                if (confirm('确定删除该用户?') == false) {
                    return;
                }
                var urls = "${basePath}/lk-vc/admin/user/delete.do?id=" + id;
                $.ajax({
                    url: urls,
                    type: 'post',
                    dataType: "json",
                    success: function (data, textStatus) {
                        alert("删除成功");
                        location.href = "${basePath}/lk-vc/admin/userList.do?wantpage=${nowpage}";
                    }
                });
            }
            function toPage() {
                var topage = $("#to_page").val();
                if (isNaN(topage)) {
                    alert("请输入数字！");
                } else if (topage <= 0) {
                    alert("输入不合法！");
                } else if (topage > '${pagecount}') {
                    alert("超出范围！");
                } else {
                    if ('${type}' == 1) {
                        location.href = "${basePath}/lk-vc/admin/UserSeach.do?key=${key}&wantpage=" + p;
                    } else {
                        location.href = "${basePath}/lk-vc/admin/userList.do?wantpage=" + $('#to_page').val();
                    }
                }
            }
            ;


            function add() {
                location.href = "${basePath}/lk-vc/admin/userGroup.do";
            }

            $(".file").on("change", "input[type='file']", function () {
                var filePath = $(this).val();
                if (filePath.indexOf("xls") != -1 || filePath.indexOf("xlsx") != -1) {
                    $(".fileerrorTip").html("").hide();
                    var arr = filePath.split('\\');
                    var fileName = arr[arr.length - 1];
                    $(".showFileName").html(fileName).show();
                    $("#submitExport").click();
                } else {
                    $(".showFileName").html("");
                    $(".fileerrorTip").html("您上传文件类型有误！").show();
                    $(this).val("");
                    return false;
                }
            })


            function dep(type) {

                if (type == 1) {
                    var div2 = document.getElementById("de");
                    div2.style.display = "none";
                    var div3 = document.getElementById("dep");
                    div3.style.display = "Inline";

                    if ('${type}' == 1) {
                        <c:forEach  items="${userFY}"  var="userInfo" varStatus="status">
                        var div4 = document.getElementById('${userInfo.id}');
                        div4.style.display = "Inline";
                        </c:forEach>;
                    } else {
                        <c:forEach  items="${all}"  var="al" varStatus="status">
                        var div4 = document.getElementById('${al.id}');
                        div4.style.display = "Inline";
                        </c:forEach>;
                    }

                }

                if (type == 2) {
                    if (confirm('确定删除选中用户?') == false) {
                        return;
                    }
                    var value = "";
                    var checks = document.getElementsByName("cb");
                    for (var i = 0; i < checks.length; i++) {
                        if (checks[i].checked) {
                            value = checks[i].value;

                            var urls = "${basePath}/lk-vc/admin/user/delete.do?id=" + value;
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
                        location = "${basePath}/lk-vc/admin/userList.do?wantpage=${nowpage}";
                    }


                }
            }
        </script>
</div>

</body>
</html>