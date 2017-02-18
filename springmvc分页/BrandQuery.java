package com.yzj.core.bean;

import java.io.Serializable;

public class BrandQuery extends Brand implements Serializable {
	
	private static final int PAGE_SIZE=5;
	private Integer pageNum;
	private Integer startRow;
	private Integer pageSize = PAGE_SIZE;
	public Integer getPageNum() {
		return pageNum;
	}
	//设置当前页数的时候得到查询起始行数
	public void setPageNum(Integer pageNum) {
		startRow = (pageNum-1)*pageSize;
		this.pageNum = pageNum;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
