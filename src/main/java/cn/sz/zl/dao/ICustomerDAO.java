package cn.sz.zl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.sz.zl.pojo.Customer;
import cn.sz.zl.util.CustomerSearchCondition;

@Mapper
public interface ICustomerDAO {
	//根据编号级联查询
	public Customer findCustomerById(Integer cid);
	//根据条件查询所有的客户信息
	public List<Customer> findAllCustomer(CustomerSearchCondition cusc);
	//符合条件的用户数量
	public Integer CountAllCus(CustomerSearchCondition cusc);
	//禁用账号
	public void disableCustomer(Integer cusid);	
	//启用账号
	public void ablCustomer(Integer cusid);
}
