<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>main</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<script src="/js/jquery-3.6.0.min.js"></script>

<style>
@import 
url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	.container {
		padding: 4px;
		font-family: 'Noto Sans KR', sans-serif;
		margin:0 auto; 
	}
	body {
		word-break: keep-all;
	}
	#searchBg {
		width: 100%;
		height: 633px;
	}
	.content {
		padding-bottom: 100px;
		margin-top: 70px;
	}
	/* 최근 등록한 공고 */
	.title1 {
		font-weight: 600;
		font-size: 30px;
		line-height: 39px;
		color: #313131;
	}
	.recentHire {
		position: relative;
		left: 42px; 
	}
	.hireBox {
		float: left;
		border: 1px solid #D8D8D8;
		margin-top: 40px;
		margin-right: 50px;
		width: 270px;
		height: 180px;
		padding: 15px;
	}
	.hireBox:hover {
		border: 2px solid #0D6EFD;
	}
	.textBax {
		height: 120px;
	}
	.textBax>a {
		text-decoration: none;
	}
	.floatDelete {
		clear: both;
	}
	#dday {
		color: #A7A7A7;
		padding-left: 160px;
	}
	#cname {
		font-weight: 600;
		font-size: 17px;
		line-height: 29px;
		color: #848484;		
		padding-bottom: 10px;
	}
	#htitle{
		font-weight: 500;
		font-size: 18px;
		line-height: 29px;
		color: #000000;
	}
	.bottomBox>button {
		padding-left: 0;
		background-color: white;
		border: 0;
	}
	/* 평점이 높은 공고 */
	.title2 {
		font-weight: 600;
		font-size: 30px;
		line-height: 39px;
		color: #313131;
	}
	.gradeHire {
		position: relative;
		left: 42px; 
		padding-top: 50px;
		padding-bottom: 50px;
	}
	.hireBox2 {
		float: left;
		border: 1px solid #D8D8D8;
		margin-top: 40px;
		margin-right: 50px;
		width: 270px;
		height: 180px;
		padding: 15px;
	}
	.hireBox2:hover {
		border: 2px solid #0D6EFD;
	}
	.textBax2 {
		height: 120px;
	}
	.textBax2>a {
		text-decoration: none;
	}
	.floatDelete {
		clear: both;
	}
	#dday {
		color: #A7A7A7;
		padding-left: 115px;
	}
	#cname {
		font-weight: 600;
		font-size: 17px;
		line-height: 29px;
		color: #848484;		
		padding-bottom: 10px;
	}
	#htitle{
		font-weight: 500;
		font-size: 18px;
		line-height: 29px;
		color: #000000;
	}
	.bottomBox2>button {
		padding-left: 0;
		background-color: white;
		border: 0;
	}		
	/* 검색바 */
	/* .searchBar {
		width: 850px;
		hegiht: 57px;
		padding: 10px;
		padding-left: 20px;
		margin:0 auto; 
		position: relative;
		bottom: 340px; 
		background-color: white;
		border: 1px solid #E5E5E5;
		border-radius: 10px;
		font-family: 'Noto Sans KR', sans-serif;
	}
	.inputText {
		float: left;
	}
	#inputKeyword {
		border: none;
		height: 32px;
		width: 420px;
	}
	#inputKeyword:focus {
		outline: none;
	}
	.areaBtn {
		float: left;
		border-left: 1px solid #ACB1C6;
		padding-left: 10px;
		padding-right: 20px;
	}
	.jobBtn {
		float: left;
		border-left: 1px solid #ACB1C6;
		padding-left: 15px;
		padding-right: 20px;
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
	div.searchBtn > a > span > img {
		padding-top: 3px;	
	} */
	.test {
		clear: both;
	}
</style>
<script>


	function LikeUpdateR(hno, index){
		/* console.log(hno)
		console.log(rrList)
		console.log(index) */
		var $like = $("#likeR_"+index);
		var type;
		if($like.val() == 1){
			type = "del";
			$like.val(0);
			$("#likeImgR_"+index).attr("src", "images/bookmark.png");
		}else{
			type = "ins";
			$like.val(1);
			$("#likeImgR_"+index).attr("src", "images/bookmark1.png");
		}
		
		
		// xhr 객체 선언
		var xhr = new XMLHttpRequest();
		// 보내는 방식, 보내는 url, 비동기화 여부
		xhr.open("POST", "/goodjob/likeUpdate?"+"type="+type+"&hno="+hno, true);
		
		
		xhr.send();
		
		
		
		/*  
		$.ajax({
			url : "/main/likeUpdate",
			type : "post",
			data : {
				"type" : type,
				"hNum" : hno,
			},
			success : function(){
				
			}
		})
		 */
	}
	
	function LikeUpdateB(hno, index){
		var $like = $("#likeB_"+index);
		var type;
		if($like.val() == 1){
			type = "del";
			$like.val(0);
			$("#likeImgB_"+index).attr("src", "images/bookmark.png");
		}else{
			type = "ins";
			$like.val(1);
			$("#likeImgB_"+index).attr("src", "images/bookmark1.png");
		}
		
		
		// xhr 객체 선언
		var xhr = new XMLHttpRequest();
		// 보내는 방식, 보내는 url, 비동기화 여부
		xhr.open("POST", "/goodjob/main/likeUpdate?"+"type="+type+"&hno="+hno, true);
		
		
		xhr.send();
	}
	

