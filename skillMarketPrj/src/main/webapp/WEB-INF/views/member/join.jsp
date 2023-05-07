<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="${root}/static/css/member/join.css">
</head>
<body>

    <div class="wrap">

        <div class="logo-icon">

            <a href="${root}/home" class="logo-iconimg">
                <img src="static/svg/메인로고.svg" alt="로고이미지">

            </a>

        </div>

        
        <div class="choice-join">
            <div class="choice-join-area">

                <div class="choice-join-title bold">회원가입</div>

                
            </div>
            <div class="join-normal">
                <div class="bold">서비스를 의뢰하고 싶다면?</div>
                    <button class="join-normal-box" onclick="location.href='/skillmarket/join/normal'">
                        <span class="material-symbols-outlined join-btn-color">
                            radio_button_unchecked
                            </span>
                        <div class="join-box-text">일반 회원 가입</div>

                    </button>
            </div>
            <div class="join-expert">
                <div class="bold">내 전문성을 판매하고 싶다면?</div>
                <button class="join-expert-box">
                    <span class="material-symbols-outlined join-btn-color">
                        radio_button_unchecked
                        </span>
                    <div class="join-box-text">전문가로 가입</div>

                </button>
            </div>

        </div>

    </div>

</body>
</html>