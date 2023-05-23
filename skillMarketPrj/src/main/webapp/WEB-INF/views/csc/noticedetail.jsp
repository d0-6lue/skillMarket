<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<link rel="stylesheet" href="${root}/static/css/csc/noticedetail.css">
</head>
<body>

    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="${root}/static/svg/고객센터로고.svg" alt="로고이미지">
                    </a>
                    <a href="${root}/csc" class="header-csc bold">고객센터</a>
                </div>
                
                <div class="header-menu">
                    <ul>
                        <li><a href="${root}/inquiry">문의하기</a></li>
                        <c:if test="${empty loginMember}">
                        	<li><a id="login-modal-add-btn">로그인</a></li>                        
                        </c:if>
                        <c:if test="${not empty loginMember}">
                        	<li><a href="${root}/logout">로그아웃</a></li>
                        </c:if>
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
            <div class="notice-title bold">${noticeVo.notiTitle}</div>

            <div class="notice-cotent">${noticeVo.notiContent}</div>
        </div>

    </main>

    <div class="modal" id="modal">
        <div class="modal-body">
            <div class="m-head">
                <div class="m-img">
                    <img src="${root}/static/svg/로그인사진.svg" alt="로그인사진">
                </div>
                <form action="${root}/login" method="post">
                <div class="m-main">
                    <div class="close-btn" id="close-btn">
                        <span class="material-symbols-outlined">close</span>
                    </div>
                    <div class="login-title bold">로그인</div>
                    
                        <input type="text" name="memberId" placeholder="아이디를 입력해주세요">
                        <input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요">
                        <br>
                        <input type="hidden" name="currentUrl" value="">
                        <input type="submit" value="로그인" class="login-submit-btn">
                        <div class="idpwd-search bold">
                            <a href="${root}/forgot-id">아이디/비밀번호 찾기</a>
                        </div>
                        <button class="join-move-btn" type="button" onclick="location.href='${root}/join'">회원가입</button>
                    </div>
                </form>
                    
            </div>
            
            
        </div>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>

<script>
    // 로그인 모달

    const openBtn = document.querySelector("#login-modal-add-btn");
    const modal = document.querySelector("#modal");
    const closeBtn = document.querySelector("#close-btn");
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

    const currentUrl = document.querySelector('input[name=currentUrl]');
    url = window.location.pathname;
    currentUrl.value = url;

$(function(){
    $("li").slice(0, 6).show(); // 초기갯수
    $("#load").click(function(e){ // 클릭시 more
        e.preventDefault();
        $("li:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정
        if($("li:hidden").length == 0){ // 컨텐츠 남아있는지 확인
            // $("#load").attr("disabled",true);
            $("#load").remove();
        }
    });
});
</script>