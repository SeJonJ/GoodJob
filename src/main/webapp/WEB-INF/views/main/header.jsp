<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>header</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
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
	#login_Btn {
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
		padding-right: 40px;
		padding-left: 40px;
		font-weight: 600;
		color: #333333;
		font-size: 18px;
		line-height: 27px;
	}
	#menuName>li>a:hover {
		color: #0D6EFD;
	}
	
	#reg{
		margin-left: 140px;
	}
	
	.row#row{
		padding-left: 80px;
    	margin-right: -50px;
	}
</style>
<!-- jquery cdn -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".resume").on("click", function(){
			console.log("test");
			alert("로그인 후 이용 가능합니다.");
			$(".resume").attr("href", "/goodjob/login/loginform");
		})
	})
</script>
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
				<!-- <button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button> -->
				<div class="collapse navbar-collapse" id="navbarContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0" id="menuName">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/goodjob/hireList">채용공고</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/goodjob/reviewList">기업리뷰</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/goodjob/comm/commList">커뮤니티</a></li>
						<li class="nav-item"><a class="nav-link active resume"
							aria-current="page" href="/">이력서</a></li>
							<!-- 회원/기업 주소 다르게 -->
					</ul>					
					<a href="/goodjob/login/loginform"><input id="login_Btn" class="btn btn-primary btn-sm rounded-pill" type="button" value="로그인" /></a>
					<input id="registerBtn" class="btn btn-outline-secondary btn-sm rounded-pill" type="button" value="회원가입" data-bs-toggle="modal" data-bs-target="#register"/>
				</div>
			</div>
		</nav>
	</div>
	
		<!-- 회원가입 모달 -->
	<div class="modal fade" id="register"  data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
              <h5 class="modal-title" id="myModalLabel"><strong id="reg">GoobJob 회원가입</strong></h5>
      
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" style= "padding-top: 30px; padding-bottom:30px;">
		  <div class="container-fluid">
		    <div class="row">
		      <div class="col-sm-9">
		        <div class="row">
		          <div class="col-8 col-sm-6">
		            <a href="/goodjob/login/registerformMember"><button type="button" class="btn btn-outline-primary" style="width: 140px; ">개인 회원</button></a>
		          </div>
		          <div class="col-4 col-sm-6">
		            <a href="/goodjob/login/registerformCompany"><button type="button" class="btn btn-outline-primary" style="width: 140px; ">기업 회원</button></a>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
 </div>  
</body>
</html>