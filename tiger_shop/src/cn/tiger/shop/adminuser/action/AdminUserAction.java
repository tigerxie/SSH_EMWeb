package cn.tiger.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.tiger.shop.adminuser.service.AdminUserService;
import cn.tiger.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	// 注入service
	private AdminUserService adminUserService;
	// 模型驱动封装AdminUser对象
	private AdminUser adminUser = new AdminUser();

	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	/**
	 * 管理员登录功能
	 * @return
	 */
	public String login() {
		/*
		 * 后台校验用户名密码
		 */
		AdminUser exitAdminUser = adminUserService.login(adminUser);
		if (exitAdminUser == null) {
			this.addActionError("用户名或密码错误！");
			return "adminLogin";
		}
		/*
		 * 校验成功，保存到session
		 */
		ServletActionContext.getRequest().getSession().setAttribute("adminuser", exitAdminUser);
		return "loginSuccess";
	}

}
