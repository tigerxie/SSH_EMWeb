package cn.tiger.shop.category.adminaction;

import java.util.List;

import cn.tiger.shop.category.service.CategoryService;
import cn.tiger.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 管理模块一级分类Action
 * @author tiger
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	// 注入一级分类service
	private CategoryService categoryService;
	// 模型驱动封装对象
	private Category category = new Category();
	
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * 查询所有一级分类
	 * @return
	 */
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "list";
	}
	
	/**
	 * 添加一级分类
	 */
	public String save() {
		categoryService.save(category);
		return "save";
	}
	
	/**
	 * 删除一级分类
	 * @return
	 */
	public String delete() {
		categoryService.delete(category.getCid());
		return "delete";
	}
	
	/**
	 * 预编辑一级分类
	 * @return
	 */
	public String edit() {
		category = categoryService.findByCid(category.getCid());
		
		return "edit";
	}
	
	/**
	 * 修改一级分类（分类名称）
	 * @return
	 */
	public String update() {
		categoryService.update(category);
		return "update";
	}
}
