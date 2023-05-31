<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/cashcharge.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script defer src="${root}/static/js/member/cashcharge.js"></script>
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
                    
                    <a class="profile-btn bold" href="${root}/expert/sale-list">전문가로 변환</a>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/customer/order-list">구매 관리</a></li>
                    <li><a href="${root}/customer/cash">캐시</a></li>
                    <li><a class="bold" href="${root}/customer/cash-charge">캐시 충전</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="cash-area">
                <div class="cash-title bold">캐시 충전</div>

                <div class="cash-charge-area">
                    <div class="cash-charge-text">
                        <div class="cash-charge-title bold">충전 캐시</div>
                        <div class="cash-charge-title-sub bold">충전할 금액을 선택해주세요</div>
                    </div>
                    <div class="cash-charge-box-area">
                        <button class="amount-btn" value="50000">5만원</button>
                        <button class="amount-btn" value="100000">10만원</button>
                        <button class="amount-btn" value="300000">30만원</button>
                        <button class="amount-btn" value="500000">50만원</button>
                        <button class="amount-btn" value="1000000">100만원</button>
                        <button class="amount-btn" value="2000000">200만원</button>
                        <div class="cash-charge-inputbox">
                            <input class="result-amount" type="number">
                            <div>원</div>
                        </div>
                    </div>
                    
                    <button disable="" class="charge-btn bold yellow">충전하기</button>
                </div>

            </div>


    </main>

</body>
</html>