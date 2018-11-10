package cn.sz.zl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sz.zl.dao.ICustomerDAO;
import cn.sz.zl.pojo.Customer;
import cn.sz.zl.service.ICustomerService;
import cn.sz.zl.util.CustomerSearchCondition;
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDAO cd;
	@Override
	public Customer findCustomerById(Integer cid) {
		return cd.findCustomerById(cid);
	}


	@Override
	public void disableCustomer(Integer c_id) {
		if(c_id!=null&&!"".equals(c_id)) {
			cd.disableCustomer(c_id);
		}
		
	}

	@Override
	public void ablCustomer(Integer c_id) {
		if(c_id!=null&&!"".equals(c_id)) {
			cd.ablCustomer(c_id);
		}
	}

	@Override
	public List<Customer> findAllCustomer(CustomerSearchCondition cusc) {
		return cd.findAllCustomer(cusc);
	}


	@Override
	public Integer CountAllCus(CustomerSearchCondition cusc) {
		return cd.CountAllCus(cusc);
	}

}
