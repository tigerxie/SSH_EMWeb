package cn.tiger.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.tiger.shop.order.dao.OrderDao;
import cn.tiger.shop.order.vo.Order;
import cn.tiger.shop.order.vo.OrderItem;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PageConstants;

/**
 * 订单模块业务逻辑层
 * @author tiger
 *
 */
@Transactional
public class OrderService {
	
	// 注入dao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	/**
	 * 生成订单
	 * @param order
	 */
	public void save(Order order) {
		// TODO Auto-generated method stub
		orderDao.save(order);
	}
	
	/**
	 * 我的订单
	 * @param order
	 */
	public PageBean<Order> myOrder(Integer uid, int page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);
		pageBean.setPageSize(PageConstants.ORDER_PAGE_SIZE);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = orderDao.findTotalRecordByUid(uid);
		pageBean.setTotalRecord(totalRecord);
		// 设置当前页数据
		List<Order> orderList = orderDao.findByUidPage(uid, page);
		pageBean.setBeanList(orderList);
		return pageBean;
	}

	/**
	 * 已付款
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findByOid(oid);
	}

	/**
	 * 修改订单信息
	 * @param exitOrder
	 */
	public void update(Order exitOrder) {
		// TODO Auto-generated method stub
		orderDao.update(exitOrder);
	}

	/**
	 * 按分页查询所有订单
	 * @param page
	 * @return
	 */
	public PageBean<Order> findAll(int page) {
		// TODO Auto-generated method stub
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPageSize(PageConstants.ORDER_PAGE_SIZE);
		pageBean.setPage(page);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = orderDao.findTotalRecord();
		pageBean.setTotalRecord(totalRecord);
		// 设置页面记录
		List<Order> beanList = orderDao.findAll(page);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 加载订单条目
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOIByOid(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findOIByOid(oid);
	}
}
