<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/changepwd.css">
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
                    <li><a href="${root}/expert-info">전문가 정보</a></li>
                    <li><a class="bold" href="${root}/my-info/change-pwd">비밀번호 변경</a></li>
                    <li><a href="${root}/my-info/delete-member">회원 탈퇴</a></li>
                </ul>
            </div>
    
        </nav>

        <form action="${root}/my-info/change-pwd" method="post" onsubmit="return checkPwd();">
            <div class="changepwd-area">
                <div class="changepwd-title bold">비밀번호 변경</div>
                <div class="changepwd-body">
                        <div class="password-text">현재 비밀번호</div>
                        <input type="password" name="memberCurrentPwd" placeholder="기존 비밀번호를 입력해주세요">
                        <div class="changepwd-input-area">
                            <div class="password-text bold">변경할 비밀번호</div>
                            <div class="password-text bold">한번 더 입력</div>
                            <input type="password" name="changePwd" placeholder="변경할 비밀번호를 입력해주세요.">
                            <input type="password" name="changePwd2" placeholder="변경할 비밀번호를 한번더 입력해주세요.">
                        </div>
                        <div class="expertinfo-submit-btn">
                            <input id="submit-btn" class="bold" type="submit" value="비밀번호 변경">
                        </div>
                </div>

            </div>

        </form>

    </main>

</body>
</html>

<script>

function checkPwd(){
    const currentPwd = document.querySelector("input[name=memberCurrentPwd]").value;
    const changePwd1 = document.querySelector("input[name=changePwd]").value;
    const changePwd2 = document.querySelector("input[name=changePwd2]").value;
    if(currentPwd != '${loginMember.memberPwd}'){
        alert("현재 비밀번호가 맞지 않습니다.")
        return false;
    }else if(changePwd1 == '${loginMember.memberPwd}'){
        alert("변경할 비밀번호가 현재 비밀번호와 같습니다.");
        return false;
        return true;
    }else if(changePwd1 != changePwd2){
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }else{
        return true;
    }

}


</script>