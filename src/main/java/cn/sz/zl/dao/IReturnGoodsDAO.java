package cn.sz.zl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import cn.sz.zl.query.FHQuery;
import cn.sz.zl.util.FHUtil;
@Mapper

public interface IReturnGoodsDAO {
	//查询退货表链表查询
	public List<FHUtil> queryAll(FHQuery query );
	//修改状态
	public boolean updatestate(Integer stateid);
	//符合条件的数据
	public int queryGoodsRows(FHQuery query);
}
