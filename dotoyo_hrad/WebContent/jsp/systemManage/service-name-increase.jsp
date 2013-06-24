<%@page pageEncoding="utf-8"%>
<%@ include file="/jsp/systemManage/top.jsp"%>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<jsp:include page="/jsp/systemManage/left.jsp"></jsp:include>
    <div class="content-right">
    	<div class="administrator-title"><strong>增加服务名称设置</strong></div>
    	<s:form name="sysIncrementServiceNameForm" method="post" action="serviceNameMainAction!add.action">
        <div class="inside-tab">

   	    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab-input">
              <tr>
                <td width="12%" align="right" valign="middle"><strong>服务编码：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:textfield name="dto.serviceCode" maxLength="20"/>
                	<FONT color="red"><s:text name="serviceCodeMsg"/></FONT>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>服务名称：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:textfield name="dto.serviceName" maxLength="20" ></s:textfield>
                	<FONT color="red"><s:text name="serviceNameMsg"/></FONT>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>可用天数：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:textfield name="dto.usableDays" cssStyle="ime-mode:disabled" maxLength="4"
                	onkeypress="return limitNumberOnkeyPress(this,event)"
                	onpaste = "return limitNumberOnpaste(this)"
                	ondrop = "return limitNumberOndrop(this,event)"></s:textfield>
                	<FONT color="red"><s:text name="serviceUseAbleMsg"/></FONT>
                </td>
              </tr>
              <tr>
                <td width="12%" align="right" valign="middle"><strong>服务简介：</strong></td>
                <td width="88%" height="40" align="left">
                	<s:textarea name="dto.serviceIntro"></s:textarea>
                	<FONT color="red"><s:text name="serviceInfMsg"/></FONT>
                </td>
              </tr>
            </table>
			</div>
	       	<div class="system-botton">
	        	<ul>
	            	<li class="botton1"><a href="#" onclick="document.getElementsByName('sysIncrementServiceNameForm')[0].submit()" title="保存">保存</a></li>
	                <li class="botton3"><a href="<%=request.getContextPath()%>/sysIncrement/serviceNameMainAction!cancel.action" title="取消">取消</a></li>
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
		$("#sys_increment_ul_sysname a").addClass("active");
	}
</script>
