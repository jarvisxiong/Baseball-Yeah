<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/comm.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>爱学派</title>

    <script
            src="<%=request.getContextPath()%>/js/extensions/export/bootstrap-table-export.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/axp/index.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/axp/alluser.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/DataTables-1.10.11/media/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/axp/base.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/JSON-js-master/json_parse.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/plugs/blockui-master/jquery.blockUI.js"></script>

    <link
            href="<%=request.getContextPath()%>/plugs/sweet-alert/css/sweet-alert.css"
            rel="stylesheet">
    <script
            src="<%=request.getContextPath()%>/plugs/sweet-alert/js/sweet-alert.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $('.theme-poptit .close').click(function () {
                $('#cover').fadeOut(100);
                $('#form-add').slideUp(200);
            })
        });
        function openDialog() {
            var scrollHeight = $(document).scrollTop();
            var posiTop = scrollHeight - 150;
            $('#form-add').css('top', posiTop).slideDown(200);
            $("#cover").fadeIn(100);
        }

        function closeDialog() {

        }
    </script>

</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div id="page-wrapper" class="container">
    <div class="row">
        <jsp:include page="../common/baseLeft.jsp"></jsp:include>
        <jsp:include page="../common/footer.jsp"></jsp:include>

        <form action="">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="main-box clearfix">
                                <div class="main-box-body clearfix">
                                    <div class="table-responsive">
                                        <table id="userTable" class="table">
                                            <thead>
                                            <tr>
                                                <th><a href="#"><span>用户名</span></a></th>
                                                <th class="text-right"><a href="#" class="asc"><span>是否被删除</span></a>
                                                </th>
                                                <th class="text-center"><span>是否实名认证</span></th>
                                                <th class="text-center"><span>性别</span></th>
                                                <th class="text-center"><a href="#" class="desc"><span>注册时间</span></a>
                                                </th>
                                                <th>&nbsp;</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${users}" var="user" varStatus="status">
                                                <tr>
                                                    <td>${user.userName}</td>
                                                    <td class="text-right"><c:if
                                                            test="${user.beDeleted ==1}">
                                                        已删除
                                                    </c:if> <c:if test="${user.beDeleted ==0}">
                                                        可用
                                                    </c:if></td>
                                                    <c:choose>
                                                        <c:when test="${user.verifyStatus==1}">
                                                            <td class="text-center"><a
                                                                    class="label label-success"
                                                                    onclick="verify(${user.userId},'0');">已实名认证 </a>
                                                            </td>
                                                        </c:when>
                                                        <c:when test="${user.verifyStatus==0}">
                                                            <td class="text-center"><a
                                                                    class="label label-warning"
                                                                    onclick="verify(${user.userId},'1');">未实名认证</a></td>
                                                        </c:when>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${user.gender=='p_gender_female'}">
                                                            <td class="text-center"><span>女</span></td>
                                                        </c:when>
                                                        <c:when test="${user.gender=='p_gender_male'}">
                                                            <td class="text-center"><span>男</span></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-center"><span></span></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td class="text-center"><fmt:formatDate
                                                            value="${user.signupTime}" type="both"
                                                            pattern="yyyy.MM.dd HH:mm:ss"/></td>
                                                    <td style="width: 15%;"><a href="#"
                                                                               class="table-link"> <span
                                                            class="fa-stack"> <i
                                                            class="fa fa-square fa-stack-2x"></i> <i
                                                            class="fa fa-search-plus fa-stack-1x fa-inverse"
                                                            onclick="reloadDataTable();"></i>
																</span>
                                                    </a> <a href="#" class="table-link"> <span
                                                            class="fa-stack"> <i
                                                            class="fa fa-square fa-stack-2x"></i> <i
                                                            class="fa fa-pencil fa-stack-1x fa-inverse"
                                                            onclick="openDialog();"></i>
																</span>
                                                    </a> <c:if test="${user.beDeleted ==0}">
                                                        <a href="#" class="table-link danger"> <span
                                                                class="fa-stack" onclick="cancelUser(${user.userId});">
																			<i class="fa fa-square fa-stack-2x"></i> <i
                                                                class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
																	</span>
                                                        </a>
                                                    </c:if></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

    </div>
</div>
</div>
<jsp:include page="../common/tools.jsp"></jsp:include>

<jsp:include page="editUser.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/js/demo-skin-changer.js"></script>

</body>
</html>
