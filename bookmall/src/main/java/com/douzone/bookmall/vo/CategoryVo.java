package com.douzone.bookmall.vo;

public class CategoryVo {
	private String category;
	private Long no;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "CategoryVo [category=" + category + ", no=" + no + "]";
	}


	
}
