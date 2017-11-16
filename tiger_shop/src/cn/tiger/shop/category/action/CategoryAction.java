package cn.tiger.shop.category.action;

import cn.tiger.shop.category.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 分类模块表示层
 * @author tiger
 *
 */
public class CategoryAction extends ActionSupport{
	
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
}
