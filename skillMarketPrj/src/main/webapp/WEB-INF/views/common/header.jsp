<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/common/header.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

	<div class="header-yellowarea"></div>
    
    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="static/svg/메인로고.svg" alt="로고이미지">
                    </a>
                </div>
                <div class="header-search">
                        <input type="text" placeholder="서비스를 입력해주세요">
                        <button>
                            <span class="material-symbols-outlined">
                                search
                                </span>
                        </button>
                </div>
                <div class="header-menu">
                    <ul>
                        <li class="bold"><a href="">전문가 등록</a></li>
                        <li class="bold"><a href="">로그인</a></li>
                        <li class="bold"><a href="${root}/join">회원가입</a></li>
    
                    </ul>
                </div>
    
            </div>
            <div class="nav-area">
                <div class="bold" id="category-area">
                    <span class="material-symbols-outlined">menu</span>
                    <span class="category-spl bold">전체 카테고리</span>
                </div>
                <div>
                    <a class="bold" href="">인기견적서</a>
                </div>
                <div>
                    <a class="bold" href="${root}/community/all">커뮤니티</a>
                </div>
            </div>
            
        </div>


    </div>

        


</body>
</html>