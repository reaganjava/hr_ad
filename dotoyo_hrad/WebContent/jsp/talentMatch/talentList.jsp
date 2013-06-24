<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--个人线下求职意向信息列表</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>个人线下求职意向信息</strong></div>
			<s:form id="searchForm" name="searchForm" method="post" action="talentMatchAction!viewTalentList.action">
				<div class="inside-search">
					<ul>
						<li>申请人：
							<s:textfield name="recommendationDto.userName" size="17"></s:textfield>
						</li>
						<li>意向职位：
							<s:textfield name="recommendationDto.expectedPosition" size="17"></s:textfield>
						</li>
						<li>
							<a href="javascript:document.searchForm.submit()">
								<img src="<%=path %>/images/inside-botton.png" width="68" height="25" />
							</a>
						</li>
					</ul>
				</div>
			</s:form>
			<div class="inside-tab">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="2%" align="center"></td>
						<td width="15%" height="40" align="left"><strong>申请人</strong></td>
						<td width="14%" align="left"><strong>意向职能</strong></td>
						<td width="14%" align="left"><strong>意向职位</strong></td>
						<td width="14%" align="left"><strong>意向工作地区</strong></td>
						<td width="9%" align="left"><strong>工作性质</strong></td>
						<td width="9%" align="left"><strong>是否顾问</strong></td>
						<td width="14%" align="left"><strong>期望薪水</strong></td>
						<td width="9%" align="left"><strong>操作</strong></td>
					</tr>
					<s:iterator value="talentList">
						<tr>
							<td width="2%" align="center">&nbsp;</td>
							<td width="15%" height="40" align="left">
								<span style="width:110px;float:left" class="index_hid_css" title="<s:property value='userInfo.userName'/>">
			                    	<s:property value="userInfo.userName"/>
			                    </span>
							</td>
							<td width="14%" align="left">
								<span style="width:100px;float:left" class="index_hid_css" title="<s:property value='function.name'/>">
			                    	<s:property value="function.name"/>
			                    </span>
							</td>
							<td width="14%" align="left">
								<span style="width:100px;float:left" class="index_hid_css" title="<s:property value='expectedPosition'/>">
			                    	<s:property value="expectedPosition"/>
			                    </span>
							</td>
							<td width="14%" align="left">
								<span style="width:100px;float:left" class="index_hid_css" title="<s:property value='province.name'/><s:property value='city.name'/><s:property value='area.name'/>">
			                    	<s:property value="city.name" /><s:property value="area.name" />
			                    </span>
							</td>
							<td width="9%" align="left"><s:property value="jobType.name" /></td>
							<td width="9%" align="left">
								<s:if test='isAdvisor=="Y"'>
									是
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
							<td width="14%" align="left"><s:property value="expectedSalary.name" /></td>
							<td width="9%" align="left" class="inside-links">
								<a href="talentMatchAction!matchPosition4Personal.action?recommendationDto.id=<s:property value='id' />">人才匹配</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<s:form id="pageFrom" name="pageFrom" method="post" action="talentMatchAction!viewTalentList.action">
				<s:hidden name="recommendationDto.userName"></s:hidden>
				<s:hidden name="recommendationDto.expectedPosition"></s:hidden>
				<div class="qx">
					<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
				</div>
				<div class="num">
					<s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
				</div>
			</s:form>
		</div>
	</div>
</body>
<script type="text/javascript">

	function init(){
		$("#sys_lineofftalentsmath_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_personallineoffapply a").addClass("active");
	}
</script>
</html>
