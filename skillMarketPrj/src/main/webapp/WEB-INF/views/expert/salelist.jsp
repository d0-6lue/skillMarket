<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/expert/salelist.css">
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
                    <li><a class="bold" href="${root}/expert/sale-list">판매 관리</a></li>
                    <li><a href="${root}/expert/estimate-mgmt">견적서 관리</a></li>
                    <li><a href="${root}/expert/QNA-mgmt">전문가 Q&A 관리</a></li>
                    <li><a href="${root}/expert/grade">스킬 등급</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="salelist-area">
                <div class="salelist-title bold">구매 관리</div>
                <div class="salelist-showboard-area">
                    <div class="salelist-showboard">
                        <div class="salelist-textbox">
                            <div class="salelist-showboard-text bold">진행중</div>
                            <div class="salelist-showboard-num bold">0</div>
                        </div>
                    </div>
                    <div class="salelist-showboard">
                        <div class="salelist-textbox">
                            <div class="salelist-showboard-text bold">거래완료</div>
                            <div class="salelist-showboard-num bold">0</div>
                        </div>
                    </div>
                    <div class="salelist-showboard">
                        <div class="salelist-textbox">
                            <div class="salelist-evaluation">
                                <div class="salelist-smalltext">작성 가능한 평가</div>
                                <div class="salelist-smalltext">0</div>
                            </div>
                            <div class="salelist-cancel">
                                <div class="salelist-smalltext">주문 취소</div>
                                <div class="salelist-smalltext">0</div>
                            </div>
                        </div>
                    </div>

                </div>
                <form action="">
                    <div class="salelist-form-area">
                        <select class="salelist-selectbox bold" name="orderStatus">
                            <option value="">전체상태</option>
                        </select>
                        <select class="salelist-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <div class="bold" style="font-size: 24px;">~</div>
                        <select class="salelist-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <select class="salelist-selectbox bold" name="">
                            <option value="">닉네임</option>
                        </select>
                        <input class="salelist-searchbox" type="text">
                        <input class="salelist-searchbtn bold" type="submit" value="검색">
                    </div>
                </form>

                <div class="salelist-list-area">


                </div>

            </div>


    </main>

</body>
</html>