package cn.tiger.shop.user.service;

import cn.tiger.shop.user.vo.User;

public interface UserService {
	
	public User findByUsername(String username);

	public void save(User user);

	public User findByCode(String code);

	public void update(User exitUser);

	public User login(User user);
	
}
