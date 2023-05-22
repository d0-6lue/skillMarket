<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/expert/grade.css">
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
                    <a class="profile-btn bold" href="${root}/customer/request-list">의로인으로 변환</a>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/expert/request-list">제안서 관리</a></li>
                    <li><a href="${root}/expert/sale-list">판매 관리</a></li>
                    <li><a href="${root}/expert/estimate-mgmt">견적서 관리</a></li>
                    <li><a href="${root}/expert/QNA-mgmt">전문가 Q&A 관리</a></li>
                    <li><a class="bold" href="${root}/expert/grade">스킬 등급</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="grade-area">
                <div class="grade-title bold">스킬 등급</div>
                <div class="grade-title-sub-area">
                    <span class="grade-title-sub bold">null</span>
                    <span class="grade-title-sub">님의 전문가 등급은</span>
                    <span class="grade-title-sub bold">null</span>
                    <span class="grade-title-sub">입니다.</span>

                </div>
                
                <div class="grade-current-title bold">등급 현황</div>
                <div class="grade-current-area">
                    <div class="grade-current-sale">
                        <div class="bold">누적 판매량</div>
                        <div class="grade-current-num bold">0</div>
                    </div>
                    <div class="grade-current-money">
                        <div class="bold">누적 판매 금액</div>
                        <div class="grade-current-num bold">0</div>

                    </div>
                    <div class="grade-current-grade">
                        <img class="grade-current-gradeimg" src="${root}/static/svg/0_skill.svg" alt="스킬 등급">
                    </div>

                </div>

            </div>


    </main>

</body>
</html>