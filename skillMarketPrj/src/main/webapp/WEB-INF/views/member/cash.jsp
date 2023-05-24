<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/cash.css">
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
                    <a class="profile-btn bold" href="${root}/expert/request-list">전문가로 변환</a>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/customer/request-list">제안서 관리</a></li>
                    <li><a href="${root}/customer/order-list">구매 관리</a></li>
                    <li><a class="bold" href="${root}/customer/cash">캐시</a></li>
                    <li><a href="${root}/customer/cash-charge">캐시 충전</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="cash-area">
                <div class="cash-title bold">캐시</div>
                <div class="cash-showboard-area">
                    <div class="cash-showboard">
                        <div class="cash-textbox lightyellow">
                            <div class="cash-showboard-text bold">보유 중인 총 캐시</div>
                            <div class="cash-showboard-box">
                                <div id="cash-total" class="cash-showboard-num">${cashVo.cashTotal}</div>
                            </div>
                        </div>
                    </div>
                    <div class="cash-showboard">
                        <div class="cash-textbox">
                            <div class="cash-showboard-text bold">적립 캐시</div>
                            <div class="cash-showboard-box">
                                <div id="cash-bonus" class="cash-showboard-num">${cashVo.cashPoint}</div>
                            </div>
                        </div>
                    </div>
                    <div class="cash-showboard">
                        <div class="cash-textbox">
                            <div class="cash-showboard-text bold">충전 캐시</div>
                            <div class="cash-showboard-refund">
                                <div class="cash-showboard-refund-box">
                                    <div id="cash-real" class="cash-showboard-num">${cashVo.cashMoney}</div>
                                </div>
                                <a id="refund-btn" class="cash-showboard-refund-btn yellow bold">환불</a>

                            </div>
                        </div>
                    </div>

                </div>
                <form action="">
                    <div class="cash-form-area">
                        <select class="cash-selectbox bold" name="orderStatus">
                            <option value="">전체상태</option>
                        </select>
                        <select class="cash-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <div class="cash-deco bold">~</div>
                        <select class="cash-selectbox bold" name="">
                            <option value="">2022-04</option>
                        </select>
                        <input class="cash-searchbtn bold" type="submit" value="검색">
                    </div>
                </form>

                <div class="cash-list-area">

                    ${cList}
                </div>

            </div>


    </main>

</body>
</html>

<script>

    const totalCash = document.querySelector("#cash-total");
    const bonusCash = document.querySelector("#cash-bonus");
    const realCash = document.querySelector("#cash-real");

    function searchRealCash(){
        
    }

    function searchBonusCash(){

    }

    function searchTotalCash(){

    }

    const chargeBtn = document.querySelector("#refund-btn");
    chargeBtn.addEventListener("click", function(){

    let result = document.querySelector("#cash-real");

    // if(result.value == ''){
    //     return null;
    // }

    result = result.innerHTML;

    location.href = "${root}/cash/refund?amount=" + result;
})

</script>