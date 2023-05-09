<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/myinfo.css">
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
                    <li><a class="bold" href="${root}/my-info">내 정보</a></li>
                    <li><a href="${root}/expert-info">전문가 정보</a></li>
                    <li><a href="${root}/my-info/chang-pwd">비밀번호 변경</a></li>
                    <li><a href="${root}/my-info/delete-member">회원 탈퇴</a></li>
                </ul>
            </div>
    
        </nav>
        <form action="${root}/my-info" method="post">
            <div class="register-area">
                <div class="register-title bold">내 정보</div>
                <div class="register-body">
                    <div class="profile-area">
                        <div class="profile-img">
                            <img src="" alt="프로필사진">
                        </div>
                        <button type="button">프로필 변경</button>
                    </div>
                    <div class="register-content">
                        <div class="nick-text">닉네임</div>
                        <input type="text" name="memberNick" placeholder="닉네임을 입력해주세요">
                        <div class="small">닉네임은 1회만 수정 가능합니다</div>
                        <div class="small">닉네임 변경 이력이 있다면 수정하기 기능이 비활성화 됩니다</div>
                        <div class="register-content-text">연락가능시간</div>
                        <input type="text" name="expertTime" placeholder="연락가능시간를 입력해주세요">
                        <div class="register-content-text">전문분야</div>
                        <select name="expertField">
                            <option value="" selected disabled>전문 분야 선택를 선택해주세요</option>
                        </select>
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