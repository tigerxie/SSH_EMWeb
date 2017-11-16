package cn.tiger.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PageConstants;
import cn.tiger.shop.utils.PageHibernateCallback;

/**
 * 商品模块持久层
 * @author tiger
 *
 */
public class ProductDao extends HibernateDaoSupport {

	/**
	 * 分页查询热门商品
	 * @return
	 */
	public List<Product> findHot() {
		// TODO Auto-generated method stub
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 热门商品条件is_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序
		criteria.addOrder(Order.desc("pdate"));
		//执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * 分页查询最新商品
	 * @return
	 */
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按时间倒序排序
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	/**
	 * 查询商品详细
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	
	/**
	 * 得到一级分类总记录数
	 * @param cid
	 * @return
	 */
	public int findTotalRecordByCid(Integer cid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 得到一级分类当前记录
	 * @param cid
	 * @param page
	 * @return
	 */
	public List<Product> findByCidPage(Integer cid, Integer page) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		Object[] params = {cid};
		int pageSize = PageConstants.PRODUCT_PAGE_SIZE;
		int startIndex = (page - 1) * pageSize;
		try {
			List<Product> productList = new PageHibernateCallback<Product>(hql, params, startIndex, pageSize).doInHibernate(this.getSession());
			return productList;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}

	/**
	 * 查询二级分类总记录数
	 * @param csid
	 * @return
	 */
	public int findTotalRecordByCsid(Integer csid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	/**
	 * 查询二级分类总记录
	 * @param csid
	 * @param pageBean
	 * @return
	 */
	public List<Product> findByCsidPage(Integer csid, Integer page) {
		// TODO Auto-generated method stub
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
		Object[] params = {csid};
		int pageSize = PageConstants.PRODUCT_PAGE_SIZE;
		int startIndex = (page - 1) * pageSize;
		try {
			List<Product> productList = new PageHibernateCallback<Product>(hql, params, startIndex, pageSize).doInHibernate(this.getSession());
			return productList;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询总记录数
	 * @return
	 */
	public int findTotalRecord() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}

	/**
	 * 查询总记录
	 * @param page
	 * @return
	 */
	public List<Product> findByPage(int page) {
		// TODO Auto-generated method stub
		String hql = "from Product order by pdate desc";
		int pageSize = PageConstants.PRODUCT_PAGE_SIZE;
		int startIndex = (page - 1) * pageSize;
		List<Product> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null, startIndex,
						pageSize));
		return list;
	}

	/**
	 * 添加商品
	 * @param product
	 */
	public void save(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(product);
	}
	
	/**
	 * 从数据库中删除商品信息
	 * @param pid
	 */
	public void delete(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(product);
	}

	/**
	 * 修改商品信息
	 * @param product
	 */
	public void update(Product product) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(product);
	}
}
