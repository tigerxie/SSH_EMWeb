package cn.tiger.shop.user.service.impl;

import org.springframework.transaction.annotation.Transactional;

import cn.tiger.shop.user.dao.UserDao;
import cn.tiger.shop.user.service.UserService;
import cn.tiger.shop.user.vo.User;
import cn.tiger.shop.utils.MailUtils;
import cn.tiger.shop.utils.UUIDUtils;

@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 通过用户名查询
	 */
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}
	
	/**
	 * 用户注册功能
	 */
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		/*
		 * 1. 补全uid(自增)、state、code
		 */
		user.setState(0);	//0:代表未激活，1:代表已激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code); //通过uuid生成32位随机字符
		/*
		 * 2. 调用dao完成保存
		 */
		userDao.save(user);
		/*
		 * 3. 发送激活邮件
		 */
		MailUtils.sendMail(user.getEmail(), code);
	}

	/**
	 * 用户激活功能
	 * 	>同过激活码查询用户
	 */
	@Override
	public User findByCode(String code) {
		// TODO Auto-generated method stub
		return userDao.findByCode(code);
	}

	/**
	 * 用户激活功能
	 * 	>修改激活转态
	 */
	@Override
	public void update(User exitUser) {
		// TODO Auto-generated method stub
		userDao.update(exitUser);
	}

	/**
	 * 用户登录功能
	 */
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.findUser(user);
	}
	
}
