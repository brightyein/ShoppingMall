package com.shop.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.MemberVO;
import com.shop.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	/* 회원가입 페이지 이동 */
	@GetMapping("join")
	public void joinGET() {
		logger.info("회원가입 페이지 진입");
	}
	
	/* 회원가입 */
	@PostMapping("/join")
	public String joinPOST(MemberVO member) throws Exception {
		
		String rawPw = ""; // 인코딩 전
		String encodePw = ""; // 인코딩 후 
		
		rawPw = member.getMemberPw(); // 입력받은 비밀번호
		encodePw = pwEncoder.encode(rawPw); // 비밀번호 인코딩
		member.setMemberPw(encodePw); // 인코딩된 비밀번호 저장
		
		memberService.memberJoin(member);
		
		return "redirect:/main";
	}
	
	/* 아이디 중복체크 */
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception {
		//logger.info("memberIdChk() 진입");
		
		int result = memberService.idCheck(memberId);
		logger.info("결과값 = " + result);
		
		if (result !=0) {
			return "fail"; 
		} else {
			return "success"; 
		}
	}
	
	/* 이메일 인증 */
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String email) throws Exception{
		
		// 뷰(view)로부터 넘어온 데이터 확인
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증번호 : " + email);
		
		// 인증번호(난수) 생성
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 " + checkNum);
		
		// 이메일 전송
		String setFrom = "wmfrjqsp@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = 
						"홈페이지를 방문해주셔서 감사합니다." +
						"<br><br>" +
						"인증 번호는 " + checkNum + "입니다." +
						"<br>" +
						"해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		try {
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String num = Integer.toString(checkNum);
		return num;
	}
	
	/* 로그인 페이지 이동 */
	@GetMapping("login")
	public void loginGET() {
		logger.info("로그인 페이지 진입");
	}
	
	/* 로그인 */
	@PostMapping("/login.do")
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {
		
		HttpSession session = request.getSession();
		
		String rawPw = "";
		String encodePw = "";
		
		MemberVO loginMember = memberService.memberLogin(member);
		
		if (loginMember != null) { // 아이디 일치 시
			logger.info("아이디 일치");
			rawPw = member.getMemberPw(); // 입력받은 비밀번호 
			encodePw = loginMember.getMemberPw(); // 저장된 인코딩 비밀번호
			
			if (pwEncoder.matches(rawPw, encodePw) == true) { // 비밀번호 일치여부 판단
				logger.info("비밀번호 일치");
				loginMember.setMemberPw(""); // 인코딩된 비밀번호 정보 지움
				session.setAttribute("loginMember", loginMember);
				return "redirect:/main"; // 메인페이지로 이동
				
			} else {
				logger.info("비밀번호 불일치");
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login"; // 로그인 페이지로 이동
			}
		} else { // 아이디 불일치 시
			logger.info("아이디 불일치");
			rttr.addFlashAttribute("result", 0);
			return "redirect:/member/login";
		}

	}
	
	/* 로그아웃 */
	@GetMapping("/logout.do")
	public String logoutMainGET(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/main";
		
	}
	
	/* 비동기 로그아웃 */
	@PostMapping("/logout.do")
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
	}

}
