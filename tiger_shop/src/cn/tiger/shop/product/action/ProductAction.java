package cn.tiger.shop.product.action;

import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	
	// 模型驱动注入商品
	private Product product = new Product();
	// 接收当前页码
	private Integer page;
	// 接收一级分类cid
	private Integer cid;
	// 接收二级分类csid
	private Integer csid;
	
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * 查询商品详细
	 * @return
	 */
	public String findByPid() {
		// 模型驱动，直接把product放到栈顶
		product = this.productService.findByPid(this.product.getPid());
		return "product";
	}

	/**
	 * 一级分类查询商品
	 * @return
	 */
	public String findByCidPage() {
		/*
		 * 1. 得到一级分类及其关联的二级分类，显示在页面左侧（session中已存入categoryList）
		 */
		/*
		 * 2. 通过cid和page调用service得到分类下的所有商品PageBean<Product>
		 */
		PageBean<Product> pageBean = this.productService.findByCidPage(cid, page);
		/*
		 * 3. 将PageBean<Product>保存到值栈中
		 */
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCidPage";
		
	}
	
	/**
	 * 二级分类查询商品
	 * @return
	 */
	public String findByCsidPage() {
		/*
		 * 1. 得到一级分类及其关联的二级分类，显示在页面左侧（session中已存入categoryList）
		 */
		/*
		 * 2. 通过csid和page调用service方法得到PageBean<Product>
		 */
		PageBean<Product> pageBean = this.productService.findbyCsidPage(csid, page);
		/*
		 * 3. 将pageBean保存在值栈中
		 */
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsidPage";
	}

}
