<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--猎证需求管理</title>
	<s:include value="/jsp/common.jsp"/>
	<%String path = request.getContextPath(); %>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>猎证需求管理</strong></div>
			<s:form id="searchForm" name="searchForm" method="post" action="certNeedsManageAction!viewAllCertNeeds4Admin.action">
				<div class="inside-search">
					<ul>
						<li>用户名：
							<s:textfield name="certNeedsMap.userName" size="10"></s:textfield>
						</li>
						<li>公司名：
							<s:textfield name="certNeedsMap.companyName" size="10"></s:textfield>
						</li>
						<li>证书名：
							<s:textfield name="certNeedsMap.certName" size="10"></s:textfield>
						</li>
						<li>状态：
							<s:select name="certNeedsMap.queryType"
								list="#{null:'所有','0':'未猎证','1':'已猎证','2':'暂停猎证','3':'未过期','4':'即将过期','5':'已过期'}">
							</s:select>
						</li>
						<li>
							<a href="javascript:clickSearch()">
								<img src="<%=path %>/images/inside-botton.png" width="68" height="25" />
							</a>
						</li>
					</ul>
				</div>
				<s:hidden name="isNew"></s:hidden>
			</s:form>
			<div class="inside-tab">
				<s:form id="listForm" name="listForm" method="post">
					<s:hidden name="isNew"></s:hidden>
					<s:hidden name="certNeedsMap.userName"></s:hidden>
					<s:hidden name="certNeedsMap.companyName"></s:hidden>
					<s:hidden name="certNeedsMap.certName"></s:hidden>
					<s:hidden name="certNeedsMap.queryType"></s:hidden>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="2%" align="center"><input type="checkbox" name="checkbox" id="selected" /></td>
							<td width="4%" align="left"><strong>编号</strong></td>
							<td width="4%" height="40" align="left"><strong>用户名</strong></td>
							<td width="6%" align="left"><strong>公司名</strong></td>
							<td width="9%" align="left"><strong>证书类型</strong></td>
							<td width="9%" align="left"><strong>证书名称</strong></td>
							<td width="9%" align="left"><strong>挂证地区</strong></td>
							<td width="9%" align="left"><strong>需求数量</strong></td>
							<td width="7%" align="left"><strong>专业</strong></td>
							<td width="9%" align="left"><strong>有效期至</strong></td>
							<td width="9%" align="left"><strong>猎证状态</strong></td>
							<td width="9%" align="left"><strong>是否过期</strong></td>
							<td width="10%" align="left"><strong>是否急需证书</strong></td>
							<td width="4%" align="left"><strong>操作</strong></td>
						</tr>
						<s:iterator value="certNeedsList">
							<tr>
								<td width="2%" align="center"><input name="certNeedsCheckBox" type="checkbox" value="<s:property value='id'/>"/></td>
								<td width="4%" height="40" align="left"><s:property value="id"/></td>
								<td width="4%" height="40" align="left"><s:property value="userName"/></td>
								<td width="6%" align="left"><s:property value="userRealName"/></td>
								<td width="9%" align="left"><s:property value="certTypeName"/></td>
								<td width="9%" align="left"><s:property value="certName"/></td>
								<td width="9%" align="left"><s:property value="cityName"/><s:property value="areaName"/></td>
								<td width="9%" align="left"><s:property value="certNeedsAmount"/></td>
								<td width="7%" align="left"><s:property value="specialFieldName"/></td>
								<td width="9%" align="left"><s:date name="expDate" format="yyyy-MM-dd"/></td>
								<td width="9%" align="left">
									<s:if test="certNeedsStatus==0">
										未猎证
									</s:if>
									<s:elseif test="certNeedsStatus==1">
										已猎证
									</s:elseif>
									<s:else>
										暂停猎证
									</s:else>
								</td>
								<td width="9%" align="left">
									<s:if test="remainsDays>7">
										未过期
									</s:if>
									<s:elseif test="remainsDays<0">
										已过期
									</s:elseif>
									<s:else>
										即将过期
									</s:else>
								</td>
								<td width="10%" align="left">
									<s:if test='isIndeed=="Y"'>是</s:if>
									<s:else>否</s:else>
								</td>
								<td width="4%" align="left" class="inside-links">
									<a href="certNeedsManageAction!viewCertNeedsDetail4Admin.action?certNeedsId=<s:property value='id'/>">查看</a>
								</td>
							</tr>
						</s:iterator>
					</table>
				</s:form>
			</div>
			<s:form  id="pageFrom" name="pageFrom" method="POST" action="certNeedsManageAction!viewAllCertNeeds4Admin.action">
				<s:hidden name="isNew"></s:hidden>
				<s:hidden name="certNeedsMap.userName"></s:hidden>
				<s:hidden name="certNeedsMap.companyName"></s:hidden>
				<s:hidden name="certNeedsMap.certName"></s:hidden>
				<s:hidden name="certNeedsMap.queryType"></s:hidden>
				<div class="qx">
					<s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
					<input id="SSS" name="" type="checkbox" value="" />
					<label for="SSS">&nbsp;&nbsp;全选</label>
				</div>
					<div class="system-botton2">
						<ul>
							<li class="botton1">
								<a href="javascript:deleteCertNeeds()" title="删除">删除</a>
							</li>
							<li class="botton2">
								<a href="javascript:editIndeedStatus('Y')" title="设为急需">设为急需状态</a>
							</li>
							<li class="botton3">
								<a href="javascript:editIndeedStatus('N')" title="取消急需">取消急需状态</a>
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
		function clickSearch(){
			document.searchForm.submit();
		}
		function deleteCertNeeds(){
			if(checkStatus("certNeedsCheckBox")){
				if(window.confirm("你确认要进行此操作吗？")){
					document.listForm.action = "certNeedsManageAction!deleteCertNeeds.action";
					document.listForm.submit();
				}
			}
			else{
				alert("请至少选择一条记录");
			}
		}
		function editIndeedStatus(indeedStatus){
			if(checkStatus("certNeedsCheckBox")){
				if(window.confirm("你确认要进行此操作吗？")){
					document.listForm.action = "certNeedsManageAction!editIndeedStatus.action?indeedStatus="+indeedStatus;
					document.listForm.submit();
				}
			}
			else{
				alert("请至少选择一条记录");
			}
		}
		function init(){
			$("#sys_huntcard_requrement_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		}
	</script>
</body>
</html>
