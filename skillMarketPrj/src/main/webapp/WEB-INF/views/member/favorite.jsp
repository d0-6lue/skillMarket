<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/favorite.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        
        <nav>
            <div class="nav-myinfo-area">

                <div class="nav-myinfo-title bold">
                    <div>찜목록</div>
                </div>
                <div class="bold">서비스</div>
                <ul>
                    <li><button>전체(0)</button></li>
                    <li><button>디자인(0)</button></li>
                    <li><button>디자인(0)</button></li>
                </ul>
            </div>
    
        </nav>

        <div class="favorite-area">

            <div class="ex">얖</div>
            <div class="ex">얖</div>
            <div class="ex">얖</div>
            <div class="ex">얖</div>
            <div class="ex">얖</div>
            <div class="ex">얖</div>
            <div class="ex">얖</div>
            <div class="ex">얖</div>
            <div class="ex">얖</div>

        </div>

        

    </main>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>