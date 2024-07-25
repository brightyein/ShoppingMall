<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome BookMall</title>
<link rel="stylesheet" href="resources/css/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<ul class="list">
				<c:if test="${loginMember == null}">
					<li>
						<a href="/member/login">로그인</a>
					</li>
					<li>
						<a href="/member/join">회원가입</a>
					</li>
				</c:if>
				<c:if test="${loginMember != null}">
					<c:if test="${loginMember.adminCk == 1}">
						<li><a href="/admin/main">관리자 페이지</a></li>
					</c:if>
					<li>
						<a id="gnb_logout_button">로그아웃</a>
					</li>
					<li>
						마이페이지
					</li>
					<li>
						장바구니
					</li>
				</c:if>
				<li>
					고객센터
				</li>
			</ul>
		</div>
		<div class="top_area">
			<div class="logo_area">
				<a href="/main"><img src="resources/img/logo.png"></a>
			</div>
			<div class="search_area">
				<h1>Search area</h1>
			</div>
			<div class="login_area">
				<!-- 로그아웃 상태 -->
				<c:if test="${loginMember == null}">
					<div class="login_button"><a href="/member/login">로그인</a></div>
					<span><a href="/member/join">회원가입</a></span>
				</c:if>
				<!-- 로그인 상태 -->
				<c:if test="${loginMember != null}">
					<div class="login_success_area">
					<span>회원 : ${loginMember.memberName}</span>
					<span>충전금액 : <fmt:formatNumber value="${loginMember.money}" pattern="\#,###.##"/></span>
					<span>포인트 : <fmt:formatNumber value="${loginMember.point}" pattern="#,###"/></span>
					<a href="/member/logout.do">로그아웃</a>
					</div>
				</c:if>
			</div>
			<div class="clearfix"></div>			
		</div>
		<div class="navi_bar_area">
			<h1>navi area</h1>
		</div>
		<div class="content_area">
			<h1>content area</h1>
		</div>
		<!-- Footer 영역 -->
        <div class="footer_nav">
            <div class="footer_nav_container">
                <ul>
                    <li>회사소개</li>
                    <span class="line">|</span>
                    <li>이용약관</li>
                    <span class="line">|</span>
                    <li>고객센터</li>
                    <span class="line">|</span>
                    <li>광고문의</li>
                    <span class="line">|</span>
                    <li>채용정보</li>
                    <span class="line">|</span>
                </ul>
            </div>
        </div> <!-- class="footer_nav" -->
        
        <div class="footer">
            <div class="footer_container">
                
                <div class="footer_left">
                    <img src="resources/img/logo.png">
                </div>
                <div class="footer_right">
                    (주) VBook    대표이사 : 김예인
                    <br>
                    사업자등록번호 : ooo-oo-ooooo
                    <br>
                    대표전화 : oooo-oooo(발신자 부담전화)
                    <br>
                    <br>
                    COPYRIGHT(C) <strong>wmfrjqsp.velog.com</strong>    ALL RIGHTS RESERVED.
                </div>
                <div class="clearfix"></div>
            </div>
        </div> <!-- class="footer" -->     
	</div>
</div>

<script>

/* gnb_area 로그아웃 버튼 작동 */
$("#gnb_logout_button").click(function(){
	$.ajax({
		type:"POST",
		url:"/member/logout.do",
		success:function(data){
			alert("로그아웃 성공");
			document.location.reload(); // 세션의 변경사항이 화면에 반영되도록 reload
		}
	});
});

</script>

</body>
</html>