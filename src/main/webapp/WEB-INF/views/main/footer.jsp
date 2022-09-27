<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
<style>
@import 
url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	.container {
		font-family: 'Noto Sans KR', sans-serif;
	}
	.logo {
		float: left;
	}
	.footerTop {
		height: 60px; 
		border-bottom: 1px solid #E5E5E5;
		padding-top: 8px;
	}
	.footerLinks {
		position: relative;
		left: 20px;
	} 
	.footerLinks>a{
		text-decoration: none;
		font-weight: 400;
		font-size: 16px;
		line-height: 24px;
		color: #333333;
		padding-left: 40px;
		padding-right: 40px;
	}
	.footerText {
		padding-top: 30px;
		padding-bottom: 60px;
		font-weight: 500;
		font-size: 12px;
		line-height: 18px;
		color: #767676;
	}
	.app {
		padding-left: 370px;
	}
	.app>img {
		margin-right: 10px;
	}
	hr {
		margin-top: 0;
	}
</style>
</head>
<body>
	<hr />
	<div class="container">
		<div class="footerTop">
			<div class="logo">
				<img src="/goodjob/images/logo.png" alt="" width="110" height="30">
			</div>
			<div class="footerLinks">
				<a href="#">기업소개</a>
				<a href="#">이용약관</a>
				<a href="#">개인정보 처리방침</a>
				<a href="#">고객센터</a>
				<span class="app">
					<img src="/goodjob/images/insta.jpg" alt="" />
					<img src="/goodjob/images/facebook.jpg" alt="" />
					<img src="/goodjob/images/kakao.jpg" alt="" />
					<img src="/goodjob/images/apple.jpg" alt="" />
					<img src="/goodjob/images/android.jpg" alt="" />
				</span>
			</div>
		</div>
		<div class="footerText">
			<p>
				(주)굿잡 (대표이사:정희록) | 서울특별시 송파구 올림픽로 300 롯데월드타워 40층 | 통신판매번호 : 2022-서울송파-7019
				<br />
				유료직업소개사업등록번호 : (국내) 제2022-1907229-14-5-00018호 | (국외) 서울동부-유-2022-7 | 사업자등록번호 : 200-19-00022 | 02-719-5118
				<br />
				© goodjob,Inc. All rights reserved.
			</p>
		</div>		
	</div>
</body>
</html>