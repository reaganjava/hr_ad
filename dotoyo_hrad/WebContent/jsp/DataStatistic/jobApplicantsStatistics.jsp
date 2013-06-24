<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--招聘应聘人数统计报表</title>
	<s:include value="/jsp/common.jsp"/>
	<script src="<%=request.getContextPath()%>/javascripts/My97DatePicker/WdatePicker.js"></script>
</head>

<s:include value="/jsp/top.jsp"/>

<div class="content">
<s:include value="/jsp/left.jsp"/>
<div class="content-right">
    	<div class="administrator-title"><strong>招聘应聘人数统计报表</strong></div>
    	<s:form  id="searchForm" name="searchForm" method="POST" action="dataStatisticAction!jobApplicantsStatistics.action">
	        <div class="inside-search">
	        	<ul>
	                <li>职位有效日期：
	                <input id="jobIssuetDate" name="jobIssuetDate" type="text" size="15" value="<s:date name="jobIssuetDate" format="yyyy-MM-dd" />" class="Wdate2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',errDealMode:1})"/>&nbsp;至&nbsp;
	                <input id="jobExpiryDate" class='Wdate2' name="jobExpiryDate" value="<s:date name="jobExpiryDate" format="yyyy-MM-dd" />" type="text" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'jobIssuetDate\')}'})"/></li>
	                <li id="inside-search-input">
	                	<input type="image" src="<%=request.getContextPath()%>/images/inside-botton.png" />
	                </li>
	            </ul>
	        </div>
        </s:form>
        <s:form  id="pageFrom" name="pageFrom" method="POST" action="dataStatisticAction!jobApplicantsStatistics.action">
        <s:hidden name="jobIssuetDate"></s:hidden>
        <s:hidden name="jobExpiryDate"></s:hidden>
        <div class="inside-tab">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
              	<td width="2%">&nbsp;</td>
                <td width="10%" height="40" align="left"><strong>省份</strong></td>
                <td width="15%" align="left"><strong>行业</strong></td>
                <td width="15%" align="left"><strong>职能</strong></td>
                <td width="13%" align="left"><strong>职位</strong></td>
                <td width="7%" align="left"><strong>招聘人数</strong></td>
                <td width="7%" align="left"><strong>应聘人数</strong></td>

              </tr>
              <s:iterator value="jobApplicantsList">
	              <tr>
	              	<td width="2%">&nbsp;</td>
	                <td width="10%" height="40" align="left"><s:property value="provinceName"/></td>
	                <td width="15%" align="left"><s:property value="industryName"/></td>
	                <td width="15%" align="left"><s:property value="competencyName"/></td>
	                <td width="13%" align="left"><s:property value="jobName"/></td>
	                <td width="7%" align="center"><s:property value="recruitingNumber"/></td>
	                <td width="7%" align="center"><s:property value="employ"/></td>
	              </tr>
              </s:iterator>
              <tr>
              	<td width="2%">&nbsp;</td>
              	<td width="10%" height="40" align="left"><strong>总计</strong></td>
              	<td width="15%" align="left"><strong>&nbsp;&nbsp;</strong></td>
              	<td width="15%" align="left"><strong>&nbsp;&nbsp;</strong></td>
              	<td width="13%" align="left"><strong>&nbsp;&nbsp;</strong></td>
              	<td width="7%" align="center"><strong><s:property value="sumRecruitingNumber"/></strong></td>
              	<td width="7%" align="center"><strong><s:property value="sumEmploy"/></strong></td>
              </tr>
        </table>
	    </div>

		<div class="qx">
         	<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
     	</div>


		<div class="system-botton3">
			<ul>
				<li class="botton1">
					<a href="javascript:exportAsExcel()" title="导成Excel">导成Excel</a>
				</li>
				<li class="botton3">
					<a href="javascript:window.history.go(-1)" title="返回">返回</a>
				</li>
			</ul>
		</div>
		<div class="num">
    		<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
     	</div>
		</s:form>
  </div>
</div>
<script type="text/javascript">
	function exportAsExcel(){
        var oldaction=document.pageFrom.action;
		document.pageFrom.action='dataStatisticAction!exportJobApplicantsStatisticsAsExcel.action';
		document.pageFrom.method='post';
		document.pageFrom.submit();
		document.pageFrom.action=oldaction;
	}
	function init(){
		$("#sys_dataStatistic_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_dataStatistic_recruit a").addClass("active");
	}
</script>
</body>
</html>