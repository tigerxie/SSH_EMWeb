package cn.tiger.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.tiger.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限拦截器
 * @author tiger
 *
 */
@SuppressWarnings("serial")
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// TODO Auto-generated method stub
		// 从session中获得adminuser
		AdminUser adminuser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("adminuser");
		/*
		 * 判断adminuser 
		 * 	>null，保存错误信息返回到登录页面
		 *  >否则，放行
		 */
		if (adminuser == null) {
			ActionSupport support = (ActionSupport) actionInvocation.getAction();
			support.addActionError("您还没有登录！没有访问权限！");
			return "loginFail";
		}
		return actionInvocation.invoke();
	}
}
