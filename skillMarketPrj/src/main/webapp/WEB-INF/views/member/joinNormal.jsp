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
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer src="${root}/static/js/member/joinNormal.js"></script>
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

                <form action="${root}/join/normal" method="post" class="join-form-area" onsubmit="return submitCheck()">
                    
                    <div class="join-form-text">이름</div>
                    <input type="text" class="join-form-input" name="memberName" placeholder="이름을 입력하세요.">

                    <div class="join-form-text">아이디</div>
                    <div>
                        <input type="text" class="join-form-input3" name="memberId" placeholder="아이디를 입력하세요."> 
                        <button id="duplicate-check-btn" class="duplication-btn yellow bold" type="button">중복확인</button>
                        <div id="check-id-availability"></div>
                    </div>

                    <div class="join-form-text">비밀번호</div>
                        <input type="password" class="join-form-input2" name="memberPwd" placeholder="비밀번호를 입력하세요.(8~20자리)">
                        <div id="check-pwd1-availability"></div>
                        <input type="password" class="join-form-input" name="memberPwd2" placeholder="비밀번호를 한번 더 입력하세요.">
                        <div id="check-pwd2-availability"></div>

                    <div class="join-form-text">닉네임</div>
                    <div>
                        <input type="text" class="join-form-input3" name="memberNick" placeholder="닉네임을 입력하세요.">
                        <button id="duplicate-check-nick-btn" class="duplication-btn yellow bold" type="button">중복확인</button>
                        <div id="check-nick-availability"></div>
                    </div>

                    <div class="join-form-text">이메일</div>
                    <div>
                        <input type="email" class="join-form-input3" name="memberEmail" placeholder="이메일를 입력하세요.">
                        <button id="duplicate-check-email-btn" class="duplication-btn yellow bold" type="button">중복확인</button>
                        <div id="check-email-availability"></div>
                    </div>

                    <div class="join-form-text">주소</div>
                    <input type="text" class="join-form-input"  id="address_kakao" name="memberAddress" placeholder="주소를 입력해주세요.">

                    <div class="join-form-text">상세 주소</div>
                    <input type="text" class="join-form-input" name="memberAddress2" placeholder="상세 주소를 입력해주세요.">
                    
                    <div class="join-form-text">전화번호</div>
                    <input type="tel" class="join-form-input" name="memberPhone" placeholder="전화번호를 입력하세요.">
                    <div id="check-phone-availability"></div>

                    <div class="join-form-text">계좌은행</div>
                    <input type="text" class="join-form-input" name="memberBank" placeholder="계좌은행을 입력하세요.">

                    <div class="join-form-text">계좌번호</div>
                    <input type="text" class="join-form-input" name="memberAccount" placeholder="계좌번호을 입력하세요.">
                    
                    <div class="join-form-text">관심분야</div>
                    <select class="join-form-input" name="memberFavorite">
						<c:forEach items="${categoryList}" var="category">
                            <option value="${category.estimateCatNo}">${category.estimateCatName}</option>
						
						</c:forEach>
                    </select>  
                    
                    <input type="submit" value="가입 완료" class="bold">

                </form>
                
            </div>

        </div>

    </div>

    <br><br><br><br><br><br><br><br><br>
</body>


</html>

<script>
    window.onload = function(){
        document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
            //카카오 지도 발생
            new daum.Postcode({
                oncomplete: function(data) { //선택시 입력값 세팅
                    document.getElementById("address_kakao").value = data.address; // 주소 넣기
                    document.querySelector("input[name=memberAddress2]").focus(); //상세입력 
                }
            }).open();
        });
    }
    </script>

