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
    <link href="//cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css"
          rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="//cdn.bootcss.com/metisMenu/1.1.3/metisMenu.min.css"
          rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/lk-vc/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="//cdn.bootcss.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${basePath}/page/util/paging.css">

    <!-- jQuery -->
    <script src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="/lk-vc/page/loadContent.js"></script>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
            width: 520px;
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
            /*             border-right: 1px solid black;
             */
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
    <script>
        $(function () {
            $(".btnShow").click(function () {
                /*  var str="我是弹出对话框";
                 $(".form").html(str); */
                $("#BgDiv").css({display: "block", height: $(document).height()});
                var yscroll = document.documentElement.scrollTop;
                $("#DialogDiv").css("top", "100px");
                $("#DialogDiv").css("display", "block");
                var id = $(this).data("id");
                $("#DialogDiv").data("id", id);
                document.documentElement.scrollTop = 0;
                $.get(BASE_DIR + "article/avail.do", {id: id}, function (data) {
                    selectedGrpOrUser = data.extra;
                })
            });
            $("#btnClose").click(function () {
                $("#BgDiv").css("display", "none");
                $("#DialogDiv").css("display", "none");
            });
        });
    </script>
</head>
<body style="background:#ffffff;">
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
                            <li><a href="${basePath}/admin/articleList.do" class="active"
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
                <h4 class="page-header" style="line-height:24px">话题列表
                    <span style="padding-left:50%;">
							                            <c:if test="${rootNumber!=4}">

                <div style="margin-left:40%;position: relative;">
                  <c:if test="${articleType==0}">
                     <select  id="op" onchange="extm()">

                            <option value="1">审核中</option>
                            <option value="0" selected="selected">已通过</option>
                            <option value="2">未通过</option>
                        </select></c:if>

                  <c:if test="${articleType==1}">
                     <select  id="op" onchange="extm()">

                            <option value="1" selected="selected">审核中</option>
                            <option value="0">已通过</option>
                            <option value="2">未通过</option>
                        </select>
						                  <input type="checkbox" id="testCheckbox">全选

						</c:if>

                  <c:if test="${articleType==2}">
                     <select  id="op" onchange="extm()">
                         
                            <option value="1">审核中</option>
                            <option value="0">已通过</option>
                            <option value="2" selected="selected">未通过</option>
                        </select></c:if>

<c:if test="${articleType==1}">
       <button onclick="pass(1)" id="p"
               style="display:Inline;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        批量通过
                    </button>
    <button onclick="pass(2)" id="p2"
            style="display:none;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        确认通过
                    </button>
    </c:if>



                    <button onclick="dep(1)" id="de"
                            style="display:Inline;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        批量删除
                    </button>
                    <button onclick="dep(2)" id="dep"
                            style="display:none;background:#ed5f00;color:white;border:0px;font-size:12px;height:30px;width:100px">
                        确认删除
                    </button>
					</c:if>
                    <div style="display: inline-block;">
                        <input type="text" id="se" style="height:24px;padding-right: 24px; "/><button onclick="seach()"
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
                            <th width="200px">标题</th>
                            <th>所属讨论区</th>
                            <th>收藏列表</th>
                            <th>点赞列表</th>
                            <th>回复列表</th>
                            <th>时间</th>
