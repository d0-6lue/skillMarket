<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/orderlist.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        
        <nav>
            <div class="nav-customer-area">
                <div class="profile-area">
                    <div class="profile-img">
                        <c:if test="{empty loginMember.memberProfilePhoto}">
                            <img src="${root}/static/svg/기본프로필.svg" alt="프로필사진">
                        </c:if>
                        <c:if test="${not empty loginMember.memberProfilePhoto}">
                        		<img alt="프로필사진" src="${root}/static/img/profile/${loginMember.memberProfilePhoto}">
                        </c:if>
                    </div>
                    <div class="profile-nick bold">${loginMember.memberNick}</div>
                    <a class="profile-btn bold" href="${root}/expert/request-list">전문가로 변환</a>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/customer/request-list">제안서 관리</a></li>
                    <li><a class="bold" href="${root}/customer/order-list">구매 관리</a></li>
                    <li><a href="${root}/customer/cash">캐시</a></li>
                    <li><a href="${root}/customer/cash-charge">캐시 충전</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="orderlist-area">
                <div class="orderlist-title bold">구매 관리</div>
                <div class="orderlist-showboard-area">
                    <div class="orderlist-showboard">
                        <div class="orderlist-textbox">
                            <div class="orderlist-showboard-text bold">진행중</div>
                            <div class="orderlist-showboard-num bold">0</div>
                        </div>
                    </div>
                    <div class="orderlist-showboard">
                        <div class="orderlist-textbox">
                            <div class="orderlist-showboard-text bold">거래완료</div>
                            <div class="orderlist-showboard-num bold">0</div>
                        </div>
                    </div>
                    <div class="orderlist-showboard">
                        <div class="orderlist-textbox">
                            <div class="orderlist-evaluation">
                                <div class="orderlist-smalltext">작성 가능한 평가</div>
                                <div class="orderlist-smalltext">0</div>
                            </div>
                            <div class="orderlist-cancel">
                                <div class="orderlist-smalltext">주문 취소</div>
                                <div class="orderlist-smalltext">0</div>
                            </div>
                        </div>
                    </div>

                </div>
                <form action="">
                    <div class="orderlist-form-area">
                        <select class="orderlist-selectbox bold" name="orderStatus">
                            <option value="">전체상태</option>
                        </select>
                        <select class="orderlist-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <div class="bold" style="font-size: 24px;">~</div>
                        <select class="orderlist-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <select class="orderlist-selectbox bold" name="">
                            <option value="">닉네임</option>
                        </select>
                        <input class="orderlist-searchbox" type="text">
                        <input class="orderlist-searchbtn bold" type="submit" value="검색">
                    </div>
                </form>

                <div class="orderlist-list-area">


                </div>

            </div>


    </main>

</body>
</html>