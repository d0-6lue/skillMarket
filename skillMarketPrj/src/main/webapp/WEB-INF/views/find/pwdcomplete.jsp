<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/find/idcomplete.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    
    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="${root}/static/svg/메인로고.svg" alt="로고이미지">
                    </a>
                </div>
    
            </div>
            
        </div>


    </div>

    <main>
            <div class="find-body">
                <div class="find-title bold">비밀번호 확인</div>
                
                <div class="find-content">해당 비밀번호는</div>
                <div class="find-content2">${memberPwd}입니다</div>
                <div class="btn-area">
                    <button class="btn-detail bold" onclick="location.href='${root}/home'">홈으로 가기</button>

                </div>

                    
            </div>
    </main>

</body>
</html>