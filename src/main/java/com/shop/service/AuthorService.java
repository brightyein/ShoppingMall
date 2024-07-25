package com.shop.service;

import java.util.List;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

public interface AuthorService {

	/* 작가 등록 */
	public void authorEnroll(AuthorVO author) throws Exception;

	/* 작가 목록 조회 */
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception;

	/* 총 작가 수 */
	public int authorGetTotal(Criteria cri) throws Exception;

}
