<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--求职意向</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>求职意向</strong></div>
			<s:form id="searchForm" name="searchForm" method="post" action="applyJobManageAction!viewApplyJobIntention4Admin.action">
				<s:hidden name="isNew"></s:hidden>
				<div class="inside-search">
					<ul>
						<li>求职人：
							<s:textfield name="applyJobMap.userName" size="15"></s:textfield>
						</li>
						<li>求职职位：
							<s:textfield name="applyJobMap.expectedPosition" size="15"></s:textfield>
						</li>
						<li>意向工作地点：
							<s:textfield name="applyJobMap.provinceName" size="15"></s:textfield>
						</li>
						<li>
							<a href="javascript:clickSearch()">
								<img src="<%=path %>/images/inside-botton.png" width="68" height="25" />
							</a>
						</li>
					</ul>
				</div>
			</s:form>
			<div class="inside-tab">
				<s:form id="listForm" name="listForm" method="post">
					<s:hidden name="applyJobMap.userName"></s:hidden>
					<s:hidden name="applyJobMap.expectedPosition"></s:hidden>
					<s:hidden name="isNew"></s:hidden>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="2%" align="center"><input id="selected" type="checkbox"/></td>
							<td width="5%" height="40" align="left"><strong>编号</strong></td>
							<td width="14%" height="40" align="left"><strong>求职人</strong></td>
							<td width="12%" align="left"><strong>意向职能</strong></td>
							<td width="11%" align="left"><strong>意向职位</strong></td>
							<td width="13%" align="left"><strong>意向工作地点</strong></td>
							<td width="9%" align="left"><strong>是否顾问</strong></td>
							<td width="9%" align="left"><strong>工作性质</strong></td>
							<td width="10%" align="left"><strong>薪水</strong></td>
							<td width="15%" align="left"><strong>求职时间</strong></td>
						</tr>
						<s:iterator value="applyJobList">
							<tr>
								<td width="2%" align="center"><input name="applyJobCheckBox" type="checkbox" value="<s:property value='id'/>"/></td>
								<td width="5%" align="center"><s:property value="id"/></td>
								<td width="14%" height="40" align="left">
									<span style="width:100px;float:left" class="index_hid_css" title="<s:property value='userName'/>">
				                    	<s:property value="userName"/>
				                    </span>
								</td>
								<td width="12%" align="left">
									<span style="width:90px;float:left" class="index_hid_css" title="<s:property value='functionName'/>">
				                    	<s:property value="functionName"/>
				                    </span>
								</td>
								<td width="11%" align="left">
									<span style="width:80px;float:left" class="index_hid_css" title="<s:property value='expectedPosition'/>">
				                    	<s:property value="expectedPosition"/>
				                    </span>
								</td>
								<td width="13%" align="left">
									<span style="width:85px;float:left" class="index_hid_css" title="<s:property value='provinceName'/><s:property value='cityName'/><s:property value='areaName'/>">
				                    	<s:property value="cityName"/><s:property value="areaName"/>
				                    </span>
								</td>
								<td width="9%" align="left">
									<s:if test='isAdvisor=="Y"'>
										是
									</s:if>
									<s:else>
										否
									</s:else>
								</td>
								<td width="9%" align="left"><s:property value="jobTypeName"/></td>
								<td width="10%" align="left">
									<s:property value="expectedSalaryName"/>
								</td>
								<td width="15%" align="left">
									<s:date name="submitDate" format="yyyy-MM-dd" />
								</td>
							</tr>
						</s:iterator>
					</table>
				</s:form>
			</div>
			<s:form  id="pageFrom" name="pageFrom" method="POST" action="applyJobManageAction!viewApplyJobIntention4Admin.action">
				<s:hidden name="isNew"></s:hidden>
				<s:hidden name="applyJobMap.userName"></s:hidden>
				<s:hidden name="applyJobMap.expectedPosition"></s:hidden>
				<div class="qx">
					<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
					<input id="SSS" name="" type="checkbox" value="" />
					<label for="SSS">&nbsp;&nbsp;全选</label>
				</div>
				<div class="shanchu" onclick="deleteApplyJobIntention();">
					删除
				</div>
				<div class="num">
					<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
				</div>
			</s:form>
		</div>
	</div>
	<script type="text/javascript">
		function clickSearch(){
			document.searchForm.submit();
		}
		function deleteApplyJobIntention(){
			if(checkStatus("applyJobCheckBox")){
				if(window.confirm("你确认要进行此操作吗？")){
					document.listForm.action = "applyJobManageAction!deleteApplyJobIntention.action";
					document.listForm.submit();	
				}
			}
			else{
				alert("请至少选择一条记录");
			}
		}
		function init(){
			$("#sys_applyjob_manage_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
			$("#sys_applyjon_purpose a").addClass("active");
		}
	</script>
</body>
</html>
