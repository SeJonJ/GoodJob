<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
<!-- 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
 -->    
 	<!-- <link rel="stylesheet" href="/login/fonts/icomoon/style.css"> -->

    <!-- <link rel="stylesheet" href="/login/css/owl.carousel.min.css"> -->

    <!-- Bootstrap CSS -->
    <!-- <link rel="stylesheet" href="/login/css/bootstrap.min.css"> -->
    
    <!-- Style -->
    <link rel="stylesheet" href="/goodjob/login/css/style.css">

	<!-- Remember to include jQuery :) -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="/goodjob/login/js/jquery-3.3.1.min.js"></script>
    <script src="/goodjob/login/js/popper.min.js"></script>
    <script src="/goodjob/login/js/bootstrap.min.js"></script>
    <script src="/goodjob/login/js/main.js"></script>
    
    <!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
	<title>Login</title>
	<style>
	.col-sm-9 {
	    text-align: center;
	    margin: auto;
	}
	
	.allButton {
	    display: flex;
	    justify-content: space-evenly	
    }
    
    .allButton#loginBtn {
    	width : 200px;
    }
    
    
    
	</style>
	<script>
		$(function(){
			
		})
		
		function loginSubmit(){
			var memberCheck = $("#member").is(':checked');
			var companyCheck = $("#company").is(':checked');
			
			var $user = $("#user");
			
			var $username = $("#username");
			var $password = $("#password");
			
			if(memberCheck){
				var username = $user.val()+" user"
				$username.val(username);
				//console.log("username : "+$username.val())
				return true;
			}else if(companyCheck){
				var username = $user.val()+" com"
				$username.val(username);
				return true;
			}else{
				return false;
			}
		}
	</script>
  </head>
  <style>
  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
 	.content, .modal {
  		font-family: 'Noto Sans KR', sans-serif;
 	}
  	.check{
  		height: 50px;
    	line-height: 35px;
  	}
  	
  	#registerbtn1 {
    	padding-left: 200px;
    }
  </style>
  <body>

<!-- <h1>임시 회원</h1>
<ul>
	<li>tester : tester123</li>
	<li>tester2 : g15ICL4g</li>
	<li>goodjob : Mdy4ZTZ9</li>
</ul> -->
	<!-- header -->
	<sec:authorize access="isAnonymous()">		
		<jsp:include page="../main/header.jsp" /> 	
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		<jsp:include page="../main/headerMember.jsp" />
	</sec:authorize> 
	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<jsp:include page="../main/headerCompany.jsp" />
	</sec:authorize> 
	
  <div class="content">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <img src="images/login_image.png" alt="Image" class="img-fluid">
        </div>
        <div class="col-md-6 contents">
          <div class="row justify-content-center">
            <div class="col-md-8">
             <div class="mb-4">
              <div class="title1">로그인이 필요한 서비스입니다.</div>
              <div class="title2">굿잡 회원이 아니면, 지금 회원가입 해주세요.</div>
              <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
				  <input type="radio" class="btn-check" name="btnradio" id="member" autocomplete="off" checked>
				  <label class="btn btn-outline-primary check" for="member">일반 회원</label>
				
				  <input type="radio" class="btn-check" name="btnradio" id="company" autocomplete="off">
				  <label class="btn btn-outline-primary check" for="company">기업 회원</label>
				
			  </div>
            </div>
            <form action="/goodjob/login" method="post" onsubmit="return loginSubmit()">
              <div class="form-group first">
               <!--  <label for="username">아이디</label> -->
                <p>아이디</p>
                <input type="text" class="form-control" id="user" name="user" placeholder="아이디">
                <input type="hidden" class="form-control" id="username" name="username">

              </div>
              <div class="form-group last mb-4">
                <!-- <label for="password">패스워드</label> -->
                <p>비밀번호</p>
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
              </div>
              
              <div class="d-flex mb-5 align-items-center">
                <label class="control control--checkbox mb-0"><span class="caption">로그인 유지</span>
                  <input type="checkbox" checked="checked"/>
                  <div class="control__indicator"></div>
                </label>
                <span class="ml-auto">
					<!-- modal 구동 버튼 (trigger) -->
					<button type="button" class="btn btn-outline-secondary btn-sm" data-bs-toggle="modal" data-bs-target="#findAccount">
					  아이디/비밀번호 찾기
					</button>
                </span>
               </div> 
              <div class="allButton">
	              <button type="button" id="Btn" value="회원가입" class="btn btn-outline-secondary btn-lg" data-bs-toggle="modal" data-bs-target="#register">회원가입</button>
	              <input type="submit" id="loginBtn" value="로그인" class="btn btn-outline-primary btn-lg">
              </div>
              
              <div class="social-login">
	            <span class="d-block text-left my-4 text-muted">SNS로 간편 로그인</span>
                <div>
                <a href="http://43.200.224.121:8080/goodjob/oauth2/authorization/kakao">
					<img src="/goodjob/login/images/kakaotalk.png" width="200" height="auto" alt="" />
                </a>
                <a href="http://43.200.224.121:8080/goodjob/oauth2/authorization/naver">
					<img src="/goodjob/login/images/naver.png" width="200" height="auto" alt="" />
                </a>
                </div>
              </div> 
            </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
<!-- 회원가입 Modal은 헤더에서 불러옴 -->

  
<!-- 계정 정보 찾기 Modal -->
<div class="modal fade" id="findAccount" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
              <h5 class="modal-title" id="myModalLabel"><strong>아이디/비밀번호 찾기</strong></h5>
      
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body"  style= "padding-top: 30px; padding-bottom:30px;">
		  <div class="container-fluid">
		    <div class="row">
		      <div class="col-sm-9">
		        <div class="row">
		          <div class="col-8 col-sm-6 ">
		            <a href="/goodjob/login/findAcntMember"><button type="button" class="btn btn-outline-primary" style="width: 130px; height: 50px;">개인 회원</button></a>
		          </div>
		          <div class="col-4 col-sm-6">
		            <a href="/goodjob/login/findAcntCompany"><button type="button" class="btn btn-outline-primary" style="width: 130px; height: 50px;">기업 회원</button></a>
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

 <!-- footer -->
 <jsp:include page="../main/footer.jsp" /> 

  </body>
</html>