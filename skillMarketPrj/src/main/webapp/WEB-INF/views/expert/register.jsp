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
                    <div class="register-content">
                        <div class="register-content-text2">자기소개</div>
                        <textarea name="expertIntroduction" placeholder="자기소개"></textarea>
                        <div class="register-content-text">연락가능시간</div>
                        <input type="text" name="expertTime" placeholder="연락가능시간를 입력해주세요">
                        <div class="register-content-text">전문분야</div>
                        <select name="expertField">
                            <option value="" selected disabled>전문 분야 선택를 선택해주세요</option>
                            <c:forEach items="${categoryList}" var="list">
                            	<option value="${list.estimateCatNo }">${list.estimateCatName }</option>
                            </c:forEach>
                        </select>
                        <div class="register-content-text">경력사항</div>
                        <div class="register-career-area">
                            <div class="career-content-text">총기간</div>
                            <select name="expertCareerDuring">
                                <option value="" selected disabled>선택해주세요</option>
                            </select>
                            <div class="career-content-text">경력사항</div>
                            <button id="career-modal-add-btn" class="career-btn" type="button">경력 추가하기</button>
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

    <div class="c-modal" id="c-modal">
        <div class="c-modal-body">
            <div class="c-m-head">
                <div class="c-close-btn" id="c-close-btn">
                    <span class="material-symbols-outlined">close</span>
                </div>
                <div class="c-m-main">
                    <div class="c-m-head-title bold">경력사항</div>
                    <div>
                        <div>회사명</div>
                        <input type="text" >
                    </div>
                    <div>
                        <div>근무부서</div>
                        <input type="text">
                    </div>
                    <div>
                        <div>직위</div>
                        <select name="" id="">
                            <option value="사원">사원</option>
                            <option value="대리">대리</option>
                            <option value="과장">과장</option>
                            <option value="차장">차장</option>
                            <option value="부장">부장</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div>
                        <div>근무지역</div>
                        <select name="" id="">
                            <option value=""></option>
                        </select>
                    </div>
                    <div>

                    </div>
                </div>
                    
            </div>
            
            
        </div>
    </div>

</body>
</html>

<script>

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

// 경력사항 모달

const openBtn = document.querySelector("#career-modal-add-btn");
const modal = document.querySelector("#c-modal");
const closeBtn = document.querySelector("#c-close-btn");
if(openBtn != null){
    openBtn.addEventListener("click", function(){
        modal.classList.add('show');
        document.body.style.overflow = 'hidden';
    });
}

closeBtn.addEventListener("click", function(){
    modal.classList.remove('show');
    document.body.style.removeProperty('overflow');
})


</script>