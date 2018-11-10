<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<title>Insert title here</title>
<script type="text/javascript" src="jquery-1.8.3.js"></script>

<script src="jquery.table2excel.js"></script>
<script>
	function songshen(id){
		//alert(id);
		/* var page = parseInt(pageNum);
		var allp = document.getElementById("allpage").value;
		var cp = document.getElementById("cp");
		var allpage = parseInt(allp);

		if(page<=0){
			cp.value = 1;
		}else if(page>allpage){
			cp.value = allpage;
		}else{
			cp.value = page;
		} */
		sale.action="pc/updatestate?status="+id;
		sale.submit();
	}
</script>
<script type="text/javascript">
	$(function () {
		   $("#ExportExcel").click(function(){
	  	  
	  	  $("#tab").table2excel({
	  		  exclude:$(".noExl"),
	  		  name:"Excel Document Name",
	  		  filename:generateRandowNumber(),
	  		  exclude_img:true,
	  	  	  exclude_links:true,
	  	  	  exclude_inputs:true
	  	  });
	  	  
	  	  
	    });
	    
	})
	
	function generateRandowNumber(){
		  var numbers = [0,1,2,3,4,5,6,7,8,9];
		  var chars = ["a","b","c","d","e","f","g","h","i","j"]
		  var index1 = parseInt(Math.random()*10);var index2 = parseInt(Math.random()*10);var index3 = parseInt(Math.random()*10);
		  var index4 = parseInt(Math.random()*10);var index5 = parseInt(Math.random()*10);var index6 = parseInt(Math.random()*10);
		  var index7 = parseInt(Math.random()*10);var index8 = parseInt(Math.random()*10);var index9 = parseInt(Math.random()*10);
		  var number = ""+numbers[index1]+chars[index2]+numbers[index3]+numbers[index4]+chars[index5]+numbers[index6]+numbers[index7]+chars[index8]+numbers[index9];
		  console.log(number);
		  return number;
	}
	function getPage(obj){
		var node = document.getElementById("getforPage");
		var page = parseInt(node.value);
		if(page!=null||page!=""){
			gotoPage(page);
		}
	}
	
function gotoPage(pageNum){
		
		//console.log(forSplitView);
		
		var page = parseInt(pageNum);
		var allp = document.getElementById("allpage").value;
		var cp = document.getElementById("cp");
		var allpage = parseInt(allp);

		if(page<=0){
			cp.value = 1;
		}else if(page>allpage){
			cp.value = allpage;
		}else{
			cp.value = page;
		}
		
		sale.submit();
	}
</script>
</head>
<body>
<h1>这里是退货表展示页面</h1>
<form action="pc/gogoods" method="post">
			搜索 ：退货订单编号：<input type="text" name="orderid" value="" ></br>
				客户编号：<input type="text" name="c_id" value="" ></br>
				客户名查询：<input type="text" name="c_name" value="" /></br>
				商品编号编号：<input type="text" name="material_id" value="" ></br>
			添加日期开始:<input type="text" name="query.qStartAddDate" class="Wdate" onclick="WdatePicker()" readonly="readonly" value="<fmt:formatDate value='${qStartAddDate }' pattern='yyyy-MM-dd'/>"></br>
	  		添加日期结束: <input type="text" name="query.qEndAddDate" class="Wdate" onclick="WdatePicker()" readonly="readonly" value="<fmt:formatDate value='${qEndAddDate }' pattern='yyyy-MM-dd'/>"></br>
			<input type="submit" value="搜索">
</form> 
<input type="button" id="ExportExcel" name="ExportExcel" value="导出数据"  /> 
<form id="sale" method="post" name="sale" action="" >
<input type="hidden" name="cp" id = "cp" value="1"/>
<input type="hidden" name="allpage" id= "allpage" value="${allpage }"/>
<table id="tab" border="1" bordercolor="red">
			<tr align="center">
			    <td class="noExl" style="width: 50px;"><input id="all" type="checkbox" /></td>
				<td>订单编号</td>
				<td>客户 编号</td>
				<td>客户 姓名</td>
				<td>客户 电话</td>
				<td>客户 地址</td>
				<td>客户购买数量</td>
				<td>商品编号</td>
				<td>商品名</td>
				<td>商品单价</td>
				<td>商品图片</td>
				<td>库存数量</td>
				<td>订单生成日期</td>
				<td>退货原因</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${lf }" var="l">
				<tr align="center">
					<td class="noExl"><input name="product" type="checkbox" value="${cus.userid } "/></td>
					<td>${l.returngoods.orderid}</td>
					<td>${l.returngoods.c_id }</td>
					<td>${l.customer.c_name }</td>
					<td>${l.customer.c_phone }</td>
					<td>${l.customer.c_address }</td>
					<td>${l.returngoods.goodscount }</td>
					<td>${l.material.material_id }</td>
					<td>${l.material.material_name }</td>
					<td>${l.material.material_price }</td>
					<td>${l.material.material_url }</td>
					<td>${l.material.material_sum }</td>
					<td><fmt:formatDate value="${l.returngoods.orderdate }" pattern="yyyy-MM-dd"/></td>
					<td>${l.returngoods.returnreason }</td>
					<td>${l.state.statename }</td>
					<td>
					<c:if test="${l.returngoods.stateid eq '0' }">
					<input type="button" value="退货" onclick="songshen(${l.returngoods.orderid})" />
					</c:if>
					
					</td>
				</tr>
			</c:forEach>
		</table>
		</form>
		<jsp:include page="${pageUrl }"></jsp:include>
		<table width="86%" border="0"  cellpadding="0" cellspacing="0">
        <tr>
          <td height="33">
          	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center">共 <span >${allpage }</span> 页 | 第 <span >${cp }</span> 页</td>
                <td width="49%" align="right">[<a href="javascript:gotoPage(1)" >首页</a> | <a href="javascript:gotoPage(${cp}-1)" >上一页</a> | <a href="javascript:gotoPage(${cp}+1)" >下一页</a> | <a href="javascript:gotoPage(${allpage })" >末页</a>] 转至：</td>
                	<td width="1%">
                		<table width="20" border="0" cellspacing="0" cellpadding="0">
                    		<tr>
                      			<td width="1%"><input id="getforPage" name="textfield3" type="text"  size="1" /></td>
                      			<td width="87%"><input name="Submit23222" type="button" value="Go" onclick="getPage();"/></td>
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