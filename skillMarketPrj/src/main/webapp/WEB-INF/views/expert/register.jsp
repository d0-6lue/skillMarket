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
                        		<img alt="프로필사진" src="${root}/static/img/profile/기본프로필.svg">
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