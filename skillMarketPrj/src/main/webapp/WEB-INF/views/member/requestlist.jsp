<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/requestlist.css">
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
                    <li><a class="bold" href="${root}/customer/request-list">제안서 관리</a></li>
                    <li><a href="${root}/customer/order-list">구매 관리</a></li>
                    <li><a href="${root}/customer/cash">캐시</a></li>
                    <li><a href="${root}/customer/cash-charge">캐시 충전</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="requestlist-area">
                <div class="requestlist-title bold">제안서 관리</div>
                <div class="requestlist-showboard-area">
                    <div class="requestlist-showboard">
                        <div class="requestlist-textbox">
                            <div class="requestlist-showboard-text bold">보낸 제안서</div>
                            <div class="requestlist-showboard-num bold">0</div>
                        </div>
                    </div>
                    <div class="requestlist-showboard">
                        <div class="requestlist-textbox">
                            <div class="requestlist-showboard-text bold">받은 제안서</div>
                            <div class="requestlist-showboard-num bold">0</div>
                        </div>
                    </div>
                    <div class="requestlist-showboard">
                        <div class="requestlist-textbox">
                            <div class="requestlist-evaluation">
                                <div class="requestlist-smalltext">미처리된 제안서</div>
                                <div class="requestlist-smalltext">0</div>
                            </div>
                            <div class="requestlist-evaluation">
                                <div class="requestlist-smalltext">처리된 제안서</div>
                                <div class="requestlist-smalltext">0</div>
                            </div>
                            <div class="requestlist-cancel">
                                <div class="requestlist-smalltext">제안서 취소</div>
                                <div class="requestlist-smalltext">0</div>
                            </div>
                        </div>
                    </div>

                </div>
                <form action="">
                    <div class="requestlist-form-area">
                        <select class="requestlist-selectbox bold" name="orderStatus">
                            <option value="">전체상태</option>
                        </select>
                        <select class="requestlist-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <div class="bold" style="font-size: 24px;">~</div>
                        <select class="requestlist-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <select class="requestlist-selectbox bold" name="">
                            <option value="">닉네임</option>
                        </select>
                        <input class="requestlist-searchbox" type="text">
                        <input class="requestlist-searchbtn" type="submit" value="검색">
                    </div>
                </form>

                <div class="requestlist-list-area">


                </div>

            </div>


    </main>

</body>
</html>