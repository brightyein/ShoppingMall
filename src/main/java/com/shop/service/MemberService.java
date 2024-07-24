package com.shop.service;

import com.shop.model.MemberVO;

public interface MemberService {
	
	// 회원가입
	public void memberJoin(MemberVO member) throws Exception;

	// 아이디 중복체크
	public int idCheck(String memberId) throws Exception;

}