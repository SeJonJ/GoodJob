<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="/goodjob/login/fonts/icomoon/style.css">

<link rel="stylesheet" href="/goodjob/login/css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/goodjob/login/css/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="/goodjob/login/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- 주소 찾기 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script>
	var idchk = false; // 아이디 검사
	var pwdchk = false; // 패스워드
	var pwdconfrimchk = false; // 패스워드 확인
	var namechk = false; // 이름
	var emchk = false; // 이메일
	var emconfirmchk = false; // 이메일 인증
	var birchk = false; // 생일
	var phchk = false; // 핸드폰
	

	$(function() {
		var $mid = $("#mid");
		var $id = $("#id");
		var $mpwd = $("#mpwd");
		var $mname = $("#mname");
		var $pwdconfrim = $("#pwdconfrim");
		var $memail = $("#memail");
		var $mbrith = $("#mbrith");
		var $mphone = $("#mphone");
		
		var $checkEmail = $("#checkEmail"); // 인증번호 발송 버튼
		var $memailconfirm = $("#memailconfirm"); // 인증번호 확인input
		var $memailconfirmTxt = $("#memailconfirmTxt"); // 인증번호 확인 txt
		
		//console.dir("mid : "+$mid);
		
		// 아이디 정규식
		$mid.on("keyup", function() { // 키보드 눌렀을 때 실행
			var regExp = /^[a-z]+[a-z0-9]{5,15}$/g;

			if (!regExp.test($mid.val())) { // id 가 공백인 경우 체크
				idchk = false;
				$id.html("<span id='check'>사용할 수 없는 아이디입니다.</span>");
				$("#check").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			} else { // 공백아니면 중복체크
				$.ajax({
					type : "POST",
					url : "/goodjob/login/checkid",
					data : {
						"type" : "user",
						"id" : $mid.val()
					},
					success : function(data) {
						if (data == 1) { // 1이면 중복
							idchk = false;
							$id.html("<span id='check'>이미 존재하는 아이디입니다</span>")
							$("#check").css({
								"color" : "#FA3E3E",
								"font-weight" : "bold",
								"font-size" : "10px"

							})
							//console.log("중복아이디");
						} else { // 아니면 중복아님
							idchk = true;
							$id.html("<span id='check'>사용가능한 아이디입니다</span>")

							$("#check").css({
								"color" : "#0D6EFD",
								"font-weight" : "bold",
								"font-size" : "10px"

							})
							//console.log("중복아닌 아이디");
						}
					}
				})
			}
		});

		// 비밀번호 정규식
		$mpwd.on("keyup", function() {
			var regExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
			//console.log("email : "+$memail.val());
			if (!regExp.test($mpwd.val())) {
				//console.log("형식 미확인");

				$("#cpwdnew").html("<span id='chkpwd'>패스워드 형식이 맞지 않습니다</span>")
				$("#chkpwd").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				pwdchk = false;
			} else {
				//console.log("형식 확인");
				$("#cpwdnew").html("<span id='chkpwd'>패스워드 형식을 확인했습니다</span>")
				$("#chkpwd").css({
					"color" : "#0D6EFD",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				pwdchk = true;
				//console.log("idchk : "+idchk);
				//console.log("pwdconfrimchk : "+pwdconfrimchk);
				//console.log("emlchkchk : "+emlchkchk);

			}
		})

		// 패스워드 2중 검사
		$pwdconfrim.on("keyup", function() {
			if ($pwdconfrim.val() != $mpwd.val()) {
				pwdconfrimchk = false;
				//console.log("불일치");
				$("#pwdText").html("<span id='checkpwd'>비밀번호가 일치하지 않습니다</span>")
				$("#checkpwd").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			} else {
				pwdconfrimchk = true;
				//console.log("동일한 비밀번호");
				$("#pwdText").html("<span id='checkpwd'>비밀번호 일치 확인</span>")
				$("#checkpwd").css({
					"color" : "#0D6EFD",
					"font-weight" : "bold",
					"font-size" : "10px"

				})
			}
		})

		// 이름 확인
		$mname.on("keyup", function() {
			if ($mname.val() == "") { // id 가 공백인 경우 체크
				namechk = false;
				$("#nameTxt").html("<span id='nmchk'>이름은 필수입니다</span>");
				$("#nmchk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			} else {
				namechk = true;
				$("#nmchk").hide();
			}
		})

		// 생년월일 정규식 검사
		$mbrith.on("keyup",function() {
							var regExp = /([0-9]{4}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))$/;
							//console.log("email : "+$memail.val());
							if (!regExp.test($mbrith.val())) {
								//console.log("형식 미확인");
								birchk = false;

								$("#briTxt")
										.html(
												"<span id='chkbir'>생년월일 형식이 맞지않습니다</span>")
								$("#chkbir").css({
									"color" : "#FA3E3E",
									"font-weight" : "bold",
									"font-size" : "10px"
								})
							} else {
								birchk = true;

								//console.log("형식 확인");
								$("#briTxt")
										.html(
												"<span id='chkbir'>생년월일 형식을 확인했습니다</span>")
								$("#chkbir").css({
									"color" : "#0D6EFD",
									"font-weight" : "bold",
									"font-size" : "10px"
								})
								//console.log("idchk : "+idchk);
								//console.log("pwdconfrimchk : "+pwdconfrimchk);
								//console.log("emlchkchk : "+emlchkchk);

							}
						})

		// 이메일 정규식 검사
		$memail.on("keyup",function() {
							var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
							//console.log("email : "+$memail.val());
							if (!regExp.test($memail.val())) {
								//console.log("형식 미확인");
								emchk  = false;

								$("#mailTxt")
										.html(
												"<span id='chkmail'>이메일 형식이 맞지 않습니다</span>")
								$("#chkmail").css({
									"color" : "#FA3E3E",
									"font-weight" : "bold",
									"font-size" : "10px"
								})
							} else {
								emchk  = true;

								//console.log("형식 확인");
								$("#mailTxt")
										.html(
												"<span id='chkmail'>이메일을 형식을 확인했습니다</span>")
								$("#chkmail").css({
									"color" : "#0D6EFD",
									"font-weight" : "bold",
									"font-size" : "10px"
								})
							}
						})
						
		// 이메일 인증번호
		$checkEmail.click(function() {
			$.ajax({
				type : "POST",
				url : "/goodjob/login/mailConfirm",
				data : {
					"email" : $memail.val()
				},
				success : function(data){
					alert("해당 이메일로 인증번호 발송이 완료되었습니다. \n 확인부탁드립니다.")
					console.log("data : "+data);
					chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt);
				}
			})
		})

		// 핸드폰 번호
		$mphone.on("keyup", function() {
			var regExp = /^\d{3}\d{4}\d{4}$/;
			//console.log("email : "+$memail.val());
			if (!regExp.test($mphone.val())) {
				//console.log("형식 미확인");

				$("#phoneTxt").html(
						"<span id='chkphone'>핸드폰 번호 형식이 맞지 않습니다</span>")
				$("#chkphone").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				phchk = false;
			} else {
				//console.log("형식 확인");
				$("#phoneTxt").html(
						"<span id='chkphone'>핸드폰 번호를 형식을 확인했습니다</span>")
				$("#chkphone").css({
					"text-align" : "center",
					"color" : "#0D6EFD",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				phchk = true;
				//console.log("idchk : "+idchk);
				//console.log("pwdconfrimchk : "+pwdconfrimchk);
				//console.log("emlchkchk : "+emlchkchk);

			}
		})

		// selectBox 관련 JS
		var selectTarget = $('.selectbox select');

		// focus 가 되었을 때와 focus 를 잃었을 때
		selectTarget.on({
			'focus' : function() {
				$(this).parent().addClass('focus');
			},
			'blur' : function() {
				$(this).parent().removeClass('focus');
			}
		});

		selectTarget.change(function() {
			var select_name = $(this).children('option:selected').text();
			$(this).siblings('label').text(select_name);
			$(this).parent().removeClass('focus');
		});

	});
	
	// 이메일 인증번호 체크 함수
	function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt){
		$memailconfirm.on("keyup", function(){
			if (data != $memailconfirm.val()) { //
				emconfirmchk = false;
				$memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
				$("#emconfirmchk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"

				})
				//console.log("중복아이디");
			} else { // 아니면 중복아님
				emconfirmchk = true;
				$memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")

				$("#emconfirmchk").css({
					"color" : "#0D6EFD",
					"font-weight" : "bold",
					"font-size" : "10px"

				})
			}
		})
	}
	
	
	// 주소 찾기 script
    function daumPostcode() {
		$("#maddr").val("");
		$("#maddr2").val("");
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = extraAddr;
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("maddr2").value = extraAddr;
                
                } else {
                    document.getElementById("maddr2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#maddr").val(addr);
                $("#maddr2").val(extraAddr)
                
                $("#mmaddr").remove();
                $("#mmaddr2").remove();
                
                
                //$("#caddr").val(addr);
                //document.getElementById("caddr").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("maddr2").focus();
            }
        }).open();
    }
	
	
	// formSubmit 함수
	function formSubmit(){
		var maleCheck = $("#male").is(':checked');
		var femaleCheck = $("#female").is(':checked');
		
		//console.log("idchk : "+idchk)
		//console.log("pwdchk : "+pwdchk)
		//console.log("pwdconfrimchk : "+pwdconfrimchk)		
		//console.log("namechk : "+namechk)
		
		//console.log("birchk : "+birchk)
		//console.log("emchk : "+emchk)
		//console.log("emconfirmchk : "+emconfirmchk)
		//console.log("phchk : "+phchk)
		//console.log("select : "+$("#select").val())
		
		if(!idchk){
			frm.mid.focus();
			return false;
		}else if(!pwdchk){
			frm.mpwd.focus();
			return false;
		}else if(!pwdconfrimchk){
			frm.pwdconfrim.focus();
			return false;
		}else if(!namechk){
			frm.mname.focus();
			return false;
		}else if(!birchk){
			frm.mbrith.focus();
			return false;
		}else if(!emchk){
			frm.memail.focus();
			return false;
		}else if(!emconfirmchk){
			frm.memailconfirm.focus();
			return false;
		}else if(!phchk){
			frm.mphone.focus();
			return false;
		}else if(!$("#chek").is(":checked")){
			frm.chek.focus();
			return false;
		}else if($("#select").val() == "none"){
			frm.tno.focus();
			return false;
		}else if($("#maddr").val()==""){
			frm.maddr.focus();
			return false;
		}else if(maleCheck == false && femaleCheck == false){
			frm.mgender.focus();
			return false;
		}else{
			return true;
		}
		
	}
