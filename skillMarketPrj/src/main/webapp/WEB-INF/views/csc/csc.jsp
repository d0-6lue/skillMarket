<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="${root}/static/css/csc/csc.css">

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
                        <li><a href="${root}/inquiry">문의하기</a></li>
                        <li><a href="">로그인</a></li>
                    </ul>
                </div>
    
            </div>

            <div class="header-search">
                <div class="header-search-area">
                    <input type="text" placeholder="검색">
                    <button>
                        <span class="material-symbols-outlined">
                            search
                            </span>
                    </button>
                </div>
            </div>
            
            
        </div>

    </div>

    <main>
        <div class="main-area">
            <div class="notice-area">
                <div class="bold notice-title">공지사항</div>
                <div class="notice-go"><a href="${root}/notice">더보기</a></div>
                <table class="csc-table">
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                </table>
            </div>

            <div class="faq-area">
                <div class="faq-title bold">FAQ</div>
                <div class="faq-go"><a href="${root}/faq">더보기</a></div>
                <table class="csc-table">
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                    <tr>
                        <td>공지사항 제목입니다</td>
                    </tr>
                </table>
            </div>

        </div>
        

    </main>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>