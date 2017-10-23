package me.xueyao.crm.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.xueyao.crm.domain.SysUser;
import me.xueyao.crm.service.UserService;

@Controller("userAction")
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/user")
public class UserAction extends ActionSupport implements ModelDriven<SysUser>{

	private static final long serialVersionUID = 1L;

	private SysUser user = new SysUser(); //一定要手动实例化
	
	@Autowired //自动注入userService
	private UserService userService;
	
	/**
	 * 处理用户注册请求的方法
	 * @Action 注解： 用户配置action
	 * 		value属性： 指定action的name
	 * 		results属性： 指定多个结果集
	 * @Result 注解： 配置结果集
	 * 		location属性： 页面的路径
	 * @return
	 */
	@Action(value="user_register",results={@Result(location="/login.jsp")})
	public String register() {
		userService.saveUser(user);
		return SUCCESS;
	}
	
	
	@Override
	public SysUser getModel() {
		return user;
	}

}
