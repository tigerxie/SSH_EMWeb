package cn.tiger.shop.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUnit {

	public static ApplicationContext context = null;

	static {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

}
