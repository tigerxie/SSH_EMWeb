<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="span6">
	<div class="hotProductCategory">
		<s:iterator var="c" value="#session.categoryList">
			<dl>
				<dt>
					<a href="${pageContext.request.contextPath }/product_findByCidPage.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
				</dt>
					<s:iterator var="cs" value="#c.categorySeconds">
						<dd>
							<a href="${pageContext.request.contextPath }/product_findByCsidPage.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property value="#cs.csname"/></a>
						</dd>
					</s:iterator>
			</dl>
		</s:iterator>
	</div>
</div>
