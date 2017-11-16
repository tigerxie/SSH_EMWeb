package cn.tiger.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tiger.shop.category.vo.Category;

/**
 * 分类模块持久层
 * @author tiger
 *
 */
public class CategoryDao extends HibernateDaoSupport {

	/**
	 * 查询所有一级分类
	 * @return
	 */
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql = "from Category";
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 添加一级分类
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(category);
	}

	/**
	 * 删除一级分类
	 * @param cid
	 */
	public void delete(Integer cid) {
		// TODO Auto-generated method stub
		Category category = this.getHibernateTemplate().get(Category.class, cid);
		this.getHibernateTemplate().delete(category);
	}

	/**
	 * 通过cid得到一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	
	/**
	 * 修改一级分类
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
}
