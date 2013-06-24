<%@page pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大太阳建筑猎头--系统管理登录</title>
<%@ include file="/jsp/common.jsp"%>
</head>
<s:include value="/jsp/top.jsp"/>

<!--中间部分-->
<div class="administrator-login-box">
  <div class="administrator-login-title"><strong>系统管理登录</strong></div>
  	<s:form id="pageFrom" name="pageFrom" method="post" action="sysUserLogin!login.action">
    <div class="administrator-login">
          <ul>
              <li class="administrator0"><em>*</em>用户名：</li>
              <li class="administrator1"><s:textfield name="dto.userName" cssClass="validate[custom[nullCheckS]]"/>
              </li>
              <li class="administrator3"></li>
          </ul>
          <ul>
              <li class="administrator0"><em>*</em>密&nbsp;&nbsp;&nbsp;&nbsp;码：</li>
              <li class="administrator1"><s:password name="password" cssClass="validate[custom[nullCheckS]]"/></li>
              <li class="administrator3"></li>
          </ul>
          <ul>
              <li class="administrator0"><em>*</em>验证码：</li>
              <li class="administrator2"><s:textfield id="captcha" name="captcha" value="" cssClass="validate[custom[nullCheckS]]"/></li>
              <li class="administrator3" style="height:25px"><img src="<%=request.getContextPath()%>/jcaptcha" onclick="changeImage(this)" alt="click to refresh" style="cursor: pointer;" id="kaptchaImage" width="80" height="20" /></li>
          </ul>
       </div>
       <s:hidden name="msg" id="msg"/>
       <div class="jobs-botton">
       	<a href="#"><input id="dotoyoSubmit" type="image" src="<%=request.getContextPath()%>/images/administrator-login-botton.png" width="107" height="34" alt="登录" /></a>
       </div>
       </s:form>
	</div>
</body>
<script>
	if(document.getElementById("msg").value != ""){
		alert(document.getElementById("msg").value);
	}
	function changeImage(obj){
		obj.src="<%=request.getContextPath()%>"+"/jcaptcha?"+Math.random();
	}
	function captchaOnFocus(obj){
		var text = obj.value;
		if("验证码"==text){
			obj.value="";
		}
	}
	function captchaOnBlur(obj){
		var text = obj.value;
		if(""==text.trim()){
			obj.value="验证码";
		}
	}

</script>
</html>
