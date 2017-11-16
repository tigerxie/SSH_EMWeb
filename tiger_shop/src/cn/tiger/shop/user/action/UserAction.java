package cn.tiger.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.tiger.shop.user.service.UserService;
import cn.tiger.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	private User user = new User();
	private UserService userService;
	private String verifyCode;

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/**
	 * 到注册页面
	 * @return
	 */
	public String registPage() {
		return "regist";
	}
	
	/**
	 * ajax异步校验用户名
	 * @return
	 * @throws IOException 
	 */
	public String ajaxCheckUsername() throws IOException {
		System.out.println(this.user.getUsername());
		/*
		 * 1. 通过username获得已存在User
		 */
		User exitUser = this.userService.findByUsername(this.user.getUsername());
		/*
		 * 2. 判断User
		 * 		>为null，向页面响应用户名可以用
		 * 		>否则，响应用户名已被注册
		 */
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (exitUser == null) {
			response.getWriter().print("<font color='green'>用户名可以使用！</font>");
		} else {
			response.getWriter().print("<font color='red'>用户名已被注册！</font>");
		}
		return NONE;
		
	}
	/**
	 * 用户注册功能
	 * 	>后台校验
	 *  >保存注册信息
	 * 
	 */
	public String regist() {
		//校验验证码
		String checkcode = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!this.verifyCode.equalsIgnoreCase(checkcode)) {
			this.addActionError("验证码不正确！");
			return "checkFailure";
		}
		/*
		 * 1. 调用service方法完成数据保存
		 */
		this.userService.save(user);
		/*
		 * 2. 保存成功信息输出到页面
		 */
		this.addActionMessage("邮件发送成功，请完成激活！");
		return "msg";
	}
	
	/**
	 * 激活用户功能
	 * @return
	 */
	public String active() {
		/*
		 * 1. 通过激活码去查询是否有该用户
		 *	>null，添加错误信息输出到错误页面，激活无效
		 * 	>有，判断激活转态
		 * 		>1：添加错误信息输出到错误页面，该用户已被激活
		 * 		>否则：修改激活状态为1，添加成功信息输出到页面
		 */
		User exitUser = this.userService.findByCode(this.user.getCode());
		if (exitUser == null) {
			this.addActionMessage("激活失败：激活码无效！");
		} else {
			exitUser.setState(1);
			exitUser.setCode(null);
			this.userService.update(exitUser);
			this.addActionMessage("用户激活成功！");
		}
		return "msg";
	}
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	public String loginPage() {
		return "login";
	}
	/**
	 * 用户登录功能
	 * @return
	 */
	public String login() {
		/*
		 * 1. 通过用户登录信息调用service获得已存在用户
		 */
		User exitUser = this.userService.login(this.user);
		/*
		 * 2. 判断用户
		 * 	>null，保存错误信息，返回login（转回login.jsp）
		 * 	>保存已存在用户到session，返回index
		 */
		if (exitUser == null) {
			this.addActionError("用户名或密码错误或用户未被激活！");
			return "login";
		}
		ServletActionContext.getRequest().getSession().setAttribute("user", exitUser);
		return "loginSuccess";
	}

	/**
	 * 退出功能
	 * @return
	 */
	public String quit() {
		//销毁session
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		return "quit";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
