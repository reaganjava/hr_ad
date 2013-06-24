<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务管理--服务详情</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>套餐详情</strong></div>
	        <div class="inside-tab">
	   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
	              <tr>
	                <td width="29%" height="40" align="right"><strong>套餐编码</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:if test="incrementServiceVo.serviceNameMaintainanceDto.serviceCode != null">
	                		<s:text name="incrementServiceVo.serviceNameMaintainanceDto.serviceCode" />
	                	</s:if>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>套餐名称</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:if test="incrementServiceVo.serviceNameMaintainanceDto.serviceName != null">
	                		<s:text name="incrementServiceVo.serviceNameMaintainanceDto.serviceName"/>
	                	</s:if>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>服务项目</strong>：</td>
	                <td align="left">
	                	<s:if test="incrementServiceVo.advertSettingDto != null">
	                		<s:text name="incrementServiceVo.advertSettingDto.itemName"/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.hangCardSettingDto != null">
	                		<s:text name="incrementServiceVo.hangCardSettingDto.itemName"/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.huntCardSettingDto != null">
	                		<s:text name="incrementServiceVo.huntCardSettingDto.itemName"/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.recruitSettingDto != null">
	                		<s:text name="incrementServiceVo.recruitSettingDto.itemName"/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.talentsFoundSettingDto != null">
	                		<s:text name="incrementServiceVo.talentsFoundSettingDto.itemName"/>
	                	</s:if>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>可用天数</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:if test="incrementServiceVo.serviceNameMaintainanceDto.usableDays != null">
	                		<s:text name="incrementServiceVo.serviceNameMaintainanceDto.usableDays"/>
	                	</s:if>
	                </td>
	         	 </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>服务简介</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:if test="incrementServiceVo.serviceNameMaintainanceDto.serviceIntro != null">
							<s:text name="incrementServiceVo.serviceNameMaintainanceDto.serviceIntro"/>
						</s:if>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>服务价格</strong>：</td>
	                <td align="left">
	                		<s:if test='incrementServiceVo.serviceNameMaintainanceDto.priceStatus=="0"'>
	                			面议
	                		</s:if>
	                		<s:else>
	                			<s:text name="incrementServiceVo.serviceNameMaintainanceDto.price"/>
	                		</s:else>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right" valign="top"><strong>服务设置</strong>：</td>
	                <td align="left">
	                	<s:if test="incrementServiceVo.advertSettingDto != null">
	                		<strong>广告位</strong>:
	                		<s:text name="incrementServiceVo.advertSettingDto.advertisingName"/> &nbsp;<br/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.hangCardSettingDto != null">
	                		<strong>证书提交个数</strong>:
	                		<s:text name="incrementServiceVo.hangCardSettingDto.countOfSubmit"/> &nbsp;<br/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.huntCardSettingDto != null">
	                		<strong>查看联系方式次数</strong>:
	                		<s:text name="incrementServiceVo.huntCardSettingDto.timesOfLookup"/> &nbsp;
	                		<strong>提交证书需求个数</strong>:
	                		<s:text name="incrementServiceVo.huntCardSettingDto.timesOfSubmitCertNeeds"/> &nbsp;<br/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.recruitSettingDto != null">
                			<strong>发布职位次数</strong>:
	                			<s:text name="incrementServiceVo.recruitSettingDto.timesOfPublicPosition"/> &nbsp;
	                		<strong>邀请面试次数</strong>:
	                			<s:text name="incrementServiceVo.recruitSettingDto.timesOfLookupInvite"/> &nbsp;
	                		<strong>查看博站次数</strong>:
	                			<s:text name="incrementServiceVo.recruitSettingDto.timesOfLookupBlog"/><br/>
	                	</s:if>
	                	<s:if test="incrementServiceVo.talentsFoundSettingDto != null">
	                		<strong>可搜索的次数</strong>:
	                		<s:text name="incrementServiceVo.talentsFoundSettingDto.timesOfSearch"/> &nbsp;
	                		<strong>可搜索字段</strong>:
	                		<s:text name="incrementServiceVo.talentsFoundSettingDto.fieldsOfSearchName"/> &nbsp;
	                	</s:if>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="shanchu">
	        	<ul>
	                <li class="botton1" style="text-align:center"><a href="<%=request.getContextPath()%>/sys/sysIncrementManage!cancel.action" title="确定">确定</a></li>
	            </ul>
	        </div>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){
		$("#sys_increment_manager_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
	}
</script>
