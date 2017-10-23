package me.xueyao.crm.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.xueyao.crm.dao.UserDao;
import me.xueyao.crm.domain.SysUser;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(SysUser user) {
		hibernateTemplate.save(user);
	}

}
