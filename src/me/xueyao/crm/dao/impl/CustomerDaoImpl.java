package me.xueyao.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
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
	public int findCount() {
		List<Long> list = (List<Long>) hibernateTemplate.find("SELECT COUNT(1) FROM Customer");
		return list.get(0).intValue();
	}

	@Override
	public List<Customer> findByPage(int i, int rows) {
		//构造离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		List<Customer> list = (List<Customer>) hibernateTemplate.findByCriteria(dc, i, rows);
		return list;
	}

}
