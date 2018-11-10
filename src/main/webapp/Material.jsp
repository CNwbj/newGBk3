<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=<%=basePath%> />
<script type="text/javascript" src="jquery-1.8.3.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息中心</title>
<style type="text/css">
a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	function batchDel(){
		var arr = [];
		var selects = document.getElementsByName("selectBooks");	
		for(var i=0;i<selects.length;i++){
			console.log("a");
			
			if(selects[i].checked){
				arr.push(selects[i].value)
			}
		}
		console.log(arr.toString());
		document.location = "ml/dele?arr="+arr.toString();
	}
</script>

<script type="text/javascript">
 	onload = function(){
 		var pagination = geld("pagination").value
 		if(pagination==""){
 			document.location="ml/fy";
 		}
		
	} 
function geld(id){
	return document.getElementById(id);
}

<!--分页的方法 -->
function gotoPage(pageNum){
	var pagenum = parseInt(pageNum);
	var allpag = parseInt(geld("pag").value);
	/* alert(allpag); */
	var page = geld("pagination");
	/* alert(page); */
	if(pagenum<=1){
		page.value = 1
	}else if(pagenum>=allpag){
		page.value = allpag;
	}else{
		page.value = pagenum;
	}
	lookml.submit();
}
function a(){
	var page = geld("pagination");
	page.value = 1;
	book.submit();
}
</script>
</head>
<body style="text-align: center;">
	<div style="width: 1000px; height: 700px;">
		<form action="" name="lookml" id="lookbook" method="GET">
			<input type="hidden" name="pagination" id="pagination" value="${pagination }" /> 
			<input type="hidden" name="pag" id="pag"value="${allpag }" />
																<br /> 
			搜索文档:<input type="text" name="linchpin" value="${linchpin }" /> 
																<br /> 
			价格搜索:<input type="text" name="sprice" value="${sprice }" />-<input type="text" name="tprice" value="${tprice }" /> 
			<input type="submit" value="搜索" onclick="a();" /><br />
		</form>
			
			<table border="1" align="center">
				<tr>
					<th >选择</th>
					<th >编号</th>
					<th >物资名</th>
					<th >价格</th>
					<th >库存量</th>
					<th>图片</th>
					<th >仓库地址</th>
					<th >物资详情</th>
					<th>仓库状态</th>
					<th >操作</th>
				</tr>
				<c:if test="${!empty mllist}">
					<c:forEach items="${mllist }" var="ml">
					<tr>
						<td width="5%"><input type="checkbox" name="selectBooks" value="${ml.material_id }"/></td>
						<td width="5%">${ml.material_id }</td>
						<td width="10%">${ml.material_name }</td>
						<td width="5%">${ml.material_price}</td>
						<td width="10%">${ml.depot.depot_sum}</td>
						<td width="10%"><img style="width: 250px;height: 150px;" src="${ml.material_url}"/></td>
						<td width="15%">${ml.depot.depot_name}</td>
						<td width="20%">${ml.material_details}</td>
						<td width="10%">${ml.state.statename}</td>
						<td width="10%"><a href="javascript:batchDel();">增加</a>|<a href="bc/baler?id=${book.bookid }">修改</a></td>
					</tr>
					</c:forEach>
				</c:if>
			</table>
			<table align="center">
		<tr align="center">
			<td><input type="button" name="firstPage" value="首页" onclick="gotoPage(1);"/></td>
			<td><input type="button" name="previous" value="上一页" onclick="gotoPage(${pagination}-1);"/></td>
			<td>总共:${allpag }页&nbsp;&nbsp;&nbsp; 当前第:${pagination }页</td>
			<td><input type="button" name="next" value="下一页" onclick="gotoPage(${pagination}+1);"/></td>
			<td><input type="button" name="last" value="最后一页" onclick="gotoPage(${allpag});"/></td>
		</tr>
	</table>
</div>
</body>
</html>