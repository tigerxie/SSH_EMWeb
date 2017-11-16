package cn.tiger.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.tiger.shop.cart.vo.Cart;
import cn.tiger.shop.cart.vo.CartItem;
import cn.tiger.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车模块Action
 * @author tiger
 *
 */
public class CartAction extends ActionSupport{
	
	// 接收pid
	private Integer pid;
	// 接收count
	private int count;
	// 注入商品service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// 将商品添加到购物车:添加购物车
	public String addCart() {
		// 创建CartItem
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findByPid(pid));
		// 通过CartItem,调用cart的addCart完成购物车添加操作
		// 购物车存在session中
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	// 删除购物车条目
	public String removeCartItem() {
		// 通过CartItem,调用cart的removeCartItem完成购物车添加操作
		// 购物车存在session中
		Cart cart = getCart();
		cart.removeCartItem(pid);
		return "removeCartItem";
	}
	
	// 清空购物车
	public String clearCart() {
		Cart cart = getCart();
		cart.clearCart();
		return "clearCart";
	}
	
	// 我的购物车
	public String myCart() {
		return "myCart";
	}

	// 从session中获取购物车
	private Cart getCart() {
		// TODO Auto-generated method stub
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
}
