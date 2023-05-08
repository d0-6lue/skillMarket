<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/find/findid.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    
    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="static/svg/메인로고.svg" alt="로고이미지">
                    </a>
                </div>
    
            </div>
            
        </div>


    </div>

    <main>
        <form action="/skillmarket/forgot-id" method="post">
            <div class="find-body">
                <div class="find-title bold">아이디 / 비밀번호 찾기</div>
                <div class="choice-find-btn">
                    <a href="${root}/forgot-id" class="findid-btn bold"><div>아이디</div></a>
                    <a href="${root}/forgot-pwd" class="findpwd-btn bold"><div>비밀번호</div></a>
                </div>
                
                <div>
                    <div class="findid-title bold">아이디 찾기</div>
                    <div>이메일 주소를 입력해주시면 해당 이메일로 아이디를 보내드립니다.</div>
                    <div class="findid-email-title bold">이메일 주소</div>
                    <input type="email" name="findEmail" class="findid-email" placeholder="이메일을 입력해주세요">
                    <br>
                    <div class="email-btn">
                        <input type="submit" value="이메일 발송" class="email-send-btn bold">
                    </div>
                </div>
                    
                    
                    
            </div>
        </form>
    </main>

</body>
</html>