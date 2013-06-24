<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务设置--增加招聘服务设置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>增加招聘服务设置</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="recruitSetting!add.action">
        <div class="inside-tab">
	 	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>套餐名称：</strong></td>
	                <td width="80%" height="40" align="left" class="tab-input">
	                	<s:select list="serviceNames" name="dto.serviceCode" cssClass="validate[custom[requiredSelected]]"/>
	                	<FONT color="red"><s:text name="serviceNameMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>发布职位次数：</strong></td>
	                <td width="80%" align="left">
	                	<s:textfield name="dto.timesOfPublicPosition" cssClass="validate[required,custom[onlyNumber]]" maxLength="9"></s:textfield>
	                	<FONT color="red"><s:text name="timesOfPublicPositionMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>查看博站次数：</strong></td>
	                <td width="80%" align="left">
	                	<s:textfield name="dto.timesOfLookupBlog" cssClass="validate[required,custom[onlyNumber]]" maxLength="9"></s:textfield>
	                	<FONT color="red"><s:text name="timesOfLookupBlogMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>邀请面试次数：</strong></td>
	                <td width="80%" align="left">
	                	<s:textfield name="dto.timesOfLookupInvite" cssClass="validate[required,custom[onlyNumber]]" maxLength="9"></s:textfield>
	                	<FONT color="red"><s:text name="timesOfLookupInviteMsg"/></FONT>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="system-botton">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="$('#pageFrom').submit();return false;" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/recruitSetting!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</s:form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){
		$("#sys_increment_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_increment_ul_recruit a").addClass("active");
	}
</script>
