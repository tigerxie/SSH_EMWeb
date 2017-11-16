package cn.tiger.shop.test;

import org.junit.Test;

import cn.tiger.shop.user.service.UserService;
import cn.tiger.shop.user.vo.User;
/**
 * 用户名模块测试类
 * @author tiger
 *
 */
public class UserTest extends SpringUnit {

	@Test
	public void testUser() {
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService);
		User user = userService.findByUsername("bbb");
		System.out.println(user);
		
	}
	
}
