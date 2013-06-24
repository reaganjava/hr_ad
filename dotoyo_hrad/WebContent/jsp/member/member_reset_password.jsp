<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--会员管理--重置密码</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>重置密码</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="sysMemberReset!resetPassword.action">
	        <div class="inside-tab">
	   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="inside-tab">
	              <tr>
	                <td width="29%" height="40" align="right"><strong>用户名</strong>：</td>
	                <td width="71%" align="left" class="tab-input">
	                	<s:text name="userdto.userName"/>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>积分</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:text name="userdto.point"/>

	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>状态</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:text name="userdto.status"/>
	                </td>
	              </tr>
	              <tr>
	                <td width="29%" height="40" align="right"><strong>提供的Email</strong>：</td>
	                <td align="left" class="tab-input">
	                	<s:textfield name="emailTo" id="emailTo" cssStyle="width:220px" cssClass="validate[custom[email]]" maxlength="61"/>
	                	<FONT color="red" id="emailToMsg"><s:text name="emailToMsg"/></FONT>
	                	<FONT color="green" id="msg"><s:text name="msg"/></FONT>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="system-botton3">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="$('#pageFrom').submit()" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/sysMemberReset!cancel.action" title="取消">取消</a></li>
	            </ul>
	        </div>
      	</s:form>
  		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	function init(){
		$("#sys_member_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_member_reset a").addClass("active");
	}
</script>
