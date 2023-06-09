<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/expert/qnamgmt.css">
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
                    <a class="profile-btn bold" href="${root}/customer/order-list">의뢰인으로 변환</a>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/expert/sale-list">판매 관리</a></li>
                    <li><a href="${root}/expert/estimate-mgmt">견적서 관리</a></li>
                    <li><a class="bold" href="${root}/expert/QNA-mgmt">전문가 Q&A 관리</a></li>
                    <li><a href="${root}/expert/grade">스킬 등급</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="qna-area">
                <div class="qna-title bold">전문가 Q&A 관리</div>
                

                <div class="qna-list-area">

                    <div class="qna-grid-area">
                    	<c:forEach items="${estimateList}" var="evo">
                    		<div class="qna-list">
                    			<c:if test="${evo.estimateDisReview > 0}">
	                            	<div class="qna-dec bold">${evo.estimateDisReview}</div>
                    			</c:if>
	                            ${evo.estimateThumbnail}
	                            <div class="qna-list-text">
	                                <div class="qna-list-title">${evo.estimateTitle}</div>
	                            </div>
	                            <div class="qna-list-num">
	                                <div class="qna-list-price bold">${evo.estimatePrice}원</div>
	                            </div>
	                            <a href="${root}/esti?estimateNo=${evo.estimateNo}" class="qna-comment-btn bold">답변 하로 가기</a>
	                        </div>
                    	</c:forEach>
                    </div>
                    
                </div>

            </div>


    </main>

</body>
</html>