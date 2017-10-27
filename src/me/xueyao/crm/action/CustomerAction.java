package me.xueyao.crm.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
	
	private int page; //当前页码
	private int rows; //每页显示几条数据
	//分页信息的封装
	private Map<String,Object> pagination = new HashMap<String,Object>();
	
	//表示上传的文件，名字要与页面的file的name一致
	private File upload;
	private String uploadContentType; //表示上传文件的类型
	private String uploadFileName; //表示上传文件的名字
	/**
	 * 处理客户保存请求
	 * @return
	 */
	@Action(value="customer_save",results={@Result(location="/jsp/customer/list.jsp")})
	public String save() {
		
		try {
			FileUtils.copyFile(upload, new File("C:/upload/"+uploadFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*//如果上传了文件就需要处理文件
		if (null != upload) {
			//生成文件的随机文件名
			String randomFileName = UploadUtils.generateRandonFileName(uploadFileName);
			//生成随机二级目录：/1/12
			String randomDir = UploadUtils.generateRandomDir(randomFileName);
			
			try {
				FileUtils.copyFile(upload, new File(SystemConstants.baseDir+randomDir+"/"+randomFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//设置图片保存的路径
			customer.setCust_image(randomDir+"/"+randomFileName);
			//设置图片原始名字
			customer.setCust_filename(uploadFileName);
		}
		*/
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
	
	/**
	 * 分页
	 * @return
	 */
	@Action(value="customer_findByPage",results={@Result(type="json",params={"root","pagination"})})
	public String findByPage() {
		//构造离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		//拼接条件开始
		if (StringUtils.isNotBlank(customer.getCust_name())) {
			dc.add(Restrictions.eq("cust_name",customer.getCust_name()));
		}
		
		/**
		 * 当用户点击的是左边菜单栏中的客户列表链接，没有发送级别参数，
		 * customer.getBaseDictLevel()为空
		 * 如果直接cutomer.getBaseDictLevel();
		 * 所以，需要先判断cutomer.getBaseDictLevel()是否空，后边来源，行业同理
		 */
		if (null != customer.getBaseDictLevel()) {
			if (StringUtils.isNotBlank(customer.getBaseDictLevel().getDict_id())) {
				dc.add(Restrictions.eq("baseDictLevel.dict_id",customer.getBaseDictLevel().getDict_id()));
			}
		}
		
		if (null != customer.getBaseDictSource()) {
			if (StringUtils.isNotBlank(customer.getBaseDictSource().getDict_id())) {
				dc.add(Restrictions.eq("baseDictSource.dict_id",customer.getBaseDictSource().getDict_id()));
			}
		}
		
		if (null != customer.getBaseDictIndustry()) {
			if (StringUtils.isNotBlank(customer.getBaseDictIndustry().getDict_id())) {
				dc.add(Restrictions.eq("baseDictIndustry.dict_id",customer.getBaseDictIndustry().getDict_id()));
			}
		}
		//拼接条件结束
		int total = customerService.findCount(dc);
		//设置总记录数到pagination中，key一定要是total
		pagination.put("total", total); 
		List<Customer> list = customerService.findByPage(dc, (page-1)* rows, rows);
		//设置当前页的数据集合到pagination中，key一定要是rows
		pagination.put("rows",list);
		return SUCCESS;
	}
	
	@Override
	public Customer getModel() {
		return customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}


	public Map<String, Object> getPagination() {
		return pagination;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}
	
	

}
