<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/member/deletemember.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>

    <main>
        
        <nav>
            <div class="nav-myinfo-area">

                <div class="nav-myinfo-title bold">
                    <div>프로필 정보</div>
                </div>
                <ul>
                    <li><a href="${root}/my-info">내 정보</a></li>
                    <li><a href="${root}/expert-info">전문가 정보</a></li>
                    <li><a href="${root}/my-info/change-pwd">비밀번호 변경</a></li>
                    <li><a class="bold" href="${root}/my-info/delete-member">회원 탈퇴</a></li>
                </ul>
            </div>
    
        </nav>

        <form action="${root}/my-info/delete-member" method="post">
            <div class="deletemember-area">
                <div class="deletemember-title bold">회원 탈퇴</div>
                <div class="deletemember-body">
                        <div class="password-text">비밀번호</div>
                            <input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요">
                        <div class="caution-area">
                            <div class="caution-title bold">주의 사항</div>
                            <ul>
                                <li>회원 탈퇴 시 개인정보나 작성한 게시물, 문서, 사진 등이 모두 삭제될 수 있습니다.</li>
                                <li>서비스 이용 중 발생한 모든 비용이 정산되어야 회원 탈퇴가 가능합니다.</li>
                                <li>회원 탈퇴 후에도, 일부 정보는 서비스 제공사에서 관리되어 저장될 수 있습니다. </li>
                            </ul>
                        </div>
                        <div class="deletemember-submit-btn">
                            <div class="caution-btn">
                                <span class="material-symbols-outlined">radio_button_unchecked</span>
                                <div class="caution-btn-text">주의 사항을 모두 확인했습니다.</div>
                            </div>
                            <input class="bold" type="submit" disabled value="회원 탈퇴">
                        </div>
                </div>

            </div>

        </form>

    </main>

</body>
</html>