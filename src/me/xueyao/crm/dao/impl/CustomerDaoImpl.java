package me.xueyao.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import me.xueyao.crm.dao.CustomerDao;
import me.xueyao.crm.domain.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void save(Customer customer) {
		hibernateTemplate.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return (List<Customer>) hibernateTemplate.find("from Customer");
	}

	@Override
	public int findCount(DetachedCriteria dc) {
		//设置projection，表示要查询总记录数
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) hibernateTemplate.findByCriteria(dc);
		return list.get(0).intValue();
	}

	@Override
	public List<Customer> findByPage(DetachedCriteria dc, int i, int rows) {
		//清空之前设置查询总行数的信息
		dc.setProjection(null);
		//查询分页数据
		List<Customer> list = (List<Customer>) hibernateTemplate.findByCriteria(dc, i, rows);
		return list;
	}

}
