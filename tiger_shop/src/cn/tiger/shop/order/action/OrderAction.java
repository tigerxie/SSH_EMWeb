package cn.tiger.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.tiger.shop.cart.vo.Cart;
import cn.tiger.shop.cart.vo.CartItem;
import cn.tiger.shop.order.service.OrderService;
import cn.tiger.shop.order.vo.Order;
import cn.tiger.shop.order.vo.OrderItem;
import cn.tiger.shop.user.vo.User;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单模块Action
 * @author tiger
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	// 注入service
	private OrderService orderService;
	// 模型驱动
	private Order order = new Order();
	// 接收page
	private int page;
	// 接收pd_FrpId
	private String pd_FrpId;
	// 接收r6_Order
	private String r6_Order;
	// 接收r3_Amt
	private String r3_Amt;
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// 提交订单
	public String save () {
		/*
		 * 1.生成订单：补全信息
		 */
		order.setOrdertime(new Date());
		order.setState(1);
		// 得到User
		User exitUser = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (exitUser == null) {
			this.addActionError("您还未登录！请先登录！");
			return "login";
		}
		order.setUser(exitUser);
		// 得到Cart
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if (cart == null) {
			this.addActionMessage("您还没有购物车！请先购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 遍历购物车条目，并设置到订单条目
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			// 将所有订单条目添加到订单
			order.getOrderItems().add(orderItem);
		}
		/*
		 * 2. 调用service方法完成生成订单
		 */
		orderService.save(order);
		/*
		 * 3. 销毁cart
		 */
		cart.clearCart();
		return "saveSuccess";
	}
	
	/**
	 * 我的订单
	 * @return
	 */
	public String myOrder(){
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		PageBean<Order> pageBean = orderService.myOrder(user.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "myOrder";
	}
	
	/**
	 * 预付款
	 * @return
	 */
	public String findByOid() {
		/*
		 * 1. 调用service查询订单，赋给模型驱动对象
		 */
		order = orderService.findByOid(order.getOid());
		/*
		 * 2. 订单已存入模型驱动，页面可以直接在模型驱动里面取
		 */
		return "findByOidSuccess";
	}
	
	/**
	 * 订单支付
	 * @return
	 * @throws IOException 
	 */
	public String payOrder() throws IOException {
		/*
		 * 1. 修改订单信息
		 */
		Order exitOrder = orderService.findByOid(order.getOid());
		exitOrder.setAddr(order.getAddr());
		exitOrder.setName(order.getName());
		exitOrder.setPhone(order.getPhone());
		orderService.update(exitOrder);
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	
	/**
	 * 支付回调
	 * @return
	 */
	public String callBack() {
		// 修改订单的状态:
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		orderService.update(currOrder);
		this.addActionMessage("支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	/**
	 * 确认收货
	 * @return
	 */
	public String updateState() {
		order = orderService.findByOid(order.getOid());
		order.setState(4);
		orderService.update(order);
		this.addActionMessage("支付成功!订单编号为: "+order.getOid() +" 付款金额为: "+order.getTotal());
		return "msg";
	}
}
