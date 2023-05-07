<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="${root}/static/css/member/joinnormal.css">
</head>
<body>

    <div class="wrap">

        <div class="logo-icon">

            <a href="${root}/home" class="logo-iconimg">
                <img src="../static/svg/메인로고.svg" alt="로고이미지">
                

            </a>

        </div>

        <div class="normal-join-area">

            <div class="join-area">

                <div class="join-title bold">회원가입</div>

                <form action="${root}/join/normal" method="post" class="join-form-area">
                    
                    <div class="join-form-text">이름</div>
                    <input type="text" class="join-form-input" name="memberName" placeholder="이름을 입력하세요.">

                    <div class="join-form-text">아이디</div>
                    <input type="text" class="join-form-input" name="memberId" placeholder="아이디를 입력하세요.">

                    <div class="join-form-text">비밀번호</div>
                    <input type="password" class="join-form-input2" name="memberPwd" placeholder="비밀번호를 입력하세요.(8~20자리)">
                    <input type="password" class="join-form-input" name="memberPwd2" placeholder="비밀번호를 한번 더 입력하세요.">

                    <div class="join-form-text">닉네임</div>
                    <input type="text" class="join-form-input" name="memberNick" placeholder="닉네임을 입력하세요.">

                    <div class="join-form-text">전화번호</div>
                    <input type="number" class="join-form-input" name="memberPhone" placeholder="전화번호를 입력하세요.">

                    <div class="join-form-text">이메일</div>
                    <input type="email" class="join-form-input" name="memberEmail" placeholder="이메일를 입력하세요.">

                    <div class="join-form-text">관심분야</div>
                    <select class="join-form-input" name="memberFavorite">

                        <option value="">IT/프로그래밍</option>
                    </select>                
                    
                    <input type="submit" value="가입 완료" class="bold">

                </form>
                
            </div>

        </div>

    </div>

    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

</body>
</html>

