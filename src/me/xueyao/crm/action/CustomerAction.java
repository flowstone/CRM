package me.xueyao.crm.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.xueyao.crm.domain.Customer;
import me.xueyao.crm.service.CustomerService;
@Controller("customerAction")
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/customer")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private static final long serialVersionUID = 1L;

	private Customer customer = new Customer();
	
	private List<Customer> customers; //存放对象
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 处理客户保存请求
	 * @return
	 */
	@Action(value="customer_save",results={@Result(location="/jsp/customer/list.jsp")})
	public String save() {
		//当新增页面选择了"--请选择--"这一项，报违反外键约束
		//当选择"--请选择--"时，customer.getBaseDictIndustry().getDict_id的值""
		//就会把""保存到表中，违反外键约束；所以当为"",就设置为null
		if (StringUtils.isBlank(customer.getBaseDictIndustry().getDict_id())) {
			customer.setBaseDictIndustry(null);
		}
		if (StringUtils.isBlank(customer.getBaseDictSource().getDict_id())) {
			customer.setBaseDictSource(null);
		}
		if (StringUtils.isBlank(customer.getBaseDictLevel().getDict_id())) {
			customer.setBaseDictLevel(null);
		}
		customerService.saveCustomer(customer);
		return SUCCESS;
	}
	
	
	/**
	 * 处理查询所有客户请求的方法
	 * @return
	 */
	@Action(value="customer_list",results={@Result(type="json",params={"root","customers"})})
	public String list() {
		customers = customerService.findAllCustomer();
		//System.out.println(customers);

		return SUCCESS;
	}
	
	@Override
	public Customer getModel() {
		return customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}
	
	

}
