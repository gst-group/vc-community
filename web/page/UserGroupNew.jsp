<!DOCTYPE html>
<!-- saved from url=(0072)http://121.40.137.214:80/1/json.php?mod=admin&act=newNotifyPage&1465824995 -->
<html lang="en">
<%@ page language="java" pageEncoding="UTF-8" %>
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
    <script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/modernizr.custom.js"></script>
    <!-- STYLES -->

    <script type="text/javascript" src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/lk-vc/page/loadContent.js"></script>
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


        function save() {
            var title = $("#title").val();
            var content = $("#img").val();
            if (title == "" || title == null) {
                alert("请输入讨论区名称");
                return;
            }
            if (content == "" || content == null) {
                alert("请选择讨论区LOGO");
                return;
            }
            setGroupInfo("#list1");
           
            $("#filterUsers").val(JSON.stringify(selectedGrpOrUser));
           document.getElementById('form1').submit();
        }
        
        window.onload = function () {
          var msg='${msg}';
          if(msg=="1"){
        	  alert("添加成功");
        	  location.href="/lk-vc/admin/userGroup.do";
          }else if(msg=="0"){
        	  alert("添加失败");
          }else if(msg=="2"){
        	  alert("讨论区已存在");
          }
        }

    </script>
    <style type="text/css">
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
            font-size: 12px;
            color: #000000
        }

        #DialogDiv .form {
            padding: 10px;
        }

        ul {
            list-style: none;
        }

        ul li {
            list-style: none;
        }

        #filter {
            text-align: left;
        }

        #list1 {
            border-right: 1px solid black;
            text-align: left;
            width: 120px;
            display: inline-block;
        }

        #list2 {
            text-align: left;
            width: 180px;
            display: inline-block;
            max-height: 170px;
            overflow-x: hidden;
        }
    </style>


    <link href="http://121.40.137.214:80/statics/uploadify/uploadify.css" rel="stylesheet" type="text/css">
