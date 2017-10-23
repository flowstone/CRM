package me.xueyao.crm.service;

import me.xueyao.crm.domain.SysUser;

public interface UserService {
	
	/**
	 * 保存用户
	 * @param user
	 */
	public void saveUser(SysUser user);

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public SysUser login(SysUser user);
}
