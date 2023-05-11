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
                        <img src="${root}/static/png/예시사진.png" alt="프로필사진">
                    </div>
                    <div class="profile-nick bold">스킬쟁이123</div>
                    <a class="profile-btn bold" href="${root}/customer/request-list">의로인으로 변환</a>
                </div>
                <div class="nav-customer-title-area bold">
                    <div>마이페이지</div>
                </div>
                <ul>
                    <li><a href="${root}/expert/request-list">제안서 관리</a></li>
                    <li><a href="${root}/expert/sale-list">판매 관리</a></li>
                    <li><a href="${root}/expert/estimate-mgmt">견적서 관리</a></li>
                    <li><a class="bold" href="${root}/expert/QAN-mgmt">전문가 Q&A 관리</a></li>
                    <li><a href="${root}/expert/grade">스킬 등급</a></li>
                </ul>
            </div>
    
        </nav>

            <div class="qna-area">
                <div class="qna-title bold">전문가 Q&A 관리</div>
                

                <div class="qna-list-area">


                </div>

            </div>


    </main>

</body>
</html>