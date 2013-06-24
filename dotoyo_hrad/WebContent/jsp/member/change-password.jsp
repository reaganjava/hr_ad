<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--修改密码</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<%@ include file="/jsp/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>修改密码</strong></div>
	    <s:form id="pageFrom" name="pageFrom" method="post" action="sysUserChangePwd!changPassword.action">
	        <div class="inside-tab">
	   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
	              <tr>
	                <td width="29%" height="40" align="right"><strong>用户名</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:text name="userName"/>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong><em style="color:#ff6600">*</em>原密码</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:password name="oldPwd" id="oldPwd" cssClass="validate[custom[nullCheckS]]"/>
	                	<FONT color="red" id="oldPwdmsg"><s:text name="oldPwdmsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong><em style="color:#ff6600">*</em>新密码</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:password name="newPwd" id="newPwd" maxlength="20" cssClass="validate[length[6,20],notconfirm[oldPwd]]"/>
	                	<FONT color="red" id="newPwdmsg"><s:text name="newPwdmsg"/></FONT>
	                </td>
	              </tr>
	            </table>
	      	</div>
	       	<div class="system-botton5">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="$('#pageFrom').submit()">修改</a></li>
	            </ul>
	        </div>
		</s:form>
  </div>
</div>
<script type="text/javascript">
	function init(){
		$("#sys_member_changepwd_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		if('<s:text name="flag"/>' == "1"){
			alert('<s:text name="msg"/>');
			document.location.replace("<%=request.getContextPath()%>/sys/sysUserLogin!loginout.action");
		}
	}
</script>
</body>
</html>
