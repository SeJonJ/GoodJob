<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchBar</title>
<style>
@import 
url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	.h1{
		margin-top: 100px;
		font-size: 28px;
    	font-weight: 700;
    	line-height: 19px;
    	color: #333;
	}
	.searchBar {
		font-family: 'Noto Sans KR', sans-serif;
		width: 850px;
		hegiht: 57px;
		padding: 10px;
		padding-left: 20px;
		margin:0 auto; 
		background-color: white;
		border: 1px solid #E5E5E5;
		border-radius: 10px;
		margin-top: 20px;
		margin-bottom: 100px;
	}
	.inputText>input:focus {
		outline: none;
	}
	.inputText>input {
		border: none;
		width: 760px;
	}
	.inputText {
		float: left;
		width: 760px;
	}
	#inputKeyword {
		border: none;
		height: 32px;
		width: 750px;
	}
	.searchBtn {
		position: relative;
		left: 10px;
		float: left;
	}
	.searchBar button {
		background-color: white;
		border: 0px;
	}
	div.searchBtn > img {
		padding-top: 3px;	
	}
	.test {
		clear: both;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	//검색기능
	$(document).on("click", "#btnSearch", function(e){
		let moveForm = $("#moveForm");
		
		//console.log("test");ok
		 e.preventDefault();
		
		let cname = $('input[name="cname"]').val();
		//console.log(cname);
		
		moveForm.find('input[name="search"]').val(cname);
		moveForm.find('input[name="amount"]').val(10);
		moveForm.find('input[name="pageNum"]').val(1);
		moveForm.attr("action", "/goodjob/reviewList");
		moveForm.submit();  
	})


</script>
</head>
<body>
<div class="container">
<div class="h1">리뷰검색</div>
	<div class="searchBar">
		<div class="inputText">
			<input type="text" name="cname" id="cname" value="${pageMaker.page.search}" placeholder="기업명을 입력해주세요."/>
		</div>
		<div class="searchBtn">	
			<img src="/goodjob/images/Union.png" alt="search" id="btnSearch" />
		</div>
		<div class="test"></div>
	</div>
	
	<form id="moveForm" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.page.pageNum}" />
			<input type="hidden" name="amount" value="${pageMaker.page.amount}" />
			<input type="hidden" name="search" value="${pageMaker.page.search}" />
	</form>
</div>
</body>
</html>