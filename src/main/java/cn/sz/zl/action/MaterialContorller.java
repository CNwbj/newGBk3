package cn.sz.zl.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.sz.zl.pojo.Material;
import cn.sz.zl.service.IMaterialServce;
import cn.sz.zl.util.Excellent;

@Controller
@RequestMapping("/ml")
public class MaterialContorller {
	@Autowired
	private IMaterialServce MaterilaService;

	@RequestMapping(value="/fy" ,method=RequestMethod.GET)
	public String lookshow( HttpServletRequest request) {
		Excellent exe = new Excellent();
		//页数
		String c = request.getParameter("pagination");
		//System.out.println(c);
		
		//关键字
		String linchpin = request.getParameter("linchpin");
		//System.out.println(linchpin);
		//价格查询
		String sprice = request.getParameter("sprice");
		//System.out.println(sprice);
		String tprice = request.getParameter("tprice");
		//System.out.println(tprice);
		//判断模糊查询
		if(linchpin!=null&&!linchpin.equals("")) {
			exe.setLinchpin(linchpin);
			request.setAttribute("linchpin", linchpin);
		}
		if(sprice!=null) {
			exe.setSprice(sprice);
			request.setAttribute("sprice",sprice);
			System.out.println(sprice);
		}if(tprice!=null) {
			exe.setTprice(tprice);
			request.setAttribute("tprice",tprice);
			System.out.println(tprice);
		}
		if(c==null||c.equals("")) {
			c="1";
		}
		int pagination = Integer.parseInt(c);
		exe.setPagination(pagination);
		System.out.println(exe.getStart());
		System.out.println(exe.getEnd());
		//int ps = Integer.parseInt(sp);
		List<Material>mllist=MaterilaService.findall(exe);
		int allpag = (MaterilaService.getCount(exe)-1)/2+1;
		request.setAttribute("pagination", pagination);
		request.setAttribute("allpag", allpag);
		request.setAttribute("mllist", mllist);
		for(int i=0;i<mllist.size();i++) {
			System.out.println(mllist.get(i));
		}
		return "pages/frame/uc/Warehouse";
	}
	
}
