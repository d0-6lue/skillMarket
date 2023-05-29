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
                <div class="salelist-title1 bold">판매 관리</div>
                <div class="salelist-showboard-area">
                    <div class="salelist-showboard">
                        <div class="salelist-textbox">
                            <div class="salelist-showboard-text bold">진행중</div>
                            <div class="salelist-showboard-num bold">${vo.progress}</div>
                        </div>
                    </div>
                    <div class="salelist-showboard">
                        <div class="salelist-textbox">
                            <div class="salelist-showboard-text bold">거래완료</div>
                            <div class="salelist-showboard-num bold">${vo.complete}</div>
                        </div>
                    </div>
                    <div class="salelist-showboard">
                        <div class="salelist-textbox">
                            <div class="salelist-evaluation">
                                <div class="salelist-smalltext">작성 가능한 평가</div>
                                <div class="salelist-smalltext">${vo.complete}</div>
                            </div>
                            <div class="salelist-cancel">
                                <div class="salelist-smalltext">주문 취소</div>
                                <div class="salelist-smalltext">${vo.cancel}</div>
                            </div>
                        </div>
                    </div>

                </div>
                <form action="${root}/expert/sale-list" method="post">
                    <div class="salelist-form-area">
                        <select class="salelist-selectbox bold" name="saleStatus">
                            <option value="" disabled selected hidden>전체상태</option>
                            <option value="진행중">진행중</option>
                            <option value="완료">완료</option>
                            <option value="중지">중지</option>
                            <option value="취소">취소</option>
                        </select>
                        <select id="salelist-date1" class="salelist-selectbox bold" name="saleDate1">
                            <option value="2023-06">2023-06</option>
                            <option value="2023-05">2023-05</option>
                            <option value="2023-04">2023-04</option>
                            <option value="2023-03">2023-03</option>
                            <option value="2023-02">2023-02</option>
                            <option value="2023-01">2023-01</option>
                            <option value="2022-12">2022-12</option>
                            <option value="2022-11">2022-11</option>
                            <option value="2022-10">2022-10</option>
                            <option value="2022-09">2022-09</option>
                            <option value="2022-08">2022-08</option>
                            <option value="2022-07">2022-07</option>
                            <option value="2022-06">2022-06</option>
                            <option value="2022-05">2022-05</option>
                            <option value="2022-04">2022-04</option>
                            <option value="2022-03">2022-03</option>
                            <option value="2022-02">2022-02</option>
                            <option value="2022-01">2022-01</option>
                        </select>
                        <div class="salelist-deco bold">~</div>
                        <select id="salelist-date2" class="salelist-selectbox bold" name="saleDate2">
                            <option value="2023-06">2023-06</option>
                            <option value="2023-05">2023-05</option>
                            <option value="2023-04">2023-04</option>
                            <option value="2023-03">2023-03</option>
                            <option value="2023-02">2023-02</option>
                            <option value="2023-01">2023-01</option>
                            <option value="2022-12">2022-12</option>
                            <option value="2022-11">2022-11</option>
                            <option value="2022-10">2022-10</option>
                            <option value="2022-09">2022-09</option>
                            <option value="2022-08">2022-08</option>
                            <option value="2022-07">2022-07</option>
                            <option value="2022-06">2022-06</option>
                            <option value="2022-05">2022-05</option>
                            <option value="2022-04">2022-04</option>
                            <option value="2022-03">2022-03</option>
                            <option value="2022-02">2022-02</option>
                            <option value="2022-01">2022-01</option>
                        </select>
                        <input type="text" name="saleSearch">
                        <input class="salelist-searchbtn bold" type="submit" value="검색">
                    </div>
                </form>

                <div class="salelist-list-area">

                    <table class="salelist-list">
                        <thead>
                            <tr>
                                <td>상태</td>
                                <td>제목</td>
                                <td>금액</td>
                                <td>날짜</td>
                            </tr>

                        </thead>
                        <tbody>
                            <c:forEach items="${saleList}" var="vo">
                                <tr onclick="location.href='${root}/order/detail?no=${vo.quotationNo}'">
                                    <td class="salelist-list-box${vo.quotationStatusNo}">${vo.quotationStatusName}</td>
                                    <td class="salelist-title">${vo.estimateTitle}</td>
                                    <td class="table-cell">${vo.quotationPrice}원</td>
                                    <td class="table-cell">${vo.quotationEnrollDate}</td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div>


    </main>

</body>
</html>