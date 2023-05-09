<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/expert/expertinfo.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>


    
    <main>
        
        <nav>
            <div class="nav-myinfo-area">

                <div class="nav-myinfo-title bold">
                    <div>프로필 정보</div>
                </div>
                <ul>
                    <li><a href="${root}/my-info">내 정보</a></li>
                    <li><a class="bold" href="${root}/expert-info">전문가 정보</a></li>
                    <li><a href="${root}/my-info/change-pwd">비밀번호 변경</a></li>
                    <li><a href="${root}/my-info/delete-member">회원 탈퇴</a></li>
                </ul>
            </div>
    
        </nav>
        <form action="${root}/expert-info" method="post">
            <div class="expertinfo-area">
                <div class="expertinfo-title bold">전문가 정보</div>
                <div class="expertinfo-body">
                    <div class="profile-area">
                        <div class="profile-img">
                            <img src="${root}/static/png/예시사진.png" alt="프로필사진">
                        </div>
                        <button class="profile-btn bold" type="button">프로필 변경</button>
                    </div>
                    <div class="expertinfo-content">
                        <div class="nick-text">닉네임</div>
                        <input type="text" name="memberNick" placeholder="닉네임을 입력해주세요">
                        <div class="small">닉네임은 1회만 수정 가능합니다</div>
                        <div class="small">닉네임 변경 이력이 있다면 수정하기 기능이 비활성화 됩니다</div>
                        <div class="expertinfo-content-text">자기소개</div>
                        <textarea name="expertIntroduction" placeholder="자기소개"></textarea>
                        <div class="expertinfo-content-text">주소</div>
                        <input type="text" name="expertAddress" placeholder="주소를 입력해주세요">
                        <div class="expertinfo-content-text">연락가능시간</div>
                        <input type="text" name="expertTime" placeholder="연락가능시간를 입력해주세요">
                        <div class="expertinfo-content-text">전문분야</div>
                        <select name="expertField">
                            <option value="" selected disabled>전문 분야 선택를 선택해주세요</option>
                        </select>
                        <div class="expertinfo-content-text">경력사항</div>
                        <div class="expertinfo-career-area">
                            <div class="career-content-text">총기간</div>
                            <select name="expertCareerDuring">
                                <option value="" selected disabled>선택해주세요</option>
                            </select>
                            <div class="career-content-text">경력사항</div>
                            <button class="career-btn" type="button"></button>
                        </div>
                        <div class="expertinfo-content-text">학력</div>
                        <div class="education-area">
                            <div class="career-content-text">학력·전공</div>
                            <button class="education-btn" type="button"></button>
                        </div>
                        <div class="expertinfo-submit-btn">
                            <input class="bold" type="submit" value="수정 완료">
                        </div>

                    </div>

                </div>

            </div>

        </form>

    </main>

</body>
</html>