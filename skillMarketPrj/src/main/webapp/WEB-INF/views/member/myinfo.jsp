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
                    <li><a href="${root}/my-info/change-pwd">비밀번호 변경</a></li>
                    <li><a href="${root}/my-info/delete-member">회원 탈퇴</a></li>
                </ul>
            </div>
    
        </nav>
        <form action="${root}/my-info" method="post">
            <div class="myinfo-area">
                <div class="myinfo-title bold">내 정보</div>
                <div class="myinfo-body">
                    <div class="profile-area">
                        <div class="profile-img">
                            <img src="${root}/static/png/예시사진.png" alt="프로필사진">
                        </div>
                        <button class="profile-btn bold" type="button">프로필 변경</button>
                    </div>
                    <div class="myinfo-content">
                        <div class="nick-text">닉네임</div>
                        <div>
                            <input type="text" name="memberNick" placeholder="닉네임을 입력해주세요">
                        <button id="duplicate-check-nick-btn" class="duplication-btn yellow bold" type="button">중복확인</button>
                        </div>
                        <div class="small">닉네임은 1회만 수정 가능합니다</div>
                        <div class="small">닉네임 변경 이력이 있다면 수정하기 기능이 비활성화 됩니다</div>
                        <div class="myinfo-content-text">이메일</div>
                        <div>
                            <input type="text" name="memberEmail" placeholder="이메일을 입력해주세요">
                            <button id="duplicate-check-email-btn" class="duplication-btn yellow bold" type="button">중복확인</button>
                        </div>
                        <div class="myinfo-content-text">전화번호</div>
                        <input type="text" name="memberPhone" placeholder="전화번호를 입력해주세요">
                        <div class="myinfo-content-text">관심분야</div>
                        <select name="memberFavorite">
                            <option value="" selected disabled>관심분야 선택를 선택해주세요</option>
                        </select>
                        <div class="myinfo-submit-btn">
                            <input class="bold" type="submit" value="수정 완료">
                        </div>

                    </div>

                </div>

            </div>

        </form>

    </main>

</body>
</html>

<script>
    // 닉네임 중복 검사

    const nickCheckBtn = document.querySelector("#duplicate-check-nick-btn");
    nickCheckBtn.addEventListener("click", function(){

    const memberNick = document.querySelector("input[name=memberNick]").value;
    console.log(memberNick);
    const NickData = {
        'memberNick' : memberNick
    };

    $.ajax({
        url : '/skillmarket/join/check-nick',
        type : 'POST',
        data : NickData,
        success : function(e){
            if(e == 0){
                alert("사용 불가능한 닉네임입니다.")
            }else{
                alert("사용가능한 닉네임입니다.");
            }

        },
        error : function(e){
            console.log(e);
        }

    })

    })

</script>