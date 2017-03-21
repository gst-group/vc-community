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
    <link rel="stylesheet" type="text/css" href="${basePath}/page/util/paging.css">
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
                    </li>
                    <li>
<c:if test="${articleRoole==1}">
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

</c:if>
                    <li><a href="">
                        <i class="fa fa-empire fa-fw"></i> 话题管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse in">
                            <li><a href="${basePath}/admin/articleList.do">话题列表</a></li>
                            <li><a href="${basePath}/admin/articleAddView.do">话题发布</a></li>
                            <li><a href="${basePath}/admin/allCommentArticle.do">评论列表</a></li>
                            <li class="active"
                                >
                               <a style="color:white;background-color:#DEDEDE" href="${basePath}/admin/AlldeleteList.do">删除列表</a></li>
                        </ul>
                    </li>
<c:if test="${articleRoole==1}">
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
    </c:if>
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
                <h4 class="page-header">删除列表</h4>
                <c:if test="${delType==1}">
                    <select  id="op" onchange="extm()">
                                <option value="1" selected="selected">已删除评论列表</option>
                                <option value="2">已删除话题列表</option>
                    </select>
                </c:if>
                <c:if test="${delType==2}">
                    <select  id="op" onchange="extm()">
                        <option  value="1">已删除评论列表</option>
                        <option value="2" selected="selected" >已删除话题列表</option>
                    </select>
                </c:if>


                <div class="table-responsive">

                 <c:if test="${delType==1}">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户</th>
                            <th style="width:500px">内容</th>
                            <th>所属帖子</th>
                            <th>所属讨论区</th>
                            <th>时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${CommentList}" var="list" varStatus="status">
                        <tbody>
                        <tr>
                            <td>
                                <input type="checkbox" id="${list.cid }" value="${list.cid }" style="display:none"name="cb">

                            ${(status.index+1)*nowpage}</td>
                            <td><c:out value='${list.name1}'/>
                                <c:if test='${list.name2!=null&&list.name2!="" }'>
                                    @<c:out value='${list.name2}'/>
                                </c:if>
                            </td>

                            <td>${list.content}
                                <c:forEach items="${list.commentPics}" var="li" varStatus="s">
                                <img src="${basePath}/${li }" width="40px" height="40px">
                                </c:forEach>
                                <br>
                            <td>${list.article}</td>
                            <td>${list.group}</td>
                            <td><c:out value='${list.time}'/></td>
                        </tr>
                        </tbody>
                        </c:forEach>
                    </table>
                     </c:if>
                    <c:if test="${delType==2}">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>用户名</th>
                                <th width="200px">标题</th>
                                <th>所属讨论区</th>
                               <%-- <th>收藏列表</th>
                                <th>点赞列表</th>
                                <th>回复列表</th>--%>
                                <th>时间</th>
