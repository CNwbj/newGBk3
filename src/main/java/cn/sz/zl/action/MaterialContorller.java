package cn.sz.zl.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.sz.zl.pojo.Depot;
import cn.sz.zl.pojo.Material;
import cn.sz.zl.pojo.Purchase;
import cn.sz.zl.pojo.Sale;
import cn.sz.zl.pojo.Stream;
import cn.sz.zl.service.IDepotService;
import cn.sz.zl.service.IMaterialServce;
import cn.sz.zl.service.IPurchaseService;
import cn.sz.zl.service.IShipmentService;
import cn.sz.zl.service.IStreamService;
import cn.sz.zl.util.Excellent;
import cn.sz.zl.util.FHUtil;

@Controller
@RequestMapping("/ml")
public class MaterialContorller {
	// 物资控制
	@Autowired
	private IMaterialServce MaterilaService;
	// 出货控制
	@Autowired
	private IShipmentService shipmentService;
	@Autowired
	private IPurchaseService purchaseService;
	@Autowired
	private IDepotService depotService;
	@Autowired
	private IStreamService streamService;
 
	@RequestMapping(value = "/ch", method=RequestMethod.GET)
	public String lookshow(HttpServletRequest request) {
		Excellent exe = new Excellent();
		String c = request.getParameter("pagination");
		String linchpin = request.getParameter("linchpin");
		// 时间查询
		String startTime = request.getParameter("startTime");
		String stopTime = request.getParameter("stopTime");
		String statename = request.getParameter("statename");
		// 判断模糊查询
		if (linchpin != null && !linchpin.equals("")) {
			exe.setLinchpin(linchpin);
			request.setAttribute("linchpin", linchpin);
		}
		// 下拉框查询状态
		if (statename != null && !statename.equals("")) {
			if (statename.equals("已发货")) {
				request.setAttribute("statename", "已发货");
				exe.setState(8);
			} else if (statename.equals("未发货")) {
				request.setAttribute("statename", "未发货");
				exe.setState(9);
			}
		}
		if (startTime != null && startTime.indexOf("-") > 0) {
			exe.setStartTime(getDateByStr(startTime));
			request.setAttribute("startTime", startTime);
		} else {
			exe.setStartTime(null);
		}
		if (stopTime != null && stopTime.indexOf("-") > 0) {
			exe.setStopTime(getDateByStr(stopTime));
			request.setAttribute("stopTime", stopTime);
		} else {
			exe.setStopTime(null);
		}
		if (c == null || c.equals("")) {
			c = "1";
		}
		int pagination = Integer.parseInt(c);
		exe.setStart((pagination-1)*6);
		exe.setEnd(pagination*6);
		exe.setPagination(pagination);
		List<FHUtil> shlist = shipmentService.findAllAndShipments(exe);
		int allpag = (shipmentService.getCount(exe)-1)/6+1;
		request.setAttribute("pagination", pagination);
		request.setAttribute("allpag", allpag);
		request.setAttribute("shlist", shlist);
		return "pages/frame/uc/Shipments2";
	}
	/**
	 * 方法作用:通过sale表id的进行发货,当点击发货的时候
	 * 添加时间同时改变发货的状态
	 * 当点击发货的同事间触发boolean stre = streamService.insertByStream(stream);
	 * 方法生成流水单
	 * @param saleid
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="mend")
	public String UpdateShipment(Integer saleid, HttpServletRequest request) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("column5", 8);
		map.put("saleid", saleid);
		boolean flag = shipmentService.updatePurchase(map);//
		Sale sh = shipmentService.findById(saleid);
		System.out.println(saleid);
		System.out.println("*****************************");
		System.out.println("sh:" + sh);
		Integer count = sh.getGoodscount();
		System.out.println("******" + count);
		Integer depot_id = sh.getMaterial().getDepot_id();
		System.out.println(depot_id);
		Depot depot = depotService.findById(depot_id);
		System.out.println(depot);
		int depotsum = depot.getDepot_sum();
		System.out.println("+++depotsum++++" + depotsum);
		int sum = depotsum - count;
		System.out.println("扣库存前判断" + sum);
		depot.setDepot_sum(sum);
		System.out.println("fdsafdsadsafdsfdsdsfdsafs");
		boolean finger = depotService.updateDepot(depot);
		boolean flats = shipmentService.updateSysDate(map);
		System.out.println("---------------------------------");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String stream_number = sdf.format(new Date())+saleid;
		Stream stream =  new Stream();
		stream.setStream_number(stream_number);
		stream.setStream_date(sh.getCreate_date());
		stream.setDepot(depot);
		stream.setState_id(sh.getColumn5());
		stream.setSale(sh);
		System.out.println(stream_number);
		System.out.println(sh.getCreate_date());
		System.out.println(depot);
		System.out.println(sh.getColumn5());
		System.out.println(sh);
		System.out.println("+++++++++++++++++++++++++++++++++");
		boolean stre = streamService.insertByStream(stream);
		System.out.println("+++++++++++++++++________________");
		return "redirect:/ml/ch";
	}
	
	/**
	 * 查询出货和退货流水
	 * @param request
	 * @return
	 */
	@RequestMapping(value="ls")
	public String lookReturns(HttpServletRequest request){
		Excellent exe = new Excellent();
		String c = request.getParameter("pagination");
		if (c == null || c.equals("")) {
			c = "1";
		}
		int pagination = Integer.parseInt(c);
		exe.setStart((pagination-1)*6);
		exe.setEnd(pagination*6);
		exe.setPagination(pagination);
		List<Stream> st = streamService.findById(exe);
		int allpag = (streamService.getCount(exe)-1)/6 + 1;
		request.setAttribute("pagination", pagination);
		request.setAttribute("allpag", allpag);
		request.setAttribute("st", st);
		return "pages/frame/uc/Shipments";
	}
	
	
	/**
	 * 查询物资表,
	 * 展示所有Material里的数据
	 * 功能展示分页模糊查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fy", method = RequestMethod.GET)
	public String shipmentLook(HttpServletRequest request) {
		Excellent exe = new Excellent();
		String c = request.getParameter("pagination");
		String linchpin = request.getParameter("linchpin");
		String sprice = request.getParameter("sprice");
		String tprice = request.getParameter("tprice");
		if (linchpin != null && !linchpin.equals("")) {
			exe.setLinchpin(linchpin);
			request.setAttribute("linchpin", linchpin);
		} else {
			exe.setLinchpin(null);
		}
		if (sprice != null) {
			exe.setSprice(sprice);
			request.setAttribute("sprice", sprice);
		}
		if (tprice != null) {
			exe.setTprice(tprice);
			request.setAttribute("tprice", tprice);
		}
		if (c == null || c.equals("")) {
			c = "1";
		}
		int pagination = Integer.parseInt(c);
		exe.setStart((pagination-1)*6);
		exe.setEnd(pagination*6);
		exe.setPagination(pagination);
		List<Material> mllist = MaterilaService.findall(exe);
		int allpag = (MaterilaService.getCount(exe) - 1) /6 + 1;
		request.setAttribute("pagination", pagination);
		request.setAttribute("allpag", allpag);
		request.setAttribute("mllist", mllist);
		return "pages/frame/uc/Warehouse";
	}

	
	
	
	
	
	/**
	 * 采购
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cg", method = RequestMethod.GET)
	public String LookPurchase(HttpServletRequest request) {
		Excellent exe = new Excellent();
		String c = request.getParameter("pagination");
		String linchpin = request.getParameter("linchpin");
		String startTime = request.getParameter("startTime");
		String stopTime = request.getParameter("stopTime");
		if (linchpin != null && !linchpin.equals("")) {
			exe.setLinchpin(linchpin);
			request.setAttribute("linchpin", linchpin);
		}
		if (startTime != null) {
			exe.setStartTime(getDateByStr(startTime));
			request.setAttribute("startTime", startTime);
		} else {
			exe.setStartTime(null);
		}
		if (stopTime != null) {
			exe.setStopTime(getDateByStr(stopTime));
			request.setAttribute("stopTime", stopTime);
		} else {
			exe.setStopTime(null);
		}
		if (c == null || c.equals("")) {
			c = "1";
		}
		int pagination = Integer.parseInt(c);
		exe.setPagination(pagination);
		List<Purchase> pulist = purchaseService.findAllPurchase(exe);
		System.err.println("pulist:" + pulist.size());
		for (int i = 0; i < pulist.size(); i++) {
			System.out.println(pulist.get(i).getMaterial().getMaterial_name());
		}
		int allpag = (purchaseService.getCcount(exe) - 1) / 3 + 1;
		request.setAttribute("pagination", pagination);
		request.setAttribute("allpag", allpag);
		request.setAttribute("pulist", pulist);
		return "pages/frame/uc/Purchase";

	}

	/**
	 * 修改采购表
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "xiu")
	public String mendPurchase(HttpServletRequest request, Integer id) {
		String purchase_id = request.getParameter("purchase_id");
		Integer purid = Integer.parseInt(purchase_id);

		request.setAttribute("pu", purchaseService.lookshowExcellent(purid));
		return "mendshowPurchase";
	}

	/*
	 * 修改后跳转主页面显示
	 */
	@RequestMapping(value = "showup")
	public String MendShowInFor(HttpServletRequest request) {
		String id = request.getParameter("id");
		String orders_sum = request.getParameter("orders_sum");
		String cost_price = request.getParameter("cost_price");
		Integer purchase_id = Integer.parseInt(id);
		Integer cpst = Integer.parseInt(cost_price);
		Integer orders = Integer.parseInt(orders_sum);
		Purchase pur = purchaseService.lookshowExcellent(purchase_id);
		pur.setCost_price(cpst);
		pur.setOrders_sum(orders);
		purchaseService.UpdatePurchaseById(purchase_id);
		return "redirect:/ml/ch";
	}

	// 时间设置转换
	public Date getDateByStr(String str) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sf.parse(str);
			return date;
		} catch (Exception e) {
		}
		return null;
	}
}
