<%@ page contentType="application/msexcel; charset=gbk" pageEncoding="gbk" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
   response.setHeader("Content-disposition","attachment; filename=test1.xls");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Save as Excel</title>
</head>
<body>
  <table border="1" width="100%" style="display:none;">
    <tr>
      <td>ͳ����</td>
      <td>����</td>
    </tr>
    <tr>
      <td>�����ύ֤������</td>
      <td><s:property value="personalCertAmount"/></td>
    </tr>
    <tr>
      <td>��ҵ֤����������</td>
      <td><s:property value="certNeedsAmount"/></td>
    </tr>
    <tr>
      <td>�ܼ�</td>
      <td><s:property value="certNeedsAmount+personalCertAmount"/></td>
    </tr>
  </table>
</body>
</html>