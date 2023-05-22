<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <%@ include file="/WEB-INF/views/common/setup.jsp" %> --%>

<link rel="stylesheet" href="${root}/static/css/common/footer.css">

<footer>

    <div class="footer-area">

        <div class="footer-elem">
            <div class="footer-header cs-center-head bold">고객센터</div>
            <div class="cs-center-info regular">15:30~22:00(저녁시간 18:20~19:00)<br>주일,공휴일 휴무</div>
            <button class="cs-center-btn bold" onclick="location.href='${root}/inquiry'">1:1 문의</button>
        </div>

        <div class="footer-elem">
            <div class="footer-header bold">스킬마켓</div>
            <div class="footer-link light"><a href="${root}/home">스킬마켓 메인</a></div>
            <div class="footer-link light">인기 견적서</div>
            <div class="footer-link light"><a href="${root}/community/all">커뮤니티</a></div>
        </div>

        <div class="footer-elem">
            <div class="footer-header bold">스킬마켓 정보</div>
            <div class="footer-link light">서비스 소개</div>
            <div class="footer-link light">인재 영입</div>
        </div>

        <div class="footer-elem">
            <div class="footer-header bold">지원</div>
            <div class="light footer-link"><a href="${root}/notice">공지사항</a></div>
            <div class="footer-link light"><a href="${root}/faq">자주 묻는 질문</a></div>
            <div class="footer-link light">이용약관</div>
            <div class="footer-link light"><a href="${root}/csc">고객센터</a></div>
        </div>
        
    </div>

</footer>