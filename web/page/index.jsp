<!DOCTYPE html>
<!-- saved from url=(0067)http://121.40.137.214:80/1/json.php?mod=admin&act=index&1465824401136 -->
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
    <style type="text/css">
    .a-upload {
            padding: 4px 10px;
            height: 20px;
            line-height: 20px;
            position: relative;
            cursor: pointer;
            color: #888;
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
            text-decoration: none
        }

        /*file*/
        .file {
            margin-top: 10px;
            position: relative;
            display: inline-block;
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
            text-decoration: none;
        }

        body, h2, ul {
            margin: 0;
            padding: 0;
        }
            .mainDiv {
                width: 1000px;
                overflow: hidden;
                margin: 0px auto;

            }
            .leftDiv {
                width: 400px;
                float: left;
                margin-right: 5px;


            }
            .rightDiv {
                width: 500px;
                float: right;
                margin-left: 5px;

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
            <a class="navbar-brand" href="${basePath}/lk-vc/page/index.jsp">Admin v2.0</a>
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
                <div class="col-lg-12">
                    <h3 class="page-header">Welcome</h3>
                </div>
<div class="mainDiv" aling="center">
            <!-- 左边显示最新版本信息 -->
            <div class="leftDiv">
                <table>
                    <tr>
                        <td width="130px">Android版本号：&nbsp;</td>
                        <td>${code}</td>
                    </tr>
                    <tr>
                        <td>更新日志：&nbsp;</td>
                        <td>
                            <textarea  style="font-family:楷 体;font-size: 18px; resize:none;" rows="3" cols="30" disabled="disabled">${log}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>更新时间：&nbsp;</td>
                        <td>${time}</td>
                    </tr>
                    <tr>
                        <td>下载地址：&nbsp;</td>
                        <td><a href="/lk-vc/${url}" />安装包下载</td>
                    </tr>

                </table>


<br/>
                <table>
                    <tr>
                        <td width="130px">IOS版本号：&nbsp;</td>
                        <td>${code2}</td>
                    </tr>
                    <tr>
                        <td>更新日志：&nbsp;</td>
                        <td>
                            <textarea  style="font-family:楷 体;font-size: 18px; resize:none;" rows="3" cols="30" disabled="disabled">${log2}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>更新时间：&nbsp;</td>
                        <td>${time2}</td>
                    </tr>
                    <tr>
                        <td>下载地址：&nbsp;</td>
                        <td><a href="/lk-vc/${url2}" />安装包下载</td>
                    </tr>
                    <tr>

                    <tr>

                        <td>
                            <%--<input class="but" type="button"--%>
                                   <%--style="margin-top:50px;background:#ed5f00;color:white;border:0px;width:100px;height:40px;"--%>
                                   <%--class="btn btn-default" onclick="location.href='${basePath}/lk-vc/admin/Sensitive.do'" value="敏感词管理"/>--%>


                        </td>
                    </tr>
                </table>
            </div>

            <!-- 右边提交最新版本信息 -->
            <div class="rightDiv">
                <form name="newVersion" action="${basePath}/lk-vc/admin/app/new.do" method="post"
                      enctype="multipart/form-data">
                    <table>
                        <tr style="line-height:30px">
                            <td>待提交版本：&nbsp;</td>
                            <td><input type="text" value="" name="code" id="code" /></td>
                        </tr>
                        <tr  style="line-height:30px">
                            <td>版本：&nbsp;</td>
                            <td><select name="type">
                                <option value="1">安卓</option>
                                <option value="2">ios</option>
                                </select>
                            </td>
                        </tr>
                        <tr style="line-height:50px">
                            <td>待提交更新日志：&nbsp;</td>
                            <td>
                                <textarea  style="font-family:楷 体;font-size: 18px; resize:none;" name="log" id="log" rows="3" cols="30" ></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>待提交安装文件：&nbsp;</td>
                            <td>
                                 <a href="javascript:;" class="file"><img src="${basePath}/lk-vc/img/Button.png">
                                    <input type="file" name="files" id="files">
                                </a>
                                <span class="showFileName fileerrorTip"></span>

                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right">
                                <input type="button" onclick="save()" style="background:#ed5f00;color:white;border:0px;width:100px" value="提交最新版本" />
                                <input type="submit" id="submit" style="display:none" value="提交最新版本" />
                            </td>
                        </tr>
                    </table>
                </form>
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
<script>
window.onload = function () {
    var admin = '<%=session.getAttribute("adminUser")%>';
    if (admin == "null") {
        location.href = "${basePath}/lk-vc/page/Login.jsp";
    }

    if('${type}'!="1"){
        location.href="${basePath}/lk-vc/admin/app/Version.do";
    }
    if('${msg}'=="success"){
        alert("添加成功");
    }
}

        function save() {
            var val1 = $("#code").val();
            var val2 = $("#log").val();
            if (jQuery.trim(val1).length == 0) {
                alert("请提供版本号");
                return;
            }
            if (jQuery.trim(val2).length == 0) {
                alert("请填写更新日志");
                return;
            }
            $("#submit").click();
        }

$(".file").on("change", "input[type='file']", function () {
    var filePath = $(this).val();
    if (filePath.indexOf("apk") != -1 ||filePath.indexOf("ipa") != -1 ) {
        $(".fileerrorTip").html("").hide();
        var arr = filePath.split('\\');
        var fileName = arr[arr.length - 1];
        $(".showFileName").html(fileName).show();
    } else {
        $(".showFileName").html("");
        $(".fileerrorTip").html("您上传文件类型有误！").show();
        $(this).val("");
        return false;
    }
})
</script>


</body>
</html>