package cn.tiger.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tiger.shop.adminuser.vo.AdminUser;

/**
 * 后台管理员模块持久层
 * @author tiger
 *
 */
public class AdminUserDao extends HibernateDaoSupport{

	/**
	 * 通过用户名和密码查询
	 * @param username
	 * @param password
	 * @return
	 */
	public AdminUser findByUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		String hql = "from AdminUser where username=? and password=?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql, username, password);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
