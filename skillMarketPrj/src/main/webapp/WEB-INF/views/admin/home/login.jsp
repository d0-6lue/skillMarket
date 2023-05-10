<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
</head>
<body>
    
    <div id="wrap">
        <div id="logo_area">
            <img src="${root}/static/svg/관리자 페이지 로고.svg" alt="로고">
        </div>

        <div id="login_area">
            <h1>관리자 로그인</h1>
            <form action="${root}/admin/login" method="POST">
                <div>
                    <input class="admin_login admin_login_input" type="text" name="adminId" placeholder="아이디를 입력하세요">
                    <input class="admin_login admin_login_input" type="password" name="adminPwd" placeholder="비밀번호를 입력하세요">
                </div>
                <input class="admin_login submit" type="submit" value="로그인" >
            </form>
            

        </div>
    </div>
    

</body>
</html>
<link rel="stylesheet" href="${root}/static/css/admin/login.css">
<script src="${root}/static/js/admin/login.js"></script>
