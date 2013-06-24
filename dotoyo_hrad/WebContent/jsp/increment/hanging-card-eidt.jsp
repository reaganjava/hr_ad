<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--增值服务设置--修改挂证服务设置</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<%@ include file="/jsp/top.jsp"%>
<div class="content">
<jsp:include page="/jsp/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>修改挂证服务设置</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="hangCardSetting!edit.action">
        <div class="inside-tab">
	 	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>套餐名称：</strong></td>
	                <td width="80%" height="40" align="left" class="tab-input">
	                	<s:hidden name="dto.serviceCode"/>
	                	<s:text name="dto.serviceName"/>
	                	<FONT color="red"><s:text name="serviceNameMsg"/></FONT>
	                </td>
	              </tr>
	              <tr>
	                <td width="20%" align="right" valign="middle"><strong>次数：</strong></td>
	                <td width="80%" align="left">
	                	<s:textfield name="dto.countOfSubmit" cssClass="validate[required,custom[onlyNumber]]" maxLength="9"></s:textfield>
	                	<FONT color="red"><s:text name="countOfSubmitMsg"/></FONT>
	                </td>
	              </tr>
	            </table>
			</div>
	       	<div class="system-botton">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="$('#pageFrom').submit()" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sys/hangCardSetting!cancel.action" title="取消">取消</a></li>
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
		$("#sys_increment_ul_hangcard a").addClass("active");
	}
</script>
