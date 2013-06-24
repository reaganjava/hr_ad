<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--求职方管理</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>求职方管理</strong></div>
			<s:form id="searchForm" name="searchForm" method="post" action="applyJobManageAction!viewJobApplyRecordList4Admin.action">
				<div class="inside-search">
					<ul>
						<li>应聘人：
							<s:textfield name="recordMap.personalUserName" size="15"></s:textfield>
						</li>
						<li>招聘公司：
							<s:textfield name="recordMap.companyUserRealName" size="15"></s:textfield>
						</li>
						<li>应聘职位：
							<s:textfield name="recordMap.jobName" size="15"></s:textfield>
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
				<s:hidden name="recordMap.personalUserName"></s:hidden>
				<s:hidden name="recordMap.companyUserRealName"></s:hidden>
				<s:hidden name="recordMap.jobName"></s:hidden>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="5%" align="center"><input id="selected" type="checkbox" value="" /></td>
						<td width="23%" align="left"><strong>应聘人</strong></td>
						<td width="26%" height="40" align="left"><strong>求职职位</strong></td>
						<td width="23%" align="left"><strong>招聘公司</strong></td>
						<td width="11%" align="left"><strong>应聘时间</strong></td>
						<td width="12%" align="left"><strong>操作</strong></td>
					</tr>
					<s:iterator value="applicationJobRecordList">
						<tr>
							<td width="5%" align="center"><input name="recordCheckBox" type="checkbox" value="<s:property value='id'/>" /></td>
							<td width="23%" align="left"><s:property value="personalUserInfo.userName"/></td>
							<td width="26%" height="40" align="left"><s:property value="jobName"/></td>
							<td width="23%" align="left"><s:property value="companyUserInfo.companyName"/></td>
							<td width="11%" align="left"><s:date name="applicationDate" format="yyyy-MM-dd"/></td>
							<td width="12%" align="left" class="inside-links">
								<a href="applyJobManageAction!viewApplyJobIntention4Admin.action?applyJobMap.userName=<s:property value="personalUserInfo.userName"/>">
									查看求职意向
								</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</s:form>
			</div>
			<s:form  id="pageFrom" name="pageFrom" method="POST" action="applyJobManageAction!viewJobApplyRecordList4Admin.action">
				<s:hidden name="recordMap.personalUserName"></s:hidden>
				<s:hidden name="recordMap.companyUserRealName"></s:hidden>
				<s:hidden name="recordMap.jobName"></s:hidden>
				<div class="qx">
					<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
					<input id="SSS" name="" type="checkbox" value="" />
					<label for="SSS">&nbsp;&nbsp;全选</label>
				</div>


			<div class="shanchu" onclick="deleteJobApplyRecord();">
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
		function deleteJobApplyRecord(){
			if(checkStatus("recordCheckBox")){
				if(window.confirm("你确认要进行此操作吗？")){
					document.listForm.action = "applyJobManageAction!deleteJobApplyRecord.action";
					document.listForm.submit();
				}
			}
			else{
				alert("请至少选择一条记录");
			}
		}
		function init(){
			$("#sys_applyjob_manage_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
			$("#sys_applyjob_this a").addClass("active");
		}
	</script>
</body>
</html>
