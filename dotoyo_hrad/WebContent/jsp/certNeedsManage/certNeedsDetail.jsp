<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头--系统管理--证书需求详细信息</title>
	<s:include value="/jsp/common.jsp"/>
</head>
	<s:include value="/jsp/top.jsp"/>
	<!--以上为头部分-->
	<!--中间部分-->
	<div class="content">
		<s:include value="/jsp/left.jsp"/>
		<div class="content-right">
			<div class="administrator-title"><strong>证书需求详细信息</strong></div>
			<div class="inside-tab">
				<s:form id="form" name="form" method="post" action="certNeedsManageAction!saveEditCertNeeds.action">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>证书类型</strong>：</td>
							<td width="71%" align="left" class="tab-input">
							 	<s:select disabled="true"  name="certNeedsDto.certTypeCode" id="certType" list="certificateTypeList" listKey="code" listValue="name" headerKey="" headerValue="请选择证书类型">
							 	</s:select>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>证书名称</strong>：</td>
							<td align="left" class="tab-input">
							 	<s:select disabled="true" name="certNeedsDto.certCode" id="cert" list="certList" listKey="code" listValue="name" headerKey="" headerValue="请选择证书名称">
							 	</s:select>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>注册状况</strong>：</td>
							<td align="left" class="tab-input">
								<s:select disabled="true" name="certNeedsDto.registerStatusCode" list="registerStatusList" listKey="code" listValue="name" headerKey="" headerValue="请选择注册状况">
								</s:select>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>专业</strong>：</td>
							<td align="left" class="tab-input">
								<s:select disabled="true" name="certNeedsDto.specialFieldCode" list="specialFieldList" listKey="code" listValue="name" headerKey="" headerValue="请选择专业">
								</s:select>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>挂证地区</strong>：</td>
							<td align="left">
								<s:select disabled="true" id="province" name="certNeedsDto.provinceCode" list="provinceList" listKey="code" listValue="name" headerKey="" headerValue="省" cssStyle="width:70px;">
								</s:select>
								<s:select disabled="true" id="city" name="certNeedsDto.cityCode" list="cityList" listKey="code" listValue="name" headerKey="" headerValue="市" cssStyle="width:70px;">
								</s:select>
								<s:select disabled="true" id="area" name="certNeedsDto.areaCode" list="areaList" listKey="code" listValue="name" headerKey="" headerValue="区" cssStyle="width:70px;">
								</s:select>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>证书需求数</strong>：</td>
							<td align="left" class="tab-input">
								<s:textfield disabled="true" name="certNeedsDto.certNeedsAmount"/>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>发布日期</strong>：</td>
							<td align="left" class="tab-input">
								<input disabled="disabled" class='Wdate2' id="effDate" name="certNeedsDto.effDate" type="text" value="<s:date name="certNeedsDto.effDate" format="yyyy-MM-dd" />" />
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>有效期至</strong>：</td>
							<td align="left" class="tab-input">
								<input disabled="disabled" class='Wdate2' id="expDate" name="certNeedsDto.expDate" type="text" value="<s:date name="certNeedsDto.expDate" format="yyyy-MM-dd" />" />
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><em>*</em><strong>单位规模</strong>：</td>
							<td align="left" class="tab-input">
								<s:select disabled="true" name="certNeedsDto.companySizeCode" list="companySizeList" listKey="code" listValue="name" headerKey="" headerValue="请选择单位规模">
								</s:select>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><strong>联系人</strong>：</td>
							<td align="left" class="tab-input">
								<s:textfield disabled="true" name="certNeedsDto.contact"/>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><strong>联系电话</strong>：</td>
							<td align="left" class="tab-input">
								<s:textfield disabled="true"  name="certNeedsDto.telephone"/>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><strong>手机</strong>：</td>
							<td align="left" class="tab-input">
								<s:textfield  disabled="true" name="certNeedsDto.cellPhone"/>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><strong>Email</strong>：</td>
							<td align="left" class="tab-input">
								<s:textfield disabled="true" name="certNeedsDto.email"/>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><strong>传真</strong>：</td>
							<td align="left" class="tab-input">
								<s:textfield disabled="true"  name="certNeedsDto.fax"/>
							</td>
						</tr>
						<tr>
							<td width="29%" height="40" align="right"><strong>补充说明</strong>：</td>
							<td align="left" class="tab-input">
								<s:textarea  disabled="true"  name="certNeedsDto.descp"></s:textarea>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
			<div class="system-botton3">
				<ul>
					<li class="botton1"><a href="javascript:goBack()" title="返回">返回</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		function init(){
			$("#sys_huntcard_requrement_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		}
	</script>
</html>
