<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img src="${pageContext.request.contextPath }/image/r___________renleipic_01/logo3.gif" alt="网上商城"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath }/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.user == null">
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
						<a href="${ pageContext.request.contextPath }/user_loginPage.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${ pageContext.request.contextPath }/user_registPage.action">注册</a>|
					</li>
				</s:if>
				<s:else>
					<li id="headerUsername" class="headerUsername" style="display: list-item;">
						<s:property value="#session.user.name"/>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/order_myOrder?page=1">我的订单</a>|
					</li>
					<li id="headerLogout" class="headerLogout" style="display: list-item;">
						<a href="${ pageContext.request.contextPath }/user_quit.action">退出</a>|
					</li>
				</s:else>

						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${ pageContext.request.contextPath }/cart_myCart">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>10086/1008611</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${ pageContext.request.contextPath }/index.action">首页</a>
						|
					</li>
					<s:iterator var="category" value="#session.categoryList">
						<li>
							<a href="${ pageContext.request.contextPath }/product_findByCidPage.action?cid=<s:property value="#category.cid"/>&page=1"><s:property value="#category.cname"/></a>
							|
						</li>
					</s:iterator>
		</ul>
	</div>

</div>	