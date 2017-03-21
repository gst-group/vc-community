<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin 2 - Bootstrap Admin Theme</title>
    <link rel="stylesheet" href="${basePath}/lk-vc/page/kindeditor-master/themes/default/default.css"/>
    <link rel="stylesheet" href="${basePath}/lk-vc/page/kindeditor-master/plugins/code/prettify.css"/>
    <script charset="utf-8" src="${basePath}/lk-vc/page/kindeditor-master/kindeditor-all.js"></script>
    <script charset="utf-8" src="${basePath}/lk-vc/page/kindeditor-master/lang/zh-CN.js"></script>
    <script charset="utf-8" src="${basePath}/lk-vc/page/kindeditor-master/plugins/code/prettify.js"></script>
    <script type="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>

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
    </style>
    <script>
        function show_sub(v) {
            var a = v - 100;
            if (a == 5) {
                $("#5").show();
                $("#6").hide();
                $("#7").hide();
                $("#8").hide();
            }
            if (a == 6) {
                $("#6").show();
                $("#5").hide();
                $("#7").hide();
                $("#8").hide();
            }
            if (a == 7) {
                $("#7").show();
                $("#6").hide();
                $("#8").hide();
                $("#5").hide();
            }
            if (a == 8) {
                $("#8").show();
                $("#5").hide();
                $("#6").hide();
                $("#7").hide();
            }

        }
        function show_sub2(v) {
            var a = v - 100;
            if (a == 9) {
                $("#9").show();
                $("#10").hide();
                $("#11").hide();
                $("#12").hide();
            }
            if (a == 10) {
                $("#10").show();
                $("#9").hide();
                $("#11").hide();
                $("#12").hide();
            }
            if (a == 11) {
                $("#11").show();
                $("#12").hide();
                $("#9").hide();
                $("#10").hide();
            }
            if (a == 12) {
                $("#12").show();
                $("#11").hide();
                $("#10").hide();
                $("#9").hide();
            }

        }

    </script>
    <!-- Bootstrap Core CSS -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/lk-vc/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
                        <a href="${basePath}/lk-vc/page/index.jsp"><i class="fa fa-dashboard fa-fw"></i> 首页</a>

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
                            <li class='hideIfNotSuper'><a href="${basePath}/lk-vc/page/adminList.jsp">管理员列表</a></li>
                            <li class='hideIfNotSuper'><a href="${basePath}/lk-vc/page/adminAdd.jsp">添加管理员</a></li>
                            <li><a href="${basePath}/lk-vc/page/adminPass.jsp">修改密码</a></li>
                        </ul>
                    </li>


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

                    <li><a href="">
                        <i class="fa fa-empire fa-fw"></i> 聊天管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/groupInfo.do">聊天群组列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/allUser.do">新增聊天群组</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 日记管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/diaryList.do">日记列表</a></li>
                        </ul>
                    </li>


                    <li><a href="">
                        <i class="fa fa-gittip fa-fw"></i> 推送通知<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/messageList.do">推送列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/sendPushView.do">新建推送</a></li>
                        </ul>
                    </li>


                    <li><a href="">

                        <i class="fa fa-gittip fa-fw"></i> 积分管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/lk-vc/admin/ScoreInfo.do" class="active"
                                   style="color:white;background-color:#DEDEDE">积分规则修改</a></li>
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

    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <h4 class="page-header">积分规则修改</h4>
                <!-- 登录 -->
                <%--           <select>
                <c:forEach begin="0" end="50" varStatus="stataus">
                 <option>${stataus.index }</option>
                </c:forEach>

                </select> --%>


                <table width="600px" style="margin-left:16.3%;">
                    <tr>
                        <td align="right" width="15%">${li[0].name }</td>
                        <td align="center" width="15%">
                            <input type="text" value="${li[0].value }" id="1"
                                   style="margin-top:10px;text-align:center; width:80px;height:30px">
                        </td>
                        <td style="display: none" align="right" width="10%">${li[1].name }</td>
                        <td style="display: none" align="center" width="10%"><select id="2">
                            <c:forEach begin="1" end="50" varStatus="so">
                                <option value="${so.index }">${so.index }</option>
                            </c:forEach>
                            <option value="${li[1].value }" selected="selected">${li[1].value }</option>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" width="15%">${li[3].name }</td>
                        <td align="center" width="10%">
                            <input type="text" id="4" value="${li[3].value }"
                                   style="margin-top:10px;text-align:center;width:80px;height:30px">
                        </td>
                    </tr>
                    <tr style="line-height:30px">
                        <td align="right" width="15%">${li[2].name }</td>
                        <td align="center" width="10%">
                            <input type="text" id="3" value="${li[2].value }"
                                   style="margin-top:10px;text-align:center;width:80px;height:30px">
                        </td>
                        <td align="right" width="10%"></td>
                        <td align="center" width="10%">
                        </td>
                    </tr>
                    <tr style="line-height:30px">
                        <td style="display:none" align="right" width="15%">${li[12].name }</td>
                        <td style="display:none" align="center" width="10%"><select id="13">
                            <c:forEach begin="1" end="50" varStatus="so">
                                <option value="${so.index }">${so.index }</option>
                            </c:forEach>
                            <option value="${li[12].value }" selected="selected">${li[12].value }</option>
                        </select></td>
                        <td align="right" style="display:none" width="10%">${li[13].name }</td>
                        <td style="display:none" align="center" width="10%"><select id="14">
                            <c:forEach begin="1" end="50" varStatus="so">
                                <option value="${so.index }">${so.index }</option>
                            </c:forEach>
                            <option value="${li[13].value }" selected="selected">${li[13].value }</option>
                        </select></td>
                    </tr>
                </table>
                <select style="width:350px" id="login"
                        onchange="show_sub(this.options[this.options.selectedIndex].value)">
                    <c:forEach items="${li}" begin="4" end="7" var="list" varStatus="status">
                        <option value="${status.index+101 }">${list.name }</option>
                    </c:forEach>
                    <%--   <c:forEach  items="${li}" begin="4" end="7" var="list" varStatus="status">
                         </c:forEach> --%>
                </select>
                <span style="padding-left:4.4%">
                <input type="text" value="${li[4].value }" id="5" style="margin-top:10px;width:80px;text-align:center;">
                 <input type="text" value="${li[5].value }" id="6"
                        style="margin-top:10px;text-align:center;width:80px;display:none">
                 <input type="text" value="${li[6].value }" id="7"
                        style="margin-top:10px;text-align:center;width:80px;display:none">
                 <input type="text" value="${li[7].value }" id="8"
                        style="margin-top:10px;text-align:center;width:80px;display:none">
                 </span>
                <p>
                    <!-- 回复 -->
                    <select style="width:350px;margin-top:10px"
                            onchange="show_sub2(this.options[this.options.selectedIndex].value)">
                        <c:forEach items="${li}" begin="8" end="11" var="list" varStatus="status">
                            <option value="${status.index+101 }">${list.name }</option>
                        </c:forEach>
                        <%-- <c:forEach  items="${li}" begin="8" end="11" var="list" varStatus="status">
                                       </c:forEach>  --%>              </select>
                    <span style="padding-left:4.4%">
                 <input type="text" value="${li[8].value }" style="text-align:center;width:80px" id="9">
                 <input type="text" value="${li[9].value }" style="text-align:center;width:80px;display:none" id="10">
                 <input type="text" value="${li[10].value }" id="11" style="text-align:center;width:80px;display:none">
                 <input type="text" value="${li[11].value }" id="12" style="text-align:center;width:80px;display:none">
                 </span>

                <p>
                    <input type="button"
                           style="margin-top:50px;background:#ed5f00;color:white;border:0px;width:100px;height:40px;margin-left:30%"
                           onclick="save()" value="保存">
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <script>
        function save() {
            var array = new Array();
            <c:forEach items="${li}" var="a">
            array.push('${a.id}');
            </c:forEach>
            var array2 = new Array();
            for (var int = 0; int < array.length; int++) {
                var val = $("#" + array[int] + "").val();

                if (isNaN(val)) {
                    alert("请输入数字");
                    return;
                }
                if (val > 50 || val < -1) {
                    alert("积分范围为0~50");
                    return;
                }

                array2.push(val);
            }

            if (window.confirm("发布日记：" + $("#1").val() + "分\u000d生日：" + $("#3").val() + "分\u000d发布反馈：" + $("#4").val() + "分\u000d" +
                            "登录<30分钟：" + $("#4").val() + "分。登录30~60分钟：" + $("#5").val() +
                            "分。\u000d登录60~80分钟：" + $("#6").val() + "分。登录>80分钟：" + $("#7").val() +
                            "分\u000d回复<6小时：" + $("#8").val() + "分。回复6~24小时：" + $("#9").val() +
                            "分。\u000d回复24~48小时：" + $("#10").val() + "分。回复>48小时：" + $("#11").val() + "分。\u000d确定修改？")) {
            } else {
                return false;
            }
            var urls = "${basePath}/lk-vc/admin/scoreChang.do?&li1=" + array + "&li2=" + array2;
            $
                    .ajax({
                        url: urls,
                        type: 'post',
                        dataType: "text",
                        success: function (data, textStatus) {
                            if (data == "success") {
                                alert("修改成功");
                                location = location;
                            } else {
                                alert("网络错误");
                            }
                        },
                        error: function (data) {
                            alert("网络错误");
                        }
                    });
        }


    </script>
    <!-- Bootstrap Core JavaScript -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script>var isSuperAdmin = ${sessionScope.adminUser.superAdmin?'true':'false'};</script>
    <script src="/lk-vc/js/sb-admin-2.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>


</body>
</html>
