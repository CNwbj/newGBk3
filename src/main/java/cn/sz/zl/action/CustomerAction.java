package cn.sz.zl.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.sz.zl.pojo.Customer;
import cn.sz.zl.service.ICustomerService;
import cn.sz.zl.util.CustomerSearchCondition;

@Controller
@RequestMapping("ca")
public class CustomerAction {
	@Autowired
	private ICustomerService customerServiceImpl;

	@RequestMapping(value = "findcussplit", method = RequestMethod.GET)
	public String queryAllCus(CustomerSearchCondition cusc, Model model) {
		Integer cp = 1, ps = 5;
		cusc.setC_mail(null);
		cusc.setC_phone(null);
		cusc.setC_sex(null);
		cusc.setStateid(null);
		cusc.setStart((cp - 1) * ps);
		cusc.setEnd(cp * ps);

		List<Customer> cuslist = customerServiceImpl.findAllCustomer(cusc);
		int count = customerServiceImpl.CountAllCus(cusc);
		int allpage = (count - 1) / ps + 1;//
		model.addAttribute("cp", cp);
		model.addAttribute("ps", ps);
		model.addAttribute("cuslist", cuslist);
		model.addAttribute("count", count);
		model.addAttribute("allpage", allpage);
		return "pages/frame/uc/cus_list";
	}

	@RequestMapping(value = "findcussplitbysc", method = RequestMethod.POST)
	public String queryAllCusByCondition(HttpServletRequest request, Model model) {
		CustomerSearchCondition cusc = new CustomerSearchCondition();
		Integer cp = 1, ps = 5;
		String cpvalue = request.getParameter("cp");
		if (cpvalue != null && !cpvalue.equals("")) {
			cp = Integer.valueOf(cpvalue);
		}
		String sex = request.getParameter("c_sex");
		String c_sex = "".equals(sex) || sex == null ? null : sex;
		String phone = request.getParameter("c_phone");
		String c_phone = "".equals(phone) || phone == null ? null : phone;
		String mail = request.getParameter("c_mail");
		String c_mail = "".equals(mail) || mail == null ? null : mail;

		String isdele = request.getParameter("stateid");
		String isd = "".equals(isdele) || isdele == null ? null : isdele;

		Integer stateid = null;
		if (isd != null) {
			stateid = Integer.parseInt(isd);
		}

		cusc.setC_mail(c_mail);
		cusc.setC_phone(c_phone);
		cusc.setC_sex(c_sex);
		cusc.setStateid(stateid);
		cusc.setStart((cp - 1) * ps);
		cusc.setEnd(cp * ps);

		List<Customer> cuslist = customerServiceImpl.findAllCustomer(cusc);
		int count = customerServiceImpl.CountAllCus(cusc);
		int allpage = (count - 1) / ps + 1;//
		model.addAttribute("cp", cp);
		model.addAttribute("ps", ps);
		model.addAttribute("cuslist", cuslist);
		model.addAttribute("count", count);
		model.addAttribute("cusc", cusc);
		model.addAttribute("allpage", allpage);
		return "pages/frame/uc/cus_list";
	}

	@RequestMapping(value = "disablecus")
	public String disableCus(Integer c_id) {
		customerServiceImpl.disableCustomer(c_id);
		return "redirect:/ca/findcussplit";
	}

	@RequestMapping(value = "ablecus")
	public String ableCus(Integer c_id) {
		customerServiceImpl.ablCustomer(c_id);
		return "redirect:/ca/findcussplit";
	}

}
