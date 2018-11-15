<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basepath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户列表页面</title>
<style type="text/css">
</style>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">

</script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css"/>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.table2excel.js"></script>

<script type="text/javascript">
 	function getpage(num){
		
		var getforPage=document.getElementById("getforPage");
		var gonum=parseInt(getforPage.value);
		console.log("gonum"+gonum);
		if(gonum!=null&&gonum!=""){
			gonum=num;
			
			/* sale.submit(); */
		}	
	} 
/* 	function getPage(obj){ 
		var node = document.getElementById("getforPage");
		var page = parseInt(node.value);
		if(page!=null||page!=""){
			gotoPage(page);
		}
	} */
	
/* function gotoPage(pageNum){
		
		//console.log(forSplitView);
		
		var page = parseInt(pageNum);
		var allp = document.getElementById("pagecount").value;
		var cp = document.getElementById("page");
		var allpage = parseInt(allp);

		if(page<=0){
			cp.value = 1;
		}else if(page>allpage){
			cp.value = allpage;
		}else{
			cp.value = page;
		}
		
		sale.submit();
	} */
</script>
</head>
<body>
<div id="dlg"  >
	
</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30">      
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
        	<tr>
        		<!-- 搜索部分开始 -->
          		<td height="62" background="images/nav04.gif">
	          		<form action="qa/gofenye" method="post">
			   			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			    			<tr>
				  				<td width="21"><img src="images/ico07.gif" width="20" height="18" /></td>
				  				<td width="538">查看内容：
									客户姓名:	<select name="query.qcid">
							  					<option value="">--请选择--</option>
							  					<c:forEach items="${customlist }" var ="cl">
							  						<option value="${cl.c_id }" ${fy.query.qcid==cl.c_id?'selected':'' }> ${cl.c_name} </option>
							  					</c:forEach>
						  					</select>
						  			付款方式:<select name="query.qcpaymentid">
							  					<option value="">--请选择--</option>
							  					<c:forEach items="${paymentlist }" var ="pt">
							  						<option value="${pt.c_paymentid }" ${fy.query.qcpaymentid==pt.c_paymentid?'selected':'' }> ${pt.payment} </option>
							  					</c:forEach>
						  					</select>
						  				<input type="submit" value="搜索">	
								</td>
			    			</tr>
	          			</table>
          			</form>
          		</td>
          		<!-- 搜索部分结束 -->
        	</tr>
    	</table>
    </td>
   </tr>
  <tr>
    <td>
    	<table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        	<tr>
          		<td>
          			<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 			<!-- 表格部分开始 -->
              			<tr>
                			<td height="40" class="font42">
                			<form id="sale" method="post" name="sale" action="" enctype="multipart/form-data">
	                			<%-- <input type="hidden" name="page" id = "page" value="1"/>
								<input type="hidden" name="pagecount" id= "pagecount" value="${pagecount }"/> --%>								
								<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03" id="tab">
				 					<tr class="CTitle" >
                    					<td height="22" width="" colspan="23" align="center" style="font-size:16px">金蝶报价表</td>
                  					</tr>
                  					<tr bgcolor="#EEEEEE">
										<!-- <td class="noExl" style="width: 50px;"><input id="all" type="checkbox" /></td> -->
										<td>选项</td>
										<td>报价单编号</td>
										<td>物品名称</td>
										<td>付款方式</td>
										<td>求购数量</td>
										<td>客户报价</td>
										<td>客户名称</td>
										<td>联系电话</td>
										<td>客户住址</td>
										<td>报价日期</td>
										<td>有效期至</td>	
										<td>提交状态</td>
                  					</tr>
               						<c:forEach items="${qtList }" var="ql">		
									<tr bgcolor="#FFFFFF">	
										<td><input type="checkbox"  class="check" name="box"></td> 					
										<td>${ql.quotationid}</td>
										<td>${ql.material.material_name} </td>	
										<td>${ql.cpayment.payment} </td>
										<td>${ql.paycount} </td>
										<td>${ql.q_price} </td>					
										<td>${ql.customer.c_name}</td>
										<td>${ql.customer.c_phone} </td>					
										<td>${ql.customer.c_address}</td>
										
										<td>
											<fmt:formatDate value="${ql.quotation_date}" pattern="yyyy年MM月dd日" />
										 </td>	
										<td>
											<fmt:formatDate value="${ql.ofr_vail}" pattern="yyyy年MM月dd日" />
										</td>
										<td>${ql.state5.statename }</td>
								
									</tr>
								</c:forEach>
            					</table>
            				</form>
            				</td>
        				</tr>
        				<!-- 表格部分结束 -->
      				</table>
      				<%-- <jsp:include page="${pageUrl }"></jsp:include> --%>
      				<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        				<tr>
          					<td height="6"><img src="images/spacer.gif" width="1" height="1" /></td>
        				</tr>
        				<tr>
          					<td height="33">
									<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
										<tr>
											<td width="50%">
											
											
											<c:url var="Url" value="qa/gofenye">
											 	<c:param name="query.qcpaymentid" value="${fy.query.qcpaymentid }"></c:param>			
												<c:param name="query.qcpaymentid" value="${fy.query.qcpaymentid }"></c:param>							
											</c:url> 
												共 <span class="right-text09">${fy.pageCount }</span> 页 | 
												第 <span class="right-text09">${fy.page }</span> 页
											</td>
												<td width="49%" align="right">
													[<a href="${Url }&page=1">首页</a> | 
													<c:choose>
														<c:when test="${fy.page==1 }">
															上一页
														</c:when>
														<c:otherwise>
															<a href="${Url }&page=${fy.page-1}">上一页</a>
														</c:otherwise>
													</c:choose>| 
													<c:choose>
														<c:when test="${fy.page==fy.pageCount }">
															下一页
														</c:when>
														<c:otherwise>
															<a href="${Url }&page=${fy.page+1}">下一页</a>
														</c:otherwise>
													</c:choose> | 
													<a href="${Url }&page=${fy.pageCount}">尾页</a>]
												</td>
											<td width="1%">
												 <table width="20" border="0" cellspacing="0" cellpadding="0">
						 							<tr>
						 								<td width="1%">
       														<input id="getforPage" name="textfield3" type="text"  size="1" />
						 								</td>
						 								<td width="87%">
       														<input name="Submit23222" type="button" value="Go" onclick="getPage(num);"/>
						 								</td>
						 							</tr>
												</table> 
											</td> 
										</tr>
									</table>
										
          						<!-- 分页部分结束 -->
          					</td>
        				</tr>
      				</table>
      			</td>
  			</tr>
		</table>
	</td>
</tr>
</table>
</body>
</html> 
