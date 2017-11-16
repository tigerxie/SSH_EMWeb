package cn.tiger.shop.user.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tiger.shop.user.dao.UserDao;
import cn.tiger.shop.user.vo.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	/**
	 * 通过用户名查找
	 */
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		String hql = "from User where username=?";
		List<User> user = this.getHibernateTemplate().find(hql, username);
		if (user != null && user.size() > 0) {
			return user.get(0);
		}
		return null;
	}

	/**
	 * 保存用户
	 */
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 通过激活码查询
	 */
	@Override
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		String hql = "from User where code=?";
		List<User> user = this.getHibernateTemplate().find(hql, code);
		if (user != null && user.size() > 0) {
			return user.get(0);
		}
		return null;
	}

	/**
	 * 修改激活码
	 */
	@Override
	public void update(User exitUser) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(exitUser);
	}

	/**
	 * 查询用户
	 */
	@Override
	public User findUser(User user) {
		// TODO Auto-generated method stub
		String hql = "from User where username=? and password=? and state=?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(), user.getPassword(), 1);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
