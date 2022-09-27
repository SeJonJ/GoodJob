<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hireList</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
<style>
@import 
url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
	.contentList {
		padding: 120px 0 120px 0;
		font-family: 'Noto Sans KR', sans-serif;
	}
	.Top {
		padding: 40px 60px 30px 60px;
	}
	.Top>p {
		float: left;
		color: #222;
    	font-size: 22px;
    	line-height: 27px;
    	font-weight: 600;
	}
	.dropdownSort {
		padding-left: 1090px;
	}
	.hireArea {
		border-top: 1px solid #E5E5E5;
		padding: 30px 60px 30px 60px;
	}
	.hireArea:hover {
		background-clip: border-box;
		background-color: rgba(239,245,255,0.3);
	}
	.hireTitle {
		float: left;
	}
	.hireDate {
		float: left;
		color: #949494;
		line-height: 17px;
		font-size: 13px;
		padding: 4px 0 0 12px;
	}
	.comTitle {
		float: left;
		font-size: 15px;
    	font-weight: bold;
    	letter-spacing: -1px;
    	color: #222222;
    	padding-top: 15px;
	}
	.hireCondition {
		text-align: left;
		/* float: left; */
		color: #555555;
    	font-size: 13px;
    	line-height: 17px;
    	padding-top: 7px;
	} 
	.hireCondition>span {
		padding-right: 8px;
	}
	.bookmark>button {
		background-color: transparent;
		border: 0;
		padding-top: 12px;
	}
	.hireTitle>a {
		text-decoration: none;
		color: #222222;
		font-size: 17px;
    	font-weight: bold;
   		letter-spacing: -1px;
	}
	.hireTitle a:hover {
		color: #222222;
		text-decoration: underline;
	}
	.hireInfo {
		float: left;
	}
	.comArea {
		padding-left: 960px; 
	}
	.floatDelete {
		clear: both;
	} 
	. {
		font-size: 10px;
	}
	/* 페이징 */
	.pagination {
		text-align: center;
		justify-content: center;
		margin: 0px;
		padding: 50px 0 50px 0;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		let result = '<c:out value="${result}"/>'
	
		checkAlert(result);
		//console.log(result);
	
		function checkAlert(result){
			if(result === ''){
				return;
			}
		}
	})
	
	// 페이지 이동
	$(".pageInfo a").on("click", function(e) {
		let moveForm = $("#moveForm");
		e.preventDefault();
		//console.log("test");
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		moveForm.attr("action", "/goodjob/hireList");
		moveForm.submit(); 
	})  
	
	// 정렬
	$(function(){
		$(".form-select").change(function(){
			let sortForm = $("#sortForm");
			var sort = $(".form-select").val();
			//console.log(sort);
			sortForm.find('input[name="pageNum"]').val(1);
			sortForm.find('input[name="sort"]').val(sort);
			sortForm.submit();
		})
	})
	
	function LikeUpdate(hno, index){

		
		var $like = $("#like_"+index);
		var type;
		
		if($like.val() == 1){
			type = "del";
			$like.val(0);
			$("#likeImg_"+index).attr("src", "images/bookmark.png");
		}else{
			type = "ins";
			$like.val(1);
			$("#likeImg_"+index).attr("src", "images/bookmark1.png");
		}
		
		//console.log("$like : "+$like)
		//console.log("type : "+type)
		
		
		$.ajax({
			type : "POST",
			url : "/likeUpdate",
			data : {
				"type" : type,
				"hno" : hno
			},
		})
		
	}
	
