package me.xueyao.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.xueyao.crm.dao.CustomerDao;
import me.xueyao.crm.domain.Customer;
import me.xueyao.crm.service.CustomerService;


@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public void saveCustomer(Customer customer) {
		customerDao.save(customer);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Customer> findAllCustomer() {
		return customerDao.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public int findCount(DetachedCriteria dc) {
		return customerDao.findCount(dc);
	}
	
	
	@Transactional(readOnly=true)
	@Override
	public List<Customer> findByPage(DetachedCriteria dc, int i, int rows) {
		return customerDao.findByPage(dc,i,rows);
	}

	
	

}
