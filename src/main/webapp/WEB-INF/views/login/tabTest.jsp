<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>


<script>
	$(function() {
		var $mnameID = $("#mnameID");
		var $phoneID = $("#mphoneID");
		var $idText = $("#idText");
		var $findOk = new bootstrap.Modal(document.getElementById('findOk'));
		var $findNone = new bootstrap.Modal(document.getElementById('findNone'));

		$("#findId").click(function() {

			$.ajax({
				type : "POST",
				url : "/goodjob//findMemberId",
				data : {
					"mname" : $mnameID.val(),
					"mphone" : $phoneID.val()
				},
				success : function(data) {
					if (data == "") {
						//console.log("없음!")
						$findNone.show();
					} else {
						//console.log("data :"+data)
						$idText.text("회원님의 아이디 : " + data)
						$idText.css({
							"color" : "blue",
							"font-weight" : "bold",
							"font-size" : "15px"
						})
						$findOk.show();
					}
				}
			})
		})
	})
</script>
<style>
#idText {
	color: red;
	font-weight: bold;
}
</style>
<title>아이디/비밀번호 찾기</title>
</head>
<body>
	<h1>아이디/비밀번호 찾기</h1>
	
	<div class="content">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 contents">
					<div class="col-md-8-custom">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>