</script>
<style>
/* .custom-btn {
	height: 40px;
	color: white;
	text-align: center;
	border-radius: 5px;
	padding: 10px 25px;
	/* background: transparent; */
	transition: all 0.3s ease;
	cursor: pointer;
	position: relative;
	display: inline-block;
	box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, .5), 7px 7px 20px
		0px rgba(0, 0, 0, .1), 4px 4px 5px 0px rgba(0, 0, 0, .1);
	outline: none;
	border-radius: 5px;
} */

/* .btn-2 {
	background: rgb(96, 9, 240);
	background: linear-gradient(0deg, rgba(96, 9, 240, 1) 0%,
		rgba(129, 5, 240, 1) 100%);
	border: none;
	margin-left: 15px;
    margin-bottom: 10px;
} */

.btn-2:before {
	height: 0%;
	width: 2px;
}

.btn-2:hover {
	box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5), -4px -4px 6px 0
		rgba(116, 125, 136, .5), inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
		inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
}

span {
	/* font-size: 20px; */
}

.container{
	max-width: 910px;
}

.col-md-8-custom {
    /* -webkit-box-flex: 0;
    -ms-flex: 0 0 66.66667%;
    flex: 0 0 66.66667%; */
    max-width: 80%; 
    padding: 30px 0 30px 145px;
}

