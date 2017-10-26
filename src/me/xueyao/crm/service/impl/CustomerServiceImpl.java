package me.xueyao.crm.service.impl;

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

}
