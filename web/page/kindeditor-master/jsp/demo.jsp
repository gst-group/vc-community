<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<%@ include file="../../base.jsp" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Admin 2 - Bootstrap Admin Theme</title>
    <link rel="stylesheet" href="${basePath}/page/kindeditor-master/themes/default/default.css"/>
    <%-- 	<link rel="stylesheet" href="${basePath}/lk-vc/page/kindeditor-master/plugins/code/prettify.css" />
     --%>
    <script charset="utf-8" src="${basePath}/page/kindeditor-master/kindeditor-all.js"></script>
    <script charset="utf-8" src="${basePath}/page/kindeditor-master/lang/zh-CN.js"></script>
    <script charset="utf-8" src="${basePath}/page/kindeditor-master/plugins/code/prettify.js"></script>
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/page/loadContent.js"></script>
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
            width: 153px;
            display: inline-block;
            max-height: 180px;
            overflow-x: hidden;
        }
    </style>
    <script>
        var editor1;
        KindEditor.ready(function (K) {
            editor1 = K.create('textarea[name="content1"]', {
                cssPath: '${basePath}/page/kindeditor-master/plugins/code/prettify.css',
                uploadJson: '${basePath}/page/kindeditor-master/jsp/upload_json.jsp',
                fileManagerJson: '${basePath}/page/kindeditor-master/jsp/file_manager_json.jsp',
                allowFileManager: true,
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                        document.forms['example'].submit();

                    });
                    K.ctrl(self.edit.doc, 13, function () {

                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
            var x = document.getElementById("hid");
            if (x.innerHTML.trim() == null || x.innerHTML.trim() == "") {
            } else {
                save();
            }

            prettyPrint();
        });

        function save() {

            var a = $("#tit").val();
            if (a.length == 0) {
                alert("标题不符合要求");
                return;
            }
            if (editor1.text().length <= 0) {
                alert("内容不符合要求");
                return;
            }
            setGroupInfo("#list1");
            if (selectedGrpOrUser.group == 0) {
                alert("请选择讨论区");
                return;
            } else {
                document.getElementById("subfb").setAttribute("disabled", true);
            }
            //存入标题radio
            var rad=$('input[name="rad"]:checked ').val();
            var urls = "${basePath}/admin/article/new.do";
            var sendContent = {
                title: a,
                availUserOrGrp: JSON.stringify(selectedGrpOrUser),
                content: editor1.html(),
                gid: rad
            };

            $.ajax({
                url: urls,
                type: 'post',
                data: sendContent,
                success: function (data, textStatus) {
                    if (data.message == "success") {
                        document.getElementById("subfb").setAttribute("disabled", true);
                        alert("发布成功");
                        location.href = "${basePath}/admin/articleList.do";
                    } else {
                        alert(data.message);
                    }

                }
            });
        }
        function no() {
            //关闭弹窗
            $("#btnClose").click();
//            var x = document.getElementsByName("rad");  //获取所有name=brand的元素
//            for (var i = 0; i < x.length; i++) { //对所有结果进行遍历，如果状态是被选中的，则将其选择取消
//                if (x[i].checked == true) {
//                    x[i].checked = false;
//                }
//            }
        }
    </script>
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

</head>
<body>
<div id="hid" style="display:none">
    <%=htmlData%>
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
                        <ul class="nav nav-second-level collapse">
                            <li><a href="${basePath}/admin/articleList.do">话题列表</a></li>
                            <li><a href="${basePath}/admin/articleAddView.do" class="active"
                                   style="color:white;background-color:#DEDEDE">话题发布</a></li>
                            <li ><a href="${basePath}/admin/allCommentArticle.do">评论列表</a></li>
                            <li>
                                <a href="${basePath}/admin/AlldeleteList.do">删除列表</a></li>
                        </ul>
                    </li>
<c:if test="${articleRoole==1}">                    <c:if test="${visible==0}">

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

    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <h3 class="page-header">话题发布</h3>
                <input type="text" id="tit" style="width:1000px;height:40px;font-size:18px" placeholder="请输入标题"></p>
                <form name="example" method="post" id="myfrom">
                    <textarea name="content1" cols="100" rows="15" style="width:1000px;height:400px;visibility:hidden;"
                              id="textarea"><%-- <%=htmlspecialchars(htmlData)%> --%></textarea>
                    <br/>
                    <!--  name="button" onclick="s()" class="but" type="button" id="btnShow" class="btn btn-default" id="save_jobs" -->

                    <div id="BgDiv"></div>


                    <input class="but" type="button"
                           style="background:#ed5f00;color:white;border:0px;width:100px;height:40px;margin-left:30%"
                           id="btnShow"
                           class="btn btn-default" id="save_jobs" value="发&nbsp&nbsp;布"/>

                    <input class="but" type="button"
                           style="background:#ed5f00;color:white;border:0px;width:100px;height:40px;margin-left:15%"

                           class="btn btn-default" onclick="saveDrf()" value="存草稿"/>
                </form>
            </div>
            <div id="DialogDiv" style="display:none; width:530px;height:430px">
                <h2><a href="#" id="btnClose">关闭</a></h2>
                <div style="float:right;margin-right:10px;display:none">
                    <form id="filter">
                        <table>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;性别</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input name="sex"
                                                                                                    type="radio"
                                                                                                    value="0"/>男
                                    <input name="sex" type="radio" value="1"/>女
                                    <input name="sex" type="radio" value="-1"/>保密
                                    <!--  <input name="sex" type="radio"/>保密 --></td>
                            </tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;年龄</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:120px"><input name="ageS"
                                                                                                    type="text"
                                                                                                    style="width:50px"/>
                                    <input name="ageE" type="text" style="width:50px"/></td>
                            </tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:80px">&nbsp;&nbsp;城市</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input name="city"
                                                                                                    type="text"
                                                                                                    style="width:103px"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px">&nbsp;&nbsp;学历</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input
                                        name="degree" type="text" style="width:103px"/></td>
                            </tr>
                            <tr>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px">&nbsp;&nbsp;婚否</td>
                                <td style="border:1px #CDCDC1 solid;height:30px;width:100px"><input
                                        name="marriage" type="radio" value="未婚"/>未婚
                                    <input name="marriage" type="radio" value="true"/>已婚<br/>
                                    <input name="marriage" type="radio" value=""/>保密
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" style="border:1px #CDCDC1 solid;height:27px;width:220px">&nbsp;&nbsp;<a
                                        href="#" onclick="return filterUser('#filter')">过滤</a></td>
                            </tr>
                        </table>
                    </form>


                </div>
                <div style="text-align: left;margin-left:200px">
                    <!-- <ul id="list1">
                        <table width="120px"> -->
                    <div id="list1"
                         style="width:200px;float:left;height:200px;overflow-Y: scroll;/* border:1px #CDCDC1 solid; */">
                        <c:forEach items="${group}" var="list1" varStatus="status">
                            <!-- <tr style="border:1px #CDCDC1 solid;height:32px">
                            <td>

                            <li> -->
                            <div> &nbsp;&nbsp;
                           
                            <c:choose>
                            <c:when test="${list1.name=='未分组'}">
                            
                            </c:when>
                            <c:otherwise>
                            <input type="radio" name="rad" onclick="return loadContent(this)"
                                       value="<c:out value="${list1.id}"/>">
                                <c:out value="${list1.name}"/>
                            </c:otherwise>
                            </c:choose>
                            
                                
                            </div>
                            <!-- </li>
                            </td>
                            </tr> -->
                        </c:forEach>
                    </div>
                    <!--  </table>
                 </ul> -->
                    <span style="float:right;">
           <div style="border:1px #CDCDC1 solid;width:155px;height:193px;display:none">
            <ul id="list2">

            </ul>
            </div>
            </span>
                </div>
                <center>
                    <button id="subfb" type="submit"
                            style="background:#ed5f00;color:white;border:0px;width:100px;height:40px;width:100px;margin-top:250px;margin-left:-350px"
                            onclick="return save()">确定
                    </button>
                    <button id="subfb" type="reset"
                            style="background:#ed5f00;color:white;border:0px;width:100px;height:40px;width:100px;margin-top:250px;margin-left:100px"
                            onclick="no()">取消
                    </button>
                </center>
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
    <script src="${basePath}/js/sb-admin-2.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="http://121.40.137.214:80/statics//bootstrap/js/bootbox.js"></script>

</div>
<script>
    function toPage() {
        var topage = $("#to_page").val();
        if (isNaN(topage)) {
            alert("请输入数字！");
        } else if (topage <= 0) {
            alert("输入不合法！");
        } else if (topage > pagecount) {
            alert("超出范围！");
        } else {
            location.href = "${basePath}/admin/articleAddView.do?wantpage=" + topage;


        }

    }
    ;

    function setCookie(name, value, iDay) {
        var oDate = new Date();

        oDate.setDate(oDate.getDate() + iDay);

        document.cookie = name + '=' + encodeURIComponent(value) + ';expires=' + oDate;
    }

    function getCookie(name) {
        var arr = document.cookie.split('; ');
        var i = 0;
        for (i = 0; i < arr.length; i++) {
            //arr2->['username', 'abc']
            var arr2 = arr[i].split('=');

            if (arr2[0] == name) {
                var getC = decodeURIComponent(arr2[1]);
                return getC;
            }
        }

        return '';
    }

    function removeCookie(name) {
        setCookie(name, '1', -1);
    }
    <%-- $
    .ajax({
        url : "${basePath}/admin/add.do?title="+title+"&content="+<%=htmlData%>,
        type : 'post',
        dataType : "json",
        success : function(data, textStatus) {


            }
        });
} --%>
    //显示草稿
     window.onload = function() {
         var sendContent = {
             userId: ${sessionScope.adminUser.id},
             type:1
         };
         $.ajax({
             url:"${basePath}/user/selectDraft.do",
             data:sendContent,
             type:"post",
             dataType:"json",
             success:function (data) {
                 if(data.message=="success"){
                     if(data.extra!=null){
                         $("#tit").val(data.extra.title);
                         editor1.html(data.extra.content);
                     }
                 }
             },error:function (data) {

             }
         });
     }
    /**存草稿
     *
     */
    function saveDrf(){
        var sendContent = {
            title:$("#tit").val(),
            content:editor1.html(),
            userId: ${sessionScope.adminUser.id},
            type:1
        };
        $.ajax({
           url:"${basePath}/user/insertDraft.do",
            data:sendContent,
            type:"post",
        dataType:"json",
            success:function (data) {
                if(data.message=="success"){
                    alert("保存成功");
                }
            },error:function (data) {
                alert(data.message);
            }
        });
    }
</script>
</body>
</html>

<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
%>