package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.AuthorVO;
import com.shop.model.BookVO;
import com.shop.model.Criteria;
import com.shop.model.PageDTO;
import com.shop.service.AdminService;
import com.shop.service.AuthorService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AdminService adminService;

	/* 관리자 메인 페이지 이동 */
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception{
		
	}
	
	/* 상품 관리 페이지 접속 */
	@GetMapping("/goodsManage")
	public void goodsManageGET() throws Exception{
		log.info("상품 관리 페이지 접속");
	}
	
	/* 상품 등록 페이지 접속 */
	@GetMapping("/goodsEnroll")
	public void goodsEnrollGET() throws Exception{
		log.info("상품 등록 페이지 접속");
	}
	
	/* 작가 등록 페이지 접속 */
	@GetMapping("/authorEnroll")
	public void authorEnrollGET() throws Exception{
		log.info("작가 등록 페이지 접속");
	}
	
	/* 작가 관리 페이지 접속 */
	@GetMapping("/authorManage")
	public void authorManageGET(Criteria cri, Model model) throws Exception{
		log.info("작가 관리 페이지 접속..........." + cri);
		
		/* 작가 목록 출력 데이터 */
		List list = authorService.authorGetList(cri);
		
		// 결과 존재 여부 판단해 프론트로 전달
		if(!list.isEmpty()) { 
			model.addAttribute("list",list);
		} else {
			model.addAttribute("listCheck","empty");
		}
		
		/* 페이지 이동 인터페이스 데이터 */
		int total = authorService.authorGetTotal(cri);
		PageDTO pageMaker = new PageDTO(cri, total);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	/* 작가 등록 */
	@PostMapping("/authorEnroll.do")
	public String aurhotEnrollPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {
		log.info("authorEnroll :" + author);
		
		authorService.authorEnroll(author);
		rttr.addFlashAttribute("enroll_result", author.getAuthorName());
		
		return "redirect:/admin/authorManage";
	}
	
	/* 작가 상세 페이지 */
	@GetMapping({"/authorDetail", "/authorModify"})
	public void authorGetInfoGet(Integer authorId, Criteria cri, Model model) throws Exception {
		log.info("authorDetail........" + authorId);
		
		/* 작가 관리 페이지 정보 */
		model.addAttribute("cri", cri);
		
		/* 선택 작가 정보 */
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
	}
	
	/* 작가 정보 수정 */
	@PostMapping("/authorModify")
	public String authorModifyPOST(AuthorVO author, RedirectAttributes rttr) throws Exception {
		log.info("authorModifyPOST........" + author);
		
		int result = authorService.authorModify(author);
		
		rttr.addFlashAttribute("modify_result", result);
		
		return "redirect:/admin/authorManage";
	}
	
	/* 상품 등록 */
	@PostMapping("/goodsEnroll")
	public String goodsEnrollPOST(BookVO book, RedirectAttributes rttr) {
		adminService.bookEnroll(book);
		rttr.addFlashAttribute("enroll_result", book.getBookName());
		
		return "redirect:/admin/goodsManage";
	}
	
	/* 작가 검색 팝업창 */
	@GetMapping("/authorPop")
	public void authorPopGET() throws Exception{
		
		log.info("authorPopGET.......");
	
	}
	
}
