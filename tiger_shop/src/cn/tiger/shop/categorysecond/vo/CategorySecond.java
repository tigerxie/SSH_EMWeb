package cn.tiger.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.product.vo.Product;

/**
 * 二级分类实体类
 * @author tiger
 *
 */
public class CategorySecond implements Serializable{
	
	private Integer csid;
	private String csname;
	// 二级分类所有对应的一级分类
	private Category category;
	// 二级分类下的cid：通过二级分类的对象
	private Set<Product> products = new HashSet<Product>();
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "CategorySecond [csid=" + csid + ", csname=" + csname
				+ ", category=" + category + "]";
	}
	public CategorySecond(Integer csid, String csname, Category category) {
		super();
		this.csid = csid;
		this.csname = csname;
		this.category = category;
	}
	public CategorySecond() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
}
