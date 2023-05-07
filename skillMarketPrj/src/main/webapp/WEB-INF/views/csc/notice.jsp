<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="${root}/static/css/csc/notice.css">
</head>
<body>

    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="static/svg/고객센터로고.svg" alt="로고이미지">
                    </a>
                    <a href="${root}/csc" class="header-csc bold">고객센터</a>
                </div>
                
                <div class="header-menu">
                    <ul>
                        <li><a href="">문의하기</a></li>
                        <li><a href="">로그인</a></li>
                    </ul>
                </div>
    
            </div>
        </div>

    </div>
            <div class="header-search">
                <div class="bold">고객센터 > 공지사항</div>
                <div class="header-search-area">
                    <input type="text" placeholder="검색">
                    <button>
                        <span class="material-symbols-outlined">
                            search
                            </span>
                    </button>
                </div>
            </div>
            
            


    <main>

        <div class="notice-area">
            <div class="notice-title bold">공지사항</div>

            <ul class="notice-ul">
                <li>[공지]공지사항 제목입니다.</li>
                <li>[공지]공지사항 제목입니다.</li>
                <li>[공지]공지사항 제목입니다.</li>
                <li>[공지]공지사항 제목입니다.</li>
                <li>[공지]공지사항 제목입니다.</li>
                <li>[공지]공지사항 제목입니다.</li>
            </ul>

            <button class="notice-btn">더보기</button>

        </div>

    </main>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>