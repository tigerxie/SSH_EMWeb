package cn.tiger.shop.categorysecond.adminaction;

import java.util.List;

import cn.tiger.shop.category.service.CategoryService;
import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.categorysecond.service.CategorySecondService;
import cn.tiger.shop.categorysecond.vo.CategorySecond;
import cn.tiger.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台管理员模块二级分类Action
 * @author tiger
 *
 */
@SuppressWarnings("serial")
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	// 模型驱动封装二级分类
	private CategorySecond categorySecond = new CategorySecond();
	
	@Override
	public CategorySecond getModel() {
		// TODO Auto-generated method stub
		return categorySecond;
	}
	// 注入二级分类service
	private CategorySecondService categorySecondService;
	// 注入一级分类service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 接收page
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	/**
	 * 按分页查询所有二级分类
	 * @return
	 */
	public String findAll() {
		/*
		 * 1. 调用service方法查询封装二级分类的PageBean对象
		 */
		PageBean<CategorySecond> pageBean = categorySecondService.findAll(page);
		/*
		 * 2. 将PageBean保存到值栈
		 */
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	/**
	 * 预添加
	 * @return
	 */
	public String preAdd() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "preAdd";
	}

	/**
	 * 添加二级分类
	 * @return
	 */
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	/**
	 * 删除二级分类
	 */
	public String delete() {
		categorySecondService.delete(categorySecond.getCsid());
		return "delete";
	}
	
	/**
	 * 预修改二级分类
	 * @return
	 */
	public String edit() {
		// 到的所有一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 将此二级分类存入模型驱动中
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		return "edit";
	}
	
	/**
	 * 修改二级分类
	 * @return
	 */
	public String update() {
		categorySecondService.update(categorySecond);
		return "update";
	}
}
