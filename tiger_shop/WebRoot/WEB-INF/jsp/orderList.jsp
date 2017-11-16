<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>订单页面</title>
<link href="${pageContext.request.contextPath }/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<%@include file="menu.jsp" %>

<div class="container cart">

		<div class="span24">
		
			<div class="step step1">
				<ul>
					
					<li  class="current"></li>
					<li  >我的订单显示</li>
				</ul>
			</div>
				<s:iterator value="pageBean.beanList" var="order">
					<tr>
						<th colspan="5">订单编号:<s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;&nbsp;订单金额:<font
							color="red"><s:property value="#order.total" />
						</font>
						&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">
							<s:if test="#order.state == 1">
								<a href="${ pageContext.request.contextPath }/order_findByOid.action?oid=<s:property value="#order.oid" />">付款</a>
							</s:if>
							<s:if test="#order.state == 2">
								已付款
							</s:if>
							<s:if test="#order.state == 3">
								<a href="${ pageContext.request.contextPath }/order_updateState.action?oid=<s:property value="#order.oid" />">确认收货</a>
							</s:if>
							<s:if test="#order.state == 4">
								交易成功
							</s:if>
						</font>
						</th>
					</tr>
				<table>
					<tbody>
					<tr>
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
					</tr>
					<s:iterator var="orderItem" value="#order.orderItems">
						<tr>
							<td width="60">
								<input type="hidden" name="id" value="22"/>
								<img src="${pageContext.request.contextPath }/<s:property value="#orderItem.product.image"/>"/>
							</td>
							<td>
								<a target="_blank"><s:property value="#orderItem.product.pname"/></a>
							</td>
							<td>
								<s:property value="#orderItem.product.shop_price"/>
							</td>
							<td class="quantity" width="60">
								<input type="text" name="count" value="<s:property value="#orderItem.count"/>" maxlength="4" onpaste="return false;"/>
								<div>
									<span class="increase">&nbsp;</span>
									<span class="decrease">&nbsp;</span>
								</div>
							</td>
							<td width="140">
								<span class="subtotal">￥<s:property value="#orderItem.subtotal"/></span>
							</td>

						</tr>
					</s:iterator>
				</tbody>
			</table>
			</s:iterator>
		</div>
		<div class="pagination">
			<s:if test="pageBean.page > 1">
				<a href="${pageContext.request.contextPath }/order_myOrder.action?page=1" class="firstPage">&nbsp;</a>
				<a href="${pageContext.request.contextPath }/order_myOrder.action?page=<s:property value="pageBean.page - 1"/>" class="previousPage">&nbsp;</a>
			</s:if>
			<s:iterator var="i" begin="1" end="pageBean.totalPage">
				<s:if test="pageBean.page == #i">
					<a href="${pageContext.request.contextPath }/order_myOrder.action?page=<s:property value="#i"/>" class="currentPage"><s:property value="#i"/></a>
				</s:if>
				<s:else>
					<a href="${pageContext.request.contextPath }/order_myOrder.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
				</s:else>
			</s:iterator>
			<s:if test="pageBean.page < pageBean.totalPage">
				<a class="nextPage" href="${pageContext.request.contextPath }/order_myOrder.action?page=<s:property value="pageBean.page + 1"/>">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath }/order_myOrder.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
			</s:if>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>
