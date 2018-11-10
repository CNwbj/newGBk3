package cn.sz.zl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.sz.zl.query.FHQuery;
import cn.sz.zl.util.FHUtil;



@Mapper

public interface ISaleDao {
	//链表查询所有数据
	public List<FHUtil> findAllInfo(FHQuery query);
	//符合条件的数据
	public int querySaleRows(FHQuery query);
	//修改状态
	public boolean updateStateid(Integer sateid);
	//提交
	public int submit(Map map);
	//生成销售订单
	public boolean saleorder(Map map);
}
