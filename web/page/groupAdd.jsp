<!DOCTYPE html>
<!-- saved from url=(0071)http://121.40.137.214:80/1/json.php?mod=admin&act=newGroupPage&1465824936 -->
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
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/page/loadContent.js"></script>
    <script>
        window.onload = function a() {
            if ('${addmsg}' == "error") {
                alert("管理者不存在");
            }
        }

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
            var gname = $("#gname").val();
            if (gname == "") {
                alert("组名不能为空");
                return;
            }
            var owner = $("#owner").val();
            if (isNaN(owner) || owner == "") {
                alert("请输入正确管理者账号！");
                return;
            }

            var max = $("#maxusers").val();
            if (isNaN(max) || max <= 0) {
                alert("请输入正确的群组人数(数字)！");
                return;
            }
            var desc = $("#desc").val();
            /* 	value=" ";
             for (var i=0;i<user.length;i++ ){
             if(user[i].checked){
             value=value+user[i].value + ",";
             }
             }
             if(value==" "){
             alert("请选择组成员");
             return;
             } */
             setGroupInfo("#list1");
             if (selectedGrpOrUser.group.length == 0 && selectedGrpOrUser.user.length == 0) {
                 alert("请选择接收成员");
                 return;
             }
             ;
            $("#filterUsers").val(JSON.stringify(selectedGrpOrUser));
            /*  document.getElementById('form1').submit(); */

            $("#sub").click();
            /*  var urls="
            ${basePath}/admin/groupAdd.do?gname="+gname+"&owner="+owner+"&users="+JSON.stringify(selectedGrpOrUser)+"&desc="+desc+"&max="+max;

             $.ajax({
             url:urls,
             type : 'post',
             dataType : "text",
             success : function(data, textStatus) {
             if(data=="success"){
             alert("添加成功");
             location.href="
            ${basePath}/admin/groupInfo.do";
             }else{
             alert("网络错误");
             }
             }
             });  */
        }


    </script>
    <style>
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
            text-align: left;
            width: 120px;
            display: inline-block;
        }

        #list2 {
            text-align: left;
            width: 150px;
            display: inline-block;
            max-height: 170px;
            overflow-x: hidden;
        }

    </style>


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
            <a class="navbar-brand" href="${basePath}/page/index.jsp">Admin v2.0</a>
        </div>

        <!-- /.navbar-top-links -->
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
                            <li><a href="${basePath}/admin/allUser.do" class="active"
                                   style="color:white;background-color:#DEDEDE">新增聊天群组</a></li>
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
                <h4 class="page-header">添加群组</h4>
                <form action="${basePath}/admin/groupAdd.do" id="from1" class="form-horizontal"
                      enctype="multipart/from-data" method="post" style="width:90%" role="form">
                    <div class="form-group">
                        <input type="hidden" name="usrs" id="filterUsers" value=""/>
                        <label class="col-sm-2 control-label">群组名称：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" name="gname" id="gname">
                            <!--    <input type="hidden" class="form-control"> --></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="padding-top:7px;">群组描述：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" name="desc" id="desc"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="padding-top:7px;">群组人数：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" id="maxusers" name="max"></div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="padding-top:7px;">管理员：</label>
                        <div class="col-sm-5"><input type="text" class="form-control" id="owner" name="owner"
                                                     placeholder="已注册用户号码"></div>
                        <label class="col-sm-4" style="padding-top:7px;"></label>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label" style="padding-top:7px;">添加成员：</label>
                        <div><input type="button" id="btnShow" style="background:#ed5f00;color:white;border:0px;"
                                    value="选择组成员" class="btn btn-default"></div>

                        <%-- 	    <div style="width:450px; height:225px; overflow:auto;">
                                <table>
                               <c:forEach  items="${ul}"  var="list" varStatus="status">
                               <c:if test="${(status.index+1)%3==0 }">
                               <tr>
                                <td><input onclick="setGroup();" id="user" value="${list.name }"type="checkbox" > ${list.nickname }&nbsp;&nbsp;</td>
                               </c:if>

                              <c:if test="${(status.index+1)%3==1 }">
                              <td><input onclick="setGroup();" id="user" value="${list.name }"type="checkbox"> ${list.nickname }&nbsp;&nbsp;</td>
                               </c:if>

                              <c:if test="${(status.index+1)%3==2 }">
                              <td><input onclick="setGroup();" id="user" value="${list.name }"type="checkbox"> ${list.nickname }&nbsp;&nbsp;</td>
                              </tr>
                               </c:if>
                               </c:forEach>
                        </table>
                                </div> --%>
                    </div>
                    <div class="form-group" style="display:none">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" id="sub"
                                    style="width:100px;background:#ed5f00;color:white;border:0px;margin-left:20%"
                                    class="btn btn-default" id="save_jobs">保存
                            </button>
                        </div>
                    </div>

                </form>


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
                                    <%--   <tr>

                                          <td style="border:1px #CDCDC1 solid;height:30px;width:100px">
                                          &nbsp;<c:out value="${info.name}"/>
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
                                      </tr> --%>

                                    <tr>

                                        <select name="selectionType" id="selectionType" style="width:100px">
                                            <option value="0">只选左边</option>
                                            <option value="1">只选右边</option>
                                            <option value="2">左右求交</option>
                                            <option value="3">左右求并</option>
                                        </select>
                                    </tr>
                                </table>
                            </form>
                        </div>

                    </div>
                    <div style="text-align: left;padding-left:10px">
                        <ul id="list1">

                            <div  style="width:120px;border:1px #CDCDC1 solid;overflow-Y: scroll;height:193px">

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



</body>
</html>