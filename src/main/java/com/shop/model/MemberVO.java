package com.shop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	
		private String memberId;
		
		private String memberPw;
		
		private String memberName;
		
		private String memberMail;
		
		private String memberAddr1;
		
		private String memberAddr2;
		
		private String memberAddr3;
		
		// 관리자 구분(0:일반사용자, 1:관리자)
		private int adminCk;
		
		private int regDate;
		
		private int money;
		
		private int point;
	
}
