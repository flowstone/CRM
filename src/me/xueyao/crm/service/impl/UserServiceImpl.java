package me.xueyao.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.xueyao.crm.dao.UserDao;
import me.xueyao.crm.domain.SysUser;
import me.xueyao.crm.service.UserService;

@Service("userService")
@Transactional //加上事务
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveUser(SysUser user) {
		//设置用户状态为1，表示可用，不设置会报错，因为user_state不能为空
		user.setUser_state("1");
		userDao.save(user);
	}

}
