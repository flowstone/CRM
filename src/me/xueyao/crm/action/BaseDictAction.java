package me.xueyao.crm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import me.xueyao.crm.domain.BaseDict;
import me.xueyao.crm.service.BaseDictService;

@Controller("baseDict")
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/baseDict")
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	private static final long serialVersionUID = 1L;
	
	private BaseDict baseDict = new BaseDict();
	
	@Autowired
	private BaseDictService baseDictService;
	
	private List<BaseDict> baseDicts; //就在对象栈里
	
	/**
	 * 根据类别编号查询数据字典
	 * @Result的type属性一定要是json,表示发送json数据到前端
	 * @Result的params属性是为json结果集指定参数，表示需要把action中的哪个属性转变成json发送到前端，
	 * 参数名是固定写法：root
	 * 现在我们查询出来 的数据赋值给了baseDicts，所以root参数的值就写baseDicts.
	 * 注意：需要提供baseDicts的get方法，因为框架要调用其get方法获取其值，再转成json
	 * @return
	 */
	@Action(value="baseDict_findByTypeCode",results={@Result(name="success",type="json",params={"root","baseDicts"})})
	public String findByTypeCode() {
		baseDicts = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		return SUCCESS;
	}
	
	
	
	@Override
	public BaseDict getModel() {
		return baseDict;
	}



	public List<BaseDict> getBaseDicts() {
		return baseDicts;
	}

	

}
