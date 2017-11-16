package cn.tiger.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.tiger.shop.adminuser.dao.AdminUserDao;
import cn.tiger.shop.adminuser.vo.AdminUser;

/**
 * 后台管理员模块业务逻辑层
 * @author tiger
 *
 */
@Transactional
public class AdminUserService {

	// 注入dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	/**
	 * 后台校验用户名密码
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		// TODO Auto-generated method stub
		AdminUser exitAdminUser = adminUserDao.findByUsernamePassword(adminUser.getUsername(), adminUser.getPassword());
		return exitAdminUser;
	}
	
}
