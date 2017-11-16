package cn.tiger.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.tiger.shop.categorysecond.dao.CategorySecondDao;
import cn.tiger.shop.categorysecond.vo.CategorySecond;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PageConstants;

/**
 * 二级分类业务层
 * @author tiger
 *
 */
@Transactional
public class CategorySecondService {
	
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**
	 * 按分页查询所有二级分类
	 * @return
	 */
	public PageBean<CategorySecond> findAll(int page) {
		// TODO Auto-generated method stub
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		pageBean.setPageSize(PageConstants.CATEGORYSECOND_PAGE_SIZE);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = categorySecondDao.findTotalRecord();
		pageBean.setTotalRecord(totalRecord);
		// 设置页面记录
		List<CategorySecond> beanList = categorySecondDao.findAll(page);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 添加二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.save(categorySecond);
	}

	/**
	 * 删除二级分类
	 * @param csid
	 */
	public void delete(Integer csid) {
		// TODO Auto-generated method stub
		categorySecondDao.delete(csid);
	}

	/**
	 * 通过csid查询二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsid(csid);
	}

	/**
	 * 修改二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.update(categorySecond);
	}

	/**
	 * 查询所有二级分类
	 */
	public List<CategorySecond> findAllCS() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAllCS();
	}
}
