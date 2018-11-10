package cn.sz.zl.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.sz.zl.query.FHQuery;
import cn.sz.zl.service.IReturnGoodsService;
import cn.sz.zl.service.ISaleService;
import cn.sz.zl.util.FHFenYe;
import cn.sz.zl.util.FHUtil;

@RestController
@RequestMapping("/pc")
public class FHController {
	@Autowired
	private ISaleService iSaleServiceImpl;
	@Autowired
	private IReturnGoodsService iReturnGoodsServiceImpl;
	@RequestMapping("/gopage")
	public ModelAndView gopage(FHQuery query,FHFenYe fy,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(fy==null) {
			fy = new FHFenYe();
		}
		if(query == null) {
			query = new FHQuery();
		}
		if(fy.getCp()!=null) {
			request.setAttribute("cp", fy.getCp());
		}
		Integer counts = iSaleServiceImpl.querySaleRows(query);
		fy.setAllCount(counts);
		List<FHUtil> lss = iSaleServiceImpl.findallInfo(query,fy.getStartRows(),fy.getStopRows());
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.addObject("lss",lss);
		mv.setViewName("SaleList");
		return mv;
	}
	
	@RequestMapping("/updatestateid")
	public ModelAndView updatestateid(HttpServletRequest request,FHQuery query,FHFenYe fy){
		ModelAndView mv = new ModelAndView();
		if(fy==null) {
			fy = new FHFenYe();
		}
		if(query == null) {
			query = new FHQuery();
		}
		if(fy.getCp()!=null) {
			request.setAttribute("cp", fy.getCp());
		}
		Integer counts = iSaleServiceImpl.querySaleRows(query);
		fy.setAllCount(counts);
		String idd = request.getParameter("status");
		Integer id = Integer.parseInt(idd);
		iSaleServiceImpl.updateStateid(id);
		List<FHUtil> lss = iSaleServiceImpl.findallInfo(query,fy.getStartRows(),fy.getStopRows());
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.addObject("lss",lss);
		mv.setViewName("SaleList");
		return mv;
		}
	@RequestMapping("/submit")
	public ModelAndView gosubmit(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String cid = req.getParameter("c_id");
		Integer c_id = Integer.parseInt(cid);
		String mid = req.getParameter("m_id");
		Integer m_id = Integer.parseInt(mid);
		Map map = new HashMap();
		map.put("c_id", c_id);
		map.put("m_id", m_id);
		Integer price = iSaleServiceImpl.submit(map);
		if(price==0) {
			mv.setViewName("404");
			return mv;
		}
		String cpid = req.getParameter("c_paymentid");
		Integer c_paymentid = Integer.parseInt(cpid);
		String goods = req.getParameter("goodscount");
		Integer goodscount = Integer.parseInt(goods);
		String sendaddr = req.getParameter("sendaddr");
		map.put("sendaddr", sendaddr);
		map.put("price", price);
		map.put("c_paymentid",c_paymentid);
		map.put("goodscount", goodscount);
		iSaleServiceImpl.saleorder(map);
		mv.setViewName("SaleList");
		return mv;
	}
	@RequestMapping("/gogoods")
	public ModelAndView gogoods(FHQuery query,FHFenYe fy,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(fy==null) {
			fy = new FHFenYe();
		}
		if(query == null) {
			query = new FHQuery();
		}
		if(fy.getCp()!=null) {
			request.setAttribute("cp", fy.getCp());
		}
		Integer counts = iReturnGoodsServiceImpl.queryGoodsRows(query);
		fy.setAllCount(counts);
		List<FHUtil> lf = iReturnGoodsServiceImpl.queryAll(query,fy.getStartRows(),fy.getStopRows());
		mv.addObject("lf",lf);
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.setViewName("ReturnGoodsList");
		return mv;
	}
	
	@RequestMapping("/updatestate")
	public ModelAndView updatestate(HttpServletRequest request,FHQuery query,FHFenYe fy){
		ModelAndView mv = new ModelAndView();
		if(fy==null) {
			fy = new FHFenYe();
		}
		if(query == null) {
			query = new FHQuery();
		}
		if(fy.getCp()!=null) {
			request.setAttribute("cp", fy.getCp());
		}
		Integer counts = iReturnGoodsServiceImpl.queryGoodsRows(query);
		fy.setAllCount(counts);
		String idd = request.getParameter("status");
		Integer id = Integer.parseInt(idd);
		iReturnGoodsServiceImpl.updatestate(id);
		List<FHUtil> lf = iReturnGoodsServiceImpl.queryAll(query,fy.getStartRows(),fy.getStopRows());
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.addObject("lf",lf);
		mv.setViewName("ReturnGoodsList");
		return mv;
		}
	
}