<c:if test="${rootNumber!=4}">
<th>操作</th>
</c:if>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${list}" var="list" varStatus="status">
                        <tbody>
                        <tr>
                            <c:forEach items="${idlist}" var="idlist1" begin="${status.index }" end="${status.index }"
                                       varStatus="s">
                                <td><input type="checkbox" id="${list.id }" value="${list.id }" style="display:none"
                                           name="cb"><c:out value='${idlist1}'/></td>
                            </c:forEach>
                            <c:forEach items="${user}" begin="${status.index }" end="${status.index }" var="u"
                                       varStatus="st">
                                <td>${u.nickname}</td>
                            </c:forEach>
                            <td width="150px"><c:out value='${list.title}'/></td>
                            <c:forEach items="${time}" begin="${status.index }" end="${status.index }" var="t"
                                                   varStatus="sts">
                            <td>${t.inGroup}</td>
                        </c:forEach>

                            <td><a href="${basePath}/admin/like.do?id=${list.id}">收藏列表</a></td>
                            <td><a href="${basePath}/admin/likeList.do?id=${list.id}">点赞列表</a></td>
                            <td><a href="${basePath}/admin/articleCommentList.do?id=${list.id}">回复列表</a></td>
                            <c:forEach items="${time}" begin="${status.index }" end="${status.index }" var="t"
                                       varStatus="sts">
                                <td>${t.time}</td>
                            </c:forEach>
							                            <c:if test="${rootNumber!=4}">

                            <td>

                                <c:if test="${list.top==null }">
                                    <a href="" onclick="toparticle(${list.id})">置顶</a>&nbsp;&nbsp;
                                </c:if>
                                <c:if test="${list.top!=null }">
                                    <a href="" onclick="toparticle(${list.id})">取消置顶</a>&nbsp;&nbsp;
                                </c:if>

                                <a href="${basePath}/admin/articleInfo.do?id=${list.id}">详情</a>&nbsp;&nbsp;<a href="#"
                                                                                                              onclick="del(${list.id})">删除</a>&nbsp;&nbsp;
                                <a href="${basePath}/article/export.do?id=${list.id}">导出</a>&nbsp;&nbsp;
                            <br/>
                                <a
                                        class="btnShow" href="#" data-id="${list.id}">权限</a>
                                <c:if test="${articleType==1}">
                                    <a onclick="at(0,${list.id})">通过</a>&nbsp;&nbsp;<a onclick="at(2,${list.id})">拒绝</a>
                                </c:if>
                            </td>
							</c:if>
                        </tr>
                        </tbody>

                        </c:forEach>
                        </tbody>
                    </table>

                    <c:if test="${type==1 }">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <div class="tcdPageCode">
                            </div>
                            <div id="dataTables-example_filter" class="dataTables_filter">
                                总页数:${count}选择跳转页:<input size="8" type="text" value="${nowpage}" id="to_page">
                                <button onclick="toPage();return false;" type="button" class="btn btn-default">确定
                                </button>
                            </div>
                            <br><br>
                            <!-- <ul class="pagination">

                            </ul> -->
                        </div>
                    </c:if>
                    <c:if test="${type==2 }">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <div class="tcdPageCode">
                            </div>
                            <div id="dataTables-example_filter" class="dataTables_filter">
                                总页数:${count}选择跳转页:<input size="8" type="text" value="${nowpage}" id="to_page">
                                <button onclick="toPage2();return false;" type="button" class="btn btn-default">确定
                                </button>
                            </div>
                            <br><br>
                            <!-- <ul class="pagination">

                            </ul> -->
                        </div>
                    </c:if>

                </div>
            </div>
        </div>

        <div id="BgDiv"></div>
        <div id="DialogDiv" style="display:none; width:550px;height:400px;">
            <h2><a href="#" id="btnClose">关闭</a></h2>
            <div>
                <div style="float:right;margin-right:10px;display:none">
                    <form id="filter" action="#">
                        <table style="display:none">
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;性别</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input name="sex"
                                                                                                    type="radio"
                                                                                                    value="0"/>男
                                    <input name="sex" type="radio" value="1"/>女
                                    <input name="sex" type="radio" value="-1"/>保密
                                    <!-- <input name="sex" type="radio"/>保密 --></td>
                            <tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;年龄</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:120px"><input name="ageS"
                                                                                                    type="text"
                                                                                                    style="width:50px"/>
                                    <input name="ageE" type="text" style="width:50px"/></td>
                            <tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;城市</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input name="city"
                                                                                                    type="text"
                                                                                                    style="width:103px"/>
                                </td>
                            <tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px">&nbsp;&nbsp;学历</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input name="degree"
                                                                                                    type="text"
                                                                                                    style="width:103px"/>
                                </td>
                            <tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px">&nbsp;&nbsp;婚否</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input name="marriage"
                                                                                                    type="radio"
                                                                                                    value="未婚"/>未婚
                                    <input name="marriage" type="radio" value="true"/>已婚<br/>
                                    <input name="marriage" type="radio" value=""/>保密
                                </td>
                            <tr>
                            <tr>
                                <td colspan="2"
                                    style="margin-left:200px;border:1px #CDCDC1 solid;height:27px;width:220px">&nbsp;&nbsp;<a
                                        href="#" onclick="return filterUser('#filter')">过滤</a></td>
                            <tr>
                        </table>
                    </form>
                </div>

                </form>
            </div>
            <div style="text-align: left;padding-left:220px">
                <ul id="list1">
                    <div style="/* border:1px #CDCDC1 solid; */width:200px;height:200px;overflow-Y:auto;">
                        <c:forEach items="${group}" var="list1" varStatus="status">
                            <li>&nbsp;
                                <c:choose>
                                    <c:when test="${list1.name=='未分组'}">

                                    </c:when>
                                    <c:otherwise>

                                        <input type="radio" name="rad" onclick="return loadContent(this)"
                                               value="<c:out value="${list1.id}"/>">
                                        <span><c:out value="${list1.name}"/></span>
                                    </c:otherwise></c:choose>
                            </li>
                        </c:forEach>
                    </div>
                </ul>
                <span style="float:right;">
          <!--  <div style="border:1px #CDCDC1 solid;width:185px;height:193px;padding-left:0px">
            <ul id="list2" >

            </ul>
            </div> -->
            </span>
            </div>
            <center>
                <button style="background:#ed5f00;color:white;border:0px;width:100px;height:40px;width:100px;margin-top:50px"
                        onclick="authoritySelection()">确定
                </button>
            </center>
        </div>
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
    <script src="${basePath}/page/util/paging.js"></script>

    <script type="text/javascript">
        function at(type,id) {
            var URL="${basePath}/admin/changArticle.do?type="+type+"&id="+id;
            $.ajax({
                url:URL,
                type: 'post',
                dataType: "json",
                success: function (data, textStatus) {
                    alert("操作成功");
                    location=location;
                },error :function () {
                    alert("操作成功");
                    location=location;
                }
            });
        }


