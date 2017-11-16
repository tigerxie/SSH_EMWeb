package cn.tiger.shop.order.adminaction;

import java.util.List;

import cn.tiger.shop.order.service.OrderService;
import cn.tiger.shop.order.vo.Order;
import cn.tiger.shop.order.vo.OrderItem;
import cn.tiger.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台订单管理模块
 * @author tiger
 *
 */
@SuppressWarnings("serial")
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	// 注入订单service
	private OrderService orderService;
	// 接收page
	private int page;
	// 模型驱动封装Order
	private Order order = new Order();
	
	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	/**
	 * 按分页查询所有订单
	 * @return
	 */
	public String findAll() {
		PageBean<Order> pageBean = orderService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	/**
	 * 加载订单条目
	 * @return
	 */
	public String findOrderItem() {
		List<OrderItem> oiList = orderService.findOIByOid(order.getOid());
		
		ActionContext.getContext().getValueStack().set("oiList", oiList);
		return "findOrderItem";
	}
	
	/**
	 * 发货：将订单状态修改为3
	 * @return
	 */
	public String updateState() {
		order = orderService.findByOid(order.getOid());
		order.setState(3);
		orderService.update(order);
		return "updateStateSuccess";
	}

}