#findAddr, #checkEmail{
	/* margin-left : 115px; */
	float: left;
}

.check{
  		/* height: 50px; */
    	line-height: 35px;
  	}
  	.mb-4>img {
  		width: 200px;
  		margin: 10px 0 10px 75px;
  	}
  	.member_register>form>div>label {
  		padding: 14px;
  	}
  	.member_register>form>div>input {
  		font-size: 12px;
  		height: 50px;
  	}
  	.addr_input>input, .email_input>input {
  		width: 260px;
  	}
  	.addr_input, .email_input {
  		float: left;
  	}
  	.addr_detail, .check_input {
  		clear: both;
  	}
  	.addr_btn, #checkEmail {
  		margin: 4px 0 0 5px;
  	}
  	.btn-group {
  		width: 206px;
  		margin: 0 0 0 7px;
  	}
  	#form-select {
  		border: 1px solid #ced4da;
  		border-radius: .375rem;
  	}
  	#form-select:focus {
  		border: 1px solid #0D6EFD;
  	}
  	.bg-white {
  		padding-bottom: 30px;
  	}

</style>
<title>회원가입</title>
</head>
<body>
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
	<div class="content bg-light">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 contents bg-white">
					<div class="col-md-8-custom member_register">
						<div class="mb-4">
							<!-- <h3>회원가입</h3> -->
							<img src="../images/logo.png" alt="" />
						</div>
						<form action="/goodjob/registerOkUser" name="frm" method="post" onsubmit="return formSubmit()">
							<!-- <span>아이디</span> -->
							<div class="form-group first">
								<label for="mid" id="id">아이디를 입력해주세요 (5~15자 입력)</label> 
								<input type="text" class="form-control" name="mid" id="mid">
							</div>

							<!-- <span>비밀번호</span> -->
							<div class="form-group last mb-4">
								<label for="mpwd" id="pwdnew">비밀번호를 입력해주세요 (영문&숫자를 포함한
									8~20자)</label> 
								<input type="password" class="form-control" name="mpwd" id="mpwd">
							</div>

							<!-- <span>비밀번호 확인</span> -->
							<div class="form-group last mb-4">
								<label for="pwdconfrim" id="pwdText">비밀번호 확인</label> <input
									type="password" class="form-control" id="pwdconfrim">
							</div>

							<!-- <span>이름</span> -->
							<div class="form-group last mb-4">
								<label for="mname" id="nameTxt">이름을 입력해주세요</label> <input
									type="text" class="form-control" name="mname" id="mname">
							</div>
							
							<!-- <span>주소</span> -->
							
							<div class="form-group last mb-4 addr_input">
								<label for="maddr" id="mmaddr">주소를 입력해주세요</label>
								<input type="text" class="form-control" name="maddr" id="maddr">
							</div>
							<input type="button" class="btn btn-outline-primary addr_btn" id="findAddr" onclick="daumPostcode()" value="주소 찾기"><br>
							
							<!-- <span>상세 주소</span> -->
							<div class="form-group last mb-4 addr_detail">
								<label for="maddr2" id="mmaddr2">상세 주소를 입력해주세요</label>
								<input type="text" class="form-control" name="maddr2" id="maddr2">
							</div>

							<span>성별을 선택해주세요</span>
							<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
							  <input type="radio" class="btn-check" name="mgender" id="male" autocomplete="off" value="남성">
							  <label class="btn btn-outline-primary check" for="male">남성</label>
							
							  <input type="radio" class="btn-check" name="mgender" id="female" autocomplete="off" value="여성">
							  <label class="btn btn-outline-primary check" for="female">여성</label>
						  	</div>
						  	
						  	<br />
							<br />
							<!-- <span>생년월일</span> -->
							<div class="form-group last mb-4">
								<label for="mbrith" id="briTxt">생년월일을 입력해주세요 (8자)</label> <input
									type="text" class="form-control" name="mbrith" id="mbrith">
							</div>

							<!-- <span>이메일 </span> -->
							<div class="form-group last mb-4 email_input">
								<label for="memail" id="mailTxt">이메일을 입력해주세요</label> 
								<input type="text" class="form-control" name="memail" id="memail">
							</div>
							<!-- <span>이메일 인증번호</span> -->
							<button class="btn btn-outline-primary" type="button" id="checkEmail">인증번호</button>


							<div class="form-group last mb-4 check_input">
								<label for="memailconfirm" id="memailconfirmTxt">인증번호를 입력해주세요</label> 
								<input type="text" class="form-control" id="memailconfirm">
							</div>

							<!-- <span>핸드폰 번호</span> -->
							<div class="form-group last mb-4">
								<label for="mphone" id="phoneTxt">핸드폰번호를 입력해주세요 (번호만 입력)</label>
								<input type="text" class="form-control" name="mphone"
									id="mphone">
							</div>

							<!-- <span>관심 분야</span> -->
							<div class="selectbox">
								<!-- <span for="select">관심분야 선택</span> --> 
								<select id="form-select" name="tno" style="width:355px; height: 50px;">
									<option selected value="none">관심분야를 선택해주세요</option>
									<option class="dropdown-item" value=1>서비스업</option>
									<option class="dropdown-item" value=2>제조·화학</option>
									<option class="dropdown-item" value=3>IT·웹·통신</option>
									<option class="dropdown-item" value=4>은행·금융업</option>
									<option class="dropdown-item" value=5>미디어·디자인</option>
									<option class="dropdown-item" value=6>교육업</option>
									<option class="dropdown-item" value=7>의료·제약·복지</option>
									<option class="dropdown-item" value=8>판매·유통</option>
									<option class="dropdown-item" value=9>건설업</option>
									<option class="dropdown-item" value=10>기관·협회</option>
								</select>
							</div>
							<br>
							<hr class="divider">
							<div>
          						<p> GoodJob 약관 동의(필수)  <input type="checkbox" class="checkbox" name="chek" id="chek" /></p>
          					    <p> 개인정보 이용 약관 동의(필수)  <input type="checkbox" class="checkbox" name="chek" id="chek" /></p>
          					
          					</div>
          					<hr class="divider">
							<br /> <input type="submit" value="가입하기"
								class="btn btn-block btn-primary">
						</form>
					</div>
				</div>

			</div>

		</div>
	</div>
	<!-- footer -->
	<jsp:include page="../main/footer.jsp" /> 
</body>
</html>