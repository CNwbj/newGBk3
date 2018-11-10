package cn.sz.zl.service;

import java.util.List;

import cn.sz.zl.pojo.Customer;
import cn.sz.zl.util.CustomerSearchCondition;

public interface ICustomerService {
	//根据编号级联查询
		public Customer findCustomerById(Integer cid);
		//查询所有的客户信息
		public List<Customer> findAllCustomer(CustomerSearchCondition cusc);
		
		public Integer CountAllCus(CustomerSearchCondition cusc);
		//禁用账号
		public void disableCustomer(Integer cusid);	
		//启用账号
		public void ablCustomer(Integer cusid);
}
