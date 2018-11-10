package cn.sz.zl.action;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sz.zl.pojo.Permission;
import cn.sz.zl.service.IPermissionService;

@Controller
@RequestMapping("/pc")
public class PermissionController {
	@Autowired
	private IPermissionService ps;
	@RequestMapping(value="findPriByLoginname")
	public String queryPermissionByLoginname(Model model) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		String loginname = (String) session.getAttribute("currentUser");
		
		List<Permission> perlist = ps.queryPermissionByLoginname(loginname);
		model.addAttribute("perlist", perlist);
		model.addAttribute("loginname", loginname);
		return "pages/frame/left";
	}
}
