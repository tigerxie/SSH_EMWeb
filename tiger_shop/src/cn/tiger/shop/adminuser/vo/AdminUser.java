package cn.tiger.shop.adminuser.vo;

/**
 * 后台管理员模块
 * @author tiger
 *
 */
public class AdminUser {
	private Integer uid;
	private String username;
	private String password;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
