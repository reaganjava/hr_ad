<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>大太阳建筑猎头-设置沙龙首页</title>
	<s:include value="/jsp/common.jsp"/>
</head>
<s:include value="/jsp/top.jsp"/>
<!--以上为头部分-->
<!--中间部分-->
<div class="content">
<s:include value="/jsp/left.jsp"/>
 <div class="content-right">
    	<div class="administrator-title"><strong>设置沙龙首页</strong></div>
    	<div class="inside-tab">
    	<s:actionmessage cssStyle="color:blue"/>
        <s:form id="confirmForm" name="confirmForm" method="POST" action="salonAdminAction!setSalonHomePageDisplay.action">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
              <tr>
                <td width="6%" align="left"><strong>顺序号</strong></td>
                <td width="13%" align="left"><strong>沙龙名称</strong></td>
                <td width="12%" height="40" align="left"><strong>沙龙类型</strong></td>
                <td width="6%" align="left"><strong>组织者</strong></td>
                <td width="6%" align="left"><strong>状态</strong></td>
                <td width="10%" align="left"><strong>开始日期</strong></td>
                <td width="10%"  align="left"><strong>截止日期</strong></td>
                <td width="15%" align="left"><strong>地点</strong></td>

              </tr>
             <s:iterator value="salonList">
             <input id="updateIds" name="updateIds" type="hidden" value="<s:property value="id"/>"/>
              <tr>
                <td width="6%" align="left"><s:textfield id="updateOrders" name="updateOrders" size="2" maxlength="4" cssClass="requred number"/></td>
                <td width="13%" align="left"><s:property value="subject"/></td>
                <td width="12%" height="40" align="left"><s:property value="salonTypeText"/></td>
                <td width="6%" align="left"><s:property value="userName"/></td>
                <td width="6%" align="left"><s:property value="audiFlag"/></td>
                <td width="10%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{startDate})"/></td>
                <td width="10%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{endDate})"/></td>
                <td width="15%" align="left" class="inside-links"><s:property value="location"/></td>
              </tr>
            </s:iterator>
             <s:iterator value="settedSalonList">
             <input id="updateIds" name="updateIds" type="hidden" value="<s:property value="id"/>"/>
              <tr>
                <td width="6%" align="left"> <input id="updateOrders" name="updateOrders" type="text" value="<s:property value="intOrder"/>" size="2" maxlength="4"/></td>
                <td width="13%" align="left"><s:property value="subject"/></td>
                <td width="12%" height="40" align="left"><s:property value="salonTypeText"/></td>
                <td width="6%" align="center"><s:property value="userName"/></td>
                <td width="6%" align="center"><s:property value="audiFlag"/></td>
                <td width="10%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{startDate})"/></td>
                <td width="10%" align="left"><s:property value="getText('formatDate_yyyy-MM-dd',{endDate})"/></td>
                <td width="15%" align="left" class="inside-links"><s:property value="location"/></td>

              </tr>
            </s:iterator>

            </table>
          </s:form>
</div>

       	<div class="system-botton3">
        	<ul>
                <li class="botton1"><a href="#" onclick="submitForm(confirmForm)" title="确定">确定</a></li>
               <!--  <li class="botton2"><a href="#" onclick="submitFormChooseCheck(deleteForm,'arrayIds')" title="删除">删除</a></li> -->
                <li class="botton2"><a href="salonAdminAction!toAudiSalonPage.action"  title="返回">返回</a></li>
            </ul>
		</div>

  </div>


</div>

</body>
</html>