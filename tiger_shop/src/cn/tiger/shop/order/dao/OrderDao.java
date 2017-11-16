package cn.tiger.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tiger.shop.order.vo.Order;
import cn.tiger.shop.order.vo.OrderItem;
import cn.tiger.shop.utils.PageConstants;
import cn.tiger.shop.utils.PageHibernateCallback;

/**
 * 订单模块持久层
 * @author tiger
 *
 */
public class OrderDao extends HibernateDaoSupport{

	/**
	 * 添加订单
	 * @param order
	 */
	public void save(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}
	
	/**
	 * 按照uid查询
	 * @param order
	 */
	public List<Order> findByUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql = "from Order o where o.user.uid=?";
		List<Order> orderList = this.getHibernateTemplate().find(hql, uid);
		return orderList;
	}

	/**
	 * 查询总记录数
	 * @param uid
	 * @return
	 */
	public int findTotalRecordByUid(Integer uid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		return list.get(0).intValue();
	}

	/**
	 * 按分页查询记录
	 * @param uid
	 * @param page
	 * @return
	 */
	public List<Order> findByUidPage(Integer uid, int page) {
		// TODO Auto-generated method stub
		String hql = "from Order o where o.user.uid=? order by o.ordertime desc";
		Object[] params = {uid};
		int pageSize = PageConstants.ORDER_PAGE_SIZE;
		int startIndex = (page - 1) * pageSize;
		try {
			List<Order> orderList = this.getHibernateTemplate().execute(
					new PageHibernateCallback<Order>(hql, params, startIndex,
							pageSize));
			return orderList;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}

	/**
	 * 通过oid查询订单
	 * @param oid
	 */
	public Order findByOid(Integer oid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	/**
	 * 修改订单信息
	 * @param exitOrder
	 */
	public void update(Order exitOrder) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(exitOrder);
	}

	/**
	 * 查询总记录数
	 * @return
	 */
	public int findTotalRecord() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}

	/**
	 * 查询页面记录
	 * @return
	 */
	public List<Order> findAll(int page) {
		// TODO Auto-generated method stub
		String hql = "from Order order by ordertime desc";
		int pageSize = PageConstants.ORDER_PAGE_SIZE;
		int startIndex = (page -1 ) * pageSize;
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, startIndex, pageSize));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null ;
	}

	/**
	 * 按oid查询订单条目
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOIByOid(Integer oid) {
		// TODO Auto-generated method stub
		String hql = "from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
