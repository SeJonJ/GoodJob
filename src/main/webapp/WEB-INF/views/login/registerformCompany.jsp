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

<link rel="stylesheet" href="/goodjob/login/css/owl.carousel.min.css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/goodjob/login/css/bootstrap.min.css">

<!-- Style -->
<link rel="stylesheet" href="/goodjob/login/css/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script>
	var cbinchk = false;
	var cnamechk = false;
	var ceochk = false;
	var caddrchk = false;
	var csetupchk = false;
	var csaleschk = false;
	var cpeoplechk = false;
	var chomepagechk = false;

	var idchk = false; // 아이디 검사
	var pwdchk = false; // 패스워드
	var pwdconfrimchk = false; // 패스워드 확인
	var cmanagerchk = false; // 담당자 확인
	var phchk = false; // 대표번호
	var emchk = false; // 이메일
	var emconfirmchk = false; // 이메일 인증
	

	$(function() {
		
		var $cbin = $("#cbin"); // 사업자 등록 번호
		var $cname = $("#cname"); // 회사명
		var $ceo = $("#ceo"); // 대표자명
		var $caddr = $("#caddr"); // 주소
		var $csetup = $("#csetup"); // 설립일
		var $csales = $("#csales"); // 매출액
		var $cpeople = $("#cpeople"); // 사원수
		
		var $cid = $("#cid"); // 아이디
		var $id = $("#id"); 
		var $cpwd = $("#cpwd"); // 패스워드
		var $cpwdconfrim = $("#cpwdconfrim"); // 패스워드 확인
		var $cmanager = $("#cmanager"); // 담당자명
		var $cemail = $("#cemail"); // 이메일
		var $cphone = $("#cphone"); // 대표번호
		
		var $checkEmail = $("#checkEmail"); // 인증번호 발송 버튼
		var $cemailconfirm = $("#cemailconfirm"); // 인증번호 확인input
		var $emailconfirmTxt = $("#emailconfirmTxt"); // 인증번호 확인 txt
		
		//console.dir("mid : "+$mid);
		
		// 사업자 등록번호 검사 
		$cbin.on("keyup", function() {
			var regExp = /^[0-9]{10}$/;
			//console.log("email : "+$memail.val());
			if (!regExp.test($cbin.val()) || $cbin.val() == "") {
				//console.log("형식 미확인");

				$("#ccbin").html("<span id='binchk'>- 를 제외한 10자리 숫자만 입력해주세요</span>")
				$("#binchk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				cbinchk = false;
			} else {
				//console.log("형식 확인");
				$("#ccbin").html("<span id='binchk'>사업자 등록번호 확인 완료</span>")
				$("#binchk").css({
					"color" : "blue",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				cbinchk = true;
			
			}
		})
		
		// 매출액 검사
		$csales.on("keyup", function() {
			var regExp = /^[0-9]{1,10}$/;
			//console.log("email : "+$memail.val());
			if (!regExp.test($csales.val())) {
				console.log("형식 미확인");
				$("#ccsales").show();
				$("#ccsales").html("<span id='ccsaleschk'>숫자(원)만 입력해주세요</span>")
				$("#ccsaleschk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				csaleschk = false;
			} else {
				//console.log("형식 확인");
				$("#ccsales").hide();
				
				csaleschk = true;

			}
		})
		
		
		// 사원수 검사
		$cpeople.on("keyup", function() {
			var regExp = /^[0-9]{1,7}$/;
			//console.log("email : "+$memail.val());
			if (!regExp.test($cpeople.val())) {
				//console.log("형식 미확인");

				$("#ccpeople").show();
				$("#ccpeople").html("<span id='ccpeoplechk'>숫자(명)만 입력해주세요</span>")
				$("#ccpeoplechk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				cpeoplechk = false;
			} else {
				//console.log("형식 확인");
				$("#ccpeople").hide();
				
				cpeoplechk = true;


			}
		})
		
		// 기업명 검사
		$cname.on("keyup", function(){
			
			var regExp = /\s/g;
			if(regExp.test($cname.val()) || $cname.val()==""){
				cnamechk = false;
				$("#cnamecheck").show();
				$("#ccname").html("<span id='cnamecheck'>기업명은 필수입니다.</span>");
				$("#cnamecheck").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			}else{
				cnamechk = true;
				$("#cnamecheck").hide();
			}
		})
		
		// 대표자명 검사
		$ceo.on("keyup", function(){
			//console.log("ceo : "+$ceo.val())
			var regExp = /\s/g;
			if(regExp.test($ceo.val()) || $ceo.val()==""){
				ceochk = false;
				$("#ceocheck").show();
				$("#cceo").html("<span id='ceocheck'>대표자명은 필수입니다.</span>");
				$("#ceocheck").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			}else{
				ceochk = true;
				$("#ceocheck").hide();
			}
		})
		
		
		// 설립일 정규식 검사
		$csetup.on("keyup",function() {
							var regExp = /([0-9]{4}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))$/;
							//console.log("email : "+$memail.val());
							if (!regExp.test($csetup.val())) {
								//console.log("형식 미확인");
								csetupchk = false;
								$("#ccsetchk").show();
								$("#ccsetup")
										.html(
												"<span id='csetupchk'>설립일 형식이 맞지않습니다</span>")
								$("#csetupchk").css({
									"color" : "#FA3E3E",
									"font-weight" : "bold",
									"font-size" : "10px"
								})
							} else {
								csetupchk = true;
								$("#csetupchk").hide();
								//console.log("형식 확인");
								

			}
		})
		
		// 아이디 중복 체크
		$cid.on("keyup", function() { // 키보드 눌렀을 때 실행
			// 아이디 정규식
			var regExp = /^[a-z]+[a-z0-9]{5,15}$/g;
			if (!regExp.test($cid.val())) { // id 가 공백인 경우 체크
				idchk = false;
				$id.html("<span id='check'>사용할 수 없는 아이디입니다</span>");
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
						"type" : "com",
						"id" : $cid.val()
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
								"font-size" : "15px"

							})
							//console.log("중복아닌 아이디");
						}
					}
				})
			}
		});

		// 비밀번호 정규식
		$cpwd.on("keyup", function() {
			var regExp = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
			//console.log("email : "+$memail.val());
			if (!regExp.test($cpwd.val())) {
				//console.log("형식 미확인");

				$("#pwdnew").html("<span id='chkpwd'>패스워드 형식이 맞지 않습니다</span>")
				$("#chkpwd").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				pwdchk = false;
			} else {
				//console.log("형식 확인");
				$("#pwdnew").html("<span id='chkpwd'>패스워드 형식을 확인했습니다</span>")
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
		$cpwdconfrim.on("keyup", function() {
			if ($cpwdconfrim.val() != $cpwd.val()) {
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

		// 담당자명 확인
		$cmanager.on("keyup", function() {
			if ($cmanager.val() == "") { // id 가 공백인 경우 체크
				cmanagerchk  = false;
				$("#cmanagerTxt").html("<span id='nmchk'>이름은 필수입니다</span>");
				$("#nmchk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
			} else {
				cmanagerchk  = true;
				$("#nmchk").hide();
			}
		})
		
		// 핸드폰 번호
		$cphone.on("keyup", function() {
			var regExp = /^\d{2,3}\d{4}\d{4}$/;
			//console.log("email : "+$memail.val());
			if (!regExp.test($cphone.val())) {
				//console.log("형식 미확인");
				$("#phoneTxt").show();

				$("#phoneTxt").html(
						"<span id='chkphone'>번호 형식이 맞지 않습니다</span>")
				$("#chkphone").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"
				})
				phchk = false;
			} else {
				//console.log("형식 확인");
				$("#phoneTxt").hide();
				phchk = true;
			}
		})



		// 이메일 정규식 검사
		$cemail.on("keyup",function() {
							var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
							//console.log("email : "+$memail.val());
							if (!regExp.test($cemail.val())) {
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
					"email" : $cemail.val()
				},
				success : function(data){
					chkEmailConfirm(data, $cemailconfirm, $emailconfirmTxt);
				}
			})
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
	function chkEmailConfirm(data, $cemailconfirm, $emailconfirmTxt){
		$cemailconfirm.on("keyup", function(){
			if (data != $cemailconfirm.val()) { //
				emconfirmchk = false;
				$emailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
				$("#emconfirmchk").css({
					"color" : "#FA3E3E",
					"font-weight" : "bold",
					"font-size" : "10px"

				})
				//console.log("중복아이디");
			} else { // 아니면 중복아님
				emconfirmchk = true;
				$emailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")

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
		$("#caddr").val("");
		$("#caddr2").val("");
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
                    document.getElementById("caddr2").value = extraAddr;
                
                } else {
                    document.getElementById("caddr2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#caddr").val(addr);
                $("#caddr2").val(extraAddr)
                
                $("#ccaddr").remove();
                $("#ccaddr2").remove();
                
                
                //$("#caddr").val(addr);
                //document.getElementById("caddr").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("caddr2").focus();
            }
        }).open();
    }
	
	// formSubmit 함수
	function formSubmit(){
		
		/* console.log("cbin : "+cbinchk)
		console.log("cnamechk : "+cnamechk)
		console.log("ceochk : "+ceochk)
		console.log("caddrchk : "+caddrchk)
		console.log("csetupchk : "+csetupchk)
		console.log("csaleschk : "+csaleschk)
		console.log("cpeoplechk : "+cpeoplechk)
		console.log("chomepagechk : "+chomepagechk)
		
		console.log("idchk : "+idchk)
		console.log("pwdchk : "+pwdchk)
		console.log("pwdconfrimchk : "+pwdconfrimchk)
		console.log("cmanagerchk : "+cmanagerchk)
		console.log("phchk : "+phchk)
		console.log("emchk : "+emchk)
		console.log("emconfirmchk : "+emconfirmchk) */
		
		
		if(!cbinchk){
			frm.cbin.focus();
			return false;
		}else if(!cnamechk){
			frm.cname.focus();
			return false;
		}else if(!ceochk){
			frm.ceo.focus();
			return false;
		}else if($("#caddr").val()==""){
			frm.caddr.focus();
			return false;
		}else if(!csetupchk){
			frm.csetup.focus();
			return false;
		}else if(!csaleschk){
			frm.csales.focus();
			return false;
		}else if(!cpeoplechk){
			frm.cpeople.focus();
			return false;
		}else if(!idchk){
			frm.cid.focus();
			return false;
		}else if(!pwdchk){
			frm.cpwd.focus();
			return false;
		}else if(!pwdconfrimchk){
			frm.cpwdconfrim.focus();
			return false;
		}else if(!cmanagerchk){
			frm.cmanager.focus();
			return false;
		}else if(!phchk){
			frm.cphone.focus();
			return false;
		}else if(!emchk){
			frm.cemail.focus();
			return false;
		}else if(!emconfirmchk){
			frm.cemailconfirm.focus();
			return false;
		}else if(!$("#chek").is(":checked")){
			console.log("여기")
			return false;
		}else if($("#select").val() == "none"){
			frm.tno.focus();
			return false;
		}else{
			return true;
		}
	
	}
</script>
<style>
/* .custom-btn {
	width: 170px;
	height: 40px;
	color: white;
	text-align: center;
	border-radius: 5px;
	padding: 10px 25px;
	font-family: 'Lato', sans-serif;
	background: transparent;
	cursor: pointer;
	transition: all 0.3s ease;
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
	font-size: 20px;
}

.container{
	max-width: 910px;
}

.col-md-8-custom {
    -webkit-box-flex: 0;
    -ms-flex: 0 0 66.66667%;
    flex: 0 0 66.66667%;
    max-width: 80%;
}

.btn-2#findAddr{
	margin-left: 75px;
}

.bg-white {
	padding: 30px 0 40px 150px;
}
.mb-4>img {
  	width: 200px;
  	margin: 10px 0 10px 75px;
}
.company_register>form>div>input {
	font-size: 12px;
  	height: 50px;
}
.company_register>form>div>label {
  		padding: 14px;
  	}
.mb-4>P {
	color: #222;
    font-size: 20px;
    line-height: 24px;
    font-weight: 600;
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
#findAddr, #checkEmail {
  	margin: 4px 0 0 5px;
}
#form-select {
  	border: 1px solid #ced4da;
  	border-radius: .375rem;
}
#form-select:focus {
  	border: 1px solid #0D6EFD;
}
.drivder {
  	width: 354px;
}
.selectbox {
	margin-bottom: 30px;
}
.submit_btn {
	width: 355px;
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
				<div class="col-md-6 contents bg-white" style="padding-left: 150px;">
					<div class="col-md-8-custom company_register">
						
						<form action="/goodjob/registerOkCom" name="frm" method="post" onsubmit="return formSubmit()">
							<div class="mb-4">
								<!-- <h3>기업 인증</h3> -->
								<img src="../images/logo.png" alt="" />
							</div>
							<!-- <span>사업자 등록 번호</span> -->
							<div class="form-group last mb-4">
								<label for="cbin" id="ccbin">사업자 등록 번호를 입력해주세요(- 제외)</label>
								<input type="text" class="form-control" name="cbin" id="cbin">
							</div>
							<hr class="drivder">
							<div class="mb-4">
								<P>기업 정보</P>
							</div>
							<!-- <span>기업 명</span> -->
							<div class="form-group last mb-4">
								<label for="cname" id="ccname">기업명을 입력해주세요</label>
								<input type="text" class="form-control" name="cname" id="cname">
							</div>
							<!-- <span>대표자 명</span> -->
							<div class="form-group last mb-4">
								<label for="ceo" id="cceo">대표자명을 입력해주세요</label>
								<input type="text" class="form-control" name="ceo" id="ceo">
							</div>
							
							<div class="form-group last mb-4 addr_input">
								<label for="caddr" id="ccaddr">기업 주소를 입력해주세요</label>
								<input type="text" class="form-control" name="caddr" id="caddr">
							</div>
							<!-- <span>기업 주소</span> -->
							<input type="button" class="btn btn-outline-primary addr_btn" id="findAddr" onclick="daumPostcode()" value="주소 찾기"><br>
							
							<!-- <span>상세 주소</span> -->
							<div class="form-group last mb-4 addr_detail">
								<label for="caddr2" id="ccaddr2">상세 주소를 입력해주세요</label>
								<input type="text" class="form-control" name="caddr2" id="caddr2">
							</div>
							<!-- <span>설립일</span> -->
							<div class="form-group last mb-4">
								<label for="csetup" id="ccsetup">설립일을 입력해주세요(8자)</label>
								<input type="text" class="form-control" name="csetup" id="csetup">
							</div>
							<!-- <span>매출액</span> -->
							<div class="form-group last mb-4">
								<label for="csales" id="ccsales">매출액을 입력해주세요</label>
								<input type="text" class="form-control" name="csales" id="csales">
							</div>
							<!-- <span>사원수</span> -->
							<div class="form-group last mb-4">
								<label for="cpeople" id="ccpeople">사원수를 입력해주세요</label>
								<input type="text" class="form-control" name="cpeople" id="cpeople">
							</div>
							<!-- <span>홈페이지 주소</span> -->
							<div class="form-group last mb-4">
								<label for="chomepage" id="cchomepage">홈페이지 주소를 입력해주세요(http:// 제외)</label>
								<input type="text" class="form-control" name="chomepage" id="chomepage">
							</div>
							<!-- <span>회사 업종</span> -->
							<div class="selectbox">
								<!-- <label for="select">관심분야 선택</label>  -->
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
							<hr class="drivder">

							<div class="mb-4">
								<P>채용담당자 정보</P>
							</div>
							<!-- <span>아이디</span> -->
							<div class="form-group last mb-4">
								<label for="cid" id="id">아이디를 입력해주세요 (5~15자 입력)</label> <input
									type="text" class="form-control" name="cid" id="cid">
							</div>

							<!-- <span>비밀번호</span> -->
							<div class="form-group last mb-4">
								<label for="cpwd" id="pwdnew">비밀번호를 입력해주세요 (영문&숫자를 포함한
									8~20자)</label> <input type="password" class="form-control"
									name="cpwd" id="cpwd">
							</div>

							<!-- <span>비밀번호 확인</span> -->
							<div class="form-group last mb-4">
								<label for="cpwdconfrim" id="pwdText">비밀번호 확인</label> <input
									type="password" class="form-control" id="cpwdconfrim">
							</div>

							<!-- <span>담당자명</span> -->
							<div class="form-group last mb-4">
								<label for="cmanager" id="cmanagerTxt">담당자명을 입력해주세요</label> <input
									type="text" class="form-control" name="cmanager" id="cmanager">
							</div>

							<!-- <span>전화 번호</span> -->
							<div class="form-group last mb-4">
								<label for="cphone" id="phoneTxt">대표 번호를 입력해주세요 (번호만 입력)</label>
								<input type="text" class="form-control" name="cphone"
									id="cphone">
							</div>

							<!-- <span>이메일 </span> -->
							<div class="form-group last mb-4 email_input">
								<label for="cemail" id="mailTxt">이메일을 입력해주세요</label> <input
									type="text" class="form-control" name="cemail" id="cemail">
							</div>

							<!-- <span>이메일 인증번호</span> -->
							<button class="btn btn-outline-primary" type="button" id="checkEmail">인증번호</button>

							<div class="form-group last mb-4 check_input">
								<label for="cemailconfirm" id="emailconfirmTxt">인증번호를 입력해주세요</label> <input
									type="text" class="form-control" id="cemailconfirm">
							</div>

							<br>
							<hr class="drivder">
							<div class="checkbox">
          						<p> GoodJob 약관에 동의합니다 <input type="checkbox" name="chek" id="chek" /></p>
          					</div>
							<br /> 
							<input type="submit" value="가입하기" class="btn btn-block btn-primary submit_btn" style="width: 356px;">
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