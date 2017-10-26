package me.xueyao.crm.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cst_customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cust_id;
	private String cust_name;
	private String cust_phone;
	private String cust_address;
	private String cust_image;
	private String cust_filename;
	
	@ManyToOne
	@JoinColumn(name="cust_source")
	private BaseDict baseDictSource; //客户来源
	
	@ManyToOne
	@JoinColumn(name="cust_industry")
	private BaseDict baseDictIndustry; //客户所属行业
	
	@ManyToOne
	@JoinColumn(name="cust_level")
	private BaseDict baseDictLevel; //客户的级别

	public Integer getCust_id() {
		return cust_id;
	}

	public void setCust_id(Integer cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_address() {
		return cust_address;
	}

	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}

	public String getCust_image() {
		return cust_image;
	}

	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}

	public String getCust_filename() {
		return cust_filename;
	}

	public void setCust_filename(String cust_filename) {
		this.cust_filename = cust_filename;
	}

	public BaseDict getBaseDictSource() {
		return baseDictSource;
	}

	public void setBaseDictSource(BaseDict baseDictSource) {
		this.baseDictSource = baseDictSource;
	}

	public BaseDict getBaseDictIndustry() {
		return baseDictIndustry;
	}

	public void setBaseDictIndustry(BaseDict baseDictIndustry) {
		this.baseDictIndustry = baseDictIndustry;
	}

	public BaseDict getBaseDictLevel() {
		return baseDictLevel;
	}

	public void setBaseDictLevel(BaseDict baseDictLevel) {
		this.baseDictLevel = baseDictLevel;
	}
	
	
}
