package cn.tiger.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.tiger.shop.category.dao.CategoryDao;
import cn.tiger.shop.category.vo.Category;

/**
 * 分类模块业务层
 * @author tiger
 *
 */
@Transactional
public class CategoryService {
	
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	/**
	 * 将所有一级分类显示在访问页面
	 * @return
	 */
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	/**
	 * 添加一级分类
	 * @param category
	 */
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	/**
	 *  按cid删除一级分类
	 * @param cid
	 */
	public void delete(Integer cid) {
		// TODO Auto-generated method stub
		categoryDao.delete(cid);
	}

	/**
	 * 通过cid得到一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}
	
	/**
	 * 修改一级分类
	 * @param category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}
}
