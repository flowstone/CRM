package me.xueyao.crm.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.xueyao.crm.dao.CustomerDao;
import me.xueyao.crm.domain.Customer;
import me.xueyao.crm.service.CustomerService;
import me.xueyao.crm.utils.SystemConstants;


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

	@Override
	public void delete(Integer cust_id) {
		Customer customer = customerDao.findById(cust_id);
		//如果上传过文件，就删除文件
		if (StringUtils.isNotBlank(customer.getCust_image())) {
			new File(SystemConstants.baseDir+customer.getCust_image()).delete();
		}
		//删除客户
		customerDao.delete(customer);
	}

	@Transactional(readOnly=true)
	@Override
	public Customer findById(Integer cust_id) {
		return customerDao.findById(cust_id);
	}

	
	

}
