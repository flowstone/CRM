package me.xueyao.crm.dao;

import me.xueyao.crm.domain.SysUser;

public interface UserDao {

	/**
	 * 用户保存
	 * @param user
	 */
	public void save(SysUser user);
}
