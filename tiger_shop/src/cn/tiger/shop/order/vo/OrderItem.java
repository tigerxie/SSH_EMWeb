package cn.tiger.shop.order.vo;

import cn.tiger.shop.product.vo.Product;

/**
 * 订单模块订单条目
 * @author tiger
 *
 */
public class OrderItem {
	
	private Integer itemid;		//订单id
	private Integer count;		//数量
	private Double subtotal;	//小计 = 数量 * 单价
	private Product product;	//订单pid对应的商品对象
	private Order order;		//订单oid对应的订单对象
	
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
/*	public Double getSubtotal() {
		return count * product.getShop_price();
	}*/

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
