<%@page pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务设置--服务名称维护</title>
<script src="<%=request.getContextPath()%>/javascripts/common/validation.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/css/system-manage.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/javascripts/common/common.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/My97DatePicker/WdatePicker.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery-ui-1.7.2.custom.min.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/accordion.js"></script>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery.cookie.js"></script>
<style type="text/css">
.index_hid_css {
   overflow:hidden;
   word-break:keep-all;
   white-space:nowrap;
   text-overflow:ellipsis;
}
</style>
</head>
<body onload="beforeInit()">
<div class="top">
	<div class="top-left"><img src="<%=request.getContextPath()%>/images/logo.png" width="152" height="88" alt="中国建筑平台"/></div>
    <div class="top-right">xxx&nbsp;&nbsp;中国建筑猎头平台欢迎您！</div>
</div>
<div class="nav">
	<ul>
    	<li class="nav-on" id="menu01" onmouseover="changeMenu(this)"><a href="../../index.html">首页</a></li>
        <li class="nav-on" id="menu02" onmouseover="changeMenu(this)"><a href="../headhunterCenter/head.html">猎头中心</a></li>
        <li class="nav-on" id="menu03" onmouseover="changeMenu(this)"><a href="../applyJobCenter/people.html">人才中心</a></li>
        <li class="nav-on" id="menu04" onmouseover="changeMenu(this)"><a href="../peopleExcavate/excavate.html">人才挖掘</a></li>
        <li class="nav-out" id="menu05" onmouseover="changeMenu(this)"><a href="../certificateCenter/certificate.html">猎证中心</a></li>
        <li class="nav-on" id="menu08" onmouseover="changeMenu(this)"><a href="../innovationSalon/salon.html">创业沙龙</a></li>
    </ul>
</div>
