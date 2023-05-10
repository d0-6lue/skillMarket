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
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        
        <nav>
            <div class="nav-customer-area">
                <div class="profile-area">
                    <div class="profile-img">
                        <img src="${root}/static/png/예시사진.png" alt="프로필사진">
                    </div>
                    <div class="profile-nick bold">스킬쟁이123</div>
                    
                    <button class="profile-btn bold" type="button">전문가로 변환</button>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/customer/request-list">제안서 관리</a></li>
                    <li><a href="${root}/customer/order-list">구매 관리</a></li>
                    <li><a href="${root}/customer/cash">캐시</a></li>
                    <li><a class="bold" href="${root}/customer/cash-charge">캐시 충전</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="cash-area">
                <div class="cash-title bold">캐시 충전</div>

                <div class="cash-charge-area">

                    <div>충전 캐시</div>
                    <div>충전할 금액을 선택해주세요</div>
                    <div class="cash-charge-box-area">
                        <button value="50000">5만원</button>
                        <button value="100000">10만원</button>
                        <button value="300000">30만원</button>
                        <button value="500000">50만원</button>
                        <button value="100000">100만원</button>
                        <button value="200000">200만원</button>
                        <div class="cash-charge-inputbox">
                            <input type="number">
                            <div>원</div>
                        </div>
                    </div>
                    
                    <a href="">충전하기</a>
                </div>

            </div>


    </main>

</body>
</html>