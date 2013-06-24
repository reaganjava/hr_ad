<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--证书管理</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>证书管理</strong></div>
			<s:form id="searchForm" name="searchForm" method="post" action="personalCertManageAction!viewPersonalCertList4Admin.action">
				<s:hidden name="isNew"></s:hidden>
				<div class="inside-search">
						<ul>
							<li>用户名:
								<s:textfield name="personalCertMap.userName" size="10"></s:textfield>
							</li>
							<li>姓名:
								<s:textfield name="personalCertMap.companyName" size="10"></s:textfield>
							</li>
							<li>证书名称:
								<s:textfield name="personalCertMap.certName" size="10"></s:textfield>
							</li>
							<li>挂靠状态:
								<s:select name="personalCertMap.certStatus"
									list="#{null:'所有','0':'未挂靠','1':'已挂靠','2':'暂停挂靠'}">
								</s:select>
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
				<s:hidden name="isNew"></s:hidden>
				<s:hidden name="personalCertMap.userName"></s:hidden>
				<s:hidden name="personalCertMap.companyName"></s:hidden>
				<s:hidden name="personalCertMap.certName"></s:hidden>
				<s:hidden name="personalCertMap.certStatus"></s:hidden>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="5%" align="center"><input id="selected" type="checkbox"/></td>
						<td width="16%" height="40" align="left"><strong>用户名</strong></td>
						<td width="15%" align="left"><strong>姓名</strong></td>
						<td width="11%" height="40" align="left"><strong>证书类型</strong></td>
						<td width="10%" align="left"><strong>证书名称</strong></td>
						<td width="9%" align="left"><strong>所在地区</strong></td>
						<td width="9%" align="left"><strong>注册情况</strong></td>
						<td width="9%" align="left"><strong>专业</strong></td>
						<td width="10%" align="left"><strong>挂靠状态</strong></td>
						<td width="6%" align="left"><strong>操作</strong></td>
					</tr>
					<s:iterator value="personalCertList">
						<tr>
							<td width="5%" align="center"><input name="personalCertCheckBox" type="checkbox" value="<s:property value='id'/>"/></td>
							<td width="16%" height="40" align="left"><s:property value="userName"/></td>
							<td width="15%" align="left"><s:property value="userRealName"/></td>
							<td width="11%" height="40" align="left"><s:property value="certTypeName"/></td>
							<td width="10%" align="left"><s:property value="certName"/></td>
							<td width="9%" align="left"><s:property value="cityName"/><s:property value="areaName"/></td>
							<td width="9%" align="left"><s:property value="registerStatusName"/></td>
							<td width="9%" align="left"><s:property value="specialFieldName"/></td>
							<td width="10%" align="left">
								<s:if test="certStatus==0">
									未挂靠
								</s:if>
								<s:elseif test="certStatus==1">
									已挂靠
								</s:elseif>
								<s:else>
									暂停挂靠
								</s:else>
							</td>
							<td width="6%" align="left" class="inside-links">
								<a href="personalCertManageAction!viewPersonalCertDetail4Admin.action?personalCertId=<s:property value='id'/>">查看</a>
							</td>
						</tr>
					</s:iterator>
				</table>
			</s:form>
			</div>
			<s:form  id="pageFrom" name="pageFrom" method="POST" action="personalCertManageAction!viewPersonalCertList4Admin.action">
					<s:hidden name="isNew"></s:hidden>
					<s:hidden name="personalCertMap.userName"></s:hidden>
					<s:hidden name="personalCertMap.companyName"></s:hidden>
					<s:hidden name="personalCertMap.certName"></s:hidden>
					<s:hidden name="personalCertMap.certStatus"></s:hidden>
				<div class="qx">
					<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
					<input id="SSS" name="" type="checkbox" value="" />
					<label for="SSS">&nbsp;&nbsp;全选</label>
				</div>
				<div class="shanchu">
					<a href="javascript:deletePersonalCert()" title="删除" >删除</a>
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
		function deletePersonalCert(){
			if(checkStatus("personalCertCheckBox")){
				if(window.confirm("你确认要进行此操作吗？")){
					document.listForm.action = "personalCertManageAction!deletePersonalCert.action";
					document.listForm.submit();
				}
			}
			else{
				alert("请至少选择一条记录");
			}
		}
		function init(){
			$("#sys_card_manage_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		}
	</script>
</body>
</html>