function extm(){
    var myselect = document.getElementById("op");
    var index = myselect.selectedIndex;
    var val = myselect.options[index].value;
    location.href="${basePath}/admin/articleList.do?type="+val;
};


 $("#testCheckbox").change(function() {

            if(document.getElementById("testCheckbox").checked)
            {
                //全选
                <c:forEach  items="${list}"  var="list" varStatus="status">
                document.getElementById('${list.id}').checked=true;

                </c:forEach>;
            }else{
                <c:forEach  items="${list}"  var="list" varStatus="status">
               document.getElementById('${list.id}').checked=false;
                </c:forEach>;
            }

        });


        $(".tcdPageCode").createPage({
            pageCount: Number('${count}'),
            current: Number('${nowpage}'),
            backFn: function (p) {
                if ('${type }' == 2) {
                    location.href = "${basePath}/admin/articleSearch.do?key=${key}&wantpage=" + p;
                }
                if ('${type }' == 1) {
                    location.href = "${basePath}/admin/articleList.do?wantpage=" + p+"&type="+${articleType};
                }
            }
        });
        function dep(type) {

            if (type == 1) {
                var div2 = document.getElementById("de");
                div2.style.display = "none";
                var div3 = document.getElementById("dep");
                div3.style.display = "Inline";
                <c:forEach  items="${list}"  var="list" varStatus="status">
                var div4 = document.getElementById('${list.id}');
                div4.style.display = "Inline";
                </c:forEach>;
            }

            if (type == 2) {
                if (confirm('确定删除选中话题?') == false) {
                    return;
                }
                var value = "";
                var checks = document.getElementsByName("cb");
                for (var i = 0; i < checks.length; i++) {
                    if (checks[i].checked) {
                        value = checks[i].value;
                        var urls = "${basePath}/admin/article/delete.do?id=" + value;

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


        function pass(type) {

            if (type == 1) {
                var div2 = document.getElementById("p");
                div2.style.display = "none";
                var div3 = document.getElementById("p2");
                div3.style.display = "Inline";
                <c:forEach  items="${list}"  var="list" varStatus="status">
                var div4 = document.getElementById('${list.id}');
                div4.style.display = "Inline";
                </c:forEach>;
            }

            if (type == 2) {
                if (confirm('确定通过选中话题?') == false) {
                    var div2 = document.getElementById("p");
                    div2.style.display = "inline";
                    var div3 = document.getElementById("p2");
                    div3.style.display = "none";
                    <c:forEach  items="${list}"  var="list" varStatus="status">
                    var div4 = document.getElementById('${list.id}');
                    div4.style.display = "none";
                    </c:forEach>;

                    return;
                }
                var div2 = document.getElementById("p");
                div2.style.display = "inline";
                var div3 = document.getElementById("p2");
                div3.style.display = "none";
                <c:forEach  items="${list}"  var="list" varStatus="status">
                var div4 = document.getElementById('${list.id}');
                div4.style.display = "none";
                </c:forEach>;


                var value = "";
                var checks = document.getElementsByName("cb");
                for (var i = 0; i < checks.length; i++) {
                    if (checks[i].checked) {
                        value = checks[i].value;
                        var URL="${basePath}/admin/changArticle.do?type=0&id="+value;
                        $.ajax({
                            url:URL,
                            type: 'post',
                            dataType: "json",
                            success: function (data, textStatus) {

                            },error :function () {
                            }
                        });
                    }
                }


                alert("操作成功");
                location=location;

        }
        }






        function add() {
            location.href = "${basePath}/page/kindeditor-master/jsp/demo.jsp";
        }
        var nowpage = 1;
        var wantpage = 1;
        var pagecount =${count};
        function s() {
            alert("已是第一页");
        }
        function next() {
            alert("已是最后一页");
        }
        function toPage() {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > pagecount) {
                alert("超出范围！");
            } else {
                load(topage);
                nowpage = topage;
            }

        }
        function toPage2() {
            var topage = $("#to_page").val();
            if (isNaN(topage)) {
                alert("请输入数字！");
            } else if (topage <= 0) {
                alert("输入不合法！");
            } else if (topage > pagecount) {
                alert("超出范围！");
            } else {
                location.href = "${basePath}/admin/articleSearch.do?key=${key}&wantpage=" + topage;
            }

        }
        function load(wantpage) {
            var urls = "${basePath}/admin/articleList.do?wantpage=" + wantpage+"&type="+${articleType};
            nowpage = wantpage;
            location.href = urls;
        }
        function toparticle(id) {
            var urls = "${basePath}/admin/article/top.do?id=" + id;
            $.ajax({
                url: urls,
                type: 'post',
                dataType: "json",
                success: function (data, textStatus) {
                    alert("操作成功");
                    var urls = "${basePath}/admin/articleList.do?wantpage=${nowpage}";
                }
            });
        }
        function del(id) {
            if (confirm('确定删除该话题?') == false) {
                return;
            }
            var urls = "${basePath}/admin/article/delete.do?id=" + id;

            $.ajax({
                url: urls,
                type: 'post',
                dataType: "json",
                success: function (data, textStatus) {
                    alert("删除成功");
                    location.href = "${basePath}/admin/articleList.do?wantpage=${nowpage}&type=${articleType}";
                }
            });
        }
        function seach() {
            var seach = $("#se").val();
            location.href = "${basePath}/admin/articleSearch.do?key=" + seach;
        }
        function authoritySelection() {
            setGroupInfo("#list1");
            var id = $("#DialogDiv").data("id");
            var url = BASE_DIR + "admin/article/setGroupId.do";
            $.post(url, {articleId: id, grpId: selectedGrpOrUser.group[0]}, function (data) {
                if (data.message == "success") {
                    alert("操作成功");
                    location = "${basePath}/admin/articleList.do?wantpage=${nowpage}";
                } else {
                    alert(data.message);
                }
            });
        }
    </script>
</div>
</body>
</html>
