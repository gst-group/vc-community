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
    <link rel="stylesheet" type="text/css" href="${basePath}/page/util/paging.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>

        function load(wantpage) {
            location.href = "${basePath}/admin/diaryList.do?wantpage=" + wantpage;

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
                <h4 class="page-header">日记列表<span style="line-height:24px">
         <div style="margin-left:300px;position: relative;">
              <div class="row" style="float: left">
                <form name="diaryShownForm">
                    <label>隐藏日记</label>
                    <select id="setShown"  style="display: none"name="setShown" required onchange="changeShownData(this)">
                        <option value="WEB" data-value="${showOnWeb}" checked>WEB</option>
                        <option value="IOS" data-value="${showOnIOS}">IOS</option>
                        <option value="ANDROID" data-value="${showOnAndroid}">ANDROID</option>
                    </select>
                    <input type="radio" value="1" name="isShow" id="showTrue">显示
                    <input type="radio" value="0" name="isShow" id="showFalse">隐藏
                  <button type="button" onclick="return updateShown();" style="display:Inline;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">提交</button>
                </form>
            </div>
           <select onchange="Change()" id="op" style="height:22px;font-size:12px;margin-left: 50px">
        <c:if test="${type==1 }">
            <option value="1">时间排序</option>
            <option value="2">回复排序</option>
            <option value="3">首字母排序</option>
        </c:if>
        <c:if test="${type==2 }">
            <option value="2">回复排序</option>
            <option value="1">时间排序</option>
            <option value="3">首字母排序</option>


        </c:if>
        <c:if test="${type==3 }">
            <option value="3">首字母排序</option>
            <option value="1">时间排序</option>
            <option value="2">回复排序</option>
        </c:if>
        
       
        <c:if test="${type==4 }">
            <option value="1">时间排序</option>
            <option value="2">回复排序</option>
            <option value="3">首字母排序</option>
        </c:if>
                 </select>
                    <button onclick="dep(1)" id="de"
                            style="display:Inline;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        批量删除
                    </button>
                    <button onclick="dep(2)" id="dep"
                            style="display:none;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        确认删除
                    </button>
                      
                   
                   
                    <div style="display: inline-block;">
                        <input type="text" id="seach" style="height:24px;padding-right: 24px; "/><button onclick="se()"
                                style="width:24px;height:24px;background-image:url('${basePath}/img/search.png');border:none;vertical-align: top;margin-left: -24px;"></button>
              
                    </div>
                </div>
        
        
        </span></h4>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户名</th>
                            <th>点赞列表</th>
                            <th>时间</th>
                            <th>点赞数</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${diarylist}" var="list1" varStatus="status">
                        <tbody>
                        <tr>
                            <c:forEach items="${idlist}" begin="${status.index }" end="${status.index }" var="idlist1"
                                       varStatus="ss">
                                <td><input type="checkbox" value="${list1.id }" name="cb" id="${list1.id }"
                                           style="display:none"><c:out value='${idlist1}'/></td>
                            </c:forEach>

                            <c:forEach items="${list}" begin="${status.index }" end="${status.index }" var="u"
                                       varStatus="status">
                                <td>${u.name}</td>
                                <td><a href="${basePath}/admin/diaryPraise.do?id=${list1.id}">点赞列表</a></td>
                                <td>${u.time }</td>

                            </c:forEach>
                            <td><c:out value='${list1.followCount }'/></td>
                            <td><a href="${basePath}/admin/diaryCommentList.do?id=${list1.id}">回复列表</a>
                                    <%--    &nbsp;&nbsp;<a href="" onclick="diaryTop(${list1.id})">置顶</a> --%>
                                &nbsp;&nbsp;<a href="${basePath}/admin/diaryInfo.do?id=${list1.id}">详情</a>&nbsp;&nbsp;<a
                                        href="#" onclick="del(${list1.id})">删除</a>&nbsp;&nbsp;<a
                                        href="${basePath}/diary/export.do?id=${list1.id}">导出</a></td>
                        </tr>
                        </tbody>
                        </c:forEach>
                        </tbody>
                    </table>

                    <c:if test="${type!=4 }">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <div class="tcdPageCode">
                            </div>
                            <div id="dataTables-example_filter" class="dataTables_filter">
                                总页数:${count}选择跳转页:<input size="8" type="text" value="${nowpage}" id="to_page">
                                <button onclick="toPage(order1=${type})" type="button" class="btn btn-default">确定
                                </button>
                            </div>
                            <br><br>
                            <!-- <ul class="pagination">

                            </ul> -->
                        </div>
                    </c:if>
                    <!-- 搜索页码 -->
                    <c:if test="${type==4 }">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <div class="tcdPageCode">
                            </div>
                            <div id="dataTables-example_filter" class="dataTables_filter">
                                总页数:${count}选择跳转页:<input size="8" type="text" value="${nowpage}" id="to_page">
                                <button onclick="toPage(4)" type="button" class="btn btn-default">确定</button>
                            </div>
                            <br><br>
                            <!-- <ul class="pagination">

                            </ul> -->
                        </div>
                    </c:if>
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
    <script src="${basePath}/page/util/paging.js"></script>

    <script type="text/javascript">
        $(".tcdPageCode").createPage({
            pageCount: Number('${count}'),
            current: Number('${nowpage}'),
            backFn: function (p) {
                if ('${type}' != 4) {
                    location.href = "${basePath}/admin/diaryList.do?order1=${type}&wantpage=" + p;
                } else {
                    location.href = "${basePath}/admin/diarySearch.do?key=${key }&wantpage=" + p;
                }
            }
        });

        function dep(type) {

            if (type == 1) {
                var div2 = document.getElementById("de");
                div2.style.display = "none";
                var div3 = document.getElementById("dep");
                div3.style.display = "Inline";
                <c:forEach  items="${diarylist}"  var="list1" varStatus="status">
                var div4 = document.getElementById('${list1.id}');
                div4.style.display = "Inline";
                </c:forEach>;
            }

            if (type == 2) {
                if (confirm('确定删除选中日记?') == false) {
                    return;
                }
                var value = "";
                var checks = document.getElementsByName("cb");
                for (var i = 0; i < checks.length; i++) {
                    if (checks[i].checked) {
                        value = checks[i].value;
                        var urls = "${basePath}/admin/diary/delete.do?id=" + value;
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
                    location = location;
                }


            }

        }

        function Change() {
            var wp = '${nowpage}';
            var myselect = document.getElementById("op");
            var index = myselect.selectedIndex;
            val = myselect.options[index].value;
            location.href = "${basePath}/admin/diaryList.do?wantpage=" + wp + "&order1=" + val;
        }


        var nowpage = 1;
        var wantpage = 1;
        var pagecount =${count};
        function toPage(type) {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > pagecount) {
                alert("超出范围！");
            } else {
                if (type == 4) {
                    location.href = "${basePath}/admin/diarySearch.do?key=${key }&wantpage=" + topage;

                } else {
                    location.href = "${basePath}/admin/diaryList.do?wantpage=" + topage + "&order1=" + type;

                }
            }

        }
        ;
        function tops() {
            alert("已是第一页");
        }
        ;
        function nextx() {
            alert("已是最后一页");
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
        function updateShown() {
          var formdata = new FormData(document.diaryShownForm);
          $.ajax({
            url: "${basePath}/admin/updateDiaryShow.do",
            type: 'post',
            data: formdata,
            processData: false,
            contentType: false ,
            success: function (data, textStatus) {
              if (data == "success") {
                alert("成功");
              }
            }
          });
          return false;
        }
        function changeShownData(ele) {
          var value = $(ele).find('*:selected').data('value');
          var showTrue = $('#showTrue');
          var showFalse = $('#showFalse');
          if(value){
            showFalse.prop("checked",false);
            showTrue.prop("checked","checked");
          }else{
            showTrue.prop("checked",false);
            showFalse.prop("checked","checked");
          }
        }
        changeShownData("#setShown");

        function del(id) {
            if (confirm('确定删除该日记?') == false) {
                return;
            }
            var urls = "${basePath}/admin/diary/delete.do?id=" + id;
            $.ajax({
                url: urls,
                type: 'post',
                dataType: "json",
                success: function (data, textStatus) {
                    alert("删除成功");
                    location.href = "${basePath}/admin/diaryList.do";
                }
            });
        }
        function se() {
            var seach = $("#seach").val();
            location.href = "${basePath}/admin/diarySearch.do?key=" + seach;
        }
        function diaryTop(id) {
            var urls = "${basePath}/diary/top.do?id=" + id;
            $.ajax({
                url: urls,
                type: 'post',
                dataType: "text",
                success: function (data, textStatus) {
                    if (data = "success") {
                        alert("置顶成功");
                    } else {
                        alert("网络错误");
                    }
                    history.go(0);
                }
            });
        }
    </script>
</body>
</html>
