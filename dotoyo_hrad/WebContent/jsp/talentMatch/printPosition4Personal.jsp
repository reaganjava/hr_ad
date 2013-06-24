<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>匹配结果</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
<body>
			<div class="administrator-title"><strong>匹配的条件</strong></div>
			<div class="inside-tab">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="18%" height="40" align="right"><strong>意向工作地区</strong>：</td>
						<td width="22%" align="left" class="tab-input">
							<s:property value="recommendationVo.city.name" /><s:property value="recommendationVo.area.name" /> 
						</td>
						<td width="9%">&nbsp;</td>
						<td width="12%" align="right"><strong>工作性质</strong>：</td>
						<td width="39%" align="left"><s:property value="recommendationVo.jobType.name" /></td>
					</tr>
					<tr>
						<td height="40" align="right"><strong>是否顾问</strong>：</td>
						<td align="left" class="tab-input">
							<s:if test='recommendationVo.isAdvisor=="Y"'>
								是
							</s:if>
							<s:else>
								否
							</s:else>
						</td>
						<td>&nbsp;</td>
						<td align="right"><strong>意向职位</strong>：</td>
						<td align="left"><s:property value="recommendationVo.expectedPosition" /></td>
					</tr>
				</table>
			</div>
			<div class="administrator-title2"><strong>匹配结果</strong></div>
			<div class="inside-tab">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="4%" align="center">&nbsp;</td>
						<td width="18%" height="40" align="left"><strong>工作地区</strong></td>
						<td width="10%" align="left"><strong>工作性质</strong></td>
						<td width="9%" align="left"><strong>是否顾问</strong></td>
						<td width="29%" align="left"><strong>招聘公司</strong></td>
						<td width="20%" align="left"><strong>职位</strong></td>
						<td width="10%" align="left"><strong>发布日期</strong></td>
					</tr>
					<s:iterator value="jobResultList">
						<tr>
							<td width="4%" align="center">&nbsp;</td>
							<td width="18%" height="40" align="left"><s:property value="cityCode" /><s:property value="areaCode" /></td>
							<td width="10%" align="left"><s:property value="jobNature" /></td>
							<td width="9%" align="left">
								<s:if test="isAdviser==1">
									是
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
							<td width="29%" align="left"><s:property value="companyName" /></td>
							<td width="20%" align="left"><s:property value="jobName" /></td>
							<td width="10%" align="left"><s:date name="jobIssuetDate" format="yyyy-MM-dd"/></td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<script type="text/javascript">
				$(document).ready(function(){
					window.print();
					window.opener = null;
					window.close();
				});
			</script>
</body>
</html>