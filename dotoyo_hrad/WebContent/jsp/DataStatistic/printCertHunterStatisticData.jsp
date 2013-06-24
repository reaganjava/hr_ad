<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>猎证统计报表</title>
<script src="<%=request.getContextPath()%>/javascripts/common/jquery.js"></script>
</head>
<body>
<table border="1" width="100%">
	<tr>
		<td>统计项</td>
		<td>数量</td>
	</tr>
	<tr>
		<td>个人提交证书数量</td>
		<td><s:property value="personalCertAmount" /></td>
	</tr>
	<tr>
		<td>企业证书需求数量</td>
		<td><s:property value="certNeedsAmount" /></td>
	</tr>
	<tr>
		<td>总计</td>
		<td><s:property value="certNeedsAmount+personalCertAmount" /></td>
	</tr>
</table>
<script type="text/javascript">
	$(document).ready(function() {
		window.print();
		window.close();
		//window.location.href = "dataStatisticAction!viewCertHunterStatistic.action?random="+new Date();
		//window.history.go(-1);
	});
</script>
</body>
</html>