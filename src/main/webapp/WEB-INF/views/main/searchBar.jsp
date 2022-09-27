<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <meta name="_csrf" id="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" id="_csrf_header" content="${_csrf.headerName}"/> --%>
<title>searchBar</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	/* 검색바 */
	.searchBar {
		font-family: 'Noto Sans KR', sans-serif;
		width: 850px;
		hegiht: 57px;
		padding: 10px 20px 10px 20px;
		margin:0 auto; 
		background-color: white;
		border: 1px solid #E5E5E5;
		border-radius: 10px;
		margin-top: 120px;
		margin-bottom: 100px;
	}
	.inputText {
		float: left;
		padding-right: 10px;
		padding-top: 2px;
	}
	#keyword {
		border: none;
		height: 32px;
		width: 320px;
	}
	#keyword:focus {
		outline: none;
	}
	.areaBtn {
		float: left;
		border-left: 1px solid #ACB1C6;
		padding: 0 10px 0 15px;
	}
	.jobBtn {
		float: left;
		border-left: 1px solid #ACB1C6;
		padding: 0 10px 0 15px;
	}
	.searchBtn {
		float: left;
	}
	.searchBtn>button {
		padding: 2px 0 0 0;		
	}
	#downword {
		padding-right: 5px;
	}
	.searchBar button {
		background-color: white;
		border: 0px;
	}
	div.searchBtn > a > span > img {
		padding-top: 3px;	
	}
	.floatDelete {
		clear: both;
	}
	#areaChoice, #jobChoice {
		padding: 6px 0 0 8px;
		/* padding-left: 8px; */
		width: 150px;
		border: none;
	}
	#areaChoice:focus, #jobChoice:focus {
		outline: none;
	}
	#areaChoice:hover, #jobChoice:hover {
		color: #0D6EFD;
	}
	
	/* 지역선택 드롭다운 */
	.searchAreaBox {
		width: 850px;
		height: 255px;	
		padding-top: 10px;
	}
	.box_btn {
		float: left;
		width: 50%;		
	}
	.box_btn:hover {
		background-color: RGBA(239,245,255,0.5);
	}

	.box_btn>a {
		text-decoration: none;	
		color: #222222;
    	font-size: 15px;
    	line-height: 31px;
    	text-align: left;
	}
	.bigAreaContent	{
		float: left;
		width: 330px;
		height: 255px;
		border-right: 1px solid #ACB1C6;
	}
	.smallAreaContent {
		float: left;
		width: 498px;
		height: 255px;
		padding-left: 20px;	
		overflow-y: scroll;
	}
	/* 스크롤바 */
	.smallAreaContent::-webkit-scrollbar {
    	width: 6px;  /* 스크롤바의 너비 */
	}
	.smallAreaContent::-webkit-scrollbar-thumb {
    	height: 30%; /* 스크롤바의 길이 */
    	background: rgba(33, 122, 244, .1); /* 스크롤바의 색상 */
    	border-radius: 10px;
	}
	.smallAreaContent::-webkit-scrollbar-track {
    	background: white;  /*스크롤바 뒷 배경 색상*/
	}
	/* 스크롤바끝 */
		
	.smallList > div{
		float: left;
		width: 33%;	
	}
	.smallList > div:hover {
		background-color: RGBA(239,245,255,0.5);
	}
	
	.smallList > div > a {
		text-decoration: none;	
		color: #222222;
    	font-size: 15px;
    	line-height: 31px;
    	text-align: left;
	}
	
	/* 직무선택 드롭다운 */
	.jobAreaBox {
		width: 850px;
		height: 255px;	
		padding-top: 20px;
		padding-left: 20px;
	}
	.jobAreaBox > div {
		width: 30%;	
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	var jno;
	//var moveForm = $("#moveForm");
	
	// 지역선택, 직무선택 클릭
	$(function(){
		// 기본 : 지역선택, 직무선택 감춤
		$(".searchAreaBox").hide();	
		$(".jobAreaBox").hide();	
	
		// smallno, jno 기본값으로 0 설정
		$('input[name="smallno"]').attr("value",0);
		$('input[name="jno"]').attr("value",0);
		
		// 클릭하면 드롭다운 나타나기
		$(".areaBtn").click(function(){
			if($(".searchAreaBox").hasClass("on")){
				$(".searchAreaBox").slideUp();
				$(".searchAreaBox").removeClass("on");
			}else{
				$(".searchAreaBox").addClass("on");
				$(".searchAreaBox").slideDown();
				$(".jobAreaBox").hide();
			}
		})
		
		$(".jobBtn").click(function(){
			if($(".jobAreaBox").hasClass("on")){
				$(".jobAreaBox").slideUp();
				$(".jobAreaBox").removeClass("on");
			}else{
				$(".jobAreaBox").addClass("on");
				$(".jobAreaBox").slideDown();
				$(".searchAreaBox").hide();
			}
		})
	})
	
	// 지역(시/도) 선택
	function locAjax(key){		
		$("#bigno").val(key);
		
		/* var header = $("meta[name='_csrf_header']").attr('content');
		var token = $("meta[name='_csrf']").attr('content'); */
		
		$.ajax({
			type: "POST",
			url: "/goodjob/locList",
			data: $('#hireForm').serialize(),
			dataType: "json",
			/* beforeSend: function(xhr){
		        xhr.setRequestHeader(header, token);
		    }, */
			success: function(data){
 				var s = "";
				
				data.forEach(function(d){
 					s += "<div><a href='javascript:void(0);' id="+ d.smallno +" onclick='getSamllname("+"this"+")'>"+ d.smallname +"</a></div>";	
				})
				
				$('.smallList').empty();
				$('.smallList').append(s);
				
			},
			error:function(e){    
				console.log(e);
			}
		})
	}
	
	// 직무선택 text 가져오기
	$(function(){
		$(".jobAreaBox > div > a").click(function(e) {
			var i = $("a").index(this);
			//console.log(i);
			var job = $("a").eq(i).text();
			$('input[name=jobChoice]').attr('value', job);
			
			//let jno = $('.box_btn > button').val();
			//console.log(jno);
			//let jno = $('input[name="jno"]').val();
			//console.log(jno);
		})
		
	})
	
	// 지역선택 text 가져오기
	function getSamllname(smallno){
		var area = $(smallno).text();
		
		$('input[name=areaChoice]').attr('value', area);
		
		$('input[name="smallno"]').attr("value",$(smallno).attr("id"));
	}
	
	// 직무선택 value
	function getjno(jno){
		//console.log(jno);
		$('input[name="jno"]').val(jno);
	}
	
	// 검색 폼 보내기
	$(document).on("click", "#formBtn", function(e){
		let moveForm = $("#moveForm");
		
		//console.log("test");
		e.preventDefault();
		let keyword = $('input[name="keyword"]').val();
		moveForm.find('input[name="keyword"]').val(keyword);
		moveForm.find('input[name="amount"]').val(10);
		//moveForm.find('input[name="jno"]').val(jno);
		//moveForm.find('input[name="smallno"]').val(smallno);
		moveForm.find('input[name="pageNum"]').val(1);
		moveForm.attr("action", "/goodjob/hireList");
		moveForm.submit();
	})
	
		
</script>
</head>
<body>
<div class="container">
	<!-- 시군구 폼 -->
	<form name="hireForm" id="hireForm" method="post">
		<input type="hidden" name="bigno" id="bigno"/> 
	</form> 
		<div class="searchBar">
		<%-- <h5>키워드 : ${pageMaker.page.keyword}</h5>
		<h5>직무 : ${pageMaker.page.jno}</h5> --%>
		<%-- <h4>키워드 : ${pageMaker.page.keyword}</h4>
		<h4>jobno : ${pageMaker.page.jno}</h4>
		<h6>총 게시물 수 : ${pageMaker.total}</h6>
		<h6>처음 페이지 : ${pageMaker.startPage}</h6>
		<h6>끝 페이지 : ${pageMaker.endPage}</h6>
		<h6>현재 페이지 : ${pageMaker.page.pageNum}</h6>
		<h6>페이지 당 게시물 수 : ${pageMaker.page.amount}</h6> --%> 
		<%-- <h4>smallno : ${pageMaker.page.smallno}</h4> --%>
			<div class="inputText">
				<input type="text" name="keyword" id="keyword" value="${pageMaker.page.keyword}" placeholder="검색어를 입력해주세요."/>
			</div>
			<div class="areaBtn">
					<span><img src="images/MapPin.png" alt="" /></span>
					<input type="text" name="areaChoice" id="areaChoice" value="지역선택" readonly />
					
					<span id="downword"><img src="images/downword.png" alt="" /></span>
			</div>
			<div class="jobBtn">
					<span><img src="images/Vector.png" alt="" /></span>
					<input type="text" name="jobChoice" id="jobChoice" value="직무선택" readonly />
					<%-- <input type="hidden" name="jno" value="${pageMaker.page.jno}" />  --%>
					<span id="downword"><img src="images/downword.png" alt="" /></span>
			</div>
			<div class="searchBtn">
				<button id="formBtn">
					<img src="images/Union.png" alt="" />
				</button>
			</div>
			<div class="floatDelete"></div>
			<!-- 지역선택 결과 -->
	        <div class="searchAreaBox">
	        	<!-- 대분류 선택 -->       	
				<div class="bigAreaContent">
					<div class="box_btn" onclick="locAjax('1')" id="jno"><a href="javascript:void(0);" ><span>서울</span></a></div>
	                <div class="box_btn" onclick="locAjax('2')"><a href="javascript:void(0);"><span>경기</span></a></div>
	                <div class="box_btn" onclick="locAjax('8')"><a href="javascript:void(0);"><span>인천</span></a></div>
	                <div class="box_btn" onclick="locAjax('6')"><a href="javascript:void(0);"><span>부산</span></a></div>
	                <div class="box_btn" onclick="locAjax('4')"><a href="javascript:void(0);"><span>대구</span></a></div>
	                <div class="box_btn" onclick="locAjax('3')"><a href="javascript:void(0);"><span>광주</span></a></div>
	                <div class="box_btn" onclick="locAjax('5')"><a href="javascript:void(0);"><span>대전</span></a></div>
	                <div class="box_btn" onclick="locAjax('7')"><a href="javascript:void(0);"><span>울산</span></a></div>
	                <div class="box_btn" onclick="locAjax('10')"><a href="javascript:void(0);"><span>경남</span></a></div>
	                <div class="box_btn" onclick="locAjax('11')"><a href="javascript:void(0);"><span>경북</span></a></div>
	                <div class="box_btn" onclick="locAjax('12')"><a href="javascript:void(0);"><span>전남</span></a></div>
	                <div class="box_btn" onclick="locAjax('13')"><a href="javascript:void(0);"><span>전북</span></a></div>
	                <div class="box_btn" onclick="locAjax('15')"><a href="javascript:void(0);"><span>충남</span></a></div>
	                <div class="box_btn" onclick="locAjax('14')"><a href="javascript:void(0);"><span>충북</span></a></div>
	                <div class="box_btn" onclick="locAjax('9')"><a href="javascript:void(0);"><span>강원</span></a></div>
	                <div class="box_btn" onclick="locAjax('16')"><a href="javascript:void(0);"><span>제주</span></a></div>
				</div>
				<!-- 중분류 선택 -->
				<div class="smallAreaContent">
					<div class="smallList">
						<!-- 중분류 div 나오는 부분 -->
					</div>
				</div>	
				<div class="floatDelete"></div>		
	        </div>
	        <!-- 직무 선택 -->
	        <div class="jobAreaBox">
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(2)">상품기획·MD</a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(3)"><span>기획·전략</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(4)"><span>운전·운송·배송</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(5)"><span>서비스</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(6)"><span>생산</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(7)"><span>건설·건축</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(8)"><span>의료</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(9)"><span>연구</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(10)"><span>교육</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(11)"><span>미디어·문화·스포츠	</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(12)"><span>금융·보험</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(13)"><span>공공·복지</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(14)"><span>마케팅·홍보·조사</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(15)"><span>회계·세무·재무</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(16)"><span>인사·노무·HRD</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(17)"><span>총무·법무·사무</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(18)"><span>IT개발·데이터</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(19)"><span>디자인</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(20)"><span>영업·판매·무역</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(21)"><span>고객상담·TM</span></a></div>
	        	<div class="box_btn"><a href="javascript:void(0);" onclick="getjno(22)"><span>구매·자재·물류</span></a></div> 
	        	<%-- <c:forEach items="${Jlist}" var="dto">
	        		<div class="box_btn"><a href="javascript:void(0);"><span>${dto.jtitle}</span></a>
		        		<input type="hidden" name="jno" value="${dto.jno}" />        		
	        		</div>
	        	</c:forEach> --%>
	        </div>
		</div>	
		
		<form id="moveForm" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.page.pageNum}" />
			<input type="hidden" name="amount" value="${pageMaker.page.amount}" />
			<input type="hidden" name="keyword" value="${pageMaker.page.keyword}" />
			<input type="hidden" name="smallno" value="${pageMaker.page.smallno}" /> 
			<input type="hidden" name="jno" value="${pageMaker.page.jno}" />  
		</form>
</div>
</body>
</html>