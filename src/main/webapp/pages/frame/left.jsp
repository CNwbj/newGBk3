<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<html>
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>左边菜单页面</title>
<link rel="stylesheet" type="text/css" href="css/zzsc.css">
<link rel="stylesheet" href="css/dcalendar.picker.css"/>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js" ></script>
<script type="text/javascript">
$(function(){
	$(".firstmenu").click(function(){
		var allchildtable = $(".left-table02");
		var pid = $(this).attr("id");
		var childtb = $("#subtree_"+(pid  || "").split("_")[1]);
		
		if(childtb.css("display")=="none"){
			childtb.css("display","block");
		}else{
			childtb.css("display","none");
		}
		
		var allchildtb = $(".left-table02");
		for(var i=0;i<allchildtb.length;i++){
			if(allchildtb[i].id!="subtree_"+(pid  || "").split("_")[1]){
				$(allchildtb[i]).css("display","none");
			}
		}
	});
	
	//单击子菜单时，需要修改子菜单前面的图标
	$(".childmenu").click(function(){
		var allchild = $(".childmenu");
		for(var i=0;i<allchild.length;i++){
			if($(this).html()==$(allchild[i]).html()){
				$(this).parent().prev().children("img").attr("src","images/ico05.gif");
			}else{
				$(allchild[i]).parent().prev().children("img").attr("src","images/ico06.gif");
			}
		}
	});
});
</script>
<script type="text/javascript">
$(function(){
	setTimeout(function() {
		var maxTime = 1800; /* 时间,second */
		var time = maxTime;
		document.body.addEventListener('mousemove', function() {
			time = maxTime;  /* 鼠标移动,重置时间 */
		}, false);
		var intervalId = setInterval(function() {
			time--;
			if(time <= 0) {
				window.top.location.href="uc/logout"/* 跳出此页面上层跳转页面 */
				clearInterval(intervalId);
			}
		}, 1000);
	}, 5000);
});
</script>
</head>
<body>
<table width="198" border="0" cellpadding="0" cellspacing="0" class="left-table01">
  <tr>
    <TD>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="207" height="55" background="images/nav01.gif">
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					<td width="25%" rowspan="2"><img src="images/ico02.gif" width="35" height="35" /></td>
					<td width="75%" height="22" class="left-font01">您好，<span class="left-font02">${loginname }</span></td>
				  </tr>
				  <tr>
					<td height="22" class="left-font01">
						[&nbsp;<a href="uc/logout" target="_top" class="left-font01">退出</a>&nbsp;]</td>
				  </tr>
				</table>
			</td>
		  </tr>
		</table>
		<!--  任务系统开始    -->
		<c:forEach items="${perlist }" var="first" varStatus="p" >
		<c:if test="${first.parentid==0 }">
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img8" id="img8" src="images/ico04.gif" width="8" height="11" /></td>
						<td width="100%">
								<a href="javascript:void(0);" target="mainFrame" class="left-font03 firstmenu" id="first_${p.count }"  >${first.pname }</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		
		<table id="subtree_${p.count}" style="display:none;" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
			<c:forEach items="${perlist }" var="child">
			<c:if test="${child.parentid==first.perid }">
			<tr width="100">
				<td width="9%" height="20" ><img  src="images/ico06.gif" width="8" height="12" /></td>
				<td width="100%"><a href="${child.perurl }" target="mainFrame" class="left-font03 childmenu"><font color="black">${child.pname }</font></a></td>
			</tr>
			</c:if>
			</c:forEach>
      </table>
      
      </c:if>
      </c:forEach>
		<!--  任务系统结束    -->
	  </TD>
	  
  </tr>
  </table>
	  <div class="zzsc-container">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<h3></h3>
					<table id='mycalendar' class='calendar'></table>
				</div>
				<div class="col-md-6">
				</div>
			</div>
		</div>
	</div>
	
	<script src="js/jquery-1.11.0.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/dcalendar.picker.js"></script>
	<script type="text/javascript">
		$('#mycalendar').dcalendar();
	</script>
</body>
</html>

