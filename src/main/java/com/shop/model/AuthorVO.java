package com.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorVO {

    private int authorId;
    
    private String authorName;
    
    private String nationId;
    
    private String nationName;
    
    private String authorInfo;
    
    private Date regDate;
    
    private Date updateDate;
    
    public void setNationId(String nationId) {
    	this.nationId = nationId;
    	if(nationId.equals("01")) {
    		this.nationName = "국내";
    	} else {
    		this.nationName = "국외";
    	}
    }
}
