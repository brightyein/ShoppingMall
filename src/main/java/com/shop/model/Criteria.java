package com.shop.model;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum;
	
	private int amount;
	
	private String type;
	
	private String keyword;

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public Criteria() {
		this(1,10);
	}
	
	/* 검색 타입 데이터 배열 변환 */
	public String[] getTypeArr() {
		return type == null? new String[] {}:type.split("");
	}
	
	
}
