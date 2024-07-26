package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AuthorMapper;
import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	AuthorMapper authorMapper;

	/* 작가 등록 */
	@Override
	public void authorEnroll(AuthorVO author) {
		authorMapper.authorEnroll(author);
	}

	/* 작가 목록 조회 */
	@Override
	public List<AuthorVO> authorGetList(Criteria cri) throws Exception {
		log.info("(service)authorGetList() ....... " + cri);
		
		return authorMapper.authorGetList(cri);
	}

	/* 총 작가 수 */
	@Override
	public int authorGetTotal(Criteria cri) throws Exception {
		log.info("(service)authorGetTotal() ....... " + cri);
		return authorMapper.authorGetTotal(cri);
	}

	/* 작가 상세 조회 */
	@Override
	public Object authorGetDetail(int authorId) throws Exception {
		log.info("(service) authorGetDetail....... " + authorId);
		return authorMapper.authorGetDetail(authorId);
	}

	/* 작가 정보 수정 */
	@Override
	public int authorModify(AuthorVO author) throws Exception {
		log.info("(service) authorModify....... " + author);
		return authorMapper.authorModify(author);
	}

}
