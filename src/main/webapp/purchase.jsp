<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html >

<html>
	<head>
		<base href="<%=basePath%>" />
		<meta charset="utf-8">
		<title>购买</title>
		<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/bootstrap-theme.css" />
		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.js"></script>
		<script>
		onload = function(){
	 		var pagination = geld("pagination").value
	 		if(pagination==""){
	 			document.location="ca/kehufy";
	 		}
			
		} 
		
		
			$(function(){
				let arrI = $(".applyDiscount");
				for(let i=0;i<arrI.length;i++){
					$(arrI[i]).click(function(){
						let parentWant = $(this).parents().parents().parents().parents().parents().children(".panel-body").children().first().children().first().next().children().first().children().first().first().children().first();
						
						$("#materialID").val($(parentWant).val());
						$("#customerprice").val($(parentWant).next().val());
						
					});
				}
				
				 $("#sendCustomerPrice").click(function(){
					 
					 let customerPrice = $("#customerprice").val();
					 let c_paymentid = $("#paymentID").val();
					 let c_id = $("#c_id").val();
					 let material_id = $("#materialID").val();
					 
					 console.log(c_id);
					 console.log($("#materialID").val());
					 console.log($("#paymentID").val());
					 console.log(customerPrice);
					 
					  $.post('tpa/itbp',{"customerprice":customerPrice,"material_id":material_id,"c_paymentid":c_paymentid,"c_id":c_id},function(data){
							
						 if(data==true){
								alert("议价成功,请下订单购买,后台会自动为您修改该商品单价!");
								$("#modal-805551").attr("disabled","disabled");
								$("#sendCustomerPrice").attr("disabled","disabled");
							}else{
								alert("议价失败,您提供的价格太低我们无法接受!")
							}
							
						},"json"); 
					 
					/*  $.ajax({
						 url:"tpa/itbp",
						 type:"POST",
						 dataType:"JSON",
						 success:function(){
							 alert("1");
						 },
						 error:function(){
							 alert("2");
						 }
					 }); */
					 
				 });
					
				
				$(".commitorder").click(function(){
					var $par = $(this).parent();
					var arrs = $par.children();
					for(let i=0;i<arrs.length;i++){
						if($(arrs[i]).is(":hidden")){
							
							$(arrs[i]).removeClass("hide");
						}else{
							
							$(arrs[i]).hide();
						}
					}
				});
				
				$(".miniCount").click(function(){
					
					let currentCount = parseInt($(this).next().val())-1;
					
					if(currentCount==0){
						$(this).next().val("1");
					}else{
						$(this).next().val(currentCount);
					}
				});
				
				$(".plusCount").click(function(){
					let currentCount = parseInt($(this).prev().val())+1;
					$(this).prev().val(currentCount);
				});
			});
			
		function giveValueTopament(pamentMet){
			$("#paymentID").val(pamentMet);
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
	
		
		/* 提交订单 */
		function tijiao(material_id){
			var c_id = geld("c_id").value;
			var sendaddr = geld("c_address").value;	
			var goodscount =$("#count"+material_id).val();
			var material_price = $("#material_price"+material_id).val();
			var realprice = parseInt(goodscount)*parseFloat(material_price);
			var c_paymentid= $("#payMethod"+material_id).val();
			//document.location="pc/submit?c_id="+c_id+"&material_id="+material_id+"&sendaddr="+sendaddr+"&goodscount="+goodscount+"&realprice="+realprice+"&c_paymentid="+c_paymentid;
			if (c_id!=null&c_id!='') {
				$.post('pc/submit',{"c_id":c_id,"material_id":material_id,"sendaddr":sendaddr,"goodscount":goodscount,"c_paymentid":c_paymentid,"realprice":realprice},function(data){
					if(data.flag){
						alert("购买成功！");
						location="cate_pencil.jsp";
					}
				},'json');
			}else{
				alert("您还没登录账号，请先登录，谢谢！");
				location="login_in.jsp";
			} 
			
		}
		
		</script>
	</head>
	<body>
		<div id="xxxxxx"></div>
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
				
				<!-- 商品详情开始 -->
						<form action="" name="lookml" id="lookbook" method="GET">
							<input type="hidden" name="pagination" id="pagination" value="${pagination }" /> 
							<input type="hidden" name="pag" id="pag" value="${allpag }" /><br />
							<input type="hidden" name="" id="" value="" /><br />
						</form>
						
						<input id="materialID"  value="" type="hidden"/>
						<input id="paymentID" value="1" type="hidden">
						
						<c:if test="${!empty mllist}">
						<c:forEach items="${mllist }" var="ml">
						<div class="panel panel-default"  >
							<div class="panel-heading">
								<h3 class="panel-title">
									${ml.material_name }
								</h3>
							</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-3"><img class="img-rounded img-responsive" src="${ml.material_url }" /></div>
								<div class="col-md-9">
									<dl>
										<dt><input type="hidden" name="pag" id="material_id" value="${ml.material_id }" /><input type="hidden" name="priceOri" value="${ml.material_price }" /></dt>
										<dd>${ml.material_details }</dd>
										<dd>价格：${ml.material_price }￥<input type="hidden" name="pag" id="material_price${ml.material_id}"value="${ml.material_price }" /></dd>
									</dl>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<div class="row">
								<div class="col-md-8">
									<div class="col-md-4">
										<a class="btn btn-default miniCount" role="button"><span class="glyphicon glyphicon-minus"></span></a>
										<input id="count${ml.material_id}" type="botton" style="width:40px;" class="btn btn-default" name="count" value="1" /> <!-- 购买商品数量 -->
										<a class="btn btn-default plusCount" role="button"><span class="glyphicon glyphicon-plus"></span></a>
									</div>
									<div class="col-md-8">
										<div class="form-group">
												支付方式:
											<select id="payMethod${ml.material_id}" onchange="giveValueTopament(this.value)">
												<c:forEach items="${clist }" var="c">
												<option value="${c.c_paymentid }">${c.payment } </option>												
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="col-md-6 connect">
										<a  class="btn applyDiscount" href="#modal-container-805551" role="button" data-toggle="modal"><span class="glyphicon glyphicon-headphones">申请议价</span></a>
									</div>
									<div class="col-md-6 hide returngood">
										<button class="applyReturn" onclick="tuihuo();"><span class="glyphicon glyphicon-headphones">申请退货</span></button>
									</div>
									<div class="col-md-6 hide confirmgood">
										<button class="confirmRecieveGood" onclick="shouhuo();"><span class="glyphicon glyphicon-shopping-cart" >确认收货<span></button>
									</div>
									<div class="col-md-6 commitorder">
										<button class="chuckOrders"  onclick="tijiao(${ml.material_id });"><span class="glyphicon glyphicon-shopping-cart">提交订单<span></button>
									</div>
								</div>
							</div>
						</div>
						</div>	
					</c:forEach>
				</c:if>
				<!-- 议价盒子 -->
				<div class="modal fade" id="modal-container-805551" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" id="modal-container-805551">
								<div class="modal-content">
										<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
												<h4 class="modal-title" id="myModalLabel">
													提议价格
												</h4>
										</div>
								<div class="modal-body">
								<input type="number" name="customerprice" id="customerprice" value=""/>
								</div>
								<div class="modal-footer">
									<!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  -->
									<button type="button" class="btn btn-primary" data-dismiss="modal" id="sendCustomerPrice">发送</button>
								</div>
								</div>
						</div>
				</div>	
					
				<!-- 商品详情结束！ -->	
					<table align="center" class="pagination">
						<tr align="center">
							<td><input type="button" name="firstPage" value="首页" onclick="gotoPage(1);"/>&nbsp;&nbsp;</td>
							<td><input type="button" name="previous" value="上一页" onclick="gotoPage(${pagination}-1);"/>&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;第:${pagination }页&nbsp;&nbsp;</td>
							<td><input type="button" name="next" value="下一页" onclick="gotoPage(${pagination}+1);"/>&nbsp;&nbsp;</td>
							<td><input type="button" name="last" value="最后一页" onclick="gotoPage(${allpag});"/>&nbsp;共:${allpag }页</td>
						</tr>
					</table>
				</div>
				<input type="hidden" name="pag" id="c_id"value="${customer.c_id }" /><input type="hidden" name="pag" id="c_address"value="${customer.c_address }" />
			</div>
		</div>
	</body>
</html>
