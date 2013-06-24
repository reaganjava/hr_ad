<%@page pageEncoding="utf-8"%>
<%@ include file="/jsp/systemManage/top.jsp"%>
<div class="content">
<%@ include file="/jsp/systemManage/left.jsp"%>
    <div class="content-right">
    	<div class="administrator-title"><strong>招聘服务设置</strong></div>
    	<s:form id="pageFrom" name="pageFrom" method="post" action="recruitSetting!search.action">
        <div class="inside-search">
        	<ul>
            	<li><strong>服务名称</strong>：<s:textfield name="searchdto.serviceName" size="17" maxLength="20"/></li>
                <li><a href="#" onclick="document.getElementsByName('pageFrom')[0].submit()"><img src="<%=request.getContextPath()%>/images/inside-botton.png" width="68" height="25" /></a></li>
            </ul>
      	</div>

        <div class="inside-tab">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="13%" align="left" class="tab-padding"><strong>服务名称</strong></td>
                <td width="13%" align="left"><strong>价格</strong></td>
                <td width="15%" align="left"><strong>发布职位次数</strong></td>
                <td width="16%" align="left"><strong>查看博站次数</strong></td>
                <td width="18%" height="40" align="left"><strong>邀请面试次数</strong></td>
                <td width="12%"  align="left"><strong>操作人</strong></td>
                <td width="13%" align="left"><strong>操作</strong></td>
              </tr>
              <s:if test="dtoList!=null">
              	<s:iterator value="dtoList" var="dto">
	              <tr>
	                <td width="13%" align="left" class="tab-padding"><s:text name="serviceName"/>&nbsp;</td>
	                <td width="13%" align="left">
	                	<s:if test="price==0">
	                		面议
	                	</s:if>
	                	<s:else>
	                		<s:text name="price"/>&nbsp;
	                	</s:else>
	                </td>
	                <td width="15%" align="left"><s:text name="timesOfPublicPosition"/>&nbsp;</td>
	                <td width="16%" align="left"><s:text name="timesOfLookupBlog"/>&nbsp;</td>
	                <td width="18%" align="left"><s:text name="timesOfLookupInvite"/>&nbsp;</td>
	                <td width="12%" align="left"><s:text name="operatorName"/>&nbsp;</td>
	                <td width="13%" align="left" class="inside-links">
	                	<a href="#" onclick="beforeEdit('<s:text name="serviceCode"/>','<s:text name="serviceName"/>','<s:text name="timesOfPublicPosition"/>','<s:text name="timesOfLookupBlog"/>','<s:text name="timesOfLookupInvite"/>','<s:text name="price"/>')">修改</a>
	                	&nbsp;&nbsp;
	                	<a href="#" onclick="remove('<s:text name="serviceCode"/>')">删除</a>
	                </td>
	              </tr>
              	</s:iterator>
              </s:if>
            </table>
    	</div>
        <div class="qx" >
           <s:property value="pageInfo.htmlListTableFootInfoNoAllCheck" escapeHtml="false"/>
        </div>
       	<div class="shanchu"><a href="<%=request.getContextPath()%>/sysIncrement/recruitSetting!beforeAdd.action">增加</a></div>
		<div class="num">
		   <s:property value="pageInfo.htmlPagingInfo" escapeHtml="false"/>
		</div>
       	</s:form>
       	<div style="display:none">
       		<s:form name="sysIncrementRecruitFormEdit" method="post" action="recruitSetting!beforeEdit.action">
				<s:hidden name="dto.serviceCode"/>
				<s:hidden name="dto.serviceName"/>
				<s:hidden name="dto.timesOfPublicPosition"/>
				<s:hidden name="dto.timesOfLookupBlog"/>
				<s:hidden name="dto.timesOfLookupInvite"/>
				<s:hidden name="dto.price"/>
       		</s:form>
       		<s:form name="sysIncrementRecruitFormRemove" method="post" action="recruitSetting!remove.action">
				<s:hidden name="dto.serviceCode"/>
       		</s:form>
       		<s:text name="msg"/>
       	</div>
  </div>
</div>
<script type="text/javascript">
	function beforeEdit(){
		document.getElementsByName("dto.serviceCode")[0].value=arguments[0];
		document.getElementsByName("dto.serviceName")[0].value=arguments[1];
		document.getElementsByName("dto.timesOfPublicPosition")[0].value=arguments[2];
		document.getElementsByName("dto.timesOfLookupBlog")[0].value=arguments[3];
		document.getElementsByName("dto.timesOfLookupInvite")[0].value=arguments[4];
		document.getElementsByName("dto.price")[0].value=arguments[5];
		document.getElementsByName("sysIncrementRecruitFormEdit")[0].submit();
	}
	function remove(){
		if(confirm("你确定要删除这条服务的设置吗?")){
			document.getElementsByName("dto.serviceCode")[1].value=arguments[0];
			document.getElementsByName("sysIncrementRecruitFormRemove")[0].submit();
		}
	}
	function init(){
		$("#sys_increment_h3").addClass("ui-state-active").next().addClass("ui-accordion-content-active").show();
		$("#sys_increment_ul_recruit a").addClass("active");
	}
</script>
</body>
</html>
