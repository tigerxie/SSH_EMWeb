package cn.tiger.shop.test;

import java.util.List;

import org.junit.Test;

import cn.tiger.shop.category.service.CategoryService;
import cn.tiger.shop.category.vo.Category;

public class CategoryTest extends SpringUnit{
	@Test
	public void testCategory() {
		CategoryService categoryService = (CategoryService) context.getBean("categoryService");
		List<Category> list = categoryService.findAll();
		for (Category c : list) {
			System.out.println(c);
		}
	}
}
