<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理</title>
	<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<style>
form{margin:0px;padding:0px;}
</style>
<body>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
   <div class="content-right">
    	<div class="administrator-title"><span>今日提醒</span></div>
        <div class="administrator-font">
        	<ul>
            	<li>新增注册个人用户：<em><a href="<s:url action="sysMember!init.action">
            		 <s:param name="memberType">0</s:param>
            		 <s:param name="isNew">Y</s:param>
            	</s:url>"><s:property value="individualMember"/></a></em>位</li>
                <li>新增注册企业用户：<em><a href="<s:url action="sysMember!init.action">
                	<s:param name="memberType">1</s:param>
                	<s:param name="isNew">Y</s:param>
                </s:url>"><s:property value="corporateMember"/></a></em>位</li>
                <li>新增发布招聘职位：<em><a href="<s:url action="jobSideManagementAction!companiesJobManage.action?jobSearchVo.isNew=Y"></s:url>"><s:property value="releasedNumber"/></a></em>个</li>
                <li>新增发布求职意向：<em><a href="<s:url action="applyJobManageAction!viewApplyJobIntention4Admin.action?isNew=Y"></s:url>"><s:property value="publishedAbroad"/></a></em>份</li>

                <li>新增企业证书需求提交：<em><a href="<s:url action="certNeedsManageAction!viewAllCertNeeds4Admin.action?isNew=Y"></s:url>"><s:property value="certificateRequirements"/></a></em>条</li>
                <li>新增个人证书信息提交：<em><a href="<s:url action="personalCertManageAction!viewPersonalCertList4Admin.action?isNew=Y"></s:url>"><s:property value="personalCertificate"/></a></em>条</li>
                <li>新增线下推荐人才：<em><a href="<s:url action="lineTalentPoolAction!loadLineTalentPool.action?isNew=Y"></s:url>"><s:property value="newLineRecommendedPersonnels"/></a></em>位</li>

                <li>新增文章：<em><a href="<s:url action="salonAdminAction!toSalonArticleManage.action?isNew=Y"></s:url>"><s:property value="newArticles"/></a></em>篇</li>
                <li>新增订单：<em><a href="<s:url action="sysOrder!init.action?isNew=Y"></s:url>"><s:property value="newOrders"/></a></em>份</li>
            </ul>
        </div>

        <div class="system-botton2">
        	<ul>
                <li class="botton1"><a href="<s:url action="sysMember!init.action"></s:url>" title="会员管理">会员管理</a></li>
                <li class="botton2"><a href="<s:url action="salonAdminAction!toSetSalonPage.action"></s:url>" title="经典沙龙设置">经典沙龙设置</a></li>
                <li class="botton3"><a href="<s:url action="salonAdminAction!toCommentManagePage.action"></s:url>" title="评论管理">评论管理</a></li>
                <li class="botton3"><a href="<s:url action="sysOrder!init.action"></s:url>" title="订单管理">订单管理</a></li>
                <li class="botton1"><a href="<s:url action="salonAdminAction!toSalonArticleManage.action"></s:url>" title="文章管理">文章管理</a></li>
                <li class="botton2"><a href="<s:url action="sysFilterWord!init.action"></s:url>" title="过滤词汇管理">过滤词汇管理</a></li>
            </ul>
        </div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementsByName("dto.serviceCode")[0].value=arguments[0];
		document.getElementsByName("dto.serviceName")[0].value=arguments[1];
		document.getElementsByName("dto.advertising")[0].value=arguments[2];
		document.getElementsByName("dto.price")[0].value=arguments[3];
		document.getElementsByName("sysIncrementAdvertFormEdit")[0].submit();
	}
	function remove(){
		if(confirm("你确定要删除这条服务的设置吗?")){
			document.getElementsByName("dto.serviceCode")[1].value=arguments[0];
			document.getElementsByName("sysIncrementAdvertFormRemove")[0].submit();
		}
	}
	function init(){
		//$("#sys_administrator_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
</body>
</html>
