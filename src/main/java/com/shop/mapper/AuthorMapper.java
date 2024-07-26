package com.shop.mapper;

import java.util.List;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

public interface AuthorMapper {

	/* 작가 등록 */
	void authorEnroll(AuthorVO author);

	/* 작가 조회 */
	List<AuthorVO> authorGetList(Criteria cri);
	
	/* 총 작가 수 */
	public int authorGetTotal(Criteria cri);

	/* 작가 상세 조회 */
	AuthorVO authorGetDetail(int authorId);

	/* 작가 정보 수정 */
	int authorModify(AuthorVO author);

}
