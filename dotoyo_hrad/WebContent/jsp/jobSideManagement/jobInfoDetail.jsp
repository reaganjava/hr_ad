<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=request.getContextPath()%>/css/index-style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/head.css" rel="stylesheet" type="text/css" />
	<title>大太阳建筑猎头-猎头中心-职位信息</title>
	<%@ include file="/jsp/common.jsp"%>
	
</head>
<%@ include file="/jsp/top.jsp"%>

<!--中间部分-->
<div class="position-details">
   <div class="details-title1"><strong>职位信息</strong></div>
    <div class="details-title1-content">
   	  <div class="details-title1-left">
       	<h3><s:property value="jobInfo.jobName"/></h3>
          <ul>
           	  <li><s:property value="jobInfo.companyName"/></li>
              <li><img src="<%=request.getContextPath()%>/images/details-icon1.png" width="12" height="15" />行业类别：<s:property value="jobInfo.industryType"/></li>
              <li><img src="<%=request.getContextPath()%>/images/details-icon2.png" width="12" height="15" />工作地点：<s:property value="jobInfo.provinceCode"/> <s:property value="jobInfo.cityCode"/> <s:property value="jobInfo.areaCode"/></li>
              <li><img src="<%=request.getContextPath()%>/images/details-icon3.png" width="12" height="16" />招聘人数：<s:property value="jobInfo.recruitingNumber"/></li>
              <li><img src="<%=request.getContextPath()%>/images/details-icon4.png" width="12" height="15" />发布时间：<s:property value="getText('format.date',{jobInfo.jobIssuetDate})"/></li>
          </ul>
      </div>
      <div class="details-title1-right">
          <ul>
           	  <li class="details-xiangxi1">工作年限：<s:property value="jobInfo.workingLife"/></li>
              <li class="details-xiangxi2">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：
              	<s:if test='jobInfo.sex=="1"'>男</s:if><s:elseif test='jobInfo.sex=="0"'>女</s:elseif><s:elseif test='jobInfo.sex=="2"'>不限</s:elseif>
              </li>
              <li class="details-xiangxi1">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能：<s:property value="jobInfo.competency"/></li>
              <li class="details-xiangxi2">年龄要求：<s:property value="jobInfo.ageName"/></li>
              <li class="details-xiangxi1">专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业：<s:property value="jobInfo.specializedType"/></li>
              <li class="details-xiangxi2">工作性质：<s:property value="jobInfo.jobNature"/></li>
              <li class="details-xiangxi1">语言能力：<s:property value="jobInfo.langCapa"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="jobInfo.mastery"/></li>
              <li class="details-xiangxi2">顾&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;问：
              	<s:if test='jobInfo.isAdviser=="1"' >是</s:if>
              	<s:else>
              		否
              	</s:else>
             　　　　　</li>
              <li class="details-xiangxi1">学历要求：<s:property value="jobInfo.education"/></li>
              <li class="details-xiangxi2">计算机能力：<s:property value="jobInfo.computerGrade"/></li>
              <li class="details-xiangxi1">月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;薪：<s:property value="jobInfo.salary"/></li>
              <li class="details-xiangxi2">截止日期：<s:property value="getText('format.date',{jobInfo.jobExpiryDate})"/></li>
          </ul>
      </div>
    </div>
    <div class="details-title2"><strong>岗位职责</strong></div>
    <div class="details-title2-content">
       <s:textarea readonly="true"  id="jobResponsibility" name="jobInfo.jobResponsibility"  />
    </div>
    <div class="details-title3"><strong>任职要求</strong></div>
     <div class="details-title2-content">
       <s:textarea readonly="true"  id="jobRequirements" name="jobInfo.jobRequirements"  />
    </div>
</div>
</body>
</html>



