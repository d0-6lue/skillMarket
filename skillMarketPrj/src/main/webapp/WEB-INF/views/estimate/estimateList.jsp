<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 선택시 프로젝트 리스트 ~</title>

<!-- 상세보기 페이지 css -->
<link rel="stylesheet" href="${root}/static/css/estimate/estimateList.css">

<!-- 부트스트랩 css js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

    <div id="wrap">

        <!-- header -->
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
		<main>
           
			<div class="esti-list-box">
            
                <div class="sidebar">
                    <div class="category-container">
                        <div class="category-title">
                            <strong>카테고리</strong>
                        </div>
                    </div>

                    <div class="middle-category-container">
                        <div><strong>중분류</strong></div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                    </div>

                    <div class="middle-category-container">
                        <div><strong>중분류</strong></div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                        <div>소분류</div>
                    </div>
                
        
                </div>
				
                <div class="main-list-box">
                    <div class="category-total-box">
                        <div class="category-select">홈 > 카테고리</div>
                        <div class="category-menu-bar-container">
                            <span class="category-menu-bar">
                                <select class="form-select" id="category-1">
                                    <option value="" disabled selected>카테고리 선택</option>
                                    <option value="category1">카테고리1</option>
                                    <option value="category2">카테고리2</option>
                                </select>
                                <select class="form-select" id="category-2">
                                    <option value="" disabled selected>서비스 옵션</option>
                                    <option value="category3">카테고리3</option>
                                    <option value="category4">카테고리4</option>
                                </select>
                                <select class="form-select" id="category-3">
                                    <option value="" disabled selected>전문가 옵션</option>
                                    <option value="category5">카테고리5</option>
                                    <option value="category6">카테고리6</option>
                                </select>
                                <select class="form-select" id="category-4">
                                    <option value="" disabled selected>예산</option>
                                    <option value="category7">카테고리7</option>
                                    <option value="category8">카테고리8</option>
                                </select>
                            </span>
                        </div>       
                    </div>


                    <div class="seller-service-box">
                        
                        <div class="four-service-list">
                           
                            <c:forEach var="estimate" items="${estimateList}">
                            <div class="service-border">
                                <div class="profile-image-box">
                                <div id="content">
                                    <c:out value="${estimate.mainImage}" escapeXml="false" />
                                </div>
                                </div>
                                <div class="expert-info">
                                <div class="expert-box">
                                    <div class="expert-name">${estimate.memberNick}</div>
                                    <div class="expert-badge">${estimate.estimateNo}</div>
                                </div>
                                <div class="expert-title">${estimate.estimateTitle}</div>
                                <div class="expert-account">${estimate.estimateLineIntroduction}</div>
                                <div class="expert-balance">${estimate.estimatePrice}원</div>
                                <div class="heart-area">
                                    <div class="heart-icon">❤️</div>
                                    <div class="expert-ratings">${estimate.reviewCount}</div>
                                </div>
                                </div>
                            </div>
                            </c:forEach>
                 
                        </div>

                   
                        </div>
                    </div>

                    <div class="page-area">
                        <c:if test="${pv.currentPage > 1}">
                        <a class="btn btn-primary" href="${root}/community?page=${pv.currentPage - 1}">이전</a>
                        </c:if>
                            <c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
                                <c:if test="${pv.currentPage != i}">
                                    <a class="btn btn-primary btn-sm" href="${root}/community?page=${i}">${i}</a>
                                </c:if>
                                <c:if test="${pv.currentPage == i}">
                                    <a class="btn btn-primary btn-sm">${i}</a>
                                </c:if>
                            </c:forEach>
                        <c:if test="${pv.currentPage < pv.maxPage}">
                            <a class="btn btn-primary btn-sm" href="${root}/community?page=${pv.currentPage + 1}">다음</a>
                        </c:if>
                    </div>
                    
                        
                </div>
				
			</div>

			
		</main>

        <!-- footer  -->
			<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
		

	</div>
	

</body>
</html>