</script>
</head>
<body>
	<!-- header include -->
	<sec:authorize access="isAnonymous()">		
		<jsp:include page="../main/header.jsp" /> 	
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_USER')">
		<jsp:include page="../main/headerMember.jsp" />
	</sec:authorize> 
	<sec:authorize access="hasRole('ROLE_MANAGER')">
		<jsp:include page="../main/headerCompany.jsp" />
	</sec:authorize> 
	
	<!-- 검색창 include -->
	<div>
		<jsp:include page="../main/searchBar.jsp" />  
	</div>
	
	<!-- content -->
	<div class="bg-light contentList">
		<div class="container bg-white hireList">
		<%-- <h6>총 게시물 수 : ${pageMaker.total}</h6>
		<h6>처음 페이지 : ${pageMaker.startPage}</h6>
		<h6>끝 페이지 : ${pageMaker.endPage}</h6>
		<h6>현재 페이지 : ${pageMaker.page.pageNum}</h6>
		<h6>페이지 당 게시물 수 : ${pageMaker.page.amount}</h6>  
		<h6>키워드 : ${pageMaker.page.keyword}</h6>
		<h6>jno : ${pageMaker.page.jno}</h6>
		<h6>smallno : ${pageMaker.page.smallno}</h6>
		<h6>정렬 : ${pageMaker.page.sort}</h6> --%>
			<!-- 정렬 -->
			<div class="Top">
				<p>채용정보</p>
				<div class="dropdownSort">
				  <select class="form-select" aria-label="Default select example" style="width:130px;">
				  	<option selected>정렬</option>
				  	<option class="dropdown-item" value="hno">등록일순</option>
				  	<option class="dropdown-item" value="bookmark">좋아요순</option>
				  	<option class="dropdown-item" value="star">평점순</option>
				  </select>
				</div>
			</div>
			
			<!-- list -->
			<div class="content">
			<c:forEach var="dto" items="${HList}" varStatus="status">
				<div class="hireArea">
					<div class="hireInfo">
						<div class="hireTitle">
							<a href="/goodjob/hire/hiredetail?hno=${dto.hno}">${dto.htitle}</a>
						</div>
						<div class="hireDate">
							<span>~${dto.hdate}</span>
						</div><br />
						<div class="hireCondition">
							<span>${dto.bigname} ${dto.smallname}</span>
							<span>${dto.hworkinfo}</span>
							<span>${dto.jtitle}</span>
						</div>
						<!-- <div class="floatDelete"></div> -->
					</div>
					<div class="comArea">
						<div class="comTitle">
							${dto.cname}
						</div>
						<div class="bookmark">
							<c:choose>
								<c:when test="${check != 'user'}">
									<button type="button" class="btn_scrap" title="공고좋아요"
										data-bs-toggle="modal" data-bs-target="#ModalLike">
									<img class="like" id="likeImg" src="images/bookmark.png" alt="좋아요" />
									</button>											
									<%--로그인이 필요합니다 modal--%>
									<div class="modal fade" id="ModalLike" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel"></h5>
													<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body" style="text-align: center; padding-top:30px; padding-bottom:30px;">
								<c:if test="${check == 'company'}">
														<h5>일반회원만 이용하실 수 있습니다</h5>
								</c:if>
								<c:if test="${check == 'noLogin'}">
														<h5>로그인이 필요합니다</h5>
								</c:if>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
													<a href="/goodjob/login/loginform"><button type="button" class="btn btn-primary">&nbsp로그인창으로 이동&nbsp</button></a>
												</div>
												</div>
											</div>
										</div>									
								</c:when>						
								<c:when test="${hhList[status.index] != null}">
									<button type="button" class="btn_scrap" title="공고좋아요" id="like_${status.index}"
										onclick="LikeUpdate('${dto.hno}', '${status.index}')" value=1>
									<img class="like" id="likeImg_${status.index}" src="images/bookmark1.png" alt="좋아요" />
									</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn_scrap" title="공고좋아요" id="like_${status.index}"
										onclick="LikeUpdate('${dto.hno}', '${status.index}')" value=0>
									<img class="like" id="likeImg_${status.index}" src="images/bookmark.png" alt="좋아요" />
									</button>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="floatDelete"></div>
					</div>
				</div>
			</c:forEach>
			</div>
			<!-- 페이지네이션 -->
			<div class="pageInfo_wrap">
				<div class="pageInfo_area">
					<ul id="pageInfo" class="pagination pageInfo">
						<!-- 이전페이지 버튼 -->
						<c:if test="${pageMaker.prev}">
							<li class="pageInfo_btn previous page-item"><a class="page-link" href="${pageMaker.startPage-1}"><span aria-hidden="true">&laquo;</span></a></li>
						</c:if>
						<!-- 페이지 번호 버튼 -->
						<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
							<li class="pageInfo_btn page-item ${pageMaker.page.pageNum == num ? "active":"" }"><a class="page-link" href="/goodjob/hireList?pageNum=${num}&amount=${pageMaker.page.amount}&keyword=${pageMaker.page.keyword}&smallno=${pageMaker.page.smallno}&jno=${pageMaker.page.jno}&sort=${pageMaker.page.sort}">${num}</a></li>
						</c:forEach>
						<!-- 다음페이지 버튼 --> 
						<c:if test="${pageMaker.next}">
							<li class="pageInfo_btn next page-item"><a class="page-link" href="${pageMaker.endPage+1}"><span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
					</ul>
				</div>
			</div>
			<form id="sortForm" method="get">
				<input type="hidden" name="pageNum" value="${pageMaker.page.pageNum}" />
				<input type="hidden" name="amount" value="${pageMaker.page.amount}" />
				<input type="hidden" name="keyword" value="${pageMaker.page.keyword}" />
				<input type="hidden" name="smallno" value="${pageMaker.page.smallno}" /> 
				<input type="hidden" name="jno" value="${pageMaker.page.jno}" />  
				<input type="hidden" name="sort" value="${pageMaker.page.sort}" />
			</form> 
		</div>
	</div> 
	
	<!-- footer include -->
	<jsp:include page="../main/footer.jsp" /> 
</body>
</html>