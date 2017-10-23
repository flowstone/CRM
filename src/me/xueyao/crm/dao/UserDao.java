package me.xueyao.crm.dao;

import me.xueyao.crm.domain.SysUser;

public interface UserDao {

	/**
	 * 用户保存
	 * @param user
	 */
	public void save(SysUser user);

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public SysUser login(SysUser user);
}
