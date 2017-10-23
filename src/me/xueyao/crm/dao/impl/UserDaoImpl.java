package me.xueyao.crm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.xueyao.crm.dao.UserDao;
import me.xueyao.crm.domain.SysUser;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(SysUser user) {
		hibernateTemplate.save(user);
	}

	@Override
	public SysUser login(SysUser user) {
		List<SysUser> list = (List<SysUser>) hibernateTemplate.find(
				"from SysUser where user_code = ? and user_password = ?", user.getUser_code(), user.getUser_password());
		if (list.size() > 0) {
			//返回查询出来的结果
			return list.get(0);
		} else {
			//如果查询不到，返回null
			return null;
		}
	}

}
