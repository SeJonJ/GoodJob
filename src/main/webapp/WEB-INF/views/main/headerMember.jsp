<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>header</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

<!-- jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
<style>
@import 
url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	#header {
		font-family: 'Noto Sans KR', sans-serif;
		width: 100%;
		padding: 0 100px 0 100px;
		border-bottom: 1px solid #E5E5E5;
		z-index: 800;
	}
	#loginBtn {
		width: 90px;
		font-weight: 600;
		position: relative;
		right: 30px;
	}
	#registerBtn {
		width: 90px;
		font-weight: 600;
		position: relative;
		right: 10px;
	}
	#menuName>li>a {
		padding: 10px 30px 0 30px;
		font-weight: 600;
		color: #333333;
		font-size: 18px;
		line-height: 27px;
	}
	#menuName>li>a:hover {
		color: #0D6EFD;
	}
	.profile, .dropdown-menu, .dropdown-toggle {
		margin: 0 0 0 250px; 
	}
</style>
</head>
<body class="pt-5">
	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-white" id="header">
			<div class="container-fluid">
				<nav class="navbar navbar-light bg-white">
					<a class="navbar-brand" href="/goodjob"> 
						<img src="/goodjob/images/logo.png" alt="" width="130" height="35">
					</a>
				</nav>
				<div class="collapse navbar-collapse" id="navbarContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0" id="menuName">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/goodjob/hireList">채용공고</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/goodjob/reviewList">기업리뷰</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/goodjob/comm/commList">커뮤니티</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/goodjob/myResume">이력서</a></li>
							<!-- 회원/기업 주소 다르게 -->
						<!-- 드롭다운 -->
						<li class="nav-item dropdown profile">
				          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				           <img src="/goodjob/images/profile.png" alt="" class="rounded-circle" />
				          </a>
				          <ul class="dropdown-menu">
				            <li><a class="dropdown-item" href="/goodjob/myPage/myPageMember">마이페이지</a></li>
				            <li><a class="dropdown-item" href="/goodjob/dropdownResume">내 이력서</a></li>
				            <li><hr class="dropdown-divider"></li>
				            <li><a class="dropdown-item" href="/goodjob/logout">로그아웃</a></li>
				          </ul>	
				       </li>
			       </ul>
				</div>
			</div>
		</nav>
	</div>
</body>
</html>