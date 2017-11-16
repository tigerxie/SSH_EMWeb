package cn.tiger.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.tiger.shop.category.service.CategoryService;
import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 访问首页
 * @author tiger
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
	//注入一级分类service
	private CategoryService categoryService;
	//注入商品service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 1.将所有一级分类显示在首页
		 */
		List<Category> categoryList = this.categoryService.findAll();
		//将categoryList保存到session中
		ServletActionContext.getRequest().getSession().setAttribute("categoryList", categoryList);
		System.out.println(categoryList.size());
		
		/**
		 * 2. 将热门商品显示在首页:10个
		 */
		List<Product> pHotList = this.productService.findHot();
		// 保存到值栈中
		ActionContext.getContext().getValueStack().set("pHotList", pHotList);
		
		/**
		 * 3. 将最新商品显示在首页：10个
		 */
		List<Product> pNewList = this.productService.findNew();
		ActionContext.getContext().getValueStack().set("pNewList", pNewList);
		return "index";
	}
	

}
