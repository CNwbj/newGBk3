package cn.sz.zl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.sz.zl.pojo.Quotation;
import cn.sz.zl.util.MFenYe;
import cn.sz.zl.util.Query;
@Mapper
public interface IQuotationDAO {
	//根据id编号查询报价单
	public Quotation findQuotationById(Integer quotationid);
	//查询所有的报价单
	public List<Quotation> findAllQuotation();
	//多条件分页查询
	public List<Quotation> queryQuotationByFy(MFenYe fy);
	//查询符合要求的条数
	public Integer queryRowsByQuery(Query query);
	//修改
	public boolean  UpdateQuotation(Quotation quotation);
	//添加
	public boolean insertQuotation(Quotation quotation);
}
