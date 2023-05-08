<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="${root}/static/css/csc/inquiry.css">
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
        </div>

    </div>
    
    <div class="header-search">
        <div class="bold">고객센터 > 문의하기</div>
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

        <div class="inquiry-area">
            <div class="inquiry-title bold">문의 등록</div>
            <div class="inquiry-form-area">
                <form action="${root}/inquiry" method="post">
                    <div class="inquiry-form-text">문의 유형</div>
                    <select name="inuqiryCategroy" class="inquiry-categroy">
                        <option value="">카테고리1</option>
                    </select>
                    <div class="inquiry-form-text">제목</div>
                    <input type="text" class="inquiry-title-box" name="inquiryTitle" placeholder="제목을 입력해주세요">
                    <div class="inquiry-form-text">내용</div>
                    <textarea name="inquiryContent" class="inquiry-content-box" placeholder="내용을 입력해주세요"></textarea>
                    <br>
                    <div class="inquiry-submit-box"><input class="inquiry-submit-btn bold" type="submit" value="문의하기"></div>
    
                </form>

            </div>
        </div>

    </main>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>