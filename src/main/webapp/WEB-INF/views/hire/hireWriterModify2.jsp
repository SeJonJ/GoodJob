<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<!-- bootstrap-datepicker cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>	
<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<!-- bootstrap-datepicker cdn -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<style type="text/css">
	
	.smallContainer{
        background-color: whitesmoke;
        padding: 50px 100px;

    }
    
    .box1,.box2,.box3, .box5{	
        background-color: white;
        margin-bottom: 50px;
        margin-top: 20px;
        padding: 30px 50px;
	}
    .mb-3{
        padding: 5px;
    }
    .col-sm-10{
        padding-top: 5px;
    }
    .form-select{
        height: 50px;
    }
    .form-control{
        height: 50px;
    }
    label>strong{
        padding-top: 10px;
    }
    h6{
        margin: 20px;
    }
    ul{
        list-style:none;
        margin:20px 0px;
        padding: 0px;
    }
    li{
        border: 1px solid gainsboro;
        height: 120px;
        padding-top: 40px;
        padding-left: 30px;
    }
    #check1, #check2{
        height: 30px;
        width: 30px;

    }
    
</style>

    <script>
        $(document).ready(function(){
            $("#hsalcheck").on('click',function(){
                console.log("클릭");
                console.log($("#hsalcheck").val(""));
                
                $("#hsal").val("입력");
          
            });
        })
  
    </script>
    