<%--
                                <th>操作</th>--%>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${list}" var="list" varStatus="status">
                            <tbody>
                            <tr>

                                    <td> ${(status.index+1)*nowpage}</td>
                                <c:forEach items="${user}" begin="${status.index }" end="${status.index }" var="u"
                                           varStatus="st">
                                    <td>${u.nickname}</td>
                                </c:forEach>
                                <td width="150px"><c:out value='${list.title}'/></td>
                                <c:forEach items="${time}" begin="${status.index }" end="${status.index }" var="t"
                                           varStatus="sts">
                                    <td>${t.inGroup}</td>
                                </c:forEach>

                             <%--   <td><a href="${basePath}/admin/like.do?id=${list.id}">收藏列表</a></td>
                                <td><a href="${basePath}/admin/likeList.do?id=${list.id}">点赞列表</a></td>
                                <td><a href="${basePath}/admin/articleCommentList.do?id=${list.id}">回复列表</a></td>--%>
                                <c:forEach items="${time}" begin="${status.index }" end="${status.index }" var="t"
                                           varStatus="sts">
                                    <td>${t.time}</td>
                                </c:forEach>

                                <%--<td>
                                    <a href="${basePath}/admin/articleInfo.do?id=${list.id}">详情</a>
                                    <a href="${basePath}/article/export.do?id=${list.id}">导出</a>&nbsp;&nbsp;

                                </td>--%>
                            </tr>
                            </tbody>

                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">


                        <div class="tcdPageCode">
                        </div>

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
    <script>var isSuperAdmin = ${sessionScope.adminUser.superAdmin?'true':'false'};</script>
    <script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>

    <script src="${basePath}/page/util/paging.js"></script>

    <script type="text/javascript">
        $(".tcdPageCode").createPage({
            pageCount: Number('${count}'),
            current: Number('${nowpage}'),
            backFn: function (p) {
                location.href = "${basePath}/admin/AlldeleteList.do?nowpage=" + p+"&type=${delType}";
            }
        });



        function extm(){
            var myselect = document.getElementById("op");
            var index = myselect.selectedIndex;
            var val = myselect.options[index].value;
            location.href = "${basePath}/admin/AlldeleteList.do?type="+val;

        };

        function toPage() {
            var page = $("#to_page").val();
            if (isNaN(page)) {
                alert("请输入数字！");
            } else if (page <= 0) {
                alert("输入不合法！");
            } else if (page > ${count}) {
                alert("超出范围！");
            } else {
                location.href = "${basePath}/admin/AlldeleteList.do?nowpage=" +page+"&type=${delType}";
            }
        }


		//回复
    function rep(id,aid){
        var content = prompt("请输入内容", "");
        if (content == "" || content == null) {
            return;
        }
        var urls = "${basePath}/admin/AdminArticleRep.do?aid="+aid+"&id="+id+"&content="+content;
        $.ajax({
            url: urls,
            async:false,
            type: 'post',
            dataType: "json",
            success: function (data) {
              if(data=="success"){
                  alert("回复成功");
                  location=location;
              }
            },error:function () {
                alert("回复成功");
                location=location;
            }
        });


    }

        function pass(type) {
            //12通过34删除
            if (type ==1) {
                var div2 = document.getElementById("p");
                div2.style.display = "none";
                var div3 = document.getElementById("p2");
                div3.style.display = "Inline";
                <c:forEach items="${CommentList}" var="list" varStatus="status">
                var div4 = document.getElementById('${list.cid}');
                div4.style.display = "Inline";
                </c:forEach>;
            }else if(type==2){
                var div2 = document.getElementById("p");
                div2.style.display = "inline";
                var div3 = document.getElementById("p2");
                div3.style.display = "none";
                <c:forEach items="${CommentList}" var="list" varStatus="status">
                var div4 = document.getElementById('${list.cid}');
                div4.style.display = "none";
                </c:forEach>;
            }else if (type == 3) {
                var div2 = document.getElementById("pd");
                div2.style.display = "none";
                var div3 = document.getElementById("pd2");
                div3.style.display = "Inline";
                <c:forEach items="${CommentList}" var="list" varStatus="status">
                var div4 = document.getElementById('${list.cid}');
                div4.style.display = "Inline";
                </c:forEach>;
            }else{
                var div2 = document.getElementById("pd");
                div2.style.display = "inline";
                var div3 = document.getElementById("pd2");
                div3.style.display = "none";
                <c:forEach items="${CommentList}" var="list" varStatus="status">
                var div4 = document.getElementById('${list.cid}');
                div4.style.display = "none";
                </c:forEach>;
            }

var y=0;

                if(type==4){

                    if (confirm('确定删除选中评论?') == false) {
                        return;
                    }
                    type=3;
                    y=1;
                }
            if(type==2){

                if (confirm('确定通过选中评论?') == false) {
                    return;
                }
                type=0;
                y=1;
            }
            if(y==1){
                var value = "";
                var checks = document.getElementsByName("cb");
                var re=1;
                for (var i = 0; i < checks.length; i++) {


                    if (checks[i].checked) {
                        value = checks[i].value;
                        var urls = "${basePath}/admin/article/comment/delete.do?type="+type+"&id=" + value;
                        $.ajax({
                            url: urls,
                            type: 'post',
                            dataType: "text",
                            success: function (data, textStatus) {

                            }
                        });
                        re=0;
                    }

                }
                if(re==0){
                    alert("操作成功");
                    location = location;
                }
            }





        }


    </script>
</body>
</html>
