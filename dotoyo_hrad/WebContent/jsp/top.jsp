<%@page pageEncoding="utf-8"%>
<%@page import="com.dotoyo.buildjob.sys.dto.SysUserDto"%>
<%@page import="com.dotoyo.buildjob.common.constant.Constants"%>
<%@page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<%@page import="com.dotoyo.buildjob.common.util.PropertiesValue"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	Object obj = request.getSession().getAttribute(Constants.SESSION_USER);
	String frontRootPath = String.valueOf(PropertiesValue.getPropertyValue(ApplicationConstant.FRONT_HTTP_PATH));
	String userName = "";
	if(obj != null && obj instanceof SysUserDto){
		SysUserDto user = (SysUserDto)obj;
		userName = user.getUserName();
	}
%>
<style>
body{
	overflow-y:scroll;
	*overflow-y:auto!important;
	*overflow-y:auto;
	}
</style>
<body onload="beforeInit()">
<div class="top">
	<div class="top-left"><img src="<%=request.getContextPath()%>/images/logo.png" width="152" height="88" alt="中国建筑平台"/></div>
    <div class="top-right"><span style="color:#f60"><%=userName %></span>&nbsp;&nbsp;中国建筑猎头平台欢迎您！</div>
</div>
<div class="nav">
	<ul>
    	<li class="nav-on" id="menu01" onmouseover="changeMenu(this)"><a target="_blank" href="<%=frontRootPath %>/homePage/homePageAction!init.action">首页</a></li>
        <li class="nav-on" id="menu02" onmouseover="changeMenu(this)"><a target="_blank" href="<%=frontRootPath %>/headhunterCenter/headhunterCenterAction!initIndex.action">猎头中心</a></li>
        <li class="nav-on" id="menu03" onmouseover="changeMenu(this)"><a target="_blank" href="<%=frontRootPath %>/applyJobCenter/applyJobCenter!initIndex.action">人才中心</a></li>
        <li class="nav-on" id="menu04" onmouseover="changeMenu(this)"><a target="_blank" href="<%=frontRootPath %>/peopleExcavate/peopleExcavateAction!initIndex.action">人才挖掘</a></li>
        <li class="nav-on" id="menu05" onmouseover="changeMenu(this)"><a target="_blank" href="<%=frontRootPath %>/certificateCenter/certificateAction!initIndex.action">猎证中心</a></li>
        <li class="nav-on" id="menu08" onmouseover="changeMenu(this)"><a target="_blank" href="<%=frontRootPath %>/innovationSalon/salonAction!initIndex.action">创业沙龙</a></li>
    </ul>
</div>
