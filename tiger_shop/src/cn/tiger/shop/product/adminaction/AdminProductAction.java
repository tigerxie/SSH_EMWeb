package cn.tiger.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.tiger.shop.categorysecond.service.CategorySecondService;
import cn.tiger.shop.categorysecond.vo.CategorySecond;
import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台商品管理模块
 * @author tiger
 *
 */
@SuppressWarnings("serial")
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	// 注入商品service
	private ProductService productService;
	// 注入二级分类Service
	private CategorySecondService categorySecondService;
	// 模型驱动封装product
	private Product product = new Product();
	// 接收文件上传参数
	private File upload;				//上传文件
	private String uploadFileName;		//文件名
	private String uploadContextType;	//文件MIME类型
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	

	// 接收当前页码page
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	// 带分页查询所有商品
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	// 预添加商品
	public String addProduct() {
		// 得到所有二级分类
		List<CategorySecond> csList = categorySecondService.findAllCS();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addProduct";
	}

	// 添加商品
	public String save() throws IOException {
		System.out.println(product);
		/*
		 * 补全product信息
		 */
		// 设置上传时间
		product.setPdate(new Date());
		if (upload != null) {
			// 得到保存文件磁盘的绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件对象
			File destFile = new File(realPath + "//" + uploadFileName);
			// 复制文件
			FileUtils.copyFile(upload, destFile);
			//设置图片
			product.setImage("products/" + uploadFileName);
		}
		/*
		 *  调用service保存商品
		 */
		productService.save(product);
		return "saveSuccess";
	}
	
	/**
	 * 删除商品
	 * @return
	 */
	public String delete() {
		/*
		 * 得到图片路径，创建图片对象，删除图片
		 */
		product = productService.findByPid(product.getPid());
		String realPath = ServletActionContext.getServletContext().getRealPath("/");
		File descFile = new File(realPath + "//" + product.getImage());
		descFile.delete();
		/*
		 * 通过pid调用service方法从数据库中删除
		 */
		productService.delete(product);
		return "deleteSuccess";
	}
	
	/**
	 * 预修改
	 * @return
	 */
	public String edit() {
		/*
		 * 通过pid得到商品,保存到模型驱动
		 */
		product = productService.findByPid(product.getPid());
		/*
		 * 得到二级分类，存到值栈
		 */
		List<CategorySecond> csList = categorySecondService.findAllCS();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	
	/**
	 * 修改商品
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException {
		
		if (upload != null) {
			/*
			 * 删除旧文件
			 */
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			
			/*
			 *  保存新上传文件
			 */
			// 得到保存文件的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 创建文件对象
			File destFile = new File(realPath + "//" + uploadFileName);
			// 将上传文件保存到磁盘
			FileUtils.copyFile(upload, destFile);
		}
		
		/*
		 * 补全product信息
		 */
		product.setImage("products/" + uploadFileName);
		product.setPdate(new Date());
		
		/*
		 * 调用service方法完成修改
		 */
		productService.update(product);
		return "updateSuccess";
	}

}