</head>
<body style="background:#ffffff;font-family:&#39;å¾®è½¯éé»&#39;;&#39;">
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

                    <li><a href=""><i class="fa fa-drupal fa-fw"></i> 用户管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/lk-vc/admin/userList.do">用户列表</a></li>
                            <li><a href="${basePath}/lk-vc/page/UserAdd.jsp">新增用户</a></li>
                        </ul>
                    </li>


                    <li><a href=""><i class="fa fa-drupal fa-fw"></i>
                        讨论区管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/lk-vc/admin/userGroup.do">讨论区列表</a></li>
                            <li><a href="${basePath}/lk-vc/admin/userGroupNew.do" class="active"
                                   style="color:white;background-color:#DEDEDE">新增讨论区</a></li>
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
                            <li><a href="${basePath}/lk-vc/admin/sendPushView.do" >新建推送</a></li>
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
    <div id="page-wrapper" style="min-height: 685px;">
        <div class="container-fluid">

            <div class="row">

                <div id="BgDiv"></div>
                <div id="DialogDiv" style="display:none; width:550px;height:400px;">
                    <h2><a href="#" id="btnClose">关闭</a></h2>
                    <div>
                        <div style="float:right;margin-right:10px">
                            <form id="filter" action="#">
                                <table>
                                    <tr>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;性别</td>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:100px">
                                            <input name="sex" type="radio" value="0"/>男
                                            <input name="sex" type="radio" value="1"/>女
                                            <input name="sex" type="radio" value="-1"/>保密
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;年龄</td>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:120px">
                                            <input name="ageS" type="text" style="width:50px"/>
                                            <input name="ageE" type="text" style="width:50px"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;城市</td>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:100px">
                                            <input name="city" type="text" style="width:103px"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:100px">&nbsp;&nbsp;学历</td>
                                        <td style="border:1px #CDCDC1 solid;height:30px;width:100px">
                                            <input name="degree" type="text" style="width:103px"/></td>
                                    </tr>
                                    <tr>
                                        <td style="border:1px #CDCDC1 solid;height:52px;width:100px">&nbsp;&nbsp;婚否</td>
                                        <td style="border:1px #CDCDC1 solid;width:100px">
                                            <input name="marriage" type="radio" value="false"/>未婚
                                            <input name="marriage" type="radio" value="true"/>已婚<br/>
                                            <input name="marriage" type="radio" value=""/>保密
                                        </td>
                                    </tr>
                                    <c:forEach items="${extendsInfo}" var="info">
                                        <tr>
                                            <td style="border:1px #CDCDC1 solid;height:30px;width:100px">
                                                <c:out value="${info.name}"/>
                                            </td>
                                            <td style="border:1px #CDCDC1 solid;">
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

                                    </c:forEach>
                                    
                                    <tr>

                                        <select name="selectionType" id="selectionType" style="width:100px">
                                            <option value="0">只选左边</option>
                                            <option value="1">只选右边</option>
                                            <option value="2">左右求交</option>
                                            <option value="3">左右求并</option>
                                        </select>
                                        <input type="text" id="salert" style="width:121px;height:20px">
                                        <script>
                                            //查询用户信息
                                            $('#salert').on('propertychange input',function() {
                                                var name=eval(document.getElementById('salert')).value ;
                                                var reqParams = {};
                                                reqParams['name'] =name;
                                                $.post("userSearchAlert.do", reqParams, function (data) {
                                                    console.log(data);
                                                    var jsda=JSON.parse(data);
                                                    var html="";
                                                    for(var i=0;i<jsda.UserList.length;i++){
                                                        //    console.log(jsda.UserList[i].name);
                                                        html+='<input type="checkbox" onchange="selectUser(this,'+jsda.UserList[i].name+',-1)"><span>'+jsda.UserList[i].name+'</span><br/>';
                                                    };
                                                    html+='<input type="checkbox" onchange="selectUser(this,-1,-1)" style="display:none">';

                                                    document.getElementById("list2").innerHTML="";
                                                    document.getElementById("list2").innerHTML=html;
                                                }).error(function (res) {
                                                    alert("系统错误");
                                                });
                                            });
                                        </script>
                                    </tr>
                                </table>
                            </form>
                        </div>

                    </div>
                    <div style="text-align: left;padding-left:10px">
                        <ul id="list1">

                            <div style="width:120px;border:1px #CDCDC1 solid;overflow-Y: scroll;height:193px">

                                <li><a href="#" onclick="selectAllGroup(true);return false;">全选</a>
                                    <a href="#" onclick="selectAllGroup(false);return false;">全不选</a>
                                </li>
                                <div style="width:120px;height:193px">
 <c:forEach items="${group}" var="list1" varStatus="status">
                                        <li onclick="return filterUser('#filter',<c:out value="${list1.id}"/>)">
                                            <input id="list1<c:out value="${list1.id}"/>"
                                                   type="checkbox"
                                                   name="<c:out value="${list1.name}"/>"
                                                   value="<c:out value="${list1.id}"/>"/>
                                            <label for="<c:out value="${list1.name}"/>" style="cursor: pointer">
                                                <c:out value="${list1.name}"/>
                                            </label>
                                        </li>
                                    </c:forEach>
                                </div>
                        </ul>
                        <span style="float:right;">
           <div style="border:1px #CDCDC1 solid;width:185px;height:193px;padding-left:0px">
               <div><a href="#" onclick="selectAllUser();return false;">全选</a>
                   <a href="#" onclick="deselectAllUser();return false;">全不选</a></div>
            <ul id="list2">

            </ul>
            </div>
            </span>
                    </div>

                    <center>
                        <button type="submit"
                                style="margin-left:200px;background:#ed5f00;color:white;border:0px;width:100px;height:40px;width:100px;margin-top:80px;"
                                onclick="save()">确定
                        </button>
                    </center>
                </div>


                <h4 class="page-header">新增讨论区</h4>
                <form action="${basePath}/lk-vc/admin/user/group/add.do" id="form1" enctype="multipart/form-data"
                      method="post" style="width:90%">
                    <div class="form-group">
                        <input type="hidden" name="usrs" id="filterUsers" value=""/>
                        <div style="padding-left:120px" class="control-group">
                            <label class="control-label">讨论区名称</label>
                            <input type="text" class="form-control" id="title" name="grp"/>
                        </div>
                        <div style="padding-left:120px" class="control-group">
                          <input type="radio" value="1" name="type">用户组&nbsp;&nbsp;<input name="type" type="radio" checked="checked" value="2">项目组
                        </div>
                        <div style="padding-left:13%">
                            <label class="control-label">LOGO图片</label>

                            <br/>
                            <a href="javascript:;" class="file">
                                <img src="${basePath}/lk-vc/img/Button.png">
                                <input type="file" name="img" id="img">
                            </a>
                            <span class="showFileName fileerrorTip"></span>

                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <!-- onclick="send()" -->
                                <input class="btn" type="button"
                                       style="margin-left:40%;background:#ed5f00;color:white;border:0px" id="btnShow"
                                       class="btn btn-default" id="save_jobs" value="选择组成员"/></div>
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

<script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>

<script src="http://121.40.137.214:80/statics//uploadify/jquery.uploadify.js" type="text/javascript"></script>


<!-- JS -->
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/waypoints.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/waypoints-sticky.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/jquery.hideseek.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/rainbow-custom.min.js"></script>
<script type="text/javascript" src="${basePath}/lk-vc/page/util/javascripts/vendor/jquery.anchor.js"></script>
<script src="${basePath}/lk-vc/page/util/javascripts/initializers.js"></script>

<script>
    $('#search-highlight').bind('input propertychange', function () {
        var content = $("#search-highlight").val();
        if (content == null || content == "") {
            return;
        }

        /* 	 var div2 = document.getElementById("uls");
         div2.style.display="Inline";  */
        var html = "";
        $
                .ajax({
                    url: "${basePath}/lk-vc/admin/getArticleTitle.do?content=" + content,
                    type: 'post',
                    dataType: "json",
                    success: function (data, textStatus) {

                        var obj = data.li;
                        for (var i = 0; i < obj.length; i++) {
                            html = html + "<li    onclick='cl(" + obj[i].id + ")'><input type='text' style='display:none'value='" + obj[i].title + "' id='" + obj[i].id + "'>" + obj[i].title + "<li>";
                        }
                        $("#uls").html(html);
                    }
                });


    });
    function cl(id) {
        var html = $("#" + id).val();
        document.getElementById('search-highlight').value = html;
        document.getElementById('hi').value = id;

        var s = document.getElementById('uls');
        for (var i = 0; i < s.childNodes.length; i++) {
            s.removeChild(s.childNodes[i]);
        }

    }

    $(".file").on("change", "input[type='file']", function () {
        var filePath = $(this).val();
        if (filePath.indexOf("png") != -1 || filePath.indexOf("jpg") != -1 || filePath.indexOf("jpeg") != -1) {
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