package cn.sz.zl.service;

import java.util.List;
import java.util.Map;

import cn.sz.zl.query.FHQuery;
import cn.sz.zl.util.FHUtil;



public interface ISaleService {
	
	//链表查询所有数据
	public List<FHUtil> findallInfo(FHQuery query,Integer startR, Integer stopR);

	//修改状态
	public boolean updateStateid(Integer sateid);
	//符合条件的个数
	public int querySaleRows(FHQuery query);
	//提交
	public int submit(Map map);
	//生成销售订单
	public boolean saleorder(Map map);
}
