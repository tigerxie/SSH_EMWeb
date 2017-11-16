package cn.tiger.shop.product.vo;

import java.io.Serializable;
import java.util.Date;

import cn.tiger.shop.categorysecond.vo.CategorySecond;

/**
 * 商品模块实体类
 * @author tiger
 *
 */
public class Product implements Serializable{
	
	private Integer pid;
	private String pname;
	private Double market_price;
	private Double shop_price;
	private String image;
	private String pdesc;
	private Integer is_hot;
	private Date pdate;
	// 二级分类的外键:使用二级分类的对象.
	private CategorySecond categorySecond;
	
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(Integer pid, String pname, Double market_price,
			Double shop_price, String image, String pdesc, Integer is_hot,
			Date pdate) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.market_price = market_price;
		this.shop_price = shop_price;
		this.image = image;
		this.pdesc = pdesc;
		this.is_hot = is_hot;
		this.pdate = pdate;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price="
				+ market_price + ", shop_price=" + shop_price + ", image="
				+ image + ", pdesc=" + pdesc + ", is_hot=" + is_hot
				+ ", pdate=" + pdate + "]";
	}
	
}
