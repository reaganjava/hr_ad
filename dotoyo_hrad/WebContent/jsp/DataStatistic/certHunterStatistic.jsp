<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--数据统计</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>猎证统计报表</strong></div>
			<div class="inside-tab">
				<table id="dataList" width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="2%" align="left">&nbsp;</td>
						<td width="77%" height="40" align="left"><strong>统计项</strong></td>
						<td width="21%" align="left"><strong>数量</strong></td>
					</tr>
					<tr>
						<td align="left">&nbsp;</td>
						<td height="40" align="left">个人提交证书数量</td>
						<td align="left">
							<a href="personalCertManageAction!viewPersonalCertList4Admin.action">
								<s:property value="personalCertAmount"/>
							</a>
						</td>
					</tr>
					<tr>
						<td align="left">&nbsp;</td>
						<td height="40" align="left">企业证书需求数量</td>
						<td align="left">
							<a href="certNeedsManageAction!viewAllCertNeeds4Admin.action">
								<s:property value="certNeedsAmount"/>
							</a>
						</td>
					</tr>
					<tr>
						<td align="left">&nbsp;</td>
						<td height="40" align="left"><strong>总计</strong></td>
						<td align="left">
							<strong>
								<s:property value="certNeedsAmount+personalCertAmount" />
							</strong>
						</td>
					</tr>
				</table>
			</div>
			<s:form id="exportForm" name="exportForm" method="post" action="dataStatisticAction!exportHunterStatisticAsExcel.action">
				<s:hidden name="personalCertAmount"></s:hidden>
				<s:hidden name="certNeedsAmount"></s:hidden>
			</s:form>
			<s:form target="_blank" id="hiddenForm" name="hiddenForm" method="post" action="dataStatisticAction!printCertHunterStatistic.action">
				<s:hidden name="personalCertAmount"></s:hidden>
				<s:hidden name="certNeedsAmount"></s:hidden>
				<div class="system-botton2">
					<ul>
						<li class="botton1">
							<a href="javascript:print()" title="打印">打印</a>
						</li>
						<li class="botton2">
							<a href="javascript:exportAsExcel()" title="导成Excel">导成Excel</a>
						</li>
						<li class="botton3">
							<a href="javascript:window.history.go(-1)" title="返回">返回</a>
						</li>
					</ul>
				</div>
			</s:form>
		</div>
	</div>
	<script type="text/javascript">
		function exportAsExcel(){
			$("#exportForm").submit();
		}
		function print(){
			$("#hiddenForm").submit();
		}
		function init(){
			$("#sys_dataStatistic_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
			$("#sys_dataStatistic_count a").addClass("active");
		}
	</script>
</body>
</html>
