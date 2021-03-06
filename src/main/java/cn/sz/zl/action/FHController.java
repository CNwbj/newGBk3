package cn.sz.zl.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.sz.zl.pojo.TalkPrice;
import cn.sz.zl.query.FHQuery;
import cn.sz.zl.service.IReturnGoodsService;
import cn.sz.zl.service.ISaleService;
import cn.sz.zl.service.IThirdAccountService;
import cn.sz.zl.util.FHFenYe;
import cn.sz.zl.util.FHUtil;

@Controller
@RequestMapping("/pc")
public class FHController implements Serializable{
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(FHController.class);
	@Autowired
	@Qualifier("redisTemplate")
	RedisTemplate<Object,Object> rt;
	@Autowired
	private ISaleService iSaleServiceImpl;
	@Autowired
	private IReturnGoodsService iReturnGoodsServiceImpl;
	@Autowired
	private IThirdAccountService iThirdAccountServiceImpl;
	
	/**
	 * 多条件查询sale+分页
	 * @param query
	 * @param fy
	 * @param request
	 * @return
	 */
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
		log.warn("操作了数据库");
		log.debug("进入gopage");
		fy.setAllCount(counts);
		
		List<FHUtil> lss = iSaleServiceImpl.findallInfo(query,fy.getStartRows(),fy.getStopRows());
		if(lss!=null) {
			List<FHUtil> lsh = new ArrayList<FHUtil>();
			for(FHUtil fh : lss) {
				lsh.add(fh);
			}
			int saleno = lsh.hashCode();
			String sale = String.valueOf(saleno);
			rt.opsForValue().set(sale,lsh);
		}
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.addObject("lss",lss);
		mv.setViewName("SaleList");
		return mv;
	}
	/**
	 * 更改状态分页+送审
	 * @param request
	 * @param query
	 * @param fy
	 * @return
	 */
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
		log.warn("操作了数据库--update");
		log.debug("进入updatestateid");
		List<FHUtil> lss = iSaleServiceImpl.findallInfo(query,fy.getStartRows(),fy.getStopRows());
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.addObject("lss",lss);
		mv.setViewName("SaleList");
		return mv;
		}
	/**
	 * 用户提交
	 * @param req
	 * @return
	 */
	//14成交 //15未成交
	@RequestMapping("/submit")
	@ResponseBody
	public Map<String,Object> bargaining(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		String cid = req.getParameter("c_id");
		Integer c_id = Integer.parseInt(cid);
		String mid = req.getParameter("material_id");
		Integer m_id = Integer.parseInt(mid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("c_id", c_id);
		map.put("m_id", m_id);
		List<TalkPrice> tp = iSaleServiceImpl.bargaining(map);
		Collections.sort(tp, new Comparator<TalkPrice>() {
			@Override
			public int compare(TalkPrice o1, TalkPrice o2) {
				// TODO Auto-generated method stub
				return o1.getTalkdate().compareTo(o2.getTalkdate());
			}
		});
		if(tp.size()==0) {
			String realprice1 = req.getParameter("realprice");
			Integer realprice = Integer.parseInt(realprice1);
			String cpid = req.getParameter("c_paymentid");
			Integer c_paymentid = Integer.parseInt(cpid);
			String goods = req.getParameter("goodscount");
			Integer goodscount = Integer.parseInt(goods);
			String sendaddr = req.getParameter("sendaddr");
			map.put("sendaddr", sendaddr);
			map.put("realprice", realprice);
			map.put("c_paymentid",c_paymentid);
			map.put("goodscount", goodscount);
			iSaleServiceImpl.saleorder(map);
			Map<String,Object> mapp=new HashMap<String,Object>();
			mapp.put("flag", "true");
			return mapp;
		}
		TalkPrice Tp = tp.get(tp.size()-1);
		String cpid = req.getParameter("c_paymentid");
		Integer c_paymentid = Integer.parseInt(cpid);
		String goods = req.getParameter("goodscount");
		Integer goodscount = Integer.parseInt(goods);
		String sendaddr = req.getParameter("sendaddr");
		map.put("sendaddr", sendaddr);
		map.put("realprice", Tp.getQ_price()*goodscount);
		map.put("c_paymentid",c_paymentid);
		map.put("goodscount", goodscount);
		iSaleServiceImpl.saleorder(map);
		log.warn("操作了数据库--insert");
		Map<String,Object> mapp=new HashMap<String,Object>();
		mapp.put("flag", "true");
		return mapp;
	}
	/**
	 * 多条件查询returngoods+分页
	 * @param query
	 * @param fy
	 * @param request
	 * @return
	 */
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
		log.warn("操作了数据库--select");
		fy.setAllCount(counts);
		List<FHUtil> lf = iReturnGoodsServiceImpl.queryAll(query,fy.getStartRows(),fy.getStopRows());
		if(lf!=null) {
			List<FHUtil> lsh = new ArrayList<FHUtil>();
			for(FHUtil fh : lf) {
				lsh.add(fh);
			}
			rt.opsForValue().set("sale",lsh);
		}
		log.warn("操作了数据库--select");
		mv.addObject("lf",lf);
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.setViewName("ReturnGoodsList");
		return mv;
	}
	/**
	 * 更改退货状态+分页（退货）
	 * @param request
	 * @param query
	 * @param fy
	 * @return
	 */
	@RequestMapping("/updatestate")
	public ModelAndView updatestate(HttpServletRequest request,FHQuery query,FHFenYe fy,HttpSession session){
		ModelAndView mv = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
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
		log.warn("操作了数据库--select");
		fy.setAllCount(counts);
		String cid = request.getParameter("c_id").trim();
		System.out.println(cid);
		Integer c_id = Integer.parseInt(cid);
		String s_id = (String)session.getAttribute("returnSaleid");
		Integer saleid = null;
		if(s_id!=null) {
			String s_idd = s_id.trim();
			System.out.println(s_id);
			saleid = Integer.parseInt(s_idd);
		}
		
		Integer money = iSaleServiceImpl.queryPay(saleid);
		iThirdAccountServiceImpl.updateAccount(money);
		log.warn("扣钱");
		map.put("money", money);
		map.put("c_id", c_id);
		iThirdAccountServiceImpl.updateUserAccount(map);
		log.warn("退款给客户");
		String idd = request.getParameter("status");
		Integer id = Integer.parseInt(idd);
		iReturnGoodsServiceImpl.updatestate(id);
		List<FHUtil> lf = iReturnGoodsServiceImpl.queryAll(query,fy.getStartRows(),fy.getStopRows());
		log.warn("操作了数据库--select");
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.addObject("lf",lf);
		mv.setViewName("ReturnGoodsList");
		return mv;
		}
	/**
	 * 用户点击退货按钮，退货表分页+修改sale表的退货状态
	 * @param req
	 * @return
	 */
	@RequestMapping("/returngoods")
	public ModelAndView returnOrder(HttpServletRequest req,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		Map<String,Object> map = new HashMap<String,Object>();
		String cid = req.getParameter("c_id");
		Integer c_id = Integer.parseInt(cid);
		String mid = req.getParameter("material_id");
		Integer m_id = Integer.parseInt(mid);
		String counts = req.getParameter("goodscount");
		Integer goodscount = Integer.parseInt(counts);
		String s_id = req.getParameter("saleid");
		session.setAttribute("returnSaleid", s_id);
		
		map.put("c_id", c_id);
		map.put("m_id", m_id);
		map.put("goodscount", goodscount);
		log.warn("操作了数据库");
		iReturnGoodsServiceImpl.returnorder(map);
		mv.setViewName("cate_pencil");
		return mv;
	}
	/**
	 * 用户确认收货
	 * @param req
	 * @return
	 */
	@RequestMapping("/confirm")
	public String confirm(HttpServletRequest req) {
		String mon = req.getParameter("money");
		Integer money = Integer.parseInt(mon);
		iThirdAccountServiceImpl.updateAccount(money);
		log.warn("用户扣款");
		String cid = req.getParameter("c_id");
		Integer c_id = Integer.parseInt(cid);
		String sid = req.getParameter("saleid");
		Integer saleid = Integer.parseInt(sid);
		iSaleServiceImpl.updateColumn6(saleid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("money", money);
		map.put("c_id", c_id);
		log.warn("给第三方打款");
		iThirdAccountServiceImpl.updateUserAccount(map);
		return "cate_pencil";
	}
	/**
	 * salelist批量修改
	 * @param request
	 * @param query
	 * @param fy
	 * @return
	 */
	@RequestMapping("/updateAll")
	public ModelAndView updateAll(HttpServletRequest request,FHQuery query,FHFenYe fy) {
		ModelAndView mv = new ModelAndView();
		String str = request.getParameter("status");
		String[] strl = str.split(",");
		List<String> resultList = new ArrayList<>(strl.length);
		for (String s : strl) {
			resultList.add(s);
		}
		iSaleServiceImpl.updateAll(resultList);
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
	@RequestMapping("updateAllgoods")
	public ModelAndView updateAllGoods(HttpServletRequest request,FHQuery query,FHFenYe fy) {
		ModelAndView mv = new ModelAndView();
		String str = request.getParameter("status");
		String[] strl = str.split(",");
		List<String> resultList = new ArrayList<>(strl.length);
		for (String s : strl) {
			resultList.add(s);
		}
		iReturnGoodsServiceImpl.updateAllGoods(resultList);
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
		log.warn("操作了数据库--select");
		fy.setAllCount(counts);
		List<FHUtil> lf = iReturnGoodsServiceImpl.queryAll(query,fy.getStartRows(),fy.getStopRows());
		log.warn("操作了数据库--select");
		mv.addObject("lf",lf);
		mv.addObject("dataurl","pc/gopage");
		mv.addObject("pageUrl","SaleList");
		mv.addObject("allpage",fy.getAllPage());
		mv.addObject("fy",fy);
		mv.setViewName("ReturnGoodsList");
		return mv;
	}
	/*@Scheduled(cron = "0/2 * * * * *")
	public void timer() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("当前时间为:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}*/
}

