package cn.tiger.shop.user.dao;

import cn.tiger.shop.user.vo.User;

public interface UserDao {
	
	public User findByUsername(String username);

	public void save(User user);

	public User findByCode(String code);

	public void update(User exitUser);

	public User findUser(User user);
	
}
