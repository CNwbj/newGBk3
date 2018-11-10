package cn.sz.zl.util;

public class Excellent {
	//关键字
		private String linchpin;
		//分页数
		private Integer pagination;
		//当前页码数
		private Integer ps=2;
	
		private Integer start;
		
		private Integer end;
		
		//价格最小
		private String sprice;
		//价格最大
		private String tprice;
		
		public String getLinchpin() {
			return linchpin;
		}
		public void setLinchpin(String linchpin) {
			this.linchpin = linchpin;
		}
		public Integer getPagination() {
			return pagination;
		}
		public void setPagination(Integer pagination) {
			this.pagination = pagination;
		}
		public String getSprice() {
			return sprice;
		}
		public void setSprice(String sprice) {
			this.sprice = sprice;
		}
		public String getTprice() {
			return tprice;
		}
		public void setTprice(String tprice) {
			this.tprice = tprice;
		}
		public Integer getStart() {
			return  pagination*ps;
		}
		public void setStart(Integer start) {
			this.start =start;
		}
		public Integer getEnd() {
			return (pagination-1)*ps;
		}
		public void setEnd(Integer end) {
			this.end = end;
		}
		public Integer getPs() {
			return ps;
		}
		public void setPs(Integer ps) {
			this.ps = ps;
		}
		
		
		
}
