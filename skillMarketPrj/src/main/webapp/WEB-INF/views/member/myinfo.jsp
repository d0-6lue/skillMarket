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
    <script defer src="${root}/static/js/member/myinfo.js"></script>
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
        <form action="${root}/my-info" method="post" enctype="multipart/form-data">
            <div class="myinfo-area">
                <div class="myinfo-title bold">내 정보</div>
                <div class="myinfo-body">
                    <div class="profile-area">
                        <div class="profile-img">
                        	<c:if test="${empty loginMember.memberProfilePhoto}">
	                            <img src="${root}/static/svg/기본프로필.svg" alt="프로필사진">
                        	</c:if>
                        	<c:if test="${not empty loginMember.memberProfilePhoto}">
                        		<img alt="프로필사진" src="${root}/static/img/profile/${loginMember.memberProfilePhoto}">
                        	</c:if>
                        </div>
                        <label for="profile-btn" class="profile-btn bold">프로필 등록</label>
                        <input id="profile-btn" type="file" name="f" hidden></input>
                    </div>
                    <div class="myinfo-content">
                        <div class="nick-text">닉네임</div>
                        <div>
                            <input type="text" name="memberNick" placeholder="닉네임을 입력해주세요" value="${loginMember.memberNick}">
                        <button id="duplicate-check-nick-btn" class="duplication-btn yellow bold" type="button">중복확인</button>
                        </div>
                        <div id="check-nick-availability"></div>
                        <div class="small">닉네임은 1회만 수정 가능합니다</div>
                        <div class="small">닉네임 변경 이력이 있다면 수정하기 기능이 비활성화 됩니다</div>
                        <div class="myinfo-content-text">이메일</div>
                        <div>
                            <input type="text" name="memberEmail" placeholder="이메일을 입력해주세요" value="${loginMember.memberEmail}">
                            <button id="duplicate-check-email-btn" class="duplication-btn yellow bold" type="button">중복확인</button>
                        </div>
                        <div id="check-email-availability"></div>
                        <div class="myinfo-content-text">전화번호</div>
                        <input type="text" name="memberPhone" placeholder="전화번호를 입력해주세요" value="${loginMember.memberPhone}">
                        <div id="check-phone-availability"></div>
                        <div class="myinfo-content-text">관심분야</div>
                        <select name="memberFavorite">
                            <c:forEach items="${categoryList}" var="list">
                            	<option value="${list.estimateCatNo}">${list.estimateCatName }</option>
                            </c:forEach>
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
var test = '${loginMember.memberNick}';
console.log(test);
if(memberNick == '${loginMember.memberNick}'){
    return ;
}
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

});

// 이메일 중복 검사
const emailCheckBtn = document.querySelector("#duplicate-check-email-btn");
emailCheckBtn.addEventListener("click", function(){

    const memberEmail = document.querySelector("input[name=memberEmail]").value;
    if(memberEmail == `${loginMember.memberEmail}`){
        return ;
    }
    const EmailData = {
        'memberEmail' : memberEmail
    };

    $.ajax({
        url : '/skillmarket/join/check-email',
        type : 'POST',
        data : EmailData,
        success : function(e){
            if(e == 0){
                alert("중복된 이메일입니다.")
            }else{
                alert("사용가능한 이메일입니다.");
            }

        },
        error : function(e){
            console.log(e);
        }

    })

});

const optionList = document.querySelectorAll("option");
function searchCategory(){
    for(let i = 0; i < optionList.length; i++){
        if(optionList[i].value == '${loginMember.memberInterst}'){
            optionList[i].selected = true;
        }
    }
}

searchCategory();

//미리보기
const fileTag = document.querySelector("input[type=file]");
const previewArea = document.querySelector(".profile-img");


fileTag.onchange = function(e){
    
    if(fileTag.files.length == 0){		//취소누른상태
        return;
    }

    for(let i = 0 ; i < fileTag.files.length; i++){
        const fr = new FileReader();
        fr.readAsDataURL(fileTag.files[i]);

        fr.onload = function(e){
            previewArea.innerHTML = '';
            const imgTag = document.createElement('img');
            imgTag.src = e.target.result;
            imgTag.alt = "미리보기이미지사진";
            previewArea.appendChild(imgTag);
        };
    }


};


</script>