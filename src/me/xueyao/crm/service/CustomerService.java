package me.xueyao.crm.service;

import java.util.List;

import me.xueyao.crm.domain.Customer;

public interface CustomerService {

	/**
	 * 保存客户
	 * @param customer
	 */
	public void saveCustomer(Customer customer);

	/**
	 * 查询所有客户
	 * @return
	 */
	public List<Customer> findAllCustomer();

}
