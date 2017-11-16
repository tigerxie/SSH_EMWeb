package cn.tiger.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tiger.shop.categorysecond.vo.CategorySecond;
import cn.tiger.shop.utils.PageConstants;
import cn.tiger.shop.utils.PageHibernateCallback;

/**
 * 二级分类持久层
 * @author tiger
 *
 */
public class CategorySecondDao extends HibernateDaoSupport {

	/**
	 * 查询总记录数
	 * @return
	 */
	public int findTotalRecord() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}

	/**
	 * 查询所有二级分类
	 * @param page
	 * @return
	 */
	public List<CategorySecond> findAll(int page) {
		// TODO Auto-generated method stub
		String hql = "from CategorySecond order by csid desc ";
		int pageSize = PageConstants.CATEGORYSECOND_PAGE_SIZE;
		int startIndex = (page - 1) * pageSize;
		List<CategorySecond> list = this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<CategorySecond>(hql, null,
						startIndex, pageSize));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * 添加二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(categorySecond);
	}

	/**
	 * 删除二级分类
	 * @param csid
	 */
	public void delete(Integer csid) {
		// TODO Auto-generated method stub
		CategorySecond cs = this.getHibernateTemplate().get(CategorySecond.class, csid);
		this.getHibernateTemplate().delete(cs);
	}

	/**
	 * 通过csid查询二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	
	/**
	 * 修改二级分类
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	/**
	 * 查询所有二级分类
	 * @return
	 */
	public List<CategorySecond> findAllCS() {
		// TODO Auto-generated method stub
		String hql = "from CategorySecond order by csid";
		return this.getHibernateTemplate().find(hql);
	}
}