</script>
</head>
<body>

	<h1>${model.type}</h1>
	<!-- header include -->
	<sec:authorize access="isAnonymous()">		
		<jsp:include page="header.jsp" /> 	
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		<jsp:include page="headerMember.jsp" />
	</sec:authorize> 
	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<jsp:include page="headerCompany.jsp" />
	</sec:authorize> 
	
	<!-- 검색창 배경 -->
	<div class=".container-xl" style="position: relative;">
		<img src="images/background.png" alt="" id="searchBg"/>		
		<div class="test12" style="position: absolute; top: 50%; left: 50%;  transform: translate(-50%, -50%);">
			<jsp:include page="searchBar.jsp" />	
		</div>
	</div>
	
	<!-- 검색창 -->

	<!-- <div class="container">
		<div class="searchBar">
			<div class="inputText">
				<input type="text" name="" id="inputKeyword" placeholder="검색어를 입력해주세요."/>
			</div>
			<div class="areaBtn">
				<button>
					<span><img src="images/MapPin.png" alt="" /></span>
					<span>지역선택</span>&emsp;
					<span><img src="images/downword.png" alt="" /></span>
				</button>
			</div>
			<div class="jobBtn">
				<button>
					<span><img src="images/Vector.png" alt="" /></span>
					<span>&ensp;직무선택</span>&emsp;
					<span><img src="images/downword.png" alt="" /></span>
				</button>
			</div>
			<div class="searchBtn">
				<a href="hireList">
					<span><img src="images/Union.png" alt="" /></span>
				</a>
			</div>
			<div class="test"></div>
		</div>
	</div> -->
	<!-- content 영역 -->
	<div class="container content">
		<!-- 최근 등록한 공고 -->
		<div class="recentHire">
			<div class="title2">최근 등록한 공고</div>
			<div class="hireList">
				<c:forEach var="dto" items="${Rlist}" begin="0" end="7" varStatus="status">
					<div class="hireBox">
						<div class="textBax">
							<a href="/goodjob/hire/hiredetail?hno=${dto.hno}">
								<span id="cname">${dto.cname}</span><br />
								<span id="htitle">${dto.htitle}</span><br />
							 </a>					
						</div>
						<div class="bottomBox">
							<c:choose>
								<c:when test="${!check}">
									<button type="button" class="btn_scrap" title="공고좋아요"
										data-bs-toggle="modal" data-bs-target="#ModalLike1">
									<img class="like" id="likeImg" src="images/bookmark.png" alt="좋아요" />
									</button>
									<%--로그인이 필요합니다 modal--%>
									<div class="modal fade" id="ModalLike1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel"></h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body" style="text-align: center; padding-top:30px; padding-bottom:30px;">
														<h5>로그인이 필요합니다</h5>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
													<a href="/goodjob/login/loginform"><button type="button" class="btn btn-primary">&nbsp로그인창으로 이동&nbsp</button></a>
												</div>
												</div>
											</div>
										</div>
									
								</c:when>
								
								<c:when test="${rrList[status.index] != null}">
									<button type="button" class="btn_scrap" title="공고좋아요" id="likeR_${status.index}"
										onclick="LikeUpdateR('${dto.hno}', '${status.index}')" value=1>
									<img class="like" id="likeImgR_${status.index}" src="images/bookmark1.png" alt="좋아요" />
									</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn_scrap" title="공고좋아요" id="likeR_${status.index}"
										onclick="LikeUpdateR('${dto.hno}', '${status.index}')" value=0>
									<img class="like" id="likeImgR_${status.index}" src="images/bookmark.png" alt="좋아요" />
									</button>
								</c:otherwise>
							</c:choose>
						<span id="dday">${dto.hdate}</span>	
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="floatDelete"></div>
		<!-- 평점이 높은 공고 -->
		<div class="gradeHire">
			<div class="title1">좋아요가 많은 공고</div>
			<div class="hireList2">
				<c:forEach var="dto" items="${Blist}" begin="0" end="7" varStatus="status">
					<div class="hireBox2">
						<div class="textBax2">
							<a href="/goodjob/hire/hiredetail?hno=${dto.hno}">
								<span id="cname">${dto.cname}</span><br />
								<span id="htitle">${dto.htitle}</span><br />
							 </a>					
						</div>
						<div class="bottomBox2">
							<c:choose>
								<c:when test="${!check}">
									<button type="button" class="btn_scrap" title="공고좋아요" data-bs-toggle="modal" data-bs-target="#ModalLike2">
									<img class="like" id="likeImg" src="images/bookmark.png" alt="좋아요" />
									</button>
									
									<%--로그인이 필요합니다 modal--%>
									<div class="modal fade" id="ModalLike2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel"></h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body" style="text-align: center; padding-top:30px; padding-bottom:30px;">
														<h5>로그인이 필요합니다</h5>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
													<a href="/goodjob/login/loginform"><button type="button" class="btn btn-primary">&nbsp로그인창으로 이동&nbsp</button></a>
												</div>
												</div>
											</div>
										</div>
									
								</c:when>
								
								<c:when test="${bbList[status.index] != null}">
									<button type="button" class="btn_scrap" title="공고좋아요" id="likeB_${status.index}"
										onclick="LikeUpdateB('${dto.hno}', '${status.index}')" value=1>
									<img class="like" id="likeImgB_${status.index}" src="images/bookmark1.png" alt="좋아요" />
									</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn_scrap" title="공고좋아요" id="likeB_${status.index}"
										onclick="LikeUpdateB('${dto.hno}', '${status.index}')" value=0>
									<img class="like" id="likeImgB_${status.index}" src="images/bookmark.png" alt="좋아요" />
									</button>
								</c:otherwise>
							</c:choose>
							<span id="dday">${dto.hdate}</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</div> 
		<div class="floatDelete"></div>
	</div>


	<!-- footer include -->
	<jsp:include page="footer.jsp" /> 
</body>
</html>