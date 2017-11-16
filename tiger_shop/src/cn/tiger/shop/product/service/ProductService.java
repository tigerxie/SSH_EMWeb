package cn.tiger.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.tiger.shop.product.dao.ProductDao;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PageConstants;

/**
 * 商品模块业务层
 * @author tiger
 *
 */
@Transactional
public class ProductService {
	
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	/**
	 * 分页查询热门商品
	 * @return
	 */
	public List<Product> findHot(){
		return productDao.findHot();
	}
	/**
	 * 分页查询最新商品
	 * @return
	 */
	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}
	
	/**
	 * 查询商品详细
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}
	
	/**
	 * 通过cid和page查到此cid下的所有商品
	 * @param cid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByCidPage(Integer cid, Integer page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setPageSize(PageConstants.PRODUCT_PAGE_SIZE);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = productDao.findTotalRecordByCid(cid);
		pageBean.setTotalRecord(totalRecord);
		// 设置当前页数据
		List<Product> productList = productDao.findByCidPage(cid, page);
		pageBean.setBeanList(productList);
		return pageBean;
	}
	
	/**
	 * 二级分类查询商品
	 * @param csid
	 * @param page
	 * @return
	 */
	public PageBean<Product> findbyCsidPage(Integer csid, Integer page) {
		// TODO Auto-generated method stub
		//1. 补全PageBean信息
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setPageSize(PageConstants.PRODUCT_PAGE_SIZE);
		int totalRecord = 0;
		// 得到二级分类总记录数
		totalRecord = productDao.findTotalRecordByCsid(csid);
		pageBean.setTotalRecord(totalRecord);
		// 得到二级分类当前记录
		List<Product> beanList = productDao.findByCsidPage(csid, page);
		pageBean.setBeanList(beanList);
		return pageBean;
	}
	
	/**
	 * 按分页查询所有商品
	 * @param page
	 * @return
	 */
	public PageBean<Product> findByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		pageBean.setPageSize(PageConstants.PRODUCT_PAGE_SIZE);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = productDao.findTotalRecord();
		pageBean.setTotalRecord(totalRecord);
		// 设置总记录
		List<Product> beanList = productDao.findByPage(page);
		pageBean.setBeanList(beanList);
		return pageBean;
	}
	
	/**
	 * 添加商品
	 * @param product
	 */
	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}
	
	/**
	 * 从数据库中删除商品信息
	 * @param pid
	 */
	public void delete(Product product) {
		// TODO Auto-generated method stub
		productDao.delete(product);
	}
	
	/**
	 * 修改商品信息
	 * @param product
	 */
	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
	}
}
