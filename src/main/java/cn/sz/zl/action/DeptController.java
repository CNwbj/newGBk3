package cn.sz.zl.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sz.zl.pojo.Dept;
import cn.sz.zl.service.IDeptService;

@Controller
@RequestMapping("/dc")
public class DeptController {
	@Autowired
	private IDeptService ds;

	@RequestMapping(value = "findalldept", method = RequestMethod.POST)
	public List<Dept> allDept() {
		return ds.queryAllDept();
	}
	
	public void addDept(Dept dept) {
		
	}
}
