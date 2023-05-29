<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/expert/estimatemgmt.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        
        <nav>
            <div class="nav-customer-area">
                <div class="profile-area">
                    <div class="profile-img">
                        <c:if test="${empty loginMember.memberProfilePhoto}">
                            <img src="${root}/static/svg/기본프로필.svg" alt="프로필사진">
                        </c:if>
                        <c:if test="${not empty loginMember.memberProfilePhoto}">
                        		<img alt="프로필사진" src="${root}/static/img/profile/${loginMember.memberProfilePhoto}">
                        </c:if>
                    </div>
                    <div class="profile-nick bold">${loginMember.memberNick}</div>
                    <a class="profile-btn bold" href="${root}/customer/request-list">의뢰인으로 변환</a>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/expert/request-list">제안서 관리</a></li>
                    <li><a href="${root}/expert/sale-list">판매 관리</a></li>
                    <li><a class="bold" href="${root}/expert/estimate-mgmt">견적서 관리</a></li>
                    <li><a href="${root}/expert/QNA-mgmt">전문가 Q&A 관리</a></li>
                    <li><a href="${root}/expert/grade">스킬 등급</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="estimate-area">
                <div class="estimate-title bold">견적서 관리</div>
                

                <div class="estimate-list-area">

                    <div class="estimate-grid-area">
                    	<c:forEach items="${estimateList}" var="vo">
                    		<a href="" class="estimate-list">
                            <div class="estimate-certification">인증계정</div>
                            <img src="" alt="로고이미지">
                            <div class="estimate-list-text">
                                <div class="estimate-list-title">${vo.estimateTitle }...</div>
                                <div class="estimate-list-content">${vo.estimateLineIntroducation}</div>
                            </div>
                            <div class="estimate-list-num">
                                <div class="estimate-list-price bold">${vo.estimatePrice}원</div>
                                <div class="estimate-list-review">${vo.estimateReviewCount} 평가</div>

                            </div>
                        </a>
                    	</c:forEach>
                    </div>
                    
                </div>
                
            </div>
            

    </main>

</body>
</html>