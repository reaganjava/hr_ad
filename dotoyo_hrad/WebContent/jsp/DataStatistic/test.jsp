<%@ page contentType="application/msexcel; charset=gbk" pageEncoding="gbk" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
   response.setHeader("Content-disposition","attachment; filename=test1.xls");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Excel�������ַ�ʽ</title>
</head>
<body>
  <table border="1" width="100%">
    <tr>
      <td>����</td><td>���֤�ֺ�</td><td>����</td>
    </tr>
    <tr>
      <td>����</td><td>N111111111</td><td>1900/11/12</td>
    </tr>
    <tr>
      <td>������</td><td>N222222222</td><td>1923/10/1</td>
    </tr>
    <tr>
      <td>�Ż���</td><td>N333333333</td><td>1934/12/18</td>
    </tr>
  </table>
</body>
</html>





