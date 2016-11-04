<%@ page import="com.rofour.baseball.controller.model.UserModel" %>
<%@ page import="com.rofour.baseball.dao.user.bean.UserManagerLoginBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function getChlidrenMenu(menuId){
		if("false" == $("#menu_"+menuId).attr("upload")){
			$.post("/getChlidrenMenu",{"menuId":menuId},function(data){
				var chtml = '<ul class="submenu" style="display: block;">';
				if(null != data && '' != data){
					for(var i=0; i<data.length; i++){
						chtml += '<li><a href="#" tabhref="<%=request.getContextPath()%>' + data[i].menuCtrlPath + '" tabmodule="" onclick="openTab(this)">' + data[i].caption + '</a></li>';
					}
				}
				chtml += '</ul>';
            	$("#menu_"+menuId).append(chtml);
				$("#menu_"+menuId).attr("upload", "true");
			},"json");
		}
	}
</script>
<div id="nav-col">
    <section id="col-left" class="col-left-nano">
        <div id="col-left-inner" class="col-left-nano-content">
            <div id="user-left-box" class="clearfix hidden-sm hidden-xs">
                <img alt=""
                     src="<%=request.getContextPath()%>/img/samples/scarlet-159.png"/>
                <div class="user-box">

					<span class="name"> <%=((UserManagerLoginBean) session.getAttribute("user"))
                            .getShowName()%><br/>
					</span> <span class="status"> <i class="fa fa-circle"></i> 在线
					</span>
                </div>
            </div>
            <div class="collapse navbar-collapse navbar-ex1-collapse"
                 id="sidebar-nav">
                <ul class="nav nav-pills nav-stacked">
                    <li class="active"><a href="#"> <i
                            class="fa fa-dashboard"></i> <span>主页</span>
                    </a></li>
                    <c:forEach items="${menuList}" var="menu" varStatus="status">
                        <shiro:hasAnyRoles name="${menu.roleNameAll}">
                            <li id="menu_${menu.menuId}" upload="false"><a href="#" class="dropdown-toggle" onclick="getChlidrenMenu('${menu.menuId}');"> <i
                                    class="${menu.picFile}"></i> <span>${menu.caption}</span> <i
                                    class="fa fa-chevron-circle-right drop-icon"></i>
                            </a>
                                <!-- <ul class="submenu">
                                    <c:forEach items="${menu.chlidrenMenu}" var="child"
                                               varStatus="status">
                                        <shiro:hasAnyRoles name="${child.roleNameAll}">
                                            <li><a href="#" tabhref="<%=request.getContextPath()%>${child.menuCtrlPath}" tabmodule="" onclick="openTab(this)"
                                                   <c:if test="${child.chlidrenMenu.size()>0}">class="dropdown-toggle" </c:if>>
                                                    ${child.caption}
                                                <c:if test="${child.chlidrenMenu.size()>0}">
                                                    <i class="fa fa-chevron-circle-right drop-icon"></i>
                                                </c:if>
                                            </a> <c:if test="${child.chlidrenMenu.size()>0}">
                                                <ul class="submenu" style="display: block;">
                                                    <c:forEach items="${child.chlidrenMenu}" var="three"
                                                               varStatus="status">
                                                        <li><a href="#" tabhref="<%=request.getContextPath()%>${three.menuCtrlPath}">
                                                                ${three.caption} </a></li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if></li>
                                        </shiro:hasAnyRoles>
                                    </c:forEach>
                                </ul> -->
                            </li>
                        </shiro:hasAnyRoles>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </section>
</div>
<div id="content-wrapper">