package cn.tiger.shop.test;

import java.util.List;

import org.junit.Test;

import cn.tiger.shop.order.service.OrderService;
import cn.tiger.shop.order.vo.Order;
import cn.tiger.shop.order.vo.OrderItem;
import cn.tiger.shop.product.dao.ProductDao;
import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.user.service.UserService;
import cn.tiger.shop.user.vo.User;
import cn.tiger.shop.utils.PageBean;
/**
 * 用户名模块测试类
 * @author tiger
 *
 */
public class ShopTest extends SpringUnit {

	@Test
	public void testUser() {
		UserService userService = (UserService) context.getBean("userService");
		System.out.println(userService);
		User user = userService.findByUsername("bbb");
		System.out.println(user);
		
	}
	
	@Test
	public void testProduct() {
		ProductService productService = (ProductService) context.getBean("productService");
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		
		PageBean<Product> pb = productService.findByCidPage(1, 1);
		for (Product p : pb.getBeanList()) {
			System.out.println(p);
		}
	}
	@Test
	public void testProduct2() {
		ProductService productService = (ProductService) context.getBean("productService");
	}
	
	@Test
	public void testProduct1() {
		OrderService orderService = (OrderService) context.getBean("orderService");
		PageBean<Order> pb = (PageBean<Order>)orderService.myOrder(12, 1);
		for (Order p : pb.getBeanList()) {
			System.out.println(p.getOrderItems());
		}
	}
	@Test
	public void testProduct4() {
		OrderService orderService = (OrderService) context.getBean("orderService");
		List<OrderItem> list = orderService.findOIByOid(9025);
		System.out.println(list.size());
	}
	
}
