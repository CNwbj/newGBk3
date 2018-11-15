<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>顶部页面</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script>
     function myClock(){   //定义一个名为myClock的函数。
         var date = new Date();  //构造日期对象。
         document.getElementById("clock").innerHTML = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
     }
     window.setInterval(myClock,1000);  //间隔是1000ms，重复执行myClock函数中的内容。
 </script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="60" bgcolor="#99D9EA"><table width="99%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="1%"><img src="images/logo.png" width="560" height="60" border="0" /></a></td>
        <td width="64%" align="right" height="60">
	        <div>
	        	<span id="clock" style="font-size: 20px;"></span>
	    	</div>
    	</td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
