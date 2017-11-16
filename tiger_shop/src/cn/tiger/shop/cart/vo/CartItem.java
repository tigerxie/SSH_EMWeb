package cn.tiger.shop.cart.vo;

import cn.tiger.shop.product.vo.Product;

/**
 * 购物车模块购物条目类
 * @author tiger
 *
 */
public class CartItem {
	
	private Product product;	//商品
	private int count;			//数量
	private double subtotal;	//小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return count * product.getShop_price();
	}
}
