<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="../static/css/expert/register.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>


    
    <main>
        
        <nav>
    
            <div class="nav-expert-area bold">
                <div>전문가 등록</div>
            </div>
    
        </nav>
        <form action="${root}/expert/register" method="post">
            <div class="register-area">
                <div class="register-title bold">전문가 등록</div>
                <div class="register-body">
                    <div class="profile-img"><img src="" alt="프로필사진"></div>
                    <div class="register-content">
                        <div class="nick-text">닉네임</div>
                        <input type="text" name="memberNick" placeholder="닉네임을 입력해주세요">
                        <div class="small">닉네임은 1회만 수정 가능합니다</div>
                        <div class="small">닉네임 변경 이력이 있다면 수정하기 기능이 비활성화 됩니다</div>
                        <div class="register-content-text">자기소개</div>
                        <textarea name="expertIntroduction" placeholder="자기소개"></textarea>
                        <div class="register-content-text">주소</div>
                        <input type="text" name="expertAddress" placeholder="주소를 입력해주세요">
                        <div class="register-content-text">연락가능시간</div>
                        <input type="text" name="expertTime" placeholder="연락가능시간를 입력해주세요">
                        <div class="register-content-text">전문분야</div>
                        <select name="expertField">
                            <option value="" selected disabled>전문 분야 선택를 선택해주세요</option>
                        </select>
                        <div class="register-content-text">경력사항</div>
                        <div class="register-career-area">
                            <div class="career-content-text">총기간</div>
                            <select name="expertCareerDuring">
                                <option value="" selected disabled>선택해주세요</option>
                            </select>
                            <div class="career-content-text">경력사항</div>
                            <button class="career-btn" type="button"></button>
                        </div>
                        <div class="register-content-text">학력</div>
                        <div class="education-area">
                            <div class="career-content-text">학력·전공</div>
                            <button class="education-btn" type="button"></button>
                        </div>
                        <div class="register-submit-btn">
                            <input class="bold" type="submit" value="전문가 등록">
                        </div>

                    </div>

                </div>

            </div>

        </form>

    </main>

</body>
</html>