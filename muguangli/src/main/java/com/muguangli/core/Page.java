package com.muguangli.core;

public class Page {
	
	private Long pageNo = 1L;
	
	private Integer pageSize = 10;

	private Long pageBeginIndex;
	
	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageBeginIndex() {
		this.pageBeginIndex = (pageNo - 1) * pageSize;
		return pageBeginIndex;
	}

	public void setPageBeginIndex(Long pageBeginIndex) {
		this.pageBeginIndex = pageBeginIndex;
	}
	
}
