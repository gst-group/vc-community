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
                            <li><a href="${basePath}/admin/articleList.do"class="active"
                                   style="color:white;background-color:#DEDEDE">话题列表</a></li>

<c:if test="${rootNumber!=4}">
                            <li><a href="${basePath}/admin/articleAddView.do">话题发布</a></li>

							</c:if>

                            <li ><a href="${basePath}/admin/allCommentArticle.do">评论列表</a></li>
                            <c:if test="${rootNumber!=4}">
							
							<li>
                                <a href="${basePath}/admin/AlldeleteList.do">删除列表</a></li>
</c:if>
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
                <h4 class="page-header">回复列表</h4>
               
											                            <c:if test="${rootNumber!=4}">

				<c:if test="${CommentType==0}">
                    <select  id="op" onchange="extm()">

                        <option value="2">审核中</option>
                        <option value="0" selected="selected">已通过</option>
                        <option value="1">未通过</option>
                    </select></c:if>
                <c:if test="${CommentType==1}">
                    <select  id="op" onchange="extm()">

                        <option value="2">审核中</option>
                        <option value="0">已通过</option>
                        <option value="1" selected="selected">未通过</option>
                    </select></c:if>
                <c:if test="${CommentType==2}">
                    <select  id="op" onchange="extm()">

                        <option value="2" selected="selected">审核中</option>
                        <option value="0">已通过</option>
                        <option value="1">未通过</option>
                    </select>

                </c:if>
</c:if>

                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户</th>
                            <th>内容</th>
                            <th>时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="list" varStatus="status">
                        <tbody>
                        <tr>
                            <td><c:out value='${list.id}'/></td>
                            <td><c:out value='${list.name1}'/>
                                <c:if test='${list.name2!=null }'>
                                    @<c:out value='${list.name2}'/>
                                </c:if>
                            </td>

                            <td>${list.content}
                                <c:forEach items="${imgList}" var="li" varStatus="s">
                                <img src="${li }">
                                </c:forEach>
                                <br>
                            <td><c:out value='${list.time}'/></td>
                            <td>
														                            <c:if test="${rootNumber!=4}">
<a href="" onclick="del(${list.id},1)">删除</a></c:if>
                            &nbsp;&nbsp;<a onclick="rep(${list.id})"> 回复</a>&nbsp;&nbsp;
                            <c:if test="${CommentType==2}">
                                <a onclick="del(${list.id},3)">拒绝</a>&nbsp;&nbsp;
                                <a onclick="del(${list.id},0)">通过</a>
                            </c:if>
                            </td>
                        </tr>

                        </tbody>
                        </c:forEach>

                    </table>
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
                location.href = "${basePath}/admin/articleCommentList.do?id=${id }&wantpage=" + p+"&type=${CommentType}";
            }
        });




        function extm(){
            var myselect = document.getElementById("op");
            var index = myselect.selectedIndex;
            var val = myselect.options[index].value;
            location.href="${basePath}/admin/articleCommentList.do?id=${id}&type="+val;
        };
        function del(id,type) {
            if(type==3){
                type=1;
            }
            if(type==1){

                if (confirm('确定删除该评论?') == false) {
                    return;
                }
             type=3;
            }

            var urls = "${basePath}/admin/article/comment/delete.do?type="+type+"&id=" + id;
            $.ajax({
                url: urls,
                type: 'post',
                dataType: "text",
                success: function (data, textStatus) {
                    alert("操作成功");
                    location = location;
                }
            });
        }
        function topage() {
            var page = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > pagecount) {
                alert("超出范围！");
            } else {
                location.href = "${basePath}/admin/articleCommentList.do?type=${CommentType}&id=${id }&wantpage=" + topage;
            }
        }
        function load(wantpage) {


            var urls = "${basePath}/admin/articleCommentList.do?type=${CommentType}&id=${id }&wantpage=" + page;
            location.href = urls;
        }
        function s() {
            alert("已是第一页");
        }
        function next() {
            alert("已是最后一页");
        }

		//回复
    function rep(id){
        var content = prompt("请输入内容", "");
        if (content == "" || content == null) {
            return;
        }
        var urls = "${basePath}/admin/AdminArticleRep.do?aid=${id }&id="+id+"&content="+content;
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

    </script>
</body>
</html>