</head>
<body> 


	<div class="container">
		<form action="/goodjob/hire/hireWriterModify" method="get">
			<div class="smallContainer">	
				<div><!-- 첫번쨰영역 제목 -->					
					<h2><strong>어떤일을 담당할 직원을 찾으시나요?</strong></h2>
				</div>
				<div class="box1"><!-- 첫번째 영역 -->						
					<div class="mb-3 row"><!-- 기업명선택 -->
						<label for="jno" class="col-sm-2 col-form-label" style="padding-top: 10px;"><strong>기업명</strong></label>
						<div class="col-sm-10">
						<input type="text" class="form-control" value="${one.cname }"  disabled readonly> 
						<input type="hidden" class="form-control" id="cno" name="cno" value="${one.cno }" />
							<!-- <select class="form-select" aria-label="Default select example" id="selectCom"
								name="cno">
								<option selected>기업명</option>
								<c:forEach var="dto" items="${CList}">
									<option value="${dto.cno }">${dto.cname }</option>
								</c:forEach>
							</select> -->
						</div>
					</div>

				<div class="mb-3 row"> <!-- 모집분야명 -->		
						<label for="jno" class="col-sm-2 col-form-label" style="padding-top:10px;"><strong>모집분야명</strong></label>
						<div class="col-sm-10">
							<select class="form-select" aria-label="Default select example" name="jno">
								<option selected>직무를 선택해주세요</option>
								<option value="2">상품기획·MD</option>
								<option value="3">기획·전략</option>
								<option value="4">운전·운송·배송</option>
								<option value="5">서비스</option>
								<option value="6">생산</option>
								<option value="7">건설·건축</option>
								<option value="8">의료</option>
								<option value="9">연구</option>
								<option value="10">교육</option>
								<option value="11">미디어·문화·스포츠</option>
								<option value="12">금융·보험</option>
								<option value=13">공공·복지</option>
								<option value="14">마케팅·홍보·조사</option>
								<option value="15">회계·세무·재무</option>
								<option value="16">인사·노무·HRD</option>
								<option value="17">총무·법무·사무</option>
								<option value="18">IT개발·데이터</option>
								<option value="19">디자인</option>
								<option value="20">영업·판매·무역</option>
								<option value="21">고객상담·TM</option>
								<option value="22">구매·자재·물류</option>							
							</select>
						</div>
					</div>
					
					<div class="mb-3 row"> <!-- 경력여부 -->
						<label for="hworkinfo" class="col-sm-2 col-form-label"><strong>경력여부</strong></label>
						<div class="col-sm-10">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hworkinfo" id="inlineRadio1" value="신입">
								<label class="form-check-label" for="inlineRadio1">신입</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hworkinfo" id="inlineRadio2" value="경력">
								<label class="form-check-label" for="inlineRadio2">경력</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hworkinfo" id="inlineRadio3" value="경력무관">
								<label class="form-check-label" for="inlineRadio4">경력무관</label>
							</div>
						</div>
					</div>

					<div class="mb-3 row"> <!-- 주요업무 -->
						<label for="hmain" class="col-sm-2 col-form-label" style="padding-top:20px;"><strong>주요업무</strong></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="hmain" name="hmain" value="${one.hmain }"
								placeholder="주요업무를 입력해주세요">
						</div>
					</div>
					<div class="mb-3 row"> <!-- 필수/우대조건 -->
						<label for="hreq" class="col-sm-2 col-form-label" style="padding-top:20px;"><strong>필수/우대조건</strong></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="hreq" name="hreq" value="${one.hreq }"
								placeholder="필수/우대조건을 입력해주세요">
						</div>
					</div>
				</div><!-- 첫번째영역end -->

				<div> <!-- 두번쨰영역 제목 -->
					<h2><strong>지원자격과 근무조건은 어떻게 되나요?</strong></h2>
				</div>
				<div class="box2"> <!-- 두번째영역 -->
					<div class="mb-3 row"> <!-- 지원자학력 -->		
						<label for="" class="col-sm-2 col-form-label"><strong>지원자 학력</strong></label>
						<div class="col-sm-10">
							<select class="form-select" aria-label="Default select example" name="hedu">
								<option selected>학력을 선택해주세요</option>
								<option value="고등학교졸업">고등학교 졸업</option>
								<option value="대학(2~3년제)졸업">대학(2~3년제)졸업</option>
								<option value="대학교(4년제)졸업">대학교(4년제)졸업</option>
								<option value="대학원졸업">대학원졸업</option>
								<option value="학력 무관">학력 무관</option>
							</select>
						</div>
					</div>
					
					<div class="mb-3 row"> <!-- 근무형태 -->		
						<label for="" class="col-sm-2 col-form-label"><strong>근무형태</strong></label>
						<div class="col-sm-10">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hform" id="hform" value="정규직">
								<label class="form-check-label" for="">정규직</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hform" id="hform" value="계약직">
								<label class="form-check-label" for="">계약직</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hform" id="hform" value="인턴직">
								<label class="form-check-label" for="">인턴직</label>
							</div>
						</div>
					</div>
					
					<div class="mb-3 row"> <!-- 급여 -->		
						<label for="" class="col-sm-2 col-form-label" style="padding-top:20px;"><strong>급여</strong></label>
						<div class="col-sm-10">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hsal" id="" value="면접 후 결정">
								<label class="form-check-label" for="">면접 후 결정</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hsal" id="" value="회사 내규에 따름">
								<label class="form-check-label" for="">회사 내규에 따름</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									 name="hsal" id="hsalcheck" value="직접 작성">
								<label class="form-check-label" for="">직접 작성</label>				
							</div>
							<div class="form-check form-check-inline">
								<input type="text" class="form-control" id="hsal" name="hsal" placeholder="내용을 입력해주세요">
							</div>
						</div>
					</div>
					<div class="mb-3 row"> <!-- 근무 요일 -->		
						<label for="" class="col-sm-2 col-form-label"><strong>근무요일</strong></label>
						<div class="col-sm-10">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hworkday" id="hworkday" value="주5일(월~금)">
								<label class="form-check-label" for="">주5일(월~금)</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hworkday" id="hworkday" value="유연 근무제">
								<label class="form-check-label" for="">유연 근무제</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hworkday" id="hworkday" value="면접 후 결정">
								<label class="form-check-label" for="">면접 후 결정</label>
							</div>
						</div>
					</div>
					<div class="mb-3 row"> <!-- 근무 지역 -->		
						<label for="" class="col-sm-2 col-form-label"><strong>근무지역</strong></label>
						<div class="col-sm-3">
							<select class="form-select" aria-label="Default select example" name="bigno">
								<option selected>대분류</option> 
							<c:forEach var="dto" items="${big}">
								<option value="${dto.bigno }">${dto.bigname }</option>	
							</c:forEach>
										
							</select>
						</div>
						<div class="col-sm-5">
							<select class="form-select" aria-label="Default select example" name="smallno">
								<option selected>소분류</option> 
							<c:forEach var="dto2" items="${small}">
								<option value="${dto2.smallno }">${dto2.smallname }</option>	
							</c:forEach>
							</select>
		<!-- hidden값 넣음 --><input type="hidden" name="hno" value="${one.hno }" />	
						</div>
					</div>		
				</div> <!-- 두번째영역end-->

				<div> <!-- 세번쨰영역 제목 -->
					<h2><strong>채용절차는 어떻게 되나요?</strong></h2>
				</div>
				<div class="box3"><!-- 세번째영역 -->
					<div class="mb-3 row"> <!-- 접수 기간 -->
						<label for="hreq" class="col-sm-2 col-form-label"><strong>접수 기간</strong></label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="datepicker" name="hdate" value="접수기간 선택해주세요" />
							<script>
							$('#datepicker').datepicker({
								format:"yyyy-mm-dd",
								autoclose: true,
								todayHighlight : true
								
							});
							</script>
						</div>
						<div class="col-sm-5">  
							<select class="form-select" aria-label="Default select example" name="hdate">
								<option selected>시간선택</option>
								<%for(int i = 0; i<=24; i++){
								if(i<=9){%>								
									<option value=<%="0"+i+" : 00"%>><%="0"+i+" : 00"%></option>
								<%}else{%>
									<option value=<%=i+" : 00"%>><%=i+" : 00"%></option>
								<%}%>
									
								<%}%>	
							</select>
						</div>
					</div>
					<div class="mb-3 row"> <!-- 접수 방법 -->		
						<label for="" class="col-sm-2 col-form-label"><strong>접수 방법</strong></label>
						<div class="col-sm-10">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hway" id="hway" value="사람인">
								<label class="form-check-label" for="">사람인</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hway" id="hway" value="홈페이지">
								<label class="form-check-label" for="">홈페이지</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="hway" id="hway" value="이메일">
								<label class="form-check-label" for="">이메일</label>
							</div>
						</div>
					</div>
					
					
				</div>

				<div> <!-- 네번쨰영역 제목 -->
					<h2><strong>채용제목을 입력해주세요</strong></h2>
				</div>
				<div><!-- 네번째영역 -->
					<div class="mb-3 row">
						<div class="col-sm-12">
							<input style="margin-bottom:30px; margin-top:10px" type="text" class="form-control" id="htitle" name="htitle"
							 placeholder="채용제목을 입력해주세요" >
						</div>
					</div>
				</div>

				<div> <!-- 다섯번쨰영역 제목 -->
					<h2><strong>공고등록 전 확인하기</strong></h2>
				</div>
				<div><!-- 다섯번째영역 -->
					<div class="box5">
                        <h6><strong>안전하고 올바른 채용을 위해 아래와 같은 내용을 확인 받고 있습니다</strong></h6>
                        <ul>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="check1">
                                    <label class="form-check-label" for="flexCheckDefault" style="padding-left:30px; padding-top: 7px;">
                                        <strong>최저임금을 준수하지 않는 경우, 공고 강제 마감 및 행정처분을 받을 수 있습니다.</strong> 
                                    </label>
                                </div>
                            </li>
                            <li>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="check2">
                                    <label class="form-check-label" for="flexCheckDefault" style="padding-left:30px; padding-top: 7px;" >
                                        <strong>채용절차 공정화법상 금지되는 개인정보를 요구하는 경우, 공고 강제 마감 및 행정처분을 받을 수 있습니다.</strong>
                                    </label>
                                </div>
                            </li>
                        </ul>
					</div>
				</div>
				
					<!-- submit추가 -->
					<div style="text-align:center;">
					<input type="submit" id="submit" value="수정" class="btn btn-primary" style= "height:50px; width:500px;"  />
					</div>
					
					
			</div><!-- mid end(회색전체) -->
		</form>
	</div> 
	<!-- footer include -->
	<jsp:include page="../main/footer.jsp" /> 
</body>
</html>